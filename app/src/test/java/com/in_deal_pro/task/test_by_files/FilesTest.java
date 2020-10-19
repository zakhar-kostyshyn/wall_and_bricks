package com.in_deal_pro.task.test_by_files;

import com.in_deal_pro.task.Validator;
import com.in_deal_pro.task.input.FileInput;
import com.in_deal_pro.task.input.Input;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class FilesTest {

    protected static final String home = "src/test/resources/";
    protected Input input;
    protected Validator validator;

    void whenInputFile(String fileName) {
        input = new FileInput(home + fileName);
        validator = new Validator(input.getAllBricks(), input.getWall());
    }

    void thenTrue() {
        assertTrue(validator.check());
    }

    void thenFalse() {
        assertFalse(validator.check());
    }
}
