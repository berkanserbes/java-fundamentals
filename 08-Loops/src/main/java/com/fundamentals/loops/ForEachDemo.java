package com.fundamentals.loops;

public class ForEachDemo {
    public static void main(String[] args) {
        System.out.println("=== For-Each Loop Demo ===\n");

        // ========== BASIC FOR-EACH ==========
        System.out.println("--- Basic For-Each ---");
        int[] numbers = { 10, 20, 30, 40, 50 };
        for (int num : numbers) {
            System.out.println("Number: " + num);
        }

        // ========== STRING ARRAY ==========
        System.out.println("\n--- String Array ---");
        String[] fruits = { "Apple", "Banana", "Orange", "Mango", "Grape" };
        for (String fruit : fruits) {
            System.out.println("Fruit: " + fruit);
        }

        // ========== SUM OF ARRAY ==========
        System.out.println("\n--- Sum of Array ---");
        int[] values = { 5, 10, 15, 20, 25 };
        int sum = 0;
        for (int value : values) {
            sum += value;
        }
        System.out.println("Sum: " + sum);

        // ========== FIND MAXIMUM ==========
        System.out.println("\n--- Find Maximum ---");
        int[] scores = { 78, 92, 85, 67, 95, 88 };
        int max = scores[0];
        for (int score : scores) {
            if (score > max) {
                max = score;
            }
        }
        System.out.println("Maximum score: " + max);

        // ========== FIND MINIMUM ==========
        System.out.println("\n--- Find Minimum ---");
        int[] prices = { 250, 180, 320, 150, 290 };
        int min = prices[0];
        for (int price : prices) {
            if (price < min) {
                min = price;
            }
        }
        System.out.println("Minimum price: $" + min);

        // ========== COUNT OCCURRENCES ==========
        System.out.println("\n--- Count Occurrences ---");
        int[] data = { 5, 3, 7, 3, 9, 3, 1, 3 };
        int target = 3;
        int count = 0;
        for (int num : data) {
            if (num == target) {
                count++;
            }
        }
        System.out.println("Number " + target + " appears " + count + " times");

        // ========== AVERAGE CALCULATION ==========
        System.out.println("\n--- Average Calculation ---");
        double[] grades = { 85.5, 92.0, 78.5, 88.0, 95.5 };
        double total = 0;
        for (double grade : grades) {
            total += grade;
        }
        double average = total / grades.length;
        System.out.println("Average grade: " + average);

        // ========== STRING OPERATIONS ==========
        System.out.println("\n--- String Operations ---");
        String[] words = { "Java", "Python", "JavaScript", "C++", "Ruby" };
        System.out.println("Languages:");
        for (String word : words) {
            System.out.println("  - " + word + " (length: " + word.length() + ")");
        }

        // ========== FILTER ELEMENTS ==========
        System.out.println("\n--- Filter Even Numbers ---");
        int[] allNumbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        System.out.print("Even numbers: ");
        for (int num : allNumbers) {
            if (num % 2 == 0) {
                System.out.print(num + " ");
            }
        }
        System.out.println();

        // ========== DOUBLE ARRAY (2D) ==========
        System.out.println("\n--- 2D Array (Matrix) ---");
        int[][] matrix = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };

        for (int[] row : matrix) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }

        // ========== SUM OF 2D ARRAY ==========
        System.out.println("\n--- Sum of 2D Array ---");
        int[][] numbers2D = {
                { 10, 20, 30 },
                { 40, 50, 60 }
        };
        int sum2D = 0;
        for (int[] row : numbers2D) {
            for (int num : row) {
                sum2D += num;
            }
        }
        System.out.println("Sum of all elements: " + sum2D);

        // ========== BOOLEAN ARRAY ==========
        System.out.println("\n--- Boolean Array ---");
        boolean[] flags = { true, false, true, true, false };
        int trueCount = 0;
        for (boolean flag : flags) {
            if (flag) {
                trueCount++;
            }
        }
        System.out.println("True count: " + trueCount);
        System.out.println("False count: " + (flags.length - trueCount));

        // ========== CHARACTER ARRAY ==========
        System.out.println("\n--- Character Array ---");
        char[] letters = { 'J', 'a', 'v', 'a' };
        System.out.print("Characters: ");
        for (char letter : letters) {
            System.out.print(letter + " ");
        }
        System.out.println();

        // ========== STRING CONCATENATION ==========
        System.out.println("\n--- String Concatenation ---");
        String[] parts = { "Hello", "from", "Java", "programming" };
        String sentence = "";
        for (String part : parts) {
            sentence += part + " ";
        }
        System.out.println("Sentence: " + sentence.trim());

        // ========== SEARCH IN ARRAY ==========
        System.out.println("\n--- Search in Array ---");
        String[] names = { "Alice", "Bob", "Charlie", "David", "Eve" };
        String searchName = "Charlie";
        boolean found = false;
        for (String name : names) {
            if (name.equals(searchName)) {
                found = true;
                break;
            }
        }
        System.out.println(searchName + (found ? " found" : " not found"));

        // ========== CONVERT TO UPPERCASE ==========
        System.out.println("\n--- Convert to Uppercase ---");
        String[] cities = { "new york", "london", "tokyo", "paris" };
        System.out.println("Cities in uppercase:");
        for (String city : cities) {
            System.out.println("  " + city.toUpperCase());
        }

        // ========== COUNT POSITIVE/NEGATIVE ==========
        System.out.println("\n--- Count Positive/Negative ---");
        int[] mixedNumbers = { 5, -3, 8, -1, 0, 12, -7, 4 };
        int positive = 0, negative = 0, zero = 0;
        for (int num : mixedNumbers) {
            if (num > 0)
                positive++;
            else if (num < 0)
                negative++;
            else
                zero++;
        }
        System.out.println("Positive: " + positive);
        System.out.println("Negative: " + negative);
        System.out.println("Zero: " + zero);

        // ========== PRODUCT OF ARRAY ==========
        System.out.println("\n--- Product of Array ---");
        int[] factors = { 2, 3, 4, 5 };
        long product = 1;
        for (int factor : factors) {
            product *= factor;
        }
        System.out.println("Product: " + product);

        // ========== COMPARISON: FOR vs FOR-EACH ==========
        System.out.println("\n--- Comparison: For vs For-Each ---");
        int[] arr = { 10, 20, 30, 40, 50 };

        System.out.println("Traditional for loop (with index):");
        for (int i = 0; i < arr.length; i++) {
            System.out.println("  Index " + i + ": " + arr[i]);
        }

        System.out.println("\nFor-each loop (no index):");
        for (int element : arr) {
            System.out.println("  Value: " + element);
        }

        System.out.println("\nNote: For-each is simpler but doesn't provide index access");

        System.out.println("\nFor-Each Loop Demo completed!");
    }
}
