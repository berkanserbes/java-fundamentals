package com.fundamentals.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.time.Instant;
import java.util.stream.Stream;

/**
 * ============================================================================
 * JAVA NIO.2 PATH AND FILES - Modern File Operations
 * ============================================================================
 * 
 * Introduced in Java 7, the java.nio.file package provides modern, more 
 * powerful alternatives to the traditional java.io.File class.
 * 
 * KEY CLASSES:
 * ------------
 * 1. Path (Interface)
 *    - Represents a file or directory path
 *    - Immutable and platform-independent
 *    - More powerful than java.io.File
 * 
 * 2. Paths (Utility Class)
 *    - Factory class for creating Path instances
 *    - Paths.get(String...) creates Path objects
 * 
 * 3. Files (Utility Class)
 *    - Contains static methods for file operations
 *    - Reading, writing, copying, moving, deleting files
 *    - Creating files and directories
 *    - File attributes and permissions
 * 
 * ADVANTAGES OVER java.io.File:
 * -----------------------------
 * - Better exception handling (IOException with details)
 * - More file system operations (copy, move with options)
 * - Support for symbolic links
 * - Better directory traversal (walk, find)
 * - Easier file attribute access
 * - Watch service for file system events
 * 
 * PATH vs FILE:
 * -------------
 * - Path is an interface, File is a class
 * - Path is immutable, File is mutable
 * - Path supports more operations
 * - Convert between them: path.toFile(), file.toPath()
 * 
 * ============================================================================
 */
public class PathAndFiles {
    
    private static final String BASE_DIR = "nio_demo";
    
    public static void demonstrate() {
        System.out.println();
        System.out.println("=".repeat(70));
        System.out.println("JAVA NIO.2 - PATH AND FILES");
        System.out.println("=".repeat(70));
        
        // Setup demo directory
        setupDemoDirectory();
        
        demonstratePathCreation();
        demonstratePathMethods();
        demonstrateFilesUtility();
        demonstrateFileOperations();
        demonstrateFileAttributes();
        demonstrateDirectoryTraversal();
        
        // Cleanup
        System.out.println("\n[CLEANUP] Removing demo files...");
        cleanupDemoDirectory();
        System.out.println("Demo files removed.");
        
        System.out.println("\n" + "=".repeat(70));
    }
    
    /**
     * Demonstrates different ways to create Path objects.
     */
    private static void demonstratePathCreation() {
        System.out.println("\n[1] CREATING PATH OBJECTS");
        System.out.println("-".repeat(70));
        
        // Method 1: Using Paths.get() with string
        Path path1 = Paths.get("documents/report.txt");
        System.out.println("Paths.get(\"documents/report.txt\"):");
        System.out.println("  " + path1);
        
        // Method 2: Using Paths.get() with multiple parts
        Path path2 = Paths.get("documents", "2024", "january", "report.txt");
        System.out.println("\nPaths.get(\"documents\", \"2024\", \"january\", \"report.txt\"):");
        System.out.println("  " + path2);
        
        // Method 3: Using Path.of() (Java 11+) - preferred
        Path path3 = Path.of("documents", "notes.txt");
        System.out.println("\nPath.of(\"documents\", \"notes.txt\") [Java 11+]:");
        System.out.println("  " + path3);
        
        // Method 4: From URI
        Path path4 = Paths.get(java.net.URI.create("file:///C:/temp/file.txt"));
        System.out.println("\nPaths.get(URI.create(\"file:///C:/temp/file.txt\")):");
        System.out.println("  " + path4);
        
        // Method 5: From FileSystem
        Path path5 = FileSystems.getDefault().getPath("data", "config.json");
        System.out.println("\nFileSystems.getDefault().getPath(\"data\", \"config.json\"):");
        System.out.println("  " + path5);
        
        // Convert between Path and File
        System.out.println("\nCONVERSION BETWEEN PATH AND FILE:");
        java.io.File file = path1.toFile();
        Path pathFromFile = file.toPath();
        System.out.println("  path.toFile() -> " + file);
        System.out.println("  file.toPath() -> " + pathFromFile);
    }
    
    /**
     * Demonstrates Path interface methods.
     */
    private static void demonstratePathMethods() {
        System.out.println("\n[2] PATH METHODS");
        System.out.println("-".repeat(70));
        
        Path path = Paths.get("/users/john/documents/project/report.txt");
        
        System.out.println("Path: " + path);
        System.out.println();
        
        System.out.println("PATH COMPONENTS:");
        System.out.println("  getFileName():    " + path.getFileName());
        System.out.println("  getParent():      " + path.getParent());
        System.out.println("  getRoot():        " + path.getRoot());
        System.out.println("  getNameCount():   " + path.getNameCount());
        
        System.out.println("\nACCESSING PATH ELEMENTS:");
        for (int i = 0; i < path.getNameCount(); i++) {
            System.out.println("  getName(" + i + "): " + path.getName(i));
        }
        
        System.out.println("\nSUBPATH:");
        System.out.println("  subpath(1, 4):    " + path.subpath(1, 4));
        
        System.out.println("\nPATH PROPERTIES:");
        System.out.println("  isAbsolute():     " + path.isAbsolute());
        System.out.println("  toAbsolutePath(): " + path.toAbsolutePath());
        
        // Path resolution
        System.out.println("\nPATH RESOLUTION:");
        Path basePath = Paths.get("/users/john");
        Path relativePath = Paths.get("documents/file.txt");
        
        System.out.println("  Base path:                " + basePath);
        System.out.println("  Relative path:            " + relativePath);
        System.out.println("  resolve():                " + basePath.resolve(relativePath));
        System.out.println("  resolveSibling(\"data\"):   " + path.resolveSibling("data"));
        
        // Relativize paths
        System.out.println("\nRELATIVIZE PATHS:");
        Path path1 = Paths.get("/users/john/documents");
        Path path2 = Paths.get("/users/john/documents/project/file.txt");
        System.out.println("  From: " + path1);
        System.out.println("  To:   " + path2);
        System.out.println("  relativize(): " + path1.relativize(path2));
        
        // Normalize paths
        System.out.println("\nNORMALIZE PATHS:");
        Path messyPath = Paths.get("/users/john/../john/./documents/../documents/file.txt");
        System.out.println("  Before: " + messyPath);
        System.out.println("  After normalize(): " + messyPath.normalize());
        
        // Compare paths
        System.out.println("\nCOMPARE PATHS:");
        Path p1 = Paths.get("docs/file.txt");
        Path p2 = Paths.get("docs/file.txt");
        Path p3 = Paths.get("docs/other.txt");
        System.out.println("  p1.equals(p2): " + p1.equals(p2));
        System.out.println("  p1.equals(p3): " + p1.equals(p3));
        System.out.println("  p1.startsWith(\"docs\"): " + p1.startsWith("docs"));
        System.out.println("  p1.endsWith(\"file.txt\"): " + p1.endsWith("file.txt"));
    }
    
    /**
     * Demonstrates Files utility class methods.
     */
    private static void demonstrateFilesUtility() {
        System.out.println("\n[3] FILES UTILITY CLASS");
        System.out.println("-".repeat(70));
        
        try {
            Path dir = Paths.get(BASE_DIR);
            Path file = dir.resolve("test.txt");
            Path subdir = dir.resolve("subdir");
            
            // Create directory
            System.out.println("CREATE DIRECTORIES:");
            if (!Files.exists(dir)) {
                Files.createDirectory(dir);
                System.out.println("  Created: " + dir);
            }
            
            // Create nested directories
            Path nestedDir = dir.resolve("level1/level2/level3");
            Files.createDirectories(nestedDir);
            System.out.println("  Created nested: " + nestedDir);
            
            // Create file
            System.out.println("\nCREATE FILES:");
            if (!Files.exists(file)) {
                Files.createFile(file);
                System.out.println("  Created: " + file);
            }
            
            // Create temporary file
            Path tempFile = Files.createTempFile(dir, "temp_", ".txt");
            System.out.println("  Temp file: " + tempFile.getFileName());
            
            // Check file existence and type
            System.out.println("\nFILE CHECKS:");
            System.out.println("  exists():          " + Files.exists(file));
            System.out.println("  notExists():       " + Files.notExists(Paths.get("nonexistent.txt")));
            System.out.println("  isRegularFile():   " + Files.isRegularFile(file));
            System.out.println("  isDirectory():     " + Files.isDirectory(dir));
            System.out.println("  isReadable():      " + Files.isReadable(file));
            System.out.println("  isWritable():      " + Files.isWritable(file));
            System.out.println("  isExecutable():    " + Files.isExecutable(file));
            System.out.println("  isHidden():        " + Files.isHidden(file));
            System.out.println("  isSymbolicLink():  " + Files.isSymbolicLink(file));
            
            // File size and owner
            System.out.println("\nFILE PROPERTIES:");
            System.out.println("  size():            " + Files.size(file) + " bytes");
            System.out.println("  getLastModified(): " + Files.getLastModifiedTime(file));
            try {
                System.out.println("  getOwner():        " + Files.getOwner(file));
            } catch (UnsupportedOperationException e) {
                System.out.println("  getOwner():        Not supported on this platform");
            }
            
            // Probe content type
            System.out.println("  probeContentType():" + Files.probeContentType(file));
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates file operations: read, write, copy, move, delete.
     */
    private static void demonstrateFileOperations() {
        System.out.println("\n[4] FILE OPERATIONS");
        System.out.println("-".repeat(70));
        
        try {
            Path dir = Paths.get(BASE_DIR);
            Path sourceFile = dir.resolve("source.txt");
            Path copyFile = dir.resolve("copy.txt");
            Path movedFile = dir.resolve("subdir/moved.txt");
            
            // Create subdir if needed
            Files.createDirectories(dir.resolve("subdir"));
            
            // Write to file
            System.out.println("WRITE TO FILE:");
            String content = "Hello, NIO!\nThis is line 2.\nThis is line 3.";
            Files.writeString(sourceFile, content);
            System.out.println("  Written to: " + sourceFile.getFileName());
            
            // Write lines
            Path linesFile = dir.resolve("lines.txt");
            Files.write(linesFile, java.util.List.of("Line 1", "Line 2", "Line 3"));
            System.out.println("  Written lines to: " + linesFile.getFileName());
            
            // Read from file
            System.out.println("\nREAD FROM FILE:");
            String readContent = Files.readString(sourceFile);
            System.out.println("  Content:\n  " + readContent.replace("\n", "\n  "));
            
            // Read all lines
            System.out.println("\nREAD ALL LINES:");
            java.util.List<String> lines = Files.readAllLines(linesFile);
            for (String line : lines) {
                System.out.println("  " + line);
            }
            
            // Read all bytes
            byte[] bytes = Files.readAllBytes(sourceFile);
            System.out.println("\nREAD AS BYTES:");
            System.out.println("  Size: " + bytes.length + " bytes");
            
            // Copy file
            System.out.println("\nCOPY FILE:");
            Files.copy(sourceFile, copyFile, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("  Copied: " + sourceFile.getFileName() + " -> " + copyFile.getFileName());
            
            // Copy options explained
            System.out.println("\nCOPY OPTIONS:");
            System.out.println("  REPLACE_EXISTING  - Overwrite if target exists");
            System.out.println("  COPY_ATTRIBUTES   - Copy file attributes too");
            System.out.println("  NOFOLLOW_LINKS    - Don't follow symbolic links");
            
            // Move file
            System.out.println("\nMOVE FILE:");
            Files.move(copyFile, movedFile, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("  Moved: " + copyFile.getFileName() + " -> " + movedFile);
            
            // Move options
            System.out.println("\nMOVE OPTIONS:");
            System.out.println("  REPLACE_EXISTING  - Overwrite if target exists");
            System.out.println("  ATOMIC_MOVE       - Atomic operation (may fail)");
            
            // Delete file
            System.out.println("\nDELETE FILE:");
            Files.delete(movedFile);
            System.out.println("  Deleted: " + movedFile.getFileName());
            
            // Delete if exists (no exception if not found)
            boolean deleted = Files.deleteIfExists(Paths.get(BASE_DIR + "/nonexistent.txt"));
            System.out.println("  deleteIfExists() returned: " + deleted);
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates file attribute operations.
     */
    private static void demonstrateFileAttributes() {
        System.out.println("\n[5] FILE ATTRIBUTES");
        System.out.println("-".repeat(70));
        
        try {
            Path file = Paths.get(BASE_DIR, "source.txt");
            
            if (!Files.exists(file)) {
                Files.createFile(file);
            }
            
            // Basic attributes
            System.out.println("BASIC FILE ATTRIBUTES:");
            BasicFileAttributes attrs = Files.readAttributes(file, BasicFileAttributes.class);
            System.out.println("  creationTime():     " + attrs.creationTime());
            System.out.println("  lastModifiedTime(): " + attrs.lastModifiedTime());
            System.out.println("  lastAccessTime():   " + attrs.lastAccessTime());
            System.out.println("  size():             " + attrs.size() + " bytes");
            System.out.println("  isRegularFile():    " + attrs.isRegularFile());
            System.out.println("  isDirectory():      " + attrs.isDirectory());
            System.out.println("  isSymbolicLink():   " + attrs.isSymbolicLink());
            System.out.println("  isOther():          " + attrs.isOther());
            
            // Modify attributes
            System.out.println("\nMODIFY ATTRIBUTES:");
            FileTime newTime = FileTime.from(Instant.now().minusSeconds(86400));
            Files.setLastModifiedTime(file, newTime);
            System.out.println("  Set lastModifiedTime to: " + Files.getLastModifiedTime(file));
            
            // Get individual attributes by name
            System.out.println("\nGET ATTRIBUTE BY NAME:");
            Object creationTime = Files.getAttribute(file, "basic:creationTime");
            System.out.println("  basic:creationTime = " + creationTime);
            
            // Set attribute by name
            System.out.println("\nATTRIBUTE VIEWS:");
            System.out.println("  basic:    Basic file attributes (all platforms)");
            System.out.println("  posix:    POSIX attributes (Unix/Linux/Mac)");
            System.out.println("  dos:      DOS attributes (Windows)");
            System.out.println("  owner:    File owner attributes");
            System.out.println("  acl:      Access control lists");
            System.out.println("  user:     User-defined attributes");
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates directory traversal with Files.walk() and Files.find().
     */
    private static void demonstrateDirectoryTraversal() {
        System.out.println("\n[6] DIRECTORY TRAVERSAL");
        System.out.println("-".repeat(70));
        
        try {
            Path dir = Paths.get(BASE_DIR);
            
            // Create some test files
            Files.createDirectories(dir.resolve("docs"));
            Files.writeString(dir.resolve("docs/readme.txt"), "Readme content");
            Files.writeString(dir.resolve("docs/notes.txt"), "Notes content");
            Files.createDirectories(dir.resolve("src"));
            Files.writeString(dir.resolve("src/Main.java"), "public class Main {}");
            
            // Files.list() - Direct children only
            System.out.println("FILES.LIST() - Direct children only:");
            try (Stream<Path> stream = Files.list(dir)) {
                stream.forEach(p -> System.out.println("  " + p.getFileName()));
            }
            
            // Files.walk() - Recursive directory traversal
            System.out.println("\nFILES.WALK() - Recursive traversal:");
            try (Stream<Path> stream = Files.walk(dir)) {
                stream.forEach(p -> {
                    int depth = p.getNameCount() - dir.getNameCount();
                    String indent = "  ".repeat(depth);
                    String type = Files.isDirectory(p) ? "[D]" : "[F]";
                    System.out.println("  " + indent + type + " " + p.getFileName());
                });
            }
            
            // Files.walk() with max depth
            System.out.println("\nFILES.WALK() with maxDepth=1:");
            try (Stream<Path> stream = Files.walk(dir, 1)) {
                stream.filter(p -> !p.equals(dir))
                      .forEach(p -> System.out.println("  " + p.getFileName()));
            }
            
            // Files.find() - Find files matching criteria
            System.out.println("\nFILES.FIND() - Find .txt files:");
            try (Stream<Path> stream = Files.find(dir, Integer.MAX_VALUE,
                    (path, attrs) -> path.toString().endsWith(".txt") && attrs.isRegularFile())) {
                stream.forEach(p -> System.out.println("  " + dir.relativize(p)));
            }
            
            // Files.walkFileTree() - More control with FileVisitor
            System.out.println("\nFILES.WALKFILETREE() - with FileVisitor:");
            System.out.println("  (See FileTreeWalker class for detailed example)");
            
            // Directory stream with glob pattern
            System.out.println("\nDIRECTORY STREAM with glob pattern (*.txt):");
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir.resolve("docs"), "*.txt")) {
                for (Path entry : stream) {
                    System.out.println("  " + entry.getFileName());
                }
            }
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Sets up the demo directory.
     */
    private static void setupDemoDirectory() {
        try {
            Path dir = Paths.get(BASE_DIR);
            if (!Files.exists(dir)) {
                Files.createDirectory(dir);
            }
        } catch (IOException e) {
            System.out.println("Setup error: " + e.getMessage());
        }
    }
    
    /**
     * Cleans up demo directory.
     */
    private static void cleanupDemoDirectory() {
        try {
            Path dir = Paths.get(BASE_DIR);
            if (Files.exists(dir)) {
                try (Stream<Path> stream = Files.walk(dir)) {
                    stream.sorted(java.util.Comparator.reverseOrder())
                          .forEach(p -> {
                              try {
                                  Files.delete(p);
                              } catch (IOException e) {
                                  System.out.println("Delete failed: " + p);
                              }
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
