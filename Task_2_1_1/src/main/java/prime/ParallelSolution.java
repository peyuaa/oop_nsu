package prime;

import java.lang.Math;

/**
 * The ParallelSolution class provides a parallelized solution
 * for determining whether an integer array
 * contains any non-prime numbers. It uses multiple threads to check portions
 * of the array simultaneously.
 */
public class ParallelSolution {
    private final int numThreads;

    /**
     * Constructs a ParallelSolution object with a specified number of threads.
     *
     * @param numThreads the number of threads to use for checking the array
     */
    public ParallelSolution(int numThreads) {
        this.numThreads = numThreads;
    }

    /**
     * Determines whether a given integer is a prime number.
     *
     * @param n the integer to check
     * @return true if n is a prime number, false otherwise
     */
    public boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determines whether an integer array contains any non-prime numbers, using multiple threads.
     *
     * @param arr the integer array to check
     * @return true if the array contains at least one non-prime number, false otherwise
     * @throws InterruptedException if any of the threads are interrupted during execution
     */
    public boolean containsNonPrime(int[] arr) throws InterruptedException {
        if (arr == null) {
            return false;
        }

        int chunkSize = (int) Math.ceil((double) arr.length / numThreads);
        CheckPrimeThread[] threads = new CheckPrimeThread[numThreads];

        // Create and start the threads
        for (int i = 0; i < numThreads; i++) {
            int startIndex = i * chunkSize;
            int endIndex = Math.min((i + 1) * chunkSize, arr.length);
            threads[i] = new CheckPrimeThread(arr, startIndex, endIndex);
            threads[i].start();
        }

        // Wait for all threads to finish and collect their results
        boolean hasNonPrime = false;
        for (int i = 0; i < numThreads; i++) {
            threads[i].join();
            if (threads[i].hasNonPrime()) {
                hasNonPrime = true;
            }
        }
        return hasNonPrime;
    }

    /**
     * A private inner class representing a thread that checks a portion of an integer array for non-prime numbers.
     */
    private class CheckPrimeThread extends Thread {
        private final int[] arr;
        private final int startIndex;
        private final int endIndex;
        private boolean nonPrimeFound;

        public CheckPrimeThread(int[] arr, int startIndex, int endIndex) {
            this.arr = arr;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        public boolean hasNonPrime() {
            return nonPrimeFound;
        }

        public void run() {
            for (int i = startIndex; i < endIndex; i++) {
                int num = arr[i];
                if (num == 2) {
                    continue;
                }
                if (num % 2 == 0 || !isPrime(num)) {
                    nonPrimeFound = true;
                    break;
                }
            }
        }
    }
}

