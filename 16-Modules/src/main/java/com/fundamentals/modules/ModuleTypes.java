package com.fundamentals.modules;

/**
 * Java Modul Turleri
 */
public class ModuleTypes {
    
    public static void demonstrate() {
        System.out.println();
        
        System.out.println("[*] JAVA MODUL TURLERI:");
        System.out.println("------------------------------------------------------------");
        System.out.println();
        
        // Named Module
        System.out.println("[1] NAMED MODULE (Isimli Modul)");
        System.out.println("+-----------------------------------------------------------+");
        System.out.println("| * module-info.java dosyasi ICERIR                         |");
        System.out.println("| * Acik bagimlilik ve export bildirimleri VAR              |");
        System.out.println("| * Guclu kapsulleme saglar                                 |");
        System.out.println("| * En guvenli modul turudur                                |");
        System.out.println("+-----------------------------------------------------------+");
        System.out.println();
        System.out.println("   Ornek module-info.java:");
        System.out.println("   module com.myapp {");
        System.out.println("       requires java.sql;");
        System.out.println("       exports com.myapp.api;");
        System.out.println("   }");
        System.out.println();
        
        // Automatic Module
        System.out.println("[2] AUTOMATIC MODULE (Otomatik Modul)");
        System.out.println("+-----------------------------------------------------------+");
        System.out.println("| * module-info.java dosyasi YOKTUR                         |");
        System.out.println("| * Modul yolu (--module-path) uzerinde bulunur             |");
        System.out.println("| * Modul ismi JAR dosyasinin adindan turetilir             |");
        System.out.println("| * Tum paketleri otomatik olarak export eder               |");
        System.out.println("| * Tum diger modulleri 'requires transitive' ile gorur     |");
        System.out.println("+-----------------------------------------------------------+");
        System.out.println();
        System.out.println("   Isim Turetme Ornekleri:");
        System.out.println("   * guava-28.0-jre.jar     -> guava");
        System.out.println("   * commons-lang3-3.9.jar  -> commons.lang3");
        System.out.println("   * jackson-core-2.10.jar  -> jackson.core");
        System.out.println();
        
        // Unnamed Module
        System.out.println("[3] UNNAMED MODULE (Isimsiz Modul)");
        System.out.println("+-----------------------------------------------------------+");
        System.out.println("| * Classpath'teki tum siniflar                             |");
        System.out.println("| * Geriye donuk uyumluluk icin                             |");
        System.out.println("| * Tum named ve automatic modulleri okuyabilir             |");
        System.out.println("| * Named moduller tarafindan OKUNAMAZ                      |");
        System.out.println("| * Modul sisteminin disinda calisir                        |");
        System.out.println("+-----------------------------------------------------------+");
        System.out.println();
        
        // Karsilastirma tablosu
        System.out.println("[=] MODUL TURLERI KARSILASTIRMASI:");
        System.out.println("+-----------------+---------------+---------------+---------------+");
        System.out.println("| Ozellik         | Named         | Automatic     | Unnamed       |");
        System.out.println("+-----------------+---------------+---------------+---------------+");
        System.out.println("| module-info     | Evet [V]      | Hayir [X]     | Hayir [X]     |");
        System.out.println("| Konum           | Module path   | Module path   | Classpath     |");
        System.out.println("| Export kontrolu | Acik          | Hepsini       | Hepsini       |");
        System.out.println("| Kapsulleme      | Guclu         | Zayif         | Yok           |");
        System.out.println("| jlink destegi   | Evet [V]      | Hayir [X]     | Hayir [X]     |");
        System.out.println("+-----------------+---------------+---------------+---------------+");
        System.out.println();
        
        // Erisim matrisi
        System.out.println("[?] MODUL ERISIM MATRISI (Kim kimi okuyabilir?):");
        System.out.println("+-----------------+---------------+---------------+---------------+");
        System.out.println("| Okuyan v        | Named         | Automatic     | Unnamed       |");
        System.out.println("+-----------------+---------------+---------------+---------------+");
        System.out.println("| Named           | requires ile  | requires ile  | HAYIR [X]     |");
        System.out.println("| Automatic       | Otomatik      | Otomatik      | Otomatik      |");
        System.out.println("| Unnamed         | Otomatik      | Otomatik      | Otomatik      |");
        System.out.println("+-----------------+---------------+---------------+---------------+");
    }
}
