package com.fundamentals.loops;

public class WhileLoopDemo {
    public static void main(String[] args) {
        System.out.println("=== While Loop Demo ===\n");

        // ========== BASIC WHILE LOOP ==========
        System.out.println("--- Basic While Loop ---");
        int count = 1;
        while (count <= 5) {
            System.out.println("Count: " + count);
            count++;
        }

        // ========== COUNTDOWN ==========
        System.out.println("\n--- Countdown ---");
        int countdown = 5;
        while (countdown > 0) {
            System.out.println(countdown);
            countdown--;
        }
        System.out.println("Liftoff!");

        // ========== SUM UNTIL CONDITION ==========
        System.out.println("\n--- Sum Until > 100 ---");
        int sum = 0;
        int num = 1;
        while (sum <= 100) {
            sum += num;
            num++;
        }
        System.out.println("Final sum: " + sum);
        System.out.println("Numbers added: " + (num - 1));

        // ========== POWER OF 2 ==========
        System.out.println("\n--- Powers of 2 (up to 1000) ---");
        int power = 1;
        while (power <= 1000) {
            System.out.print(power + " ");
            power *= 2;
        }
        System.out.println();

        // ========== FACTORIAL ==========
        System.out.println("\n--- Factorial Using While ---");
        int n = 6;
        long factorial = 1;
        int i = 1;
        while (i <= n) {
            factorial *= i;
            i++;
        }
        System.out.println(n + "! = " + factorial);

        // ========== REVERSE NUMBER ==========
        System.out.println("\n--- Reverse Number ---");
        int number = 12345;
        int reversed = 0;
        int temp = number;
        while (temp > 0) {
            int digit = temp % 10;
            reversed = reversed * 10 + digit;
            temp /= 10;
        }
        System.out.println("Original: " + number);
        System.out.println("Reversed: " + reversed);

        // ========== COUNT DIGITS ==========
        System.out.println("\n--- Count Digits ---");
        int value = 987654;
        int digitCount = 0;
        int tempValue = value;
        while (tempValue > 0) {
            digitCount++;
            tempValue /= 10;
        }
        System.out.println("Number: " + value);
        System.out.println("Digit count: " + digitCount);

        // ========== SUM OF DIGITS ==========
        System.out.println("\n--- Sum of Digits ---");
        int num2 = 12345;
        int digitSum = 0;
        int temp2 = num2;
        while (temp2 > 0) {
            digitSum += temp2 % 10;
            temp2 /= 10;
        }
        System.out.println("Number: " + num2);
        System.out.println("Sum of digits: " + digitSum);

        // ========== PALINDROME CHECK ==========
        System.out.println("\n--- Palindrome Check ---");
        int original = 12321;
        int rev = 0;
        int tempNum = original;
        while (tempNum > 0) {
            rev = rev * 10 + tempNum % 10;
            tempNum /= 10;
        }
        System.out.println("Number: " + original);
        System.out.println("Is palindrome: " + (original == rev));

        // ========== GCD (GREATEST COMMON DIVISOR) ==========
        System.out.println("\n--- GCD (Euclidean Algorithm) ---");
        int a = 48, b = 18;
        int tempA = a, tempB = b;
        while (tempB != 0) {
            int remainder = tempA % tempB;
            tempA = tempB;
            tempB = remainder;
        }
        System.out.println("GCD of " + a + " and " + b + " is: " + tempA);

        // ========== FIBONACCI UNTIL LIMIT ==========
        System.out.println("\n--- Fibonacci (up to 100) ---");
        int fib1 = 0, fib2 = 1;
        System.out.print(fib1 + " " + fib2 + " ");
        while (true) {
            int next = fib1 + fib2;
            if (next > 100)
                break;
            System.out.print(next + " ");
            fib1 = fib2;
            fib2 = next;
        }
        System.out.println();

        // ========== FIND FIRST POWER OF 2 > N ==========
        System.out.println("\n--- First Power of 2 > 1000 ---");
        int pow = 1;
        while (pow <= 1000) {
            pow *= 2;
        }
        System.out.println("First power of 2 greater than 1000: " + pow);

        // ========== COLLATZ SEQUENCE ==========
        System.out.println("\n--- Collatz Sequence ---");
        int collatz = 13;
        System.out.print("Starting with " + collatz + ": ");
        while (collatz != 1) {
            System.out.print(collatz + " ");
            if (collatz % 2 == 0) {
                collatz /= 2;
            } else {
                collatz = 3 * collatz + 1;
            }
        }
        System.out.println("1");

        // ========== ARRAY PROCESSING ==========
        System.out.println("\n--- Array Processing ---");
        int[] numbers = { 5, 12, 8, 23, 15, 7 };
        int index = 0;
        int total = 0;
        while (index < numbers.length) {
            total += numbers[index];
            index++;
        }
        System.out.println("Array sum: " + total);

        // ========== SEARCH IN ARRAY ==========
        System.out.println("\n--- Search in Array ---");
        int[] data = { 10, 25, 30, 45, 50 };
        int searchValue = 30;
        int searchIndex = 0;
        boolean found = false;
        while (searchIndex < data.length) {
            if (data[searchIndex] == searchValue) {
                found = true;
                break;
            }
            searchIndex++;
        }
        if (found) {
            System.out.println(searchValue + " found at index " + searchIndex);
        } else {
            System.out.println(searchValue + " not found");
        }

        // ========== MULTIPLICATION TABLE ==========
        System.out.println("\n--- Multiplication Table (7) ---");
        int multiplier = 7;
        int counter = 1;
        while (counter <= 10) {
            System.out.println(multiplier + " x " + counter + " = " + (multiplier * counter));
            counter++;
        }

        // ========== BINARY REPRESENTATION ==========
        System.out.println("\n--- Binary Representation ---");
        int decimal = 25;
        String binary = "";
        int tempDec = decimal;
        while (tempDec > 0) {
            binary = (tempDec % 2) + binary;
            tempDec /= 2;
        }
        System.out.println("Decimal: " + decimal);
        System.out.println("Binary: " + binary);

        System.out.println("\nWhile Loop Demo completed!");
    }
}
