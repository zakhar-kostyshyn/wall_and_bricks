package com.in_deal_pro.task;

import com.in_deal_pro.task.model.*;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;


public class TestCheck {

    @Test
    void earlyAndSimpleCheckTest() {
        int [][] matrix =
                {
                        {1, 0, 1, 1, 0, 1},
                        {1, 1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1, 1}
                };

        Set<Brick> bricks = new TreeSet<>();
        var brick3 = new NormalBrick(3, 1, new BrickCount(1));
        var brick1 = new NormalBrick(1, 1, new BrickCount(4));
        var brick2 = new NormalBrick(2, 1, new BrickCount(6));

        bricks.add(brick1);
        bricks.add(brick2);
        bricks.add(brick3);
        bricks.add(new SwappedBrick(brick1));
        bricks.add(new SwappedBrick(brick2));
        bricks.add(new SwappedBrick(brick3));

        Validator validator = new Validator(bricks, new Wall(matrix));
        assertTrue(validator.check());
    }

    @Test
    void earlyAndSimpleCheckTest3() {
        int [][] matrix =
                {
                        {1, 1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1, 1}
                };
        Set<Brick> bricks = new TreeSet<>();
        bricks.add(new NormalBrick(3, 3, new BrickCount(2)));
        bricks.add(new NormalBrick(3, 3, new BrickCount(2)));
        Validator validator = new Validator(bricks, new Wall(matrix));
        assertTrue(validator.check());
    }

    @Test
    void earlyAndSimpleCheckTest4() {
        int [][] matrix =
                {
                        {1, 1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1, 1}
                };

        Set<Brick> bricks = new TreeSet<>();
        bricks.add(new NormalBrick(6, 3, new BrickCount(1)));
        bricks.add(new NormalBrick(3, 6, new BrickCount(1)));
        Validator validator = new Validator(bricks, new Wall(matrix));
        assertTrue(validator.check());
    }

    @Test
    void earlyAndSimpleCheckTest2() {
        int [][] matrix =
                {
                        {1, 1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1, 1}
                };

        Set<Brick> bricks = new TreeSet<>();
        bricks.add(new NormalBrick(3, 6, new BrickCount(1)));
        bricks.add(new NormalBrick(6, 3, new BrickCount(1)));
        Validator validator = new Validator(bricks, new Wall(matrix));
        assertTrue(validator.check());
    }

    @Test
    void earlyAndSimpleCheckTest5() {
        int [][] matrix =
                {
                        {0, 0, 1, 1, 0, 0},
                        {1, 1, 0, 0, 1, 1},
                        {1, 1, 1, 1, 1, 1}
                };
        Set<Brick> bricks = new TreeSet<>();

        var brick3 = new NormalBrick(2, 2, new BrickCount(1));
        var brick1 = new NormalBrick(3, 1, new BrickCount(1));
        var brick2 = new NormalBrick(4, 1, new BrickCount(1));

        bricks.add(brick1);
        bricks.add(brick2);
        bricks.add(brick3);
        bricks.add(new SwappedBrick(brick1));
        bricks.add(new SwappedBrick(brick2));
        bricks.add(new SwappedBrick(brick3));


        Validator validator = new Validator(bricks, new Wall(matrix));
        assertFalse(validator.check());
    }

    @Test
    void earlyAndSimpleCheckTest6() {
        int [][] matrix =
                {
                        {0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 1},
                        {1, 1, 1, 1, 1, 0, 1},
                        {1, 1, 0, 1, 1, 0, 1},
                        {1, 1, 0, 0, 0, 0, 1},
                        {1, 1, 0, 1, 1, 1, 1}
                };

        Set<Brick> bricks = new TreeSet<>();

        var brick1 = new NormalBrick(6, 3, new BrickCount(2));
        var brick2 = new NormalBrick(7, 1, new BrickCount(2));
        var brick3 = new NormalBrick(3, 2, new BrickCount(2));
        var brick4 = new NormalBrick(2, 2, new BrickCount(3));
        var brick5 = new NormalBrick(2, 1, new BrickCount(3));

        bricks.add(brick1);
        bricks.add(brick2);
        bricks.add(brick3);
        bricks.add(brick4);
        bricks.add(brick5);
        bricks.add(new SwappedBrick(brick1));
        bricks.add(new SwappedBrick(brick2));
        bricks.add(new SwappedBrick(brick3));
        bricks.add(new SwappedBrick(brick4));
        bricks.add(new SwappedBrick(brick5));

        Validator validator = new Validator(bricks, new Wall(matrix));
        assertFalse(validator.check());
    }

    @Test
    void earlyAndSimpleCheckTest7() {
        int [][] matrix =
                {
                        {0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 1},
                        {1, 1, 1, 1, 1, 0, 1},
                        {1, 1, 0, 1, 1, 0, 1},
                        {1, 1, 0, 0, 0, 0, 1},
                        {1, 1, 0, 1, 1, 1, 1}
                };

        Set<Brick> bricks = new TreeSet<>();

        var brick1 = new NormalBrick(6, 3, new BrickCount(2));
        var brick2 = new NormalBrick(7, 1, new BrickCount(2));
        var brick3 = new NormalBrick(3, 2, new BrickCount(2));
        var brick4 = new NormalBrick(2, 2, new BrickCount(3));
        var brick5 = new NormalBrick(2, 1, new BrickCount(3));
        var brick6 = new NormalBrick(1, 1, new BrickCount(2));

        bricks.add(brick1);
        bricks.add(brick2);
        bricks.add(brick3);
        bricks.add(brick4);
        bricks.add(brick5);
        bricks.add(brick6);
        bricks.add(new SwappedBrick(brick1));
        bricks.add(new SwappedBrick(brick2));
        bricks.add(new SwappedBrick(brick3));
        bricks.add(new SwappedBrick(brick4));
        bricks.add(new SwappedBrick(brick5));
        bricks.add(new SwappedBrick(brick6));

        Validator validator = new Validator(bricks, new Wall(matrix));
        assertTrue(validator.check());
    }



}
