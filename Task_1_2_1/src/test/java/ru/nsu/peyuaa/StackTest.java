/*
 * This Java source file was generated by the Gradle 'init' task.
 */

package ru.nsu.peyuaa;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;
import org.junit.jupiter.api.Test;

class StackTest {
    @Test
    void isPushAndPopWorking() {
        int length = 10;
        Stack<Integer> stack = new Stack<>(length);
        Integer[] expected = new Integer[length];
        Integer[] got = new Integer[length];

        for (int i = length - 1; i >= 0; i--) {
            stack.push(i);
        }

        for (int i = 0; i < length; i++) {
            expected[i] = i;
            got[i] = stack.pop();
        }

        assertArrayEquals(expected, got);
    }

    @Test
    void isPushStackWorking() {
        int length = 5;
        Stack<Integer> expected = new Stack<>(length);
        Stack<Integer> got = new Stack<>(length);
        Stack<Integer> pushedStack = new Stack<>(length);

        for (int i = 0; i < length; i++) {
            expected.push(i);
            pushedStack.push(i);
        }

        got.pushStack(pushedStack);

        assertEquals(expected, got);
    }

    @Test
    void isPopStackWorking() {
        int length = 5;
        int poppedLength = 2;
        Stack<Integer> stack = new Stack<>(length);
        Stack<Integer> expected = new Stack<>(poppedLength);

        for (int i = 0; i < length; i++) {
            stack.push(i);
        }

        for (int i = 0; i < poppedLength; i++) {
            expected.push(i + 3);
        }

        Stack<Integer> got = stack.popStack(poppedLength);

        assertEquals(expected, got);
    }

    @Test
    void isPopStackWorkingCorrect() {
        int length = 5;
        int poppedLength = 2;
        Stack<Integer> got = new Stack<>(length);
        Stack<Integer> expected = new Stack<>(length);

        for (int i = 0; i < length; i++) {
            got.push(i);
        }
        for (int i = 0; i < length - poppedLength; i++) {
            expected.push(i);
        }

        got.popStack(2);

        assertEquals(expected, got);
    }

    @Test
    void growTest() {
        int gotCapacity = 1;
        int expectedCapacity = 5;

        Stack<Integer> got = new Stack<>(gotCapacity);
        Stack<Integer> expected = new Stack<>(expectedCapacity);

        for (int i = 0; i < expectedCapacity; i++) {
            got.push(i);
            expected.push(i);
        }

        assertEquals(expected, got);
    }

    @Test
    void stackEqualityObjects() {
        Random random = new Random();
        int capacity = random.nextInt(300);

        Stack<Object> got = new Stack<>(capacity);
        Stack<Object> expected = new Stack<>(capacity);

        for (int i = 0; i < capacity; i++) {
            Object object = new Object();
            got.push(object);
            expected.push(object);
        }

        assertEquals(expected, got);
    }
}
