package com.in_deal_pro.task;

import com.in_deal_pro.task.model.Brick;
import com.in_deal_pro.task.model.Wall;

import java.util.*;

public class Validator {

    public List<Brick> bricks;
    public Wall wall;
    public Deque<Memento> queue = new ArrayDeque<>();


    public Validator(Set<Brick> bricks, Wall wall) {
        this.bricks = new ArrayList<>(bricks);
        this.wall = wall;
    }

    public boolean check() {
        for (int i = 0; i < bricks.size(); i++) {
            Brick brick = bricks.get(i);

            if (!isEnoughBlocks(i)) {
                //  step back
                return false;
            }
            int width = brick.getWidth();
            int height = brick.getHeight();
            int[] place = findPlaceForBrick(brick);

            if (place == null) {
                //  step back
                return false;
            }

            int x = place[0];
            int y = place[1];

            makeStep(x, y, height, width, i);

        }

        return false;
    }

    public void makeStep(int x, int y, int height, int width, int brickIndex) {

        //  save

        int[][] matrix = wall.getCurrentMatrix();
        int [][] copyMatrix = Arrays.stream(matrix).map(int[]::clone).toArray(int[][]::new);


        Memento memento = new Memento(x, y, brickIndex, copyMatrix, wall.getCountOfBlocks());
        queue.push(memento);

        //  decrement and save brick coordinates
        Brick brick = bricks.get(brickIndex);
        brick.setCountOfSimilarBricks(brick.getCountOfSimilarBricks() - 1);
        brick.setX(x);
        brick.setY(y);

        // change matrix
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                matrix[x + i][y + j] = 0;
        wall.setCurrentMatrix(matrix);
        wall.setCountOfBlocks(wall.getCountOfBlocks() - height * width);
    }


    public boolean isEnoughBlocks(int index) {
        List<Brick> sublist = bricks.subList(index, bricks.size());
        int countOfBlocks = sublist.stream().mapToInt(Brick::getCountOfBlocks).sum();
        return countOfBlocks >= wall.getCountOfBlocks();
    }

    public int[] findPlaceForBrick(Brick brick) {
        int width = brick.getWidth();
        int height = brick.getHeight();
        int [][] matrix = wall.getCurrentMatrix();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1) {
                    if ((i + height <= matrix.length) && (j + width <= matrix[i].length)) {
                        int[][] subMatrix = createSubMatrix(i, j, height, width, matrix);
                        boolean isAllOne = checkIfEveryBlockIsOne(subMatrix);
                        if (isAllOne)
                            return new int[] {i, j};
                    }
                }
            }
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
        public int brickIndex;
        public int [][] currentMatrix;
        public int countOfBlocks;

        public Memento(int x, int y, int brickIndex, int[][] currentMatrix, int countOfBlocks) {
            this.x = x;
            this.y = y;
            this.brickIndex = brickIndex;
            this.currentMatrix = currentMatrix;
            this.countOfBlocks = countOfBlocks;
        }
    }
}

