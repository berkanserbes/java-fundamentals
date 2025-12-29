# 11-ExceptionHandling

Java'da **Exception Handling** (İstisna Yönetimi) konusunu detaylı ve kapsamlı şekilde öğrenmek için örnekler.

## 📚 İçindekiler

### Kapsanan Konular

| Sınıf | Konu | Açıklama |
|-------|------|----------|
| **ExceptionBasics** | Temel Exception'lar | ArithmeticException, NullPointerException, ArrayIndexOutOfBoundsException, vb. |
| **TryCatchFinally** | Try-Catch-Finally | Exception yakalama, finally bloğu, try-with-resources, multi-catch |
| **ThrowAndThrows** | Throw & Throws | Exception fırlatma, throws bildirimi, re-throw, checked vs unchecked |
| **CustomExceptions** | Özel Exception'lar | Custom exception oluşturma, hata kodları, exception chaining |
| **ExceptionBestPractices** | En İyi Uygulamalar | Best practices, yaygın hatalar, gerçek dünya örnekleri |

## 🎯 Exception Hiyerarşisi

```
Object
  └── Throwable
        ├── Error (JVM hataları - yakalanmamalı)
        │     ├── OutOfMemoryError
        │     ├── StackOverflowError
        │     └── ...
        │
        └── Exception (Yakalanabilir hatalar)
              ├── RuntimeException (Unchecked)
              │     ├── NullPointerException
              │     ├── ArithmeticException
              │     ├── ArrayIndexOutOfBoundsException
              │     ├── NumberFormatException
              │     ├── ClassCastException
              │     └── ...
              │
              └── Checked Exceptions
                    ├── IOException
                    ├── SQLException
                    ├── ClassNotFoundException
                    └── ...
```

## 🚀 Nasıl Çalıştırılır?

### Tüm Demoları Çalıştırma:
```bash
cd 11-ExceptionHandling
mvn compile exec:java
```

### Tek Bir Demo Çalıştırma:
```bash
# Exception Basics
mvn exec:java -Dexec.mainClass="com.fundamentals.exceptionhandling.ExceptionBasics"

# Try-Catch-Finally
mvn exec:java -Dexec.mainClass="com.fundamentals.exceptionhandling.TryCatchFinally"

# Throw and Throws
mvn exec:java -Dexec.mainClass="com.fundamentals.exceptionhandling.ThrowAndThrows"

# Custom Exceptions
mvn exec:java -Dexec.mainClass="com.fundamentals.exceptionhandling.CustomExceptions"

# Best Practices
mvn exec:java -Dexec.mainClass="com.fundamentals.exceptionhandling.ExceptionBestPractices"
```

## 📖 Detaylı Konu Anlatımı

### 1. Exception Basics (Temel Exception'lar)

#### ArithmeticException
```java
try {
    int result = 10 / 0;  // Sıfıra bölme
} catch (ArithmeticException e) {
    System.out.println("Hata: Sıfıra bölme!");
}
```

#### NullPointerException
```java
try {
    String str = null;
    int length = str.length();  // Null referans
} catch (NullPointerException e) {
    System.out.println("Hata: Null nesne!");
}
```

#### ArrayIndexOutOfBoundsException
```java
try {
    int[] arr = {1, 2, 3};
    int value = arr[10];  // Dizi sınır aşımı
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("Hata: Geçersiz indeks!");
}
```

### 2. Try-Catch-Finally

#### Temel Yapı
```java
try {
    // Hata oluşabilecek kod
} catch (SpecificException e) {
    // Exception handling
} catch (AnotherException e) {
    // Başka bir exception
} finally {
    // Her durumda çalışır
}
```

#### Try-with-Resources (Java 7+)
```java
// Otomatik kaynak yönetimi
try (BufferedReader reader = new BufferedReader(new FileReader("file.txt"))) {
    String line = reader.readLine();
    // Kaynak otomatik kapatılır
} catch (IOException e) {
    e.printStackTrace();
}
```

#### Multi-Catch (Java 7+)
```java
try {
    // Kod
} catch (IOException | SQLException e) {
    // Birden fazla exception tek catch'de
    System.out.println("Hata: " + e.getMessage());
}
```

### 3. Throw ve Throws

#### throw - Exception Fırlatma
```java
public void validateAge(int age) {
    if (age < 18) {
        throw new IllegalArgumentException("Yaş 18'den küçük olamaz");
    }
}
```

#### throws - Exception Bildirimi
```java
public void readFile(String filename) throws IOException {
    // Dosya okuma işlemi
    throw new IOException("Dosya bulunamadı");
}
```

#### Re-throw - Yeniden Fırlatma
```java
try {
    // İşlem
} catch (Exception e) {
    // Loglama
    throw e;  // Yeniden fırlat
}
```

### 4. Custom Exceptions (Özel Exception'lar)

#### Basit Custom Exception
```java
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
```

#### Hata Kodu ile Custom Exception
```java
class ValidationException extends Exception {
    private final String errorCode;
    
    public ValidationException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
}
```

#### Exception Chaining
```java
try {
    // Alt seviye işlem
} catch (IOException e) {
    // Orijinal hatayı koru
    throw new OrderProcessingException("Sipariş işlenemedi", e);
}
```

### 5. Best Practices (En İyi Uygulamalar)

#### ✓ DO (Yapılması Gerekenler)

**1. Spesifik Exception'ları Yakala**
```java
// ✓ İYİ
try {
    // kod
} catch (FileNotFoundException e) {
    // Spesifik handling
}

// ❌ KÖTÜ
try {
    // kod
} catch (Exception e) {
    // Çok genel!
}
```

**2. Açıklayıcı Mesajlar Kullan**
```java
// ✓ İYİ
throw new IllegalArgumentException(
    "Kullanıcı adı boş olamaz. Lütfen geçerli bir değer girin."
);

// ❌ KÖTÜ
throw new IllegalArgumentException("Hata");
```

**3. Try-with-Resources Kullan**
```java
// ✓ İYİ
try (BufferedReader reader = new BufferedReader(new FileReader("file.txt"))) {
    // Otomatik kapatılır
}

// ❌ KÖTÜ
BufferedReader reader = null;
try {
    reader = new BufferedReader(new FileReader("file.txt"));
} finally {
    if (reader != null) reader.close();
}
```

**4. Exception Chain'i Koru**
```java
// ✓ İYİ
catch (IOException e) {
    throw new Exception("İşlem başarısız", e);  // Orijinal hata korundu
}

// ❌ KÖTÜ
catch (IOException e) {
    throw new Exception("İşlem başarısız");  // Orijinal hata kayboldu
}
```

#### ✗ DON'T (Yapılmaması Gerekenler)

**1. Boş Catch Bloğu**
```java
// ❌ KÖTÜ
try {
    // kod
} catch (Exception e) {
    // Boş! Exception yutuldu
}
```

**2. printStackTrace() Kullanımı (Production'da)**
```java
// ❌ KÖTÜ (Production'da)
catch (Exception e) {
    e.printStackTrace();  // Console'a yazdırır
}

// ✓ İYİ
catch (Exception e) {
    logger.error("Hata oluştu", e);  // Logger kullan
}
```

**3. Exception'ı Kontrol Akışı İçin Kullanma**
```java
// ❌ KÖTÜ
try {
    String text = null;
    if (text.isEmpty()) {  // NullPointerException
        // ...
    }
} catch (NullPointerException e) {
    // Exception ile kontrol
}

// ✓ İYİ
String text = null;
if (text == null || text.isEmpty()) {
    // Normal kontrol
}
```

## 🔍 Checked vs Unchecked Exceptions

### Checked Exceptions
- **Compile-time'da kontrol edilir**
- **try-catch veya throws zorunlu**
- **Örnekler**: IOException, SQLException, ClassNotFoundException

```java
// Checked exception - throws gerekir
public void readFile() throws IOException {
    throw new IOException("Dosya bulunamadı");
}
```

### Unchecked Exceptions
- **Runtime'da oluşur**
- **Handle etmek zorunlu değil**
- **RuntimeException ve alt sınıfları**
- **Örnekler**: NullPointerException, ArithmeticException, IllegalArgumentException

```java
// Unchecked exception - throws opsiyonel
public void divide(int a, int b) {
    if (b == 0) {
        throw new ArithmeticException("Sıfıra bölme");
    }
}
```

## 💡 Gerçek Dünya Örnekleri

### Kullanıcı Kaydı Senaryosu
```java
public User registerUser(String email, String password, int age) 
        throws UserServiceException {
    try {
        validateEmail(email);
        validatePassword(password);
        validateAge(age);
        return new User(email, age);
    } catch (ValidationException e) {
        throw new UserServiceException("USR001", "Kayıt başarısız", e);
    }
}
```

### Banka İşlemi Senaryosu
```java
public void withdraw(double amount) throws InsufficientBalanceException {
    if (amount > balance) {
        throw new InsufficientBalanceException(
            "Yetersiz bakiye! İstenen: " + amount + ", Mevcut: " + balance
        );
    }
    balance -= amount;
}
```

## 📊 Exception Handling Akış Şeması

```
Program Başlangıç
       ↓
   try bloğu
       ↓
   Hata var mı?
    ↙     ↘
 Evet    Hayır
   ↓       ↓
catch   finally
bloğu    bloğu
   ↓       ↓
finally  Program
bloğu    Devam
   ↓
Program
Devam/Sonlanma
```

## 🎓 Öğrenme Hedefleri

Bu modülü tamamladıktan sonra:

- ✓ Exception hiyerarşisini anlayabileceksiniz
- ✓ try-catch-finally yapısını kullanabileceksiniz
- ✓ Checked ve unchecked exception'ları ayırt edebileceksiniz
- ✓ throw ve throws arasındaki farkı bileceksiniz
- ✓ Custom exception oluşturabileceksiniz
- ✓ Exception best practices'leri uygulayabileceksiniz
- ✓ try-with-resources kullanabileceksiniz
- ✓ Exception chaining yapabileceksiniz

## 📚 Kaynaklar

- [Java Exceptions - Oracle Docs](https://docs.oracle.com/javase/tutorial/essential/exceptions/)
- [Java Exceptions - Programiz](https://www.programiz.com/java-programming/exceptions)
- [Java Exception Handling - Programiz](https://www.programiz.com/java-programming/exception-handling)
- [Java Try-Catch - Programiz](https://www.programiz.com/java-programming/try-catch)
- [Java Throw and Throws - Programiz](https://www.programiz.com/java-programming/throw-throws)
- [Java Multiple Exceptions - Programiz](https://www.programiz.com/java-programming/multiple-exceptions)
- [Java Try-with-resources - Programiz](https://www.programiz.com/java-programming/try-with-resources)
- [Exception Handling in Java Tutorial - Coding with John](https://www.youtube.com/watch?v=1XAfapkBQjk)
- [Exception Handling in Java - Baeldung](https://www.baeldung.com/java-exceptions)
- [Checked vs Unchecked Exceptions - Baeldung](https://www.baeldung.com/java-checked-unchecked-exceptions)
- [Checked vs. Unchecked Exceptions in Java Tutorial - Coding with John](https://www.youtube.com/watch?v=bCPClyGsVhc)
- [Create a Custom Exception in Java - Baeldung](https://www.baeldung.com/java-new-custom-exception)
- [Java Custom Exceptions Tutorial - Coding with John](https://youtu.be/OIozDnGYqIU)
- [Chained Exceptions in Java - Baeldung](https://www.baeldung.com/java-chained-exceptions)
- [Difference Between Throw and Throws in Java - Baeldung](https://www.baeldung.com/java-throw-throws)
- [Java – Try with Resources - Baeldung](https://www.baeldung.com/java-try-with-resources)
- [Static and dynamic binding in java - Beginnersbook](https://beginnersbook.com/2013/04/java-static-dynamic-binding/)
- [Static and Dynamic Binding in Java - Baeldung](https://www.baeldung.com/java-static-dynamic-binding)
- [Static vs Dynamic Binding in Java - GeeksforGeeks](https://www.geeksforgeeks.org/java/static-vs-dynamic-binding-in-java/)