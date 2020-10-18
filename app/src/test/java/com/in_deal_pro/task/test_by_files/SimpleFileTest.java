package com.in_deal_pro.task.test_by_files;

import org.junit.jupiter.api.Test;

public class SimpleFileTest extends FilesTest {

    @Test
    void testExampleTrueFile() {
        whenInputFile("exampleTrue.txt");
        thenTrue();
    }

    @Test
    void testExampleFalseFile() {
        whenInputFile("exampleFalse.txt");
        thenFalse();
    }

    @Test
    void test6x3True() {
        whenInputFile("6x3True.txt");
        thenTrue();
    }

    @Test
    void test6x3False() {
        whenInputFile("6x3False.txt");
        thenFalse();
    }

    @Test
    void test4x4True() {
        whenInputFile("4x4True.txt");
        thenTrue();
    }

    @Test
    void test4x4False() {
        whenInputFile("4x4False.txt");
        thenFalse();
    }

}
