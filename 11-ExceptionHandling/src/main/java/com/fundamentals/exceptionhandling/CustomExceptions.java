package com.fundamentals.exceptionhandling;

/**
 * Custom (Özel) Exception Sınıfları
 * 
 * ============================================================================
 * CUSTOM EXCEPTION NEDİR?
 * ============================================================================
 * 
 * Custom Exception: Kendi ihtiyaçlarımıza göre oluşturduğumuz exception
 * sınıfları.
 * Java'nın standart exception'ları yeterli olmadığında kullanılır.
 * 
 * ============================================================================
 * CUSTOM EXCEPTION OLUŞTURMA:
 * ============================================================================
 * 
 * 1. Exception veya RuntimeException'dan türet
 * 2. Constructor'lar ekle (message, cause)
 * 3. İsteğe bağlı ek alanlar ve metodlar ekle
 * 
 * Checked Exception için:
 * class MyException extends Exception { }
 * 
 * Unchecked Exception için:
 * class MyException extends RuntimeException { }
 * 
 * ============================================================================
 * NE ZAMAN KULLANILIR?
 * ============================================================================
 * 
 * ✓ İş mantığına özgü hatalar
 * ✓ Daha açıklayıcı hata mesajları
 * ✓ Hata kodları ile çalışma
 * ✓ Ek bilgi taşıma ihtiyacı
 * ✓ Hata kategorilendirme
 * 
 * ============================================================================
 */
public class CustomExceptions {

    public static void main(String[] args) {
        System.out.println("=== Custom Exceptions ===\n");

        demonstrateBasicCustomException();
        demonstrateExceptionWithErrorCode();
        demonstrateExceptionWithDetails();
        demonstrateExceptionChaining();
        demonstrateBusinessExceptions();
    }

    /**
     * 1. Temel Custom Exception
     */
    private static void demonstrateBasicCustomException() {
        System.out.println("--- 1. Temel Custom Exception ---");
        System.out.println("En basit özel exception:\n");

        BankAccount account = new BankAccount("TR123456", 1000);

        try {
            account.withdraw(500);
            System.out.println("✓ Para çekme başarılı");
            System.out.println("Kalan bakiye: " + account.getBalance());
        } catch (InsufficientBalanceException e) {
            System.out.println("❌ Hata: " + e.getMessage());
        }

        try {
            account.withdraw(1500);
        } catch (InsufficientBalanceException e) {
            System.out.println("❌ Hata: " + e.getMessage());
        }

        System.out.println();
    }

    /**
     * 2. Hata Kodu ile Custom Exception
     */
    private static void demonstrateExceptionWithErrorCode() {
        System.out.println("--- 2. Hata Kodu ile Exception ---");
        System.out.println("Exception'a hata kodu ekleme:\n");

        try {
            validateUser("", "pass123");
        } catch (ValidationException e) {
            System.out.println("❌ Validasyon Hatası:");
            System.out.println("  Kod: " + e.getErrorCode());
            System.out.println("  Mesaj: " + e.getMessage());
        }

        try {
            validateUser("user", "123");
        } catch (ValidationException e) {
            System.out.println("❌ Validasyon Hatası:");
            System.out.println("  Kod: " + e.getErrorCode());
            System.out.println("  Mesaj: " + e.getMessage());
        }

        System.out.println();
    }

    private static void validateUser(String username, String password) throws ValidationException {
        if (username == null || username.trim().isEmpty()) {
            throw new ValidationException("VAL001", "Kullanıcı adı boş olamaz");
        }
        if (password.length() < 6) {
            throw new ValidationException("VAL002", "Şifre en az 6 karakter olmalı");
        }
        System.out.println("✓ Kullanıcı validasyonu başarılı");
    }

    /**
     * 3. Detaylı Bilgi ile Custom Exception
     */
    private static void demonstrateExceptionWithDetails() {
        System.out.println("--- 3. Detaylı Bilgi ile Exception ---");
        System.out.println("Exception'a ek bilgiler ekleme:\n");

        Product product = new Product("P001", "Laptop", 50);

        try {
            product.sell(30);
            System.out.println("✓ Satış başarılı");
        } catch (OutOfStockException e) {
            System.out.println("❌ Stok Hatası:");
            System.out.println("  Ürün ID: " + e.getProductId());
            System.out.println("  Ürün Adı: " + e.getProductName());
            System.out.println("  İstenen: " + e.getRequestedQuantity());
            System.out.println("  Mevcut: " + e.getAvailableQuantity());
            System.out.println("  Mesaj: " + e.getMessage());
        }

        System.out.println();
    }

    /**
     * 4. Exception Chaining (Zincirleme)
     */
    private static void demonstrateExceptionChaining() {
        System.out.println("--- 4. Exception Chaining ---");
        System.out.println("Bir exception'ı başka bir exception'ın nedeni olarak gösterme:\n");

        try {
            processOrder("ORD001");
        } catch (OrderProcessingException e) {
            System.out.println("❌ Sipariş İşleme Hatası:");
            System.out.println("  Mesaj: " + e.getMessage());
            System.out.println("  Neden: " + e.getCause().getMessage());
            System.out.println("  Neden Türü: " + e.getCause().getClass().getSimpleName());
        }

        System.out.println();
    }

    private static void processOrder(String orderId) throws OrderProcessingException {
        try {
            // Simüle edilmiş veritabanı hatası
            connectToDatabase();
        } catch (DatabaseException e) {
            // Exception chaining - orijinal hatayı koru
            throw new OrderProcessingException(
                    "Sipariş işlenemedi: " + orderId, e);
        }
    }

    private static void connectToDatabase() throws DatabaseException {
        throw new DatabaseException("Veritabanı bağlantısı başarısız");
    }

    /**
     * 5. İş Mantığı Exception'ları
     */
    private static void demonstrateBusinessExceptions() {
        System.out.println("--- 5. İş Mantığı Exception'ları ---");
        System.out.println("Gerçek dünya senaryoları:\n");

        // Kullanıcı kaydı
        try {
            registerUser("ahmet@example.com", 15);
        } catch (UserRegistrationException e) {
            System.out.println("❌ Kayıt Hatası: " + e.getMessage());
        }

        // Ödeme işlemi
        try {
            processPayment("1234-5678-9012-3456", -100);
        } catch (PaymentException e) {
            System.out.println("❌ Ödeme Hatası: " + e.getMessage());
            System.out.println("  Hata Kodu: " + e.getErrorCode());
        }

        // Dosya yükleme
        try {
            uploadFile("document.exe", 15 * 1024 * 1024);
        } catch (FileUploadException e) {
            System.out.println("❌ Yükleme Hatası: " + e.getMessage());
            System.out.println("  Dosya: " + e.getFileName());
            System.out.println("  Boyut: " + e.getFileSize() + " bytes");
        }

        System.out.println("\nExercise completed!");
    }

    private static void registerUser(String email, int age) throws UserRegistrationException {
        if (age < 18) {
            throw new UserRegistrationException("Kullanıcı 18 yaşından küçük olamaz");
        }
        System.out.println("✓ Kullanıcı kaydedildi: " + email);
    }

    private static void processPayment(String cardNumber, double amount) throws PaymentException {
        if (amount <= 0) {
            throw new PaymentException("PAY001", "Ödeme tutarı pozitif olmalı");
        }
        System.out.println("✓ Ödeme işlendi: " + amount);
    }

    private static void uploadFile(String fileName, long fileSize) throws FileUploadException {
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (extension.equals("exe")) {
            throw new FileUploadException("Executable dosyalar yüklenemez", fileName, fileSize);
        }
        if (fileSize > 10 * 1024 * 1024) {
            throw new FileUploadException("Dosya boyutu 10MB'dan büyük olamaz", fileName, fileSize);
        }
        System.out.println("✓ Dosya yüklendi: " + fileName);
    }
}

// ============================================================================
// CUSTOM EXCEPTION SINIFLARI
// ============================================================================

/**
 * 1. Temel Custom Exception - Yetersiz Bakiye
 */
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

/**
 * Banka Hesabı Sınıfı
 */
class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException(
                    "Yetersiz bakiye! İstenen: " + amount + ", Mevcut: " + balance);
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}

/**
 * 2. Hata Kodu ile Exception - Validasyon
 */
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

/**
 * 3. Detaylı Bilgi ile Exception - Stok Hatası
 */
class OutOfStockException extends Exception {
    private final String productId;
    private final String productName;
    private final int requestedQuantity;
    private final int availableQuantity;

    public OutOfStockException(String productId, String productName,
            int requestedQuantity, int availableQuantity) {
        super("Stok yetersiz!");
        this.productId = productId;
        this.productName = productName;
        this.requestedQuantity = requestedQuantity;
        this.availableQuantity = availableQuantity;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getRequestedQuantity() {
        return requestedQuantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }
}

/**
 * Ürün Sınıfı
 */
class Product {
    private String id;
    private String name;
    private int stock;

    public Product(String id, String name, int stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
    }

    public void sell(int quantity) throws OutOfStockException {
        if (quantity > stock) {
            throw new OutOfStockException(id, name, quantity, stock);
        }
        stock -= quantity;
    }
}

/**
 * 4. Exception Chaining - Veritabanı Hatası
 */
class DatabaseException extends Exception {
    public DatabaseException(String message) {
        super(message);
    }
}

/**
 * Sipariş İşleme Hatası
 */
class OrderProcessingException extends Exception {
    public OrderProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}

/**
 * 5. İş Mantığı Exception'ları
 */
class UserRegistrationException extends Exception {
    public UserRegistrationException(String message) {
        super(message);
    }
}

class PaymentException extends Exception {
    private final String errorCode;

    public PaymentException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}

class FileUploadException extends Exception {
    private final String fileName;
    private final long fileSize;

    public FileUploadException(String message, String fileName, long fileSize) {
        super(message);
        this.fileName = fileName;
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public long getFileSize() {
        return fileSize;
    }
}
