package com.fundamentals.oop;

/**
 * Abstraction in Java
 * 
 * Abstraction: Hiding implementation details and showing only functionality.
 * 
 * Abstract Class:
 * - Cannot be instantiated
 * - Can have abstract methods (no body) and concrete methods (with body)
 * - Can have constructors, fields, static methods
 * - Use 'abstract' keyword
 * - Child class must implement all abstract methods (or be abstract itself)
 * 
 * Abstract Method:
 * - Method without implementation (no body)
 * - Must be in abstract class
 * - Child class must provide implementation
 * 
 * When to use:
 * - Common base functionality with some methods to be implemented by subclasses
 * - Partial implementation (some methods concrete, some abstract)
 * - When you want to provide default behavior but force subclasses to implement
 * certain methods
 * 
 * Abstract Class vs Interface:
 * - Abstract class: Can have state (fields), constructors, concrete methods
 * - Interface: Only method signatures (Java 8+ allows default/static methods)
 */
public class Abstraction {

    public static void main(String[] args) {
        System.out.println("=== Abstraction ===\n");

        demonstrateAbstractClass();
        demonstrateAbstractMethods();
        demonstrateConcreteMethodsInAbstract();
        demonstrateConstructorInAbstract();
        demonstrateMultipleAbstractLevels();
        demonstrateRealWorldExample();
    }

    /**
     * Abstract class basics
     */
    private static void demonstrateAbstractClass() {
        System.out.println("--- Abstract Class Basics ---");

        // Cannot instantiate abstract class
        // AnimalAbstract animal = new AnimalAbstract(); // ERROR

        // Can create objects of concrete subclasses
        DogAbstract dog = new DogAbstract("Buddy");
        dog.eat();
        dog.sleep();
        dog.makeSound(); // Abstract method implemented

        System.out.println();

        CatAbstract cat = new CatAbstract("Whiskers");
        cat.eat();
        cat.sleep();
        cat.makeSound(); // Different implementation

        System.out.println("\nAbstract classes cannot be instantiated");
    }

    /**
     * Abstract methods
     */
    private static void demonstrateAbstractMethods() {
        System.out.println("\n--- Abstract Methods ---");

        // Polymorphism with abstract class
        AnimalAbstract[] animals = {
                new DogAbstract("Max"),
                new CatAbstract("Luna"),
                new DogAbstract("Rocky")
        };

        for (AnimalAbstract animal : animals) {
            animal.makeSound(); // Each calls its own implementation
        }

        System.out.println("\nAbstract methods must be implemented by subclasses");
    }

    /**
     * Concrete methods in abstract class
     */
    private static void demonstrateConcreteMethodsInAbstract() {
        System.out.println("\n--- Concrete Methods in Abstract Class ---");

        BirdAbstract sparrow = new Sparrow("Sparrow");
        sparrow.eat(); // Concrete method from abstract class
        sparrow.sleep(); // Concrete method from abstract class
        sparrow.fly(); // Abstract method implemented
        sparrow.makeSound(); // Abstract method implemented

        System.out.println("\nAbstract classes can have both abstract and concrete methods");
    }

    /**
     * Constructor in abstract class
     */
    private static void demonstrateConstructorInAbstract() {
        System.out.println("\n--- Constructor in Abstract Class ---");

        VehicleAbstract car = new CarAbstract("Toyota", 4);
        car.displayInfo(); // Uses constructor
        car.start();
        car.stop();

        System.out.println("\nAbstract classes can have constructors");
    }

    /**
     * Multiple levels of abstraction
     */
    private static void demonstrateMultipleAbstractLevels() {
        System.out.println("\n--- Multiple Abstract Levels ---");

        SmartphoneAbstract phone = new SmartphoneAbstract("iPhone", "Apple");
        phone.powerOn();
        phone.powerOff();
        phone.makeCall("123-456-7890");
        phone.sendMessage("Hello!");
        phone.browseInternet("www.example.com");

        System.out.println("\nAbstract classes can extend other abstract classes");
    }

    /**
     * Real-world example
     */
    private static void demonstrateRealWorldExample() {
        System.out.println("\n--- Real-World Example: Banking ---");

        BankAccountAbstract savings = new SavingsAccount("SA001", "Alice", 1000);
        savings.deposit(500);
        savings.withdraw(200);
        savings.displayBalance();
        savings.calculateInterest();

        System.out.println();

        BankAccountAbstract checking = new CheckingAccount("CA001", "Bob", 2000);
        checking.deposit(300);
        checking.withdraw(100);
        checking.displayBalance();
        checking.calculateInterest();

        System.out.println("\nExercise completed!");
    }
}

/**
 * Abstract class - Animal
 */
abstract class AnimalAbstract {
    protected String name;

    public AnimalAbstract(String name) {
        this.name = name;
    }

    // Concrete methods
    public void eat() {
        System.out.println(name + " is eating");
    }

    public void sleep() {
        System.out.println(name + " is sleeping");
    }

    // Abstract method (no implementation)
    public abstract void makeSound();
}

/**
 * Concrete class - Dog
 */
class DogAbstract extends AnimalAbstract {
    public DogAbstract(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(name + " says: Woof! Woof!");
    }
}

/**
 * Concrete class - Cat
 */
class CatAbstract extends AnimalAbstract {
    public CatAbstract(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(name + " says: Meow! Meow!");
    }
}

/**
 * Abstract class with multiple abstract methods
 */
abstract class BirdAbstract extends AnimalAbstract {
    public BirdAbstract(String name) {
        super(name);
    }

    // Additional abstract method
    public abstract void fly();
}

/**
 * Concrete class - Sparrow
 */
class Sparrow extends BirdAbstract {
    public Sparrow(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(name + " says: Chirp! Chirp!");
    }

    @Override
    public void fly() {
        System.out.println(name + " is flying");
    }
}

/**
 * Abstract class - Vehicle
 */
abstract class VehicleAbstract {
    protected String brand;

    public VehicleAbstract(String brand) {
        this.brand = brand;
        System.out.println("VehicleAbstract constructor called");
    }

    public void displayInfo() {
        System.out.println("Brand: " + brand);
    }

    public abstract void start();

    public abstract void stop();
}

/**
 * Concrete class - Car
 */
class CarAbstract extends VehicleAbstract {
    private int doors;

    public CarAbstract(String brand, int doors) {
        super(brand);
        this.doors = doors;
    }

    @Override
    public void start() {
        System.out.println(brand + " car with " + doors + " doors is starting");
    }

    @Override
    public void stop() {
        System.out.println(brand + " car has stopped");
    }
}

/**
 * Abstract class - Device
 */
abstract class Device {
    protected String name;

    public Device(String name) {
        this.name = name;
    }

    public abstract void powerOn();

    public abstract void powerOff();
}

/**
 * Abstract class - Phone extends Device
 */
abstract class PhoneAbstract extends Device {
    protected String manufacturer;

    public PhoneAbstract(String name, String manufacturer) {
        super(name);
        this.manufacturer = manufacturer;
    }

    public abstract void makeCall(String number);

    public abstract void sendMessage(String message);
}

/**
 * Concrete class - Smartphone
 */
class SmartphoneAbstract extends PhoneAbstract {
    public SmartphoneAbstract(String name, String manufacturer) {
        super(name, manufacturer);
    }

    @Override
    public void powerOn() {
        System.out.println(name + " by " + manufacturer + " is powering on");
    }

    @Override
    public void powerOff() {
        System.out.println(name + " is powering off");
    }

    @Override
    public void makeCall(String number) {
        System.out.println("Calling " + number);
    }

    @Override
    public void sendMessage(String message) {
        System.out.println("Sending message: " + message);
    }

    public void browseInternet(String url) {
        System.out.println("Browsing: " + url);
    }
}

/**
 * Abstract class - BankAccount
 */
abstract class BankAccountAbstract {
    protected String accountNumber;
    protected String ownerName;
    protected double balance;

    public BankAccountAbstract(String accountNumber, String ownerName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = initialBalance;
    }

    // Concrete methods
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        } else {
            System.out.println("Invalid withdrawal");
        }
    }

    public void displayBalance() {
        System.out.println("Balance: $" + balance);
    }

    // Abstract method - different for each account type
    public abstract void calculateInterest();
}

/**
 * Concrete class - SavingsAccount
 */
class SavingsAccount extends BankAccountAbstract {
    private static final double INTEREST_RATE = 0.04; // 4%

    public SavingsAccount(String accountNumber, String ownerName, double initialBalance) {
        super(accountNumber, ownerName, initialBalance);
    }

    @Override
    public void calculateInterest() {
        double interest = balance * INTEREST_RATE;
        System.out.println("Savings interest: $" + interest);
    }
}

/**
 * Concrete class - CheckingAccount
 */
class CheckingAccount extends BankAccountAbstract {
    private static final double INTEREST_RATE = 0.01; // 1%

    public CheckingAccount(String accountNumber, String ownerName, double initialBalance) {
        super(accountNumber, ownerName, initialBalance);
    }

    @Override
    public void calculateInterest() {
        double interest = balance * INTEREST_RATE;
        System.out.println("Checking interest: $" + interest);
    }
}
