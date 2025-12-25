package com.fundamentals.methods;

/**
 * Method Overloading and Varargs in Java
 * 
 * Method Overloading:
 * - Same method name, different parameters
 * - Different number of parameters, OR
 * - Different types of parameters, OR
 * - Different order of parameters
 * - Return type alone is NOT enough for overloading
 * 
 * Compiler Resolution:
 * 1. Exact match
 * 2. Widening primitive conversion
 * 3. Autoboxing
 * 4. Varargs
 * 
 * Varargs (Variable Arguments):
 * - Allows variable number of arguments
 * - Syntax: type... paramName
 * - Must be last parameter
 * - Treated as array inside method
 * - Only one varargs per method
 * 
 * Benefits:
 * - Flexibility in method calls
 * - Cleaner API
 * - Backward compatibility
 */
public class MethodOverloading {

    public static void main(String[] args) {
        System.out.println("=== Method Overloading & Varargs ===\n");

        demonstrateBasicOverloading();
        demonstrateTypeOverloading();
        demonstrateOrderOverloading();
        demonstrateVarargs();
        demonstrateOverloadingResolution();
        demonstrateTricksAndPitfalls();
    }

    /**
     * Basic overloading - different number of parameters
     */
    private static void demonstrateBasicOverloading() {
        System.out.println("--- Basic Overloading (Different Parameter Count) ---");

        // Same method name, different number of parameters
        System.out.println("add(5, 3) = " + add(5, 3));
        System.out.println("add(5, 3, 2) = " + add(5, 3, 2));
        System.out.println("add(5, 3, 2, 1) = " + add(5, 3, 2, 1));

        // Print methods
        print("Hello");
        print("Name", "Alice");
        print("Name", "Bob", "Age", "25");
    }

    // Overloaded add methods
    public static int add(int a, int b) {
        System.out.println("  Called: add(int, int)");
        return a + b;
    }

    public static int add(int a, int b, int c) {
        System.out.println("  Called: add(int, int, int)");
        return a + b + c;
    }

    public static int add(int a, int b, int c, int d) {
        System.out.println("  Called: add(int, int, int, int)");
        return a + b + c + d;
    }

    // Overloaded print methods
    public static void print(String message) {
        System.out.println("Message: " + message);
    }

    public static void print(String label, String value) {
        System.out.println(label + ": " + value);
    }

    public static void print(String label1, String value1, String label2, String value2) {
        System.out.println(label1 + ": " + value1 + ", " + label2 + ": " + value2);
    }

    /**
     * Type-based overloading - different parameter types
     */
    private static void demonstrateTypeOverloading() {
        System.out.println("\n--- Type-Based Overloading ---");

        // Same method name, different types
        display(42); // int
        display(3.14); // double
        display("Hello"); // String
        display(true); // boolean
        display(new int[] { 1, 2 }); // array

        // Calculate area
        System.out.println("\nArea of square: " + area(5));
        System.out.println("Area of rectangle: " + area(5, 3));
        System.out.println("Area of circle: " + area(2.5));
    }

    // Overloaded display methods
    public static void display(int value) {
        System.out.println("Integer: " + value);
    }

    public static void display(double value) {
        System.out.println("Double: " + value);
    }

    public static void display(String value) {
        System.out.println("String: " + value);
    }

    public static void display(boolean value) {
        System.out.println("Boolean: " + value);
    }

    public static void display(int[] values) {
        System.out.println("Array: " + java.util.Arrays.toString(values));
    }

    // Overloaded area calculations
    public static int area(int side) {
        System.out.println("  Called: area(int) - Square");
        return side * side;
    }

    public static int area(int length, int width) {
        System.out.println("  Called: area(int, int) - Rectangle");
        return length * width;
    }

    public static double area(double radius) {
        System.out.println("  Called: area(double) - Circle");
        return Math.PI * radius * radius;
    }

    /**
     * Order-based overloading - different parameter order
     */
    private static void demonstrateOrderOverloading() {
        System.out.println("\n--- Order-Based Overloading ---");

        // Different parameter order
        createUser("Alice", 25);
        createUser(30, "Bob");

        formatData("Name", 123);
        formatData(456, "Age");
    }

    public static void createUser(String name, int age) {
        System.out.println("Created user: " + name + ", age: " + age);
    }

    public static void createUser(int id, String name) {
        System.out.println("Created user with ID: " + id + ", name: " + name);
    }

    public static void formatData(String label, int value) {
        System.out.println(label + " = " + value);
    }

    public static void formatData(int value, String label) {
        System.out.println(value + " (" + label + ")");
    }

    /**
     * Varargs - variable number of arguments
     */
    private static void demonstrateVarargs() {
        System.out.println("\n--- Varargs (Variable Arguments) ---");

        // Can pass any number of arguments
        System.out.println("sum() = " + sum()); // 0 args
        System.out.println("sum(5) = " + sum(5)); // 1 arg
        System.out.println("sum(5, 3) = " + sum(5, 3)); // 2 args
        System.out.println("sum(5, 3, 2, 1) = " + sum(5, 3, 2, 1)); // 4 args

        // Can pass array
        int[] numbers = { 10, 20, 30 };
        System.out.println("sum(array) = " + sum(numbers));

        // Varargs with other parameters
        printWithPrefix("Numbers:", 1, 2, 3, 4, 5);

        // Multiple types
        formatOutput("Result", "Value: %d, Status: %s", 42, "OK");
    }

    // Varargs method
    public static int sum(int... numbers) {
        System.out.println("  Received " + numbers.length + " arguments");
        int total = 0;
        for (int num : numbers) {
            total += num;
        }
        return total;
    }

    // Varargs with other parameters (varargs must be last!)
    public static void printWithPrefix(String prefix, int... numbers) {
        System.out.print(prefix + " ");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Varargs with mixed types
    public static void formatOutput(String title, String format, Object... args) {
        System.out.println(title + ": " + String.format(format, args));
    }

    /**
     * Overloading resolution - how compiler chooses
     */
    private static void demonstrateOverloadingResolution() {
        System.out.println("\n--- Overloading Resolution ---");

        // 1. Exact match
        process(10); // Calls process(int)
        process(10L); // Calls process(long)

        // 2. Widening conversion
        byte b = 5;
        process(b); // byte -> int (widening)

        // 3. Autoboxing
        Integer i = 10;
        processObject(i); // Autoboxing to Object

        // 4. Varargs (last resort)
        processVarargs(1, 2, 3);

        System.out.println("\nResolution order:");
        System.out.println("1. Exact match");
        System.out.println("2. Widening primitive");
        System.out.println("3. Autoboxing");
        System.out.println("4. Varargs");
    }

    public static void process(int n) {
        System.out.println("process(int): " + n);
    }

    public static void process(long n) {
        System.out.println("process(long): " + n);
    }

    public static void processObject(Object obj) {
        System.out.println("processObject: " + obj);
    }

    public static void processVarargs(int... numbers) {
        System.out.println("processVarargs: " + numbers.length + " args");
    }

    /**
     * Tricks and pitfalls
     */
    private static void demonstrateTricksAndPitfalls() {
        System.out.println("\n--- Tricks & Pitfalls ---");

        // PITFALL 1: Ambiguous overloading
        System.out.println("PITFALL 1: Ambiguous calls");
        // ambiguous(null); // ERROR! Could be String or Integer
        ambiguous("Hello"); // OK
        ambiguous((String) null); // OK - explicit cast

        // PITFALL 2: Return type is not part of signature
        System.out.println("\nPITFALL 2: Return type doesn't matter");
        System.out.println("Can't overload based on return type alone!");
        // int getValue() { return 1; }
        // double getValue() { return 1.0; } // ERROR!

        // PITFALL 3: Varargs ambiguity
        System.out.println("\nPITFALL 3: Varargs can cause ambiguity");
        printNumbers(1, 2, 3); // Which method?

        // TRICK 1: Overloading with null-safe
        System.out.println("\nTRICK 1: Null-safe overloading");
        String result1 = concat("Hello", "World");
        String result2 = concat("Hello", null);
        System.out.println("Result 1: " + result1);
        System.out.println("Result 2: " + result2);

        // TRICK 2: Builder pattern with overloading
        System.out.println("\nTRICK 2: Builder pattern");
        buildMessage("Error");
        buildMessage("Error", "File not found");
        buildMessage("Error", "File not found", 404);

        // BEST PRACTICES
        System.out.println("\n--- Best Practices ---");
        System.out.println("1. Keep overloaded methods similar in behavior");
        System.out.println("2. Avoid ambiguous overloading");
        System.out.println("3. Use varargs sparingly (can be confusing)");
        System.out.println("4. Document which overload to prefer");
        System.out.println("5. Consider using different method names if behavior differs");
        System.out.println("6. Varargs must be last parameter");

        System.out.println("\nExercise completed!");
    }

    // Ambiguous methods
    public static void ambiguous(String s) {
        System.out.println("String version: " + s);
    }

    public static void ambiguous(Integer i) {
        System.out.println("Integer version: " + i);
    }

    // Varargs ambiguity
    public static void printNumbers(int... numbers) {
        System.out.println("Varargs version: " + numbers.length);
    }

    public static void printNumbers(int a, int b, int c) {
        System.out.println("Three-param version: " + a + ", " + b + ", " + c);
    }

    // Null-safe concatenation
    public static String concat(String a, String b) {
        if (a == null)
            a = "";
        if (b == null)
            b = "";
        return a + b;
    }

    // Builder pattern with overloading
    public static void buildMessage(String type) {
        System.out.println("[" + type + "]");
    }

    public static void buildMessage(String type, String message) {
        System.out.println("[" + type + "] " + message);
    }

    public static void buildMessage(String type, String message, int code) {
        System.out.println("[" + type + "] " + message + " (Code: " + code + ")");
    }
}
