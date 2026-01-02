package com.fundamentals.optionals;

import java.util.Optional;
import java.util.List;
import java.util.stream.Stream;

/**
 * Optional Donusum Metodlari
 */
public class OptionalTransformation {
    
    public static void demonstrate() {
        System.out.println();
        
        // ============================================================
        // 1. map() - Degeri Donustur
        // ============================================================
        System.out.println("[1] map() - Degeri Donustur:");
        System.out.println("------------------------------------------------------------");
        
        Optional<String> name = Optional.of("java programming");
        
        // String -> buyuk harf
        Optional<String> upperName = name.map(String::toUpperCase);
        System.out.println("Orijinal: " + name.get());
        System.out.println("map(toUpperCase): " + upperName.get());
        System.out.println();
        
        // String -> uzunluk (Integer)
        Optional<Integer> length = name.map(String::length);
        System.out.println("map(length): " + length.get());
        System.out.println();
        
        // Bos Optional uzerinde map
        Optional<String> empty = Optional.empty();
        Optional<String> result = empty.map(String::toUpperCase);
        System.out.println("Bos Optional.map(): " + result);  // Optional.empty
        System.out.println();
        
        // Zincirleme map
        Optional<String> text = Optional.of("  hello world  ");
        String processed = text
            .map(String::trim)
            .map(String::toUpperCase)
            .map(s -> s.replace(" ", "_"))
            .orElse("");
        System.out.println("Zincirleme map: " + processed);  // HELLO_WORLD
        System.out.println();
        
        // ============================================================
        // 2. flatMap() - Ic Ice Optional'lari Duzlestir
        // ============================================================
        System.out.println("[2] flatMap() - Ic Ice Optional'lari Duzlestir:");
        System.out.println("------------------------------------------------------------");
        
        // Ornek siniflar
        User user = new User("Ali", new Address("Istanbul"));
        Optional<User> userOpt = Optional.of(user);
        
        // map ile - Optional<Optional<String>> olur!
        Optional<Optional<String>> cityWithMap = userOpt.map(u -> u.getCity());
        System.out.println("map ile: " + cityWithMap);  // Ic ice Optional
        
        // flatMap ile - duzlestirilmis
        Optional<String> cityWithFlatMap = userOpt.flatMap(u -> u.getCity());
        System.out.println("flatMap ile: " + cityWithFlatMap);  // Optional<String>
        System.out.println();
        
        // Gercek senaryo: Zincirleme flatMap
        Optional<String> cityName = userOpt
            .flatMap(u -> u.getAddressOpt())
            .flatMap(a -> a.getCityOpt());
        System.out.println("Zincirleme flatMap: " + cityName);
        System.out.println();
        
        System.out.println("map vs flatMap:");
        System.out.println("  map    -> Function<T, R>         -> Optional<R>");
        System.out.println("  flatMap-> Function<T, Optional<R>> -> Optional<R>");
        System.out.println();
        
        // ============================================================
        // 3. filter() - Kosul Uymuyorsa Bos Optional
        // ============================================================
        System.out.println("[3] filter() - Kosul Uymuyorsa Bos Optional:");
        System.out.println("------------------------------------------------------------");
        
        Optional<Integer> number = Optional.of(42);
        
        // Kosul saglanirsa - ayni Optional
        Optional<Integer> even = number.filter(n -> n % 2 == 0);
        System.out.println("42 cift mi? filter(n % 2 == 0): " + even);
        
        // Kosul saglanmazsa - bos Optional
        Optional<Integer> greaterThan100 = number.filter(n -> n > 100);
        System.out.println("42 > 100 mi? filter(n > 100): " + greaterThan100);
        System.out.println();
        
        // Pratik ornek: Yetiskin kullanici kontrolu
        Optional<User> adultUser = Optional.of(new User("Mehmet", 25));
        Optional<User> childUser = Optional.of(new User("Ayse", 15));
        
        Optional<User> adult1 = adultUser.filter(u -> u.getAge() >= 18);
        Optional<User> adult2 = childUser.filter(u -> u.getAge() >= 18);
        
        System.out.println("Yetiskin mi (25 yas)? " + adult1.isPresent());  // true
        System.out.println("Yetiskin mi (15 yas)? " + adult2.isPresent());  // false
        System.out.println();
        
        // ============================================================
        // 4. or() - Alternatif Optional (Java 9+)
        // ============================================================
        System.out.println("[4] or() - Alternatif Optional (Java 9+):");
        System.out.println("------------------------------------------------------------");
        
        Optional<String> primary = Optional.empty();
        Optional<String> secondary = Optional.of("Yedek Deger");
        
        // or() - Bossa alternatif Optional dondur
        Optional<String> finalResult = primary.or(() -> secondary);
        System.out.println("primary.or(secondary): " + finalResult);
        System.out.println();
        
        // Zincirleme or
        Optional<String> first = Optional.empty();
        Optional<String> second = Optional.empty();
        Optional<String> third = Optional.of("Ucuncu");
        
        Optional<String> found = first
            .or(() -> second)
            .or(() -> third);
        System.out.println("Zincirleme or: " + found);
        System.out.println();
        
        // ============================================================
        // 5. stream() - Optional to Stream (Java 9+)
        // ============================================================
        System.out.println("[5] stream() - Optional to Stream (Java 9+):");
        System.out.println("------------------------------------------------------------");
        
        Optional<String> optWithValue = Optional.of("Hello");
        Optional<String> optEmpty = Optional.empty();
        
        // Optional -> Stream (0 veya 1 eleman)
        long count1 = optWithValue.stream().count();
        long count2 = optEmpty.stream().count();
        
        System.out.println("Dolu Optional.stream().count(): " + count1);  // 1
        System.out.println("Bos Optional.stream().count(): " + count2);   // 0
        System.out.println();
        
        // List<Optional<T>> -> Stream<T> donusumu
        List<Optional<String>> optionals = List.of(
            Optional.of("A"),
            Optional.empty(),
            Optional.of("B"),
            Optional.empty(),
            Optional.of("C")
        );
        
        List<String> values = optionals.stream()
            .flatMap(Optional::stream)  // Boslari filtrele
            .toList();
        
        System.out.println("Optional listesinden degerler: " + values);  // [A, B, C]
    }
    
    // Yardimci siniflar
    static class User {
        private String name;
        private Address address;
        private int age;
        
        User(String name, Address address) {
            this.name = name;
            this.address = address;
        }
        
        User(String name, int age) {
            this.name = name;
            this.age = age;
        }
        
        Optional<String> getCity() {
            return address != null ? Optional.ofNullable(address.getCity()) : Optional.empty();
        }
        
        Optional<Address> getAddressOpt() {
            return Optional.ofNullable(address);
        }
        
        int getAge() {
            return age;
        }
    }
    
    static class Address {
        private String city;
        
        Address(String city) {
            this.city = city;
        }
        
        String getCity() {
            return city;
        }
        
        Optional<String> getCityOpt() {
            return Optional.ofNullable(city);
        }
    }
}
