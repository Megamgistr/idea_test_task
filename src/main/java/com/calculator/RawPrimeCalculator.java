package com.calculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class BigIntegerIterator {
    private final List<String> contain = new ArrayList<>(500);
    private final List<Integer> reference = new ArrayList<>(500);

    BigIntegerIterator(int i) {
        contain.add("" + i + "");
        reference.add(i);
    }

    Integer getContain() {
        return Math.max(Integer.decode(contain.get(0)), reference.get(0));
    }
}

public class RawPrimeCalculator {
    public static java.util.List<Integer> computeUpTo(int maxPrime) throws InterruptedException {
        return getPrimes(maxPrime);
    }
    public static void main(String[] args) throws InterruptedException {
        int maxPrime;
        try {
            maxPrime = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            String s = args.length > 0 ? args[0] : "";
            if (s.matches("(?i)^0x[0-9a-f]+l?$")) {
                throw new IllegalArgumentException("Unsupported number format");
            }
            throw e;
        }
        for (Integer prime : getPrimes(maxPrime)) {
            System.out.print(prime + "\n");
        }
    }

    private static List<Integer> getPrimes(int maxPrime) throws InterruptedException {
        if (maxPrime < 0) {
            throw new IllegalArgumentException("Value must be greater or equal to 0 ");
        }
        List<Integer> primes = new ArrayList<>();
        if (maxPrime < 2) {
            return primes;
        }
        for (int i = 2; i <= maxPrime; i++) {
            if (com.calculator.utils.NumberUtils.isPrime(i)) {
                primes.add(i);
            }
        }
        return primes;
    }
}
