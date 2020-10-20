package com.in_deal_pro.task.model;

import com.in_deal_pro.task.Memento;

import java.util.Arrays;

import static java.util.Arrays.stream;

public class Wall {

    private int [][] matrix;

    public Wall(int[][] matrix) {
        this.matrix = matrix;
    }

    public void restore(int[][] buckUp) {
        matrix = cloneMatrix(buckUp);
    }

    public Memento makeBackUp() {
        int [][] copyOfMatrix = cloneMatrix(matrix);
        return new Memento(this, copyOfMatrix);
    }

    private int[][] cloneMatrix(int[][] matrixForCopy) {
        return stream(matrixForCopy).map(int[]::clone).toArray(int[][]::new);
    }

    public Point getPointOnAreaForBrick(Brick brick) {
        Point nextPoint = getNextPointFromCurrent(brick.getLastPoint());
        for (int x = nextPoint.getX(); x < matrix.length; x++) {
            for (int y = nextPoint.getY(); y < matrix[x].length; y++) {
                Point currentPoint = new Point(x, y);
                if (isBlock(currentPoint)) {
                    if (brick.isEnoughMatrixSpaceFromPoint(matrix, currentPoint)) {
                        int[][] subMatrix = createSubMatrix(currentPoint, brick);
                        if (checkIfEveryBlockIsOne(subMatrix)) {
                            return new Point(x, y);
                        }
                    }
                }
            }
            nextPoint.setPointOnRowStart();
        }
        return null;
    }

    private Point getNextPointFromCurrent(Point point) {
        return point.isNextPointOutOfMatrixRowRange(matrix) ? point.onNextColumn() : point.onNextRow();
    }

    private boolean isBlock(Point p) {
        return matrixElementByPoint(p) == 1;
    }

    private int[][] createSubMatrix(Point p, Brick brick) {
        int width = brick.getWidth();
        int height = brick.getHeight();
        int [][] subMatrix = new int [height][width];
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                subMatrix[i][j] = matrix[p.getX() + i][p.getY() + j];
        return subMatrix;
    }

    private boolean checkIfEveryBlockIsOne(int[][] subMatrix) {
        return stream(subMatrix).flatMapToInt(Arrays::stream).allMatch(i -> i == 1);
    }

    public void setCoordinateWithZero(int x, int y) {
        matrix[x][y] = 0;
    }

    public boolean isWallFilled() {
        return stream(matrix).flatMapToInt(Arrays::stream).allMatch(i -> i == 0);
    }

    public int matrixElementByPoint(Point p) {
        return matrix[p.getX()][p.getY()];
    }

    public int[][] getMatrix() {
        return matrix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wall wall = (Wall) o;
        return Arrays.deepEquals(matrix, wall.matrix);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(matrix);
    }

    @Override
    public String toString() {
        return "Wall{" +
                " matrix=" + Arrays.deepToString(matrix) +
                '}';
    }
}
