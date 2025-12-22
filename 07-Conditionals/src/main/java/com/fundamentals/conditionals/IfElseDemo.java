package com.fundamentals.conditionals;

public class IfElseDemo {
    public static void main(String[] args) {
        System.out.println("=== If-Else Statement Demo ===\n");

        // ========== SIMPLE IF ==========
        int temperature = 30;
        if (temperature > 25) {
            System.out.println("It's hot outside! Temperature: " + temperature + "°C");
        }

        // ========== IF-ELSE ==========
        int age = 18;
        if (age >= 18) {
            System.out.println("You are an adult (age: " + age + ")");
        } else {
            System.out.println("You are a minor (age: " + age + ")");
        }

        // ========== NESTED IF ==========
        int score = 85;
        boolean hasAttendance = true;

        if (score >= 60) {
            System.out.println("You passed! Score: " + score);
            if (hasAttendance) {
                System.out.println("✓ Good attendance bonus applied!");
            } else {
                System.out.println("⚠ Warning: Poor attendance");
            }
        } else {
            System.out.println("You failed. Score: " + score);
        }

        // ========== MULTIPLE CONDITIONS (AND) ==========
        int userAge = 25;
        boolean hasLicense = true;

        if (userAge >= 18 && hasLicense) {
            System.out.println("✓ You can rent a car");
        } else {
            System.out.println("✗ You cannot rent a car");
            if (userAge < 18) {
                System.out.println("  Reason: Under 18");
            }
            if (!hasLicense) {
                System.out.println("  Reason: No license");
            }
        }

        // ========== MULTIPLE CONDITIONS (OR) ==========
        String dayOfWeek = "Saturday";

        if (dayOfWeek.equals("Saturday") || dayOfWeek.equals("Sunday")) {
            System.out.println("It's the weekend! Day: " + dayOfWeek);
        } else {
            System.out.println("It's a weekday. Day: " + dayOfWeek);
        }

        // ========== COMPLEX CONDITIONS ==========
        int income = 50000;
        int creditScore = 720;
        int yearsEmployed = 3;

        if ((income >= 40000 && creditScore >= 700) || yearsEmployed >= 5) {
            System.out.println("✓ Loan approved!");
            System.out.println("  Income: $" + income);
            System.out.println("  Credit Score: " + creditScore);
            System.out.println("  Years Employed: " + yearsEmployed);
        } else {
            System.out.println("✗ Loan denied");
        }

        // ========== COMPARISON OPERATORS ==========
        int a = 10, b = 20;

        System.out.println("a = " + a + ", b = " + b);
        if (a == b)
            System.out.println("a == b: true");
        else
            System.out.println("a == b: false");

        if (a != b)
            System.out.println("a != b: true");
        else
            System.out.println("a != b: false");

        if (a < b)
            System.out.println("a < b: true");
        else
            System.out.println("a < b: false");

        if (a > b)
            System.out.println("a > b: true");
        else
            System.out.println("a > b: false");

        if (a <= b)
            System.out.println("a <= b: true");
        else
            System.out.println("a <= b: false");

        if (a >= b)
            System.out.println("a >= b: true");
        else
            System.out.println("a >= b: false");

        // ========== STRING COMPARISON ==========
        String username = "admin";
        String password = "12345";

        // Correct way to compare strings
        if (username.equals("admin") && password.equals("12345")) {
            System.out.println("✓ Login successful!");
        } else {
            System.out.println("✗ Invalid credentials");
        }

        // Case-insensitive comparison
        String input = "HELLO";
        if (input.equalsIgnoreCase("hello")) {
            System.out.println("✓ Strings match (case-insensitive)");
        }

        // Example with non-null value
        String name2 = "Alice";
        if (name2 == null) {
            System.out.println("Name2 is null");
        } else {
            System.out.println("Name2: " + name2);
        }

        // Safe string comparison with null check
        String userInput = null; // Try changing to "test" to see the if branch
        if (userInput != null && userInput.equals("test")) {
            System.out.println("Input is 'test'");
        } else {
            System.out.println("Input is null or not 'test'");
        }

        // Example with actual value
        String userInput2 = "test";
        if (userInput2 != null && userInput2.equals("test")) {
            System.out.println("Input2 is 'test'");
        } else {
            System.out.println("Input2 is null or not 'test'");
        }
    }
}
