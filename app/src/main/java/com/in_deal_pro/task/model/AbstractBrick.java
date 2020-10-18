package com.in_deal_pro.task.model;

import java.util.Objects;

public abstract class AbstractBrick implements Brick {

    protected final int width;
    protected final int height;
    protected final int countOfBlocks;

    protected SimilarBlocks similarBlocks;
    protected Point lastIndex;

    public AbstractBrick(int width, int height, SimilarBlocks similarBlocks) {
        this.width = width;
        this.height = height;
        this.similarBlocks = similarBlocks;
        this.countOfBlocks = width * height;
        this.lastIndex = new Point();
    }

    @Override
    public int compareTo(Brick o) {
        int result = Integer.compare(o.getWidth() + o.getHeight(), width + height);
        if (result == 0)
            return Integer.compare(o.getWidth(), width);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractBrick brick = (AbstractBrick) o;
        return  width == brick.width &&
                height == brick.height &&
                similarBlocks == brick.similarBlocks &&
                countOfBlocks == brick.countOfBlocks &&
                lastIndex.equals(brick.lastIndex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height, similarBlocks, countOfBlocks, lastIndex);
    }

    @Override
    public String toString() {
        return "Brick{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }


    public int getCountOfBlocks() {
        return countOfBlocks;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Point getLastIndex() {
        return lastIndex;
    }

    public void setLastIndex(Point lastIndex) {
        this.lastIndex = lastIndex;
    }

    public int getCountOfSimilarBricks() {
        return similarBlocks.getCount();
    }

    public SimilarBlocks getSimilarBlocks() {
        return similarBlocks;
    }

    public void setCountOfSimilarBricks(int count) {
        similarBlocks.setCount(count);
    }

    public void decreaseCountOfBricksByOne() {
        this.setCountOfSimilarBricks(this.getCountOfSimilarBricks() - 1);
    }

    public void increaseCountOfBricksByOne() {
        this.setCountOfSimilarBricks(this.getCountOfSimilarBricks() + 1);
    }
}
