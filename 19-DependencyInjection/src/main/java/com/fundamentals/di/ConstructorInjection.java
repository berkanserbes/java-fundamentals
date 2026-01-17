package com.fundamentals.di;

/**
 * CONSTRUCTOR INJECTION
 * 
 * En cok tercih edilen DI yontemidir.
 * Zorunlu bagimliliklari garanti eder ve immutability saglar.
 * 
 * Avantajlari:
 * - Immutability (final fields)
 * - Zorunlu bagimliliklari garanti eder
 * - Circular dependency compile-time hata verir
 * - Test edilmesi kolay
 */
public class ConstructorInjection {

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("CONSTRUCTOR INJECTION");
        System.out.println("=".repeat(60));

        basicExample();
        multipleDependencies();
        requiredVsOptional();
        realWorldExample();
    }

    // ==========================================
    // 1. TEMEL ORNEK
    // ==========================================

    static void basicExample() {
        System.out.println("\n1. TEMEL CONSTRUCTOR INJECTION");
        System.out.println("-".repeat(40));

        // Bagimlilik olustur
        IMessageService emailService = new EmailService();

        // Constructor uzerinden inject et
        NotificationManager manager = new NotificationManager(emailService);
        manager.sendNotification("Merhaba Dunya!");

        // Farkli implementasyon - ayni interface
        IMessageService smsService = new SmsService();
        NotificationManager smsManager = new NotificationManager(smsService);
        smsManager.sendNotification("SMS ile bildirim");
    }

    interface IMessageService {
        void send(String message);
    }

    static class EmailService implements IMessageService {
        @Override
        public void send(String message) {
            System.out.println("   [Email] " + message);
        }
    }

    static class SmsService implements IMessageService {
        @Override
        public void send(String message) {
            System.out.println("   [SMS] " + message);
        }
    }

    static class NotificationManager {
        private final IMessageService messageService; // final = immutable

        public NotificationManager(IMessageService messageService) {
            // Null kontrolu - Defensive Programming
            if (messageService == null) {
                throw new IllegalArgumentException("MessageService null olamaz!");
            }
            this.messageService = messageService;
        }

        public void sendNotification(String message) {
            messageService.send(message);
        }
    }

    // ==========================================
    // 2. BIRDEN FAZLA BAGIMLILIK
    // ==========================================

    /**
     * Bir servis birden fazla bagimliligi constructor'dan alabilir.
     * Her bagimlilik final olarak tanimlanir.
     */
    static void multipleDependencies() {
        System.out.println("\n2. BIRDEN FAZLA BAGIMLILIK");
        System.out.println("-".repeat(40));

        ILogger logger = new ConsoleLogger();
        IUserRepository userRepo = new InMemoryUserRepository();
        IEmailService emailSvc = new SmtpEmailService();

        // Tek constructor'da tum bagimliliklar
        UserRegistrationService service = new UserRegistrationService(
                logger, userRepo, emailSvc);

        service.registerUser("ahmet", "ahmet@email.com");
    }

    interface ILogger {
        void log(String message);
    }

    interface IUserRepository {
        void save(User user);

        User findByEmail(String email);
    }

    interface IEmailService {
        void sendWelcomeEmail(String to);
    }

    record User(String name, String email) {
    }

    static class ConsoleLogger implements ILogger {
        @Override
        public void log(String message) {
            System.out.println("   [LOG] " + message);
        }
    }

    static class InMemoryUserRepository implements IUserRepository {
        private final java.util.Map<String, User> users = new java.util.HashMap<>();

        @Override
        public void save(User user) {
            users.put(user.email(), user);
            System.out.println("   [REPO] Kullanici kaydedildi: " + user.name());
        }

        @Override
        public User findByEmail(String email) {
            return users.get(email);
        }
    }

    static class SmtpEmailService implements IEmailService {
        @Override
        public void sendWelcomeEmail(String to) {
            System.out.println("   [EMAIL] Hosgeldin maili gonderildi: " + to);
        }
    }

    static class UserRegistrationService {
        private final ILogger logger;
        private final IUserRepository userRepository;
        private final IEmailService emailService;

        public UserRegistrationService(
                ILogger logger,
                IUserRepository userRepository,
                IEmailService emailService) {
            this.logger = logger;
            this.userRepository = userRepository;
            this.emailService = emailService;
        }

        public void registerUser(String name, String email) {
            logger.log("Kayit islemi basliyor: " + name);

            User user = new User(name, email);
            userRepository.save(user);
            emailService.sendWelcomeEmail(email);

            logger.log("Kayit tamamlandi");
        }
    }

    // ==========================================
    // 3. ZORUNLU vs OPSIYONEL BAGIMLILIKLAR
    // ==========================================

    /**
     * Zorunlu bagimliliklar: Constructor'da mutlaka olmali
     * Opsiyonel bagimliliklar: Overloaded constructor veya setter ile
     */
    static void requiredVsOptional() {
        System.out.println("\n3. ZORUNLU vs OPSIYONEL BAGIMLILIKLAR");
        System.out.println("-".repeat(40));

        IDataSource dataSource = new DatabaseSource();
        ICache cache = new RedisCache();

        // Sadece zorunlu bagimlilik
        System.out.println("   Cache olmadan:");
        DataService service1 = new DataService(dataSource);
        service1.getData("key1");

        // Zorunlu + opsiyonel
        System.out.println("\n   Cache ile:");
        DataService service2 = new DataService(dataSource, cache);
        service2.getData("key2");
        service2.getData("key2"); // Cache'den gelir
    }

    interface IDataSource {
        String fetch(String key);
    }

    interface ICache {
        String get(String key);

        void put(String key, String value);
    }

    static class DatabaseSource implements IDataSource {
        @Override
        public String fetch(String key) {
            System.out.println("   [DB] Veritabanindan okundu: " + key);
            return "data_" + key;
        }
    }

    static class RedisCache implements ICache {
        private final java.util.Map<String, String> store = new java.util.HashMap<>();

        @Override
        public String get(String key) {
            String value = store.get(key);
            if (value != null) {
                System.out.println("   [Cache] HIT: " + key);
            }
            return value;
        }

        @Override
        public void put(String key, String value) {
            store.put(key, value);
            System.out.println("   [Cache] Yazildi: " + key);
        }
    }

    static class DataService {
        private final IDataSource dataSource; // Zorunlu
        private final ICache cache; // Opsiyonel

        // Constructor 1: Sadece zorunlu
        public DataService(IDataSource dataSource) {
            this(dataSource, null);
        }

        // Constructor 2: Zorunlu + opsiyonel
        public DataService(IDataSource dataSource, ICache cache) {
            if (dataSource == null) {
                throw new IllegalArgumentException("DataSource zorunludur!");
            }
            this.dataSource = dataSource;
            this.cache = cache;
        }

        public String getData(String key) {
            // Once cache kontrol (opsiyonel)
            if (cache != null) {
                String cached = cache.get(key);
                if (cached != null)
                    return cached;
            }

            // Cache'de yoksa DB'den al
            String data = dataSource.fetch(key);

            // Cache'e yaz (opsiyonel)
            if (cache != null) {
                cache.put(key, data);
            }

            return data;
        }
    }

    // ==========================================
    // 4. GERCEK DUNYA ORNEGI - E-TICARET
    // ==========================================

    static void realWorldExample() {
        System.out.println("\n4. GERCEK DUNYA ORNEGI - E-Ticaret Siparis Islemi");
        System.out.println("-".repeat(40));

        // Bagimliliklar
        IInventoryService inventory = new WarehouseInventory();
        IPaymentService payment = new CreditCardPayment();
        IShippingService shipping = new CargoShipping();
        INotificationService notification = new EmailNotification();

        // Order Service tum bagimliliklari aliyor
        OrderProcessingService orderService = new OrderProcessingService(
                inventory, payment, shipping, notification);

        // Siparis islemlerini yap
        Order order = new Order("ORD-001", "Laptop", 1, 15000.0);
        orderService.processOrder(order);
    }

    record Order(String id, String product, int quantity, double price) {
    }

    interface IInventoryService {
        boolean checkStock(String product, int quantity);

        void reserveStock(String product, int quantity);
    }

    interface IPaymentService {
        boolean processPayment(double amount);
    }

    interface IShippingService {
        String createShipment(Order order);
    }

    interface INotificationService {
        void notifyCustomer(String orderId, String message);
    }

    static class WarehouseInventory implements IInventoryService {
        @Override
        public boolean checkStock(String product, int quantity) {
            System.out.println("   [Envanter] Stok kontrol: " + product);
            return true;
        }

        @Override
        public void reserveStock(String product, int quantity) {
            System.out.println("   [Envanter] Stok ayrildi: " + product + " x" + quantity);
        }
    }

    static class CreditCardPayment implements IPaymentService {
        @Override
        public boolean processPayment(double amount) {
            System.out.println("   [Odeme] Kredi karti ile: " + amount + " TL");
            return true;
        }
    }

    static class CargoShipping implements IShippingService {
        @Override
        public String createShipment(Order order) {
            String trackingNo = "TRK-" + System.currentTimeMillis() % 100000;
            System.out.println("   [Kargo] Gonderi olusturuldu: " + trackingNo);
            return trackingNo;
        }
    }

    static class EmailNotification implements INotificationService {
        @Override
        public void notifyCustomer(String orderId, String message) {
            System.out.println("   [Bildirim] " + orderId + ": " + message);
        }
    }

    static class OrderProcessingService {
        private final IInventoryService inventoryService;
        private final IPaymentService paymentService;
        private final IShippingService shippingService;
        private final INotificationService notificationService;

        public OrderProcessingService(
                IInventoryService inventoryService,
                IPaymentService paymentService,
                IShippingService shippingService,
                INotificationService notificationService) {
            this.inventoryService = inventoryService;
            this.paymentService = paymentService;
            this.shippingService = shippingService;
            this.notificationService = notificationService;
        }

        public void processOrder(Order order) {
            System.out.println("\n   Siparis isleniyor: " + order.id());

            // 1. Stok kontrol
            if (!inventoryService.checkStock(order.product(), order.quantity())) {
                notificationService.notifyCustomer(order.id(), "Stokta yok!");
                return;
            }

            // 2. Stok ayir
            inventoryService.reserveStock(order.product(), order.quantity());

            // 3. Odeme al
            if (!paymentService.processPayment(order.price())) {
                notificationService.notifyCustomer(order.id(), "Odeme basarisiz!");
                return;
            }

            // 4. Kargo olustur
            String trackingNo = shippingService.createShipment(order);

            // 5. Musteriye bildir
            notificationService.notifyCustomer(order.id(),
                    "Siparisiniz hazirlandi. Takip No: " + trackingNo);
        }
    }
}
