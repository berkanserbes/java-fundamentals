package com.fundamentals.di;

import java.util.*;

/**
 * SETTER INJECTION
 * 
 * Opsiyonel bagimliliklar icin tercih edilir.
 * Runtime'da bagimlilik degistirilebilir.
 * 
 * Avantajlari:
 * - Opsiyonel bagimliliklar icin ideal
 * - Circular dependency cozebilir
 * - Runtime'da degisim mumkun
 * 
 * Dezavantajlari:
 * - Nesne tutarsiz durumda olabilir
 * - Her kullanımda null kontrol gerekir
 * - Immutability saglanamaz
 */
public class SetterInjection {

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("SETTER INJECTION");
        System.out.println("=".repeat(60));

        basicExample();
        optionalDependencies();
        runtimeDependencyChange();
        circularDependencySolution();
        fluentSetterPattern();
    }

    // ==========================================
    // 1. TEMEL SETTER INJECTION
    // ==========================================

    static void basicExample() {
        System.out.println("\n1. TEMEL SETTER INJECTION");
        System.out.println("-".repeat(40));

        // Nesne olustur (bagimlilik olmadan)
        ReportGenerator generator = new ReportGenerator();

        // Setter ile bagimlilik ver
        generator.setDataSource(new DatabaseDataSource());
        generator.setFormatter(new PdfFormatter());

        // Kullan
        generator.generateReport();
    }

    interface IDataSource {
        String fetchData();
    }

    interface IFormatter {
        String format(String data);
    }

    static class DatabaseDataSource implements IDataSource {
        @Override
        public String fetchData() {
            return "Veritabanindan gelen veri";
        }
    }

    static class PdfFormatter implements IFormatter {
        @Override
        public String format(String data) {
            return "[PDF] " + data;
        }
    }

    static class ExcelFormatter implements IFormatter {
        @Override
        public String format(String data) {
            return "[Excel] " + data;
        }
    }

    static class ReportGenerator {
        private IDataSource dataSource;
        private IFormatter formatter;

        public void setDataSource(IDataSource dataSource) {
            this.dataSource = dataSource;
        }

        public void setFormatter(IFormatter formatter) {
            this.formatter = formatter;
        }

        public void generateReport() {
            if (dataSource == null || formatter == null) {
                System.out.println("   [!] Bagimliliklar eksik!");
                return;
            }
            String data = dataSource.fetchData();
            String output = formatter.format(data);
            System.out.println("   Rapor: " + output);
        }
    }

    // ==========================================
    // 2. OPSIYONEL BAGIMLILIKLAR
    // ==========================================

    /**
     * Cache opsiyonel bir bagimlilik. Olmadan da calisir,
     * ama varsa performans artar.
     */
    static void optionalDependencies() {
        System.out.println("\n2. OPSIYONEL BAGIMLILIKLAR");
        System.out.println("-".repeat(40));

        // Cache olmadan
        ProductService service1 = new ProductService();
        service1.setRepository(new ProductRepository());
        System.out.println("   Cache olmadan:");
        service1.getProduct("P001");

        System.out.println();

        // Cache ile
        ProductService service2 = new ProductService();
        service2.setRepository(new ProductRepository());
        service2.setCache(new InMemoryCache()); // Opsiyonel
        System.out.println("   Cache ile:");
        service2.getProduct("P002");
        service2.getProduct("P002"); // Cache'den gelir
    }

    interface IProductRepository {
        String findById(String id);
    }

    interface ICache {
        String get(String key);

        void put(String key, String value);
    }

    static class ProductRepository implements IProductRepository {
        @Override
        public String findById(String id) {
            System.out.println("   [DB] Veritabanindan okundu: " + id);
            return "Product_" + id;
        }
    }

    static class InMemoryCache implements ICache {
        private final Map<String, String> store = new HashMap<>();

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

    static class ProductService {
        private IProductRepository repository; // Zorunlu
        private ICache cache; // Opsiyonel

        public void setRepository(IProductRepository repository) {
            if (repository == null) {
                throw new IllegalArgumentException("Repository zorunlu!");
            }
            this.repository = repository;
        }

        public void setCache(ICache cache) {
            this.cache = cache;
        }

        public String getProduct(String id) {
            // Cache varsa once kontrol
            if (cache != null) {
                String cached = cache.get(id);
                if (cached != null)
                    return cached;
            }

            // DB'den al
            String product = repository.findById(id);

            // Cache varsa yaz
            if (cache != null) {
                cache.put(id, product);
            }

            return product;
        }
    }

    // ==========================================
    // 3. RUNTIME'DA BAGIMLILIK DEGISIMI
    // ==========================================

    /**
     * Setter ile bagimlilik runtime'da degistirilebilir.
     * Constructor injection'da bu mumkun degil (final).
     */
    static void runtimeDependencyChange() {
        System.out.println("\n3. RUNTIME'DA BAGIMLILIK DEGISIMI");
        System.out.println("-".repeat(40));

        Logger logger = new Logger();

        // Baslangicta console'a yaz
        logger.setOutput(new ConsoleOutput());
        logger.log("Uygulama baslatildi");

        // Runtime'da dosyaya degistir
        logger.setOutput(new FileOutput());
        logger.log("Dosyaya loglaniyor");

        // Tekrar console'a
        logger.setOutput(new ConsoleOutput());
        logger.log("Tekrar console'da");

        System.out.println("\n   Not: Constructor injection'da final oldugu icin degisim mumkun degil.");
    }

    interface IOutput {
        void write(String message);
    }

    static class ConsoleOutput implements IOutput {
        @Override
        public void write(String message) {
            System.out.println("   [Console] " + message);
        }
    }

    static class FileOutput implements IOutput {
        @Override
        public void write(String message) {
            System.out.println("   [File] " + message);
        }
    }

    static class Logger {
        private IOutput output;

        public void setOutput(IOutput output) {
            this.output = output;
        }

        public void log(String message) {
            if (output != null) {
                output.write(message);
            }
        }
    }

    // ==========================================
    // 4. CIRCULAR DEPENDENCY COZUMU
    // ==========================================

    /**
     * Circular Dependency: A->B, B->A bagimlilik problemi.
     * Constructor ile olusturulamaz (StackOverflow veya compile error).
     * Setter ile cozulebilir: Once olustur, sonra set et.
     */
    static void circularDependencySolution() {
        System.out.println("\n4. CIRCULAR DEPENDENCY COZUMU");
        System.out.println("-".repeat(40));

        // Setter ile circular dependency cozumu
        ServiceA serviceA = new ServiceA();
        ServiceB serviceB = new ServiceB();

        // Birbirlerine set et
        serviceA.setServiceB(serviceB);
        serviceB.setServiceA(serviceA);

        // Simdi guvenle kullanilabilir
        serviceA.doWorkA();
        serviceB.doWorkB();

        System.out.println("""

                Circular Dependency:
                ServiceA --> ServiceB
                   ^             |
                   |_____________|

                Constructor ile: Compile error veya StackOverflow
                Setter ile: Once olustur, sonra set et
                """);
    }

    static class ServiceA {
        private ServiceB serviceB;

        public void setServiceB(ServiceB serviceB) {
            this.serviceB = serviceB;
        }

        public void doWorkA() {
            System.out.println("   ServiceA calisiyor");
        }

        public void callB() {
            if (serviceB != null)
                serviceB.doWorkB();
        }
    }

    static class ServiceB {
        private ServiceA serviceA;

        public void setServiceA(ServiceA serviceA) {
            this.serviceA = serviceA;
        }

        public void doWorkB() {
            System.out.println("   ServiceB calisiyor");
        }

        public void callA() {
            if (serviceA != null)
                serviceA.doWorkA();
        }
    }

    // ==========================================
    // 5. FLUENT SETTER PATTERN
    // ==========================================

    /**
     * Fluent API: Setter'lar this dondurerek method chaining saglar.
     * Builder benzeri okunabilir kod.
     */
    static void fluentSetterPattern() {
        System.out.println("\n5. FLUENT SETTER PATTERN");
        System.out.println("-".repeat(40));

        // Method chaining ile setter
        EmailClient client = new EmailClient()
                .setSmtpServer("smtp.gmail.com")
                .setPort(587)
                .setUsername("user@gmail.com")
                .setPassword("****")
                .setUseTls(true);

        client.sendEmail("test@test.com", "Test mesaji");
    }

    static class EmailClient {
        private String smtpServer;
        private int port;
        private String username;
        private String password;
        private boolean useTls;

        // Fluent setters - return this
        public EmailClient setSmtpServer(String smtpServer) {
            this.smtpServer = smtpServer;
            return this;
        }

        public EmailClient setPort(int port) {
            this.port = port;
            return this;
        }

        public EmailClient setUsername(String username) {
            this.username = username;
            return this;
        }

        public EmailClient setPassword(String password) {
            this.password = password;
            return this;
        }

        public EmailClient setUseTls(boolean useTls) {
            this.useTls = useTls;
            return this;
        }

        public void sendEmail(String to, String message) {
            System.out.println("   Email gonderiliyor...");
            System.out.println("   Server: " + smtpServer + ":" + port);
            System.out.println("   TLS: " + useTls);
            System.out.println("   Alici: " + to);
            System.out.println("   Mesaj: " + message);
        }
    }
}
