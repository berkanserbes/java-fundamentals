# Module 13: Annotations ☕

## 📖 Genel Bakış

**Annotations (Notasyonlar)**, Java'da kod hakkında meta-veri (metadata) sağlayan özel işaretlerdir. `@` sembolü ile başlarlar ve sınıflara, metodlara, alanlara ve diğer program elemanlarına ek bilgi eklerler.

Annotation'lar doğrudan program mantığını değiştirmezler, ancak:
- 🔧 **Derleyiciye talimat verebilirler** (`@Override`, `@SuppressWarnings`)
- ⚙️ **Derleme zamanında kod üretebilirler** (Lombok, MapStruct)
- 🚀 **Çalışma zamanında okunabilirler** (Spring, JUnit, Jackson)
- 📝 **Kod dokümantasyonu sağlarlar** (`@Deprecated`, `@author`)

> **💡 Not:** Modern Java framework'lerinin %90'ı annotation'lar üzerine kuruludur!

---

## 📚 İçerik

Bu modül aşağıdaki sınıfları içerir:

| Sınıf | Açıklama |
|-------|----------|
| `AnnotationsDemo.java` | Ana giriş noktası ve hızlı referans |
| `BuiltInAnnotations.java` | Java yerleşik annotation'ları |
| `CustomAnnotations.java` | Özel annotation oluşturma |
| `MetaAnnotations.java` | Meta-annotation'lar |
| `AnnotationProcessing.java` | Reflection ile annotation işleme |
| `RealWorldExamples.java` | Gerçek dünya örnekleri |

---

## 🎯 Öğrenme Hedefleri

Bu modülü tamamladığınızda:

- ✅ Java'nın yerleşik annotation'larını (`@Override`, `@Deprecated`, `@SuppressWarnings`, `@SafeVarargs`, `@FunctionalInterface`) kullanabileceksiniz
- ✅ Kendi özel annotation'larınızı tanımlayabileceksiniz
- ✅ Meta-annotation'ları (`@Target`, `@Retention`, `@Documented`, `@Inherited`, `@Repeatable`) anlayacaksınız
- ✅ Reflection API ile annotation'ları okuyup işleyebileceksiniz
- ✅ Spring, JUnit, JPA gibi framework'lerde annotation'ların nasıl çalıştığını kavrayacaksınız

---

## 📋 Hızlı Referans

### Yerleşik Annotations

```java
// 1. @Override - Metod override kontrolü
class Child extends Parent {
    @Override
    public void display() { }
}

// 2. @Deprecated - Kullanımdan kaldırıldı
@Deprecated(since = "2.0", forRemoval = true)
public void oldMethod() { }

// 3. @SuppressWarnings - Uyarı bastırma
@SuppressWarnings("unchecked")
List rawList = new ArrayList();

// 4. @SafeVarargs - Varargs güvenliği
@SafeVarargs
public static <T> void print(T... elements) { }

// 5. @FunctionalInterface - Lambda uyumlu
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}
```

### Özel Annotation Tanımlama

```java
@Retention(RetentionPolicy.RUNTIME)  // Çalışma zamanında okunabilir
@Target(ElementType.METHOD)           // Sadece metodlara uygulanabilir
public @interface MyAnnotation {
    String value();                   // Zorunlu element
    int priority() default 0;         // Varsayılan değerli element
    String[] tags() default {};       // Dizi element
}

// Kullanım
@MyAnnotation(value = "test", priority = 5, tags = {"a", "b"})
public void myMethod() { }
```

### Meta-Annotations

| Meta-Annotation | Açıklama |
|-----------------|----------|
| `@Target` | Annotation nereye uygulanabilir? (`TYPE`, `METHOD`, `FIELD`, vb.) |
| `@Retention` | Ne kadar saklanır? (`SOURCE`, `CLASS`, `RUNTIME`) |
| `@Documented` | Javadoc'a dahil edilsin mi? |
| `@Inherited` | Alt sınıflara miras geçsin mi? |
| `@Repeatable` | Aynı elemana birden fazla uygulanabilir mi? |

### RetentionPolicy Değerleri

```
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
```

### ElementType Değerleri

| ElementType | Açıklama |
|-------------|----------|
| `TYPE` | Sınıf, interface, enum, record |
| `FIELD` | Alan (instance variable) |
| `METHOD` | Metod |
| `PARAMETER` | Metod parametresi |
| `CONSTRUCTOR` | Constructor |
| `LOCAL_VARIABLE` | Yerel değişken |
| `ANNOTATION_TYPE` | Başka bir annotation |
| `PACKAGE` | Paket (package-info.java) |
| `TYPE_PARAMETER` | Generic tip parametresi (Java 8+) |
| `TYPE_USE` | Her türlü tip kullanımı (Java 8+) |
| `MODULE` | Modül (Java 9+) |
| `RECORD_COMPONENT` | Record bileşeni (Java 16+) |

---

## 🔍 Reflection ile Annotation Okuma

```java
// Sınıf annotation'ını oku
Class<?> clazz = MyClass.class;
if (clazz.isAnnotationPresent(MyAnnotation.class)) {
    MyAnnotation ann = clazz.getAnnotation(MyAnnotation.class);
    System.out.println(ann.value());
}

// Metod annotation'ını oku
Method method = clazz.getMethod("myMethod");
MyAnnotation ann = method.getAnnotation(MyAnnotation.class);

// Field annotation'ını oku
Field field = clazz.getDeclaredField("myField");
if (field.isAnnotationPresent(NotNull.class)) {
    // validation logic
}

// Tüm annotation'ları al
Annotation[] annotations = clazz.getAnnotations();
```

---

## 🌍 Gerçek Dünya Kullanımları

### 1. Spring Framework
```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findById(id);
    }
}
```

### 2. JPA/Hibernate (ORM)
```java
@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "email", unique = true, nullable = false)
    private String email;
}
```

### 3. JUnit 5 (Test)
```java
@DisplayName("Calculator Tests")
class CalculatorTest {
    
    @BeforeEach
    void setUp() { }
    
    @Test
    @DisplayName("Addition should work correctly")
    void testAddition() {
        assertEquals(4, calculator.add(2, 2));
    }
}
```

### 4. Lombok
```java
@Data                    // Getter, Setter, equals, hashCode, toString
@NoArgsConstructor       // Parametresiz constructor
@AllArgsConstructor      // Tüm alanlarla constructor
@Builder                 // Builder pattern
public class User {
    private Long id;
    private String name;
}
```

---

## 🚀 Çalıştırma

### Ana Demo
```bash
.\run-module.bat 13-Annotations AnnotationsDemo
```

### Belirli Bir Sınıfı Çalıştırma
```bash
# Yerleşik annotation'lar
.\run-module.bat 13-Annotations BuiltInAnnotations

# Özel annotation oluşturma
.\run-module.bat 13-Annotations CustomAnnotations

# Meta-annotations
.\run-module.bat 13-Annotations MetaAnnotations

# Annotation processing
.\run-module.bat 13-Annotations AnnotationProcessing

# Gerçek dünya örnekleri
.\run-module.bat 13-Annotations RealWorldExamples
```

---

## 💡 Best Practices

1. **@Override her zaman kullanın**
   - Yazım hatalarını önler
   - Parent metod değiştiğinde uyarı verir

2. **@Deprecated ile açıklama ekleyin**
   - `since` ve `forRemoval` parametrelerini kullanın
   - Alternatif metodu Javadoc'ta belirtin

3. **@SuppressWarnings dikkatli kullanın**
   - Mümkün olduğunca dar kapsamda kullanın
   - Neden bastırdığınızı yorum olarak yazın

4. **Custom annotation'larda @Retention(RUNTIME) tercih edin**
   - Reflection ile okunabilir olur
   - Framework entegrasyonu sağlar

5. **Tek değerli annotation'larda `value()` kullanın**
   - `@MyAnnotation("test")` şeklinde kısa kullanım sağlar

---

## Kaynaklar
- [Annotations - Oracle Docs](https://docs.oracle.com/javase/tutorial/java/annotations/)
- [Annotations in Java - GeeksforGeeks](https://www.geeksforgeeks.org/java/annotations-in-java/)
- [Java Annotations - Jenkov](https://jenkov.com/tutorials/java/annotations.html)
- [Java Annotations - Programiz](https://www.programiz.com/java-programming/annotations)
- [Java Annotation Types - Programiz](https://www.programiz.com/java-programming/annotation-types)
- [Java Annotations - DigitalOcean](https://www.digitalocean.com/community/tutorials/java-annotations)
- [Java Annotations - dev.java](https://dev.java/learn/annotations/)
- [Java Annotations - W3Schools](https://www.w3schools.com/java/java_annotations.asp)
- [Overview of Java Built-in Annotations - Baeldung](https://www.baeldung.com/java-default-annotations)
- [Creating a Custom Annotation in Java - Baeldung](https://www.baeldung.com/java-custom-annotation)
- [Java Reflection API](https://docs.oracle.com/javase/tutorial/reflect/)

---

## ✅ Özet

| Konu | Açıklama |
|------|----------|
| **Annotation** | `@` ile başlayan meta-veri işareti |
| **Meta-annotation** | Annotation'ları yapılandıran annotation |
| **Marker Annotation** | Element içermeyen annotation (sadece varlığı önemli) |
| **Single-value Annotation** | Tek elementi olan annotation |
| **Retention** | Annotation'ın yaşam süresi (SOURCE, CLASS, RUNTIME) |
| **Target** | Annotation'ın uygulanabileceği hedefler |
| **Reflection** | Çalışma zamanında annotation okuma yöntemi |

---


