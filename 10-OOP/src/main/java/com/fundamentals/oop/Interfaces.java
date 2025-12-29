package com.fundamentals.oop;

/**
 * Interfaces in Java
 * 
 * Interface: Contract that defines what a class must do, but not how.
 * 
 * Key Characteristics:
 * - All methods are public and abstract by default (before Java 8)
 * - All fields are public, static, and final (constants)
 * - Cannot be instantiated
 * - Class implements interface using 'implements' keyword
 * - Class can implement multiple interfaces
 * - Interface can extend other interfaces
 * 
 * Java 8+ Features:
 * - Default methods: Methods with implementation
 * - Static methods: Utility methods in interface
 * 
 * Java 9+ Features:
 * - Private methods: Helper methods for default methods
 * 
 * Interface vs Abstract Class:
 * - Interface: Pure abstraction, multiple inheritance
 * - Abstract Class: Partial abstraction, single inheritance, can have state
 * 
 * When to use:
 * - Define contract for unrelated classes
 * - Achieve multiple inheritance
 * - Loose coupling
 */
public class Interfaces {

    public static void main(String[] args) {
        System.out.println("=== Interfaces ===\n");

        demonstrateBasicInterface();
        demonstrateMultipleInterfaces();
        demonstrateInterfaceInheritance();
        demonstrateDefaultMethods();
        demonstrateStaticMethods();
        demonstratePolymorphismWithInterfaces();
        demonstrateRealWorldExample();
    }

    /**
     * Basic interface implementation
     */
    private static void demonstrateBasicInterface() {
        System.out.println("--- Basic Interface ---");

        Drawable circle = new CircleDrawable(5);
        circle.draw();

        Drawable rectangle = new RectangleDrawable(4, 6);
        rectangle.draw();

        System.out.println("\nClasses implement interface contract");
    }

    /**
     * Multiple interface implementation
     */
    private static void demonstrateMultipleInterfaces() {
        System.out.println("\n--- Multiple Interfaces ---");

        SmartPhone phone = new SmartPhone();

        // Can use as Phone
        phone.call("123-456-7890");
        phone.sendSMS("Hello!");

        // Can use as Camera
        phone.takePhoto();
        phone.recordVideo();

        // Can use as MusicPlayer
        phone.play();
        phone.pause();
        phone.stop();

        System.out.println("\nClass can implement multiple interfaces");
    }

    /**
     * Interface inheritance
     */
    private static void demonstrateInterfaceInheritance() {
        System.out.println("\n--- Interface Inheritance ---");

        ElectricCar tesla = new ElectricCar("Tesla Model 3");
        tesla.start();
        tesla.stop();
        tesla.charge();

        System.out.println("\nInterfaces can extend other interfaces");
    }

    /**
     * Default methods (Java 8+)
     */
    private static void demonstrateDefaultMethods() {
        System.out.println("\n--- Default Methods ---");

        PaymentProcessor creditCard = new CreditCardProcessor();
        creditCard.processPayment(100.0);
        creditCard.refund(50.0); // Default method

        System.out.println();

        PaymentProcessor paypal = new PayPalProcessor();
        paypal.processPayment(200.0);
        paypal.refund(75.0); // Can override default method

        System.out.println("\nDefault methods provide default implementation");
    }

    /**
     * Static methods in interfaces
     */
    private static void demonstrateStaticMethods() {
        System.out.println("\n--- Static Methods ---");

        // Call static method from interface
        MathOperations.printInfo();

        int sum = MathOperations.add(10, 20);
        int product = MathOperations.multiply(5, 6);

        System.out.println("Sum: " + sum);
        System.out.println("Product: " + product);

        System.out.println("\nStatic methods belong to interface");
    }

    /**
     * Polymorphism with interfaces
     */
    private static void demonstratePolymorphismWithInterfaces() {
        System.out.println("\n--- Polymorphism with Interfaces ---");

        Playable[] media = {
                new VideoFile("movie.mp4"),
                new AudioFile("song.mp3"),
                new VideoFile("tutorial.avi")
        };

        for (Playable item : media) {
            item.play();
            item.pause();
            item.stop();
            System.out.println();
        }

        System.out.println("Interface enables polymorphism");
    }

    /**
     * Real-world example
     */
    private static void demonstrateRealWorldExample() {
        System.out.println("\n--- Real-World Example: Database ---");

        Database mysql = new MySQLDatabase();
        mysql.connect();
        mysql.executeQuery("SELECT * FROM users");
        mysql.disconnect();

        System.out.println();

        Database mongodb = new MongoDBDatabase();
        mongodb.connect();
        mongodb.executeQuery("db.users.find()");
        mongodb.disconnect();

        System.out.println("\nExercise completed!");
    }
}

/**
 * Basic interface
 */
interface Drawable {
    void draw(); // Abstract method (public abstract by default)
}

/**
 * Circle implementing Drawable
 */
class CircleDrawable implements Drawable {
    private double radius;

    public CircleDrawable(double radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("Drawing circle with radius: " + radius);
    }
}

/**
 * Rectangle implementing Drawable
 */
class RectangleDrawable implements Drawable {
    private double width;
    private double height;

    public RectangleDrawable(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw() {
        System.out.println("Drawing rectangle: " + width + " x " + height);
    }
}

/**
 * Phone interface
 */
interface Phone {
    void call(String number);

    void sendSMS(String message);
}

/**
 * Camera interface
 */
interface Camera {
    void takePhoto();

    void recordVideo();
}

/**
 * MusicPlayer interface
 */
interface MusicPlayer {
    void play();

    void pause();

    void stop();
}

/**
 * SmartPhone implementing multiple interfaces
 */
class SmartPhone implements Phone, Camera, MusicPlayer {
    @Override
    public void call(String number) {
        System.out.println("Calling: " + number);
    }

    @Override
    public void sendSMS(String message) {
        System.out.println("Sending SMS: " + message);
    }

    @Override
    public void takePhoto() {
        System.out.println("Taking photo");
    }

    @Override
    public void recordVideo() {
        System.out.println("Recording video");
    }

    @Override
    public void play() {
        System.out.println("Playing music");
    }

    @Override
    public void pause() {
        System.out.println("Pausing music");
    }

    @Override
    public void stop() {
        System.out.println("Stopping music");
    }
}

/**
 * Vehicle interface
 */
interface VehicleInterface {
    void start();

    void stop();
}

/**
 * Electric interface extends Vehicle
 */
interface Electric extends VehicleInterface {
    void charge();
}

/**
 * ElectricCar implementing Electric (which extends Vehicle)
 */
class ElectricCar implements Electric {
    private String model;

    public ElectricCar(String model) {
        this.model = model;
    }

    @Override
    public void start() {
        System.out.println(model + " is starting silently");
    }

    @Override
    public void stop() {
        System.out.println(model + " has stopped");
    }

    @Override
    public void charge() {
        System.out.println(model + " is charging");
    }
}

/**
 * Interface with default method
 */
interface PaymentProcessor {
    void processPayment(double amount);

    // Default method (Java 8+)
    default void refund(double amount) {
        System.out.println("Processing refund: $" + amount);
    }
}

/**
 * CreditCard processor
 */
class CreditCardProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment: $" + amount);
    }
}

/**
 * PayPal processor with overridden default method
 */
class PayPalProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment: $" + amount);
    }

    @Override
    public void refund(double amount) {
        System.out.println("Processing PayPal refund: $" + amount);
    }
}

/**
 * Interface with static methods
 */
interface MathOperations {
    // Static method
    static void printInfo() {
        System.out.println("Math Operations Interface");
    }

    // Default (instance) method — a default method belongs to an instance, not to
    // the interface itself.
    // It is implicitly public and provides a concrete implementation inside the
    // interface.
    // Classes that implement this interface inherit it automatically (unless they
    // override it).
    // Unlike static methods, it is invoked on an object (e.g., obj.method()), not
    // on the interface name.
    default int squareSum(int a, int b) {
        int sum = add(a, b);
        return sum * sum;
    }

    static int add(int a, int b) {
        return a + b;
    }

    static int multiply(int a, int b) {
        return a * b;
    }
}

/**
 * Playable interface
 */
interface Playable {
    void play();

    void pause();

    void stop();
}

/**
 * VideoFile implementing Playable
 */
class VideoFile implements Playable {
    private String filename;

    public VideoFile(String filename) {
        this.filename = filename;
    }

    @Override
    public void play() {
        System.out.println("Playing video: " + filename);
    }

    @Override
    public void pause() {
        System.out.println("Pausing video: " + filename);
    }

    @Override
    public void stop() {
        System.out.println("Stopping video: " + filename);
    }
}

/**
 * AudioFile implementing Playable
 */
class AudioFile implements Playable {
    private String filename;

    public AudioFile(String filename) {
        this.filename = filename;
    }

    @Override
    public void play() {
        System.out.println("Playing audio: " + filename);
    }

    @Override
    public void pause() {
        System.out.println("Pausing audio: " + filename);
    }

    @Override
    public void stop() {
        System.out.println("Stopping audio: " + filename);
    }
}

/**
 * Database interface
 */
interface Database {
    void connect();

    void disconnect();

    void executeQuery(String query);
}

/**
 * MySQL implementation
 */
class MySQLDatabase implements Database {
    @Override
    public void connect() {
        System.out.println("Connecting to MySQL database");
    }

    @Override
    public void disconnect() {
        System.out.println("Disconnecting from MySQL");
    }

    @Override
    public void executeQuery(String query) {
        System.out.println("Executing MySQL query: " + query);
    }
}

/**
 * MongoDB implementation
 */
class MongoDBDatabase implements Database {
    @Override
    public void connect() {
        System.out.println("Connecting to MongoDB database");
    }

    @Override
    public void disconnect() {
        System.out.println("Disconnecting from MongoDB");
    }

    @Override
    public void executeQuery(String query) {
        System.out.println("Executing MongoDB query: " + query);
    }
}
