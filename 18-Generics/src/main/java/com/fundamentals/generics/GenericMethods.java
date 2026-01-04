package com.fundamentals.generics;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * GENERIC METODLAR
 * =============================================================
 * 
 * Generic metodlar, kendi tip parametrelerine sahip metodlardir.
 * Generic siniflar gibi, generic metodlar da farkli tiplerle
 * calisabilir ve tip guvenligi saglar.
 * 
 * GENERIC METOD TANIMLAMA:
 * ------------------------
 * - Tip parametresi donus tipinden ONCE yazilir: <T> T methodName(T param)
 * - Hem static hem instance metodlar generic olabilir
 * - Sinifin generic olmasina gerek yoktur
 * - Tip parametresi metod cagrisi sirasinda otomatik cikarilir (type inference)
 * 
 * YAZIM FORMATI:
 * [erisim belirteci] <tip parametreleri> [donus tipi] [metod adi](parametreler)
 * 
 * Ornek: public static <T> T getFirst(List<T> list)
 */
public class GenericMethods {

    public static void demonstrate() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("GENERIC METODLAR");
        System.out.println("=".repeat(60));

        demonstrateBasicGenericMethods();
        demonstrateMultipleTypeParameters();
        demonstrateBoundedGenericMethods();
        demonstrateGenericUtilityMethods();
        demonstrateTypeInference();
    }

    /**
     * TEMEL GENERIC METODLAR
     * -----------------------
     */
    private static void demonstrateBasicGenericMethods() {
        System.out.println("\n1. TEMEL GENERIC METODLAR");
        System.out.println("-".repeat(40));

        // print metodu - herhangi bir tipi yazdirir
        print("Merhaba");
        print(123);
        print(3.14);
        print(true);

        System.out.println();

        // identity metodu - ayni degeri dondurur
        String str = identity("Java");
        Integer num = identity(42);
        Double dbl = identity(2.718);

        System.out.println("identity(\"Java\"): " + str);
        System.out.println("identity(42): " + num);
        System.out.println("identity(2.718): " + dbl);
    }

    /**
     * COKLU TIP PARAMETRELI METODLAR
     * -------------------------------
     */
    private static void demonstrateMultipleTypeParameters() {
        System.out.println("\n2. COKLU TIP PARAMETRELI METODLAR");
        System.out.println("-".repeat(40));

        // Iki farkli tip ile calisma
        Pair<String, Integer> pair1 = makePair("Yas", 25);
        System.out.println("makePair(\"Yas\", 25): " + pair1);

        Pair<Double, String> pair2 = makePair(99.99, "TL");
        System.out.println("makePair(99.99, \"TL\"): " + pair2);

        // Tip donusumu
        String converted = convert(123, String::valueOf);
        System.out.println("\nconvert(123, String::valueOf): " + converted);

        Integer parsed = convert("456", Integer::parseInt);
        System.out.println("convert(\"456\", Integer::parseInt): " + parsed);
    }

    /**
     * SINIRLANDIRILMIS GENERIC METODLAR
     * -----------------------------------
     * Tip parametresi belirli bir ust sinifa veya arayuze sinirlanabilir.
     */
    private static void demonstrateBoundedGenericMethods() {
        System.out.println("\n3. SINIRLANDIRILMIS GENERIC METODLAR");
        System.out.println("-".repeat(40));

        // Number'dan turetilen tipler icin
        Integer[] intArray = {5, 2, 9, 1, 7};
        Double[] doubleArray = {3.14, 1.41, 2.71};

        System.out.println("Integer array toplami: " + sum(intArray));
        System.out.println("Double array toplami: " + sum(doubleArray));

        // Comparable tipler icin
        System.out.println("\nminimum(3, 7): " + minimum(3, 7));
        System.out.println("minimum(\"Apple\", \"Banana\"): " + minimum("Apple", "Banana"));
        System.out.println("maximum(10.5, 20.3): " + maximum(10.5, 20.3));
    }

    /**
     * GENERIC UTILITY METODLAR
     * -------------------------
     */
    private static void demonstrateGenericUtilityMethods() {
        System.out.println("\n4. GENERIC UTILITY METODLAR");
        System.out.println("-".repeat(40));

        // Array'den List'e donusum
        String[] names = {"Ali", "Veli", "Ayse"};
        List<String> nameList = toList(names);
        System.out.println("toList(names): " + nameList);

        // Iki degeri degis-tokus
        String[] swapArray = {"Birinci", "Ikinci"};
        System.out.println("Swap oncesi: " + Arrays.toString(swapArray));
        swap(swapArray, 0, 1);
        System.out.println("Swap sonrasi: " + Arrays.toString(swapArray));

        // Ilk ve son elemanlari al
        List<Integer> numbers = Arrays.asList(10, 20, 30, 40, 50);
        System.out.println("\nListe: " + numbers);
        System.out.println("getFirst(numbers): " + getFirst(numbers));
        System.out.println("getLast(numbers): " + getLast(numbers));

        // Sayma islemi
        List<String> items = Arrays.asList("a", "b", "a", "c", "a", "b");
        long aCount = count(items, "a");
        System.out.println("\n\"a\" sayisi: " + aCount);
    }

    /**
     * TIP CIKARIMI (TYPE INFERENCE)
     * ------------------------------
     * Derleyici cogu durumda tip parametresini otomatik cikarir.
     */
    private static void demonstrateTypeInference() {
        System.out.println("\n5. TIP CIKARIMI (TYPE INFERENCE)");
        System.out.println("-".repeat(40));

        // Acik tip belirtme
        String explicit = GenericMethods.<String>identity("Explicit");
        System.out.println("Acik tip belirtme: " + explicit);

        // Tip cikarimi - onerilir
        String inferred = identity("Inferred");
        System.out.println("Tip cikarimi: " + inferred);

        // Karmasik tip cikarimi
        List<String> strings = Arrays.asList("x", "y", "z");
        List<String> copy = copyList(strings);
        System.out.println("\nKopyalanan liste: " + copy);

        // Method referansi ile
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        List<String> strNums = mapList(nums, String::valueOf);
        System.out.println("Donusturulen liste: " + strNums);
    }

    // =========================================================
    // GENERIC METOD TANIMLARI
    // =========================================================

    /**
     * Herhangi bir tipi yazdiran generic metod
     * @param <T> Yazdirilacak degerin tipi
     * @param value Yazdirilacak deger
     */
    public static <T> void print(T value) {
        System.out.println("print(" + value.getClass().getSimpleName() + "): " + value);
    }

    /**
     * Ayni degeri donduren identity metodu
     * @param <T> Deger tipi
     * @param value Deger
     * @return Ayni deger
     */
    public static <T> T identity(T value) {
        return value;
    }

    /**
     * Iki degerden Pair olusturan metod
     * @param <F> Birinci deger tipi
     * @param <S> Ikinci deger tipi
     */
    public static <F, S> Pair<F, S> makePair(F first, S second) {
        return new Pair<>(first, second);
    }

    /**
     * Bir tipi baska bir tipe donusturen metod
     * @param <T> Kaynak tip
     * @param <R> Hedef tip
     */
    public static <T, R> R convert(T value, java.util.function.Function<T, R> converter) {
        return converter.apply(value);
    }

    /**
     * Number tiplerinin toplamini hesaplayan metod
     * Bounded type parameter kullanir
     * @param <T> Number'dan turetilen tip
     */
    public static <T extends Number> double sum(T[] numbers) {
        double total = 0;
        for (T num : numbers) {
            total += num.doubleValue();
        }
        return total;
    }

    /**
     * Comparable tiplerden kucuk olani donduren metod
     * @param <T> Comparable interface'ini uygulayan tip
     */
    public static <T extends Comparable<T>> T minimum(T a, T b) {
        return a.compareTo(b) <= 0 ? a : b;
    }

    /**
     * Comparable tiplerden buyuk olani donduren metod
     * @param <T> Comparable interface'ini uygulayan tip
     */
    public static <T extends Comparable<T>> T maximum(T a, T b) {
        return a.compareTo(b) >= 0 ? a : b;
    }

    /**
     * Array'i List'e donusturen metod
     * @param <T> Eleman tipi
     */
    public static <T> List<T> toList(T[] array) {
        List<T> list = new ArrayList<>();
        for (T item : array) {
            list.add(item);
        }
        return list;
    }

    /**
     * Array icerisinde iki elemani degistiren metod
     * @param <T> Eleman tipi
     */
    public static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Liste'nin ilk elemanini donduren metod
     * @param <T> Eleman tipi
     */
    public static <T> T getFirst(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    /**
     * Liste'nin son elemanini donduren metod
     * @param <T> Eleman tipi
     */
    public static <T> T getLast(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(list.size() - 1);
    }

    /**
     * Belirli bir degerin kac kez gectigini sayan metod
     * @param <T> Eleman tipi
     */
    public static <T> long count(List<T> list, T target) {
        return list.stream().filter(item -> item.equals(target)).count();
    }

    /**
     * Liste'yi kopyalayan metod
     * @param <T> Eleman tipi
     */
    public static <T> List<T> copyList(List<T> source) {
        return new ArrayList<>(source);
    }

    /**
     * Liste elemanlarini donusturen map metodu
     * @param <T> Kaynak tip
     * @param <R> Hedef tip
     */
    public static <T, R> List<R> mapList(List<T> source, java.util.function.Function<T, R> mapper) {
        List<R> result = new ArrayList<>();
        for (T item : source) {
            result.add(mapper.apply(item));
        }
        return result;
    }

    // =========================================================
    // YARDIMCI SINIFLAR
    // =========================================================

    static class Pair<F, S> {
        private final F first;
        private final S second;

        public Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public String toString() {
            return "(" + first + ", " + second + ")";
        }
    }
}
