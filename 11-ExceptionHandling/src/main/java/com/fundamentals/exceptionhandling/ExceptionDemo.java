package com.fundamentals.exceptionhandling;

/**
 * Exception Handling - Ana Demo Sınıfı
 * 
 * Bu sınıf, Java Exception Handling konusunun tüm örneklerini çalıştırır.
 * 
 * Kapsanan Konular:
 * 1. Exception Basics - Temel exception türleri
 * 2. Try-Catch-Finally - Exception yakalama mekanizması
 * 3. Throw and Throws - Exception fırlatma
 * 4. Custom Exceptions - Özel exception sınıfları
 * 5. Best Practices - En iyi uygulamalar
 */
public class ExceptionDemo {

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║         JAVA EXCEPTION HANDLING - KAPSAMLI DEMO           ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.println();

        runDemo("Exception Basics", () -> ExceptionBasics.main(args));
        runDemo("Try-Catch-Finally", () -> TryCatchFinally.main(args));
        runDemo("Throw and Throws", () -> ThrowAndThrows.main(args));
        runDemo("Custom Exceptions", () -> CustomExceptions.main(args));
        runDemo("Best Practices", () -> ExceptionBestPractices.main(args));

        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              TÜM DEMOLAR TAMAMLANDI! ✓                     ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }

    /**
     * Demo çalıştırıcı yardımcı metod
     */
    private static void runDemo(String demoName, Runnable demo) {
        System.out.println("┌────────────────────────────────────────────────────────────┐");
        System.out.println("│  " + demoName);
        System.out.println("└────────────────────────────────────────────────────────────┘");
        System.out.println();

        try {
            demo.run();
        } catch (Exception e) {
            System.out.println("❌ Demo hatası: " + e.getMessage());
        }

        System.out.println("\n" + "═".repeat(60) + "\n");
    }
}
