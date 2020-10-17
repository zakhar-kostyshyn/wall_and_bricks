package com.in_deal_pro.task.input;

import com.in_deal_pro.task.model.Brick;
import com.in_deal_pro.task.model.Wall;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.*;

public class FileInput implements Input {

    private static final int WIDTH_SHAPE_INDEX = 0;
    private static final int HEIGHT_SHAPE_INDEX = 2;
    private static final int COUNT_OF_BRICKS_INDEX = 0;

    public static final int WIDTH_HEIGHT_LINE_INDEX = 0;


    private List<String> allLines;

    public FileInput(String fileName) {
        try {
            allLines = Files.readAllLines(Path.of(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.printf("Path: %s doesn't exist%n", fileName);
            e.printStackTrace();
        }
    }

    @Override
    public int getShapeWidth() {
        return getElementFromLine(WIDTH_SHAPE_INDEX, WIDTH_HEIGHT_LINE_INDEX);
    }

    @Override
    public int getShapeHeight() {
        return getElementFromLine(HEIGHT_SHAPE_INDEX, WIDTH_HEIGHT_LINE_INDEX);
    }

    @Override
    public int getBricksCount() {
        return getElementFromLine(COUNT_OF_BRICKS_INDEX, countOfBricksLineIndex());
    }

    private int countOfBricksLineIndex() {
        //  1 - width/height of wall line
        return 1 + getShapeHeight();
    }

    private int getElementFromLine(int elementIndex, int lineIndex) {
        return parseInt(allLines.get(lineIndex).substring(elementIndex, elementIndex + 1));
    }

    @Override
    public Wall getWall() {
        return new Wall(getShapeMatrix(), getCountOfBlocksInWall());
    }

    private int getCountOfBlocksInWall() {
        return streamOfMatrixLines()
                .map(line -> line.replaceAll("0", ""))
                .mapToInt(String::length)
                .sum();

    }

    private int[][] getShapeMatrix() {
        return streamOfMatrixLines()
                .map(line -> Arrays.stream(line.split(""))
                        .mapToInt(Integer::parseInt)
                        .toArray()
                )
                .toArray(int[][]::new);
    }

    private Stream<String> streamOfMatrixLines() {
        return allLines.stream().skip(1).limit(getShapeHeight());
    }

    //  TODO enter similar lines
    @Override
    public Set<Brick> getAllBricks() {
        return streamOfBricksLines()
                .map(line -> line.replaceAll("\\s", ""))
                .map(line -> new Brick(
                        parseInt(line.substring(0, 1)),
                        parseInt(line.substring(1, 2)),
                        parseInt(line.substring(2)))
                ).collect(toCollection(TreeSet::new));
    }

    private Stream<String> streamOfBricksLines() {
        //  2 — width/height of wall line + bricks count line
        return allLines.stream().skip(2 + getShapeHeight());
    }

    public List<String> getAllLines() {
        return allLines;
    }
}










