package com.fundamentals.oop;

/**
 * Fields and Methods in Java
 * 
 * Fields (Variables):
 * - Instance variables: Belong to object, each object has its own copy
 * - Static variables: Belong to class, shared among all objects
 * - Local variables: Declared in methods, exist only during method execution
 * - Parameters: Special local variables passed to methods
 * 
 * Methods:
 * - Instance methods: Operate on instance variables, need object to call
 * - Static methods: Belong to class, can be called without object
 * - Constructors: Special methods to initialize objects
 * 
 * Access Modifiers:
 * - public: Accessible everywhere
 * - private: Only within same class
 * - protected: Same class, subclasses, same package
 * - default (no modifier): Same package only
 */
public class FieldsAndMethods {

    public static void main(String[] args) {
        System.out.println("=== Fields and Methods ===\n");

        demonstrateInstanceFields();
        demonstrateStaticFields();
        demonstrateConstructors();
        demonstrateInstanceMethods();
        demonstrateStaticMethods();
        demonstrateMethodOverloading();
        demonstrateThisKeyword();
    }

    /**
     * Instance fields demonstration
     */
    private static void demonstrateInstanceFields() {
        System.out.println("--- Instance Fields ---");

        BankAccount account1 = new BankAccount("ACC001", "Alice");
        BankAccount account2 = new BankAccount("ACC002", "Bob");

        // Each object has its own instance variables
        account1.deposit(1000);
        account2.deposit(500);

        account1.displayBalance();
        account2.displayBalance();

        System.out.println("Each object has its own instance fields");
    }

    /**
     * Static fields demonstration
     */
    private static void demonstrateStaticFields() {
        System.out.println("\n--- Static Fields ---");

        Counter c1 = new Counter();
        Counter c2 = new Counter();
        Counter c3 = new Counter();

        c1.increment();
        c2.increment();
        c3.increment();

        System.out.println("c1 instance count: " + c1.instanceCount);
        System.out.println("c2 instance count: " + c2.instanceCount);
        System.out.println("c3 instance count: " + c3.instanceCount);

        System.out.println("Total objects created: " + Counter.totalCount);
        System.out.println("Static fields are shared among all objects");
    }

    /**
     * Constructors demonstration
     */
    private static void demonstrateConstructors() {
        System.out.println("\n--- Constructors ---");

        // Default constructor
        EmployeeFields emp1 = new EmployeeFields();
        emp1.displayInfo();

        // Parameterized constructor
        EmployeeFields emp2 = new EmployeeFields("E001", "Alice", 50000);
        emp2.displayInfo();

        // Constructor with partial parameters
        EmployeeFields emp3 = new EmployeeFields("E002", "Bob");
        emp3.displayInfo();

        // Constructor chaining
        EmployeeFields emp4 = new EmployeeFields("E003");
        emp4.displayInfo();
    }

    /**
     * Instance methods demonstration
     */
    private static void demonstrateInstanceMethods() {
        System.out.println("\n--- Instance Methods ---");

        Calculator calc = new Calculator();

        // Instance methods operate on instance data
        calc.setNumber(10);
        System.out.println("Number: " + calc.getNumber());
        System.out.println("Square: " + calc.square());
        System.out.println("Cube: " + calc.cube());

        calc.setNumber(5);
        System.out.println("\nNumber: " + calc.getNumber());
        System.out.println("Square: " + calc.square());
        System.out.println("Cube: " + calc.cube());
    }

    /**
     * Static methods demonstration
     */
    private static void demonstrateStaticMethods() {
        System.out.println("\n--- Static Methods ---");

        // Static methods can be called without creating object
        System.out.println("Max(10, 20): " + MathUtils.max(10, 20));
        System.out.println("Min(10, 20): " + MathUtils.min(10, 20));
        System.out.println("Abs(-15): " + MathUtils.abs(-15));
        System.out.println("Square(7): " + MathUtils.square(7));

        // Can also call through object (not recommended)
        MathUtils utils = new MathUtils();
        System.out.println("Max via object: " + utils.max(30, 40));

        System.out.println("\nStatic methods don't need object instance");
    }

    /**
     * Method overloading demonstration
     */
    private static void demonstrateMethodOverloading() {
        System.out.println("\n--- Method Overloading ---");

        Printer printer = new Printer();

        // Same method name, different parameters
        printer.print(42);
        printer.print(3.14);
        printer.print("Hello");
        printer.print(true);
        printer.print(10, 20);
        printer.print("Name", "Alice");
    }

    /**
     * 'this' keyword demonstration
     */
    private static void demonstrateThisKeyword() {
        System.out.println("\n--- 'this' Keyword ---");

        Product product = new Product("Laptop", 999.99);
        product.displayInfo();

        // this is used to:
        // 1. Distinguish between instance variables and parameters
        // 2. Call other constructors
        // 3. Pass current object as parameter
        // 4. Return current object

        Product discounted = product.applyDiscount(10);
        discounted.displayInfo();

        System.out.println("\nExercise completed!");
    }
}

/**
 * BankAccount class - instance fields
 */
class BankAccount {
    // Instance fields
    private String accountNumber;
    private String ownerName;
    private double balance;

    BankAccount(String accountNumber, String ownerName) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = 0.0;
    }

    void deposit(double amount) {
        balance += amount;
    }

    void displayBalance() {
        System.out.println(ownerName + "'s balance: $" + balance);
    }
}

/**
 * Counter class - static fields
 */
class Counter {
    // Instance field
    int instanceCount = 0;

    // Static field (shared among all objects)
    static int totalCount = 0;

    Counter() {
        totalCount++; // Increment static counter
    }

    void increment() {
        instanceCount++;
        totalCount++;
    }
}

/**
 * Employee class - multiple constructors
 */
class EmployeeFields {
    private String id;
    private String name;
    private double salary;

    // Default constructor
    EmployeeFields() {
        this.id = "UNKNOWN";
        this.name = "UNKNOWN";
        this.salary = 0.0;
    }

    // Constructor with all parameters
    EmployeeFields(String id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    // Constructor with partial parameters
    EmployeeFields(String id, String name) {
        this(id, name, 0.0); // Constructor chaining
    }

    // Constructor with single parameter
    EmployeeFields(String id) {
        this(id, "UNKNOWN", 0.0); // Constructor chaining
    }

    void displayInfo() {
        System.out.println("Employee: " + id + ", " + name + ", $" + salary);
    }
}

/**
 * Calculator class - instance methods
 */
class Calculator {
    private int number;

    void setNumber(int number) {
        this.number = number;
    }

    int getNumber() {
        return number;
    }

    int square() {
        return number * number;
    }

    int cube() {
        return number * number * number;
    }
}

/**
 * MathUtils class - static methods
 */
class MathUtils {
    // Static methods
    static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    static int min(int a, int b) {
        return (a < b) ? a : b;
    }

    static int abs(int n) {
        return (n < 0) ? -n : n;
    }

    static int square(int n) {
        return n * n;
    }
}

/**
 * Printer class - method overloading
 */
class Printer {
    void print(int value) {
        System.out.println("Integer: " + value);
    }

    void print(double value) {
        System.out.println("Double: " + value);
    }

    void print(String value) {
        System.out.println("String: " + value);
    }

    void print(boolean value) {
        System.out.println("Boolean: " + value);
    }

    void print(int a, int b) {
        System.out.println("Two integers: " + a + ", " + b);
    }

    void print(String label, String value) {
        System.out.println(label + ": " + value);
    }
}

/**
 * Product class - 'this' keyword
 */
class Product {
    private String name;
    private double price;

    Product(String name, double price) {
        // 'this' distinguishes instance variables from parameters
        this.name = name;
        this.price = price;
    }

    void displayInfo() {
        System.out.println("Product: " + this.name + ", Price: $" + this.price);
    }

    // Return 'this' for method chaining
    Product applyDiscount(double percentage) {
        this.price = this.price * (1 - percentage / 100);
        return this; // Return current object
    }
}
