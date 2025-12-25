package com.fundamentals.datatypes;

/**
 * Type Casting in Java
 * 
 * Two types of casting:
 * 1. Widening (Implicit) - Automatic, safe, no data loss
 * byte -> short -> int -> long -> float -> double
 * 
 * 2. Narrowing (Explicit) - Manual, risky, possible data loss
 * double -> float -> long -> int -> short -> byte
 * 
 * Key principle: Smaller types can fit into larger types automatically,
 * but going from larger to smaller requires explicit casting.
 */
public class TypeCasting {

    public static void main(String[] args) {
        System.out.println("=== Type Casting ===\n");

        demonstrateWideningCasting();
        demonstrateNarrowingCasting();
        demonstratePitfalls();
        demonstrateBestPractices();
    }

    /**
     * Widening (Implicit) Casting - Automatic and safe
     */
    private static void demonstrateWideningCasting() {
        System.out.println("--- Widening Casting (Automatic) ---");

        // int -> long (safe, no data loss)
        int intValue = 100;
        long longValue = intValue; // Automatic

        // int -> double (safe)
        double doubleValue = intValue;

        System.out.println("int: " + intValue);
        System.out.println("long: " + longValue);
        System.out.println("double: " + doubleValue);

        // Chain of widening
        byte b = 10;
        short s = b;
        int i = s;
        long l = i;
        float f = l;
        double d = f;
        System.out.println("\nWidening chain: byte -> short -> int -> long -> float -> double");
        System.out.println("Result: " + d);
    }

    /**
     * Narrowing (Explicit) Casting - Manual and risky
     */
    private static void demonstrateNarrowingCasting() {
        System.out.println("\n--- Narrowing Casting (Explicit) ---");

        // double -> int (loses decimal part)
        double pi = 3.14159;
        int truncatedPi = (int) pi; // Explicit cast required

        System.out.println("double: " + pi);
        System.out.println("int (truncated): " + truncatedPi);

        // long -> int (potential data loss)
        long bigNumber = 123456789L;
        int smallerNumber = (int) bigNumber;

        System.out.println("\nlong: " + bigNumber);
        System.out.println("int: " + smallerNumber);

        // TRICK: Rounding instead of truncating
        double value = 3.7;
        int rounded = (int) Math.round(value); // Rounds to nearest
        int truncated = (int) value; // Just cuts off decimal

        System.out.println("\nValue: " + value);
        System.out.println("Rounded: " + rounded); // 4
        System.out.println("Truncated: " + truncated); // 3
    }

    /**
     * Common pitfalls
     */
    private static void demonstratePitfalls() {
        System.out.println("\n--- Pitfalls ---");

        // PITFALL 1: Overflow when narrowing
        long tooBig = 3_000_000_000L; // Larger than Integer.MAX_VALUE
        int overflow = (int) tooBig;

        System.out.println("long: " + tooBig);
        System.out.println("int (overflow!): " + overflow); // Unexpected negative number!

        // PITFALL 2: Loss of precision with floating-point
        long largeLong = 123456789012345L;
        float asFloat = largeLong; // Widening, but loses precision!

        System.out.println("\nlong: " + largeLong);
        System.out.println("float (precision loss): " + (long) asFloat);

        // PITFALL 3: Division before casting
        int a = 5;
        int b = 2;
        double wrong = (double) (a / b); // 2.0 (division happens first as int)
        double correct = (double) a / b; // 2.5 (a is cast first)

        System.out.println("\n5 / 2:");
        System.out.println("Wrong: (double)(5/2) = " + wrong);
        System.out.println("Correct: (double)5/2 = " + correct);
    }

    /**
     * Best practices
     */
    private static void demonstrateBestPractices() {
        System.out.println("\n--- Best Practices ---");

        System.out.println("1. Prefer widening over narrowing (safer)");
        System.out.println("2. Always check bounds before narrowing:");

        long value = 100L;
        if (value >= Integer.MIN_VALUE && value <= Integer.MAX_VALUE) {
            int safe = (int) value;
            System.out.println("   Safe cast: " + safe);
        } else {
            System.out.println("   Value out of range!");
        }

        System.out.println("\n3. Use Math.round() for rounding, not truncating");
        System.out.println("4. Be careful with division - cast before dividing");
        System.out.println("5. For financial calculations, use BigDecimal (not float/double)");

        System.out.println("\nExercise completed!");
    }
}
