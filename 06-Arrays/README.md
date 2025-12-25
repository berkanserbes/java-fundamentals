# Module 06: Arrays

## What You'll Learn

This module covers comprehensive array concepts in Java:
- Array basics and fundamentals
- Array operations and algorithms
- Multidimensional arrays
- Arrays utility class methods
- Best practices and common pitfalls

## Learning Objectives

By the end of this module, you will:
- Understand array declaration and initialization
- Master array operations (searching, sorting, copying)
- Work with multidimensional and jagged arrays
- Use Arrays utility class effectively
- Implement common array algorithms
- Avoid array-related pitfalls

## Classes

1. **ArrayBasics** - Fundamental array concepts
   - Declaration and initialization
   - Accessing and modifying elements
   - Iteration methods
   - Length property
   - Common pitfalls (IndexOutOfBounds, NullPointer)

2. **ArrayOperations** - Operations and algorithms
   - Searching (linear, binary)
   - Sorting (built-in and custom)
   - Copying and cloning
   - Comparing arrays
   - Reversing and rotating
   - Math operations (sum, average, min, max)
   - Advanced operations (merge, remove duplicates)

3. **MultidimensionalArrays** - 2D and 3D arrays
   - 2D array creation and manipulation
   - Jagged arrays (different row lengths)
   - Matrix operations (transpose, diagonal sum)
   - 3D arrays
   - Deep copy vs shallow copy

4. **ArraysUtility** - java.util.Arrays class
   - Sorting methods (sort, parallelSort)
   - Searching (binarySearch)
   - Comparing (equals, compare, mismatch)
   - Copying (copyOf, copyOfRange)
   - Filling (fill, setAll)
   - Converting (toString, asList)
   - Streaming operations

## Running the Classes

```bash
.\run-module.bat 06-Arrays ArrayBasics
.\run-module.bat 06-Arrays ArrayOperations
.\run-module.bat 06-Arrays MultidimensionalArrays
.\run-module.bat 06-Arrays ArraysUtility
```

## Key Concepts

### Array Characteristics
- **Fixed size**: Cannot be changed after creation
- **Homogeneous**: All elements same type
- **Zero-indexed**: First element at index 0
- **Reference type**: Stored in heap
- **Default values**: 0 for numbers, false for boolean, null for objects

### Array Declaration
```java
// Preferred
int[] numbers = {1, 2, 3};
int[] arr = new int[5];

// C-style (not recommended)
int numbers[] = {1, 2, 3};
```

### Iteration Methods
```java
// Traditional for loop (when you need index)
for (int i = 0; i < arr.length; i++) {
    System.out.println(arr[i]);
}

// Enhanced for loop (when index not needed)
for (int num : arr) {
    System.out.println(num);
}
```

### Multidimensional Arrays
```java
// 2D array
int[][] matrix = new int[3][4];  // 3 rows, 4 columns

// Jagged array
int[][] jagged = new int[3][];
jagged[0] = new int[2];
jagged[1] = new int[4];
jagged[2] = new int[3];
```

## Best Practices

### General
1. Always check bounds before accessing
2. Check for null before using array
3. Use enhanced for loop when index not needed
4. Remember: arrays are fixed size
5. Use clone() or Arrays.copyOf() to copy
6. Prefer `type[]` over `type[]` syntax

### Arrays Utility Class
1. Use `Arrays.toString()` for debugging
2. Use `Arrays.equals()` to compare arrays
3. Use `Arrays.copyOf()` instead of manual copying
4. Use `Arrays.fill()` to initialize with same value
5. Use `Arrays.stream()` for functional operations
6. Use `parallelSort()` for large arrays
7. Always sort before using `binarySearch()`

### Multidimensional Arrays
1. Check row lengths for jagged arrays
2. Use deep copy for multidimensional arrays
3. Be careful with bounds in nested loops
4. Consider using ArrayList for dynamic 2D structures

## Common Pitfalls

1. **ArrayIndexOutOfBoundsException**
   ```java
   int[] arr = {1, 2, 3};
   int x = arr[5];  // ERROR: Index 5 doesn't exist
   ```

2. **NullPointerException**
   ```java
   int[] arr = null;
   int len = arr.length;  // ERROR: null array
   ```

3. **Arrays are reference types**
   ```java
   int[] arr1 = {1, 2, 3};
   int[] arr2 = arr1;  // Same array!
   arr2[0] = 999;
   // arr1[0] is also 999
   ```

4. **Shallow copy for 2D arrays**
   ```java
   int[][] matrix = {{1, 2}, {3, 4}};
   int[][] copy = matrix.clone();  // Shallow copy!
   copy[0][0] = 999;
   // matrix[0][0] is also 999
   ```

5. **Using == to compare arrays**
   ```java
   int[] arr1 = {1, 2, 3};
   int[] arr2 = {1, 2, 3};
   arr1 == arr2;  // false (different objects)
   Arrays.equals(arr1, arr2);  // true (same content)
   ```

6. **Binary search on unsorted array**
   ```java
   int[] arr = {5, 2, 8, 1};
   Arrays.binarySearch(arr, 5);  // Undefined result!
   // Must sort first
   ```

7. **Modifying array returned by asList()**
   ```java
   List<String> list = Arrays.asList("A", "B", "C");
   list.add("D");  // ERROR: Fixed-size list
   ```

## Performance Notes

- **Linear search**: O(n)
- **Binary search**: O(log n) - requires sorted array
- **Arrays.sort()**: O(n log n) - dual-pivot quicksort
- **parallelSort()**: Faster for large arrays (>8192 elements)
- **Array access**: O(1) - constant time

## Resources

- [Java Arrays - Oracle Docs](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/arrays.html)
- [Arrays Class - Oracle Docs](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Arrays.html)
- [Java Arrays - Jenkov](https://jenkov.com/tutorials/java/arrays.html)
- [Java Arrays - Programiz](https://www.programiz.com/java-programming/arrays)
- [Multidimensional Arrays - Baeldung](https://www.baeldung.com/java-jagged-arrays)
