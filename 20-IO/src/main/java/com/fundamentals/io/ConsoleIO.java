package com.fundamentals.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * ============================================================================
 * JAVA CONSOLE I/O - Console Input and Output Operations
 * ============================================================================
 * 
 * Java provides several ways to interact with the console (terminal).
 * 
 * STANDARD STREAMS:
 * -----------------
 * System.in  - Standard input (InputStream)
 * System.out - Standard output (PrintStream)
 * System.err - Standard error (PrintStream)
 * 
 * INPUT METHODS:
 * --------------
 * 1. Scanner           - Easy parsing of primitives and strings
 * 2. BufferedReader    - Efficient line-by-line reading
 * 3. Console           - Password reading (no echo), formatted I/O
 * 4. DataInputStream   - Legacy, not recommended
 * 
 * OUTPUT METHODS:
 * ---------------
 * 1. System.out.println()  - Print with newline
 * 2. System.out.print()    - Print without newline
 * 3. System.out.printf()   - Formatted output
 * 4. System.err            - Error output (separate stream)
 * 
 * Console CLASS:
 * --------------
 * - Available only when running from real terminal
 * - Returns null when running in IDE or redirected I/O
 * - Supports password reading (no echo)
 * 
 * ============================================================================
 */
public class ConsoleIO {
    
    public static void demonstrate() {
        System.out.println();
        System.out.println("=".repeat(70));
        System.out.println("JAVA CONSOLE I/O");
        System.out.println("=".repeat(70));
        
        demonstrateSystemStreams();
        demonstratePrintMethods();
        demonstrateFormattedOutput();
        demonstrateScannerUsage();
        demonstrateBufferedReaderUsage();
        demonstrateConsoleClass();
        demonstrateStreamRedirection();
    }
    
    /**
     * Demonstrates System.in, System.out, System.err.
     */
    private static void demonstrateSystemStreams() {
        System.out.println("\n[1] STANDARD SYSTEM STREAMS");
        System.out.println("-".repeat(70));
        
        System.out.println("SYSTEM.OUT (Standard Output):");
        System.out.println("  Type: " + System.out.getClass().getName());
        System.out.println("  This is normal output");
        
        System.out.println("\nSYSTEM.ERR (Standard Error):");
        System.out.println("  Type: " + System.err.getClass().getName());
        System.err.println("  This is error output (may appear in different color)");
        
        System.out.println("\nSYSTEM.IN (Standard Input):");
        System.out.println("  Type: " + System.in.getClass().getName());
        System.out.println("  Used with Scanner or BufferedReader");
        
        System.out.println("\nSTREAM CHARACTERISTICS:");
        System.out.println("  +------------+----------------+------------------+");
        System.out.println("  | Stream     | Type           | Purpose          |");
        System.out.println("  +------------+----------------+------------------+");
        System.out.println("  | System.in  | InputStream    | Keyboard input   |");
        System.out.println("  | System.out | PrintStream    | Normal output    |");
        System.out.println("  | System.err | PrintStream    | Error messages   |");
        System.out.println("  +------------+----------------+------------------+");
    }
    
    /**
     * Demonstrates print, println, and printf methods.
     */
    private static void demonstratePrintMethods() {
        System.out.println("\n[2] PRINT METHODS");
        System.out.println("-".repeat(70));
        
        // print() - no newline
        System.out.println("print() - No newline:");
        System.out.print("  Hello");
        System.out.print(" ");
        System.out.print("World");
        System.out.println(" (now newline)");
        
        // println() - with newline
        System.out.println("\nprintln() - With newline:");
        System.out.println("  Line 1");
        System.out.println("  Line 2");
        
        // println() overloads
        System.out.println("\nprintln() OVERLOADS:");
        System.out.println("  Boolean: " + true);
        System.out.println("  Integer: " + 42);
        System.out.println("  Double:  " + 3.14159);
        System.out.println("  Char:    " + 'X');
        System.out.println("  String:  " + "Hello");
        System.out.println("  Object:  " + new Object().getClass().getSimpleName());
        
        // Empty println
        System.out.println("\nEmpty println():");
        System.out.print("  Before");
        System.out.println(); // Just newline
        System.out.println("  After");
    }
    
    /**
     * Demonstrates printf and format methods.
     */
    private static void demonstrateFormattedOutput() {
        System.out.println("\n[3] FORMATTED OUTPUT (printf/format)");
        System.out.println("-".repeat(70));
        
        System.out.println("BASIC FORMAT SPECIFIERS:");
        System.out.printf("  %%d (integer):    %d%n", 42);
        System.out.printf("  %%f (float):      %f%n", 3.14159);
        System.out.printf("  %%s (string):     %s%n", "Hello");
        System.out.printf("  %%c (char):       %c%n", 'X');
        System.out.printf("  %%b (boolean):    %b%n", true);
        System.out.printf("  %%n (newline):    end%n");
        
        System.out.println("\nWIDTH AND PRECISION:");
        System.out.printf("  %%10d  (width 10):      |%10d|%n", 42);
        System.out.printf("  %%-10d (left align):    |%-10d|%n", 42);
        System.out.printf("  %%010d (zero pad):      |%010d|%n", 42);
        System.out.printf("  %%.2f   (2 decimals):   %.2f%n", 3.14159);
        System.out.printf("  %%10.2f (width + prec): |%10.2f|%n", 3.14159);
        
        System.out.println("\nSTRING FORMATTING:");
        System.out.printf("  %%10s  (right align):  |%10s|%n", "Hi");
        System.out.printf("  %%-10s (left align):   |%-10s|%n", "Hi");
        System.out.printf("  %%.5s  (max length):   |%.5s|%n", "Hello World");
        
        System.out.println("\nSPECIAL FORMATS:");
        System.out.printf("  %%x (hex):        %x%n", 255);
        System.out.printf("  %%X (HEX):        %X%n", 255);
        System.out.printf("  %%o (octal):      %o%n", 64);
        System.out.printf("  %%e (scientific): %e%n", 12345.678);
        System.out.printf("  %%%% (literal %%): 50%% %n");
        
        System.out.println("\nARGUMENT INDEX:");
        System.out.printf("  %2$s %1$s%n", "World", "Hello"); // Position-based
        
        System.out.println("\nUSING String.format():");
        String formatted = String.format("Name: %s, Age: %d", "John", 30);
        System.out.println("  " + formatted);
    }
    
    /**
     * Demonstrates Scanner class usage.
     */
    private static void demonstrateScannerUsage() {
        System.out.println("\n[4] SCANNER CLASS");
        System.out.println("-".repeat(70));
        
        System.out.println("CREATING SCANNER:");
        System.out.println("  Scanner scanner = new Scanner(System.in);");
        
        System.out.println("\nSCANNER METHODS:");
        System.out.println("  nextLine()    - Read entire line");
        System.out.println("  next()        - Read next token (word)");
        System.out.println("  nextInt()     - Read integer");
        System.out.println("  nextDouble()  - Read double");
        System.out.println("  nextBoolean() - Read boolean");
        System.out.println("  hasNext()     - Check if more input");
        System.out.println("  hasNextInt()  - Check if next is int");
        
        // Demonstrate with string input
        String input = "John 25 3.14 true\nHello World";
        System.out.println("\nDEMO WITH STRING INPUT: \"" + input.replace("\n", "\\n") + "\"");
        
        try (Scanner scanner = new Scanner(input)) {
            System.out.println("  next():        " + scanner.next());      // John
            System.out.println("  nextInt():     " + scanner.nextInt());   // 25
            System.out.println("  nextDouble():  " + scanner.nextDouble()); // 3.14
            System.out.println("  nextBoolean(): " + scanner.nextBoolean()); // true
            System.out.println("  nextLine():    " + scanner.nextLine());  // (empty)
            System.out.println("  nextLine():    " + scanner.nextLine());  // Hello World
        }
        
        System.out.println("\nCOMMON PATTERN (reading line by line):");
        System.out.println("  while (scanner.hasNextLine()) {");
        System.out.println("      String line = scanner.nextLine();");
        System.out.println("      // process line");
        System.out.println("  }");
        
        System.out.println("\n[!] IMPORTANT: Always close Scanner when done!");
        System.out.println("    Use try-with-resources for auto-close");
    }
    
    /**
     * Demonstrates BufferedReader for console input.
     */
    private static void demonstrateBufferedReaderUsage() {
        System.out.println("\n[5] BUFFEREDREADER FOR CONSOLE");
        System.out.println("-".repeat(70));
        
        System.out.println("CREATING BUFFEREDREADER:");
        System.out.println("  BufferedReader reader = new BufferedReader(");
        System.out.println("      new InputStreamReader(System.in, StandardCharsets.UTF_8));");
        
        System.out.println("\nREADING INPUT:");
        System.out.println("  String line = reader.readLine(); // Returns null at EOF");
        
        System.out.println("\nSCANNER vs BUFFEREDREADER:");
        System.out.println("  +------------------+------------------+------------------+");
        System.out.println("  | Feature          | Scanner          | BufferedReader   |");
        System.out.println("  +------------------+------------------+------------------+");
        System.out.println("  | Parsing          | Built-in         | Manual           |");
        System.out.println("  | Speed            | Slower           | Faster           |");
        System.out.println("  | Thread-safe      | No               | Yes              |");
        System.out.println("  | Buffer size      | Smaller          | Larger           |");
        System.out.println("  | Exceptions       | Hides in hasNext | Throws directly  |");
        System.out.println("  +------------------+------------------+------------------+");
        
        // Demo with string
        String input = "Line 1\nLine 2\nLine 3";
        System.out.println("\nDEMO WITH STRING INPUT:");
        try (BufferedReader reader = new BufferedReader(new StringReader(input))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("  Read: " + line);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates Console class usage.
     */
    private static void demonstrateConsoleClass() {
        System.out.println("\n[6] CONSOLE CLASS");
        System.out.println("-".repeat(70));
        
        Console console = System.console();
        
        if (console == null) {
            System.out.println("Console is NOT available");
            System.out.println("(Running in IDE or with redirected I/O)");
        } else {
            System.out.println("Console IS available");
        }
        
        System.out.println("\nCONSOLE METHODS:");
        System.out.println("  readLine()           - Read a line of text");
        System.out.println("  readLine(prompt)     - Read with prompt");
        System.out.println("  readPassword()       - Read password (no echo)");
        System.out.println("  readPassword(prompt) - Read password with prompt");
        System.out.println("  format(fmt, args)    - Formatted output");
        System.out.println("  printf(fmt, args)    - Same as format");
        System.out.println("  writer()             - Get PrintWriter");
        System.out.println("  reader()             - Get Reader");
        
        System.out.println("\nEXAMPLE USAGE:");
        System.out.println("  Console console = System.console();");
        System.out.println("  if (console != null) {");
        System.out.println("      String user = console.readLine(\"Username: \");");
        System.out.println("      char[] pass = console.readPassword(\"Password: \");");
        System.out.println("      // Use credentials");
        System.out.println("      Arrays.fill(pass, ' '); // Clear password from memory");
        System.out.println("  }");
        
        System.out.println("\nWHEN CONSOLE IS NULL:");
        System.out.println("  - Running in IDE");
        System.out.println("  - Input/output redirected");
        System.out.println("  - Running as background service");
        System.out.println("  Always check for null before using!");
    }
    
    /**
     * Demonstrates stream redirection.
     */
    private static void demonstrateStreamRedirection() {
        System.out.println("\n[7] STREAM REDIRECTION");
        System.out.println("-".repeat(70));
        
        System.out.println("REDIRECTING OUTPUT:");
        System.out.println("  // Save original");
        System.out.println("  PrintStream originalOut = System.out;");
        System.out.println("  ");
        System.out.println("  // Redirect to file");
        System.out.println("  System.setOut(new PrintStream(new FileOutputStream(\"out.txt\")));");
        System.out.println("  System.out.println(\"This goes to file\");");
        System.out.println("  ");
        System.out.println("  // Restore");
        System.out.println("  System.setOut(originalOut);");
        
        System.out.println("\nREDIRECTING INPUT:");
        System.out.println("  System.setIn(new FileInputStream(\"input.txt\"));");
        
        System.out.println("\nCAPTURING OUTPUT (for testing):");
        
        // Demonstrate capturing output
        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream captureStream = new PrintStream(baos);
        
        System.setOut(captureStream);
        System.out.println("Captured line 1");
        System.out.println("Captured line 2");
        System.setOut(originalOut);
        
        String captured = baos.toString();
        System.out.println("  Captured output:");
        System.out.println("  \"" + captured.replace("\n", "\\n").replace("\r", "\\r") + "\"");
        
        System.out.println("\n" + "=".repeat(70));
    }
    
    public static void main(String[] args) {
        demonstrate();
    }
}
