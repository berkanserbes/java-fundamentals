package com.fundamentals.generics;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * TIP SILME (TYPE ERASURE)
 * =============================================================
 * 
 * Java'da generics derleme zamani (compile-time) ozelligidir.
 * Calisma zamaninda (runtime) tip bilgisi SILINIR.
 * 
 * TYPE ERASURE NEDIR?
 * -------------------
 * Derleyici, generic tip parametrelerini:
 * - Unbounded (<T>) -> Object ile degistirir
 * - Bounded (<T extends Number>) -> Number ile degistirir
 * - Gerekli yerlere cast islemleri ekler
 * 
 * TYPE ERASURE SONUCLARI:
 * -----------------------
 * 1. Runtime'da generic tip bilgisi yoktur
 * 2. Primitive tipler kullanilamaz (int yerine Integer)
 * 3. instanceof generic tiplerle kullanilamaz
 * 4. Generic tip dizisi olusturulamaz
 * 5. Static alanlar tip parametresine bagli olamaz
 */
public class TypeErasure {

    public static void demonstrate() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TIP SILME (TYPE ERASURE)");
        System.out.println("=".repeat(60));

        demonstrateBasicErasure();
        demonstrateErasureEffects();
        demonstrateReflectionWithGenerics();
        demonstrateBridgeMethods();
    }

    /**
     * TEMEL TYPE ERASURE
     */
    private static void demonstrateBasicErasure() {
        System.out.println("\n1. TEMEL TYPE ERASURE");
        System.out.println("-".repeat(40));

        List<String> stringList = new ArrayList<>();
        List<Integer> intList = new ArrayList<>();

        // Runtime'da ayni tiptirler!
        Class<?> stringListClass = stringList.getClass();
        Class<?> intListClass = intList.getClass();

        System.out.println("String List class: " + stringListClass.getName());
        System.out.println("Integer List class: " + intListClass.getName());
        System.out.println("Ayni sinif mi: " + (stringListClass == intListClass));

        // getClass() generic tip bilgisi vermez
        System.out.println("\nstringList.getClass(): " + stringList.getClass());
        System.out.println("Bu ciktida <String> bilgisi YOK!");

        // Container ornegi
        Container<String> strContainer = new Container<>("Test");
        Container<Integer> intContainer = new Container<>(123);

        System.out.println("\nContainer<String> class: " + strContainer.getClass());
        System.out.println("Container<Integer> class: " + intContainer.getClass());
        System.out.println("Ayni sinif mi: " + (strContainer.getClass() == intContainer.getClass()));
    }

    /**
     * TYPE ERASURE ETKILERI - Kisitlamalar
     */
    private static void demonstrateErasureEffects() {
        System.out.println("\n2. TYPE ERASURE KISITLAMALARI");
        System.out.println("-".repeat(40));

        // KISITLAMA 1: instanceof generic tiplerle kullanilamaz
        System.out.println("KISITLAMA 1: instanceof generic tiplerle kullanilamaz");
        List<String> list = new ArrayList<>();
        
        // if (list instanceof ArrayList<String>) { }  // DERLEME HATASI!
        // Bunun yerine raw type kullanilabilir:
        if (list instanceof ArrayList<?>) {
            System.out.println("  list bir ArrayList");
        }
        if (list instanceof List<?>) {
            System.out.println("  list bir List");
        }

        // KISITLAMA 2: Generic tip dizisi olusturulamaz
        System.out.println("\nKISITLAMA 2: Generic tip dizisi olusturulamaz");
        // List<String>[] arrayOfLists = new ArrayList<String>[10];  // DERLEME HATASI!
        System.out.println("  List<String>[] olusturulamaz");
        System.out.println("  Cozum: List<List<String>> kullan");

        List<List<String>> listOfLists = new ArrayList<>();
        listOfLists.add(new ArrayList<>());
        listOfLists.get(0).add("Alternatif cozum");
        System.out.println("  " + listOfLists);

        // KISITLAMA 3: new T() kullanilamaz
        System.out.println("\nKISITLAMA 3: new T() kullanilamaz");
        System.out.println("  Generic tip parametresi ile nesne olusturulamaz");
        System.out.println("  Cozum: Supplier veya Class<T> kullan");

        // Supplier ile cozum ornegi
        Container<StringBuilder> container = createWithSupplier(StringBuilder::new);
        container.getValue().append("Supplier ile olusturuldu");
        System.out.println("  " + container.getValue());

        // KISITLAMA 4: Primitive tipler kullanilamaz
        System.out.println("\nKISITLAMA 4: Primitive tipler kullanilamaz");
        // List<int> intList = new ArrayList<int>();  // DERLEME HATASI!
        System.out.println("  List<int> kullanilamaz, List<Integer> kullan");
    }

    /**
     * REFLECTION ILE GENERIC BILGI
     */
    private static void demonstrateReflectionWithGenerics() {
        System.out.println("\n3. REFLECTION ILE GENERIC BILGI");
        System.out.println("-".repeat(40));

        // Method imzalarindan generic bilgi alinabilir
        try {
            Method method = TypeErasure.class.getDeclaredMethod("sampleMethod", List.class);
            
            System.out.println("Method adi: " + method.getName());
            
            // Parametre tipleri (erasure sonrasi)
            Class<?>[] paramTypes = method.getParameterTypes();
            System.out.println("Parametre tipi (erased): " + paramTypes[0].getName());
            
            // Generic parametre bilgisi
            java.lang.reflect.Type[] genericParamTypes = method.getGenericParameterTypes();
            System.out.println("Generic parametre tipi: " + genericParamTypes[0]);
            
            // Donus tipi
            System.out.println("Donus tipi (erased): " + method.getReturnType().getName());
            System.out.println("Generic donus tipi: " + method.getGenericReturnType());
            
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * BRIDGE METHODS
     * Derleyicinin type erasure icin olusturdugu yardimci metodlar
     */
    private static void demonstrateBridgeMethods() {
        System.out.println("\n4. BRIDGE METHODS");
        System.out.println("-".repeat(40));

        System.out.println("Bridge method: Derleyicinin erasure icin olusturdugu metodlar");
        System.out.println();

        // IntegerContainer sinifinin metodlarini incele
        Method[] methods = IntegerContainer.class.getDeclaredMethods();
        
        System.out.println("IntegerContainer metodlari:");
        for (Method m : methods) {
            String bridgeInfo = m.isBridge() ? " [BRIDGE]" : "";
            System.out.println("  " + m.getName() + 
                "(" + getParamTypesString(m) + ") -> " + 
                m.getReturnType().getSimpleName() + bridgeInfo);
        }

        System.out.println("\nAciklama:");
        System.out.println("  - set(Integer) : Gercek metod");
        System.out.println("  - set(Object)  : Bridge metod (erasure sonucu)");
        System.out.println("  Bridge metod, Object parametresi alir ve");
        System.out.println("  Integer'a cast ederek gercek metodu cagirir");
    }

    // Helper method
    private static String getParamTypesString(Method m) {
        StringBuilder sb = new StringBuilder();
        for (Class<?> param : m.getParameterTypes()) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(param.getSimpleName());
        }
        return sb.toString();
    }

    // Sample method for reflection demo
    public List<String> sampleMethod(List<Integer> numbers) {
        return new ArrayList<>();
    }

    // Supplier ile nesne olusturma
    public static <T> Container<T> createWithSupplier(java.util.function.Supplier<T> supplier) {
        return new Container<>(supplier.get());
    }

    // =========================================================
    // YARDIMCI SINIFLAR
    // =========================================================

    static class Container<T> {
        private T value;

        public Container(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }

    /**
     * Generic arayuz
     */
    interface Box<T> {
        void set(T value);
        T get();
    }

    /**
     * Somut tip ile uygulama - bridge method olusturulur
     */
    static class IntegerContainer implements Box<Integer> {
        private Integer value;

        // Bu metodun yaninda derleyici bir bridge method olusturur:
        // public void set(Object value) { set((Integer)value); }
        @Override
        public void set(Integer value) {
            this.value = value;
        }

        @Override
        public Integer get() {
            return value;
        }
    }
}
