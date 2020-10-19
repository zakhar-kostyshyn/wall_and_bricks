package com.in_deal_pro.task.test_by_files;

import org.junit.jupiter.api.Test;

public class MediumFileTest extends FilesTest {

    @Test
    void test7x8True() {
        whenInputFile("7x8True.txt");
        thenTrue();
    }

}
