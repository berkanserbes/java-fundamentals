package com.fundamentals.exceptionhandling;

import java.io.*;
import java.util.Scanner;

/**
 * Try-Catch-Finally Mekanizması
 * 
 * ============================================================================
 * TRY-CATCH-FINALLY YAPISI:
 * ============================================================================
 * 
 * try {
 * // Hata oluşabilecek kod
 * } catch (ExceptionType1 e) {
 * // Exception1 için işlem
 * } catch (ExceptionType2 e) {
 * // Exception2 için işlem
 * } finally {
 * // Her durumda çalışacak kod (opsiyonel)
 * }
 * 
 * ============================================================================
 * KURALLAR:
 * ============================================================================
 * 
 * ✓ try bloğu zorunlu
 * ✓ En az bir catch veya finally olmalı
 * ✓ finally her zaman çalışır (return bile olsa)
 * ✓ Birden fazla catch olabilir
 * ✓ Spesifik exception'lar önce, genel olanlar sonra
 * ✓ try-with-resources (Java 7+) otomatik kaynak yönetimi
 * 
 * ============================================================================
 */
public class TryCatchFinally {

    public static void main(String[] args) {
        System.out.println("=== Try-Catch-Finally ===\n");

        demonstrateBasicTryCatch();
        demonstrateMultipleCatch();
        demonstrateFinallyBlock();
        demonstrateFinallyWithReturn();
        demonstrateTryWithResources();
        demonstrateNestedTryCatch();
        demonstrateMultiCatch();
    }

    /**
     * 1. Temel Try-Catch
     */
    private static void demonstrateBasicTryCatch() {
        System.out.println("--- 1. Temel Try-Catch ---");
        System.out.println("En basit exception handling:\n");

        try {
            System.out.println("try bloğu başladı");
            int result = 10 / 0;
            System.out.println("Bu satır çalışmayacak");
        } catch (ArithmeticException e) {
            System.out.println("catch bloğu çalıştı");
            System.out.println("Hata mesajı: " + e.getMessage());
        }

        System.out.println("Program devam ediyor\n");
    }

    /**
     * 2. Birden Fazla Catch Bloğu
     */
    private static void demonstrateMultipleCatch() {
        System.out.println("--- 2. Birden Fazla Catch ---");
        System.out.println("Farklı exception türleri için ayrı catch blokları:\n");

        String[] data = { "10", "20", "abc", "30" };

        for (String item : data) {
            try {
                int number = Integer.parseInt(item);
                int result = 100 / number;
                System.out.println("✓ " + item + " -> Sonuç: " + result);
            } catch (NumberFormatException e) {
                System.out.println("❌ '" + item + "' geçerli bir sayı değil!");
            } catch (ArithmeticException e) {
                System.out.println("❌ '" + item + "' ile bölme hatası!");
            } catch (Exception e) {
                System.out.println("❌ Beklenmeyen hata: " + e.getMessage());
            }
        }

        System.out.println();
    }

    /**
     * 3. Finally Bloğu
     */
    private static void demonstrateFinallyBlock() {
        System.out.println("--- 3. Finally Bloğu ---");
        System.out.println("Finally her durumda çalışır:\n");

        // Senaryo 1: Exception yok
        System.out.println("Senaryo 1: Exception yok");
        try {
            System.out.println("  try: İşlem başarılı");
        } catch (Exception e) {
            System.out.println("  catch: Hata yakalandı");
        } finally {
            System.out.println("  finally: Temizlik yapılıyor");
        }

        // Senaryo 2: Exception var
        System.out.println("\nSenaryo 2: Exception var");
        try {
            System.out.println("  try: İşlem başladı");
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("  catch: Hata yakalandı");
        } finally {
            System.out.println("  finally: Temizlik yapılıyor");
        }

        System.out.println();
    }

    /**
     * 4. Finally ile Return
     */
    private static void demonstrateFinallyWithReturn() {
        System.out.println("--- 4. Finally ile Return ---");
        System.out.println("Finally, return'den önce bile çalışır:\n");

        String result = methodWithReturn();
        System.out.println("Dönen değer: " + result);
        System.out.println();
    }

    private static String methodWithReturn() {
        try {
            System.out.println("  try bloğu");
            return "try'dan dönen değer";
        } catch (Exception e) {
            System.out.println("  catch bloğu");
            return "catch'den dönen değer";
        } finally {
            System.out.println("  finally bloğu (return'den önce!)");
            // Not: finally'de return kullanmak kötü pratiktir!
        }
    }

    /**
     * 5. Try-with-Resources (Java 7+)
     */
    private static void demonstrateTryWithResources() {
        System.out.println("--- 5. Try-with-Resources ---");
        System.out.println("Otomatik kaynak yönetimi:\n");

        // Eski yöntem (manuel kapatma)
        System.out.println("Eski yöntem:");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new StringReader("Merhaba\nDünya"));
            String line = reader.readLine();
            System.out.println("  Okunan: " + line);
        } catch (IOException e) {
            System.out.println("  Hata: " + e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                    System.out.println("  Kaynak manuel kapatıldı");
                }
            } catch (IOException e) {
                System.out.println("  Kapatma hatası: " + e.getMessage());
            }
        }

        // Yeni yöntem (otomatik kapatma)
        System.out.println("\nYeni yöntem (try-with-resources):");
        try (BufferedReader autoReader = new BufferedReader(new StringReader("Merhaba\nDünya"))) {
            String line = autoReader.readLine();
            System.out.println("  Okunan: " + line);
            System.out.println("  Kaynak otomatik kapatılacak!");
        } catch (IOException e) {
            System.out.println("  Hata: " + e.getMessage());
        }

        System.out.println();
    }

    /**
     * 6. İç İçe Try-Catch (Nested)
     */
    private static void demonstrateNestedTryCatch() {
        System.out.println("--- 6. İç İçe Try-Catch ---");
        System.out.println("Nested exception handling:\n");

        try {
            System.out.println("Dış try bloğu");

            try {
                System.out.println("  İç try bloğu");
                int[] arr = { 1, 2, 3 };
                System.out.println("  Değer: " + arr[5]);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("  İç catch: Dizi hatası yakalandı");
            }

            // Dış try devam ediyor
            int result = 10 / 0;

        } catch (ArithmeticException e) {
            System.out.println("Dış catch: Aritmetik hata yakalandı");
        }

        System.out.println();
    }

    /**
     * 7. Multi-Catch (Java 7+)
     */
    private static void demonstrateMultiCatch() {
        System.out.println("--- 7. Multi-Catch (Java 7+) ---");
        System.out.println("Birden fazla exception'ı tek catch'de yakalama:\n");

        // Eski yöntem
        System.out.println("Eski yöntem:");
        try {
            processData("abc");
        } catch (NumberFormatException e) {
            System.out.println("  Hata: " + e.getClass().getSimpleName());
        } catch (NullPointerException e) {
            System.out.println("  Hata: " + e.getClass().getSimpleName());
        }

        // Yeni yöntem (Multi-catch)
        System.out.println("\nYeni yöntem (Multi-catch):");
        try {
            processData(null);
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("  Hata yakalandı: " + e.getClass().getSimpleName());
            System.out.println("  Mesaj: " + e.getMessage());
        }

        System.out.println("\nExercise completed!");
    }

    private static void processData(String data) {
        int length = data.length(); // NullPointerException if null
        int number = Integer.parseInt(data); // NumberFormatException if invalid
    }
}
