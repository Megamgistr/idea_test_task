package com.calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Segmented Sieve of Eratosthenes implementation for finding prime numbers.
 * This algorithm is memory-efficient for large ranges by processing numbers in segments.
 *
 * Algorithm:
 * 1. Find all primes up to sqrt(n) using simple sieve
 * 2. Divide the range [sqrt(n)+1, n] into segments
 * 3. For each segment, use the base primes to mark composites
 * 4. Collect all unmarked numbers as primes
 */
public class SegmentedSievePrimeCalculator {

    private static final int SEGMENT_SIZE = 32768; // 32KB cache-friendly size

    public static List<Integer> computeUpTo(int maxPrime) throws InterruptedException {
        Collection<Integer> primes = getPrimes(maxPrime);
        return new ArrayList<>(primes);
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length == 0) {
            throw new NumberFormatException("Missing argument");
        }
        int maxPrime;
        try {
            maxPrime = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            String s = args[0];
            if (s != null && s.matches("(?i)^0x[0-9a-f]+l?$")) {
                throw new IllegalArgumentException("Unsupported number format");
            }
            throw e;
        }
        if (maxPrime < 0) {
            throw new IllegalArgumentException("Value must be greater or equal to 0");
        }

        for (Integer prime : computeUpTo(maxPrime)) {
            System.out.print(prime + "\n");
        }
    }

    private static Collection<Integer> getPrimes(int maxPrime) throws InterruptedException {
        if (maxPrime < 2) {
            return new ArrayList<>();
        }

        // Find all primes up to sqrt(maxPrime) using simple sieve
        int limit = (int) Math.sqrt(maxPrime);
        List<Integer> basePrimes = simpleSieve(limit);

        // Use thread-safe set to collect all primes
        Set<Integer> allPrimes = new ConcurrentSkipListSet<>(basePrimes);

        // Process segments in parallel
        int low = limit + 1;
        int high = Math.min(low + SEGMENT_SIZE - 1, maxPrime);

        List<SegmentTask> tasks = new ArrayList<>();
        while (low <= maxPrime) {
            tasks.add(new SegmentTask(low, high, basePrimes));
            low = high + 1;
            high = Math.min(low + SEGMENT_SIZE - 1, maxPrime);
        }

        // Process segments in parallel using ForkJoinPool
        ForkJoinPool pool = new ForkJoinPool();
        try {
            List<Future<List<Integer>>> futures = new ArrayList<>();
            for (SegmentTask task : tasks) {
                futures.add(pool.submit(task));
            }

            // Collect results from all segments
            for (Future<List<Integer>> future : futures) {
                try {
                    allPrimes.addAll(future.get());
                } catch (ExecutionException e) {
                    throw new RuntimeException("Error processing segment", e);
                }
            }
        } finally {
            pool.shutdown();
            pool.awaitTermination(1, TimeUnit.MINUTES);
        }

        return allPrimes;
    }

    /**
     * Simple Sieve of Eratosthenes for finding primes up to limit
     */
    private static List<Integer> simpleSieve(int limit) {
        if (limit < 2) {
            return new ArrayList<>();
        }

        boolean[] isPrime = new boolean[limit + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i * i <= limit; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= limit; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= limit; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        return primes;
    }

    /**
     * Task for processing a single segment of numbers
     */
    private static class SegmentTask implements Callable<List<Integer>> {
        private final int low;
        private final int high;
        private final List<Integer> basePrimes;

        public SegmentTask(int low, int high, List<Integer> basePrimes) {
            this.low = low;
            this.high = high;
            this.basePrimes = basePrimes;
        }

        @Override
        public List<Integer> call() {
            int size = high - low + 1;
            boolean[] isPrime = new boolean[size];
            Arrays.fill(isPrime, true);

            // For each base prime, mark its multiples in current segment
            for (int prime : basePrimes) {
                // Find the first multiple of prime in [low, high]
                long start = Math.max(1L * prime * prime, ((low + (long) prime - 1L) / prime) * 1L * prime);

                // Mark all multiples of prime in the segment
                for (long j = start; j <= high; j += prime) {
                    isPrime[(int) (j - low)] = false;
                }
            }

            // Collect all primes in this segment
            List<Integer> segmentPrimes = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                if (isPrime[i]) {
                    segmentPrimes.add(low + i);
                }
            }

            return segmentPrimes;
        }
    }
}
