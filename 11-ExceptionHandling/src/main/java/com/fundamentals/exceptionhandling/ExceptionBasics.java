package com.fundamentals.exceptionhandling;

/**
 * Exception Basics - Temel Exception Kavramları
 * 
 * ============================================================================
 * EXCEPTION NEDİR?
 * ============================================================================
 * 
 * Exception: Program çalışırken oluşan beklenmedik durumlar (hatalar).
 * Java'da exception'lar nesne olarak temsil edilir ve Exception sınıfından
 * türer.
 * 
 * ============================================================================
 * EXCEPTION HİYERARŞİSİ:
 * ============================================================================
 * 
 * Object
 * └── Throwable
 * ├── Error (JVM hataları - yakalanmamalı)
 * │ ├── OutOfMemoryError
 * │ ├── StackOverflowError
 * │ └── ...
 * │
 * └── Exception (Yakalanabilir hatalar)
 * ├── RuntimeException (Unchecked)
 * │ ├── NullPointerException
 * │ ├── ArithmeticException
 * │ ├── ArrayIndexOutOfBoundsException
 * │ └── ...
 * │
 * └── Checked Exceptions
 * ├── IOException
 * ├── SQLException
 * ├── ClassNotFoundException
 * └── ...
 * 
 * ============================================================================
 * CHECKED vs UNCHECKED EXCEPTIONS:
 * ============================================================================
 * 
 * CHECKED (Kontrollü):
 * - Compile-time'da kontrol edilir
 * - try-catch veya throws ile handle edilmeli
 * - IOException, SQLException, ClassNotFoundException
 * 
 * UNCHECKED (Kontrolsüz):
 * - Runtime'da oluşur
 * - Handle etmek zorunlu değil
 * - RuntimeException ve alt sınıfları
 * - NullPointerException, ArithmeticException
 * 
 * ============================================================================
 */
public class ExceptionBasics {

    public static void main(String[] args) {
        System.out.println("=== Exception Basics ===\n");

        demonstrateArithmeticException();
        demonstrateNullPointerException();
        demonstrateArrayIndexOutOfBounds();
        demonstrateNumberFormatException();
        demonstrateClassCastException();
        demonstrateIllegalArgumentException();
    }

    /**
     * 1. ArithmeticException - Matematiksel hata
     */
    private static void demonstrateArithmeticException() {
        System.out.println("--- 1. ArithmeticException ---");
        System.out.println("Sıfıra bölme hatası:\n");

        try {
            int a = 10;
            int b = 0;
            int result = a / b; // ArithmeticException
            System.out.println("Sonuç: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Hata yakalandı: " + e.getMessage());
            System.out.println("Açıklama: Bir sayı sıfıra bölünemez!");
        }

        System.out.println("Program devam ediyor...\n");
    }

    /**
     * 2. NullPointerException - Null referans hatası
     */
    private static void demonstrateNullPointerException() {
        System.out.println("--- 2. NullPointerException ---");
        System.out.println("Null nesne üzerinde işlem yapma:\n");

        try {
            String str = null;
            int length = str.length(); // NullPointerException
            System.out.println("Uzunluk: " + length);
        } catch (NullPointerException e) {
            System.out.println("Hata yakalandı: " + e.getClass().getSimpleName());
            System.out.println("Açıklama: Null bir nesne üzerinde metod çağrılamaz!");
        }

        // Doğru kullanım
        String str = "Merhaba";
        if (str != null) {
            System.out.println("✓ Güvenli kullanım: " + str.length());
        }

        System.out.println();
    }

    /**
     * 3. ArrayIndexOutOfBoundsException - Dizi sınır aşımı
     */
    private static void demonstrateArrayIndexOutOfBounds() {
        System.out.println("--- 3. ArrayIndexOutOfBoundsException ---");
        System.out.println("Dizi sınırları dışına erişim:\n");

        try {
            int[] numbers = { 10, 20, 30 };
            System.out.println("Dizi boyutu: " + numbers.length);
            int value = numbers[5]; // ArrayIndexOutOfBoundsException
            System.out.println("Değer: " + value);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("❌ Hata yakalandı: " + e.getMessage());
            System.out.println("Açıklama: Dizi indeksi 0 ile " + (3 - 1) + " arasında olmalı!");
        }

        // Doğru kullanım
        int[] numbers = { 10, 20, 30 };
        for (int i = 0; i < numbers.length; i++) {
            System.out.println("✓ numbers[" + i + "] = " + numbers[i]);
        }

        System.out.println();
    }

    /**
     * 4. NumberFormatException - Sayı format hatası
     */
    private static void demonstrateNumberFormatException() {
        System.out.println("--- 4. NumberFormatException ---");
        System.out.println("Geçersiz string'i sayıya çevirme:\n");

        try {
            String text = "abc123";
            int number = Integer.parseInt(text); // NumberFormatException
            System.out.println("Sayı: " + number);
        } catch (NumberFormatException e) {
            System.out.println("Hata yakalandı: " + e.getMessage());
            System.out.println("Açıklama: '" + "abc123" + "' geçerli bir sayı değil!");
        }

        // Doğru kullanım
        try {
            String validText = "12345";
            int number = Integer.parseInt(validText);
            System.out.println("Başarılı dönüşüm: " + number);
        } catch (NumberFormatException e) {
            System.out.println("Hata: " + e.getMessage());
        }

        System.out.println();
    }

    /**
     * 5. ClassCastException - Tip dönüşüm hatası
     */
    private static void demonstrateClassCastException() {
        System.out.println("--- 5. ClassCastException ---");
        System.out.println("Yanlış tip dönüşümü:\n");

        try {
            Object obj = "Merhaba";
            Integer number = (Integer) obj; // ClassCastException
            System.out.println("Sayı: " + number);
        } catch (ClassCastException e) {
            System.out.println("Hata yakalandı: " + e.getMessage());
            System.out.println("Açıklama: String, Integer'a dönüştürülemez!");
        }

        // Doğru kullanım - instanceof kontrolü
        Object obj = "Merhaba";
        if (obj instanceof String) {
            String str = (String) obj;
            System.out.println("✓ Güvenli dönüşüm: " + str);
        }

        System.out.println();
    }

    /**
     * 6. IllegalArgumentException - Geçersiz parametre
     */
    private static void demonstrateIllegalArgumentException() {
        System.out.println("--- 6. IllegalArgumentException ---");
        System.out.println("Geçersiz parametre ile metod çağırma:\n");

        try {
            setAge(-5); // IllegalArgumentException
        } catch (IllegalArgumentException e) {
            System.out.println("Hata yakalandı: " + e.getMessage());
        }

        // Doğru kullanım
        try {
            setAge(25);
            System.out.println("Yaş başarıyla ayarlandı!");
        } catch (IllegalArgumentException e) {
            System.out.println("Hata: " + e.getMessage());
        }

        System.out.println("\nExercise completed!");
    }

    /**
     * Yaş ayarlama metodu - parametre validasyonu
     */
    private static void setAge(int age) {
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("Yaş 0-150 arasında olmalıdır! Verilen: " + age);
        }
        System.out.println("Yaş ayarlandı: " + age);
    }
}
