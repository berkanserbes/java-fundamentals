package com.fundamentals.oop;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Records in Java
 * 
 * Record: Immutable (değişmez) veri taşıyıcı sınıflar oluşturmak için
 * kullanılan
 * özel bir sınıf türüdür. Record'lar, boilerplate kod yazmadan veri sınıfları
 * oluşturmayı sağlar.
 * 
 * Record'lar Neden Kullanılır?
 * - Boilerplate Reduction: getter, equals, hashCode, toString otomatik oluşur
 * - Immutability: Tüm field'lar final ve private'dır
 * - Okunabilirlik: Daha az kod, daha okunabilir yapı
 * - Data Transfer Objects (DTO): Veri taşıma için ideal
 * 
 * Record Özellikleri:
 * - Tüm field'lar implicitly final'dır
 * - Record'lar implicitly final class'tır (extend edilemez)
 * - java.lang.Record sınıfından implicit olarak türer
 * - Canonical constructor otomatik oluşur
 * - Accessor methods (getter) otomatik oluşur
 * - equals(), hashCode(), toString() otomatik oluşur
 * - Interface implement edebilir
 * - Static field ve method içerebilir
 * - Instance method eklenebilir
 * 
 * Record'ların Kısıtlamaları:
 * - Başka bir class extend edemez (zaten Record'dan türer)
 * - Instance field eklenemez (sadece component'ler)
 * - Field'lar değiştirilemez (immutable)
 * - Abstract olamaz
 * 
 * @author Java Fundamentals
 */
public class Records {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                              JAVA RECORDS                    ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        demonstrateBasicRecord();
        demonstrateRecordComponents();
        demonstrateRecordMethods();
        demonstrateCompactConstructor();
        demonstrateCanonicalConstructor();
        demonstrateCustomConstructors();
        demonstrateRecordWithInterfaces();
        demonstrateNestedRecords();
        demonstrateRecordVsClass();
        demonstrateRealWorldExamples();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // BÖLÜM 1: TEMEL RECORD KULLANIMI
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * En basit record tanımı ve kullanımı
     */
    private static void demonstrateBasicRecord() {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("1. TEMEL RECORD KULLANIMI");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        /*
         * Basit Record Tanımı:
         * record Point(int x, int y) { }
         * 
         * Bu tek satır şunları otomatik oluşturur:
         * - private final int x;
         * - private final int y;
         * - public Point(int x, int y) {...} // Canonical constructor
         * - public int x() {...} // Getter for x
         * - public int y() {...} // Getter for y
         * - public boolean equals(Object o)
         * - public int hashCode()
         * - public String toString()
         */

        // Record oluşturma
        PointRecord point1 = new PointRecord(10, 20);
        System.out.println("Point oluşturuldu: " + point1);

        // Component erişimi (getter metodları)
        System.out.println("x değeri: " + point1.x());
        System.out.println("y değeri: " + point1.y());

        // toString() otomatik oluşur
        System.out.println("toString(): " + point1.toString());

        // İkinci bir point oluşturma
        PointRecord point2 = new PointRecord(10, 20);
        PointRecord point3 = new PointRecord(30, 40);

        // equals() otomatik oluşur ve değerlere göre karşılaştırır
        System.out.println("\nKarşılaştırma:");
        System.out.println("point1.equals(point2): " + point1.equals(point2)); // true
        System.out.println("point1.equals(point3): " + point1.equals(point3)); // false
        System.out.println("point1 == point2: " + (point1 == point2)); // false (farklı referanslar)

        // hashCode() otomatik oluşur
        System.out.println("\nHashCode:");
        System.out.println("point1.hashCode(): " + point1.hashCode());
        System.out.println("point2.hashCode(): " + point2.hashCode());
        System.out.println("Aynı değerler = Aynı hashCode: " + (point1.hashCode() == point2.hashCode()));

        System.out.println("\n[!] Record'lar IMMUTABLE'dır - değerler oluşturulduktan sonra değiştirilemez!");
        System.out.println("    point1.x = 100; // HATA! Değer atanamaz\n");
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // BÖLÜM 2: RECORD COMPONENT'LERİ
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Record component'leri (bileşenleri)
     */
    private static void demonstrateRecordComponents() {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("2. RECORD COMPONENT'LERİ (Bileşenler)");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        /*
         * Record component'leri:
         * - Header'da tanımlanan parametreler component'lerdir
         * - Her component için private final field oluşur
         * - Her component için public accessor method oluşur
         * - Accessor method isimleri field isimleriyle aynıdır (getX değil, x())
         */

        // Çoklu component'li record
        PersonRecord person = new PersonRecord("Ahmet", "Yılmaz", 30, "ahmet@email.com");

        System.out.println("Person Record:");
        System.out.println("  Ad: " + person.firstName());
        System.out.println("  Soyad: " + person.lastName());
        System.out.println("  Yaş: " + person.age());
        System.out.println("  Email: " + person.email());
        System.out.println("  toString: " + person);

        // Farklı veri türleri içeren record
        ProductRecord product = new ProductRecord(
                "PRD-001",
                "Laptop",
                15000.50,
                true,
                LocalDate.of(2024, 1, 15));

        System.out.println("\nProduct Record:");
        System.out.println("  ID: " + product.id());
        System.out.println("  İsim: " + product.name());
        System.out.println("  Fiyat: " + product.price() + " TL");
        System.out.println("  Stokta: " + product.inStock());
        System.out.println("  Eklenme Tarihi: " + product.addedDate());

        // Koleksiyon içeren record
        List<String> tags = List.of("electronics", "computer", "tech");
        TaggedItemRecord item = new TaggedItemRecord("Telefon", tags);

        System.out.println("\nTaggedItem Record:");
        System.out.println("  İsim: " + item.name());
        System.out.println("  Etiketler: " + item.tags());

        System.out.println();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // BÖLÜM 3: RECORD METODLARI
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Record'lara özel metodlar ekleme
     */
    private static void demonstrateRecordMethods() {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("3. RECORD METODLARI (Instance & Static Methods)");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        /*
         * Record'lara metodlar eklenebilir:
         * - Instance methods
         * - Static methods
         * - Static fields (instance field EKLENEMEZ!)
         * - Nested classes/interfaces/records
         */

        // Instance method kullanımı
        RectangleRecord rect = new RectangleRecord(10, 20);
        System.out.println("Rectangle: " + rect);
        System.out.println("Alan: " + rect.area());
        System.out.println("Çevre: " + rect.perimeter());
        System.out.println("Kare mi? " + rect.isSquare());

        // Static method kullanımı
        System.out.println("\nStatic method ile oluşturma:");
        RectangleRecord square = RectangleRecord.createSquare(15);
        System.out.println("Kare: " + square);
        System.out.println("Alan: " + square.area());
        System.out.println("Kare mi? " + square.isSquare());

        // Static field kullanımı
        System.out.println("\nStatic field:");
        System.out.println("Birim: " + RectangleRecord.UNIT);
        System.out.println("Toplam oluşturulan: " + RectangleRecord.getInstanceCount());

        // Celsius-Fahrenheit örneği
        System.out.println("\nTemperature Record:");
        TemperatureRecord temp = new TemperatureRecord(25.0);
        System.out.println("Celsius: " + temp.celsius() + "°C");
        System.out.println("Fahrenheit: " + temp.toFahrenheit() + "°F");
        System.out.println("Kelvin: " + temp.toKelvin() + "K");

        TemperatureRecord freezing = TemperatureRecord.fromFahrenheit(32);
        System.out.println("\n32°F = " + freezing.celsius() + "°C");

        System.out.println();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // BÖLÜM 4: COMPACT CONSTRUCTOR
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Compact constructor kullanımı
     */
    private static void demonstrateCompactConstructor() {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("4. COMPACT CONSTRUCTOR (Validation)");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        /*
         * Compact Constructor:
         * - Parametre listesi YAZILMAZ (implicit)
         * - Parametre atamaları OTOMATIK yapılır (sonunda)
         * - Sadece validation ve transformation için kullanılır
         * - En yaygın kullanım: input validation
         */

        // Geçerli email ile oluşturma
        EmailRecord email1 = new EmailRecord("test@example.com");
        System.out.println("Geçerli email: " + email1);

        // Geçersiz email ile oluşturma deneme
        System.out.println("\nGeçersiz email denemesi:");
        try {
            EmailRecord invalidEmail = new EmailRecord("invalid-email");
            System.out.println(invalidEmail); // Bu satıra ulaşılmaz
        } catch (IllegalArgumentException e) {
            System.out.println("  HATA: " + e.getMessage());
        }

        // Null email denemesi
        try {
            EmailRecord nullEmail = new EmailRecord(null);
            System.out.println(nullEmail);
        } catch (NullPointerException e) {
            System.out.println("  HATA: Email null olamaz!");
        }

        // Range validation örneği
        System.out.println("\nRange Record (1-100 arası değer):");
        RangeRecord range = new RangeRecord(50);
        System.out.println("Değer: " + range.value() + ", Yüzde: %" + range.asPercentage());

        try {
            RangeRecord invalidRange = new RangeRecord(150);
            System.out.println(invalidRange);
        } catch (IllegalArgumentException e) {
            System.out.println("  HATA: " + e.getMessage());
        }

        // Normalization örneği
        System.out.println("\nNormalized String Record:");
        NormalizedStringRecord str1 = new NormalizedStringRecord("  HELLO World  ");
        System.out.println("Input: '  HELLO World  '");
        System.out.println("Normalized: '" + str1.value() + "'");

        System.out.println();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // BÖLÜM 5: CANONICAL CONSTRUCTOR
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Canonical constructor (tam constructor) override etme
     */
    private static void demonstrateCanonicalConstructor() {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("5. CANONICAL CONSTRUCTOR (Full Override)");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        /*
         * Canonical Constructor:
         * - Tüm component'leri parametre olarak alır
         * - Parametre atamaları MANUEL yapılmalıdır
         * - Compact constructor'dan daha fazla kontrol sağlar
         * - Parametre dönüştürme için kullanılabilir
         */

        // Defensive copy örneği
        List<String> originalList = new java.util.ArrayList<>();
        originalList.add("A");
        originalList.add("B");

        ImmutableListRecord record = new ImmutableListRecord(originalList);
        System.out.println("Original list: " + originalList);
        System.out.println("Record list: " + record.items());

        // Original list'i değiştirme
        originalList.add("C");
        System.out.println("\nOriginal list'e 'C' eklendi:");
        System.out.println("Original list: " + originalList);
        System.out.println("Record list: " + record.items()); // Değişmemiş!

        System.out.println("Record list değişmedi çünkü defensive copy yapıldı!");

        // Timestamp record örneği
        System.out.println("\nAuditRecord - Otomatik timestamp:");
        AuditRecord audit1 = new AuditRecord("Kayıt oluşturuldu", "admin");
        System.out.println(audit1);

        // Kısa bir bekleme
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        AuditRecord audit2 = new AuditRecord("Kayıt güncellendi", "user1");
        System.out.println(audit2);

        System.out.println();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // BÖLÜM 6: ÖZEL CONSTRUCTOR'LAR
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Özel (non-canonical) constructor'lar
     */
    private static void demonstrateCustomConstructors() {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("6. ÖZEL CONSTRUCTOR'LAR (Custom Constructors)");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        /*
         * Özel Constructor Kuralları:
         * - Canonical constructor'ı MUTLAKA çağırmalıdır
         * - this(...) ile delegate etmelidir
         * - Varsayılan değerler sağlamak için kullanılır
         */

        // Farklı constructor'lar ile oluşturma
        System.out.println("Farklı constructor'lar:");

        // Tam constructor
        ColorRecord color1 = new ColorRecord(255, 128, 64);
        System.out.println("RGB(255, 128, 64): " + color1);

        // Hex string ile
        ColorRecord color2 = new ColorRecord("#FF8040");
        System.out.println("Hex #FF8040: " + color2);

        // Gri tonları
        ColorRecord gray = new ColorRecord(128);
        System.out.println("Gray(128): " + gray);

        // Varsayılan (siyah)
        ColorRecord black = new ColorRecord();
        System.out.println("Default (black): " + black);

        // Money record örneği
        System.out.println("\nMoney Record:");
        MoneyRecord price1 = new MoneyRecord(100.50, "TRY");
        MoneyRecord price2 = new MoneyRecord(50); // Varsayılan TRY
        MoneyRecord price3 = MoneyRecord.zero("USD");

        System.out.println("Fiyat 1: " + price1);
        System.out.println("Fiyat 2: " + price2);
        System.out.println("Fiyat 3 (zero): " + price3);
        System.out.println("Toplam (TRY): " + price1.add(price2));

        System.out.println();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // BÖLÜM 7: INTERFACE İLE RECORD
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Record'ların interface implement etmesi
     */
    private static void demonstrateRecordWithInterfaces() {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("7. INTERFACE İLE RECORD (implements)");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        /*
         * Record'lar interface implement edebilir:
         * - Comparable<T> için sıralama
         * - Serializable için serialization
         * - Özel interface'ler için polimorfizm
         */

        // Comparable interface örneği
        System.out.println("Comparable Interface (Sıralama):");
        List<EmployeeRecord> employees = new java.util.ArrayList<>();
        employees.add(new EmployeeRecord("E003", "Zeynep", 55000));
        employees.add(new EmployeeRecord("E001", "Ali", 45000));
        employees.add(new EmployeeRecord("E002", "Mehmet", 60000));

        System.out.println("Sıralanmamış:");
        employees.forEach(e -> System.out.println("  " + e));

        java.util.Collections.sort(employees);
        System.out.println("\nMaaşa göre sıralanmış:");
        employees.forEach(e -> System.out.println("  " + e));

        // Özel interface örneği
        System.out.println("\nPrintable Interface:");
        List<Printable> printables = List.of(
                new DocumentRecord("Rapor.pdf", 1024),
                new ImageRecord("Foto.jpg", 800, 600));

        for (Printable p : printables) {
            System.out.println("  " + p.getClass().getSimpleName() + ":");
            System.out.println("    " + p.toPrintableString());
        }

        // Serializable örneği
        System.out.println("\nSerializable Record:");
        UserRecord user = new UserRecord("U001", "john_doe", "john@email.com");
        System.out.println("  " + user);
        System.out.println("  Serializable: " + (user instanceof java.io.Serializable));

        System.out.println();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // BÖLÜM 8: İÇ İÇE (NESTED) RECORD'LAR
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Nested record'lar ve composition
     */
    private static void demonstrateNestedRecords() {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("8. İÇ İÇE (NESTED) RECORD'LAR");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        /*
         * Record'lar içinde:
         * - Başka record'lar nested olabilir
         * - Record'lar başka record'ları component olarak alabilir (composition)
         * - Karmaşık veri yapıları oluşturulabilir
         */

        // Composition örneği - Adres içeren Kişi
        AddressRecord address = new AddressRecord(
                "Atatürk Cad.",
                "No: 123",
                "Kadıköy",
                "İstanbul",
                "34000");

        CustomerRecord customer = new CustomerRecord(
                "C001",
                "Ayşe Demir",
                "ayse@email.com",
                address);

        System.out.println("Customer Record (Composition):");
        System.out.println("  ID: " + customer.id());
        System.out.println("  Ad: " + customer.name());
        System.out.println("  Email: " + customer.email());
        System.out.println("  Adres: " + customer.address().fullAddress());

        // Nested record örneği
        System.out.println("\nNested Records (Order > OrderItem):");
        OrderRecord order = new OrderRecord(
                "ORD-001",
                List.of(
                        new OrderRecord.OrderItem("Laptop", 2, 15000),
                        new OrderRecord.OrderItem("Mouse", 3, 250),
                        new OrderRecord.OrderItem("Klavye", 1, 500)),
                LocalDateTime.now());

        System.out.println("  Sipariş No: " + order.orderId());
        System.out.println("  Ürün Sayısı: " + order.items().size());
        System.out.println("  Toplam Tutar: " + order.totalAmount() + " TL");
        System.out.println("  Tarih: " + order.orderDate());
        System.out.println("  Detaylar:");
        for (OrderRecord.OrderItem item : order.items()) {
            System.out.println("    - " + item.productName() + " x" + item.quantity()
                    + " = " + item.totalPrice() + " TL");
        }

        System.out.println();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // BÖLÜM 9: RECORD VS CLASS KARŞILAŞTIRMASI
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Record ve geleneksel class karşılaştırması
     */
    private static void demonstrateRecordVsClass() {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("9. RECORD VS CLASS KARŞILAŞTIRMASI");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        /*
         * RECORD: CLASS:
         * record Person(String name, int age) class Person {
         * { } private final String name;
         * private final int age;
         * 
         * public Person(String name, int age) {
         * this.name = name;
         * this.age = age;
         * }
         * 
         * public String name() { return name; }
         * public int age() { return age; }
         * 
         * @Override
         * public boolean equals(Object o) {...}
         * 
         * @Override
         * public int hashCode() {...}
         * 
         * @Override
         * public String toString() {...}
         * }
         */

        System.out.println("Karşılaştırma Tablosu:");
        System.out.println("─────────────────────────────────────────────────────────────────");
        System.out.printf("%-25s | %-15s | %-15s%n", "Özellik", "Record", "Class");
        System.out.println("─────────────────────────────────────────────────────────────────");
        System.out.printf("%-25s | %-15s | %-15s%n", "Immutability", "Varsayılan", "Manuel");
        System.out.printf("%-25s | %-15s | %-15s%n", "equals/hashCode", "Otomatik", "Manuel");
        System.out.printf("%-25s | %-15s | %-15s%n", "toString", "Otomatik", "Manuel");
        System.out.printf("%-25s | %-15s | %-15s%n", "Getter'lar", "Otomatik", "Manuel");
        System.out.printf("%-25s | %-15s | %-15s%n", "Setter'lar", "Yok", "İsteğe bağlı");
        System.out.printf("%-25s | %-15s | %-15s%n", "Inheritance", "Sadece interface", "Class + Interface");
        System.out.printf("%-25s | %-15s | %-15s%n", "Instance field ekleme", "Hayır", "Evet");
        System.out.printf("%-25s | %-15s | %-15s%n", "Kod miktarı", "Az (1-5 satır)", "Çok (30+ satır)");
        System.out.println("─────────────────────────────────────────────────────────────────");

        // Pratik karşılaştırma
        System.out.println("\nPratik Örnek:");

        // Record kullanımı
        BookRecord bookRecord = new BookRecord("1984", "George Orwell", 328);
        System.out.println("\nRecord: " + bookRecord);

        // Equivalent class kullanımı
        BookClass bookClass = new BookClass("1984", "George Orwell", 328);
        System.out.println("Class: " + bookClass);

        // equals karşılaştırması
        BookRecord bookRecord2 = new BookRecord("1984", "George Orwell", 328);
        BookClass bookClass2 = new BookClass("1984", "George Orwell", 328);

        System.out.println("\nEquals Karşılaştırması:");
        System.out.println("  Record1.equals(Record2): " + bookRecord.equals(bookRecord2)); // true (otomatik)
        System.out.println("  Class1.equals(Class2): " + bookClass.equals(bookClass2)); // true (manuel override)

        System.out.println("\n[!] Record ne zaman kullanılmalı?");
        System.out.println("    ✅ DTO (Data Transfer Object) için");
        System.out.println("    ✅ Immutable veri yapıları için");
        System.out.println("    ✅ Value objects için");
        System.out.println("    ✅ API response/request için");
        System.out.println("    ❌ Mutable state gerektiğinde kullanılmamalı");
        System.out.println("    ❌ Inheritance gerektiğinde kullanılmamalı\n");
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // BÖLÜM 10: GERÇEK DÜNYA ÖRNEKLERİ
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Gerçek dünya uygulamaları
     */
    private static void demonstrateRealWorldExamples() {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("10. GERÇEK DÜNYA ÖRNEKLERİ");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        // 1. API Response örneği
        System.out.println("1. API Response:");
        ApiResponse<UserRecord> successResponse = new ApiResponse<>(
                true,
                new UserRecord("U001", "john_doe", "john@email.com"),
                null);
        ApiResponse<UserRecord> errorResponse = new ApiResponse<>(
                false,
                null,
                "User not found");

        System.out.println("  Success: " + successResponse);
        System.out.println("  Error: " + errorResponse);

        // 2. Configuration örneği
        System.out.println("\n2. Database Configuration:");
        DatabaseConfig dbConfig = new DatabaseConfig(
                "localhost",
                5432,
                "mydb",
                "admin",
                true);
        System.out.println("  " + dbConfig);
        System.out.println("  Connection String: " + dbConfig.connectionString());

        // 3. Event örneği
        System.out.println("\n3. Domain Event:");
        UserCreatedEvent event = new UserCreatedEvent(
                java.util.UUID.randomUUID().toString(),
                "john_doe",
                "john@email.com",
                LocalDateTime.now());
        System.out.println("  " + event);

        // 4. Result/Either pattern
        System.out.println("\n4. Result Pattern (Success/Failure):");
        Result<Integer> divisionResult1 = Result.success(10 / 2);
        Result<Integer> divisionResult2 = Result.failure("Division by zero");

        System.out.println("  10/2: " + divisionResult1);
        System.out.println("  10/0: " + divisionResult2);

        // 5. Coordinate/GeoLocation
        System.out.println("\n5. GeoLocation:");
        GeoLocation istanbul = new GeoLocation(41.0082, 28.9784, "İstanbul");
        GeoLocation ankara = new GeoLocation(39.9334, 32.8597, "Ankara");
        System.out.println("  " + istanbul);
        System.out.println("  " + ankara);
        System.out.println("  Mesafe: " + String.format("%.2f", istanbul.distanceTo(ankara)) + " km");

        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║               RECORDS KONUSU TAMAMLANDI! ✅                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");
    }
}

// ═══════════════════════════════════════════════════════════════════════════════
// RECORD TANIMLARI
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * Temel record örneği - 2D nokta
 */
record PointRecord(int x, int y) {
}

/**
 * Çoklu component'li record - Kişi bilgisi
 */
record PersonRecord(String firstName, String lastName, int age, String email) {
}

/**
 * Farklı veri türleri içeren record - Ürün
 */
record ProductRecord(String id, String name, double price, boolean inStock, LocalDate addedDate) {
}

/**
 * Koleksiyon içeren record
 */
record TaggedItemRecord(String name, List<String> tags) {
}

/**
 * Instance ve static method içeren record - Dikdörtgen
 */
record RectangleRecord(double width, double height) {
    // Static field (izin verilen)
    public static final String UNIT = "cm";
    private static int instanceCount = 0;

    // Static initializer block
    static {
        System.out.println("    [RectangleRecord class yüklendi]");
    }

    // Compact constructor - sayacı artır
    public RectangleRecord {
        instanceCount++;
    }

    // Instance methods
    public double area() {
        return width * height;
    }

    public double perimeter() {
        return 2 * (width + height);
    }

    public boolean isSquare() {
        return width == height;
    }

    // Static factory method
    public static RectangleRecord createSquare(double side) {
        return new RectangleRecord(side, side);
    }

    public static int getInstanceCount() {
        return instanceCount;
    }
}

/**
 * Sıcaklık dönüşüm record'u
 */
record TemperatureRecord(double celsius) {
    public double toFahrenheit() {
        return celsius * 9 / 5 + 32;
    }

    public double toKelvin() {
        return celsius + 273.15;
    }

    public static TemperatureRecord fromFahrenheit(double fahrenheit) {
        return new TemperatureRecord((fahrenheit - 32) * 5 / 9);
    }

    public static TemperatureRecord fromKelvin(double kelvin) {
        return new TemperatureRecord(kelvin - 273.15);
    }
}

/**
 * Compact constructor ile validation - Email
 */
record EmailRecord(String address) {
    public EmailRecord {
        Objects.requireNonNull(address, "Email adresi null olamaz");
        if (!address.contains("@")) {
            throw new IllegalArgumentException("Geçersiz email formatı: " + address);
        }
        // Normalization - küçük harfe çevir
        address = address.toLowerCase().trim();
    }
}

/**
 * Compact constructor ile range validation
 */
record RangeRecord(int value) {
    public RangeRecord {
        if (value < 1 || value > 100) {
            throw new IllegalArgumentException("Değer 1-100 arasında olmalı: " + value);
        }
    }

    public int asPercentage() {
        return value;
    }
}

/**
 * Compact constructor ile normalization
 */
record NormalizedStringRecord(String value) {
    public NormalizedStringRecord {
        value = value == null ? "" : value.trim().toLowerCase();
    }
}

/**
 * Canonical constructor ile defensive copy
 */
record ImmutableListRecord(List<String> items) {
    public ImmutableListRecord(List<String> items) {
        // Defensive copy - dış değişikliklere karşı koruma
        this.items = List.copyOf(items);
    }
}

/**
 * Canonical constructor ile timestamp ekleme
 */
record AuditRecord(String message, String user, LocalDateTime timestamp) {
    // Canonical constructor sadece 2 parametre ile
    public AuditRecord(String message, String user) {
        this(message, user, LocalDateTime.now());
    }
}

/**
 * Birden fazla constructor - Renk
 */
record ColorRecord(int red, int green, int blue) {
    // Compact constructor - validation
    public ColorRecord {
        if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
            throw new IllegalArgumentException("RGB değerleri 0-255 arasında olmalı");
        }
    }

    // Hex string'den oluşturma
    public ColorRecord(String hex) {
        this(
                Integer.parseInt(hex.substring(1, 3), 16),
                Integer.parseInt(hex.substring(3, 5), 16),
                Integer.parseInt(hex.substring(5, 7), 16));
    }

    // Gri ton (tek değer)
    public ColorRecord(int gray) {
        this(gray, gray, gray);
    }

    // Varsayılan - siyah
    public ColorRecord() {
        this(0, 0, 0);
    }

    public String toHex() {
        return String.format("#%02X%02X%02X", red, green, blue);
    }

    @Override
    public String toString() {
        return String.format("RGB(%d, %d, %d) = %s", red, green, blue, toHex());
    }
}

/**
 * Para birimi record'u
 */
record MoneyRecord(double amount, String currency) {
    public MoneyRecord {
        if (amount < 0) {
            throw new IllegalArgumentException("Tutar negatif olamaz");
        }
        currency = currency.toUpperCase();
    }

    // Varsayılan TRY
    public MoneyRecord(double amount) {
        this(amount, "TRY");
    }

    // Sıfır para
    public static MoneyRecord zero(String currency) {
        return new MoneyRecord(0, currency);
    }

    public MoneyRecord add(MoneyRecord other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Farklı para birimleri toplanamaz");
        }
        return new MoneyRecord(this.amount + other.amount, this.currency);
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", amount, currency);
    }
}

/**
 * Comparable interface implement eden record
 */
record EmployeeRecord(String id, String name, double salary) implements Comparable<EmployeeRecord> {
    @Override
    public int compareTo(EmployeeRecord other) {
        return Double.compare(this.salary, other.salary);
    }
}

/**
 * Özel interface tanımı
 */
interface Printable {
    String toPrintableString();
}

/**
 * Printable interface implement eden record - Document
 */
record DocumentRecord(String name, long sizeBytes) implements Printable {
    @Override
    public String toPrintableString() {
        return String.format("Document: %s (%d bytes)", name, sizeBytes);
    }
}

/**
 * Printable interface implement eden record - Image
 */
record ImageRecord(String name, int width, int height) implements Printable {
    @Override
    public String toPrintableString() {
        return String.format("Image: %s (%dx%d pixels)", name, width, height);
    }
}

/**
 * Serializable record
 */
record UserRecord(String id, String username, String email) implements java.io.Serializable {
}

/**
 * Adres record'u
 */
record AddressRecord(String street, String buildingNo, String district, String city, String postalCode) {
    public String fullAddress() {
        return String.format("%s %s, %s, %s %s", street, buildingNo, district, city, postalCode);
    }
}

/**
 * Müşteri record'u - başka record içerir (Composition)
 */
record CustomerRecord(String id, String name, String email, AddressRecord address) {
}

/**
 * Nested record örneği - Sipariş
 */
record OrderRecord(String orderId, List<OrderItem> items, LocalDateTime orderDate) {
    // Nested record
    public record OrderItem(String productName, int quantity, double unitPrice) {
        public double totalPrice() {
            return quantity * unitPrice;
        }
    }

    public double totalAmount() {
        return items.stream()
                .mapToDouble(OrderItem::totalPrice)
                .sum();
    }
}

/**
 * Kitap record'u - karşılaştırma için
 */
record BookRecord(String title, String author, int pages) {
}

/**
 * Kitap class'ı - Record ile karşılaştırma için
 */
class BookClass {
    private final String title;
    private final String author;
    private final int pages;

    public BookClass(String title, String author, int pages) {
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    public String title() {
        return title;
    }

    public String author() {
        return author;
    }

    public int pages() {
        return pages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BookClass bookClass = (BookClass) o;
        return pages == bookClass.pages &&
                Objects.equals(title, bookClass.title) &&
                Objects.equals(author, bookClass.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, pages);
    }

    @Override
    public String toString() {
        return "BookClass[title=" + title + ", author=" + author + ", pages=" + pages + "]";
    }
}

/**
 * Generic API Response record
 */
record ApiResponse<T>(boolean success, T data, String error) {
    public boolean hasError() {
        return !success && error != null;
    }
}

/**
 * Database configuration record
 */
record DatabaseConfig(String host, int port, String database, String username, boolean ssl) {
    public String connectionString() {
        String protocol = ssl ? "jdbc:postgresql" : "jdbc:postgresql";
        return String.format("%s://%s:%d/%s", protocol, host, port, database);
    }
}

/**
 * Domain event record
 */
record UserCreatedEvent(String eventId, String username, String email, LocalDateTime createdAt) {
}

/**
 * Result pattern record (Success/Failure)
 */
record Result<T>(boolean success, T value, String error) {
    public static <T> Result<T> success(T value) {
        return new Result<>(true, value, null);
    }

    public static <T> Result<T> failure(String error) {
        return new Result<>(false, null, error);
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isFailure() {
        return !success;
    }
}

/**
 * GeoLocation record - mesafe hesaplama
 */
record GeoLocation(double latitude, double longitude, String name) {
    // Haversine formula ile mesafe hesaplama (km)
    public double distanceTo(GeoLocation other) {
        double earthRadius = 6371; // km

        double dLat = Math.toRadians(other.latitude - this.latitude);
        double dLon = Math.toRadians(other.longitude - this.longitude);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(this.latitude)) *
                        Math.cos(Math.toRadians(other.latitude)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadius * c;
    }

    @Override
    public String toString() {
        return String.format("%s (%.4f, %.4f)", name, latitude, longitude);
    }
}

record UserPerson(String name, int age) {
    public UserPerson {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("name boş olamaz");

        if (age < 0)
            throw new IllegalArgumentException("age negatif olamaz");
    }
}

record FullName(String first, String last) {
    public FullName(String full) {
        this(
                full.split(" ")[0],
                full.split(" ")[1]);
    }
}