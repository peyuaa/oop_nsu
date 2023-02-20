package ru.nsu.peyuaa;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SerialSolutionTest {
    SerialSolution serialSolution = new SerialSolution();

    @Test
    void testContainsNonPrime() {
        int[][] testCases = {
                {},
                {2, 3, 5, 7, 11, 13, 17},
                {4, 6, 8, 9, 10, 12, 14},
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                {2, 3, 5, 7, 11, 13, 17, 19, 23, 29},
                {-1, -2, -3, -4, -5, -6, -7, -8, -9, -10},
                {3, 5, 7, 11, 13, 17, 19, 23, 29, 31},
                {4, 6, 8, 10, 12, 14, 16, 18, 20, 22},
                {3, -2}
        };

        boolean[] expectedResults = {
            false,
            false,
            true,
            true,
            false,
            true,
            false,
            true,
            true
        };

        for (int i = 0; i < testCases.length; i++) {
            boolean result = serialSolution.containsNonPrime(testCases[i]);
            assertEquals(expectedResults[i], result,
                    String.format("Test case %d failed. Expected %b but got %b",
                            i + 1, expectedResults[i], result));
        }
    }
}