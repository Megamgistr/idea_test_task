package com.calculator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public abstract class BasePositiveTest {
    protected ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    protected PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpOut() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreOut() {
        System.setOut(originalOut);
    }
}
