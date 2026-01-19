package com.fundamentals.io;

/**
 * ============================================================================
 * JAVA I/O DEMO - Main Entry Point
 * ============================================================================
 * 
 * This class orchestrates all I/O demonstrations in the 20-IO module.
 * Run this class to see all Java I/O concepts explained with examples.
 * 
 * TOPICS COVERED:
 * ---------------
 * 1.  IOIntroduction    - Overview of Java I/O
 * 2.  FileClass         - java.io.File operations
 * 3.  PathAndFiles      - NIO.2 Path and Files
 * 4.  ByteStreams       - InputStream/OutputStream
 * 5.  CharacterStreams  - Reader/Writer
 * 6.  BufferedStreams   - Buffered I/O for efficiency
 * 7.  DataStreams       - Primitive data types
 * 8.  ObjectStreams     - Serialization
 * 9.  RandomAccess      - RandomAccessFile
 * 10. NIOBuffers        - NIO Buffer operations
 * 11. NIOChannels       - Channel-based I/O
 * 12. ConsoleIO         - Console input/output
 * 13. FileUtilities     - Practical file utilities
 * 
 * ============================================================================
 */
public class IODemo {
    
    public static void main(String[] args) {
        System.out.println();
        System.out.println("#".repeat(70));
        System.out.println("#" + " ".repeat(68) + "#");
        System.out.println("#" + centerText("JAVA I/O - INPUT/OUTPUT FUNDAMENTALS", 68) + "#");
        System.out.println("#" + centerText("Comprehensive Guide to File and Stream Operations", 68) + "#");
        System.out.println("#" + " ".repeat(68) + "#");
        System.out.println("#".repeat(70));
        
        // Display table of contents
        printTableOfContents();
        
        // Run all demonstrations
        System.out.println("\n" + "=".repeat(70));
        System.out.println("Starting demonstrations...");
        System.out.println("=".repeat(70));
        
        // 1. Introduction
        runDemo("I/O Introduction", IOIntroduction::demonstrate);
        
        // 2. File Class
        runDemo("File Class (java.io.File)", FileClass::demonstrate);
        
        // 3. Path and Files
        runDemo("Path and Files (NIO.2)", PathAndFiles::demonstrate);
        
        // 4. Byte Streams
        runDemo("Byte Streams", ByteStreams::demonstrate);
        
        // 5. Character Streams
        runDemo("Character Streams", CharacterStreams::demonstrate);
        
        // 6. Buffered Streams
        runDemo("Buffered Streams", BufferedStreams::demonstrate);
        
        // 7. Data Streams
        runDemo("Data Streams", DataStreams::demonstrate);
        
        // 8. Object Streams
        runDemo("Object Streams (Serialization)", ObjectStreams::demonstrate);
        
        // 9. Random Access
        runDemo("Random Access File", RandomAccess::demonstrate);
        
        // 10. NIO Buffers
        runDemo("NIO Buffers", NIOBuffers::demonstrate);
        
        // 11. NIO Channels
        runDemo("NIO Channels", NIOChannels::demonstrate);
        
        // 12. Console I/O
        runDemo("Console I/O", ConsoleIO::demonstrate);
        
        // 13. File Utilities
        runDemo("File Utilities", FileUtilities::demonstrate);
        
        // Summary
        printSummary();
    }
    
    private static void printTableOfContents() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("TABLE OF CONTENTS");
        System.out.println("=".repeat(70));
        System.out.println();
        System.out.println("  1.  IOIntroduction    - Overview of Java I/O concepts");
        System.out.println("  2.  FileClass         - java.io.File for file system operations");
        System.out.println("  3.  PathAndFiles      - Modern NIO.2 file operations");
        System.out.println("  4.  ByteStreams       - Reading/writing binary data");
        System.out.println("  5.  CharacterStreams  - Reading/writing text data");
        System.out.println("  6.  BufferedStreams   - Efficient buffered I/O");
        System.out.println("  7.  DataStreams       - Primitive data type handling");
        System.out.println("  8.  ObjectStreams     - Object serialization");
        System.out.println("  9.  RandomAccess      - Random file access");
        System.out.println("  10. NIOBuffers        - NIO buffer operations");
        System.out.println("  11. NIOChannels       - Channel-based I/O");
        System.out.println("  12. ConsoleIO         - Console input/output");
        System.out.println("  13. FileUtilities     - Practical file utilities");
    }
    
    private static void runDemo(String name, Runnable demo) {
        try {
            demo.run();
        } catch (Exception e) {
            System.out.println("\n[ERROR] " + name + " failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void printSummary() {
        System.out.println("\n" + "#".repeat(70));
        System.out.println("#" + " ".repeat(68) + "#");
        System.out.println("#" + centerText("JAVA I/O SUMMARY", 68) + "#");
        System.out.println("#" + " ".repeat(68) + "#");
        System.out.println("#".repeat(70));
        
        System.out.println("""
            
            KEY TAKEAWAYS:
            ==============
            
            1. CLASSIC I/O (java.io)
               - File class for file system operations
               - Byte streams (InputStream/OutputStream) for binary data
               - Character streams (Reader/Writer) for text data
               - Always use buffered streams for efficiency
            
            2. NIO (java.nio)
               - Path and Files for modern file operations
               - Buffers for efficient data handling
               - Channels for high-performance I/O
               - Memory-mapped files for large data
            
            3. BEST PRACTICES
               - Always close streams (use try-with-resources)
               - Always specify charset for character streams
               - Use buffered streams for file I/O
               - Use Files utility methods when possible
               - Handle IOException properly
            
            4. CHOOSING THE RIGHT API
               - Simple file operations: Files class
               - Text files: BufferedReader/BufferedWriter
               - Binary files: BufferedInputStream/BufferedOutputStream
               - Random access: RandomAccessFile
               - High performance: NIO Channels
            
            """);
        
        System.out.println("=".repeat(70));
        System.out.println("End of Java I/O Module");
        System.out.println("=".repeat(70));
    }
    
    private static String centerText(String text, int width) {
        if (text.length() >= width) {
            return text;
        }
        int padding = (width - text.length()) / 2;
        return " ".repeat(padding) + text + " ".repeat(width - text.length() - padding);
    }
}
