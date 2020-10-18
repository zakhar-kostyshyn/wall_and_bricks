package com.in_deal_pro.task;

import com.in_deal_pro.task.model.Brick;
import com.in_deal_pro.task.model.Point;
import com.in_deal_pro.task.model.Wall;

import java.util.*;

public class Validator {

    public List<Brick> bricks;
    public Wall wall;
    public Deque<Memento> queue = new ArrayDeque<>();

    int brickIndex = 0;

    int width;
    int height;
    Brick currentBrick;

    int[][] matrix;
    int countOfBlocks;

    Point currentPoint = new Point();

    public Validator(Set<Brick> bricks, Wall wall) {
        this.bricks = new ArrayList<>(bricks);
        this.wall = wall;
    }

    public boolean check() {
        while (brickIndex < bricks.size()) {

            currentBrick = bricks.get(brickIndex);

            if (currentBrick.getCountOfSimilarBricks() == 0) {
                if (brickIndex + 1 != bricks.size()) {
                    brickIndex = brickIndex + 1;
                    currentBrick = bricks.get(brickIndex);
                } else {
                    if (queue.isEmpty())
                        return false;
                    rollBack();
                }
            }

            width = currentBrick.getWidth();
            height = currentBrick.getHeight();
            matrix = wall.getCurrentMatrix();
            countOfBlocks = wall.getCountOfBlocks();

            if (!isEnoughBlocks(brickIndex)) {
                if (queue.isEmpty())
                    return  false;
                rollBack();
                continue;
            }

            if (findPlace())
                makeStep();

            if (isWallFilled())
                return true;


            if (currentBrick.getCountOfSimilarBricks() <= 0 || !findPlace())
                brickIndex = brickIndex + 1;


            if (brickIndex == bricks.size()) {
                if (queue.isEmpty())
                    return false;
                rollBack();
            }
        }

        return false;
    }

    void rollBack() {
        Memento back = queue.pop();
        Brick poppedBrick = bricks.get(back.brickIndex);
        poppedBrick.increaseCountOfBricksByOne();
        matrix = Arrays.stream(back.currentMatrix).map(int[]::clone).toArray(int[][]::new);
        brickIndex = back.brickIndex;
    }

    boolean findPlace() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (isEqualsOne(i, j)) {
                    if (isEnoughAreaForBrick(i, j)) {
                        int[][] subMatrix = createSubMatrix(i, j);
                        boolean isAllOne = checkIfEveryBlockIsOne(subMatrix);
                        if (isAllOne) {
                            currentPoint.setX(i);
                            currentPoint.setY(j);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean isEqualsOne(int i, int j) {
        return matrix[i][j] == 1;
    }

    boolean isEnoughAreaForBrick(int row, int column) {
        return  isEnoughMatrixSpace(row, height, matrix.length) &&
                isEnoughMatrixSpace(column, width, matrix[row].length);
    }

    boolean isEnoughMatrixSpace(int index, int direction, int limit) {
        return index + direction <= limit;
    }

    public int[][] createSubMatrix(int i, int j) {
        int [][] subMatrix = new int [height][width];
        for (int ii = 0; ii < height; ii++)
            for (int jj = 0; jj < width; jj++)
                subMatrix[ii][jj] = matrix[i + ii][j + jj];
        return subMatrix;
    }

    public boolean checkIfEveryBlockIsOne(int[][] subMatrix) {
        return Arrays.stream(subMatrix).flatMapToInt(Arrays::stream).allMatch(i -> i == 1);
    }

    public void makeStep() {
        createSnapshot();
        changeCurrentBrickCoordinatesAndDecreaseItCount();
        fillMatrixWithCurrentBrick();
    }

    public void createSnapshot() {
        int [][] copyMatrix = Arrays.stream(matrix).map(int[]::clone).toArray(int[][]::new);
        Memento memento = new Memento(brickIndex, copyMatrix, countOfBlocks);
        queue.push(memento);
    }

    void changeCurrentBrickCoordinatesAndDecreaseItCount() {
        currentBrick.decreaseCountOfBricksByOne();
    }

    void fillMatrixWithCurrentBrick() {
        int x = currentPoint.getX();
        int y = currentPoint.getY();
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                matrix[x + i][y + j] = 0;
        wall.decreaseCountOfBlocksByBrickSize(currentBrick.getCountOfBlocks());
    }

    public boolean isEnoughBlocks(int index) {
        return countOfBlocksInSublist(index) >= wall.getCountOfBlocks();
    }

    public int countOfBlocksInSublist(int index) {
        return bricks.subList(index, bricks.size()).stream()
                .mapToInt(brick -> brick.getCountOfSimilarBricks() * brick.getCountOfBlocks())
                .sum();
    }

    public boolean isWallFilled() {
        return Arrays.stream(wall.getCurrentMatrix()).flatMapToInt(Arrays::stream).allMatch(i -> i == 0);
    }


    public List<Brick> getBricks() {
        return bricks;
    }

    static class Memento {

        public int brickIndex;
        public int [][] currentMatrix;
        public int countOfBlocks;

        public Memento(int brickIndex, int[][] currentMatrix, int countOfBlocks) {
            this.brickIndex = brickIndex;
            this.currentMatrix = currentMatrix;
            this.countOfBlocks = countOfBlocks;
        }
    }

}

