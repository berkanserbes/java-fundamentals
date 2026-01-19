# 20-IO - Java I/O Fundamentals

## Overview

This module provides a comprehensive guide to Java Input/Output (I/O) operations. It covers both the classic `java.io` package and the modern `java.nio` (New I/O) package introduced in Java 7.

## Topics Covered

### 1. IOIntroduction
Introduction to Java I/O concepts, stream hierarchy, and the difference between classic I/O and NIO.

### 2. FileClass
Working with `java.io.File` class for file system operations:
- Creating File objects
- File and directory creation
- File information and attributes
- Directory operations
- File permissions
- File listing with filters
- Renaming and moving files

### 3. PathAndFiles
Modern NIO.2 file operations with `Path` and `Files`:
- Creating Path objects
- Path methods and navigation
- Files utility class
- File operations (read, write, copy, move, delete)
- File attributes
- Directory traversal with walk() and find()

### 4. ByteStreams
Binary data handling with InputStream and OutputStream:
- FileInputStream and FileOutputStream
- Read and write methods
- ByteArrayInputStream and ByteArrayOutputStream
- Efficient file copying

### 5. CharacterStreams
Text data handling with Reader and Writer:
- FileReader and FileWriter
- Character encoding
- InputStreamReader and OutputStreamWriter bridge classes
- PrintWriter for formatted output

### 6. BufferedStreams
Efficient I/O with buffering:
- BufferedReader with readLine()
- BufferedWriter with newLine()
- BufferedInputStream and BufferedOutputStream
- Performance comparison
- Stream patterns

### 7. DataStreams
Reading and writing primitive types:
- DataInputStream and DataOutputStream
- All primitive type methods
- Record-like structures
- UTF string handling

### 8. ObjectStreams
Object serialization and deserialization:
- Basic serialization
- Transient keyword
- serialVersionUID
- Object graphs
- Custom serialization

### 9. RandomAccess
Random file access with RandomAccessFile:
- File pointer operations
- Seek operations
- Fixed-length records
- File modification

### 10. NIOBuffers
NIO Buffer operations:
- Buffer creation (allocate, wrap, allocateDirect)
- Buffer properties (capacity, limit, position)
- Put and get operations
- flip(), clear(), rewind(), compact()
- ByteBuffer views
- Direct vs heap buffers

### 11. NIOChannels
Channel-based I/O:
- FileChannel basics
- Channel-to-channel transfers
- Scatter/gather operations
- Memory-mapped files
- File locking

### 12. ConsoleIO
Console input and output:
- System streams (in, out, err)
- Print methods
- Formatted output (printf)
- Scanner class
- BufferedReader usage
- Console class
- Stream redirection

### 13. FileUtilities
Practical file utilities:
- Properties files
- CSV file handling
- ZIP file operations
- File searching
- Directory operations

## Key Classes Reference

### java.io Package
| Class | Purpose |
|-------|---------|
| `File` | File/directory path representation |
| `FileInputStream` | Reading bytes from file |
| `FileOutputStream` | Writing bytes to file |
| `FileReader` | Reading characters from file |
| `FileWriter` | Writing characters to file |
| `BufferedInputStream` | Buffered byte input |
| `BufferedOutputStream` | Buffered byte output |
| `BufferedReader` | Buffered character input |
| `BufferedWriter` | Buffered character output |
| `DataInputStream` | Reading primitive types |
| `DataOutputStream` | Writing primitive types |
| `ObjectInputStream` | Object deserialization |
| `ObjectOutputStream` | Object serialization |
| `PrintWriter` | Formatted text output |
| `RandomAccessFile` | Random file access |

### java.nio Package
| Class | Purpose |
|-------|---------|
| `Path` | Modern path representation |
| `Files` | File utility methods |
| `ByteBuffer` | Byte buffer for NIO |
| `FileChannel` | Channel for file I/O |
| `StandardOpenOption` | File open options |

## Running the Module

```bash
# Compile the module
cd 20-IO
mvn compile

# Run all demonstrations
mvn exec:java -Dexec.mainClass="com.fundamentals.io.IODemo"

# Run individual class
mvn exec:java -Dexec.mainClass="com.fundamentals.io.ByteStreams"
```

## Best Practices

1. **Always close streams** - Use try-with-resources
2. **Use buffered streams** - Much faster for file I/O
3. **Specify charset explicitly** - Use `StandardCharsets.UTF_8`
4. **Prefer NIO for new code** - `Files` class is more powerful
5. **Handle IOException** - Don't ignore it
6. **Use appropriate stream type** - Byte streams for binary, character streams for text

## Common Patterns

### Reading a text file
```java
List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
// or
String content = Files.readString(path);
```

### Writing a text file
```java
Files.writeString(path, content);
// or
Files.write(path, lines);
```

### Copying a file
```java
Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
```

### Reading with BufferedReader
```java
try (BufferedReader reader = Files.newBufferedReader(path)) {
    String line;
    while ((line = reader.readLine()) != null) {
        // process line
    }
}
```

## External Resources

- [Java I/O Tutorial (Oracle)](https://docs.oracle.com/javase/tutorial/essential/io/)
- [NIO.2 File Operations](https://docs.oracle.com/javase/tutorial/essential/io/fileio.html)
- [Java API: java.io](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/io/package-summary.html)
- [Java API: java.nio.file](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/nio/file/package-summary.html)
