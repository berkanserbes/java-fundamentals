package com.fundamentals.loops;

public class LoopControlDemo {
    public static void main(String[] args) {
        System.out.println("=== Loop Control Demo ===\n");

        // ========== BREAK STATEMENT ==========
        System.out.println("--- Break Statement ---");
        for (int i = 1; i <= 10; i++) {
            if (i == 6) {
                System.out.println("Breaking at " + i);
                break;
            }
            System.out.println("i = " + i);
        }

        // ========== CONTINUE STATEMENT ==========
        System.out.println("\n--- Continue Statement ---");
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                continue; // Skip even numbers
            }
            System.out.println("Odd number: " + i);
        }

        // ========== BREAK AND CONTINUE TOGETHER ==========
        System.out.println("\n--- Break and Continue Together ---");
        for (int i = 1; i <= 20; i++) {
            if (i % 2 == 0) {
                continue; // Skip even numbers
            }
            if (i > 15) {
                System.out.println("Stopping at " + i);
                break; // Stop when > 15
            }
            System.out.println(i);
        }

        // ========== SEARCH WITH BREAK ==========
        System.out.println("\n--- Search with Break ---");
        int[] numbers = { 5, 12, 8, 23, 15, 7, 30 };
        int target = 23;
        int foundIndex = -1;

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == target) {
                foundIndex = i;
                break; // Stop searching once found
            }
        }

        if (foundIndex != -1) {
            System.out.println(target + " found at index " + foundIndex);
        } else {
            System.out.println(target + " not found");
        }

        // ========== SKIP MULTIPLES ==========
        System.out.println("\n--- Skip Multiples of 3 ---");
        for (int i = 1; i <= 20; i++) {
            if (i % 3 == 0) {
                continue;
            }
            System.out.print(i + " ");
        }
        System.out.println();

        // ========== PRIME NUMBER WITH BREAK ==========
        System.out.println("\n--- Prime Check with Break ---");
        int num = 29;
        boolean isPrime = true;

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                isPrime = false;
                break; // No need to check further
            }
        }
        System.out.println(num + " is " + (isPrime ? "prime" : "not prime"));

        // ========== LABELED BREAK ==========
        System.out.println("\n--- Labeled Break (Nested Loops) ---");
        outerLoop: for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                System.out.println("i = " + i + ", j = " + j);
                if (i == 2 && j == 2) {
                    System.out.println("Breaking outer loop!");
                    break outerLoop; // Breaks out of both loops
                }
            }
        }

        // ========== LABELED CONTINUE ==========
        System.out.println("\n--- Labeled Continue (Nested Loops) ---");
        outer: for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (j == 2) {
                    System.out.println("Skipping rest of inner loop at i=" + i + ", j=" + j);
                    continue outer; // Continue outer loop
                }
                System.out.println("i = " + i + ", j = " + j);
            }
        }

        // ========== FIND FIRST PAIR ==========
        System.out.println("\n--- Find First Pair Sum = 10 ---");
        int[] arr = { 2, 4, 6, 8, 3, 7, 5 };
        boolean pairFound = false;

        searchLoop: for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == 10) {
                    System.out.println("Pair found: " + arr[i] + " + " + arr[j] + " = 10");
                    pairFound = true;
                    break searchLoop;
                }
            }
        }

        if (!pairFound) {
            System.out.println("No pair found");
        }

        // ========== SKIP NEGATIVE NUMBERS ==========
        System.out.println("\n--- Skip Negative Numbers ---");
        int[] values = { 5, -3, 8, -1, 12, -7, 4, -2 };
        System.out.print("Positive numbers: ");
        for (int value : values) {
            if (value < 0) {
                continue;
            }
            System.out.print(value + " ");
        }
        System.out.println();

        // ========== BREAK ON CONDITION ==========
        System.out.println("\n--- Sum Until > 50 ---");
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += i;
            if (sum > 50) {
                System.out.println("Sum exceeded 50 at i = " + i);
                System.out.println("Final sum: " + sum);
                break;
            }
        }

        // ========== MATRIX SEARCH ==========
        System.out.println("\n--- Matrix Search ---");
        int[][] matrix = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };
        int searchValue = 5;
        boolean matrixFound = false;

        matrixSearch: for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == searchValue) {
                    System.out.println(searchValue + " found at [" + i + "][" + j + "]");
                    matrixFound = true;
                    break matrixSearch;
                }
            }
        }

        // ========== SKIP VOWELS ==========
        System.out.println("\n--- Skip Vowels ---");
        String text = "Hello World";
        System.out.print("Consonants: ");
        for (char ch : text.toCharArray()) {
            char lower = Character.toLowerCase(ch);
            if (lower == 'a' || lower == 'e' || lower == 'i' ||
                    lower == 'o' || lower == 'u' || ch == ' ') {
                continue;
            }
            System.out.print(ch);
        }
        System.out.println();

        // ========== FIRST N PRIMES ==========
        System.out.println("\n--- First 10 Prime Numbers ---");
        int primeCount = 0;
        int number = 2;

        while (primeCount < 10) {
            boolean prime = true;
            for (int i = 2; i <= Math.sqrt(number); i++) {
                if (number % i == 0) {
                    prime = false;
                    break;
                }
            }
            if (prime) {
                System.out.print(number + " ");
                primeCount++;
            }
            number++;
        }
        System.out.println();

        // ========== BREAK IN WHILE LOOP ==========
        System.out.println("\n--- Break in While Loop ---");
        int count = 1;
        while (true) { // Infinite loop
            System.out.println("Count: " + count);
            if (count == 5) {
                System.out.println("Breaking infinite loop");
                break;
            }
            count++;
        }

        // ========== CONTINUE IN WHILE LOOP ==========
        System.out.println("\n--- Continue in While Loop ---");
        int i = 0;
        while (i < 10) {
            i++;
            if (i % 3 == 0) {
                continue; // Skip multiples of 3
            }
            System.out.print(i + " ");
        }
        System.out.println();

        // ========== NESTED LOOP PATTERN ==========
        System.out.println("\n--- Pattern with Break ---");
        for (int row = 1; row <= 5; row++) {
            for (int col = 1; col <= 10; col++) {
                System.out.print("* ");
                if (col == row) {
                    break; // Break inner loop
                }
            }
            System.out.println();
        }

        // ========== VALIDATION WITH CONTINUE ==========
        System.out.println("\n--- Process Valid Numbers Only ---");
        int[] data = { 10, -5, 20, 0, 30, -3, 40 };
        int validSum = 0;

        for (int value : data) {
            if (value <= 0) {
                System.out.println("Skipping invalid value: " + value);
                continue;
            }
            validSum += value;
        }
        System.out.println("Sum of valid numbers: " + validSum);

        System.out.println("\nLoop Control Demo completed!");
    }
}
