package com.fundamentals.collections;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Stack Class
 * 
 * Stack is a legacy class that represents a LIFO (Last-In-First-Out) stack.
 * It extends Vector and provides stack operations.
 * 
 * Key Characteristics:
 * - LIFO: Last element added is first to be removed
 * - Legacy Class: Part of original Java 1.0
 * - Thread-Safe: Synchronized methods (slower)
 * - Extends Vector: Inherits all Vector methods
 * 
 * Note: Modern Java recommends using Deque instead of Stack
 * - ArrayDeque is faster and not synchronized
 * - Deque provides more consistent interface
 * 
 * Stack Methods:
 * - push(E item): Add element to top
 * - pop(): Remove and return top element
 * - peek(): View top element without removing
 * - empty(): Check if stack is empty
 * - search(Object o): Get 1-based position from top
 * 
 * Common Use Cases:
 * - Expression evaluation
 * - Undo/Redo functionality
 * - Backtracking algorithms
 * - Function call stack simulation
 * - Parentheses matching
 */
public class StackOperations {

    public static void main(String[] args) {
        System.out.println("=== Stack Class ===\n");

        demonstrateBasicStack();
        demonstrateStackMethods();
        demonstrateParenthesesMatching();
        demonstrateReverseString();
        demonstrateStackVsDeque();
    }

    /**
     * Demonstrate basic stack operations
     */
    private static void demonstrateBasicStack() {
        System.out.println("--- 1. Basic Stack Operations ---");
        System.out.println("Stack follows LIFO (Last-In-First-Out)\n");

        Stack<String> plates = new Stack<>();

        // Pushing elements
        System.out.println("Stacking plates:");
        plates.push("Plate 1");
        plates.push("Plate 2");
        plates.push("Plate 3");
        plates.push("Plate 4");
        plates.push("Plate 5");

        System.out.println("Stack: " + plates);
        System.out.println("Size: " + plates.size());

        // Peeking at top
        System.out.println("\nPeeking at top:");
        System.out.println("Top plate: " + plates.peek());
        System.out.println("Stack unchanged: " + plates);

        // Popping elements
        System.out.println("\nRemoving plates (LIFO order):");
        while (!plates.empty()) {
            System.out.println("  Removed: " + plates.pop());
            if (!plates.empty()) {
                System.out.println("  Remaining: " + plates);
            }
        }

        System.out.println("\nStack is now empty: " + plates.empty());

        System.out.println();
    }

    /**
     * Demonstrate stack methods
     */
    private static void demonstrateStackMethods() {
        System.out.println("--- 2. Stack Methods ---");

        Stack<Integer> numbers = new Stack<>();

        // push
        System.out.println("Pushing numbers:");
        numbers.push(10);
        numbers.push(20);
        numbers.push(30);
        numbers.push(40);
        numbers.push(50);

        System.out.println("Stack: " + numbers);

        // peek
        System.out.println("\npeek(): " + numbers.peek());

        // search (1-based position from top)
        System.out.println("\nSearching:");
        System.out.println("Position of 50 (top): " + numbers.search(50));
        System.out.println("Position of 40: " + numbers.search(40));
        System.out.println("Position of 10 (bottom): " + numbers.search(10));
        System.out.println("Position of 99 (not found): " + numbers.search(99));

        // empty
        System.out.println("\nempty(): " + numbers.empty());

        // pop
        System.out.println("\nPopping:");
        System.out.println("Popped: " + numbers.pop());
        System.out.println("Popped: " + numbers.pop());
        System.out.println("After popping: " + numbers);

        // Exception handling
        System.out.println("\nException handling:");
        Stack<String> emptyStack = new Stack<>();
        try {
            emptyStack.pop(); // Throws EmptyStackException
        } catch (EmptyStackException e) {
            System.out.println("pop() on empty stack: " + e.getClass().getSimpleName());
        }

        try {
            emptyStack.peek(); // Throws EmptyStackException
        } catch (EmptyStackException e) {
            System.out.println("peek() on empty stack: " + e.getClass().getSimpleName());
        }

        System.out.println();
    }

    /**
     * Demonstrate parentheses matching using stack
     */
    private static void demonstrateParenthesesMatching() {
        System.out.println("--- 3. Parentheses Matching ---");
        System.out.println("Using stack to check balanced parentheses\n");

        String[] expressions = {
                "(a + b)",
                "((a + b) * c)",
                "{[a + (b * c)]}",
                "(a + b))",
                "((a + b)",
                "{[a + b]}"
        };

        for (String expr : expressions) {
            boolean balanced = isBalanced(expr);
            System.out.println(expr + " -> " + (balanced ? "Balanced" : "Not Balanced"));
        }

        System.out.println();
    }

    /**
     * Check if parentheses are balanced
     */
    private static boolean isBalanced(String expression) {
        Stack<Character> stack = new Stack<>();

        for (char ch : expression.toCharArray()) {
            // Push opening brackets
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            }
            // Check closing brackets
            else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.empty()) {
                    return false;
                }
                char top = stack.pop();
                if (!isMatchingPair(top, ch)) {
                    return false;
                }
            }
        }

        return stack.empty();
    }

    /**
     * Check if opening and closing brackets match
     */
    private static boolean isMatchingPair(char opening, char closing) {
        return (opening == '(' && closing == ')') ||
                (opening == '[' && closing == ']') ||
                (opening == '{' && closing == '}');
    }

    /**
     * Demonstrate reversing a string using stack
     */
    private static void demonstrateReverseString() {
        System.out.println("--- 4. Reverse String Using Stack ---");

        String original = "Hello World";
        String reversed = reverseString(original);

        System.out.println("Original: " + original);
        System.out.println("Reversed: " + reversed);

        System.out.println();
    }

    /**
     * Reverse a string using stack
     */
    private static String reverseString(String str) {
        Stack<Character> stack = new Stack<>();

        // Push all characters
        for (char ch : str.toCharArray()) {
            stack.push(ch);
        }

        // Pop all characters
        StringBuilder reversed = new StringBuilder();
        while (!stack.empty()) {
            reversed.append(stack.pop());
        }

        return reversed.toString();
    }

    /**
     * Compare Stack vs Deque
     */
    private static void demonstrateStackVsDeque() {
        System.out.println("--- 5. Stack vs Deque ---");

        System.out.println("Stack (Legacy):");
        System.out.println("  + Simple API (push, pop, peek)");
        System.out.println("  + Thread-safe (synchronized)");
        System.out.println("  - Slower due to synchronization");
        System.out.println("  - Extends Vector (legacy)");
        System.out.println("  - Not recommended for new code");

        System.out.println("\nDeque (Modern Alternative):");
        System.out.println("  + Faster (not synchronized)");
        System.out.println("  + More flexible (double-ended)");
        System.out.println("  + Consistent with Collections Framework");
        System.out.println("  + Recommended for new code");
        System.out.println("  - Requires explicit synchronization if needed");

        System.out.println("\nRecommendation:");
        System.out.println("  Use ArrayDeque instead of Stack:");
        System.out.println("    Deque<String> stack = new ArrayDeque<>();");
        System.out.println("    stack.push(item);  // Same as Stack");
        System.out.println("    stack.pop();       // Same as Stack");
        System.out.println("    stack.peek();      // Same as Stack");

        System.out.println("\nWhen to use Stack:");
        System.out.println("  - Legacy code maintenance");
        System.out.println("  - When thread-safety is required");
        System.out.println("  - Simple stack operations only");

        System.out.println("\nExercise completed!");
    }
}
