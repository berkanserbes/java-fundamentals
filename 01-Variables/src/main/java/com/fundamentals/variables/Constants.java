package com.fundamentals.variables;

/**
 * Constants
 * 
 * Demonstrates:
 * - Using 'final' keyword for constants
 * - Naming conventions (UPPER_SNAKE_CASE)
 * - Different types of constants
 */
public class Constants {

    // Class-level constants (static final)
    private static final double PI = 3.14159;
    private static final int MAX_STUDENTS = 30;

    public static void main(String[] args) {
        // Local constants
        final int DAYS_IN_WEEK = 7;
        final double TAX_RATE = 0.18;
        final String userName;
        userName = "User";

        // DAYS_IN_WEEK = 8; // Error: cannot assign a value to final variable

        System.out.println("--- Local Constants ---");
        System.out.println("User name: " + userName);
        System.out.println("Days in week: " + DAYS_IN_WEEK);
        System.out.println("Tax rate: " + (TAX_RATE * 100) + "%");

        // Class constants
        System.out.println("\n--- Class Constants ---");
        System.out.println("PI: " + PI);
        System.out.println("Max students: " + MAX_STUDENTS);

        double price = 100.0;
        double total = price + (price * TAX_RATE);
        System.out.println("Price: " + price + " TL");
        System.out.println("Total with tax: " + total + " TL");
    }
}
