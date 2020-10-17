package com.in_deal_pro.task.model;

import java.util.Arrays;
import java.util.Objects;

public class Wall {

    private int [][] currentMatrix;
    private int countOfBlocks;

    public Wall(int[][] currentMatrix, int countOfBlocks) {
        this.currentMatrix = currentMatrix;
        this.countOfBlocks = countOfBlocks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wall wall = (Wall) o;
        return countOfBlocks == wall.countOfBlocks &&
                Arrays.deepEquals(currentMatrix, wall.currentMatrix);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(countOfBlocks);
        result = 31 * result + Arrays.deepHashCode(currentMatrix);
        return result;
    }

    @Override
    public String toString() {
        return "Wall{" +
                "countOfBlocks=" + countOfBlocks +
                ", currentMatrix=" + Arrays.deepToString(currentMatrix) +
                '}';
    }

    public int[][] getCurrentMatrix() {
        return currentMatrix;
    }

    public void setCurrentMatrix(int[][] currentMatrix) {
        this.currentMatrix = currentMatrix;
    }

    public int getCountOfBlocks() {
        return countOfBlocks;
    }

    public void setCountOfBlocks(int countOfBlocks) {
        this.countOfBlocks = countOfBlocks;
    }
}
