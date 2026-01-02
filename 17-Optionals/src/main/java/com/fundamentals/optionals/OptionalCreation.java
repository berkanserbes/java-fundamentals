package com.fundamentals.optionals;

import java.util.Optional;

/**
 * Optional Olusturma Yontemleri
 */
public class OptionalCreation {

    public static void demonstrate() {
        System.out.println();

        // ============================================================
        // 1. Optional.of() - null olmayan degerler icin
        // ============================================================
        System.out.println("[1] Optional.of() - null olmayan degerler icin:");
        System.out.println("------------------------------------------------------------");

        String name = "Ahmet";
        Optional<String> optName = Optional.of(name);
        System.out.println("Optional.of(\"Ahmet\"): " + optName);
        System.out.println("Deger: " + optName.get());

        System.out.println();
        System.out.println("DIKKAT: Optional.of(null) -> NullPointerException firlatir!");
        System.out.println("Ornek:");
        System.out.println("  String nullValue = null;");
        System.out.println("  Optional.of(nullValue); // NullPointerException!");
        System.out.println();

        // ============================================================
        // 2. Optional.ofNullable() - null olabilecek degerler icin
        // ============================================================
        System.out.println("[2] Optional.ofNullable() - null olabilecek degerler icin:");
        System.out.println("------------------------------------------------------------");

        String value1 = "Merhaba";
        String value2 = null;

        Optional<String> opt1 = Optional.ofNullable(value1);
        Optional<String> opt2 = Optional.ofNullable(value2);

        System.out.println("Optional.ofNullable(\"Merhaba\"): " + opt1);
        System.out.println("Optional.ofNullable(null): " + opt2);
        System.out.println();
        System.out.println("Deger varsa: " + opt1.isPresent()); // true
        System.out.println("Deger yoksa: " + opt2.isPresent()); // false
        System.out.println();

        // ============================================================
        // 3. Optional.empty() - Bos Optional
        // ============================================================
        System.out.println("[3] Optional.empty() - Bos Optional:");
        System.out.println("------------------------------------------------------------");

        Optional<String> emptyOpt = Optional.empty();
        System.out.println("Optional.empty(): " + emptyOpt);
        System.out.println("isEmpty(): " + emptyOpt.isEmpty());
        System.out.println("isPresent(): " + emptyOpt.isPresent());
        System.out.println();

        // ============================================================
        // Karsilastirma
        // ============================================================
        System.out.println("OLUSTURMA METODLARI KARSILASTIRMASI");
        System.out.println("------------------------------------------------------------");
        System.out.println("+----------------------+------------------+------------------+");
        System.out.println("| Metod                | null Degeri      | Kullanim         |");
        System.out.println("+----------------------+------------------+------------------+");
        System.out.println("| Optional.of(value)   | Exception        | Kesin deger var  |");
        System.out.println("| Optional.ofNullable  | Bos Optional     | null olabilir    |");
        System.out.println("| Optional.empty()     | -                | Bos Optional     |");
        System.out.println("+----------------------+------------------+------------------+");
        System.out.println();

        // ============================================================
        // Pratik Ornekler
        // ============================================================
        System.out.println("[>] PRATIK ORNEKLER:");
        System.out.println("------------------------------------------------------------");

        // Database'den gelen deger (null olabilir)
        String dbValue = getUserFromDatabase();
        Optional<String> userOpt = Optional.ofNullable(dbValue);
        System.out.println("Database'den gelen deger: " + userOpt);

        // Config deger (kesinlikle var)
        String configValue = "production";
        Optional<String> configOpt = Optional.of(configValue);
        System.out.println("Config degeri: " + configOpt);

        // Varsayilan bos deger
        Optional<String> defaultOpt = Optional.empty();
        System.out.println("Varsayilan bos: " + defaultOpt);
    }

    // Simule edilmis database sorgusu
    private static String getUserFromDatabase() {
        // Gercek uygulamada database'den gelir
        return null; // Kullanici bulunamadi
    }
}
