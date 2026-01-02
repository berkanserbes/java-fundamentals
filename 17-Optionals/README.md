# 17 - Java Optional

## Optional Nedir?

**Optional**, Java 8 ile birlikte gelen ve `null` degerlerini daha guvenli bir sekilde yonetmemizi saglayan bir container sinifidir. `NullPointerException` hatasini onlemek ve kodun okunabilirligini artirmak icin tasarlanmistir.

## Neden Optional Kullanmaliyiz?

### null Kullanimi Sorunlari

```java
// Tehlikeli kod - NullPointerException riski
String name = user.getAddress().getCity().getName();

// Geleneksel null kontrolu - uzun ve karmasik
if (user != null) {
    Address address = user.getAddress();
    if (address != null) {
        City city = address.getCity();
        if (city != null) {
            String name = city.getName();
        }
    }
}
```

### Optional ile Cozum

```java
// Temiz ve guvenli
String name = Optional.ofNullable(user)
    .map(User::getAddress)
    .map(Address::getCity)
    .map(City::getName)
    .orElse("Bilinmiyor");
```

## Optional Olusturma Yontemleri

| Metod | Aciklama |
|-------|----------|
| `Optional.of(value)` | null olmayan deger icin |
| `Optional.ofNullable(value)` | null olabilecek deger icin |
| `Optional.empty()` | Bos Optional |

## Temel Metodlar

### Deger Kontrolu
| Metod | Aciklama |
|-------|----------|
| `isPresent()` | Deger var mi? |
| `isEmpty()` | Bos mu? (Java 11+) |
| `ifPresent(consumer)` | Varsa islem yap |
| `ifPresentOrElse(consumer, runnable)` | Varsa/yoksa islem (Java 9+) |

### Deger Alma
| Metod | Aciklama |
|-------|----------|
| `get()` | Degeri al (tehlikeli!) |
| `orElse(default)` | Yoksa varsayilan deger |
| `orElseGet(supplier)` | Yoksa uret |
| `orElseThrow()` | Yoksa hata firlat |
| `orElseThrow(supplier)` | Ozel hata firlat |

### Donusum
| Metod | Aciklama |
|-------|----------|
| `map(function)` | Degeri donustur |
| `flatMap(function)` | Ic ice Optional'lari duzlestir |
| `filter(predicate)` | Kosul uymuyorsa bos Optional |
| `or(supplier)` | Bossa alternatif Optional (Java 9+) |

### Stream Entegrasyonu
| Metod | Aciklama |
|-------|----------|
| `stream()` | Optional'i Stream'e cevir (Java 9+) |

## Best Practices

1. **Return type olarak kullan** - Metodun null donebilecegini belirt
2. **Parametre olarak KULLANMA** - Metod parametresi olarak gecirme
3. **Field olarak KULLANMA** - Sinif alani olarak kullanma
4. **get() KULLANMA** - Yerine orElse, orElseGet, orElseThrow kullan
5. **isPresent() + get() KULLANMA** - Yerine ifPresent, map, orElse kullan

## Bu Moduldeki Ornekler

| Dosya | Aciklama |
|-------|----------|
| `OptionalsDemo.java` | Ana demo sinifi |
| `OptionalCreation.java` | Optional olusturma yontemleri |
| `OptionalMethods.java` | Temel metodlar |
| `OptionalTransformation.java` | map, flatMap, filter |
| `OptionalBestPractices.java` | Dogru ve yanlis kullanimlar |
| `OptionalRealWorld.java` | Gercek dunya ornekleri |

## Calistirma

```bash
./run-module.bat 17-Optionals OptionalsDemo
```

## Kaynaklar

- [Java Optional - Oracle Docs](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Optional.html)
- [Optional - Baeldung](https://www.baeldung.com/java-optional)
- [Optionals In Java - Coding with John](https://www.youtube.com/watch?v=vKVzRbsMnTQ)
- [Java Optional Sınıfı](https://tugrulbayrak.medium.com/java-optional-sinifi-nedir-ff5d1058be84)
- [Java 8 Optional Class - GeeksforGeeks](https://www.geeksforgeeks.org/java/java-8-optional-class/)
- [Optional in Java - Best Practices for Safer Code](https://medium.com/@alxkm/optional-in-java-best-practices-for-safer-code-3f4a3b80e122)
- [Tired of Null Pointer Exceptions? - Oracle](https://www.oracle.com/technical-resources/articles/java/java8-optional.html)
