package prime;

import java.lang.Math;

/**
 * The SerialSolution class provides a serial solution for determining whether an integer array contains
 * any non-prime numbers.
 */
public class SerialSolution {

    /**
     * Determines whether a given integer is a prime number.
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
     * Determines whether an integer array contains any non-prime numbers.
     * @param arr the integer array to check
     * @return true if the array contains at least one non-prime number, false otherwise
     */
    public boolean containsNonPrime(int[] arr) {
        if (arr == null) {
            return false;
        }

        for (int num : arr) {
            if (!isPrime(num)) {
                return true;
            }
        }
        return false;
    }
}
