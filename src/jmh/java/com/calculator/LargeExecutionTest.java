package com.calculator;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.concurrent.ExecutionException;

@State(Scope.Benchmark)
public class LargeExecutionTest extends BaseBenchmarkTest {
    @Param({"10000"})
    private String LARGE_EXECUTION_VALUE;

    @Benchmark
    public void rawLargeExecution() throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            RawPrimeCalculator.main(new String[]{LARGE_EXECUTION_VALUE});
        }
    }

    @Benchmark
    public void fixedLargeExecution() throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            FixedPrimeCalculator.main(new String[]{LARGE_EXECUTION_VALUE});
        }
    }

    @Benchmark
    public void treeLargeExecution() throws InterruptedException, ExecutionException {
        for (int i = 0; i < 1000; i++) {
            TreePrimeCalculator.main(new String[]{LARGE_EXECUTION_VALUE});
        }
    }
}
