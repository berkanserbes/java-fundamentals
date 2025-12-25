package com.fundamentals.oop;

/**
 * Encapsulation in Java
 * 
 * Encapsulation: Bundling data (fields) and methods that operate on the data
 * into a single unit (class) and restricting direct access to some components.
 * 
 * Key Principles:
 * - Make fields private
 * - Provide public getter/setter methods
 * - Validate data in setters
 * - Hide implementation details
 * 
 * Benefits:
 * - Data hiding and security
 * - Flexibility to change implementation
 * - Better control over data
 * - Easier maintenance
 * - Reusability
 * 
 * Access Modifiers:
 * - private: Only within same class
 * - default (package-private): Same package
 * - protected: Same package + subclasses
 * - public: Everywhere
 */
public class Encapsulation {

    public static void main(String[] args) {
        System.out.println("=== Encapsulation ===\n");

        demonstrateBasicEncapsulation();
        demonstrateDataValidation();
        demonstrateReadOnlyFields();
        demonstrateWriteOnlyFields();
        demonstrateAccessModifiers();
        demonstrateBestPractices();
    }

    /**
     * Basic encapsulation with getters and setters
     */
    private static void demonstrateBasicEncapsulation() {
        System.out.println("--- Basic Encapsulation ---");

        PersonEncap person = new PersonEncap();

        // Cannot access private fields directly
        // person.name = "Alice"; // ERROR: name is private

        // Use setters to modify
        person.setName("Alice");
        person.setAge(25);
        person.setEmail("alice@example.com");

        // Use getters to access
        System.out.println("Name: " + person.getName());
        System.out.println("Age: " + person.getAge());
        System.out.println("Email: " + person.getEmail());

        person.displayInfo();
    }

    /**
     * Data validation in setters
     */
    private static void demonstrateDataValidation() {
        System.out.println("\n--- Data Validation ---");

        BankAccountEncap account = new BankAccountEncap("ACC001", "Bob");

        // Valid deposit
        account.deposit(1000);
        System.out.println("Balance after deposit: $" + account.getBalance());

        // Invalid deposit (negative amount)
        account.deposit(-500);
        System.out.println("Balance after invalid deposit: $" + account.getBalance());

        // Valid withdrawal
        account.withdraw(300);
        System.out.println("Balance after withdrawal: $" + account.getBalance());

        // Invalid withdrawal (insufficient funds)
        account.withdraw(1000);
        System.out.println("Balance after invalid withdrawal: $" + account.getBalance());

        // Invalid withdrawal (negative amount)
        account.withdraw(-100);
        System.out.println("Balance after invalid withdrawal: $" + account.getBalance());
    }

    /**
     * Read-only fields (only getter, no setter)
     */
    private static void demonstrateReadOnlyFields() {
        System.out.println("\n--- Read-Only Fields ---");

        StudentEncap student = new StudentEncap("S001", "Charlie");

        // Can read but cannot modify
        System.out.println("Student ID: " + student.getStudentId());
        System.out.println("Name: " + student.getName());

        // student.setStudentId("S002"); // No setter available

        // Can modify name (has setter)
        student.setName("Charles");
        System.out.println("Updated name: " + student.getName());

        System.out.println("Student ID is read-only (immutable)");
    }

    /**
     * Write-only fields (only setter, no getter)
     */
    private static void demonstrateWriteOnlyFields() {
        System.out.println("\n--- Write-Only Fields ---");

        UserAccount user = new UserAccount("alice");

        // Can set password but cannot read it
        user.setPassword("secret123");

        // user.getPassword(); // No getter available

        // Can verify password
        System.out.println("Password correct: " + user.verifyPassword("secret123"));
        System.out.println("Password incorrect: " + user.verifyPassword("wrong"));

        System.out.println("Password is write-only (security)");
    }

    /**
     * Access modifiers demonstration
     */
    private static void demonstrateAccessModifiers() {
        System.out.println("\n--- Access Modifiers ---");

        AccessDemo demo = new AccessDemo();

        // public: accessible
        demo.publicMethod();

        // private: not accessible
        // demo.privateMethod(); // ERROR

        // default (package-private): accessible in same package
        demo.defaultMethod();

        // protected: accessible in same package
        demo.protectedMethod();

        System.out.println("\nAccess modifiers control visibility");
    }

    /**
     * Best practices
     */
    private static void demonstrateBestPractices() {
        System.out.println("\n--- Best Practices ---");

        RectangleEncap rect = new RectangleEncap(10, 20);

        System.out.println("Width: " + rect.getWidth());
        System.out.println("Height: " + rect.getHeight());
        System.out.println("Area: " + rect.getArea());
        System.out.println("Perimeter: " + rect.getPerimeter());

        // Validation in setters
        rect.setWidth(15);
        rect.setHeight(25);
        System.out.println("\nAfter modification:");
        System.out.println("Area: " + rect.getArea());

        // Invalid values rejected
        rect.setWidth(-5);
        rect.setHeight(0);
        System.out.println("\nAfter invalid modification:");
        System.out.println("Area: " + rect.getArea());

        System.out.println("\nExercise completed!");
    }
}

/**
 * Person class with basic encapsulation
 */
class PersonEncap {
    // Private fields
    private String name;
    private int age;
    private String email;

    // Public getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    // Public setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void displayInfo() {
        System.out.println("Person: " + name + ", " + age + " years old, " + email);
    }
}

/**
 * BankAccount with data validation
 */
class BankAccountEncap {
    private String accountNumber;
    private String ownerName;
    private double balance;

    public BankAccountEncap(String accountNumber, String ownerName) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = 0.0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    // Deposit with validation
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount: $" + amount);
        }
    }

    // Withdraw with validation
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount: $" + amount);
        } else if (amount > balance) {
            System.out.println("Insufficient funds. Balance: $" + balance);
        } else {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        }
    }
}

/**
 * Student with read-only field
 */
class StudentEncap {
    private final String studentId; // Read-only (final)
    private String name;

    public StudentEncap(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    // Getter only (no setter for studentId)
    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

/**
 * UserAccount with write-only field
 */
class UserAccount {
    private String username;
    private String password; // Write-only

    public UserAccount(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    // Setter only (no getter for password)
    public void setPassword(String password) {
        this.password = password;
    }

    // Method to verify password without exposing it
    public boolean verifyPassword(String password) {
        return this.password != null && this.password.equals(password);
    }
}

/**
 * Access modifiers demonstration
 */
class AccessDemo {
    private String privateField = "Private";
    String defaultField = "Default"; // package-private
    protected String protectedField = "Protected";
    public String publicField = "Public";

    private void privateMethod() {
        System.out.println("Private method");
    }

    void defaultMethod() {
        System.out.println("Default (package-private) method");
    }

    protected void protectedMethod() {
        System.out.println("Protected method");
    }

    public void publicMethod() {
        System.out.println("Public method");
        // Can access all members within same class
        System.out.println("  " + privateField);
        System.out.println("  " + defaultField);
        System.out.println("  " + protectedField);
        System.out.println("  " + publicField);
    }
}

/**
 * Rectangle with proper encapsulation
 */
class RectangleEncap {
    private int width;
    private int height;

    public RectangleEncap(int width, int height) {
        setWidth(width); // Use setter for validation
        setHeight(height);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        if (width > 0) {
            this.width = width;
        } else {
            System.out.println("Invalid width: " + width);
        }
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (height > 0) {
            this.height = height;
        } else {
            System.out.println("Invalid height: " + height);
        }
    }

    // Calculated properties (no setter)
    public int getArea() {
        return width * height;
    }

    public int getPerimeter() {
        return 2 * (width + height);
    }
}
