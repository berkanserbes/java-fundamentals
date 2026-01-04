package com.fundamentals.generics;

import java.util.*;
import java.util.function.*;

/**
 * GENERICS EN IYI UYGULAMALAR (BEST PRACTICES)
 * =============================================================
 * 
 * Bu sinif, Generics kullaniminda dikkat edilmesi gereken
 * en iyi uygulamalari ve yaygin hatalari gosterir.
 */
public class GenericBestPractices {

    public static void demonstrate() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("GENERICS EN IYI UYGULAMALAR");
        System.out.println("=".repeat(60));

        demonstrateAvoidRawTypes();
        demonstratePreferLists();
        demonstratePECSInPractice();
        demonstrateTypeTokens();
        demonstrateGenericSingleton();
    }

    /**
     * RAW TYPE KULLANMAKTAN KACIN
     */
    private static void demonstrateAvoidRawTypes() {
        System.out.println("\n1. RAW TYPE KULLANMAKTAN KACIN");
        System.out.println("-".repeat(40));

        // YANLIS: Raw type kullanimi
        @SuppressWarnings("rawtypes")
        List rawList = new ArrayList();
        rawList.add("String");
        rawList.add(123);  // Farkli tipler eklenebilir!
        System.out.println("Raw List (yanlis): " + rawList);
        System.out.println("  SORUN: Tip guvenligi yok, ClassCastException riski!");

        // DOGRU: Parameterized type kullanimi
        List<String> paramList = new ArrayList<>();
        paramList.add("String");
        // paramList.add(123);  // DERLEME HATASI - guvenli!
        System.out.println("\nParameterized List (dogru): " + paramList);
        System.out.println("  AVANTAJ: Derleme zamaninda tip kontrolu");

        // Bilinmeyen tip icin <?> kullan
        List<?> unknownList = rawList;
        System.out.println("\nWildcard <?> ile (kabul edilebilir): " + unknownList.size());
    }

    /**
     * ARRAY YERINE LIST TERCIH ET
     */
    private static void demonstratePreferLists() {
        System.out.println("\n2. ARRAY YERINE LIST TERCIH ET");
        System.out.println("-".repeat(40));

        // Array covariant (birlikte degisken) - tehlikeli!
        System.out.println("Array covariance problemi:");
        Object[] objectArray = new String[3];  // Derlenir!
        try {
            objectArray[0] = "OK";
            objectArray[1] = 123;  // RUNTIME HATASI!
        } catch (ArrayStoreException e) {
            System.out.println("  ArrayStoreException: " + e.getMessage());
        }

        // List invariant (degismez) - guvenli!
        System.out.println("\nList invariance guvenligi:");
        // List<Object> objectList = new ArrayList<String>();  // DERLEME HATASI!
        List<String> stringList = new ArrayList<>();
        stringList.add("Guvenli");
        System.out.println("  List<String>: " + stringList);
        System.out.println("  AVANTAJ: Hatalar derleme zamaninda yakalanir");

        // Generic array olusturma sorunu
        System.out.println("\nGeneric array sorunu:");
        System.out.println("  T[] array = new T[10];  // GECERSIZ!");
        System.out.println("  Cozum: List<T> kullan veya @SuppressWarnings ile Array.newInstance");
    }

    /**
     * PECS KURALI PRATIKTE
     */
    private static void demonstratePECSInPractice() {
        System.out.println("\n3. PECS KURALI PRATIKTE");
        System.out.println("-".repeat(40));

        System.out.println("PECS = Producer Extends, Consumer Super");
        System.out.println();

        // Ornek: Stack sinifi
        GenericStack<Number> numberStack = new GenericStack<>();
        
        // Producer (extends) - veri kaynagi olarak kullaniyoruz
        List<Integer> integers = Arrays.asList(1, 2, 3);
        numberStack.pushAll(integers);  // Integer, Number'in alt tipi
        System.out.println("pushAll(List<Integer>): Stack simdi Number'lar iceriyor");
        
        List<Double> doubles = Arrays.asList(1.1, 2.2);
        numberStack.pushAll(doubles);  // Double da Number'in alt tipi
        System.out.println("pushAll(List<Double>): Double'lar da eklendi");

        // Consumer (super) - veri hedefi olarak kullaniyoruz
        List<Object> objects = new ArrayList<>();
        numberStack.popAll(objects);  // Object, Number'in ust tipi
        System.out.println("popAll(List<Object>): " + objects);

        System.out.println("\nKural Ozeti:");
        System.out.println("  - OKU (get): <? extends T>");
        System.out.println("  - YAZ (put): <? super T>");
    }

    /**
     * TYPE TOKEN PATTERN
     */
    private static void demonstrateTypeTokens() {
        System.out.println("\n4. TYPE TOKEN PATTERN");
        System.out.println("-".repeat(40));

        System.out.println("Type token: Runtime'da tip bilgisi tasima yontemi");
        System.out.println();

        // TypeSafe container
        TypeSafeContainer container = new TypeSafeContainer();
        
        container.put(String.class, "Hello");
        container.put(Integer.class, 42);
        container.put(Double.class, 3.14);

        String str = container.get(String.class);
        Integer num = container.get(Integer.class);
        Double dbl = container.get(Double.class);

        System.out.println("String: " + str);
        System.out.println("Integer: " + num);
        System.out.println("Double: " + dbl);

        System.out.println("\nClass<T> parametresi tip guvenligini saglar");
    }

    /**
     * GENERIC SINGLETON FACTORY PATTERN
     */
    private static void demonstrateGenericSingleton() {
        System.out.println("\n5. GENERIC SINGLETON FACTORY");
        System.out.println("-".repeat(40));

        // Identity function - farkli tiplerle ayni instance
        Function<String, String> stringIdentity = identityFunction();
        Function<Integer, Integer> intIdentity = identityFunction();

        System.out.println("stringIdentity.apply(\"Test\"): " + stringIdentity.apply("Test"));
        System.out.println("intIdentity.apply(42): " + intIdentity.apply(42));

        // Ayni instance mi kontrol - type erasure sonucu ayni nesne
        Object strIdentityObj = stringIdentity;
        Object intIdentityObj = intIdentity;
        System.out.println("\nAyni instance mi: " + (strIdentityObj == intIdentityObj));
        System.out.println("Type erasure sayesinde ayni singleton kullanilabilir");

        // Empty collection ornekleri
        List<String> emptyStrings = Collections.emptyList();
        List<Integer> emptyInts = Collections.emptyList();
        System.out.println("\nCollections.emptyList() ayni singleton dondurur");
    }

    // =========================================================
    // YARDIMCI SINIFLAR ve METODLAR
    // =========================================================

    /**
     * PECS ornegi icin Stack sinifi
     */
    static class GenericStack<E> {
        private List<E> elements = new ArrayList<>();

        public void push(E element) {
            elements.add(element);
        }

        public E pop() {
            if (elements.isEmpty()) {
                throw new EmptyStackException();
            }
            return elements.remove(elements.size() - 1);
        }

        public boolean isEmpty() {
            return elements.isEmpty();
        }

        /**
         * Producer - extends kullan
         * Kaynak listeden OKUYORUZ
         */
        public void pushAll(Iterable<? extends E> source) {
            for (E element : source) {
                push(element);
            }
        }

        /**
         * Consumer - super kullan
         * Hedef listeye YAZIYORUZ
         */
        public void popAll(Collection<? super E> destination) {
            while (!isEmpty()) {
                destination.add(pop());
            }
        }
    }

    /**
     * Type-safe heterogeneous container
     * Farkli tipleri guvenli sekilde saklar
     */
    static class TypeSafeContainer {
        private Map<Class<?>, Object> container = new HashMap<>();

        public <T> void put(Class<T> type, T instance) {
            container.put(type, instance);
        }

        public <T> T get(Class<T> type) {
            return type.cast(container.get(type));
        }
    }

    /**
     * Generic singleton factory
     */
    private static final Function<Object, Object> IDENTITY_FN = t -> t;

    @SuppressWarnings("unchecked")
    public static <T> Function<T, T> identityFunction() {
        return (Function<T, T>) IDENTITY_FN;
    }
}
