package com.in_deal_pro.task;

import com.in_deal_pro.task.model.Brick;
import com.in_deal_pro.task.model.Wall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    int [][] matrix =
            {
                    {1, 0, 1, 1, 0, 1},
                    {1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1}
            };

    Validator validator;

    @BeforeEach
    void setUp() {

        Set<Brick> bricks = new TreeSet<>();
        bricks.add(new Brick(1, 1, 4));
        bricks.add(new Brick(2, 1, 6));
        bricks.add(new Brick(1, 3, 1));
        bricks.add(new Brick(3, 4, 5));

        validator = new Validator(bricks, new Wall(matrix, 16));
    }

    @Test
    void isListSortedRight() {
        List<Brick> expected = List.of(
                new Brick(3, 4, 5),
                new Brick(1, 3, 1),
                new Brick(2, 1, 6),
                new Brick(1, 1, 4)
        );
        List<Brick> actual = validator.getBricks();
        assertEquals(expected, actual);
    }

    @Test
    void isEnoughBlocks() {
//        assertTrue(validator.isEnoughBlocks(0));
//        assertFalse(validator.isEnoughBlocks(1));
//        assertFalse(validator.isEnoughBlocks(2));
//
//        Set<Brick> bricks = Set.of(
//                new Brick(1, 1, 5),
//                new Brick(6, 5, 1)
//        );
//        Validator test = new Validator(bricks, new Wall(matrix, 16));
//
//        assertTrue(test.isEnoughBlocks(0));
//        assertTrue(test.isEnoughBlocks(1));
//        assertFalse(test.isEnoughBlocks(2));

    }

   @Test
    void checkIfEveryBlockIsOneTest() {
       int [][] matrix =
               {
                       {1, 1, 1, 1, 1, 1},
                       {1, 1, 1, 1, 1, 1},
                       {1, 1, 1, 1, 1, 1}
               };
       boolean check = validator.checkIfEveryBlockIsOne(matrix);
       assertTrue(check);

       matrix =
               new int[][] {
                       {1, 1, 1, 1, 1, 1},
                       {1, 1, 1, 1, 1, 1},
                       {1, 1, 1, 1, 1, 1},
                       {1, 1, 1, 1, 1, 1},
                       {1, 1, 1, 1, 1, 1},
                       {1, 1, 1, 1, 1, 1}
               };
       check = validator.checkIfEveryBlockIsOne(matrix);
       assertTrue(check);

       matrix = new int[][] {
               {1, 1, 1, 1, 1, 1},
               {1, 1, 1, 0, 1, 1},
               {1, 1, 1, 1, 1, 1},
               {1, 1, 1, 1, 1, 1},
               {1, 1, 1, 1, 1, 1},
               {1, 1, 1, 1, 1, 1}
       };
       check = validator.checkIfEveryBlockIsOne(matrix);
       assertFalse(check);
    }

    @Test
    void createSubMatrixTest() {

        int [][] matrix =
                {
                        {0, 1, 2, 2, 1, 1},
                        {1, 1, 4, 1, 6, 1},
                        {2, 1, 5, 6, 7, 1},
                        {1, 4, 4, 3, 0, 9}
                };

        int[][] expected =
                {
                        {2, 2},
                        {4, 1},
                        {5, 6}
                };
        int[][] actual = validator.createSubMatrix(0, 2, 3, 2, matrix);
        assertTrue(Arrays.deepEquals(expected, actual));

        expected =
                new int[][] {
                        {1, 4, 1, 6, 1},
                        {1, 5, 6, 7, 1},
                        {4, 4, 3, 0, 9}
                };
        actual = validator.createSubMatrix(1, 1, 3, 5, matrix);
        assertTrue(Arrays.deepEquals(expected, actual));

        actual = validator.createSubMatrix(0, 0, 4, 6, matrix);
        assertTrue(Arrays.deepEquals(matrix, actual));
    }

    @Test
    void findPlaceForSwapBrickTest() {

/*
        {1, 0, 1, 1, 0, 1},
        {1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1}
*/

        Brick brick = new Brick(2, 3, 4);

        int[] expected = {1, 0};
        int[] actual = validator.findPlaceForSwapBrick(brick);
        assertArrayEquals(expected, actual);

        brick.setSwapX(1);
        brick.setSwapY(0);
        expected = new int[] {1, 1};
        actual = validator.findPlaceForSwapBrick(brick);
        assertArrayEquals(expected, actual);

        brick.setSwapX(1);
        brick.setSwapY(2);
        expected = new int[] {1, 3};
        actual = validator.findPlaceForSwapBrick(brick);
        assertArrayEquals(expected, actual);

        brick.setSwapX(1);
        brick.setSwapY(3);
        actual = validator.findPlaceForSwapBrick(brick);
        assertNull(actual);
    }

    @Test
    void findPlaceForBrickTest() {

/*
        {1, 0, 1, 1, 0, 1},
        {1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1}
*/

        Brick brick1 = new Brick(1, 1, 4);
        Brick brick2 = new Brick(2, 3, 4);
        Brick brick3 = new Brick(6, 1, 4);

        int[] expected = {0, 2};
        int[] actual = validator.findPlaceForBrick(brick2);
        assertArrayEquals(expected, actual);

        expected = new int[] {0, 0};
        actual = validator.findPlaceForBrick(brick1);
        assertArrayEquals(expected, actual);

        expected = new int[] {1, 0};
        actual = validator.findPlaceForBrick(brick3);
        assertArrayEquals(expected, actual);

        // test get next place if x and y not standart
        brick1.setX(2);
        brick1.setY(4);
        expected = new int[] {2, 5};
        actual = validator.findPlaceForBrick(brick1);
        assertArrayEquals(expected, actual);

        brick1.setX(1);
        brick1.setY(5);
        expected = new int[] {2, 0};
        actual = validator.findPlaceForBrick(brick1);
        assertArrayEquals(expected, actual);

        brick3.setX(1);
        brick3.setY(0);
        expected = new int[] {2, 0};
        actual = validator.findPlaceForBrick(brick3);
        assertArrayEquals(expected, actual);

        brick2.setX(0);
        brick2.setY(2);
        actual = validator.findPlaceForBrick(brick2);
        assertNull(actual);

/*
        {1, 0, 1, 1, 0, 1},
        {1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1}
*/

    }

    @Test
    void testMakeStep() {

/*
        {1, 0, 1, 1, 0, 1},
        {1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1}
*/
        int x = 0;
        int y = 2;
        int swapX = 0;
        int swapY = 0;
        int height = 3;
        int width = 1;
        int brickIndex = 1;
        int countOfSimilarBricks = validator.bricks.get(brickIndex).getCountOfSimilarBricks();
        validator.makeStep(x, y, swapX, swapY, height, width, brickIndex, true);
        Validator.Memento memento = validator.queue.peek();
        assertEquals(x, memento.x);
        assertEquals(y, memento.y);
        assertEquals(brickIndex, memento.brickIndex);

        System.out.println(Arrays.deepToString(memento.currentMatrix));
        System.out.println(Arrays.deepToString(validator.wall.getCurrentMatrix()));

        Brick updatedBrick = validator.bricks.get(brickIndex);
        assertEquals(updatedBrick.getCountOfSimilarBricks() + 1, countOfSimilarBricks);
        assertEquals(updatedBrick.getX(), x);
        assertEquals(updatedBrick.getY(), y);

        assertNotEquals(validator.wall.getCountOfBlocks(), memento.countOfBlocks);
        assertFalse(Arrays.deepEquals(validator.wall.getCurrentMatrix(), memento.currentMatrix));

    }

}






