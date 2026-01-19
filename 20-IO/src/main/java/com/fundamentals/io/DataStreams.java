package com.fundamentals.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * ============================================================================
 * JAVA DATA STREAMS - Reading and Writing Primitive Types
 * ============================================================================
 * 
 * DataInputStream and DataOutputStream allow reading and writing Java primitive
 * data types (int, long, double, etc.) in a portable way.
 * 
 * KEY FEATURES:
 * -------------
 * - Write/read primitives in binary format
 * - Machine-independent (big-endian byte order)
 * - Efficient for numerical data
 * - Preserves exact values (no string conversion loss)
 * 
 * SUPPORTED TYPES:
 * ----------------
 * boolean - readBoolean() / writeBoolean()
 * byte    - readByte() / writeByte()
 * short   - readShort() / writeShort()
 * int     - readInt() / writeInt()
 * long    - readLong() / writeLong()
 * float   - readFloat() / writeFloat()
 * double  - readDouble() / writeDouble()
 * char    - readChar() / writeChar()
 * String  - readUTF() / writeUTF() (modified UTF-8)
 * 
 * IMPORTANT:
 * - Data must be read in the same order it was written
 * - Data format is binary, not human-readable
 * - readUTF/writeUTF uses modified UTF-8, limited to 65535 bytes
 * 
 * USE CASES:
 * ----------
 * - Saving game state (scores, positions)
 * - Binary configuration files
 * - Network protocols
 * - Simple database files
 * 
 * ============================================================================
 */
public class DataStreams {
    
    private static final String DEMO_DIR = "data_demo";
    
    public static void demonstrate() {
        System.out.println();
        System.out.println("=".repeat(70));
        System.out.println("JAVA DATA STREAMS");
        System.out.println("=".repeat(70));
        
        setupDemoDirectory();
        
        demonstrateBasicUsage();
        demonstrateAllPrimitiveTypes();
        demonstrateRecordLikeStructure();
        demonstrateUTFStrings();
        
        System.out.println("\n[CLEANUP] Removing demo files...");
        cleanupDemoDirectory();
        
        System.out.println("\n" + "=".repeat(70));
    }
    
    /**
     * Demonstrates basic DataInputStream and DataOutputStream usage.
     */
    private static void demonstrateBasicUsage() {
        System.out.println("\n[1] BASIC USAGE");
        System.out.println("-".repeat(70));
        
        Path filePath = Path.of(DEMO_DIR, "basic.dat");
        
        try {
            // Writing primitives
            System.out.println("WRITING PRIMITIVES:");
            try (DataOutputStream dos = new DataOutputStream(
                    new BufferedOutputStream(
                        new FileOutputStream(filePath.toString())))) {
                
                dos.writeInt(42);
                dos.writeDouble(3.14159);
                dos.writeBoolean(true);
                dos.writeUTF("Hello Data Streams!");
                
                System.out.println("  writeInt(42)");
                System.out.println("  writeDouble(3.14159)");
                System.out.println("  writeBoolean(true)");
                System.out.println("  writeUTF(\"Hello Data Streams!\")");
            }
            
            System.out.println("\nFile size: " + Files.size(filePath) + " bytes");
            
            // Reading primitives
            System.out.println("\nREADING PRIMITIVES:");
            try (DataInputStream dis = new DataInputStream(
                    new BufferedInputStream(
                        new FileInputStream(filePath.toString())))) {
                
                // Must read in same order as written!
                int intValue = dis.readInt();
                double doubleValue = dis.readDouble();
                boolean boolValue = dis.readBoolean();
                String strValue = dis.readUTF();
                
                System.out.println("  readInt():     " + intValue);
                System.out.println("  readDouble():  " + doubleValue);
                System.out.println("  readBoolean(): " + boolValue);
                System.out.println("  readUTF():     " + strValue);
            }
            
            System.out.println("\nIMPORTANT: Read in the SAME ORDER as written!");
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates all primitive type methods.
     */
    private static void demonstrateAllPrimitiveTypes() {
        System.out.println("\n[2] ALL PRIMITIVE TYPES");
        System.out.println("-".repeat(70));
        
        Path filePath = Path.of(DEMO_DIR, "all_types.dat");
        
        try {
            // Write all types
            try (DataOutputStream dos = new DataOutputStream(
                    new FileOutputStream(filePath.toString()))) {
                
                dos.writeBoolean(true);          // 1 byte
                dos.writeByte(127);              // 1 byte
                dos.writeShort(32767);           // 2 bytes
                dos.writeChar('A');              // 2 bytes
                dos.writeInt(2147483647);        // 4 bytes
                dos.writeLong(9223372036854775807L); // 8 bytes
                dos.writeFloat(3.14f);           // 4 bytes
                dos.writeDouble(2.718281828);    // 8 bytes
            }
            
            System.out.println("Written all primitive types:");
            System.out.println("  File size: " + Files.size(filePath) + " bytes");
            System.out.println("  Expected: 1+1+2+2+4+8+4+8 = 30 bytes");
            
            // Read all types
            System.out.println("\nRead back:");
            try (DataInputStream dis = new DataInputStream(
                    new FileInputStream(filePath.toString()))) {
                
                System.out.println("  boolean: " + dis.readBoolean());
                System.out.println("  byte:    " + dis.readByte());
                System.out.println("  short:   " + dis.readShort());
                System.out.println("  char:    " + dis.readChar());
                System.out.println("  int:     " + dis.readInt());
                System.out.println("  long:    " + dis.readLong());
                System.out.println("  float:   " + dis.readFloat());
                System.out.println("  double:  " + dis.readDouble());
            }
            
            // Size table
            System.out.println("\nPRIMITIVE TYPE SIZES:");
            System.out.println("  +-----------+-------+");
            System.out.println("  | Type      | Bytes |");
            System.out.println("  +-----------+-------+");
            System.out.println("  | boolean   |     1 |");
            System.out.println("  | byte      |     1 |");
            System.out.println("  | short     |     2 |");
            System.out.println("  | char      |     2 |");
            System.out.println("  | int       |     4 |");
            System.out.println("  | long      |     8 |");
            System.out.println("  | float     |     4 |");
            System.out.println("  | double    |     8 |");
            System.out.println("  +-----------+-------+");
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates using DataStreams for record-like structures.
     */
    private static void demonstrateRecordLikeStructure() {
        System.out.println("\n[3] RECORD-LIKE STRUCTURE");
        System.out.println("-".repeat(70));
        
        Path filePath = Path.of(DEMO_DIR, "employees.dat");
        
        // Simulating Employee record: id (int), name (String), salary (double)
        System.out.println("Writing 3 employee records:");
        
        try {
            // Write records
            try (DataOutputStream dos = new DataOutputStream(
                    new FileOutputStream(filePath.toString()))) {
                
                // Write number of records first
                dos.writeInt(3);
                
                // Employee 1
                dos.writeInt(101);
                dos.writeUTF("Alice Johnson");
                dos.writeDouble(75000.00);
                
                // Employee 2
                dos.writeInt(102);
                dos.writeUTF("Bob Smith");
                dos.writeDouble(82500.50);
                
                // Employee 3
                dos.writeInt(103);
                dos.writeUTF("Carol Williams");
                dos.writeDouble(91000.00);
                
                System.out.println("  Written 3 employee records");
            }
            
            // Read records
            System.out.println("\nReading employee records:");
            try (DataInputStream dis = new DataInputStream(
                    new FileInputStream(filePath.toString()))) {
                
                int count = dis.readInt();
                System.out.println("  Record count: " + count);
                System.out.println();
                
                for (int i = 0; i < count; i++) {
                    int id = dis.readInt();
                    String name = dis.readUTF();
                    double salary = dis.readDouble();
                    
                    System.out.printf("  ID: %d | Name: %-15s | Salary: $%,.2f%n", 
                        id, name, salary);
                }
            }
            
            System.out.println("\nFile size: " + Files.size(filePath) + " bytes");
            System.out.println("(Much smaller than text-based CSV!)");
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates UTF String handling.
     */
    private static void demonstrateUTFStrings() {
        System.out.println("\n[4] UTF STRING HANDLING");
        System.out.println("-".repeat(70));
        
        Path filePath = Path.of(DEMO_DIR, "utf.dat");
        
        try {
            // Write UTF strings
            System.out.println("WRITING UTF STRINGS:");
            try (DataOutputStream dos = new DataOutputStream(
                    new FileOutputStream(filePath.toString()))) {
                
                dos.writeUTF("Hello");
                dos.writeUTF("World");
                dos.writeUTF("Special: Cafe Resume");
                dos.writeUTF(""); // Empty string
                
                System.out.println("  Written 4 UTF strings");
            }
            
            // Read UTF strings
            System.out.println("\nREADING UTF STRINGS:");
            try (DataInputStream dis = new DataInputStream(
                    new FileInputStream(filePath.toString()))) {
                
                System.out.println("  1: \"" + dis.readUTF() + "\"");
                System.out.println("  2: \"" + dis.readUTF() + "\"");
                System.out.println("  3: \"" + dis.readUTF() + "\"");
                System.out.println("  4: \"" + dis.readUTF() + "\" (empty)");
            }
            
            // UTF format explanation
            System.out.println("\nUTF FORMAT DETAILS:");
            System.out.println("  - writeUTF() prefixes with 2-byte length");
            System.out.println("  - Uses modified UTF-8 encoding");
            System.out.println("  - Maximum string length: 65535 bytes");
            System.out.println("  - Null character encoded as 2 bytes (not 1)");
            
            // Show structure
            System.out.println("\nSTRING STORAGE:");
            System.out.println("  \"Hello\" = [0x00, 0x05] + [H, e, l, l, o]");
            System.out.println("           = 2 bytes length + 5 bytes data = 7 bytes");
            
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
