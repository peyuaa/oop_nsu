package ru.nsu.peyuaa;

import jakarta.validation.constraints.NotNull;

/**
 * The SerialSolution class provides a serial solution
 * for determining whether an integer array contains
 * any non-prime numbers.
 */
public class SerialSolution {

    /**
     * Determines whether an integer array contains any non-prime numbers.
     *
     * @param arr the integer array to check
     * @return true if the array contains at least one non-prime number, false otherwise
     */
    public boolean containsNonPrime(@NotNull int[] arr) {
        for (int num : arr) {
            if (Prime.isNonPrime(num)) {
                return true;
            }
        }
        return false;
    }
}
