package ru.nsu.peyuaa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParallelStreamSolutionTest {
    private ParallelStreamSolution parallelStreamSolution = new ParallelStreamSolution();

    @ParameterizedTest
    @CsvSource({
            "2, true",
            "3, true",
            "4, false",
            "5, true",
            "6, false",
            "7, true",
            "8, false",
            "9, false",
            "10, false",
            "11, true",
            "12, false",
            "13, true",
            "14, false",
            "15, false",
            "16, false",
            "17, true",
            "18, false",
            "19, true",
            "20, false",
            "21, false",
            "22, false",
            "23, true",
            "24, false",
            "25, false",
            "26, false",
            "27, false",
            "28, false",
            "29, true",
            "30, false",
            "31, true"
    })
    void testIsNonPrime(int num, boolean expected) {
        assertEquals(expected, !parallelStreamSolution.isNonPrime(num));
    }

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

