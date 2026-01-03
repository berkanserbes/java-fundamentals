package com.fundamentals.collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.Arrays;

/**
 * List Interface and Implementations
 * 
 * List is an ordered collection (sequence) that allows duplicate elements.
 * Elements can be accessed by their integer index (position).
 * 
 * Key Characteristics:
 * - Ordered: Elements maintain insertion order
 * - Indexed: Elements can be accessed by index (0-based)
 * - Duplicates: Allows duplicate elements
 * - Null: Allows null elements
 * 
 * Common Implementations:
 * 1. ArrayList - Resizable array, fast random access, slow insertion/deletion
 * 2. LinkedList - Doubly-linked list, slow random access, fast
 * insertion/deletion
 * 3. Vector - Synchronized ArrayList (thread-safe but slower)
 * 
 * List-Specific Methods:
 * - get(int index): Get element at index
 * - set(int index, E element): Replace element at index
 * - add(int index, E element): Insert element at index
 * - remove(int index): Remove element at index
 * - indexOf(Object o): Get first index of element
 * - lastIndexOf(Object o): Get last index of element
 * - subList(int from, int to): Get view of portion of list
 */
public class ListOperations {

    public static void main(String[] args) {
        System.out.println("=== List Interface and Implementations ===\n");

        demonstrateArrayList();
        demonstrateLinkedList();
        demonstrateListMethods();
        demonstrateListComparison();
        demonstrateListSearching();
    }

    /**
     * Demonstrate ArrayList operations
     */
    private static void demonstrateArrayList() {
        System.out.println("--- 1. ArrayList Operations ---");
        System.out.println("ArrayList: Resizable array implementation\n");

        // Creating ArrayList
        List<String> cities = new ArrayList<>();

        // Adding elements
        cities.add("Istanbul");
        cities.add("Ankara");
        cities.add("Izmir");
        cities.add("Bursa");
        cities.add("Antalya");

        System.out.println("Cities: " + cities);
        System.out.println("Size: " + cities.size());

        // Accessing elements by index
        System.out.println("\nAccessing by index:");
        System.out.println("First city (index 0): " + cities.get(0));
        System.out.println("Third city (index 2): " + cities.get(2));
        System.out.println("Last city: " + cities.get(cities.size() - 1));

        // Modifying elements
        System.out.println("\nModifying element at index 1:");
        String oldValue = cities.set(1, "Konya");
        System.out.println("Old value: " + oldValue);
        System.out.println("Updated list: " + cities);

        // Inserting at specific position
        System.out.println("\nInserting 'Adana' at index 2:");
        cities.add(2, "Adana");
        System.out.println("After insertion: " + cities);

        // Removing by index
        System.out.println("\nRemoving element at index 3:");
        String removed = cities.remove(3);
        System.out.println("Removed: " + removed);
        System.out.println("After removal: " + cities);

        // Removing by value
        System.out.println("\nRemoving 'Izmir' by value:");
        boolean wasRemoved = cities.remove("Izmir");
        System.out.println("Was removed? " + wasRemoved);
        System.out.println("After removal: " + cities);

        System.out.println();
    }

    /**
     * Demonstrate LinkedList operations
     */
    private static void demonstrateLinkedList() {
        System.out.println("--- 2. LinkedList Operations ---");
        System.out.println("LinkedList: Doubly-linked list implementation\n");

        LinkedList<Integer> numbers = new LinkedList<>();

        // Adding elements
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);
        numbers.add(50);

        System.out.println("Numbers: " + numbers);

        // LinkedList-specific methods (first/last operations)
        System.out.println("\nFirst/Last operations:");
        System.out.println("First element: " + numbers.getFirst());
        System.out.println("Last element: " + numbers.getLast());

        // Adding at beginning and end
        System.out.println("\nAdding at beginning and end:");
        numbers.addFirst(5);
        numbers.addLast(60);
        System.out.println("After addFirst(5) and addLast(60): " + numbers);

        // Removing from beginning and end
        System.out.println("\nRemoving from beginning and end:");
        Integer first = numbers.removeFirst();
        Integer last = numbers.removeLast();
        System.out.println("Removed first: " + first + ", last: " + last);
        System.out.println("After removal: " + numbers);

        // Peek operations (view without removing)
        System.out.println("\nPeek operations:");
        System.out.println("Peek first: " + numbers.peekFirst());
        System.out.println("Peek last: " + numbers.peekLast());
        System.out.println("List unchanged: " + numbers);

        System.out.println();
    }

    /**
     * Demonstrate common List methods
     */
    private static void demonstrateListMethods() {
        System.out.println("--- 3. Common List Methods ---");

        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        fruits.add("Date");
        fruits.add("Banana"); // Duplicate

        System.out.println("Fruits: " + fruits);

        // indexOf and lastIndexOf
        System.out.println("\nSearching:");
        System.out.println("Index of 'Banana': " + fruits.indexOf("Banana"));
        System.out.println("Last index of 'Banana': " + fruits.lastIndexOf("Banana"));
        System.out.println("Index of 'Grape': " + fruits.indexOf("Grape")); // -1 if not found

        // contains
        System.out.println("\nContains check:");
        System.out.println("Contains 'Cherry'? " + fruits.contains("Cherry"));
        System.out.println("Contains 'Mango'? " + fruits.contains("Mango"));

        // subList
        System.out.println("\nSubList (index 1 to 3):");
        List<String> subList = fruits.subList(1, 4); // From index 1 (inclusive) to 4 (exclusive)
        System.out.println("SubList: " + subList);

        // Converting array to list
        System.out.println("\nConverting array to list:");
        String[] colorArray = { "Red", "Green", "Blue" };
        List<String> colorList = Arrays.asList(colorArray);
        System.out.println("Color list: " + colorList);

        // Creating list with initial values
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13);
        System.out.println("Prime numbers: " + primes);

        System.out.println();
    }

    /**
     * Compare ArrayList vs LinkedList performance characteristics
     */
    private static void demonstrateListComparison() {
        System.out.println("--- 4. ArrayList vs LinkedList ---");

        System.out.println("ArrayList:");
        System.out.println("  + Fast random access (get/set by index)");
        System.out.println("  + Less memory overhead");
        System.out.println("  - Slow insertion/deletion in middle");
        System.out.println("  - Resizing overhead when capacity exceeded");

        System.out.println("\nLinkedList:");
        System.out.println("  + Fast insertion/deletion at beginning/end");
        System.out.println("  + Fast insertion/deletion in middle (if iterator used)");
        System.out.println("  + No resizing needed");
        System.out.println("  - Slow random access");
        System.out.println("  - More memory overhead (node objects)");

        System.out.println("\nWhen to use:");
        System.out.println("ArrayList: Frequent access by index, rare insertions/deletions");
        System.out.println("LinkedList: Frequent insertions/deletions, rare random access");

        System.out.println();
    }

    /**
     * Demonstrate searching and sorting
     */
    private static void demonstrateListSearching() {
        System.out.println("--- 5. Searching and Sorting ---");

        List<Integer> scores = new ArrayList<>();
        scores.add(85);
        scores.add(92);
        scores.add(78);
        scores.add(95);
        scores.add(88);

        System.out.println("Original scores: " + scores);

        // Sorting
        System.out.println("\nSorting:");
        scores.sort(null); // Natural order
        System.out.println("Sorted (ascending): " + scores);

        scores.sort((a, b) -> b - a); // Descending order
        System.out.println("Sorted (descending): " + scores);

        // Binary search (requires sorted list)
        List<Integer> sortedNumbers = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50, 60, 70));
        System.out.println("\nBinary search in sorted list: " + sortedNumbers);
        int index = java.util.Collections.binarySearch(sortedNumbers, 40);
        System.out.println("Index of 40: " + index);

        // Finding min and max
        System.out.println("\nMin and max:");
        System.out.println("Min: " + java.util.Collections.min(scores));
        System.out.println("Max: " + java.util.Collections.max(scores));

        // Reversing
        System.out.println("\nReversing:");
        java.util.Collections.reverse(scores);
        System.out.println("Reversed: " + scores);

        System.out.println("\nExercise completed!");
    }
}
