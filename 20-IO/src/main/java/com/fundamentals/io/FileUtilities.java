package com.fundamentals.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.zip.*;

/**
 * ============================================================================
 * PRACTICAL FILE UTILITIES - Real-World File Operations
 * ============================================================================
 * 
 * This class demonstrates practical, real-world file operations commonly used
 * in Java applications. These utilities cover common tasks that developers
 * frequently need to implement.
 * 
 * TOPICS COVERED:
 * ---------------
 * 1. Properties Files    - Reading/writing configuration files
 * 2. CSV Files           - Parsing and writing CSV data
 * 3. ZIP Files           - Creating and extracting archives
 * 4. File Searching      - Finding files by name/pattern
 * 5. Directory Operations- Recursive copy, delete, size
 * 6. File Comparison     - Comparing file contents
 * 
 * ============================================================================
 */
public class FileUtilities {
    
    private static final String DEMO_DIR = "utilities_demo";
    
    public static void demonstrate() {
        System.out.println();
        System.out.println("=".repeat(70));
        System.out.println("PRACTICAL FILE UTILITIES");
        System.out.println("=".repeat(70));
        
        setupDemoDirectory();
        
        demonstratePropertiesFiles();
        demonstrateCSVHandling();
        demonstrateZIPOperations();
        demonstrateFileSearching();
        demonstrateDirectoryOperations();
        
        System.out.println("\n[CLEANUP] Removing demo files...");
        cleanupDemoDirectory();
        
        System.out.println("\n" + "=".repeat(70));
    }
    
    /**
     * Demonstrates Properties file handling.
     */
    private static void demonstratePropertiesFiles() {
        System.out.println("\n[1] PROPERTIES FILES");
        System.out.println("-".repeat(70));
        
        Path propsPath = Path.of(DEMO_DIR, "config.properties");
        
        try {
            // Writing properties
            System.out.println("WRITING PROPERTIES:");
            Properties props = new Properties();
            props.setProperty("app.name", "MyApplication");
            props.setProperty("app.version", "1.0.0");
            props.setProperty("database.host", "localhost");
            props.setProperty("database.port", "5432");
            props.setProperty("debug.enabled", "true");
            
            try (OutputStream os = Files.newOutputStream(propsPath)) {
                props.store(os, "Application Configuration");
            }
            System.out.println("  Written to: " + propsPath.getFileName());
            
            // Show file content
            System.out.println("\nFILE CONTENT:");
            Files.readAllLines(propsPath).forEach(line -> 
                System.out.println("  " + line));
            
            // Reading properties
            System.out.println("\nREADING PROPERTIES:");
            Properties loaded = new Properties();
            try (InputStream is = Files.newInputStream(propsPath)) {
                loaded.load(is);
            }
            
            System.out.println("  app.name:       " + loaded.getProperty("app.name"));
            System.out.println("  app.version:    " + loaded.getProperty("app.version"));
            System.out.println("  database.host:  " + loaded.getProperty("database.host"));
            System.out.println("  missing.key:    " + loaded.getProperty("missing.key", "default"));
            
            // Iterate all properties
            System.out.println("\nALL PROPERTIES:");
            loaded.forEach((key, value) -> 
                System.out.println("    " + key + " = " + value));
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates CSV file handling.
     */
    private static void demonstrateCSVHandling() {
        System.out.println("\n[2] CSV FILE HANDLING");
        System.out.println("-".repeat(70));
        
        Path csvPath = Path.of(DEMO_DIR, "data.csv");
        
        try {
            // Writing CSV
            System.out.println("WRITING CSV:");
            List<String[]> records = Arrays.asList(
                new String[]{"Name", "Age", "City"},
                new String[]{"Alice", "30", "New York"},
                new String[]{"Bob", "25", "Los Angeles"},
                new String[]{"Carol", "35", "Chicago"}
            );
            
            try (PrintWriter pw = new PrintWriter(Files.newBufferedWriter(csvPath))) {
                for (String[] record : records) {
                    pw.println(String.join(",", record));
                }
            }
            System.out.println("  Written " + records.size() + " records");
            
            // Show file content
            System.out.println("\nFILE CONTENT:");
            Files.readAllLines(csvPath).forEach(line -> 
                System.out.println("  " + line));
            
            // Reading CSV
            System.out.println("\nREADING CSV:");
            try (BufferedReader br = Files.newBufferedReader(csvPath)) {
                String header = br.readLine();
                String[] columns = header.split(",");
                System.out.println("  Columns: " + Arrays.toString(columns));
                
                String line;
                System.out.println("  Data:");
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    System.out.printf("    %s is %s years old from %s%n", 
                        values[0], values[1], values[2]);
                }
            }
            
            System.out.println("\nNOTE: For complex CSV (quotes, escaping), use a library");
            System.out.println("  - Apache Commons CSV");
            System.out.println("  - OpenCSV");
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates ZIP file operations.
     */
    private static void demonstrateZIPOperations() {
        System.out.println("\n[3] ZIP FILE OPERATIONS");
        System.out.println("-".repeat(70));
        
        Path zipPath = Path.of(DEMO_DIR, "archive.zip");
        Path extractDir = Path.of(DEMO_DIR, "extracted");
        
        try {
            // Create some files to zip
            Path file1 = Path.of(DEMO_DIR, "file1.txt");
            Path file2 = Path.of(DEMO_DIR, "file2.txt");
            Files.writeString(file1, "Content of file 1");
            Files.writeString(file2, "Content of file 2");
            
            // Creating ZIP file
            System.out.println("CREATING ZIP FILE:");
            try (ZipOutputStream zos = new ZipOutputStream(
                    Files.newOutputStream(zipPath))) {
                
                // Add file1
                zos.putNextEntry(new ZipEntry("file1.txt"));
                Files.copy(file1, zos);
                zos.closeEntry();
                
                // Add file2 in a subdirectory
                zos.putNextEntry(new ZipEntry("subdir/file2.txt"));
                Files.copy(file2, zos);
                zos.closeEntry();
                
                // Add data directly
                zos.putNextEntry(new ZipEntry("generated.txt"));
                zos.write("Generated content".getBytes(StandardCharsets.UTF_8));
                zos.closeEntry();
            }
            System.out.println("  Created: " + zipPath.getFileName());
            System.out.println("  Size: " + Files.size(zipPath) + " bytes");
            
            // Listing ZIP contents
            System.out.println("\nZIP CONTENTS:");
            try (ZipInputStream zis = new ZipInputStream(
                    Files.newInputStream(zipPath))) {
                ZipEntry entry;
                while ((entry = zis.getNextEntry()) != null) {
                    System.out.println("  " + entry.getName() + 
                        " (" + entry.getSize() + " bytes)");
                }
            }
            
            // Extracting ZIP
            System.out.println("\nEXTRACTING ZIP:");
            Files.createDirectories(extractDir);
            try (ZipInputStream zis = new ZipInputStream(
                    Files.newInputStream(zipPath))) {
                ZipEntry entry;
                while ((entry = zis.getNextEntry()) != null) {
                    Path destPath = extractDir.resolve(entry.getName());
                    
                    if (entry.isDirectory()) {
                        Files.createDirectories(destPath);
                    } else {
                        Files.createDirectories(destPath.getParent());
                        Files.copy(zis, destPath, StandardCopyOption.REPLACE_EXISTING);
                        System.out.println("  Extracted: " + entry.getName());
                    }
                }
            }
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates file searching.
     */
    private static void demonstrateFileSearching() {
        System.out.println("\n[4] FILE SEARCHING");
        System.out.println("-".repeat(70));
        
        try {
            // Create test files
            Path subdir = Path.of(DEMO_DIR, "search_test");
            Files.createDirectories(subdir);
            Files.writeString(subdir.resolve("readme.txt"), "readme");
            Files.writeString(subdir.resolve("notes.txt"), "notes");
            Files.writeString(subdir.resolve("data.csv"), "data");
            Files.writeString(subdir.resolve("image.png"), "fake image");
            
            Path dir = Path.of(DEMO_DIR);
            
            // Find by extension using Files.walk
            System.out.println("FIND *.txt FILES:");
            try (var stream = Files.walk(dir)) {
                stream.filter(p -> p.toString().endsWith(".txt"))
                      .forEach(p -> System.out.println("  " + dir.relativize(p)));
            }
            
            // Find by name pattern using PathMatcher
            System.out.println("\nFIND FILES MATCHING glob:**/*.txt:");
            PathMatcher matcher = FileSystems.getDefault()
                .getPathMatcher("glob:**/*.txt");
            
            try (var stream = Files.walk(dir)) {
                stream.filter(p -> matcher.matches(p) && Files.isRegularFile(p))
                      .forEach(p -> System.out.println("  " + dir.relativize(p)));
            }
            
            // Find by size
            System.out.println("\nFIND FILES > 0 BYTES:");
            try (var stream = Files.walk(dir)) {
                stream.filter(Files::isRegularFile)
                      .filter(p -> {
                          try { return Files.size(p) > 0; }
                          catch (IOException e) { return false; }
                      })
                      .limit(5)
                      .forEach(p -> System.out.println("  " + dir.relativize(p)));
            }
            
            // Using Files.find()
            System.out.println("\nUSING Files.find():");
            try (var stream = Files.find(dir, Integer.MAX_VALUE,
                    (path, attrs) -> attrs.isRegularFile() && 
                                     path.toString().endsWith(".csv"))) {
                stream.forEach(p -> System.out.println("  " + dir.relativize(p)));
            }
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates directory operations.
     */
    private static void demonstrateDirectoryOperations() {
        System.out.println("\n[5] DIRECTORY OPERATIONS");
        System.out.println("-".repeat(70));
        
        try {
            Path sourceDir = Path.of(DEMO_DIR, "source_dir");
            Path destDir = Path.of(DEMO_DIR, "dest_dir");
            
            // Create source directory with content
            Files.createDirectories(sourceDir.resolve("subdir"));
            Files.writeString(sourceDir.resolve("file1.txt"), "File 1 content");
            Files.writeString(sourceDir.resolve("file2.txt"), "File 2 content");
            Files.writeString(sourceDir.resolve("subdir/file3.txt"), "File 3 content");
            
            // Calculate directory size
            System.out.println("CALCULATE DIRECTORY SIZE:");
            try (var stream = Files.walk(sourceDir)) {
                long size = stream.filter(Files::isRegularFile)
                                  .mapToLong(p -> {
                                      try { return Files.size(p); }
                                      catch (IOException e) { return 0; }
                                  })
                                  .sum();
                System.out.println("  " + sourceDir.getFileName() + ": " + size + " bytes");
            }
            
            // Count files
            System.out.println("\nCOUNT FILES:");
            try (var stream = Files.walk(sourceDir)) {
                long count = stream.filter(Files::isRegularFile).count();
                System.out.println("  Total files: " + count);
            }
            
            // Recursive copy
            System.out.println("\nRECURSIVE COPY:");
            copyDirectory(sourceDir, destDir);
            System.out.println("  Copied " + sourceDir.getFileName() + 
                " to " + destDir.getFileName());
            
            // Verify copy
            try (var stream = Files.walk(destDir)) {
                long count = stream.filter(Files::isRegularFile).count();
                System.out.println("  Destination file count: " + count);
            }
            
            // Recursive delete
            System.out.println("\nRECURSIVE DELETE:");
            deleteDirectory(destDir);
            System.out.println("  Deleted: " + destDir.getFileName());
            System.out.println("  Exists: " + Files.exists(destDir));
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    // Helper methods
    
    private static void copyDirectory(Path source, Path target) throws IOException {
        try (var stream = Files.walk(source)) {
            stream.forEach(sourcePath -> {
                try {
                    Path targetPath = target.resolve(source.relativize(sourcePath));
                    if (Files.isDirectory(sourcePath)) {
                        Files.createDirectories(targetPath);
                    } else {
                        Files.copy(sourcePath, targetPath, 
                            StandardCopyOption.REPLACE_EXISTING);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
    
    private static void deleteDirectory(Path dir) throws IOException {
        if (Files.exists(dir)) {
            try (var stream = Files.walk(dir)) {
                stream.sorted(Comparator.reverseOrder())
                      .forEach(p -> {
                          try { Files.delete(p); }
                          catch (IOException ignored) {}
                      });
            }
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
            deleteDirectory(Path.of(DEMO_DIR));
        } catch (IOException e) {
            System.out.println("Cleanup error: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        demonstrate();
    }
}
