package com.calculator;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;

import java.util.concurrent.ExecutionException;

public class BigValueTest extends BaseBenchmarkTest {
    @Param({"100000"})
    private String BIG_INT_VALUE;

    @Benchmark
    public void rawBigInt() throws InterruptedException {
        RawPrimeCalculator.main(new String[]{BIG_INT_VALUE});
    }

    @Benchmark
    public void fixedBigInt() throws InterruptedException {
        FixedPrimeCalculator.main(new String[]{BIG_INT_VALUE});
    }

    @Benchmark
    public void treeBigInt() throws InterruptedException, ExecutionException {
        TreePrimeCalculator.main(new String[]{BIG_INT_VALUE});
    }
}
