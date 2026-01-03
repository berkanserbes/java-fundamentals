package com.fundamentals.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Collection Framework Basics
 * 
 * The Collection Framework is a unified architecture for representing and
 * manipulating collections of objects.
 * 
 * Key Interfaces Hierarchy:
 * 
 * Iterable (root)
 * |
 * +-- Collection
 * |
 * +-- List (ordered, allows duplicates)
 * | |
 * | +-- ArrayList, LinkedList, Vector, Stack
 * |
 * +-- Set (no duplicates)
 * | |
 * | +-- HashSet, LinkedHashSet
 * | +-- SortedSet
 * | |
 * | +-- NavigableSet
 * | |
 * | +-- TreeSet
 * |
 * +-- Queue (FIFO - First In First Out)
 * |
 * +-- PriorityQueue, LinkedList
 * +-- Deque (Double Ended Queue)
 * |
 * +-- ArrayDeque, LinkedList
 * 
 * Map (separate hierarchy - not a Collection)
 * |
 * +-- HashMap, LinkedHashMap, Hashtable
 * +-- SortedMap
 * |
 * +-- NavigableMap
 * |
 * +-- TreeMap
 * 
 * Core Methods in Collection Interface:
 * - add(E e): Add element
 * - remove(Object o): Remove element
 * - contains(Object o): Check if element exists
 * - size(): Get number of elements
 * - isEmpty(): Check if collection is empty
 * - clear(): Remove all elements
 * - iterator(): Get iterator
 * - toArray(): Convert to array
 * - addAll(Collection c): Add all elements from another collection
 * - removeAll(Collection c): Remove all elements that exist in another
 * collection
 * - retainAll(Collection c): Keep only elements that exist in another
 * collection
 */
public class CollectionBasics {

    public static void main(String[] args) {
        System.out.println("=== Collection Framework Basics ===\n");

        demonstrateCollectionInterface();
        demonstrateCommonOperations();
        demonstrateBulkOperations();
        demonstrateCollectionTypes();
        demonstrateIterationMethods();
    }

    /**
     * Demonstrate Collection interface basics
     */
    private static void demonstrateCollectionInterface() {
        System.out.println("--- 1. Collection Interface Basics ---");
        System.out.println("Collection is the root interface for List, Set, and Queue.\n");

        // Using Collection reference with ArrayList implementation
        Collection<String> fruits = new ArrayList<>();

        // Adding elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Mango");

        System.out.println("Fruits collection: " + fruits);
        System.out.println("Size: " + fruits.size());
        System.out.println("Is empty? " + fruits.isEmpty());
        System.out.println("Contains 'Banana'? " + fruits.contains("Banana"));
        System.out.println("Contains 'Grape'? " + fruits.contains("Grape"));

        System.out.println();
    }

    /**
     * Demonstrate common collection operations
     */
    private static void demonstrateCommonOperations() {
        System.out.println("--- 2. Common Operations ---");

        Collection<Integer> numbers = new ArrayList<>();

        // Adding elements
        System.out.println("Adding elements:");
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);
        numbers.add(50);
        System.out.println("Numbers: " + numbers);

        // Removing elements
        System.out.println("\nRemoving element 30:");
        numbers.remove(30);
        System.out.println("After removal: " + numbers);

        // Checking existence
        System.out.println("\nChecking existence:");
        System.out.println("Contains 20? " + numbers.contains(20));
        System.out.println("Contains 30? " + numbers.contains(30));

        // Size and empty check
        System.out.println("\nCollection info:");
        System.out.println("Size: " + numbers.size());
        System.out.println("Is empty? " + numbers.isEmpty());

        // Converting to array
        System.out.println("\nConverting to array:");
        Object[] numArray = numbers.toArray();
        System.out.print("Array: [");
        for (int i = 0; i < numArray.length; i++) {
            System.out.print(numArray[i]);
            if (i < numArray.length - 1)
                System.out.print(", ");
        }
        System.out.println("]");

        // Typed array conversion
        Integer[] typedArray = numbers.toArray(new Integer[0]);
        System.out.println("Typed array length: " + typedArray.length);

        System.out.println();
    }

    /**
     * Demonstrate bulk operations
     */
    private static void demonstrateBulkOperations() {
        System.out.println("--- 3. Bulk Operations ---");

        Collection<String> colors1 = new ArrayList<>();
        colors1.add("Red");
        colors1.add("Green");
        colors1.add("Blue");

        Collection<String> colors2 = new ArrayList<>();
        colors2.add("Yellow");
        colors2.add("Purple");

        System.out.println("Collection 1: " + colors1);
        System.out.println("Collection 2: " + colors2);

        // addAll - Add all elements from another collection
        System.out.println("\nUsing addAll:");
        colors1.addAll(colors2);
        System.out.println("After addAll: " + colors1);

        // removeAll - Remove all elements that exist in another collection
        Collection<String> toRemove = new ArrayList<>();
        toRemove.add("Green");
        toRemove.add("Purple");

        System.out.println("\nUsing removeAll (removing Green and Purple):");
        colors1.removeAll(toRemove);
        System.out.println("After removeAll: " + colors1);

        // retainAll - Keep only elements that exist in another collection
        Collection<String> toRetain = new ArrayList<>();
        toRetain.add("Red");
        toRetain.add("Yellow");
        toRetain.add("Orange"); // Not in colors1

        System.out.println("\nUsing retainAll (keeping only Red and Yellow):");
        colors1.retainAll(toRetain);
        System.out.println("After retainAll: " + colors1);

        // clear - Remove all elements
        System.out.println("\nUsing clear:");
        colors1.clear();
        System.out.println("After clear: " + colors1);
        System.out.println("Is empty? " + colors1.isEmpty());

        System.out.println();
    }

    /**
     * Demonstrate different collection types
     */
    private static void demonstrateCollectionTypes() {
        System.out.println("--- 4. Different Collection Types ---");

        // ArrayList - Ordered, allows duplicates, fast random access
        Collection<String> arrayList = new ArrayList<>();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("A"); // Duplicate allowed
        System.out.println("ArrayList (allows duplicates): " + arrayList);

        // HashSet - No duplicates, no order guarantee
        Collection<String> hashSet = new HashSet<>();
        hashSet.add("A");
        hashSet.add("B");
        hashSet.add("A"); // Duplicate ignored
        System.out.println("HashSet (no duplicates): " + hashSet);

        // LinkedList - Ordered, allows duplicates, efficient insertion/deletion
        Collection<String> linkedList = new LinkedList<>();
        linkedList.add("X");
        linkedList.add("Y");
        linkedList.add("X"); // Duplicate allowed
        System.out.println("LinkedList (allows duplicates): " + linkedList);

        System.out.println();
    }

    /**
     * Demonstrate iteration methods
     */
    private static void demonstrateIterationMethods() {
        System.out.println("--- 5. Iteration Methods ---");

        Collection<String> languages = new ArrayList<>();
        languages.add("Java");
        languages.add("Python");
        languages.add("JavaScript");
        languages.add("C++");

        // Method 1: Enhanced for loop
        System.out.println("Method 1: Enhanced for loop");
        for (String lang : languages) {
            System.out.println("  - " + lang);
        }

        // Method 2: Iterator
        System.out.println("\nMethod 2: Iterator");
        java.util.Iterator<String> iterator = languages.iterator();
        while (iterator.hasNext()) {
            String lang = iterator.next();
            System.out.println("  - " + lang);
        }

        // Method 3: forEach with lambda (Java 8+)
        System.out.println("\nMethod 3: forEach with lambda");
        languages.forEach(lang -> System.out.println("  - " + lang));

        // Method 4: Stream API (Java 8+)
        System.out.println("\nMethod 4: Stream API");
        languages.stream()
                .filter(lang -> lang.startsWith("J"))
                .forEach(lang -> System.out.println("  - " + lang + " (starts with J)"));

        System.out.println("\nExercise completed!");
    }
}
