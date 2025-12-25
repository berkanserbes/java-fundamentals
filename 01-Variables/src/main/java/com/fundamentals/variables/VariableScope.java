package com.fundamentals.variables;

/**
 * Variable Scope
 * 
 * This exercise demonstrates:
 * - Local variable scope
 * - Instance variable scope
 * - Static (class) variable scope
 * - Variable shadowing
 */
public class VariableScope {

    // Instance variable - belongs to each object
    // Accessible to all instance methods
    private int instanceCounter = 0;

    // Static (class) variable - shared across all objects
    // Accessible to all methods (static and instance)
    private static int staticCounter = 0;

    public static void main(String[] args) {
        System.out.println("=== Variable Scope ===\n");

        // Local variable - only exists within this method
        int localVariable = 10;
        System.out.println("Local variable in main: " + localVariable);

        // Static variable can be accessed in static methods
        staticCounter = 100;
        System.out.println("Static counter: " + staticCounter);

        // Create objects to demonstrate instance variables
        VariableScope obj1 = new VariableScope();
        VariableScope obj2 = new VariableScope();

        // Each object has its own instance variable
        obj1.incrementInstanceCounter();
        obj1.incrementInstanceCounter();
        obj2.incrementInstanceCounter();

        System.out.println("\nInstance variables (separate for each object):");
        System.out.println("  obj1 instance counter: " + obj1.instanceCounter);
        System.out.println("  obj2 instance counter: " + obj2.instanceCounter);

        // Static variable is shared across all objects
        System.out.println("\nStatic variable (shared across all objects):");
        System.out.println("  Static counter: " + staticCounter);

        // Demonstrate block scope
        demonstrateBlockScope();

        // Demonstrate variable shadowing
        obj1.demonstrateShadowing();

        System.out.println("\nExercise completed!");
    }

    /**
     * Instance method - can access both instance and static variables
     */
    public void incrementInstanceCounter() {
        instanceCounter++; // Access instance variable
        staticCounter++; // Access static variable
    }

    /**
     * Demonstrates block scope with if and for blocks
     */
    public static void demonstrateBlockScope() {
        System.out.println("\n--- Block Scope Demo ---");

        int outerVariable = 5;

        if (true) {
            // Variable declared in if block - only exists here
            int blockVariable = 10;
            System.out.println("Inside if block:");
            System.out.println("  outerVariable: " + outerVariable);
            System.out.println("  blockVariable: " + blockVariable);
        }

        // blockVariable is not accessible here
        // System.out.println(blockVariable); // Compilation error!

        // Loop variable scope
        for (int i = 0; i < 3; i++) {
            // 'i' only exists within this loop
            System.out.println("Loop iteration: " + i);
        }

        // 'i' is not accessible here
        // System.out.println(i); // Compilation error!
    }

    /**
     * Demonstrates variable shadowing
     */
    public void demonstrateShadowing() {
        System.out.println("\n--- Variable Shadowing Demo ---");

        // Local variable with same name as instance variable
        int instanceCounter = 999; // This "shadows" the instance variable

        System.out.println("Local instanceCounter: " + instanceCounter);
        System.out.println("Instance instanceCounter (using 'this'): " + this.instanceCounter);

        // Without 'this', the local variable takes precedence
    }
}
