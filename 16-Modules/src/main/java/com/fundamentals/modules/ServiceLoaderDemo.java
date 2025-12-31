package com.fundamentals.modules;

import java.util.ServiceLoader;

/**
 * ServiceLoader Pattern Demo
 * 
 * Java'nin ServiceLoader mekanizmasi, modul sistemindeki
 * provides/uses direktifleri ile birlikte calisir.
 */
public class ServiceLoaderDemo {
    
    public static void demonstrate() {
        System.out.println();
        
        System.out.println("[*] SERVICE LOADER PATTERN:");
        System.out.println("------------------------------------------------------------");
        System.out.println("ServiceLoader, loose coupling saglayan bir servis kesif mekanizmasidir.");
        System.out.println();
        
        System.out.println("[>] TASARIM:");
        System.out.println("+-----------------------------------------------------------+");
        System.out.println("|     [API Modulu]              [Provider Modulu]           |");
        System.out.println("|  +------------------+      +---------------------+        |");
        System.out.println("|  | PaymentService   |<-----| CreditCardPayment   |        |");
        System.out.println("|  |   (interface)    |      | PayPalPayment       |        |");
        System.out.println("|  +------------------+      +---------------------+        |");
        System.out.println("|          ^                         |                      |");
        System.out.println("|          | uses                    | provides             |");
        System.out.println("|  +-------+---------+               |                      |");
        System.out.println("|  | Consumer Modulu |<--------------+                      |");
        System.out.println("|  | ServiceLoader   |                                      |");
        System.out.println("|  +-----------------+                                      |");
        System.out.println("+-----------------------------------------------------------+");
        System.out.println();
        
        System.out.println("[>] module-info.java YAPILANDIRMASI:");
        System.out.println();
        System.out.println("// API Modulu");
        System.out.println("module com.api {");
        System.out.println("    exports com.api;");
        System.out.println("}");
        System.out.println();
        System.out.println("// Provider Modulu");
        System.out.println("module com.provider {");
        System.out.println("    requires com.api;");
        System.out.println("    provides com.api.PaymentService");
        System.out.println("        with com.provider.CreditCardPayment,");
        System.out.println("             com.provider.PayPalPayment;");
        System.out.println("}");
        System.out.println();
        System.out.println("// Consumer Modulu");
        System.out.println("module com.consumer {");
        System.out.println("    requires com.api;");
        System.out.println("    uses com.api.PaymentService;");
        System.out.println("}");
        System.out.println();
        
        System.out.println("[>] KULLANIM KODU:");
        System.out.println("------------------------------------------------------------");
        System.out.println("ServiceLoader<PaymentService> loader =");
        System.out.println("    ServiceLoader.load(PaymentService.class);");
        System.out.println();
        System.out.println("for (PaymentService service : loader) {");
        System.out.println("    service.processPayment(100.00);");
        System.out.println("}");
        System.out.println();
        System.out.println("// veya Optional ile");
        System.out.println("PaymentService service = loader.findFirst()");
        System.out.println("    .orElseThrow(() -> new Exception(\"No provider\"));");
        System.out.println();
        
        // Gercek calisan ornek
        System.out.println("[>] GERCEK ORNEK (JDK Servisleri):");
        System.out.println("------------------------------------------------------------");
        
        // Charset provider'lari listele
        ServiceLoader<java.nio.charset.spi.CharsetProvider> charsetProviders = 
            ServiceLoader.load(java.nio.charset.spi.CharsetProvider.class);
        
        System.out.println("Kayitli Charset Provider'lari:");
        int count = 0;
        for (java.nio.charset.spi.CharsetProvider provider : charsetProviders) {
            System.out.println("  * " + provider.getClass().getName());
            count++;
        }
        if (count == 0) {
            System.out.println("  (Ek provider bulunamadi - sadece varsayilanlar aktif)");
        }
        System.out.println();
        
        System.out.println("[+] AVANTAJLARI:");
        System.out.println("------------------------------------------------------------");
        System.out.println("* Loose coupling - Consumer provider'i bilmek zorunda degil");
        System.out.println("* Plugin mimarisi - Yeni provider'lar dinamik olarak eklenir");
        System.out.println("* Compile-time bagimsizlik - Provider ayri derlenebilir");
        System.out.println("* Fabrikalara alternatif - Factory pattern yerine kullanilabilir");
    }
}
