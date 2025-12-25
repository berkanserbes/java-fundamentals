package com.fundamentals.oop;

/**
 * Inheritance in Java
 * 
 * Inheritance: Mechanism where one class acquires properties and behaviors
 * of another class.
 * 
 * Key Concepts:
 * - Parent class (superclass, base class)
 * - Child class (subclass, derived class)
 * - 'extends' keyword
 * - 'super' keyword
 * - Method overriding
 * - Constructor chaining
 * 
 * Types:
 * - Single inheritance: Class extends one class
 * - Multilevel inheritance: Class extends class that extends another
 * - Hierarchical inheritance: Multiple classes extend same parent
 * 
 * Benefits:
 * - Code reusability
 * - Method overriding (runtime polymorphism)
 * - Hierarchical classification
 * 
 * Note: Java does NOT support multiple inheritance (class extending multiple
 * classes)
 */
public class Inheritance {

    public static void main(String[] args) {
        System.out.println("=== Inheritance ===\n");

        demonstrateSingleInheritance();
        demonstrateMethodOverriding();
        demonstrateSuperKeyword();
        demonstrateConstructorChaining();
        demonstrateMultilevelInheritance();
        demonstrateHierarchicalInheritance();
        demonstrateInstanceOf();
    }

    /**
     * Single inheritance
     */
    private static void demonstrateSingleInheritance() {
        System.out.println("--- Single Inheritance ---");

        // Parent class object
        AnimalInherit animal = new AnimalInherit("Generic Animal");
        animal.eat();
        animal.sleep();

        System.out.println();

        // Child class object
        DogInherit dog = new DogInherit("Buddy", "Golden Retriever");
        dog.eat(); // Inherited from Animal
        dog.sleep(); // Inherited from Animal
        dog.bark(); // Dog's own method

        System.out.println("\nDog inherits from Animal");
    }

    /**
     * Method overriding
     */
    private static void demonstrateMethodOverriding() {
        System.out.println("\n--- Method Overriding ---");

        VehicleInherit vehicle = new VehicleInherit("Generic Vehicle");
        vehicle.start();
        vehicle.stop();

        System.out.println();

        CarInherit car = new CarInherit("Toyota Camry", 4);
        car.start(); // Overridden method
        car.stop(); // Overridden method
        car.honk();

        System.out.println("\nChild class can override parent methods");
    }

    /**
     * super keyword
     */
    private static void demonstrateSuperKeyword() {
        System.out.println("\n--- super Keyword ---");

        EmployeeInherit emp = new EmployeeInherit("E001", "Alice", 50000);
        emp.displayInfo();

        ManagerInherit mgr = new ManagerInherit("M001", "Bob", 80000, "IT");
        mgr.displayInfo(); // Uses super to call parent method

        System.out.println("\nsuper accesses parent class members");
    }

    /**
     * Constructor chaining
     */
    private static void demonstrateConstructorChaining() {
        System.out.println("\n--- Constructor Chaining ---");

        System.out.println("Creating Student:");
        StudentInherit student = new StudentInherit("S001", "Charlie", "Computer Science");
        student.displayInfo();

        System.out.println("\nConstructors are called from parent to child");
    }

    /**
     * Multilevel inheritance
     */
    private static void demonstrateMultilevelInheritance() {
        System.out.println("\n--- Multilevel Inheritance ---");

        GraduateStudentInherit gradStudent = new GraduateStudentInherit(
                "G001", "Diana", "Physics", "Quantum Mechanics");
        gradStudent.displayInfo();
        gradStudent.research();

        System.out.println("\nGraduateStudent -> Student -> Person (multilevel)");
    }

    /**
     * Hierarchical inheritance
     */
    private static void demonstrateHierarchicalInheritance() {
        System.out.println("\n--- Hierarchical Inheritance ---");

        CircleInherit circle = new CircleInherit(5);
        System.out.println("Circle area: " + circle.getArea());
        circle.draw();

        System.out.println();

        RectangleInherit rect = new RectangleInherit(4, 6);
        System.out.println("Rectangle area: " + rect.getArea());
        rect.draw();

        System.out.println("\nCircle and Rectangle both extend Shape");
    }

    /**
     * instanceof operator
     */
    private static void demonstrateInstanceOf() {
        System.out.println("\n--- instanceof Operator ---");

        AnimalInherit animal = new AnimalInherit("Generic");
        DogInherit dog = new DogInherit("Max", "Labrador");
        AnimalInherit animalDog = new DogInherit("Rex", "Beagle"); // Polymorphism

        System.out.println("animal instanceof AnimalInherit: " + (animal instanceof AnimalInherit));
        System.out.println("animal instanceof DogInherit: " + (animal instanceof DogInherit));

        System.out.println("\ndog instanceof DogInherit: " + (dog instanceof DogInherit));
        System.out.println("dog instanceof AnimalInherit: " + (dog instanceof AnimalInherit));

        System.out.println("\nanimalDog instanceof AnimalInherit: " + (animalDog instanceof AnimalInherit));
        System.out.println("animalDog instanceof DogInherit: " + (animalDog instanceof DogInherit));

        System.out.println("\nExercise completed!");
    }
}

/**
 * Parent class - Animal
 */
class AnimalInherit {
    protected String name;

    public AnimalInherit(String name) {
        this.name = name;
        System.out.println("Animal constructor called");
    }

    public void eat() {
        System.out.println(name + " is eating");
    }

    public void sleep() {
        System.out.println(name + " is sleeping");
    }
}

/**
 * Child class - Dog extends AnimalInherit
 */
class DogInherit extends AnimalInherit {
    private String breed;

    public DogInherit(String name, String breed) {
        super(name); // Call parent constructor
        this.breed = breed;
        System.out.println("Dog constructor called");
    }

    public void bark() {
        System.out.println(name + " is barking");
    }

    // Can access protected members of parent
    public void displayBreed() {
        System.out.println(name + " is a " + breed);
    }
}

/**
 * Parent class - Vehicle
 */
class VehicleInherit {
    protected String name;

    public VehicleInherit(String name) {
        this.name = name;
    }

    public void start() {
        System.out.println(name + " is starting");
    }

    public void stop() {
        System.out.println(name + " has stopped");
    }
}

/**
 * Child class - Car extends VehicleInherit (method overriding)
 */
class CarInherit extends VehicleInherit {
    private int doors;

    public CarInherit(String name, int doors) {
        super(name);
        this.doors = doors;
    }

    // Override parent method
    @Override
    public void start() {
        System.out.println(name + " (car with " + doors + " doors) is starting with key");
    }

    @Override
    public void stop() {
        System.out.println(name + " has stopped and locked");
    }

    public void honk() {
        System.out.println(name + " is honking");
    }
}

/**
 * Parent class - Employee
 */
class EmployeeInherit {
    protected String id;
    protected String name;
    protected double salary;

    public EmployeeInherit(String id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public void displayInfo() {
        System.out.println("Employee: " + id + ", " + name + ", $" + salary);
    }
}

/**
 * Child class - Manager extends EmployeeInherit
 */
class ManagerInherit extends EmployeeInherit {
    private String department;

    public ManagerInherit(String id, String name, double salary, String department) {
        super(id, name, salary); // Call parent constructor
        this.department = department;
    }

    @Override
    public void displayInfo() {
        super.displayInfo(); // Call parent method
        System.out.println("Department: " + department);
    }
}

/**
 * Base class - Person
 */
class PersonInherit {
    protected String id;
    protected String name;

    public PersonInherit(String id, String name) {
        this.id = id;
        this.name = name;
        System.out.println("PersonInherit constructor");
    }

    public void displayInfo() {
        System.out.println("ID: " + id + ", Name: " + name);
    }
}

/**
 * Level 1 - Student extends PersonInherit
 */
class StudentInherit extends PersonInherit {
    protected String major;

    public StudentInherit(String id, String name, String major) {
        super(id, name); // Call PersonInherit constructor
        this.major = major;
        System.out.println("StudentInherit constructor");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Major: " + major);
    }
}

/**
 * Level 2 - GraduateStudent extends Student (multilevel)
 */
class GraduateStudentInherit extends StudentInherit {
    private String researchTopic;

    public GraduateStudentInherit(String id, String name, String major, String researchTopic) {
        super(id, name, major); // Call StudentInherit constructor
        this.researchTopic = researchTopic;
        System.out.println("GraduateStudentInherit constructor");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Research: " + researchTopic);
    }

    public void research() {
        System.out.println(name + " is researching " + researchTopic);
    }
}

/**
 * Parent class - Shape (hierarchical inheritance)
 */
class ShapeInherit {
    protected String color;

    public ShapeInherit() {
        this.color = "Black";
    }

    public double getArea() {
        return 0.0;
    }

    public void draw() {
        System.out.println("Drawing a shape");
    }
}

/**
 * Child 1 - Circle extends ShapeInherit
 */
class CircleInherit extends ShapeInherit {
    private double radius;

    public CircleInherit(double radius) {
        super();
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a circle with radius " + radius);
    }
}

/**
 * Child 2 - Rectangle extends ShapeInherit
 */
class RectangleInherit extends ShapeInherit {
    private double width;
    private double height;

    public RectangleInherit(double width, double height) {
        super();
        this.width = width;
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a rectangle " + width + " x " + height);
    }
}
