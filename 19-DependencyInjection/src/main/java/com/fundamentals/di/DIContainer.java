package com.fundamentals.di;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.function.Supplier;

/**
 * SIMPLE DI CONTAINER IMPLEMENTASYONU
 * 
 * Framework kullanmadan basit bir DI Container'in nasil calistigini gosterir.
 * Spring, Guice gibi framework'larin temel prensiplerini anlamak icin
 * faydalidir.
 * 
 * Ozellikler:
 * - Service registration (tip kaydi)
 * - Dependency resolution (bagimlilik cozumleme)
 * - Lifecycle management (Singleton vs Transient)
 * - Constructor injection destegi
 */
public class DIContainer {

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("SIMPLE DI CONTAINER IMPLEMENTASYONU");
        System.out.println("=".repeat(60));

        basicContainerUsage();
        singletonVsTransient();
        automaticDependencyResolution();
        realWorldExample();
    }

    // ==========================================
    // 1. TEMEL CONTAINER KULLANIMI
    // ==========================================

    static void basicContainerUsage() {
        System.out.println("\n1. TEMEL CONTAINER KULLANIMI");
        System.out.println("-".repeat(40));

        // Container olustur
        SimpleContainer container = new SimpleContainer();

        // Servisleri kaydet (Interface -> Implementation)
        container.register(ILogger.class, ConsoleLogger.class);
        container.register(IDatabase.class, InMemoryDatabase.class);

        // Servisleri coz (resolve)
        ILogger logger = container.resolve(ILogger.class);
        IDatabase database = container.resolve(IDatabase.class);

        // Kullan
        logger.log("Container calisiyor!");
        database.save("Test verisi");
    }

    // ==========================================
    // 2. SINGLETON vs TRANSIENT LIFECYCLE
    // ==========================================

    /**
     * Singleton: Her resolve'da ayni instance doner
     * Transient: Her resolve'da yeni instance olusturur
     */
    static void singletonVsTransient() {
        System.out.println("\n2. SINGLETON vs TRANSIENT LIFECYCLE");
        System.out.println("-".repeat(40));

        SimpleContainer container = new SimpleContainer();

        // Singleton: Her seferinde ayni instance
        container.registerSingleton(ILogger.class, ConsoleLogger::new);

        // Transient: Her seferinde yeni instance
        container.registerTransient(IDatabase.class, InMemoryDatabase::new);

        System.out.println("   Singleton ILogger:");
        ILogger logger1 = container.resolve(ILogger.class);
        ILogger logger2 = container.resolve(ILogger.class);
        System.out.println("   logger1 == logger2: " + (logger1 == logger2));

        System.out.println("\n   Transient IDatabase:");
        IDatabase db1 = container.resolve(IDatabase.class);
        IDatabase db2 = container.resolve(IDatabase.class);
        System.out.println("   db1 == db2: " + (db1 == db2));

        System.out.println("""

                Lifecycle Turleri:
                - Singleton: Uygulama boyunca tek instance
                - Transient: Her resolve'da yeni instance
                - Scoped: Belirli scope icinde tek (orn: HTTP request)
                """);
    }

    // ==========================================
    // 3. OTOMATIK BAGIMLILIK COZUMLEME
    // ==========================================

    /**
     * Advanced Container, constructor parametrelerini otomatik cozumler.
     * UserService(ILogger, IDatabase) icin her iki bagimlilik otomatik inject
     * edilir.
     */
    static void automaticDependencyResolution() {
        System.out.println("\n3. OTOMATIK BAGIMLILIK COZUMLEME");
        System.out.println("-".repeat(40));

        AdvancedContainer container = new AdvancedContainer();

        // Interface -> Implementation kayit
        container.register(ILogger.class, ConsoleLogger.class);
        container.register(IDatabase.class, InMemoryDatabase.class);
        container.register(IEmailService.class, SmtpEmailService.class);

        // UserService constructor'da ILogger ve IDatabase istiyor
        container.register(UserService.class, UserService.class);

        // Container otomatik olarak constructor bagimliliklarini cozumler!
        UserService userService = container.resolve(UserService.class);
        userService.createUser("Ahmet");

        System.out.println("\n   Container otomatik olarak constructor parametrelerini cozumledi.");
    }

    // ==========================================
    // 4. GERCEK DUNYA ORNEGI
    // ==========================================

    static void realWorldExample() {
        System.out.println("\n4. GERCEK DUNYA ORNEGI - E-Ticaret");
        System.out.println("-".repeat(40));

        // Container olustur ve konfigure et
        AdvancedContainer container = configureServices();

        // OrderController'i resolve et (tum bagimliliklar otomatik gelir)
        OrderController controller = container.resolve(OrderController.class);
        controller.createOrder("ORD-001", "Laptop", 15000);
    }

    static AdvancedContainer configureServices() {
        AdvancedContainer container = new AdvancedContainer();

        // Altyapi servisleri (Singleton)
        container.registerSingleton(ILogger.class, () -> new ConsoleLogger());
        container.registerSingleton(IDatabase.class, () -> new InMemoryDatabase());

        // Domain servisleri
        container.register(IOrderRepository.class, OrderRepository.class);
        container.register(IPaymentService.class, PaymentService.class);
        container.register(INotificationService.class, NotificationService.class);

        // Application servisleri
        container.register(OrderService.class, OrderService.class);
        container.register(OrderController.class, OrderController.class);

        return container;
    }

    // ==========================================
    // INTERFACES
    // ==========================================

    interface ILogger {
        void log(String message);
    }

    interface IDatabase {
        void save(String data);

        String find(String id);
    }

    interface IEmailService {
        void send(String to, String message);
    }

    interface IOrderRepository {
        void save(Order order);
    }

    interface IPaymentService {
        boolean processPayment(double amount);
    }

    interface INotificationService {
        void notify(String message);
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

    static class InMemoryDatabase implements IDatabase {
        private final Map<String, String> store = new HashMap<>();

        @Override
        public void save(String data) {
            String id = "ID-" + store.size();
            store.put(id, data);
            System.out.println("   [DB] Kaydedildi: " + data);
        }

        @Override
        public String find(String id) {
            return store.get(id);
        }
    }

    static class SmtpEmailService implements IEmailService {
        @Override
        public void send(String to, String message) {
            System.out.println("   [SMTP] -> " + to + ": " + message);
        }
    }

    static class UserService {
        private final ILogger logger;
        private final IDatabase database;

        // Constructor injection - Container tarafindan cozulecek
        public UserService(ILogger logger, IDatabase database) {
            this.logger = logger;
            this.database = database;
        }

        public void createUser(String name) {
            logger.log("Kullanici olusturuluyor: " + name);
            database.save("User: " + name);
        }
    }

    // E-ticaret siniflari
    record Order(String id, String product, double price) {
    }

    static class OrderRepository implements IOrderRepository {
        private final IDatabase database;

        public OrderRepository(IDatabase database) {
            this.database = database;
        }

        @Override
        public void save(Order order) {
            database.save("Order: " + order.id() + " - " + order.product());
        }
    }

    static class PaymentService implements IPaymentService {
        private final ILogger logger;

        public PaymentService(ILogger logger) {
            this.logger = logger;
        }

        @Override
        public boolean processPayment(double amount) {
            logger.log("Odeme isleniyor: " + amount + " TL");
            return true;
        }
    }

    static class NotificationService implements INotificationService {
        private final ILogger logger;

        public NotificationService(ILogger logger) {
            this.logger = logger;
        }

        @Override
        public void notify(String message) {
            logger.log("[Bildirim] " + message);
        }
    }

    static class OrderService {
        private final IOrderRepository orderRepository;
        private final IPaymentService paymentService;
        private final INotificationService notificationService;
        private final ILogger logger;

        public OrderService(IOrderRepository orderRepository,
                IPaymentService paymentService,
                INotificationService notificationService,
                ILogger logger) {
            this.orderRepository = orderRepository;
            this.paymentService = paymentService;
            this.notificationService = notificationService;
            this.logger = logger;
        }

        public void createOrder(String id, String product, double price) {
            logger.log("Siparis olusturuluyor: " + id);

            Order order = new Order(id, product, price);

            if (paymentService.processPayment(price)) {
                orderRepository.save(order);
                notificationService.notify("Siparis olusturuldu: " + id);
            }
        }
    }

    static class OrderController {
        private final OrderService orderService;

        public OrderController(OrderService orderService) {
            this.orderService = orderService;
        }

        public void createOrder(String id, String product, double price) {
            System.out.println("\n   [Controller] POST /orders");
            orderService.createOrder(id, product, price);
        }
    }

    // ==========================================
    // SIMPLE CONTAINER IMPLEMENTASYONU
    // ==========================================

    static class SimpleContainer {
        private final Map<Class<?>, Class<?>> typeRegistry = new HashMap<>();
        private final Map<Class<?>, Object> singletonInstances = new HashMap<>();
        private final Map<Class<?>, Supplier<?>> factories = new HashMap<>();
        private final Set<Class<?>> singletonTypes = new HashSet<>();

        public <T> void register(Class<T> serviceType, Class<? extends T> implementationType) {
            typeRegistry.put(serviceType, implementationType);
        }

        public <T> void registerSingleton(Class<T> serviceType, Supplier<T> factory) {
            factories.put(serviceType, factory);
            singletonTypes.add(serviceType);
        }

        public <T> void registerTransient(Class<T> serviceType, Supplier<T> factory) {
            factories.put(serviceType, factory);
            singletonTypes.remove(serviceType);
        }

        @SuppressWarnings("unchecked")
        public <T> T resolve(Class<T> serviceType) {
            // Factory varsa kullan
            if (factories.containsKey(serviceType)) {
                if (singletonTypes.contains(serviceType)) {
                    return (T) singletonInstances.computeIfAbsent(serviceType,
                            k -> factories.get(k).get());
                } else {
                    return (T) factories.get(serviceType).get();
                }
            }

            // Type registry'den cozumle
            Class<?> implType = typeRegistry.get(serviceType);
            if (implType == null) {
                throw new RuntimeException("Servis bulunamadi: " + serviceType.getName());
            }

            try {
                return (T) implType.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Instance olusturulamadi: " + implType.getName(), e);
            }
        }
    }

    // ==========================================
    // ADVANCED CONTAINER (Otomatik Resolution)
    // ==========================================

    static class AdvancedContainer {
        private final Map<Class<?>, Class<?>> typeRegistry = new HashMap<>();
        private final Map<Class<?>, Object> singletonInstances = new HashMap<>();
        private final Map<Class<?>, Supplier<?>> factories = new HashMap<>();
        private final Set<Class<?>> singletonTypes = new HashSet<>();

        public <T> void register(Class<T> serviceType, Class<? extends T> implementationType) {
            typeRegistry.put(serviceType, implementationType);
        }

        public <T> void registerSingleton(Class<T> serviceType, Supplier<T> factory) {
            factories.put(serviceType, factory);
            singletonTypes.add(serviceType);
        }

        @SuppressWarnings("unchecked")
        public <T> T resolve(Class<T> serviceType) {
            // Singleton kontrolu
            if (singletonTypes.contains(serviceType) && singletonInstances.containsKey(serviceType)) {
                return (T) singletonInstances.get(serviceType);
            }

            // Factory varsa kullan
            if (factories.containsKey(serviceType)) {
                T instance = (T) factories.get(serviceType).get();
                if (singletonTypes.contains(serviceType)) {
                    singletonInstances.put(serviceType, instance);
                }
                return instance;
            }

            // Type registry'den cozumle
            Class<?> implType = typeRegistry.getOrDefault(serviceType, serviceType);

            try {
                Constructor<?>[] constructors = implType.getConstructors();
                if (constructors.length == 0) {
                    return (T) implType.getDeclaredConstructor().newInstance();
                }

                // En fazla parametreli constructor'i sec
                Constructor<?> constructor = constructors[0];
                for (Constructor<?> c : constructors) {
                    if (c.getParameterCount() > constructor.getParameterCount()) {
                        constructor = c;
                    }
                }

                // Constructor parametrelerini recursive olarak cozumle
                Class<?>[] paramTypes = constructor.getParameterTypes();
                Object[] params = new Object[paramTypes.length];

                for (int i = 0; i < paramTypes.length; i++) {
                    params[i] = resolve(paramTypes[i]);
                }

                T instance = (T) constructor.newInstance(params);

                if (singletonTypes.contains(serviceType)) {
                    singletonInstances.put(serviceType, instance);
                }

                return instance;

            } catch (Exception e) {
                throw new RuntimeException("Instance olusturulamadi: " + implType.getName(), e);
            }
        }
    }
}
