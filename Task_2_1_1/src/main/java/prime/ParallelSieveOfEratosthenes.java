package prime;

import java.util.Arrays;

public class ParallelSieveOfEratosthenes {

    private boolean[] primes;

    public boolean[] findPrimes(int n, int numThreads) throws InterruptedException {
        // Initialize the boolean array to true (assume all numbers are prime)
        primes = new boolean[n];
        Arrays.fill(primes, true);

        // The first two numbers are not prime, so mark them as composite
        primes[0] = primes[1] = false;

        // Compute the square root of n
        int sqrtN = (int) Math.sqrt(n);

        // Determine the size of each chunk of numbers to be processed
        int chunkSize = (n - 1) / numThreads + 1;

        // Create and start the threads
        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            int start = i * chunkSize;
            int end = Math.min(start + chunkSize, n);
            threads[i] = new PrimeCheckerThread(start, end, primes, sqrtN);
            threads[i].start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            thread.join();
        }

        // Return the array of primes
        return primes;
    }

    private static class PrimeCheckerThread extends Thread {
        private final int start;
        private final int end;
        private final boolean[] primes;
        private final int sqrtN;

        public PrimeCheckerThread(int start, int end, boolean[] primes, int sqrtN) {
            this.start = start;
            this.end = end;
            this.primes = primes;
            this.sqrtN = sqrtN;
        }

        @Override
        public void run() {
            // Find the primes in the current chunk of numbers
            for (int i = start; i < end; i++) {
                // Check if the current number is prime
                if (primes[i]) {
                    // If the current number is less than the square root of n,
                    // mark all its multiples as composite
                    if (i <= sqrtN) {
                        for (int j = i * i; j < primes.length; j += i) {
                            primes[j] = false;
                        }
                    }
                    // If the current number is greater than the square root of n,
                    // mark all its multiples as composite except itself
                    else {
                        for (int j = i * 2; j < primes.length; j += i) {
                            primes[j] = false;
                        }
                    }
                }
            }
        }
    }
}
