package com.fundamentals.datatypes;

/**
 * Wrapper Classes in Java
 * 
 * Every primitive type has a corresponding wrapper class:
 * byte -> Byte, short -> Short, int -> Integer, long -> Long
 * float -> Float, double -> Double, char -> Character, boolean -> Boolean
 * 
 * Why use wrappers?
 * - Can be null (primitives cannot)
 * - Required for Collections (List<Integer>, not List<int>)
 * - Provide utility methods (parsing, conversion, constants)
 * 
 * Trade-off: Wrappers use more memory and are slower than primitives
 */
public class WrapperClasses {

    public static void main(String[] args) {
        System.out.println("=== Wrapper Classes ===\n");

        demonstrateBasicUsage();
        demonstrateAutoboxing();
        demonstrateUtilityMethods();
        demonstratePitfalls();
    }

    /**
     * Basic wrapper usage
     */
    private static void demonstrateBasicUsage() {
        System.out.println("--- Basic Usage ---");

        // Creating wrappers
        Integer num1 = Integer.valueOf(42); // Recommended way
        Integer num2 = 42; // Autoboxing (automatic conversion)

        // BEST PRACTICE: Wrappers can be null (use for optional values)
        Integer nullableAge = null;
        // int primitiveAge = null; // ERROR: primitives cannot be null

        System.out.println("Wrapper valueOf: " + num1);
        System.out.println("Wrapper autoboxed: " + num2);
        System.out.println("Nullable: " + nullableAge);

        // Converting back to primitive
        int primitive = num1.intValue();
        System.out.println("Back to primitive: " + primitive);
    }

    /**
     * Autoboxing and Unboxing (Java 5+)
     */
    private static void demonstrateAutoboxing() {
        System.out.println("\n--- Autoboxing & Unboxing ---");

        // Autoboxing: primitive -> wrapper (automatic)
        Integer autoBoxed = 100; // int -> Integer

        // Unboxing: wrapper -> primitive (automatic)
        int autoUnboxed = autoBoxed; // Integer -> int

        System.out.println("Autoboxed: " + autoBoxed);
        System.out.println("Unboxed: " + autoUnboxed);

        // Works in expressions too
        Integer a = 10;
        Integer b = 20;
        int sum = a + b; // Automatic unboxing
        System.out.println("Sum: " + sum);
    }

    /**
     * Useful utility methods
     */
    private static void demonstrateUtilityMethods() {
        System.out.println("\n--- Utility Methods ---");

        // Parsing strings to numbers
        int parsed = Integer.parseInt("123");
        double parsedDouble = Double.parseDouble("3.14");
        System.out.println("Parsed int: " + parsed);
        System.out.println("Parsed double: " + parsedDouble);

        // Constants
        System.out.println("\nUseful constants:");
        System.out.println("Integer.MAX_VALUE: " + Integer.MAX_VALUE);
        System.out.println("Integer.MIN_VALUE: " + Integer.MIN_VALUE);
        System.out.println("Double.NaN: " + Double.NaN);
        System.out.println("Double.POSITIVE_INFINITY: " + Double.POSITIVE_INFINITY);

        // Conversion methods
        String hexString = Integer.toHexString(255);
        String binaryString = Integer.toBinaryString(10);
        System.out.println("\n255 in hex: " + hexString);
        System.out.println("10 in binary: " + binaryString);

        // Comparison
        Integer x = 100;
        Integer y = 200;
        int comparison = Integer.compare(x, y); // Returns -1, 0, or 1
        System.out.println("\nCompare 100 vs 200: " + comparison);
    }

    /**
     * Common pitfalls
     */
    private static void demonstratePitfalls() {
        System.out.println("\n--- Pitfalls ---");

        // PITFALL 1: NullPointerException with autoboxing
        Integer nullValue = null;
        try {
            @SuppressWarnings("null") // Intentional NPE for demonstration
            int result = nullValue + 10; // NPE! Tries to unbox null
            System.out.println("This won't print: " + result);
        } catch (NullPointerException e) {
            System.out.println("NPE when unboxing null!");
        }

        // PITFALL 2: == vs .equals()
        Integer a = 127;
        Integer b = 127;
        Integer c = 128;
        Integer d = 128;

        System.out.println("\n127 == 127: " + (a == b)); // true (cached)
        System.out.println("128 == 128: " + (c == d)); // false! (not cached)
        System.out.println("128.equals(128): " + c.equals(d)); // true

        // BEST PRACTICE: Always use .equals() for wrapper comparison
        System.out.println("\nAlways use .equals() for wrappers!");

        // TRICK: Integer cache (-128 to 127)
        System.out.println("\nIntegers from -128 to 127 are cached for performance");

        System.out.println("\nExercise completed!");
    }
}
