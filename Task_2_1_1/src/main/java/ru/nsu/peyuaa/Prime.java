package ru.nsu.peyuaa;

/**
 * The Prime class provides methods for checking
 * whether a given integer is a non-prime number.
 */
public class Prime {

    /**
     * Determines whether a given integer is a non-prime number.
     *
     * @param n the integer to check
     * @return true if num is a non-prime number, false otherwise
     */
    public static boolean isNonPrime(int n) {
        if (n < 2) {
            return true;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return true;
            }
        }
        return false;
    }
}
