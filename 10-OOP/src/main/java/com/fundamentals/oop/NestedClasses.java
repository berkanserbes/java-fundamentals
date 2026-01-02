package com.fundamentals.oop;

import java.util.ArrayList;
import java.util.List;

/**
 * Nested Classes (Ic Ice Siniflar) in Java
 * 
 * A nested class is a class defined within another class.
 * 
 * Types of Nested Classes:
 * 
 * 1. STATIC NESTED CLASS:
 * - Declared with 'static' keyword
 * - Cannot access non-static members of outer class directly
 * - Can be instantiated without outer class instance
 * 
 * 2. NON-STATIC NESTED CLASS (Inner Class):
 * - Declared without 'static' keyword
 * - Can access all members (including private) of outer class
 * - Requires outer class instance to be instantiated
 * 
 * 3. LOCAL CLASS:
 * - Declared inside a method or block
 * - Can access local variables only if they are final or effectively final
 * - Not accessible outside the method/block
 * 
 * 4. ANONYMOUS CLASS:
 * - Class without a name
 * - Used for one-time implementations
 * - Created and instantiated in a single expression
 */
public class NestedClasses {

    public static void main(String[] args) {
        System.out.println("=== Nested Classes (Ic Ice Siniflar) ===\n");

        demonstrateStaticNestedClass();
        demonstrateInnerClass();
        demonstrateLocalClass();
        demonstrateAnonymousClass();
        demonstrateShadowingAndThis();
    }

    // ==================== 1. STATIC NESTED CLASS ====================

    private static void demonstrateStaticNestedClass() {
        System.out.println("--- 1. Static Nested Class ---");
        System.out.println("Can be instantiated WITHOUT outer class instance.\n");

        // Create static nested class instance directly
        SchoolNested.ClassRoom room1 = new SchoolNested.ClassRoom("101", 30);
        SchoolNested.ClassRoom room2 = new SchoolNested.ClassRoom("102", 25);

        room1.displayInfo();
        room2.displayInfo();
        System.out.println("Total classrooms: " + SchoolNested.ClassRoom.getTotalRooms());

        System.out.println();
    }

    // ==================== 2. INNER CLASS (Non-Static Nested Class)
    // ====================

    private static void demonstrateInnerClass() {
        System.out.println("--- 2. Inner Class (Non-Static Nested Class) ---");
        System.out.println("Requires outer class instance. Can access private members.\n");

        // First, create outer class instance
        LibraryNested library = new LibraryNested("City Library");

        // Create inner class instance using outer class instance
        LibraryNested.BookItem book1 = library.new BookItem("1984", "George Orwell");
        LibraryNested.BookItem book2 = library.new BookItem("Clean Code", "Robert Martin");

        library.addBook(book1);
        library.addBook(book2);
        library.displayBooks();

        // Inner class accessing outer class private field
        book1.showLibraryName();

        System.out.println();
    }

    // ==================== 3. LOCAL CLASS ====================

    private static void demonstrateLocalClass() {
        System.out.println("--- 3. Local Class ---");
        System.out.println("Defined inside a method. Can access final/effectively final variables.\n");

        final String currency = "TL";
        double taxRate = 0.18; // Effectively final (not modified)

        // Local class defined inside method
        class PriceCalculator {
            double calculateWithTax(double price) {
                return price * (1 + taxRate);
            }

            String formatPrice(double price) {
                return String.format("%.2f %s", price, currency);
            }
        }

        PriceCalculator calculator = new PriceCalculator();
        double basePrice = 100.0;
        double finalPrice = calculator.calculateWithTax(basePrice);

        System.out.println("Base price: " + calculator.formatPrice(basePrice));
        System.out.println("With tax: " + calculator.formatPrice(finalPrice));

        System.out.println();
    }

    // ==================== 4. ANONYMOUS CLASS ====================

    private static void demonstrateAnonymousClass() {
        System.out.println("--- 4. Anonymous Class ---");
        System.out.println("Class without name. Used for one-time implementations.\n");

        // Anonymous class implementing interface
        GreetingNested english = new GreetingNested() {
            @Override
            public void sayHello(String name) {
                System.out.println("Hello, " + name + "!");
            }
        };

        GreetingNested turkish = new GreetingNested() {
            @Override
            public void sayHello(String name) {
                System.out.println("Merhaba, " + name + "!");
            }
        };

        english.sayHello("John");
        turkish.sayHello("Ali");

        // Anonymous class extending abstract class
        System.out.println("\nAnonymous class with abstract class:");
        TextFormatterNested uppercase = new TextFormatterNested() {
            @Override
            String format(String text) {
                return text.toUpperCase();
            }
        };

        TextFormatterNested decorated = new TextFormatterNested() {
            @Override
            String format(String text) {
                return "*** " + text + " ***";
            }
        };

        System.out.println("Original: hello world");
        System.out.println("Uppercase: " + uppercase.format("hello world"));
        System.out.println("Decorated: " + decorated.format("hello world"));

        System.out.println();
    }

    // ==================== 5. SHADOWING & Outer.this ====================

    private static void demonstrateShadowingAndThis() {
        System.out.println("--- 5. Shadowing & Outer.this Reference ---");
        System.out.println("When inner class has same variable name as outer class.\n");

        ShadowingDemo outer = new ShadowingDemo();
        ShadowingDemo.InnerDemo inner = outer.new InnerDemo();
        inner.printAllValues();

        System.out.println("\nExercise completed!");
    }
}

// ==================== SUPPORTING CLASSES ====================

/**
 * Example for Static Nested Class
 */
class SchoolNested {
    private String schoolName;

    public SchoolNested(String schoolName) {
        this.schoolName = schoolName;
    }

    // Static nested class
    public static class ClassRoom {
        private static int totalRooms = 0;
        private String roomNumber;
        private int capacity;

        public ClassRoom(String roomNumber, int capacity) {
            this.roomNumber = roomNumber;
            this.capacity = capacity;
            totalRooms++;
        }

        public void displayInfo() {
            System.out.println("Room " + roomNumber + " (Capacity: " + capacity + ")");
        }

        public static int getTotalRooms() {
            return totalRooms;
        }

        // Note: Cannot access schoolName directly (non-static from static context)
    }
}

/**
 * Example for Inner Class
 */
class LibraryNested {
    private String libraryName;
    private List<BookItem> books;

    public LibraryNested(String libraryName) {
        this.libraryName = libraryName;
        this.books = new ArrayList<>();
    }

    public void addBook(BookItem book) {
        books.add(book);
    }

    public void displayBooks() {
        System.out.println(libraryName + " - Books:");
        for (BookItem book : books) {
            System.out.println("  " + book.getDetails());
        }
    }

    // Inner class (non-static)
    public class BookItem {
        private String title;
        private String author;

        public BookItem(String title, String author) {
            this.title = title;
            this.author = author;
        }

        public String getDetails() {
            return title + " by " + author;
        }

        // Can access outer class private members
        public void showLibraryName() {
            System.out.println("This book belongs to: " + libraryName);
        }
    }
}

/**
 * Interface for Anonymous Class example
 */
interface GreetingNested {
    void sayHello(String name);
}

/**
 * Abstract class for Anonymous Class example
 */
abstract class TextFormatterNested {
    abstract String format(String text);
}

/**
 * Example for Shadowing and Outer.this reference
 * When inner class declares a variable with the same name as outer class,
 * use Outer.this.variable to access outer class variable.
 */
class ShadowingDemo {
    private String message = "I am Outer's message";
    private int value = 100;

    public class InnerDemo {
        private String message = "I am Inner's message"; // Shadows outer's message

        public void printAllValues() {
            String message = "I am local message"; // Shadows inner's message

            System.out.println("1) Local variable:");
            System.out.println("   message = " + message);

            System.out.println("\n2) Inner class variable (this.message):");
            System.out.println("   this.message = " + this.message);

            System.out.println("\n3) Outer class variable (ShadowingDemo.this.message):");
            System.out.println("   ShadowingDemo.this.message = " + ShadowingDemo.this.message);

            System.out.println("\n4) Outer class variable (no shadowing):");
            System.out.println("   ShadowingDemo.this.value = " + ShadowingDemo.this.value);
        }
    }
}
