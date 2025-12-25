package com.fundamentals.oop;

/**
 * Classes and Objects in Java
 * 
 * Class: Blueprint or template for creating objects
 * Object: Instance of a class
 * 
 * Key Concepts:
 * - Class defines structure (fields) and behavior (methods)
 * - Objects are created using 'new' keyword
 * - Each object has its own copy of instance variables
 * - Objects are stored in heap memory
 * - Reference variables point to objects
 * 
 * Class Components:
 * - Fields (variables)
 * - Methods (functions)
 * - Constructors
 * - Blocks (static/instance)
 * - Nested classes
 */
public class ClassesAndObjects {

    public static void main(String[] args) {
        System.out.println("=== Classes and Objects ===\n");

        demonstrateBasicClass();
        demonstrateObjectCreation();
        demonstrateMultipleObjects();
        demonstrateReferenceTypes();
        demonstrateNullReferences();
        demonstrateObjectComparison();
    }

    /**
     * Basic class demonstration
     */
    private static void demonstrateBasicClass() {
        System.out.println("--- Basic Class ---");

        // Create an object
        CarDemo car1 = new CarDemo();

        // Access fields (if public)
        car1.brand = "Toyota";
        car1.model = "Camry";
        car1.year = 2023;

        // Call methods
        car1.displayInfo();
        car1.start();
        car1.stop();
    }

    /**
     * Object creation with constructors
     */
    private static void demonstrateObjectCreation() {
        System.out.println("\n--- Object Creation ---");

        // Using default constructor
        Book book1 = new Book();
        book1.displayInfo();

        // Using parameterized constructor
        Book book2 = new Book("1984", "George Orwell", 328);
        book2.displayInfo();

        // Using another constructor
        Book book3 = new Book("Animal Farm", "George Orwell");
        book3.displayInfo();
    }

    /**
     * Multiple objects demonstration
     */
    private static void demonstrateMultipleObjects() {
        System.out.println("\n--- Multiple Objects ---");

        // Each object has its own state
        StudentDemo student1 = new StudentDemo("Alice", 20, "CS");
        StudentDemo student2 = new StudentDemo("Bob", 22, "Math");
        StudentDemo student3 = new StudentDemo("Charlie", 21, "Physics");

        System.out.println("Student 1:");
        student1.displayInfo();

        System.out.println("\nStudent 2:");
        student2.displayInfo();

        System.out.println("\nStudent 3:");
        student3.displayInfo();

        // Modifying one object doesn't affect others
        student1.age = 21;
        System.out.println("\nAfter modifying student1:");
        System.out.println("Student 1 age: " + student1.age);
        System.out.println("Student 2 age: " + student2.age);
    }

    /**
     * Reference types demonstration
     */
    private static void demonstrateReferenceTypes() {
        System.out.println("\n--- Reference Types ---");

        // Objects are reference types
        Point p1 = new Point(10, 20);
        Point p2 = p1; // p2 references same object as p1

        System.out.println("Before modification:");
        System.out.println("p1: " + p1);
        System.out.println("p2: " + p2);

        // Modifying through p2 affects p1 (same object)
        p2.x = 100;
        p2.y = 200;

        System.out.println("\nAfter modifying p2:");
        System.out.println("p1: " + p1);
        System.out.println("p2: " + p2);
        System.out.println("Both changed (same object)");

        // Creating a new object
        Point p3 = new Point(30, 40);
        System.out.println("\np3 (different object): " + p3);
    }

    /**
     * Null references
     */
    private static void demonstrateNullReferences() {
        System.out.println("\n--- Null References ---");

        // Null reference
        RectangleDemo rect = null;
        System.out.println("rect is null: " + (rect == null));

        // Attempting to use null reference causes NullPointerException
        try {
            rect.displayInfo();
        } catch (NullPointerException e) {
            System.out.println("NullPointerException caught!");
        }

        // Proper null check
        if (rect != null) {
            rect.displayInfo();
        } else {
            System.out.println("Rectangle is null, creating new one");
            rect = new RectangleDemo(10, 20);
            rect.displayInfo();
        }
    }

    /**
     * Object comparison
     */
    private static void demonstrateObjectComparison() {
        System.out.println("\n--- Object Comparison ---");

        PersonDemo person1 = new PersonDemo("Alice", 25);
        PersonDemo person2 = new PersonDemo("Alice", 25);
        PersonDemo person3 = person1;

        // == compares references
        System.out.println("person1 == person2: " + (person1 == person2)); // false
        System.out.println("person1 == person3: " + (person1 == person3)); // true

        // equals() compares content (if overridden)
        System.out.println("person1.equals(person2): " + person1.equals(person2));

        System.out.println("\nExercise completed!");
    }
}

/**
 * Simple Car class
 */
class CarDemo {
    // Fields (instance variables)
    String brand;
    String model;
    int year;

    // Methods
    void displayInfo() {
        System.out.println("Car: " + year + " " + brand + " " + model);
    }

    void start() {
        System.out.println(brand + " " + model + " is starting...");
    }

    void stop() {
        System.out.println(brand + " " + model + " has stopped.");
    }
}

/**
 * Book class with multiple constructors
 */
class Book {
    String title;
    String author;
    int pages;

    // Default constructor
    Book() {
        this.title = "Unknown";
        this.author = "Unknown";
        this.pages = 0;
    }

    // Parameterized constructor
    Book(String title, String author, int pages) {
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    // Constructor with partial parameters
    Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.pages = 0;
    }

    void displayInfo() {
        System.out.println("Book: " + title + " by " + author + " (" + pages + " pages)");
    }
}

/**
 * Student class
 */
class StudentDemo {
    String name;
    int age;
    String major;

    StudentDemo(String name, int age, String major) {
        this.name = name;
        this.age = age;
        this.major = major;
    }

    void displayInfo() {
        System.out.println("Name: " + name + ", Age: " + age + ", Major: " + major);
    }
}

/**
 * Point class
 */
class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point(" + x + ", " + y + ")";
    }
}

/**
 * Rectangle class
 */
class RectangleDemo {
    int width;
    int height;

    RectangleDemo(int width, int height) {
        this.width = width;
        this.height = height;
    }

    void displayInfo() {
        System.out.println("Rectangle: " + width + " x " + height);
    }
}

/**
 * Person class for comparison
 */
class PersonDemo {
    String name;
    int age;

    PersonDemo(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        PersonDemo person = (PersonDemo) obj;
        return age == person.age && name.equals(person.name);
    }
}
