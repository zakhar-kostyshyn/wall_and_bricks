package com.in_deal_pro.task.model;

public interface Brick extends Comparable<Brick> {

    int getCountOfBlocks();
    int getWidth();
    int getHeight();
    String toString();
    void decreaseCountOfSimilarBricksByOne();
    Point getLastPoint();
    void setLastPoint(Point point);
    SimilarBlocks getSimilarBlocks();
    int getCountOfSimilarBricks();
    void setCountOfSimilarBricks(int count);
    void increaseCountOfSimilarBricksByOne();


    boolean isThereBricks();
}
