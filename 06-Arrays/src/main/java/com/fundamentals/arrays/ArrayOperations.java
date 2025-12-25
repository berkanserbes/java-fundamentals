package com.fundamentals.arrays;

import java.util.Arrays;

/**
 * Array Operations and Algorithms
 * 
 * Common array operations:
 * - Searching (linear, binary)
 * - Sorting (built-in and custom)
 * - Copying and cloning
 * - Comparing
 * - Reversing
 * - Rotating
 * - Finding min/max
 * - Sum and average
 * 
 * Performance Considerations:
 * - Linear search: O(n)
 * - Binary search: O(log n) - requires sorted array
 * - Sorting: O(n log n) - Arrays.sort uses dual-pivot quicksort
 */
public class ArrayOperations {

    public static void main(String[] args) {
        System.out.println("=== Array Operations ===\n");

        demonstrateSearching();
        demonstrateSorting();
        demonstrateCopying();
        demonstrateComparing();
        demonstrateReversing();
        demonstrateRotating();
        demonstrateMathOperations();
        demonstrateAdvancedOperations();
    }

    /**
     * Searching in arrays
     */
    private static void demonstrateSearching() {
        System.out.println("--- Searching ---");

        int[] numbers = { 5, 2, 8, 1, 9, 3, 7 };

        // Linear search
        int target = 8;
        int index = linearSearch(numbers, target);
        System.out.println("Linear search for " + target + ": index " + index);

        // Binary search (requires sorted array)
        int[] sorted = { 1, 2, 3, 5, 7, 8, 9 };
        int binaryIndex = Arrays.binarySearch(sorted, 5);
        System.out.println("Binary search for 5: index " + binaryIndex);

        // Binary search - not found
        int notFound = Arrays.binarySearch(sorted, 4);
        System.out.println("Binary search for 4 (not found): " + notFound);
        System.out.println("Negative value means not found, -(insertion point) - 1");

        // Contains check
        boolean contains = contains(numbers, 9);
        System.out.println("Array contains 9: " + contains);

        // Find first occurrence
        int[] duplicates = { 1, 2, 3, 2, 4, 2, 5 };
        int firstIndex = findFirst(duplicates, 2);
        System.out.println("First occurrence of 2: index " + firstIndex);

        // Find last occurrence
        int lastIndex = findLast(duplicates, 2);
        System.out.println("Last occurrence of 2: index " + lastIndex);
    }

    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1; // Not found
    }

    public static boolean contains(int[] arr, int target) {
        return linearSearch(arr, target) != -1;
    }

    public static int findFirst(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static int findLast(int[] arr, int target) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Sorting arrays
     */
    private static void demonstrateSorting() {
        System.out.println("\n--- Sorting ---");

        // Arrays.sort() - ascending order
        int[] numbers = { 5, 2, 8, 1, 9, 3, 7 };
        System.out.println("Original: " + Arrays.toString(numbers));

        Arrays.sort(numbers);
        System.out.println("Sorted (ascending): " + Arrays.toString(numbers));

        // Sort range
        int[] partial = { 9, 5, 2, 8, 1, 3, 7 };
        Arrays.sort(partial, 1, 5); // Sort index 1-4
        System.out.println("Partial sort (index 1-4): " + Arrays.toString(partial));

        // Sort strings
        String[] names = { "Charlie", "Alice", "Bob", "David" };
        Arrays.sort(names);
        System.out.println("Sorted strings: " + Arrays.toString(names));

        // Custom sorting (descending) - using bubble sort
        int[] descending = { 5, 2, 8, 1, 9 };
        bubbleSortDescending(descending);
        System.out.println("Bubble sort (descending): " + Arrays.toString(descending));

        // Check if sorted
        int[] sorted = { 1, 2, 3, 4, 5 };
        int[] unsorted = { 1, 3, 2, 4, 5 };
        System.out.println("Is [1,2,3,4,5] sorted: " + isSorted(sorted));
        System.out.println("Is [1,3,2,4,5] sorted: " + isSorted(unsorted));
    }

    public static void bubbleSortDescending(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] < arr[j + 1]) {
                    // Swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Copying arrays
     */
    private static void demonstrateCopying() {
        System.out.println("\n--- Copying Arrays ---");

        int[] original = { 1, 2, 3, 4, 5 };

        // Method 1: clone()
        int[] cloned = original.clone();
        System.out.println("Cloned: " + Arrays.toString(cloned));

        // Method 2: Arrays.copyOf()
        int[] copied = Arrays.copyOf(original, original.length);
        System.out.println("Copied: " + Arrays.toString(copied));

        // Method 3: Arrays.copyOfRange()
        int[] range = Arrays.copyOfRange(original, 1, 4); // Index 1-3
        System.out.println("Range copy (1-3): " + Arrays.toString(range));

        // Method 4: System.arraycopy()
        int[] dest = new int[5];
        System.arraycopy(original, 0, dest, 0, original.length);
        System.out.println("System.arraycopy: " + Arrays.toString(dest));

        // Resize with copyOf
        int[] larger = Arrays.copyOf(original, 8); // Pad with 0
        int[] smaller = Arrays.copyOf(original, 3); // Truncate
        System.out.println("Larger (8): " + Arrays.toString(larger));
        System.out.println("Smaller (3): " + Arrays.toString(smaller));

        // Deep copy for 2D arrays
        int[][] matrix = { { 1, 2 }, { 3, 4 } };
        int[][] deepCopy = deepCopy2D(matrix);
        deepCopy[0][0] = 999;
        System.out.println("\nOriginal matrix[0][0]: " + matrix[0][0]);
        System.out.println("Deep copy matrix[0][0]: " + deepCopy[0][0]);
    }

    public static int[][] deepCopy2D(int[][] original) {
        int[][] copy = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i].clone();
        }
        return copy;
    }

    /**
     * Comparing arrays
     */
    private static void demonstrateComparing() {
        System.out.println("\n--- Comparing Arrays ---");

        int[] arr1 = { 1, 2, 3 };
        int[] arr2 = { 1, 2, 3 };
        int[] arr3 = { 1, 2, 4 };

        // Wrong way: == compares references
        System.out.println("arr1 == arr2: " + (arr1 == arr2)); // false

        // Correct way: Arrays.equals()
        System.out.println("Arrays.equals(arr1, arr2): " + Arrays.equals(arr1, arr2)); // true
        System.out.println("Arrays.equals(arr1, arr3): " + Arrays.equals(arr1, arr3)); // false

        // Compare 2D arrays
        int[][] matrix1 = { { 1, 2 }, { 3, 4 } };
        int[][] matrix2 = { { 1, 2 }, { 3, 4 } };
        System.out.println("Arrays.deepEquals(matrix1, matrix2): " + Arrays.deepEquals(matrix1, matrix2));

        // Lexicographic comparison
        int result = Arrays.compare(arr1, arr3);
        System.out.println("Arrays.compare(arr1, arr3): " + result); // negative (arr1 < arr3)
    }

    /**
     * Reversing arrays
     */
    private static void demonstrateReversing() {
        System.out.println("\n--- Reversing Arrays ---");

        int[] numbers = { 1, 2, 3, 4, 5 };
        System.out.println("Original: " + Arrays.toString(numbers));

        reverse(numbers);
        System.out.println("Reversed: " + Arrays.toString(numbers));

        // Reverse string array
        String[] words = { "Java", "Python", "C++", "Go" };
        reverseGeneric(words);
        System.out.println("Reversed strings: " + Arrays.toString(words));
    }

    public static void reverse(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            // Swap
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
    }

    public static <T> void reverseGeneric(T[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            T temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
    }

    /**
     * Rotating arrays
     */
    private static void demonstrateRotating() {
        System.out.println("\n--- Rotating Arrays ---");

        int[] numbers = { 1, 2, 3, 4, 5 };
        System.out.println("Original: " + Arrays.toString(numbers));

        rotateLeft(numbers, 2);
        System.out.println("Rotate left by 2: " + Arrays.toString(numbers));

        int[] numbers2 = { 1, 2, 3, 4, 5 };
        rotateRight(numbers2, 2);
        System.out.println("Rotate right by 2: " + Arrays.toString(numbers2));
    }

    public static void rotateLeft(int[] arr, int positions) {
        positions = positions % arr.length; // Handle positions > length

        reverse(arr, 0, positions - 1);
        reverse(arr, positions, arr.length - 1);
        reverse(arr, 0, arr.length - 1);
    }

    public static void rotateRight(int[] arr, int positions) {
        positions = positions % arr.length;
        rotateLeft(arr, arr.length - positions);
    }

    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * Mathematical operations
     */
    private static void demonstrateMathOperations() {
        System.out.println("\n--- Math Operations ---");

        int[] numbers = { 5, 2, 8, 1, 9, 3, 7 };

        // Sum
        int sum = sum(numbers);
        System.out.println("Sum: " + sum);

        // Average
        double avg = average(numbers);
        System.out.println("Average: " + avg);

        // Min and Max
        int min = findMin(numbers);
        int max = findMax(numbers);
        System.out.println("Min: " + min);
        System.out.println("Max: " + max);

        // Count occurrences
        int[] data = { 1, 2, 3, 2, 4, 2, 5 };
        int count = countOccurrences(data, 2);
        System.out.println("Occurrences of 2: " + count);
    }

    public static int sum(int[] arr) {
        int total = 0;
        for (int num : arr) {
            total += num;
        }
        return total;
    }

    public static double average(int[] arr) {
        if (arr.length == 0)
            return 0;
        return (double) sum(arr) / arr.length;
    }

    public static int findMin(int[] arr) {
        if (arr.length == 0)
            throw new IllegalArgumentException("Empty array");

        int min = arr[0];
        for (int num : arr) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }

    public static int findMax(int[] arr) {
        if (arr.length == 0)
            throw new IllegalArgumentException("Empty array");

        int max = arr[0];
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    public static int countOccurrences(int[] arr, int target) {
        int count = 0;
        for (int num : arr) {
            if (num == target) {
                count++;
            }
        }
        return count;
    }

    /**
     * Advanced operations
     */
    private static void demonstrateAdvancedOperations() {
        System.out.println("\n--- Advanced Operations ---");

        // Remove duplicates
        int[] withDuplicates = { 1, 2, 2, 3, 3, 3, 4, 5, 5 };
        int[] unique = removeDuplicates(withDuplicates);
        System.out.println("Remove duplicates: " + Arrays.toString(unique));

        // Merge two sorted arrays
        int[] arr1 = { 1, 3, 5, 7 };
        int[] arr2 = { 2, 4, 6, 8 };
        int[] merged = mergeSorted(arr1, arr2);
        System.out.println("Merge sorted: " + Arrays.toString(merged));

        // Find second largest
        int[] numbers = { 5, 2, 8, 1, 9, 3, 7 };
        int secondLargest = findSecondLargest(numbers);
        System.out.println("Second largest: " + secondLargest);

        // Check if array is palindrome
        int[] palindrome = { 1, 2, 3, 2, 1 };
        int[] notPalindrome = { 1, 2, 3, 4, 5 };
        System.out.println("Is [1,2,3,2,1] palindrome: " + isPalindrome(palindrome));
        System.out.println("Is [1,2,3,4,5] palindrome: " + isPalindrome(notPalindrome));

        System.out.println("\nExercise completed!");
    }

    public static int[] removeDuplicates(int[] arr) {
        if (arr.length == 0)
            return arr;

        Arrays.sort(arr);
        int[] temp = new int[arr.length];
        int j = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] != arr[i + 1]) {
                temp[j++] = arr[i];
            }
        }
        temp[j++] = arr[arr.length - 1];

        return Arrays.copyOf(temp, j);
    }

    public static int[] mergeSorted(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;

        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                result[k++] = arr1[i++];
            } else {
                result[k++] = arr2[j++];
            }
        }

        while (i < arr1.length) {
            result[k++] = arr1[i++];
        }

        while (j < arr2.length) {
            result[k++] = arr2[j++];
        }

        return result;
    }

    public static int findSecondLargest(int[] arr) {
        if (arr.length < 2)
            throw new IllegalArgumentException("Array must have at least 2 elements");

        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for (int num : arr) {
            if (num > largest) {
                secondLargest = largest;
                largest = num;
            } else if (num > secondLargest && num != largest) {
                secondLargest = num;
            }
        }

        return secondLargest;
    }

    public static boolean isPalindrome(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            if (arr[left] != arr[right]) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}
