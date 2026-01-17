package com.fundamentals.di;

import java.util.*;

/**
 * SERVICE LOCATOR PATTERN
 * 
 * Service Locator, servisleri bir merkezi registry'den alan pattern'dir.
 * DI'nin alternatifi olarak kullanilabilir, ancak genellikle ANTI-PATTERN
 * olarak kabul edilir.
 * 
 * Bu sinif hem pattern'i hem de neden kacinilmasi gerektigini gosterir.
 */
public class ServiceLocator {

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("SERVICE LOCATOR PATTERN");
        System.out.println("=".repeat(60));

        basicServiceLocator();
        serviceLocatorVsDI();
        whyItsAntiPattern();
        whenToUseServiceLocator();
    }

    // ==========================================
    // 1. TEMEL SERVICE LOCATOR
    // ==========================================

    static void basicServiceLocator() {
        System.out.println("\n1. TEMEL SERVICE LOCATOR");
        System.out.println("-".repeat(40));

        // Servisleri kaydet
        SimpleServiceLocator.register(ILogger.class, new ConsoleLogger());
        SimpleServiceLocator.register(IDatabase.class, new InMemoryDatabase());
        SimpleServiceLocator.register(IEmailService.class, new SmtpEmailService());

        // Servis locator'i kullanan sinif
        OrderProcessor processor = new OrderProcessor();
        processor.processOrder("ORD-001");

        // Temizlik
        SimpleServiceLocator.clear();
    }

    static class SimpleServiceLocator {
        private static final Map<Class<?>, Object> services = new HashMap<>();

        public static <T> void register(Class<T> type, T implementation) {
            services.put(type, implementation);
        }

        @SuppressWarnings("unchecked")
        public static <T> T get(Class<T> type) {
            T service = (T) services.get(type);
            if (service == null) {
                throw new RuntimeException("Servis bulunamadi: " + type.getName());
            }
            return service;
        }

        public static void clear() {
            services.clear();
        }
    }

    // Service Locator kullanan sinif - BAGIMLILIKLAR GIZLI!
    static class OrderProcessor {
        public void processOrder(String orderId) {
            // Bagimliliklar constructor'da degil, METHOD ICINDE aliniyor!
            ILogger logger = SimpleServiceLocator.get(ILogger.class);
            IDatabase db = SimpleServiceLocator.get(IDatabase.class);
            IEmailService email = SimpleServiceLocator.get(IEmailService.class);

            logger.log("Siparis isleniyor: " + orderId);
            db.save("Order: " + orderId);
            email.send("customer@email.com", "Siparis alindi: " + orderId);
        }
    }

    // ==========================================
    // 2. SERVICE LOCATOR vs DI
    // ==========================================

    static void serviceLocatorVsDI() {
        System.out.println("\n2. SERVICE LOCATOR vs DEPENDENCY INJECTION");
        System.out.println("-".repeat(40));

        // DI yaklasimiyla ayni sinif
        ILogger logger = new ConsoleLogger();
        IDatabase database = new InMemoryDatabase();

        // DI ile bagimliliklar ACIKCA belirtilir
        OrderProcessorWithDI processorDI = new OrderProcessorWithDI(logger, database);
        processorDI.processOrder("ORD-002");

        System.out.println("""

                SERVICE LOCATOR:
                +------------------+
                | OrderProcessor   |
                |------------------|
                | processOrder() { |
                |   locator.get()  | <- Bagimlilik GIZLI!
                | }                |
                +------------------+

                DEPENDENCY INJECTION:
                +------------------------+
                | OrderProcessor         |
                |------------------------|
                | - logger: ILogger      | <- Bagimlilik ACIK!
                | - db: IDatabase        | <- Bagimlilik ACIK!
                |------------------------|
                | OrderProcessor(l, db)  | <- Constructor'da gorunur
                +------------------------+
                """);
    }

    static class OrderProcessorWithDI {
        private final ILogger logger;
        private final IDatabase database;

        public OrderProcessorWithDI(ILogger logger, IDatabase database) {
            this.logger = logger;
            this.database = database;
        }

        public void processOrder(String orderId) {
            logger.log("Siparis isleniyor (DI): " + orderId);
            database.save("Order: " + orderId);
        }
    }

    // ==========================================
    // 3. NEDEN ANTI-PATTERN?
    // ==========================================

    static void whyItsAntiPattern() {
        System.out.println("\n3. SERVICE LOCATOR NEDEN ANTI-PATTERN?");
        System.out.println("-".repeat(40));

        System.out.println("""

                1. GIZLI BAGIMLILIKLAR
                   - Sinifin bagimliliklar belli degil
                   - Sadece kodu okuyarak anlasilmaz

                2. GLOBAL STATE
                   - Service Locator genellikle static/singleton
                   - Global state = test ve bakim zorlugu

                3. TEST ZORLUGU
                   - Mock objeler icin global state degismeli
                   - Paralel testlerde sorun cikar

                4. RUNTIME HATALARI
                   - Compile-time hata yok
                   - Servis kayitli degilse runtime exception

                5. IMPLICIT COUPLING
                   - Tum siniflar Service Locator'a bagimli
                   - Bu da bir bagimlilik!
                """);

        demonstrateTestDifficulty();
    }

    static void demonstrateTestDifficulty() {
        System.out.println("   TEST ZORLUGU:");
        System.out.println("""

                Service Locator ile:
                - Test oncesi GLOBAL STATE degismeli
                - Test sonrasi temizlenmeli
                - Paralel testler cakirisir!

                Dependency Injection ile:
                - Mock'lar constructor'a verilir
                - Her test izole
                - Paralel testlerde sorun yok
                """);
    }

    // ==========================================
    // 4. NE ZAMAN KULLANILIR?
    // ==========================================

    static void whenToUseServiceLocator() {
        System.out.println("\n4. NE ZAMAN SERVICE LOCATOR KULLANILIR?");
        System.out.println("-".repeat(40));

        System.out.println("""

                GECERLI KULLANIM SENARYOLARI:

                1. Legacy Kod Entegrasyonu
                   - Eski kod DI desteklemiyorsa
                   - Gecis sureci icin gecici cozum

                2. Framework Icsel Kullanimi
                   - Spring gibi framework'lar iceride kullanir
                   - Kullaniciya DI interface'i sunar

                3. Plugin Sistemleri
                   - Dynamic plugin yukleme
                   - Compile-time'da bilinmeyen tipler

                ONERI:
                +---------------------------------------------+
                | Varsayilan: DEPENDENCY INJECTION            |
                | Service Locator: Sadece mecbur kalindiginda |
                +---------------------------------------------+
                """);

        // Modern hybrid yaklasim
        demonstrateModernApproach();
    }

    static void demonstrateModernApproach() {
        System.out.println("   MODERN HYBRID YAKLASIM:");

        ModernContainer container = new ModernContainer();
        container.register(ILogger.class, new ConsoleLogger());
        container.register(IDatabase.class, new InMemoryDatabase());

        // Container (Service Locator) sadece baslangicta kullanilir
        // Servisler Constructor Injection ile verilir
        ModernOrderService service = new ModernOrderService(
                container.get(ILogger.class),
                container.get(IDatabase.class));

        service.process("ORD-003");

        System.out.println("""

                Container = Internal Service Locator
                Servisler = Constructor Injection ile verilir

                Service Locator'in riskleri azalir,
                DI'nin avantajlari korunur.
                """);
    }

    static class ModernContainer {
        private final Map<Class<?>, Object> services = new HashMap<>();

        public <T> void register(Class<T> type, T impl) {
            services.put(type, impl);
        }

        @SuppressWarnings("unchecked")
        public <T> T get(Class<T> type) {
            return (T) services.get(type);
        }
    }

    static class ModernOrderService {
        private final ILogger logger;
        private final IDatabase database;

        public ModernOrderService(ILogger logger, IDatabase database) {
            this.logger = logger;
            this.database = database;
        }

        public void process(String orderId) {
            logger.log("Modern yaklasim: " + orderId);
            database.save("Order: " + orderId);
        }
    }

    // ==========================================
    // INTERFACES VE IMPLEMENTATIONS
    // ==========================================

    interface ILogger {
        void log(String message);
    }

    interface IDatabase {
        void save(String data);
    }

    interface IEmailService {
        void send(String to, String message);
    }

    static class ConsoleLogger implements ILogger {
        @Override
        public void log(String message) {
            System.out.println("   [LOG] " + message);
        }
    }

    static class InMemoryDatabase implements IDatabase {
        @Override
        public void save(String data) {
            System.out.println("   [DB] Kaydedildi: " + data);
        }
    }

    static class SmtpEmailService implements IEmailService {
        @Override
        public void send(String to, String message) {
            System.out.println("   [EMAIL] -> " + to + ": " + message);
        }
    }
}
