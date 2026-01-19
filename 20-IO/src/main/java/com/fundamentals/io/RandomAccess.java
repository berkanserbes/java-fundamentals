package com.fundamentals.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * ============================================================================
 * JAVA RANDOMACCESSFILE - Random File Access
 * ============================================================================
 * 
 * RandomAccessFile allows reading and writing to any position in a file,
 * unlike streams which only allow sequential access.
 * 
 * KEY FEATURES:
 * -------------
 * - Read and write anywhere in the file
 * - Seek to any position using file pointer
 * - Can grow the file by writing past the end
 * - Supports both reading and writing in one object
 * 
 * ACCESS MODES:
 * -------------
 * "r"   - Read only
 * "rw"  - Read and write
 * "rws" - Read/write with synchronous file/metadata updates
 * "rwd" - Read/write with synchronous file updates only
 * 
 * FILE POINTER:
 * -------------
 * - Current position in the file (in bytes)
 * - Starts at 0 (beginning of file)
 * - Moves forward after each read/write
 * - Can be moved with seek()
 * 
 * USE CASES:
 * ----------
 * - Database-like file storage
 * - Fixed-length record files
 * - Editing specific parts of large files
 * - Binary file manipulation
 * - Log file analysis
 * 
 * ============================================================================
 */
public class RandomAccess {
    
    private static final String DEMO_DIR = "random_demo";
    
    public static void demonstrate() {
        System.out.println();
        System.out.println("=".repeat(70));
        System.out.println("JAVA RANDOMACCESSFILE");
        System.out.println("=".repeat(70));
        
        setupDemoDirectory();
        
        demonstrateBasicUsage();
        demonstrateSeekOperations();
        demonstrateFixedLengthRecords();
        demonstrateFileModification();
        
        System.out.println("\n[CLEANUP] Removing demo files...");
        cleanupDemoDirectory();
        
        System.out.println("\n" + "=".repeat(70));
    }
    
    /**
     * Demonstrates basic RandomAccessFile usage.
     */
    private static void demonstrateBasicUsage() {
        System.out.println("\n[1] BASIC USAGE");
        System.out.println("-".repeat(70));
        
        Path filePath = Path.of(DEMO_DIR, "random.dat");
        
        try {
            // Create and write
            System.out.println("CREATING AND WRITING:");
            try (RandomAccessFile raf = new RandomAccessFile(filePath.toString(), "rw")) {
                raf.writeUTF("Hello");
                raf.writeInt(42);
                raf.writeDouble(3.14159);
                raf.writeBoolean(true);
                
                System.out.println("  Written: String, int, double, boolean");
                System.out.println("  File pointer: " + raf.getFilePointer());
                System.out.println("  File length: " + raf.length());
            }
            
            // Read back
            System.out.println("\nREADING:");
            try (RandomAccessFile raf = new RandomAccessFile(filePath.toString(), "r")) {
                System.out.println("  readUTF():     " + raf.readUTF());
                System.out.println("  readInt():     " + raf.readInt());
                System.out.println("  readDouble():  " + raf.readDouble());
                System.out.println("  readBoolean(): " + raf.readBoolean());
            }
            
            // Access modes
            System.out.println("\nACCESS MODES:");
            System.out.println("  \"r\"   - Read only (FileNotFoundException if not exists)");
            System.out.println("  \"rw\"  - Read/write (creates file if not exists)");
            System.out.println("  \"rws\" - Sync content and metadata to disk");
            System.out.println("  \"rwd\" - Sync content only to disk");
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates seek operations and file pointer manipulation.
     */
    private static void demonstrateSeekOperations() {
        System.out.println("\n[2] SEEK OPERATIONS");
        System.out.println("-".repeat(70));
        
        Path filePath = Path.of(DEMO_DIR, "seek.dat");
        
        try {
            // Create file with known structure
            try (RandomAccessFile raf = new RandomAccessFile(filePath.toString(), "rw")) {
                // Write 10 integers (4 bytes each = 40 bytes total)
                for (int i = 0; i < 10; i++) {
                    raf.writeInt(i * 100); // 0, 100, 200, ..., 900
                }
                System.out.println("Written 10 integers: 0, 100, 200, ..., 900");
                System.out.println("File size: " + raf.length() + " bytes");
            }
            
            // Random access reading
            System.out.println("\nRANDOM ACCESS READING:");
            try (RandomAccessFile raf = new RandomAccessFile(filePath.toString(), "r")) {
                // Read 5th integer (index 4, position = 4 * 4 = 16)
                raf.seek(16);
                System.out.println("  seek(16), read 5th int: " + raf.readInt());
                
                // Read 3rd integer
                raf.seek(8);
                System.out.println("  seek(8), read 3rd int: " + raf.readInt());
                
                // Read last integer
                raf.seek(36);
                System.out.println("  seek(36), read 10th int: " + raf.readInt());
                
                // Read first integer
                raf.seek(0);
                System.out.println("  seek(0), read 1st int: " + raf.readInt());
            }
            
            // File pointer methods
            System.out.println("\nFILE POINTER METHODS:");
            try (RandomAccessFile raf = new RandomAccessFile(filePath.toString(), "r")) {
                System.out.println("  Initial position: " + raf.getFilePointer());
                
                raf.readInt();
                System.out.println("  After readInt(): " + raf.getFilePointer());
                
                raf.skipBytes(8); // Skip 2 integers
                System.out.println("  After skipBytes(8): " + raf.getFilePointer());
                
                raf.seek(0);
                System.out.println("  After seek(0): " + raf.getFilePointer());
            }
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates fixed-length record handling.
     */
    private static void demonstrateFixedLengthRecords() {
        System.out.println("\n[3] FIXED-LENGTH RECORDS");
        System.out.println("-".repeat(70));
        
        Path filePath = Path.of(DEMO_DIR, "records.dat");
        
        // Record structure: ID (4 bytes) + Name (20 bytes) + Score (8 bytes) = 32 bytes
        final int RECORD_SIZE = 32;
        final int NAME_SIZE = 20;
        
        try {
            // Write records
            System.out.println("WRITING FIXED-LENGTH RECORDS:");
            System.out.println("  Record size: " + RECORD_SIZE + " bytes");
            System.out.println("  Structure: ID(4) + Name(20) + Score(8)");
            
            try (RandomAccessFile raf = new RandomAccessFile(filePath.toString(), "rw")) {
                writeRecord(raf, 1, "Alice", 95.5, NAME_SIZE);
                writeRecord(raf, 2, "Bob", 87.3, NAME_SIZE);
                writeRecord(raf, 3, "Carol", 92.8, NAME_SIZE);
                writeRecord(raf, 4, "David", 78.9, NAME_SIZE);
                writeRecord(raf, 5, "Eve", 88.5, NAME_SIZE);
                System.out.println("  Written 5 records");
            }
            
            // Read all records
            System.out.println("\nREADING ALL RECORDS:");
            try (RandomAccessFile raf = new RandomAccessFile(filePath.toString(), "r")) {
                long numRecords = raf.length() / RECORD_SIZE;
                for (int i = 0; i < numRecords; i++) {
                    int id = raf.readInt();
                    String name = readFixedString(raf, NAME_SIZE);
                    double score = raf.readDouble();
                    System.out.printf("  ID: %d, Name: %-10s, Score: %.1f%n", id, name.trim(), score);
                }
            }
            
            // Random access to specific record
            System.out.println("\nRANDOM ACCESS TO RECORD #3:");
            try (RandomAccessFile raf = new RandomAccessFile(filePath.toString(), "r")) {
                int recordIndex = 2; // 0-based index for record #3
                raf.seek(recordIndex * RECORD_SIZE);
                
                int id = raf.readInt();
                String name = readFixedString(raf, NAME_SIZE);
                double score = raf.readDouble();
                System.out.printf("  ID: %d, Name: %s, Score: %.1f%n", id, name.trim(), score);
            }
            
            // Update specific record
            System.out.println("\nUPDATING RECORD #2 SCORE:");
            try (RandomAccessFile raf = new RandomAccessFile(filePath.toString(), "rw")) {
                // Seek to score field of record #2
                // Position = (1 * 32) + 4 + 20 = 56
                raf.seek(1 * RECORD_SIZE + 4 + NAME_SIZE);
                raf.writeDouble(99.9);
                System.out.println("  Updated Bob's score to 99.9");
            }
            
            // Verify update
            System.out.println("\nVERIFYING UPDATE:");
            try (RandomAccessFile raf = new RandomAccessFile(filePath.toString(), "r")) {
                raf.seek(1 * RECORD_SIZE);
                int id = raf.readInt();
                String name = readFixedString(raf, NAME_SIZE);
                double score = raf.readDouble();
                System.out.printf("  ID: %d, Name: %s, Score: %.1f%n", id, name.trim(), score);
            }
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates modifying existing file content.
     */
    private static void demonstrateFileModification() {
        System.out.println("\n[4] FILE MODIFICATION");
        System.out.println("-".repeat(70));
        
        Path filePath = Path.of(DEMO_DIR, "modify.txt");
        
        try {
            // Create text file
            Files.writeString(filePath, "Hello World! This is a test file.");
            System.out.println("Original: " + Files.readString(filePath));
            
            // Modify specific bytes
            System.out.println("\nMODIFYING BYTES:");
            try (RandomAccessFile raf = new RandomAccessFile(filePath.toString(), "rw")) {
                // Change "World" to "Java!"
                raf.seek(6); // Position of 'W'
                raf.writeBytes("Java!");
            }
            System.out.println("After change: " + Files.readString(filePath));
            
            // Truncate file
            System.out.println("\nTRUNCATING FILE:");
            try (RandomAccessFile raf = new RandomAccessFile(filePath.toString(), "rw")) {
                System.out.println("  Original length: " + raf.length());
                raf.setLength(12); // Keep only first 12 bytes
                System.out.println("  After setLength(12): " + raf.length());
            }
            System.out.println("Content: \"" + Files.readString(filePath) + "\"");
            
            // Extend file
            System.out.println("\nEXTENDING FILE:");
            try (RandomAccessFile raf = new RandomAccessFile(filePath.toString(), "rw")) {
                raf.seek(raf.length()); // Go to end
                raf.writeBytes(" - Extended!");
                System.out.println("  New length: " + raf.length());
            }
            System.out.println("Content: \"" + Files.readString(filePath) + "\"");
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    // Helper methods
    
    private static void writeRecord(RandomAccessFile raf, int id, String name, 
            double score, int nameSize) throws IOException {
        raf.writeInt(id);
        writeFixedString(raf, name, nameSize);
        raf.writeDouble(score);
    }
    
    private static void writeFixedString(RandomAccessFile raf, String s, 
            int size) throws IOException {
        byte[] bytes = new byte[size];
        byte[] strBytes = s.getBytes();
        System.arraycopy(strBytes, 0, bytes, 0, Math.min(strBytes.length, size));
        raf.write(bytes);
    }
    
    private static String readFixedString(RandomAccessFile raf, 
            int size) throws IOException {
        byte[] bytes = new byte[size];
        raf.readFully(bytes);
        return new String(bytes);
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
