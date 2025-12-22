package com.fundamentals.operators;

/**
 * OperatorsDemo
 * 
 * Demonstrates all Java operators
 */
public class OperatorsDemo {

    public static void main(String[] args) {
        System.out.println("=== Java Operators Demo ===\n");

        // Arithmetic Operators
        System.out.println("--- Arithmetic Operators ---");
        int a = 10, b = 3;
        System.out.println("a + b = " + (a + b)); // Addition
        System.out.println("a - b = " + (a - b)); // Subtraction
        System.out.println("a * b = " + (a * b)); // Multiplication
        System.out.println("a / b = " + (a / b)); // Division
        System.out.println("a % b = " + (a % b)); // Modulus (remainder)

        // Comparison Operators
        System.out.println("\n--- Comparison Operators ---");
        System.out.println("a == b: " + (a == b)); // Equal to
        System.out.println("a != b: " + (a != b)); // Not equal to
        System.out.println("a > b: " + (a > b)); // Greater than
        System.out.println("a < b: " + (a < b)); // Less than
        System.out.println("a >= b: " + (a >= b)); // Greater than or equal
        System.out.println("a <= b: " + (a <= b)); // Less than or equal

        // Logical Operators
        System.out.println("\n--- Logical Operators ---");
        boolean x = true, y = false;
        System.out.println("x && y: " + (x && y)); // AND
        System.out.println("x || y: " + (x || y)); // OR
        System.out.println("!x: " + (!x)); // NOT

        // Ternary Operator
        System.out.println("\n--- Ternary Operator ---");
        int max = (a > b) ? a : b;
        System.out.println("Max of " + a + " and " + b + " is: " + max);
    }
}
