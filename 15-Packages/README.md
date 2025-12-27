# 15-Packages

Java'da **Packages** (Paketler) konusunu öğrenmek için kapsamlı örnekler.

## 📚 İçindekiler

### Package Kavramı
- Package nedir?
- Neden package kullanırız?
- Package türleri (Built-in vs User-defined)
- Package adlandırma kuralları

### Import Türleri
- **Explicit Import**: Tek sınıf import etme
- **Wildcard Import**: Tüm package import etme
- **Static Import**: Static üyeleri import etme

### Package Yapısı
```
com.fundamentals.packages/
├── PackagesDemo.java (Ana sınıf)
├── models/
│   ├── Person.java
│   └── Student.java
├── services/
│   ├── UserService.java
│   └── EmailService.java
└── utils/
    ├── StringUtils.java
    └── MathUtils.java
```

### Erişim Kontrolü
- `public`: Her yerden erişilebilir
- `protected`: Aynı package + alt sınıflar
- `default` (no modifier): Sadece aynı package
- `private`: Sadece aynı sınıf

## 🚀 Nasıl Çalıştırılır?

### Maven ile:
```bash
cd 15-Packages
mvn compile exec:java
```

### Komut satırından:
```bash
# Windows
..\run-module.bat 15-Packages PackagesDemo

# Linux/Mac
../run-module.sh 15-Packages PackagesDemo
```

## 📖 Öğrenilen Konular

### 1. Package Organizasyonu
```java
package com.fundamentals.packages.models;

public class Person {
    private String name;
    private int age;
    // ...
}
```

### 2. Import Kullanımı
```java
// Explicit import
import com.fundamentals.packages.models.Person;

// Wildcard import
import com.fundamentals.packages.utils.*;

// Static import
import static java.lang.Math.PI;
import static java.lang.Math.*;
```

### 3. Package Yapısı
- **models**: Veri modelleri (Person, Student)
- **services**: İş mantığı (UserService, EmailService)
- **utils**: Yardımcı fonksiyonlar (StringUtils, MathUtils)

### 4. Erişim Belirleyiciler

| Modifier | Class | Package | Subclass | World |
|----------|-------|---------|----------|-------|
| public | ✓ | ✓ | ✓ | ✓ |
| protected | ✓ | ✓ | ✓ | ✗ |
| default | ✓ | ✓ | ✗ | ✗ |
| private | ✓ | ✗ | ✗ | ✗ |

## 🎯 Best Practices

### ✓ DO (Yapılması Gerekenler)
```java
// Anlamlı package isimleri
package com.company.project.models;

// Küçük harf kullan
package com.fundamentals.packages;

// Ters domain adı kullan
package com.google.maps;

// Explicit import tercih et
import java.util.ArrayList;

// Sorumluluklara göre ayır
models/      // Veri sınıfları
services/    // İş mantığı
controllers/ // HTTP istekleri
utils/       // Yardımcı fonksiyonlar
```

### ✗ DON'T (Yapılmaması Gerekenler)
```java
// Anlamsız isimler
package com.company.stuff;

// Büyük harf kullanma
package com.Company.Project;

// Wildcard import
import java.util.*;

// Tek package'de her şey
everything_in_one_package/
```

## 📦 Yerleşik (Built-in) Packages

### java.lang (Otomatik import)
```java
String str = "Java";        // java.lang.String
Math.sqrt(25);              // java.lang.Math
System.out.println();       // java.lang.System
```

### java.util
```java
ArrayList<String> list = new ArrayList<>();
HashMap<String, Integer> map = new HashMap<>();
Scanner scanner = new Scanner(System.in);
```

### java.io
```java
File file = new File("data.txt");
FileInputStream fis = new FileInputStream(file);
BufferedReader reader = new BufferedReader(new FileReader(file));
```

### java.time
```java
LocalDate today = LocalDate.now();
LocalDateTime now = LocalDateTime.now();
ZonedDateTime zdt = ZonedDateTime.now();
```

## 🔍 Package Kullanım Senaryoları

### Senaryo 1: Aynı Package İçinde
```java
package com.fundamentals.packages.models;

// Student, Person ile aynı package'de
// Import gerekmez
public class Student extends Person {
    // ...
}
```

### Senaryo 2: Farklı Package'den Import
```java
package com.fundamentals.packages;

// Farklı package'den import gerekir
import com.fundamentals.packages.models.Person;
import com.fundamentals.packages.utils.StringUtils;

public class PackagesDemo {
    // ...
}
```

### Senaryo 3: Fully Qualified Name
```java
// Import kullanmadan tam isimle
java.util.ArrayList<String> list = new java.util.ArrayList<>();
```

## 💡 Önemli Notlar

1. **java.lang** otomatik import edilir
2. Aynı package'deki sınıflar import gerektirmez
3. Package isimleri küçük harf olmalı
4. Ters domain adı kullanımı standart
5. Wildcard import yerine explicit import tercih edilmeli
6. Utility sınıfları static metodlar içermeli
7. Package yapısı proje organizasyonunu yansıtmalı

## 📚 Kaynaklar

- [Java Packages - Oracle Docs](https://docs.oracle.com/javase/tutorial/java/package/packages.html)
- [Java Packages - W3Schools](https://www.w3schools.com/java/java_packages.asp)
- [Java Packages - Baeldung](https://www.baeldung.com/java-packages)
- [Java Access Modifiers - Oracle](https://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html)
- [Java Package - Programiz](https://www.programiz.com/java-programming/packages-import)  
- [Java Packages - GeeksforGeeks](https://www.geeksforgeeks.org/java-packages-in-java/)