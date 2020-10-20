package com.in_deal_pro.task.model;

import java.util.Objects;

public class Point {

    int x;
    int y;

    public Point() {
        this(0, -1);
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isNextPointOutOfMatrixRowRange(int[][] matrix) {
        return y + 1 <= matrix[x].length;
    }

    public Point onNextColumn() {
        return new Point(x, y + 1);
    }

    public Point onNextRow() {
        return new Point(x + 1, 0);
    }

    public void setPointOnRowStart() {
        setY(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
