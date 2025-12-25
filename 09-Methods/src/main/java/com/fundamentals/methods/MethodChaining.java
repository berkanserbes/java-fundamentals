package com.fundamentals.methods;

/**
 * Method Chaining (Fluent Interface) in Java
 * 
 * Method Chaining: Bir metodun sonucunda 'this' döndürerek, birden fazla
 * metodu art arda (zincir halinde) çagirmayi saglayan tasarim örüntüsüdür.
 * 
 * ============================================================================
 * TEMEL KAVRAMLAR:
 * ============================================================================
 * 
 * 1. Fluent Interface (Akici Arayüz):
 * - Metodlar 'this' döndürür
 * - Kod okunabilirligi artar
 * - Daha az satirda daha fazla is yapilir
 * 
 * 2. Builder Pattern (Olusturucu Deseni):
 * - Method Chaining'in en yaygin kullanim alani
 * - Karmasik nesneleri adim adim olusturur
 * - İmmutable nesneler için idealdir
 * 
 * ============================================================================
 * FAYDALARI:
 * ============================================================================
 * 
 * ✓ Okunabilirlik: Kod daha dogal ve akici okunur
 * ✓ Kisalik: Daha az kod satiri
 * ✓ İfade Gücü: Kodun ne yaptigi daha net anlasilir
 * ✓ Zincirleme: İslemler mantiksal sirada yapilir
 * ✓ Immutability Destegi: Degistirilemez nesneler olusturmayi kolaylastirir
 * 
 * ============================================================================
 * KULLANIM ALANLARI:
 * ============================================================================
 * 
 * - StringBuilder: append().append().toString()
 * - Stream API: filter().map().collect()
 * - Builder Pattern: setName().setAge().build()
 * - Test Frameworks: given().when().then()
 * - Query Builders: select().from().where()
 * 
 * ============================================================================
 * KARsILAsTIRMA:
 * ============================================================================
 * 
 * Method Chaining OLMADAN: Method Chaining İLE:
 * -------------------------- ------------------------
 * builder.setName("Ali"); builder.setName("Ali")
 * builder.setAge(25); .setAge(25)
 * builder.setCity("Istanbul"); .setCity("Istanbul")
 * Person p = builder.build(); .build();
 * 
 * ============================================================================
 */
public class MethodChaining {

    public static void main(String[] args) {
        System.out.println("=== Method Chaining (Fluent Interface) ===\n");

        demonstrateBasicChaining();
        demonstrateStringBuilderChaining();
        demonstrateBuilderPattern();
        demonstrateFluentEmployee();
        demonstrateQueryBuilder();
        demonstrateConfigBuilder();
        demonstrateChainVsNoChain();
    }

    /**
     * Temel Method Chaining örnegi
     */
    private static void demonstrateBasicChaining() {
        System.out.println("--- 1. Temel Method Chaining ---");
        System.out.println("Method Chaining: Her metod 'this' döndürür, böylece metodlar zincirlenebilir.\n");

        // Method Chaining kullanarak hesap makinesi
        ChainCalculator calc = new ChainCalculator(10);

        int result = calc
                .add(5) // 10 + 5 = 15
                .multiply(2) // 15 * 2 = 30
                .subtract(10) // 30 - 10 = 20
                .divide(4) // 20 / 4 = 5
                .getResult();

        System.out.println("Hesaplama: ((10 + 5) * 2 - 10) / 4 = " + result);
        System.out.println("Her metod 'this' döndürdügü için zincirleme mümkün.\n");
    }

    /**
     * StringBuilder ile Method Chaining (Java'nin yerlesik örnegi)
     */
    private static void demonstrateStringBuilderChaining() {
        System.out.println("--- 2. StringBuilder ile Method Chaining ---");
        System.out.println("StringBuilder Java'nin en bilinen Method Chaining örnegidir.\n");

        // Geleneksel yöntem (chaining OLMADAN)
        StringBuilder sb1 = new StringBuilder();
        sb1.append("Merhaba");
        sb1.append(" ");
        sb1.append("Dünya");
        sb1.append("!");
        System.out.println("Geleneksel: " + sb1.toString());

        // Method Chaining ile
        String message = new StringBuilder()
                .append("Merhaba")
                .append(" ")
                .append("Dünya")
                .append("!")
                .toString();
        System.out.println("Chaining ile: " + message);
        System.out.println();
    }

    /**
     * Builder Pattern örnegi
     */
    private static void demonstrateBuilderPattern() {
        System.out.println("--- 3. Builder Pattern ---");
        System.out.println("Builder Pattern, karmasik nesneleri adim adim olusturmak için kullanilir.\n");

        // Person nesnesini Builder ile olustur
        ChainPerson person = new ChainPerson.Builder()
                .setFirstName("Ahmet")
                .setLastName("Yilmaz")
                .setAge(30)
                .setEmail("ahmet@example.com")
                .setPhone("555-1234")
                .setCity("Istanbul")
                .build();

        person.displayInfo();

        // Sadece zorunlu alanlarla olustur
        ChainPerson minimalPerson = new ChainPerson.Builder()
                .setFirstName("Ayse")
                .setLastName("Demir")
                .build();

        minimalPerson.displayInfo();
        System.out.println();
    }

    /**
     * Fluent Employee (Setter zincirleme) örnegi
     */
    private static void demonstrateFluentEmployee() {
        System.out.println("--- 4. Fluent Setters ---");
        System.out.println("Setter metodlari 'this' döndürerek zincirleme yapilabilir.\n");

        // Fluent setter'lar ile
        ChainEmployee employee = new ChainEmployee()
                .setId("E001")
                .setName("Mehmet Kaya")
                .setDepartment("Yazilim")
                .setSalary(75000)
                .setActive(true);

        employee.displayInfo();
        System.out.println();
    }

    /**
     * Query Builder örnegi
     */
    private static void demonstrateQueryBuilder() {
        System.out.println("--- 5. Query Builder ---");
        System.out.println("SQL sorgulari gibi yapilar Method Chaining ile okunabilir hale gelir.\n");

        // SQL benzeri sorgu olustur
        String query = new ChainQueryBuilder()
                .select("id", "name", "email")
                .from("users")
                .where("age > 18")
                .and("city = 'Istanbul'")
                .orderBy("name")
                .limit(10)
                .build();

        System.out.println("Olusturulan Sorgu:");
        System.out.println(query);
        System.out.println();
    }

    /**
     * Konfigürasyon Builder örnegi
     */
    private static void demonstrateConfigBuilder() {
        System.out.println("--- 6. Configuration Builder ---");
        System.out.println("Uygulama ayarlari için Builder Pattern kullanimi.\n");

        // Veritabani konfigürasyonu
        ChainDatabaseConfig config = new ChainDatabaseConfig.Builder()
                .host("localhost")
                .port(5432)
                .database("myapp")
                .username("admin")
                .password("secret123")
                .maxConnections(10)
                .timeout(30000)
                .enableSSL(true)
                .build();

        config.displayConfig();
        System.out.println();
    }

    /**
     * Method Chaining vs Geleneksel Yöntem karsilastirmasi
     */
    private static void demonstrateChainVsNoChain() {
        System.out.println("--- 7. Karsilastirma: Chaining vs Geleneksel ---\n");

        // === Geleneksel Yöntem (Method Chaining OLMADAN) ===
        System.out.println("=== Geleneksel Yöntem (Chaining YOK) ===");
        System.out.println("StringBuilder sb = new StringBuilder();");
        System.out.println("sb.append(\"SELECT * \");");
        System.out.println("sb.append(\"FROM users \");");
        System.out.println("sb.append(\"WHERE active = true\");");
        System.out.println("String query = sb.toString();");

        // === Method Chaining İLE ===
        System.out.println("\n=== Method Chaining İLE ===");
        System.out.println("String query = new StringBuilder()");
        System.out.println("    .append(\"SELECT * \")");
        System.out.println("    .append(\"FROM users \")");
        System.out.println("    .append(\"WHERE active = true\")");
        System.out.println("    .toString();");

        System.out.println("\nExercise completed!");
    }
}

// =============================================================================
// ÖRNEK SINIFLAR
// =============================================================================

/**
 * ChainCalculator - Temel Method Chaining örnegi
 * Her aritmetik metod 'this' döndürür
 */
class ChainCalculator {
    private int value;

    public ChainCalculator(int initialValue) {
        this.value = initialValue;
    }

    public ChainCalculator add(int n) {
        value += n;
        return this; // Zincirleme için 'this' döndür
    }

    public ChainCalculator subtract(int n) {
        value -= n;
        return this;
    }

    public ChainCalculator multiply(int n) {
        value *= n;
        return this;
    }

    public ChainCalculator divide(int n) {
        if (n != 0) {
            value /= n;
        }
        return this;
    }

    public int getResult() {
        return value;
    }
}

/**
 * ChainPerson - Builder Pattern ile olusturulan immutable sinif
 */
class ChainPerson {
    // Immutable alanlar (final)
    private final String firstName;
    private final String lastName;
    private final int age;
    private final String email;
    private final String phone;
    private final String city;

    // Private constructor - sadece Builder kullanabilir
    private ChainPerson(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.email = builder.email;
        this.phone = builder.phone;
        this.city = builder.city;
    }

    public void displayInfo() {
        System.out.println("Person: " + firstName + " " + lastName);
        System.out.println("  Age: " + age);
        System.out.println("  Email: " + (email != null ? email : "N/A"));
        System.out.println("  Phone: " + (phone != null ? phone : "N/A"));
        System.out.println("  City: " + (city != null ? city : "N/A"));
    }

    // Static nested Builder class
    public static class Builder {
        private String firstName;
        private String lastName;
        private int age;
        private String email;
        private String phone;
        private String city;

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this; // Zincirleme için Builder döndür
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public ChainPerson build() {
            return new ChainPerson(this);
        }
    }
}

/**
 * ChainEmployee - Fluent Setters örnegi
 */
class ChainEmployee {
    private String id;
    private String name;
    private String department;
    private double salary;
    private boolean active;

    // Fluent setters - her biri 'this' döndürür
    public ChainEmployee setId(String id) {
        this.id = id;
        return this;
    }

    public ChainEmployee setName(String name) {
        this.name = name;
        return this;
    }

    public ChainEmployee setDepartment(String department) {
        this.department = department;
        return this;
    }

    public ChainEmployee setSalary(double salary) {
        this.salary = salary;
        return this;
    }

    public ChainEmployee setActive(boolean active) {
        this.active = active;
        return this;
    }

    public void displayInfo() {
        System.out.println("Employee: " + id + " - " + name);
        System.out.println("  Department: " + department);
        System.out.println("  Salary: $" + salary);
        System.out.println("  Active: " + active);
    }
}

/**
 * ChainQueryBuilder - SQL sorgu olusturucu
 */
class ChainQueryBuilder {
    private StringBuilder query = new StringBuilder();

    public ChainQueryBuilder select(String... columns) {
        query.append("SELECT ");
        query.append(String.join(", ", columns));
        return this;
    }

    public ChainQueryBuilder from(String table) {
        query.append("\nFROM ").append(table);
        return this;
    }

    public ChainQueryBuilder where(String condition) {
        query.append("\nWHERE ").append(condition);
        return this;
    }

    public ChainQueryBuilder and(String condition) {
        query.append("\n  AND ").append(condition);
        return this;
    }

    public ChainQueryBuilder or(String condition) {
        query.append("\n  OR ").append(condition);
        return this;
    }

    public ChainQueryBuilder orderBy(String column) {
        query.append("\nORDER BY ").append(column);
        return this;
    }

    public ChainQueryBuilder limit(int count) {
        query.append("\nLIMIT ").append(count);
        return this;
    }

    public String build() {
        return query.toString();
    }
}

/**
 * ChainDatabaseConfig - Konfigürasyon Builder örnegi
 */
class ChainDatabaseConfig {
    private final String host;
    private final int port;
    private final String database;
    private final String username;
    private final String password;
    private final int maxConnections;
    private final int timeout;
    private final boolean enableSSL;

    private ChainDatabaseConfig(Builder builder) {
        this.host = builder.host;
        this.port = builder.port;
        this.database = builder.database;
        this.username = builder.username;
        this.password = builder.password;
        this.maxConnections = builder.maxConnections;
        this.timeout = builder.timeout;
        this.enableSSL = builder.enableSSL;
    }

    public void displayConfig() {
        System.out.println("Database Configuration:");
        System.out.println("  Host: " + host + ":" + port);
        System.out.println("  Database: " + database);
        System.out.println("  Username: " + username);
        System.out.println("  Password: " + maskPassword(password));
        System.out.println("  Max Connections: " + maxConnections);
        System.out.println("  Timeout: " + timeout + "ms");
        System.out.println("  SSL Enabled: " + enableSSL);
    }

    private String maskPassword(String password) {
        if (password == null)
            return "N/A";
        StringBuilder masked = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            masked.append("*");
        }
        return masked.toString();
    }

    public static class Builder {
        private String host = "localhost";
        private int port = 3306;
        private String database;
        private String username;
        private String password;
        private int maxConnections = 5;
        private int timeout = 10000;
        private boolean enableSSL = false;

        public Builder host(String host) {
            this.host = host;
            return this;
        }

        public Builder port(int port) {
            this.port = port;
            return this;
        }

        public Builder database(String database) {
            this.database = database;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder maxConnections(int maxConnections) {
            this.maxConnections = maxConnections;
            return this;
        }

        public Builder timeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder enableSSL(boolean enableSSL) {
            this.enableSSL = enableSSL;
            return this;
        }

        public ChainDatabaseConfig build() {
            return new ChainDatabaseConfig(this);
        }
    }
}
