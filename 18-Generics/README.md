# 18-Generics

Java Generics - Tip Guvenligi ve Kod Tekrar Kullanimi

## Icerik

Bu modul Java Generics konusunu kapsamli bir sekilde ele almaktadir.

### Siniflar

| Sinif | Aciklama |
|-------|----------|
| `GenericBasics` | Temel kavramlar, neden kullanilir, diamond operator |
| `GenericClasses` | Generic sinif tanimlama, Container, Stack, Repository ornekleri |
| `GenericMethods` | Generic metod tanimlama, tip cikarimi, coklu tip parametreleri |
| `GenericInterfaces` | Generic arayuzler, Comparable, Comparator, functional interfaces |
| `BoundedTypeParameters` | Upper bound (extends), multiple bounds, recursive bounds |
| `Wildcards` | Unbounded, upper/lower bounded wildcards, PECS kurali |
| `TypeErasure` | Tip silme mekanizmasi, kisitlamalar, bridge methods |
| `GenericBestPractices` | En iyi uygulamalar, type tokens, generic singleton |

## Generics Nedir?

Generics, Java 5 ile tanitilan ve **tip guvenligini (type safety)** saglayan bir mekanizmadir. Kod yazarken tip parametreleri kullanarak, derleme zamaninda tip kontrolu yapilmasini saglar.

## Neden Generics Kullanilir?

1. **Tip Guvenligi**: Derleme zamaninda tip hatalari yakalanir
2. **Cast Isleminden Kurtulma**: Explicit casting'e gerek kalmaz
3. **Kod Tekrar Kullanimi**: Ayni kod farkli tiplerle kullanilabilir
4. **Generic Algoritmalar**: Tip bagimsiz algoritmalar yazilabilir

## Tip Parametresi Konvansiyonlari

| Parametre | Anlami | Kullanim Alani |
|-----------|--------|----------------|
| `E` | Element | Collection siniflari |
| `K` | Key | Map anahtari |
| `V` | Value | Map degeri |
| `T` | Type | Genel tip parametresi |
| `N` | Number | Sayi tipleri |
| `S, U` | - | Ek tip parametreleri |

## Temel Kullanim

### Generic Sinif
```java
public class Box<T> {
    private T content;
    
    public void put(T item) {
        this.content = item;
    }
    
    public T get() {
        return content;
    }
}

// Kullanim
Box<String> stringBox = new Box<>();
stringBox.put("Hello");
String value = stringBox.get();  // Cast gerekmez
```

### Generic Metod
```java
public static <T> T getFirst(List<T> list) {
    return list.isEmpty() ? null : list.get(0);
}

// Kullanim
List<String> names = Arrays.asList("Ali", "Veli");
String first = getFirst(names);  // Tip cikarimi otomatik
```

### Bounded Type Parameters
```java
// Upper bound - Number veya alt siniflari
public static <T extends Number> double sum(T[] numbers) {
    double total = 0;
    for (T num : numbers) {
        total += num.doubleValue();
    }
    return total;
}

// Multiple bounds
public static <T extends Number & Comparable<T>> T max(T a, T b) {
    return a.compareTo(b) > 0 ? a : b;
}
```

## Wildcards

### Unbounded Wildcard
```java
public void printList(List<?> list) {
    for (Object item : list) {
        System.out.println(item);
    }
}
```

### Upper Bounded Wildcard
```java
// Number veya alt siniflarini okuyabilir
public double sumOfList(List<? extends Number> list) {
    double sum = 0;
    for (Number n : list) {
        sum += n.doubleValue();
    }
    return sum;
}
```

### Lower Bounded Wildcard
```java
// Integer veya ust siniflarini yazabilir
public void addNumbers(List<? super Integer> list) {
    list.add(1);
    list.add(2);
}
```

## PECS Kurali

**Producer Extends, Consumer Super**

- **OKUMA (Producer)**: `<? extends T>` kullan
- **YAZMA (Consumer)**: `<? super T>` kullan

```java
public static <T> void copy(
    List<? extends T> source,    // Okuma - extends
    List<? super T> destination  // Yazma - super
) {
    for (T item : source) {
        destination.add(item);
    }
}
```

## Type Erasure

Java'da generics **derleme zamani** ozelligidir. Runtime'da tip bilgisi silinir:

- `<T>` -> `Object`
- `<T extends Number>` -> `Number`

### Kisitlamalar

1. `instanceof` generic tiplerle kullanilamaz
2. Generic tip dizisi olusturulamaz (`new T[]`)
3. `new T()` kullanilamaz
4. Primitive tipler kullanilamaz

## En Iyi Uygulamalar

1. **Raw type kullanmayin** - Her zaman parameterized type kullanin
2. **Array yerine List tercih edin** - Daha guvenli
3. **PECS kuralina uyun** - Dogru wildcard secimi
4. **Type token kullanin** - Runtime tip guvenligi icin
5. **@SuppressWarnings dikkatli kullanin** - Sadece gerektiginde

## Calistirma

```bash
# Maven ile
mvn compile exec:java -pl 18-Generics

# Batch dosyasi ile
.\run-module.bat 18-Generics GenericsDemo
```

## Kaynaklar

- [Oracle Java Generics Tutorial](https://docs.oracle.com/javase/tutorial/java/generics/)
- [Generics - dev.java](https://dev.java/learn/generics/)
- [Java Generics Tutorial](https://jenkov.com/tutorials/java-generics/index.html)
- [Java Generics - W3Schools](https://www.w3schools.com/java/java_generics.asp)
- [Java'da Generics](https://medium.com/pera-soft/java-generics-7948a2da58d3)
- [Generic Class in Java - GeeksforGeeks](https://www.geeksforgeeks.org/java/generic-class-in-java/)
- [Java Generics - Programiz](https://www.programiz.com/java-programming/generics)
- [Generics in Java - GeeksforGeeks](https://www.geeksforgeeks.org/java/generics-in-java/)
- [The Basics of Java Generics - Baeldung](https://www.baeldung.com/java-generics)
- [Generics in Java - Coding with John](https://www.youtube.com/watch?v=K1iu1kXkVoA)