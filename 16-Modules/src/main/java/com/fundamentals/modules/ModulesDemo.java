package com.fundamentals.modules;

/**
 * Java Platform Module System (JPMS) - Ana Demo Sinifi
 * 
 * Java 9 ile birlikte gelen modul sistemi, Java uygulamalarini daha moduler,
 * guvenli ve bakimi kolay hale getirir.
 * 
 * @author Java Fundamentals
 * @version 1.0
 */
public class ModulesDemo {
    
    public static void main(String[] args) {
        System.out.println("======================================================================");
        System.out.println("           JAVA PLATFORM MODULE SYSTEM (JPMS)                        ");
        System.out.println("                    Java 9+ Modul Sistemi                            ");
        System.out.println("======================================================================");
        System.out.println();
        
        // 1. Modul Temelleri
        System.out.println("+--------------------------------------------------------------------+");
        System.out.println("| 1. MODUL TEMELLERI                                                 |");
        System.out.println("+--------------------------------------------------------------------+");
        ModuleBasics.demonstrate();
        
        // 2. Modul Anahtar Kelimeleri
        System.out.println("\n+--------------------------------------------------------------------+");
        System.out.println("| 2. MODUL ANAHTAR KELIMELERI                                        |");
        System.out.println("+--------------------------------------------------------------------+");
        ModuleKeywords.demonstrate();
        
        // 3. Modul Turleri
        System.out.println("\n+--------------------------------------------------------------------+");
        System.out.println("| 3. MODUL TURLERI                                                   |");
        System.out.println("+--------------------------------------------------------------------+");
        ModuleTypes.demonstrate();
        
        // 4. JDK Modulleri
        System.out.println("\n+--------------------------------------------------------------------+");
        System.out.println("| 4. JDK MODULLERI                                                   |");
        System.out.println("+--------------------------------------------------------------------+");
        JdkModules.demonstrate();
        
        // 5. ServiceLoader Demo
        System.out.println("\n+--------------------------------------------------------------------+");
        System.out.println("| 5. SERVICE LOADER PATTERN                                          |");
        System.out.println("+--------------------------------------------------------------------+");
        ServiceLoaderDemo.demonstrate();
        
        // 6. Komut Satiri Araclari
        System.out.println("\n+--------------------------------------------------------------------+");
        System.out.println("| 6. MODUL KOMUT SATIRI ARACLARI                                     |");
        System.out.println("+--------------------------------------------------------------------+");
        ModuleCommands.demonstrate();
        
        System.out.println("\n======================================================================");
        System.out.println("                    DEMO TAMAMLANDI                                  ");
        System.out.println("======================================================================");
    }
}
