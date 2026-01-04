package com.fundamentals.generics;

/**
 * JAVA GENERICS DEMO
 * =============================================================
 * 
 * Bu sinif, Generics konusundaki tum ornekleri calistirir.
 * 
 * GENERICS NEDIR?
 * ---------------
 * Generics, Java 5 ile gelen, tip guvenligini (type safety) saglayan
 * ve kod tekrar kullanimini artiran bir mekanizmadir.
 * 
 * BU MODULDEKI KONULAR:
 * ---------------------
 * 1. GenericBasics     - Temel kavramlar ve neden kullanilir
 * 2. GenericClasses    - Generic sinif tanimlama
 * 3. GenericMethods    - Generic metod tanimlama
 * 4. GenericInterfaces - Generic arayuzler
 * 5. BoundedTypeParameters - Sinirlandirilmis tip parametreleri
 * 6. Wildcards         - Wildcard (?) kullanimi ve PECS kurali
 * 7. TypeErasure       - Tip silme mekanizmasi
 * 8. GenericBestPractices - En iyi uygulamalar
 * 
 * CALISTIRMAK ICIN:
 * -----------------
 * mvn compile exec:java -pl 18-Generics
 * 
 * VEYA
 * 
 * .\run-module.bat 18-Generics GenericsDemo
 */
public class GenericsDemo {

    public static void main(String[] args) {
        System.out.println("*".repeat(60));
        System.out.println("*          JAVA GENERICS - KAPSAMLI REHBER              *");
        System.out.println("*".repeat(60));

        // 1. Generic Temelleri
        GenericBasics.demonstrate();

        // 2. Generic Siniflar
        GenericClasses.demonstrate();

        // 3. Generic Metodlar
        GenericMethods.demonstrate();

        // 4. Generic Arayuzler
        GenericInterfaces.demonstrate();

        // 5. Sinirlandirilmis Tip Parametreleri
        BoundedTypeParameters.demonstrate();

        // 6. Wildcard Kullanimi
        Wildcards.demonstrate();

        // 7. Tip Silme (Type Erasure)
        TypeErasure.demonstrate();

        // 8. En Iyi Uygulamalar
        GenericBestPractices.demonstrate();

        // Ozet
        printSummary();
    }

    private static void printSummary() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("GENERICS OZET");
        System.out.println("=".repeat(60));

        System.out.println("""
            
            TEMEL KAVRAMLAR:
            ----------------
            - Generics tip guvenligi saglar
            - Derleme zamaninda tip kontrolu yapilir
            - Cast islemlerine gerek kalmaz
            - Kod tekrar kullanimi artar
            
            TIP PARAMETRESI KONVANSIYONLARI:
            --------------------------------
            E - Element (Collection'larda)
            K - Key (Map anahtari)
            V - Value (Map degeri)
            T - Type (Genel tip)
            N - Number (Sayi tipleri)
            S, U - Ek tip parametreleri
            
            BOUNDED TYPE PARAMETERS:
            -------------------------
            <T extends Type>      - Upper bound (T, Type veya alt sinifi)
            <T extends A & B & C> - Multiple bounds (A sinif, B ve C arayuz)
            
            WILDCARDS:
            ----------
            <?>                - Unbounded (herhangi bir tip)
            <? extends Type>   - Upper bounded (Type veya alt siniflari)
            <? super Type>     - Lower bounded (Type veya ust siniflari)
            
            PECS KURALI:
            ------------
            Producer Extends - Okuma icin extends kullan
            Consumer Super   - Yazma icin super kullan
            
            TYPE ERASURE:
            -------------
            - Runtime'da generic tip bilgisi silinir
            - <T> -> Object, <T extends Number> -> Number
            - Primitive tipler kullanilamaz
            - Generic dizi olusturulamaz
            
            EN IYI UYGULAMALAR:
            -------------------
            - Raw type kullanmayin
            - Array yerine List tercih edin
            - PECS kuralina uyun
            - Type token ile runtime tip guvenligi saglayin
            """);
    }
}
