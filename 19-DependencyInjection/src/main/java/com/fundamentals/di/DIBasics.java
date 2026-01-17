package com.fundamentals.di;

/**
 * DEPENDENCY INJECTION TEMELLERI
 * 
 * Bu sinif DI'nin temel kavramlarini aciklar:
 * - Tight Coupling vs Loose Coupling
 * - Inversion of Control (IoC) prensibi
 * - Uc temel DI yontemi
 */
public class DIBasics {

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("DEPENDENCY INJECTION TEMELLERI");
        System.out.println("=".repeat(60));

        demonstrateTightCoupling();
        demonstrateLooseCoupling();
        demonstrateDITypes();
        explainIoC();
    }

    // ==========================================
    // 1. TIGHT COUPLING (Siki Baglanti) - KOTU
    // ==========================================

    /**
     * Tight Coupling: Bir sinifin baska bir sinifa dogrudan bagimli olmasi.
     * 'new' ile bagimlilik sinif icinde olusturulur.
     * 
     * Problemleri:
     * - Test edilemez (mock kullanilamaz)
     * - Degisiklik zor (EmailService degisirse OrderService etkilenir)
     * - Kod tekrar kullanimi zor
     */
    static void demonstrateTightCoupling() {
        System.out.println("\n1. TIGHT COUPLING (Kotu Ornek)");
        System.out.println("-".repeat(40));

        TightCoupledOrderService orderService = new TightCoupledOrderService();
        orderService.placeOrder("Laptop");

        System.out.println("\n   Problem: EmailService sinif icinde 'new' ile olusturuluyor.");
        System.out.println("   Problem: Farkli bildirim tipi kullanmak icin kod degismeli.");
    }

    static class TightCoupledOrderService {
        // KOTU: Bagimlilik sinif icinde olusturuluyor
        private EmailService emailService = new EmailService();

        public void placeOrder(String product) {
            System.out.println("   Siparis olusturuldu: " + product);
            emailService.sendEmail("Siparisiniz alindi: " + product);
        }
    }

    static class EmailService {
        public void sendEmail(String message) {
            System.out.println("   [Email] " + message);
        }
    }

    // ==========================================
    // 2. LOOSE COUPLING (Gevsek Baglanti) - IYI
    // ==========================================

    /**
     * Loose Coupling: Bagimliliklar interface uzerinden saglanir.
     * Concrete class yerine abstraction kullanilir.
     * 
     * Avantajlari:
     * - Kolay test (mock objeler kullanilabilir)
     * - Kolay degisiklik (yeni implementasyon eklenebilir)
     * - Polimorfizm ile esneklik
     */
    static void demonstrateLooseCoupling() {
        System.out.println("\n2. LOOSE COUPLING (Iyi Ornek)");
        System.out.println("-".repeat(40));

        // Email ile bildirim
        INotificationService emailService = new EmailNotificationService();
        LooseCoupledOrderService orderService1 = new LooseCoupledOrderService(emailService);
        orderService1.placeOrder("Telefon");

        // SMS ile bildirim - ayni interface, farkli implementasyon
        INotificationService smsService = new SmsNotificationService();
        LooseCoupledOrderService orderService2 = new LooseCoupledOrderService(smsService);
        orderService2.placeOrder("Tablet");

        System.out.println("\n   Avantaj: Notification tipi kolayca degistirilebilir.");
        System.out.println("   Avantaj: Test ederken mock servis kullanilabilir.");
    }

    interface INotificationService {
        void notify(String message);
    }

    static class EmailNotificationService implements INotificationService {
        @Override
        public void notify(String message) {
            System.out.println("   [Email] " + message);
        }
    }

    static class SmsNotificationService implements INotificationService {
        @Override
        public void notify(String message) {
            System.out.println("   [SMS] " + message);
        }
    }

    static class LooseCoupledOrderService {
        private final INotificationService notificationService;

        // Bagimlilik disaridan veriliyor = DEPENDENCY INJECTION
        public LooseCoupledOrderService(INotificationService notificationService) {
            this.notificationService = notificationService;
        }

        public void placeOrder(String product) {
            System.out.println("   Siparis olusturuldu: " + product);
            notificationService.notify("Siparisiniz alindi: " + product);
        }
    }

    // ==========================================
    // 3. DEPENDENCY INJECTION TURLERI
    // ==========================================

    /**
     * DI Turleri:
     * 1. Constructor Injection - Zorunlu bagimliliklar icin (tercih edilen)
     * 2. Setter Injection - Opsiyonel bagimliliklar icin
     * 3. Field Injection - Framework'lar icin (onerilmez)
     */
    static void demonstrateDITypes() {
        System.out.println("\n3. DEPENDENCY INJECTION TURLERI");
        System.out.println("-".repeat(40));

        ILogger logger = new ConsoleLogger();
        IRepository repository = new UserRepository();

        // a) Constructor Injection - en yaygin ve tercih edilen
        System.out.println("\n   a) Constructor Injection:");
        ServiceWithConstructorDI service1 = new ServiceWithConstructorDI(logger);
        service1.doWork();

        // b) Setter Injection - opsiyonel bagimliliklar icin
        System.out.println("\n   b) Setter Injection:");
        ServiceWithSetterDI service2 = new ServiceWithSetterDI();
        service2.setLogger(logger);
        service2.doWork();

        // c) Birden fazla bagimlilik
        System.out.println("\n   c) Coklu Bagimlilik:");
        CompleteService service3 = new CompleteService(logger, repository);
        service3.processUser();
    }

    interface ILogger {
        void log(String message);
    }

    interface IRepository {
        String getData();
    }

    static class ConsoleLogger implements ILogger {
        @Override
        public void log(String message) {
            System.out.println("      [LOG] " + message);
        }
    }

    static class UserRepository implements IRepository {
        @Override
        public String getData() {
            return "User: Ahmet";
        }
    }

    static class ServiceWithConstructorDI {
        private final ILogger logger;

        public ServiceWithConstructorDI(ILogger logger) {
            this.logger = logger;
        }

        public void doWork() {
            logger.log("Constructor DI ile calisiyor");
        }
    }

    static class ServiceWithSetterDI {
        private ILogger logger;

        public void setLogger(ILogger logger) {
            this.logger = logger;
        }

        public void doWork() {
            if (logger != null) {
                logger.log("Setter DI ile calisiyor");
            }
        }
    }

    static class CompleteService {
        private final ILogger logger;
        private final IRepository repository;

        public CompleteService(ILogger logger, IRepository repository) {
            this.logger = logger;
            this.repository = repository;
        }

        public void processUser() {
            String data = repository.getData();
            logger.log("Islenen veri: " + data);
        }
    }

    // ==========================================
    // 4. INVERSION OF CONTROL (IoC)
    // ==========================================

    /**
     * IoC: Kontrol akisinin tersine cevrilmesi.
     * 
     * Geleneksel: Sinif -> Bagimlilik olusturur -> Kullanir
     * IoC: Dis kaynak -> Bagimlilik olusturur -> Sinifa verir
     * 
     * "Hollywood Prensibi": Bizi aramayin, biz sizi arariz.
     */
    static void explainIoC() {
        System.out.println("\n4. INVERSION OF CONTROL (IoC)");
        System.out.println("-".repeat(40));

        System.out.println("""

                Geleneksel Yaklasim:
                +------------------+
                |  OrderService    |
                |------------------|
                | new EmailSvc()   | <- Sinif bagimliligini kendisi olusturur
                +------------------+

                IoC Yaklasimi:
                +------------------+
                |  IoC Container   |
                |------------------|
                | EmailSvc olustur |
                | OrderSvc olustur |
                | Inject et        | <- Container yonetiyor
                +------------------+
                        |
                        v
                +------------------+
                |  OrderService    |
                |------------------|
                | IEmailSvc (DI)   | <- Hazir olarak aliyor
                +------------------+

                IoC Avantajlari:
                - Gevsek baglanti
                - Kolay test
                - Kolay konfigürasyon
                - Yasam dongusu yonetimi
                """);
    }
}
