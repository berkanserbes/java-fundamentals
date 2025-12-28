# Java Fundamentals

Java temellerini öğrenmek için kapsamlı bir çalışma alanı. Her modül bağımsız olarak çalıştırılabilir ve pratik örnekler içerir.

## 📚 Modüller

| # | Modül | Açıklama |
|---|-------|----------|
| **01** | [Variables](01-Variables) | Değişken tanımlama, kapsam, sabitler |
| **02** | [Data Types](02-DataTypes) | Primitive tipler, wrapper sınıflar, tip dönüşümü |
| **03** | [Operators](03-Operators) | Aritmetik, mantıksal, bitwise operatörler |
| **04** | [Strings](04-Strings) | String işlemleri, StringBuilder, formatlama |
| **05** | [Math Operations](05-MathOperations) | Math sınıfı, hesaplamalar |
| **06** | [Arrays](06-Arrays) | Dizi işlemleri, çok boyutlu diziler |
| **07** | [Conditionals](07-Conditionals) | if-else, switch yapıları |
| **08** | [Loops](08-Loops) | for, while, do-while döngüleri |
| **09** | [Methods](09-Methods) | Metot oluşturma, parametreler, overloading |
| **10** | [OOP](10-OOP) | Sınıflar, kalıtım, polimorfizm, kapsülleme |
| **11** | [Exception Handling](11-ExceptionHandling) | try-catch, özel exception'lar |
| **12** | [Lambda Expressions](12-LambdaExpressions) | Lambda ifadeleri, functional interface'ler |
| **13** | [Annotations](13-Annotations) | Annotation kullanımı, özel annotation'lar |
| **14** | [Collections](14-Collections) | List, Set, Map, Queue ve koleksiyon işlemleri |
| **15** | [Packages](15-Packages) | Package organizasyonu, import, erişim kontrolü |

## 🚀 Hızlı Başlangıç

### Gereksinimler
- **Java 17+** ([İndir](https://adoptium.net/))
- **Maven 3.6+** ([İndir](https://maven.apache.org/download.cgi))

### Kurulum Kontrolü
```bash
java -version
mvn -version
```

### Projeyi Derleme
```bash
cd c:\Development\java\java-fundamentals
mvn clean compile
```

### Modül Çalıştırma
```bash
# Windows
.\run-module.bat 01-Variables BasicVariables

# Linux/Mac
./run-module.sh 01-Variables BasicVariables
```

## 📖 Örnek Kullanım

```bash
# Variables modülü
.\run-module.bat 01-Variables BasicVariables
.\run-module.bat 01-Variables VariableScope
.\run-module.bat 01-Variables Constants
.\run-module.bat 01-Variables TypeInference

# Diğer modüller
.\run-module.bat 03-Operators OperatorsDemo
.\run-module.bat 07-Conditionals ConditionalsDemo
.\run-module.bat 10-OOP OOPDemo
.\run-module.bat 12-LambdaExpressions LambdaDemo
```

## 🎯 Öğrenme Yolu

### Yeni Başlayanlar
Modülleri sırayla takip edin (01 → 13). Her modül önceki konuları temel alır.

### Pratik Yapmak İsteyenler
İlgilendiğiniz modüle direkt geçebilirsiniz. Her modül bağımsız çalışır.

### Mülakat Hazırlığı
09-13 arası modüllere (OOP, Exception Handling, Lambda) odaklanın.

## 📁 Proje Yapısı

```
XX-ModulAdi/
├── README.md                    # Modül açıklaması
├── pom.xml                      # Maven yapılandırması
└── src/
    └── main/
        └── java/
            └── com/
                └── fundamentals/
                    └── moduladi/
                        ├── KonuAdi1.java
                        ├── KonuAdi2.java
                        └── ...
```

## 💡 İpuçları

1. **README'leri okuyun** - Her modülün öğrenme hedeflerini anlayın
2. **Kodu çalıştırın** - Sadece okumakla kalmayın, çalıştırın
3. **Değiştirin ve deneyin** - Kodda değişiklik yapın, hatalar yapın, düzeltin
4. **Yorumları inceleyin** - Kodlardaki açıklamalar önemli kavramları anlatır

## 🛠️ Sorun Giderme

### "Maven bulunamadı" hatası
Maven'in PATH'e eklendiğinden ve terminal'in yeniden başlatıldığından emin olun:
```bash
mvn -version
```

### "Java sürümü uyumsuz" hatası
Java 17 veya üstü kullandığınızdan emin olun:
```bash
java -version
```

### "Sınıf bulunamadı" hatası
Önce derleme yaptığınızdan emin olun:
```bash
mvn clean compile
```

## 📄 Lisans

Bu proje MIT Lisansı altında lisanslanmıştır.
