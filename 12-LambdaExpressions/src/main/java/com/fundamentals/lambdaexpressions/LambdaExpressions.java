package com.fundamentals.lambdaexpressions;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * Lambda Expressions in Java
 * 
 * LAMBDA EXPRESSION: A concise way to represent an anonymous function
 * that can be passed around as if it were an object.
 * 
 * Introduced in Java 8 to enable functional programming.
 * 
 * Syntax:
 * ┌─────────────────────────────────────────────────────────────────────┐
 * │ (parameters) -> expression // Single expression │
 * │ (parameters) -> { statements; } // Block of code │
 * │ │
 * │ Examples: │
 * │ () -> 42 // No parameters │
 * │ x -> x * 2 // One parameter │
 * │ (x, y) -> x + y // Two parameters │
 * │ (String s) -> s.length() // Explicit type │
 * │ (x, y) -> { return x + y; } // Block with return │
 * └─────────────────────────────────────────────────────────────────────┘
 * 
 * Requirements:
 * - Target type must be a FUNCTIONAL INTERFACE
 * - Functional Interface = Interface with exactly ONE abstract method
 * - Can have default/static methods
 * 
 * Benefits:
 * - More concise code
 * - Enables functional programming
 * - Better support for parallel processing
 * - Improved API design (e.g., Stream API)
 */
public class LambdaExpressions {

    public static void main(String[] args) {
        System.out.println("=== Lambda Expressions in Java ===\n");

        demonstrateLambdaSyntax();
        demonstrateFunctionalInterfaces();
        demonstrateBuiltInFunctionalInterfaces();
        demonstrateMethodReferences();
        demonstrateClosureAndScope();
        demonstrateLambdaWithCollections();
        demonstrateStreamAPI();
        demonstrateCompositionOfFunctions();
        demonstrateCustomFunctionalInterfaces();
        demonstrateRealWorldExamples();
    }

    // ========================================================================
    // LAMBDA SYNTAX VARIATIONS
    // ========================================================================

    /**
     * Demonstrates different lambda syntax options
     */
    private static void demonstrateLambdaSyntax() {
        System.out.println("--- 1. Lambda Syntax Variations ---\n");

        // 1. No parameters
        Runnable noParams = () -> System.out.println("No parameters lambda");
        noParams.run();

        // 2. Single parameter (parentheses optional)
        Consumer<String> singleParam = s -> System.out.println("Single param: " + s);
        Consumer<String> singleParamWithParen = (s) -> System.out.println("With parens: " + s);
        singleParam.accept("Hello");
        singleParamWithParen.accept("World");

        // 3. Multiple parameters
        BiFunction<Integer, Integer, Integer> multiParams = (a, b) -> a + b;
        System.out.println("Multi params (5 + 3): " + multiParams.apply(5, 3));

        // 4. Explicit type declaration
        BiFunction<String, String, String> explicitTypes = (String a, String b) -> a.concat(b);
        System.out.println("Explicit types: " + explicitTypes.apply("Hello, ", "Lambda!"));

        // 5. Block body with multiple statements
        BiFunction<Integer, Integer, Integer> blockBody = (a, b) -> {
            int result = a * b;
            System.out.println("Block body: " + a + " * " + b + " = " + result);
            return result;
        };
        blockBody.apply(4, 5);

        // 6. Single expression (no return keyword needed)
        Function<Integer, Integer> expressionBody = x -> x * x;
        System.out.println("Expression body (5²): " + expressionBody.apply(5));

        System.out.println("\nSyntax Rules:");
        System.out.println("┌────────────────────────────────────────────────┐");
        System.out.println("│ () -> expr          : No parameters           │");
        System.out.println("│ x -> expr           : Single parameter        │");
        System.out.println("│ (x, y) -> expr      : Multiple parameters     │");
        System.out.println("│ (Type x) -> expr    : Explicit type           │");
        System.out.println("│ x -> { return x; }  : Block body              │");
        System.out.println("└────────────────────────────────────────────────┘");
        System.out.println();
    }

    // ========================================================================
    // FUNCTIONAL INTERFACES
    // ========================================================================

    /**
     * Understanding @FunctionalInterface
     */
    private static void demonstrateFunctionalInterfaces() {
        System.out.println("--- 2. Functional Interfaces ---\n");

        // Using our custom functional interface
        Transformer<String, Integer> lengthGetter = s -> s.length();
        System.out.println("Length of 'Lambda': " + lengthGetter.transform("Lambda"));

        // Functional interface with default method
        Validator<String> emailValidator = s -> s.contains("@") && s.contains(".");
        System.out.println("\nEmail validation:");
        System.out.println("  'test@email.com' valid? " + emailValidator.validate("test@email.com"));
        System.out.println("  'invalid-email' valid? " + emailValidator.validate("invalid-email"));

        // Using default method
        Validator<String> notEmptyValidator = s -> !s.isEmpty();
        System.out.println("  '' is empty? " + notEmptyValidator.isInvalid(""));

        // Chaining with and()
        Validator<String> combinedValidator = notEmptyValidator;
        System.out.println("  'hello' not empty? " + combinedValidator.validate("hello"));

        System.out.println("\n@FunctionalInterface Rules:");
        System.out.println("- Exactly ONE abstract method");
        System.out.println("- Can have multiple default methods");
        System.out.println("- Can have multiple static methods");
        System.out.println("- @FunctionalInterface annotation is optional but recommended");
        System.out.println();
    }

    // ========================================================================
    // BUILT-IN FUNCTIONAL INTERFACES
    // ========================================================================

    /**
     * java.util.function package interfaces
     */
    private static void demonstrateBuiltInFunctionalInterfaces() {
        System.out.println("--- 3. Built-in Functional Interfaces ---\n");

        // 1. Predicate<T> - Tests a condition, returns boolean
        System.out.println("PREDICATE (T -> boolean):");
        Predicate<Integer> isPositive = n -> n > 0;
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println("  5 is positive? " + isPositive.test(5));
        System.out.println("  4 is even? " + isEven.test(4));

        // Predicate composition
        Predicate<Integer> isPositiveAndEven = isPositive.and(isEven);
        System.out.println("  4 is positive AND even? " + isPositiveAndEven.test(4));
        System.out.println("  -4 is positive AND even? " + isPositiveAndEven.test(-4));

        // 2. Function<T, R> - Takes T, returns R
        System.out.println("\nFUNCTION (T -> R):");
        Function<String, Integer> strlen = String::length;
        Function<Integer, Integer> square = x -> x * x;
        System.out.println("  Length of 'Hello': " + strlen.apply("Hello"));
        System.out.println("  Square of 5: " + square.apply(5));

        // Function composition
        Function<String, Integer> lengthSquared = strlen.andThen(square);
        System.out.println("  Length of 'Hi' squared: " + lengthSquared.apply("Hi"));

        // 3. Consumer<T> - Takes T, returns nothing
        System.out.println("\nCONSUMER (T -> void):");
        Consumer<String> logger = msg -> System.out.println("  LOG: " + msg);
        Consumer<String> alerter = msg -> System.out.println("  ALERT: " + msg);
        logger.accept("Application started");

        // Consumer chaining
        Consumer<String> logAndAlert = logger.andThen(alerter);
        logAndAlert.accept("Critical event!");

        // 4. Supplier<T> - Takes nothing, returns T
        System.out.println("\nSUPPLIER (() -> T):");
        Supplier<Double> randomSupplier = Math::random;
        Supplier<String> greetingSupplier = () -> "Hello, World!";
        System.out.println("  Random: " + randomSupplier.get());
        System.out.println("  Greeting: " + greetingSupplier.get());

        // 5. BiFunction<T, U, R> - Takes T and U, returns R
        System.out.println("\nBIFUNCTION (T, U -> R):");
        BiFunction<String, Integer, String> repeat = (s, n) -> s.repeat(n);
        System.out.println("  'Ha' repeated 3 times: " + repeat.apply("Ha", 3));

        // 6. BiPredicate<T, U> - Takes T and U, returns boolean
        System.out.println("\nBIPREDICATE (T, U -> boolean):");
        BiPredicate<String, Integer> longerThan = (s, len) -> s.length() > len;
        System.out.println("  'Hello' longer than 3? " + longerThan.test("Hello", 3));

        // 7. UnaryOperator<T> - Takes T, returns T
        System.out.println("\nUNARYOPERATOR (T -> T):");
        UnaryOperator<String> toUpper = String::toUpperCase;
        System.out.println("  'lambda' to upper: " + toUpper.apply("lambda"));

        // 8. BinaryOperator<T> - Takes T and T, returns T
        System.out.println("\nBINARYOPERATOR (T, T -> T):");
        BinaryOperator<Integer> sum = Integer::sum;
        BinaryOperator<Integer> max = Integer::max;
        System.out.println("  Sum of 5 and 3: " + sum.apply(5, 3));
        System.out.println("  Max of 5 and 3: " + max.apply(5, 3));

        System.out.println();
    }

    // ========================================================================
    // METHOD REFERENCES
    // ========================================================================

    /**
     * Method references as shorthand for lambdas
     */
    private static void demonstrateMethodReferences() {
        System.out.println("--- 4. Method References ---\n");

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        // 1. Reference to static method (ClassName::staticMethod)
        System.out.println("1. Static method reference:");
        System.out.println("   Lambda: s -> Integer.parseInt(s)");
        System.out.println("   Method ref: Integer::parseInt");
        Function<String, Integer> parser1 = s -> Integer.parseInt(s);
        Function<String, Integer> parser2 = Integer::parseInt;
        System.out.println("   Parse '123': " + parser2.apply("123"));

        // 2. Reference to instance method of particular object
        System.out.println("\n2. Instance method reference (object::method):");
        System.out.println("   Lambda: s -> System.out.println(s)");
        System.out.println("   Method ref: System.out::println");
        Consumer<String> printer1 = s -> System.out.println(s);
        Consumer<String> printer2 = System.out::println;
        names.forEach(System.out::println);

        // 3. Reference to instance method of arbitrary object
        System.out.println("\n3. Arbitrary object instance method (ClassName::instanceMethod):");
        System.out.println("   Lambda: s -> s.toUpperCase()");
        System.out.println("   Method ref: String::toUpperCase");
        Function<String, String> upperLambda = s -> s.toUpperCase();
        Function<String, String> upperMethodRef = String::toUpperCase;
        List<String> upperNames = names.stream()
                .map(String::toUpperCase)
                .toList();
        System.out.println("   Upper names: " + upperNames);

        // 4. Reference to constructor (ClassName::new)
        System.out.println("\n4. Constructor reference (ClassName::new):");
        System.out.println("   Lambda: () -> new ArrayList<>()");
        System.out.println("   Method ref: ArrayList::new");
        Supplier<List<String>> listCreator1 = () -> new ArrayList<>();
        Supplier<List<String>> listCreator2 = ArrayList::new;

        Function<String, StringBuilder> sbCreator = StringBuilder::new;
        System.out.println("   Created StringBuilder: " + sbCreator.apply("Hello"));

        System.out.println("\nMethod Reference Summary:");
        System.out.println("┌─────────────────────────┬─────────────────────────────┐");
        System.out.println("│ Type                    │ Syntax                      │");
        System.out.println("├─────────────────────────┼─────────────────────────────┤");
        System.out.println("│ Static method           │ ClassName::staticMethod     │");
        System.out.println("│ Instance (specific obj) │ object::instanceMethod      │");
        System.out.println("│ Instance (arbitrary)    │ ClassName::instanceMethod   │");
        System.out.println("│ Constructor             │ ClassName::new              │");
        System.out.println("└─────────────────────────┴─────────────────────────────┘");
        System.out.println();
    }

    // ========================================================================
    // CLOSURE AND VARIABLE SCOPE
    // ========================================================================

    /**
     * Variable capture and effective finality
     */
    private static void demonstrateClosureAndScope() {
        System.out.println("--- 5. Closure and Variable Scope ---\n");

        // Effectively final variable (can be captured)
        String prefix = "Value: ";
        int multiplier = 10;

        // Lambda capturing variables
        Function<Integer, String> formatter = n -> prefix + (n * multiplier);
        System.out.println("Captured variables:");
        System.out.println("  " + formatter.apply(5));
        System.out.println("  " + formatter.apply(10));

        // 'this' reference in lambda vs anonymous class
        System.out.println("\n'this' in Lambda:");
        demonstrateThisInLambda();

        // Cannot modify captured variables
        System.out.println("\nVariable Capture Rules:");
        System.out.println("- Local variables must be final or effectively final");
        System.out.println("- Instance/class variables can be modified");
        System.out.println("- 'this' refers to enclosing class (not lambda)");

        // Workaround for mutable state
        System.out.println("\nWorkaround for mutable state:");
        final int[] counter = { 0 }; // Array is effectively final, contents can change
        Runnable incrementer = () -> counter[0]++;
        incrementer.run();
        incrementer.run();
        incrementer.run();
        System.out.println("  Counter value: " + counter[0]);
        System.out.println();
    }

    private static void demonstrateThisInLambda() {
        // In lambda, 'this' refers to the enclosing class
        Supplier<String> lambdaThis = () -> "Lambda 'this' class: " +
                LambdaExpressions.class.getSimpleName();
        System.out.println("  " + lambdaThis.get());
    }

    // ========================================================================
    // LAMBDA WITH COLLECTIONS
    // ========================================================================

    /**
     * Using lambdas with Java Collections
     */
    private static void demonstrateLambdaWithCollections() {
        System.out.println("--- 6. Lambda with Collections ---\n");

        List<String> fruits = new ArrayList<>(Arrays.asList(
                "Apple", "Banana", "Cherry", "Date", "Elderberry"));

        // forEach
        System.out.println("1. forEach:");
        fruits.forEach(f -> System.out.println("   - " + f));

        // removeIf
        System.out.println("\n2. removeIf (remove fruits with length > 6):");
        List<String> fruitsCopy = new ArrayList<>(fruits);
        fruitsCopy.removeIf(f -> f.length() > 6);
        System.out.println("   Remaining: " + fruitsCopy);

        // replaceAll
        System.out.println("\n3. replaceAll (to uppercase):");
        List<String> fruitsUpper = new ArrayList<>(fruits);
        fruitsUpper.replaceAll(String::toUpperCase);
        System.out.println("   Upper: " + fruitsUpper);

        // sort with Comparator lambda
        System.out.println("\n4. sort with lambda:");
        List<String> sortedByLength = new ArrayList<>(fruits);
        sortedByLength.sort((a, b) -> a.length() - b.length());
        System.out.println("   By length: " + sortedByLength);

        sortedByLength.sort(Comparator.comparingInt(String::length).reversed());
        System.out.println("   By length (desc): " + sortedByLength);

        // Map operations
        System.out.println("\n5. Map operations:");
        Map<String, Integer> fruitPrices = new HashMap<>();
        fruitPrices.put("Apple", 100);
        fruitPrices.put("Banana", 50);
        fruitPrices.put("Cherry", 200);

        fruitPrices.forEach((fruit, price) -> System.out.println("   " + fruit + ": $" + price));

        // computeIfAbsent
        fruitPrices.computeIfAbsent("Date", k -> 150);
        System.out.println("   After computeIfAbsent: " + fruitPrices);

        // replaceAll on Map
        fruitPrices.replaceAll((k, v) -> v + 10); // Add $10 to all
        System.out.println("   After +10: " + fruitPrices);

        System.out.println();
    }

    // ========================================================================
    // STREAM API
    // ========================================================================

    /**
     * Stream API with lambdas
     */
    private static void demonstrateStreamAPI() {
        System.out.println("--- 7. Stream API with Lambdas ---\n");

        List<Employee> employees = Arrays.asList(
                new Employee("Alice", "Engineering", 75000),
                new Employee("Bob", "Marketing", 55000),
                new Employee("Charlie", "Engineering", 90000),
                new Employee("Diana", "HR", 45000),
                new Employee("Eve", "Engineering", 85000),
                new Employee("Frank", "Marketing", 60000));

        // Filter
        System.out.println("1. Filter (salary > 60000):");
        employees.stream()
                .filter(e -> e.getSalary() > 60000)
                .forEach(e -> System.out.println("   " + e));

        // Map
        System.out.println("\n2. Map (get names):");
        List<String> names = employees.stream()
                .map(Employee::getName)
                .toList();
        System.out.println("   Names: " + names);

        // Reduce
        System.out.println("\n3. Reduce (total salary):");
        double totalSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .sum();
        System.out.println("   Total: $" + totalSalary);

        // Group by
        System.out.println("\n4. Collect with groupingBy:");
        Map<String, List<Employee>> byDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        byDept.forEach((dept, emps) -> {
            System.out.println("   " + dept + ": " + emps.size() + " employees");
        });

        // Complex pipeline
        System.out.println("\n5. Complex pipeline:");
        System.out.println("   (Engineering dept, salary > 80k, sorted by name)");
        employees.stream()
                .filter(e -> e.getDepartment().equals("Engineering"))
                .filter(e -> e.getSalary() > 80000)
                .sorted(Comparator.comparing(Employee::getName))
                .forEach(e -> System.out.println("   " + e.getName() + ": $" + e.getSalary()));

        // Average
        System.out.println("\n6. Average salary by department:");
        Map<String, Double> avgByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)));
        avgByDept.forEach((dept, avg) -> System.out.printf("   %s: $%.2f%n", dept, avg));

        System.out.println();
    }

    // ========================================================================
    // FUNCTION COMPOSITION
    // ========================================================================

    /**
     * Composing functions together
     */
    private static void demonstrateCompositionOfFunctions() {
        System.out.println("--- 8. Function Composition ---\n");

        // Basic functions
        Function<Integer, Integer> multiplyBy2 = x -> x * 2;
        Function<Integer, Integer> add10 = x -> x + 10;
        Function<Integer, Integer> square = x -> x * x;

        // andThen - applies functions in sequence
        System.out.println("1. andThen (left to right):");
        Function<Integer, Integer> multiplyThenAdd = multiplyBy2.andThen(add10);
        System.out.println("   (5 * 2) + 10 = " + multiplyThenAdd.apply(5));

        // compose - applies functions in reverse order
        System.out.println("\n2. compose (right to left):");
        Function<Integer, Integer> addThenMultiply = multiplyBy2.compose(add10);
        System.out.println("   (5 + 10) * 2 = " + addThenMultiply.apply(5));

        // Chain multiple functions
        System.out.println("\n3. Chaining multiple functions:");
        Function<Integer, Integer> pipeline = multiplyBy2
                .andThen(add10)
                .andThen(square);
        System.out.println("   ((5 * 2) + 10)² = " + pipeline.apply(5));

        // Predicate composition
        System.out.println("\n4. Predicate composition:");
        Predicate<Integer> greaterThan10 = x -> x > 10;
        Predicate<Integer> lessThan100 = x -> x < 100;
        Predicate<Integer> isEven = x -> x % 2 == 0;

        Predicate<Integer> inRangeAndEven = greaterThan10
                .and(lessThan100)
                .and(isEven);

        System.out.println("   50: inRange(10-100) AND even? " + inRangeAndEven.test(50));
        System.out.println("   51: inRange(10-100) AND even? " + inRangeAndEven.test(51));
        System.out.println("   5: inRange(10-100) AND even? " + inRangeAndEven.test(5));

        // Consumer chaining
        System.out.println("\n5. Consumer chaining:");
        Consumer<String> step1 = s -> System.out.println("   Step 1: Received '" + s + "'");
        Consumer<String> step2 = s -> System.out.println("   Step 2: Processing '" + s.toUpperCase() + "'");
        Consumer<String> step3 = s -> System.out.println("   Step 3: Completed for '" + s + "'");

        Consumer<String> pipeline2 = step1.andThen(step2).andThen(step3);
        pipeline2.accept("data");

        System.out.println();
    }

    // ========================================================================
    // CUSTOM FUNCTIONAL INTERFACES
    // ========================================================================

    /**
     * Creating and using custom functional interfaces
     */
    private static void demonstrateCustomFunctionalInterfaces() {
        System.out.println("--- 9. Custom Functional Interfaces ---\n");

        // TriFunction (3 parameters)
        TriFunction<Integer, Integer, Integer, Integer> sumThree = (a, b, c) -> a + b + c;
        System.out.println("1. TriFunction (3 params):");
        System.out.println("   Sum of 1, 2, 3: " + sumThree.apply(1, 2, 3));

        // Checked exception handling
        System.out.println("\n2. ThrowingFunction (handles exceptions):");
        ThrowingFunction<String, Integer> parseWithException = Integer::parseInt;
        try {
            System.out.println("   Parse '123': " + parseWithException.apply("123"));
            System.out.println("   Parse 'abc': " + parseWithException.apply("abc"));
        } catch (Exception e) {
            System.out.println("   Exception caught: " + e.getClass().getSimpleName());
        }

        // Builder pattern with lambda
        System.out.println("\n3. Builder with lambda configurator:");
        Configuration config = Configuration.builder()
                .configure(c -> c.setHost("localhost"))
                .configure(c -> c.setPort(8080))
                .configure(c -> c.setTimeout(30))
                .build();
        System.out.println("   " + config);

        System.out.println();
    }

    // ========================================================================
    // REAL-WORLD EXAMPLES
    // ========================================================================

    /**
     * Practical real-world lambda usage
     */
    private static void demonstrateRealWorldExamples() {
        System.out.println("--- 10. Real-World Examples ---\n");

        // 1. Event handling
        System.out.println("1. Event/Callback handling:");
        EventEmitter emitter = new EventEmitter();
        emitter.on("click", () -> System.out.println("   Button clicked!"));
        emitter.on("hover", () -> System.out.println("   Mouse hovering..."));
        emitter.emit("click");
        emitter.emit("hover");

        // 2. Strategy pattern
        System.out.println("\n2. Strategy pattern:");
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(100);
        cart.addItem(200);
        cart.addItem(150);

        // Different pricing strategies
        PricingStrategy regular = total -> total;
        PricingStrategy member = total -> total * 0.9; // 10% discount
        PricingStrategy premium = total -> total * 0.8; // 20% discount

        System.out.println("   Regular price: $" + cart.checkout(regular));
        System.out.println("   Member price: $" + cart.checkout(member));
        System.out.println("   Premium price: $" + cart.checkout(premium));

        // 3. Validation chain
        System.out.println("\n3. Validation chain:");
        ValidationResult result = ValidationChain.<String>start()
                .addRule(s -> !s.isEmpty(), "Cannot be empty")
                .addRule(s -> s.length() >= 3, "Minimum 3 characters")
                .addRule(s -> s.matches("[a-zA-Z]+"), "Only letters allowed")
                .validate("Hello");
        System.out.println("   'Hello' valid? " + result.isValid());

        ValidationResult result2 = ValidationChain.<String>start()
                .addRule(s -> !s.isEmpty(), "Cannot be empty")
                .addRule(s -> s.length() >= 3, "Minimum 3 characters")
                .validate("Hi");
        System.out.println("   'Hi' valid? " + result2.isValid() +
                " - Error: " + result2.getError());

        // 4. Retry mechanism
        System.out.println("\n4. Retry mechanism:");
        RetryExecutor.execute(
                () -> {
                    System.out.println("   Attempting operation...");
                    if (Math.random() < 0.7) { // 70% chance of failure
                        throw new RuntimeException("Simulated failure");
                    }
                    return "Success!";
                },
                3, // max retries
                result3 -> System.out.println("   Result: " + result3),
                error -> System.out.println("   All retries failed: " + error.getMessage()));

        System.out.println("\n" + "=".repeat(60));
        System.out.println("LAMBDA EXPRESSIONS SUMMARY:");
        System.out.println("=".repeat(60));
        System.out.println("✓ Concise alternative to anonymous classes");
        System.out.println("✓ Requires functional interface (single abstract method)");
        System.out.println("✓ Supports method references (::)");
        System.out.println("✓ Enables functional programming in Java");
        System.out.println("✓ Essential for Stream API");
        System.out.println("✓ Variables must be effectively final");
        System.out.println("✓ 'this' refers to enclosing class");
        System.out.println("=".repeat(60));
        System.out.println("\nLambda Expressions demonstration completed!");
    }
}

// ============================================================================
// CUSTOM FUNCTIONAL INTERFACES
// ============================================================================

/**
 * Custom transformer functional interface
 */
@FunctionalInterface
interface Transformer<T, R> {
    R transform(T input);
}

/**
 * Validator with default method
 */
@FunctionalInterface
interface Validator<T> {
    boolean validate(T value);

    default boolean isInvalid(T value) {
        return !validate(value);
    }
}

/**
 * TriFunction - takes 3 parameters
 */
@FunctionalInterface
interface TriFunction<A, B, C, R> {
    R apply(A a, B b, C c);
}

/**
 * Function that can throw checked exceptions
 */
@FunctionalInterface
interface ThrowingFunction<T, R> {
    R apply(T t) throws Exception;
}

/**
 * Pricing strategy for shopping cart
 */
@FunctionalInterface
interface PricingStrategy {
    double applyDiscount(double total);
}

// ============================================================================
// SUPPORTING CLASSES
// ============================================================================

/**
 * Employee class for Stream API demo
 */
class Employee {
    private String name;
    private String department;
    private double salary;

    Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    String getName() {
        return name;
    }

    String getDepartment() {
        return department;
    }

    double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return name + " (" + department + "): $" + salary;
    }
}

/**
 * Configuration builder demonstrating lambda configurator
 */
class Configuration {
    private String host;
    private int port;
    private int timeout;

    void setHost(String host) {
        this.host = host;
    }

    void setPort(int port) {
        this.port = port;
    }

    void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    @Override
    public String toString() {
        return "Config{host='" + host + "', port=" + port + ", timeout=" + timeout + "}";
    }

    static ConfigurationBuilder builder() {
        return new ConfigurationBuilder();
    }
}

class ConfigurationBuilder {
    private Configuration config = new Configuration();

    ConfigurationBuilder configure(Consumer<Configuration> configurator) {
        configurator.accept(config);
        return this;
    }

    Configuration build() {
        return config;
    }
}

/**
 * Simple event emitter using lambdas
 */
class EventEmitter {
    private Map<String, Runnable> handlers = new HashMap<>();

    void on(String event, Runnable handler) {
        handlers.put(event, handler);
    }

    void emit(String event) {
        Runnable handler = handlers.get(event);
        if (handler != null) {
            handler.run();
        }
    }
}

/**
 * Shopping cart with strategy pattern
 */
class ShoppingCart {
    private List<Double> items = new ArrayList<>();

    void addItem(double price) {
        items.add(price);
    }

    double checkout(PricingStrategy strategy) {
        double total = items.stream().mapToDouble(d -> d).sum();
        return strategy.applyDiscount(total);
    }
}

/**
 * Validation chain
 */
class ValidationChain<T> {
    private List<Predicate<T>> rules = new ArrayList<>();
    private List<String> messages = new ArrayList<>();

    static <T> ValidationChain<T> start() {
        return new ValidationChain<>();
    }

    ValidationChain<T> addRule(Predicate<T> rule, String errorMessage) {
        rules.add(rule);
        messages.add(errorMessage);
        return this;
    }

    ValidationResult validate(T value) {
        for (int i = 0; i < rules.size(); i++) {
            if (!rules.get(i).test(value)) {
                return new ValidationResult(false, messages.get(i));
            }
        }
        return new ValidationResult(true, null);
    }
}

class ValidationResult {
    private boolean valid;
    private String error;

    ValidationResult(boolean valid, String error) {
        this.valid = valid;
        this.error = error;
    }

    boolean isValid() {
        return valid;
    }

    String getError() {
        return error;
    }
}

/**
 * Retry executor with callbacks
 */
class RetryExecutor {
    static <T> void execute(
            Supplier<T> operation,
            int maxRetries,
            Consumer<T> onSuccess,
            Consumer<Exception> onFailure) {

        Exception lastException = null;
        for (int i = 0; i < maxRetries; i++) {
            try {
                T result = operation.get();
                onSuccess.accept(result);
                return;
            } catch (Exception e) {
                lastException = e;
                System.out.println("   Retry " + (i + 1) + "/" + maxRetries + " failed");
            }
        }
        onFailure.accept(lastException);
    }
}
