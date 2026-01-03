package com.fundamentals.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generic Collections
 * 
 * Generics provide type safety and eliminate the need for casting.
 * They allow collections to be parameterized with specific types.
 * 
 * Benefits of Generics:
 * - Type Safety: Compile-time type checking
 * - No Casting: No need to cast when retrieving elements
 * - Code Reuse: Write once, use with any type
 * - Compile-time Errors: Catch type errors early
 * 
 * Wildcards:
 * - <?> : Unknown type (unbounded)
 * - <? extends T> : Upper bounded (T or subtype)
 * - <? super T> : Lower bounded (T or supertype)
 * 
 * PECS Rule (Producer Extends, Consumer Super):
 * - Use "extends" when you only GET values from collection
 * - Use "super" when you only PUT values into collection
 * - Use exact type when you both GET and PUT
 * 
 * Type Erasure:
 * - Generic type information is removed at runtime
 * - List<String> and List<Integer> are same at runtime
 * - Cannot use instanceof with generic types
 */
public class GenericCollections {

    public static void main(String[] args) {
        System.out.println("=== Generic Collections ===\n");

        demonstrateTypeSafety();
        demonstrateGenericMethods();
        demonstrateBoundedWildcards();
        demonstratePECSRule();
        demonstrateCustomGenericClass();
        demonstrateMultipleTypeParameters();
    }

    /**
     * Demonstrate type safety with generics
     */
    private static void demonstrateTypeSafety() {
        System.out.println("--- 1. Type Safety ---\n");

        // Without generics (raw type) - NOT recommended
        System.out.println("Without generics (raw type):");
        System.out.println("  List rawList = new ArrayList();");
        System.out.println("  rawList.add(\"Hello\");");
        System.out.println("  rawList.add(123); // Compiles but causes issues");
        System.out.println("  String s = (String) rawList.get(1); // ClassCastException!");

        // With generics - Recommended
        System.out.println("\nWith generics (type safe):");
        List<String> safeList = new ArrayList<>();
        safeList.add("Hello");
        safeList.add("World");
        // safeList.add(123); // Compile error! Only String allowed

        System.out.println("  List<String> safeList = new ArrayList<>();");
        System.out.println("  safeList.add(\"Hello\");");
        System.out.println("  safeList.add(123); // Compile ERROR - only String allowed");

        // No casting needed
        String first = safeList.get(0); // No cast needed
        System.out.println("\n  String first = safeList.get(0); // No cast needed");
        System.out.println("  first = " + first);

        System.out.println();
    }

    /**
     * Demonstrate generic methods
     */
    private static void demonstrateGenericMethods() {
        System.out.println("--- 2. Generic Methods ---\n");

        // Using generic method with different types
        Integer[] intArray = { 1, 2, 3, 4, 5 };
        String[] strArray = { "A", "B", "C" };
        Double[] doubleArray = { 1.1, 2.2, 3.3 };

        System.out.println("Using generic printArray method:");
        System.out.print("  Integer array: ");
        printArray(intArray);
        System.out.print("  String array: ");
        printArray(strArray);
        System.out.print("  Double array: ");
        printArray(doubleArray);

        // Generic swap
        System.out.println("\nUsing generic swap:");
        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");
        System.out.println("  Before: " + names);
        swap(names, 0, 2);
        System.out.println("  After swap(0, 2): " + names);

        System.out.println();
    }

    /**
     * Generic method to print array
     */
    private static <T> void printArray(T[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1)
                System.out.print(", ");
        }
        System.out.println("]");
    }

    /**
     * Generic method to swap elements
     */
    private static <T> void swap(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    /**
     * Demonstrate bounded wildcards
     */
    private static void demonstrateBoundedWildcards() {
        System.out.println("--- 3. Bounded Wildcards ---\n");

        // Unbounded wildcard: <?>
        System.out.println("Unbounded wildcard (?):");
        System.out.println("  List<?> - any type of List");
        List<String> strings = List.of("A", "B", "C");
        List<Integer> integers = List.of(1, 2, 3);
        printList(strings);
        printList(integers);

        // Upper bounded: <? extends Number>
        System.out.println("\nUpper bounded (? extends Number):");
        System.out.println("  Accepts Number or any subclass");
        List<Integer> intList = List.of(10, 20, 30);
        List<Double> doubleList = List.of(1.5, 2.5, 3.5);
        System.out.println("  Sum of integers: " + sumNumbers(intList));
        System.out.println("  Sum of doubles: " + sumNumbers(doubleList));

        // Lower bounded: <? super Integer>
        System.out.println("\nLower bounded (? super Integer):");
        System.out.println("  Accepts Integer or any superclass");
        List<Number> numberList = new ArrayList<>();
        addIntegers(numberList, 5);
        System.out.println("  Added integers to Number list: " + numberList);

        System.out.println();
    }

    /**
     * Print any type of list
     */
    private static void printList(List<?> list) {
        System.out.print("  List: ");
        for (Object item : list) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    /**
     * Sum numbers (upper bounded)
     */
    private static double sumNumbers(List<? extends Number> numbers) {
        double sum = 0;
        for (Number num : numbers) {
            sum += num.doubleValue();
        }
        return sum;
    }

    /**
     * Add integers (lower bounded)
     */
    private static void addIntegers(List<? super Integer> list, int count) {
        for (int i = 1; i <= count; i++) {
            list.add(i);
        }
    }

    /**
     * Demonstrate PECS rule
     */
    private static void demonstratePECSRule() {
        System.out.println("--- 4. PECS Rule ---");
        System.out.println("Producer Extends, Consumer Super\n");

        System.out.println("Producer (you GET from it) - use 'extends':");
        System.out.println("  List<? extends Number> producer");
        System.out.println("  Number n = producer.get(0); // OK");
        System.out.println("  producer.add(1); // COMPILE ERROR!");

        System.out.println("\nConsumer (you PUT into it) - use 'super':");
        System.out.println("  List<? super Integer> consumer");
        System.out.println("  consumer.add(1); // OK");
        System.out.println("  Integer n = consumer.get(0); // COMPILE ERROR!");

        System.out.println("\nBoth (GET and PUT) - use exact type:");
        System.out.println("  List<Integer> both");
        System.out.println("  Integer n = both.get(0); // OK");
        System.out.println("  both.add(1); // OK");

        // Practical example
        System.out.println("\nPractical example - copy method:");
        List<Integer> source = List.of(1, 2, 3);
        List<Number> destination = new ArrayList<>();
        copyElements(source, destination);
        System.out.println("  Source (Integer): " + source);
        System.out.println("  Destination (Number): " + destination);

        System.out.println();
    }

    /**
     * Copy elements following PECS
     */
    private static <T> void copyElements(List<? extends T> source, List<? super T> dest) {
        for (T item : source) {
            dest.add(item);
        }
    }

    /**
     * Demonstrate custom generic class
     */
    private static void demonstrateCustomGenericClass() {
        System.out.println("--- 5. Custom Generic Class ---\n");

        // Using custom generic Box class
        System.out.println("Box<String>:");
        Box<String> stringBox = new Box<>("Hello");
        System.out.println("  Content: " + stringBox.getContent());
        stringBox.setContent("World");
        System.out.println("  After update: " + stringBox.getContent());

        System.out.println("\nBox<Integer>:");
        Box<Integer> intBox = new Box<>(100);
        System.out.println("  Content: " + intBox.getContent());

        // Box with constraints
        System.out.println("\nNumberBox<Double> (bounded type):");
        NumberBox<Double> doubleBox = new NumberBox<>(3.14);
        System.out.println("  Content: " + doubleBox.getContent());
        System.out.println("  Doubled: " + doubleBox.getDoubled());

        System.out.println();
    }

    /**
     * Generic Box class
     */
    static class Box<T> {
        private T content;

        Box(T content) {
            this.content = content;
        }

        T getContent() {
            return content;
        }

        void setContent(T content) {
            this.content = content;
        }
    }

    /**
     * Bounded generic class
     */
    static class NumberBox<T extends Number> {
        private T content;

        NumberBox(T content) {
            this.content = content;
        }

        T getContent() {
            return content;
        }

        double getDoubled() {
            return content.doubleValue() * 2;
        }
    }

    /**
     * Demonstrate multiple type parameters
     */
    private static void demonstrateMultipleTypeParameters() {
        System.out.println("--- 6. Multiple Type Parameters ---\n");

        // Pair class with two type parameters
        Pair<String, Integer> nameAge = new Pair<>("Alice", 25);
        System.out.println("Pair<String, Integer>:");
        System.out.println("  First: " + nameAge.getFirst());
        System.out.println("  Second: " + nameAge.getSecond());

        Pair<Integer, String> idName = new Pair<>(101, "Bob");
        System.out.println("\nPair<Integer, String>:");
        System.out.println("  First: " + idName.getFirst());
        System.out.println("  Second: " + idName.getSecond());

        // Using Map with generics
        System.out.println("\nMap<String, List<Integer>>:");
        Map<String, List<Integer>> scores = new HashMap<>();
        scores.put("Math", List.of(85, 90, 88));
        scores.put("Science", List.of(92, 88, 95));
        scores.forEach((subject, grades) -> System.out.println("  " + subject + ": " + grades));

        System.out.println("\nExercise completed!");
    }

    /**
     * Generic Pair class
     */
    static class Pair<K, V> {
        private final K first;
        private final V second;

        Pair(K first, V second) {
            this.first = first;
            this.second = second;
        }

        K getFirst() {
            return first;
        }

        V getSecond() {
            return second;
        }
    }
}
