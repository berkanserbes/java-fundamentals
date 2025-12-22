package com.fundamentals.loops;

public class ForLoopDemo {
    public static void main(String[] args) {
        System.out.println("=== For Loop Demo ===\n");

        // ========== BASIC FOR LOOP ==========
        System.out.println("--- Basic For Loop ---");
        for (int i = 1; i <= 5; i++) {
            System.out.println("Count: " + i);
        }

        // ========== COUNTING BACKWARDS ==========
        System.out.println("\n--- Counting Backwards ---");
        for (int i = 5; i >= 1; i--) {
            System.out.println("Countdown: " + i);
        }

        // ========== STEP BY 2 ==========
        System.out.println("\n--- Step by 2 ---");
        for (int i = 0; i <= 10; i += 2) {
            System.out.println("Even number: " + i);
        }

        // ========== MULTIPLICATION TABLE ==========
        System.out.println("\n--- Multiplication Table (5) ---");
        int number = 5;
        for (int i = 1; i <= 10; i++) {
            System.out.println(number + " x " + i + " = " + (number * i));
        }

        // ========== SUM OF NUMBERS ==========
        System.out.println("\n--- Sum of Numbers (1 to 10) ---");
        int sum = 0;
        for (int i = 1; i <= 10; i++) {
            sum += i;
        }
        System.out.println("Sum: " + sum);

        // ========== FACTORIAL ==========
        System.out.println("\n--- Factorial ---");
        int n = 5;
        long factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        System.out.println(n + "! = " + factorial);

        // ========== ARRAY ITERATION ==========
        System.out.println("\n--- Array Iteration ---");
        int[] numbers = { 10, 20, 30, 40, 50 };
        for (int i = 0; i < numbers.length; i++) {
            System.out.println("Index " + i + ": " + numbers[i]);
        }

        // ========== STRING ITERATION ==========
        System.out.println("\n--- String Character Iteration ---");
        String text = "Hello";
        for (int i = 0; i < text.length(); i++) {
            System.out.println("Character at " + i + ": " + text.charAt(i));
        }

        // ========== REVERSE STRING ==========
        System.out.println("\n--- Reverse String ---");
        String original = "Java";
        String reversed = "";
        for (int i = original.length() - 1; i >= 0; i--) {
            reversed += original.charAt(i);
        }
        System.out.println("Original: " + original);
        System.out.println("Reversed: " + reversed);

        // ========== NESTED FOR LOOP ==========
        System.out.println("\n--- Nested For Loop (Pattern) ---");
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }

        // ========== MULTIPLICATION TABLE (NESTED) ==========
        System.out.println("\n--- Multiplication Table (1-5) ---");
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                System.out.print(i * j + "\t");
            }
            System.out.println();
        }

        // ========== PRIME NUMBER CHECK ==========
        System.out.println("\n--- Prime Number Check ---");
        int numToCheck = 17;
        boolean isPrime = true;

        if (numToCheck <= 1) {
            isPrime = false;
        } else {
            for (int i = 2; i <= Math.sqrt(numToCheck); i++) {
                if (numToCheck % i == 0) {
                    isPrime = false;
                    break;
                }
            }
        }
        System.out.println(numToCheck + " is " + (isPrime ? "prime" : "not prime"));

        // ========== FIND PRIMES IN RANGE ==========
        System.out.println("\n--- Prime Numbers (1-30) ---");
        for (int num = 2; num <= 30; num++) {
            boolean prime = true;
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    prime = false;
                    break;
                }
            }
            if (prime) {
                System.out.print(num + " ");
            }
        }
        System.out.println();

        // ========== FIBONACCI SEQUENCE ==========
        System.out.println("\n--- Fibonacci Sequence (First 10) ---");
        int a = 0, b = 1;
        System.out.print(a + " " + b + " ");
        for (int i = 2; i < 10; i++) {
            int next = a + b;
            System.out.print(next + " ");
            a = b;
            b = next;
        }
        System.out.println();

        // ========== POWER CALCULATION ==========
        System.out.println("\n--- Power Calculation ---");
        int base = 2;
        int exponent = 8;
        long result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        System.out.println(base + "^" + exponent + " = " + result);

        // ========== ARRAY SUM AND AVERAGE ==========
        System.out.println("\n--- Array Sum and Average ---");
        int[] scores = { 85, 92, 78, 95, 88 };
        int total = 0;
        for (int i = 0; i < scores.length; i++) {
            total += scores[i];
        }
        double average = (double) total / scores.length;
        System.out.println("Total: " + total);
        System.out.println("Average: " + average);

        // ========== FIND MAX IN ARRAY ==========
        System.out.println("\n--- Find Maximum ---");
        int[] values = { 23, 67, 12, 89, 45, 34 };
        int max = values[0];
        for (int i = 1; i < values.length; i++) {
            if (values[i] > max) {
                max = values[i];
            }
        }
        System.out.println("Maximum value: " + max);

        // ========== COUNT VOWELS ==========
        System.out.println("\n--- Count Vowels ---");
        String sentence = "Hello World";
        int vowelCount = 0;
        for (int i = 0; i < sentence.length(); i++) {
            char ch = Character.toLowerCase(sentence.charAt(i));
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                vowelCount++;
            }
        }
        System.out.println("Sentence: " + sentence);
        System.out.println("Vowel count: " + vowelCount);

        // ========== PYRAMID PATTERN ==========
        System.out.println("\n--- Pyramid Pattern ---");
        int rows = 5;
        for (int i = 1; i <= rows; i++) {
            // Print spaces
            for (int j = 1; j <= rows - i; j++) {
                System.out.print(" ");
            }
            // Print stars
            for (int k = 1; k <= 2 * i - 1; k++) {
                System.out.print("*");
            }
            System.out.println();
        }

        System.out.println("\nFor Loop Demo completed!");
    }
}
