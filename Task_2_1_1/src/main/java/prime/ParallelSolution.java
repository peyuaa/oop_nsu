package prime;

import java.lang.Math;

public class ParallelSolution {
    private final int numThreads;

    public ParallelSolution(int numThreads) {
        this.numThreads = numThreads;
    }

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

