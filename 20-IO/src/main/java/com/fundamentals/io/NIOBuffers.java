package com.fundamentals.io;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;

/**
 * ============================================================================
 * JAVA NIO BUFFERS - Buffer-Oriented I/O
 * ============================================================================
 * 
 * Buffers are containers for data in NIO. They hold data to be read or written.
 * 
 * BUFFER TYPES:
 * -------------
 * ByteBuffer   - Stores bytes (most common, versatile)
 * CharBuffer   - Stores characters (16-bit)
 * ShortBuffer  - Stores shorts (16-bit)
 * IntBuffer    - Stores ints (32-bit)
 * LongBuffer   - Stores longs (64-bit)
 * FloatBuffer  - Stores floats (32-bit)
 * DoubleBuffer - Stores doubles (64-bit)
 * 
 * KEY PROPERTIES:
 * ---------------
 * capacity - Maximum number of elements (fixed)
 * limit    - First element that should not be read/written
 * position - Next element to be read/written
 * mark     - Saved position for reset
 * 
 * Invariant: 0 <= mark <= position <= limit <= capacity
 * 
 * KEY OPERATIONS:
 * ---------------
 * put()     - Write data to buffer, advances position
 * get()     - Read data from buffer, advances position
 * flip()    - Prepare for reading (limit=position, position=0)
 * clear()   - Prepare for writing (position=0, limit=capacity)
 * rewind()  - Re-read buffer (position=0, limit unchanged)
 * compact() - Shift unread data to beginning
 * 
 * BUFFER CREATION:
 * ----------------
 * allocate()      - Creates a heap buffer
 * allocateDirect() - Creates a direct buffer (faster for I/O)
 * wrap()          - Wraps an existing array
 * 
 * DIRECT vs HEAP BUFFERS:
 * -----------------------
 * Heap Buffer:   Stored in JVM heap, GC managed
 * Direct Buffer: Stored in native memory, faster I/O but slower allocation
 * 
 * ============================================================================
 */
public class NIOBuffers {
    
    public static void demonstrate() {
        System.out.println();
        System.out.println("=".repeat(70));
        System.out.println("JAVA NIO BUFFERS");
        System.out.println("=".repeat(70));
        
        demonstrateBufferCreation();
        demonstrateBufferProperties();
        demonstrateBufferOperations();
        demonstrateFlipAndClear();
        demonstrateByteBufferViews();
        demonstrateDirectBuffers();
    }
    
    /**
     * Demonstrates different ways to create buffers.
     */
    private static void demonstrateBufferCreation() {
        System.out.println("\n[1] BUFFER CREATION");
        System.out.println("-".repeat(70));
        
        // Method 1: allocate() - creates empty buffer
        System.out.println("METHOD 1 - allocate():");
        ByteBuffer bb1 = ByteBuffer.allocate(10);
        System.out.println("  ByteBuffer.allocate(10)");
        System.out.println("  Capacity: " + bb1.capacity());
        
        // Method 2: wrap() - wraps existing array
        System.out.println("\nMETHOD 2 - wrap():");
        byte[] array = {1, 2, 3, 4, 5};
        ByteBuffer bb2 = ByteBuffer.wrap(array);
        System.out.println("  ByteBuffer.wrap(byte[]{1,2,3,4,5})");
        System.out.println("  Capacity: " + bb2.capacity());
        System.out.println("  First byte: " + bb2.get(0));
        
        // Method 3: allocateDirect() - direct memory
        System.out.println("\nMETHOD 3 - allocateDirect():");
        ByteBuffer bb3 = ByteBuffer.allocateDirect(10);
        System.out.println("  ByteBuffer.allocateDirect(10)");
        System.out.println("  isDirect(): " + bb3.isDirect());
        
        // Other buffer types
        System.out.println("\nOTHER BUFFER TYPES:");
        IntBuffer intBuf = IntBuffer.allocate(5);
        CharBuffer charBuf = CharBuffer.allocate(10);
        System.out.println("  IntBuffer.allocate(5)  - capacity: " + intBuf.capacity());
        System.out.println("  CharBuffer.allocate(10) - capacity: " + charBuf.capacity());
    }
    
    /**
     * Demonstrates buffer properties: capacity, limit, position.
     */
    private static void demonstrateBufferProperties() {
        System.out.println("\n[2] BUFFER PROPERTIES");
        System.out.println("-".repeat(70));
        
        ByteBuffer buffer = ByteBuffer.allocate(10);
        
        System.out.println("INITIAL STATE:");
        printBufferState(buffer);
        
        // Write some data
        buffer.put((byte) 'H');
        buffer.put((byte) 'e');
        buffer.put((byte) 'l');
        buffer.put((byte) 'l');
        buffer.put((byte) 'o');
        
        System.out.println("\nAFTER WRITING 5 BYTES:");
        printBufferState(buffer);
        
        // Set limit manually
        buffer.limit(8);
        System.out.println("\nAFTER limit(8):");
        printBufferState(buffer);
        
        System.out.println("\nPROPERTY INVARIANT:");
        System.out.println("  0 <= mark <= position <= limit <= capacity");
    }
    
    /**
     * Demonstrates put and get operations.
     */
    private static void demonstrateBufferOperations() {
        System.out.println("\n[3] PUT AND GET OPERATIONS");
        System.out.println("-".repeat(70));
        
        IntBuffer buffer = IntBuffer.allocate(5);
        
        // Relative put (uses position)
        System.out.println("RELATIVE PUT (advances position):");
        buffer.put(10);
        buffer.put(20);
        buffer.put(30);
        System.out.println("  put(10), put(20), put(30)");
        System.out.println("  Position: " + buffer.position());
        
        // Absolute put (specific index)
        System.out.println("\nABSOLUTE PUT (specific index):");
        buffer.put(0, 100);  // Doesn't change position
        System.out.println("  put(0, 100) - replaced first element");
        System.out.println("  Position still: " + buffer.position());
        
        // Prepare for reading
        buffer.flip();
        
        // Relative get
        System.out.println("\nRELATIVE GET (advances position):");
        System.out.println("  get(): " + buffer.get());
        System.out.println("  get(): " + buffer.get());
        System.out.println("  Position: " + buffer.position());
        
        // Absolute get
        System.out.println("\nABSOLUTE GET (specific index):");
        System.out.println("  get(2): " + buffer.get(2));
        System.out.println("  Position still: " + buffer.position());
        
        // Bulk operations
        System.out.println("\nBULK OPERATIONS:");
        IntBuffer src = IntBuffer.wrap(new int[]{1, 2, 3, 4, 5});
        IntBuffer dst = IntBuffer.allocate(10);
        
        dst.put(src); // Bulk put
        System.out.println("  dst.put(src) - copied " + dst.position() + " elements");
    }
    
    /**
     * Demonstrates flip, clear, and rewind operations.
     */
    private static void demonstrateFlipAndClear() {
        System.out.println("\n[4] FLIP, CLEAR, REWIND, COMPACT");
        System.out.println("-".repeat(70));
        
        ByteBuffer buffer = ByteBuffer.allocate(10);
        
        // Write data
        buffer.put("Hello".getBytes());
        System.out.println("After writing 'Hello':");
        printBufferState(buffer);
        
        // FLIP - prepare for reading
        System.out.println("\nAFTER flip():");
        System.out.println("  Sets limit = position, position = 0");
        buffer.flip();
        printBufferState(buffer);
        
        // Read some
        byte[] read = new byte[3];
        buffer.get(read);
        System.out.println("\nAfter reading 3 bytes: \"" + new String(read) + "\"");
        printBufferState(buffer);
        
        // REWIND - re-read from beginning
        System.out.println("\nAFTER rewind():");
        System.out.println("  Sets position = 0, limit unchanged");
        buffer.rewind();
        printBufferState(buffer);
        
        // COMPACT - shift remaining to front
        buffer.get(read);
        System.out.println("\nAfter reading 3 bytes again:");
        printBufferState(buffer);
        
        System.out.println("\nAFTER compact():");
        System.out.println("  Copies remaining to front, prepares for writing");
        buffer.compact();
        printBufferState(buffer);
        
        // CLEAR - reset for writing
        System.out.println("\nAFTER clear():");
        System.out.println("  Sets position = 0, limit = capacity");
        buffer.clear();
        printBufferState(buffer);
        
        // Summary
        System.out.println("\nOPERATION SUMMARY:");
        System.out.println("  flip()    - Write mode -> Read mode");
        System.out.println("  clear()   - Reset for writing (data not erased)");
        System.out.println("  rewind()  - Re-read from beginning");
        System.out.println("  compact() - Keep unread, prepare for append");
    }
    
    /**
     * Demonstrates ByteBuffer views for different data types.
     */
    private static void demonstrateByteBufferViews() {
        System.out.println("\n[5] BYTEBUFFER VIEWS");
        System.out.println("-".repeat(70));
        
        ByteBuffer bb = ByteBuffer.allocate(16);
        
        // Write different types
        System.out.println("WRITING DIFFERENT TYPES:");
        bb.putInt(42);        // 4 bytes
        bb.putDouble(3.14);   // 8 bytes
        bb.putChar('X');      // 2 bytes
        bb.putShort((short) 100); // 2 bytes
        
        System.out.println("  putInt(42)");
        System.out.println("  putDouble(3.14)");
        System.out.println("  putChar('X')");
        System.out.println("  putShort(100)");
        System.out.println("  Total: " + bb.position() + " bytes");
        
        // Read back
        bb.flip();
        System.out.println("\nREADING BACK:");
        System.out.println("  getInt():    " + bb.getInt());
        System.out.println("  getDouble(): " + bb.getDouble());
        System.out.println("  getChar():   " + bb.getChar());
        System.out.println("  getShort():  " + bb.getShort());
        
        // View as IntBuffer
        System.out.println("\nVIEW AS INTBUFFER:");
        ByteBuffer intBytes = ByteBuffer.allocate(20);
        intBytes.putInt(1).putInt(2).putInt(3).putInt(4).putInt(5);
        intBytes.flip();
        
        IntBuffer intView = intBytes.asIntBuffer();
        System.out.println("  Capacity: " + intView.capacity());
        System.out.print("  Values: ");
        while (intView.hasRemaining()) {
            System.out.print(intView.get() + " ");
        }
        System.out.println();
        
        // String encoding/decoding
        System.out.println("\nSTRING ENCODING:");
        String text = "Hello NIO!";
        ByteBuffer encoded = StandardCharsets.UTF_8.encode(text);
        System.out.println("  Encoded: " + encoded.remaining() + " bytes");
        
        CharBuffer decoded = StandardCharsets.UTF_8.decode(encoded);
        System.out.println("  Decoded: \"" + decoded + "\"");
    }
    
    /**
     * Demonstrates direct vs heap buffers.
     */
    private static void demonstrateDirectBuffers() {
        System.out.println("\n[6] DIRECT vs HEAP BUFFERS");
        System.out.println("-".repeat(70));
        
        ByteBuffer heapBuffer = ByteBuffer.allocate(1024);
        ByteBuffer directBuffer = ByteBuffer.allocateDirect(1024);
        
        System.out.println("HEAP BUFFER:");
        System.out.println("  isDirect(): " + heapBuffer.isDirect());
        System.out.println("  hasArray(): " + heapBuffer.hasArray());
        
        System.out.println("\nDIRECT BUFFER:");
        System.out.println("  isDirect(): " + directBuffer.isDirect());
        System.out.println("  hasArray(): " + directBuffer.hasArray());
        
        System.out.println("\nCOMPARISON:");
        System.out.println("  +------------------+------------------+------------------+");
        System.out.println("  | Feature          | Heap Buffer      | Direct Buffer    |");
        System.out.println("  +------------------+------------------+------------------+");
        System.out.println("  | Location         | JVM Heap         | Native Memory    |");
        System.out.println("  | Allocation       | Fast             | Slow             |");
        System.out.println("  | I/O Performance  | Slower           | Faster           |");
        System.out.println("  | GC Impact        | Managed by GC    | Less GC impact   |");
        System.out.println("  | hasArray()       | true             | false            |");
        System.out.println("  +------------------+------------------+------------------+");
        
        System.out.println("\nWHEN TO USE DIRECT BUFFERS:");
        System.out.println("  - Large buffers used for I/O operations");
        System.out.println("  - Long-lived buffers (avoid allocation cost)");
        System.out.println("  - Network or file I/O");
        
        System.out.println("\nWHEN TO USE HEAP BUFFERS:");
        System.out.println("  - Small, short-lived buffers");
        System.out.println("  - When array access is needed");
        System.out.println("  - Simple data processing");
        
        System.out.println("\n" + "=".repeat(70));
    }
    
    private static void printBufferState(ByteBuffer buffer) {
        System.out.println("  [position=" + buffer.position() + 
            ", limit=" + buffer.limit() + 
            ", capacity=" + buffer.capacity() + 
            ", remaining=" + buffer.remaining() + "]");
    }
    
    public static void main(String[] args) {
        demonstrate();
    }
}
