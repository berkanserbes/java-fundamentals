package com.fundamentals.di;

import java.util.*;

/**
 * DEPENDENCY INJECTION EN IYI UYGULAMALAR
 * 
 * DI kullanirken uyulmasi gereken en iyi uygulamalar
 * ve SOLID prensipleriyle iliskisi.
 */
public class DIBestPractices {

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("DEPENDENCY INJECTION EN IYI UYGULAMALAR");
        System.out.println("=".repeat(60));

        dependencyInversionPrinciple();
        preferConstructorInjection();
        programToInterface();
        singleResponsibility();
        testabilityExample();
        commonMistakes();
    }

    // ==========================================
    // 1. DEPENDENCY INVERSION PRINCIPLE (DIP)
    // ==========================================

    /**
     * SOLID'in "D" harfi: Dependency Inversion Principle
     * 
     * 1. Ust seviye moduller alt seviye modullere bagimli OLMAMALI
     * 2. Her ikisi de soyutlamalara (abstractions) bagimli OLMALI
     */
    static void dependencyInversionPrinciple() {
        System.out.println("\n1. DEPENDENCY INVERSION PRINCIPLE");
        System.out.println("-".repeat(40));

        System.out.println("""

                KOTU (DIP ihlali):
                OrderService -> MySqlDatabase (concrete)

                IYI (DIP uyumlu):
                OrderService -> IDatabase (abstraction)
                                   ^
                                   |
                              MySqlDatabase
                """);

        // Iyi ornek - Interface'e bagimli
        IDatabase database = new PostgresDatabase();
        OrderService orderService = new OrderService(database);
        orderService.saveOrder("ORD-001");
    }

    interface IDatabase {
        void save(String data);
    }

    static class MySqlDatabase implements IDatabase {
        @Override
        public void save(String data) {
            System.out.println("   [MySQL] " + data);
        }
    }

    static class PostgresDatabase implements IDatabase {
        @Override
        public void save(String data) {
            System.out.println("   [PostgreSQL] " + data);
        }
    }

    static class OrderService {
        private final IDatabase database;

        public OrderService(IDatabase database) {
            this.database = database;
        }

        public void saveOrder(String orderId) {
            database.save("Order: " + orderId);
        }
    }

    // ==========================================
    // 2. CONSTRUCTOR INJECTION TERCIH EDIN
    // ==========================================

    static void preferConstructorInjection() {
        System.out.println("\n2. CONSTRUCTOR INJECTION TERCIH EDIN");
        System.out.println("-".repeat(40));

        System.out.println("""

                TERCIH SIRASI:

                1. Constructor Injection (Varsayilan)
                   + Zorunlu bagimliliklari garanti eder
                   + Immutability (final fields)
                   + Test edilmesi kolay

                2. Setter Injection (Opsiyonel bagimliliklar)
                   + Opsiyonel bagimliliklar icin uygun
                   - Nesne tutarsiz olabilir

                3. Field Injection (Kacinin)
                   - Gizli bagimliliklar
                   - Test zorlugu
                """);

        ILogger logger = new ConsoleLogger();
        IEmailService emailService = new SmtpEmailService();
        ICache cache = new InMemoryCache();

        // Zorunlu: Constructor, Opsiyonel: Setter
        NotificationService service = new NotificationService(logger, emailService);
        service.setCache(cache);
        service.notify("user@email.com", "Merhaba!");
    }

    interface ILogger {
        void log(String message);
    }

    interface IEmailService {
        void send(String to, String message);
    }

    interface ICache {
        void put(String key, String value);

        String get(String key);
    }

    static class ConsoleLogger implements ILogger {
        @Override
        public void log(String message) {
            System.out.println("   [LOG] " + message);
        }
    }

    static class SmtpEmailService implements IEmailService {
        @Override
        public void send(String to, String message) {
            System.out.println("   [EMAIL] -> " + to + ": " + message);
        }
    }

    static class InMemoryCache implements ICache {
        private final Map<String, String> store = new HashMap<>();

        @Override
        public void put(String key, String value) {
            store.put(key, value);
        }

        @Override
        public String get(String key) {
            return store.get(key);
        }
    }

    static class NotificationService {
        private final ILogger logger; // Zorunlu
        private final IEmailService email; // Zorunlu
        private ICache cache; // Opsiyonel

        public NotificationService(ILogger logger, IEmailService email) {
            this.logger = Objects.requireNonNull(logger);
            this.email = Objects.requireNonNull(email);
        }

        public void setCache(ICache cache) {
            this.cache = cache;
        }

        public void notify(String to, String message) {
            logger.log("Bildirim gonderiliyor: " + to);
            email.send(to, message);
        }
    }

    // ==========================================
    // 3. INTERFACE'E PROGRAMLAYIN
    // ==========================================

    static void programToInterface() {
        System.out.println("\n3. INTERFACE'E PROGRAMLAYIN");
        System.out.println("-".repeat(40));

        System.out.println("""

                KOTU:
                MySqlDatabase db = new MySqlDatabase();

                IYI:
                IDatabase db = new MySqlDatabase();

                AVANTAJLAR:
                + Implementasyon kolayca degisir
                + Mock objeler kullanilabilir
                + Polimorfizm
                """);

        // Interface'e programlama
        IPaymentGateway stripe = new StripePayment();
        PaymentProcessor processor1 = new PaymentProcessor(stripe);
        processor1.process(100.0);

        // Kolayca degistir
        IPaymentGateway paypal = new PayPalPayment();
        PaymentProcessor processor2 = new PaymentProcessor(paypal);
        processor2.process(200.0);
    }

    interface IPaymentGateway {
        void charge(double amount);
    }

    static class StripePayment implements IPaymentGateway {
        @Override
        public void charge(double amount) {
            System.out.println("   [Stripe] $" + amount);
        }
    }

    static class PayPalPayment implements IPaymentGateway {
        @Override
        public void charge(double amount) {
            System.out.println("   [PayPal] $" + amount);
        }
    }

    static class PaymentProcessor {
        private final IPaymentGateway gateway;

        public PaymentProcessor(IPaymentGateway gateway) {
            this.gateway = gateway;
        }

        public void process(double amount) {
            gateway.charge(amount);
        }
    }

    // ==========================================
    // 4. SINGLE RESPONSIBILITY + DI
    // ==========================================

    /**
     * SRP: Bir sinifin degismesi icin sadece bir sebep olmali.
     * DI, SRP'yi destekler - her sinif kendi isinden sorumlu.
     */
    static void singleResponsibility() {
        System.out.println("\n4. SINGLE RESPONSIBILITY + DI");
        System.out.println("-".repeat(40));

        System.out.println("""

                KOTU (SRP ihlali):
                UserService {
                    validateUser()     // Validation
                    saveToDatabase()   // Persistence
                    sendEmail()        // Notification  <- Cok fazla sorumluluk!
                }

                IYI (SRP uyumlu + DI):
                UserService {
                    validator: IValidator
                    repo: IUserRepository   <- Her is ayri serviste
                    email: IEmailService
                }
                """);

        IUserValidator validator = new UserValidator();
        IUserRepository repository = new InMemoryUserRepository();
        IEmailSender emailSender = new EmailSender();

        UserRegistrationService service = new UserRegistrationService(validator, repository, emailSender);
        service.register("Ahmet", "ahmet@email.com");
    }

    interface IUserValidator {
        boolean validate(String name, String email);
    }

    interface IUserRepository {
        void save(String name, String email);
    }

    interface IEmailSender {
        void sendWelcome(String email);
    }

    static class UserValidator implements IUserValidator {
        @Override
        public boolean validate(String name, String email) {
            System.out.println("   [Validator] Dogrulama: " + name);
            return !name.isEmpty() && email.contains("@");
        }
    }

    static class InMemoryUserRepository implements IUserRepository {
        @Override
        public void save(String name, String email) {
            System.out.println("   [Repository] Kaydedildi: " + name);
        }
    }

    static class EmailSender implements IEmailSender {
        @Override
        public void sendWelcome(String email) {
            System.out.println("   [Email] Hosgeldin: " + email);
        }
    }

    static class UserRegistrationService {
        private final IUserValidator validator;
        private final IUserRepository repository;
        private final IEmailSender emailSender;

        public UserRegistrationService(
                IUserValidator validator,
                IUserRepository repository,
                IEmailSender emailSender) {
            this.validator = validator;
            this.repository = repository;
            this.emailSender = emailSender;
        }

        public void register(String name, String email) {
            if (validator.validate(name, email)) {
                repository.save(name, email);
                emailSender.sendWelcome(email);
            }
        }
    }

    // ==========================================
    // 5. TEST EDILEBILIRLIK
    // ==========================================

    static void testabilityExample() {
        System.out.println("\n5. DI ILE TEST EDILEBILIRLIK");
        System.out.println("-".repeat(40));

        System.out.println("   DI olmadan test etmek zor:");
        System.out.println("""
                   class OrderService {
                       private EmailService email = new EmailService();
                       public void process() {
                           email.send(...); // Gercek email gider!
                       }
                   }
                """);

        System.out.println("   DI ile test etmek kolay:");

        // Mock implementasyonlar
        MockLogger mockLogger = new MockLogger();
        MockEmailService mockEmail = new MockEmailService();

        TestableOrderService service = new TestableOrderService(mockLogger, mockEmail);
        service.processOrder("ORD-TEST");

        // Test dogrulamalari
        System.out.println("\n   Test sonuclari:");
        System.out.println("   - log() cagirildi: " + mockLogger.wasCalled);
        System.out.println("   - send() cagirildi: " + mockEmail.wasCalled);
        System.out.println("   - Son mesaj: " + mockEmail.lastMessage);
    }

    static class MockLogger implements ILogger {
        boolean wasCalled = false;

        @Override
        public void log(String message) {
            wasCalled = true;
            System.out.println("   [MOCK LOG] " + message);
        }
    }

    static class MockEmailService implements IEmailService {
        boolean wasCalled = false;
        String lastMessage;

        @Override
        public void send(String to, String message) {
            wasCalled = true;
            lastMessage = message;
            System.out.println("   [MOCK EMAIL] " + message);
        }
    }

    static class TestableOrderService {
        private final ILogger logger;
        private final IEmailService email;

        public TestableOrderService(ILogger logger, IEmailService email) {
            this.logger = logger;
            this.email = email;
        }

        public void processOrder(String orderId) {
            logger.log("Siparis: " + orderId);
            email.send("test@test.com", "Siparis: " + orderId);
        }
    }

    // ==========================================
    // 6. GENEL HATALAR
    // ==========================================

    static void commonMistakes() {
        System.out.println("\n6. GENEL HATALAR VE COZUMLERI");
        System.out.println("-".repeat(40));

        System.out.println("""

                1. COK FAZLA BAGIMLILIK
                   Hata: Constructor'da 5+ parametre
                   Cozum: Sinifi parcala, SRP uygula

                2. SERVICE LOCATOR KULLANIMI
                   Hata: Bagimlilik method icinde alinir
                   Cozum: Constructor injection

                3. CONCRETE SINIFA BAGIMLILIK
                   Hata: MySqlDatabase db = new MySqlDatabase()
                   Cozum: IDatabase db = new MySqlDatabase()

                4. NEW KEYWORD ILE BAGIMLILIK
                   Hata: this.logger = new ConsoleLogger()
                   Cozum: Constructor'dan al

                5. CIRCULAR DEPENDENCY
                   Hata: A->B, B->A
                   Cozum: Interface extraction, setter, redesign

                ALTIN KURAL:
                +---------------------------------------+
                | Bagimlilik disaridan gelir.           |
                | Sinif kendi bagimliligini OLUSTURMAZ! |
                +---------------------------------------+
                """);
    }
}
