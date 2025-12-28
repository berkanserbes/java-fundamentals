package com.fundamentals.oop;

/**
 * Enums (Enumerations) in Java
 * 
 * Enum: Java'da sabit değerlerin bir arada tutulduğu özel bir veri tipidir.
 * Enum, "enumeration" kelimesinin kısaltmasıdır ve sınırlı sayıda sabit değer
 * içeren bir tip tanımlamak için kullanılır.
 * 
 * Enum'lar Neden Kullanılır?
 * - Type Safety: Sadece tanımlı değerler kullanılabilir
 * - Okunabilirlik: Kod daha anlaşılır hale gelir
 * - Maintainability: Sabit değerler tek bir yerde tanımlanır
 * - IDE Desteği: Otomatik tamamlama ve compile-time kontrol
 * 
 * Enum Özellikleri:
 * - Enum'lar java.lang.Enum sınıfından implicit olarak türer
 * - Final ve static olarak tanımlıdır
 * - Constructor, field ve method içerebilir
 * - Interface implement edebilir (extend edemez)
 * - Singleton pattern için kullanılabilir
 * - Switch-case yapılarında kullanılabilir
 * 
 * @author Java Fundamentals
 */
public class Enums {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                    JAVA ENUMS (ENUMERATIONS)                 ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        demonstrateBasicEnum();
        demonstrateEnumMethods();
        demonstrateEnumWithFields();
        demonstrateEnumWithMethods();
        demonstrateEnumWithConstructor();
        demonstrateEnumInSwitch();
        demonstrateEnumComparison();
        demonstrateEnumWithInterface();
        demonstrateEnumSingleton();
        demonstrateAdvancedEnumFeatures();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // BÖLÜM 1: TEMEL ENUM KULLANIMI
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * En basit enum tanımı ve kullanımı
     */
    private static void demonstrateBasicEnum() {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("1. TEMEL ENUM KULLANIMI");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        /*
         * Basit Enum Tanımı:
         * enum GunlerEnum { PAZARTESI, SALI, CARSAMBA, PERSEMBE, CUMA, CUMARTESI, PAZAR
         * }
         * 
         * NOT: Enum sabitleri büyük harfle yazılır (convention)
         */

        // Enum değişkeni tanımlama
        Day today = Day.MONDAY;
        System.out.println("Bugün: " + today);

        // Enum değerini değiştirme
        Day tomorrow = Day.TUESDAY;
        System.out.println("Yarın: " + tomorrow);

        // Enum değerini String'den parse etme
        Day weekend = Day.valueOf("SATURDAY");
        System.out.println("Hafta sonu: " + weekend);

        // Tüm enum değerlerini listeleme
        System.out.println("\nHaftanın tüm günleri:");
        for (Day day : Day.values()) {
            System.out.println("  - " + day);
        }

        System.out.println("\n[!] Enum kullanmanın avantajı: Sadece tanımlı değerler atanabilir");
        System.out.println("    Örneğin 'Day day = Day.FUNDAY;' compile hatası verir!\n");
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // BÖLÜM 2: ENUM BUILT-IN METODLARI
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Enum'ların sahip olduğu hazır metodlar
     */
    private static void demonstrateEnumMethods() {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("2. ENUM BUILT-IN METODLARI");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        Season season = Season.SUMMER;

        // 1. name() - Enum sabitinin adını döndürür
        System.out.println("name(): " + season.name());

        // 2. ordinal() - Enum sabitinin sıra numarasını döndürür (0'dan başlar)
        System.out.println("ordinal(): " + season.ordinal());

        // 3. toString() - Varsayılan olarak name() ile aynı
        System.out.println("toString(): " + season.toString());

        // 4. values() - Tüm enum değerlerini array olarak döndürür
        System.out.println("\nvalues() ile tüm mevsimler ve ordinal değerleri:");
        for (Season s : Season.values()) {
            System.out.println("  " + s.name() + " -> ordinal: " + s.ordinal());
        }

        // 5. valueOf(String) - String'den enum değeri oluşturur
        Season winter = Season.valueOf("WINTER");
        System.out.println("\nvalueOf(\"WINTER\"): " + winter);

        // 6. compareTo() - Ordinal değerlerine göre karşılaştırma
        System.out.println("\nSUMMER.compareTo(WINTER): " + Season.SUMMER.compareTo(Season.WINTER));
        System.out.println("WINTER.compareTo(SPRING): " + Season.WINTER.compareTo(Season.SPRING));

        // 7. getDeclaringClass() - Enum sınıfını döndürür
        System.out.println("getDeclaringClass(): " + season.getDeclaringClass().getSimpleName());

        System.out.println("\n[!] UYARI: ordinal() kullanmak önerilmez çünkü enum sırası");
        System.out.println("    değişirse kodunuz bozulabilir. Bunun yerine field kullanın.\n");
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // BÖLÜM 3: FIELD'LI ENUM
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Enum'lara field (alan) ekleme
     */
    private static void demonstrateEnumWithFields() {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("3. FIELD'LI ENUM (Enum with Fields)");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        /*
         * Enum'lar basit sabitlerden fazlasıdır!
         * Her enum sabitine ek veri (field) ekleyebiliriz.
         */

        System.out.println("HTTP Status Kodları:");
        System.out.println("─────────────────────────────────────────");
        for (HttpStatus status : HttpStatus.values()) {
            System.out.printf("  %s -> Kod: %d, Mesaj: %s%n",
                    status.name(),
                    status.getCode(),
                    status.getMessage());
        }

        // Belirli bir status kullanma
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        System.out.println("\n404 Hatası detayları:");
        System.out.println("  Kod: " + notFound.getCode());
        System.out.println("  Mesaj: " + notFound.getMessage());
        System.out.println("  Başarılı mı? " + notFound.isSuccess());

        // Planet örneği - çoklu field
        System.out.println("\nGezegen Bilgileri:");
        System.out.println("─────────────────────────────────────────");
        for (Planet planet : Planet.values()) {
            System.out.printf("  %-8s - Kütle: %.2e kg, Yarıçap: %.2e m%n",
                    planet.name(),
                    planet.getMass(),
                    planet.getRadius());
        }

        System.out.println();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // BÖLÜM 4: METHOD'LU ENUM
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Enum'lara method ekleme
     */
    private static void demonstrateEnumWithMethods() {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("4. METHOD'LU ENUM (Enum with Methods)");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        // Operation enum'u - her sabit kendi davranışını tanımlar
        System.out.println("Matematiksel İşlemler:");
        System.out.println("─────────────────────────────────────────");

        double x = 10;
        double y = 3;

        for (Operation op : Operation.values()) {
            System.out.printf("  %s: %.1f %s %.1f = %.2f%n",
                    op.name(), x, op.getSymbol(), y, op.apply(x, y));
        }

        // Belirli işlem kullanma
        System.out.println("\nTek işlem örneği:");
        Operation multiply = Operation.MULTIPLY;
        double result = multiply.apply(7, 8);
        System.out.println("  7 * 8 = " + result);

        // TrafficLight örneği - duruma göre davranış
        System.out.println("\nTrafik Işığı Durumları:");
        System.out.println("─────────────────────────────────────────");
        for (TrafficLight light : TrafficLight.values()) {
            System.out.printf("  %s -> Aksiyon: %s, Süre: %d saniye%n",
                    light.name(), light.getAction(), light.getDuration());
        }

        System.out.println();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // BÖLÜM 5: CONSTRUCTOR'LU ENUM
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Enum constructor kullanımı
     */
    private static void demonstrateEnumWithConstructor() {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("5. CONSTRUCTOR'LU ENUM");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        /*
         * Enum Constructor Kuralları:
         * 1. Constructor HER ZAMAN private veya package-private olmalıdır
         * 2. public veya protected constructor YAPILAMAZ
         * 3. Constructor, enum sabiti tanımlandığında otomatik çağrılır
         */

        System.out.println("Programlama Dilleri:");
        System.out.println("─────────────────────────────────────────");
        for (ProgrammingLanguage lang : ProgrammingLanguage.values()) {
            System.out.printf("  %s%n", lang.getDescription());
            System.out.printf("    - Yıl: %d, Paradigma: %s, Typed: %s%n",
                    lang.getYear(),
                    lang.getParadigm(),
                    lang.isStronglyTyped() ? "Strong" : "Weak");
        }

        // Dil arama örneği
        System.out.println("\nDil Arama:");
        ProgrammingLanguage found = ProgrammingLanguage.findByYear(1995);
        if (found != null) {
            System.out.println("  1995'te çıkan dil: " + found.name());
        }

        System.out.println();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // BÖLÜM 6: SWITCH-CASE İLE ENUM
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Enum'ların switch-case yapısında kullanımı
     */
    private static void demonstrateEnumInSwitch() {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("6. SWITCH-CASE İLE ENUM KULLANIMI");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        // Klasik switch-case
        Day today = Day.FRIDAY;
        System.out.println("Klasik Switch-Case:");
        System.out.println("  Bugün: " + today);

        String message;
        switch (today) {
            case MONDAY:
                message = "Yeni hafta başladı! 💪";
                break;
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:
                message = "Hafta ortası, devam! 📚";
                break;
            case FRIDAY:
                message = "TGIF! Hafta sonu yaklaşıyor! 🎉";
                break;
            case SATURDAY:
            case SUNDAY:
                message = "Hafta sonu! Dinlenme zamanı! 😎";
                break;
            default:
                message = "Bilinmeyen gün";
        }
        System.out.println("  Mesaj: " + message);

        // Java 14+ Switch Expression
        System.out.println("\nJava 14+ Switch Expression:");
        Priority priority = Priority.HIGH;
        System.out.println("  Öncelik: " + priority);

        String action = switch (priority) {
            case LOW -> "Bekleyebilir";
            case MEDIUM -> "Yakında halledilmeli";
            case HIGH -> "Hemen halledilmeli!";
            case CRITICAL -> "ACİL! Şu an halledilmeli!";
        };
        System.out.println("  Aksiyon: " + action);

        // Switch expression ile yield kullanımı
        System.out.println("\nSwitch Expression (yield ile):");
        int priorityScore = switch (priority) {
            case LOW -> 1;
            case MEDIUM -> 2;
            case HIGH -> {
                System.out.println("  [LOG] Yüksek öncelikli görev!");
                yield 3;
            }
            case CRITICAL -> 4;
        };
        System.out.println("  Öncelik Skoru: " + priorityScore);

        System.out.println();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // BÖLÜM 7: ENUM KARŞILAŞTIRMA
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Enum değerlerini karşılaştırma yöntemleri
     */
    private static void demonstrateEnumComparison() {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("7. ENUM KARŞILAŞTIRMA (Comparison)");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        Season spring1 = Season.SPRING;
        Season spring2 = Season.SPRING;
        Season summer = Season.SUMMER;

        // 1. == operatörü ile karşılaştırma (ÖNERİLEN)
        System.out.println("== operatörü ile karşılaştırma:");
        System.out.println("  spring1 == spring2: " + (spring1 == spring2)); // true
        System.out.println("  spring1 == summer: " + (spring1 == summer)); // false

        // 2. equals() metodu ile karşılaştırma
        System.out.println("\nequals() metodu ile karşılaştırma:");
        System.out.println("  spring1.equals(spring2): " + spring1.equals(spring2)); // true
        System.out.println("  spring1.equals(summer): " + spring1.equals(summer)); // false

        // 3. compareTo() ile sıra karşılaştırma
        System.out.println("\ncompareTo() ile sıra karşılaştırma:");
        System.out.println("  SPRING.compareTo(SUMMER): " + Season.SPRING.compareTo(Season.SUMMER));
        System.out.println("  SUMMER.compareTo(SPRING): " + Season.SUMMER.compareTo(Season.SPRING));
        System.out.println("  SPRING.compareTo(SPRING): " + Season.SPRING.compareTo(Season.SPRING));

        /*
         * NEDEN == KULLANILMALI?
         * 
         * 1. Enum sabitleri singleton'dır - her değerden sadece bir tane vardır
         * 2. == daha hızlıdır (referans karşılaştırması)
         * 3. NullPointerException riski yoktur: null == Season.SPRING → false
         * Ama: null.equals(Season.SPRING) → NullPointerException!
         */

        System.out.println("\nNull-safe karşılaştırma:");
        Season nullSeason = null;
        System.out.println("  null == Season.SPRING: " + (nullSeason == Season.SPRING)); // false, güvenli!
        // nullSeason.equals(Season.SPRING) -> NullPointerException fırlatır!
        System.out.println("  [!] == kullanmak null-safe'dir!\n");
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // BÖLÜM 8: INTERFACE IMPLEMENT EDEN ENUM
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Enum'ların interface implement etmesi
     */
    private static void demonstrateEnumWithInterface() {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("8. INTERFACE IMPLEMENT EDEN ENUM");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        /*
         * Enum'lar interface implement edebilir!
         * Bu, polimorfizm sağlar ve farklı enum tiplerini aynı şekilde kullanmamızı
         * sağlar.
         */

        System.out.println("Describable Interface Örneği:");
        System.out.println("─────────────────────────────────────────");

        // Farklı enum'lar aynı interface'i implement ediyor
        Describable[] describables = {
                Color.RED,
                Color.GREEN,
                Size.LARGE,
                Size.SMALL
        };

        for (Describable item : describables) {
            System.out.printf("  %s: %s%n",
                    ((Enum<?>) item).name(),
                    item.getDescription());
        }

        // ErrorCode örneği - interface ile mesaj ve kod
        System.out.println("\nHata Kodları (ErrorCode interface):");
        System.out.println("─────────────────────────────────────────");
        for (FileError error : FileError.values()) {
            System.out.printf("  [%s] %s (Kod: %d)%n",
                    error.name(),
                    error.getMessage(),
                    error.getCode());
        }

        System.out.println();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // BÖLÜM 9: SINGLETON OLARAK ENUM
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Enum kullanarak Singleton pattern
     */
    private static void demonstrateEnumSingleton() {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("9. ENUM SINGLETON PATTERN");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        /*
         * Enum Singleton Avantajları:
         * 1. Thread-safe (JVM garanti eder)
         * 2. Serialization sorunsuz (otomatik)
         * 3. Reflection saldırılarına karşı korumalı
         * 4. Lazy initialization
         * 5. En basit ve güvenli singleton yöntemi
         */

        System.out.println("Database Connection Singleton:");
        System.out.println("─────────────────────────────────────────");

        // İlk erişim
        DatabaseConnection db1 = DatabaseConnection.INSTANCE;
        db1.connect();
        System.out.println("  Connection URL: " + db1.getUrl());

        // İkinci erişim - aynı instance döner
        DatabaseConnection db2 = DatabaseConnection.INSTANCE;
        System.out.println("  db1 == db2: " + (db1 == db2)); // true - singleton!

        // Configuration örneği
        System.out.println("\nApplication Configuration Singleton:");
        System.out.println("─────────────────────────────────────────");
        AppConfig config = AppConfig.INSTANCE;
        System.out.println("  App Name: " + config.getAppName());
        System.out.println("  Version: " + config.getVersion());
        System.out.println("  Debug Mode: " + config.isDebugMode());

        System.out.println("\n[!] Effective Java (Joshua Bloch) kitabında");
        System.out.println("    Enum Singleton 'en iyi singleton yöntemi' olarak önerilir!\n");
    }

    // ═══════════════════════════════════════════════════════════════════════════
    // BÖLÜM 10: İLERİ DÜZEY ENUM ÖZELLİKLERİ
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * İleri düzey enum özellikleri ve pattern'lar
     */
    private static void demonstrateAdvancedEnumFeatures() {
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("10. İLERİ DÜZEY ENUM ÖZELLİKLERİ");
        System.out.println("═══════════════════════════════════════════════════════════════\n");

        // 1. EnumSet kullanımı
        System.out.println("1. EnumSet Kullanımı:");
        System.out.println("─────────────────────────────────────────");
        java.util.EnumSet<Day> weekdays = java.util.EnumSet.range(Day.MONDAY, Day.FRIDAY);
        java.util.EnumSet<Day> weekend = java.util.EnumSet.of(Day.SATURDAY, Day.SUNDAY);

        System.out.println("  İş günleri: " + weekdays);
        System.out.println("  Hafta sonu: " + weekend);
        System.out.println("  Tüm günler: " + java.util.EnumSet.allOf(Day.class));
        System.out.println("  Boş set: " + java.util.EnumSet.noneOf(Day.class));

        // 2. EnumMap kullanımı
        System.out.println("\n2. EnumMap Kullanımı:");
        System.out.println("─────────────────────────────────────────");
        java.util.EnumMap<Season, String> seasonActivities = new java.util.EnumMap<>(Season.class);
        seasonActivities.put(Season.SPRING, "Piknik yapma zamanı! 🌸");
        seasonActivities.put(Season.SUMMER, "Tatil zamanı! ☀️");
        seasonActivities.put(Season.AUTUMN, "Kitap okuma zamanı! 🍂");
        seasonActivities.put(Season.WINTER, "Kayak zamanı! ⛷️");

        for (java.util.Map.Entry<Season, String> entry : seasonActivities.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }

        // 3. Nested Enum
        System.out.println("\n3. Nested Enum (İç İçe Enum):");
        System.out.println("─────────────────────────────────────────");
        for (Vehicle.Type type : Vehicle.Type.values()) {
            System.out.println("  " + type.name() + " - " + type.getCategory());
        }

        // 4. Abstract method ile enum
        System.out.println("\n4. Abstract Method ile Enum:");
        System.out.println("─────────────────────────────────────────");
        for (PaymentMethod payment : PaymentMethod.values()) {
            double amount = 100.0;
            System.out.printf("  %s: %.2f TL -> İşlem Ücreti: %.2f TL%n",
                    payment.name(), amount, payment.calculateFee(amount));
        }

        // 5. Enum'dan değer bulma
        System.out.println("\n5. Özel Finder Metodları:");
        System.out.println("─────────────────────────────────────────");
        HttpStatus status = HttpStatus.fromCode(404);
        System.out.println("  Kod 404 -> " + status);

        ProgrammingLanguage lang = ProgrammingLanguage.findByYear(1995);
        System.out.println("  1995 yılı -> " + lang);

        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║               ENUM KONUSU TAMAMLANDI! ✅                     ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");
    }
}

// ═══════════════════════════════════════════════════════════════════════════════
// ENUM TANIMLARI
// ═══════════════════════════════════════════════════════════════════════════════

/**
 * Basit Enum - Haftanın günleri
 */
enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

/**
 * Basit Enum - Mevsimler
 */
enum Season {
    SPRING, SUMMER, AUTUMN, WINTER
}

/**
 * Basit Enum - Öncelik seviyeleri
 */
enum Priority {
    LOW, MEDIUM, HIGH, CRITICAL
}

/**
 * Field'lı Enum - HTTP Status Kodları
 * Her enum sabitine ek veri ekleme örneği
 */
enum HttpStatus {
    OK(200, "Success"),
    CREATED(201, "Resource Created"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final int code;
    private final String message;

    // Private constructor
    HttpStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return code >= 200 && code < 300;
    }

    // Kod'dan enum bulmak için static method
    public static HttpStatus fromCode(int code) {
        for (HttpStatus status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown HTTP status code: " + code);
    }
}

/**
 * Çoklu Field'lı Enum - Gezegenler
 */
enum Planet {
    MERCURY(3.303e23, 2.4397e6),
    VENUS(4.869e24, 6.0518e6),
    EARTH(5.976e24, 6.37814e6),
    MARS(6.421e23, 3.3972e6),
    JUPITER(1.9e27, 7.1492e7),
    SATURN(5.688e26, 6.0268e7),
    URANUS(8.686e25, 2.5559e7),
    NEPTUNE(1.024e26, 2.4746e7);

    private final double mass; // kilogram
    private final double radius; // metre

    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
    }

    public double getMass() {
        return mass;
    }

    public double getRadius() {
        return radius;
    }

    // Yüzey yerçekimi hesaplama
    public double surfaceGravity() {
        double G = 6.67300E-11; // Gravitational constant
        return G * mass / (radius * radius);
    }

    // Yüzey ağırlığı hesaplama
    public double surfaceWeight(double otherMass) {
        return otherMass * surfaceGravity();
    }
}

/**
 * Abstract Method'lu Enum - Matematiksel İşlemler
 * Her enum sabiti kendi implementasyonunu sağlar
 */
enum Operation {
    ADD("+") {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    SUBTRACT("-") {
        @Override
        public double apply(double x, double y) {
            return x - y;
        }
    },
    MULTIPLY("*") {
        @Override
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        @Override
        public double apply(double x, double y) {
            if (y == 0)
                throw new ArithmeticException("Division by zero");
            return x / y;
        }
    },
    MODULO("%") {
        @Override
        public double apply(double x, double y) {
            return x % y;
        }
    },
    POWER("^") {
        @Override
        public double apply(double x, double y) {
            return Math.pow(x, y);
        }
    };

    private final String symbol;

    Operation(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    // Abstract method - her sabit implemente etmeli
    public abstract double apply(double x, double y);
}

/**
 * Method'lu Enum - Trafik Işığı
 */
enum TrafficLight {
    RED(60) {
        @Override
        public String getAction() {
            return "DUR! 🛑";
        }
    },
    YELLOW(5) {
        @Override
        public String getAction() {
            return "HAZIRLAN! ⚠️";
        }
    },
    GREEN(45) {
        @Override
        public String getAction() {
            return "GEÇE! ✅";
        }
    };

    private final int duration; // saniye

    TrafficLight(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public abstract String getAction();
}

/**
 * Detaylı Constructor'lu Enum - Programlama Dilleri
 */
enum ProgrammingLanguage {
    JAVA("Java", 1995, "Object-Oriented", true),
    PYTHON("Python", 1991, "Multi-paradigm", false),
    JAVASCRIPT("JavaScript", 1995, "Multi-paradigm", false),
    CSHARP("C#", 2000, "Object-Oriented", true),
    GO("Go", 2009, "Procedural", true),
    RUST("Rust", 2010, "Multi-paradigm", true),
    KOTLIN("Kotlin", 2011, "Object-Oriented", true);

    private final String name;
    private final int year;
    private final String paradigm;
    private final boolean stronglyTyped;

    ProgrammingLanguage(String name, int year, String paradigm, boolean stronglyTyped) {
        this.name = name;
        this.year = year;
        this.paradigm = paradigm;
        this.stronglyTyped = stronglyTyped;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getParadigm() {
        return paradigm;
    }

    public boolean isStronglyTyped() {
        return stronglyTyped;
    }

    public String getDescription() {
        return String.format("%s (%d)", name, year);
    }

    // Yıla göre dil bulma
    public static ProgrammingLanguage findByYear(int year) {
        for (ProgrammingLanguage lang : values()) {
            if (lang.year == year) {
                return lang;
            }
        }
        return null;
    }
}

/**
 * Interface tanımı - Describable
 */
interface Describable {
    String getDescription();
}

/**
 * Interface implement eden Enum - Renkler
 */
enum Color implements Describable {
    RED("Kırmızı - Tutku ve enerji rengi"),
    GREEN("Yeşil - Doğa ve huzur rengi"),
    BLUE("Mavi - Güven ve sadakat rengi"),
    YELLOW("Sarı - Mutluluk ve iyimserlik rengi");

    private final String description;

    Color(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }
}

/**
 * Interface implement eden Enum - Boyutlar
 */
enum Size implements Describable {
    SMALL("Küçük boy - Çocuklar için uygun"),
    MEDIUM("Orta boy - Standart boyut"),
    LARGE("Büyük boy - Geniş alan için"),
    EXTRA_LARGE("Ekstra büyük - Maksimum kapasite");

    private final String description;

    Size(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }
}

/**
 * Hata kodu interface'i
 */
interface ErrorCode {
    int getCode();

    String getMessage();
}

/**
 * Interface implement eden Enum - Dosya Hataları
 */
enum FileError implements ErrorCode {
    FILE_NOT_FOUND(1001, "Dosya bulunamadı"),
    PERMISSION_DENIED(1002, "Erişim reddedildi"),
    DISK_FULL(1003, "Disk dolu"),
    FILE_CORRUPTED(1004, "Dosya bozuk"),
    INVALID_FORMAT(1005, "Geçersiz format");

    private final int code;
    private final String message;

    FileError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

/**
 * Singleton Enum - Database Connection
 * Thread-safe ve serialization-safe singleton pattern
 */
enum DatabaseConnection {
    INSTANCE;

    private String url = "jdbc:mysql://localhost:3306/mydb";
    private boolean connected = false;

    public void connect() {
        if (!connected) {
            System.out.println("  Veritabanına bağlanılıyor...");
            connected = true;
            System.out.println("  Bağlantı başarılı! ✅");
        } else {
            System.out.println("  Zaten bağlı!");
        }
    }

    public void disconnect() {
        if (connected) {
            System.out.println("  Bağlantı kapatılıyor...");
            connected = false;
        }
    }

    public String getUrl() {
        return url;
    }

    public boolean isConnected() {
        return connected;
    }
}

/**
 * Singleton Enum - Application Configuration
 */
enum AppConfig {
    INSTANCE;

    private final String appName = "Java Fundamentals";
    private final String version = "1.0.0";
    private boolean debugMode = true;

    public String getAppName() {
        return appName;
    }

    public String getVersion() {
        return version;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }
}

/**
 * Nested Enum örneği - Araç sınıfı
 */
class Vehicle {
    private String name;
    private Type type;

    enum Type {
        CAR("Kara Taşıtı"),
        MOTORCYCLE("Kara Taşıtı"),
        TRUCK("Kara Taşıtı"),
        BOAT("Deniz Taşıtı"),
        SHIP("Deniz Taşıtı"),
        AIRPLANE("Hava Taşıtı"),
        HELICOPTER("Hava Taşıtı");

        private final String category;

        Type(String category) {
            this.category = category;
        }

        public String getCategory() {
            return category;
        }
    }

    public Vehicle(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }
}

/**
 * Abstract method ile ödeme yöntemleri
 * Her ödeme yöntemi kendi ücret hesaplamasını yapar
 */
enum PaymentMethod {
    CASH {
        @Override
        public double calculateFee(double amount) {
            return 0; // Nakit için komisyon yok
        }
    },
    CREDIT_CARD {
        @Override
        public double calculateFee(double amount) {
            return amount * 0.02; // %2 komisyon
        }
    },
    DEBIT_CARD {
        @Override
        public double calculateFee(double amount) {
            return amount * 0.01; // %1 komisyon
        }
    },
    BANK_TRANSFER {
        @Override
        public double calculateFee(double amount) {
            return 5.0; // Sabit 5 TL
        }
    },
    CRYPTO {
        @Override
        public double calculateFee(double amount) {
            return amount * 0.005; // %0.5 komisyon
        }
    };

    public abstract double calculateFee(double amount);
}

enum DeliveryPoint {
    CUSTOMS("Gümrük Teslim"),
    FACTORY("Fabrika Teslim");

    private final String displayName;

    DeliveryPoint(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}