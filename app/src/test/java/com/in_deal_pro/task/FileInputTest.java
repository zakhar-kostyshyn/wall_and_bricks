package com.in_deal_pro.task;

import com.in_deal_pro.task.input.FileInput;
import com.in_deal_pro.task.input.Input;
import com.in_deal_pro.task.model.Brick;
import com.in_deal_pro.task.model.NormalBrick;
import com.in_deal_pro.task.model.BrickCount;
import com.in_deal_pro.task.model.Wall;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
        var expected = new Wall(matrix);
        var actual = input.getWall();
        assertEquals(expected, actual);
    }

    @Test
    void testGetAllBricks() {
        Set<Brick> expected = new TreeSet<>();
        expected.add(new NormalBrick(4, 3, new BrickCount(5)));
        expected.add(new NormalBrick(1, 1, new BrickCount(4)));
        expected.add(new NormalBrick(2, 1, new BrickCount(6)));
        expected.add(new NormalBrick(1, 3, new BrickCount(1)));
        expected.add(new NormalBrick(3, 4, new BrickCount(5)));
        expected.add(new NormalBrick(1, 1, new BrickCount(4)));
        expected.add(new NormalBrick(1, 2, new BrickCount(6)));
        expected.add(new NormalBrick(3, 1, new BrickCount(1)));
        var actual = input.getAllBricks();
        assertEquals(expected, actual);
    }

}