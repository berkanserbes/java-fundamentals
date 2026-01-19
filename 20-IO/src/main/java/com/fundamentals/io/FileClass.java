package com.fundamentals.io;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * ============================================================================
 * JAVA FILE CLASS - java.io.File
 * ============================================================================
 * 
 * The File class represents a file or directory pathname in an abstract way.
 * It provides methods to work with the file system without actually reading 
 * or writing file contents.
 * 
 * KEY POINTS:
 * -----------
 * 1. File is an abstract representation - the file may not exist
 * 2. File can represent both files and directories
 * 3. File operations are platform-independent (path separators handled)
 * 4. File is immutable - path cannot be changed after creation
 * 
 * WHAT FILE CAN DO:
 * -----------------
 * - Check if a file/directory exists
 * - Create new files or directories
 * - Delete files or directories
 * - List contents of directories
 * - Get file attributes (size, modified date, etc.)
 * - Check permissions (readable, writable, executable)
 * - Rename or move files
 * 
 * WHAT FILE CANNOT DO:
 * --------------------
 * - Read file contents (use FileInputStream, FileReader)
 * - Write to files (use FileOutputStream, FileWriter)
 * - Copy files (use Files.copy() from NIO)
 * 
 * NOTE: Since Java 7, java.nio.file.Path and java.nio.file.Files are 
 * preferred for more functionality and better error handling.
 * 
 * ============================================================================
 */
public class FileClass {
    
    // Base directory for demo files
    private static final String BASE_DIR = "io_demo";
    
    public static void demonstrate() {
        System.out.println();
        System.out.println("=".repeat(70));
        System.out.println("JAVA FILE CLASS (java.io.File)");
        System.out.println("=".repeat(70));
        
        // Clean up from previous runs
        cleanupDemoDirectory();
        
        demonstrateCreatingFileObjects();
        demonstrateFileCreation();
        demonstrateFileInformation();
        demonstrateDirectoryOperations();
        demonstrateFilePermissions();
        demonstrateFileListing();
        demonstrateFileRenaming();
        
        // Clean up after demo
        System.out.println("\n[CLEANUP] Removing demo files...");
        cleanupDemoDirectory();
        System.out.println("Demo files removed.");
        
        System.out.println("\n" + "=".repeat(70));
    }
    
    /**
     * Demonstrates different ways to create File objects.
     * Creating a File object does NOT create an actual file.
     */
    private static void demonstrateCreatingFileObjects() {
        System.out.println("\n[1] CREATING FILE OBJECTS");
        System.out.println("-".repeat(70));
        
        // Method 1: Using pathname string
        File file1 = new File("document.txt");
        System.out.println("Method 1 - Pathname string:");
        System.out.println("  new File(\"document.txt\")");
        System.out.println("  Path: " + file1.getPath());
        
        // Method 2: Using parent directory and child name
        File file2 = new File(BASE_DIR, "notes.txt");
        System.out.println("\nMethod 2 - Parent and child:");
        System.out.println("  new File(\"" + BASE_DIR + "\", \"notes.txt\")");
        System.out.println("  Path: " + file2.getPath());
        
        // Method 3: Using parent File object and child name
        File parentDir = new File(BASE_DIR);
        File file3 = new File(parentDir, "data.csv");
        System.out.println("\nMethod 3 - Parent File object:");
        System.out.println("  File parent = new File(\"" + BASE_DIR + "\")");
        System.out.println("  new File(parent, \"data.csv\")");
        System.out.println("  Path: " + file3.getPath());
        
        // Path separator - platform independent
        System.out.println("\n[!] PLATFORM-INDEPENDENT PATH SEPARATORS:");
        System.out.println("  File.separator: \"" + File.separator + "\"");
        System.out.println("  File.pathSeparator: \"" + File.pathSeparator + "\" (for PATH env)");
        
        // Use forward slashes - Java converts them automatically
        File file4 = new File(BASE_DIR + "/subdir/file.txt");
        System.out.println("\n  Tip: Forward slashes work on all platforms");
        System.out.println("  new File(\"" + BASE_DIR + "/subdir/file.txt\")");
    }
    
    /**
     * Demonstrates creating files and directories.
     */
    private static void demonstrateFileCreation() {
        System.out.println("\n[2] CREATING FILES AND DIRECTORIES");
        System.out.println("-".repeat(70));
        
        try {
            // Create a single directory
            File singleDir = new File(BASE_DIR);
            boolean created = singleDir.mkdir();
            System.out.println("mkdir() - Create single directory:");
            System.out.println("  directory: " + singleDir.getPath());
            System.out.println("  created: " + created);
            
            // Create nested directories
            File nestedDirs = new File(BASE_DIR + "/level1/level2/level3");
            boolean nestedCreated = nestedDirs.mkdirs();
            System.out.println("\nmkdirs() - Create nested directories:");
            System.out.println("  directories: " + nestedDirs.getPath());
            System.out.println("  created: " + nestedCreated);
            
            // Create a new file
            File newFile = new File(BASE_DIR + "/sample.txt");
            boolean fileCreated = newFile.createNewFile();
            System.out.println("\ncreateNewFile() - Create new empty file:");
            System.out.println("  file: " + newFile.getPath());
            System.out.println("  created: " + fileCreated);
            
            // Try to create the same file again
            boolean duplicateCreated = newFile.createNewFile();
            System.out.println("\n  Creating same file again: " + duplicateCreated);
            System.out.println("  (false = file already exists)");
            
            // Create temporary file
            File tempFile = File.createTempFile("myapp_", ".tmp");
            System.out.println("\ncreateTempFile() - Create temporary file:");
            System.out.println("  file: " + tempFile.getAbsolutePath());
            System.out.println("  Delete on exit with: tempFile.deleteOnExit()");
            tempFile.deleteOnExit(); // Will be deleted when JVM exits
            
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates getting file information and attributes.
     */
    private static void demonstrateFileInformation() {
        System.out.println("\n[3] FILE INFORMATION AND ATTRIBUTES");
        System.out.println("-".repeat(70));
        
        File file = new File(BASE_DIR + "/sample.txt");
        
        System.out.println("FILE PATH METHODS:");
        System.out.println("  getPath():          " + file.getPath());
        System.out.println("  getAbsolutePath():  " + file.getAbsolutePath());
        System.out.println("  getName():          " + file.getName());
        System.out.println("  getParent():        " + file.getParent());
        
        try {
            System.out.println("  getCanonicalPath(): " + file.getCanonicalPath());
        } catch (IOException e) {
            System.out.println("  getCanonicalPath(): Error - " + e.getMessage());
        }
        
        System.out.println("\nFILE STATUS METHODS:");
        System.out.println("  exists():       " + file.exists());
        System.out.println("  isFile():       " + file.isFile());
        System.out.println("  isDirectory():  " + file.isDirectory());
        System.out.println("  isHidden():     " + file.isHidden());
        System.out.println("  isAbsolute():   " + file.isAbsolute());
        
        System.out.println("\nFILE SIZE AND DATES:");
        System.out.println("  length():       " + file.length() + " bytes");
        System.out.println("  lastModified(): " + new Date(file.lastModified()));
        
        // Modify the last modified date
        long newTime = System.currentTimeMillis() - 86400000; // 1 day ago
        file.setLastModified(newTime);
        System.out.println("  After setLastModified(): " + new Date(file.lastModified()));
    }
    
    /**
     * Demonstrates directory-specific operations.
     */
    private static void demonstrateDirectoryOperations() {
        System.out.println("\n[4] DIRECTORY OPERATIONS");
        System.out.println("-".repeat(70));
        
        File dir = new File(BASE_DIR);
        
        System.out.println("Directory: " + dir.getAbsolutePath());
        System.out.println("  Is directory: " + dir.isDirectory());
        System.out.println("  Total space:  " + formatBytes(dir.getTotalSpace()));
        System.out.println("  Free space:   " + formatBytes(dir.getFreeSpace()));
        System.out.println("  Usable space: " + formatBytes(dir.getUsableSpace()));
        
        // List system roots (drives on Windows, "/" on Unix)
        System.out.println("\nSYSTEM ROOTS (File.listRoots()):");
        File[] roots = File.listRoots();
        for (File root : roots) {
            System.out.println("  " + root.getAbsolutePath() + 
                " - Free: " + formatBytes(root.getFreeSpace()));
        }
    }
    
    /**
     * Demonstrates file permission methods.
     */
    private static void demonstrateFilePermissions() {
        System.out.println("\n[5] FILE PERMISSIONS");
        System.out.println("-".repeat(70));
        
        File file = new File(BASE_DIR + "/sample.txt");
        
        System.out.println("File: " + file.getName());
        System.out.println("\nCHECK PERMISSIONS:");
        System.out.println("  canRead():    " + file.canRead());
        System.out.println("  canWrite():   " + file.canWrite());
        System.out.println("  canExecute(): " + file.canExecute());
        
        System.out.println("\nMODIFY PERMISSIONS:");
        
        // Make file read-only
        boolean success = file.setWritable(false);
        System.out.println("  setWritable(false): " + success);
        System.out.println("  canWrite() now: " + file.canWrite());
        
        // Restore write permission
        success = file.setWritable(true);
        System.out.println("  setWritable(true): " + success);
        System.out.println("  canWrite() now: " + file.canWrite());
        
        System.out.println("\nPERMISSION METHODS SUMMARY:");
        System.out.println("  setReadable(boolean readable)");
        System.out.println("  setWritable(boolean writable)");
        System.out.println("  setExecutable(boolean executable)");
        System.out.println("  setReadOnly() - makes file read-only");
    }
    
    /**
     * Demonstrates listing directory contents.
     */
    private static void demonstrateFileListing() {
        System.out.println("\n[6] LISTING DIRECTORY CONTENTS");
        System.out.println("-".repeat(70));
        
        // Create some files for demonstration
        try {
            new File(BASE_DIR + "/document1.txt").createNewFile();
            new File(BASE_DIR + "/document2.txt").createNewFile();
            new File(BASE_DIR + "/image.png").createNewFile();
            new File(BASE_DIR + "/data.csv").createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating demo files: " + e.getMessage());
        }
        
        File dir = new File(BASE_DIR);
        
        // Method 1: list() - returns String array
        System.out.println("list() - Returns String array of names:");
        String[] names = dir.list();
        if (names != null) {
            for (String name : names) {
                System.out.println("  " + name);
            }
        }
        
        // Method 2: listFiles() - returns File array
        System.out.println("\nlistFiles() - Returns File array:");
        File[] files = dir.listFiles();
        if (files != null) {
            for (File f : files) {
                String type = f.isDirectory() ? "[DIR]" : "[FILE]";
                System.out.println("  " + type + " " + f.getName());
            }
        }
        
        // Method 3: list with filter - only .txt files
        System.out.println("\nlist() with FilenameFilter (*.txt only):");
        String[] txtFiles = dir.list((directory, name) -> name.endsWith(".txt"));
        if (txtFiles != null) {
            for (String name : txtFiles) {
                System.out.println("  " + name);
            }
        }
        
        // Method 4: listFiles with FileFilter
        System.out.println("\nlistFiles() with FileFilter (directories only):");
        File[] directories = dir.listFiles(File::isDirectory);
        if (directories != null) {
            for (File d : directories) {
                System.out.println("  " + d.getName() + "/");
            }
        }
    }
    
    /**
     * Demonstrates renaming and moving files.
     */
    private static void demonstrateFileRenaming() {
        System.out.println("\n[7] RENAMING AND MOVING FILES");
        System.out.println("-".repeat(70));
        
        File original = new File(BASE_DIR + "/document1.txt");
        File renamed = new File(BASE_DIR + "/renamed_document.txt");
        
        System.out.println("Original file: " + original.getName());
        System.out.println("  exists: " + original.exists());
        
        // Rename file
        boolean success = original.renameTo(renamed);
        System.out.println("\nrenameTo() result: " + success);
        System.out.println("  New name: " + renamed.getName());
        System.out.println("  Old file exists: " + original.exists());
        System.out.println("  New file exists: " + renamed.exists());
        
        // Move file to subdirectory (also uses renameTo)
        File moved = new File(BASE_DIR + "/level1/moved_document.txt");
        success = renamed.renameTo(moved);
        System.out.println("\nMove to subdirectory (renameTo): " + success);
        System.out.println("  Destination: " + moved.getPath());
        System.out.println("  File exists: " + moved.exists());
        
        System.out.println("\n[!] NOTE: renameTo() may fail across different drives.");
        System.out.println("    Use Files.move() from NIO for reliable moving.");
    }
    
    /**
     * Cleans up demo files and directories.
     */
    private static void cleanupDemoDirectory() {
        File dir = new File(BASE_DIR);
        deleteRecursively(dir);
    }
    
    /**
     * Recursively deletes a file or directory.
     */
    private static void deleteRecursively(File file) {
        if (file.isDirectory()) {
            File[] children = file.listFiles();
            if (children != null) {
                for (File child : children) {
                    deleteRecursively(child);
                }
            }
        }
        file.delete();
    }
    
    /**
     * Formats bytes to human-readable string.
     */
    private static String formatBytes(long bytes) {
        if (bytes < 1024) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(1024));
        String pre = "KMGTPE".charAt(exp - 1) + "";
        return String.format("%.1f %sB", bytes / Math.pow(1024, exp), pre);
    }
    
    public static void main(String[] args) {
        demonstrate();
    }
}
