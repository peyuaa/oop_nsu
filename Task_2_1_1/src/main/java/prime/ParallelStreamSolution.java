package prime;

import java.util.Arrays;

/**
 * The ParallelStreamSolution class provides a parallelized solution
 * for determining whether an integer array
 * contains any non-prime numbers.
 * It uses Java 8 Streams to parallelize the checking process.
 */
public class ParallelStreamSolution {

    /**
     * Determines whether a given integer is a non-prime number.
     *
     * @param num the integer to check
     * @return true if num is a non-prime number, false otherwise
     */
    public boolean isNonPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines whether an integer array contains any non-prime numbers, using Java 8 Streams.
     *
     * @param arr the integer array to check
     * @return true if the array contains at least one non-prime number, false otherwise
     */
    public boolean containsNonPrime(int[] arr) {
        if (arr == null) {
            return false;
        }

        return Arrays.stream(arr)
                .parallel()
                .anyMatch(this::isNonPrime);
    }
}

