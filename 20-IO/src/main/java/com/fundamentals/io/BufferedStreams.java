package com.fundamentals.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * ============================================================================
 * JAVA BUFFERED STREAMS - Efficient I/O with Buffering
 * ============================================================================
 * 
 * Buffered streams reduce the number of I/O operations by reading/writing 
 * data in chunks (buffers) instead of byte-by-byte or char-by-char.
 * 
 * WHY BUFFERING?
 * --------------
 * - Each read()/write() call to a file involves OS-level operations
 * - OS calls are expensive (context switches, disk access)
 * - Buffering reduces the number of OS calls dramatically
 * - Default buffer size is typically 8KB (8192 bytes)
 * 
 * BUFFERED STREAM CLASSES:
 * ------------------------
 * BufferedInputStream  - Buffers byte input
 * BufferedOutputStream - Buffers byte output
 * BufferedReader       - Buffers character input, adds readLine()
 * BufferedWriter       - Buffers character output, adds newLine()
 * 
 * PERFORMANCE:
 * - Unbuffered: Each read/write = 1 OS call
 * - Buffered:   Many reads/writes = 1 OS call (when buffer flushes)
 * - Can be 10-100x faster for large files
 * 
 * KEY FEATURES:
 * - BufferedReader.readLine() - reads entire line at once
 * - BufferedWriter.newLine() - platform-independent line separator
 * - BufferedReader.lines() - returns Stream<String> for processing
 * 
 * ============================================================================
 */
public class BufferedStreams {
    
    private static final String DEMO_DIR = "buffered_demo";
    
    public static void demonstrate() {
        System.out.println();
        System.out.println("=".repeat(70));
        System.out.println("JAVA BUFFERED STREAMS");
        System.out.println("=".repeat(70));
        
        setupDemoDirectory();
        
        demonstrateBufferedReader();
        demonstrateBufferedWriter();
        demonstrateBufferedByteStreams();
        demonstratePerformanceComparison();
        demonstrateReadLine();
        
        System.out.println("\n[CLEANUP] Removing demo files...");
        cleanupDemoDirectory();
        
        System.out.println("\n" + "=".repeat(70));
    }
    
    /**
     * Demonstrates BufferedReader for efficient text reading.
     */
    private static void demonstrateBufferedReader() {
        System.out.println("\n[1] BUFFEREDREADER");
        System.out.println("-".repeat(70));
        
        Path filePath = Path.of(DEMO_DIR, "lines.txt");
        
        try {
            // Create test file with multiple lines
            Files.writeString(filePath, 
                "Line 1: Hello World\n" +
                "Line 2: Java I/O\n" +
                "Line 3: Buffered Streams\n" +
                "Line 4: Efficient Reading\n" +
                "Line 5: End of file");
            
            // Method 1: readLine() - most common usage
            System.out.println("METHOD 1 - readLine():");
            try (BufferedReader br = new BufferedReader(
                    new FileReader(filePath.toString(), StandardCharsets.UTF_8))) {
                String line;
                int lineNum = 1;
                while ((line = br.readLine()) != null) {
                    System.out.println("  " + lineNum++ + ": " + line);
                }
            }
            
            // Method 2: lines() - Stream API (Java 8+)
            System.out.println("\nMETHOD 2 - lines() Stream:");
            try (BufferedReader br = new BufferedReader(
                    new FileReader(filePath.toString(), StandardCharsets.UTF_8))) {
                br.lines()
                  .filter(line -> line.contains("Line"))
                  .forEach(line -> System.out.println("  " + line));
            }
            
            // Method 3: Read characters with mark/reset
            System.out.println("\nMETHOD 3 - mark() and reset():");
            try (BufferedReader br = new BufferedReader(
                    new FileReader(filePath.toString(), StandardCharsets.UTF_8))) {
                
                br.mark(100); // Mark current position (readAheadLimit = 100)
                String first = br.readLine();
                System.out.println("  First read: " + first);
                
                br.reset(); // Go back to marked position
                String again = br.readLine();
                System.out.println("  After reset: " + again);
            }
            
            // Custom buffer size
            System.out.println("\nCUSTOM BUFFER SIZE:");
            System.out.println("  new BufferedReader(reader, 16384) // 16KB buffer");
            System.out.println("  Default buffer: 8192 bytes (8KB)");
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates BufferedWriter for efficient text writing.
     */
    private static void demonstrateBufferedWriter() {
        System.out.println("\n[2] BUFFEREDWRITER");
        System.out.println("-".repeat(70));
        
        Path filePath = Path.of(DEMO_DIR, "output.txt");
        
        try {
            // Basic usage with newLine()
            System.out.println("WRITING WITH BufferedWriter:");
            try (BufferedWriter bw = new BufferedWriter(
                    new FileWriter(filePath.toString(), StandardCharsets.UTF_8))) {
                
                bw.write("First line of text");
                bw.newLine(); // Platform-independent line separator
                
                bw.write("Second line");
                bw.newLine();
                
                bw.write("Third line with ");
                bw.append("appended text"); // Append returns Writer
                bw.newLine();
                
                System.out.println("  Written 3 lines to file");
            }
            
            // Verify content
            System.out.println("\nFILE CONTENT:");
            Files.readAllLines(filePath).forEach(line -> 
                System.out.println("  " + line));
            
            // newLine() importance
            System.out.println("\nnewLine() IMPORTANCE:");
            System.out.println("  Windows: \\r\\n (CRLF)");
            System.out.println("  Unix/Linux/Mac: \\n (LF)");
            System.out.println("  bw.newLine() handles this automatically!");
            
            // flush() usage
            System.out.println("\nflush() USAGE:");
            System.out.println("  - Forces buffered data to be written immediately");
            System.out.println("  - Important for real-time logging");
            System.out.println("  - Called automatically on close()");
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates BufferedInputStream and BufferedOutputStream.
     */
    private static void demonstrateBufferedByteStreams() {
        System.out.println("\n[3] BUFFERED BYTE STREAMS");
        System.out.println("-".repeat(70));
        
        Path filePath = Path.of(DEMO_DIR, "binary.dat");
        
        try {
            // BufferedOutputStream
            System.out.println("BUFFEREDOUTPUTSTREAM:");
            try (BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(filePath.toString()))) {
                
                // Write some binary data
                for (int i = 0; i < 100; i++) {
                    bos.write(i);
                }
                System.out.println("  Written 100 bytes to binary file");
            }
            
            // BufferedInputStream
            System.out.println("\nBUFFEREDINPUTSTREAM:");
            try (BufferedInputStream bis = new BufferedInputStream(
                    new FileInputStream(filePath.toString()))) {
                
                System.out.println("  available(): " + bis.available());
                
                // Read first 10 bytes
                byte[] buffer = new byte[10];
                int bytesRead = bis.read(buffer);
                System.out.print("  First 10 bytes: ");
                for (int i = 0; i < bytesRead; i++) {
                    System.out.print(buffer[i] + " ");
                }
                System.out.println();
                
                // Skip and read
                bis.skip(40);
                bytesRead = bis.read(buffer);
                System.out.print("  After skip(40), next 10: ");
                for (int i = 0; i < bytesRead; i++) {
                    System.out.print(buffer[i] + " ");
                }
                System.out.println();
            }
            
            // Custom buffer size
            System.out.println("\nCUSTOM BUFFER SIZE:");
            System.out.println("  new BufferedInputStream(is, 32768) // 32KB");
            System.out.println("  Larger buffer = fewer OS calls = faster for big files");
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates performance difference between buffered and unbuffered.
     */
    private static void demonstratePerformanceComparison() {
        System.out.println("\n[4] PERFORMANCE COMPARISON");
        System.out.println("-".repeat(70));
        
        Path filePath = Path.of(DEMO_DIR, "perf_test.dat");
        int dataSize = 100000; // 100KB
        
        try {
            // Create test data
            byte[] data = new byte[dataSize];
            for (int i = 0; i < dataSize; i++) {
                data[i] = (byte) (i % 256);
            }
            Files.write(filePath, data);
            
            // Test 1: Unbuffered read (byte by byte)
            System.out.println("Reading " + dataSize + " bytes:");
            long start = System.nanoTime();
            try (FileInputStream fis = new FileInputStream(filePath.toString())) {
                while (fis.read() != -1) { }
            }
            long unbufferedTime = System.nanoTime() - start;
            System.out.printf("  Unbuffered (byte-by-byte): %.2f ms%n", 
                unbufferedTime / 1_000_000.0);
            
            // Test 2: Buffered read
            start = System.nanoTime();
            try (BufferedInputStream bis = new BufferedInputStream(
                    new FileInputStream(filePath.toString()))) {
                while (bis.read() != -1) { }
            }
            long bufferedTime = System.nanoTime() - start;
            System.out.printf("  Buffered:                  %.2f ms%n", 
                bufferedTime / 1_000_000.0);
            
            // Speedup
            double speedup = (double) unbufferedTime / bufferedTime;
            System.out.printf("\n  Buffered is %.1fx faster!%n", speedup);
            
            System.out.println("\nCONCLUSION:");
            System.out.println("  ALWAYS use buffered streams for file I/O");
            System.out.println("  Exception: Files.readAllBytes() is already efficient");
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates readLine() variations and patterns.
     */
    private static void demonstrateReadLine() {
        System.out.println("\n[5] READLINE() PATTERNS");
        System.out.println("-".repeat(70));
        
        Path filePath = Path.of(DEMO_DIR, "readline.txt");
        
        try {
            Files.writeString(filePath, 
                "apple,red,fruit\n" +
                "carrot,orange,vegetable\n" +
                "banana,yellow,fruit\n");
            
            // Pattern 1: Loop until null
            System.out.println("PATTERN 1 - While loop:");
            try (BufferedReader br = Files.newBufferedReader(filePath)) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println("  " + line);
                }
            }
            
            // Pattern 2: Use Stream API
            System.out.println("\nPATTERN 2 - Stream API:");
            try (Stream<String> lines = Files.lines(filePath)) {
                lines.map(line -> line.split(","))
                     .filter(parts -> parts.length >= 3)
                     .forEach(parts -> System.out.println("  " + parts[0] + " is " + parts[1]));
            }
            
            // Pattern 3: Files.readAllLines (for small files)
            System.out.println("\nPATTERN 3 - Files.readAllLines():");
            var allLines = Files.readAllLines(filePath);
            System.out.println("  Total lines: " + allLines.size());
            
            System.out.println("\nWHICH TO USE?");
            System.out.println("  Small files: Files.readAllLines() - loads all into memory");
            System.out.println("  Large files: BufferedReader.readLine() - line by line");
            System.out.println("  Processing:  Files.lines() - lazy Stream, auto-closeable");
            
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
