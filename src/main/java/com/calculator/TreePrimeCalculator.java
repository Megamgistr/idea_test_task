package com.calculator;

import com.calculator.utils.NumberUtils;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class TreePrimeCalculator {
    private static int getChunkCount(int maxPrime, int chunkSize) {
        int result = 1;
        while (chunkSize != 1 && maxPrime > chunkSize) {
            result++;
            maxPrime -= chunkSize;
            chunkSize /= 10;
        }
        if (chunkSize == 1) {
            result += maxPrime;
        }
        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        int maxPrime = Integer.parseInt(args[0]);
        int currentChunkSize = 10000;
        int chunkCount = getChunkCount(maxPrime, currentChunkSize);

        if (maxPrime < 0) {
            throw new IllegalArgumentException("Value must be greater or equal to 0 ");
        }

        if (maxPrime < 2) {
            return;
        }

//        ExecutorService executors = Executors.newCachedThreadPool();
        ForkJoinPool executors = new ForkJoinPool();
        Set<Integer> set = new ConcurrentSkipListSet<>();
        CountDownLatch latch = new CountDownLatch(chunkCount);

        int start = 2;
        int end = Math.min(maxPrime, start + currentChunkSize);
        for (int i = 0; i < chunkCount; i++) {
            int finalStart = start;
            int finalEnd = end;
            executors.submit(() -> {
                getPrimes(finalStart, finalEnd, set);
                latch.countDown();
            });
            start = end + 1;
            end = Math.min(maxPrime, start + currentChunkSize);
            currentChunkSize = Math.min(1, currentChunkSize / 10);
        }
        latch.await();
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
