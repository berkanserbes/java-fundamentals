package com.fundamentals.optionals;

import java.util.Optional;

/**
 * Optional Best Practices
 */
public class OptionalBestPractices {
    
    public static void demonstrate() {
        System.out.println();
        
        // ============================================================
        // 1. DOGRU KULLANIM: Return Type
        // ============================================================
        System.out.println("[V] DOGRU: Return type olarak kullan");
        System.out.println("------------------------------------------------------------");
        System.out.println("// Metod null donebilecegini acikca belirtir");
        System.out.println("public Optional<User> findUserById(int id) {");
        System.out.println("    User user = database.find(id);");
        System.out.println("    return Optional.ofNullable(user);");
        System.out.println("}");
        System.out.println();
        
        // ============================================================
        // 2. YANLIS: Parametre olarak kullanma
        // ============================================================
        System.out.println("[X] YANLIS: Parametre olarak kullanma");
        System.out.println("------------------------------------------------------------");
        System.out.println("// YANLIS!");
        System.out.println("public void processUser(Optional<User> userOpt) { ... }");
        System.out.println();
        System.out.println("// DOGRU - overload kullan");
        System.out.println("public void processUser(User user) { ... }");
        System.out.println("public void processUser() { /* default behavior */ }");
        System.out.println();
        System.out.println("Neden? Cagiran taraf null gecebilir: processUser(null)");
        System.out.println();
        
        // ============================================================
        // 3. YANLIS: Field olarak kullanma
        // ============================================================
        System.out.println("[X] YANLIS: Field olarak kullanma");
        System.out.println("------------------------------------------------------------");
        System.out.println("// YANLIS!");
        System.out.println("class User {");
        System.out.println("    private Optional<String> middleName;");
        System.out.println("}");
        System.out.println();
        System.out.println("// DOGRU - getter'da Optional don");
        System.out.println("class User {");
        System.out.println("    private String middleName; // null olabilir");
        System.out.println("    ");
        System.out.println("    public Optional<String> getMiddleName() {");
        System.out.println("        return Optional.ofNullable(middleName);");
        System.out.println("    }");
        System.out.println("}");
        System.out.println();
        System.out.println("Neden? Optional Serializable degil, bellek ek yukii");
        System.out.println();
        
        // ============================================================
        // 4. YANLIS: get() kullanma
        // ============================================================
        System.out.println("[X] YANLIS: get() kullanma");
        System.out.println("------------------------------------------------------------");
        
        Optional<String> opt = Optional.of("value");
        
        System.out.println("// YANLIS!");
        System.out.println("if (opt.isPresent()) {");
        System.out.println("    String value = opt.get();");
        System.out.println("}");
        System.out.println();
        System.out.println("// DOGRU - ifPresent kullan");
        System.out.println("opt.ifPresent(value -> System.out.println(value));");
        System.out.println();
        System.out.println("// DOGRU - orElse kullan");
        System.out.println("String value = opt.orElse(\"default\");");
        System.out.println();
        
        // ============================================================
        // 5. YANLIS: isPresent() + get() kombinasyonu
        // ============================================================
        System.out.println("[X] YANLIS: isPresent() + get() kombinasyonu");
        System.out.println("------------------------------------------------------------");
        System.out.println("// YANLIS - eski stil null kontrolu gibi!");
        System.out.println("if (userOpt.isPresent()) {");
        System.out.println("    User user = userOpt.get();");
        System.out.println("    process(user);");
        System.out.println("}");
        System.out.println();
        System.out.println("// DOGRU - fonksiyonel stil");
        System.out.println("userOpt.ifPresent(this::process);");
        System.out.println("// veya");
        System.out.println("userOpt.map(this::transform).orElse(defaultValue);");
        System.out.println();
        
        // ============================================================
        // 6. Dogru Kullanim Ornekleri
        // ============================================================
        System.out.println("[V] DOGRU KULLANIM ORNEKLERI:");
        System.out.println("------------------------------------------------------------");
        
        // Ornek 1: orElse ile varsayilan deger
        System.out.println("1. Varsayilan deger:");
        Optional<String> nameOpt = Optional.empty();
        String name = nameOpt.orElse("Misafir");
        System.out.println("   Sonuc: " + name);
        System.out.println();
        
        // Ornek 2: map ile donusum
        System.out.println("2. Donusum:");
        Optional<String> input = Optional.of("hello");
        String output = input
            .map(String::toUpperCase)
            .orElse("");
        System.out.println("   Sonuc: " + output);
        System.out.println();
        
        // Ornek 3: filter ile kosul
        System.out.println("3. Kosullu islem:");
        Optional<Integer> age = Optional.of(25);
        age.filter(a -> a >= 18)
           .ifPresent(a -> System.out.println("   Yetiskin: " + a));
        System.out.println();
        
        // Ornek 4: orElseThrow ile hata
        System.out.println("4. Hata firlatma:");
        System.out.println("   User user = findById(id)");
        System.out.println("       .orElseThrow(() -> new UserNotFoundException(id));");
        System.out.println();
        
        // ============================================================
        // 7. Anti-Patterns
        // ============================================================
        System.out.println("[!] KACININ - ANTI-PATTERNS:");
        System.out.println("------------------------------------------------------------");
        System.out.println("1. Optional.of(null)          -> NullPointerException");
        System.out.println("2. optional.get()             -> NoSuchElementException riski");
        System.out.println("3. optional == null           -> Optional'i null kontrol etme");
        System.out.println("4. new Optional()             -> Constructor private");
        System.out.println("5. Optional<Optional<T>>      -> Ic ice Optional kullanma");
        System.out.println("6. Optional<Collection>       -> Bos collection don");
    }
}
