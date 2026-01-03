package com.fundamentals.collections;

import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map;
import java.util.NavigableMap;

/**
 * SortedMap and NavigableMap Interfaces
 * 
 * SortedMap is a Map that maintains keys in sorted order.
 * NavigableMap extends SortedMap with navigation methods.
 * 
 * Key Characteristics:
 * - Sorted Keys: Keys are ordered (natural or custom comparator)
 * - No Null Keys: TreeMap doesn't allow null keys
 * - Allows Null Values: Values can be null
 * 
 * Implementation:
 * - TreeMap: Red-Black tree implementation
 * 
 * SortedMap Methods:
 * - firstKey(): Get first (lowest) key
 * - lastKey(): Get last (highest) key
 * - headMap(K toKey): Map with keys less than toKey
 * - tailMap(K fromKey): Map with keys greater than or equal to fromKey
 * - subMap(K from, K to): Map with keys in range [from, to)
 * - comparator(): Get comparator
 * 
 * NavigableMap Additional Methods:
 * - lowerKey(K key): Greatest key less than key
 * - floorKey(K key): Greatest key less than or equal to key
 * - ceilingKey(K key): Least key greater than or equal to key
 * - higherKey(K key): Least key greater than key
 * - pollFirstEntry(): Remove and return first entry
 * - pollLastEntry(): Remove and return last entry
 * - descendingMap(): Reverse order view
 */
public class SortedMapOperations {

    public static void main(String[] args) {
        System.out.println("=== SortedMap and NavigableMap ===\n");

        demonstrateSortedMapBasics();
        demonstrateSortedMapRanges();
        demonstrateNavigableMapMethods();
        demonstrateCustomComparator();
        demonstrateDescendingOperations();
    }

    /**
     * Demonstrate SortedMap basics
     */
    private static void demonstrateSortedMapBasics() {
        System.out.println("--- 1. SortedMap Basics ---");
        System.out.println("SortedMap maintains keys in sorted order\n");

        SortedMap<Integer, String> rankings = new TreeMap<>();

        // Adding entries in random order
        rankings.put(3, "Bronze");
        rankings.put(1, "Gold");
        rankings.put(5, "Participant");
        rankings.put(2, "Silver");
        rankings.put(4, "Certificate");

        System.out.println("Rankings (sorted by rank): " + rankings);

        // First and last keys
        System.out.println("\nFirst and last:");
        System.out.println("First key (lowest rank): " + rankings.firstKey());
        System.out.println("Last key (highest rank): " + rankings.lastKey());

        // Getting values
        System.out.println("\nValues:");
        System.out.println("Rank 1: " + rankings.get(rankings.firstKey()));
        System.out.println("Rank 5: " + rankings.get(rankings.lastKey()));

        System.out.println();
    }

    /**
     * Demonstrate range operations
     */
    private static void demonstrateSortedMapRanges() {
        System.out.println("--- 2. Range Operations ---");

        SortedMap<String, Integer> phonebook = new TreeMap<>();
        phonebook.put("Alice", 1001);
        phonebook.put("Bob", 1002);
        phonebook.put("Charlie", 1003);
        phonebook.put("David", 1004);
        phonebook.put("Eve", 1005);
        phonebook.put("Frank", 1006);
        phonebook.put("Grace", 1007);

        System.out.println("Phonebook: " + phonebook);

        // headMap - entries with keys less than specified key
        System.out.println("\nheadMap (names before 'David'):");
        SortedMap<String, Integer> headMap = phonebook.headMap("David");
        System.out.println("headMap('David'): " + headMap);

        // tailMap - entries with keys greater than or equal to specified key
        System.out.println("\ntailMap (names from 'Eve' onwards):");
        SortedMap<String, Integer> tailMap = phonebook.tailMap("Eve");
        System.out.println("tailMap('Eve'): " + tailMap);

        // subMap - entries with keys in range [from, to)
        System.out.println("\nsubMap (names from 'Charlie' to 'Frank'):");
        SortedMap<String, Integer> subMap = phonebook.subMap("Charlie", "Frank");
        System.out.println("subMap('Charlie', 'Frank'): " + subMap);
        System.out.println("Note: 'Frank' is excluded");

        System.out.println();
    }

    /**
     * Demonstrate NavigableMap methods
     */
    private static void demonstrateNavigableMapMethods() {
        System.out.println("--- 3. NavigableMap Methods ---");
        System.out.println("NavigableMap provides navigation methods\n");

        NavigableMap<Integer, String> menu = new TreeMap<>();
        menu.put(10, "Starter");
        menu.put(20, "Soup");
        menu.put(30, "Salad");
        menu.put(40, "Main Course");
        menu.put(50, "Dessert");
        menu.put(60, "Coffee");

        System.out.println("Menu: " + menu);

        // Navigation methods
        int target = 35;
        System.out.println("\nNavigation around key " + target + ":");
        System.out.println("lowerKey(" + target + "): " + menu.lowerKey(target));
        System.out.println("floorKey(" + target + "): " + menu.floorKey(target));
        System.out.println("ceilingKey(" + target + "): " + menu.ceilingKey(target));
        System.out.println("higherKey(" + target + "): " + menu.higherKey(target));

        // Entry navigation
        System.out.println("\nEntry navigation:");
        System.out.println("lowerEntry(" + target + "): " + menu.lowerEntry(target));
        System.out.println("floorEntry(" + target + "): " + menu.floorEntry(target));
        System.out.println("ceilingEntry(" + target + "): " + menu.ceilingEntry(target));
        System.out.println("higherEntry(" + target + "): " + menu.higherEntry(target));

        // First and last entries
        System.out.println("\nFirst and last entries:");
        System.out.println("firstEntry(): " + menu.firstEntry());
        System.out.println("lastEntry(): " + menu.lastEntry());

        // Poll operations
        System.out.println("\nPoll operations:");
        NavigableMap<Integer, String> menuCopy = new TreeMap<>(menu);
        Map.Entry<Integer, String> first = menuCopy.pollFirstEntry();
        Map.Entry<Integer, String> last = menuCopy.pollLastEntry();
        System.out.println("pollFirstEntry(): " + first);
        System.out.println("pollLastEntry(): " + last);
        System.out.println("After polling: " + menuCopy);

        System.out.println();
    }

    /**
     * Demonstrate custom comparator
     */
    private static void demonstrateCustomComparator() {
        System.out.println("--- 4. Custom Comparator ---");

        // Natural order (ascending)
        SortedMap<String, Integer> naturalOrder = new TreeMap<>();
        naturalOrder.put("Zebra", 1);
        naturalOrder.put("Apple", 2);
        naturalOrder.put("Mango", 3);
        naturalOrder.put("Banana", 4);

        System.out.println("Natural order (alphabetical): " + naturalOrder);

        // Reverse order
        SortedMap<String, Integer> reverseOrder = new TreeMap<>((a, b) -> b.compareTo(a));
        reverseOrder.put("Zebra", 1);
        reverseOrder.put("Apple", 2);
        reverseOrder.put("Mango", 3);
        reverseOrder.put("Banana", 4);

        System.out.println("Reverse order: " + reverseOrder);

        // By string length
        SortedMap<String, Integer> byLength = new TreeMap<>((a, b) -> {
            int lengthCompare = Integer.compare(a.length(), b.length());
            if (lengthCompare != 0)
                return lengthCompare;
            return a.compareTo(b);
        });
        byLength.put("Java", 1);
        byLength.put("C", 2);
        byLength.put("Python", 3);
        byLength.put("Go", 4);
        byLength.put("JavaScript", 5);

        System.out.println("By key length: " + byLength);

        System.out.println();
    }

    /**
     * Demonstrate descending operations
     */
    private static void demonstrateDescendingOperations() {
        System.out.println("--- 5. Descending Operations ---");

        NavigableMap<Integer, String> months = new TreeMap<>();
        months.put(1, "January");
        months.put(2, "February");
        months.put(3, "March");
        months.put(4, "April");
        months.put(5, "May");

        System.out.println("Original map: " + months);

        // Descending map (reverse view)
        NavigableMap<Integer, String> descending = months.descendingMap();
        System.out.println("Descending view: " + descending);

        // Descending key set
        System.out.println("\nDescending key set: " + months.descendingKeySet());

        // Modifications to descending view affect original
        System.out.println("\nAdding June (6) to descending view:");
        descending.put(6, "June");
        System.out.println("Original map: " + months);
        System.out.println("Descending view: " + descending);

        // Iterating in reverse order
        System.out.println("\nIterating in reverse order:");
        for (Map.Entry<Integer, String> entry : descending.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }

        System.out.println("\nExercise completed!");
    }
}
