package com.fundamentals.conditionals;

public class ElseIfDemo {
    public static void main(String[] args) {
        System.out.println("=== Else-If Chain Demo ===\n");

        // ========== GRADE CALCULATOR ==========
        System.out.println("========== GRADE CALCULATOR ==========");
        int score = 85;

        if (score >= 90) {
            System.out.println("Score: " + score + " → Grade: A (Excellent!)");
        } else if (score >= 80) {
            System.out.println("Score: " + score + " → Grade: B (Good!)");
        } else if (score >= 70) {
            System.out.println("Score: " + score + " → Grade: C (Average)");
        } else if (score >= 60) {
            System.out.println("Score: " + score + " → Grade: D (Pass)");
        } else {
            System.out.println("Score: " + score + " → Grade: F (Fail)");
        }

        // ========== BMI CALCULATOR ==========
        System.out.println("\n========== BMI CALCULATOR ==========");
        double weight = 70; // kg
        double height = 1.75; // meters
        double bmi = weight / (height * height);

        System.out.println("Weight: " + weight + "kg, Height: " + height + "m");
        System.out.println("BMI: " + String.format("%.2f", bmi));

        if (bmi < 18.5) {
            System.out.println("Category: Underweight");
        } else if (bmi < 25) {
            System.out.println("Category: Normal weight");
        } else if (bmi < 30) {
            System.out.println("Category: Overweight");
        } else {
            System.out.println("Category: Obese");
        }

        // ========== TICKET PRICING ==========
        System.out.println("\n========== TICKET PRICING ==========");
        int customerAge = 45;
        double ticketPrice;

        if (customerAge < 3) {
            ticketPrice = 0.0;
            System.out.println("Age: " + customerAge + " → Free (Infant)");
        } else if (customerAge < 13) {
            ticketPrice = 10.0;
            System.out.println("Age: " + customerAge + " → $" + ticketPrice + " (Child)");
        } else if (customerAge < 18) {
            ticketPrice = 15.0;
            System.out.println("Age: " + customerAge + " → $" + ticketPrice + " (Teen)");
        } else if (customerAge < 65) {
            ticketPrice = 25.0;
            System.out.println("Age: " + customerAge + " → $" + ticketPrice + " (Adult)");
        } else {
            ticketPrice = 18.0;
            System.out.println("Age: " + customerAge + " → $" + ticketPrice + " (Senior)");
        }

        // ========== SHIPPING COST ==========
        System.out.println("\n========== SHIPPING COST ==========");
        double orderAmount = 75.0;
        double shippingCost;

        System.out.println("Order Amount: $" + orderAmount);
        if (orderAmount >= 100) {
            shippingCost = 0.0;
            System.out.println("Shipping: FREE! 🎉");
        } else if (orderAmount >= 50) {
            shippingCost = 5.0;
            System.out.println("Shipping: $" + shippingCost);
        } else if (orderAmount >= 25) {
            shippingCost = 10.0;
            System.out.println("Shipping: $" + shippingCost);
        } else {
            shippingCost = 15.0;
            System.out.println("Shipping: $" + shippingCost);
        }
        System.out.println("Total: $" + (orderAmount + shippingCost));

        // ========== TRAFFIC LIGHT ==========
        System.out.println("\n========== TRAFFIC LIGHT ==========");
        String lightColor = "yellow";

        System.out.println("Light color: " + lightColor);
        if (lightColor.equals("green")) {
            System.out.println("Action: GO! 🟢");
        } else if (lightColor.equals("yellow")) {
            System.out.println("Action: SLOW DOWN! 🟡");
        } else if (lightColor.equals("red")) {
            System.out.println("Action: STOP! 🔴");
        } else {
            System.out.println("Action: Unknown signal - proceed with caution");
        }

        // ========== EXAM RESULT WITH BONUS ==========
        System.out.println("\n========== EXAM RESULT WITH BONUS ==========");
        int examScore = 88;
        int bonusPoints = 5;
        int finalScore = examScore + bonusPoints;

        System.out.println("Exam Score: " + examScore);
        System.out.println("Bonus Points: " + bonusPoints);
        System.out.println("Final Score: " + finalScore);

        if (finalScore >= 95) {
            System.out.println("Result: A+ (Outstanding!)");
        } else if (finalScore >= 90) {
            System.out.println("Result: A (Excellent!)");
        } else if (finalScore >= 85) {
            System.out.println("Result: B+ (Very Good!)");
        } else if (finalScore >= 80) {
            System.out.println("Result: B (Good!)");
        } else if (finalScore >= 75) {
            System.out.println("Result: C+ (Above Average)");
        } else if (finalScore >= 70) {
            System.out.println("Result: C (Average)");
        } else if (finalScore >= 60) {
            System.out.println("Result: D (Pass)");
        } else {
            System.out.println("Result: F (Fail)");
        }

        System.out.println("\n✓ Else-If Demo completed!");
    }
}
