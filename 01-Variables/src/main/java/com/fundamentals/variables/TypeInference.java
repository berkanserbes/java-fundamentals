package com.fundamentals.variables;

/**
 * Type Inference with 'var'
 * 
 * This exercise demonstrates:
 * - Using 'var' keyword (Java 10+)
 * - When to use var and when not to
 * - Limitations of var
 * - Best practices
 */
public class TypeInference {

    public static void main(String[] args) {
        System.out.println("=== Type Inference with 'var' ===\n");

        // Traditional way - explicit type declaration
        String name = "Alice";
        int age = 25;
        double salary = 50000.0;
        boolean isActive = true;

        System.out.println("--- Traditional Declaration ---");
        System.out.println("String name = " + name);
        System.out.println("int age = " + age);
        System.out.println("double salary = " + salary);
        System.out.println("boolean isActive = " + isActive);

        // Using 'var' - type is inferred from the right side
        var inferredName = "Bob"; // Inferred as String
        var inferredAge = 30; // Inferred as int
        var inferredSalary = 60000.0; // Inferred as double
        var inferredIsActive = false; // Inferred as boolean

        System.out.println("\n--- Using 'var' (Type Inference) ---");
        System.out.println("var inferredName = " + inferredName);
        System.out.println("var inferredAge = " + inferredAge);
        System.out.println("var inferredSalary = " + inferredSalary);
        System.out.println("var inferredIsActive = " + inferredIsActive);

        // GOOD: var is useful for complex types
        var longTypeName = new java.util.ArrayList<String>();
        longTypeName.add("Item 1");
        longTypeName.add("Item 2");

        System.out.println("\n--- var with Complex Types ---");
        System.out.println("ArrayList: " + longTypeName);

        // GOOD: var in loops
        var numbers = new int[] { 1, 2, 3, 4, 5 };
        System.out.println("\n--- var in Loops ---");
        for (var number : numbers) {
            System.out.println("Number: " + number);
        }

        // Demonstrate limitations and best practices
        demonstrateLimitations();
        demonstrateBestPractices();

        System.out.println("\nExercise completed!");
    }

    /**
     * Demonstrates limitations of var
     */
    private static void demonstrateLimitations() {
        System.out.println("\n--- Limitations of 'var' ---");

        // CANNOT use var without initialization
        // var x; // Compilation error!
        // x = 10;

        // CANNOT use var with null
        // var nullValue = null; // Compilation error!

        // But you can do this:
        var nullableString = (String) null; // Must cast

        // CANNOT use var for instance variables
        // Only works for local variables

        // CANNOT use var for method parameters
        // public void method(var param) { } // Compilation error!

        // CANNOT use var for method return types
        // public var method() { } // Compilation error!

        System.out.println("var limitations:");
        System.out.println("  1. Must be initialized when declared");
        System.out.println("  2. Cannot be initialized with null (without cast)");
        System.out.println("  3. Only for local variables");
        System.out.println("  4. Not for fields, parameters, or return types");
    }

    /**
     * Demonstrates best practices for using var
     */
    private static void demonstrateBestPractices() {
        System.out.println("\n--- Best Practices ---");

        // GOOD: Type is obvious from right side
        var message = "Hello, World!";
        var count = 42;
        var price = 19.99;

        // GOOD: Reduces verbosity with long type names
        var studentList = new java.util.ArrayList<String>();

        // AVOID: Type is not obvious
        var result = calculate(); // What type is this?

        // BETTER: Use explicit type when it's not obvious
        int betterResult = calculate();

        System.out.println("Best practices:");
        System.out.println("  1. Use var when type is obvious from initialization");
        System.out.println("  2. Use var to reduce verbosity with long type names");
        System.out.println("  3. Use explicit types when it improves readability");
        System.out.println("  4. Be consistent within your codebase");
    }

    private static int calculate() {
        return 100;
    }
}
