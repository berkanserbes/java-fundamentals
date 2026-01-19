package com.fundamentals.io;

/**
 * ============================================================================
 * JAVA I/O INTRODUCTION - COMPREHENSIVE OVERVIEW
 * ============================================================================
 * 
 * This class provides a comprehensive introduction to Java I/O (Input/Output).
 * 
 * WHAT IS I/O?
 * ------------
 * I/O stands for Input/Output, which refers to the communication between a 
 * program and the outside world. Input is data received by the program, and 
 * output is data sent from the program.
 * 
 * JAVA I/O PACKAGES:
 * ------------------
 * 1. java.io (Classic I/O)
 *    - Introduced in Java 1.0
 *    - Stream-based I/O operations
 *    - Blocking I/O operations
 *    - File class for file system operations
 * 
 * 2. java.nio (New I/O / Non-blocking I/O)
 *    - Introduced in Java 1.4, enhanced in Java 7
 *    - Buffer-oriented operations
 *    - Non-blocking I/O support
 *    - Path, Files, Channels for modern file operations
 * 
 * I/O STREAM TYPES:
 * -----------------
 * 1. BYTE STREAMS (8-bit data)
 *    - InputStream (reading bytes)
 *    - OutputStream (writing bytes)
 *    - Used for: binary files, images, audio, video
 * 
 * 2. CHARACTER STREAMS (16-bit Unicode)
 *    - Reader (reading characters)
 *    - Writer (writing characters)
 *    - Used for: text files, configuration files
 * 
 * STREAM HIERARCHY:
 * -----------------
 *                    Object
 *                      |
 *         +------------+------------+
 *         |                         |
 *    InputStream              OutputStream
 *         |                         |
 *    +----+----+               +----+----+
 *    |    |    |               |    |    |
 *  File Buffered ByteArray   File Buffered ByteArray
 *    |         |                   |         |
 *  Data    Object              Data      Object
 * 
 *                    Object
 *                      |
 *         +------------+------------+
 *         |                         |
 *      Reader                    Writer
 *         |                         |
 *    +----+----+               +----+----+
 *    |    |    |               |    |    |
 *  File Buffered InputStream File Buffered OutputStreamWriter
 *             |                          |
 *          String                     String
 * 
 * KEY CONCEPTS:
 * -------------
 * 1. STREAM: A sequence of data flowing from source to destination
 * 2. BUFFER: Temporary storage for data during transfer
 * 3. CHANNEL: Connection between I/O service and entity
 * 4. SERIALIZATION: Converting objects to byte stream
 * 
 * COMMON USE CASES:
 * -----------------
 * - Reading/Writing text files
 * - Reading/Writing binary files
 * - Network communication (sockets)
 * - Object persistence (serialization)
 * - Console input/output
 * - Working with databases (JDBC uses I/O)
 * 
 * ============================================================================
 */
public class IOIntroduction {
    
    public static void demonstrate() {
        System.out.println();
        System.out.println("=".repeat(70));
        System.out.println("JAVA I/O INTRODUCTION");
        System.out.println("=".repeat(70));
        
        // ============================================================
        // 1. Overview of I/O Packages
        // ============================================================
        System.out.println("\n[1] JAVA I/O PACKAGES OVERVIEW");
        System.out.println("-".repeat(70));
        
        System.out.println("""
            +------------------+--------------------------------------------+
            | Package          | Description                                |
            +------------------+--------------------------------------------+
            | java.io          | Classic stream-based I/O (since 1.0)       |
            | java.nio         | Buffer-based I/O, channels (since 1.4)     |
            | java.nio.file    | Modern file operations (since 7)           |
            | java.nio.channels| Channels for data transfer                 |
            | java.nio.charset | Character encoding/decoding                |
            +------------------+--------------------------------------------+
            """);
        
        // ============================================================
        // 2. Stream Types Comparison
        // ============================================================
        System.out.println("[2] BYTE STREAMS vs CHARACTER STREAMS");
        System.out.println("-".repeat(70));
        
        System.out.println("""
            +-------------------+-------------------+------------------------+
            | Feature           | Byte Streams      | Character Streams      |
            +-------------------+-------------------+------------------------+
            | Unit of data      | 8-bit bytes       | 16-bit Unicode chars   |
            | Base classes      | InputStream/      | Reader/Writer          |
            |                   | OutputStream      |                        |
            | Best for          | Binary data       | Text data              |
            | Examples          | Images, audio,    | .txt, .xml, .json,     |
            |                   | executables       | .properties            |
            | Encoding          | No encoding       | Charset support        |
            +-------------------+-------------------+------------------------+
            """);
        
        // ============================================================
        // 3. Common Stream Classes
        // ============================================================
        System.out.println("[3] COMMON STREAM CLASSES");
        System.out.println("-".repeat(70));
        
        System.out.println("BYTE STREAMS:");
        System.out.println("  Input:  FileInputStream, BufferedInputStream, DataInputStream");
        System.out.println("  Output: FileOutputStream, BufferedOutputStream, DataOutputStream");
        System.out.println();
        System.out.println("CHARACTER STREAMS:");
        System.out.println("  Input:  FileReader, BufferedReader, InputStreamReader");
        System.out.println("  Output: FileWriter, BufferedWriter, OutputStreamWriter");
        System.out.println();
        
        // ============================================================
        // 4. Standard I/O Streams
        // ============================================================
        System.out.println("[4] STANDARD I/O STREAMS (System class)");
        System.out.println("-".repeat(70));
        
        System.out.println("""
            System.in  -> Standard input stream (InputStream)
                          - Typically connected to keyboard
                          - Used with Scanner or BufferedReader
            
            System.out -> Standard output stream (PrintStream)
                          - Typically connected to console
                          - println(), print(), printf() methods
            
            System.err -> Standard error stream (PrintStream)
                          - Separate stream for error messages
                          - Also goes to console by default
            """);
        
        // ============================================================
        // 5. Resource Management
        // ============================================================
        System.out.println("[5] RESOURCE MANAGEMENT");
        System.out.println("-".repeat(70));
        
        System.out.println("""
            IMPORTANT: Always close I/O resources!
            
            Traditional approach (try-finally):
            ----------------------------------
            InputStream is = null;
            try {
                is = new FileInputStream("file.txt");
                // use the stream
            } finally {
                if (is != null) is.close();
            }
            
            Modern approach (try-with-resources) - RECOMMENDED:
            ---------------------------------------------------
            try (InputStream is = new FileInputStream("file.txt")) {
                // use the stream
            } // auto-closed here
            
            Benefits of try-with-resources:
            - Automatic resource closing
            - Cleaner, more readable code
            - Handles suppressed exceptions
            - Works with any AutoCloseable
            """);
        
        // ============================================================
        // 6. I/O Decorators Pattern
        // ============================================================
        System.out.println("[6] DECORATOR PATTERN IN I/O");
        System.out.println("-".repeat(70));
        
        System.out.println("""
            Java I/O uses the Decorator pattern extensively.
            Streams can be wrapped to add functionality:
            
            Example - Adding buffering and data reading capabilities:
            ---------------------------------------------------------
            DataInputStream dis = new DataInputStream(
                new BufferedInputStream(
                    new FileInputStream("data.bin")
                )
            );
            
            Layered structure:
            - FileInputStream: reads raw bytes from file
            - BufferedInputStream: adds buffering for efficiency
            - DataInputStream: adds methods for primitive types
            """);
        
        // ============================================================
        // 7. Topics Covered in This Module
        // ============================================================
        System.out.println("[7] TOPICS COVERED IN THIS MODULE");
        System.out.println("-".repeat(70));
        
        System.out.println("""
            1. FileClass          - Working with java.io.File
            2. PathAndFiles       - Modern java.nio.file Path and Files
            3. ByteStreams        - InputStream and OutputStream operations
            4. CharacterStreams   - Reader and Writer operations
            5. BufferedStreams    - Efficient buffered I/O
            6. DataStreams        - Reading/writing primitive types
            7. ObjectStreams      - Serialization and deserialization
            8. RandomAccessFile   - Random file access
            9. NIOBuffers         - Buffer operations in NIO
            10. NIOChannels       - Channel-based I/O
            11. ConsoleIO         - Console input/output
            12. FileUtilities     - Practical file utilities
            """);
        
        System.out.println("=".repeat(70));
    }
    
    public static void main(String[] args) {
        demonstrate();
    }
}
