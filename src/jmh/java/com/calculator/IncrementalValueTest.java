package com.calculator;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class IncrementalValueTest extends BaseBenchmarkTest {
    @Param({"100", "1000", "10000", "100000"})
    private String incremental_value;

    @Benchmark
    public void rawValueIncrease() throws InterruptedException {
        RawPrimeCalculator.computeUpTo(Integer.parseInt(incremental_value));
    }

    @Benchmark
    public void fixedValueIncrease() throws InterruptedException {
        FixedPrimeCalculator.computeUpTo(Integer.parseInt(incremental_value));
    }

    @Benchmark
    public void treeValueIncrease() throws InterruptedException {
        TreePrimeCalculator.computeUpTo(Integer.parseInt(incremental_value));
    }

    @Benchmark
    public void segmentedSieveValueIncrease() throws InterruptedException {
        SegmentedSievePrimeCalculator.computeUpTo(Integer.parseInt(incremental_value));
    }
}
