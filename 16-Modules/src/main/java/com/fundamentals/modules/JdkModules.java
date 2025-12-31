package com.fundamentals.modules;

/**
 * JDK Modulleri Analizi
 */
public class JdkModules {
    
    public static void demonstrate() {
        System.out.println();
        
        System.out.println("[*] JDK MODUL YAPISI:");
        System.out.println("------------------------------------------------------------");
        System.out.println("Java 9 ile birlikte JDK, 90+ module ayrildi.");
        System.out.println();
        
        System.out.println("[>] TEMEL JDK MODULLERI:");
        System.out.println("+-------------------------+------------------------------------+");
        System.out.println("| Modul                   | Aciklama                           |");
        System.out.println("+-------------------------+------------------------------------+");
        System.out.println("| java.base               | Temel siniflar (Object, String)    |");
        System.out.println("| java.sql                | JDBC API                           |");
        System.out.println("| java.logging            | Logging API                        |");
        System.out.println("| java.xml                | XML isleme                         |");
        System.out.println("| java.desktop            | AWT ve Swing                       |");
        System.out.println("| java.net.http           | HTTP Client API (Java 11+)         |");
        System.out.println("| java.compiler           | Compiler API                       |");
        System.out.println("| java.instrument         | Java Agent API                     |");
        System.out.println("| java.management         | JMX API                            |");
        System.out.println("| java.naming             | JNDI API                           |");
        System.out.println("| java.rmi                | Remote Method Invocation           |");
        System.out.println("| java.scripting          | Scripting API                      |");
        System.out.println("| java.security.jgss      | GSS-API                            |");
        System.out.println("| java.security.sasl      | SASL                               |");
        System.out.println("| java.smartcardio        | Smart Card I/O                     |");
        System.out.println("| java.transaction.xa     | JTA API                            |");
        System.out.println("+-------------------------+------------------------------------+");
        System.out.println();
        
        // java.base modulu
        System.out.println("[*] java.base MODULU (Temel Modul):");
        System.out.println("------------------------------------------------------------");
        System.out.println("* Her modul tarafindan otomatik olarak require edilir");
        System.out.println("* 'requires java.base' yazmaniza gerek yok");
        System.out.println("* Icerdigi paketler:");
        System.out.println("  - java.lang (Object, String, System, Math, ...)");
        System.out.println("  - java.util (Collections, Date, Optional, ...)");
        System.out.println("  - java.io (InputStream, OutputStream, File, ...)");
        System.out.println("  - java.nio (ByteBuffer, Path, Files, ...)");
        System.out.println("  - java.net (URL, Socket, HttpURLConnection, ...)");
        System.out.println("  - java.time (LocalDate, ZonedDateTime, ...)");
        System.out.println("  - java.util.concurrent (ExecutorService, ...)");
        System.out.println();
        
        // Modul bilgilerini goruntuleme
        System.out.println("[?] MODUL BILGILERINI GORUNTULEME:");
        System.out.println("------------------------------------------------------------");
        System.out.println();
        
        // Mevcut modulu goster
        Module currentModule = JdkModules.class.getModule();
        System.out.println("Bu sinifin modulu: " + currentModule.getName());
        System.out.println("  Unnamed mi?: " + (currentModule.getName() == null ? "Evet" : "Hayir"));
        System.out.println();
        
        // String sinifinin modulunu goster
        Module stringModule = String.class.getModule();
        System.out.println("String sinifinin modulu: " + stringModule.getName());
        System.out.println();
        
        // Bazi JDK siniflarinin modullerini goster
        System.out.println("[>] Bazi Siniflarin Modulleri:");
        printModuleInfo("java.lang.Object", Object.class);
        printModuleInfo("java.util.List", java.util.List.class);
        printModuleInfo("java.io.File", java.io.File.class);
        printModuleInfo("java.time.LocalDate", java.time.LocalDate.class);
        System.out.println();
        
        // Komutlar
        System.out.println("[>] MODUL BILGISI KOMUTLARI:");
        System.out.println("------------------------------------------------------------");
        System.out.println("* java --list-modules              -> Tum JDK modullerini listele");
        System.out.println("* java --describe-module java.sql  -> Modul detaylarini goster");
        System.out.println("* jar --describe-module -f app.jar -> JAR modul bilgisi");
    }
    
    private static void printModuleInfo(String className, Class<?> clazz) {
        System.out.println("  * " + className + " -> " + clazz.getModule().getName());
    }
}
