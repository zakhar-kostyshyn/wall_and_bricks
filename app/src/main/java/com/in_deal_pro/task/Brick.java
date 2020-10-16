package com.in_deal_pro.task;

import java.util.Objects;

public class Brick {

    private final int countOfSimilarBricks;
    private final int countOfBlocks;
    private final int width;
    private final int height;

    public Brick(int width, int height, int countOfSimilarBricks) {
        this.width = width;
        this.height = height;
        this.countOfSimilarBricks = countOfSimilarBricks;
        this.countOfBlocks = width * height;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brick brick = (Brick) o;
        return countOfSimilarBricks == brick.countOfSimilarBricks &&
                countOfBlocks == brick.countOfBlocks &&
                width == brick.width &&
                height == brick.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(countOfSimilarBricks, countOfBlocks, width, height);
    }

    @Override
    public String toString() {
        return "Brick{" +
                "countOfSimilarBricks=" + countOfSimilarBricks +
                ", countOfBlocks=" + countOfBlocks +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
