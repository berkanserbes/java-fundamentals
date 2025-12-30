package com.fundamentals.annotations;

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

/**
 * =====================================================
 * ANNOTATION PROCESSING (REFLECTION İLE)
 * =====================================================
 * 
 * Annotation'lar çalışma zamanında Reflection API kullanılarak
 * okunur ve işlenir. Bu teknik framework'lerin temelini oluşturur.
 * 
 * TEMEL REFLECTION METODLARI:
 * - isAnnotationPresent() : Annotation var mı kontrol
 * - getAnnotation() : Tek annotation al
 * - getAnnotations() : Tüm annotation'ları al
 * - getAnnotationsByType() : Repeatable dahil al
 * 
 * @author Java Fundamentals
 */
public class AnnotationProcessing {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║          ANNOTATION PROCESSING (REFLECTION İLE)              ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        processClassAnnotations();
        processMethodAnnotations();
        processFieldAnnotations();
        processParameterAnnotations();
        buildSimpleValidator();

        System.out.println("✓ Tüm annotation processing örnekleri tamamlandı!");
    }

    // ==================== 1. SINIF ANNOTATION'LARI ====================
    public static void processClassAnnotations() {
        System.out.println("1️⃣ SINIF ANNOTATION'LARINI İŞLEME");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        Class<?> clazz = UserService.class;

        // Tüm annotation'ları al
        System.out.println("  Tüm Annotation'lar:");
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation ann : annotations) {
            System.out.println("    - " + ann.annotationType().getSimpleName());
        }

        // Belirli annotation'ı kontrol et ve oku
        if (clazz.isAnnotationPresent(Service.class)) {
            Service service = clazz.getAnnotation(Service.class);
            System.out.println("\n  @Service Detayları:");
            System.out.println("    Name    : " + service.name());
            System.out.println("    Version : " + service.version());
            System.out.println("    Lazy    : " + service.lazy());
        }
        System.out.println();
    }

    // ==================== 2. METOD ANNOTATION'LARI ====================
    public static void processMethodAnnotations() {
        System.out.println("2️⃣ METOD ANNOTATION'LARINI İŞLEME");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        Class<?> clazz = UserService.class;

        System.out.println("  Metodlar ve Annotation'ları:");
        for (Method method : clazz.getDeclaredMethods()) {
            System.out.println("\n  📌 " + method.getName() + "()");

            // @Transactional kontrolü
            if (method.isAnnotationPresent(Transactional.class)) {
                Transactional tx = method.getAnnotation(Transactional.class);
                System.out.println("     @Transactional(readOnly=" + tx.readOnly() +
                        ", timeout=" + tx.timeout() + ")");
            }

            // @Cacheable kontrolü
            if (method.isAnnotationPresent(Cacheable.class)) {
                Cacheable cache = method.getAnnotation(Cacheable.class);
                System.out.println("     @Cacheable(key=\"" + cache.key() +
                        "\", ttl=" + cache.ttl() + ")");
            }

            // @Loggable kontrolü
            if (method.isAnnotationPresent(Loggable.class)) {
                System.out.println("     @Loggable");
            }
        }
        System.out.println();
    }

    // ==================== 3. FIELD ANNOTATION'LARI ====================
    public static void processFieldAnnotations() {
        System.out.println("3️⃣ FIELD ANNOTATION'LARINI İŞLEME");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        Class<?> clazz = UserEntity.class;

        System.out.println("  UserEntity Alanları:");
        for (Field field : clazz.getDeclaredFields()) {
            StringBuilder info = new StringBuilder();
            info.append("    ").append(field.getName()).append(" (").append(field.getType().getSimpleName())
                    .append(")");

            List<String> annotations = new ArrayList<>();

            if (field.isAnnotationPresent(Column.class)) {
                Column col = field.getAnnotation(Column.class);
                annotations.add("@Column(\"" + col.name() + "\", nullable=" + col.nullable() + ")");
            }
            if (field.isAnnotationPresent(Id.class)) {
                annotations.add("@Id");
            }
            if (field.isAnnotationPresent(NotNull.class)) {
                NotNull nn = field.getAnnotation(NotNull.class);
                annotations.add("@NotNull(\"" + nn.message() + "\")");
            }
            if (field.isAnnotationPresent(Size.class)) {
                Size size = field.getAnnotation(Size.class);
                annotations.add("@Size(" + size.min() + "-" + size.max() + ")");
            }
            if (field.isAnnotationPresent(Email.class)) {
                annotations.add("@Email");
            }

            if (!annotations.isEmpty()) {
                info.append("\n      ").append(String.join(", ", annotations));
            }
            System.out.println(info);
        }
        System.out.println();
    }

    // ==================== 4. PARAMETRE ANNOTATION'LARI ====================
    public static void processParameterAnnotations() {
        System.out.println("4️⃣ PARAMETRE ANNOTATION'LARINI İŞLEME");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        try {
            Method method = ApiController.class.getMethod("getUser", Long.class, String.class);
            Parameter[] params = method.getParameters();

            System.out.println("  getUser() Parametreleri:");
            for (Parameter param : params) {
                System.out.print("    " + param.getName() + " (" + param.getType().getSimpleName() + ")");

                if (param.isAnnotationPresent(PathVariable.class)) {
                    PathVariable pv = param.getAnnotation(PathVariable.class);
                    System.out.print(" -> @PathVariable(\"" + pv.value() + "\")");
                }
                if (param.isAnnotationPresent(RequestHeader.class)) {
                    RequestHeader rh = param.getAnnotation(RequestHeader.class);
                    System.out.print(" -> @RequestHeader(\"" + rh.value() + "\")");
                }
                System.out.println();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    // ==================== 5. BASİT VALİDATOR ====================
    public static void buildSimpleValidator() {
        System.out.println("5️⃣ BASİT ANNOTATION-BASED VALIDATOR");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        // Geçerli kullanıcı
        User validUser = new User("ahmet@example.com", "Ahmet", 25);
        System.out.println("  Geçerli kullanıcı doğrulama:");
        validateObject(validUser);

        // Geçersiz kullanıcı
        User invalidUser = new User("invalid-email", "", -5);
        System.out.println("\n  Geçersiz kullanıcı doğrulama:");
        validateObject(invalidUser);

        System.out.println();
    }

    private static void validateObject(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        List<String> errors = new ArrayList<>();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);

                // @NotNull kontrolü
                if (field.isAnnotationPresent(NotNull.class)) {
                    NotNull nn = field.getAnnotation(NotNull.class);
                    if (value == null || (value instanceof String && ((String) value).isEmpty())) {
                        errors.add(field.getName() + ": " + nn.message());
                    }
                }

                // @Email kontrolü
                if (field.isAnnotationPresent(Email.class) && value instanceof String) {
                    String email = (String) value;
                    if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                        errors.add(field.getName() + ": Geçersiz email formatı");
                    }
                }

                // @Min kontrolü
                if (field.isAnnotationPresent(Min.class) && value instanceof Number) {
                    Min min = field.getAnnotation(Min.class);
                    if (((Number) value).longValue() < min.value()) {
                        errors.add(field.getName() + ": " + min.message());
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        if (errors.isEmpty()) {
            System.out.println("    ✅ Doğrulama başarılı!");
        } else {
            System.out.println("    ❌ Doğrulama hataları:");
            for (String error : errors) {
                System.out.println("       - " + error);
            }
        }
    }
}

// ==================== ANNOTATION TANIMLARI ====================

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Service {
    String name() default "";

    String version() default "1.0";

    boolean lazy() default false;
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Transactional {
    boolean readOnly() default false;

    int timeout() default 30;
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Cacheable {
    String key();

    int ttl() default 3600;
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Loggable {
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Column {
    String name();

    boolean nullable() default true;
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Id {
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotNull {
    String message() default "Bu alan boş olamaz";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Size {
    int min() default 0;

    int max() default Integer.MAX_VALUE;
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Email {
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Min {
    long value();

    String message() default "Minimum değer aşıldı";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@interface PathVariable {
    String value() default "";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@interface RequestHeader {
    String value();
}

// ==================== ÖRNEK SINIFLAR ====================

@Service(name = "userService", version = "2.0", lazy = true)
class UserService {
    @Transactional(readOnly = true)
    @Cacheable(key = "users", ttl = 1800)
    @Loggable
    public void findAll() {
    }

    @Transactional
    @Loggable
    public void save() {
    }

    @Cacheable(key = "user:id")
    public void findById() {
    }
}

class UserEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email")
    @NotNull
    @Email
    private String email;

    @Column(name = "username")
    @NotNull(message = "Kullanıcı adı zorunlu")
    @Size(min = 3, max = 50)
    private String username;
}

class ApiController {
    public String getUser(@PathVariable("id") Long id, @RequestHeader("Authorization") String auth) {
        return "User: " + id;
    }
}

class User {
    @NotNull(message = "Email zorunlu")
    @Email
    private String email;

    @NotNull(message = "İsim zorunlu")
    private String name;

    @Min(value = 0, message = "Yaş negatif olamaz")
    private int age;

    public User(String email, String name, int age) {
        this.email = email;
        this.name = name;
        this.age = age;
    }
}
