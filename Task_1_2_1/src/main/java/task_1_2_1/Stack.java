/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package task_1_2_1;

import java.lang.reflect.Array;

public class Stack<T> {
    private int capacity;
    private int length;
    private T[] elements;
    private final Class<T> clazz;

    public Stack(Class<T> clazz, int capacity) {
        elements = (T[]) Array.newInstance(clazz, capacity);
        this.capacity = capacity;
        this.length = 0;
        this.clazz = clazz;
    }

    public int count() {
        return length;
    }

    public void push(T element) {
        if (length >= capacity) {
            throw new RuntimeException("Stack is full");
        }
        elements[length] = element;
        length += 1;
    }

    public void pushStack(Stack<T> stack) {
        int length = stack.count();
        T[] elements = (T[]) Array.newInstance(clazz, capacity);
        for (int i = 0; i < length; i++) {
            elements[i] = stack.pop();
        }
        for (int i = length - 1; i >= 0; i--) {
            push(elements[i]);
        }
    }

    public T pop() {
        if (length < 1) {
            throw new RuntimeException("Stack is empty");
        }
        T element = elements[length - 1];
        length -= 1;
        return element;
    }

    public Stack<T> popStack(int length) {
        Stack<T> newStack = new Stack<T>(this.clazz, length);
        T[] elements = (T[]) Array.newInstance(clazz, capacity);
        for (int i = 0; i < length; i++) {
            elements[i] = pop();
        }
        for (int i = length - 1; i >= 0; i--) {
            newStack.push(elements[i]);
        }

        return newStack;
    }

}
