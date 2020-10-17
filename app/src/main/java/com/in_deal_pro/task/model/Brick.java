package com.in_deal_pro.task.model;

import java.util.Objects;

public class Brick implements Comparable<Brick> {

    private int width;
    private int height;
    private int countOfSimilarBricks;
    private final int countOfBlocks;
    private int x = 0;
    private int y = -1;     //  for add possibility in findPlaceForBrick()
    private int swapX = 0;
    private int swapY = -1;

    private Point lastIndex = new Point(0, -1);
    private Point lastSwapIndex = new Point(0, -1);

    public Brick(int width, int height, int countOfSimilarBricks) {
        this.width = width;
        this.height = height;
        this.countOfSimilarBricks = countOfSimilarBricks;
        this.countOfBlocks = width * height;
    }

    @Override
    public int compareTo(Brick o) {
        int result = Integer.compare(o.width + o.height, width + height);
        if (result == 0)
            return Integer.compare(o.height, height);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brick bricks = (Brick) o;
        return countOfSimilarBricks == bricks.countOfSimilarBricks &&
                countOfBlocks == bricks.countOfBlocks &&
                width == bricks.width &&
                height == bricks.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(countOfSimilarBricks, countOfBlocks, width, height);
    }

    @Override
    public String toString() {
        return "Brick{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }

    public int getCountOfSimilarBricks() {
        return countOfSimilarBricks;
    }

    public int getCountOfBlocks() {
        return countOfBlocks;
    }

    public void setCountOfSimilarBricks(int countOfSimilarBricks) {
        this.countOfSimilarBricks = countOfSimilarBricks;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSwapX() {
        return swapX;
    }

    public int getSwapY() {
        return swapY;
    }

    public void setSwapX(int swapX) {
        this.swapX = swapX;
    }

    public void setSwapY(int swapY) {
        this.swapY = swapY;
    }
}
