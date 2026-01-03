package com.fundamentals.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Iterator and ListIterator
 * 
 * Iterator is an interface that provides a way to traverse through a
 * collection.
 * ListIterator extends Iterator and provides bidirectional traversal for Lists.
 * 
 * Iterator:
 * - Unidirectional: Only forward traversal
 * - Works with: All Collection types
 * - Methods:
 * - hasNext(): Returns true if more elements exist
 * - next(): Returns next element
 * - remove(): Removes last returned element (optional)
 * 
 * ListIterator:
 * - Bidirectional: Forward and backward traversal
 * - Works with: Only List types
 * - Additional Methods:
 * - hasPrevious(): Returns true if previous element exists
 * - previous(): Returns previous element
 * - nextIndex(): Returns index of next element
 * - previousIndex(): Returns index of previous element
 * - set(E e): Replaces last returned element
 * - add(E e): Adds element at current position
 * 
 * Why use Iterator?
 * - Safe removal during iteration (vs ConcurrentModificationException)
 * - Uniform way to traverse any collection
 * - Lazy evaluation (elements fetched one at a time)
 */
public class IteratorOperations {

    public static void main(String[] args) {
        System.out.println("=== Iterator and ListIterator ===\n");

        demonstrateBasicIterator();
        demonstrateIteratorRemoval();
        demonstrateListIterator();
        demonstrateBidirectionalTraversal();
        demonstrateListIteratorModification();
        demonstrateConcurrentModificationIssue();
    }

    /**
     * Demonstrate basic iterator usage
     */
    private static void demonstrateBasicIterator() {
        System.out.println("--- 1. Basic Iterator Usage ---");
        System.out.println("Iterator provides forward-only traversal\n");

        List<String> colors = new ArrayList<>();
        colors.add("Red");
        colors.add("Green");
        colors.add("Blue");
        colors.add("Yellow");

        System.out.println("List: " + colors);

        // Getting iterator
        Iterator<String> iterator = colors.iterator();

        // Traversing with iterator
        System.out.println("\nTraversing with iterator:");
        while (iterator.hasNext()) {
            String color = iterator.next();
            System.out.println("  " + color);
        }

        // Iterator is exhausted now
        System.out.println("\nAfter traversal:");
        System.out.println("hasNext(): " + iterator.hasNext());
        System.out.println("(Iterator is exhausted, need new iterator to traverse again)");

        System.out.println();
    }

    /**
     * Demonstrate safe removal using iterator
     */
    private static void demonstrateIteratorRemoval() {
        System.out.println("--- 2. Safe Removal with Iterator ---");
        System.out.println("Iterator.remove() allows safe removal during iteration\n");

        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            numbers.add(i);
        }

        System.out.println("Original list: " + numbers);

        // Remove even numbers using iterator
        System.out.println("\nRemoving even numbers:");
        Iterator<Integer> iterator = numbers.iterator();
        while (iterator.hasNext()) {
            Integer num = iterator.next();
            if (num % 2 == 0) {
                iterator.remove(); // Safe removal
                System.out.println("  Removed: " + num);
            }
        }

        System.out.println("\nAfter removal: " + numbers);

        System.out.println();
    }

    /**
     * Demonstrate ListIterator basics
     */
    private static void demonstrateListIterator() {
        System.out.println("--- 3. ListIterator Basics ---");
        System.out.println("ListIterator works only with Lists, provides more features\n");

        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        fruits.add("Date");

        System.out.println("List: " + fruits);

        // Getting ListIterator
        ListIterator<String> listIterator = fruits.listIterator();

        // Forward traversal with index info
        System.out.println("\nForward traversal with indices:");
        while (listIterator.hasNext()) {
            int nextIndex = listIterator.nextIndex();
            String fruit = listIterator.next();
            System.out.println("  Index " + nextIndex + ": " + fruit);
        }

        // Starting from specific index
        System.out.println("\nStarting from index 2:");
        ListIterator<String> fromIndex = fruits.listIterator(2);
        while (fromIndex.hasNext()) {
            System.out.println("  " + fromIndex.next());
        }

        System.out.println();
    }

    /**
     * Demonstrate bidirectional traversal
     */
    private static void demonstrateBidirectionalTraversal() {
        System.out.println("--- 4. Bidirectional Traversal ---");
        System.out.println("ListIterator can traverse both forward and backward\n");

        List<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);
        numbers.add(50);

        System.out.println("List: " + numbers);

        ListIterator<Integer> listIterator = numbers.listIterator();

        // Forward traversal
        System.out.println("\nForward traversal:");
        while (listIterator.hasNext()) {
            System.out.print(listIterator.next() + " ");
        }
        System.out.println();

        // Now iterator is at the end, go backward
        System.out.println("\nBackward traversal:");
        while (listIterator.hasPrevious()) {
            System.out.print(listIterator.previous() + " ");
        }
        System.out.println();

        // Skip traversal (every other element)
        System.out.println("\nSkip traversal (every other element):");
        listIterator = numbers.listIterator();
        while (listIterator.hasNext()) {
            System.out.print(listIterator.next() + " ");
            if (listIterator.hasNext()) {
                listIterator.next(); // Skip one element
            }
        }
        System.out.println();

        System.out.println();
    }

    /**
     * Demonstrate ListIterator modification
     */
    private static void demonstrateListIteratorModification() {
        System.out.println("--- 5. ListIterator Modification ---");
        System.out.println("ListIterator can add and set elements during iteration\n");

        List<String> letters = new LinkedList<>();
        letters.add("A");
        letters.add("B");
        letters.add("C");
        letters.add("D");

        System.out.println("Original list: " + letters);

        // set() - Replace element
        System.out.println("\nUsing set() to replace 'B' with 'BB':");
        ListIterator<String> iterator = letters.listIterator();
        while (iterator.hasNext()) {
            String letter = iterator.next();
            if (letter.equals("B")) {
                iterator.set("BB"); // Replace current element
            }
        }
        System.out.println("After set(): " + letters);

        // add() - Add element at current position
        System.out.println("\nUsing add() to insert 'X' after 'BB':");
        iterator = letters.listIterator();
        while (iterator.hasNext()) {
            String letter = iterator.next();
            if (letter.equals("BB")) {
                iterator.add("X"); // Add after current element
            }
        }
        System.out.println("After add(): " + letters);

        System.out.println();
    }

    /**
     * Demonstrate concurrent modification issue
     */
    private static void demonstrateConcurrentModificationIssue() {
        System.out.println("--- 6. Concurrent Modification Issue ---");
        System.out.println("Modifying collection directly during iteration causes exception\n");

        List<String> items = new ArrayList<>();
        items.add("Item1");
        items.add("Item2");
        items.add("Item3");

        System.out.println("Original list: " + items);

        // WRONG: Modifying collection directly
        System.out.println("\nWRONG way (causes ConcurrentModificationException):");
        System.out.println("  for (String item : items) {");
        System.out.println("      items.remove(item); // EXCEPTION!");
        System.out.println("  }");

        try {
            List<String> testList = new ArrayList<>(items);
            for (String item : testList) {
                testList.remove(item);
            }
        } catch (java.util.ConcurrentModificationException e) {
            System.out.println("  Result: " + e.getClass().getSimpleName());
        }

        // RIGHT: Using iterator.remove()
        System.out.println("\nRIGHT way (using iterator.remove()):");
        System.out.println("  Iterator<String> iter = items.iterator();");
        System.out.println("  while (iter.hasNext()) {");
        System.out.println("      iter.next();");
        System.out.println("      iter.remove(); // SAFE!");
        System.out.println("  }");

        Iterator<String> iter = items.iterator();
        while (iter.hasNext()) {
            iter.next();
            iter.remove();
        }
        System.out.println("  Result: " + items + " (successfully cleared)");

        System.out.println("\nExercise completed!");
    }
}
