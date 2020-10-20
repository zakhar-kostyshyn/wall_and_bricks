package com.in_deal_pro.task;

import com.in_deal_pro.task.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WallTest {

    int [][] matrix =
            {
                    {1, 0, 1, 1, 0, 1},
                    {1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1}
            };

    Wall wall = new Wall(matrix);
    Brick brick = new NormalBrick(3, 1, new BrickCount(1));

    public void trueWhen(Point brickPoint, Point point) {
        brick.setLastPoint(brickPoint);
        Point actual = wall.getPointOnAreaForBrick(brick);
        assertNotNull(actual);
        assertEquals(point, actual);
    }

    @Test
    void setPointInBrickOnWallAreaTest() {

        trueWhen(new Point(0, -1), new Point(1, 0));
        trueWhen(new Point(1, 0), new Point(1, 1));
        trueWhen(new Point(2, 0), new Point(2, 1));
        trueWhen(new Point(2, 1), new Point(2, 2));
        trueWhen(new Point(2, 2), new Point(2, 3));

        brick = new NormalBrick(1, 1, new BrickCount(1));

        trueWhen(new Point(1, 0), new Point(1, 1));
        trueWhen(new Point(1, 1), new Point(1, 2));
        trueWhen(new Point(1, 2), new Point(1, 3));
        trueWhen(new Point(1, 3), new Point(1, 4));
        trueWhen(new Point(1, 4), new Point(1, 5));
    }


}
