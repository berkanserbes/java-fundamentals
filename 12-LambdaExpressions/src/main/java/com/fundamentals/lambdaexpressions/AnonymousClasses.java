package com.fundamentals.lambdaexpressions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Anonymous Classes in Java
 * 
 * ANONYMOUS CLASS: A class without a name, declared and instantiated
 * at the same time. It's used for one-time use implementations.
 * 
 * Key Characteristics:
 * - No explicit class name
 * - Declared and instantiated in a single expression
 * - Can extend a class OR implement an interface (not both)
 * - Can access final/effectively final variables from enclosing scope
 * - Cannot have explicit constructors (uses parent constructor)
 * - Can have instance variables and methods
 * 
 * Common Use Cases:
 * - Event handling (GUI applications)
 * - Implementing single-method interfaces
 * - Providing quick implementations of abstract classes
 * - Callbacks and listeners
 * 
 * Anonymous Class vs Lambda:
 * ┌────────────────────────────────────────────────────────────────────┐
 * │ Anonymous Class │ Lambda Expression │
 * ├────────────────────────────────┼───────────────────────────────────┤
 * │ Can implement any interface │ Only functional interfaces │
 * │ Can extend abstract classes │ Cannot extend classes │
 * │ Can have multiple methods │ Single abstract method only │
 * │ Has its own 'this' reference │ 'this' refers to enclosing class │
 * │ Can have instance variables │ Cannot have instance variables │
 * │ More verbose │ More concise │
 * └────────────────────────────────┴───────────────────────────────────┘
 */
public class AnonymousClasses {

    private String instanceField = "Enclosing class field";

    public static void main(String[] args) {
        System.out.println("=== Anonymous Classes in Java ===\n");

        AnonymousClasses demo = new AnonymousClasses();

        demo.demonstrateBasicAnonymousClass();
        demo.demonstrateAnonymousWithInterface();
        demo.demonstrateAnonymousWithAbstractClass();
        demo.demonstrateAnonymousWithConstructorArgs();
        demo.demonstrateVariableAccess();
        demo.demonstrateThisReference();
        demo.demonstrateComparatorExample();
        demo.demonstrateEventHandlerPattern();
        demo.demonstrateMultipleMethods();
        demo.demonstrateAnonymousVsNamed();
    }

    // ========================================================================
    // BASIC ANONYMOUS CLASS
    // ========================================================================

    /**
     * Basic anonymous class implementing an interface
     */
    private void demonstrateBasicAnonymousClass() {
        System.out.println("--- 1. Basic Anonymous Class ---\n");

        // Normal class implementation
        System.out.println("Traditional way (named class):");
        Greeter normalGreeter = new EnglishGreeter();
        normalGreeter.greet("Alice");

        // Anonymous class implementation
        System.out.println("\nAnonymous class way:");
        Greeter anonymousGreeter = new Greeter() {
            @Override
            public void greet(String name) {
                System.out.println("Bonjour, " + name + "! (French Anonymous)");
            }
        };
        anonymousGreeter.greet("Alice");

        System.out.println("\nSyntax breakdown:");
        System.out.println("  Greeter anonymousGreeter = new Greeter() { ... };");
        System.out.println("  ↓");
        System.out.println("  new Greeter() -> Creates instance of anonymous class");
        System.out.println("  { ... }       -> Class body with method implementations");
        System.out.println();
    }

    // ========================================================================
    // ANONYMOUS CLASS WITH INTERFACE
    // ========================================================================

    /**
     * Anonymous class implementing a custom interface
     */
    private void demonstrateAnonymousWithInterface() {
        System.out.println("--- 2. Anonymous Class with Interface ---\n");

        // Multiple anonymous implementations of same interface
        MathOperation addition = new MathOperation() {
            @Override
            public double execute(double a, double b) {
                return a + b;
            }

            @Override
            public String getSymbol() {
                return "+";
            }
        };

        MathOperation subtraction = new MathOperation() {
            @Override
            public double execute(double a, double b) {
                return a - b;
            }

            @Override
            public String getSymbol() {
                return "-";
            }
        };

        MathOperation multiplication = new MathOperation() {
            @Override
            public double execute(double a, double b) {
                return a * b;
            }

            @Override
            public String getSymbol() {
                return "*";
            }
        };

        // Using the anonymous classes
        double x = 10, y = 5;
        System.out.println("Operations with x=" + x + ", y=" + y + ":");
        performOperation(addition, x, y);
        performOperation(subtraction, x, y);
        performOperation(multiplication, x, y);
        System.out.println();
    }

    private void performOperation(MathOperation op, double a, double b) {
        System.out.println(a + " " + op.getSymbol() + " " + b + " = " + op.execute(a, b));
    }

    // ========================================================================
    // ANONYMOUS CLASS WITH ABSTRACT CLASS
    // ========================================================================

    /**
     * Anonymous class extending an abstract class
     */
    private void demonstrateAnonymousWithAbstractClass() {
        System.out.println("--- 3. Anonymous Class with Abstract Class ---\n");

        // Anonymous class extending abstract class
        Vehicle electricCar = new Vehicle("Tesla Model 3") {
            @Override
            public void startEngine() {
                System.out.println(getName() + ": Electric motor silently starting...");
            }

            @Override
            public void stopEngine() {
                System.out.println(getName() + ": Electric motor stopped.");
            }

            // We can add new methods in anonymous class
            public void chargeBattery() {
                System.out.println(getName() + ": Charging battery...");
            }
        };

        Vehicle dieselTruck = new Vehicle("Ford F-150") {
            @Override
            public void startEngine() {
                System.out.println(getName() + ": Diesel engine roaring to life!");
            }

            @Override
            public void stopEngine() {
                System.out.println(getName() + ": Diesel engine shut down.");
            }
        };

        // Using abstract methods
        electricCar.startEngine();
        electricCar.honk(); // Concrete method from abstract class
        electricCar.stopEngine();

        System.out.println();

        dieselTruck.startEngine();
        dieselTruck.honk();
        dieselTruck.stopEngine();

        System.out.println();
    }

    // ========================================================================
    // ANONYMOUS CLASS WITH CONSTRUCTOR ARGUMENTS
    // ========================================================================

    /**
     * Anonymous class passing arguments to parent constructor
     */
    private void demonstrateAnonymousWithConstructorArgs() {
        System.out.println("--- 4. Anonymous Class with Constructor Arguments ---\n");

        // Anonymous classes can't have explicit constructors
        // But they can pass arguments to parent class constructor

        Person student = new Person("John", 20) {
            private String studentId = "STU-001"; // Instance variable in anonymous class

            @Override
            public void introduce() {
                System.out.println("I'm " + getName() + ", a " + getAge() +
                        "-year-old student. ID: " + studentId);
            }
        };

        Person teacher = new Person("Dr. Smith", 45) {
            private String department = "Computer Science";

            @Override
            public void introduce() {
                System.out.println("I'm " + getName() + ", a professor in " +
                        department + " department.");
            }
        };

        student.introduce();
        teacher.introduce();

        System.out.println("\nNote: Anonymous classes inherit parent constructor");
        System.out.println("but cannot define their own explicit constructors.");
        System.out.println();
    }

    // ========================================================================
    // VARIABLE ACCESS IN ANONYMOUS CLASSES
    // ========================================================================

    /**
     * Demonstrates variable access rules for anonymous classes
     */
    private void demonstrateVariableAccess() {
        System.out.println("--- 5. Variable Access in Anonymous Classes ---\n");

        final String finalVar = "I'm final";
        String effectivelyFinal = "I'm effectively final"; // Never modified
        int counter = 100; // This would cause error if modified

        Runnable accessDemo = new Runnable() {
            private int localVar = 50; // Anonymous class's own variable

            @Override
            public void run() {
                // Can access enclosing class's instance field
                System.out.println("Instance field: " + instanceField);

                // Can access final local variables
                System.out.println("Final variable: " + finalVar);

                // Can access effectively final variables
                System.out.println("Effectively final: " + effectivelyFinal);

                // Can access counter (effectively final - not modified)
                System.out.println("Counter: " + counter);

                // Can access own variables
                System.out.println("Local var: " + localVar);
                localVar++; // Can modify own variables
                System.out.println("Local var after increment: " + localVar);
            }
        };

        accessDemo.run();

        // Uncommenting this would make counter not effectively final:
        // counter = 200; // ERROR: Variable used in anonymous class must be final

        System.out.println("\nRules:");
        System.out.println("1. Can access instance fields of enclosing class");
        System.out.println("2. Can access final local variables");
        System.out.println("3. Can access effectively final variables");
        System.out.println("4. Can modify its own instance variables");
        System.out.println();
    }

    // ========================================================================
    // 'THIS' REFERENCE IN ANONYMOUS CLASSES
    // ========================================================================

    /**
     * Demonstrates 'this' keyword behavior in anonymous classes
     */
    private void demonstrateThisReference() {
        System.out.println("--- 6. 'this' Reference in Anonymous Classes ---\n");

        System.out.println("Enclosing class: " + this.getClass().getSimpleName());

        ThisDemo demo = new ThisDemo() {
            @Override
            public void showThis() {
                // 'this' refers to the anonymous class instance
                System.out.println("Anonymous 'this': " + this.getClass().getName());

                // To access enclosing class, use EnclosingClass.this
                System.out.println("Enclosing 'this': " +
                        AnonymousClasses.this.getClass().getSimpleName());

                // Accessing enclosing class field
                System.out.println("Enclosing field: " +
                        AnonymousClasses.this.instanceField);
            }
        };

        demo.showThis();

        System.out.println("\nKey difference from Lambda:");
        System.out.println("- Anonymous class: 'this' = anonymous class instance");
        System.out.println("- Lambda: 'this' = enclosing class instance");
        System.out.println();
    }

    // ========================================================================
    // COMPARATOR EXAMPLE
    // ========================================================================

    /**
     * Real-world example: Custom sorting with Comparator
     */
    private void demonstrateComparatorExample() {
        System.out.println("--- 7. Comparator with Anonymous Class ---\n");

        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", 999.99, 50));
        products.add(new Product("Mouse", 29.99, 200));
        products.add(new Product("Keyboard", 79.99, 150));
        products.add(new Product("Monitor", 299.99, 75));

        System.out.println("Original list:");
        printProducts(products);

        // Sort by price using anonymous Comparator
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Double.compare(p1.getPrice(), p2.getPrice());
            }
        });

        System.out.println("\nSorted by price (ascending):");
        printProducts(products);

        // Sort by name using anonymous Comparator
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return p1.getName().compareTo(p2.getName());
            }
        });

        System.out.println("\nSorted by name (alphabetically):");
        printProducts(products);

        // Sort by stock (descending) using anonymous Comparator
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Integer.compare(p2.getStock(), p1.getStock()); // Reversed
            }
        });

        System.out.println("\nSorted by stock (descending):");
        printProducts(products);
        System.out.println();
    }

    private void printProducts(List<Product> products) {
        for (Product p : products) {
            System.out.printf("  %-10s | $%7.2f | Stock: %d%n",
                    p.getName(), p.getPrice(), p.getStock());
        }
    }

    // ========================================================================
    // EVENT HANDLER PATTERN
    // ========================================================================

    /**
     * Demonstrates event handler pattern (common in GUI programming)
     */
    private void demonstrateEventHandlerPattern() {
        System.out.println("--- 8. Event Handler Pattern ---\n");

        Button saveButton = new Button("Save");
        Button deleteButton = new Button("Delete");
        Button cancelButton = new Button("Cancel");

        // Anonymous class event handlers
        saveButton.setClickHandler(new ClickHandler() {
            @Override
            public void onClick(String buttonName) {
                System.out.println("📁 " + buttonName + " clicked: Saving data...");
            }
        });

        deleteButton.setClickHandler(new ClickHandler() {
            @Override
            public void onClick(String buttonName) {
                System.out.println("🗑️ " + buttonName + " clicked: Deleting item...");
            }
        });

        cancelButton.setClickHandler(new ClickHandler() {
            @Override
            public void onClick(String buttonName) {
                System.out.println("❌ " + buttonName + " clicked: Operation cancelled.");
            }
        });

        // Simulate button clicks
        saveButton.click();
        deleteButton.click();
        cancelButton.click();

        System.out.println("\nThis pattern is common in:");
        System.out.println("- Swing/AWT GUI applications");
        System.out.println("- Android development (pre-Lambda)");
        System.out.println("- Event-driven programming");
        System.out.println();
    }

    // ========================================================================
    // MULTIPLE METHODS IN ANONYMOUS CLASS
    // ========================================================================

    /**
     * Anonymous class implementing interface with multiple methods
     */
    private void demonstrateMultipleMethods() {
        System.out.println("--- 9. Multiple Methods (Can't use Lambda!) ---\n");

        // This cannot be done with Lambda - multiple abstract methods
        DataProcessor processor = new DataProcessor() {
            private int processedCount = 0;

            @Override
            public String process(String data) {
                processedCount++;
                return data.toUpperCase();
            }

            @Override
            public boolean validate(String data) {
                return data != null && !data.isEmpty();
            }

            @Override
            public int getProcessedCount() {
                return processedCount;
            }
        };

        String[] testData = { "hello", "world", "java", null, "" };

        System.out.println("Processing data:");
        for (String data : testData) {
            if (processor.validate(data)) {
                System.out.println("  Valid: '" + data + "' -> '" + processor.process(data) + "'");
            } else {
                System.out.println("  Invalid: '" + data + "' (skipped)");
            }
        }

        System.out.println("Total processed: " + processor.getProcessedCount());

        System.out.println("\nWhy Lambda can't do this:");
        System.out.println("- Lambda requires @FunctionalInterface (single abstract method)");
        System.out.println("- DataProcessor has 3 abstract methods");
        System.out.println("- Anonymous class is the only option here");
        System.out.println();
    }

    // ========================================================================
    // ANONYMOUS VS NAMED CLASS COMPARISON
    // ========================================================================

    /**
     * Compares anonymous class with named class approach
     */
    private void demonstrateAnonymousVsNamed() {
        System.out.println("--- 10. Anonymous Class vs Named Class ---\n");

        System.out.println("SCENARIO: Creating a custom thread\n");

        // Named class approach
        System.out.println("1. Named class approach:");
        Thread namedThread = new Thread(new CustomTask("Named-Task"));
        namedThread.start();

        // Wait briefly for thread to complete
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }

        // Anonymous class approach
        System.out.println("\n2. Anonymous class approach:");
        Thread anonymousThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("   Anonymous-Task: Executing in " +
                        Thread.currentThread().getName());
            }
        });
        anonymousThread.start();

        // Wait briefly for thread to complete
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }

        System.out.println("\n" + "=".repeat(55));
        System.out.println("WHEN TO USE ANONYMOUS CLASSES:");
        System.out.println("=".repeat(55));
        System.out.println("✓ One-time use implementations");
        System.out.println("✓ Need to extend abstract class");
        System.out.println("✓ Interface with multiple methods");
        System.out.println("✓ Need 'this' to refer to the implementation");
        System.out.println("✓ Need instance variables");
        System.out.println();
        System.out.println("WHEN TO USE LAMBDA (instead):");
        System.out.println("=".repeat(55));
        System.out.println("✓ Functional interface (single abstract method)");
        System.out.println("✓ Want more concise code");
        System.out.println("✓ Don't need 'this' reference to implementation");
        System.out.println("=".repeat(55));
        System.out.println("\nAnonymous Classes demonstration completed!");
    }
}

// ============================================================================
// SUPPORTING INTERFACES AND CLASSES
// ============================================================================

/**
 * Simple greeter interface
 */
interface Greeter {
    void greet(String name);
}

/**
 * Named implementation of Greeter (for comparison)
 */
class EnglishGreeter implements Greeter {
    @Override
    public void greet(String name) {
        System.out.println("Hello, " + name + "! (English Named Class)");
    }
}

/**
 * Math operation interface with multiple methods
 */
interface MathOperation {
    double execute(double a, double b);

    String getSymbol();
}

/**
 * Abstract Vehicle class
 */
abstract class Vehicle {
    private String name;

    Vehicle(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    // Abstract methods - must be implemented
    abstract void startEngine();

    abstract void stopEngine();

    // Concrete method - inherited by anonymous class
    void honk() {
        System.out.println(name + ": Beep beep!");
    }
}

/**
 * Abstract Person class with constructor parameters
 */
abstract class Person {
    private String name;
    private int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    String getName() {
        return name;
    }

    int getAge() {
        return age;
    }

    abstract void introduce();
}

/**
 * Interface to demonstrate 'this' reference
 */
interface ThisDemo {
    void showThis();
}

/**
 * Product class for Comparator example
 */
class Product {
    private String name;
    private double price;
    private int stock;

    Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    String getName() {
        return name;
    }

    double getPrice() {
        return price;
    }

    int getStock() {
        return stock;
    }
}

/**
 * Click handler interface (like ActionListener)
 */
interface ClickHandler {
    void onClick(String buttonName);
}

/**
 * Simple Button class for event handling demo
 */
class Button {
    private String name;
    private ClickHandler handler;

    Button(String name) {
        this.name = name;
    }

    void setClickHandler(ClickHandler handler) {
        this.handler = handler;
    }

    void click() {
        if (handler != null) {
            handler.onClick(name);
        }
    }
}

/**
 * Interface with multiple methods (cannot be Lambda)
 */
interface DataProcessor {
    String process(String data);

    boolean validate(String data);

    int getProcessedCount();
}

/**
 * Named Runnable implementation for comparison
 */
class CustomTask implements Runnable {
    private String taskName;

    CustomTask(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println("   " + taskName + ": Executing in " +
                Thread.currentThread().getName());
    }
}
