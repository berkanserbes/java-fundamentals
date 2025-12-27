package com.fundamentals.packages.utils;

/**
 * StringUtils - String işlemleri için yardımcı sınıf
 * 
 * Bu sınıf 'utils' package'inde bulunur.
 * Static metodlar içerir.
 */
public class StringUtils {

    // Private constructor - utility class instantiate edilmemeli
    private StringUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * String'i tersine çevirir
     */
    public static String reverse(String str) {
        if (str == null) {
            return null;
        }
        return new StringBuilder(str).reverse().toString();
    }

    /**
     * String'in palindrome olup olmadığını kontrol eder
     */
    public static boolean isPalindrome(String str) {
        if (str == null) {
            return false;
        }
        String reversed = reverse(str);
        return str.equalsIgnoreCase(reversed);
    }

    /**
     * String'in boş olup olmadığını kontrol eder
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * İlk harfi büyük yapar
     */
    public static String capitalize(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
