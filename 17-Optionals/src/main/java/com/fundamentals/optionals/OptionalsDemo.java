package com.fundamentals.optionals;

/**
 * Java Optional - Ana Demo Sinifi
 * 
 * Java 8 ile gelen Optional sinifi, null degerleri guvenli bir sekilde
 * yonetmemizi saglar ve NullPointerException riskini azaltir.
 * 
 * @author Java Fundamentals
 * @version 1.0
 */
public class OptionalsDemo {
    
    public static void main(String[] args) {
        System.out.println("======================================================================");
        System.out.println("                    JAVA OPTIONAL CLASS                              ");
        System.out.println("              Null Safety & Functional Programming                   ");
        System.out.println("======================================================================");
        System.out.println();
        
        // 1. Optional Olusturma
        System.out.println("+--------------------------------------------------------------------+");
        System.out.println("| 1. OPTIONAL OLUSTURMA                                              |");
        System.out.println("+--------------------------------------------------------------------+");
        OptionalCreation.demonstrate();
        
        // 2. Optional Metodlari
        System.out.println("\n+--------------------------------------------------------------------+");
        System.out.println("| 2. OPTIONAL METODLARI                                              |");
        System.out.println("+--------------------------------------------------------------------+");
        OptionalMethods.demonstrate();
        
        // 3. Donusum Metodlari
        System.out.println("\n+--------------------------------------------------------------------+");
        System.out.println("| 3. DONUSUM METODLARI (map, flatMap, filter)                        |");
        System.out.println("+--------------------------------------------------------------------+");
        OptionalTransformation.demonstrate();
        
        // 4. Best Practices
        System.out.println("\n+--------------------------------------------------------------------+");
        System.out.println("| 4. BEST PRACTICES                                                  |");
        System.out.println("+--------------------------------------------------------------------+");
        OptionalBestPractices.demonstrate();
        
        // 5. Gercek Dunya Ornekleri
        System.out.println("\n+--------------------------------------------------------------------+");
        System.out.println("| 5. GERCEK DUNYA ORNEKLERI                                          |");
        System.out.println("+--------------------------------------------------------------------+");
        OptionalRealWorld.demonstrate();
        
        System.out.println("\n======================================================================");
        System.out.println("                    DEMO TAMAMLANDI                                  ");
        System.out.println("======================================================================");
    }
}
