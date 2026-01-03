package com.fundamentals.collections;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.NavigableSet;

/**
 * SortedSet and NavigableSet Interfaces
 * 
 * SortedSet is a Set that maintains elements in sorted order.
 * NavigableSet extends SortedSet with navigation methods.
 * 
 * Key Characteristics:
 * - Sorted: Elements are ordered (natural or custom comparator)
 * - No Duplicates: Like regular Set
 * - No Null: TreeSet doesn't allow null elements
 * 
 * Implementation:
 * - TreeSet: Red-Black tree implementation
 * 
 * SortedSet Methods:
 * - first(): Get first (lowest) element
 * - last(): Get last (highest) element
 * - headSet(E toElement): Elements less than toElement
 * - tailSet(E fromElement): Elements greater than or equal to fromElement
 * - subSet(E from, E to): Elements in range [from, to)
 * - comparator(): Get comparator (null if natural ordering)
 * 
 * NavigableSet Additional Methods:
 * - lower(E e): Greatest element less than e
 * - floor(E e): Greatest element less than or equal to e
 * - ceiling(E e): Least element greater than or equal to e
 * - higher(E e): Least element greater than e
 * - pollFirst(): Remove and return first element
 * - pollLast(): Remove and return last element
 * - descendingSet(): Reverse order view
 */
public class SortedSetOperations {

    public static void main(String[] args) {
        System.out.println("=== SortedSet and NavigableSet ===\n");

        demonstrateSortedSetBasics();
        demonstrateSortedSetRanges();
        demonstrateNavigableSetMethods();
        demonstrateCustomComparator();
        demonstrateDescendingOperations();
    }

    /**
     * Demonstrate SortedSet basics
     */
    private static void demonstrateSortedSetBasics() {
        System.out.println("--- 1. SortedSet Basics ---");
        System.out.println("SortedSet maintains elements in sorted order\n");

        SortedSet<Integer> scores = new TreeSet<>();

        // Adding elements in random order
        scores.add(85);
        scores.add(92);
        scores.add(78);
        scores.add(95);
        scores.add(88);
        scores.add(76);
        scores.add(90);

        System.out.println("Scores (sorted): " + scores);

        // First and last elements
        System.out.println("\nFirst and last:");
        System.out.println("Lowest score: " + scores.first());
        System.out.println("Highest score: " + scores.last());

        // Comparator
        System.out.println("\nComparator:");
        System.out.println("Comparator: " + scores.comparator());
        System.out.println("(null means natural ordering)");

        System.out.println();
    }

    /**
     * Demonstrate range operations
     */
    private static void demonstrateSortedSetRanges() {
        System.out.println("--- 2. Range Operations ---");

        SortedSet<String> words = new TreeSet<>();
        words.add("apple");
        words.add("banana");
        words.add("cherry");
        words.add("date");
        words.add("elderberry");
        words.add("fig");
        words.add("grape");
        words.add("honeydew");

        System.out.println("All words: " + words);

        // headSet - elements less than specified element
        System.out.println("\nheadSet (words before 'date'):");
        SortedSet<String> headSet = words.headSet("date");
        System.out.println("headSet('date'): " + headSet);

        // tailSet - elements greater than or equal to specified element
        System.out.println("\ntailSet (words from 'fig' onwards):");
        SortedSet<String> tailSet = words.tailSet("fig");
        System.out.println("tailSet('fig'): " + tailSet);

        // subSet - elements in range [from, to)
        System.out.println("\nsubSet (words from 'cherry' to 'grape'):");
        SortedSet<String> subSet = words.subSet("cherry", "grape");
        System.out.println("subSet('cherry', 'grape'): " + subSet);
        System.out.println("Note: 'grape' is excluded (exclusive upper bound)");

        System.out.println();
    }

    /**
     * Demonstrate NavigableSet methods
     */
    private static void demonstrateNavigableSetMethods() {
        System.out.println("--- 3. NavigableSet Methods ---");
        System.out.println("NavigableSet provides navigation methods\n");

        NavigableSet<Integer> numbers = new TreeSet<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);
        numbers.add(50);
        numbers.add(60);
        numbers.add(70);

        System.out.println("Numbers: " + numbers);

        // Navigation methods
        int target = 35;
        System.out.println("\nNavigation around " + target + ":");
        System.out.println("lower(" + target + "): " + numbers.lower(target) + " (greatest < " + target + ")");
        System.out.println("floor(" + target + "): " + numbers.floor(target) + " (greatest <= " + target + ")");
        System.out.println("ceiling(" + target + "): " + numbers.ceiling(target) + " (least >= " + target + ")");
        System.out.println("higher(" + target + "): " + numbers.higher(target) + " (least > " + target + ")");

        // Navigation with exact match
        target = 40;
        System.out.println("\nNavigation around " + target + " (exact match exists):");
        System.out.println("lower(" + target + "): " + numbers.lower(target));
        System.out.println("floor(" + target + "): " + numbers.floor(target));
        System.out.println("ceiling(" + target + "): " + numbers.ceiling(target));
        System.out.println("higher(" + target + "): " + numbers.higher(target));

        // Poll operations
        System.out.println("\nPoll operations:");
        System.out.println("Original: " + numbers);
        Integer first = numbers.pollFirst();
        Integer last = numbers.pollLast();
        System.out.println("pollFirst(): " + first);
        System.out.println("pollLast(): " + last);
        System.out.println("After polling: " + numbers);

        System.out.println();
    }

    /**
     * Demonstrate custom comparator
     */
    private static void demonstrateCustomComparator() {
        System.out.println("--- 4. Custom Comparator ---");
        System.out.println("Using custom ordering\n");

        // Natural order (ascending)
        SortedSet<String> naturalOrder = new TreeSet<>();
        naturalOrder.add("Alice");
        naturalOrder.add("Bob");
        naturalOrder.add("Charlie");
        naturalOrder.add("David");

        System.out.println("Natural order (alphabetical): " + naturalOrder);

        // Reverse order
        SortedSet<String> reverseOrder = new TreeSet<>((a, b) -> b.compareTo(a));
        reverseOrder.add("Alice");
        reverseOrder.add("Bob");
        reverseOrder.add("Charlie");
        reverseOrder.add("David");

        System.out.println("Reverse order: " + reverseOrder);

        // By length
        SortedSet<String> byLength = new TreeSet<>((a, b) -> {
            int lengthCompare = Integer.compare(a.length(), b.length());
            if (lengthCompare != 0)
                return lengthCompare;
            return a.compareTo(b); // If same length, use alphabetical
        });
        byLength.add("Java");
        byLength.add("C");
        byLength.add("Python");
        byLength.add("Go");
        byLength.add("JavaScript");

        System.out.println("By length: " + byLength);

        System.out.println();
    }

    /**
     * Demonstrate descending operations
     */
    private static void demonstrateDescendingOperations() {
        System.out.println("--- 5. Descending Operations ---");

        NavigableSet<Integer> numbers = new TreeSet<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);

        System.out.println("Original set: " + numbers);

        // Descending set (reverse view)
        NavigableSet<Integer> descending = numbers.descendingSet();
        System.out.println("Descending view: " + descending);

        // Descending iterator
        System.out.print("Descending iterator: [");
        java.util.Iterator<Integer> descIter = numbers.descendingIterator();
        while (descIter.hasNext()) {
            System.out.print(descIter.next());
            if (descIter.hasNext())
                System.out.print(", ");
        }
        System.out.println("]");

        // Modifications to descending view affect original
        System.out.println("\nAdding 6 to descending view:");
        descending.add(6);
        System.out.println("Original set: " + numbers);
        System.out.println("Descending view: " + descending);

        System.out.println("\nExercise completed!");
    }
}
