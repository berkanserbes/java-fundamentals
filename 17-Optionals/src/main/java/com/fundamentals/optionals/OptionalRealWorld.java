package com.fundamentals.optionals;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Optional Gercek Dunya Ornekleri
 */
public class OptionalRealWorld {
    
    public static void demonstrate() {
        System.out.println();
        
        // ============================================================
        // 1. Repository Pattern
        // ============================================================
        System.out.println("[1] REPOSITORY PATTERN:");
        System.out.println("------------------------------------------------------------");
        
        UserRepository repo = new UserRepository();
        
        // Kullanici var
        Optional<User> user1 = repo.findById(1);
        user1.ifPresent(u -> System.out.println("Kullanici bulundu: " + u.getName()));
        
        // Kullanici yok
        Optional<User> user2 = repo.findById(999);
        String name = user2
            .map(User::getName)
            .orElse("Bilinmeyen Kullanici");
        System.out.println("Sonuc: " + name);
        System.out.println();
        
        // ============================================================
        // 2. Configuration Okuma
        // ============================================================
        System.out.println("[2] CONFIGURATION OKUMA:");
        System.out.println("------------------------------------------------------------");
        
        Config config = new Config();
        
        int port = config.getInt("server.port").orElse(8080);
        String host = config.getString("server.host").orElse("localhost");
        int timeout = config.getInt("connection.timeout")
            .orElseThrow(() -> new IllegalStateException("timeout gerekli!"));
        
        System.out.println("Port: " + port);
        System.out.println("Host: " + host);
        System.out.println("Timeout: " + timeout);
        System.out.println();
        
        // ============================================================
        // 3. Null-Safe Method Chaining
        // ============================================================
        System.out.println("[3] NULL-SAFE METHOD CHAINING:");
        System.out.println("------------------------------------------------------------");
        
        // Tehlikeli kod (NullPointerException riski)
        System.out.println("Eski stil - null kontrolu:");
        System.out.println("  if (company != null && company.getManager() != null ...");
        System.out.println();
        
        // Guvenli kod
        Company company = new Company(
            new Department(
                new Employee("Ahmet", "ahmet@mail.com")
            )
        );
        
        String email = Optional.ofNullable(company)
            .map(Company::getDepartment)
            .map(Department::getManager)
            .map(Employee::getEmail)
            .orElse("email yok");
        
        System.out.println("Optional ile: " + email);
        System.out.println();
        
        // ============================================================
        // 4. Stream ile Entegrasyon
        // ============================================================
        System.out.println("[4] STREAM ILE ENTEGRASYON:");
        System.out.println("------------------------------------------------------------");
        
        List<User> users = List.of(
            new User(1, "Ali", "ali@mail.com"),
            new User(2, "Veli", null),  // email yok
            new User(3, "Ayse", "ayse@mail.com"),
            new User(4, "Fatma", null)  // email yok
        );
        
        // Sadece email'i olan kullanicilari al
        List<String> emails = users.stream()
            .map(User::getEmailOpt)
            .flatMap(Optional::stream)  // Boslari filtrele
            .toList();
        
        System.out.println("Email'i olan kullanicilar: " + emails);
        System.out.println();
        
        // ============================================================
        // 5. Validation
        // ============================================================
        System.out.println("[5] VALIDATION:");
        System.out.println("------------------------------------------------------------");
        
        String input1 = "hello@example.com";
        String input2 = "invalid-email";
        String input3 = "";
        
        Optional<String> valid1 = validateEmail(input1);
        Optional<String> valid2 = validateEmail(input2);
        Optional<String> valid3 = validateEmail(input3);
        
        System.out.println("'" + input1 + "' gecerli mi? " + valid1.isPresent());
        System.out.println("'" + input2 + "' gecerli mi? " + valid2.isPresent());
        System.out.println("'" + input3 + "' gecerli mi? " + valid3.isPresent());
        System.out.println();
        
        // ============================================================
        // 6. First Match / Fallback
        // ============================================================
        System.out.println("[6] FIRST MATCH / FALLBACK:");
        System.out.println("------------------------------------------------------------");
        
        // Ilk bulunan degeri al
        String value = findInCache("key1")
            .or(() -> findInDatabase("key1"))
            .or(() -> findInRemoteService("key1"))
            .orElse("Bulunamadi");
        
        System.out.println("Sonuc: " + value);
        System.out.println();
        
        // ============================================================
        // 7. Primitive Optionals
        // ============================================================
        System.out.println("[7] PRIMITIVE OPTIONALS:");
        System.out.println("------------------------------------------------------------");
        System.out.println("Boxing/Unboxing maliyetinden kacinmak icin:");
        System.out.println();
        
        OptionalInt optInt = OptionalInt.of(42);
        OptionalLong optLong = OptionalLong.of(100L);
        OptionalDouble optDouble = OptionalDouble.of(3.14);
        
        System.out.println("OptionalInt: " + optInt.getAsInt());
        System.out.println("OptionalLong: " + optLong.getAsLong());
        System.out.println("OptionalDouble: " + optDouble.getAsDouble());
        System.out.println();
        
        // Stream ile kullanim
        int sum = List.of(1, 2, 3, 4, 5).stream()
            .mapToInt(Integer::intValue)
            .reduce(Integer::sum)
            .orElse(0);  // OptionalInt doner
        System.out.println("Toplam: " + sum);
    }
    
    // Yardimci metodlar
    private static Optional<String> validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return Optional.empty();
        }
        return email.contains("@") ? Optional.of(email) : Optional.empty();
    }
    
    private static Optional<String> findInCache(String key) {
        System.out.println("  Cache'de araniyor...");
        return Optional.empty(); // Cache miss
    }
    
    private static Optional<String> findInDatabase(String key) {
        System.out.println("  Database'de araniyor...");
        return Optional.of("DB'den bulundu"); // Bulundu
    }
    
    private static Optional<String> findInRemoteService(String key) {
        System.out.println("  Remote service'de araniyor...");
        return Optional.of("Remote'dan bulundu");
    }
    
    // Yardimci siniflar
    static class UserRepository {
        private Map<Integer, User> users = Map.of(
            1, new User(1, "Ali", "ali@mail.com"),
            2, new User(2, "Veli", "veli@mail.com")
        );
        
        Optional<User> findById(int id) {
            return Optional.ofNullable(users.get(id));
        }
    }
    
    static class Config {
        private Map<String, String> props = Map.of(
            "server.port", "9090",
            "connection.timeout", "30"
        );
        
        Optional<String> getString(String key) {
            return Optional.ofNullable(props.get(key));
        }
        
        Optional<Integer> getInt(String key) {
            return getString(key).map(Integer::parseInt);
        }
    }
    
    static class User {
        private int id;
        private String name;
        private String email;
        
        User(int id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }
        
        String getName() { return name; }
        String getEmail() { return email; }
        Optional<String> getEmailOpt() { return Optional.ofNullable(email); }
    }
    
    static class Company {
        private Department department;
        Company(Department dept) { this.department = dept; }
        Department getDepartment() { return department; }
    }
    
    static class Department {
        private Employee manager;
        Department(Employee mgr) { this.manager = mgr; }
        Employee getManager() { return manager; }
    }
    
    static class Employee {
        private String name;
        private String email;
        Employee(String name, String email) { this.name = name; this.email = email; }
        String getEmail() { return email; }
    }
}
