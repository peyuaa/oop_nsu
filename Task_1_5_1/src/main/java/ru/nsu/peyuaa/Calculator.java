/*
 * This Java source file was generated by the Gradle 'init' task.
 */

package ru.nsu.peyuaa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class Calculator {
    private boolean isUnaryOperation(String operand) {
        return operand.equals("log") || operand.equals("sqrt")
                || operand.equals("sin") || operand.equals("cos");
    }

    private double doUnaryOperation(String operand, String argument) {
        double arg = Double.parseDouble(argument);
        switch (operand) {
            case "log":
                return Math.log(arg);
            case "sqrt":
                return Math.sqrt(arg);
            case "sin":
                return Math.sin(arg);
            case "cos":
                return Math.cos(arg);
        }

        throw new RuntimeException("Unsupported operation");
    }

    private boolean isBinaryOperation(String operand) {
        return operand.equals("+") || operand.equals("-") || operand.equals("*") || operand.equals("/")
                || operand.equals("pow");
    }

    private double doBinaryOperation(String operand, String firstArgument, String secondArgument) {
        double firstArg = Double.parseDouble(firstArgument);
        double secondArg = Double.parseDouble(secondArgument);
        switch (operand) {
            case "+":
                return firstArg + secondArg;
            case "-":
                return firstArg - secondArg;
            case "*":
                return firstArg * secondArg;
            case "/":
                return firstArg / secondArg;
            case "pow":
                return Math.pow(firstArg, secondArg);
        }

        throw new RuntimeException("Unsupported operation");
    }

    private void calculateExpression(String[] args) {
        Stack<String> stack = new Stack<>();
        Stack<String> helperStack = new Stack<>();

        Arrays.stream(args).forEachOrdered(stack::push);

        while (!stack.isEmpty()) {
            String arg = stack.pop();
            if (isBinaryOperation(arg)) {
                if (helperStack.size() < 2) {
                    throw new RuntimeException("Incorrect expression");
                }
                stack.push(Double.toString(doBinaryOperation(arg, helperStack.pop(),helperStack.pop())));

            } else if (isUnaryOperation(arg)) {
                if (helperStack.size() < 1) {
                    throw new RuntimeException("Incorrect expression");
                }
                stack.push(Double.toString(doUnaryOperation(arg, helperStack.pop())));
            } else {
                helperStack.push(arg);
            }
        }

        System.out.println(helperStack.pop());
    }

    public void startCalculator() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the expression in prefix form:");

        while (true) {
            String expression = reader.readLine();
            if (expression.isEmpty()) {
                break;
            }

            calculateExpression(expression.split(" "));
        }
    }

    public static void main(String[] args) throws IOException {
        Calculator calculator = new Calculator();
        calculator.startCalculator();
    }

}
