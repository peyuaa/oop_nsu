/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package task_1_2_1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    @Test
    void isPushAndPopWorking() {
        int length = 10;
        Stack<Integer> stack = new Stack<Integer>(Integer.class, length);
        Integer[] elements = new Integer[length];
        Integer[] expected = new Integer[length];
        Integer[] got = new Integer[length];

        for (int i = length - 1; i >= 0; i--) {
            expected[length - i - 1] = i;
        }

        for (int i = 0; i < length; i++) {
            elements[i] = i;
        }
        for (int i = 0; i < length; i++) {
            stack.push(elements[i]);
        }

        for (int i = 0; i < length; i++) {
            got[i] = stack.pop();
        }

        assertArrayEquals(expected, got);
    }

    @Test
    void isPushStackWorking() {
        int length = 5;
        Stack<Integer> stack = new Stack<Integer>(Integer.class, length);
        Stack<Integer> pushedStack = new Stack<Integer>(Integer.class, length);
        Integer[] expected = new Integer[length];
        Integer[] got = new Integer[length];

        for (int i = 0; i < length; i++) {
            pushedStack.push(i);
        }

        for (int i = length - 1; i >= 0; i--) {
            expected[length - i - 1] = i;
        }

        stack.pushStack(pushedStack);

        for (int i = 0; i < length; i++) {
            got[i] = stack.pop();
        }

        assertArrayEquals(expected, got);
    }

    @Test
    void isPopStackWorking() {
        int length = 5;
        int poppedLength = 2;
        Stack<Integer> stack = new Stack<Integer>(Integer.class, length);
        Integer[] elements = new Integer[length];
        Integer[] expected = new Integer[poppedLength];
        Integer[] got = new Integer[poppedLength];

        for (int i = 0; i < length; i++) {
            stack.push(i);
            elements[i] = i;
        }

        expected[poppedLength - 2] = 4;
        expected[poppedLength - 1] = 3;

        Stack<Integer> poppedStack = stack.popStack(poppedLength);
        for (int i = 0; i < poppedLength; i++) {
            got[i] = poppedStack.pop();
        }

        assertArrayEquals(expected, got);
    }
}
