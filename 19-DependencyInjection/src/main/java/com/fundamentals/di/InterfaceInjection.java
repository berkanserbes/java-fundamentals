package com.fundamentals.di;

import java.util.*;

/**
 * INTERFACE INJECTION
 * 
 * Bagimlilik bir interface metodu araciligiyla inject edilir.
 * En az kullanilan DI turudur, ancak plugin sistemleri ve
 * framework tasariminda kullanislidir.
 */
public class InterfaceInjection {

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("INTERFACE INJECTION");
        System.out.println("=".repeat(60));

        basicExample();
        pluginSystemExample();
        lifecycleCallbackExample();
        compareWithSetterInjection();
    }

    // ==========================================
    // 1. TEMEL INTERFACE INJECTION
    // ==========================================

    /**
     * Sinif bir "injector interface" implement eder.
     * Bu interface, bagimlilik kabul etme metodunu tanimlar.
     */
    static void basicExample() {
        System.out.println("\n1. TEMEL INTERFACE INJECTION");
        System.out.println("-".repeat(40));

        ILogger logger = new ConsoleLogger();

        // ReportService hem is interface'ini hem injector interface'ini implement
        // ediyor
        ReportService service = new ReportService();

        // Injector interface uzerinden injection
        if (service instanceof ILoggerInjector) {
            ((ILoggerInjector) service).injectLogger(logger);
        }

        service.execute();

        System.out.println("\n   Sinif ILoggerInjector implement ediyor.");
        System.out.println("   Injection metodu interface'den geliyor.");
    }

    // Injector Interface - bagimlilik kabul etme sozlesmesi
    interface ILoggerInjector {
        void injectLogger(ILogger logger);
    }

    interface ILogger {
        void log(String message);
    }

    static class ConsoleLogger implements ILogger {
        @Override
        public void log(String message) {
            System.out.println("   [LOG] " + message);
        }
    }

    // Hem is interface'ini hem injector interface'ini implement ediyor
    static class ReportService implements ILoggerInjector {
        private ILogger logger;

        @Override
        public void injectLogger(ILogger logger) {
            this.logger = logger;
            System.out.println("   Logger inject edildi (Interface Injection)");
        }

        public void execute() {
            if (logger != null)
                logger.log("Rapor olusturuluyor...");
        }
    }

    // ==========================================
    // 2. PLUGIN SISTEMI ORNEGI
    // ==========================================

    /**
     * Interface Injection ozellikle plugin sistemlerinde kullanislidir.
     * Plugin'ler host uygulamanin servislerine erismek icin
     * injector interface'lerini implement eder.
     */
    static void pluginSystemExample() {
        System.out.println("\n2. PLUGIN SISTEMI ORNEGI");
        System.out.println("-".repeat(40));

        // Host uygulama context'i
        IPluginContext context = new ApplicationContext();

        // Plugin'ler
        IPlugin[] plugins = {
                new LoggingPlugin(),
                new MetricsPlugin(),
                new SecurityPlugin()
        };

        // Plugin'leri yukle ve configure et
        for (IPlugin plugin : plugins) {
            // Context injection (Interface Injection)
            if (plugin instanceof IContextAware contextAware) {
                contextAware.setContext(context);
            }

            plugin.initialize();
            plugin.execute();
        }
    }

    interface IPlugin {
        void initialize();

        void execute();
    }

    // Injector interface - Context bilgisi gerekiyorsa implement edilir
    interface IContextAware {
        void setContext(IPluginContext context);
    }

    interface IPluginContext {
        void log(String message);

        void recordMetric(String name, double value);
    }

    static class ApplicationContext implements IPluginContext {
        @Override
        public void log(String message) {
            System.out.println("   [Context] " + message);
        }

        @Override
        public void recordMetric(String name, double value) {
            System.out.println("   [Metric] " + name + " = " + value);
        }
    }

    static class LoggingPlugin implements IPlugin, IContextAware {
        private IPluginContext context;

        @Override
        public void setContext(IPluginContext context) {
            this.context = context;
        }

        @Override
        public void initialize() {
            System.out.println("   [LoggingPlugin] Baslatildi");
        }

        @Override
        public void execute() {
            if (context != null)
                context.log("LoggingPlugin calisiyor");
        }
    }

    static class MetricsPlugin implements IPlugin, IContextAware {
        private IPluginContext context;

        @Override
        public void setContext(IPluginContext context) {
            this.context = context;
        }

        @Override
        public void initialize() {
            System.out.println("   [MetricsPlugin] Baslatildi");
        }

        @Override
        public void execute() {
            if (context != null)
                context.recordMetric("requests", 42);
        }
    }

    // Context kullanmayan plugin - IContextAware implement etmiyor
    static class SecurityPlugin implements IPlugin {
        @Override
        public void initialize() {
            System.out.println("   [SecurityPlugin] Baslatildi (Context kullanmiyor)");
        }

        @Override
        public void execute() {
            System.out.println("   [Security] Guvenlik kontrolu yapildi");
        }
    }

    // ==========================================
    // 3. LIFECYCLE CALLBACK ORNEGI
    // ==========================================

    /**
     * Interface Injection, lifecycle callback'leri icin de kullanilir.
     * Framework, nesne yasam dongusunun belirli noktalarinda
     * interface metodlarini cagirir.
     */
    static void lifecycleCallbackExample() {
        System.out.println("\n3. LIFECYCLE CALLBACK ORNEGI");
        System.out.println("-".repeat(40));

        ComponentFramework framework = new ComponentFramework();

        framework.registerComponent(new DatabaseComponent());
        framework.registerComponent(new CacheComponent());

        System.out.println("\n   --- Uygulamayi Baslat ---");
        framework.startAll();

        System.out.println("\n   --- Uygulamayi Durdur ---");
        framework.stopAll();
    }

    // Lifecycle interface'leri
    interface IInitializable {
        void onInitialize();
    }

    interface IStartable {
        void onStart();
    }

    interface IStoppable {
        void onStop();
    }

    interface IComponent {
        String getName();
    }

    static class DatabaseComponent implements IComponent, IInitializable, IStartable, IStoppable {
        @Override
        public String getName() {
            return "Database";
        }

        @Override
        public void onInitialize() {
            System.out.println("   [DB] Baglanti havuzu olusturuluyor");
        }

        @Override
        public void onStart() {
            System.out.println("   [DB] Baglanti acildi");
        }

        @Override
        public void onStop() {
            System.out.println("   [DB] Baglanti kapatildi");
        }
    }

    static class CacheComponent implements IComponent, IStartable, IStoppable {
        @Override
        public String getName() {
            return "Cache";
        }

        @Override
        public void onStart() {
            System.out.println("   [Cache] Isitildi (warmed up)");
        }

        @Override
        public void onStop() {
            System.out.println("   [Cache] Temizlendi");
        }
    }

    static class ComponentFramework {
        private final List<IComponent> components = new ArrayList<>();

        public void registerComponent(IComponent component) {
            components.add(component);

            // IInitializable implement ediyorsa cagir
            if (component instanceof IInitializable init) {
                init.onInitialize();
            }
        }

        public void startAll() {
            for (IComponent component : components) {
                if (component instanceof IStartable start)
                    start.onStart();
            }
        }

        public void stopAll() {
            // Ters sirada durdur (LIFO)
            for (int i = components.size() - 1; i >= 0; i--) {
                if (components.get(i) instanceof IStoppable stop)
                    stop.onStop();
            }
        }
    }

    // ==========================================
    // 4. SETTER vs INTERFACE INJECTION
    // ==========================================

    static void compareWithSetterInjection() {
        System.out.println("\n4. SETTER vs INTERFACE INJECTION");
        System.out.println("-".repeat(40));

        System.out.println("""

                SETTER INJECTION:
                - Setter metodu sinifa ozel
                - public void setLogger(ILogger logger)

                INTERFACE INJECTION:
                - Injection metodu interface'de tanimli
                - implements ILoggerInjector
                - void injectLogger(ILogger logger)

                NE ZAMAN INTERFACE INJECTION:
                + Plugin/Extension sistemleri
                + Framework gelistirme
                + Lifecycle callback'leri
                + Standart injection sozlesmesi gerektiginde

                NE ZAMAN KULLANILMAZ:
                - Normal uygulama gelistirmede
                - Basit DI senaryolarinda
                - Constructor tercih edilmeli
                """);
    }
}
