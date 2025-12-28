package com.fundamentals.exceptionhandling;

import java.io.*;
import java.util.*;

/**
 * Exception Handling Best Practices
 * 
 * ============================================================================
 * EN İYİ UYGULAMALAR:
 * ============================================================================
 * 
 * ✓ DO (Yapılması Gerekenler):
 * 1. Spesifik exception'ları yakala
 * 2. Exception mesajlarını açıklayıcı yap
 * 3. finally veya try-with-resources kullan
 * 4. Exception'ları logla
 * 5. Checked exception'ları dokümante et
 * 6. Custom exception'lar oluştur
 * 7. Exception chain'i koru
 * 
 * ✗ DON'T (Yapılmaması Gerekenler):
 * 1. Boş catch bloğu kullanma
 * 2. Exception.printStackTrace() kullanma (production'da)
 * 3. Generic Exception yakalama (mümkünse)
 * 4. Exception'ları yutma
 * 5. finally'de exception fırlatma
 * 6. Exception'ı kontrol akışı için kullanma
 * 
 * ============================================================================
 */
public class ExceptionBestPractices {

    public static void main(String[] args) {
        System.out.println("=== Exception Best Practices ===\n");

        demonstrateSpecificExceptions();
        demonstrateDescriptiveMessages();
        demonstrateResourceManagement();
        demonstrateLogging();
        demonstrateExceptionWrapping();
        demonstrateCommonMistakes();
        demonstrateRealWorldExample();
    }

    /**
     * 1. Spesifik Exception'ları Yakala
     */
    private static void demonstrateSpecificExceptions() {
        System.out.println("--- 1. Spesifik Exception'lar ---\n");

        // ❌ KÖTÜ: Genel exception yakalama
        System.out.println("❌ KÖTÜ Pratik:");
        try {
            int[] arr = { 1, 2, 3 };
            System.out.println(arr[10]);
        } catch (Exception e) {
            System.out.println("  Bir hata oluştu"); // Çok genel!
        }

        // ✓ İYİ: Spesifik exception yakalama
        System.out.println("\n✓ İYİ Pratik:");
        try {
            int[] arr = { 1, 2, 3 };
            System.out.println(arr[10]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("  Dizi indeksi geçersiz: " + e.getMessage());
        }

        System.out.println();
    }

    /**
     * 2. Açıklayıcı Hata Mesajları
     */
    private static void demonstrateDescriptiveMessages() {
        System.out.println("--- 2. Açıklayıcı Mesajlar ---\n");

        // ❌ KÖTÜ: Belirsiz mesaj
        System.out.println("❌ KÖTÜ Pratik:");
        try {
            validateInput("");
        } catch (IllegalArgumentException e) {
            System.out.println("  " + e.getMessage());
        }

        // ✓ İYİ: Açıklayıcı mesaj
        System.out.println("\n✓ İYİ Pratik:");
        try {
            validateInputGood("");
        } catch (IllegalArgumentException e) {
            System.out.println("  " + e.getMessage());
        }

        System.out.println();
    }

    private static void validateInput(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Hata"); // Çok belirsiz!
        }
    }

    private static void validateInputGood(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(
                    "Kullanıcı girişi boş olamaz. Lütfen geçerli bir değer girin.");
        }
    }

    /**
     * 3. Kaynak Yönetimi
     */
    private static void demonstrateResourceManagement() {
        System.out.println("--- 3. Kaynak Yönetimi ---\n");

        // ❌ KÖTÜ: Manuel kaynak yönetimi
        System.out.println("❌ KÖTÜ Pratik (Manuel kapatma):");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new StringReader("Test data"));
            String line = reader.readLine();
            System.out.println("  Okunan: " + line);
        } catch (IOException e) {
            System.out.println("  Hata: " + e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println("  Kapatma hatası!");
            }
        }

        // ✓ İYİ: try-with-resources
        System.out.println("\n✓ İYİ Pratik (try-with-resources):");
        try (BufferedReader autoReader = new BufferedReader(new StringReader("Test data"))) {
            String line = autoReader.readLine();
            System.out.println("  Okunan: " + line);
            System.out.println("  Kaynak otomatik kapatılacak!");
        } catch (IOException e) {
            System.out.println("  Hata: " + e.getMessage());
        }

        System.out.println();
    }

    /**
     * 4. Exception Loglama
     */
    private static void demonstrateLogging() {
        System.out.println("--- 4. Exception Loglama ---\n");

        // ❌ KÖTÜ: printStackTrace kullanımı
        System.out.println("❌ KÖTÜ Pratik:");
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            // e.printStackTrace(); // Production'da kullanma!
            System.out.println("  printStackTrace() kullanıldı (kötü!)");
        }

        // ✓ İYİ: Düzgün loglama
        System.out.println("\n✓ İYİ Pratik:");
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            logError("Aritmetik hata oluştu", e);
        }

        System.out.println();
    }

    private static void logError(String message, Exception e) {
        // Gerçek uygulamada: logger.error(message, e);
        System.out.println("  [ERROR] " + message);
        System.out.println("  Exception: " + e.getClass().getSimpleName());
        System.out.println("  Message: " + e.getMessage());
    }

    /**
     * 5. Exception Wrapping (Sarmalama)
     */
    private static void demonstrateExceptionWrapping() {
        System.out.println("--- 5. Exception Wrapping ---\n");

        // ❌ KÖTÜ: Orijinal exception'ı kaybetme
        System.out.println("❌ KÖTÜ Pratik:");
        try {
            lowLevelOperationBad();
        } catch (Exception e) {
            System.out.println("  Hata: " + e.getMessage());
            System.out.println("  Orijinal hata kayboldu!");
        }

        // ✓ İYİ: Exception chain'i koruma
        System.out.println("\n✓ İYİ Pratik:");
        try {
            lowLevelOperationGood();
        } catch (Exception e) {
            System.out.println("  Hata: " + e.getMessage());
            System.out.println("  Neden: " + e.getCause().getMessage());
        }

        System.out.println();
    }

    private static void lowLevelOperationBad() throws Exception {
        try {
            throw new IOException("Dosya bulunamadı");
        } catch (IOException e) {
            throw new Exception("İşlem başarısız"); // Orijinal hata kayboldu!
        }
    }

    private static void lowLevelOperationGood() throws Exception {
        try {
            throw new IOException("Dosya bulunamadı");
        } catch (IOException e) {
            throw new Exception("İşlem başarısız", e); // Orijinal hata korundu!
        }
    }

    /**
     * 6. Yaygın Hatalar
     */
    private static void demonstrateCommonMistakes() {
        System.out.println("--- 6. Yaygın Hatalar ---\n");

        // ❌ HATA 1: Boş catch bloğu
        System.out.println("❌ HATA 1: Boş catch bloğu");
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            // Boş! Exception yutuldu!
        }
        System.out.println("  Exception yutuldu, hata gizlendi!");

        // ❌ HATA 2: Exception'ı kontrol akışı için kullanma
        System.out.println("\n❌ HATA 2: Exception'ı kontrol akışı için kullanma");
        try {
            String text = null;
            if (text.isEmpty()) { // NullPointerException
                System.out.println("Boş");
            }
        } catch (NullPointerException e) {
            System.out.println("  Null kontrolü exception ile yapıldı (kötü!)");
        }

        // ✓ DOĞRU: Normal kontrol yapısı
        System.out.println("\n✓ DOĞRU: Normal kontrol yapısı");
        String text = null;
        if (text == null || text.isEmpty()) {
            System.out.println("  Null kontrolü düzgün yapıldı!");
        }

        System.out.println();
    }

    /**
     * 7. Gerçek Dünya Örneği
     */
    private static void demonstrateRealWorldExample() {
        System.out.println("--- 7. Gerçek Dünya Örneği ---");
        System.out.println("Kullanıcı kaydı senaryosu:\n");

        UserService userService = new UserService();

        // Başarılı kayıt
        try {
            User user = userService.registerUser("ahmet@example.com", "Pass123!", 25);
            System.out.println("✓ Kullanıcı kaydedildi: " + user.getEmail());
        } catch (UserServiceException e) {
            handleUserServiceError(e);
        }

        // Başarısız kayıt - geçersiz email
        try {
            User user = userService.registerUser("invalid-email", "Pass123!", 25);
        } catch (UserServiceException e) {
            handleUserServiceError(e);
        }

        // Başarısız kayıt - zayıf şifre
        try {
            User user = userService.registerUser("ayse@example.com", "123", 25);
        } catch (UserServiceException e) {
            handleUserServiceError(e);
        }

        System.out.println("\nExercise completed!");
    }

    private static void handleUserServiceError(UserServiceException e) {
        System.out.println("❌ Kayıt Hatası:");
        System.out.println("  Kod: " + e.getErrorCode());
        System.out.println("  Mesaj: " + e.getMessage());
        if (e.getCause() != null) {
            System.out.println("  Detay: " + e.getCause().getMessage());
        }
    }
}

// ============================================================================
// GERÇEK DÜNYA ÖRNEĞİ - USER SERVICE
// ============================================================================

/**
 * Kullanıcı Servisi
 */
class UserService {

    public User registerUser(String email, String password, int age) throws UserServiceException {
        try {
            // Email validasyonu
            validateEmail(email);

            // Şifre validasyonu
            validatePassword(password);

            // Yaş validasyonu
            validateAge(age);

            // Kullanıcı oluştur
            return new User(email, age);

        } catch (BPValidationException e) {
            throw new UserServiceException("USR001", "Kullanıcı kaydı başarısız", e);
        } catch (Exception e) {
            throw new UserServiceException("USR999", "Beklenmeyen hata", e);
        }
    }

    private void validateEmail(String email) throws BPValidationException {
        if (email == null || !email.contains("@")) {
            throw new BPValidationException("Geçersiz email adresi: " + email);
        }
    }

    private void validatePassword(String password) throws BPValidationException {
        if (password == null || password.length() < 6) {
            throw new BPValidationException("Şifre en az 6 karakter olmalı");
        }
    }

    private void validateAge(int age) throws BPValidationException {
        if (age < 18) {
            throw new BPValidationException("Yaş 18'den küçük olamaz");
        }
    }
}

/**
 * Kullanıcı Sınıfı
 */
class User {
    private String email;
    private int age;

    public User(String email, int age) {
        this.email = email;
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }
}

/**
 * User Service Exception
 */
class UserServiceException extends Exception {
    private final String errorCode;

    public UserServiceException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}

/**
 * BP Validation Exception (Best Practices için)
 */
class BPValidationException extends Exception {
    public BPValidationException(String message) {
        super(message);
    }
}
