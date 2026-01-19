package com.fundamentals.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * ============================================================================
 * JAVA CHARACTER STREAMS - Reader and Writer
 * ============================================================================
 * 
 * Character streams handle I/O of character data (16-bit Unicode). They 
 * automatically handle character encoding/decoding for text-based operations.
 * 
 * CHARACTER STREAM HIERARCHY:
 * ---------------------------
 * Reader (abstract)
 * |-- InputStreamReader -> FileReader
 * |-- BufferedReader, CharArrayReader, StringReader
 * 
 * Writer (abstract)
 * |-- OutputStreamWriter -> FileWriter
 * |-- BufferedWriter, CharArrayWriter, StringWriter, PrintWriter
 * 
 * WHEN TO USE:
 * - Text files (.txt, .csv, .json, .xml)
 * - Configuration files
 * - Any human-readable content
 * 
 * IMPORTANT: Always specify charset explicitly (UTF-8 recommended)
 * 
 * ============================================================================
 */
public class CharacterStreams {
    
    private static final String DEMO_DIR = "charstream_demo";
    
    public static void demonstrate() {
        System.out.println();
        System.out.println("=".repeat(70));
        System.out.println("JAVA CHARACTER STREAMS");
        System.out.println("=".repeat(70));
        
        setupDemoDirectory();
        
        demonstrateFileReaderWriter();
        demonstrateCharacterEncoding();
        demonstrateBridgeClasses();
        demonstratePrintWriter();
        
        System.out.println("\n[CLEANUP] Removing demo files...");
        cleanupDemoDirectory();
        
        System.out.println("\n" + "=".repeat(70));
    }
    
    private static void demonstrateFileReaderWriter() {
        System.out.println("\n[1] FILEREADER AND FILEWRITER");
        System.out.println("-".repeat(70));
        
        Path filePath = Path.of(DEMO_DIR, "text.txt");
        
        try {
            // Writing with FileWriter
            System.out.println("FILEWRITER - Writing text to file:");
            try (FileWriter fw = new FileWriter(filePath.toString(), StandardCharsets.UTF_8)) {
                fw.write("Hello, World!\n");
                fw.write("This is line 2.\n");
                fw.write("Special chars: Merhaba!\n");
                System.out.println("  Written to: " + filePath.getFileName());
            }
            
            // Reading one char at a time
            System.out.println("\nFILEREADER - Reading one character at a time:");
            try (FileReader fr = new FileReader(filePath.toString(), StandardCharsets.UTF_8)) {
                int charData;
                StringBuilder sb = new StringBuilder();
                while ((charData = fr.read()) != -1) {
                    sb.append((char) charData);
                }
                System.out.println("  Content: " + sb.toString().replace("\n", "\\n"));
            }
            
            // Reading into char array
            System.out.println("\nFILEREADER - Reading into char array:");
            try (FileReader fr = new FileReader(filePath.toString(), StandardCharsets.UTF_8)) {
                char[] buffer = new char[20];
                int charsRead;
                while ((charsRead = fr.read(buffer)) != -1) {
                    String chunk = new String(buffer, 0, charsRead);
                    System.out.println("  Read " + charsRead + " chars: \"" + 
                        chunk.replace("\n", "\\n") + "\"");
                }
            }
            
            // Append mode
            System.out.println("\nFILEWRITER - Append mode:");
            try (FileWriter fw = new FileWriter(filePath.toString(), StandardCharsets.UTF_8, true)) {
                fw.write("Appended line.\n");
                System.out.println("  Appended to file successfully");
            }
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void demonstrateCharacterEncoding() {
        System.out.println("\n[2] CHARACTER ENCODING");
        System.out.println("-".repeat(70));
        
        String testData = "Hello Merhaba!";
        
        System.out.println("COMMON CHARSETS:");
        System.out.println("  StandardCharsets.UTF_8       - Unicode (recommended)");
        System.out.println("  StandardCharsets.UTF_16      - Unicode, 2+ bytes");
        System.out.println("  StandardCharsets.US_ASCII    - 7-bit ASCII only");
        System.out.println("  StandardCharsets.ISO_8859_1  - Latin-1");
        
        try {
            Path utf8File = Path.of(DEMO_DIR, "utf8.txt");
            Path utf16File = Path.of(DEMO_DIR, "utf16.txt");
            
            // Write with UTF-8
            try (FileWriter fw = new FileWriter(utf8File.toString(), StandardCharsets.UTF_8)) {
                fw.write(testData);
            }
            
            // Write with UTF-16
            try (FileWriter fw = new FileWriter(utf16File.toString(), StandardCharsets.UTF_16)) {
                fw.write(testData);
            }
            
            System.out.println("\nFILE SIZE COMPARISON for \"" + testData + "\":");
            System.out.println("  UTF-8:  " + Files.size(utf8File) + " bytes");
            System.out.println("  UTF-16: " + Files.size(utf16File) + " bytes");
            
            System.out.println("\nWARNING: Always use matching charset for read/write!");
            System.out.println("  Default charset varies by platform - specify explicitly!");
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void demonstrateBridgeClasses() {
        System.out.println("\n[3] INPUTSTREAMREADER AND OUTPUTSTREAMWRITER");
        System.out.println("-".repeat(70));
        
        Path filePath = Path.of(DEMO_DIR, "bridge.txt");
        
        System.out.println("Bridge classes connect byte and character streams:");
        System.out.println("  InputStreamReader:  InputStream -> Reader");
        System.out.println("  OutputStreamWriter: Writer -> OutputStream");
        
        try {
            // OutputStreamWriter
            System.out.println("\nOUTPUTSTREAMWRITER:");
            try (OutputStream os = new FileOutputStream(filePath.toString());
                 OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
                osw.write("Written via OutputStreamWriter\n");
                System.out.println("  getEncoding(): " + osw.getEncoding());
            }
            
            // InputStreamReader
            System.out.println("\nINPUTSTREAMREADER:");
            try (InputStream is = new FileInputStream(filePath.toString());
                 InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8)) {
                System.out.println("  getEncoding(): " + isr.getEncoding());
                char[] buffer = new char[100];
                int read = isr.read(buffer);
                System.out.println("  Content: " + new String(buffer, 0, read).replace("\n", "\\n"));
            }
            
            System.out.println("\nCOMMON PATTERN:");
            System.out.println("  BufferedReader br = new BufferedReader(");
            System.out.println("      new InputStreamReader(is, StandardCharsets.UTF_8));");
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void demonstratePrintWriter() {
        System.out.println("\n[4] PRINTWRITER - Formatted Output");
        System.out.println("-".repeat(70));
        
        Path filePath = Path.of(DEMO_DIR, "formatted.txt");
        
        try {
            System.out.println("PRINTWRITER METHODS:");
            try (PrintWriter pw = new PrintWriter(filePath.toString(), StandardCharsets.UTF_8)) {
                pw.print("Hello");
                pw.println(" World");
                pw.println(42);
                pw.printf("Integer: %d%n", 100);
                pw.printf("Float: %.3f%n", 2.718);
                pw.printf("Padded: |%10s|%-10s|%n", "right", "left");
            }
            
            System.out.println("FILE CONTENT:");
            System.out.println("  " + Files.readString(filePath).replace("\n", "\n  "));
            
            System.out.println("\nNOTE: PrintWriter does NOT throw IOException!");
            System.out.println("  Check pw.checkError() for errors");
            
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
