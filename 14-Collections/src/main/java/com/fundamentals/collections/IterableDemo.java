package com.fundamentals.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Iterable Interface and Custom Iteration
 * 
 * Iterable is the root interface for all collection classes.
 * Any class that implements Iterable can be used in enhanced for-loop.
 * 
 * Iterable Interface:
 * - iterator(): Returns an Iterator over elements
 * - forEach(Consumer): Performs action on each element (Java 8+)
 * - spliterator(): Returns a Spliterator for parallel processing (Java 8+)
 * 
 * Iterable vs Iterator:
 * - Iterable: A collection that CAN BE iterated (has iterator() method)
 * - Iterator: The object that DOES the iteration (has next(), hasNext())
 * 
 * Enhanced For-Loop:
 * - Works with any Iterable
 * - Internally uses Iterator
 * - Syntax: for (Element e : iterable) { }
 * 
 * Creating Custom Iterable:
 * - Implement Iterable interface
 * - Provide iterator() method that returns Iterator
 */
public class IterableDemo {

    public static void main(String[] args) {
        System.out.println("=== Iterable Interface ===\n");

        demonstrateIterableBasics();
        demonstrateForEachMethod();
        demonstrateCustomIterable();
        demonstrateIterableVsIterator();
        demonstrateMultipleIterators();
    }

    /**
     * Demonstrate Iterable basics
     */
    private static void demonstrateIterableBasics() {
        System.out.println("--- 1. Iterable Basics ---");
        System.out.println("Any Iterable can be used in enhanced for-loop\n");

        // List implements Iterable
        List<String> cities = Arrays.asList("Istanbul", "Ankara", "Izmir", "Bursa");

        // Enhanced for-loop (uses Iterable.iterator() internally)
        System.out.println("Enhanced for-loop:");
        for (String city : cities) {
            System.out.println("  " + city);
        }

        // What happens behind the scenes
        System.out.println("\nEquivalent to:");
        System.out.println("  Iterator<String> iter = cities.iterator();");
        System.out.println("  while (iter.hasNext()) {");
        System.out.println("      String city = iter.next();");
        System.out.println("      // process city");
        System.out.println("  }");

        System.out.println();
    }

    /**
     * Demonstrate forEach method
     */
    private static void demonstrateForEachMethod() {
        System.out.println("--- 2. forEach Method (Java 8+) ---");
        System.out.println("Iterable.forEach() accepts a Consumer\n");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // forEach with lambda
        System.out.println("forEach with lambda:");
        numbers.forEach(n -> System.out.println("  Number: " + n));

        // forEach with method reference
        System.out.println("\nforEach with method reference:");
        numbers.forEach(System.out::println);

        // forEach with multi-statement lambda
        System.out.println("\nforEach with complex logic:");
        numbers.forEach(n -> {
            if (n % 2 == 0) {
                System.out.println("  " + n + " is even");
            } else {
                System.out.println("  " + n + " is odd");
            }
        });

        System.out.println();
    }

    /**
     * Demonstrate custom Iterable implementation
     */
    private static void demonstrateCustomIterable() {
        System.out.println("--- 3. Custom Iterable ---");
        System.out.println("Creating a custom class that implements Iterable\n");

        // Custom number range
        NumberRange range = new NumberRange(1, 5);

        System.out.println("Iterating over NumberRange(1, 5):");
        for (Integer num : range) {
            System.out.println("  " + num);
        }

        // Custom string tokenizer
        System.out.println("\nIterating over custom Sentence:");
        Sentence sentence = new Sentence("Hello World From Java");
        for (String word : sentence) {
            System.out.println("  Word: " + word);
        }

        // forEach also works
        System.out.println("\nUsing forEach on custom Iterable:");
        range.forEach(n -> System.out.print(n + " "));
        System.out.println();

        System.out.println();
    }

    /**
     * Demonstrate difference between Iterable and Iterator
     */
    private static void demonstrateIterableVsIterator() {
        System.out.println("--- 4. Iterable vs Iterator ---");

        System.out.println("Iterable (Interface):");
        System.out.println("  - Represents a collection that CAN BE iterated");
        System.out.println("  - Method: iterator() returns Iterator");
        System.out.println("  - Can create multiple iterators");
        System.out.println("  - Collection itself");

        System.out.println("\nIterator (Interface):");
        System.out.println("  - Represents the object that DOES iteration");
        System.out.println("  - Methods: hasNext(), next(), remove()");
        System.out.println("  - One-time use (exhausted after traversal)");
        System.out.println("  - Traversal mechanism");

        System.out.println("\nRelationship:");
        System.out.println("  Iterable.iterator() --> returns --> Iterator");
        System.out.println("  (Collection)                       (Traversal tool)");

        System.out.println();
    }

    /**
     * Demonstrate multiple iterators from same Iterable
     */
    private static void demonstrateMultipleIterators() {
        System.out.println("--- 5. Multiple Iterators ---");
        System.out.println("Each call to iterator() returns a new independent iterator\n");

        List<String> items = Arrays.asList("A", "B", "C", "D");

        // Create two independent iterators
        Iterator<String> iter1 = items.iterator();
        Iterator<String> iter2 = items.iterator();

        System.out.println("Two independent iterators on same list:");
        System.out.println("List: " + items);

        // Move iter1 forward by 2
        System.out.println("\nAdvancing iter1 by 2 positions:");
        System.out.println("  iter1.next(): " + iter1.next());
        System.out.println("  iter1.next(): " + iter1.next());

        // iter2 is still at beginning
        System.out.println("\niter2 is still at beginning:");
        System.out.println("  iter2.next(): " + iter2.next());

        // Nested iteration
        System.out.println("\nNested iteration (all pairs):");
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        for (Integer num1 : numbers) {
            for (Integer num2 : numbers) {
                System.out.println("  (" + num1 + ", " + num2 + ")");
            }
        }

        System.out.println("\nExercise completed!");
    }

    /**
     * Custom Iterable: Number Range
     */
    static class NumberRange implements Iterable<Integer> {
        private final int start;
        private final int end;

        NumberRange(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public Iterator<Integer> iterator() {
            return new NumberRangeIterator();
        }

        private class NumberRangeIterator implements Iterator<Integer> {
            private int current = start;

            @Override
            public boolean hasNext() {
                return current <= end;
            }

            @Override
            public Integer next() {
                return current++;
            }
        }
    }

    /**
     * Custom Iterable: Sentence (word by word)
     */
    static class Sentence implements Iterable<String> {
        private final String[] words;

        Sentence(String text) {
            this.words = text.split("\\s+");
        }

        @Override
        public Iterator<String> iterator() {
            return Arrays.asList(words).iterator();
        }
    }
}
