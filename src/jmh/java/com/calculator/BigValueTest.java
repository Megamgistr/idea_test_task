package com.calculator;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
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
    public void treeBigInt() throws InterruptedException {
        TreePrimeCalculator.main(new String[]{BIG_INT_VALUE});
    }
}
