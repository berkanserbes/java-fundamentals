package com.fundamentals.collections;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Set Interface and Implementations
 * 
 * Set is a collection that contains no duplicate elements.
 * It models the mathematical set abstraction.
 * 
 * Key Characteristics:
 * - No Duplicates: Each element can appear only once
 * - No Indexing: Elements cannot be accessed by index
 * - Null: Most implementations allow one null element
 * 
 * Common Implementations:
 * 1. HashSet - Fastest, no order guarantee, allows null
 * 2. LinkedHashSet - Maintains insertion order, slightly slower
 * 3. TreeSet - Sorted order (natural or custom), no null (implements SortedSet)
 * 
 * Set Methods (inherited from Collection):
 * - add(E e): Add element (returns false if already exists)
 * - remove(Object o): Remove element
 * - contains(Object o): Check if element exists
 * - size(): Get number of elements
 * - isEmpty(): Check if set is empty
 * - clear(): Remove all elements
 * 
 * Set Operations:
 * - Union: addAll()
 * - Intersection: retainAll()
 * - Difference: removeAll()
 */
public class SetOperations {

    public static void main(String[] args) {
        System.out.println("=== Set Interface and Implementations ===\n");

        demonstrateHashSet();
        demonstrateLinkedHashSet();
        demonstrateTreeSet();
        demonstrateSetOperations();
        demonstrateDuplicateHandling();
    }

    /**
     * Demonstrate HashSet operations
     */
    private static void demonstrateHashSet() {
        System.out.println("--- 1. HashSet Operations ---");
        System.out.println("HashSet: Fastest set, no order guarantee\n");

        Set<String> languages = new HashSet<>();

        // Adding elements
        languages.add("Java");
        languages.add("Python");
        languages.add("JavaScript");
        languages.add("C++");
        languages.add("Ruby");

        System.out.println("Languages: " + languages);
        System.out.println("Note: Order is not guaranteed");

        // Trying to add duplicate
        System.out.println("\nAdding duplicate 'Java':");
        boolean added = languages.add("Java");
        System.out.println("Was added? " + added);
        System.out.println("Set after duplicate attempt: " + languages);

        // Checking existence
        System.out.println("\nChecking existence:");
        System.out.println("Contains 'Python'? " + languages.contains("Python"));
        System.out.println("Contains 'Go'? " + languages.contains("Go"));

        // Removing element
        System.out.println("\nRemoving 'Ruby':");
        boolean removed = languages.remove("Ruby");
        System.out.println("Was removed? " + removed);
        System.out.println("After removal: " + languages);

        // Size
        System.out.println("\nSet size: " + languages.size());

        System.out.println();
    }

    /**
     * Demonstrate LinkedHashSet operations
     */
    private static void demonstrateLinkedHashSet() {
        System.out.println("--- 2. LinkedHashSet Operations ---");
        System.out.println("LinkedHashSet: Maintains insertion order\n");

        Set<String> countries = new LinkedHashSet<>();

        // Adding elements
        countries.add("Turkey");
        countries.add("Germany");
        countries.add("France");
        countries.add("Italy");
        countries.add("Spain");

        System.out.println("Countries: " + countries);
        System.out.println("Note: Insertion order is maintained");

        // Adding more elements
        System.out.println("\nAdding 'Japan' and 'Brazil':");
        countries.add("Japan");
        countries.add("Brazil");
        System.out.println("After addition: " + countries);
        System.out.println("New elements appear at the end");

        // Trying to add duplicate
        System.out.println("\nAdding duplicate 'France':");
        boolean added = countries.add("France");
        System.out.println("Was added? " + added);
        System.out.println("Order unchanged: " + countries);

        System.out.println();
    }

    /**
     * Demonstrate TreeSet operations
     */
    private static void demonstrateTreeSet() {
        System.out.println("--- 3. TreeSet Operations ---");
        System.out.println("TreeSet: Elements in sorted order\n");

        Set<Integer> numbers = new TreeSet<>();

        // Adding elements in random order
        numbers.add(50);
        numbers.add(20);
        numbers.add(80);
        numbers.add(10);
        numbers.add(60);
        numbers.add(30);

        System.out.println("Numbers (automatically sorted): " + numbers);

        // Adding more elements
        System.out.println("\nAdding 5 and 100:");
        numbers.add(5);
        numbers.add(100);
        System.out.println("After addition: " + numbers);
        System.out.println("Note: Elements are automatically sorted");

        // TreeSet with strings (alphabetical order)
        Set<String> names = new TreeSet<>();
        names.add("Charlie");
        names.add("Alice");
        names.add("David");
        names.add("Bob");

        System.out.println("\nNames (alphabetically sorted): " + names);

        System.out.println();
    }

    /**
     * Demonstrate mathematical set operations
     */
    private static void demonstrateSetOperations() {
        System.out.println("--- 4. Set Operations (Mathematical) ---");

        Set<Integer> setA = new HashSet<>();
        setA.add(1);
        setA.add(2);
        setA.add(3);
        setA.add(4);
        setA.add(5);

        Set<Integer> setB = new HashSet<>();
        setB.add(4);
        setB.add(5);
        setB.add(6);
        setB.add(7);
        setB.add(8);

        System.out.println("Set A: " + setA);
        System.out.println("Set B: " + setB);

        // Union (A ∪ B)
        System.out.println("\nUnion (A union B):");
        Set<Integer> union = new HashSet<>(setA);
        union.addAll(setB);
        System.out.println("A ∪ B = " + union);

        // Intersection (A ∩ B)
        System.out.println("\nIntersection (A intersect B):");
        Set<Integer> intersection = new HashSet<>(setA);
        intersection.retainAll(setB);
        System.out.println("A ∩ B = " + intersection);

        // Difference (A - B)
        System.out.println("\nDifference (A minus B):");
        Set<Integer> difference = new HashSet<>(setA);
        difference.removeAll(setB);
        System.out.println("A - B = " + difference);

        // Symmetric Difference ((A - B) ∪ (B - A))
        System.out.println("\nSymmetric Difference:");
        Set<Integer> symmetricDiff = new HashSet<>(setA);
        symmetricDiff.addAll(setB);
        Set<Integer> temp = new HashSet<>(setA);
        temp.retainAll(setB);
        symmetricDiff.removeAll(temp);
        System.out.println("(A - B) ∪ (B - A) = " + symmetricDiff);

        System.out.println();
    }

    /**
     * Demonstrate duplicate handling
     */
    private static void demonstrateDuplicateHandling() {
        System.out.println("--- 5. Duplicate Handling ---");

        // Removing duplicates from a list
        System.out.println("Removing duplicates from array:");
        String[] fruits = {"Apple", "Banana", "Apple", "Orange", "Banana", "Mango", "Apple"};
        
        System.out.print("Original array: [");
        for (int i = 0; i < fruits.length; i++) {
            System.out.print(fruits[i]);
            if (i < fruits.length - 1) System.out.print(", ");
        }
        System.out.println("]");

        // Using HashSet to remove duplicates
        Set<String> uniqueFruits = new HashSet<>();
        for (String fruit : fruits) {
            uniqueFruits.add(fruit);
        }
        System.out.println("Unique fruits (HashSet): " + uniqueFruits);

        // Using LinkedHashSet to preserve order
        Set<String> uniqueFruitsOrdered = new LinkedHashSet<>();
        for (String fruit : fruits) {
            uniqueFruitsOrdered.add(fruit);
        }
        System.out.println("Unique fruits (LinkedHashSet, order preserved): " + uniqueFruitsOrdered);

        // Counting unique elements
        System.out.println("\nCounting:");
        System.out.println("Total elements: " + fruits.length);
        System.out.println("Unique elements: " + uniqueFruits.size());
        System.out.println("Duplicates: " + (fruits.length - uniqueFruits.size()));

        System.out.println("\nExercise completed!");
    }
}
