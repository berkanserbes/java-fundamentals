package com.fundamentals.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * WILDCARD KULLANIMI
 * =============================================================
 * 
 * Wildcard (?), bilinmeyen bir tipi temsil eder ve generic tiplerin
 * esnekligini arttirir. Ozellikle metod parametrelerinde kullanilir.
 * 
 * WILDCARD TURLERI:
 * -----------------
 * 1. Unbounded Wildcard: <?> - Herhangi bir tip
 * 2. Upper Bounded: <? extends Type> - Type veya alt siniflari
 * 3. Lower Bounded: <? super Type> - Type veya ust siniflari
 * 
 * PECS KURALI (Producer Extends, Consumer Super):
 * ------------------------------------------------
 * - Veri URETECEKSEN (okuyacaksan): extends kullan
 * - Veri TUKETECEKSEN (yazacaksan): super kullan
 * - Her ikisi de gerekiyorsa: wildcard kullanma, generic metod yaz
 */
public class Wildcards {

    public static void demonstrate() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("WILDCARD KULLANIMI");
        System.out.println("=".repeat(60));

        demonstrateUnboundedWildcard();
        demonstrateUpperBoundedWildcard();
        demonstrateLowerBoundedWildcard();
        demonstratePECSPrinciple();
    }

    /**
     * UNBOUNDED WILDCARD (<?>)
     * Herhangi bir tipi kabul eder, en esnek wildcard.
     */
    private static void demonstrateUnboundedWildcard() {
        System.out.println("\n1. UNBOUNDED WILDCARD (<?>)");
        System.out.println("-".repeat(40));

        List<String> strings = List.of("Java", "Generics", "Wildcard");
        List<Integer> integers = List.of(1, 2, 3, 4, 5);
        List<Double> doubles = List.of(1.1, 2.2, 3.3);

        System.out.println("String listesi yazdiriliyor:");
        printList(strings);

        System.out.println("\nInteger listesi yazdiriliyor:");
        printList(integers);

        System.out.println("\nDouble listesi yazdiriliyor:");
        printList(doubles);

        // Liste boyutlari
        System.out.println("\nListe boyutlari:");
        System.out.println("  Strings: " + getListSize(strings));
        System.out.println("  Integers: " + getListSize(integers));
    }

    /**
     * UPPER BOUNDED WILDCARD (<? extends Type>)
     * Belirli bir tipten veya onun alt siniflarindan olusan tipler.
     */
    private static void demonstrateUpperBoundedWildcard() {
        System.out.println("\n2. UPPER BOUNDED WILDCARD (<? extends Type>)");
        System.out.println("-".repeat(40));

        // Number ve alt siniflari
        List<Integer> intList = List.of(10, 20, 30);
        List<Double> doubleList = List.of(1.5, 2.5, 3.5);
        List<Number> numberList = List.of(1, 2.5, 3L);

        System.out.println("Integer listesi toplami: " + sumOfList(intList));
        System.out.println("Double listesi toplami: " + sumOfList(doubleList));
        System.out.println("Number listesi toplami: " + sumOfList(numberList));

        // Hayvan hiyerarsisi ornegi
        List<Animal> animals = new ArrayList<>();
        animals.add(new Dog("Karabas"));
        animals.add(new Cat("Tekir"));

        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog("Boncuk"));
        dogs.add(new Dog("Pamuk"));

        List<Cat> cats = new ArrayList<>();
        cats.add(new Cat("Minnak"));

        System.out.println("\nTum hayvanlarin sesleri:");
        printAnimalSounds(animals);
        printAnimalSounds(dogs);  // Dog extends Animal
        printAnimalSounds(cats);  // Cat extends Animal
    }

    /**
     * LOWER BOUNDED WILDCARD (<? super Type>)
     * Belirli bir tipten veya onun ust siniflarindan olusan tipler.
     */
    private static void demonstrateLowerBoundedWildcard() {
        System.out.println("\n3. LOWER BOUNDED WILDCARD (<? super Type>)");
        System.out.println("-".repeat(40));

        // Integer ve ust siniflarina (Number, Object) yazabilir
        List<Integer> intList = new ArrayList<>();
        List<Number> numList = new ArrayList<>();
        List<Object> objList = new ArrayList<>();

        addNumbers(intList);
        addNumbers(numList);
        addNumbers(objList);

        System.out.println("Integer List: " + intList);
        System.out.println("Number List: " + numList);
        System.out.println("Object List: " + objList);

        // Hayvan hiyerarsisi - ekleme ornegi
        List<Animal> animalList = new ArrayList<>();
        List<Object> objectList = new ArrayList<>();

        addDogs(animalList);  // Animal, Dog'un ust sinifi
        addDogs(objectList);  // Object, Dog'un ust sinifi

        System.out.println("\nAnimal List: " + animalList);
    }

    /**
     * PECS KURALI (Producer Extends, Consumer Super)
     */
    private static void demonstratePECSPrinciple() {
        System.out.println("\n4. PECS KURALI");
        System.out.println("-".repeat(40));

        System.out.println("PECS = Producer Extends, Consumer Super");
        System.out.println();

        // Producer - extends kullan (okuma)
        List<Integer> source = List.of(1, 2, 3, 4, 5);
        
        // Consumer - super kullan (yazma)
        List<Number> destination = new ArrayList<>();
        
        // Kopyalama islemi
        copy(source, destination);
        System.out.println("Kaynak: " + source);
        System.out.println("Hedef: " + destination);

        // Baska bir ornek
        List<Double> doubleSource = List.of(1.1, 2.2, 3.3);
        List<Object> objectDest = new ArrayList<>();
        copy(doubleSource, objectDest);
        System.out.println("\nDouble -> Object kopyalama: " + objectDest);

        // Aciklama
        System.out.println("\nPECS Kurali Aciklamasi:");
        System.out.println("  - extends: Listeden OKUYORSUN, tipini biliyorsun");
        System.out.println("  - super: Listeye YAZIYORSUN, ust tip kabul eder");
    }

    // =========================================================
    // WILDCARD KULLANAN METODLAR
    // =========================================================

    /**
     * Unbounded wildcard - herhangi bir liste yazdirir
     */
    public static void printList(List<?> list) {
        for (Object elem : list) {
            System.out.print(elem + " ");
        }
        System.out.println();
    }

    /**
     * Unbounded wildcard - liste boyutunu dondurur
     */
    public static int getListSize(List<?> list) {
        return list.size();
    }

    /**
     * Upper bounded - Number ve alt siniflarinin toplamini hesaplar
     */
    public static double sumOfList(List<? extends Number> list) {
        double sum = 0;
        for (Number n : list) {
            sum += n.doubleValue();
        }
        return sum;
    }

    /**
     * Upper bounded - Animal ve alt siniflari icin ses cikarma
     */
    public static void printAnimalSounds(List<? extends Animal> animals) {
        for (Animal animal : animals) {
            animal.makeSound();
        }
    }

    /**
     * Lower bounded - Integer veya ust siniflarini kabul eden listeye ekleme
     */
    public static void addNumbers(List<? super Integer> list) {
        list.add(1);
        list.add(2);
        list.add(3);
    }

    /**
     * Lower bounded - Dog veya ust siniflarini kabul eden listeye ekleme
     */
    public static void addDogs(List<? super Dog> list) {
        list.add(new Dog("Rex"));
        list.add(new Dog("Max"));
    }

    /**
     * PECS ornegi - kaynak extends, hedef super
     * @param <T> Kopyalanacak eleman tipi
     */
    public static <T> void copy(List<? extends T> source, List<? super T> dest) {
        for (T item : source) {
            dest.add(item);
        }
    }

    // =========================================================
    // YARDIMCI SINIFLAR - HAYVAN HIYERARSISI
    // =========================================================

    static class Animal {
        protected String name;

        public Animal(String name) {
            this.name = name;
        }

        public void makeSound() {
            System.out.println("  " + name + " ses cikariyor");
        }

        @Override
        public String toString() {
            return name;
        }
    }

    static class Dog extends Animal {
        public Dog(String name) {
            super(name);
        }

        @Override
        public void makeSound() {
            System.out.println("  " + name + ": Hav hav!");
        }
    }

    static class Cat extends Animal {
        public Cat(String name) {
            super(name);
        }

        @Override
        public void makeSound() {
            System.out.println("  " + name + ": Miyav!");
        }
    }
}
