package com.fundamentals.optionals;

import java.util.Optional;
import java.util.NoSuchElementException;

/**
 * Optional Temel Metodlar
 */
public class OptionalMethods {

    public static void demonstrate() {
        System.out.println();

        Optional<String> filled = Optional.of("Java");
        Optional<String> empty = Optional.empty();

        // ============================================================
        // 1. Deger Kontrolu
        // ============================================================
        System.out.println("[1] DEGER KONTROLU:");
        System.out.println("------------------------------------------------------------");

        // isPresent() ve isEmpty()
        System.out.println("filled.isPresent(): " + filled.isPresent()); // true
        System.out.println("empty.isPresent(): " + empty.isPresent()); // false
        System.out.println("filled.isEmpty(): " + filled.isEmpty()); // false
        System.out.println("empty.isEmpty(): " + empty.isEmpty()); // true
        System.out.println();

        // ifPresent() - Deger varsa islem yap
        System.out.println("ifPresent() kullanimi:");
        filled.ifPresent(value -> System.out.println("  Deger var: " + value));
        empty.ifPresent(value -> System.out.println("  Bu satir yazilmaz"));
        System.out.println();

        // ifPresentOrElse() - Java 9+
        System.out.println("ifPresentOrElse() kullanimi (Java 9+):");
        filled.ifPresentOrElse(
                value -> System.out.println("  Deger bulundu: " + value),
                () -> System.out.println("  Deger bulunamadi"));
        empty.ifPresentOrElse(
                value -> System.out.println("  Deger bulundu: " + value),
                () -> System.out.println("  Deger bulunamadi"));
        System.out.println();

        // ============================================================
        // 2. Deger Alma
        // ============================================================
        System.out.println("[2] DEGER ALMA:");
        System.out.println("------------------------------------------------------------");

        // get() - Tehlikeli!
        System.out.println("get() - TEHLIKELI (kullanmayin!):");
        System.out.println("  filled.get(): " + filled.get());
        System.out.println("  empty.get() -> NoSuchElementException firlatir!");
        System.out.println();

        // orElse() - Varsayilan deger
        System.out.println("orElse() - Varsayilan deger:");
        System.out.println("  filled.orElse(\"Varsayilan\"): " + filled.orElse("Varsayilan"));
        System.out.println("  empty.orElse(\"Varsayilan\"): " + empty.orElse("Varsayilan"));
        System.out.println();

        // orElseGet() - Lazy evaluation
        System.out.println("orElseGet() - Lazy evaluation (Supplier):");
        String result1 = filled.orElseGet(() -> expensiveOperation());
        String result2 = empty.orElseGet(() -> expensiveOperation());
        System.out.println("  filled icin supplier CALISTIRILMAZ");
        System.out.println("  empty icin supplier CALISTIRILIR");
        System.out.println();

        // orElseThrow() - Hata firlat
        System.out.println("orElseThrow() - Hata firlat:");
        System.out.println("  filled.orElseThrow(): " + filled.orElseThrow());
        try {
            empty.orElseThrow();
        } catch (NoSuchElementException e) {
            System.out.println("  empty.orElseThrow() -> NoSuchElementException!");
        }
        System.out.println();

        // orElseThrow(Supplier) - Ozel hata
        System.out.println("orElseThrow(Supplier) - Ozel hata:");
        try {
            empty.orElseThrow(() -> new IllegalStateException("Deger bulunamadi!"));
        } catch (IllegalStateException e) {
            System.out.println("  Ozel hata: " + e.getMessage());
        }
        System.out.println();

        // ============================================================
        // 3. orElse vs orElseGet Farki
        // ============================================================
        System.out.println("[3] orElse vs orElseGet FARKI:");
        System.out.println("------------------------------------------------------------");
        System.out.println("orElse(value):");
        System.out.println("  - Varsayilan deger HER ZAMAN hesaplanir");
        System.out.println("  - Basit degerler icin uygun");
        System.out.println();
        System.out.println("orElseGet(supplier):");
        System.out.println("  - Varsayilan deger SADECE gerektiginde hesaplanir");
        System.out.println("  - Pahali operasyonlar icin uygun (DB sorgusu, API cagri)");
        System.out.println();

        System.out.println("Ornek:");
        Optional<String> opt = Optional.of("Deger var");

        System.out.println("orElse kullanimi:");
        String r1 = opt.orElse(createDefault()); // createDefault() CAGIRILIR

        System.out.println("orElseGet kullanimi:");
        String r2 = opt.orElseGet(() -> createDefault()); // createDefault() CAGIRILMAZ
    }

    private static String expensiveOperation() {
        System.out.println("    [Pahali operasyon calisiyor...]");
        return "Expensive Result";
    }

    private static String createDefault() {
        System.out.println("  [createDefault() CAGIRILDI]");
        return "Default";
    }
}
