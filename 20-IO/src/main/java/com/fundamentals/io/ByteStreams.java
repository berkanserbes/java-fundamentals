package com.fundamentals.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * ============================================================================
 * JAVA BYTE STREAMS - InputStream and OutputStream
 * ============================================================================
 * 
 * Byte streams handle I/O of raw binary data. They are the most fundamental
 * type of streams in Java, reading and writing data one byte (8 bits) at a time.
 * 
 * BYTE STREAM HIERARCHY:
 * ----------------------
 * 
 * InputStream (abstract base class)
 * |-- FileInputStream      - Reads bytes from a file
 * |-- ByteArrayInputStream - Reads bytes from byte array in memory
 * |-- FilterInputStream    - Base for input stream decorators
 * |   |-- BufferedInputStream  - Adds buffering
 * |   |-- DataInputStream      - Reads primitive data types
 * |   |-- PushbackInputStream  - Allows "unread" of bytes
 * |-- ObjectInputStream    - Deserializes objects
 * |-- PipedInputStream     - Reads from a pipe (used with threads)
 * |-- SequenceInputStream  - Concatenates multiple streams
 * 
 * OutputStream (abstract base class)
 * |-- FileOutputStream      - Writes bytes to a file
 * |-- ByteArrayOutputStream - Writes bytes to byte array in memory
 * |-- FilterOutputStream    - Base for output stream decorators
 * |   |-- BufferedOutputStream - Adds buffering
 * |   |-- DataOutputStream     - Writes primitive data types
 * |   |-- PrintStream          - Adds formatted output
 * |-- ObjectOutputStream    - Serializes objects
 * |-- PipedOutputStream     - Writes to a pipe (used with threads)
 * 
 * WHEN TO USE BYTE STREAMS:
 * -------------------------
 * - Binary files (images, audio, video, executables)
 * - When character encoding doesn't matter
 * - When you need raw byte-level access
 * - Copying non-text files
 * 
 * KEY METHODS:
 * ------------
 * InputStream:
 *   int read()             - Reads single byte (returns -1 at EOF)
 *   int read(byte[])       - Reads into byte array
 *   int read(byte[], off, len) - Reads into portion of array
 *   long skip(long n)      - Skips n bytes
 *   int available()        - Estimates available bytes
 *   void close()           - Closes the stream
 * 
 * OutputStream:
 *   void write(int b)      - Writes single byte
 *   void write(byte[])     - Writes byte array
 *   void write(byte[], off, len) - Writes portion of array
 *   void flush()           - Flushes buffered output
 *   void close()           - Closes the stream
 * 
 * ============================================================================
 */
public class ByteStreams {
    
    private static final String DEMO_DIR = "bytestream_demo";
    
    public static void demonstrate() {
        System.out.println();
        System.out.println("=".repeat(70));
        System.out.println("JAVA BYTE STREAMS");
        System.out.println("=".repeat(70));
        
        // Setup
        setupDemoDirectory();
        
        demonstrateFileInputStream();
        demonstrateFileOutputStream();
        demonstrateReadAndWriteMethods();
        demonstrateByteArrayStreams();
        demonstrateFileCopying();
        
        // Cleanup
        System.out.println("\n[CLEANUP] Removing demo files...");
        cleanupDemoDirectory();
        System.out.println("Demo files removed.");
        
        System.out.println("\n" + "=".repeat(70));
    }
    
    /**
     * Demonstrates FileInputStream for reading bytes from files.
     */
    private static void demonstrateFileInputStream() {
        System.out.println("\n[1] FILEINPUTSTREAM - Reading Bytes from File");
        System.out.println("-".repeat(70));
        
        Path filePath = Path.of(DEMO_DIR, "input.txt");
        
        try {
            // Create a test file
            Files.writeString(filePath, "Hello World! This is test data.");
            System.out.println("Test file created: " + filePath);
            
            // Method 1: Read one byte at a time
            System.out.println("\nMethod 1 - Read one byte at a time:");
            try (FileInputStream fis = new FileInputStream(filePath.toString())) {
                int byteData;
                StringBuilder sb = new StringBuilder();
                while ((byteData = fis.read()) != -1) {
                    sb.append((char) byteData);
                }
                System.out.println("  Content: " + sb);
            }
            
            // Method 2: Read into byte array
            System.out.println("\nMethod 2 - Read into byte array:");
            try (FileInputStream fis = new FileInputStream(filePath.toString())) {
                byte[] buffer = new byte[10]; // Read 10 bytes at a time
                int bytesRead;
                System.out.println("  Reading in chunks of 10 bytes:");
                while ((bytesRead = fis.read(buffer)) != -1) {
                    String chunk = new String(buffer, 0, bytesRead);
                    System.out.println("    Read " + bytesRead + " bytes: \"" + chunk + "\"");
                }
            }
            
            // Method 3: Read all bytes at once
            System.out.println("\nMethod 3 - readAllBytes() [Java 9+]:");
            try (FileInputStream fis = new FileInputStream(filePath.toString())) {
                byte[] allBytes = fis.readAllBytes();
                System.out.println("  Total bytes: " + allBytes.length);
                System.out.println("  Content: " + new String(allBytes));
            }
            
            // Using available() and skip()
            System.out.println("\nUsing available() and skip():");
            try (FileInputStream fis = new FileInputStream(filePath.toString())) {
                System.out.println("  Available bytes: " + fis.available());
                
                fis.skip(6); // Skip "Hello "
                System.out.println("  After skip(6), available: " + fis.available());
                
                byte[] buffer = new byte[5];
                fis.read(buffer);
                System.out.println("  Reading 5 bytes after skip: \"" + new String(buffer) + "\"");
            }
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates FileOutputStream for writing bytes to files.
     */
    private static void demonstrateFileOutputStream() {
        System.out.println("\n[2] FILEOUTPUTSTREAM - Writing Bytes to File");
        System.out.println("-".repeat(70));
        
        Path filePath = Path.of(DEMO_DIR, "output.txt");
        
        try {
            // Method 1: Write - creates new file (overwrites if exists)
            System.out.println("Method 1 - Write (overwrite mode):");
            try (FileOutputStream fos = new FileOutputStream(filePath.toString())) {
                String data = "First line of data.";
                fos.write(data.getBytes());
                System.out.println("  Written: \"" + data + "\"");
            }
            
            // Verify content
            System.out.println("  File content: " + Files.readString(filePath));
            
            // Method 2: Append mode
            System.out.println("\nMethod 2 - Append mode:");
            try (FileOutputStream fos = new FileOutputStream(filePath.toString(), true)) {
                String data = "\nSecond line appended.";
                fos.write(data.getBytes());
                System.out.println("  Appended: \"" + data.replace("\n", "\\n") + "\"");
            }
            
            // Verify content
            System.out.println("  File content:\n    " + 
                Files.readString(filePath).replace("\n", "\n    "));
            
            // Method 3: Write byte by byte
            System.out.println("\nMethod 3 - Write byte by byte:");
            Path byteFile = Path.of(DEMO_DIR, "bytes.txt");
            try (FileOutputStream fos = new FileOutputStream(byteFile.toString())) {
                fos.write('A');  // Write single byte (ASCII 65)
                fos.write('B');  // Write single byte (ASCII 66)
                fos.write('C');  // Write single byte (ASCII 67)
                fos.write(10);   // Write newline (ASCII 10)
                fos.write(new byte[]{68, 69, 70}); // Write bytes for "DEF"
                System.out.println("  Written individual bytes: A, B, C, newline, D, E, F");
            }
            System.out.println("  File content: " + 
                Files.readString(byteFile).replace("\n", "\\n"));
            
            // Important: flush() usage
            System.out.println("\nFLUSH() USAGE:");
            System.out.println("  fos.flush() - Forces any buffered bytes to be written");
            System.out.println("  Important when:");
            System.out.println("    - Using buffered streams");
            System.out.println("    - Need to ensure data is persisted immediately");
            System.out.println("    - Before program crash recovery scenarios");
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates different read and write method signatures.
     */
    private static void demonstrateReadAndWriteMethods() {
        System.out.println("\n[3] READ AND WRITE METHOD VARIATIONS");
        System.out.println("-".repeat(70));
        
        Path filePath = Path.of(DEMO_DIR, "methods.dat");
        
        try {
            // Write with offset and length
            System.out.println("WRITE METHODS:");
            byte[] data = "ABCDEFGHIJ".getBytes();
            
            try (FileOutputStream fos = new FileOutputStream(filePath.toString())) {
                // write(byte[] b) - write entire array
                System.out.println("  Source data: \"ABCDEFGHIJ\"");
                
                // write(byte[] b, int off, int len) - write portion of array
                fos.write(data, 2, 5); // Write "CDEFG" (start at index 2, write 5 bytes)
                System.out.println("  write(data, 2, 5) writes: " + 
                    Files.readString(filePath));
            }
            
            // Read with offset and length
            System.out.println("\nREAD METHODS:");
            Files.writeString(filePath, "0123456789ABCDEF");
            
            try (FileInputStream fis = new FileInputStream(filePath.toString())) {
                byte[] buffer = new byte[20];
                
                // Read into middle of buffer
                int bytesRead = fis.read(buffer, 5, 10); // Start at index 5 in buffer
                System.out.println("  Source: \"0123456789ABCDEF\"");
                System.out.println("  read(buffer, 5, 10) - Read into buffer[5]:");
                System.out.println("    Bytes read: " + bytesRead);
                System.out.println("    Buffer positions 5-14: \"" + 
                    new String(buffer, 5, bytesRead) + "\"");
            }
            
            // readNBytes() - Java 11+
            System.out.println("\nreadNBytes(int len) [Java 11+]:");
            try (FileInputStream fis = new FileInputStream(filePath.toString())) {
                byte[] firstFive = fis.readNBytes(5);
                System.out.println("  readNBytes(5): \"" + new String(firstFive) + "\"");
                
                byte[] nextFive = fis.readNBytes(5);
                System.out.println("  readNBytes(5): \"" + new String(nextFive) + "\"");
            }
            
            // transferTo() - Java 9+
            System.out.println("\ntransferTo(OutputStream) [Java 9+]:");
            Path destPath = Path.of(DEMO_DIR, "transferred.dat");
            try (FileInputStream fis = new FileInputStream(filePath.toString());
                 FileOutputStream fos = new FileOutputStream(destPath.toString())) {
                long transferred = fis.transferTo(fos);
                System.out.println("  Transferred " + transferred + " bytes");
                System.out.println("  Destination content: " + Files.readString(destPath));
            }
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates ByteArrayInputStream and ByteArrayOutputStream.
     */
    private static void demonstrateByteArrayStreams() {
        System.out.println("\n[4] BYTEARRAYINPUTSTREAM AND BYTEARRAYOUTPUTSTREAM");
        System.out.println("-".repeat(70));
        
        // ByteArrayOutputStream - Write to memory
        System.out.println("BYTEARRAYOUTPUTSTREAM (in-memory writing):");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try {
            baos.write("Hello ".getBytes());
            baos.write("World!".getBytes());
            baos.write(new byte[]{10, 65, 66, 67}); // newline + ABC
            
            // Get the data
            byte[] result = baos.toByteArray();
            System.out.println("  Collected " + result.length + " bytes");
            System.out.println("  Content: " + new String(result).replace("\n", "\\n"));
            
            // Can also write to another stream
            Path outFile = Path.of(DEMO_DIR, "from_memory.txt");
            try (FileOutputStream fos = new FileOutputStream(outFile.toString())) {
                baos.writeTo(fos);
                System.out.println("  Written to file: " + outFile.getFileName());
            }
            
            // Reset and reuse
            baos.reset();
            baos.write("Fresh start!".getBytes());
            System.out.println("  After reset(): " + baos.toString());
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        // ByteArrayInputStream - Read from memory
        System.out.println("\nBYTEARRAYINPUTSTREAM (in-memory reading):");
        byte[] sourceData = "Test data for reading".getBytes();
        
        try (ByteArrayInputStream bais = new ByteArrayInputStream(sourceData)) {
            System.out.println("  Source: \"Test data for reading\"");
            System.out.println("  Available: " + bais.available() + " bytes");
            
            // Read first 5 bytes
            byte[] buffer = new byte[5];
            bais.read(buffer);
            System.out.println("  First 5 bytes: \"" + new String(buffer) + "\"");
            
            // Mark and reset
            bais.mark(0); // Mark current position
            buffer = new byte[4];
            bais.read(buffer);
            System.out.println("  Next 4 bytes: \"" + new String(buffer) + "\"");
            
            bais.reset(); // Go back to marked position
            bais.read(buffer);
            System.out.println("  After reset, 4 bytes: \"" + new String(buffer) + "\"");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        // Use cases
        System.out.println("\nUSE CASES:");
        System.out.println("  ByteArrayOutputStream:");
        System.out.println("    - Building binary data in memory");
        System.out.println("    - Capturing output for later use");
        System.out.println("    - Testing without file I/O");
        System.out.println("  ByteArrayInputStream:");
        System.out.println("    - Reading from existing byte array");
        System.out.println("    - Processing binary data in memory");
        System.out.println("    - Unit testing stream-based code");
    }
    
    /**
     * Demonstrates copying files using byte streams.
     */
    private static void demonstrateFileCopying() {
        System.out.println("\n[5] FILE COPYING WITH BYTE STREAMS");
        System.out.println("-".repeat(70));
        
        Path sourceFile = Path.of(DEMO_DIR, "source_copy.bin");
        Path destFile = Path.of(DEMO_DIR, "dest_copy.bin");
        
        try {
            // Create a binary source file
            byte[] binaryData = new byte[1000];
            for (int i = 0; i < binaryData.length; i++) {
                binaryData[i] = (byte) (i % 256);
            }
            Files.write(sourceFile, binaryData);
            System.out.println("Created source file: " + binaryData.length + " bytes");
            
            // Method 1: Simple copy (inefficient for large files)
            System.out.println("\nMethod 1 - Byte by byte (SLOW - don't use for large files):");
            long start = System.nanoTime();
            try (FileInputStream fis = new FileInputStream(sourceFile.toString());
                 FileOutputStream fos = new FileOutputStream(destFile.toString())) {
                int b;
                while ((b = fis.read()) != -1) {
                    fos.write(b);
                }
            }
            long time1 = System.nanoTime() - start;
            System.out.println("  Time: " + (time1 / 1_000_000.0) + " ms");
            
            // Method 2: Buffer copy (much faster)
            System.out.println("\nMethod 2 - With buffer (RECOMMENDED):");
            start = System.nanoTime();
            try (FileInputStream fis = new FileInputStream(sourceFile.toString());
                 FileOutputStream fos = new FileOutputStream(destFile.toString())) {
                byte[] buffer = new byte[8192]; // 8KB buffer
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
            }
            long time2 = System.nanoTime() - start;
            System.out.println("  Time: " + (time2 / 1_000_000.0) + " ms");
            System.out.println("  Speedup: " + String.format("%.1f", (double) time1 / time2) + "x faster");
            
            // Method 3: Using transferTo (Java 9+)
            System.out.println("\nMethod 3 - transferTo() [Java 9+]:");
            start = System.nanoTime();
            try (FileInputStream fis = new FileInputStream(sourceFile.toString());
                 FileOutputStream fos = new FileOutputStream(destFile.toString())) {
                fis.transferTo(fos);
            }
            long time3 = System.nanoTime() - start;
            System.out.println("  Time: " + (time3 / 1_000_000.0) + " ms");
            
            // Method 4: Using NIO Files.copy (best practice)
            System.out.println("\nMethod 4 - Files.copy() (BEST PRACTICE):");
            start = System.nanoTime();
            Files.copy(sourceFile, destFile, 
                java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            long time4 = System.nanoTime() - start;
            System.out.println("  Time: " + (time4 / 1_000_000.0) + " ms");
            
            System.out.println("\nRECOMMENDATION:");
            System.out.println("  For file copying, prefer Files.copy() from NIO");
            System.out.println("  For stream processing, use buffered approach");
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
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
