package com.fundamentals.annotations;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * =====================================================
 * ÖZEL (CUSTOM) ANNOTATION OLUŞTURMA
 * =====================================================
 * 
 * Java'da kendi annotation'larınızı oluşturabilirsiniz.
 * 
 * ANNOTATION TANIMLAMA SÖZDİZİMİ:
 * 
 * @interface AnnotationName {
 *            String value(); // Zorunlu eleman
 *            int count() default 1; // Varsayılan değerli
 *            }
 * 
 * @author Java Fundamentals
 */
public class CustomAnnotations {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║              ÖZEL (CUSTOM) ANNOTATION OLUŞTURMA              ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        demonstrateSimpleAnnotation();
        demonstrateMultiElementAnnotation();
        demonstrateDefaultValues();
        demonstrateArrayElements();
        demonstrateEnumElements();
        demonstrateMarkerAnnotation();

        System.out.println("✓ Tüm özel annotation örnekleri tamamlandı!");
    }

    // ==================== 1. BASİT ANNOTATION ====================
    public static void demonstrateSimpleAnnotation() {
        System.out.println("1️⃣ BASİT ANNOTATION");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        try {
            Class<?> clazz = SimpleService.class;
            if (clazz.isAnnotationPresent(Author.class)) {
                Author author = clazz.getAnnotation(Author.class);
                System.out.println("  Sınıf: " + clazz.getSimpleName());
                System.out.println("  Yazar: " + author.value());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    // ==================== 2. ÇOKLU ELEMENTLİ ANNOTATION ====================
    public static void demonstrateMultiElementAnnotation() {
        System.out.println("2️⃣ ÇOKLU ELEMENTLİ ANNOTATION");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        try {
            Class<?> clazz = DocumentedClass.class;
            if (clazz.isAnnotationPresent(Documentation.class)) {
                Documentation doc = clazz.getAnnotation(Documentation.class);
                System.out.println("  📄 Dokümantasyon:");
                System.out.println("     Başlık  : " + doc.title());
                System.out.println("     Yazar   : " + doc.author());
                System.out.println("     Versiyon: " + doc.version());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    // ==================== 3. VARSAYILAN DEĞERLİ ====================
    public static void demonstrateDefaultValues() {
        System.out.println("3️⃣ VARSAYILAN DEĞERLİ ANNOTATION");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        try {
            Class<?> clazz1 = DefaultValuesExample.class;
            if (clazz1.isAnnotationPresent(Configuration.class)) {
                Configuration config = clazz1.getAnnotation(Configuration.class);
                System.out.println("  ⚙️ Varsayılan Yapılandırma:");
                System.out.println("     İsim   : " + config.name());
                System.out.println("     Aktif  : " + config.enabled());
                System.out.println("     Timeout: " + config.timeout() + "ms");
            }

            Class<?> clazz2 = CustomValuesExample.class;
            if (clazz2.isAnnotationPresent(Configuration.class)) {
                Configuration config = clazz2.getAnnotation(Configuration.class);
                System.out.println("  ⚙️ Özel Yapılandırma:");
                System.out.println("     İsim   : " + config.name());
                System.out.println("     Aktif  : " + config.enabled());
                System.out.println("     Timeout: " + config.timeout() + "ms");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    // ==================== 4. DİZİ ELEMENTLİ ====================
    public static void demonstrateArrayElements() {
        System.out.println("4️⃣ DİZİ ELEMENTLİ ANNOTATION");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        try {
            Class<?> clazz = TaggedClass.class;
            if (clazz.isAnnotationPresent(Tags.class)) {
                Tags tags = clazz.getAnnotation(Tags.class);
                System.out.println("  🏷️ Etiketler:");
                for (String tag : tags.value()) {
                    System.out.println("     - " + tag);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    // ==================== 5. ENUM ELEMENTLİ ====================
    public static void demonstrateEnumElements() {
        System.out.println("5️⃣ ENUM ELEMENTLİ ANNOTATION");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        analyzeSecurityLevel(PublicResource.class);
        analyzeSecurityLevel(InternalResource.class);
        analyzeSecurityLevel(SecretResource.class);
        System.out.println();
    }

    private static void analyzeSecurityLevel(Class<?> clazz) {
        if (clazz.isAnnotationPresent(Security.class)) {
            Security security = clazz.getAnnotation(Security.class);
            String icon = switch (security.level()) {
                case PUBLIC -> "🟢";
                case INTERNAL -> "🟡";
                case CONFIDENTIAL -> "🟠";
                case SECRET -> "🔴";
            };
            System.out.println("  " + icon + " " + clazz.getSimpleName() + " -> " + security.level());
        }
    }

    // ==================== 6. MARKER ANNOTATION ====================
    public static void demonstrateMarkerAnnotation() {
        System.out.println("6️⃣ MARKER ANNOTATION (Element içermez)");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        checkImportance(ImportantTask.class);
        checkImportance(RegularTask.class);

        try {
            Field[] fields = EntityWithMarkers.class.getDeclaredFields();
            System.out.println("\n  📋 Entity Alanları:");
            for (Field field : fields) {
                StringBuilder info = new StringBuilder("     " + field.getName());
                if (field.isAnnotationPresent(Required.class))
                    info.append(" [ZORUNLU]");
                if (field.isAnnotationPresent(Unique.class))
                    info.append(" [BENZERSİZ]");
                System.out.println(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    private static void checkImportance(Class<?> clazz) {
        boolean isImportant = clazz.isAnnotationPresent(Important.class);
        String icon = isImportant ? "⭐" : "  ";
        System.out.println("  " + icon + " " + clazz.getSimpleName() + " -> " + (isImportant ? "ÖNEMLİ" : "Normal"));
    }
}

// ==================== ANNOTATION TANIMLARI ====================

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@interface Author {
    String value();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Documentation {
    String title();

    String author();

    String version() default "1.0";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Configuration {
    String name() default "default";

    boolean enabled() default true;

    long timeout() default 5000;
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Tags {
    String[] value();
}

enum SecurityLevel {
    PUBLIC, INTERNAL, CONFIDENTIAL, SECRET
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Security {
    SecurityLevel level() default SecurityLevel.INTERNAL;
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Important {
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Required {
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Unique {
}

// ==================== ÖRNEK SINIFLAR ====================

@Author("Ahmet Yılmaz")
class SimpleService {
}

@Documentation(title = "Müşteri Servisi", author = "Geliştirme Ekibi", version = "2.1")
class DocumentedClass {
}

@Configuration
class DefaultValuesExample {
}

@Configuration(name = "CustomConfig", enabled = false, timeout = 30000)
class CustomValuesExample {
}

@Tags({ "java", "annotations", "tutorial" })
class TaggedClass {
}

@Security(level = SecurityLevel.PUBLIC)
class PublicResource {
}

@Security(level = SecurityLevel.INTERNAL)
class InternalResource {
}

@Security(level = SecurityLevel.SECRET)
class SecretResource {
}

@Important
class ImportantTask {
}

class RegularTask {
}

class EntityWithMarkers {
    @Required
    @Unique
    private String id;
    @Required
    private String name;
    private String description;
}
