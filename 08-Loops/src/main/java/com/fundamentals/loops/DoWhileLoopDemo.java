package com.fundamentals.loops;

public class DoWhileLoopDemo {
    public static void main(String[] args) {
        System.out.println("=== Do-While Loop Demo ===\n");

        // ========== BASIC DO-WHILE ==========
        System.out.println("--- Basic Do-While Loop ---");
        int count = 1;
        do {
            System.out.println("Count: " + count);
            count++;
        } while (count <= 5);

        // ========== EXECUTES AT LEAST ONCE ==========
        System.out.println("\n--- Executes At Least Once ---");
        int num = 10;
        do {
            System.out.println("This runs even though condition is false!");
            System.out.println("num = " + num);
        } while (num < 5); // Condition is false, but body executes once

        // ========== MENU SIMULATION ==========
        System.out.println("\n--- Menu Simulation ---");
        int choice = 0;
        int menuIteration = 0;
        do {
            menuIteration++;
            System.out.println("\n=== Menu (Iteration " + menuIteration + ") ===");
            System.out.println("1. Option One");
            System.out.println("2. Option Two");
            System.out.println("3. Exit");

            // Simulate user choice
            if (menuIteration == 1)
                choice = 1;
            else if (menuIteration == 2)
                choice = 2;
            else
                choice = 3;

            System.out.println("Selected: " + choice);

            switch (choice) {
                case 1:
                    System.out.println("Executing Option One...");
                    break;
                case 2:
                    System.out.println("Executing Option Two...");
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
            }
        } while (choice != 3);

        // ========== INPUT VALIDATION PATTERN ==========
        System.out.println("\n--- Input Validation Pattern ---");
        int userInput;
        int attempt = 0;
        do {
            attempt++;
            // Simulate user input
            userInput = attempt * 15; // Will be valid when attempt = 5 (75)
            System.out.println("Attempt " + attempt + ": Input = " + userInput);

            if (userInput < 50 || userInput > 100) {
                System.out.println("Invalid! Must be between 50 and 100");
            }
        } while (userInput < 50 || userInput > 100);
        System.out.println("Valid input received: " + userInput);

        // ========== SUM UNTIL ZERO ==========
        System.out.println("\n--- Sum Until Zero ---");
        int sum = 0;
        int value = 5;
        do {
            System.out.println("Adding: " + value);
            sum += value;
            value--;
        } while (value > 0);
        System.out.println("Total sum: " + sum);

        // ========== COUNTDOWN ==========
        System.out.println("\n--- Countdown ---");
        int countdown = 5;
        do {
            System.out.println(countdown);
            countdown--;
        } while (countdown > 0);
        System.out.println("Blast off!");

        // ========== FACTORIAL ==========
        System.out.println("\n--- Factorial ---");
        int n = 5;
        long factorial = 1;
        int i = 1;
        do {
            factorial *= i;
            i++;
        } while (i <= n);
        System.out.println(n + "! = " + factorial);

        // ========== REVERSE NUMBER ==========
        System.out.println("\n--- Reverse Number ---");
        int number = 54321;
        int reversed = 0;
        int temp = number;
        do {
            int digit = temp % 10;
            reversed = reversed * 10 + digit;
            temp /= 10;
        } while (temp > 0);
        System.out.println("Original: " + number);
        System.out.println("Reversed: " + reversed);

        // ========== POWER CALCULATION ==========
        System.out.println("\n--- Power Calculation ---");
        int base = 3;
        int exponent = 4;
        long result = 1;
        int exp = 0;
        do {
            result *= base;
            exp++;
        } while (exp < exponent);
        System.out.println(base + "^" + exponent + " = " + result);

        // ========== PRINT DIGITS ==========
        System.out.println("\n--- Print Digits ---");
        int num2 = 6789;
        System.out.println("Digits of " + num2 + ":");
        int temp2 = num2;
        do {
            System.out.print((temp2 % 10) + " ");
            temp2 /= 10;
        } while (temp2 > 0);
        System.out.println();

        // ========== MULTIPLICATION TABLE ==========
        System.out.println("\n--- Multiplication Table (6) ---");
        int multiplier = 6;
        int counter = 1;
        do {
            System.out.println(multiplier + " x " + counter + " = " + (multiplier * counter));
            counter++;
        } while (counter <= 10);

        // ========== ARRAY ITERATION ==========
        System.out.println("\n--- Array Iteration ---");
        int[] numbers = { 10, 20, 30, 40, 50 };
        int index = 0;
        do {
            System.out.println("Index " + index + ": " + numbers[index]);
            index++;
        } while (index < numbers.length);

        // ========== FIND SUM OF EVEN NUMBERS ==========
        System.out.println("\n--- Sum of Even Numbers (1-20) ---");
        int evenSum = 0;
        int current = 2;
        do {
            evenSum += current;
            current += 2;
        } while (current <= 20);
        System.out.println("Sum of even numbers: " + evenSum);

        // ========== FIBONACCI SEQUENCE ==========
        System.out.println("\n--- Fibonacci (First 8 terms) ---");
        int fib1 = 0, fib2 = 1;
        int fibCount = 0;
        do {
            if (fibCount == 0) {
                System.out.print(fib1 + " ");
            } else if (fibCount == 1) {
                System.out.print(fib2 + " ");
            } else {
                int next = fib1 + fib2;
                System.out.print(next + " ");
                fib1 = fib2;
                fib2 = next;
            }
            fibCount++;
        } while (fibCount < 8);
        System.out.println();

        // ========== PASSWORD ATTEMPTS ==========
        System.out.println("\n--- Password Attempts ---");
        String correctPassword = "secret123";
        String enteredPassword;
        int attempts = 0;
        int maxAttempts = 3;

        do {
            attempts++;
            // Simulate password entry
            if (attempts == 1)
                enteredPassword = "wrong1";
            else if (attempts == 2)
                enteredPassword = "wrong2";
            else
                enteredPassword = "secret123";

            System.out.println("Attempt " + attempts + ": " + enteredPassword);

            if (enteredPassword.equals(correctPassword)) {
                System.out.println("Access granted!");
                break;
            } else {
                System.out.println("Incorrect password");
            }
        } while (attempts < maxAttempts);

        if (attempts >= maxAttempts && !enteredPassword.equals(correctPassword)) {
            System.out.println("Account locked!");
        }

        // ========== COMPARISON: WHILE vs DO-WHILE ==========
        System.out.println("\n--- Comparison: While vs Do-While ---");

        // While loop - may not execute
        System.out.println("While loop (condition false):");
        int w = 10;
        while (w < 5) {
            System.out.println("This won't print");
            w++;
        }
        System.out.println("While loop didn't execute");

        // Do-While loop - executes at least once
        System.out.println("\nDo-While loop (condition false):");
        int dw = 10;
        do {
            System.out.println("This prints once! dw = " + dw);
            dw++;
        } while (dw < 5);

        System.out.println("\nDo-While Loop Demo completed!");
    }
}
