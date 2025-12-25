package com.fundamentals.arrays;

import java.util.Arrays;

/**
 * Arrays Utility Class Methods
 * 
 * The java.util.Arrays class provides static methods for array manipulation:
 * - Sorting: sort(), parallelSort()
 * - Searching: binarySearch()
 * - Comparing: equals(), compare(), mismatch()
 * - Copying: copyOf(), copyOfRange()
 * - Filling: fill(), setAll()
 * - Converting: toString(), deepToString(), asList()
 * - Streaming: stream()
 * 
 * All methods are static - call using Arrays.methodName()
 * 
 * Performance Notes:
 * - sort() uses dual-pivot quicksort: O(n log n)
 * - parallelSort() uses parallel merge sort for large arrays
 * - binarySearch() requires sorted array: O(log n)
 */
public class ArraysUtility {

    public static void main(String[] args) {
        System.out.println("=== Arrays Utility Class ===\n");

        demonstrateSorting();
        demonstrateSearching();
        demonstrateComparing();
        demonstrateCopying();
        demonstrateFilling();
        demonstrateConverting();
        demonstrateStreaming();
        demonstrateAdvancedFeatures();
    }

    /**
     * Sorting methods
     */
    private static void demonstrateSorting() {
        System.out.println("--- Sorting Methods ---");

        // Basic sort
        int[] numbers = { 5, 2, 8, 1, 9, 3, 7 };
        System.out.println("Original: " + Arrays.toString(numbers));

        Arrays.sort(numbers);
        System.out.println("Sorted: " + Arrays.toString(numbers));

        // Sort range
        int[] partial = { 9, 5, 2, 8, 1, 3, 7 };
        Arrays.sort(partial, 1, 5); // Sort index 1-4 (inclusive-exclusive)
        System.out.println("Partial sort (1-4): " + Arrays.toString(partial));

        // Sort different types
        String[] names = { "Charlie", "Alice", "Bob", "David" };
        Arrays.sort(names);
        System.out.println("Sorted strings: " + Arrays.toString(names));

        double[] decimals = { 3.14, 1.41, 2.71, 0.57 };
        Arrays.sort(decimals);
        System.out.println("Sorted doubles: " + Arrays.toString(decimals));

        // Parallel sort (for large arrays)
        int[] large = { 9, 5, 2, 8, 1, 3, 7, 4, 6 };
        Arrays.parallelSort(large);
        System.out.println("Parallel sorted: " + Arrays.toString(large));

        System.out.println("\nNote: parallelSort() is faster for large arrays (>8192 elements)");
    }

    /**
     * Searching methods
     */
    private static void demonstrateSearching() {
        System.out.println("\n--- Searching Methods ---");

        int[] sorted = { 1, 2, 3, 5, 7, 8, 9 };
        System.out.println("Sorted array: " + Arrays.toString(sorted));

        // Binary search - element found
        int index = Arrays.binarySearch(sorted, 5);
        System.out.println("binarySearch(5): " + index);

        // Binary search - element not found
        int notFound = Arrays.binarySearch(sorted, 4);
        System.out.println("binarySearch(4): " + notFound);
        System.out.println("Negative value = -(insertion point) - 1");

        if (notFound < 0) {
            int insertionPoint = -(notFound + 1);
            System.out.println("Would be inserted at index: " + insertionPoint);
        }

        // Binary search on range
        int rangeSearch = Arrays.binarySearch(sorted, 2, 6, 7);
        System.out.println("binarySearch in range [2,6) for 7: " + rangeSearch);

        // IMPORTANT: Array must be sorted for binarySearch
        int[] unsorted = { 5, 2, 8, 1, 9 };
        int wrong = Arrays.binarySearch(unsorted, 8);
        System.out.println("\nWARNING: binarySearch on unsorted array: " + wrong);
        System.out.println("Result is undefined - always sort first!");
    }

    /**
     * Comparing methods
     */
    private static void demonstrateComparing() {
        System.out.println("\n--- Comparing Methods ---");

        int[] arr1 = { 1, 2, 3, 4, 5 };
        int[] arr2 = { 1, 2, 3, 4, 5 };
        int[] arr3 = { 1, 2, 3, 4, 6 };
        int[] arr4 = { 1, 2, 3 };

        // equals() - check if arrays are equal
        System.out.println("equals(arr1, arr2): " + Arrays.equals(arr1, arr2)); // true
        System.out.println("equals(arr1, arr3): " + Arrays.equals(arr1, arr3)); // false

        // compare() - lexicographic comparison
        int result1 = Arrays.compare(arr1, arr2);
        int result2 = Arrays.compare(arr1, arr3);
        int result3 = Arrays.compare(arr1, arr4);

        System.out.println("\ncompare(arr1, arr2): " + result1); // 0 (equal)
        System.out.println("compare(arr1, arr3): " + result2); // negative (arr1 < arr3)
        System.out.println("compare(arr1, arr4): " + result3); // positive (arr1 > arr4)

        // mismatch() - find first mismatch index
        int mismatchIndex = Arrays.mismatch(arr1, arr3);
        System.out.println("\nmismatch(arr1, arr3): " + mismatchIndex); // 4
        System.out.println("First difference at index 4");

        // deepEquals() for multidimensional arrays
        int[][] matrix1 = { { 1, 2 }, { 3, 4 } };
        int[][] matrix2 = { { 1, 2 }, { 3, 4 } };
        System.out.println("\ndeepEquals(matrix1, matrix2): " + Arrays.deepEquals(matrix1, matrix2));
    }

    /**
     * Copying methods
     */
    private static void demonstrateCopying() {
        System.out.println("\n--- Copying Methods ---");

        int[] original = { 1, 2, 3, 4, 5 };

        // copyOf() - copy entire array
        int[] copy1 = Arrays.copyOf(original, original.length);
        System.out.println("copyOf (same size): " + Arrays.toString(copy1));

        // copyOf() - larger size (pad with 0)
        int[] copy2 = Arrays.copyOf(original, 8);
        System.out.println("copyOf (larger): " + Arrays.toString(copy2));

        // copyOf() - smaller size (truncate)
        int[] copy3 = Arrays.copyOf(original, 3);
        System.out.println("copyOf (smaller): " + Arrays.toString(copy3));

        // copyOfRange() - copy specific range
        int[] range = Arrays.copyOfRange(original, 1, 4); // Index 1-3
        System.out.println("copyOfRange(1, 4): " + Arrays.toString(range));

        // Copy String array to Object array (valid for reference types)
        String[] strings = { "A", "B", "C" };
        Object[] objectArray = Arrays.copyOf(strings, strings.length, Object[].class);
        System.out.println("Copy String[] to Object[]: " + Arrays.toString(objectArray));
    }

    /**
     * Filling methods
     */
    private static void demonstrateFilling() {
        System.out.println("\n--- Filling Methods ---");

        // fill() - fill entire array
        int[] arr1 = new int[5];
        Arrays.fill(arr1, 7);
        System.out.println("fill(7): " + Arrays.toString(arr1));

        // fill() - fill range
        int[] arr2 = { 1, 2, 3, 4, 5, 6, 7, 8 };
        Arrays.fill(arr2, 2, 6, 0); // Fill index 2-5 with 0
        System.out.println("fill(2, 6, 0): " + Arrays.toString(arr2));

        // setAll() - fill using generator function
        int[] arr3 = new int[5];
        Arrays.setAll(arr3, i -> i * 2); // Fill with index * 2
        System.out.println("setAll(i -> i*2): " + Arrays.toString(arr3));

        // setAll() - fill with squares
        int[] arr4 = new int[5];
        Arrays.setAll(arr4, i -> i * i);
        System.out.println("setAll(i -> i*i): " + Arrays.toString(arr4));

        // parallelSetAll() - parallel version
        int[] arr5 = new int[10];
        Arrays.parallelSetAll(arr5, i -> i + 1);
        System.out.println("parallelSetAll(i -> i+1): " + Arrays.toString(arr5));
    }

    /**
     * Converting methods
     */
    private static void demonstrateConverting() {
        System.out.println("\n--- Converting Methods ---");

        int[] numbers = { 1, 2, 3, 4, 5 };

        // toString() - convert to string
        String str = Arrays.toString(numbers);
        System.out.println("toString(): " + str);

        // deepToString() - for multidimensional arrays
        int[][] matrix = { { 1, 2 }, { 3, 4 } };
        String deepStr = Arrays.deepToString(matrix);
        System.out.println("deepToString(): " + deepStr);

        // asList() - convert to List (fixed size)
        String[] fruits = { "Apple", "Banana", "Cherry" };
        java.util.List<String> list = Arrays.asList(fruits);
        System.out.println("asList(): " + list);
        System.out.println("Note: Returned list is fixed-size");

        // hashCode() - compute hash code
        int hash = Arrays.hashCode(numbers);
        System.out.println("\nhashCode(): " + hash);

        // deepHashCode() - for multidimensional
        int deepHash = Arrays.deepHashCode(matrix);
        System.out.println("deepHashCode(): " + deepHash);
    }

    /**
     * Streaming methods (Java 8+)
     */
    private static void demonstrateStreaming() {
        System.out.println("\n--- Streaming Methods ---");

        int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

        // stream() - create stream from array
        long count = Arrays.stream(numbers)
                .filter(n -> n % 2 == 0)
                .count();
        System.out.println("Count of even numbers: " + count);

        // Sum using stream
        int sum = Arrays.stream(numbers).sum();
        System.out.println("Sum: " + sum);

        // Average
        double avg = Arrays.stream(numbers).average().orElse(0.0);
        System.out.println("Average: " + avg);

        // Max
        int max = Arrays.stream(numbers).max().orElse(0);
        System.out.println("Max: " + max);

        // Filter and collect
        int[] evens = Arrays.stream(numbers)
                .filter(n -> n % 2 == 0)
                .toArray();
        System.out.println("Even numbers: " + Arrays.toString(evens));

        // Map operation
        int[] doubled = Arrays.stream(numbers)
                .map(n -> n * 2)
                .toArray();
        System.out.println("Doubled: " + Arrays.toString(doubled));

        // Stream range
        int rangeSum = Arrays.stream(numbers, 2, 7).sum(); // Index 2-6
        System.out.println("Sum of range [2,7): " + rangeSum);
    }

    /**
     * Advanced features
     */
    private static void demonstrateAdvancedFeatures() {
        System.out.println("\n--- Advanced Features ---");

        // parallelPrefix() - cumulative operation
        int[] arr1 = { 1, 2, 3, 4, 5 };
        Arrays.parallelPrefix(arr1, (a, b) -> a + b); // Cumulative sum
        System.out.println("parallelPrefix (cumulative sum): " + Arrays.toString(arr1));

        // parallelPrefix() - cumulative product
        int[] arr2 = { 1, 2, 3, 4, 5 };
        Arrays.parallelPrefix(arr2, (a, b) -> a * b);
        System.out.println("parallelPrefix (cumulative product): " + Arrays.toString(arr2));

        // spliterator() - for parallel processing
        String[] words = { "Java", "Python", "C++", "Go", "Rust" };
        java.util.Spliterator<String> spliterator = Arrays.spliterator(words);
        System.out.println("\nSpliterator characteristics: " + spliterator.characteristics());

        // Compare with custom comparator
        String[] names = { "Alice", "bob", "Charlie", "david" };
        Arrays.sort(names, String.CASE_INSENSITIVE_ORDER);
        System.out.println("\nCase-insensitive sort: " + Arrays.toString(names));

        // Sort in reverse order
        Integer[] nums = { 5, 2, 8, 1, 9 };
        Arrays.sort(nums, java.util.Collections.reverseOrder());
        System.out.println("Reverse sort: " + Arrays.toString(nums));

        // BEST PRACTICES
        System.out.println("\n--- Best Practices ---");
        System.out.println("1. Use Arrays.toString() for debugging");
        System.out.println("2. Use Arrays.equals() to compare arrays");
        System.out.println("3. Use Arrays.copyOf() instead of manual copying");
        System.out.println("4. Use Arrays.fill() to initialize with same value");
        System.out.println("5. Use Arrays.stream() for functional operations");
        System.out.println("6. Use parallelSort() for large arrays (>8192 elements)");
        System.out.println("7. Always sort before using binarySearch()");

        System.out.println("\nExercise completed!");
    }
}
