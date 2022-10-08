package com.calculator;

import com.calculator.utils.NumberUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class TreePrimeCalculator {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int maxPrime = Integer.parseInt(args[0]);
        int currentChunkSize = 10000;

        if (maxPrime < 0) {
            throw new IllegalArgumentException("Value must be greater or equal to 0 ");
        }

        if (maxPrime < 2) {
            return;
        }

//        ExecutorService executors = Executors.newCachedThreadPool();
        ForkJoinPool executors = new ForkJoinPool();
        Set<Integer> set = new ConcurrentSkipListSet<>();
        List<Future<?>> futures = new ArrayList<>();

        int start = 2;
        int end = Math.min(maxPrime, start + currentChunkSize);
        int processed = 0;
        while (processed != maxPrime) {
            int finalStart = start;
            int finalEnd = end;
            futures.add(executors.submit(() -> {
                getPrimes(finalStart, finalEnd, set);
            }));
            processed = end;
            start = end + 1;
            end = Math.min(maxPrime, start + currentChunkSize);
            currentChunkSize = Math.min(1, currentChunkSize / 10);
        }
        for (Future<?> future : futures) {
            future.get();
        }
        executors.shutdownNow();

        for (Integer prime : set) {
            System.out.print(prime + "\n");
        }
    }

    private static void getPrimes(int start, int end, Set<Integer> set) {
        AtomicInteger current = new AtomicInteger(start);
        Stream
                .generate(current::getAndIncrement)
                .limit(Math.max(end - start + 1, 0))
                .filter(NumberUtils::isPrime)
                .forEach(set::add);
    }
}
