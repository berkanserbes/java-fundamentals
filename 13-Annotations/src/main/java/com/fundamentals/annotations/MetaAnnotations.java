package com.fundamentals.annotations;

import java.lang.annotation.*;

/**
 * =====================================================
 * META-ANNOTATIONS (ANNOTATION'LARI YAPILANDIRAN ANNOTATIONS)
 * =====================================================
 * 
 * Meta-annotation'lar, diğer annotation'ların davranışını
 * kontrol etmek için kullanılır.
 * 
 * ANA META-ANNOTATIONS:
 * 
 * 1. @Target - Annotation nereye uygulanabilir?
 * 2. @Retention - Annotation ne kadar süre saklanacak?
 * 3. @Documented - Javadoc'a dahil edilsin mi?
 * 4. @Inherited - Alt sınıflara miras geçsin mi?
 * 5. @Repeatable - Aynı elemana birden fazla kez uygulanabilir mi?
 * 
 * @author Java Fundamentals
 */
public class MetaAnnotations {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                    META-ANNOTATIONS                          ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        demonstrateTarget();
        demonstrateRetention();
        demonstrateDocumented();
        demonstrateInherited();
        demonstrateRepeatable();

        System.out.println("✓ Tüm meta-annotation örnekleri tamamlandı!");
    }

    // ==================== 1. @TARGET ====================
    public static void demonstrateTarget() {
        System.out.println("1️⃣ @TARGET - Annotation Uygulama Hedefleri");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("""
                  ElementType Değerleri:
                  ┌────────────────────────┬──────────────────────────────────┐
                  │ TYPE                   │ Sınıf, interface, enum, record  │
                  │ FIELD                  │ Alan (instance variable)         │
                  │ METHOD                 │ Metod                            │
                  │ PARAMETER              │ Metod parametresi                │
                  │ CONSTRUCTOR            │ Constructor                      │
                  │ LOCAL_VARIABLE         │ Yerel değişken                   │
                  │ ANNOTATION_TYPE        │ Başka bir annotation             │
                  │ PACKAGE                │ Paket (package-info.java)        │
                  │ TYPE_PARAMETER         │ Generic tip parametresi          │
                  │ TYPE_USE               │ Her türlü tip kullanımı          │
                  │ MODULE                 │ Modül (Java 9+)                  │
                  │ RECORD_COMPONENT       │ Record bileşeni (Java 16+)       │
                  └────────────────────────┴──────────────────────────────────┘
                """);

        System.out.println("  📌 Örnek: @Target(ElementType.METHOD) -> Sadece metodlara");
        System.out.println("  📌 Birden fazla: @Target({ElementType.TYPE, ElementType.METHOD})\n");
    }

    // ==================== 2. @RETENTION ====================
    public static void demonstrateRetention() {
        System.out.println("2️⃣ @RETENTION - Annotation Saklama Politikası");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("""
                  RetentionPolicy Değerleri:
                  ┌──────────────┬────────────────────────────────────────────────┐
                  │ SOURCE       │ Derleme sırasında atılır                       │
                  │              │ Örnek: @Override, @SuppressWarnings            │
                  ├──────────────┼────────────────────────────────────────────────┤
                  │ CLASS        │ .class dosyasına yazılır ama JVM yüklemez      │
                  │              │ (Varsayılan değer)                             │
                  ├──────────────┼────────────────────────────────────────────────┤
                  │ RUNTIME      │ Çalışma zamanında Reflection ile erişilebilir  │
                  │              │ En yaygın kullanılan - Framework'ler için      │
                  └──────────────┴────────────────────────────────────────────────┘
                """);

        // Retention kontrolleri
        System.out.println("  Annotation Retention Durumları:");
        checkRetention(SourceOnlyAnnotation.class, "SourceOnlyAnnotation");
        checkRetention(ClassLevelAnnotation.class, "ClassLevelAnnotation");
        checkRetention(RuntimeAnnotation.class, "RuntimeAnnotation");
        System.out.println();
    }

    private static void checkRetention(Class<? extends Annotation> annotationClass, String name) {
        Retention retention = annotationClass.getAnnotation(Retention.class);
        if (retention != null) {
            System.out.println("    " + name + " -> " + retention.value());
        }
    }

    // ==================== 3. @DOCUMENTED ====================
    public static void demonstrateDocumented() {
        System.out.println("3️⃣ @DOCUMENTED - Javadoc'a Dahil Etme");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("""
                  @Documented ile işaretlenen annotation'lar,
                  uygulandıkları elemanların Javadoc'unda görünür.

                  Örnek:
                  @Documented
                  @interface ApiVersion {
                      String value();
                  }

                  @ApiVersion("2.0")
                  public class MyApi { }

                  -> Javadoc'ta MyApi sınıfında @ApiVersion("2.0") görünür
                """);

        // Documented kontrolü
        boolean isDocumented = DocumentedAnnotation.class.isAnnotationPresent(Documented.class);
        System.out.println("  DocumentedAnnotation Javadoc'a dahil mi? " + isDocumented);
        System.out.println();
    }

    // ==================== 4. @INHERITED ====================
    public static void demonstrateInherited() {
        System.out.println("4️⃣ @INHERITED - Miras Alınabilir Annotation");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("""
                  @Inherited ile işaretlenen annotation'lar,
                  alt sınıflara otomatik olarak miras geçer.

                  NOT: Sadece sınıflara uygulandığında çalışır!
                       Interface veya metod için geçerli değildir.
                """);

        // Inherited annotation kontrolü
        System.out.println("  Miras Testi:");
        System.out.println("    ParentWithInherited has @InheritableAnnotation? " +
                ParentWithInherited.class.isAnnotationPresent(InheritableAnnotation.class));
        System.out.println("    ChildOfInherited has @InheritableAnnotation? " +
                ChildOfInherited.class.isAnnotationPresent(InheritableAnnotation.class));

        System.out.println("\n  Normal Annotation (Inherited değil):");
        System.out.println("    ParentWithNormal has @NormalAnnotation? " +
                ParentWithNormal.class.isAnnotationPresent(NormalAnnotation.class));
        System.out.println("    ChildOfNormal has @NormalAnnotation? " +
                ChildOfNormal.class.isAnnotationPresent(NormalAnnotation.class));
        System.out.println();
    }

    // ==================== 5. @REPEATABLE ====================
    public static void demonstrateRepeatable() {
        System.out.println("5️⃣ @REPEATABLE - Tekrarlanabilir Annotation (Java 8+)");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("""
                  @Repeatable ile aynı annotation birden fazla kez uygulanabilir.

                  Gereksinimler:
                  1. @Repeatable(ContainerAnnotation.class) belirtilmeli
                  2. Container annotation "value()" metodu ile dizi döndürmeli
                """);

        // Repeatable annotation'ları oku
        System.out.println("  MultiRoleUser sınıfının rolleri:");
        Role[] roles = MultiRoleUser.class.getAnnotationsByType(Role.class);
        for (Role role : roles) {
            System.out.println("    - " + role.value());
        }

        System.out.println("\n  ScheduledTask metodunun zamanlamaları:");
        try {
            java.lang.reflect.Method method = TaskService.class.getMethod("scheduledTask");
            Schedule[] schedules = method.getAnnotationsByType(Schedule.class);
            for (Schedule schedule : schedules) {
                System.out.println("    - " + schedule.cron() + " (" + schedule.timezone() + ")");
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}

// ==================== META-ANNOTATION ÖRNEKLERİ ====================

// Retention örnekleri
@Retention(RetentionPolicy.SOURCE)
@interface SourceOnlyAnnotation {
    String value() default "";
}

@Retention(RetentionPolicy.CLASS)
@interface ClassLevelAnnotation {
    String value() default "";
}

@Retention(RetentionPolicy.RUNTIME)
@interface RuntimeAnnotation {
    String value() default "";
}

// Documented örneği
@Documented
@Retention(RetentionPolicy.RUNTIME)
@interface DocumentedAnnotation {
    String value() default "";
}

// Inherited örnekleri
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface InheritableAnnotation {
    String value() default "";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface NormalAnnotation {
    String value() default "";
}

@InheritableAnnotation("Parent")
class ParentWithInherited {
}

class ChildOfInherited extends ParentWithInherited {
}

@NormalAnnotation("Parent")
class ParentWithNormal {
}

class ChildOfNormal extends ParentWithNormal {
}

// Repeatable örnekleri
@Repeatable(Roles.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Role {
    String value();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Roles {
    Role[] value();
}

@Role("ADMIN")
@Role("USER")
@Role("MODERATOR")
class MultiRoleUser {
}

@Repeatable(Schedules.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Schedule {
    String cron();

    String timezone() default "UTC";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Schedules {
    Schedule[] value();
}

class TaskService {
    @Schedule(cron = "0 0 8 * * ?", timezone = "Europe/Istanbul")
    @Schedule(cron = "0 0 20 * * ?", timezone = "Europe/Istanbul")
    @Schedule(cron = "0 0 12 * * SAT")
    public void scheduledTask() {
    }
}
