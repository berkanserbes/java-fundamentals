package com.fundamentals.packages.models;

/**
 * Person model class
 * 
 * Bu sınıf 'models' package'inde bulunur.
 * Veri modellerini temsil eder.
 */
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void displayInfo() {
        System.out.println("Person: " + name + ", Age: " + age);
    }
}
