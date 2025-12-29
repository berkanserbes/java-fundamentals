package com.fundamentals.lambdaexpressions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public class LambdaDemo {
    
    public static void main(String[] args) {
        System.out.println("=== Lambda Expressions Demo ===\n");
        
        // Traditional way with anonymous class
        System.out.println("--- Traditional Anonymous Class ---");
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from anonymous class");
            }
        };
        r1.run();
        
        // Lambda expression
        System.out.println("\n--- Lambda Expression ---");
        Runnable r2 = () -> System.out.println("Hello from lambda!");
        r2.run();
        
        // Lambda with parameters
        System.out.println("\n--- Lambda with Parameters ---");
        Calculator add = (a, b) -> a + b;
        Calculator multiply = (a, b) -> a * b;
        
        System.out.println("5 + 3 = " + add.calculate(5, 3));
        System.out.println("5 * 3 = " + multiply.calculate(5, 3));
        
        // Built-in functional interfaces
        System.out.println("\n--- Built-in Functional Interfaces ---");
        
        // Predicate - takes one argument, returns boolean
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println("Is 4 even? " + isEven.test(4));
        System.out.println("Is 5 even? " + isEven.test(5));
        
        // Function - takes one argument, returns a result
        Function<String, Integer> stringLength = s -> s.length();
        System.out.println("Length of 'Java': " + stringLength.apply("Java"));
        
        // Consumer - takes one argument, returns nothing
        Consumer<String> printer = s -> System.out.println("Printing: " + s);
        printer.accept("Hello, Lambda!");
        
        // Supplier - takes no arguments, returns a result
        Supplier<Double> randomSupplier = () -> Math.random();
        System.out.println("Random number: " + randomSupplier.get());
        
        // Lambda with collections
        System.out.println("\n--- Lambda with Collections ---");
        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");
        
        // forEach with lambda
        System.out.println("Names:");
        names.forEach(name -> System.out.println("  - " + name));
        
        // Method reference
        System.out.println("\nUsing method reference:");
        names.forEach(System.out::println);
        
        System.out.println("\n✓ Demo completed!");
    }
}

/**
 * Functional interface for calculator operations
 */
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}
