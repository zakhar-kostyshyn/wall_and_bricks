package com.in_deal_pro.task;

import com.in_deal_pro.task.model.*;
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
        bricks.add(new NormalBrick(1, 1, new SimilarBlocks(4)));
        bricks.add(new NormalBrick(2, 1, new SimilarBlocks(6)));
        bricks.add(new NormalBrick(1, 3, new SimilarBlocks(1)));
        bricks.add(new NormalBrick(3, 4, new SimilarBlocks(5)));

        validator = new Validator(bricks, new Wall(matrix, 16));
    }

    @Test
    void isListSortedRight() {
        List<NormalBrick> expected = List.of(
                new NormalBrick(3, 4, new SimilarBlocks(5)),
                new NormalBrick(1, 3, new SimilarBlocks(1)),
                new NormalBrick(2, 1, new SimilarBlocks(6)),
                new NormalBrick(1, 1, new SimilarBlocks(4))
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
//
//        int [][] matrix =
//                {
//                        {0, 1, 2, 2, 1, 1},
//                        {1, 1, 4, 1, 6, 1},
//                        {2, 1, 5, 6, 7, 1},
//                        {1, 4, 4, 3, 0, 9}
//                };
//
//        int[][] expected =
//                {
//                        {2, 2},
//                        {4, 1},
//                        {5, 6}
//                };
//        int[][] actual = validator.createSubMatrix(0, 2, 3, 2, matrix);
//        assertTrue(Arrays.deepEquals(expected, actual));
//
//        expected =
//                new int[][] {
//                        {1, 4, 1, 6, 1},
//                        {1, 5, 6, 7, 1},
//                        {4, 4, 3, 0, 9}
//                };
//        actual = validator.createSubMatrix(1, 1, 3, 5, matrix);
//        assertTrue(Arrays.deepEquals(expected, actual));
//
//        actual = validator.createSubMatrix(0, 0, 4, 6, matrix);
//        assertTrue(Arrays.deepEquals(matrix, actual));
    }

    @Test
    void findPlaceForSwapBrickTest() {

/*
        {1, 0, 1, 1, 0, 1},
        {1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1}
*/

//        Brick brick = new Brick(2, 3, 4);
//
//        Point expected = new Point(1, 0);
//        validator.findPlaceForSwapBrick(brick);
//        Point actual = validator.getPlaceForSwapBrick();
//        assertEquals(expected, actual);
//
//
//        brick.setLastSwapIndex(new Point(1, 0));
//        expected = new Point(1, 1);
//        validator.findPlaceForSwapBrick(brick);
//        actual = validator.getPlaceForSwapBrick();
//        assertEquals(expected, actual);
//
//        brick.setLastSwapIndex(new Point(1, 2));
//        expected = new Point(1, 3);
//        validator.findPlaceForSwapBrick(brick);
//        actual = validator.getPlaceForSwapBrick();
//        assertEquals(expected, actual);
//
//        brick.setLastSwapIndex(new Point(1, 3));
//        validator.findPlaceForSwapBrick(brick);
//        actual = validator.getPlaceForSwapBrick();
//        assertNull(actual);
    }

    @Test
    void findPlaceForBrickTest() {

/*
        {1, 0, 1, 1, 0, 1},
        {1, 1, 1, 1, 1, 1},
        {1, 1, 1, 1, 1, 1}
*/

//        Brick brick1 = new Brick(1, 1, 4);
//        Brick brick2 = new Brick(2, 3, 4);
//        Brick brick3 = new Brick(6, 1, 4);
//
//        Point expected = new Point(0, 2);
//        validator.findPlaceForBrick(brick2);
//        Point actual = validator.getPlaceForBrick();
//        assertEquals(expected, actual);
//
//        expected = new Point(0, 0);
//        validator.findPlaceForBrick(brick1);
//        actual = validator.getPlaceForBrick();
//        assertEquals(expected, actual);
//
//        expected = new Point(1, 0);
//        validator.findPlaceForBrick(brick3);
//        actual = validator.getPlaceForBrick();
//        assertEquals(expected, actual);
//
//        // test get next place if x and y not standart
//
//        brick1.setLastIndex(new Point(2, 4));
//        expected = new Point(2, 5);
//        validator.findPlaceForBrick(brick1);
//        actual = validator.getPlaceForBrick();
//        assertEquals(expected, actual);
//
//        brick1.setLastIndex(new Point(1, 5));
//        expected = new Point(2, 0);
//        validator.findPlaceForBrick(brick1);
//        actual = validator.getPlaceForBrick();
//        assertEquals(expected, actual);
//
//        brick3.setLastIndex(new Point(1, 0));
//        expected = new Point(2, 0);
//        validator.findPlaceForBrick(brick3);
//        actual = validator.getPlaceForBrick();
//        assertEquals(expected, actual);
//
//        brick2.setLastIndex(new Point(0, 2));
//        validator.findPlaceForBrick(brick2);
//        actual = validator.getPlaceForBrick();
//        assertNull(actual);

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

        Point lastIndex = new Point(0, 2);
        Point lastSwapIndex = new Point(0, 0);


//        int height = 3;
//        int width = 1;
//        int brickIndex = 1;
//        int countOfSimilarBricks = validator.bricks.get(brickIndex).getSimilarBlocks();
////        validator.makeStepForSwap();
//        Validator.Memento memento = validator.queue.peek();
//        assertEquals(brickIndex, memento.brickIndex);
//
//        System.out.println(Arrays.deepToString(memento.currentMatrix));
//        System.out.println(Arrays.deepToString(validator.wall.getCurrentMatrix()));
//
//        Brick updatedBrick = validator.bricks.get(brickIndex);
//        assertEquals(updatedBrick.getSimilarBlocks() + 1, countOfSimilarBricks);
//        assertEquals(updatedBrick.getLastIndex(), lastIndex);
//
//        assertNotEquals(validator.wall.getCountOfBlocks(), memento.countOfBlocks);
//        assertFalse(Arrays.deepEquals(validator.wall.getCurrentMatrix(), memento.currentMatrix));

    }

}






