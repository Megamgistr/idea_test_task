package com.calculator;

import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@State(Scope.Benchmark)
public class BaseBenchmarkTest {
    @Setup(Level.Trial)
    public void setUp() {
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
    }
}
