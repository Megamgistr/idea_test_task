package com.calculator;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;

import java.util.concurrent.ExecutionException;

public class IncrementalValueTest extends BaseBenchmarkTest {
    @Param({"100", "1000", "10000", "100000"})
    private String incremental_value;

    @Benchmark
    public void rawValueIncrease() throws InterruptedException {
        RawPrimeCalculator.main(new String[]{incremental_value});
    }

    @Benchmark
    public void fixedValueIncrease() throws InterruptedException {
        FixedPrimeCalculator.main(new String[]{incremental_value});
    }

    @Benchmark
    public void treeValueIncrease() throws InterruptedException, ExecutionException {
        TreePrimeCalculator.main(new String[]{incremental_value});
    }
}
