package com.fundamentals.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * SINIRLANDIRILMIS TIP PARAMETRELERI (BOUNDED TYPE PARAMETERS)
 * =============================================================
 * 
 * Tip parametreleri belirli bir sinif veya arayuz ile sinirlandirilabilir.
 * Bu sayede tip parametresinin belirli metodlara veya ozelliklere sahip
 * olmasi garanti altina alinir.
 * 
 * UPPER BOUND (extends):
 * ----------------------
 * <T extends Type> - T, Type veya Type'in alt sinifi olmalidir
 * - Siniflar icin: <T extends Number>
 * - Arayuzler icin: <T extends Comparable<T>>
 * - Coklu sinir: <T extends Number & Comparable<T>>
 * 
 * NOT: Hem sinif hem arayuz kullanildiginda sinif ilk yazilmalidir.
 */
public class BoundedTypeParameters {

    public static void demonstrate() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("SINIRLANDIRILMIS TIP PARAMETRELERI");
        System.out.println("=".repeat(60));

        demonstrateUpperBound();
        demonstrateMultipleBounds();
        demonstrateBoundedClass();
        demonstrateRecursiveBound();
    }

    /**
     * UPPER BOUND - extends KULLANIMI
     */
    private static void demonstrateUpperBound() {
        System.out.println("\n1. UPPER BOUND (extends)");
        System.out.println("-".repeat(40));

        // Number ve alt siniflari ile calisir
        Integer[] intArray = {5, 2, 9, 1, 7};
        Double[] doubleArray = {3.14, 1.41, 2.71, 1.62};
        Long[] longArray = {100L, 200L, 300L};

        System.out.println("Integer toplam: " + calculateSum(intArray));
        System.out.println("Double toplam: " + calculateSum(doubleArray));
        System.out.println("Long toplam: " + calculateSum(longArray));

        System.out.println("\nInteger ortalama: " + calculateAverage(intArray));
        System.out.println("Double ortalama: " + calculateAverage(doubleArray));

        // String[] stringArray = {"a", "b"};
        // calculateSum(stringArray);  // DERLEME HATASI! String, Number degil
    }

    /**
     * COKLU SINIR (MULTIPLE BOUNDS)
     */
    private static void demonstrateMultipleBounds() {
        System.out.println("\n2. COKLU SINIR (MULTIPLE BOUNDS)");
        System.out.println("-".repeat(40));

        Integer[] numbers = {30, 10, 50, 20, 40};
        System.out.println("Array: [30, 10, 50, 20, 40]");

        Integer min = findMin(numbers);
        Integer max = findMax(numbers);

        System.out.println("Minimum: " + min);
        System.out.println("Maximum: " + max);

        // Comparable olan herhangi bir tip ile calisir
        String[] words = {"elma", "armut", "cilek", "muz"};
        System.out.println("\nKelimeler: [elma, armut, cilek, muz]");
        System.out.println("Minimum: " + findMin(words));
        System.out.println("Maximum: " + findMax(words));
    }

    /**
     * BOUNDED GENERIC SINIF
     */
    private static void demonstrateBoundedClass() {
        System.out.println("\n3. BOUNDED GENERIC SINIF");
        System.out.println("-".repeat(40));

        // NumberBox sadece Number tiplerini kabul eder
        NumberBox<Integer> intBox = new NumberBox<>(42);
        NumberBox<Double> doubleBox = new NumberBox<>(3.14159);
        NumberBox<Long> longBox = new NumberBox<>(1000000L);

        System.out.println("Integer box: " + intBox.getValue());
        System.out.println("  intValue: " + intBox.intValue());
        System.out.println("  doubleValue: " + intBox.doubleValue());

        System.out.println("\nDouble box: " + doubleBox.getValue());
        System.out.println("  intValue: " + doubleBox.intValue());
        System.out.println("  doubleValue: " + doubleBox.doubleValue());

        // NumberBox<String> strBox = new NumberBox<>("test"); // DERLEME HATASI!

        // ComparableContainer
        ComparableContainer<Integer> container = new ComparableContainer<>();
        container.add(30);
        container.add(10);
        container.add(50);
        container.add(20);

        System.out.println("\nComparableContainer:");
        System.out.println("  Elemanlar: " + container.getAll());
        System.out.println("  Minimum: " + container.getMin());
        System.out.println("  Maximum: " + container.getMax());
    }

    /**
     * RECURSIVE BOUND (Kendine referans veren sinir)
     */
    private static void demonstrateRecursiveBound() {
        System.out.println("\n4. RECURSIVE TYPE BOUND");
        System.out.println("-".repeat(40));

        // Builder pattern ornegi
        PersonBuilder builder = new PersonBuilder()
            .withName("Ahmet")
            .withAge(30)
            .withCity("Istanbul");

        Person person = builder.build();
        System.out.println("Olusturulan kisi: " + person);

        // Employee builder - inheritance ile
        EmployeeBuilder empBuilder = new EmployeeBuilder()
            .withName("Mehmet")
            .withAge(25)
            .withCity("Ankara")
            .withDepartment("IT")
            .withSalary(15000);

        Employee employee = empBuilder.build();
        System.out.println("Olusturulan calisan: " + employee);
    }

    // =========================================================
    // GENERIC METODLAR
    // =========================================================

    /**
     * Number tiplerinin toplamini hesaplar
     * @param <T> Number veya alt sinifi
     */
    public static <T extends Number> double calculateSum(T[] numbers) {
        double sum = 0;
        for (T num : numbers) {
            sum += num.doubleValue();
        }
        return sum;
    }

    /**
     * Number tiplerinin ortalamasini hesaplar
     */
    public static <T extends Number> double calculateAverage(T[] numbers) {
        return calculateSum(numbers) / numbers.length;
    }

    /**
     * Comparable tiplerden minimumu bulur
     * @param <T> Comparable interface'ini uygulayan tip
     */
    public static <T extends Comparable<T>> T findMin(T[] array) {
        if (array == null || array.length == 0) return null;
        T min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(min) < 0) {
                min = array[i];
            }
        }
        return min;
    }

    /**
     * Comparable tiplerden maximumu bulur
     */
    public static <T extends Comparable<T>> T findMax(T[] array) {
        if (array == null || array.length == 0) return null;
        T max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(max) > 0) {
                max = array[i];
            }
        }
        return max;
    }

    // =========================================================
    // BOUNDED GENERIC SINIFLAR
    // =========================================================

    /**
     * Sadece Number tiplerini kabul eden box
     */
    static class NumberBox<T extends Number> {
        private T value;

        public NumberBox(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public int intValue() {
            return value.intValue();
        }

        public double doubleValue() {
            return value.doubleValue();
        }

        public long longValue() {
            return value.longValue();
        }
    }

    /**
     * Comparable tiplerle calisan container
     */
    static class ComparableContainer<T extends Comparable<T>> {
        private List<T> items = new ArrayList<>();

        public void add(T item) {
            items.add(item);
        }

        public List<T> getAll() {
            return new ArrayList<>(items);
        }

        public T getMin() {
            if (items.isEmpty()) return null;
            T min = items.get(0);
            for (T item : items) {
                if (item.compareTo(min) < 0) {
                    min = item;
                }
            }
            return min;
        }

        public T getMax() {
            if (items.isEmpty()) return null;
            T max = items.get(0);
            for (T item : items) {
                if (item.compareTo(max) > 0) {
                    max = item;
                }
            }
            return max;
        }
    }

    // =========================================================
    // RECURSIVE BOUND ORNEKLERI
    // =========================================================

    static class Person {
        protected String name;
        protected int age;
        protected String city;

        @Override
        public String toString() {
            return name + ", " + age + " yas, " + city;
        }
    }

    static class Employee extends Person {
        private String department;
        private double salary;

        @Override
        public String toString() {
            return super.toString() + ", " + department + ", " + salary + " TL";
        }
    }

    /**
     * Self-referencing generic builder
     * @param <T> Builder'in kendisi
     */
    @SuppressWarnings("unchecked")
    static abstract class AbstractBuilder<T extends AbstractBuilder<T>> {
        protected String name;
        protected int age;
        protected String city;

        public T withName(String name) {
            this.name = name;
            return (T) this;
        }

        public T withAge(int age) {
            this.age = age;
            return (T) this;
        }

        public T withCity(String city) {
            this.city = city;
            return (T) this;
        }
    }

    static class PersonBuilder extends AbstractBuilder<PersonBuilder> {
        public Person build() {
            Person p = new Person();
            p.name = this.name;
            p.age = this.age;
            p.city = this.city;
            return p;
        }
    }

    static class EmployeeBuilder extends AbstractBuilder<EmployeeBuilder> {
        private String department;
        private double salary;

        public EmployeeBuilder withDepartment(String department) {
            this.department = department;
            return this;
        }

        public EmployeeBuilder withSalary(double salary) {
            this.salary = salary;
            return this;
        }

        public Employee build() {
            Employee e = new Employee();
            e.name = this.name;
            e.age = this.age;
            e.city = this.city;
            e.department = this.department;
            e.salary = this.salary;
            return e;
        }
    }
}
