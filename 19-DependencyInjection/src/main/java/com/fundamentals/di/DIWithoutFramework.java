package com.fundamentals.di;

import java.util.*;

/**
 * FRAMEWORK KULLANMADAN DEPENDENCY INJECTION
 * 
 * Bu sinif DI'nin framework olmadan nasil uygulandigini gosterir:
 * - Manual Dependency Injection
 * - Factory Pattern ile DI
 * - Builder Pattern ile DI
 * - Composition Root kavrami
 */
public class DIWithoutFramework {

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("FRAMEWORK KULLANMADAN DEPENDENCY INJECTION");
        System.out.println("=".repeat(60));

        manualDI();
        factoryPatternDI();
        builderPatternDI();
        compositionRoot();
    }

    // ==========================================
    // 1. MANUAL DEPENDENCY INJECTION
    // ==========================================

    /**
     * En basit DI yontemi: Bagimliliklari manuel olusturup inject et.
     * Kucuk projeler icin uygundur, buyuk projelerde yonetimi zorlar.
     */
    static void manualDI() {
        System.out.println("\n1. MANUAL DEPENDENCY INJECTION");
        System.out.println("-".repeat(40));

        // Bagimliliklari manuel olustur
        ILogger logger = new ConsoleLogger();
        IDatabase database = new MySqlDatabase();
        IEmailSender emailSender = new SmtpEmailSender();

        // Servislere inject et
        UserService userService = new UserService(logger, database);
        OrderService orderService = new OrderService(logger, emailSender);

        // Kullan
        userService.createUser("Ahmet", "ahmet@email.com");
        orderService.createOrder("ORD-001", "Laptop");

        System.out.println("\n   Avantaj: Basit, framework gerektirmez.");
        System.out.println("   Dezavantaj: Buyuk projelerde yonetimi zor.");
    }

    // ==========================================
    // 2. FACTORY PATTERN ILE DI
    // ==========================================

    /**
     * Factory Pattern: Tum servislerin olusturuldugu merkezi nokta.
     * Bagimlilik grafigi tek yerde yonetilir.
     */
    static void factoryPatternDI() {
        System.out.println("\n2. FACTORY PATTERN ILE DI");
        System.out.println("-".repeat(40));

        ServiceFactory factory = new ServiceFactory();

        UserService userService = factory.createUserService();
        OrderService orderService = factory.createOrderService();

        userService.createUser("Mehmet", "mehmet@email.com");
        orderService.createOrder("ORD-002", "Telefon");

        System.out.println("\n   Avantaj: Merkezi olusturma noktasi.");
        System.out.println("   Avantaj: Shared dependencies (singleton benzeri).");
    }

    static class ServiceFactory {
        // Shared dependencies
        private final ILogger logger = new ConsoleLogger();
        private final IDatabase database = new MySqlDatabase();
        private final IEmailSender emailSender = new SmtpEmailSender();

        public UserService createUserService() {
            return new UserService(logger, database);
        }

        public OrderService createOrderService() {
            return new OrderService(logger, emailSender);
        }

        public ReportService createReportService() {
            return new ReportService(logger, database);
        }
    }

    // ==========================================
    // 3. BUILDER PATTERN ILE DI
    // ==========================================

    /**
     * Builder Pattern: Esnek konfigürasyon ve opsiyonel bagimliliklar.
     * Fluent API ile okunabilir kod.
     */
    static void builderPatternDI() {
        System.out.println("\n3. BUILDER PATTERN ILE DI");
        System.out.println("-".repeat(40));

        ApplicationService app = new ApplicationService.Builder()
                .withLogger(new ConsoleLogger())
                .withDatabase(new MySqlDatabase())
                .withCache(new InMemoryCache())
                .build();

        app.run();

        System.out.println("\n   Avantaj: Esnek konfigürasyon.");
        System.out.println("   Avantaj: Opsiyonel bagimliliklar icin ideal.");
    }

    static class ApplicationService {
        private final ILogger logger;
        private final IDatabase database;
        private final ICache cache;

        private ApplicationService(Builder builder) {
            this.logger = builder.logger;
            this.database = builder.database;
            this.cache = builder.cache;
        }

        public void run() {
            logger.log("Uygulama baslatildi");
            System.out.println("   Veritabani: " + database.getName());

            if (cache != null) {
                cache.put("startup", "completed");
                System.out.println("   Cache kullaniliyor: " + cache.get("startup"));
            }
        }

        static class Builder {
            private ILogger logger;
            private IDatabase database;
            private ICache cache;

            public Builder withLogger(ILogger logger) {
                this.logger = logger;
                return this;
            }

            public Builder withDatabase(IDatabase database) {
                this.database = database;
                return this;
            }

            public Builder withCache(ICache cache) {
                this.cache = cache;
                return this;
            }

            public ApplicationService build() {
                if (logger == null)
                    logger = new ConsoleLogger();
                if (database == null)
                    throw new IllegalStateException("Database zorunlu!");
                return new ApplicationService(this);
            }
        }
    }

    // ==========================================
    // 4. COMPOSITION ROOT
    // ==========================================

    /**
     * Composition Root: Uygulamanin basladigi ve TUM bagimliliklarin
     * bir araya getirildigi TEK nokta.
     * 
     * Genellikle Main metodu veya Startup kodu burada bulunur.
     */
    static void compositionRoot() {
        System.out.println("\n4. COMPOSITION ROOT");
        System.out.println("-".repeat(40));

        // Composition Root - uygulama baslangic noktasi
        Application app = CompositionRoot.compose();
        app.start();

        System.out.println("""

                Composition Root Yapisi:
                +----------------------+
                |   Composition Root   | <- Uygulama giris noktasi
                |    (Main/Startup)    |
                +----------+-----------+
                           |
                +----------+-----------+
                |   Dependency Graph   | <- Tum bagimliliklar burada
                |  Logger, DB, Email   |
                |  UserSvc, OrderSvc   |
                +----------------------+
                """);
    }

    static class CompositionRoot {
        public static Application compose() {
            // 1. Altyapi bagimliliklari
            ILogger logger = new ConsoleLogger();
            IDatabase database = new MySqlDatabase();
            IEmailSender emailSender = new SmtpEmailSender();

            // 2. Servis katmani
            UserService userService = new UserService(logger, database);
            OrderService orderService = new OrderService(logger, emailSender);

            // 3. Uygulama
            return new Application(logger, userService, orderService);
        }
    }

    static class Application {
        private final ILogger logger;
        private final UserService userService;
        private final OrderService orderService;

        public Application(ILogger logger, UserService userService, OrderService orderService) {
            this.logger = logger;
            this.userService = userService;
            this.orderService = orderService;
        }

        public void start() {
            logger.log("Uygulama Composition Root ile baslatildi");
            userService.createUser("Test", "test@email.com");
            orderService.createOrder("ORD-003", "Monitor");
        }
    }

    // ==========================================
    // INTERFACES
    // ==========================================

    interface ILogger {
        void log(String message);
    }

    interface IDatabase {
        void save(String data);

        String getName();
    }

    interface IEmailSender {
        void send(String to, String message);
    }

    interface ICache {
        void put(String key, String value);

        String get(String key);
    }

    // ==========================================
    // IMPLEMENTATIONS
    // ==========================================

    static class ConsoleLogger implements ILogger {
        @Override
        public void log(String message) {
            System.out.println("   [LOG] " + message);
        }
    }

    static class MySqlDatabase implements IDatabase {
        @Override
        public void save(String data) {
            System.out.println("   [MySQL] Kaydedildi: " + data);
        }

        @Override
        public String getName() {
            return "MySQL Database";
        }
    }

    static class SmtpEmailSender implements IEmailSender {
        @Override
        public void send(String to, String message) {
            System.out.println("   [SMTP] -> " + to + ": " + message);
        }
    }

    static class InMemoryCache implements ICache {
        private final Map<String, String> cache = new HashMap<>();

        @Override
        public void put(String key, String value) {
            cache.put(key, value);
        }

        @Override
        public String get(String key) {
            return cache.getOrDefault(key, "N/A");
        }
    }

    // ==========================================
    // SERVICES
    // ==========================================

    static class UserService {
        private final ILogger logger;
        private final IDatabase database;

        public UserService(ILogger logger, IDatabase database) {
            this.logger = logger;
            this.database = database;
        }

        public void createUser(String name, String email) {
            logger.log("Kullanici olusturuluyor: " + name);
            database.save("User: " + name + ", " + email);
        }
    }

    static class OrderService {
        private final ILogger logger;
        private final IEmailSender emailSender;

        public OrderService(ILogger logger, IEmailSender emailSender) {
            this.logger = logger;
            this.emailSender = emailSender;
        }

        public void createOrder(String orderId, String product) {
            logger.log("Siparis olusturuluyor: " + orderId);
            emailSender.send("customer@email.com", "Siparis: " + product);
        }
    }

    static class ReportService {
        private final ILogger logger;
        private final IDatabase database;

        public ReportService(ILogger logger, IDatabase database) {
            this.logger = logger;
            this.database = database;
        }
    }
}
