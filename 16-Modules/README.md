# 16 - Java Modules (JPMS)

## 📖 Modül Nedir?

**Java Platform Module System (JPMS)**, Java 9 ile birlikte tanıtılan ve Java uygulamalarını daha modüler, güvenli ve bakımı kolay hale getiren bir sistemdir. Modüller, ilgili paketleri ve kaynakları bir araya getiren, açık bağımlılıkları ve dışa aktarımları (exports) olan bir yapıdır.

## 🎯 Neden Modüller?

### Java 9 Öncesi Sorunlar

1. **JAR Hell**: Sınıf yolu (classpath) karmaşıklığı ve çakışan kütüphaneler
2. **Zayıf Kapsülleme**: Public sınıflar her yerden erişilebilirdi
3. **Büyük JDK**: Tüm JDK tek bir monolitik yapıdaydı (~200MB)
4. **Güvenlik Açıkları**: Internal API'lere (sun.* paketleri) erişim mümkündü

### Modüllerin Çözdüğü Sorunlar

| Sorun | Modüllerin Çözümü |
|-------|-------------------|
| JAR Hell | Açık bağımlılık bildirimleri |
| Zayıf Kapsülleme | Güçlü kapsülleme (sadece export edilen paketler erişilebilir) |
| Büyük JDK | Özelleştirilmiş runtime images (jlink) |
| Güvenlik | Internal API'lere erişim engeli |

## 📦 Modül Yapısı

### Temel Yapı

```
my-module/
├── src/
│   └── main/
│       └── java/
│           ├── module-info.java          ← Modül tanımlayıcı
│           └── com/
│               └── example/
│                   └── MyClass.java
└── pom.xml
```

### module-info.java

```java
module com.example.mymodule {
    // Bağımlılıklar (requires)
    requires java.base;           // Varsayılan, yazılmasına gerek yok
    requires java.sql;            // SQL modülüne bağımlılık
    requires transitive java.logging; // Geçişli bağımlılık
    
    // Dışa Aktarımlar (exports)
    exports com.example.api;       // Bu paketi dışarıya aç
    exports com.example.utils to com.other.module; // Sadece belirli modüle aç
    
    // Yansıma (Reflection) İzinleri
    opens com.example.models;      // Yansımaya aç
    opens com.example.dto to com.fasterxml.jackson; // Belirli modüle yansıma izni
    
    // Servis Sağlayıcı (Service Provider)
    provides com.example.Service with com.example.impl.ServiceImpl;
    uses com.example.Service;
}
```

## 🔑 Temel Anahtar Kelimeler

### 1. `module`
Modülü tanımlar.

```java
module com.fundamentals.modules {
    // modül içeriği
}
```

### 2. `requires`
Başka bir modüle bağımlılık bildirir.

```java
module com.app {
    requires java.sql;              // Normal bağımlılık
    requires transitive java.logging; // Geçişli bağımlılık
    requires static java.compiler;  // Derleme zamanı bağımlılık (isteğe bağlı)
}
```

#### requires Türleri:

| Tür | Açıklama |
|-----|----------|
| `requires` | Normal bağımlılık |
| `requires transitive` | Geçişli bağımlılık (A→B→C ise A da C'yi görür) |
| `requires static` | Derleme zamanı bağımlılık (çalışma zamanında opsiyonel) |

### 3. `exports`
Bir paketi diğer modüllere erişilebilir kılar.

```java
module com.lib {
    exports com.lib.api;                    // Herkese açık
    exports com.lib.internal to com.app;    // Sadece belirli modüle açık
}
```

### 4. `opens`
Yansıma (reflection) için paketi açar.

```java
module com.app {
    opens com.app.models;                        // Tüm modüllere yansıma izni
    opens com.app.dto to com.fasterxml.jackson;  // Sadece Jackson'a yansıma izni
}

// veya tüm modülü aç:
open module com.app {
    exports com.app.api;
}
```

### 5. `provides ... with`
Servis sağlayıcı implementasyonunu bildirir.

```java
module com.provider {
    provides com.api.PaymentService 
        with com.provider.CreditCardPayment;
}
```

### 6. `uses`
Bir servisi kullandığını bildirir.

```java
module com.consumer {
    uses com.api.PaymentService;
}
```

## 📊 Modül Türleri

### 1. Named Module (İsimli Modül)
- `module-info.java` dosyası içerir
- Açık bağımlılık ve export bildirimleri vardır

### 2. Automatic Module (Otomatik Modül)
- `module-info.java` içermeyen JAR dosyaları
- Modül ismi JAR dosyasının adından türetilir
- Tüm paketleri otomatik olarak export eder
- Tüm diğer modülleri okuyabilir

### 3. Unnamed Module (İsimsiz Modül)
- Classpath'teki tüm sınıflar
- Tüm named modülleri okuyabilir
- Named modüller tarafından okunamaz

```
┌─────────────────────────────────────────────────────────────┐
│                    Module Types                              │
├─────────────────────────────────────────────────────────────┤
│  Named Module        │  module-info.java VAR                │
│  (Explicit Module)   │  Açık exports ve requires            │
├─────────────────────────────────────────────────────────────┤
│  Automatic Module    │  module-info.java YOK                │
│                      │  Modül yolu (module path) üzerinde   │
├─────────────────────────────────────────────────────────────┤
│  Unnamed Module      │  module-info.java YOK                │
│                      │  Sınıf yolu (classpath) üzerinde     │
└─────────────────────────────────────────────────────────────┘
```

## 🛠️ Komut Satırı Araçları

### Derleme
```bash
# Modülü derle
javac -d out --module-source-path src $(find src -name "*.java")

# veya tek modül
javac -d out/com.example --module-path mods -m com.example
```

### Çalıştırma
```bash
# Modüler uygulamayı çalıştır
java --module-path out -m com.example/com.example.Main
```

### JAR Oluşturma
```bash
# Modüler JAR oluştur
jar --create --file=mods/com.example.jar \
    --module-version=1.0 \
    --main-class=com.example.Main \
    -C out/com.example .
```

### Modül Bilgisi
```bash
# Modül açıklamasını göster
jar --describe-module --file=mods/com.example.jar

# JDK modüllerini listele
java --list-modules

# Modül çözümlemesini göster
java --show-module-resolution -m com.example
```

### jlink - Özel Runtime
```bash
# Özelleştirilmiş JRE oluştur
jlink --module-path $JAVA_HOME/jmods:mods \
      --add-modules com.example \
      --output custom-runtime \
      --strip-debug \
      --compress zip-6
```

## 🌐 JDK Modülleri

Java SE, birçok modüle ayrılmıştır:

| Modül | Açıklama |
|-------|----------|
| `java.base` | Temel sınıflar (Object, String, vb.) - her zaman dahil |
| `java.sql` | JDBC API |
| `java.logging` | Logging API |
| `java.xml` | XML işleme |
| `java.desktop` | AWT ve Swing |
| `java.net.http` | HTTP Client API |
| `java.compiler` | Compiler API |

Modül bağımlılık grafiğini görmek için:
```bash
java --describe-module java.sql
```

## ✅ Best Practices

1. **Anlamlı Modül İsimleri**: Ters domain notasyonu kullanın (`com.company.project`)
2. **Minimal Exports**: Sadece gerekli paketleri dışa aktarın
3. **Geçişli Bağımlılıklar**: API'nin parçası olan bağımlılıklar için `requires transitive` kullanın
4. **Yansıma İzolasyonu**: Sadece gerekli paketleri `opens` ile açın
5. **Servis Yükleyici**: Loose coupling için ServiceLoader pattern kullanın

## 🔍 Bu Modüldeki Örnekler

| Dosya | Açıklama |
|-------|----------|
| `ModulesDemo.java` | Ana demo sınıfı |
| `ModuleBasics.java` | Modül temel kavramları |
| `ModuleKeywords.java` | module-info.java anahtar kelimeleri |
| `ModuleTypes.java` | Modül türleri ve özellikleri |
| `JdkModules.java` | JDK modülleri analizi |
| `ServiceLoaderDemo.java` | ServiceLoader pattern örneği |
| `ModuleCommands.java` | Komut satırı araçları |

## 📝 Çalıştırma

```bash
# Modülü derleyip çalıştır
./run-module.bat 16-Modules ModulesDemo
```

## 📚 Kaynaklar

- [JEP 261: Module System](https://openjdk.org/jeps/261)
- [Oracle - Understanding Java 9 Modules](https://www.oracle.com/corporate/features/understanding-java-9-modules.html)
- [Java Modularity - Baeldung](https://www.baeldung.com/java-modularity)
- [Java Modules - Jenkov](https://jenkov.com/tutorials/java/modules.html)