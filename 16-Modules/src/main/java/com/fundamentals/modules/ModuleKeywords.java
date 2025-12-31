package com.fundamentals.modules;

/**
 * Java Module System Anahtar Kelimeleri
 */
public class ModuleKeywords {
    
    public static void demonstrate() {
        System.out.println();
        
        System.out.println("[1] 'module' ANAHTAR KELIMESI:");
        System.out.println("------------------------------------------------------------");
        System.out.println("Modul tanimlamak icin kullanilir.");
        System.out.println("Ornek: module com.company.application { }");
        System.out.println();
        
        System.out.println("[2] 'requires' ANAHTAR KELIMESI:");
        System.out.println("------------------------------------------------------------");
        System.out.println("Baska bir module bagimlilik bildirir.");
        System.out.println();
        System.out.println("  * requires java.sql              -> Normal bagimlilik");
        System.out.println("  * requires transitive java.logging -> Gecisli bagimlilik");
        System.out.println("  * requires static java.compiler     -> Derleme zamani bagimlilik");
        System.out.println();
        
        System.out.println("[3] 'exports' ANAHTAR KELIMESI:");
        System.out.println("------------------------------------------------------------");
        System.out.println("Bir paketi diger modullere erisilebilir kilar.");
        System.out.println();
        System.out.println("  * exports com.lib.api               -> Herkese acik");
        System.out.println("  * exports com.lib.internal to com.trusted -> Sadece belirli module");
        System.out.println();
        
        System.out.println("[4] 'opens' ANAHTAR KELIMESI:");
        System.out.println("------------------------------------------------------------");
        System.out.println("Yansima (reflection) erisimi icin paketi acar.");
        System.out.println();
        System.out.println("  * opens com.app.models              -> Tum modullere yansima izni");
        System.out.println("  * opens com.app.dto to com.fasterxml.jackson -> Belirli module");
        System.out.println("  * open module com.app { }           -> Tum paketler yansimaya acik");
        System.out.println();
        
        System.out.println("[=] exports vs opens KARSILASTIRMASI:");
        System.out.println("+----------------+------------------+------------------+");
        System.out.println("|                | exports          | opens            |");
        System.out.println("+----------------+------------------+------------------+");
        System.out.println("| Derleme zamani | Public erisim [V]| Erisim yok [X]   |");
        System.out.println("| Calisma zamani | Public erisim [V]| Yansima ile [V]  |");
        System.out.println("| Private erisim | Hayir [X]        | Evet [V]         |");
        System.out.println("+----------------+------------------+------------------+");
        System.out.println();
        
        System.out.println("[5] 'provides ... with' ANAHTAR KELIMESI:");
        System.out.println("------------------------------------------------------------");
        System.out.println("Servis saglayici implementasyonunu bildirir.");
        System.out.println("Ornek: provides PaymentService with CreditCardPayment;");
        System.out.println();
        
        System.out.println("[6] 'uses' ANAHTAR KELIMESI:");
        System.out.println("------------------------------------------------------------");
        System.out.println("Bir servisi kullandigini bildirir.");
        System.out.println("Ornek: uses com.api.PaymentService;");
    }
}
