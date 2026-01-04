package com.fundamentals.generics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GENERIC ARAYUZLER (INTERFACES)
 * =============================================================
 * 
 * Generic arayuzler, tip parametreleri alan arayuzlerdir.
 * Java'nin standart kutuphanesinde bircok generic arayuz bulunur:
 * Comparable, Comparator, Iterator, Iterable, List, Set, Map, Function, vb.
 */
public class GenericInterfaces {

    public static void demonstrate() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("GENERIC ARAYUZLER");
        System.out.println("=".repeat(60));

        demonstrateBasicGenericInterface();
        demonstrateComparableInterface();
        demonstrateFunctionalInterfaces();
    }

    private static void demonstrateBasicGenericInterface() {
        System.out.println("\n1. TEMEL GENERIC ARAYUZ");
        System.out.println("-".repeat(40));

        DataStore<String> stringStore = new GenericDataStore<>();
        stringStore.add("Merhaba");
        stringStore.add("Dunya");
        System.out.println("String Store: " + stringStore.getAll());

        DataStore<Integer> intStore = new GenericDataStore<>();
        intStore.add(10);
        intStore.add(20);
        System.out.println("Integer Store: " + intStore.getAll());
    }

    private static void demonstrateComparableInterface() {
        System.out.println("\n2. COMPARABLE<T> ARAYUZU");
        System.out.println("-".repeat(40));

        List<Student> students = new ArrayList<>();
        students.add(new Student("Ali", 85));
        students.add(new Student("Veli", 92));
        students.add(new Student("Ayse", 78));

        System.out.println("Orijinal:");
        students.forEach(s -> System.out.println("  " + s));

        students.sort(null);
        System.out.println("\nNota gore siralanmis:");
        students.forEach(s -> System.out.println("  " + s));
    }

    private static void demonstrateFunctionalInterfaces() {
        System.out.println("\n3. FONKSIYONEL GENERIC ARAYUZLER");
        System.out.println("-".repeat(40));

        Transformer<String, Integer> strToInt = s -> s.length();
        System.out.println("\"Merhaba\".length(): " + strToInt.transform("Merhaba"));

        Validator<String> emailValidator = email -> 
            email != null && email.contains("@");
        System.out.println("Email gecerli mi: " + emailValidator.isValid("test@mail.com"));

        Factory<StringBuilder> sbFactory = () -> new StringBuilder();
        System.out.println("Factory: " + sbFactory.create().append("Test"));
    }

    // ARAYUZ TANIMLARI
    interface DataStore<T> {
        void add(T item);
        T get(int index);
        List<T> getAll();
    }

    @FunctionalInterface
    interface Transformer<T, R> {
        R transform(T input);
    }

    @FunctionalInterface
    interface Validator<T> {
        boolean isValid(T value);
    }

    @FunctionalInterface
    interface Factory<T> {
        T create();
    }

    // UYGULAMALAR
    static class GenericDataStore<T> implements DataStore<T> {
        private List<T> data = new ArrayList<>();
        public void add(T item) { data.add(item); }
        public T get(int index) { return data.get(index); }
        public List<T> getAll() { return new ArrayList<>(data); }
    }

    static class Student implements Comparable<Student> {
        private String name;
        private int grade;

        public Student(String name, int grade) {
            this.name = name;
            this.grade = grade;
        }

        @Override
        public int compareTo(Student other) {
            return Integer.compare(other.grade, this.grade);
        }

        @Override
        public String toString() {
            return name + ": " + grade;
        }
    }
}
