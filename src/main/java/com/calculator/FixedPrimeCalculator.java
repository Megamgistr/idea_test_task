package com.calculator;

import java.util.Collection;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.calculator.utils.NumberUtils.isPrime;

public class FixedPrimeCalculator {
    public static void main(String[] args) throws InterruptedException {
        for (Integer prime : getPrimes(Integer.parseInt(args[0]))) {
            System.out.print(prime + "\n");
        }
    }

    private static Collection<Integer> getPrimes(int maxPrime) throws InterruptedException {
        if (maxPrime < 0) {
            throw new IllegalArgumentException("Value must be greater or equal to 0 ");
        }
        int limit = Math.max(0, maxPrime - 1);
        AtomicInteger atomicInteger = new AtomicInteger(2);
        Collection<Integer> primeNumbers = Stream.generate(atomicInteger::getAndIncrement)
                .limit(limit)
                .collect(Collectors.toCollection(ConcurrentLinkedQueue::new));
        CountDownLatch latch = new CountDownLatch(limit);
        //        ExecutorService executors = Executors.newCachedThreadPool();
        ForkJoinPool executors = new ForkJoinPool();
        for (Integer candidate : primeNumbers) {
            executors.submit(() -> {
                if (!isPrime(candidate)) {
                    primeNumbers.remove(candidate);
                }
                latch.countDown();
            });
        }
        latch.await();
        executors.shutdownNow();
        return primeNumbers;
    }
}
