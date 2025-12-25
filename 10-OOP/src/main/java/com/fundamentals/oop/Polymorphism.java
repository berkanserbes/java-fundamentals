package com.fundamentals.oop;

/**
 * Polymorphism in Java
 * 
 * Polymorphism: Ability of an object to take many forms.
 * "One interface, multiple implementations"
 * 
 * Types:
 * 1. Compile-time Polymorphism (Static):
 * - Method Overloading
 * - Operator Overloading (not in Java)
 * 
 * 2. Runtime Polymorphism (Dynamic):
 * - Method Overriding
 * - Achieved through inheritance and interfaces
 * 
 * Key Concepts:
 * - Upcasting: Child object to parent reference
 * - Downcasting: Parent reference to child type (explicit)
 * - Dynamic method dispatch: JVM decides which method to call at runtime
 * 
 * Benefits:
 * - Code flexibility and reusability
 * - Loose coupling
 * - Easy maintenance
 * - Extensibility
 */
public class Polymorphism {

    public static void main(String[] args) {
        System.out.println("=== Polymorphism ===\n");

        demonstrateCompileTimePolymorphism();
        demonstrateRuntimePolymorphism();
        demonstrateUpcasting();
        demonstrateDowncasting();
        demonstrateDynamicMethodDispatch();
        demonstratePolymorphismWithInterfaces();
        demonstrateRealWorldExample();
    }

    /**
     * Compile-time polymorphism (Method Overloading)
     */
    private static void demonstrateCompileTimePolymorphism() {
        System.out.println("--- Compile-Time Polymorphism (Overloading) ---");

        CalculatorPoly calc = new CalculatorPoly();

        System.out.println("add(5, 3): " + calc.add(5, 3));
        System.out.println("add(5, 3, 2): " + calc.add(5, 3, 2));
        System.out.println("add(2.5, 3.7): " + calc.add(2.5, 3.7));
        System.out.println("add(\"Hello\", \"World\"): " + calc.add("Hello", "World"));

        System.out.println("\nMethod overloading: Same name, different parameters");
    }

    /**
     * Runtime polymorphism (Method Overriding)
     */
    private static void demonstrateRuntimePolymorphism() {
        System.out.println("\n--- Runtime Polymorphism (Overriding) ---");

        AnimalPoly animal = new AnimalPoly();
        animal.makeSound();

        AnimalPoly dog = new DogPoly(); // Upcasting
        dog.makeSound(); // Calls Dog's overridden method

        AnimalPoly cat = new CatPoly(); // Upcasting
        cat.makeSound(); // Calls Cat's overridden method

        System.out.println("\nMethod overriding: Same signature, different implementation");
    }

    /**
     * Upcasting demonstration
     */
    private static void demonstrateUpcasting() {
        System.out.println("\n--- Upcasting ---");

        // Upcasting: Child to Parent (implicit)
        ShapePoly shape1 = new CirclePoly(5);
        ShapePoly shape2 = new RectanglePoly(4, 6);
        ShapePoly shape3 = new TrianglePoly(3, 4);

        // Can call overridden methods
        System.out.println("Circle area: " + shape1.getArea());
        System.out.println("Rectangle area: " + shape2.getArea());
        System.out.println("Triangle area: " + shape3.getArea());

        // Cannot call child-specific methods
        // shape1.getRadius(); // ERROR: ShapePoly doesn't have getRadius()

        System.out.println("\nUpcasting is automatic (child to parent)");
    }

    /**
     * Downcasting demonstration
     */
    private static void demonstrateDowncasting() {
        System.out.println("\n--- Downcasting ---");

        ShapePoly shape = new CirclePoly(7);

        // Downcasting: Parent to Child (explicit)
        if (shape instanceof CirclePoly) {
            CirclePoly circle = (CirclePoly) shape;
            System.out.println("Circle radius: " + circle.getRadius());
            System.out.println("Circle area: " + circle.getArea());
        }

        // Unsafe downcasting
        ShapePoly shape2 = new RectanglePoly(5, 10);
        try {
            CirclePoly circle2 = (CirclePoly) shape2; // ClassCastException
        } catch (ClassCastException e) {
            System.out.println("Cannot cast Rectangle to Circle!");
        }

        System.out.println("\nDowncasting requires explicit cast and instanceof check");
    }

    /**
     * Dynamic method dispatch
     */
    private static void demonstrateDynamicMethodDispatch() {
        System.out.println("\n--- Dynamic Method Dispatch ---");

        // Array of parent type holding different child objects
        AnimalPoly[] animals = {
                new DogPoly(),
                new CatPoly(),
                new DogPoly(),
                new CatPoly()
        };

        // JVM decides which method to call at runtime
        for (AnimalPoly animal : animals) {
            animal.makeSound(); // Different implementation for each
        }

        System.out.println("\nJVM determines method at runtime (dynamic dispatch)");
    }

    /**
     * Polymorphism with interfaces
     */
    private static void demonstratePolymorphismWithInterfaces() {
        System.out.println("\n--- Polymorphism with Interfaces ---");

        PaymentMethod[] payments = {
                new CreditCard("1234-5678-9012-3456"),
                new PayPal("user@example.com"),
                new BankTransfer("ACC123456")
        };

        for (PaymentMethod payment : payments) {
            payment.processPayment(100.0);
        }

        System.out.println("\nInterfaces enable polymorphism across unrelated classes");
    }

    /**
     * Real-world example
     */
    private static void demonstrateRealWorldExample() {
        System.out.println("\n--- Real-World Example: Employee System ---");

        EmployeePoly[] employees = {
                new FullTimeEmployee("Alice", 5000),
                new PartTimeEmployee("Bob", 20, 80),
                new Contractor("Charlie", 50, 160)
        };

        System.out.println("Monthly Salaries:");
        for (EmployeePoly emp : employees) {
            System.out.println(emp.getName() + ": $" + emp.calculateSalary());
        }

        System.out.println("\nExercise completed!");
    }
}

/**
 * Calculator for method overloading
 */
class CalculatorPoly {
    // Overloaded methods
    public int add(int a, int b) {
        return a + b;
    }

    public int add(int a, int b, int c) {
        return a + b + c;
    }

    public double add(double a, double b) {
        return a + b;
    }

    public String add(String a, String b) {
        return a + " " + b;
    }
}

/**
 * Animal for method overriding
 */
class AnimalPoly {
    public void makeSound() {
        System.out.println("Animal makes a sound");
    }
}

/**
 * Dog overrides makeSound
 */
class DogPoly extends AnimalPoly {
    @Override
    public void makeSound() {
        System.out.println("Dog says: Woof!");
    }
}

/**
 * Cat overrides makeSound
 */
class CatPoly extends AnimalPoly {
    @Override
    public void makeSound() {
        System.out.println("Cat says: Meow!");
    }
}

/**
 * Shape for upcasting/downcasting
 */
class ShapePoly {
    public double getArea() {
        return 0.0;
    }
}

/**
 * Circle extends Shape
 */
class CirclePoly extends ShapePoly {
    private double radius;

    public CirclePoly(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
}

/**
 * Rectangle extends Shape
 */
class RectanglePoly extends ShapePoly {
    private double width;
    private double height;

    public RectanglePoly(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height;
    }
}

/**
 * Triangle extends Shape
 */
class TrianglePoly extends ShapePoly {
    private double base;
    private double height;

    public TrianglePoly(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public double getArea() {
        return 0.5 * base * height;
    }
}

/**
 * PaymentMethod interface
 */
interface PaymentMethod {
    void processPayment(double amount);
}

/**
 * CreditCard implementation
 */
class CreditCard implements PaymentMethod {
    private String cardNumber;

    public CreditCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment: $" + amount);
    }
}

/**
 * PayPal implementation
 */
class PayPal implements PaymentMethod {
    private String email;

    public PayPal(String email) {
        this.email = email;
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment: $" + amount);
    }
}

/**
 * BankTransfer implementation
 */
class BankTransfer implements PaymentMethod {
    private String accountNumber;

    public BankTransfer(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing bank transfer: $" + amount);
    }
}

/**
 * Employee abstract class
 */
abstract class EmployeePoly {
    protected String name;

    public EmployeePoly(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double calculateSalary();
}

/**
 * FullTimeEmployee
 */
class FullTimeEmployee extends EmployeePoly {
    private double monthlySalary;

    public FullTimeEmployee(String name, double monthlySalary) {
        super(name);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary;
    }
}

/**
 * PartTimeEmployee
 */
class PartTimeEmployee extends EmployeePoly {
    private double hourlyRate;
    private int hoursWorked;

    public PartTimeEmployee(String name, double hourlyRate, int hoursWorked) {
        super(name);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateSalary() {
        return hourlyRate * hoursWorked;
    }
}

/**
 * Contractor
 */
class Contractor extends EmployeePoly {
    private double hourlyRate;
    private int hoursWorked;

    public Contractor(String name, double hourlyRate, int hoursWorked) {
        super(name);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateSalary() {
        return hourlyRate * hoursWorked;
    }
}
