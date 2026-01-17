package com.fundamentals.di;

/**
 * DEPENDENCY INJECTION DEMO
 * 
 * Tum DI konularini bir arada gosterir.
 */
public class DependencyInjectionDemo {

   public static void main(String[] args) {
      printHeader();
      printModuleOverview();
      runAllDemos();
      printSummary();
   }

   private static void printHeader() {
      System.out.println("*".repeat(70));
      System.out.println("*" + centerText("DEPENDENCY INJECTION - JAVA FUNDAMENTALS", 68) + "*");
      System.out.println("*".repeat(70));
   }

   private static void printModuleOverview() {
      System.out.println("""

            Dependency Injection (DI), bir sinifin ihtiyac duydugu bagimliliklarin
            disaridan verilmesi teknigidir. Inversion of Control (IoC) prensibinin
            uygulanma yollarindan biridir.

            Bu moduldeki siniflar:
            =====================

            1. DIBasics           - Temel kavramlar, Tight vs Loose Coupling, IoC
            2. DIWithoutFramework - Manual DI, Factory, Builder, Composition Root
            3. ConstructorInjection - Constructor-based DI, immutability
            4. SetterInjection    - Setter-based DI, opsiyonel bagimliliklar
            5. FieldInjection     - Field-based DI (Reflection), neden onerilmez
            6. InterfaceInjection - Interface-based DI, plugin sistemleri
            7. DIContainer        - Simple DI Container implementasyonu
            8. ServiceLocator     - Service Locator Pattern, neden anti-pattern
            9. DIBestPractices    - SOLID, testability, common mistakes
            10. QualifierAnnotation - @Qualifier kullanimi
            """);
   }

   private static void runAllDemos() {
      System.out.println("=".repeat(70));
      System.out.println(centerText("TUM DEMO'LARI CALISTIRMA", 70));
      System.out.println("=".repeat(70));

      runDemo("DIBasics", () -> DIBasics.main(new String[] {}));
      runDemo("DIWithoutFramework", () -> DIWithoutFramework.main(new String[] {}));
      runDemo("ConstructorInjection", () -> ConstructorInjection.main(new String[] {}));
      runDemo("SetterInjection", () -> SetterInjection.main(new String[] {}));
      runDemo("FieldInjection", () -> FieldInjection.main(new String[] {}));
      runDemo("InterfaceInjection", () -> InterfaceInjection.main(new String[] {}));
      runDemo("DIContainer", () -> DIContainer.main(new String[] {}));
      runDemo("ServiceLocator", () -> ServiceLocator.main(new String[] {}));
      runDemo("DIBestPractices", () -> DIBestPractices.main(new String[] {}));
      runDemo("QualifierAnnotation", () -> QualifierAnnotation.main(new String[] {}));
   }

   private static void runDemo(String name, Runnable demo) {
      System.out.println("\n");
      System.out.println("#".repeat(70));
      System.out.println("#" + centerText("DEMO: " + name, 68) + "#");
      System.out.println("#".repeat(70));

      try {
         demo.run();
      } catch (Exception e) {
         System.out.println("   [HATA] " + e.getMessage());
      }
   }

   private static void printSummary() {
      System.out.println("\n" + "=".repeat(70));
      System.out.println(centerText("TUM DEMOLAR TAMAMLANDI!", 70));
      System.out.println("=".repeat(70));

      System.out.println("""

            OZET - DEPENDENCY INJECTION
            ===========================

            INJECTION TURLERI:
            +----------------------+------------------+------------------+
            | Tur                  | Kullanim         | Oneri            |
            +----------------------+------------------+------------------+
            | Constructor Injection| Zorunlu bag.     | VARSAYILAN       |
            | Setter Injection     | Opsiyonel bag.   | Gerektiginde     |
            | Field Injection      | Framework        | KACININ          |
            | Interface Injection  | Plugin/Framework | Nadir            |
            +----------------------+------------------+------------------+

            ANAHTAR KAVRAMLAR:
            - Loose Coupling: Gevsek baglanti
            - Inversion of Control: Kontrol tersi
            - Dependency Inversion: Soyutlamaya bagimlilik
            - Composition Root: Bagimliliklarin bir araya getirildigi nokta

            EN IYI UYGULAMALAR:
            [+] Constructor Injection tercih edin
            [+] Interface'e programlayin
            [+] SOLID prensiplerini uygulayin
            [+] Service Locator'dan kacinin
            [+] @Qualifier ile belirsizligi cozun

            POPULER FRAMEWORK'LER:
            - Spring Framework (Java)
            - Google Guice (Java)
            - Dagger (Java/Android)
            - CDI (Jakarta EE)
            """);
   }

   private static String centerText(String text, int width) {
      int padding = (width - text.length()) / 2;
      return " ".repeat(Math.max(0, padding)) + text +
            " ".repeat(Math.max(0, width - text.length() - padding));
   }
}
