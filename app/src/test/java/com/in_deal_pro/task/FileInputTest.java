package com.in_deal_pro.task;

import com.in_deal_pro.task.input.FileInput;
import com.in_deal_pro.task.input.Input;
import com.in_deal_pro.task.model.Brick;
import com.in_deal_pro.task.model.Wall;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FileInputTest {

    Input input = new FileInput("input.txt");

    @Test
    void testAllLines() throws Exception {
        FileInput input = new FileInput("input.txt");
        var expected = List.of("6 3", "101101", "111111", "111111", "4", "1 1 4", "2 1 6", "1 3 1", "3 4 5");
        var actual = input.getAllLines();
        assertEquals(expected, actual);
    }

    @Test
    void testShapeWidth() {
        var expected = 6;
        var actual = input.getShapeWidth();
        assertEquals(expected, actual);
    }

    @Test
    void testShapeHeight() {
        var expected = 3;
        var actual = input.getShapeHeight();
        assertEquals(expected, actual);
    }

    @Test
    void testBricksCount() {
        var expected = 4;
        var actual = input.getBricksCount();
        assertEquals(expected, actual);
    }

    @Test
    void testGetWall() {
        int [][] matrix =
                {
                        {1, 0, 1, 1, 0, 1},
                        {1, 1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1, 1}
                };
        var expected = new Wall(matrix, 16);
        var actual = input.getWall();
        assertEquals(expected, actual);
    }

    @Test
    void testGetAllBricks() {
        var expected = Set.of(
                new Brick(1, 1, 4),
                new Brick(2, 1, 6),
                new Brick(1, 3, 1),
                new Brick(3, 4, 5)
        );
        var actual = input.getAllBricks();
        assertEquals(expected, actual);
    }

}