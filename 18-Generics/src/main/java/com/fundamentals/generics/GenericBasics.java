package com.fundamentals.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * GENERICS - TEMEL KAVRAMLAR
 * =============================================================
 * 
 * Generics, Java 5 ile tanitilan ve tip guvenligini (type safety) saglayan
 * bir mekanizmadir. Kod yazarken tip parametreleri kullanarak, derleme
 * zamaninda tip kontrolu yapilmasini saglar.
 * 
 * GENERICS NEDEN KULLANILIR?
 * ---------------------------
 * 1. TIP GUVENLIGI (Type Safety): Derleme zamaninda tip hatalari yakalanir
 * 2. CAST ISLEMINDEN KURTULMA: Explicit casting'e gerek kalmaz
 * 3. KOD TEKRAR KULLANIMI: Ayni kod farkli tiplerle kullanilabilir
 * 4. GENERIC ALGORITMALAR: Tip bagimsiz algoritmalar yazilabilir
 * 
 * TIP PARAMETRESI ISIMLENDIRME KONVANSIYONLARI:
 * ---------------------------------------------
 * E - Element (Collection'larda kullanilir)
 * K - Key (Map yapilarinda anahtar)
 * V - Value (Map yapilarinda deger)
 * T - Type (Genel tip parametresi)
 * N - Number (Sayi tipleri icin)
 * S, U, V - Ikinci, ucuncu, dorduncu tip parametreleri
 */
public class GenericBasics {

    public static void demonstrate() {
        System.out.println("=".repeat(60));
        System.out.println("GENERICS - TEMEL KAVRAMLAR");
        System.out.println("=".repeat(60));

        demonstrateWithoutGenerics();
        demonstrateWithGenerics();
        demonstrateDiamondOperator();
        demonstrateMultipleTypeParameters();
    }

    /**
     * GENERICS OLMADAN PROBLEM
     * -------------------------
     * Generics oncesi, koleksiyonlar Object tipinde veri tutardi.
     * Bu durum runtime'da ClassCastException'a yol acabilir.
     */
    private static void demonstrateWithoutGenerics() {
        System.out.println("\n1. GENERICS OLMADAN (Eski Yontem)");
        System.out.println("-".repeat(40));

        // Raw type kullanimi - onerilen degil!
        @SuppressWarnings("rawtypes")
        List rawList = new ArrayList();
        
        rawList.add("Merhaba");
        rawList.add(123);  // Farkli tipler eklenebilir - tehlikeli!
        rawList.add(45.67);
        
        System.out.println("Raw List icerigi: " + rawList);
        
        // Her erisimde casting gerekli
        String firstItem = (String) rawList.get(0);
        System.out.println("Ilk eleman (cast ile): " + firstItem);
        
        // TEHLIKE: Yanlis cast ClassCastException olusturabilir
        // Integer num = (Integer) rawList.get(0); // Runtime hatasi!
        System.out.println("UYARI: Raw type kullanimi tip guvenligi saglamaz!");
    }

    /**
     * GENERICS ILE COZUM
     * -------------------
     * Tip parametresi belirleyerek derleme zamaninda tip kontrolu saglanir.
     */
    private static void demonstrateWithGenerics() {
        System.out.println("\n2. GENERICS ILE (Modern Yontem)");
        System.out.println("-".repeat(40));

        // Generic List - sadece String kabul eder
        List<String> stringList = new ArrayList<String>();
        stringList.add("Java");
        stringList.add("Generics");
        stringList.add("Type Safety");
        // stringList.add(123);  // DERLEME HATASI - Integer eklenemez!
        
        System.out.println("String List: " + stringList);
        
        // Casting'e gerek yok
        String first = stringList.get(0);
        System.out.println("Ilk eleman (cast olmadan): " + first);
        
        // Generic List - sadece Integer kabul eder
        List<Integer> intList = new ArrayList<Integer>();
        intList.add(10);
        intList.add(20);
        intList.add(30);
        
        System.out.println("Integer List: " + intList);
        
        // Toplam hesaplama - otomatik unboxing
        int total = 0;
        for (Integer num : intList) {
            total += num;
        }
        System.out.println("Toplam: " + total);
    }

    /**
     * DIAMOND OPERATOR (<>)
     * ----------------------
     * Java 7 ile gelen diamond operator, tip parametresinin
     * sag tarafta tekrar yazilmasini gereksiz kilar.
     */
    private static void demonstrateDiamondOperator() {
        System.out.println("\n3. DIAMOND OPERATOR (<>)");
        System.out.println("-".repeat(40));

        // Java 5-6 stili - tip iki kere yazilir
        List<String> oldStyle = new ArrayList<String>();
        
        // Java 7+ stili - diamond operator
        List<String> newStyle = new ArrayList<>();  // Tip cikarimii
        
        // Nested generic tipler icin daha da kullanisli
        List<List<String>> nestedOld = new ArrayList<List<String>>();
        List<List<String>> nestedNew = new ArrayList<>();  // Cok daha temiz!
        
        nestedNew.add(new ArrayList<>());
        nestedNew.get(0).add("Nested");
        nestedNew.get(0).add("Generics");
        
        System.out.println("Diamond operator ile olusturulan nested list:");
        System.out.println(nestedNew);
        
        // Java 10+ var keyword ile birlikte
        var modernList = new ArrayList<Double>();
        modernList.add(3.14);
        modernList.add(2.71);
        System.out.println("var + diamond: " + modernList);
    }

    /**
     * COKLU TIP PARAMETRELERI
     * ------------------------
     * Birden fazla tip parametresi kullanilabilir.
     */
    private static void demonstrateMultipleTypeParameters() {
        System.out.println("\n4. COKLU TIP PARAMETRELERI");
        System.out.println("-".repeat(40));

        // Basit Pair sinifi ornegi
        Pair<String, Integer> personAge = new Pair<>("Ahmet", 25);
        System.out.println("Pair ornegi: " + personAge);
        System.out.println("  Anahtar: " + personAge.getFirst());
        System.out.println("  Deger: " + personAge.getSecond());

        // Farkli tiplerle Pair
        Pair<Double, String> priceItem = new Pair<>(99.99, "Laptop");
        System.out.println("\nFarkli tiplerle Pair: " + priceItem);

        // Uc parametreli Triple
        Triple<String, Integer, Boolean> student = 
            new Triple<>("Mehmet", 85, true);
        System.out.println("\nTriple ornegi:");
        System.out.println("  Ogrenci: " + student.getFirst());
        System.out.println("  Not: " + student.getSecond());
        System.out.println("  Gecti mi: " + student.getThird());
    }

    // =========================================================
    // YARDIMCI SINIFLAR
    // =========================================================

    /**
     * Iki deger tutan generic Pair sinifi
     */
    static class Pair<F, S> {
        private final F first;
        private final S second;

        public Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }

        public F getFirst() { return first; }
        public S getSecond() { return second; }

        @Override
        public String toString() {
            return "(" + first + ", " + second + ")";
        }
    }

    /**
     * Uc deger tutan generic Triple sinifi
     */
    static class Triple<A, B, C> {
        private final A first;
        private final B second;
        private final C third;

        public Triple(A first, B second, C third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }

        public A getFirst() { return first; }
        public B getSecond() { return second; }
        public C getThird() { return third; }

        @Override
        public String toString() {
            return "(" + first + ", " + second + ", " + third + ")";
        }
    }
}
