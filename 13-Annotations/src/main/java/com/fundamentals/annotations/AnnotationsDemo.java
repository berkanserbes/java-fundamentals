package com.fundamentals.annotations;

/**
 * =====================================================
 * JAVA ANNOTATIONS - ANA GİRİŞ NOKTASI
 * =====================================================
 * 
 * ANNOTATIONS NEDİR?
 * 
 * Annotation'lar, Java'da kod hakkında meta-veri (metadata) sağlayan
 * özel işaretlerdir. @ sembolü ile başlarlar ve koda ek bilgi eklerler.
 * 
 * Annotation'lar doğrudan program mantığını değiştirmezler, ancak:
 * - Derleyiciye talimat verebilirler (@Override, @SuppressWarnings)
 * - Derleme zamanında kod üretebilirler (Lombok)
 * - Çalışma zamanında okunabilirler (Spring, JUnit)
 * 
 * =====================================================
 * BU MODÜLDE ÖĞRENECEKLER:
 * =====================================================
 * 
 * 1. YERLEŞIK ANNOTATIONS (BuiltInAnnotations.java)
 * - @Override : Metod override kontrolü
 * - @Deprecated : Kullanımdan kaldırılan element
 * - @SuppressWarnings : Uyarı bastırma
 * - @SafeVarargs : Güvenli varargs
 * - @FunctionalInterface : Lambda uyumlu interface
 * 
 * 2. ÖZEL ANNOTATION OLUŞTURMA (CustomAnnotations.java)
 * - Basit annotation tanımlama
 * - Çoklu element kullanımı
 * - Varsayılan değerler (default)
 * - Dizi ve enum elementler
 * - Marker annotations
 * 
 * 3. META-ANNOTATIONS (MetaAnnotations.java)
 * - @Target : Uygulama hedefi
 * - @Retention : Saklama politikası
 * - @Documented : Javadoc desteği
 * - @Inherited : Miras desteği
 * - @Repeatable : Tekrar kullanım
 * 
 * 4. ANNOTATION PROCESSING (AnnotationProcessing.java)
 * - Reflection ile okuma
 * - Sınıf, metod, field annotation'ları
 * - Parametre annotation'ları
 * - Basit validator örneği
 * 
 * 5. GERÇEK DÜNYA ÖRNEKLERİ (RealWorldExamples.java)
 * - Dependency Injection simülasyonu
 * - REST Controller mapping
 * - ORM Entity mapping
 * - Test framework annotation'ları
 * - Security annotation'ları
 * 
 * @author Java Fundamentals
 * @version 2.0
 */
public class AnnotationsDemo {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                      ║");
        System.out.println("║                    ☕ JAVA ANNOTATIONS                               ║");
        System.out.println("║                    Modül 13 - Kapsamlı Eğitim                        ║");
        System.out.println("║                                                                      ║");
        System.out.println("╚══════════════════════════════════════════════════════════════════════╝");
        System.out.println();

        printWelcomeMessage();

        // Çalıştırılabilir demo sınıfları
        System.out.println("\n📋 Bu modülde aşağıdaki sınıfları ayrı ayrı çalıştırabilirsiniz:\n");
        System.out.println("   1. BuiltInAnnotations     - Java yerleşik annotation'ları");
        System.out.println("   2. CustomAnnotations      - Özel annotation oluşturma");
        System.out.println("   3. MetaAnnotations        - Meta-annotation'lar");
        System.out.println("   4. AnnotationProcessing   - Reflection ile işleme");
        System.out.println("   5. RealWorldExamples      - Gerçek dünya örnekleri");

        System.out.println("\n" + "═".repeat(70));
        System.out.println("               HIZLI ANNOTATION REFERANSI");
        System.out.println("═".repeat(70) + "\n");

        printQuickReference();

        System.out.println("\n" + "═".repeat(70));
        System.out.println("               BASİT ÖRNEK DEMO");
        System.out.println("═".repeat(70) + "\n");

        runSimpleDemo();

        System.out.println("\n✨ Detaylı örnekler için yukarıdaki sınıfları çalıştırın!");
        System.out.println("   Örnek: .\\run-module.bat 13-Annotations BuiltInAnnotations\n");
    }

    private static void printWelcomeMessage() {
        System.out.println("""
                ╭────────────────────────────────────────────────────────────────────╮
                │                         ANNOTATIONS NEDİR?                         │
                ├────────────────────────────────────────────────────────────────────┤
                │                                                                    │
                │  Annotation'lar, Java koduna eklenen meta-verilerdir.              │
                │  @ sembolü ile başlarlar ve şu amaçlarla kullanılırlar:            │
                │                                                                    │
                │  ✦ Derleyiciye bilgi vermek     (@Override, @SuppressWarnings)     │
                │  ✦ Derleme zamanı işleme        (Lombok, MapStruct)                │
                │  ✦ Çalışma zamanı işleme        (Spring, JUnit, Jackson)           │
                │  ✦ Kod dokümantasyonu           (@Deprecated, @author)             │
                │                                                                    │
                │  Framework'lerin %90'ı annotation'lar üzerine kuruludur!           │
                │                                                                    │
                ╰────────────────────────────────────────────────────────────────────╯
                """);
    }

    private static void printQuickReference() {
        System.out.println("""
                ┌────────────────────────────────────────────────────────────────────┐
                │                    YERLEŞIK ANNOTATIONS                            │
                ├─────────────────────┬──────────────────────────────────────────────┤
                │ @Override           │ Metod override kontrolü sağlar               │
                │ @Deprecated         │ Kullanımdan kaldırıldı işareti               │
                │ @SuppressWarnings   │ Derleyici uyarılarını bastırır               │
                │ @SafeVarargs        │ Varargs güvenliği belirtir                   │
                │ @FunctionalInterface│ Lambda uyumlu interface işareti              │
                └─────────────────────┴──────────────────────────────────────────────┘

                ┌────────────────────────────────────────────────────────────────────┐
                │                    META-ANNOTATIONS                                │
                ├─────────────────────┬──────────────────────────────────────────────┤
                │ @Target             │ Nereye uygulanabilir (TYPE, METHOD, FIELD)  │
                │ @Retention          │ Ne kadar saklanır (SOURCE, CLASS, RUNTIME)  │
                │ @Documented         │ Javadoc'a dahil edilir                       │
                │ @Inherited          │ Alt sınıflara miras geçer                    │
                │ @Repeatable         │ Birden fazla kez uygulanabilir               │
                └─────────────────────┴──────────────────────────────────────────────┘

                ┌────────────────────────────────────────────────────────────────────┐
                │                    ÖZEL ANNOTATION TANIMLAMA                       │
                ├────────────────────────────────────────────────────────────────────┤
                │ @Retention(RetentionPolicy.RUNTIME)                                │
                │ @Target(ElementType.METHOD)                                        │
                │ public @interface MyAnnotation {                                   │
                │     String value();                   // Zorunlu element           │
                │     int count() default 1;            // Varsayılan değerli        │
                │     String[] tags() default {};       // Dizi element              │
                │ }                                                                  │
                └────────────────────────────────────────────────────────────────────┘
                """);
    }

    private static void runSimpleDemo() {
        System.out.println("📌 Basit @Override Örneği:\n");

        Shape shape = new Circle();
        System.out.println("   shape.draw() çağrıldığında:");
        System.out.print("   ");
        shape.draw(); // Override edilmiş metod çalışır

        System.out.println("\n📌 @Deprecated Örneği:\n");
        OldClass old = new OldClass();
        System.out.println("   old.newMethod() = " + old.newMethod());
        System.out.println("   (old.oldMethod() deprecated olarak işaretli)");
    }
}

// Basit demo için yardımcı sınıflar
abstract class Shape {
    public abstract void draw();
}

class Circle extends Shape {
    @Override // Bu annotation, draw() metodunun override edildiğini belirtir
    public void draw() {
        System.out.println("⭕ Daire çiziliyor...");
    }
}

class OldClass {
    @Deprecated(since = "2.0", forRemoval = true)
    public String oldMethod() {
        return "Eski metod";
    }

    public String newMethod() {
        return "Yeni metod - Bunu kullanın!";
    }
}
