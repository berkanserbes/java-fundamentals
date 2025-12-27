package com.fundamentals.packages;

import com.fundamentals.packages.models.Person;
import com.fundamentals.packages.models.Student;
import com.fundamentals.packages.utils.StringUtils;
import com.fundamentals.packages.utils.MathUtils;
import com.fundamentals.packages.services.UserService;
import com.fundamentals.packages.services.EmailService;

/**
 * Java Packages - Organization and Access Control
 * 
 * ============================================================================
 * PACKAGE NEDİR?
 * ============================================================================
 * 
 * Package (Paket): İlgili sınıfları, interface'leri ve alt paketleri gruplamak
 * için kullanılan bir mekanizmadır. Dosya sistemindeki klasörler gibi çalışır.
 * 
 * ============================================================================
 * PACKAGE'LARIN FAYDALARI:
 * ============================================================================
 * 
 * ✓ Organizasyon: İlgili sınıfları gruplar
 * ✓ İsim Çakışması: Aynı isimli sınıflar farklı paketlerde olabilir
 * ✓ Erişim Kontrolü: Package-private erişim seviyesi sağlar
 * ✓ Modülerlik: Kodun yeniden kullanılabilirliğini artırır
 * ✓ Bakım Kolaylığı: Büyük projelerde kod yönetimini kolaylaştırır
 * 
 * ============================================================================
 * PACKAGE TÜRLER:
 * ============================================================================
 * 
 * 1. Built-in Packages (Yerleşik):
 *    - java.lang (String, System, Math - otomatik import)
 *    - java.util (ArrayList, HashMap, Scanner)
 *    - java.io (File, InputStream, OutputStream)
 *    - java.net (URL, Socket)
 * 
 * 2. User-defined Packages (Kullanıcı Tanımlı):
 *    - Kendi oluşturduğumuz paketler
 * 
 * ============================================================================
 * PACKAGE ADLANDIRMA KURALLARI:
 * ============================================================================
 * 
 * - Küçük harfle yazılır: com.company.project
 * - Ters domain adı kullanılır: com.google.maps
 * - Anlamlı isimler: models, services, utils, controllers
 * - Nokta ile ayrılır: com.fundamentals.packages
 * 
 * ============================================================================
 * IMPORT TÜRLER:
 * ============================================================================
 * 
 * 1. Explicit Import (Açık):
 *    import java.util.ArrayList;
 * 
 * 2. Wildcard Import (Joker):
 *    import java.util.*;
 * 
 * 3. Static Import:
 *    import static java.lang.Math.PI;
 *    import static java.lang.Math.*;
 * 
 * ============================================================================
 * ERİŞİM SEVİYELERİ VE PACKAGE:
 * ============================================================================
 * 
 * Modifier      | Class | Package | Subclass | World
 * --------------|-------|---------|----------|-------
 * public        |  ✓    |    ✓    |    ✓     |   ✓
 * protected     |  ✓    |    ✓    |    ✓     |   ✗
 * no modifier   |  ✓    |    ✓    |    ✗     |   ✗
 * private       |  ✓    |    ✗    |    ✗     |   ✗
 * 
 * ============================================================================
 */
public class PackagesDemo {

    public static void main(String[] args) {
        System.out.println("=== Java Packages ===\n");

        demonstrateBasicPackageUsage();
        demonstrateImportTypes();
        demonstrateStaticImport();
        demonstratePackageStructure();
        demonstrateAccessModifiers();
        demonstrateBuiltInPackages();
        demonstrateBestPractices();
    }

    /**
     * 1. Temel Package Kullanımı
     */
    private static void demonstrateBasicPackageUsage() {
        System.out.println("--- 1. Temel Package Kullanımı ---");
        System.out.println("Package: İlgili sınıfları gruplamak için kullanılır.\n");

        // models package'inden Person sınıfı
        Person person = new Person("Ahmet", 30);
        person.displayInfo();

        // models package'inden Student sınıfı
        Student student = new Student("Ayşe", 22, "12345");
        student.displayInfo();

        System.out.println("\nPerson ve Student sınıfları 'models' package'inde.\n");
    }

    /**
     * 2. Import Türleri
     */
    private static void demonstrateImportTypes() {
        System.out.println("--- 2. Import Türleri ---");
        System.out.println("Java'da 3 tür import vardır: Explicit, Wildcard, Static\n");

        // Explicit import kullanımı
        System.out.println("=== Explicit Import ===");
        System.out.println("import com.fundamentals.packages.models.Person;");
        Person p = new Person("Ali", 25);
        System.out.println("Person nesnesi oluşturuldu: " + p.getName());

        // Wildcard import (dosya başında kullanıldı)
        System.out.println("\n=== Wildcard Import ===");
        System.out.println("import com.fundamentals.packages.utils.*;");
        System.out.println("Tüm utils sınıfları import edildi.");

        System.out.println();
    }

    /**
     * 3. Static Import
     */
    private static void demonstrateStaticImport() {
        System.out.println("--- 3. Static Import ---");
        System.out.println("Static import: Static metodları doğrudan kullanmayı sağlar.\n");

        // Normal kullanım
        System.out.println("Normal: Math.PI = " + Math.PI);
        System.out.println("Normal: Math.sqrt(16) = " + Math.sqrt(16));

        // Static import ile (dosya başında: import static java.lang.Math.*;)
        // PI ve sqrt doğrudan kullanılabilir
        System.out.println("\nStatic import ile Math sınıfı metodları doğrudan kullanılabilir.");
        System.out.println("Örnek: import static java.lang.Math.*;");
        System.out.println();
    }

    /**
     * 4. Package Yapısı
     */
    private static void demonstratePackageStructure() {
        System.out.println("--- 4. Package Yapısı ---");
        System.out.println("Proje package yapısı:\n");

        System.out.println("com.fundamentals.packages/");
        System.out.println("├── PackagesDemo.java (Ana sınıf)");
        System.out.println("├── models/");
        System.out.println("│   ├── Person.java");
        System.out.println("│   └── Student.java");
        System.out.println("├── services/");
        System.out.println("│   ├── UserService.java");
        System.out.println("│   └── EmailService.java");
        System.out.println("└── utils/");
        System.out.println("    ├── StringUtils.java");
        System.out.println("    └── MathUtils.java");

        System.out.println("\nHer package belirli bir sorumluluğa sahiptir:");
        System.out.println("- models: Veri modelleri");
        System.out.println("- services: İş mantığı");
        System.out.println("- utils: Yardımcı fonksiyonlar");
        System.out.println();
    }

    /**
     * 5. Erişim Belirleyiciler ve Package
     */
    private static void demonstrateAccessModifiers() {
        System.out.println("--- 5. Erişim Belirleyiciler ---");
        System.out.println("Package, erişim kontrolünde önemli rol oynar.\n");

        // utils package'inden sınıflar
        String reversed = StringUtils.reverse("Merhaba");
        System.out.println("StringUtils.reverse('Merhaba') = " + reversed);

        boolean isPalindrome = StringUtils.isPalindrome("kayak");
        System.out.println("StringUtils.isPalindrome('kayak') = " + isPalindrome);

        int max = MathUtils.max(10, 20, 30);
        System.out.println("MathUtils.max(10, 20, 30) = " + max);

        // services package'inden sınıflar
        UserService userService = new UserService();
        userService.createUser("mehmet@example.com");

        EmailService emailService = new EmailService();
        emailService.sendEmail("ayse@example.com", "Hoş geldiniz!");

        System.out.println();
    }

    /**
     * 6. Yerleşik (Built-in) Packages
     */
    private static void demonstrateBuiltInPackages() {
        System.out.println("--- 6. Yerleşik (Built-in) Packages ---");
        System.out.println("Java'nın kendi package'leri:\n");

        // java.lang (otomatik import edilir)
        System.out.println("=== java.lang (Otomatik import) ===");
        String str = "Java";
        System.out.println("String sınıfı: " + str);
        System.out.println("Math.sqrt(25) = " + Math.sqrt(25));

        // java.util
        System.out.println("\n=== java.util ===");
        java.util.ArrayList<String> list = new java.util.ArrayList<>();
        list.add("Elma");
        list.add("Armut");
        System.out.println("ArrayList: " + list);

        // java.time
        System.out.println("\n=== java.time ===");
        java.time.LocalDate today = java.time.LocalDate.now();
        System.out.println("Bugünün tarihi: " + today);

        System.out.println("\nDiğer önemli package'ler:");
        System.out.println("- java.io: Dosya işlemleri");
        System.out.println("- java.net: Ağ işlemleri");
        System.out.println("- java.sql: Veritabanı işlemleri");
        System.out.println();
    }

    /**
     * 7. Best Practices (En İyi Uygulamalar)
     */
    private static void demonstrateBestPractices() {
        System.out.println("--- 7. Package Best Practices ---\n");

        System.out.println("✓ Anlamlı isimler kullanın:");
        System.out.println("  İyi: com.company.project.models");
        System.out.println("  Kötü: com.company.project.stuff");

        System.out.println("\n✓ Ters domain adı kullanın:");
        System.out.println("  Örnek: com.google.maps, org.apache.commons");

        System.out.println("\n✓ Küçük harf kullanın:");
        System.out.println("  İyi: com.fundamentals.packages");
        System.out.println("  Kötü: com.Fundamentals.Packages");

        System.out.println("\n✓ Sorumluluklara göre ayırın:");
        System.out.println("  - models: Veri sınıfları");
        System.out.println("  - services: İş mantığı");
        System.out.println("  - controllers: HTTP istekleri");
        System.out.println("  - repositories: Veritabanı erişimi");
        System.out.println("  - utils: Yardımcı fonksiyonlar");

        System.out.println("\n✓ Wildcard import'tan kaçının:");
        System.out.println("  İyi: import java.util.ArrayList;");
        System.out.println("  Kötü: import java.util.*;");

        System.out.println("\n✓ Package-private kullanın:");
        System.out.println("  Sadece aynı package içinde erişilmesi gereken");
        System.out.println("  sınıflar için modifier kullanmayın.");

        System.out.println("\nExercise completed!");
    }
}
