package com.fundamentals.oop;

/**
 * Static and Dynamic Binding in Java
 * 
 * BINDING: Association of a method call to a method body.
 * 
 * Types of Binding:
 * 
 * 1. STATIC BINDING (Early Binding / Compile-time Binding):
 * - Resolved at COMPILE TIME
 * - Compiler decides which method to call
 * - Used for: static, final, private methods
 * - Faster execution (no runtime resolution)
 * - Method Overloading uses static binding
 * 
 * 2. DYNAMIC BINDING (Late Binding / Runtime Binding):
 * - Resolved at RUNTIME
 * - JVM decides which method to call based on actual object type
 * - Used for: instance methods (non-static, non-final, non-private)
 * - Enables runtime polymorphism
 * - Method Overriding uses dynamic binding
 * 
 * Key Differences:
 * ┌─────────────────────────┬──────────────────────────────────────────┐
 * │ Static Binding │ Dynamic Binding │
 * ├─────────────────────────┼──────────────────────────────────────────┤
 * │ Compile-time resolution │ Runtime resolution │
 * │ Reference type matters │ Actual object type matters │
 * │ Faster performance │ Slightly slower (virtual method table) │
 * │ private/static/final │ Instance methods (overridden) │
 * │ Method Overloading │ Method Overriding │
 * └─────────────────────────┴──────────────────────────────────────────┘
 */
public class Binding {

    public static void main(String[] args) {
        System.out.println("=== Static and Dynamic Binding in Java ===\n");

        demonstrateStaticBinding();
        demonstrateDynamicBinding();
        demonstrateStaticVsDynamicBinding();
        demonstrateStaticMethodBinding();
        demonstrateFinalMethodBinding();
        demonstratePrivateMethodBinding();
        demonstrateFieldHiding();
        demonstrateMethodOverloadingBinding();
        demonstrateComplexBindingScenario();
        demonstrateRealWorldExample();
    }

    // ========================================================================
    // STATIC BINDING EXAMPLES
    // ========================================================================

    /**
     * Static Binding with static methods
     * Static methods are bound at compile-time based on REFERENCE TYPE
     */
    private static void demonstrateStaticBinding() {
        System.out.println("--- 1. Static Binding (Compile-time) ---");
        System.out.println("Static methods are resolved based on REFERENCE TYPE\n");

        // Static methods - bound at compile time
        PrinterBinding.configure(); // Calls PrinterBinding.configure()

        // Even with parent reference pointing to child object
        PrinterBinding printer = new ColorPrinterBinding();
        printer.configure(); // Still calls PrinterBinding.configure() (STATIC BINDING!)

        System.out.println("\nExplanation:");
        System.out.println("- printer.configure() calls PrinterBinding.configure()");
        System.out.println("- Because static methods use REFERENCE TYPE (PrinterBinding)");
        System.out.println("- Not the ACTUAL OBJECT TYPE (ColorPrinterBinding)");
        System.out.println();
    }

    /**
     * Dynamic Binding with instance methods
     * Instance methods are bound at runtime based on ACTUAL OBJECT TYPE
     */
    private static void demonstrateDynamicBinding() {
        System.out.println("--- 2. Dynamic Binding (Runtime) ---");
        System.out.println("Instance methods are resolved based on ACTUAL OBJECT TYPE\n");

        // Dynamic binding with instance methods
        InstrumentBinding instrument1 = new GuitarBinding();
        InstrumentBinding instrument2 = new PianoBinding();
        InstrumentBinding instrument3 = new DrumsBinding();

        // All reference types are InstrumentBinding, but actual method depends on
        // object
        System.out.println("instrument1 (Guitar):");
        instrument1.produce(); // Calls GuitarBinding.produce()

        System.out.println("\ninstrument2 (Piano):");
        instrument2.produce(); // Calls PianoBinding.produce()

        System.out.println("\ninstrument3 (Drums):");
        instrument3.produce(); // Calls DrumsBinding.produce()

        System.out.println("\nExplanation:");
        System.out.println("- All variables have type InstrumentBinding");
        System.out.println("- But the ACTUAL method called depends on the object created");
        System.out.println("- JVM resolves this at RUNTIME using virtual method table");
        System.out.println();
    }

    /**
     * Direct comparison: Static vs Dynamic Binding
     */
    private static void demonstrateStaticVsDynamicBinding() {
        System.out.println("--- 3. Static vs Dynamic Binding Comparison ---\n");

        ComputerBinding ref = new LaptopBinding();

        System.out.println("Same reference (ComputerBinding ref = new LaptopBinding()):");
        System.out.println();

        // Static method call (STATIC BINDING)
        ref.showSpecification(); // ComputerBinding.showSpecification()

        // Instance method call (DYNAMIC BINDING)
        ref.operate(); // LaptopBinding.operate()

        System.out.println("\nKey Insight:");
        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│ ref.showSpecification() -> ComputerBinding      │");
        System.out.println("│   (Static method: uses reference type)          │");
        System.out.println("│ ref.operate() -> LaptopBinding                  │");
        System.out.println("│   (Instance method: uses actual object type)    │");
        System.out.println("└─────────────────────────────────────────────────┘");
        System.out.println();
    }

    /**
     * Static Method Binding - Always at compile time
     */
    private static void demonstrateStaticMethodBinding() {
        System.out.println("--- 4. Static Method Binding ---");
        System.out.println("Static methods CANNOT be truly overridden!\n");

        CounterBinding counter1 = new CounterBinding();
        CounterBinding counter2 = new AdvancedCounterBinding();
        AdvancedCounterBinding counter3 = new AdvancedCounterBinding();

        System.out.println("CounterBinding counter1 = new CounterBinding()");
        counter1.showType(); // CounterBinding

        System.out.println("\nCounterBinding counter2 = new AdvancedCounterBinding()");
        counter2.showType(); // CounterBinding (NOT AdvancedCounterBinding!)

        System.out.println("\nAdvancedCounterBinding counter3 = new AdvancedCounterBinding()");
        counter3.showType(); // AdvancedCounterBinding

        System.out.println("\nNote: This is called METHOD HIDING, not overriding!");
        System.out.println("Static methods in child class HIDE parent's static methods.");
        System.out.println();
    }

    /**
     * Final Method Binding - Static binding due to 'final' keyword
     */
    private static void demonstrateFinalMethodBinding() {
        System.out.println("--- 5. Final Method Binding ---");
        System.out.println("Final methods use STATIC BINDING (cannot be overridden)\n");

        DeviceBinding device = new TabletBinding();

        device.turnOn(); // TabletBinding.turnOn() - Dynamic binding
        device.shutdown(); // DeviceBinding.shutdown() - Static binding (final)

        System.out.println("\nWhy final methods use static binding?");
        System.out.println("- Compiler knows final method cannot be overridden");
        System.out.println("- No need for runtime resolution");
        System.out.println("- Results in faster execution");
        System.out.println();
    }

    /**
     * Private Method Binding - Static binding due to 'private' keyword
     */
    private static void demonstratePrivateMethodBinding() {
        System.out.println("--- 6. Private Method Binding ---");
        System.out.println("Private methods use STATIC BINDING\n");

        MessengerBinding.testPrivateBinding();

        System.out.println("\nWhy private methods use static binding?");
        System.out.println("- Private methods are NOT inherited");
        System.out.println("- Child class cannot override them");
        System.out.println("- Compiler resolves at compile time");
        System.out.println();
    }

    /**
     * Field Hiding - Fields always use static binding
     */
    private static void demonstrateFieldHiding() {
        System.out.println("--- 7. Field Hiding (Fields Always Use Static Binding) ---\n");

        NotifierBinding notifier = new EmailNotifierBinding();

        System.out.println("Reference type: NotifierBinding");
        System.out.println("Actual type: EmailNotifierBinding\n");

        // Field access uses static binding (reference type)
        System.out.println("notifier.notificationType: " + notifier.notificationType);

        // Method call uses dynamic binding (actual object type)
        notifier.sendNotification();

        System.out.println("\nImportant:");
        System.out.println("- Fields are NOT overridden, they are HIDDEN");
        System.out.println("- Field access depends on REFERENCE TYPE");
        System.out.println("- Method access depends on ACTUAL OBJECT TYPE");
        System.out.println();
    }

    /**
     * Method Overloading uses Static Binding
     */
    private static void demonstrateMethodOverloadingBinding() {
        System.out.println("--- 8. Method Overloading (Static Binding) ---");
        System.out.println("Overloaded methods are resolved at COMPILE TIME\n");

        ConverterBinding converter = new ConverterBinding();

        // Compiler decides which method based on parameter types
        converter.convert(100); // int version
        converter.convert(3.14); // double version
        converter.convert("Hello"); // String version
        converter.convert(10, 20); // two int version

        System.out.println("\nCompiler Resolution:");
        System.out.println("- convert(100) -> convert(int)");
        System.out.println("- convert(3.14) -> convert(double)");
        System.out.println("- convert(\"Hello\") -> convert(String)");
        System.out.println("- convert(10, 20) -> convert(int, int)");
        System.out.println();
    }

    /**
     * Complex scenario with inheritance and binding
     */
    private static void demonstrateComplexBindingScenario() {
        System.out.println("--- 9. Complex Binding Scenario ---\n");

        // Array of parent type holding different child objects
        TransportBinding[] fleet = {
                new BicycleBinding(),
                new MotorcycleBinding(),
                new TruckBinding()
        };

        System.out.println("Iterating through TransportBinding array:");
        System.out.println("(Reference type is TransportBinding for all)\n");

        for (TransportBinding transport : fleet) {
            System.out.print("Type: " + transport.getClass().getSimpleName() + " -> ");
            transport.move(); // Dynamic binding determines actual method
        }

        System.out.println("\nDynamic Binding in Action:");
        System.out.println("- Same reference type (TransportBinding)");
        System.out.println("- Different actual objects (Bicycle, Motorcycle, Truck)");
        System.out.println("- JVM calls correct move() method at runtime");
        System.out.println();
    }

    /**
     * Real-world example: Payment Processing System
     */
    private static void demonstrateRealWorldExample() {
        System.out.println("--- 10. Real-World Example: Payment Processing ---\n");

        // Payment processor using dynamic binding
        PaymentProcessorBinding processor = new PaymentProcessorBinding();

        PaymentMethodBinding creditCard = new CreditCardPaymentBinding("1234-5678-9012-3456");
        PaymentMethodBinding bankTransfer = new BankTransferPaymentBinding("TR001234567890");
        PaymentMethodBinding cryptoPayment = new CryptoPaymentBinding("0x1234...abcd");

        System.out.println("Processing different payment types:\n");

        processor.processPayment(creditCard, 150.00);
        System.out.println();

        processor.processPayment(bankTransfer, 500.00);
        System.out.println();

        processor.processPayment(cryptoPayment, 0.05);

        System.out.println("\n" + "=".repeat(50));
        System.out.println("SUMMARY OF BINDING RULES:");
        System.out.println("=".repeat(50));
        System.out.println("1. Static methods     -> STATIC BINDING");
        System.out.println("2. Private methods    -> STATIC BINDING");
        System.out.println("3. Final methods      -> STATIC BINDING");
        System.out.println("4. Instance fields    -> STATIC BINDING");
        System.out.println("5. Method Overloading -> STATIC BINDING");
        System.out.println("6. Instance methods   -> DYNAMIC BINDING");
        System.out.println("7. Method Overriding  -> DYNAMIC BINDING");
        System.out.println("=".repeat(50));
        System.out.println("\nBinding demonstration completed!");
    }
}

// ============================================================================
// SUPPORTING CLASSES FOR STATIC BINDING DEMONSTRATION
// ============================================================================

/**
 * PrinterBinding - Base class with static method
 */
class PrinterBinding {
    // Static method - uses STATIC BINDING
    static void configure() {
        System.out.println("PrinterBinding: Default configuration");
    }
}

/**
 * ColorPrinterBinding - Child class with same static method (hiding)
 */
class ColorPrinterBinding extends PrinterBinding {
    // This HIDES parent's static method, does NOT override it
    static void configure() {
        System.out.println("ColorPrinterBinding: Color configuration");
    }
}

// ============================================================================
// SUPPORTING CLASSES FOR DYNAMIC BINDING DEMONSTRATION
// ============================================================================

/**
 * InstrumentBinding - Base class for musical instruments
 */
class InstrumentBinding {
    // Instance method - uses DYNAMIC BINDING
    void produce() {
        System.out.println("InstrumentBinding: Generic sound");
    }
}

/**
 * GuitarBinding - Specific instrument implementation
 */
class GuitarBinding extends InstrumentBinding {
    @Override
    void produce() {
        System.out.println("GuitarBinding: 🎸 Strumming guitar strings...");
    }
}

/**
 * PianoBinding - Specific instrument implementation
 */
class PianoBinding extends InstrumentBinding {
    @Override
    void produce() {
        System.out.println("PianoBinding: 🎹 Playing piano keys...");
    }
}

/**
 * DrumsBinding - Specific instrument implementation
 */
class DrumsBinding extends InstrumentBinding {
    @Override
    void produce() {
        System.out.println("DrumsBinding: 🥁 Beating the drums...");
    }
}

// ============================================================================
// SUPPORTING CLASSES FOR STATIC VS DYNAMIC COMPARISON
// ============================================================================

/**
 * ComputerBinding - Base class with both static and instance methods
 */
class ComputerBinding {
    // Static method - STATIC BINDING
    static void showSpecification() {
        System.out.println("ComputerBinding.showSpecification() [STATIC]");
    }

    // Instance method - DYNAMIC BINDING
    void operate() {
        System.out.println("ComputerBinding.operate() [INSTANCE]");
    }
}

/**
 * LaptopBinding - Child class demonstrating binding differences
 */
class LaptopBinding extends ComputerBinding {
    // Hides parent's static method
    static void showSpecification() {
        System.out.println("LaptopBinding.showSpecification() [STATIC]");
    }

    // Overrides parent's instance method
    @Override
    void operate() {
        System.out.println("LaptopBinding.operate() [INSTANCE - OVERRIDDEN]");
    }
}

// ============================================================================
// SUPPORTING CLASSES FOR STATIC METHOD HIDING
// ============================================================================

/**
 * CounterBinding - Base counter class
 */
class CounterBinding {
    private int count = 0;

    static void showType() {
        System.out.println("Type: CounterBinding");
    }

    void increment() {
        count++;
    }

    int getCount() {
        return count;
    }
}

/**
 * AdvancedCounterBinding - Child counter with hidden static method
 */
class AdvancedCounterBinding extends CounterBinding {
    // This HIDES parent's static method
    static void showType() {
        System.out.println("Type: AdvancedCounterBinding");
    }
}

// ============================================================================
// SUPPORTING CLASSES FOR FINAL METHOD BINDING
// ============================================================================

/**
 * DeviceBinding - Base class with final method
 */
class DeviceBinding {
    void turnOn() {
        System.out.println("DeviceBinding: Turning on...");
    }

    // Final method - cannot be overridden, uses STATIC BINDING
    final void shutdown() {
        System.out.println("DeviceBinding: Safe shutdown initiated (FINAL - cannot override)");
    }
}

/**
 * TabletBinding - Child class that overrides non-final method
 */
class TabletBinding extends DeviceBinding {
    @Override
    void turnOn() {
        System.out.println("TabletBinding: Tablet powering on with touch screen...");
    }

    // Cannot override shutdown() because it's final
    // void shutdown() { } // COMPILE ERROR!
}

// ============================================================================
// SUPPORTING CLASSES FOR PRIVATE METHOD BINDING
// ============================================================================

/**
 * MessengerBinding - Demonstrates private method binding
 */
class MessengerBinding {
    // Private method - uses STATIC BINDING
    private void prepareMessage() {
        System.out.println("MessengerBinding: Preparing generic message...");
    }

    void sendMessage() {
        prepareMessage(); // Calls MessengerBinding.prepareMessage() always
        System.out.println("MessengerBinding: Message sent!");
    }

    static void testPrivateBinding() {
        MessengerBinding messenger = new SmsMessengerBinding();
        messenger.sendMessage();
        System.out.println();

        System.out.println("Notice: prepareMessage() in MessengerBinding is called,");
        System.out.println("not SmsMessengerBinding's version (private = not inherited)");
    }
}

/**
 * SmsMessengerBinding - Child class with its own private method
 */
class SmsMessengerBinding extends MessengerBinding {
    // This is a DIFFERENT method, not an override
    private void prepareMessage() {
        System.out.println("SmsMessengerBinding: Preparing SMS format...");
    }
}

// ============================================================================
// SUPPORTING CLASSES FOR FIELD HIDING
// ============================================================================

/**
 * NotifierBinding - Base class demonstrating field hiding
 */
class NotifierBinding {
    String notificationType = "GENERIC";

    void sendNotification() {
        System.out.println("Sending " + notificationType + " notification");
    }
}

/**
 * EmailNotifierBinding - Child class with hidden field
 */
class EmailNotifierBinding extends NotifierBinding {
    // This HIDES parent's field
    String notificationType = "EMAIL";

    @Override
    void sendNotification() {
        System.out.println("Sending " + notificationType + " notification via SMTP");
    }
}

// ============================================================================
// SUPPORTING CLASSES FOR METHOD OVERLOADING
// ============================================================================

/**
 * ConverterBinding - Demonstrates method overloading (static binding)
 */
class ConverterBinding {
    void convert(int value) {
        System.out.println("convert(int): " + value + " -> " + (value * 2));
    }

    void convert(double value) {
        System.out.println("convert(double): " + value + " -> " + (value * 2.5));
    }

    void convert(String value) {
        System.out.println("convert(String): " + value + " -> " + value.toUpperCase());
    }

    void convert(int a, int b) {
        System.out.println("convert(int, int): " + a + ", " + b + " -> " + (a + b));
    }
}

// ============================================================================
// SUPPORTING CLASSES FOR COMPLEX SCENARIO
// ============================================================================

/**
 * TransportBinding - Base transport class
 */
abstract class TransportBinding {
    abstract void move();
}

/**
 * BicycleBinding - Specific transport implementation
 */
class BicycleBinding extends TransportBinding {
    @Override
    void move() {
        System.out.println("🚲 Pedaling the bicycle");
    }
}

/**
 * MotorcycleBinding - Specific transport implementation
 */
class MotorcycleBinding extends TransportBinding {
    @Override
    void move() {
        System.out.println("🏍️ Revving the motorcycle engine");
    }
}

/**
 * TruckBinding - Specific transport implementation
 */
class TruckBinding extends TransportBinding {
    @Override
    void move() {
        System.out.println("🚛 Driving the truck on highway");
    }
}

// ============================================================================
// REAL-WORLD EXAMPLE: PAYMENT PROCESSING SYSTEM
// ============================================================================

/**
 * PaymentMethodBinding - Abstract payment method
 */
abstract class PaymentMethodBinding {
    protected String identifier;

    PaymentMethodBinding(String identifier) {
        this.identifier = identifier;
    }

    abstract void authorize();

    abstract void executePayment(double amount);

    abstract String getPaymentType();
}

/**
 * CreditCardPaymentBinding - Credit card payment implementation
 */
class CreditCardPaymentBinding extends PaymentMethodBinding {

    CreditCardPaymentBinding(String cardNumber) {
        super(cardNumber);
    }

    @Override
    void authorize() {
        System.out.println("💳 Authorizing credit card: " + maskCard(identifier));
    }

    @Override
    void executePayment(double amount) {
        System.out.println("💳 Charging $" + amount + " to credit card");
    }

    @Override
    String getPaymentType() {
        return "Credit Card";
    }

    private String maskCard(String card) {
        return "****-****-****-" + card.substring(card.length() - 4);
    }
}

/**
 * BankTransferPaymentBinding - Bank transfer payment implementation
 */
class BankTransferPaymentBinding extends PaymentMethodBinding {

    BankTransferPaymentBinding(String iban) {
        super(iban);
    }

    @Override
    void authorize() {
        System.out.println("🏦 Verifying bank account: " + identifier);
    }

    @Override
    void executePayment(double amount) {
        System.out.println("🏦 Transferring $" + amount + " via bank transfer");
    }

    @Override
    String getPaymentType() {
        return "Bank Transfer";
    }
}

/**
 * CryptoPaymentBinding - Cryptocurrency payment implementation
 */
class CryptoPaymentBinding extends PaymentMethodBinding {

    CryptoPaymentBinding(String walletAddress) {
        super(walletAddress);
    }

    @Override
    void authorize() {
        System.out.println("₿ Connecting to crypto wallet: " + identifier);
    }

    @Override
    void executePayment(double amount) {
        System.out.println("₿ Sending " + amount + " BTC to recipient");
    }

    @Override
    String getPaymentType() {
        return "Cryptocurrency";
    }
}

/**
 * PaymentProcessorBinding - Processes payments using dynamic binding
 */
class PaymentProcessorBinding {

    void processPayment(PaymentMethodBinding paymentMethod, double amount) {
        System.out.println("Processing " + paymentMethod.getPaymentType() + " payment...");
        paymentMethod.authorize(); // Dynamic binding
        paymentMethod.executePayment(amount); // Dynamic binding
        System.out.println("✓ Payment of $" + amount + " completed!");
    }
}
