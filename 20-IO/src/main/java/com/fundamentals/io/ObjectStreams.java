package com.fundamentals.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================================
 * JAVA OBJECT STREAMS - Serialization and Deserialization
 * ============================================================================
 * 
 * Serialization is the process of converting an object's state to a byte stream.
 * Deserialization is the reverse process - reconstructing an object from bytes.
 * 
 * KEY CLASSES:
 * ------------
 * ObjectOutputStream - Serializes objects (writes to stream)
 * ObjectInputStream  - Deserializes objects (reads from stream)
 * 
 * SERIALIZABLE INTERFACE:
 * -----------------------
 * - Objects must implement java.io.Serializable to be serialized
 * - Serializable is a "marker interface" (no methods)
 * - All fields must be serializable OR marked transient
 * 
 * IMPORTANT CONCEPTS:
 * -------------------
 * 1. serialVersionUID - Version control for serialized objects
 * 2. transient keyword - Excludes field from serialization
 * 3. Inheritance - Superclass must be Serializable OR have no-arg constructor
 * 4. Object graph - Referenced objects are serialized too
 * 
 * USE CASES:
 * ----------
 * - Saving application state
 * - Caching objects to disk
 * - Sending objects over network
 * - Deep cloning objects
 * 
 * SECURITY WARNING:
 * Deserializing untrusted data is dangerous! Can lead to remote code execution.
 * Consider using JSON/XML for data exchange instead.
 * 
 * ============================================================================
 */
public class ObjectStreams {
    
    private static final String DEMO_DIR = "object_demo";
    
    public static void demonstrate() {
        System.out.println();
        System.out.println("=".repeat(70));
        System.out.println("JAVA OBJECT STREAMS - SERIALIZATION");
        System.out.println("=".repeat(70));
        
        setupDemoDirectory();
        
        demonstrateBasicSerialization();
        demonstrateTransientKeyword();
        demonstrateSerialVersionUID();
        demonstrateObjectGraphs();
        demonstrateCustomSerialization();
        
        System.out.println("\n[CLEANUP] Removing demo files...");
        cleanupDemoDirectory();
        
        System.out.println("\n" + "=".repeat(70));
    }
    
    /**
     * Demonstrates basic object serialization and deserialization.
     */
    private static void demonstrateBasicSerialization() {
        System.out.println("\n[1] BASIC SERIALIZATION");
        System.out.println("-".repeat(70));
        
        Path filePath = Path.of(DEMO_DIR, "person.ser");
        
        // Create object
        Person person = new Person("John Doe", 30, "john@example.com");
        
        try {
            // Serialize (write object)
            System.out.println("SERIALIZING OBJECT:");
            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(filePath.toString()))) {
                oos.writeObject(person);
                System.out.println("  Written: " + person);
            }
            
            System.out.println("  File size: " + Files.size(filePath) + " bytes");
            
            // Deserialize (read object)
            System.out.println("\nDESERIALIZING OBJECT:");
            try (ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(filePath.toString()))) {
                Person restored = (Person) ois.readObject();
                System.out.println("  Read: " + restored);
                System.out.println("  Name: " + restored.getName());
                System.out.println("  Age: " + restored.getAge());
            }
            
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\nNOTE: Objects must implement Serializable!");
    }
    
    /**
     * Demonstrates transient keyword to exclude fields.
     */
    private static void demonstrateTransientKeyword() {
        System.out.println("\n[2] TRANSIENT KEYWORD");
        System.out.println("-".repeat(70));
        
        Path filePath = Path.of(DEMO_DIR, "user.ser");
        
        // Create user with password (transient)
        UserAccount user = new UserAccount("alice", "secret123", "alice@email.com");
        
        try {
            System.out.println("ORIGINAL OBJECT:");
            System.out.println("  Username: " + user.getUsername());
            System.out.println("  Password: " + user.getPassword());
            System.out.println("  Email: " + user.getEmail());
            
            // Serialize
            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(filePath.toString()))) {
                oos.writeObject(user);
            }
            
            // Deserialize
            System.out.println("\nAFTER DESERIALIZATION:");
            try (ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(filePath.toString()))) {
                UserAccount restored = (UserAccount) ois.readObject();
                System.out.println("  Username: " + restored.getUsername());
                System.out.println("  Password: " + restored.getPassword() + " (was transient!)");
                System.out.println("  Email: " + restored.getEmail());
            }
            
            System.out.println("\nTRANSIENT FIELDS:");
            System.out.println("  - Not saved during serialization");
            System.out.println("  - Restored as default value (null, 0, false)");
            System.out.println("  - Use for: passwords, cached data, computed values");
            
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates serialVersionUID importance.
     */
    private static void demonstrateSerialVersionUID() {
        System.out.println("\n[3] SERIAL VERSION UID");
        System.out.println("-".repeat(70));
        
        System.out.println("WHAT IS serialVersionUID?");
        System.out.println("  - A unique ID for each Serializable class");
        System.out.println("  - Used to verify sender and receiver compatibility");
        System.out.println("  - If not declared, JVM generates one (risky!)");
        
        System.out.println("\nDECLARATION:");
        System.out.println("  private static final long serialVersionUID = 1L;");
        
        System.out.println("\nWHAT HAPPENS ON MISMATCH?");
        System.out.println("  - InvalidClassException is thrown");
        System.out.println("  - Object cannot be deserialized");
        
        System.out.println("\nBEST PRACTICES:");
        System.out.println("  1. Always declare serialVersionUID explicitly");
        System.out.println("  2. Increment it when making incompatible changes");
        System.out.println("  3. Keep it same for compatible changes (adding fields)");
        
        System.out.println("\nCOMPATIBLE CHANGES:");
        System.out.println("  - Adding new fields (get default values)");
        System.out.println("  - Adding writeObject/readObject methods");
        System.out.println("  - Changing field access modifiers");
        
        System.out.println("\nINCOMPATIBLE CHANGES:");
        System.out.println("  - Removing fields");
        System.out.println("  - Changing field types");
        System.out.println("  - Changing class hierarchy");
        System.out.println("  - Making non-transient field transient");
    }
    
    /**
     * Demonstrates serialization of object graphs.
     */
    private static void demonstrateObjectGraphs() {
        System.out.println("\n[4] OBJECT GRAPHS");
        System.out.println("-".repeat(70));
        
        Path filePath = Path.of(DEMO_DIR, "department.ser");
        
        // Create object graph: Department -> List of Employees
        Department dept = new Department("Engineering");
        dept.addEmployee(new Employee("Alice", "Developer"));
        dept.addEmployee(new Employee("Bob", "Designer"));
        dept.addEmployee(new Employee("Carol", "Manager"));
        
        try {
            System.out.println("ORIGINAL OBJECT GRAPH:");
            System.out.println("  Department: " + dept.getName());
            for (Employee e : dept.getEmployees()) {
                System.out.println("    - " + e.getName() + " (" + e.getRole() + ")");
            }
            
            // Serialize the entire graph
            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(filePath.toString()))) {
                oos.writeObject(dept);
            }
            
            System.out.println("\nSerialized to file: " + Files.size(filePath) + " bytes");
            
            // Deserialize
            System.out.println("\nDESERIALIZED OBJECT GRAPH:");
            try (ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(filePath.toString()))) {
                Department restored = (Department) ois.readObject();
                System.out.println("  Department: " + restored.getName());
                for (Employee e : restored.getEmployees()) {
                    System.out.println("    - " + e.getName() + " (" + e.getRole() + ")");
                }
            }
            
            System.out.println("\nOBJECT GRAPH BEHAVIOR:");
            System.out.println("  - All referenced objects are serialized");
            System.out.println("  - Circular references are handled");
            System.out.println("  - Same object referenced twice = serialized once");
            
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates custom serialization methods.
     */
    private static void demonstrateCustomSerialization() {
        System.out.println("\n[5] CUSTOM SERIALIZATION");
        System.out.println("-".repeat(70));
        
        System.out.println("CUSTOM SERIALIZATION METHODS:");
        System.out.println();
        System.out.println("  private void writeObject(ObjectOutputStream oos)");
        System.out.println("      throws IOException {");
        System.out.println("      oos.defaultWriteObject(); // Write default fields");
        System.out.println("      oos.writeObject(customData); // Write extra data");
        System.out.println("  }");
        System.out.println();
        System.out.println("  private void readObject(ObjectInputStream ois)");
        System.out.println("      throws IOException, ClassNotFoundException {");
        System.out.println("      ois.defaultReadObject(); // Read default fields");
        System.out.println("      customData = ois.readObject(); // Read extra data");
        System.out.println("  }");
        
        System.out.println("\nUSE CASES FOR CUSTOM SERIALIZATION:");
        System.out.println("  - Encrypting sensitive data");
        System.out.println("  - Compressing large fields");
        System.out.println("  - Versioning support");
        System.out.println("  - Validating data on read");
        
        System.out.println("\nEXTERNALIZABLE INTERFACE:");
        System.out.println("  - Alternative to Serializable");
        System.out.println("  - Requires writeExternal() and readExternal()");
        System.out.println("  - Full control over serialization format");
        System.out.println("  - Better performance, more code");
        
        System.out.println("\nSECURITY BEST PRACTICES:");
        System.out.println("  - Never deserialize untrusted data");
        System.out.println("  - Use ObjectInputFilter (Java 9+)");
        System.out.println("  - Prefer JSON/XML for data exchange");
        System.out.println("  - Validate all deserialized data");
    }
    
    // ========================================================================
    // HELPER CLASSES
    // ========================================================================
    
    private static void setupDemoDirectory() {
        try {
            Files.createDirectories(Path.of(DEMO_DIR));
        } catch (IOException e) {
            System.out.println("Setup error: " + e.getMessage());
        }
    }
    
    private static void cleanupDemoDirectory() {
        try {
            Path dir = Path.of(DEMO_DIR);
            if (Files.exists(dir)) {
                try (var stream = Files.walk(dir)) {
                    stream.sorted(java.util.Comparator.reverseOrder())
                          .forEach(p -> {
                              try { Files.delete(p); } 
                              catch (IOException ignored) {}
                          });
                }
            }
        } catch (IOException e) {
            System.out.println("Cleanup error: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        demonstrate();
    }
}

// Serializable helper classes
class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private int age;
    private String email;
    
    public Person(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
    
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getEmail() { return email; }
    
    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + ", email='" + email + "'}";
    }
}

class UserAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String username;
    private transient String password; // Not serialized!
    private String email;
    
    public UserAccount(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
}

class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private String role;
    
    public Employee(String name, String role) {
        this.name = name;
        this.role = role;
    }
    
    public String getName() { return name; }
    public String getRole() { return role; }
}

class Department implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private List<Employee> employees = new ArrayList<>();
    
    public Department(String name) {
        this.name = name;
    }
    
    public void addEmployee(Employee e) {
        employees.add(e);
    }
    
    public String getName() { return name; }
    public List<Employee> getEmployees() { return employees; }
}
