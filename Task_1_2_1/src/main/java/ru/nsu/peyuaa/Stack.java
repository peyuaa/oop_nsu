/*
 * This Java source file was generated by the Gradle 'init' task.
 */

package ru.nsu.peyuaa;

import java.util.Arrays;
import java.util.Objects;

/**
 * Stack implementation.
 *
 * @param <T> type of the parameter
 */
public class Stack<T> {
    /**
     * Capacity of the stack.
     */
    private int capacity;
    /**
     * Number of elements in the stack.
     */
    private int length;
    /**
     * Array containing elements of stack.
     */
    private T[] elements;

    /**
     * Stack constructor.
     *
     * @param capacity capacity of stack
     */
    public Stack(int capacity) {
        elements = (T[]) new Object[capacity];
        this.capacity = capacity;
        this.length = 0;
    }

    /**
     * Return number of elements in stack.
     *
     * @return number of elements in the stack
     */
    public int count() {
        return length;
    }

    /**
     * Push element into stack.
     *
     * @param element is element to push
     */
    public void push(T element) {
        if (length >= capacity) {
            throw new RuntimeException("Stack is full");
        }
        elements[length] = element;
        length += 1;
    }

    /**
     * Push elements of stack given in parameters into current stack.
     *
     * @param stack containt elements to push into our stack
     */
    public void pushStack(Stack<T> stack) {
        int length = stack.count();
        T[] elements = (T[]) new Object[length];
        for (int i = 0; i < length; i++) {
            elements[i] = stack.pop();
        }
        for (int i = length - 1; i >= 0; i--) {
            push(elements[i]);
        }
    }

    /**
     * Pop element from stack.
     *
     * @return element of stack
     */
    public T pop() {
        if (length < 1) {
            throw new RuntimeException("Stack is empty");
        }
        T element = elements[length - 1];
        length -= 1;
        return element;
    }

    /**
     * Pop length elements from stack in Stack object.
     *
     * @param length number of elements to pop.
     * @return stack object with popped elements
     */
    public Stack<T> popStack(int length) {
        Stack<T> newStack = new Stack<>(length);
        T[] elements = (T[]) new Object[capacity];
        for (int i = 0; i < length; i++) {
            elements[i] = pop();
        }
        for (int i = length - 1; i >= 0; i--) {
            newStack.push(elements[i]);
        }

        return newStack;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Stack<?>)) {
            return false;
        }

        Stack s = (Stack) o;

        return Arrays.equals(elements, s.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this);
    }
}