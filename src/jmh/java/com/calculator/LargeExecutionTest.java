package com.calculator;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class LargeExecutionTest extends BaseBenchmarkTest {
    @Param({"10000"})
    private String LARGE_EXECUTION_VALUE;

    @Benchmark
    public void rawLargeExecution() throws InterruptedException {
        RawPrimeCalculator.computeUpTo(Integer.parseInt(LARGE_EXECUTION_VALUE));
    }

    @Benchmark
    public void fixedLargeExecution() throws InterruptedException {
        FixedPrimeCalculator.computeUpTo(Integer.parseInt(LARGE_EXECUTION_VALUE));
    }

    @Benchmark
    public void treeLargeExecution() throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            TreePrimeCalculator.main(new String[]{LARGE_EXECUTION_VALUE});
        }
    }

    @Benchmark
    public void segmentedSieveLargeExecution() throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            SegmentedSievePrimeCalculator.main(new String[]{LARGE_EXECUTION_VALUE});
        }
    }
}
