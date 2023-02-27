package ru.nsu.peyuaa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ParallelStreamSolutionTest {
    private ParallelStreamSolution parallelStreamSolution = new ParallelStreamSolution();

    @Test
    void testHasNonPrime() {
        int[] arr1 = {2, 3, 5, 7, 11, 13};
        assertFalse(parallelStreamSolution.containsNonPrime(arr1));

        int[] arr2 = {2, 3, 4, 5, 7, 11, 13};
        assertTrue(parallelStreamSolution.containsNonPrime(arr2));

        int[] arr3 = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
        assertFalse(parallelStreamSolution.containsNonPrime(arr3));

        int[] arr4 = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 30};
        assertTrue(parallelStreamSolution.containsNonPrime(arr4));

        int[] arr5 = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31};
        assertFalse(parallelStreamSolution.containsNonPrime(arr5));
    }
}

