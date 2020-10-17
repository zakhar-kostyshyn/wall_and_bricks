package com.in_deal_pro.task;

import com.in_deal_pro.task.model.Brick;
import com.in_deal_pro.task.model.Wall;
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
        bricks.add(new Brick(1, 1, 4));
        bricks.add(new Brick(2, 1, 6));
        bricks.add(new Brick(3, 1, 1));

        Validator validator = new Validator(bricks, new Wall(matrix, 16));
        assertTrue(validator.check());
    }

    @Test
    void earlyAndSimpleCheckTest2() {
        int [][] matrix =
                {
                        {0, 0, 1, 1, 0, 0},
                        {1, 1, 0, 0, 1, 1},
                        {1, 1, 1, 1, 1, 1}
                };
        Set<Brick> bricks = new TreeSet<>();
        bricks.add(new Brick(2, 2, 1));
        bricks.add(new Brick(3, 1, 1));
        bricks.add(new Brick(4, 1, 1));

        Validator validator = new Validator(bricks, new Wall(matrix, 12));
        assertFalse(validator.check());
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
        bricks.add(new Brick(3, 3, 2));
        Validator validator = new Validator(bricks, new Wall(matrix, 18));
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
        bricks.add(new Brick(6, 3, 1));
        Validator validator = new Validator(bricks, new Wall(matrix, 18));
        assertTrue(validator.check());
    }

    @Test
    void earlyAndSimpleCheckTest5() {
        int [][] matrix =
                {
                        {1, 1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1, 1}
                };

        Set<Brick> bricks = new TreeSet<>();
        bricks.add(new Brick(3, 6, 1));
        Validator validator = new Validator(bricks, new Wall(matrix, 18));
        assertTrue(validator.check());
    }


//    @Test
//    void earlyAndSimpleCheckTest6() {
//        int [][] matrix =
//                {
//                        {0, 0, 0, 0, 0, 0, 1},
//                        {0, 0, 0, 0, 0, 0, 1},
//                        {0, 0, 0, 0, 0, 0, 1},
//                        {1, 1, 1, 1, 1, 0, 1},
//                        {1, 1, 0, 1, 1, 0, 1},
//                        {1, 1, 0, 0, 0, 0, 1},
//                        {1, 1, 0, 1, 1, 1, 1}
//                };
//
//        Set<Brick> bricks = new TreeSet<>();
//        bricks.add(new Brick(6, 3, 2));
//        bricks.add(new Brick(7, 1, 2));
//        bricks.add(new Brick(3, 2, 2));
//        bricks.add(new Brick(2, 2, 3));
//        bricks.add(new Brick(2, 1, 3));
//
//        Validator validator = new Validator(bricks, new Wall(matrix, 18));
//        assertFalse(validator.check());
//    }


}
