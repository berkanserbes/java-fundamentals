package com.fundamentals.collections;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Queue Interface and Implementations
 * 
 * Queue is a collection designed for holding elements prior to processing.
 * Follows FIFO (First-In-First-Out) principle.
 * 
 * Key Characteristics:
 * - FIFO Order: First element added is first to be removed
 * - Head and Tail: Elements added at tail, removed from head
 * - No Random Access: Cannot access elements by index
 * 
 * Common Implementations:
 * 1. LinkedList - General purpose queue
 * 2. PriorityQueue - Elements ordered by priority (natural or comparator)
 * 3. ArrayDeque - Resizable array implementation (also implements Deque)
 * 
 * Queue Methods:
 * 
 * Throws Exception:
 * - add(E e): Add element (throws exception if fails)
 * - remove(): Remove and return head (throws exception if empty)
 * - element(): Return head without removing (throws exception if empty)
 * 
 * Returns Special Value:
 * - offer(E e): Add element (returns false if fails)
 * - poll(): Remove and return head (returns null if empty)
 * - peek(): Return head without removing (returns null if empty)
 */
public class QueueOperations {

    public static void main(String[] args) {
        System.out.println("=== Queue Interface and Implementations ===\n");

        demonstrateBasicQueue();
        demonstrateQueueMethods();
        demonstratePriorityQueue();
        demonstratePriorityQueueWithComparator();
        demonstrateQueueAsBuffer();
    }

    /**
     * Demonstrate basic queue operations
     */
    private static void demonstrateBasicQueue() {
        System.out.println("--- 1. Basic Queue Operations ---");
        System.out.println("Queue follows FIFO (First-In-First-Out)\n");

        Queue<String> customerQueue = new LinkedList<>();

        // Adding elements (enqueue)
        System.out.println("Customers arriving:");
        customerQueue.offer("Alice");
        customerQueue.offer("Bob");
        customerQueue.offer("Charlie");
        customerQueue.offer("David");
        customerQueue.offer("Eve");

        System.out.println("Queue: " + customerQueue);
        System.out.println("Size: " + customerQueue.size());

        // Viewing head without removing
        System.out.println("\nPeeking at head:");
        System.out.println("Next customer: " + customerQueue.peek());
        System.out.println("Queue unchanged: " + customerQueue);

        // Removing elements (dequeue)
        System.out.println("\nServing customers:");
        while (!customerQueue.isEmpty()) {
            String customer = customerQueue.poll();
            System.out.println("  Serving: " + customer);
            System.out.println("  Remaining: " + customerQueue);
        }

        System.out.println("\nQueue is now empty: " + customerQueue.isEmpty());

        System.out.println();
    }

    /**
     * Demonstrate difference between exception and special value methods
     */
    private static void demonstrateQueueMethods() {
        System.out.println("--- 2. Queue Methods Comparison ---");

        Queue<Integer> numbers = new LinkedList<>();

        // add vs offer
        System.out.println("Adding elements:");
        numbers.add(10);      // Throws exception on failure
        numbers.offer(20);    // Returns false on failure
        numbers.offer(30);
        System.out.println("Queue: " + numbers);

        // element vs peek
        System.out.println("\nViewing head:");
        System.out.println("element(): " + numbers.element()); // Throws exception if empty
        System.out.println("peek(): " + numbers.peek());       // Returns null if empty

        // remove vs poll
        System.out.println("\nRemoving head:");
        System.out.println("remove(): " + numbers.remove());   // Throws exception if empty
        System.out.println("poll(): " + numbers.poll());       // Returns null if empty
        System.out.println("After removal: " + numbers);

        // Behavior on empty queue
        System.out.println("\nBehavior on empty queue:");
        numbers.clear();
        System.out.println("peek() on empty: " + numbers.peek());     // null
        System.out.println("poll() on empty: " + numbers.poll());     // null

        try {
            numbers.element(); // Throws NoSuchElementException
        } catch (Exception e) {
            System.out.println("element() on empty: " + e.getClass().getSimpleName());
        }

        try {
            numbers.remove(); // Throws NoSuchElementException
        } catch (Exception e) {
            System.out.println("remove() on empty: " + e.getClass().getSimpleName());
        }

        System.out.println();
    }

    /**
     * Demonstrate PriorityQueue with natural ordering
     */
    private static void demonstratePriorityQueue() {
        System.out.println("--- 3. PriorityQueue (Natural Ordering) ---");
        System.out.println("Elements are ordered by natural ordering (or comparator)\n");

        PriorityQueue<Integer> taskPriorities = new PriorityQueue<>();

        // Adding elements in random order
        System.out.println("Adding tasks with priorities:");
        taskPriorities.offer(5);
        taskPriorities.offer(1);
        taskPriorities.offer(8);
        taskPriorities.offer(3);
        taskPriorities.offer(2);

        System.out.println("Queue (internal order): " + taskPriorities);
        System.out.println("Note: Internal order is heap structure, not sorted array");

        // Removing elements (always removes smallest/highest priority)
        System.out.println("\nProcessing tasks by priority (lowest number = highest priority):");
        while (!taskPriorities.isEmpty()) {
            System.out.println("  Processing priority: " + taskPriorities.poll());
        }

        // PriorityQueue with strings (alphabetical order)
        System.out.println("\nPriorityQueue with strings:");
        PriorityQueue<String> names = new PriorityQueue<>();
        names.offer("Zebra");
        names.offer("Apple");
        names.offer("Mango");
        names.offer("Banana");

        System.out.println("Queue: " + names);
        System.out.print("Polling order: ");
        while (!names.isEmpty()) {
            System.out.print(names.poll());
            if (!names.isEmpty()) System.out.print(", ");
        }
        System.out.println();

        System.out.println();
    }

    /**
     * Demonstrate PriorityQueue with custom comparator
     */
    private static void demonstratePriorityQueueWithComparator() {
        System.out.println("--- 4. PriorityQueue with Custom Comparator ---");

        // Max heap (reverse order - highest priority first)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        System.out.println("Max heap (highest number first):");
        maxHeap.offer(5);
        maxHeap.offer(1);
        maxHeap.offer(8);
        maxHeap.offer(3);
        maxHeap.offer(2);

        System.out.print("Polling order: ");
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.poll());
            if (!maxHeap.isEmpty()) System.out.print(", ");
        }
        System.out.println();

        // Custom object with priority
        System.out.println("\nCustom objects (Task with priority):");
        PriorityQueue<Task> taskQueue = new PriorityQueue<>(
            (t1, t2) -> Integer.compare(t1.priority, t2.priority)
        );

        taskQueue.offer(new Task("Write report", 3));
        taskQueue.offer(new Task("Fix bug", 1));
        taskQueue.offer(new Task("Code review", 2));
        taskQueue.offer(new Task("Meeting", 4));

        System.out.println("Processing tasks by priority:");
        while (!taskQueue.isEmpty()) {
            Task task = taskQueue.poll();
            System.out.println("  Priority " + task.priority + ": " + task.name);
        }

        System.out.println();
    }

    /**
     * Demonstrate queue as a buffer
     */
    private static void demonstrateQueueAsBuffer() {
        System.out.println("--- 5. Queue as Buffer (Producer-Consumer) ---");

        Queue<String> messageBuffer = new LinkedList<>();
        int bufferSize = 5;

        // Producer adds messages
        System.out.println("Producer adding messages:");
        String[] messages = {"Msg1", "Msg2", "Msg3", "Msg4", "Msg5", "Msg6"};
        
        for (String msg : messages) {
            if (messageBuffer.size() < bufferSize) {
                messageBuffer.offer(msg);
                System.out.println("  Added: " + msg + " | Buffer: " + messageBuffer);
            } else {
                System.out.println("  Buffer full! Cannot add: " + msg);
            }
        }

        // Consumer processes messages
        System.out.println("\nConsumer processing messages:");
        while (!messageBuffer.isEmpty()) {
            String msg = messageBuffer.poll();
            System.out.println("  Processing: " + msg + " | Remaining: " + messageBuffer);
        }

        System.out.println("\nExercise completed!");
    }

    /**
     * Helper class for priority queue example
     */
    static class Task {
        String name;
        int priority;

        Task(String name, int priority) {
            this.name = name;
            this.priority = priority;
        }
    }
}
