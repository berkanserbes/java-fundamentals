package com.fundamentals.methods;

/**
 * Method Basics in Java
 * 
 * A method is a block of code that performs a specific task.
 * 
 * Method Structure:
 * [access_modifier] [static] [return_type] methodName([parameters]) {
 * // method body
 * return value; // if return_type is not void
 * }
 * 
 * Key Concepts:
 * - Access Modifiers: public, private, protected, default
 * - static: belongs to class, not instance
 * - Return Type: void (no return) or any data type
 * - Parameters: input values (optional)
 * - Method Signature: method name + parameter types
 * 
 * Benefits:
 * - Code reusability
 * - Better organization
 * - Easier testing and debugging
 * - Abstraction and encapsulation
 */
public class MethodBasics {

    public static void main(String[] args) {
        System.out.println("=== Method Basics ===\n");

        demonstrateNoParametersNoReturn();
        demonstrateWithParameters();
        demonstrateWithReturn();
        demonstrateAccessModifiers();
        demonstrateStaticVsInstance();
        demonstrateMethodCalling();
    }

    /**
     * Methods with no parameters and no return value
     */
    private static void demonstrateNoParametersNoReturn() {
        System.out.println("--- No Parameters, No Return ---");

        printWelcome();
        printSeparator();
        printCurrentTime();
    }

    // Simple method - no params, no return
    public static void printWelcome() {
        System.out.println("Welcome to Java Methods!");
    }

    public static void printSeparator() {
        System.out.println("------------------------");
    }

    public static void printCurrentTime() {
        System.out.println("Current time: " + System.currentTimeMillis());
    }

    /**
     * Methods with parameters
     */
    private static void demonstrateWithParameters() {
        System.out.println("\n--- Methods with Parameters ---");

        // Single parameter
        greet("Alice");

        // Multiple parameters
        printPersonInfo("Bob", 25, "Engineer");

        // Different types
        calculateArea(5.5, 3.2);
    }

    public static void greet(String name) {
        System.out.println("Hello, " + name + "!");
    }

    public static void printPersonInfo(String name, int age, String job) {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Job: " + job);
    }

    public static void calculateArea(double width, double height) {
        double area = width * height;
        System.out.println("Area: " + area);
    }

    /**
     * Methods with return values
     */
    private static void demonstrateWithReturn() {
        System.out.println("\n--- Methods with Return Values ---");

        // Return primitive types
        int sum = add(10, 20);
        System.out.println("Sum: " + sum);

        double average = calculateAverage(10, 20, 30);
        System.out.println("Average: " + average);

        boolean isEven = checkEven(42);
        System.out.println("42 is even: " + isEven);

        // Return String
        String fullName = getFullName("John", "Doe");
        System.out.println("Full name: " + fullName);

        // Return array
        int[] numbers = generateNumbers(5);
        System.out.print("Generated numbers: ");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Multiple return points
        String grade = getGrade(85);
        System.out.println("Grade for 85: " + grade);
    }

    public static int add(int a, int b) {
        return a + b;
    }

    public static double calculateAverage(int a, int b, int c) {
        return (a + b + c) / 3.0;
    }

    public static boolean checkEven(int number) {
        return number % 2 == 0;
    }

    public static String getFullName(String firstName, String lastName) {
        return firstName + " " + lastName;
    }

    public static int[] generateNumbers(int count) {
        int[] numbers = new int[count];
        for (int i = 0; i < count; i++) {
            numbers[i] = i + 1;
        }
        return numbers;
    }

    // Multiple return points
    public static String getGrade(int score) {
        if (score >= 90)
            return "A";
        if (score >= 80)
            return "B";
        if (score >= 70)
            return "C";
        if (score >= 60)
            return "D";
        return "F";
    }

    /**
     * Access modifiers demonstration
     */
    private static void demonstrateAccessModifiers() {
        System.out.println("\n--- Access Modifiers ---");

        publicMethod(); // Accessible everywhere
        privateMethod(); // Only within this class
        // protectedMethod(); // Would be accessible in subclasses
        defaultMethod(); // Accessible in same package

        System.out.println("Access modifiers control method visibility");
    }

    public static void publicMethod() {
        System.out.println("Public: Accessible everywhere");
    }

    private static void privateMethod() {
        System.out.println("Private: Only within this class");
    }

    protected static void protectedMethod() {
        System.out.println("Protected: This class + subclasses + same package");
    }

    static void defaultMethod() {
        System.out.println("Default: Same package only");
    }

    /**
     * Static vs Instance methods
     */
    private static void demonstrateStaticVsInstance() {
        System.out.println("\n--- Static vs Instance Methods ---");

        // Static method - called on class
        staticMethod();
        MethodBasics.staticMethod(); // Can use class name

        // Instance method - needs object
        MethodBasics obj = new MethodBasics();
        obj.instanceMethod();

        // Static can't call instance directly
        // instanceMethod(); // ERROR!

        System.out.println("\nStatic: belongs to class");
        System.out.println("Instance: belongs to object");
    }

    public static void staticMethod() {
        System.out.println("Static method called");
        // Can access: static variables, static methods
        // Cannot access: instance variables, instance methods
    }

    public void instanceMethod() {
        System.out.println("Instance method called");
        // Can access: everything (static + instance)
        staticMethod(); // Instance can call static
    }

    /**
     * Method calling patterns
     */
    private static void demonstrateMethodCalling() {
        System.out.println("\n--- Method Calling Patterns ---");

        // Direct call
        int result1 = multiply(5, 3);
        System.out.println("Direct call: " + result1);

        // Nested call
        int result2 = multiply(add(2, 3), 4);
        System.out.println("Nested call: " + result2);

        // Chain of calls
        int result3 = processNumber(10);
        System.out.println("Chain result: " + result3);

        // Method as parameter
        printResult(add(10, 20));

        // Void method in expression (not allowed)
        // int x = printWelcome(); // ERROR!
    }

    public static int multiply(int a, int b) {
        return a * b;
    }

    public static int processNumber(int n) {
        n = add(n, 5);
        n = multiply(n, 2);
        return n;
    }

    public static void printResult(int value) {
        System.out.println("Result: " + value);
    }

    // Helper method for demonstration
    private static void demonstrateBestPractices() {
        System.out.println("\n--- Best Practices ---");
        System.out.println("1. Use descriptive method names (verbs)");
        System.out.println("2. Keep methods short and focused (single responsibility)");
        System.out.println("3. Use proper access modifiers");
        System.out.println("4. Document complex methods with comments");
        System.out.println("5. Avoid too many parameters (max 3-4)");
        System.out.println("6. Return early to avoid deep nesting");

        System.out.println("\nExercise completed!");
    }
}
