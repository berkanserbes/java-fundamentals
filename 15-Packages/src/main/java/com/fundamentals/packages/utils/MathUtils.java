package com.fundamentals.packages.utils;

/**
 * MathUtils - Matematiksel işlemler için yardımcı sınıf
 * 
 * Bu sınıf 'utils' package'inde bulunur.
 * Static metodlar içerir.
 */
public class MathUtils {

    // Private constructor - utility class instantiate edilmemeli
    private MathUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Verilen sayıların en büyüğünü bulur
     */
    public static int max(int... numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("En az bir sayı gerekli");
        }
        
        int max = numbers[0];
        for (int num : numbers) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    /**
     * Verilen sayıların en küçüğünü bulur
     */
    public static int min(int... numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("En az bir sayı gerekli");
        }
        
        int min = numbers[0];
        for (int num : numbers) {
            if (num < min) {
                min = num;
            }
        }
        return min;
    }

    /**
     * Sayının çift olup olmadığını kontrol eder
     */
    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    /**
     * Sayının tek olup olmadığını kontrol eder
     */
    public static boolean isOdd(int number) {
        return !isEven(number);
    }

    /**
     * Sayının asal olup olmadığını kontrol eder
     */
    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
