package com.fundamentals.collections;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Hashtable;

/**
 * Map Interface and Implementations
 * 
 * Map is an object that maps keys to values.
 * A map cannot contain duplicate keys; each key can map to at most one value.
 * 
 * Key Characteristics:
 * - Key-Value Pairs: Stores associations between keys and values
 * - Unique Keys: No duplicate keys allowed
 * - One Value Per Key: Each key maps to exactly one value
 * - Not a Collection: Map is separate from Collection hierarchy
 * 
 * Common Implementations:
 * 1. HashMap - Fastest, no order, allows one null key
 * 2. LinkedHashMap - Maintains insertion order
 * 3. TreeMap - Sorted by keys (implements SortedMap)
 * 4. Hashtable - Legacy, synchronized, no null keys/values
 * 
 * Map Methods:
 * - put(K key, V value): Add/update key-value pair
 * - get(Object key): Get value for key
 * - remove(Object key): Remove key-value pair
 * - containsKey(Object key): Check if key exists
 * - containsValue(Object value): Check if value exists
 * - keySet(): Get set of all keys
 * - values(): Get collection of all values
 * - entrySet(): Get set of key-value pairs
 * - size(): Get number of entries
 * - isEmpty(): Check if map is empty
 * - clear(): Remove all entries
 */
public class MapOperations {

    public static void main(String[] args) {
        System.out.println("=== Map Interface and Implementations ===\n");

        demonstrateHashMap();
        demonstrateLinkedHashMap();
        demonstrateTreeMap();
        demonstrateMapMethods();
        demonstrateMapIteration();
    }

    /**
     * Demonstrate HashMap operations
     */
    private static void demonstrateHashMap() {
        System.out.println("--- 1. HashMap Operations ---");
        System.out.println("HashMap: Fastest map, no order guarantee\n");

        Map<String, Integer> population = new HashMap<>();

        // Adding key-value pairs
        population.put("Istanbul", 15840900);
        population.put("Ankara", 5663322);
        population.put("Izmir", 4425789);
        population.put("Bursa", 3147818);
        population.put("Antalya", 2619832);

        System.out.println("City populations: " + population);
        System.out.println("Note: Order is not guaranteed");

        // Getting values
        System.out.println("\nGetting values:");
        System.out.println("Population of Ankara: " + population.get("Ankara"));
        System.out.println("Population of Konya: " + population.get("Konya")); // null if not found

        // Updating value
        System.out.println("\nUpdating Istanbul's population:");
        Integer oldValue = population.put("Istanbul", 16000000);
        System.out.println("Old value: " + oldValue);
        System.out.println("New value: " + population.get("Istanbul"));

        // Checking existence
        System.out.println("\nChecking existence:");
        System.out.println("Contains key 'Izmir'? " + population.containsKey("Izmir"));
        System.out.println("Contains value 5663322? " + population.containsValue(5663322));

        // Removing entry
        System.out.println("\nRemoving Bursa:");
        Integer removed = population.remove("Bursa");
        System.out.println("Removed value: " + removed);
        System.out.println("After removal: " + population);

        System.out.println();
    }

    /**
     * Demonstrate LinkedHashMap operations
     */
    private static void demonstrateLinkedHashMap() {
        System.out.println("--- 2. LinkedHashMap Operations ---");
        System.out.println("LinkedHashMap: Maintains insertion order\n");

        Map<String, String> capitals = new LinkedHashMap<>();

        // Adding entries
        capitals.put("Turkey", "Ankara");
        capitals.put("France", "Paris");
        capitals.put("Germany", "Berlin");
        capitals.put("Italy", "Rome");
        capitals.put("Spain", "Madrid");

        System.out.println("Capitals: " + capitals);
        System.out.println("Note: Insertion order is maintained");

        // Adding more entries
        System.out.println("\nAdding Japan and Brazil:");
        capitals.put("Japan", "Tokyo");
        capitals.put("Brazil", "Brasilia");
        System.out.println("After addition: " + capitals);
        System.out.println("New entries appear at the end");

        // Updating existing key
        System.out.println("\nUpdating Turkey's capital:");
        capitals.put("Turkey", "Istanbul"); // Update
        System.out.println("After update: " + capitals);
        System.out.println("Turkey stays in original position");

        System.out.println();
    }

    /**
     * Demonstrate TreeMap operations
     */
    private static void demonstrateTreeMap() {
        System.out.println("--- 3. TreeMap Operations ---");
        System.out.println("TreeMap: Keys in sorted order\n");

        Map<Integer, String> grades = new TreeMap<>();

        // Adding entries in random order
        grades.put(85, "B");
        grades.put(95, "A");
        grades.put(75, "C");
        grades.put(65, "D");
        grades.put(90, "A");

        System.out.println("Grades (sorted by score): " + grades);

        // TreeMap with string keys (alphabetical order)
        Map<String, Integer> scores = new TreeMap<>();
        scores.put("Charlie", 85);
        scores.put("Alice", 92);
        scores.put("David", 78);
        scores.put("Bob", 88);

        System.out.println("\nScores (sorted by name): " + scores);

        System.out.println();
    }

    /**
     * Demonstrate Map methods
     */
    private static void demonstrateMapMethods() {
        System.out.println("--- 4. Map Methods ---");

        Map<String, Double> prices = new HashMap<>();
        prices.put("Laptop", 999.99);
        prices.put("Mouse", 29.99);
        prices.put("Keyboard", 79.99);
        prices.put("Monitor", 299.99);

        System.out.println("Prices: " + prices);

        // getOrDefault
        System.out.println("\ngetOrDefault:");
        System.out.println("Price of Mouse: " + prices.getOrDefault("Mouse", 0.0));
        System.out.println("Price of Tablet: " + prices.getOrDefault("Tablet", 0.0));

        // putIfAbsent
        System.out.println("\nputIfAbsent:");
        prices.putIfAbsent("Headphones", 49.99);
        prices.putIfAbsent("Mouse", 19.99); // Won't update (key exists)
        System.out.println("After putIfAbsent: " + prices);

        // replace
        System.out.println("\nreplace:");
        prices.replace("Laptop", 899.99);
        System.out.println("After replace: " + prices);

        // compute
        System.out.println("\ncompute (apply 10% discount to Monitor):");
        prices.compute("Monitor", (key, value) -> value * 0.9);
        System.out.println("After compute: " + prices);

        // merge
        System.out.println("\nmerge (add 50 to Keyboard price):");
        prices.merge("Keyboard", 50.0, (oldVal, newVal) -> oldVal + newVal);
        System.out.println("After merge: " + prices);

        System.out.println();
    }

    /**
     * Demonstrate map iteration
     */
    private static void demonstrateMapIteration() {
        System.out.println("--- 5. Map Iteration ---");

        Map<String, Integer> ages = new HashMap<>();
        ages.put("Alice", 25);
        ages.put("Bob", 30);
        ages.put("Charlie", 28);
        ages.put("David", 35);

        // Method 1: Iterating over keys
        System.out.println("Method 1: Iterating over keys");
        for (String name : ages.keySet()) {
            System.out.println("  " + name + " -> " + ages.get(name));
        }

        // Method 2: Iterating over values
        System.out.println("\nMethod 2: Iterating over values");
        for (Integer age : ages.values()) {
            System.out.println("  Age: " + age);
        }

        // Method 3: Iterating over entries
        System.out.println("\nMethod 3: Iterating over entries");
        for (Map.Entry<String, Integer> entry : ages.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }

        // Method 4: forEach with lambda
        System.out.println("\nMethod 4: forEach with lambda");
        ages.forEach((name, age) -> System.out.println("  " + name + " is " + age + " years old"));

        // Method 5: Stream API
        System.out.println("\nMethod 5: Stream API (ages > 28)");
        ages.entrySet().stream()
                .filter(entry -> entry.getValue() > 28)
                .forEach(entry -> System.out.println("  " + entry.getKey() + " -> " + entry.getValue()));

        System.out.println("\nExercise completed!");
    }
}
