package com.fundamentals.conditionals;

public class SwitchDemo {
    public static void main(String[] args) {
        System.out.println("=== Switch Statement Demo ===\n");

        // ========== BASIC SWITCH ==========
        System.out.println("========== BASIC SWITCH ==========");
        String day = "Monday";

        switch (day) {
            case "Monday":
                System.out.println("Day: " + day + " → Start of the work week 💼");
                break;
            case "Tuesday":
                System.out.println("Day: " + day + " → Second day of work");
                break;
            case "Wednesday":
                System.out.println("Day: " + day + " → Midweek!");
                break;
            case "Thursday":
                System.out.println("Day: " + day + " → Almost Friday");
                break;
            case "Friday":
                System.out.println("Day: " + day + " → End of work week! 🎉");
                break;
            case "Saturday":
            case "Sunday":
                System.out.println("Day: " + day + " → Weekend! 🏖️");
                break;
            default:
                System.out.println("Invalid day");
        }

        // ========== SWITCH WITH INTEGERS ==========
        System.out.println("\n========== SWITCH WITH INTEGERS ==========");
        int month = 7;
        String monthName;

        switch (month) {
            case 1:
                monthName = "January";
                break;
            case 2:
                monthName = "February";
                break;
            case 3:
                monthName = "March";
                break;
            case 4:
                monthName = "April";
                break;
            case 5:
                monthName = "May";
                break;
            case 6:
                monthName = "June";
                break;
            case 7:
                monthName = "July";
                break;
            case 8:
                monthName = "August";
                break;
            case 9:
                monthName = "September";
                break;
            case 10:
                monthName = "October";
                break;
            case 11:
                monthName = "November";
                break;
            case 12:
                monthName = "December";
                break;
            default:
                monthName = "Invalid month";
        }
        System.out.println("Month " + month + " → " + monthName);

        // ========== DAYS IN MONTH ==========
        System.out.println("\n========== DAYS IN MONTH ==========");
        int monthNumber = 2;
        int year = 2024;
        int daysInMonth;

        switch (monthNumber) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                daysInMonth = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                daysInMonth = 30;
                break;
            case 2:
                // Check for leap year
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                    daysInMonth = 29;
                } else {
                    daysInMonth = 28;
                }
                break;
            default:
                daysInMonth = 0;
        }
        System.out.println("Month " + monthNumber + " in year " + year + " has " + daysInMonth + " days");

        // ========== CALCULATOR ==========
        System.out.println("\n========== CALCULATOR ==========");
        int num1 = 10;
        int num2 = 5;
        char operator = '+';
        double result;

        System.out.println("Expression: " + num1 + " " + operator + " " + num2);
        switch (operator) {
            case '+':
                result = num1 + num2;
                System.out.println("Result: " + result);
                break;
            case '-':
                result = num1 - num2;
                System.out.println("Result: " + result);
                break;
            case '*':
                result = num1 * num2;
                System.out.println("Result: " + result);
                break;
            case '/':
                if (num2 != 0) {
                    result = (double) num1 / num2;
                    System.out.println("Result: " + result);
                } else {
                    System.out.println("Error: Division by zero!");
                }
                break;
            case '%':
                result = num1 % num2;
                System.out.println("Result: " + result);
                break;
            default:
                System.out.println("Invalid operator!");
        }

        // ========== VOWEL CHECKER ==========
        System.out.println("\n========== VOWEL CHECKER ==========");
        char letter = 'e';

        System.out.println("Letter: " + letter);
        switch (letter) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
            case 'A':
            case 'E':
            case 'I':
            case 'O':
            case 'U':
                System.out.println("→ '" + letter + "' is a vowel");
                break;
            default:
                System.out.println("→ '" + letter + "' is a consonant");
        }

        // ========== SEASON BY MONTH ==========
        System.out.println("\n========== SEASON BY MONTH ==========");
        int seasonMonth = 4;
        String season;

        switch (seasonMonth) {
            case 12:
            case 1:
            case 2:
                season = "Winter";
                break;
            case 3:
            case 4:
            case 5:
                season = "Spring";
                break;
            case 6:
            case 7:
            case 8:
                season = "Summer";
                break;
            case 9:
            case 10:
            case 11:
                season = "Fall";
                break;
            default:
                season = "Invalid month";
        }
        System.out.println("Month " + seasonMonth + " → Season: " + season);

        // ========== HTTP STATUS CODES ==========
        System.out.println("\n========== HTTP STATUS CODES ==========");
        int statusCode = 404;

        System.out.println("Status Code: " + statusCode);
        switch (statusCode) {
            case 200:
                System.out.println("→ OK: Request successful");
                break;
            case 201:
                System.out.println("→ Created: Resource created successfully");
                break;
            case 400:
                System.out.println("→ Bad Request: Invalid request");
                break;
            case 401:
                System.out.println("→ Unauthorized: Authentication required");
                break;
            case 403:
                System.out.println("→ Forbidden: Access denied");
                break;
            case 404:
                System.out.println("→ Not Found: Resource not found");
                break;
            case 500:
                System.out.println("→ Internal Server Error");
                break;
            default:
                System.out.println("→ Unknown status code");
        }

        // ========== MODERN SWITCH EXPRESSIONS ==========
        System.out.println("\n========== MODERN SWITCH EXPRESSIONS ==========");

        // ========== DAY NAME WITH ARROW SYNTAX ==========
        System.out.println("\n--- Day Name (Arrow Syntax) ---");
        int dayNumber = 5;

        String dayName = switch (dayNumber) {
            case 1 -> "Pazartesi";
            case 2 -> "Salı";
            case 3 -> "Çarşamba";
            case 4 -> "Perşembe";
            case 5 -> "Cuma";
            case 6 -> "Cumartesi";
            case 7 -> "Pazar";
            default -> "Geçersiz gün";
        };

        System.out.println("Gün " + dayNumber + " → " + dayName);

        // ========== MULTIPLE CASES WITH ARROW ==========
        System.out.println("\n--- Multiple Cases (Arrow Syntax) ---");
        String weekDay = "Saturday";

        String dayType = switch (weekDay) {
            case "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" -> "Weekday";
            case "Saturday", "Sunday" -> "Weekend";
            default -> "Invalid day";
        };

        System.out.println(weekDay + " is a " + dayType);

        // ========== CALCULATOR WITH SWITCH EXPRESSION ==========
        System.out.println("\n--- Calculator (Switch Expression) ---");
        int a = 15, b = 3;
        char op = '*';

        double calcResult = switch (op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> b != 0 ? (double) a / b : 0;
            case '%' -> a % b;
            default -> {
                System.out.println("Invalid operator!");
                yield 0.0;
            }
        };

        System.out.println(a + " " + op + " " + b + " = " + calcResult);

        // ========== GRADE WITH DESCRIPTION (MODERN) ==========
        System.out.println("\n--- Grade Description (Modern) ---");
        char modernGrade = 'A';

        String gradeDescription = switch (modernGrade) {
            case 'A' -> "Excellent!";
            case 'B' -> "Good!";
            case 'C' -> "Average";
            case 'D' -> "Below Average";
            case 'F' -> "Failing";
            default -> "Invalid grade";
        };

        System.out.println("Grade " + modernGrade + " → " + gradeDescription);

        // ========== SEASON WITH MODERN SWITCH ==========
        System.out.println("\n--- Season (Modern Switch) ---");
        int modernSeasonMonth = 7;

        String modernSeason = switch (modernSeasonMonth) {
            case 12, 1, 2 -> "Winter";
            case 3, 4, 5 -> "Spring";
            case 6, 7, 8 -> "Summer";
            case 9, 10, 11 -> "Fall";
            default -> "Invalid month";
        };

        System.out.println("Month " + modernSeasonMonth + " → " + modernSeason);

        // ========== DAYS IN MONTH (MODERN) ==========
        System.out.println("\n--- Days in Month (Modern) ---");
        int modernMonthNum = 2;
        int modernYear = 2024;

        int modernDaysInMonth = switch (modernMonthNum) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11 -> 30;
            case 2 -> {
                boolean isLeap = (modernYear % 4 == 0 && modernYear % 100 != 0) || (modernYear % 400 == 0);
                yield isLeap ? 29 : 28;
            }
            default -> 0;
        };

        System.out.println("Month " + modernMonthNum + " in " + modernYear + " has " + modernDaysInMonth + " days");

        // ========== NUMERIC RANGE EXAMPLE ==========
        System.out.println("\n--- Score to Grade (Modern) ---");
        int examScore = 88;

        String examGrade = switch (examScore / 10) {
            case 10, 9 -> "A";
            case 8 -> "B";
            case 7 -> "C";
            case 6 -> "D";
            default -> "F";
        };

        System.out.println("Score: " + examScore + " → Grade: " + examGrade);

        // ========== YIELD STATEMENT EXAMPLE ==========
        System.out.println("\n--- Yield Statement Example ---");
        int points = 850;

        String membership = switch (points / 100) {
            case 10, 9, 8, 7, 6, 5 -> {
                System.out.println("  Processing Gold membership...");
                yield "Gold";
            }
            case 4, 3, 2 -> {
                System.out.println("  Processing Silver membership...");
                yield "Silver";
            }
            case 1 -> {
                System.out.println("  Processing Bronze membership...");
                yield "Bronze";
            }
            default -> {
                System.out.println("  Processing Basic membership...");
                yield "Basic";
            }
        };

        System.out.println("Points: " + points + " → Membership: " + membership);

        // ========== COMPARISON: OLD vs NEW ==========
        System.out.println("\n--- Comparison: Traditional vs Modern ---");
        int comparisonDay = 3;

        // Traditional switch
        String traditionalResult;
        switch (comparisonDay) {
            case 1:
                traditionalResult = "Monday";
                break;
            case 2:
                traditionalResult = "Tuesday";
                break;
            case 3:
                traditionalResult = "Wednesday";
                break;
            default:
                traditionalResult = "Other";
        }

        // Modern switch expression
        String modernResult = switch (comparisonDay) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            default -> "Other";
        };

        System.out.println("Traditional: " + traditionalResult);
        System.out.println("Modern: " + modernResult);
        System.out.println("Both produce same result: " + traditionalResult.equals(modernResult));

        // ========== ENUM-LIKE BEHAVIOR ==========
        System.out.println("\n--- Priority Level ---");
        String priority = "HIGH";

        int priorityValue = switch (priority.toUpperCase()) {
            case "CRITICAL" -> 1;
            case "HIGH" -> 2;
            case "MEDIUM" -> 3;
            case "LOW" -> 4;
            default -> 5;
        };

        System.out.println("Priority: " + priority + " → Value: " + priorityValue);
    }
}
