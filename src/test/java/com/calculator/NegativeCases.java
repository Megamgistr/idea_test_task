package com.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class NegativeCases {

    @ParameterizedTest(name = "Raw invalid value {0}")
    @CsvFileSource(resources = "/invalid_test_data.csv", numLinesToSkip = 1)
    void rawInvalidValues(String input, String expected) throws ClassNotFoundException {
        Assertions.assertThrows(Class.forName(expected).asSubclass(Throwable.class), () -> RawPrimeCalculator.main(new String[]{input}));
    }

    @ParameterizedTest(name = "Fixed invalid value {0}")
    @CsvFileSource(resources = "/invalid_test_data.csv", numLinesToSkip = 1)
    void fixedInvalidValues(String input, String expected) throws ClassNotFoundException {
        Assertions.assertThrows(Class.forName(expected).asSubclass(Throwable.class), () -> FixedPrimeCalculator.main(new String[]{input}));
    }

    @ParameterizedTest(name = "Tree invalid value {0}")
    @CsvFileSource(resources = "/invalid_test_data.csv", numLinesToSkip = 1)
    void treeInvalidValues(String input, String expected) throws ClassNotFoundException {
        Assertions.assertThrows(Class.forName(expected).asSubclass(Throwable.class), () -> TreePrimeCalculator.main(new String[]{input}));
    }
}
