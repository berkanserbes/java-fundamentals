package com.fundamentals.oop;

/**
 * Initializer Blocks in Java
 * 
 * There are three types of initializer blocks in Java:
 * 
 * 1. Instance Initializer Block:
 * - Runs every time an object is created
 * - Executes BEFORE the constructor
 * - Written inside curly braces { }
 * - Used to share common code across all constructors
 * 
 * 2. Static Initializer Block:
 * - Runs ONCE when the class is loaded into JVM
 * - Defined with static keyword: static { }
 * - Used to initialize static variables
 * - Executes even BEFORE the main method
 * 
 * 3. Local Block:
 * - Block defined inside a method
 * - Used to limit variable scope
 * 
 * Execution Order:
 * 1. Static initializer blocks (once, when class is loaded)
 * 2. Instance initializer blocks (for each object creation)
 * 3. Constructor (for each object creation)
 * 
 * Use Cases:
 * - Complex initialization logic
 * - Code reuse across multiple constructors
 * - Initialization requiring exception handling
 * - Loading static resources (files, database connections, etc.)
 */
public class InitializerBlocks {

    public static void main(String[] args) {
        System.out.println("=== Java Initializer Blocks ===\n");

        // Static block runs BEFORE this point!
        System.out.println("--- main() method started ---\n");

        demonstrateInstanceBlock();
        demonstrateStaticBlock();
        demonstrateExecutionOrder();
        demonstrateMultipleBlocks();
        demonstrateInheritanceWithBlocks();
        demonstrateRealWorldExamples();
        demonstrateLocalBlocks();
    }

    /**
     * Instance Initializer Block Demonstration
     * Runs every time an object is created
     */
    private static void demonstrateInstanceBlock() {
        System.out.println("=== 1. Instance Initializer Block ===\n");

        System.out.println("Creating first ServerConnection object:");
        ServerConnectionInitBlock conn1 = new ServerConnectionInitBlock("Server-A");
        System.out.println("Connection ID: " + conn1.getConnectionId());
        System.out.println("Server: " + conn1.getServerName());

        System.out.println("\nCreating second ServerConnection object:");
        ServerConnectionInitBlock conn2 = new ServerConnectionInitBlock("Server-B");
        System.out.println("Connection ID: " + conn2.getConnectionId());
        System.out.println("Server: " + conn2.getServerName());

        System.out.println("\nThird object (with host and port):");
        ServerConnectionInitBlock conn3 = new ServerConnectionInitBlock("Server-C", "192.168.1.100", 8080);
        System.out.println("Connection ID: " + conn3.getConnectionId());
        System.out.println("Details: " + conn3.getConnectionDetails());
    }

    /**
     * Static Initializer Block Demonstration
     * Runs once when the class is loaded
     */
    private static void demonstrateStaticBlock() {
        System.out.println("\n=== 2. Static Initializer Block ===\n");

        // First access to DatabaseConfigInitBlock class - static block will run
        System.out.println("First access to DatabaseConfig class:");
        String dbUrl = DatabaseConfigInitBlock.getDatabaseUrl();
        System.out.println("Database URL: " + dbUrl);

        System.out.println("\nSecond access (static block will NOT run again):");
        String dbUrl2 = DatabaseConfigInitBlock.getDatabaseUrl();
        System.out.println("Database URL: " + dbUrl2);

        System.out.println("\nAll configurations:");
        System.out.println("  Driver: " + DatabaseConfigInitBlock.getDriver());
        System.out.println("  Pool Size: " + DatabaseConfigInitBlock.getPoolSize());
    }

    /**
     * Execution Order Demonstration
     * Static -> Instance -> Constructor
     */
    private static void demonstrateExecutionOrder() {
        System.out.println("\n=== 3. Execution Order Demonstration ===\n");

        System.out.println("Creating LifecycleDemoInitBlock object...\n");
        LifecycleDemoInitBlock obj1 = new LifecycleDemoInitBlock("Object-1");
        System.out.println("Object value: " + obj1.getName());

        System.out.println("\n--- Creating second object ---\n");
        LifecycleDemoInitBlock obj2 = new LifecycleDemoInitBlock("Object-2");
        System.out.println("Object value: " + obj2.getName());

        System.out.println("\n[Note] Static block ran only ONCE (when class was loaded)");
        System.out.println("[Note] Instance block and Constructor ran for EACH object");
    }

    /**
     * Multiple Blocks Usage
     * Blocks run in the order they are defined
     */
    private static void demonstrateMultipleBlocks() {
        System.out.println("\n=== 4. Multiple Blocks Usage ===\n");

        System.out.println("Creating MultiBlockDemoInitBlock object:\n");
        MultiBlockDemoInitBlock demo = new MultiBlockDemoInitBlock();
        System.out.println("\nResult: " + demo.getResult());
    }

    /**
     * Inheritance with Initializer Blocks
     * Parent static -> Child static -> Parent instance -> Parent constructor
     * -> Child instance -> Child constructor
     */
    private static void demonstrateInheritanceWithBlocks() {
        System.out.println("\n=== 5. Inheritance with Initializer Blocks ===\n");

        System.out.println("Creating child class (ChildInitBlock) object:\n");
        ChildInitBlock child = new ChildInitBlock("Child Object");
        System.out.println("\nObject created: " + child.getName());

        System.out.println("\n--- Execution Order ---");
        System.out.println("1. Parent static block");
        System.out.println("2. Child static block");
        System.out.println("3. Parent instance block");
        System.out.println("4. Parent constructor");
        System.out.println("5. Child instance block");
        System.out.println("6. Child constructor");
    }

    /**
     * Real World Usage Examples
     */
    private static void demonstrateRealWorldExamples() {
        System.out.println("\n=== 6. Real World Usage Examples ===\n");

        // Example 1: Application Configuration
        System.out.println("--- 6.1 Application Configuration ---");
        System.out.println("App Name: " + AppSettingsInitBlock.getAppName());
        System.out.println("Version: " + AppSettingsInitBlock.getVersion());
        System.out.println("Environment: " + AppSettingsInitBlock.getEnvironment());
        System.out.println("Debug Mode: " + AppSettingsInitBlock.isDebugMode());

        // Example 2: Unique ID Generator
        System.out.println("\n--- 6.2 Unique ID Generator ---");
        OrderInitBlock order1 = new OrderInitBlock("Laptop", 2);
        OrderInitBlock order2 = new OrderInitBlock("Mouse", 5);
        OrderInitBlock order3 = new OrderInitBlock("Keyboard", 3);

        System.out.println("Order 1: " + order1);
        System.out.println("Order 2: " + order2);
        System.out.println("Order 3: " + order3);

        // Example 3: Resource Management
        System.out.println("\n--- 6.3 Resource Management ---");
        ResourceManagerInitBlock.displayResources();
    }

    /**
     * Local Block Demonstration
     */
    private static void demonstrateLocalBlocks() {
        System.out.println("\n=== 7. Local Block ===\n");

        // Normal variable
        int globalValue = 100;
        System.out.println("Global value: " + globalValue);

        // Local block - limits variable scope
        {
            int localValue = 50;
            System.out.println("Inside local block - localValue: " + localValue);
            System.out.println("Inside local block - globalValue accessible: " + globalValue);
        }
        // localValue is NOT accessible here - out of scope

        // Another local block
        {
            // Can define a new variable with the same name
            int localValue = 75; // Does not conflict with previous localValue
            String message = "Message specific to this block";
            System.out.println("\nSecond local block - localValue: " + localValue);
            System.out.println("Second local block - message: " + message);
        }

        System.out.println("\n[Note] Local blocks are used to limit variable scope");
        System.out.println("[Note] Useful for memory management in large methods");
    }
}

// ==================== HELPER CLASSES ====================

/**
 * Instance Initializer Block example
 * Assigns unique ID for each object creation
 */
class ServerConnectionInitBlock {
    private String connectionId;
    private String serverName;
    private String host;
    private int port;
    private long createdAt;

    // Counter - shared across all instances (static)
    private static int connectionCounter = 0;

    // Instance initializer block
    // Runs for EACH object creation, BEFORE constructor
    {
        connectionCounter++;
        connectionId = "CONN-" + System.currentTimeMillis() + "-" + connectionCounter;
        createdAt = System.currentTimeMillis();
        System.out.println("  [Instance Block] Connection ID assigned: " + connectionId);
    }

    // Constructor 1
    public ServerConnectionInitBlock(String serverName) {
        this.serverName = serverName;
        this.host = "localhost";
        this.port = 80;
        System.out.println("  [Constructor] Server name set: " + serverName);
    }

    // Constructor 2 - Different parameters but same instance block runs
    public ServerConnectionInitBlock(String serverName, String host, int port) {
        this.serverName = serverName;
        this.host = host;
        this.port = port;
        System.out.println("  [Constructor] Server details set: " + serverName + " - " + host + ":" + port);
    }

    public String getConnectionId() {
        return connectionId;
    }

    public String getServerName() {
        return serverName;
    }

    public String getConnectionDetails() {
        return serverName + " (" + host + ":" + port + ") - ID: " + connectionId;
    }

    public long getCreatedAt() {
        return createdAt;
    }
}

/**
 * Static Initializer Block example
 * Loads configuration data once when class is loaded
 */
class DatabaseConfigInitBlock {
    private static String databaseUrl;
    private static String driver;
    private static int poolSize;
    private static boolean isInitialized = false;

    // Static initializer block
    // Runs ONCE when class is loaded into JVM
    static {
        System.out.println("  [Static Block] Loading database configuration...");

        // Simulate: Reading from configuration file
        try {
            Thread.sleep(100); // Simulate configuration loading
            databaseUrl = "jdbc:mysql://localhost:3306/mydb";
            driver = "com.mysql.cj.jdbc.Driver";
            poolSize = 10;
            isInitialized = true;
            System.out.println("  [Static Block] Configuration loaded successfully!");
        } catch (InterruptedException e) {
            System.out.println("  [Static Block] ERROR: Configuration could not be loaded!");
            databaseUrl = "jdbc:mysql://localhost:3306/default";
            driver = "com.mysql.cj.jdbc.Driver";
            poolSize = 5;
        }
    }

    public static String getDatabaseUrl() {
        return databaseUrl;
    }

    public static String getDriver() {
        return driver;
    }

    public static int getPoolSize() {
        return poolSize;
    }

    public static boolean isInitialized() {
        return isInitialized;
    }
}

/**
 * Lifecycle demonstration
 * Shows Static -> Instance -> Constructor order
 */
class LifecycleDemoInitBlock {
    private String name;
    private String instanceBlockMessage;

    // Static variable
    private static String staticBlockMessage;

    // 1. Static Block - Runs ONCE when class is loaded
    static {
        staticBlockMessage = "Static block executed";
        System.out.println("[1] STATIC BLOCK: Class loaded into JVM");
        System.out.println("    This runs only ONCE");
    }

    // 2. Instance Block - Runs for each object, BEFORE constructor
    {
        instanceBlockMessage = "Instance block executed";
        System.out.println("[2] INSTANCE BLOCK: Object is being initialized");
        System.out.println("    This runs for each object");
    }

    // 3. Constructor - Runs last
    public LifecycleDemoInitBlock(String name) {
        this.name = name;
        System.out.println("[3] CONSTRUCTOR: Object created - " + name);
    }

    public String getName() {
        return name;
    }

    public String getInstanceBlockMessage() {
        return instanceBlockMessage;
    }

    public static String getStaticBlockMessage() {
        return staticBlockMessage;
    }
}

/**
 * Multiple blocks example
 * Blocks run in the order they are defined
 */
class MultiBlockDemoInitBlock {
    private StringBuilder result = new StringBuilder();

    // First static block
    static {
        System.out.println("[Static Block 1] Executed");
    }

    // Second static block
    static {
        System.out.println("[Static Block 2] Executed");
    }

    // First instance block
    {
        result.append("Instance1 ");
        System.out.println("[Instance Block 1] Executed");
    }

    // Second instance block
    {
        result.append("Instance2 ");
        System.out.println("[Instance Block 2] Executed");
    }

    // Constructor
    public MultiBlockDemoInitBlock() {
        result.append("Constructor");
        System.out.println("[Constructor] Executed");
    }

    public String getResult() {
        return result.toString();
    }
}

/**
 * Inheritance - Parent class
 */
class ParentInitBlock {
    protected String name;

    // Parent static block
    static {
        System.out.println("[1] PARENT Static Block");
    }

    // Parent instance block
    {
        System.out.println("[3] PARENT Instance Block");
    }

    // Parent constructor
    public ParentInitBlock(String name) {
        this.name = name;
        System.out.println("[4] PARENT Constructor - name: " + name);
    }

    public String getName() {
        return name;
    }
}

/**
 * Inheritance - Child class
 */
class ChildInitBlock extends ParentInitBlock {
    private String childProperty;

    // Child static block
    static {
        System.out.println("[2] CHILD Static Block");
    }

    // Child instance block
    {
        childProperty = "Child specific value";
        System.out.println("[5] CHILD Instance Block");
    }

    // Child constructor
    public ChildInitBlock(String name) {
        super(name); // Parent constructor call
        System.out.println("[6] CHILD Constructor - name: " + name);
    }

    public String getChildProperty() {
        return childProperty;
    }
}

/**
 * Application Settings - Loading configuration with static block
 */
class AppSettingsInitBlock {
    private static final String appName;
    private static final String version;
    private static final String environment;
    private static final boolean debugMode;

    static {
        // Example: Reading from environment variables or config file
        String env = System.getProperty("app.env", "development");

        appName = "Java Fundamentals App";
        version = "1.0.0";
        environment = env;
        debugMode = env.equals("development");

        System.out.println("[AppSettings] Application settings loaded");
    }

    public static String getAppName() {
        return appName;
    }

    public static String getVersion() {
        return version;
    }

    public static String getEnvironment() {
        return environment;
    }

    public static boolean isDebugMode() {
        return debugMode;
    }
}

/**
 * Order class - Unique ID generation with instance block
 */
class OrderInitBlock {
    private static int orderCounter = 0;

    private final String orderId;
    private final String productName;
    private final int quantity;
    private final long orderTime;

    // Instance block - Creates unique ID for each order
    {
        orderCounter++;
        orderId = String.format("ORD-%04d", orderCounter);
        orderTime = System.currentTimeMillis();
    }

    public OrderInitBlock(String productName, int quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public long getOrderTime() {
        return orderTime;
    }

    @Override
    public String toString() {
        return orderId + " | " + productName + " x" + quantity;
    }
}

/**
 * Resource Management - Loading resources with static block
 */
class ResourceManagerInitBlock {
    private static final java.util.Map<String, String> resources = new java.util.HashMap<>();

    // Static block to load resources
    static {
        System.out.println("[ResourceManager] Loading resources...");

        // Example: Loading resources from file or database
        resources.put("welcome.message", "Welcome!");
        resources.put("error.notfound", "Resource not found");
        resources.put("error.permission", "Access denied");
        resources.put("success.saved", "Successfully saved");
        resources.put("button.submit", "Submit");
        resources.put("button.cancel", "Cancel");

        System.out.println("[ResourceManager] " + resources.size() + " resources loaded");
    }

    public static String getResource(String key) {
        return resources.getOrDefault(key, "Unknown resource: " + key);
    }

    public static void displayResources() {
        System.out.println("Loaded resources:");
        for (java.util.Map.Entry<String, String> entry : resources.entrySet()) {
            System.out.println("  " + entry.getKey() + " = " + entry.getValue());
        }
    }
}
