package com.fundamentals.methods;

/**
 * Recursion in Java
 * 
 * Recursion: A method calling itself
 * 
 * Components:
 * 1. Base Case: Condition to stop recursion (prevents infinite loop)
 * 2. Recursive Case: Method calls itself with modified parameters
 * 3. Progress: Each call should move toward base case
 * 
 * Types:
 * - Direct Recursion: Method calls itself directly
 * - Indirect Recursion: Method A calls B, B calls A
 * - Tail Recursion: Recursive call is last operation
 * - Head Recursion: Recursive call is first operation
 * 
 * When to Use:
 * - Tree/graph traversal
 * - Divide and conquer algorithms
 * - Mathematical sequences
 * - Backtracking problems
 * 
 * Trade-offs:
 * + Elegant and concise code
 * + Natural for certain problems
 * - Stack overflow risk
 * - Can be slower than iteration
 * - Uses more memory
 */
public class RecursionDemo {

    public static void main(String[] args) {
        System.out.println("=== Recursion Demo ===\n");

        demonstrateBasicRecursion();
        demonstrateMathematicalRecursion();
        demonstrateStringRecursion();
        demonstrateArrayRecursion();
        demonstrateTailRecursion();
        demonstrateBacktracking();
        demonstrateRecursionVsIteration();
        demonstrateTricksAndPitfalls();
    }

    /**
     * Basic recursion examples
     */
    private static void demonstrateBasicRecursion() {
        System.out.println("--- Basic Recursion ---");

        // Countdown
        System.out.println("Countdown from 5:");
        countdown(5);

        // Count up
        System.out.println("\nCount up to 5:");
        countUp(1, 5);

        // Sum of numbers
        int sum = sumToN(5);
        System.out.println("\nSum 1 to 5: " + sum);
    }

    // Simple countdown
    public static void countdown(int n) {
        // Base case
        if (n <= 0) {
            System.out.println("Blastoff!");
            return;
        }

        // Recursive case
        System.out.println(n);
        countdown(n - 1);
    }

    // Count up
    public static void countUp(int current, int max) {
        // Base case
        if (current > max) {
            return;
        }

        // Recursive case
        System.out.println(current);
        countUp(current + 1, max);
    }

    // Sum from 1 to n
    public static int sumToN(int n) {
        // Base case
        if (n <= 0) {
            return 0;
        }

        // Recursive case
        return n + sumToN(n - 1);
    }

    /**
     * Mathematical recursion
     */
    private static void demonstrateMathematicalRecursion() {
        System.out.println("\n--- Mathematical Recursion ---");

        // Factorial
        System.out.println("Factorial of 5: " + factorial(5));
        System.out.println("Factorial of 10: " + factorial(10));

        // Fibonacci
        System.out.print("Fibonacci sequence (10 terms): ");
        for (int i = 0; i < 10; i++) {
            System.out.print(fibonacci(i) + " ");
        }
        System.out.println();

        // Power
        System.out.println("\n2^8 = " + power(2, 8));
        System.out.println("3^4 = " + power(3, 4));

        // GCD (Greatest Common Divisor)
        System.out.println("\nGCD(48, 18) = " + gcd(48, 18));
        System.out.println("GCD(100, 35) = " + gcd(100, 35));
    }

    // Factorial: n! = n * (n-1)!
    public static long factorial(int n) {
        // Base case
        if (n <= 1) {
            return 1;
        }

        // Recursive case
        return n * factorial(n - 1);
    }

    // Fibonacci: F(n) = F(n-1) + F(n-2)
    public static int fibonacci(int n) {
        // Base cases
        if (n <= 0)
            return 0;
        if (n == 1)
            return 1;

        // Recursive case
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // Power: a^b
    public static long power(int base, int exponent) {
        // Base case
        if (exponent == 0) {
            return 1;
        }

        // Recursive case
        return base * power(base, exponent - 1);
    }

    // GCD using Euclidean algorithm
    public static int gcd(int a, int b) {
        // Base case
        if (b == 0) {
            return a;
        }

        // Recursive case
        return gcd(b, a % b);
    }

    /**
     * String recursion
     */
    private static void demonstrateStringRecursion() {
        System.out.println("\n--- String Recursion ---");

        // Reverse string
        String text = "Hello";
        System.out.println("Original: " + text);
        System.out.println("Reversed: " + reverseString(text));

        // Palindrome check
        System.out.println("\n'racecar' is palindrome: " + isPalindrome("racecar"));
        System.out.println("'hello' is palindrome: " + isPalindrome("hello"));

        // Count vowels
        System.out.println("\nVowels in 'education': " + countVowels("education"));

        // Remove spaces
        System.out.println("Remove spaces from 'Hello World': '" + removeSpaces("Hello World") + "'");
    }

    // Reverse string
    public static String reverseString(String str) {
        // Base case
        if (str.isEmpty()) {
            return str;
        }

        // Recursive case: last char + reverse of rest
        return str.charAt(str.length() - 1) + reverseString(str.substring(0, str.length() - 1));
    }

    // Check palindrome
    public static boolean isPalindrome(String str) {
        // Base cases
        if (str.length() <= 1) {
            return true;
        }

        // Check first and last characters
        if (str.charAt(0) != str.charAt(str.length() - 1)) {
            return false;
        }

        // Recursive case: check middle part
        return isPalindrome(str.substring(1, str.length() - 1));
    }

    // Count vowels
    public static int countVowels(String str) {
        // Base case
        if (str.isEmpty()) {
            return 0;
        }

        // Check first character
        char first = Character.toLowerCase(str.charAt(0));
        int count = (first == 'a' || first == 'e' || first == 'i' ||
                first == 'o' || first == 'u') ? 1 : 0;

        // Recursive case
        return count + countVowels(str.substring(1));
    }

    // Remove spaces
    public static String removeSpaces(String str) {
        // Base case
        if (str.isEmpty()) {
            return str;
        }

        // Recursive case
        char first = str.charAt(0);
        String rest = removeSpaces(str.substring(1));

        return (first == ' ') ? rest : first + rest;
    }

    /**
     * Array recursion
     */
    private static void demonstrateArrayRecursion() {
        System.out.println("\n--- Array Recursion ---");

        int[] numbers = { 5, 2, 8, 1, 9, 3 };

        // Sum array
        System.out.println("Array: " + java.util.Arrays.toString(numbers));
        System.out.println("Sum: " + sumArray(numbers, 0));

        // Find max
        System.out.println("Max: " + findMax(numbers, 0));

        // Find min
        System.out.println("Min: " + findMin(numbers, 0));

        // Check if sorted
        int[] sorted = { 1, 2, 3, 4, 5 };
        System.out.println("\n" + java.util.Arrays.toString(sorted) + " is sorted: " + isSorted(sorted, 0));
        System.out.println(java.util.Arrays.toString(numbers) + " is sorted: " + isSorted(numbers, 0));
    }

    // Sum array elements
    public static int sumArray(int[] arr, int index) {
        // Base case
        if (index >= arr.length) {
            return 0;
        }

        // Recursive case
        return arr[index] + sumArray(arr, index + 1);
    }

    // Find maximum
    public static int findMax(int[] arr, int index) {
        // Base case
        if (index == arr.length - 1) {
            return arr[index];
        }

        // Recursive case
        int maxOfRest = findMax(arr, index + 1);
        return Math.max(arr[index], maxOfRest);
    }

    // Find minimum
    public static int findMin(int[] arr, int index) {
        // Base case
        if (index == arr.length - 1) {
            return arr[index];
        }

        // Recursive case
        int minOfRest = findMin(arr, index + 1);
        return Math.min(arr[index], minOfRest);
    }

    // Check if array is sorted
    public static boolean isSorted(int[] arr, int index) {
        // Base case
        if (index >= arr.length - 1) {
            return true;
        }

        // Check current pair
        if (arr[index] > arr[index + 1]) {
            return false;
        }

        // Recursive case
        return isSorted(arr, index + 1);
    }

    /**
     * Tail recursion - optimization opportunity
     */
    private static void demonstrateTailRecursion() {
        System.out.println("\n--- Tail Recursion ---");

        // Regular recursion
        System.out.println("Factorial (regular): " + factorial(5));

        // Tail recursion
        System.out.println("Factorial (tail): " + factorialTail(5, 1));

        System.out.println("\nTail recursion: recursive call is last operation");
        System.out.println("Can be optimized by compiler (tail call optimization)");
    }

    // Tail recursive factorial
    public static long factorialTail(int n, long accumulator) {
        // Base case
        if (n <= 1) {
            return accumulator;
        }

        // Tail recursive case (no operation after recursive call)
        return factorialTail(n - 1, n * accumulator);
    }

    /**
     * Backtracking example
     */
    private static void demonstrateBacktracking() {
        System.out.println("\n--- Backtracking (Generate Subsets) ---");

        int[] set = { 1, 2, 3 };
        System.out.println("All subsets of " + java.util.Arrays.toString(set) + ":");
        generateSubsets(set, 0, new java.util.ArrayList<>());
    }

    // Generate all subsets (power set)
    public static void generateSubsets(int[] set, int index, java.util.List<Integer> current) {
        // Base case: processed all elements
        if (index == set.length) {
            System.out.println("  " + current);
            return;
        }

        // Include current element
        current.add(set[index]);
        generateSubsets(set, index + 1, current);

        // Backtrack: exclude current element
        current.remove(current.size() - 1);
        generateSubsets(set, index + 1, current);
    }

    /**
     * Recursion vs Iteration comparison
     */
    private static void demonstrateRecursionVsIteration() {
        System.out.println("\n--- Recursion vs Iteration ---");

        int n = 10;

        // Recursive
        long start1 = System.nanoTime();
        int result1 = fibonacci(n);
        long end1 = System.nanoTime();

        // Iterative
        long start2 = System.nanoTime();
        int result2 = fibonacciIterative(n);
        long end2 = System.nanoTime();

        System.out.println("Fibonacci(" + n + "):");
        System.out.println("  Recursive: " + result1 + " (" + (end1 - start1) / 1000 + " μs)");
        System.out.println("  Iterative: " + result2 + " (" + (end2 - start2) / 1000 + " μs)");
        System.out.println("\nIterative is usually faster for simple cases");
    }

    // Iterative fibonacci
    public static int fibonacciIterative(int n) {
        if (n <= 0)
            return 0;
        if (n == 1)
            return 1;

        int prev = 0, curr = 1;
        for (int i = 2; i <= n; i++) {
            int next = prev + curr;
            prev = curr;
            curr = next;
        }
        return curr;
    }

    /**
     * Tricks and pitfalls
     */
    private static void demonstrateTricksAndPitfalls() {
        System.out.println("\n--- Tricks & Pitfalls ---");

        // PITFALL 1: Missing base case (infinite recursion)
        System.out.println("PITFALL 1: Always have a base case!");
        System.out.println("Without it: StackOverflowError");

        // PITFALL 2: Stack overflow
        System.out.println("\nPITFALL 2: Deep recursion causes stack overflow");
        try {
            countdown(100000); // Too deep!
        } catch (StackOverflowError e) {
            System.out.println("StackOverflowError caught!");
        }

        // TRICK 1: Memoization for optimization
        System.out.println("\nTRICK 1: Use memoization for repeated calculations");
        System.out.println("Fibonacci(40) without memo: very slow");
        System.out.println("Fibonacci(40) with memo: fast");

        // TRICK 2: Convert to iteration when possible
        System.out.println("\nTRICK 2: Consider iteration for simple cases");
        System.out.println("Iteration: faster, no stack overflow risk");

        // BEST PRACTICES
        System.out.println("\n--- Best Practices ---");
        System.out.println("1. Always define clear base case(s)");
        System.out.println("2. Ensure progress toward base case");
        System.out.println("3. Consider stack depth limits");
        System.out.println("4. Use memoization for overlapping subproblems");
        System.out.println("5. Consider iteration for simple cases");
        System.out.println("6. Test with small inputs first");
        System.out.println("7. Document recursion logic clearly");

        System.out.println("\nExercise completed!");
    }
}
