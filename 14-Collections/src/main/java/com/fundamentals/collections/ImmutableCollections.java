package com.fundamentals.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Immutable Collections
 * 
 * Immutable collections cannot be modified after creation.
 * Any modification attempt throws UnsupportedOperationException.
 * 
 * Benefits:
 * - Thread Safety: No synchronization needed
 * - Security: Cannot be modified by malicious code
 * - Predictability: State never changes
 * - Simplicity: Easier to reason about
 * 
 * Ways to Create Immutable Collections:
 * 
 * 1. Java 9+ Factory Methods (truly immutable):
 * - List.of(), Set.of(), Map.of(), Map.ofEntries()
 * 
 * 2. Collections Utility (unmodifiable views):
 * - Collections.unmodifiableList()
 * - Collections.unmodifiableSet()
 * - Collections.unmodifiableMap()
 * 
 * 3. Java 10+ Copy Methods:
 * - List.copyOf(), Set.copyOf(), Map.copyOf()
 * 
 * Important Differences:
 * - List.of() creates truly immutable collection
 * - Collections.unmodifiableList() creates unmodifiable VIEW
 * (original can still be modified)
 */
public class ImmutableCollections {

    public static void main(String[] args) {
        System.out.println("=== Immutable Collections ===\n");

        demonstrateJava9FactoryMethods();
        demonstrateUnmodifiableWrappers();
        demonstrateCopyOfMethods();
        demonstrateDifferenceBetweenMethods();
        demonstrateImmutableMapEntries();
        demonstratePracticalUseCases();
    }

    /**
     * Demonstrate Java 9+ factory methods
     */
    private static void demonstrateJava9FactoryMethods() {
        System.out.println("--- 1. Java 9+ Factory Methods ---");
        System.out.println("List.of(), Set.of(), Map.of() create truly immutable collections\n");

        // Immutable List
        System.out.println("Immutable List:");
        List<String> fruits = List.of("Apple", "Banana", "Cherry");
        System.out.println("  fruits = " + fruits);

        try {
            fruits.add("Date"); // UnsupportedOperationException
        } catch (UnsupportedOperationException e) {
            System.out.println("  fruits.add() -> UnsupportedOperationException");
        }

        try {
            fruits.set(0, "Avocado"); // UnsupportedOperationException
        } catch (UnsupportedOperationException e) {
            System.out.println("  fruits.set() -> UnsupportedOperationException");
        }

        // Immutable Set
        System.out.println("\nImmutable Set:");
        Set<Integer> numbers = Set.of(1, 2, 3, 4, 5);
        System.out.println("  numbers = " + numbers);

        try {
            numbers.add(6);
        } catch (UnsupportedOperationException e) {
            System.out.println("  numbers.add() -> UnsupportedOperationException");
        }

        // Immutable Map
        System.out.println("\nImmutable Map:");
        Map<String, Integer> ages = Map.of(
                "Alice", 25,
                "Bob", 30,
                "Charlie", 28);
        System.out.println("  ages = " + ages);

        try {
            ages.put("David", 35);
        } catch (UnsupportedOperationException e) {
            System.out.println("  ages.put() -> UnsupportedOperationException");
        }

        // Null values not allowed
        System.out.println("\nNull values not allowed:");
        try {
            List<String> withNull = List.of("A", null, "B");
        } catch (NullPointerException e) {
            System.out.println("  List.of(\"A\", null, \"B\") -> NullPointerException");
        }

        // Duplicate values in Set not allowed
        System.out.println("\nDuplicate values in Set.of() not allowed:");
        try {
            Set<String> duplicates = Set.of("A", "B", "A");
        } catch (IllegalArgumentException e) {
            System.out.println("  Set.of(\"A\", \"B\", \"A\") -> IllegalArgumentException");
        }

        System.out.println();
    }

    /**
     * Demonstrate unmodifiable wrappers
     */
    private static void demonstrateUnmodifiableWrappers() {
        System.out.println("--- 2. Unmodifiable Wrappers ---");
        System.out.println("Collections.unmodifiableXxx() creates unmodifiable VIEW\n");

        // Create mutable list
        List<String> mutableList = new ArrayList<>();
        mutableList.add("Red");
        mutableList.add("Green");
        mutableList.add("Blue");

        System.out.println("Original mutable list: " + mutableList);

        // Create unmodifiable view
        List<String> unmodifiableList = Collections.unmodifiableList(mutableList);
        System.out.println("Unmodifiable view: " + unmodifiableList);

        // Cannot modify through view
        try {
            unmodifiableList.add("Yellow");
        } catch (UnsupportedOperationException e) {
            System.out.println("\nunmodifiableList.add() -> UnsupportedOperationException");
        }

        // But original can still be modified!
        System.out.println("\nModifying original list:");
        mutableList.add("Yellow");
        System.out.println("After mutableList.add(\"Yellow\"):");
        System.out.println("  Original: " + mutableList);
        System.out.println("  View also changed: " + unmodifiableList);
        System.out.println("  (View reflects changes to original!)");

        // Same for Set and Map
        System.out.println("\nUnmodifiable Set:");
        Set<Integer> mutableSet = new HashSet<>(Arrays.asList(1, 2, 3));
        Set<Integer> unmodifiableSet = Collections.unmodifiableSet(mutableSet);
        System.out.println("  unmodifiableSet = " + unmodifiableSet);

        System.out.println("\nUnmodifiable Map:");
        Map<String, String> mutableMap = new HashMap<>();
        mutableMap.put("key1", "value1");
        Map<String, String> unmodifiableMap = Collections.unmodifiableMap(mutableMap);
        System.out.println("  unmodifiableMap = " + unmodifiableMap);

        System.out.println();
    }

    /**
     * Demonstrate copyOf methods (Java 10+)
     */
    private static void demonstrateCopyOfMethods() {
        System.out.println("--- 3. CopyOf Methods (Java 10+) ---");
        System.out.println("List.copyOf(), Set.copyOf(), Map.copyOf() create immutable copies\n");

        // Create mutable list
        List<String> mutableList = new ArrayList<>();
        mutableList.add("One");
        mutableList.add("Two");
        mutableList.add("Three");

        System.out.println("Original mutable list: " + mutableList);

        // Create immutable copy
        List<String> immutableCopy = List.copyOf(mutableList);
        System.out.println("Immutable copy: " + immutableCopy);

        // Modify original
        System.out.println("\nModifying original list:");
        mutableList.add("Four");
        System.out.println("After mutableList.add(\"Four\"):");
        System.out.println("  Original: " + mutableList);
        System.out.println("  Copy unchanged: " + immutableCopy);
        System.out.println("  (Copy is independent of original!)");

        // Cannot modify copy
        try {
            immutableCopy.add("Five");
        } catch (UnsupportedOperationException e) {
            System.out.println("\nimmutableCopy.add() -> UnsupportedOperationException");
        }

        // Set.copyOf removes duplicates
        System.out.println("\nSet.copyOf() removes duplicates:");
        List<String> withDuplicates = Arrays.asList("A", "B", "A", "C", "B");
        Set<String> uniqueSet = Set.copyOf(withDuplicates);
        System.out.println("  Input: " + withDuplicates);
        System.out.println("  Set.copyOf(): " + uniqueSet);

        System.out.println();
    }

    /**
     * Demonstrate differences between methods
     */
    private static void demonstrateDifferenceBetweenMethods() {
        System.out.println("--- 4. Comparison of Methods ---\n");

        System.out.println("| Method                          | Immutable? | Allows Null? | Independent? |");
        System.out.println("|----------------------------------|------------|--------------|--------------|");
        System.out.println("| List.of()                        | Yes        | No           | N/A          |");
        System.out.println("| Collections.unmodifiableList()   | View only  | Yes          | No (view)    |");
        System.out.println("| List.copyOf()                    | Yes        | No           | Yes (copy)   |");

        System.out.println("\nRecommendations:");
        System.out.println("  1. Use List.of(), Set.of(), Map.of() for inline creation");
        System.out.println("  2. Use List.copyOf() etc. when converting from mutable collection");
        System.out.println("  3. Avoid Collections.unmodifiableXxx() unless you specifically need a view");

        System.out.println();
    }

    /**
     * Demonstrate Map.ofEntries for more than 10 entries
     */
    private static void demonstrateImmutableMapEntries() {
        System.out.println("--- 5. Map.ofEntries() ---");
        System.out.println("Map.of() supports max 10 key-value pairs");
        System.out.println("For more, use Map.ofEntries()\n");

        // Map.of() - up to 10 pairs
        Map<String, String> smallMap = Map.of(
                "key1", "value1",
                "key2", "value2",
                "key3", "value3");
        System.out.println("Map.of() (up to 10 pairs): " + smallMap);

        // Map.ofEntries() - any number of entries
        Map<String, Integer> countryCodes = Map.ofEntries(
                Map.entry("Turkey", 90),
                Map.entry("USA", 1),
                Map.entry("UK", 44),
                Map.entry("Germany", 49),
                Map.entry("France", 33),
                Map.entry("Italy", 39),
                Map.entry("Spain", 34),
                Map.entry("Japan", 81),
                Map.entry("China", 86),
                Map.entry("India", 91),
                Map.entry("Brazil", 55),
                Map.entry("Mexico", 52) // More than 10 entries!
        );
        System.out.println("\nMap.ofEntries() (any number):");
        countryCodes.forEach((country, code) -> System.out.println("  " + country + ": +" + code));

        System.out.println();
    }

    /**
     * Demonstrate practical use cases
     */
    private static void demonstratePracticalUseCases() {
        System.out.println("--- 6. Practical Use Cases ---\n");

        // Configuration constants
        System.out.println("1. Configuration Constants:");
        final Map<String, String> CONFIG = Map.of(
                "app.name", "MyApplication",
                "app.version", "1.0.0",
                "app.env", "production");
        System.out.println("  CONFIG = " + CONFIG);

        // Enum alternative
        System.out.println("\n2. Status Codes:");
        final Map<Integer, String> HTTP_STATUS = Map.of(
                200, "OK",
                201, "Created",
                400, "Bad Request",
                401, "Unauthorized",
                403, "Forbidden",
                404, "Not Found",
                500, "Internal Server Error");
        System.out.println("  HTTP_STATUS = " + HTTP_STATUS);
        System.out.println("  Status 404: " + HTTP_STATUS.get(404));

        // Returning immutable from method
        System.out.println("\n3. Returning Immutable Collection:");
        List<String> weekdays = getWeekdays();
        System.out.println("  getWeekdays() = " + weekdays);
        try {
            weekdays.add("Funday");
        } catch (UnsupportedOperationException e) {
            System.out.println("  Caller cannot modify: UnsupportedOperationException");
        }

        // Defensive copying
        System.out.println("\n4. Defensive Copying:");
        Person person = new Person("Alice", new ArrayList<>(Arrays.asList("Java", "Python")));
        System.out.println("  Person skills: " + person.getSkills());
        try {
            person.getSkills().add("C++");
        } catch (UnsupportedOperationException e) {
            System.out.println("  Cannot modify skills: UnsupportedOperationException");
        }

        System.out.println("\nExercise completed!");
    }

    /**
     * Example method returning immutable collection
     */
    private static List<String> getWeekdays() {
        return List.of("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
    }

    /**
     * Example class with defensive copying
     */
    static class Person {
        private final String name;
        private final List<String> skills;

        Person(String name, List<String> skills) {
            this.name = name;
            // Defensive copy on input
            this.skills = List.copyOf(skills);
        }

        String getName() {
            return name;
        }

        List<String> getSkills() {
            // Already immutable, safe to return directly
            return skills;
        }
    }
}
