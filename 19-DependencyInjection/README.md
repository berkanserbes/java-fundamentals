# 19-DependencyInjection

Dependency Injection - Inversion of Control ve Gevsek Baglanti

## Icerik

Bu modul Dependency Injection (DI) konusunu kapsamli bir sekilde ele almaktadir.

### Siniflar

| Sinif | Aciklama |
|-------|----------|
| `DIBasics` | Temel kavramlar, Tight vs Loose Coupling, IoC prensibi |
| `DIWithoutFramework` | Manual DI, Factory Pattern, Builder Pattern, Composition Root |
| `ConstructorInjection` | Constructor-based DI, immutability, multiple dependencies |
| `SetterInjection` | Setter-based DI, optional dependencies, circular dependency |
| `FieldInjection` | Field-based DI, reflection, neden onerilmez |
| `InterfaceInjection` | Interface-based DI, plugin sistemi, lifecycle callbacks |
| `DIContainer` | Simple DI Container implementasyonu, Singleton vs Transient |
| `ServiceLocator` | Service Locator Pattern, neden anti-pattern |
| `DIBestPractices` | SOLID prensipleri, testability, common mistakes |
| `QualifierAnnotation` | @Qualifier kullanimi, ayni interface icin farkli implementasyonlar |

## Dependency Injection Nedir?

Dependency Injection, bir sinifin ihtiyac duydugu **bagimliliklarin disaridan verilmesi** teknigidir. Bu, **Inversion of Control (IoC)** prensibinin uygulanma yollarindan biridir.

## Neden Dependency Injection Kullanilir?

1. **Loose Coupling**: Siniflar arasi gevşek baglanti
2. **Testability**: Kolay test edilebilirlik (mock/stub)
3. **Maintainability**: Kolay bakim ve degisiklik
4. **Reusability**: Kod tekrar kullanimi
5. **Separation of Concerns**: Sorumluluklarin ayrilmasi

## Injection Turleri

### 1. Constructor Injection (Tercih Edilen)
```java
public class OrderService {
    private final ILogger logger;
    private final IDatabase database;
    
    // Bagimliliklar constructor'dan verilir
    public OrderService(ILogger logger, IDatabase database) {
        this.logger = logger;
        this.database = database;
    }
}

// Kullanim
ILogger logger = new ConsoleLogger();
IDatabase db = new MySqlDatabase();
OrderService service = new OrderService(logger, db);
```

### 2. Setter Injection (Opsiyonel Bagimliliklar)
```java
public class ReportService {
    private ICache cache; // Opsiyonel
    
    // Setter ile injection
    public void setCache(ICache cache) {
        this.cache = cache;
    }
}
```

### 3. Field Injection (Framework'lar icin)
```java
public class UserService {
    @Inject  // Framework tarafindan inject edilir
    private IUserRepository repository;
}
```

### 4. Interface Injection
```java
interface IDatabaseInjector {
    void injectDatabase(IDatabase database);
}

public class Service implements IDatabaseInjector {
    private IDatabase database;
    
    @Override
    public void injectDatabase(IDatabase database) {
        this.database = database;
    }
}
```

## Injection Turleri Karsilastirmasi

| Ozellik | Constructor | Setter | Field | Interface |
|---------|-------------|--------|-------|-----------|
| Zorunlu bagimlilik | EVET | HAYIR | HAYIR | EVET |
| Immutability | EVET | HAYIR | HAYIR | HAYIR |
| Test kolayligi | YUKSEK | ORTA | DUSUK | ORTA |
| Framework gerekir | HAYIR | HAYIR | EVET | HAYIR |
| Varsayilan tercih | EVET | - | - | - |

## Inversion of Control (IoC)

```
GELENEKSEL (Kontrol bizde):
+-----------------+
|  OrderService   |
|-----------------|
| new EmailSvc()  |  <-- Sinif bagimliligini kendisi olusturur
+-----------------+

IoC (Kontrol disarida):
+-----------------+
|  IoC Container  |
|-----------------|
| Nesneleri olustur|
| Bagimliliklari   |
| inject et       |  <-- Container yonetiyor
+-----------------+
        |
        v
+-----------------+
|  OrderService   |
|-----------------|
| IEmailSvc (DI)  |  <-- Hazir olarak aliyor
+-----------------+
```

## SOLID ve Dependency Injection

### Dependency Inversion Principle (DIP)
```
KOTU:                           IYI:
OrderService                    OrderService
     |                               |
     v                               v
MySqlDatabase                   IDatabase (interface)
                                     ^
                                     |
                                MySqlDatabase
```

- Ust seviye moduller alt seviye modullere bagimli olmamali
- Her ikisi de soyutlamalara (abstractions) bagimli olmali

## Service Locator vs Dependency Injection

| Ozellik | Service Locator | Dependency Injection |
|---------|-----------------|---------------------|
| Bagimlilik gorunurlugu | Gizli | Acik |
| Test edilebilirlik | Zor | Kolay |
| Global state | EVET | HAYIR |
| Compile-time kontrol | HAYIR | EVET |
| Oneri | Anti-pattern | Tercih edilen |

## En Iyi Uygulamalar

1. **Constructor Injection varsayilan**: Zorunlu bagimliliklar icin
2. **Interface'e programlayin**: Concrete sinif yerine interface
3. **SOLID prensiplerini uygulayin**: Ozellikle DIP
4. **Service Locator'dan kacinin**: DI tercih edin
5. **Test edilebilir kod yazin**: Mock objeler kullanin

## Calistirma

```bash
# Maven ile
mvn compile exec:java -pl 19-DependencyInjection

# Batch dosyasi ile
.\run-module.bat 19-DependencyInjection DependencyInjectionDemo

# Tek bir sinifi calistirmak icin
.\run-module.bat 19-DependencyInjection DIBasics
.\run-module.bat 19-DependencyInjection ConstructorInjection
```

## Populer DI Framework'leri

| Framework | Platform | Ozellik |
|-----------|----------|---------|
| Spring Framework | Java EE | En populer, kapsamli |
| Google Guice | Java | Hafif, annotation-based |
| Dagger | Java/Android | Compile-time, performansli |
| CDI | Jakarta EE | Standart, enterprise |

## Kaynaklar

- [Martin Fowler - Inversion of Control](https://martinfowler.com/articles/injection.html)
- [Spring Framework IoC](https://docs.spring.io/spring-framework/reference/core/beans.html)
- [Dependency Injection - Wikipedia](https://en.wikipedia.org/wiki/Dependency_injection)
- [Baeldung - Intro to Inversion of Control](https://www.baeldung.com/inversion-control-and-dependency-injection-in-spring)
- [Google Guice](https://github.com/google/guice)
- [Java Dependency Injection (DI) Design Pattern - GeeksforGeeks](https://www.geeksforgeeks.org/system-design/dependency-injection-di-design-pattern/)
- [Using dependency injection in Java - Vogella](https://www.vogella.com/tutorials/DependencyInjection/article.html)
- [An Introduction to CDI (Contexts and Dependency Injection) in Java - Baeldung](https://www.baeldung.com/java-ee-cdi)
- [Java Dependency Injection - DigitalOcean](https://www.digitalocean.com/community/tutorials/java-dependency-injection-design-pattern-example-tutorial)
- [Dependency Injection Nedir Nasıl Uygulanır? Kod Örneğiyle - Medium](https://gokhana.medium.com/dependency-injection-nedir-nas%C4%B1l-uygulan%C4%B1r-kod-%C3%B6rne%C4%9Fiyle-44f4b0d576e4)
- [Dependency Injection - Jenkov](https://jenkov.com/tutorials/dependency-injection/index.html)