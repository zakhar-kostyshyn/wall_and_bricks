package com.in_deal_pro.task;

import com.in_deal_pro.task.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BrickTest {

    int[][] testMatrix = new int[][]
            {
                    {1, 0, 1, 1, 0, 1},
                    {1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1}
            };
    Brick testBrick = new NormalBrick(3, 2, new BrickCount(1));

    public void trueWhen(Point point) {
        assertTrue(testBrick.isEnoughMatrixSpaceFromPoint(testMatrix, point));
    }

    public void falseWhen(Point point) {
        assertFalse(testBrick.isEnoughMatrixSpaceFromPoint(testMatrix, point));
    }


    @Test
    void isEnoughMatrixSpaceFromPointTest() {

        trueWhen(new Point(0, 0));
        trueWhen(new Point(1, 1));
        trueWhen(new Point(1, 3));

        falseWhen(new Point(2, 0));
        falseWhen(new Point(4, 2));
        falseWhen(new Point(4, 1));

    }

    @Test
    void putInTest() {
        Wall wall = new Wall(testMatrix);
        Brick brick = new NormalBrick(2, 2, new BrickCount(1));
        brick.setLastPoint(new Point(0, 2));
        brick.putIn(wall);
        int[][] expectedMatrix =
                {
                        {1, 0, 0, 0, 0, 1},
                        {1, 1, 0, 0, 1, 1},
                        {1, 1, 1, 1, 1, 1}
                };
        assertArrayEquals(expectedMatrix, wall.getMatrix());

        brick.setLastPoint(new Point(1, 0));
        brick.putIn(wall);
        expectedMatrix = new int[][]
                {
                        {1, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 1, 1},
                        {0, 0, 1, 1, 1, 1}
                };
        assertArrayEquals(expectedMatrix, wall.getMatrix());

        brick.setLastPoint(new Point(1, 4));
        brick.putIn(wall);
        expectedMatrix = new int[][]
                {
                        {1, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0},
                        {0, 0, 1, 1, 0, 0}
                };
        assertArrayEquals(expectedMatrix, wall.getMatrix());
    }

}
