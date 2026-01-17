package com.fundamentals.di;

import java.lang.reflect.Field;

/**
 * FIELD INJECTION
 * 
 * Bagimliliklarin dogrudan alanlara (fields) atanmasi.
 * Genellikle Reflection veya framework'lar tarafindan yapilir.
 * 
 * ONEMLI: Bu yontem framework disinda ONERILMEZ!
 * 
 * Dezavantajlari:
 * - Gizli bagimliliklar (constructor'da gorunmez)
 * - Immutability saglanamaz (final kullanilamaz)
 * - Test edilmesi zor (reflection gerekir)
 * - Encapsulation ihlali
 */
public class FieldInjection {

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("FIELD INJECTION");
        System.out.println("=".repeat(60));

        manualFieldInjection();
        reflectionBasedInjection();
        annotationSimulation();
        whyNotRecommended();
    }

    // ==========================================
    // 1. MANUAL FIELD INJECTION
    // ==========================================

    /**
     * Dogrudan field'lara atama.
     * Field'lar public veya package-private olmali - encapsulation ihlali!
     */
    static void manualFieldInjection() {
        System.out.println("\n1. MANUAL FIELD INJECTION");
        System.out.println("-".repeat(40));

        OrderProcessor processor = new OrderProcessor();

        // Field'lara dogrudan atama
        processor.logger = new ConsoleLogger();
        processor.validator = new OrderValidator();

        processor.process("ORD-001");

        System.out.println("\n   Problem: Field'lar public olmak zorunda.");
        System.out.println("   Problem: Encapsulation ihlali.");
    }

    interface ILogger {
        void log(String message);
    }

    interface IValidator {
        boolean validate(String data);
    }

    static class ConsoleLogger implements ILogger {
        @Override
        public void log(String message) {
            System.out.println("   [LOG] " + message);
        }
    }

    static class OrderValidator implements IValidator {
        @Override
        public boolean validate(String data) {
            System.out.println("   [Validate] " + data + " gecerli");
            return true;
        }
    }

    static class OrderProcessor {
        // Public fields - KOTU! Encapsulation ihlali
        ILogger logger;
        IValidator validator;

        public void process(String orderId) {
            if (logger != null)
                logger.log("Siparis isleniyor: " + orderId);
            if (validator != null)
                validator.validate(orderId);
        }
    }

    // ==========================================
    // 2. REFLECTION ILE FIELD INJECTION
    // ==========================================

    /**
     * Framework'lar (Spring, Guice) bu yontemi kullanir.
     * Private field'lara bile erisim saglar.
     */
    static void reflectionBasedInjection() {
        System.out.println("\n2. REFLECTION ILE FIELD INJECTION");
        System.out.println("-".repeat(40));

        try {
            UserService userService = new UserService();

            // Reflection ile private field'a erisim
            Field repositoryField = UserService.class.getDeclaredField("repository");
            repositoryField.setAccessible(true); // private'i bypass et
            repositoryField.set(userService, new InMemoryUserRepository());

            Field loggerField = UserService.class.getDeclaredField("logger");
            loggerField.setAccessible(true);
            loggerField.set(userService, new ConsoleLogger());

            // Simdi kullanilabilir
            userService.createUser("Ahmet");

            System.out.println("\n   Framework'lar bu yontemi kullanir.");
            System.out.println("   Spring @Autowired, Guice @Inject gibi.");

        } catch (Exception e) {
            System.out.println("   Hata: " + e.getMessage());
        }
    }

    interface IUserRepository {
        void save(String user);
    }

    static class InMemoryUserRepository implements IUserRepository {
        @Override
        public void save(String user) {
            System.out.println("   [REPO] Kullanici kaydedildi: " + user);
        }
    }

    static class UserService {
        // Private fields - normalde erisilemez
        private IUserRepository repository;
        private ILogger logger;

        // Constructor YOK - Field Injection kullanilacak

        public void createUser(String name) {
            if (logger != null)
                logger.log("Kullanici olusturuluyor: " + name);
            if (repository != null)
                repository.save(name);
        }
    }

    // ==========================================
    // 3. @INJECT ANNOTATION SIMULASYONU
    // ==========================================

    /**
     * Framework'lardaki @Autowired, @Inject gibi annotation'larin
     * nasil calistigini gosteren simulasyon.
     */
    static void annotationSimulation() {
        System.out.println("\n3. @INJECT ANNOTATION SIMULASYONU");
        System.out.println("-".repeat(40));

        // Basit bir DI container simulasyonu
        SimpleInjector injector = new SimpleInjector();

        // Dependency'leri kaydet
        injector.register(IPaymentGateway.class, new StripeGateway());
        injector.register(INotificationService.class, new EmailNotificationService());

        // Servis olustur ve inject et
        PaymentService paymentService = injector.createAndInject(PaymentService.class);

        if (paymentService != null) {
            paymentService.processPayment(100.0);
        }
    }

    // Custom @Inject annotation
    @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
    @java.lang.annotation.Target(java.lang.annotation.ElementType.FIELD)
    @interface Inject {
    }

    interface IPaymentGateway {
        void charge(double amount);
    }

    interface INotificationService {
        void notify(String message);
    }

    static class StripeGateway implements IPaymentGateway {
        @Override
        public void charge(double amount) {
            System.out.println("   [Stripe] Odeme: $" + amount);
        }
    }

    static class EmailNotificationService implements INotificationService {
        @Override
        public void notify(String message) {
            System.out.println("   [Email] " + message);
        }
    }

    static class PaymentService {
        @Inject
        private IPaymentGateway paymentGateway;

        @Inject
        private INotificationService notificationService;

        public void processPayment(double amount) {
            if (paymentGateway != null)
                paymentGateway.charge(amount);
            if (notificationService != null)
                notificationService.notify("Odeme alindi: $" + amount);
        }
    }

    // Basit DI Container (Annotation tabanli)
    static class SimpleInjector {
        private final java.util.Map<Class<?>, Object> registry = new java.util.HashMap<>();

        public <T> void register(Class<T> type, T implementation) {
            registry.put(type, implementation);
        }

        @SuppressWarnings("unchecked")
        public <T> T createAndInject(Class<T> clazz) {
            try {
                T instance = clazz.getDeclaredConstructor().newInstance();

                // @Inject ile isaretli field'lari bul
                for (Field field : clazz.getDeclaredFields()) {
                    if (field.isAnnotationPresent(Inject.class)) {
                        Object dependency = registry.get(field.getType());
                        if (dependency != null) {
                            field.setAccessible(true);
                            field.set(instance, dependency);
                            System.out.println("   [Injector] " + field.getName() + " inject edildi");
                        }
                    }
                }

                return instance;
            } catch (Exception e) {
                System.out.println("   Injection hatasi: " + e.getMessage());
                return null;
            }
        }
    }

    // ==========================================
    // 4. NEDEN FIELD INJECTION ONERILMEZ
    // ==========================================

    static void whyNotRecommended() {
        System.out.println("\n4. FIELD INJECTION NEDEN ONERILMEZ?");
        System.out.println("-".repeat(40));

        System.out.println("""

                PROBLEMLER:

                1. Gizli Bagimliliklar
                   - Constructor'dan bagimliliklar gorunmuyor
                   - Sinifin ne gerektirdigi belirsiz

                2. Immutability Kaybetme
                   - final kullanilamaz
                   - Field'lar sonradan degistirilebilir

                3. Test Zorlugu
                   - Mock objeler inject etmek zor
                   - Reflection kullanmak gerekir

                4. Encapsulation Ihlali
                   - Private field'lara reflection ile erisim
                   - OOP prensiplerini bozar

                TERCIH SIRASI:
                1. Constructor Injection (varsayilan)
                2. Setter Injection (opsiyonel bagimliliklar)
                3. Field Injection (sadece framework ile)
                """);
    }
}
