package com.fundamentals.modules;

/**
 * Java Modul Sisteminin Temel Kavramlari
 * 
 * Bu sinif, JPMS'in temel kavramlarini ve modullerin ne oldugunu aciklar.
 */
public class ModuleBasics {
    
    public static void demonstrate() {
        System.out.println();
        
        // ============================================================
        // MODUL NEDIR?
        // ============================================================
        System.out.println("[*] MODUL NEDIR?");
        System.out.println("------------------------------------------------------------");
        System.out.println("Modul, ilgili Java paketlerini ve kaynaklarini bir araya getiren,");
        System.out.println("acik bagimliliklari ve disa aktarimlari olan bir yapidir.");
        System.out.println();
        System.out.println("+-----------------------------------------------------------+");
        System.out.println("|                     MODUL YAPISI                          |");
        System.out.println("+-----------------------------------------------------------+");
        System.out.println("|  my.module/                                               |");
        System.out.println("|  +-- module-info.java  <- Modul tanimlayici               |");
        System.out.println("|  +-- com/                                                 |");
        System.out.println("|      +-- example/                                         |");
        System.out.println("|          +-- api/        <- Disa aktarilan paket          |");
        System.out.println("|          |   +-- PublicApi.java                           |");
        System.out.println("|          +-- internal/   <- Ic paket (gizli)              |");
        System.out.println("|              +-- Helper.java                              |");
        System.out.println("+-----------------------------------------------------------+");
        System.out.println();
        
        // ============================================================
        // JAVA 9 ONCESI SORUNLAR
        // ============================================================
        System.out.println("\n[!] JAVA 9 ONCESI SORUNLAR:");
        System.out.println("------------------------------------------------------------");
        
        String[] problems = {
            "1. JAR Hell - Sinif yolu karmasikligi ve versiyon cakismalari",
            "2. Zayif Kapsulleme - Public siniflar her yerden erisilebilir",
            "3. Monolitik JDK - Tum JDK ~200MB tek bir yapi",
            "4. Internal API Erisimi - sun.*, com.sun.* paketlerine erisim mumkun",
            "5. Eksik Bagimliliklar - Calisma zamaninda ClassNotFoundException"
        };
        
        for (String problem : problems) {
            System.out.println("  [X] " + problem);
        }
        
        // ============================================================
        // MODULLERIN SAGLADIGI COZUMLER
        // ============================================================
        System.out.println("\n[+] MODULLERIN SAGLADIGI COZUMLER:");
        System.out.println("------------------------------------------------------------");
        
        String[] solutions = {
            "1. Guvenilir Yapilandirma - Acik bagimlilik bildirimleri",
            "2. Guclu Kapsulleme - Sadece export edilen paketler erisilebilir",
            "3. Moduler JDK - jlink ile ozel runtime (~30MB'a kadar dusebilir)",
            "4. Internal API Korumasi - Erisim varsayilan olarak engelli",
            "5. Derleme Zamani Kontrolu - Eksik bagimliliklar derleme hatasina"
        };
        
        for (String solution : solutions) {
            System.out.println("  [V] " + solution);
        }
        
        // ============================================================
        // MODULE-INFO.JAVA ORNEK
        // ============================================================
        System.out.println("\n[>] module-info.java ORNEK:");
        System.out.println("------------------------------------------------------------");
        System.out.println("// Basit bir modul tanimi");
        System.out.println("module com.mycompany.myapp {");
        System.out.println("    // Bagimliliklar");
        System.out.println("    requires java.sql;                  // SQL modulune bagimlilik");
        System.out.println("    requires java.logging;              // Logging modulune bagimlilik");
        System.out.println("    ");
        System.out.println("    // Disa aktarimlar");
        System.out.println("    exports com.mycompany.myapp.api;    // API paketini disa aktar");
        System.out.println("    ");
        System.out.println("    // Yansima izinleri (orn: framework'ler icin)");
        System.out.println("    opens com.mycompany.myapp.models;   // Yansimaya ac");
        System.out.println("}");
        System.out.println();
        
        // ============================================================
        // MODUL VS PAKET KARSILASTIRMASI
        // ============================================================
        System.out.println("\n[=] MODUL vs PAKET KARSILASTIRMASI:");
        System.out.println("------------------------------------------------------------");
        System.out.println("+-----------------+---------------------+---------------------+");
        System.out.println("| Ozellik         | Paket (Package)     | Modul (Module)      |");
        System.out.println("+-----------------+---------------------+---------------------+");
        System.out.println("| Kapsam          | Siniflari gruplar   | Paketleri gruplar   |");
        System.out.println("| Erisim Kontrolu | Public, protected,  | Export, opens       |");
        System.out.println("|                 | default, private    |                     |");
        System.out.println("| Bagimlilik      | import (derleme)    | requires (calisma)  |");
        System.out.println("| Tanimlama       | package anahtar     | module-info.java    |");
        System.out.println("|                 | kelimesi            |                     |");
        System.out.println("| Kapsulleme      | Sinif seviyesi      | Paket seviyesi      |");
        System.out.println("+-----------------+---------------------+---------------------+");
        System.out.println();
        
        // ============================================================
        // MODUL KAVRAMININ EVRIMI
        // ============================================================
        System.out.println("[>] MODUL KAVRAMININ EVRIMI:");
        System.out.println("------------------------------------------------------------");
        System.out.println("2005 | JSR 277 - Java Module System (ilk girisim)");
        System.out.println("     |");
        System.out.println("2008 | JSR 294 - Improved Modularity Support");
        System.out.println("     |");
        System.out.println("2011 | Project Jigsaw baslangici");
        System.out.println("     |");
        System.out.println("2014 | JEP 200 - The Modular JDK");
        System.out.println("     |");
        System.out.println("2017 | Java 9 - JPMS resmi olarak yayinlandi (JSR 376)");
        System.out.println("     |");
        System.out.println("2018+| Moduler ekosistem gelisimi devam ediyor");
        System.out.println();
        
        // ============================================================
        // GECIS MATRISI
        // ============================================================
        System.out.println("\n[~] MODULER JAVA'YA GECIS:");
        System.out.println("------------------------------------------------------------");
        System.out.println("Asama 1: Classpath'te calistir (isimsiz modul)");
        System.out.println("         +-- Mevcut uygulamalar degisiklik olmadan calisir");
        System.out.println("         ");
        System.out.println("Asama 2: Automatic Module olarak kullan");
        System.out.println("         +-- JAR'i module path'e tasi (module-info yok)");
        System.out.println("         ");
        System.out.println("Asama 3: Named Module olustur");
        System.out.println("         +-- module-info.java ekle ve bagimliliklari tanimla");
        System.out.println("         ");
        System.out.println("Asama 4: Tam moduler uygulama");
        System.out.println("         +-- jlink ile ozel runtime olustur");
    }
}
