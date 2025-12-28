package com.fundamentals.exceptionhandling;

import java.io.IOException;

/**
 * Throw ve Throws Anahtar Kelimeleri
 * 
 * ============================================================================
 * THROW vs THROWS:
 * ============================================================================
 * 
 * THROW:
 * - Exception fırlatmak için kullanılır
 * - Metod içinde kullanılır
 * - Bir exception nesnesi alır
 * - Örnek: throw new IllegalArgumentException("Hata mesajı");
 * 
 * THROWS:
 * - Metodun hangi exception'ları fırlatabileceğini belirtir
 * - Metod imzasında kullanılır
 * - Exception sınıf ismi alır
 * - Örnek: public void method() throws IOException, SQLException
 * 
 * ============================================================================
 * KULLANIM KURALLARI:
 * ============================================================================
 * 
 * ✓ throw ile exception fırlatılır
 * ✓ throws ile exception declare edilir
 * ✓ Checked exception'lar throws ile belirtilmeli
 * ✓ Unchecked exception'lar için throws opsiyonel
 * ✓ Birden fazla exception throws edilebilir
 * 
 * ============================================================================
 */
public class ThrowAndThrows {

    public static void main(String[] args) {
        System.out.println("=== Throw ve Throws ===\n");

        demonstrateThrow();
        demonstrateThrows();
        demonstrateRethrow();
        demonstrateThrowVsThrows();
        demonstrateCheckedVsUnchecked();
    }

    /**
     * 1. throw - Exception Fırlatma
     */
    private static void demonstrateThrow() {
        System.out.println("--- 1. throw Kullanımı ---");
        System.out.println("Manuel olarak exception fırlatma:\n");

        try {
            validateAge(15);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Yakalandı: " + e.getMessage());
        }

        try {
            validateAge(25);
            System.out.println("✓ Yaş geçerli!");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Yakalandı: " + e.getMessage());
        }

        System.out.println();
    }

    /**
     * Yaş validasyonu - throw kullanımı
     */
    private static void validateAge(int age) {
        if (age < 18) {
            // Manuel exception fırlatma
            throw new IllegalArgumentException("Yaş 18'den küçük olamaz! Verilen: " + age);
        }
        System.out.println("Yaş kontrolü başarılı: " + age);
    }

    /**
     * 2. throws - Exception Bildirimi
     */
    private static void demonstrateThrows() {
        System.out.println("--- 2. throws Kullanımı ---");
        System.out.println("Metodun exception fırlatabileceğini bildirme:\n");

        try {
            readFile("data.txt");
        } catch (IOException e) {
            System.out.println("❌ Dosya okuma hatası: " + e.getMessage());
        }

        try {
            processData(null);
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println("❌ Veri işleme hatası: " + e.getMessage());
        }

        System.out.println();
    }

    /**
     * throws ile checked exception bildirimi
     */
    private static void readFile(String filename) throws IOException {
        System.out.println("Dosya okunuyor: " + filename);
        // Simüle edilmiş hata
        throw new IOException("Dosya bulunamadı: " + filename);
    }

    /**
     * throws ile birden fazla exception bildirimi
     */
    private static void processData(String data) throws NullPointerException, IllegalArgumentException {
        if (data == null) {
            throw new NullPointerException("Veri null olamaz!");
        }
        if (data.isEmpty()) {
            throw new IllegalArgumentException("Veri boş olamaz!");
        }
        System.out.println("Veri işleniyor: " + data);
    }

    /**
     * 3. Re-throw - Exception'ı Yeniden Fırlatma
     */
    private static void demonstrateRethrow() {
        System.out.println("--- 3. Re-throw (Yeniden Fırlatma) ---");
        System.out.println("Yakalanan exception'ı tekrar fırlatma:\n");

        try {
            performOperation();
        } catch (Exception e) {
            System.out.println("❌ Ana metod: " + e.getMessage());
        }

        System.out.println();
    }

    /**
     * Exception yakalayıp yeniden fırlatan metod
     */
    private static void performOperation() throws Exception {
        try {
            System.out.println("İşlem başlatılıyor...");
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("  Alt metod: Hata yakalandı");
            System.out.println("  Loglama yapılıyor...");
            // Exception'ı yeniden fırlat
            throw e; // veya: throw new Exception("İşlem başarısız", e);
        }
    }

    /**
     * 4. throw vs throws Karşılaştırması
     */
    private static void demonstrateThrowVsThrows() {
        System.out.println("--- 4. throw vs throws Karşılaştırması ---\n");

        System.out.println("throw:");
        System.out.println("  - Metod içinde kullanılır");
        System.out.println("  - Exception nesnesi fırlatır");
        System.out.println("  - Örnek: throw new Exception();");

        System.out.println("\nthrows:");
        System.out.println("  - Metod imzasında kullanılır");
        System.out.println("  - Exception türünü bildirir");
        System.out.println("  - Örnek: void method() throws Exception");

        System.out.println("\nÖrnek Kullanım:");
        try {
            exampleMethod(5);
        } catch (CustomException e) {
            System.out.println("  Yakalandı: " + e.getMessage());
        }

        System.out.println();
    }

    /**
     * throw ve throws birlikte kullanımı
     */
    private static void exampleMethod(int value) throws CustomException {
        if (value < 10) {
            // throw ile exception fırlat
            throw new CustomException("Değer 10'dan küçük: " + value);
        }
        System.out.println("  Değer geçerli: " + value);
    }

    /**
     * 5. Checked vs Unchecked Exception Fırlatma
     */
    private static void demonstrateCheckedVsUnchecked() {
        System.out.println("--- 5. Checked vs Unchecked ---\n");

        // Unchecked exception - throws gerekmez
        System.out.println("Unchecked Exception:");
        try {
            throwUncheckedException();
        } catch (RuntimeException e) {
            System.out.println("  Yakalandı: " + e.getMessage());
        }

        // Checked exception - throws gerekir
        System.out.println("\nChecked Exception:");
        try {
            throwCheckedException();
        } catch (Exception e) {
            System.out.println("  Yakalandı: " + e.getMessage());
        }

        System.out.println("\nExercise completed!");
    }

    /**
     * Unchecked exception fırlatma - throws opsiyonel
     */
    private static void throwUncheckedException() {
        throw new IllegalStateException("Bu bir unchecked exception");
    }

    /**
     * Checked exception fırlatma - throws zorunlu
     */
    private static void throwCheckedException() throws Exception {
        throw new Exception("Bu bir checked exception");
    }
}

/**
 * Özel Exception Sınıfı
 */
class CustomException extends Exception {
    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
