package ru.nsu.peyuaa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ParallelSolutionTest {
    private ParallelSolution parallelSolution = new ParallelSolution(2);

    @Test
    void testHasNonPrime() throws InterruptedException {
        int[][] testCases = {
            {2, 3, 5, 7, 11, 13},
            {2, 3, 4, 5, 7, 11, 13},
            {2, 3, 5, 7, 11, 13, 17, 19, 23, 29},
            {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 30},
            {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31}
        };
        boolean[] expectedResults = {
            false,
            true,
            false,
            true,
            false
        };

        for (int i = 0; i < testCases.length; i++) {
            boolean result = parallelSolution.containsNonPrime(testCases[i]);
            assertEquals(expectedResults[i], result,
                    String.format("Test case %d failed. Expected %b but got %b",
                            i + 1, expectedResults[i], result));
        }
    }
}

