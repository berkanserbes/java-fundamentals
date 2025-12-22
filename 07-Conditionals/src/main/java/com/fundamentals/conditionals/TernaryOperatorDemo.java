package com.fundamentals.conditionals;

public class TernaryOperatorDemo {
    public static void main(String[] args) {
        System.out.println("=== Ternary Operator Demo ===\n");

        // ========== BASIC TERNARY ==========
        System.out.println("========== BASIC TERNARY ==========");
        int age = 18;
        String status = (age >= 18) ? "Adult" : "Minor";
        System.out.println("Age: " + age + " → Status: " + status);

        // ========== MAX OF TWO NUMBERS ==========
        System.out.println("\n========== MAX OF TWO NUMBERS ==========");
        int a = 15, b = 20;
        int max = (a > b) ? a : b;
        System.out.println("a = " + a + ", b = " + b);
        System.out.println("Maximum: " + max);

        // ========== ABSOLUTE VALUE ==========
        System.out.println("\n========== ABSOLUTE VALUE ==========");
        int number = -42;
        int absolute = (number < 0) ? -number : number;
        System.out.println("Number: " + number + " → Absolute: " + absolute);

        // ========== EVEN OR ODD ==========
        System.out.println("\n========== EVEN OR ODD ==========");
        int num = 17;
        String parity = (num % 2 == 0) ? "Even" : "Odd";
        System.out.println("Number: " + num + " → " + parity);

        // ========== DISCOUNT ELIGIBILITY ==========
        System.out.println("\n========== DISCOUNT ELIGIBILITY ==========");
        double purchaseAmount = 120.0;
        double discount = (purchaseAmount >= 100) ? 0.10 : 0.0;
        double finalAmount = purchaseAmount * (1 - discount);
        System.out.println("Purchase: $" + purchaseAmount);
        System.out.println("Discount: " + (discount * 100) + "%");
        System.out.println("Final Amount: $" + finalAmount);

        // ========== NESTED TERNARY ==========
        System.out.println("\n========== NESTED TERNARY ==========");
        int marks = 85;
        String grade = (marks >= 90) ? "A" : (marks >= 80) ? "B" : (marks >= 70) ? "C" : (marks >= 60) ? "D" : "F";
        System.out.println("Marks: " + marks + " → Grade: " + grade);

        // ========== SIGN CHECKER ==========
        System.out.println("\n========== SIGN CHECKER ==========");
        int value = -5;
        String sign = (value > 0) ? "Positive" : (value < 0) ? "Negative" : "Zero";
        System.out.println("Value: " + value + " → Sign: " + sign);

        // ========== STRING OPERATIONS ==========
        System.out.println("\n========== STRING OPERATIONS ==========");
        String username = "admin";
        String message = username.equals("admin") ? "Welcome, Administrator!" : "Welcome, User!";
        System.out.println("Username: " + username);
        System.out.println("Message: " + message);

        // ========== NULL CHECK ==========
        System.out.println("\n========== NULL CHECK ==========");
        String name = null;
        String displayName = (name != null) ? name : "Guest";
        System.out.println("Name: " + name);
        System.out.println("Display Name: " + displayName);

        // ========== LEAP YEAR CHECK ==========
        System.out.println("\n========== LEAP YEAR CHECK ==========");
        int year = 2024;
        boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        String leapStatus = isLeapYear ? "Leap Year" : "Not a Leap Year";
        System.out.println("Year: " + year + " → " + leapStatus);

        // ========== TEMPERATURE WARNING ==========
        System.out.println("\n========== TEMPERATURE WARNING ==========");
        int temperature = 35;
        String warning = (temperature > 30) ? "Hot! Stay hydrated"
                : (temperature < 10) ? "Cold! Dress warm" : "Pleasant weather";
        System.out.println("Temperature: " + temperature + "°C");
        System.out.println("Warning: " + warning);

        // ========== ARRAY INDEX VALIDATION ==========
        System.out.println("\n========== ARRAY INDEX VALIDATION ==========");
        int[] array = { 10, 20, 30, 40, 50 };
        int index = 3;
        String validationMsg = (index >= 0 && index < array.length) ? "Valid index. Value: " + array[index]
                : "Invalid index!";
        System.out.println("Array length: " + array.length);
        System.out.println("Index: " + index + " → " + validationMsg);

        // ========== FILE SIZE FORMATTER ==========
        System.out.println("\n========== FILE SIZE FORMATTER ==========");
        long fileSize = 2048; // bytes
        String formattedSize = (fileSize >= 1024 * 1024) ? (fileSize / (1024 * 1024)) + " MB"
                : (fileSize >= 1024) ? (fileSize / 1024) + " KB" : fileSize + " bytes";
        System.out.println("File size: " + formattedSize);

        // ========== PRACTICAL USE CASES ==========
        System.out.println("\n========== PRACTICAL USE CASES ==========");

        // Setting default values
        String userInput = "";
        String finalInput = (userInput.isEmpty()) ? "default" : userInput;
        System.out.println("User input: '" + userInput + "' → Final: '" + finalInput + "'");

        // Pluralization
        int itemCount = 1;
        String itemText = itemCount + " item" + ((itemCount != 1) ? "s" : "");
        System.out.println(itemText);

        System.out.println("\n Ternary Operator Demo completed!");
    }
}
