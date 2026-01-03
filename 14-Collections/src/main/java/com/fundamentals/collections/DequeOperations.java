package com.fundamentals.collections;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Deque Interface and Implementations
 * 
 * Deque (Double Ended Queue) is a linear collection that supports
 * element insertion and removal at both ends.
 * 
 * Key Characteristics:
 * - Double Ended: Can add/remove from both front and back
 * - Can be used as: Queue (FIFO), Stack (LIFO), or Deque
 * - No Random Access: Cannot access elements by index (except LinkedList)
 * 
 * Common Implementations:
 * 1. ArrayDeque - Resizable array, faster than LinkedList for most operations
 * 2. LinkedList - Doubly-linked list, implements both List and Deque
 * 
 * Deque Methods (First = Head, Last = Tail):
 * 
 * First Element (Head):
 * - addFirst(E e) / offerFirst(E e): Add at front
 * - removeFirst() / pollFirst(): Remove from front
 * - getFirst() / peekFirst(): View front
 * 
 * Last Element (Tail):
 * - addLast(E e) / offerLast(E e): Add at back
 * - removeLast() / pollLast(): Remove from back
 * - getLast() / peekLast(): View back
 * 
 * Stack Methods (LIFO):
 * - push(E e): Add to front (same as addFirst)
 * - pop(): Remove from front (same as removeFirst)
 * - peek(): View front (same as peekFirst)
 * 
 * Queue Methods (FIFO):
 * - offer(E e): Add to back (same as offerLast)
 * - poll(): Remove from front (same as pollFirst)
 * - peek(): View front (same as peekFirst)
 */
public class DequeOperations {

    public static void main(String[] args) {
        System.out.println("=== Deque Interface and Implementations ===\n");

        demonstrateBasicDeque();
        demonstrateDequeAsQueue();
        demonstrateDequeAsStack();
        demonstrateArrayDequeVsLinkedList();
        demonstrateDequeIterations();
    }

    /**
     * Demonstrate basic deque operations
     */
    private static void demonstrateBasicDeque() {
        System.out.println("--- 1. Basic Deque Operations ---");
        System.out.println("Deque allows insertion/removal at both ends\n");

        Deque<String> deque = new ArrayDeque<>();

        // Adding elements at both ends
        System.out.println("Adding elements:");
        deque.addFirst("B");
        deque.addLast("C");
        deque.addFirst("A");
        deque.addLast("D");

        System.out.println("Deque: " + deque);
        System.out.println("Expected order: [A, B, C, D]");

        // Viewing elements at both ends
        System.out.println("\nViewing elements:");
        System.out.println("First element: " + deque.getFirst());
        System.out.println("Last element: " + deque.getLast());
        System.out.println("Deque unchanged: " + deque);

        // Removing elements from both ends
        System.out.println("\nRemoving from both ends:");
        System.out.println("Remove first: " + deque.removeFirst());
        System.out.println("Remove last: " + deque.removeLast());
        System.out.println("After removal: " + deque);

        System.out.println();
    }

    /**
     * Demonstrate using Deque as Queue (FIFO)
     */
    private static void demonstrateDequeAsQueue() {
        System.out.println("--- 2. Deque as Queue (FIFO) ---");
        System.out.println("Add at tail, remove from head\n");

        Deque<Integer> queue = new ArrayDeque<>();

        // Enqueue (add at tail)
        System.out.println("Enqueueing:");
        queue.offerLast(10);
        queue.offerLast(20);
        queue.offerLast(30);
        queue.offerLast(40);

        System.out.println("Queue: " + queue);

        // Dequeue (remove from head)
        System.out.println("\nDequeueing (FIFO order):");
        while (!queue.isEmpty()) {
            System.out.println("  Dequeued: " + queue.pollFirst());
        }

        // Using Queue interface methods
        System.out.println("\nUsing Queue methods:");
        queue.offer(100); // Same as offerLast
        queue.offer(200);
        queue.offer(300);

        System.out.println("Queue: " + queue);
        System.out.println("Poll: " + queue.poll()); // Same as pollFirst
        System.out.println("After poll: " + queue);

        System.out.println();
    }

    /**
     * Demonstrate using Deque as Stack (LIFO)
     */
    private static void demonstrateDequeAsStack() {
        System.out.println("--- 3. Deque as Stack (LIFO) ---");
        System.out.println("Add and remove from same end (head)\n");

        Deque<String> stack = new ArrayDeque<>();

        // Push (add at head)
        System.out.println("Pushing:");
        stack.push("First");
        stack.push("Second");
        stack.push("Third");
        stack.push("Fourth");

        System.out.println("Stack: " + stack);

        // Peek (view head without removing)
        System.out.println("\nPeek: " + stack.peek());
        System.out.println("Stack unchanged: " + stack);

        // Pop (remove from head)
        System.out.println("\nPopping (LIFO order):");
        while (!stack.isEmpty()) {
            System.out.println("  Popped: " + stack.pop());
        }

        // Practical example: Undo functionality
        System.out.println("\nPractical example - Undo functionality:");
        Deque<String> undoStack = new ArrayDeque<>();

        String[] actions = { "Type 'Hello'", "Type ' World'", "Delete 'World'", "Type ' Java'" };
        for (String action : actions) {
            undoStack.push(action);
            System.out.println("  Action: " + action);
        }

        System.out.println("\nUndo operations:");
        System.out.println("  Undo: " + undoStack.pop());
        System.out.println("  Undo: " + undoStack.pop());
        System.out.println("  Remaining actions: " + undoStack);

        System.out.println();
    }

    /**
     * Compare ArrayDeque vs LinkedList
     */
    private static void demonstrateArrayDequeVsLinkedList() {
        System.out.println("--- 4. ArrayDeque vs LinkedList ---");

        System.out.println("ArrayDeque:");
        System.out.println("  + Faster than LinkedList for most operations");
        System.out.println("  + No capacity restrictions");
        System.out.println("  + More memory efficient");
        System.out.println("  - No null elements allowed");
        System.out.println("  - Not thread-safe");

        System.out.println("\nLinkedList:");
        System.out.println("  + Implements both List and Deque");
        System.out.println("  + Allows null elements");
        System.out.println("  + Can be used as List (index access)");
        System.out.println("  - Slower than ArrayDeque");
        System.out.println("  - More memory overhead (node objects)");

        System.out.println("\nRecommendation:");
        System.out.println("  Use ArrayDeque for deque/stack operations (faster)");
        System.out.println("  Use LinkedList when you need List interface features");

        System.out.println();
    }

    /**
     * Demonstrate deque iterations
     */
    private static void demonstrateDequeIterations() {
        System.out.println("--- 5. Deque Iterations ---");

        Deque<Integer> numbers = new ArrayDeque<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);
        numbers.add(50);

        System.out.println("Deque: " + numbers);

        // Forward iteration
        System.out.println("\nForward iteration:");
        for (Integer num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Descending iterator
        System.out.println("\nDescending iteration:");
        java.util.Iterator<Integer> descIter = numbers.descendingIterator();
        while (descIter.hasNext()) {
            System.out.print(descIter.next() + " ");
        }
        System.out.println();

        // Processing from both ends
        System.out.println("\nProcessing from both ends alternately:");
        Deque<String> letters = new ArrayDeque<>();
        letters.add("A");
        letters.add("B");
        letters.add("C");
        letters.add("D");
        letters.add("E");

        System.out.println("Original: " + letters);
        System.out.print("Alternating: ");
        boolean fromFirst = true;
        while (!letters.isEmpty()) {
            if (fromFirst) {
                System.out.print(letters.pollFirst() + " ");
            } else {
                System.out.print(letters.pollLast() + " ");
            }
            fromFirst = !fromFirst;
        }
        System.out.println();

        System.out.println("\nExercise completed!");
    }
}
