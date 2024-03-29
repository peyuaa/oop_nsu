/*
 * This Java source file was generated by the Gradle 'init' task.
 */

package ru.nsu.peyuaa;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;

class HeapSortTest {
    private final HeapSort heapSort = new HeapSort();

    @Test
    void usualArray() {
        int[] expected = {1, 2, 3, 4, 5, 6};
        int[] got = {3, 4, 1, 2, 6, 5};

        heapSort.heapSort(got);
        assertArrayEquals(expected, got);
    }

    @Test
    void equalElements() {
        int[] expected = {1, 1, 1, 1, 1, 1};
        int[] got = {1, 1, 1, 1, 1, 1};

        heapSort.heapSort(got);
        assertArrayEquals(expected, got);
    }

    @Test
    void oneElementArray() {
        int[] expected = {5};
        int[] got = {5};

        heapSort.heapSort(got);
        assertArrayEquals(expected, got);
    }

    @Test
    void emptyArray() {
        int[] expected = {};
        int[] got = {};

        heapSort.heapSort(got);
        assertArrayEquals(expected, got);
    }

    @Test
    void randomArray() {
        Random rand = new Random();
        int generatorUpperBound = 150;

        int arrayLength = rand.nextInt(generatorUpperBound) + 150;

        int[] expected = new int[arrayLength];
        int[] got = new int[arrayLength];

        for (int i = 0; i < arrayLength; i++) {
            int generatedElement = rand.nextInt();
            expected[i] = generatedElement;
            got[i] = generatedElement;
        }

        Arrays.sort(expected);

        heapSort.heapSort(got);
        assertArrayEquals(expected, got);
    }
}
