package com.fundamentals.datatypes;

/**
 * Primitive Types in Java
 * 
 * Java has 8 primitive types: byte, short, int, long, float, double, char,
 * boolean
 * - Primitives are stored directly in memory (stack)
 * - They cannot be null
 * - They have default values (0, 0.0, false, '\u0000')
 * - More memory efficient than wrapper classes
 */
public class PrimitiveTypes {

    public static void main(String[] args) {
        System.out.println("=== Primitive Types ===\n");

        demonstrateIntegerTypes();
        demonstrateFloatingPointTypes();
        demonstrateCharAndBoolean();
        demonstrateTricksAndPitfalls();
    }

    /**
     * Integer types: byte, short, int, long
     */
    private static void demonstrateIntegerTypes() {
        System.out.println("--- Integer Types ---");

        // BEST PRACTICE: Use 'int' as default for whole numbers
        int age = 25;

        // Use 'long' for large numbers (requires 'L' suffix)
        long worldPopulation = 8_000_000_000L; // Underscores for readability (Java 7+)

        // Use 'byte' for memory-critical scenarios (e.g., large arrays)
        byte smallValue = 127; // Range: -128 to 127

        // Use 'short' rarely (int is usually better)
        short mediumValue = 32000; // Range: -32,768 to 32,767

        System.out.println("int: " + age);
        System.out.println("long with underscores: " + worldPopulation);
        System.out.println("byte (saves memory): " + smallValue);
        System.out.println("short: " + mediumValue);

        // TRICK: Get min/max values
        System.out.println("\nInteger ranges:");
        System.out.println("int: " + Integer.MIN_VALUE + " to " + Integer.MAX_VALUE);
        System.out.println("long: " + Long.MIN_VALUE + " to " + Long.MAX_VALUE);
    }

    /**
     * Floating-point types: float, double
     */
    private static void demonstrateFloatingPointTypes() {
        System.out.println("\n--- Floating-Point Types ---");

        // BEST PRACTICE: Use 'double' as default for decimals
        double pi = 3.14159265359;

        // Use 'float' only when memory is critical (requires 'f' suffix)
        float approximatePi = 3.14f;

        System.out.println("double (recommended): " + pi);
        System.out.println("float (less precise): " + approximatePi);

        // PITFALL: Floating-point precision issues
        System.out.println("\nPrecision pitfall:");
        double result = 0.1 + 0.2;
        System.out.println("0.1 + 0.2 = " + result); // Not exactly 0.3!
        System.out.println("Use BigDecimal for financial calculations!");
    }

    /**
     * char and boolean types
     */
    private static void demonstrateCharAndBoolean() {
        System.out.println("\n--- char and boolean ---");

        // char: Single character (16-bit Unicode)
        char letter = 'A';
        char unicodeLetter = '\u0041'; // Also 'A'
        // Note: Emojis require 2 chars (surrogate pairs) in Java

        System.out.println("char: " + letter);
        System.out.println("Unicode: " + unicodeLetter);

        // boolean: true or false (no 0/1 like in C)
        boolean isJavaFun = true;
        System.out.println("boolean: " + isJavaFun);

        // TRICK: char is actually a number
        System.out.println("char as number: " + (int) letter); // 65
    }

    /**
     * Common tricks and pitfalls
     */
    private static void demonstrateTricksAndPitfalls() {
        System.out.println("\n--- Tricks & Pitfalls ---");

        // PITFALL: Integer overflow
        int maxInt = Integer.MAX_VALUE;
        System.out.println("Max int: " + maxInt);
        System.out.println("Max int + 1: " + (maxInt + 1)); // Wraps to negative!

        // TRICK: Binary, octal, hex literals
        int binary = 0b1010; // Binary
        int octal = 012; // Octal
        int hex = 0xA; // Hexadecimal
        System.out.println("\nAll equal to 10: " + binary + ", " + octal + ", " + hex);

        // BEST PRACTICE: Use underscores for readability
        long creditCard = 1234_5678_9012_3456L;
        int million = 1_000_000;
        System.out.println("Readable numbers: " + creditCard + ", " + million);

        System.out.println("\nExercise completed!");
    }
}
