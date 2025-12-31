package com.fundamentals.modules;

/**
 * Java Modul Sistemi Komut Satiri Araclari
 */
public class ModuleCommands {
    
    public static void demonstrate() {
        System.out.println();
        
        System.out.println("[*] MODUL KOMUT SATIRI ARACLARI:");
        System.out.println("------------------------------------------------------------");
        System.out.println();
        
        // Derleme Komutlari
        System.out.println("[>] DERLEME (javac):");
        System.out.println("------------------------------------------------------------");
        System.out.println("# Tek modulu derle");
        System.out.println("javac -d out --module-source-path src -m com.myapp");
        System.out.println();
        System.out.println("# Tum modulleri derle");
        System.out.println("javac -d out --module-source-path src $(find src -name '*.java')");
        System.out.println();
        System.out.println("# Bagimliliklerla derle");
        System.out.println("javac -d out --module-path libs --module-source-path src -m com.myapp");
        System.out.println();
        
        // Calistirma Komutlari
        System.out.println("[>] CALISTIRMA (java):");
        System.out.println("------------------------------------------------------------");
        System.out.println("# Moduler uygulamayi calistir");
        System.out.println("java --module-path out -m com.myapp/com.myapp.Main");
        System.out.println();
        System.out.println("# Kisa form");
        System.out.println("java -p out -m com.myapp/com.myapp.Main");
        System.out.println();
        System.out.println("# Birden fazla modul yolu");
        System.out.println("java -p out:libs -m com.myapp/com.myapp.Main");
        System.out.println();
        
        // JAR Komutlari
        System.out.println("[>] JAR OLUSTURMA:");
        System.out.println("------------------------------------------------------------");
        System.out.println("# Moduler JAR olustur");
        System.out.println("jar --create --file=mods/com.myapp.jar \\");
        System.out.println("    --module-version=1.0 \\");
        System.out.println("    --main-class=com.myapp.Main \\");
        System.out.println("    -C out/com.myapp .");
        System.out.println();
        System.out.println("# JAR modul bilgisini goster");
        System.out.println("jar --describe-module --file=mods/com.myapp.jar");
        System.out.println();
        
        // Modul Bilgisi Komutlari
        System.out.println("[>] MODUL BILGISI:");
        System.out.println("------------------------------------------------------------");
        System.out.println("# JDK modullerini listele");
        System.out.println("java --list-modules");
        System.out.println();
        System.out.println("# Belirli modulun detaylarini goster");
        System.out.println("java --describe-module java.sql");
        System.out.println();
        System.out.println("# Modul cozumlemesini goster");
        System.out.println("java --show-module-resolution -m com.myapp");
        System.out.println();
        
        // jlink Komutlari
        System.out.println("[>] jlink (Ozel Runtime):");
        System.out.println("------------------------------------------------------------");
        System.out.println("# Ozellestirilmis JRE olustur");
        System.out.println("jlink --module-path $JAVA_HOME/jmods:mods \\");
        System.out.println("      --add-modules com.myapp \\");
        System.out.println("      --output custom-runtime \\");
        System.out.println("      --strip-debug \\");
        System.out.println("      --compress zip-6");
        System.out.println();
        System.out.println("# Olusturulan runtime ile calistir");
        System.out.println("./custom-runtime/bin/java -m com.myapp");
        System.out.println();
        
        // JVM Bayraklari
        System.out.println("[>] MODUL SISTEMI JVM BAYRAKLARI:");
        System.out.println("------------------------------------------------------------");
        System.out.println("# Paket acma (reflection icin)");
        System.out.println("--add-opens java.base/java.lang=ALL-UNNAMED");
        System.out.println();
        System.out.println("# Paket export etme");
        System.out.println("--add-exports java.base/sun.security.x509=ALL-UNNAMED");
        System.out.println();
        System.out.println("# Modul okuma izni");
        System.out.println("--add-reads com.myapp=ALL-UNNAMED");
        System.out.println();
        System.out.println("# Birden fazla modul ekleme");
        System.out.println("--add-modules java.sql,java.logging");
        System.out.println();
        
        // Onemli Notlar
        System.out.println("[>] ONEMLI PARAMETRELER:");
        System.out.println("------------------------------------------------------------");
        System.out.println("+--------------------+------------------------------------+");
        System.out.println("| Parametre          | Aciklama                           |");
        System.out.println("+--------------------+------------------------------------+");
        System.out.println("| --module-path (-p) | Modul arama yolu                   |");
        System.out.println("| --module (-m)      | Ana modul ve main class            |");
        System.out.println("| --add-modules      | Ek moduller ekle                   |");
        System.out.println("| --add-opens        | Yansima icin paket ac              |");
        System.out.println("| --add-exports      | Paketi export et                   |");
        System.out.println("| --add-reads        | Modul okuma izni ver               |");
        System.out.println("| --patch-module     | Modulu yamala (test icin)          |");
        System.out.println("+--------------------+------------------------------------+");
    }
}
