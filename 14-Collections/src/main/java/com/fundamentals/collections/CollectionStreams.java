package com.fundamentals.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Collection Streams (Java 8+)
 * 
 * Stream API provides a functional approach to process collections.
 * Streams don't modify the source collection.
 * 
 * Stream Pipeline:
 * 1. Source: Collection, array, or generator function
 * 2. Intermediate operations: Transform stream (lazy)
 * 3. Terminal operation: Produces result (triggers execution)
 * 
 * Intermediate Operations (return Stream):
 * - filter(Predicate): Keep elements matching condition
 * - map(Function): Transform each element
 * - flatMap(Function): One-to-many transformation
 * - distinct(): Remove duplicates
 * - sorted(): Sort elements
 * - limit(n): Take first n elements
 * - skip(n): Skip first n elements
 * - peek(Consumer): Debug/inspect elements
 * 
 * Terminal Operations (produce result):
 * - forEach(Consumer): Perform action on each element
 * - collect(Collector): Collect into collection
 * - reduce(BinaryOperator): Combine elements
 * - count(): Count elements
 * - findFirst(), findAny(): Get optional element
 * - anyMatch(), allMatch(), noneMatch(): Boolean checks
 * - min(), max(): Get extreme values
 * - toArray(): Convert to array
 */
public class CollectionStreams {

    public static void main(String[] args) {
        System.out.println("=== Collection Streams (Java 8+) ===\n");

        demonstrateStreamCreation();
        demonstrateIntermediateOperations();
        demonstrateTerminalOperations();
        demonstrateCollectors();
        demonstrateNumericStreams();
        demonstratePracticalExamples();
    }

    /**
     * Demonstrate stream creation
     */
    private static void demonstrateStreamCreation() {
        System.out.println("--- 1. Stream Creation ---\n");

        // From Collection
        System.out.println("From Collection:");
        List<String> list = Arrays.asList("A", "B", "C");
        Stream<String> stream1 = list.stream();
        stream1.forEach(s -> System.out.print(s + " "));
        System.out.println();

        // From array
        System.out.println("\nFrom Array:");
        String[] array = { "X", "Y", "Z" };
        Stream<String> stream2 = Arrays.stream(array);
        stream2.forEach(s -> System.out.print(s + " "));
        System.out.println();

        // Using Stream.of()
        System.out.println("\nUsing Stream.of():");
        Stream<Integer> stream3 = Stream.of(1, 2, 3, 4, 5);
        stream3.forEach(n -> System.out.print(n + " "));
        System.out.println();

        // Using Stream.generate()
        System.out.println("\nUsing Stream.generate() (5 random numbers):");
        Stream.generate(Math::random)
                .limit(5)
                .forEach(n -> System.out.printf("%.2f ", n));
        System.out.println();

        // Using Stream.iterate()
        System.out.println("\nUsing Stream.iterate() (powers of 2):");
        Stream.iterate(1, n -> n * 2)
                .limit(8)
                .forEach(n -> System.out.print(n + " "));
        System.out.println();

        System.out.println();
    }

    /**
     * Demonstrate intermediate operations
     */
    private static void demonstrateIntermediateOperations() {
        System.out.println("--- 2. Intermediate Operations ---\n");

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "Diana", "Eve", "Alice");

        System.out.println("Original: " + names);

        // filter
        System.out.println("\nfilter (length > 3):");
        names.stream()
                .filter(name -> name.length() > 3)
                .forEach(name -> System.out.print(name + " "));
        System.out.println();

        // map
        System.out.println("\nmap (to uppercase):");
        names.stream()
                .map(String::toUpperCase)
                .forEach(name -> System.out.print(name + " "));
        System.out.println();

        // distinct
        System.out.println("\ndistinct (remove duplicates):");
        names.stream()
                .distinct()
                .forEach(name -> System.out.print(name + " "));
        System.out.println();

        // sorted
        System.out.println("\nsorted (alphabetical):");
        names.stream()
                .sorted()
                .forEach(name -> System.out.print(name + " "));
        System.out.println();

        // sorted with comparator
        System.out.println("\nsorted (by length):");
        names.stream()
                .sorted(Comparator.comparingInt(String::length))
                .forEach(name -> System.out.print(name + " "));
        System.out.println();

        // limit and skip
        System.out.println("\nskip(2).limit(3):");
        names.stream()
                .skip(2)
                .limit(3)
                .forEach(name -> System.out.print(name + " "));
        System.out.println();

        // Chaining multiple operations
        System.out.println("\nChained: filter -> map -> sorted -> distinct:");
        names.stream()
                .filter(name -> name.length() > 3)
                .map(String::toUpperCase)
                .sorted()
                .distinct()
                .forEach(name -> System.out.print(name + " "));
        System.out.println();

        System.out.println();
    }

    /**
     * Demonstrate terminal operations
     */
    private static void demonstrateTerminalOperations() {
        System.out.println("--- 3. Terminal Operations ---\n");

        List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 9, 3, 7, 4, 6);

        System.out.println("Numbers: " + numbers);

        // count
        long count = numbers.stream().filter(n -> n > 5).count();
        System.out.println("\ncount (n > 5): " + count);

        // reduce
        int sum = numbers.stream().reduce(0, Integer::sum);
        System.out.println("reduce (sum): " + sum);

        int product = numbers.stream().reduce(1, (a, b) -> a * b);
        System.out.println("reduce (product): " + product);

        // min/max
        Optional<Integer> min = numbers.stream().min(Integer::compare);
        Optional<Integer> max = numbers.stream().max(Integer::compare);
        System.out.println("min: " + min.orElse(0) + ", max: " + max.orElse(0));

        // findFirst
        Optional<Integer> first = numbers.stream().filter(n -> n > 5).findFirst();
        System.out.println("findFirst (n > 5): " + first.orElse(-1));

        // anyMatch, allMatch, noneMatch
        System.out.println("\nMatching:");
        System.out.println("anyMatch (n > 5): " + numbers.stream().anyMatch(n -> n > 5));
        System.out.println("allMatch (n > 0): " + numbers.stream().allMatch(n -> n > 0));
        System.out.println("noneMatch (n < 0): " + numbers.stream().noneMatch(n -> n < 0));

        // toArray
        Integer[] array = numbers.stream().filter(n -> n % 2 == 0).toArray(Integer[]::new);
        System.out.println("\ntoArray (even numbers): " + Arrays.toString(array));

        System.out.println();
    }

    /**
     * Demonstrate collectors
     */
    private static void demonstrateCollectors() {
        System.out.println("--- 4. Collectors ---\n");

        List<String> words = Arrays.asList("Java", "Python", "JavaScript", "Go", "Ruby", "Java");

        System.out.println("Words: " + words);

        // toList
        List<String> longWords = words.stream()
                .filter(w -> w.length() > 4)
                .collect(Collectors.toList());
        System.out.println("\ntoList (length > 4): " + longWords);

        // toSet
        java.util.Set<String> uniqueWords = words.stream()
                .collect(Collectors.toSet());
        System.out.println("toSet (unique): " + uniqueWords);

        // joining
        String joined = words.stream().collect(Collectors.joining(", "));
        System.out.println("joining: " + joined);

        // groupingBy
        Map<Integer, List<String>> byLength = words.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println("\ngroupingBy (length): " + byLength);

        // partitioningBy
        Map<Boolean, List<String>> partitioned = words.stream()
                .collect(Collectors.partitioningBy(w -> w.startsWith("J")));
        System.out.println("partitioningBy (starts with J): " + partitioned);

        // counting in group
        Map<Integer, Long> countByLength = words.stream()
                .collect(Collectors.groupingBy(String::length, Collectors.counting()));
        System.out.println("counting by length: " + countByLength);

        System.out.println();
    }

    /**
     * Demonstrate numeric streams
     */
    private static void demonstrateNumericStreams() {
        System.out.println("--- 5. Numeric Streams ---\n");

        // IntStream
        System.out.println("IntStream.range(1, 6):");
        IntStream.range(1, 6).forEach(n -> System.out.print(n + " "));
        System.out.println();

        System.out.println("\nIntStream.rangeClosed(1, 5):");
        IntStream.rangeClosed(1, 5).forEach(n -> System.out.print(n + " "));
        System.out.println();

        // Numeric operations
        int sum = IntStream.rangeClosed(1, 100).sum();
        System.out.println("\nSum of 1 to 100: " + sum);

        double average = IntStream.rangeClosed(1, 10).average().orElse(0);
        System.out.println("Average of 1 to 10: " + average);

        // Statistics
        System.out.println("\nIntStream statistics (1-10):");
        var stats = IntStream.rangeClosed(1, 10).summaryStatistics();
        System.out.println("  Count: " + stats.getCount());
        System.out.println("  Sum: " + stats.getSum());
        System.out.println("  Min: " + stats.getMin());
        System.out.println("  Max: " + stats.getMax());
        System.out.println("  Average: " + stats.getAverage());

        System.out.println();
    }

    /**
     * Demonstrate practical examples
     */
    private static void demonstratePracticalExamples() {
        System.out.println("--- 6. Practical Examples ---\n");

        // Example 1: Processing list of employees
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "IT", 75000),
                new Employee("Bob", "HR", 55000),
                new Employee("Charlie", "IT", 85000),
                new Employee("Diana", "Finance", 65000),
                new Employee("Eve", "IT", 70000),
                new Employee("Frank", "HR", 60000));

        System.out.println("Employees:");
        employees.forEach(e -> System.out.println("  " + e));

        // Find highest paid employee
        Optional<Employee> highestPaid = employees.stream()
                .max(Comparator.comparingDouble(Employee::salary));
        System.out.println("\nHighest paid: " + highestPaid.orElse(null));

        // Average salary by department
        Map<String, Double> avgByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.averagingDouble(Employee::salary)));
        System.out.println("\nAverage salary by department: " + avgByDept);

        // Names of IT employees earning > 70000
        List<String> highEarningIT = employees.stream()
                .filter(e -> e.department().equals("IT"))
                .filter(e -> e.salary() > 70000)
                .map(Employee::name)
                .collect(Collectors.toList());
        System.out.println("\nIT employees earning > 70000: " + highEarningIT);

        // Total salary by department
        Map<String, Double> totalByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::department,
                        Collectors.summingDouble(Employee::salary)));
        System.out.println("Total salary by department: " + totalByDept);

        System.out.println("\nExercise completed!");
    }

    /**
     * Helper record for practical examples
     */
    record Employee(String name, String department, double salary) {
        @Override
        public String toString() {
            return name + " (" + department + ", $" + (int) salary + ")";
        }
    }
}
