package ru.nsu.peyuaa;

import jakarta.validation.constraints.NotNull;
import java.util.Arrays;

/**
 * The ParallelStreamSolution class provides a parallelized solution
 * for determining whether an integer array
 * contains any non-prime numbers.
 * It uses Java 8 Streams to parallelize the checking process.
 */
public class ParallelStreamSolution {

    /**
     * Determines whether an integer array contains any non-prime numbers, using Java 8 Streams.
     *
     * @param arr the integer array to check
     * @return true if the array contains at least one non-prime number, false otherwise
     */
    public boolean containsNonPrime(@NotNull int[] arr) {
        return Arrays.stream(arr)
                .parallel()
                .anyMatch(Prime::isNonPrime);
    }
}

