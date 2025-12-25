package com.fundamentals.methods;

/**
 * Method Parameters in Java
 * 
 * Parameter Types:
 * 1. Primitive Parameters: Passed by value (copy)
 * 2. Reference Parameters: Reference passed by value
 * 3. final Parameters: Cannot be reassigned
 * 4. Variable-length Parameters (varargs): Variable number of arguments
 * 
 * Key Concepts:
 * - Pass by Value: Java ALWAYS passes by value
 * - Primitives: value is copied
 * - Objects: reference value is copied (can modify object, not reference)
 * - final: prevents parameter reassignment
 * - varargs: must be last parameter, treated as array
 * 
 * Common Pitfalls:
 * - Thinking Java passes objects by reference (it doesn't!)
 * - Modifying primitive parameters (doesn't affect original)
 * - Confusion between modifying object vs reassigning reference
 */
public class MethodParameters {

    public static void main(String[] args) {
        System.out.println("=== Method Parameters ===\n");

        demonstratePrimitiveParameters();
        demonstrateReferenceParameters();
        demonstrateFinalParameters();
        demonstrateArrayParameters();
        demonstrateStringParameters();
        demonstrateTricksAndPitfalls();
    }

    /**
     * Primitive parameters - passed by value
     */
    private static void demonstratePrimitiveParameters() {
        System.out.println("--- Primitive Parameters (Pass by Value) ---");

        int number = 10;
        System.out.println("Before: " + number);

        modifyPrimitive(number);
        System.out.println("After modifyPrimitive: " + number); // Still 10!

        // The method receives a COPY of the value
        System.out.println("\nPrimitives are passed by VALUE (copy)");
        System.out.println("Changes inside method don't affect original");

        // Multiple primitive parameters
        int result = calculate(5, 3, 2);
        System.out.println("Calculate result: " + result);
    }

    public static void modifyPrimitive(int n) {
        System.out.println("  Inside method before: " + n);
        n = n * 2; // Only modifies the copy!
        System.out.println("  Inside method after: " + n);
    }

    public static int calculate(int a, int b, int c) {
        return a * b + c;
    }

    /**
     * Reference parameters - reference passed by value
     */
    private static void demonstrateReferenceParameters() {
        System.out.println("\n--- Reference Parameters ---");

        // Arrays are objects (reference types)
        int[] numbers = { 1, 2, 3 };
        System.out.println("Before: " + java.util.Arrays.toString(numbers));

        modifyArray(numbers);
        System.out.println("After modifyArray: " + java.util.Arrays.toString(numbers));
        // Array IS modified! (we modified the object, not the reference)

        // But reassignment doesn't work
        reassignArray(numbers);
        System.out.println("After reassignArray: " + java.util.Arrays.toString(numbers));
        // Array NOT changed! (reassignment only affects local reference)

        System.out.println("\nObjects: reference is passed by value");
        System.out.println("Can modify object, but can't change reference");

        // Custom object example
        Person person = new Person("Alice", 25);
        System.out.println("\nBefore: " + person);

        modifyPerson(person);
        System.out.println("After modifyPerson: " + person); // Modified!

        reassignPerson(person);
        System.out.println("After reassignPerson: " + person); // Not changed!
    }

    public static void modifyArray(int[] arr) {
        arr[0] = 999; // Modifies the actual array
        System.out.println("  Inside modifyArray: " + java.util.Arrays.toString(arr));
    }

    public static void reassignArray(int[] arr) {
        arr = new int[] { 7, 8, 9 }; // Only changes local reference!
        System.out.println("  Inside reassignArray: " + java.util.Arrays.toString(arr));
    }

    public static void modifyPerson(Person p) {
        p.age = 30; // Modifies the actual object
        System.out.println("  Inside modifyPerson: " + p);
    }

    public static void reassignPerson(Person p) {
        p = new Person("Bob", 40); // Only changes local reference!
        System.out.println("  Inside reassignPerson: " + p);
    }

    /**
     * final parameters - cannot be reassigned
     */
    private static void demonstrateFinalParameters() {
        System.out.println("\n--- final Parameters ---");

        int value = 10;
        processFinalPrimitive(value);

        int[] array = { 1, 2, 3 };
        processFinalArray(array);
        System.out.println("Array after processFinalArray: " + java.util.Arrays.toString(array));

        System.out.println("\nfinal prevents reassignment, not modification");
    }

    // final prevents reassignment of parameter
    public static void processFinalPrimitive(final int n) {
        System.out.println("Final primitive: " + n);
        // n = 20; // ERROR! Cannot reassign final parameter

        // But you can use it in expressions
        int result = n * 2;
        System.out.println("Result: " + result);
    }

    public static void processFinalArray(final int[] arr) {
        // Can modify array contents
        arr[0] = 999; // OK!

        // Cannot reassign reference
        // arr = new int[]{4, 5, 6}; // ERROR! Cannot reassign final parameter

        System.out.println("Modified array: " + java.util.Arrays.toString(arr));
    }

    /**
     * Array parameters
     */
    private static void demonstrateArrayParameters() {
        System.out.println("\n--- Array Parameters ---");

        int[] numbers = { 5, 2, 8, 1, 9 };

        int sum = sumArray(numbers);
        System.out.println("Sum: " + sum);

        int max = findMax(numbers);
        System.out.println("Max: " + max);

        printArray(numbers);

        // Multidimensional array
        int[][] matrix = { { 1, 2 }, { 3, 4 }, { 5, 6 } };
        printMatrix(matrix);
    }

    public static int sumArray(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return sum;
    }

    public static int findMax(int[] arr) {
        if (arr.length == 0)
            return 0;

        int max = arr[0];
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    public static void printArray(int[] arr) {
        System.out.print("Array: [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static void printMatrix(int[][] matrix) {
        System.out.println("Matrix:");
        for (int[] row : matrix) {
            System.out.println("  " + java.util.Arrays.toString(row));
        }
    }

    /**
     * String parameters - special case
     */
    private static void demonstrateStringParameters() {
        System.out.println("\n--- String Parameters ---");

        String text = "Hello";
        System.out.println("Before: " + text);

        modifyString(text);
        System.out.println("After modifyString: " + text); // Still "Hello"!

        // Strings are immutable - behave like primitives
        System.out.println("\nStrings are IMMUTABLE");
        System.out.println("String modifications create new objects");

        // To "modify" a string, return new value
        String modified = appendToString(text, " World");
        System.out.println("Modified string: " + modified);
    }

    public static void modifyString(String s) {
        s = s + " World"; // Creates new String, doesn't affect original
        System.out.println("  Inside method: " + s);
    }

    public static String appendToString(String s, String suffix) {
        return s + suffix; // Return new String
    }

    /**
     * Tricks and pitfalls
     */
    private static void demonstrateTricksAndPitfalls() {
        System.out.println("\n--- Tricks & Pitfalls ---");

        // PITFALL 1: Expecting primitive changes to persist
        System.out.println("PITFALL 1: Primitive modifications don't persist");
        int x = 5;
        tryToDouble(x);
        System.out.println("x is still: " + x); // Still 5

        // TRICK 1: Use return value instead
        x = doubleValue(x);
        System.out.println("Now x is: " + x); // 10

        // PITFALL 2: Null parameters
        System.out.println("\nPITFALL 2: Null parameters");
        try {
            processArray(null);
        } catch (NullPointerException e) {
            System.out.println("NPE when passing null!");
        }

        // TRICK 2: Null-safe methods
        int safeSum = sumArraySafe(null);
        System.out.println("Null-safe sum: " + safeSum);

        // PITFALL 3: Array/Object modification side effects
        System.out.println("\nPITFALL 3: Unintended modifications");
        int[] original = { 1, 2, 3 };
        int[] modified = modifyAndReturn(original);
        System.out.println("Original: " + java.util.Arrays.toString(original));
        System.out.println("Modified: " + java.util.Arrays.toString(modified));
        System.out.println("Both changed! (same object)");

        // TRICK 3: Create defensive copies
        int[] copy = { 1, 2, 3 };
        int[] safe = modifyWithCopy(copy);
        System.out.println("\nWith defensive copy:");
        System.out.println("Original: " + java.util.Arrays.toString(copy));
        System.out.println("Modified: " + java.util.Arrays.toString(safe));

        // BEST PRACTICES
        System.out.println("\n--- Best Practices ---");
        System.out.println("1. Use final for parameters you won't reassign");
        System.out.println("2. Document if method modifies parameters");
        System.out.println("3. Return new values instead of modifying primitives");
        System.out.println("4. Be careful with null parameters");
        System.out.println("5. Consider defensive copies for arrays/objects");
        System.out.println("6. Keep parameter count low (max 3-4)");

        System.out.println("\nExercise completed!");
    }

    public static void tryToDouble(int n) {
        n = n * 2; // Doesn't affect original
    }

    public static int doubleValue(int n) {
        return n * 2; // Return new value
    }

    public static void processArray(int[] arr) {
        System.out.println("Length: " + arr.length); // NPE if arr is null!
    }

    public static int sumArraySafe(int[] arr) {
        if (arr == null)
            return 0; // Null check

        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return sum;
    }

    public static int[] modifyAndReturn(int[] arr) {
        arr[0] = 999; // Modifies original!
        return arr;
    }

    public static int[] modifyWithCopy(int[] arr) {
        int[] copy = arr.clone(); // Defensive copy
        copy[0] = 999;
        return copy;
    }
}

/**
 * Helper class for demonstration
 */
class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}
