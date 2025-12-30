package com.fundamentals.annotations;

import java.util.ArrayList;
import java.util.List;

/**
 * =====================================================
 * JAVA YERLEŞIK (BUILT-IN) ANNOTATIONS
 * =====================================================
 * 
 * Java'da yerleşik annotation'lar, derleyiciye ve JVM'e özel talimatlar
 * vermek için kullanılır. Bu annotation'lar java.lang paketinde bulunur.
 * 
 * YERLEŞIK ANNOTATION'LAR:
 * 
 * 1. @Override
 * - Bir metodun üst sınıftaki metodu override ettiğini belirtir
 * - Derleme zamanında kontrol sağlar
 * - Yanlış imza kullanımını önler
 * 
 * 2. @Deprecated
 * - Bir elemanın kullanımdan kaldırıldığını işaretler
 * - Derleyici uyarısı üretir
 * - since ve forRemoval parametreleri (Java 9+)
 * 
 * 3. @SuppressWarnings
 * - Derleyici uyarılarını bastırır
 * - Belirli uyarı türlerini hedefler
 * - Yaygın değerler: "unchecked", "deprecation", "unused"
 * 
 * 4. @SafeVarargs
 * - Varargs parametrelerin güvenli olduğunu belirtir
 * - Heap pollution uyarısını bastırır
 * - Sadece final veya static metodlarda kullanılır
 * 
 * 5. @FunctionalInterface
 * - Bir interface'in functional interface olduğunu belirtir
 * - Sadece BİR abstract metod içermeli
 * - Lambda expressions ile kullanılır
 * 
 * @author Java Fundamentals
 * @version 1.0
 */
public class BuiltInAnnotations {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║         JAVA YERLEŞIK (BUILT-IN) ANNOTATIONS                 ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        // 1. @Override Örneği
        demonstrateOverride();

        // 2. @Deprecated Örneği
        demonstrateDeprecated();

        // 3. @SuppressWarnings Örneği
        demonstrateSuppressWarnings();

        // 4. @SafeVarargs Örneği
        demonstrateSafeVarargs();

        // 5. @FunctionalInterface Örneği
        demonstrateFunctionalInterface();

        System.out.println("\n✓ Tüm yerleşik annotation örnekleri tamamlandı!");
    }

    // ==================== 1. @OVERRIDE ====================
    /**
     * @Override Annotation
     * 
     *           Bu annotation, bir metodun üst sınıftaki bir metodu
     *           override ettiğini derleyiciye bildirir.
     * 
     *           FAYDALAR:
     *           - Derleme zamanında hata kontrolü sağlar
     *           - Yazım hatalarını önler
     *           - Kod okunabilirliğini artırır
     *           - Metod imzası değişikliklerinde uyarı verir
     */
    public static void demonstrateOverride() {
        System.out.println("1️⃣ @OVERRIDE ANNOTATION");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        Animal animal = new Animal();
        Animal dog = new Dog();
        Animal cat = new Cat();

        System.out.println("Animal referansı ile farklı objeler:");
        System.out.print("  animal.makeSound() -> ");
        animal.makeSound();

        System.out.print("  dog.makeSound()    -> ");
        dog.makeSound(); // Override edilmiş metod çalışır

        System.out.print("  cat.makeSound()    -> ");
        cat.makeSound(); // Override edilmiş metod çalışır

        System.out.println("\n📌 NOT: @Override olmadan da override yapılabilir,");
        System.out.println("   ancak annotation kullanmak best practice'dir.\n");
    }

    // ==================== 2. @DEPRECATED ====================
    /**
     * @Deprecated Annotation
     * 
     *             Bir eleman (sınıf, metod, field vb.) kullanımdan kaldırıldığında
     *             bu annotation ile işaretlenir.
     * 
     *             PARAMETRELER (Java 9+):
     *             - since: Hangi versiyondan itibaren deprecated olduğu
     *             - forRemoval: Gelecekte kaldırılacak mı
     */
    public static void demonstrateDeprecated() {
        System.out.println("2️⃣ @DEPRECATED ANNOTATION");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        LegacyCalculator calc = new LegacyCalculator();

        // Deprecated metod - IDE'de üstü çizili görünür
        System.out.println("  Deprecated metod kullanımı:");
        System.out.println("    calc.oldAdd(5, 3) = " + calc.oldAdd(5, 3));

        // Yeni metod - önerilen kullanım
        System.out.println("  Yeni metod kullanımı:");
        System.out.println("    calc.add(5, 3) = " + calc.add(5, 3));

        System.out.println("\n📌 Deprecated metodlar derleyici uyarısı üretir.");
        System.out.println("   @SuppressWarnings(\"deprecation\") ile bastırılabilir.\n");
    }

    // ==================== 3. @SUPPRESSWARNINGS ====================
    /**
     * @SuppressWarnings Annotation
     * 
     *                   Belirli derleyici uyarılarını bastırmak için kullanılır.
     * 
     *                   YAYGIN DEĞERLER:
     *                   - "unchecked" : Generic tip kontrolü uyarıları
     *                   - "deprecation" : Deprecated eleman kullanımı
     *                   - "unused" : Kullanılmayan değişken/metod
     *                   - "rawtypes" : Raw type kullanımı
     *                   - "serial" : serialVersionUID eksikliği
     *                   - "all" : Tüm uyarılar
     */
    public static void demonstrateSuppressWarnings() {
        System.out.println("3️⃣ @SUPPRESSWARNINGS ANNOTATION");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        // @SuppressWarnings("unchecked") - Generic uyarısını bastırır
        @SuppressWarnings("unchecked")
        List rawList = new ArrayList(); // Raw type kullanımı
        rawList.add("Merhaba");
        rawList.add(42); // Type safety uyarısı bastırıldı

        System.out.println("  Raw List içeriği: " + rawList);

        // @SuppressWarnings("unused") - Kullanılmayan değişken uyarısı
        @SuppressWarnings("unused")
        int unusedVariable = 100;

        // Birden fazla uyarı bastırma
        @SuppressWarnings({ "unchecked", "rawtypes" })
        List anotherRawList = new ArrayList();
        anotherRawList.add("Test");

        System.out.println("  Başka Raw List: " + anotherRawList);

        System.out.println("\n📌 @SuppressWarnings dikkatli kullanılmalıdır.");
        System.out.println("   Gereksiz kullanım potansiyel hataları gizleyebilir.\n");
    }

    // ==================== 4. @SAFEVARARGS ====================
    /**
     * @SafeVarargs Annotation
     * 
     *              Generic varargs parametrelerin güvenli olduğunu belirtir.
     *              "Heap pollution" uyarısını bastırır.
     * 
     *              KULLANIM KURALLARI:
     *              - Sadece final, static veya private metodlarda kullanılabilir
     *              - Constructor'larda kullanılabilir
     *              - Override edilebilen metodlarda kullanılamaz
     */
    public static void demonstrateSafeVarargs() {
        System.out.println("4️⃣ @SAFEVARARGS ANNOTATION");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        // Generic varargs metod çağrısı
        List<String> list1 = List.of("A", "B", "C");
        List<String> list2 = List.of("D", "E", "F");

        List<String> merged = mergeListsExample(list1, list2);
        System.out.println("  Birleştirilmiş liste: " + merged);

        // Farklı türlerle kullanım
        printElements("Merhaba", "Dünya", "Java");
        printElements(1, 2, 3, 4, 5);

        System.out.println("\n📌 @SafeVarargs, generic array creation uyarısını");
        System.out.println("   bastırır ve metodun güvenli olduğunu belirtir.\n");
    }

    // @SafeVarargs ile güvenli varargs metod
    @SafeVarargs
    public static <T> void printElements(T... elements) {
        System.out.print("  Elemanlar: ");
        for (T element : elements) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    // Generic liste birleştirme (SafeVarargs ile)
    @SafeVarargs
    public static <T> List<T> mergeListsExample(List<T>... lists) {
        List<T> result = new ArrayList<>();
        for (List<T> list : lists) {
            result.addAll(list);
        }
        return result;
    }

    // ==================== 5. @FUNCTIONALINTERFACE ====================
    /**
     * @FunctionalInterface Annotation
     * 
     *                      Bir interface'in functional interface olduğunu belirtir.
     *                      Lambda expressions ile kullanılabilir olduğunu garanti
     *                      eder.
     * 
     *                      KURALLAR:
     *                      - Tam olarak BİR abstract metod içermelidir
     *                      - Birden fazla default veya static metod olabilir
     *                      - Object sınıfının metodlarını declare edebilir
     */
    public static void demonstrateFunctionalInterface() {
        System.out.println("5️⃣ @FUNCTIONALINTERFACE ANNOTATION");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        // Lambda expression ile Calculator kullanımı
        Calculator add = (a, b) -> a + b;
        Calculator subtract = (a, b) -> a - b;
        Calculator multiply = (a, b) -> a * b;
        Calculator divide = (a, b) -> b != 0 ? a / b : 0;

        int x = 20, y = 5;

        System.out.println("  Functional Interface ile hesaplamalar:");
        System.out.println("    " + x + " + " + y + " = " + add.calculate(x, y));
        System.out.println("    " + x + " - " + y + " = " + subtract.calculate(x, y));
        System.out.println("    " + x + " * " + y + " = " + multiply.calculate(x, y));
        System.out.println("    " + x + " / " + y + " = " + divide.calculate(x, y));

        // StringProcessor örneği
        StringProcessor toUpperCase = str -> str.toUpperCase();
        StringProcessor toLowerCase = str -> str.toLowerCase();
        StringProcessor reverse = str -> new StringBuilder(str).reverse().toString();

        String text = "Java Annotations";
        System.out.println("\n  String işlemleri:");
        System.out.println("    Orijinal    : " + text);
        System.out.println("    Büyük harf  : " + toUpperCase.process(text));
        System.out.println("    Küçük harf  : " + toLowerCase.process(text));
        System.out.println("    Ters çevir  : " + reverse.process(text));

        // Predicate benzeri functional interface
        Validator<String> notEmpty = str -> str != null && !str.isEmpty();
        Validator<Integer> isPositive = num -> num != null && num > 0;

        System.out.println("\n  Doğrulama (Validation):");
        System.out.println("    'Hello' boş değil mi? " + notEmpty.validate("Hello"));
        System.out.println("    '' boş değil mi? " + notEmpty.validate(""));
        System.out.println("    42 pozitif mi? " + isPositive.validate(42));
        System.out.println("    -5 pozitif mi? " + isPositive.validate(-5));

        System.out.println("\n📌 @FunctionalInterface ile işaretlenen interface'ler");
        System.out.println("   lambda expressions ile kullanılabilir.\n");
    }
}

// ==================== YARDIMCI SINIFLAR ====================

/**
 * @Override örneği için temel sınıf
 */
class Animal {
    public void makeSound() {
        System.out.println("Hayvan ses çıkarıyor");
    }

    public void eat() {
        System.out.println("Hayvan yemek yiyor");
    }
}

/**
 * @Override annotation kullanımı
 */
class Dog extends Animal {
    @Override // Derleyici bu metodun override olduğunu kontrol eder
    public void makeSound() {
        System.out.println("Hav hav!");
    }

    @Override
    public void eat() {
        System.out.println("Köpek mama yiyor");
    }

    // Yeni metod - override değil
    public void fetch() {
        System.out.println("Köpek topu getiriyor");
    }
}

class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Miyav!");
    }
}

/**
 * @Deprecated örneği için sınıf
 */
class LegacyCalculator {

    /**
     * @deprecated Bu metod kullanımdan kaldırıldı.
     *             Bunun yerine {@link #add(int, int)} metodunu kullanın.
     */
    @Deprecated(since = "1.5", forRemoval = true)
    public int oldAdd(int a, int b) {
        return a + b;
    }

    /**
     * Modern toplama metodu - önerilen kullanım
     */
    public int add(int a, int b) {
        return a + b;
    }

    @Deprecated(since = "2.0")
    public int oldSubtract(int a, int b) {
        return a - b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }
}

// ==================== FUNCTIONAL INTERFACES ====================

/**
 * Basit hesaplama için functional interface
 */
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);

    // Default metodlar eklenebilir
    default void printInfo() {
        System.out.println("Bu bir Calculator functional interface'dir");
    }

    // Static metodlar eklenebilir
    static Calculator getAdder() {
        return (a, b) -> a + b;
    }
}

/**
 * String işleme için functional interface
 */
@FunctionalInterface
interface StringProcessor {
    String process(String input);
}

/**
 * Generic doğrulama için functional interface
 */
@FunctionalInterface
interface Validator<T> {
    boolean validate(T value);

    // Default AND operatörü
    default Validator<T> and(Validator<T> other) {
        return value -> this.validate(value) && other.validate(value);
    }

    // Default OR operatörü
    default Validator<T> or(Validator<T> other) {
        return value -> this.validate(value) || other.validate(value);
    }
}
