package com.in_deal_pro.task.model;

import java.util.Objects;

public abstract class AbstractBrick implements Brick {

    protected final int width;
    protected final int height;

    protected BrickCount brickCount;
    protected Point lastPoint;

    public AbstractBrick(int width, int height, BrickCount brickCount) {
        this.width = width;
        this.height = height;
        this.brickCount = brickCount;
        lastPoint = new Point();
    }

    public void putIn(Wall wall) {
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++) {
                int x = lastX() + i;
                int y = lastY() + j;
                wall.setCoordinateWithZero(x, y);
            }
    }

    private int lastX() {
        return lastPoint.getX();
    }

    private int lastY() {
        return lastPoint.getY();
    }

    public boolean isEnoughMatrixSpaceFromPoint(int[][] matrix, Point point) {
        return  isEnoughMatrixSpace(point.getX(), height, matrix.length) &&
                isEnoughMatrixSpace(point.getY(), width, matrix[point.getX()].length);
    }

    private boolean isEnoughMatrixSpace(int index, int direction, int limit) {
        return index + direction <= limit;
    }


    @Override
    public int compareTo(Brick o) {
        int result = Integer.compare(o.getWidth() + o.getHeight(), width + height);
        if (result == 0)
            return Integer.compare(o.getHeight(), height);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractBrick brick = (AbstractBrick) o;
        return  width == brick.width &&
                height == brick.height &&
                brickCount == brick.brickCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height, brickCount);
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getCountOfSimilarBricks() {
        return brickCount.getCount();
    }

    public BrickCount getBrickCount() {
        return brickCount;
    }

    public void setCountOfSimilarBricks(int count) {
        brickCount.setCount(count);
    }

    public void decreaseBreaksByOne() {
        setCountOfSimilarBricks(getCountOfSimilarBricks() - 1);
    }

    public void increaseCountOfSimilarBricksByOne() {
        setCountOfSimilarBricks(getCountOfSimilarBricks() + 1);
    }

    public Point getLastPoint() {
        return lastPoint;
    }

    public void setLastPoint(Point point) {
        lastPoint = point;
    }

    public boolean isThereNOBricks() {
        return brickCount.getCount() <= 0;
    }
}
