package com.fundamentals.arrays;

import java.util.Arrays;

/**
 * Array Basics in Java
 * 
 * An array is a container object that holds a fixed number of values of a
 * single type.
 * 
 * Key Characteristics:
 * - Fixed size (cannot be changed after creation)
 * - Homogeneous (all elements same type)
 * - Zero-indexed (first element at index 0)
 * - Reference type (stored in heap)
 * - Can store primitives or objects
 * 
 * Declaration Syntax:
 * type[] arrayName; // Preferred
 * type arrayName[]; // C-style (not recommended)
 * 
 * Memory:
 * - Array object in heap
 * - Array reference in stack
 * - Primitives: values stored directly
 * - Objects: references stored in array
 */
public class ArrayBasics {

    public static void main(String[] args) {
        System.out.println("=== Array Basics ===\n");

        demonstrateDeclaration();
        demonstrateInitialization();
        demonstrateAccessing();
        demonstrateModification();
        demonstrateIteration();
        demonstrateLength();
        demonstrateTricksAndPitfalls();
    }

    /**
     * Array declaration
     */
    private static void demonstrateDeclaration() {
        System.out.println("--- Array Declaration ---");

        // Declaration only (no initialization)
        int[] numbers;
        String[] names;
        double[] prices;

        // Declaration with size
        int[] scores = new int[5]; // Creates array of 5 integers (all 0)
        System.out.println("Empty int array: " + Arrays.toString(scores));

        // Different types
        boolean[] flags = new boolean[3]; // All false
        String[] words = new String[3]; // All null

        System.out.println("Empty boolean array: " + Arrays.toString(flags));
        System.out.println("Empty String array: " + Arrays.toString(words));

        // Preferred vs C-style
        int[] preferred = new int[3]; // Recommended
        int cStyle[] = new int[3]; // Not recommended

        System.out.println("Both styles work, but [] after type is preferred");
    }

    /**
     * Array initialization
     */
    private static void demonstrateInitialization() {
        System.out.println("\n--- Array Initialization ---");

        // Method 1: Array literal (inline initialization)
        int[] numbers = { 1, 2, 3, 4, 5 };
        System.out.println("Array literal: " + Arrays.toString(numbers));

        // Method 2: new keyword with values
        String[] fruits = new String[] { "Apple", "Banana", "Cherry" };
        System.out.println("With new keyword: " + Arrays.toString(fruits));

        // Method 3: Create then assign
        double[] prices = new double[3];
        prices[0] = 9.99;
        prices[1] = 19.99;
        prices[2] = 29.99;
        System.out.println("Create then assign: " + Arrays.toString(prices));

        // Default values
        int[] defaultInts = new int[3]; // [0, 0, 0]
        double[] defaultDoubles = new double[3]; // [0.0, 0.0, 0.0]
        boolean[] defaultBools = new boolean[3]; // [false, false, false]
        String[] defaultStrings = new String[3]; // [null, null, null]

        System.out.println("\nDefault values:");
        System.out.println("int: " + Arrays.toString(defaultInts));
        System.out.println("double: " + Arrays.toString(defaultDoubles));
        System.out.println("boolean: " + Arrays.toString(defaultBools));
        System.out.println("String: " + Arrays.toString(defaultStrings));
    }

    /**
     * Accessing array elements
     */
    private static void demonstrateAccessing() {
        System.out.println("\n--- Accessing Elements ---");

        String[] colors = { "Red", "Green", "Blue", "Yellow", "Purple" };

        // Access by index (0-based)
        System.out.println("First element: " + colors[0]);
        System.out.println("Second element: " + colors[1]);
        System.out.println("Last element: " + colors[colors.length - 1]);

        // Access from end
        System.out.println("Second to last: " + colors[colors.length - 2]);

        // TRICK: Safe access with bounds check
        int index = 10;
        if (index >= 0 && index < colors.length) {
            System.out.println("Element at " + index + ": " + colors[index]);
        } else {
            System.out.println("Index " + index + " is out of bounds");
        }
    }

    /**
     * Modifying array elements
     */
    private static void demonstrateModification() {
        System.out.println("\n--- Modifying Elements ---");

        int[] numbers = { 10, 20, 30, 40, 50 };
        System.out.println("Original: " + Arrays.toString(numbers));

        // Modify single element
        numbers[0] = 100;
        System.out.println("After modifying first: " + Arrays.toString(numbers));

        // Modify multiple elements
        numbers[2] = 300;
        numbers[4] = 500;
        System.out.println("After modifying multiple: " + Arrays.toString(numbers));

        // Modify using expression
        numbers[1] = numbers[1] * 2;
        System.out.println("After doubling second: " + Arrays.toString(numbers));

        // Fill all with same value
        int[] filled = new int[5];
        Arrays.fill(filled, 7);
        System.out.println("Filled with 7: " + Arrays.toString(filled));

        // Fill range
        int[] partial = { 1, 2, 3, 4, 5 };
        Arrays.fill(partial, 1, 4, 0); // Fill index 1-3 with 0
        System.out.println("Partial fill: " + Arrays.toString(partial));
    }

    /**
     * Iterating through arrays
     */
    private static void demonstrateIteration() {
        System.out.println("\n--- Iterating Through Arrays ---");

        String[] languages = { "Java", "Python", "JavaScript", "C++", "Go" };

        // Method 1: Traditional for loop
        System.out.println("Traditional for loop:");
        for (int i = 0; i < languages.length; i++) {
            System.out.println("  [" + i + "] " + languages[i]);
        }

        // Method 2: Enhanced for loop (for-each)
        System.out.println("\nEnhanced for loop:");
        for (String lang : languages) {
            System.out.println("  " + lang);
        }

        // Method 3: While loop
        System.out.println("\nWhile loop:");
        int i = 0;
        while (i < languages.length) {
            System.out.println("  " + languages[i]);
            i++;
        }

        // Reverse iteration
        System.out.println("\nReverse iteration:");
        for (int j = languages.length - 1; j >= 0; j--) {
            System.out.println("  " + languages[j]);
        }

        // BEST PRACTICE: Use enhanced for when you don't need index
        System.out.println("\nBest practice: Use for-each when index not needed");
    }

    /**
     * Array length property
     */
    private static void demonstrateLength() {
        System.out.println("\n--- Array Length ---");

        int[] arr1 = { 1, 2, 3 };
        int[] arr2 = new int[10];
        int[] arr3 = {};

        System.out.println("arr1.length: " + arr1.length);
        System.out.println("arr2.length: " + arr2.length);
        System.out.println("arr3.length (empty): " + arr3.length);

        // length is a property, not a method (no parentheses)
        System.out.println("\nNote: length is a property, not a method");
        System.out.println("Use: array.length (not array.length())");

        // Length is final (cannot be changed)
        System.out.println("\nArray size is FIXED after creation");
        System.out.println("Cannot resize array (use ArrayList for dynamic size)");
    }

    /**
     * Tricks and pitfalls
     */
    private static void demonstrateTricksAndPitfalls() {
        System.out.println("\n--- Tricks & Pitfalls ---");

        // PITFALL 1: ArrayIndexOutOfBoundsException
        System.out.println("PITFALL 1: Index out of bounds");
        int[] numbers = { 1, 2, 3 };
        try {
            int value = numbers[5]; // Index 5 doesn't exist
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught: " + e.getClass().getSimpleName());
        }

        // PITFALL 2: NullPointerException
        System.out.println("\nPITFALL 2: Null array");
        int[] nullArray = null;
        try {
            int len = nullArray.length;
        } catch (NullPointerException e) {
            System.out.println("Caught: " + e.getClass().getSimpleName());
        }

        // PITFALL 3: Arrays are objects (reference type)
        System.out.println("\nPITFALL 3: Arrays are reference types");
        int[] original = { 1, 2, 3 };
        int[] reference = original; // Same array!
        reference[0] = 999;
        System.out.println("Original: " + Arrays.toString(original));
        System.out.println("Reference: " + Arrays.toString(reference));
        System.out.println("Both changed (same object)");

        // TRICK 1: Clone to create copy
        System.out.println("\nTRICK 1: Use clone() for shallow copy");
        int[] copy = original.clone();
        copy[0] = 1;
        System.out.println("Original: " + Arrays.toString(original));
        System.out.println("Copy: " + Arrays.toString(copy));

        // TRICK 2: Arrays.copyOf for copy with different size
        System.out.println("\nTRICK 2: Arrays.copyOf for resizing");
        int[] source = { 1, 2, 3 };
        int[] larger = Arrays.copyOf(source, 5); // Pad with 0
        int[] smaller = Arrays.copyOf(source, 2); // Truncate
        System.out.println("Source: " + Arrays.toString(source));
        System.out.println("Larger: " + Arrays.toString(larger));
        System.out.println("Smaller: " + Arrays.toString(smaller));

        // TRICK 3: Anonymous arrays
        System.out.println("\nTRICK 3: Anonymous arrays");
        printArray(new int[] { 10, 20, 30 }); // Create and pass directly

        // BEST PRACTICES
        System.out.println("\n--- Best Practices ---");
        System.out.println("1. Always check bounds before accessing");
        System.out.println("2. Check for null before using array");
        System.out.println("3. Use enhanced for loop when index not needed");
        System.out.println("4. Remember: arrays are fixed size");
        System.out.println("5. Use clone() or Arrays.copyOf() to copy");
        System.out.println("6. Prefer type[] over type[] syntax");

        System.out.println("\nExercise completed!");
    }

    private static void printArray(int[] arr) {
        System.out.println("Anonymous array: " + Arrays.toString(arr));
    }
}
