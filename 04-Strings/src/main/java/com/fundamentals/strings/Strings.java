package com.fundamentals.strings;

/**
 * Comprehensive String Methods in Java
 * 
 * String is immutable - once created, cannot be changed
 * - Every modification creates a new String object
 * - Stored in String Pool for memory efficiency
 * - Thread-safe due to immutability
 * 
 * Key characteristics:
 * - Implements CharSequence, Serializable, Comparable
 * - Final class (cannot be extended)
 * - Uses UTF-16 encoding internally
 */
public class Strings {

    public static void main(String[] args) {
        System.out.println("=== Comprehensive String Methods ===\n");

        demonstrateCreation();
        demonstrateInspection();
        demonstrateComparison();
        demonstrateSearching();
        demonstrateExtraction();
        demonstrateModification();
        demonstrateFormatting();
        demonstrateSplitAndJoin();
        demonstrateTricksAndPitfalls();
    }

    /**
     * String creation methods
     */
    private static void demonstrateCreation() {
        System.out.println("--- String Creation ---");

        // Literal (recommended - uses String Pool)
        String s1 = "Hello";
        String s2 = "Hello"; // Points to same object in pool

        // Using new (creates new object, not recommended)
        String s3 = new String("Hello");

        // From char array
        char[] chars = { 'J', 'a', 'v', 'a' };
        String s4 = new String(chars);

        // From bytes
        byte[] bytes = { 72, 101, 108, 108, 111 };
        String s5 = new String(bytes);

        System.out.println("Literal: " + s1);
        System.out.println("From char[]: " + s4);
        System.out.println("From bytes: " + s5);
        System.out.println("s1 == s2 (same reference): " + (s1 == s2)); // true
        System.out.println("s1 == s3 (different reference): " + (s1 == s3)); // false
    }

    /**
     * String inspection methods
     */
    private static void demonstrateInspection() {
        System.out.println("\n--- String Inspection ---");

        String text = "Hello, Java!";

        // Length and emptiness
        System.out.println("length(): " + text.length()); // 12
        System.out.println("isEmpty(): " + text.isEmpty()); // false
        System.out.println("isBlank() [Java 11+]: " + "   ".isBlank()); // true

        // Character access
        System.out.println("charAt(0): " + text.charAt(0)); // H
        System.out.println("codePointAt(0): " + text.codePointAt(0)); // 72 (ASCII)

        // Convert to array
        char[] charArray = text.toCharArray();
        System.out.println("toCharArray()[0]: " + charArray[0]); // H

        byte[] byteArray = text.getBytes();
        System.out.println("getBytes().length: " + byteArray.length);
    }

    /**
     * String comparison methods
     */
    private static void demonstrateComparison() {
        System.out.println("\n--- String Comparison ---");

        String s1 = "Java";
        String s2 = "Java";
        String s3 = "java";
        String s4 = "JavaScript";

        // Equality
        System.out.println("equals(): " + s1.equals(s2)); // true
        System.out.println("equals() case-sensitive: " + s1.equals(s3)); // false
        System.out.println("equalsIgnoreCase(): " + s1.equalsIgnoreCase(s3)); // true

        // Comparison (lexicographic)
        System.out.println("compareTo(): " + s1.compareTo(s4)); // negative (Java < JavaScript)
        System.out.println("compareToIgnoreCase(): " + s1.compareToIgnoreCase(s3)); // 0

        // Content check
        System.out.println("contentEquals(): " + s1.contentEquals("Java")); // true

        // BEST PRACTICE: Always use .equals(), not ==
        System.out.println("\nAlways use .equals() for String comparison!");
    }

    /**
     * String searching methods
     */
    private static void demonstrateSearching() {
        System.out.println("\n--- String Searching ---");

        String text = "Java Programming in Java";

        // Contains
        System.out.println("contains('Java'): " + text.contains("Java")); // true
        System.out.println("contains('Python'): " + text.contains("Python")); // false

        // Starts/Ends with
        System.out.println("startsWith('Java'): " + text.startsWith("Java")); // true
        System.out.println("endsWith('Java'): " + text.endsWith("Java")); // true

        // Index searching
        System.out.println("indexOf('Java'): " + text.indexOf("Java")); // 0 (first occurrence)
        System.out.println("lastIndexOf('Java'): " + text.lastIndexOf("Java")); // 20 (last occurrence)
        System.out.println("indexOf('Java', 5): " + text.indexOf("Java", 5)); // 20 (from index 5)
        System.out.println("indexOf('Python'): " + text.indexOf("Python")); // -1 (not found)

        // TRICK: Check if string contains substring
        boolean hasJava = text.indexOf("Java") != -1;
        System.out.println("Has 'Java': " + hasJava);
    }

    /**
     * String extraction methods
     */
    private static void demonstrateExtraction() {
        System.out.println("\n--- String Extraction ---");

        String text = "Hello, World!";

        // Substring
        System.out.println("substring(0, 5): " + text.substring(0, 5)); // Hello
        System.out.println("substring(7): " + text.substring(7)); // World!

        // SubSequence
        CharSequence seq = text.subSequence(0, 5);
        System.out.println("subSequence(0, 5): " + seq); // Hello

        // TRICK: Get last N characters
        String last5 = text.substring(text.length() - 5);
        System.out.println("Last 5 chars: " + last5); // orld!

        // TRICK: Get first N characters
        String first5 = text.substring(0, Math.min(5, text.length()));
        System.out.println("First 5 chars: " + first5); // Hello
    }

    /**
     * String modification methods (returns new String)
     */
    private static void demonstrateModification() {
        System.out.println("\n--- String Modification ---");

        String text = "  Hello, Java!  ";

        // Case conversion
        System.out.println("toUpperCase(): " + text.toUpperCase());
        System.out.println("toLowerCase(): " + text.toLowerCase());

        // Trimming
        System.out.println("trim(): '" + text.trim() + "'"); // Removes leading/trailing spaces
        System.out.println("strip() [Java 11+]: '" + text.strip() + "'"); // Unicode-aware trim
        System.out.println("stripLeading(): '" + text.stripLeading() + "'");
        System.out.println("stripTrailing(): '" + text.stripTrailing() + "'");

        // Replace
        String original = "Java is great. Java is powerful.";
        System.out.println("\nOriginal: " + original);
        System.out.println("replace('Java', 'Python'): " + original.replace("Java", "Python"));
        System.out.println("replaceFirst('Java', 'Python'): " + original.replaceFirst("Java", "Python"));
        System.out.println("replaceAll('[aeiou]', '*'): " + original.replaceAll("[aeiou]", "*")); // Regex

        // Repeat (Java 11+)
        System.out.println("\n\"Ha\".repeat(3): " + "Ha".repeat(3)); // HaHaHa

        // Concat
        System.out.println("concat(): " + "Hello".concat(" World"));

        // BEST PRACTICE: String is immutable
        String immutable = "Hello";
        immutable.toUpperCase(); // This doesn't change 'immutable'
        System.out.println("\nAfter toUpperCase() (not assigned): " + immutable); // Still "Hello"
        immutable = immutable.toUpperCase(); // Must reassign
        System.out.println("After reassignment: " + immutable); // "HELLO"
    }

    /**
     * String formatting methods
     */
    private static void demonstrateFormatting() {
        System.out.println("\n--- String Formatting ---");

        // String.format()
        String formatted = String.format("Name: %s, Age: %d, Score: %.2f", "Alice", 25, 95.5);
        System.out.println("format(): " + formatted);

        // formatted() - Java 15+
        String name = "Bob";
        int age = 30;
        String result = "Name: %s, Age: %d".formatted(name, age);
        System.out.println("formatted(): " + result);

        // Common format specifiers
        System.out.println("\nFormat specifiers:");
        System.out.println("%s (string): " + String.format("%s", "Hello"));
        System.out.println("%d (decimal): " + String.format("%d", 42));
        System.out.println("%f (float): " + String.format("%f", 3.14159));
        System.out.println("%.2f (2 decimals): " + String.format("%.2f", 3.14159));
        System.out.println("%10s (width 10): '" + String.format("%10s", "Hi") + "'");
        System.out.println("%-10s (left align): '" + String.format("%-10s", "Hi") + "'");

        // valueOf() - convert to String
        System.out.println("\nvalueOf(123): " + String.valueOf(123));
        System.out.println("valueOf(true): " + String.valueOf(true));
        System.out.println("valueOf(3.14): " + String.valueOf(3.14));
    }

    /**
     * Split and Join operations
     */
    private static void demonstrateSplitAndJoin() {
        System.out.println("\n--- Split and Join ---");

        // Split
        String csv = "Java,Python,JavaScript,C++";
        String[] languages = csv.split(",");
        System.out.println("split(','):");
        for (String lang : languages) {
            System.out.println("  - " + lang);
        }

        // Split with limit
        String[] limited = csv.split(",", 2);
        System.out.println("\nsplit(',', 2): " + java.util.Arrays.toString(limited));

        // Join (Java 8+)
        String joined = String.join(" | ", languages);
        System.out.println("\njoin(' | '): " + joined);

        // Join with collection
        java.util.List<String> list = java.util.Arrays.asList("A", "B", "C");
        String joinedList = String.join("-", list);
        System.out.println("join with List: " + joinedList);

        // TRICK: Split by multiple delimiters (regex)
        String mixed = "Java;Python,JavaScript:C++";
        String[] parts = mixed.split("[;,:]");
        System.out.println("\nSplit by multiple delimiters: " + java.util.Arrays.toString(parts));
    }

    /**
     * Important tricks and pitfalls
     */
    private static void demonstrateTricksAndPitfalls() {
        System.out.println("\n--- Tricks & Pitfalls ---");

        // PITFALL 1: String concatenation in loops
        System.out.println("PITFALL: Don't use + in loops!");
        String bad = "";
        for (int i = 0; i < 3; i++) {
            bad += i; // Creates new String each time - SLOW!
        }
        System.out.println("Result: " + bad + " (but inefficient)");
        System.out.println("Use StringBuilder instead!");

        // PITFALL 2: == vs .equals()
        String s1 = "Java";
        String s2 = new String("Java");
        System.out.println("\n== checks reference: " + (s1 == s2)); // false
        System.out.println(".equals() checks value: " + s1.equals(s2)); // true

        // PITFALL 3: NullPointerException
        String nullStr = null;
        try {
            System.out.println(nullStr.length()); // NPE!
        } catch (NullPointerException e) {
            System.out.println("\nNPE when calling method on null String!");
        }

        // TRICK 1: Null-safe comparison
        String safe = "Java";
        System.out.println("Null-safe: " + safe.equals(nullStr)); // false (no NPE)
        // System.out.println(nullStr.equals(safe)); // Would throw NPE!

        // TRICK 2: intern() for String Pool
        String s3 = new String("Hello").intern(); // Adds to pool
        String s4 = "Hello";
        System.out.println("\nintern() adds to pool: " + (s3 == s4)); // true

        // TRICK 3: Check empty or null
        String empty = "";
        System.out.println("\nEmpty check: " + (empty == null || empty.isEmpty()));
        System.out.println("Blank check: " + (empty == null || empty.isBlank()));

        // BEST PRACTICE: Use text blocks for multiline (Java 15+)
        String textBlock = """
                Line 1
                Line 2
                Line 3
                """;
        System.out.println("\nText block (Java 15+):");
        System.out.print(textBlock);

        System.out.println("Exercise completed!");
    }
}
