package com.fundamentals.annotations;

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

/**
 * =====================================================
 * GERÇEK DÜNYA ANNOTATION ÖRNEKLERİ
 * =====================================================
 * 
 * Bu sınıf, annotation'ların gerçek projelerde nasıl
 * kullanıldığını gösteren pratik örnekler içerir.
 * 
 * ÖRNEKLER:
 * 1. Dependency Injection (@Inject, @Autowired)
 * 2. REST Controller (@GetMapping, @PostMapping)
 * 3. ORM Mapping (@Entity, @Table, @Column)
 * 4. Test Framework (@Test, @BeforeEach)
 * 5. Validation (@Valid, @NotEmpty)
 * 6. Security (@Secured, @RolesAllowed)
 * 
 * @author Java Fundamentals
 */
public class RealWorldExamples {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║           GERÇEK DÜNYA ANNOTATION ÖRNEKLERİ                  ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        demonstrateDependencyInjection();
        demonstrateRestController();
        demonstrateORMMapping();
        demonstrateTestFramework();
        demonstrateSecurityAnnotations();

        System.out.println("✓ Tüm gerçek dünya örnekleri tamamlandı!");
    }

    // ==================== 1. DEPENDENCY INJECTION ====================
    public static void demonstrateDependencyInjection() {
        System.out.println("1️⃣ DEPENDENCY INJECTION (@Inject, @Autowired)");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        // Basit DI Container simülasyonu
        DIContainer container = new DIContainer();
        OrderController controller = container.createInstance(OrderController.class);

        System.out.println("  📦 DI Container sınıf oluşturdu:");
        if (controller != null) {
            System.out.println("     OrderController başarıyla oluşturuldu");
            System.out.println("     Enjekte edilen bağımlılıklar algılandı");
        }

        // @Inject alanlarını göster
        System.out.println("\n  Enjekte edilen alanlar:");
        for (Field field : OrderController.class.getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                Inject inject = field.getAnnotation(Inject.class);
                String qualifier = inject.value().isEmpty() ? "(default)" : inject.value();
                System.out.println("     - " + field.getName() + " : " + field.getType().getSimpleName() +
                        " [qualifier=" + qualifier + "]");
            }
        }
        System.out.println();
    }

    // ==================== 2. REST CONTROLLER ====================
    public static void demonstrateRestController() {
        System.out.println("2️⃣ REST CONTROLLER (@GetMapping, @PostMapping)");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        Class<?> clazz = ProductRestController.class;

        // @RestController bilgisi
        if (clazz.isAnnotationPresent(RestController.class)) {
            RestController rc = clazz.getAnnotation(RestController.class);
            System.out.println("  🌐 REST Controller: " + clazz.getSimpleName());
            System.out.println("     Base Path: " + rc.value());
        }

        // Endpoint'leri listele
        System.out.println("\n  Endpoint'ler:");
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(GetMapping.class)) {
                GetMapping gm = method.getAnnotation(GetMapping.class);
                System.out.println("     GET  " + gm.value() + " -> " + method.getName() + "()");
            }
            if (method.isAnnotationPresent(PostMapping.class)) {
                PostMapping pm = method.getAnnotation(PostMapping.class);
                System.out.println("     POST " + pm.value() + " -> " + method.getName() + "()");
            }
            if (method.isAnnotationPresent(PutMapping.class)) {
                PutMapping pm = method.getAnnotation(PutMapping.class);
                System.out.println("     PUT  " + pm.value() + " -> " + method.getName() + "()");
            }
            if (method.isAnnotationPresent(DeleteMapping.class)) {
                DeleteMapping dm = method.getAnnotation(DeleteMapping.class);
                System.out.println("     DEL  " + dm.value() + " -> " + method.getName() + "()");
            }
        }
        System.out.println();
    }

    // ==================== 3. ORM MAPPING ====================
    public static void demonstrateORMMapping() {
        System.out.println("3️⃣ ORM MAPPING (@Entity, @Table, @Column)");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        Class<?> clazz = ProductEntity.class;

        // Entity ve Table bilgisi
        if (clazz.isAnnotationPresent(Entity.class)) {
            System.out.println("  🗃️ Entity: " + clazz.getSimpleName());
        }
        if (clazz.isAnnotationPresent(Table.class)) {
            Table table = clazz.getAnnotation(Table.class);
            System.out.println("     Table Name: " + table.name());
            System.out.println("     Schema: " + (table.schema().isEmpty() ? "default" : table.schema()));
        }

        // Column mapping
        System.out.println("\n  Column Mapping:");
        for (Field field : clazz.getDeclaredFields()) {
            StringBuilder info = new StringBuilder("     ");
            info.append(field.getName()).append(" -> ");

            if (field.isAnnotationPresent(MColumn.class)) {
                MColumn col = field.getAnnotation(MColumn.class);
                info.append(col.name());
                if (!col.nullable())
                    info.append(" [NOT NULL]");
                if (col.unique())
                    info.append(" [UNIQUE]");
                if (col.length() != 255)
                    info.append(" [" + col.length() + "]");
            }
            if (field.isAnnotationPresent(MId.class)) {
                info.append(" [PRIMARY KEY]");
            }
            if (field.isAnnotationPresent(GeneratedValue.class)) {
                GeneratedValue gv = field.getAnnotation(GeneratedValue.class);
                info.append(" [").append(gv.strategy()).append("]");
            }

            System.out.println(info);
        }
        System.out.println();
    }

    // ==================== 4. TEST FRAMEWORK ====================
    public static void demonstrateTestFramework() {
        System.out.println("4️⃣ TEST FRAMEWORK (@Test, @BeforeEach)");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        Class<?> clazz = CalculatorTest.class;

        System.out.println("  🧪 Test Sınıfı: " + clazz.getSimpleName());

        List<Method> setup = new ArrayList<>();
        List<Method> tests = new ArrayList<>();
        List<Method> teardown = new ArrayList<>();

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(BeforeEach.class))
                setup.add(method);
            if (method.isAnnotationPresent(Test.class))
                tests.add(method);
            if (method.isAnnotationPresent(AfterEach.class))
                teardown.add(method);
        }

        System.out.println("\n  @BeforeEach metodları:");
        for (Method m : setup) {
            System.out.println("     - " + m.getName() + "()");
        }

        System.out.println("\n  @Test metodları:");
        for (Method m : tests) {
            Test t = m.getAnnotation(Test.class);
            String displayName = t.displayName().isEmpty() ? m.getName() : t.displayName();
            System.out.println("     - " + displayName);
        }

        System.out.println("\n  @AfterEach metodları:");
        for (Method m : teardown) {
            System.out.println("     - " + m.getName() + "()");
        }
        System.out.println();
    }

    // ==================== 5. SECURITY ANNOTATIONS ====================
    public static void demonstrateSecurityAnnotations() {
        System.out.println("5️⃣ SECURITY ANNOTATIONS (@Secured, @RolesAllowed)");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        Class<?> clazz = AdminService.class;

        System.out.println("  🔐 Güvenlik Kontrolleri:");
        for (Method method : clazz.getDeclaredMethods()) {
            StringBuilder info = new StringBuilder("     " + method.getName() + "()");

            if (method.isAnnotationPresent(Secured.class)) {
                Secured s = method.getAnnotation(Secured.class);
                info.append(" -> @Secured(").append(String.join(", ", s.value())).append(")");
            }
            if (method.isAnnotationPresent(RolesAllowed.class)) {
                RolesAllowed ra = method.getAnnotation(RolesAllowed.class);
                info.append(" -> @RolesAllowed(").append(String.join(", ", ra.value())).append(")");
            }
            if (method.isAnnotationPresent(PermitAll.class)) {
                info.append(" -> @PermitAll (Herkese açık)");
            }
            if (method.isAnnotationPresent(DenyAll.class)) {
                info.append(" -> @DenyAll (Kimseye açık değil)");
            }

            System.out.println(info);
        }
        System.out.println();
    }
}

// ==================== DEPENDENCY INJECTION ANNOTATIONS ====================

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.CONSTRUCTOR })
@interface Inject {
    String value() default "";
}

// Basit DI Container
class DIContainer {
    @SuppressWarnings("deprecation")
    public <T> T createInstance(Class<T> clazz) {
        try {
            T instance = clazz.newInstance();
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Inject.class)) {
                    field.setAccessible(true);
                    Object dependency = field.getType().newInstance();
                    field.set(instance, dependency);
                }
            }
            return instance;
        } catch (Exception e) {
            return null;
        }
    }
}

class OrderService {
}

class PaymentService {
}

class NotificationService {
}

class OrderController {
    @Inject
    private OrderService orderService;
    @Inject("paymentGateway")
    private PaymentService paymentService;
    @Inject
    private NotificationService notificationService;
}

// ==================== REST CONTROLLER ANNOTATIONS ====================

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface RestController {
    String value() default "";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface GetMapping {
    String value();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface PostMapping {
    String value();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface PutMapping {
    String value();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface DeleteMapping {
    String value();
}

@RestController("/api/products")
class ProductRestController {
    @GetMapping("/")
    public void getAll() {
    }

    @GetMapping("/{id}")
    public void getById() {
    }

    @PostMapping("/")
    public void create() {
    }

    @PutMapping("/{id}")
    public void update() {
    }

    @DeleteMapping("/{id}")
    public void delete() {
    }
}

// ==================== ORM ANNOTATIONS ====================

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Entity {
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Table {
    String name();

    String schema() default "";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MColumn {
    String name();

    boolean nullable() default true;

    boolean unique() default false;

    int length() default 255;
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MId {
}

enum GenerationType {
    AUTO, IDENTITY, SEQUENCE, TABLE
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface GeneratedValue {
    GenerationType strategy() default GenerationType.AUTO;
}

@Entity
@Table(name = "products", schema = "shop")
class ProductEntity {
    @MId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @MColumn(name = "id", nullable = false)
    private Long id;

    @MColumn(name = "product_name", nullable = false, length = 100)
    private String name;

    @MColumn(name = "price", nullable = false)
    private Double price;

    @MColumn(name = "sku", unique = true, length = 50)
    private String sku;
}

// ==================== TEST ANNOTATIONS ====================

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Test {
    String displayName() default "";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface BeforeEach {
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface AfterEach {
}

class CalculatorTest {
    @BeforeEach
    public void setUp() {
    }

    @Test(displayName = "Toplama işlemi doğru çalışmalı")
    public void testAddition() {
    }

    @Test(displayName = "Çıkarma işlemi doğru çalışmalı")
    public void testSubtraction() {
    }

    @Test
    public void testDivisionByZero() {
    }

    @AfterEach
    public void tearDown() {
    }
}

// ==================== SECURITY ANNOTATIONS ====================

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Secured {
    String[] value();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface RolesAllowed {
    String[] value();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface PermitAll {
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface DenyAll {
}

class AdminService {
    @Secured({ "ROLE_ADMIN" })
    public void deleteUser() {
    }

    @RolesAllowed({ "ADMIN", "MANAGER" })
    public void viewReports() {
    }

    @PermitAll
    public void healthCheck() {
    }

    @DenyAll
    public void systemMaintenance() {
    }
}
