package com.fundamentals.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Comparator and Comparable
 * 
 * Comparable and Comparator are interfaces used for sorting objects.
 * 
 * Comparable (java.lang):
 * - Defines NATURAL ordering for a class
 * - Implemented BY the class itself
 * - Single sorting sequence only
 * - Method: compareTo(T o)
 * - Used when: Class has ONE obvious way to sort
 * 
 * Comparator (java.util):
 * - Defines CUSTOM ordering
 * - Implemented OUTSIDE the class
 * - Multiple sorting sequences possible
 * - Method: compare(T o1, T o2)
 * - Used when: Need different sorting options
 * 
 * Return values for compare/compareTo:
 * - Negative: first < second (first comes before)
 * - Zero: first == second (equal)
 * - Positive: first > second (first comes after)
 * 
 * Modern Comparator Methods (Java 8+):
 * - Comparator.comparing(keyExtractor)
 * - Comparator.comparingInt/Long/Double()
 * - thenComparing() for secondary sort
 * - reversed() for reverse order
 * - nullsFirst(), nullsLast()
 */
public class ComparatorDemo {

    public static void main(String[] args) {
        System.out.println("=== Comparator and Comparable ===\n");

        demonstrateComparable();
        demonstrateComparator();
        demonstrateMultipleSorting();
        demonstrateComparatorMethods();
        demonstrateChainedComparators();
        demonstrateNullHandling();
        demonstratePracticalExamples();
    }

    /**
     * Demonstrate Comparable interface
     */
    private static void demonstrateComparable() {
        System.out.println("--- 1. Comparable Interface ---");
        System.out.println("Comparable defines natural ordering within the class\n");

        // Student implements Comparable (sorts by ID)
        List<Student> students = new ArrayList<>();
        students.add(new Student(103, "Charlie", 85));
        students.add(new Student(101, "Alice", 92));
        students.add(new Student(102, "Bob", 78));

        System.out.println("Before sorting:");
        students.forEach(s -> System.out.println("  " + s));

        // Sort using natural order (Comparable)
        Collections.sort(students);

        System.out.println("\nAfter Collections.sort() (natural order by ID):");
        students.forEach(s -> System.out.println("  " + s));

        System.out.println("\nComparable implementation in Student class:");
        System.out.println("  public int compareTo(Student other) {");
        System.out.println("      return Integer.compare(this.id, other.id);");
        System.out.println("  }");

        System.out.println();
    }

    /**
     * Demonstrate Comparator interface
     */
    private static void demonstrateComparator() {
        System.out.println("--- 2. Comparator Interface ---");
        System.out.println("Comparator defines custom ordering outside the class\n");

        List<Student> students = new ArrayList<>();
        students.add(new Student(103, "Charlie", 85));
        students.add(new Student(101, "Alice", 92));
        students.add(new Student(102, "Bob", 78));

        System.out.println("Original list:");
        students.forEach(s -> System.out.println("  " + s));

        // Sort by name using anonymous class
        System.out.println("\nSort by name (anonymous Comparator):");
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.getName().compareTo(s2.getName());
            }
        });
        students.forEach(s -> System.out.println("  " + s));

        // Sort by score using lambda
        System.out.println("\nSort by score (lambda):");
        Collections.sort(students, (s1, s2) -> Integer.compare(s1.getScore(), s2.getScore()));
        students.forEach(s -> System.out.println("  " + s));

        // Sort by score descending
        System.out.println("\nSort by score descending:");
        Collections.sort(students, (s1, s2) -> Integer.compare(s2.getScore(), s1.getScore()));
        students.forEach(s -> System.out.println("  " + s));

        System.out.println();
    }

    /**
     * Demonstrate multiple sorting options
     */
    private static void demonstrateMultipleSorting() {
        System.out.println("--- 3. Multiple Sorting Options ---");
        System.out.println("Same data, different sort orders\n");

        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", 999.99, 50));
        products.add(new Product("Mouse", 29.99, 200));
        products.add(new Product("Keyboard", 79.99, 150));
        products.add(new Product("Monitor", 299.99, 75));

        System.out.println("Original products:");
        products.forEach(p -> System.out.println("  " + p));

        // Sort by name
        System.out.println("\nSorted by name:");
        products.sort((p1, p2) -> p1.name.compareTo(p2.name));
        products.forEach(p -> System.out.println("  " + p));

        // Sort by price (ascending)
        System.out.println("\nSorted by price (ascending):");
        products.sort((p1, p2) -> Double.compare(p1.price, p2.price));
        products.forEach(p -> System.out.println("  " + p));

        // Sort by price (descending)
        System.out.println("\nSorted by price (descending):");
        products.sort((p1, p2) -> Double.compare(p2.price, p1.price));
        products.forEach(p -> System.out.println("  " + p));

        // Sort by stock
        System.out.println("\nSorted by stock:");
        products.sort((p1, p2) -> Integer.compare(p1.stock, p2.stock));
        products.forEach(p -> System.out.println("  " + p));

        System.out.println();
    }

    /**
     * Demonstrate modern Comparator methods (Java 8+)
     */
    private static void demonstrateComparatorMethods() {
        System.out.println("--- 4. Modern Comparator Methods (Java 8+) ---");
        System.out.println("Using Comparator.comparing() and related methods\n");

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Alice", "IT", 75000));
        employees.add(new Employee("Bob", "HR", 55000));
        employees.add(new Employee("Charlie", "IT", 85000));
        employees.add(new Employee("Diana", "Finance", 65000));

        System.out.println("Original employees:");
        employees.forEach(e -> System.out.println("  " + e));

        // Comparator.comparing()
        System.out.println("\nUsing Comparator.comparing(Employee::getName):");
        employees.sort(Comparator.comparing(Employee::getName));
        employees.forEach(e -> System.out.println("  " + e));

        // Comparator.comparingDouble()
        System.out.println("\nUsing Comparator.comparingDouble(Employee::getSalary):");
        employees.sort(Comparator.comparingDouble(Employee::getSalary));
        employees.forEach(e -> System.out.println("  " + e));

        // reversed()
        System.out.println("\nUsing reversed() for descending order:");
        employees.sort(Comparator.comparingDouble(Employee::getSalary).reversed());
        employees.forEach(e -> System.out.println("  " + e));

        System.out.println();
    }

    /**
     * Demonstrate chained comparators
     */
    private static void demonstrateChainedComparators() {
        System.out.println("--- 5. Chained Comparators (thenComparing) ---");
        System.out.println("Sort by multiple fields\n");

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Alice", "IT", 75000));
        employees.add(new Employee("Bob", "IT", 85000));
        employees.add(new Employee("Charlie", "HR", 55000));
        employees.add(new Employee("Diana", "IT", 75000));
        employees.add(new Employee("Eve", "HR", 60000));

        System.out.println("Original employees:");
        employees.forEach(e -> System.out.println("  " + e));

        // Sort by department, then by salary (descending)
        System.out.println("\nSort by department, then by salary (desc):");
        employees.sort(
                Comparator.comparing(Employee::getDepartment)
                        .thenComparing(Comparator.comparingDouble(Employee::getSalary).reversed()));
        employees.forEach(e -> System.out.println("  " + e));

        // Sort by department, then by name
        System.out.println("\nSort by department, then by name:");
        employees.sort(
                Comparator.comparing(Employee::getDepartment)
                        .thenComparing(Employee::getName));
        employees.forEach(e -> System.out.println("  " + e));

        System.out.println();
    }

    /**
     * Demonstrate null handling
     */
    private static void demonstrateNullHandling() {
        System.out.println("--- 6. Null Handling ---");
        System.out.println("Using nullsFirst() and nullsLast()\n");

        List<String> names = new ArrayList<>();
        names.add("Charlie");
        names.add(null);
        names.add("Alice");
        names.add(null);
        names.add("Bob");

        System.out.println("Original list with nulls:");
        System.out.println("  " + names);

        // nullsFirst - nulls come before non-null values
        System.out.println("\nUsing nullsFirst():");
        names.sort(Comparator.nullsFirst(Comparator.naturalOrder()));
        System.out.println("  " + names);

        // nullsLast - nulls come after non-null values
        System.out.println("\nUsing nullsLast():");
        names.sort(Comparator.nullsLast(Comparator.naturalOrder()));
        System.out.println("  " + names);

        // Null-safe field comparison
        System.out.println("\nNull-safe field comparison:");
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Alice", "IT", 75000));
        employees.add(new Employee("Bob", null, 55000));
        employees.add(new Employee("Charlie", "HR", 65000));

        employees.sort(
                Comparator.comparing(
                        Employee::getDepartment,
                        Comparator.nullsLast(Comparator.naturalOrder())));
        employees.forEach(e -> System.out.println("  " + e));

        System.out.println();
    }

    /**
     * Demonstrate practical examples
     */
    private static void demonstratePracticalExamples() {
        System.out.println("--- 7. Practical Examples ---\n");

        // Example 1: Sorting strings case-insensitive
        System.out.println("1. Case-insensitive string sort:");
        List<String> words = new ArrayList<>();
        words.add("banana");
        words.add("Apple");
        words.add("CHERRY");
        words.add("date");

        System.out.println("  Original: " + words);
        words.sort(String.CASE_INSENSITIVE_ORDER);
        System.out.println("  Case-insensitive: " + words);

        // Example 2: Sorting by string length
        System.out.println("\n2. Sort by string length:");
        List<String> languages = new ArrayList<>();
        languages.add("Java");
        languages.add("C");
        languages.add("Python");
        languages.add("Go");
        languages.add("JavaScript");

        System.out.println("  Original: " + languages);
        languages.sort(Comparator.comparingInt(String::length));
        System.out.println("  By length: " + languages);

        // Example 3: Finding min/max with comparator
        System.out.println("\n3. Finding min/max with comparator:");
        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", 999.99, 50));
        products.add(new Product("Mouse", 29.99, 200));
        products.add(new Product("Keyboard", 79.99, 150));

        Product cheapest = Collections.min(products, Comparator.comparingDouble(p -> p.price));
        Product mostExpensive = Collections.max(products, Comparator.comparingDouble(p -> p.price));
        System.out.println("  Cheapest: " + cheapest.name + " ($" + cheapest.price + ")");
        System.out.println("  Most expensive: " + mostExpensive.name + " ($" + mostExpensive.price + ")");

        // Example 4: Binary search with comparator
        System.out.println("\n4. Binary search with comparator:");
        List<String> sortedNames = new ArrayList<>();
        sortedNames.add("Alice");
        sortedNames.add("Bob");
        sortedNames.add("Charlie");
        sortedNames.add("David");

        int index = Collections.binarySearch(sortedNames, "Charlie", Comparator.naturalOrder());
        System.out.println("  List: " + sortedNames);
        System.out.println("  Index of 'Charlie': " + index);

        System.out.println("\nExercise completed!");
    }

    // ========== Helper Classes ==========

    /**
     * Student class implementing Comparable
     */
    static class Student implements Comparable<Student> {
        private int id;
        private String name;
        private int score;

        Student(int id, String name, int score) {
            this.id = id;
            this.name = name;
            this.score = score;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        @Override
        public int compareTo(Student other) {
            return Integer.compare(this.id, other.id);
        }

        @Override
        public String toString() {
            return "Student{id=" + id + ", name='" + name + "', score=" + score + "}";
        }
    }

    /**
     * Product class (no Comparable)
     */
    static class Product {
        String name;
        double price;
        int stock;

        Product(String name, double price, int stock) {
            this.name = name;
            this.price = price;
            this.stock = stock;
        }

        @Override
        public String toString() {
            return name + " ($" + price + ", stock: " + stock + ")";
        }
    }

    /**
     * Employee class with method references support
     */
    static class Employee {
        private String name;
        private String department;
        private double salary;

        Employee(String name, String department, double salary) {
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }

        public double getSalary() {
            return salary;
        }

        @Override
        public String toString() {
            return name + " (" + (department != null ? department : "N/A") + ", $" + (int) salary + ")";
        }
    }
}
