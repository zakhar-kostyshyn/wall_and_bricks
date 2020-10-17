package com.in_deal_pro.task;

import com.in_deal_pro.task.model.Brick;
import com.in_deal_pro.task.model.Wall;

import java.util.*;

public class Validator {

    public List<Brick> bricks;
    public Wall wall;
    public Deque<Memento> queue = new ArrayDeque<>();

    int brickIndex = 0;


    public Validator(Set<Brick> bricks, Wall wall) {
        this.bricks = new ArrayList<>(bricks);
        this.wall = wall;
    }

    public boolean check() {
        while (brickIndex < bricks.size()) {
            Brick curre = bricks.get(brickIndex);

            if (!isEnoughBlocks(brickIndex)) {
                //  roll back
                if (queue.isEmpty())
                    return  false;
                Memento back = queue.pop();
                //  roll back state of brick and matrix back then decrease index
                Brick poppedBrick = bricks.get(back.brickIndex);
                poppedBrick.setCountOfSimilarBricks(poppedBrick.getCountOfSimilarBricks() + 1);
                wall.setCurrentMatrix(back.currentMatrix);
                brickIndex = back.brickIndex;
                continue;
            }

            //  find place or swap place
            int width = curre.getWidth();
            int height = curre.getHeight();
            int[] place = findPlaceForBrick(curre);
            int[] swapPlace = findPlaceForSwapBrick(curre);

            int x;
            int y;
            int swapX;
            int swapY;
            if (place == null) {
                x = curre.getX();
                y = curre.getY();
                if (swapPlace == null) {
                    swapX = curre.getSwapX();
                    swapY = curre.getSwapY();
                } else {
                    swapX = swapPlace[0];
                    swapY = swapPlace[1];
                }
            } else {
                x = place[0];
                y = place[1];
                swapX = curre.getSwapX();
                swapY = curre.getSwapY();
            }

            //  change matrix prime
            if (place != null)
                makeStep(x, y, swapX, swapY, height, width, brickIndex, true);

            //  change matrix prime
            if (swapPlace != null && place == null)
                makeStep(x, y, swapX, swapY, height, width, brickIndex, false);

            if (isWallFilled())
                return true;

            //  rerun same brick if there are place and brick
            if (curre.getCountOfSimilarBricks() != 0 && (findPlaceForBrick(curre) != null || findPlaceForSwapBrick(curre) != null))
                brickIndex--;

            //  roll back if end of bricks but wall is not filled
            if (brickIndex + 1 == bricks.size()) {
                if (queue.isEmpty())
                    return  false;
                Memento back = queue.pop();
                //  roll back state of brick and matrix back then decrease index
                Brick poppedBrick = bricks.get(back.brickIndex);
                poppedBrick.setCountOfSimilarBricks(poppedBrick.getCountOfSimilarBricks() + 1);
                wall.setCurrentMatrix(back.currentMatrix);
                brickIndex = back.brickIndex;
                continue;
            }
            brickIndex++;    //  because no need increment when roll back to same brick in the end
        }

        return false;
    }

    public boolean isWallFilled() {
        return Arrays.stream(wall.getCurrentMatrix()).flatMapToInt(Arrays::stream).allMatch(i -> i == 0);
    }

    public void makeStep(int x, int y, int swapX, int swapY, int height, int width, int brickIndex, boolean swapFlag) {

        //  save
        int[][] matrix = wall.getCurrentMatrix();
        int [][] copyMatrix = Arrays.stream(matrix).map(int[]::clone).toArray(int[][]::new);

        Memento memento = new Memento(x, y, swapX, swapY, brickIndex, copyMatrix, wall.getCountOfBlocks());
        queue.push(memento);

        //  decrement and save brick coordinates
        Brick brick = bricks.get(brickIndex);
        brick.setCountOfSimilarBricks(brick.getCountOfSimilarBricks() - 1);
        brick.setSwapX(swapX);
        brick.setY(swapY);
        brick.setX(x);
        brick.setY(y);

        // change matrix
        if (swapFlag) {
            for (int i = 0; i < height; i++)
                for (int j = 0; j < width; j++)
                    matrix[x + i][y + j] = 0;
        } else {
            for (int i = 0; i < width; i++)
                for (int j = 0; j < height; j++)
                    matrix[swapX + i][swapY + j] = 0;
        }
        wall.setCurrentMatrix(matrix);
        wall.setCountOfBlocks(wall.getCountOfBlocks() - height * width);
    }


    public boolean isEnoughBlocks(int index) {
        List<Brick> sublist = bricks.subList(index, bricks.size());
        int countOfBlocks = sublist.stream()
                .mapToInt(brick -> brick.getCountOfSimilarBricks() * brick.getCountOfBlocks())
                .sum();
        return countOfBlocks >= wall.getCountOfBlocks();
    }

    public int[] findPlaceForSwapBrick(Brick brick) {
        //  swap width and height
        int width = brick.getHeight();
        int height = brick.getWidth();
        int x = brick.getSwapX();
        int y = brick.getSwapY();
        int [][] matrix = wall.getCurrentMatrix();
        //  set x and y with purpose not take the same place as the last time
        if (y + 1 >= matrix[x].length) {
            x++;
            y = 0;
        } else y++;
        for (int i = x; i < matrix.length; i++) {
            for (int j = y; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    if ((i + height <= matrix.length) && (j + width <= matrix[i].length)) {
                        int[][] subMatrix = createSubMatrix(i, j, height, width, matrix);
                        boolean isAllOne = checkIfEveryBlockIsOne(subMatrix);
                        if (isAllOne)
                            return new int[] {i, j};
                    }
                }
            }
            y = 0;
        }
        return null;
    }

    public int[] findPlaceForBrick(Brick brick) {
        int width = brick.getWidth();
        int height = brick.getHeight();
        int x = brick.getX();
        int y = brick.getY();
        int [][] matrix = wall.getCurrentMatrix();
        //  set x and y with purpose not take the same place as the last time
        if (y + 1 >= matrix[x].length) {
            x++;
            y = 0;
        } else y++;
        for (int i = x; i < matrix.length; i++) {
            for (int j = y; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    if ((i + height <= matrix.length) && (j + width <= matrix[i].length)) {
                        int[][] subMatrix = createSubMatrix(i, j, height, width, matrix);
                        boolean isAllOne = checkIfEveryBlockIsOne(subMatrix);
                        if (isAllOne)
                            return new int[] {i, j};
                    }
                }
            }
            y = 0;
        }
        return null;
    }

    public int[][] createSubMatrix(int i, int j, int height, int width, int [][] matrix) {
        int [][] subMatrix = new int [height][width];
        for (int ii = 0; ii < height; ii++)
            for (int jj = 0; jj < width; jj++)
                subMatrix[ii][jj] = matrix[i + ii][j + jj];
        return subMatrix;
    }

    public boolean checkIfEveryBlockIsOne(int[][] subMatrix) {
        return Arrays.stream(subMatrix).flatMapToInt(Arrays::stream).allMatch(i -> i == 1);
    }


    public List<Brick> getBricks() {
        return bricks;
    }

    static class Memento {

        public int x;
        public int y;
        public int swapX;
        public int swapY;
        public int brickIndex;
        public int [][] currentMatrix;
        public int countOfBlocks;

        public Memento(int x, int y, int swapX, int swapY, int brickIndex, int[][] currentMatrix, int countOfBlocks) {
            this.x = x;
            this.y = y;
            this.swapX = swapX;
            this.swapY = swapY;
            this.brickIndex = brickIndex;
            this.currentMatrix = currentMatrix;
            this.countOfBlocks = countOfBlocks;
        }
    }
}

