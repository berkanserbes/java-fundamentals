package com.fundamentals.arrays;

import java.util.Arrays;

/**
 * Multidimensional Arrays in Java
 * 
 * Java supports arrays of arrays (multidimensional arrays).
 * 
 * Common Types:
 * - 2D Arrays: Matrix, table, grid
 * - 3D Arrays: Cube, tensor
 * - Jagged Arrays: Arrays with different row lengths
 * 
 * Declaration:
 * type[][] arrayName; // 2D
 * type[][][] arrayName; // 3D
 * type[][]...[] arrayName; // N-dimensional
 * 
 * Memory Layout:
 * - Array of references to arrays
 * - Each row can have different length (jagged)
 * - Not stored contiguously in memory
 */
public class MultidimensionalArrays {

    public static void main(String[] args) {
        System.out.println("=== Multidimensional Arrays ===\n");

        demonstrate2DArrays();
        demonstrateJaggedArrays();
        demonstrate2DOperations();
        demonstrate3DArrays();
        demonstrateTricksAndPitfalls();
    }

    /**
     * 2D Arrays (Matrix)
     */
    private static void demonstrate2DArrays() {
        System.out.println("--- 2D Arrays ---");

        // Declaration and initialization - Method 1
        int[][] matrix1 = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };

        // Method 2: Create then assign
        int[][] matrix2 = new int[3][3];
        matrix2[0][0] = 1;
        matrix2[0][1] = 2;
        matrix2[1][0] = 3;

        // Method 3: Create with size
        int[][] matrix3 = new int[2][4]; // 2 rows, 4 columns

        System.out.println("Matrix 1:");
        print2DArray(matrix1);

        // Accessing elements
        System.out.println("\nAccessing elements:");
        System.out.println("matrix1[0][0] = " + matrix1[0][0]); // First element
        System.out.println("matrix1[1][2] = " + matrix1[1][2]); // Row 1, Col 2
        System.out.println("matrix1[2][2] = " + matrix1[2][2]); // Last element

        // Dimensions
        System.out.println("\nDimensions:");
        System.out.println("Rows: " + matrix1.length);
        System.out.println("Columns: " + matrix1[0].length);

        // Iterating
        System.out.println("\nIterating with nested loops:");
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[i].length; j++) {
                System.out.print(matrix1[i][j] + " ");
            }
            System.out.println();
        }

        // Enhanced for loop
        System.out.println("\nEnhanced for loop:");
        for (int[] row : matrix1) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    /**
     * Jagged Arrays (arrays with different row lengths)
     */
    private static void demonstrateJaggedArrays() {
        System.out.println("\n--- Jagged Arrays ---");

        // Create jagged array
        int[][] jagged = new int[3][]; // 3 rows, columns not specified
        jagged[0] = new int[2]; // Row 0: 2 columns
        jagged[1] = new int[4]; // Row 1: 4 columns
        jagged[2] = new int[3]; // Row 2: 3 columns

        // Initialize
        jagged[0][0] = 1;
        jagged[0][1] = 2;
        jagged[1][0] = 3;
        jagged[1][1] = 4;
        jagged[1][2] = 5;
        jagged[1][3] = 6;
        jagged[2][0] = 7;
        jagged[2][1] = 8;
        jagged[2][2] = 9;

        System.out.println("Jagged array:");
        print2DArray(jagged);

        // Direct initialization
        int[][] triangle = {
                { 1 },
                { 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9, 10 }
        };

        System.out.println("\nTriangle pattern:");
        print2DArray(triangle);

        // Row lengths
        System.out.println("\nRow lengths:");
        for (int i = 0; i < triangle.length; i++) {
            System.out.println("Row " + i + ": " + triangle[i].length + " elements");
        }
    }

    /**
     * 2D Array operations
     */
    private static void demonstrate2DOperations() {
        System.out.println("\n--- 2D Array Operations ---");

        int[][] matrix = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };

        // Sum of all elements
        int sum = sum2D(matrix);
        System.out.println("Sum of all elements: " + sum);

        // Sum of specific row
        int rowSum = sumRow(matrix, 1);
        System.out.println("Sum of row 1: " + rowSum);

        // Sum of specific column
        int colSum = sumColumn(matrix, 2);
        System.out.println("Sum of column 2: " + colSum);

        // Transpose
        int[][] transposed = transpose(matrix);
        System.out.println("\nOriginal matrix:");
        print2DArray(matrix);
        System.out.println("Transposed matrix:");
        print2DArray(transposed);

        // Search in 2D array
        boolean found = search2D(matrix, 5);
        System.out.println("\nSearch for 5: " + found);

        // Find max in 2D array
        int max = findMax2D(matrix);
        System.out.println("Maximum value: " + max);

        // Diagonal sum
        int diagonalSum = sumMainDiagonal(matrix);
        System.out.println("Main diagonal sum: " + diagonalSum);

        // Check if matrix is symmetric
        int[][] symmetric = {
                { 1, 2, 3 },
                { 2, 4, 5 },
                { 3, 5, 6 }
        };
        System.out.println("\nIs matrix symmetric: " + isSymmetric(symmetric));
    }

    public static int sum2D(int[][] matrix) {
        int sum = 0;
        for (int[] row : matrix) {
            for (int value : row) {
                sum += value;
            }
        }
        return sum;
    }

    public static int sumRow(int[][] matrix, int rowIndex) {
        int sum = 0;
        for (int value : matrix[rowIndex]) {
            sum += value;
        }
        return sum;
    }

    public static int sumColumn(int[][] matrix, int colIndex) {
        int sum = 0;
        for (int[] row : matrix) {
            sum += row[colIndex];
        }
        return sum;
    }

    public static int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = matrix[i][j];
            }
        }

        return result;
    }

    public static boolean search2D(int[][] matrix, int target) {
        for (int[] row : matrix) {
            for (int value : row) {
                if (value == target) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int findMax2D(int[][] matrix) {
        int max = Integer.MIN_VALUE;
        for (int[] row : matrix) {
            for (int value : row) {
                if (value > max) {
                    max = value;
                }
            }
        }
        return max;
    }

    public static int sumMainDiagonal(int[][] matrix) {
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            sum += matrix[i][i];
        }
        return sum;
    }

    public static boolean isSymmetric(int[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != matrix[j][i]) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 3D Arrays
     */
    private static void demonstrate3DArrays() {
        System.out.println("\n--- 3D Arrays ---");

        // Create 3D array (2 x 3 x 4)
        int[][][] cube = new int[2][3][4];

        // Initialize
        int value = 1;
        for (int i = 0; i < cube.length; i++) {
            for (int j = 0; j < cube[i].length; j++) {
                for (int k = 0; k < cube[i][j].length; k++) {
                    cube[i][j][k] = value++;
                }
            }
        }

        System.out.println("3D Array (2x3x4):");
        print3DArray(cube);

        // Direct initialization
        int[][][] small3D = {
                {
                        { 1, 2 },
                        { 3, 4 }
                },
                {
                        { 5, 6 },
                        { 7, 8 }
                }
        };

        System.out.println("\nSmall 3D array:");
        print3DArray(small3D);

        // Dimensions
        System.out.println("\nDimensions:");
        System.out.println("Depth: " + cube.length);
        System.out.println("Rows: " + cube[0].length);
        System.out.println("Columns: " + cube[0][0].length);

        // Sum of 3D array
        int sum3D = sum3D(cube);
        System.out.println("Sum of all elements: " + sum3D);
    }

    public static int sum3D(int[][][] array) {
        int sum = 0;
        for (int[][] matrix : array) {
            for (int[] row : matrix) {
                for (int value : row) {
                    sum += value;
                }
            }
        }
        return sum;
    }

    /**
     * Tricks and pitfalls
     */
    private static void demonstrateTricksAndPitfalls() {
        System.out.println("\n--- Tricks & Pitfalls ---");

        // PITFALL 1: Assuming rectangular array
        System.out.println("PITFALL 1: Not all 2D arrays are rectangular");
        int[][] jagged = {
                { 1, 2 },
                { 3, 4, 5 },
                { 6 }
        };
        System.out.println("Always check row length in jagged arrays");

        // PITFALL 2: Shallow copy
        System.out.println("\nPITFALL 2: Shallow copy issue");
        int[][] original = { { 1, 2 }, { 3, 4 } };
        int[][] shallowCopy = original.clone(); // Only copies references!
        shallowCopy[0][0] = 999;
        System.out.println("Original[0][0]: " + original[0][0]); // Also changed!
        System.out.println("Use deep copy for 2D arrays");

        // TRICK 1: Deep copy
        int[][] deepCopy = deepCopy2D(original);
        deepCopy[0][0] = 1;
        System.out.println("\nAfter deep copy fix:");
        System.out.println("Original[0][0]: " + original[0][0]);
        System.out.println("Deep copy[0][0]: " + deepCopy[0][0]);

        // TRICK 2: Fill 2D array
        System.out.println("\nTRICK 2: Fill 2D array with value");
        int[][] filled = new int[3][3];
        fill2D(filled, 7);
        print2DArray(filled);

        // TRICK 3: Identity matrix
        System.out.println("\nTRICK 3: Create identity matrix");
        int[][] identity = createIdentityMatrix(4);
        print2DArray(identity);

        // BEST PRACTICES
        System.out.println("\n--- Best Practices ---");
        System.out.println("1. Check row lengths for jagged arrays");
        System.out.println("2. Use deep copy for multidimensional arrays");
        System.out.println("3. Be careful with bounds in nested loops");
        System.out.println("4. Consider using ArrayList for dynamic 2D structures");
        System.out.println("5. Use enhanced for loops when index not needed");

        System.out.println("\nExercise completed!");
    }

    public static int[][] deepCopy2D(int[][] original) {
        int[][] copy = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i].clone();
        }
        return copy;
    }

    public static void fill2D(int[][] array, int value) {
        for (int[] row : array) {
            Arrays.fill(row, value);
        }
    }

    public static int[][] createIdentityMatrix(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            matrix[i][i] = 1;
        }
        return matrix;
    }

    // Helper methods
    private static void print2DArray(int[][] array) {
        for (int[] row : array) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static void print3DArray(int[][][] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println("Layer " + i + ":");
            print2DArray(array[i]);
        }
    }
}
