package com.calculator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositiveCases extends BasePositiveTest {

    @ParameterizedTest(name = "Raw valid value {0}")
    @CsvFileSource(resources = "/valid_test_data.csv", numLinesToSkip = 1)
    void rawValidValues(String input, String expected) throws InterruptedException {
        RawPrimeCalculator.main(new String[]{input});
        assertEquals(expected, outContent.toString());
    }

    @ParameterizedTest(name = "Fixed valid value {0}")
    @CsvFileSource(resources = "/valid_test_data.csv", numLinesToSkip = 1)
    void fixedValidValues(String input, String expected) throws InterruptedException {
        FixedPrimeCalculator.main(new String[]{input});
        assertEquals(expected, outContent.toString());
    }

    @ParameterizedTest(name = "Tree valid value {0}")
    @CsvFileSource(resources = "/valid_test_data.csv", numLinesToSkip = 1)
    void treeValidValues(String input, String expected) throws InterruptedException {
        TreePrimeCalculator.main(new String[]{input});
        assertEquals(expected, outContent.toString());
    }
}
