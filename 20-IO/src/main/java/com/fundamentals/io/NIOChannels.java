package com.fundamentals.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.*;

/**
 * ============================================================================
 * JAVA NIO CHANNELS - Channel-Based I/O
 * ============================================================================
 * 
 * Channels are the primary medium for data transfer in NIO. They represent
 * connections to I/O devices (files, sockets, etc.) and transfer data
 * to/from Buffers.
 * 
 * KEY CHANNEL TYPES:
 * ------------------
 * FileChannel         - Reading/writing files
 * SocketChannel       - TCP client connections
 * ServerSocketChannel - TCP server listening
 * DatagramChannel     - UDP datagram communication
 * Pipe.SinkChannel    - Write end of a pipe
 * Pipe.SourceChannel  - Read end of a pipe
 * 
 * CHANNELS vs STREAMS:
 * --------------------
 * Streams:   Unidirectional, blocking, one byte at a time
 * Channels:  Bidirectional, can be non-blocking, buffer-oriented
 * 
 * KEY FEATURES:
 * -------------
 * - Always read/write through Buffers
 * - Support scatter/gather operations
 * - Can be non-blocking (except FileChannel)
 * - Support memory-mapped files
 * - Interruptible (via Thread.interrupt())
 * 
 * FILECHANNEL SPECIAL FEATURES:
 * -----------------------------
 * - Memory-mapped I/O
 * - File locking
 * - Direct channel-to-channel transfers
 * - Position-based read/write
 * 
 * ============================================================================
 */
public class NIOChannels {
    
    private static final String DEMO_DIR = "channel_demo";
    
    public static void demonstrate() {
        System.out.println();
        System.out.println("=".repeat(70));
        System.out.println("JAVA NIO CHANNELS");
        System.out.println("=".repeat(70));
        
        setupDemoDirectory();
        
        demonstrateFileChannel();
        demonstrateChannelTransfers();
        demonstrateScatterGather();
        demonstrateMemoryMappedFile();
        demonstrateFileLocking();
        
        System.out.println("\n[CLEANUP] Removing demo files...");
        cleanupDemoDirectory();
        
        System.out.println("\n" + "=".repeat(70));
    }
    
    /**
     * Demonstrates basic FileChannel operations.
     */
    private static void demonstrateFileChannel() {
        System.out.println("\n[1] FILECHANNEL BASICS");
        System.out.println("-".repeat(70));
        
        Path filePath = Path.of(DEMO_DIR, "channel.txt");
        
        try {
            // Method 1: From FileOutputStream
            System.out.println("WRITING WITH FILECHANNEL:");
            try (FileOutputStream fos = new FileOutputStream(filePath.toString());
                 FileChannel channel = fos.getChannel()) {
                
                String data = "Hello Channel World!";
                ByteBuffer buffer = ByteBuffer.wrap(data.getBytes());
                
                int bytesWritten = channel.write(buffer);
                System.out.println("  Written " + bytesWritten + " bytes");
                System.out.println("  Channel position: " + channel.position());
                System.out.println("  Channel size: " + channel.size());
            }
            
            // Method 2: From FileInputStream
            System.out.println("\nREADING WITH FILECHANNEL:");
            try (FileInputStream fis = new FileInputStream(filePath.toString());
                 FileChannel channel = fis.getChannel()) {
                
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int bytesRead = channel.read(buffer);
                
                buffer.flip();
                byte[] bytes = new byte[buffer.remaining()];
                buffer.get(bytes);
                
                System.out.println("  Read " + bytesRead + " bytes");
                System.out.println("  Content: " + new String(bytes));
            }
            
            // Method 3: Using FileChannel.open() (Java 7+)
            System.out.println("\nUSING FileChannel.open():");
            try (FileChannel channel = FileChannel.open(filePath,
                    StandardOpenOption.READ, StandardOpenOption.WRITE)) {
                
                System.out.println("  Opened for read/write");
                System.out.println("  Size: " + channel.size());
                System.out.println("  Position: " + channel.position());
                
                // Position-based read
                ByteBuffer buffer = ByteBuffer.allocate(5);
                int read = channel.read(buffer, 6); // Read from position 6
                buffer.flip();
                System.out.println("  Read from position 6: " + 
                    new String(buffer.array(), 0, read));
            }
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates channel-to-channel transfers.
     */
    private static void demonstrateChannelTransfers() {
        System.out.println("\n[2] CHANNEL TRANSFERS");
        System.out.println("-".repeat(70));
        
        Path sourcePath = Path.of(DEMO_DIR, "source.txt");
        Path destPath1 = Path.of(DEMO_DIR, "dest1.txt");
        Path destPath2 = Path.of(DEMO_DIR, "dest2.txt");
        
        try {
            // Create source file
            Files.writeString(sourcePath, "This is the source file content for transfer.");
            
            // Method 1: transferTo()
            System.out.println("METHOD 1 - transferTo():");
            try (FileChannel source = FileChannel.open(sourcePath, StandardOpenOption.READ);
                 FileChannel dest = FileChannel.open(destPath1, 
                     StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
                
                long transferred = source.transferTo(0, source.size(), dest);
                System.out.println("  Transferred " + transferred + " bytes");
            }
            
            // Method 2: transferFrom()
            System.out.println("\nMETHOD 2 - transferFrom():");
            try (FileChannel source = FileChannel.open(sourcePath, StandardOpenOption.READ);
                 FileChannel dest = FileChannel.open(destPath2,
                     StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
                
                long transferred = dest.transferFrom(source, 0, source.size());
                System.out.println("  Transferred " + transferred + " bytes");
            }
            
            // Verify
            System.out.println("\nVERIFICATION:");
            System.out.println("  dest1.txt: " + Files.size(destPath1) + " bytes");
            System.out.println("  dest2.txt: " + Files.size(destPath2) + " bytes");
            
            System.out.println("\nADVANTAGES OF CHANNEL TRANSFER:");
            System.out.println("  - Direct kernel-level copy (no user-space buffering)");
            System.out.println("  - More efficient than read-then-write");
            System.out.println("  - Can use DMA (Direct Memory Access) if available");
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates scatter/gather operations.
     */
    private static void demonstrateScatterGather() {
        System.out.println("\n[3] SCATTER/GATHER OPERATIONS");
        System.out.println("-".repeat(70));
        
        Path filePath = Path.of(DEMO_DIR, "scatter.txt");
        
        try {
            // GATHER - Write from multiple buffers
            System.out.println("GATHER (write from multiple buffers):");
            try (FileChannel channel = FileChannel.open(filePath,
                    StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
                
                ByteBuffer header = ByteBuffer.wrap("HEADER:".getBytes());
                ByteBuffer body = ByteBuffer.wrap("This is the body content.".getBytes());
                ByteBuffer footer = ByteBuffer.wrap(":FOOTER".getBytes());
                
                ByteBuffer[] buffers = {header, body, footer};
                long written = channel.write(buffers);
                
                System.out.println("  Gathered from 3 buffers");
                System.out.println("  Total written: " + written + " bytes");
            }
            
            System.out.println("  Content: " + Files.readString(filePath));
            
            // SCATTER - Read into multiple buffers
            System.out.println("\nSCATTER (read into multiple buffers):");
            try (FileChannel channel = FileChannel.open(filePath, StandardOpenOption.READ)) {
                
                ByteBuffer buf1 = ByteBuffer.allocate(7);  // "HEADER:"
                ByteBuffer buf2 = ByteBuffer.allocate(26); // body
                ByteBuffer buf3 = ByteBuffer.allocate(7);  // ":FOOTER"
                
                ByteBuffer[] buffers = {buf1, buf2, buf3};
                long read = channel.read(buffers);
                
                System.out.println("  Scattered into 3 buffers");
                System.out.println("  Total read: " + read + " bytes");
                
                buf1.flip();
                buf2.flip();
                buf3.flip();
                
                System.out.println("  Buffer 1: " + new String(buf1.array(), 0, buf1.limit()));
                System.out.println("  Buffer 2: " + new String(buf2.array(), 0, buf2.limit()));
                System.out.println("  Buffer 3: " + new String(buf3.array(), 0, buf3.limit()));
            }
            
            System.out.println("\nUSE CASES:");
            System.out.println("  - Message protocols with fixed-size headers");
            System.out.println("  - Separating metadata from content");
            System.out.println("  - Efficient multi-part file formats");
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates memory-mapped files.
     */
    private static void demonstrateMemoryMappedFile() {
        System.out.println("\n[4] MEMORY-MAPPED FILES");
        System.out.println("-".repeat(70));
        
        Path filePath = Path.of(DEMO_DIR, "mapped.dat");
        
        try {
            // Create a file with some content
            int size = 1024;
            Files.write(filePath, new byte[size]);
            
            System.out.println("MEMORY-MAPPED FILE:");
            try (FileChannel channel = FileChannel.open(filePath,
                    StandardOpenOption.READ, StandardOpenOption.WRITE)) {
                
                // Map the file into memory
                java.nio.MappedByteBuffer mappedBuffer = channel.map(
                    FileChannel.MapMode.READ_WRITE, 0, size);
                
                System.out.println("  Mapped " + size + " bytes into memory");
                System.out.println("  isDirect(): " + mappedBuffer.isDirect());
                
                // Write directly to memory (changes reflected in file)
                mappedBuffer.put("Hello Memory Mapped!".getBytes());
                
                // Read from memory
                mappedBuffer.position(0);
                byte[] read = new byte[20];
                mappedBuffer.get(read);
                System.out.println("  Written and read: " + new String(read));
                
                // Force changes to disk
                mappedBuffer.force();
                System.out.println("  Changes forced to disk");
            }
            
            // Verify by reading normally
            byte[] content = Files.readAllBytes(filePath);
            System.out.println("  File start: " + new String(content, 0, 20));
            
            System.out.println("\nMAPPING MODES:");
            System.out.println("  READ_ONLY    - Read-only mapping");
            System.out.println("  READ_WRITE   - Read and write mapping");
            System.out.println("  PRIVATE      - Copy-on-write mapping");
            
            System.out.println("\nBENEFITS:");
            System.out.println("  - Very fast for repeated access");
            System.out.println("  - OS handles caching automatically");
            System.out.println("  - Good for large files");
            System.out.println("  - Random access without seeking");
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates file locking.
     */
    private static void demonstrateFileLocking() {
        System.out.println("\n[5] FILE LOCKING");
        System.out.println("-".repeat(70));
        
        Path filePath = Path.of(DEMO_DIR, "locked.txt");
        
        try {
            Files.writeString(filePath, "This file will be locked.");
            
            System.out.println("FILE LOCKING DEMONSTRATION:");
            try (FileChannel channel = FileChannel.open(filePath,
                    StandardOpenOption.READ, StandardOpenOption.WRITE)) {
                
                // Acquire exclusive lock
                System.out.println("\nACQUIRING EXCLUSIVE LOCK:");
                FileLock lock = channel.lock();
                
                System.out.println("  Lock acquired!");
                System.out.println("  isShared(): " + lock.isShared());
                System.out.println("  isValid(): " + lock.isValid());
                System.out.println("  position(): " + lock.position());
                System.out.println("  size(): " + lock.size());
                
                // Do work while locked
                System.out.println("  (File is now locked for exclusive access)");
                
                // Release lock
                lock.release();
                System.out.println("\n  Lock released");
            }
            
            System.out.println("\nLOCK TYPES:");
            System.out.println("  Exclusive Lock: channel.lock()");
            System.out.println("    - Only one process can hold");
            System.out.println("    - For writing");
            System.out.println();
            System.out.println("  Shared Lock: channel.lock(pos, size, true)");
            System.out.println("    - Multiple processes can hold");
            System.out.println("    - For reading");
            
            System.out.println("\nNON-BLOCKING LOCK:");
            System.out.println("  FileLock lock = channel.tryLock();");
            System.out.println("  Returns null if lock not available");
            
            System.out.println("\nIMPORTANT NOTES:");
            System.out.println("  - Locks are advisory on some systems");
            System.out.println("  - Lock validity tied to channel lifetime");
            System.out.println("  - Always release locks in finally block");
            
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
