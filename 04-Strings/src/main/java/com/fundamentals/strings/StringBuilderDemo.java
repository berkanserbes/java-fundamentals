package com.fundamentals.strings;

/**
 * StringBuilder and StringBuffer in Java
 * 
 * StringBuilder vs String:
 * - StringBuilder is MUTABLE (can be modified)
 * - Much faster for concatenation in loops
 * - Not thread-safe (use StringBuffer for thread-safety)
 * - Doesn't use String Pool
 * 
 * StringBuilder vs StringBuffer:
 * - StringBuilder: Faster, not synchronized (use in single-threaded)
 * - StringBuffer: Slower, synchronized (use in multi-threaded)
 * 
 * When to use:
 * - Use String for immutable text
 * - Use StringBuilder for string building/manipulation
 * - Use StringBuffer only when thread-safety is needed
 */
public class StringBuilderDemo {

    public static void main(String[] args) {
        System.out.println("=== StringBuilder & StringBuffer ===\n");

        demonstrateCreation();
        demonstrateAppend();
        demonstrateInsertAndDelete();
        demonstrateReplace();
        demonstrateReverse();
        demonstrateCapacity();
        demonstratePerformance();
        demonstrateBestPractices();
    }

    /**
     * Creating StringBuilder
     */
    private static void demonstrateCreation() {
        System.out.println("--- StringBuilder Creation ---");

        // Empty StringBuilder (initial capacity 16)
        StringBuilder sb1 = new StringBuilder();

        // With initial capacity
        StringBuilder sb2 = new StringBuilder(50);

        // From String
        StringBuilder sb3 = new StringBuilder("Hello");

        // From CharSequence
        StringBuilder sb4 = new StringBuilder("World");

        System.out.println("Empty: '" + sb1 + "'");
        System.out.println("From String: '" + sb3 + "'");
        System.out.println("Initial capacity: " + sb2.capacity());

        // StringBuffer (same API, but synchronized)
        StringBuffer sbuf = new StringBuffer("Thread-safe");
        System.out.println("StringBuffer: '" + sbuf + "'");
    }

    /**
     * Append operations
     */
    private static void demonstrateAppend() {
        System.out.println("\n--- Append Operations ---");

        StringBuilder sb = new StringBuilder("Hello");

        // Append different types
        sb.append(" World"); // String
        sb.append('!'); // char
        sb.append(123); // int
        sb.append(3.14); // double
        sb.append(true); // boolean

        System.out.println("After appends: " + sb);

        // Method chaining
        StringBuilder chain = new StringBuilder();
        chain.append("Java")
                .append(" ")
                .append("is")
                .append(" ")
                .append("awesome!");

        System.out.println("Method chaining: " + chain);

        // Append char array
        char[] chars = { 'A', 'B', 'C' };
        sb.append(chars);
        System.out.println("After char[]: " + sb);

        // BEST PRACTICE: Use StringBuilder for concatenation in loops
        StringBuilder loop = new StringBuilder();
        for (int i = 1; i <= 5; i++) {
            loop.append(i).append(" ");
        }
        System.out.println("Loop result: " + loop.toString().trim());
    }

    /**
     * Insert and Delete operations
     */
    private static void demonstrateInsertAndDelete() {
        System.out.println("\n--- Insert & Delete ---");

        StringBuilder sb = new StringBuilder("Hello World");

        // Insert at position
        sb.insert(5, ",");
        System.out.println("insert(5, ','): " + sb); // Hello, World

        sb.insert(0, "Say: ");
        System.out.println("insert(0, 'Say: '): " + sb); // Say: Hello, World

        // Delete range
        sb.delete(0, 5);
        System.out.println("delete(0, 5): " + sb); // Hello, World

        // Delete single char
        sb.deleteCharAt(5);
        System.out.println("deleteCharAt(5): " + sb); // Hello World

        // TRICK: Clear StringBuilder
        sb.delete(0, sb.length());
        System.out.println("Cleared: '" + sb + "'");

        // Alternative: setLength(0)
        sb.append("Test");
        sb.setLength(0);
        System.out.println("setLength(0): '" + sb + "'");
    }

    /**
     * Replace operations
     */
    private static void demonstrateReplace() {
        System.out.println("\n--- Replace Operations ---");

        StringBuilder sb = new StringBuilder("Java is good");

        // Replace range
        sb.replace(8, 12, "awesome");
        System.out.println("replace(8, 12, 'awesome'): " + sb); // Java is awesome

        // Replace single char
        sb.setCharAt(0, 'j');
        System.out.println("setCharAt(0, 'j'): " + sb); // java is awesome

        // TRICK: Replace all occurrences (manual)
        StringBuilder text = new StringBuilder("Java Java Java");
        String target = "Java";
        String replacement = "Python";
        int index = 0;
        while ((index = text.indexOf(target, index)) != -1) {
            text.replace(index, index + target.length(), replacement);
            index += replacement.length();
        }
        System.out.println("Replace all 'Java': " + text);
    }

    /**
     * Reverse operation
     */
    private static void demonstrateReverse() {
        System.out.println("\n--- Reverse ---");

        StringBuilder sb = new StringBuilder("Hello");
        sb.reverse();
        System.out.println("reverse(): " + sb); // olleH

        // TRICK: Check if palindrome
        String word = "racecar";
        String reversed = new StringBuilder(word).reverse().toString();
        boolean isPalindrome = word.equals(reversed);
        System.out.println("'" + word + "' is palindrome: " + isPalindrome);

        // TRICK: Reverse words in sentence
        String sentence = "Hello World Java";
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]);
            if (i > 0)
                result.append(" ");
        }
        System.out.println("Reversed words: " + result);
    }

    /**
     * Capacity management
     */
    private static void demonstrateCapacity() {
        System.out.println("\n--- Capacity Management ---");

        StringBuilder sb = new StringBuilder();
        System.out.println("Initial capacity: " + sb.capacity()); // 16
        System.out.println("Initial length: " + sb.length()); // 0

        // Add text
        sb.append("Hello");
        System.out.println("\nAfter 'Hello':");
        System.out.println("  capacity: " + sb.capacity()); // Still 16
        System.out.println("  length: " + sb.length()); // 5

        // Exceed capacity - auto-grows
        sb.append("This is a longer text that exceeds initial capacity");
        System.out.println("\nAfter long text:");
        System.out.println("  capacity: " + sb.capacity()); // Auto-expanded
        System.out.println("  length: " + sb.length());

        // Manual capacity control
        sb.ensureCapacity(100);
        System.out.println("\nAfter ensureCapacity(100): " + sb.capacity());

        // Trim to size
        sb.trimToSize();
        System.out.println("After trimToSize(): " + sb.capacity());

        // BEST PRACTICE: Set initial capacity if you know size
        StringBuilder optimized = new StringBuilder(1000);
        System.out.println("\nOptimized initial capacity: " + optimized.capacity());
    }

    /**
     * Performance comparison
     */
    private static void demonstratePerformance() {
        System.out.println("\n--- Performance Comparison ---");

        int iterations = 10000;

        // String concatenation (SLOW)
        long start1 = System.nanoTime();
        String str = "";
        for (int i = 0; i < iterations; i++) {
            str += "a"; // Creates new String each time!
        }
        long end1 = System.nanoTime();
        long stringTime = (end1 - start1) / 1_000_000;

        // StringBuilder (FAST)
        long start2 = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append("a"); // Modifies same object
        }
        long end2 = System.nanoTime();
        long sbTime = (end2 - start2) / 1_000_000;

        System.out.println("String concatenation: " + stringTime + " ms");
        System.out.println("StringBuilder: " + sbTime + " ms");
        System.out.println("StringBuilder is ~" + (stringTime / Math.max(sbTime, 1)) + "x faster!");

        System.out.println("\nBEST PRACTICE: Use StringBuilder for loops!");
    }

    /**
     * Best practices and common patterns
     */
    private static void demonstrateBestPractices() {
        System.out.println("\n--- Best Practices ---");

        // 1. Building CSV
        System.out.println("1. Building CSV:");
        String[] items = { "Apple", "Banana", "Cherry" };
        StringBuilder csv = new StringBuilder();
        for (int i = 0; i < items.length; i++) {
            csv.append(items[i]);
            if (i < items.length - 1) {
                csv.append(", ");
            }
        }
        System.out.println("   " + csv);

        // 2. Building SQL query
        System.out.println("\n2. Building SQL query:");
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM users ")
                .append("WHERE age > 18 ")
                .append("AND active = true ")
                .append("ORDER BY name");
        System.out.println("   " + sql);

        // 3. Building HTML
        System.out.println("\n3. Building HTML:");
        StringBuilder html = new StringBuilder();
        html.append("<div>")
                .append("<h1>Title</h1>")
                .append("<p>Content</p>")
                .append("</div>");
        System.out.println("   " + html);

        // 4. Removing characters
        System.out.println("\n4. Removing vowels:");
        StringBuilder text = new StringBuilder("Hello World");
        String vowels = "aeiouAEIOU";
        for (int i = text.length() - 1; i >= 0; i--) {
            if (vowels.indexOf(text.charAt(i)) != -1) {
                text.deleteCharAt(i);
            }
        }
        System.out.println("   " + text);

        // 5. Converting to String
        System.out.println("\n5. Converting to String:");
        StringBuilder sb = new StringBuilder("Test");
        String result1 = sb.toString(); // Recommended
        String result2 = String.valueOf(sb); // Alternative
        System.out.println("   toString(): " + result1);
        System.out.println("   valueOf(): " + result2);

        // Summary
        System.out.println("\n--- Summary ---");
        System.out.println("Use String: For immutable text");
        System.out.println("Use StringBuilder: For string building (single-threaded)");
        System.out.println("Use StringBuffer: For string building (multi-threaded)");
        System.out.println("Always use StringBuilder in loops!");

        System.out.println("\nExercise completed!");
    }
}
