package com.in_deal_pro.task.model;

public interface Brick extends Comparable<Brick> {

    int getWidth();
    int getHeight();
    Point getLastPoint();
    void setLastPoint(Point point);
    BrickCount getBrickCount();
    int getCountOfSimilarBricks();
    void setCountOfSimilarBricks(int count);
    void increaseCountOfSimilarBricksByOne();
    void decreaseBreaksByOne();
    void putIn(Wall wall);
    boolean isThereNOBricks();
    boolean isEnoughMatrixSpaceFromPoint(int [][] matrix, Point point);
}
