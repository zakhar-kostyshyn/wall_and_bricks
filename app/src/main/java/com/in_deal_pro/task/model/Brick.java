package com.in_deal_pro.task.model;

public interface Brick extends Comparable<Brick> {

    int getCountOfBlocks();
    int getWidth();
    int getHeight();
    Point getLastIndex();
    void setLastIndex(Point point);
    String toString();
    void decreaseCountOfBricksByOne();
    void increaseCountOfBricksByOne();

    SimilarBlocks getSimilarBlocks();
    int getCountOfSimilarBricks();
    void setCountOfSimilarBricks(int count);

}
