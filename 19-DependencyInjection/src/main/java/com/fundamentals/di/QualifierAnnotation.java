package com.fundamentals.di;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @Qualifier ANNOTATION KULLANIMI
 * 
 *            Ayni interface'i implement eden birden fazla sinif oldugunda,
 *            hangisinin inject edilecegini belirtmek icin @Qualifier
 *            kullanilir.
 */
public class QualifierAnnotation {

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("@QUALIFIER ANNOTATION KULLANIMI");
        System.out.println("=".repeat(60));

        problemWithoutQualifier();
        solutionWithQualifier();
        qualifierWithContainer();
        namedQualifierExample();
    }

    // ==========================================
    // 1. QUALIFIER OLMADAN PROBLEM
    // ==========================================

    static void problemWithoutQualifier() {
        System.out.println("\n1. QUALIFIER OLMADAN PROBLEM");
        System.out.println("-".repeat(40));

        System.out.println("""
                Ayni interface'i implement eden birden fazla sinif:

                interface IPaymentGateway { }

                class StripePayment implements IPaymentGateway { }
                class PayPalPayment implements IPaymentGateway { }
                class IyzicoPayment implements IPaymentGateway { }

                Container hangisini inject edecek? BELIRSIZ!
                """);
    }

    // ==========================================
    // 2. @QUALIFIER ILE COZUM
    // ==========================================

    static void solutionWithQualifier() {
        System.out.println("\n2. @QUALIFIER ILE COZUM");
        System.out.println("-".repeat(40));

        // Manuel qualifier kullanimi
        IPaymentGateway stripe = new StripePayment();
        IPaymentGateway paypal = new PayPalPayment();

        // Stripe ile odeme
        PaymentService stripeService = new PaymentService(stripe);
        stripeService.pay(100);

        // PayPal ile odeme
        PaymentService paypalService = new PaymentService(paypal);
        paypalService.pay(200);
    }

    // ==========================================
    // 3. CONTAINER ILE @QUALIFIER
    // ==========================================

    static void qualifierWithContainer() {
        System.out.println("\n3. CONTAINER ILE @QUALIFIER");
        System.out.println("-".repeat(40));

        // Container'a tum implementasyonlari kaydet
        QualifierContainer container = new QualifierContainer();
        container.register(IPaymentGateway.class, "stripe", new StripePayment());
        container.register(IPaymentGateway.class, "paypal", new PayPalPayment());
        container.register(IPaymentGateway.class, "iyzico", new IyzicoPayment());

        // @Qualifier("stripe") ile istenen alinir
        CheckoutService checkoutService = container.createWithQualifiers(CheckoutService.class);
        checkoutService.checkout(150);
    }

    // ==========================================
    // 4. @NAMED QUALIFIER (JSR-330)
    // ==========================================

    static void namedQualifierExample() {
        System.out.println("\n4. @NAMED QUALIFIER (JSR-330)");
        System.out.println("-".repeat(40));

        System.out.println("""
                Spring/Jakarta EE'de:

                @Autowired
                @Qualifier("stripe")
                private IPaymentGateway paymentGateway;

                veya

                @Inject
                @Named("stripe")
                private IPaymentGateway paymentGateway;

                Bean tanimi:
                @Component("stripe")
                class StripePayment implements IPaymentGateway { }
                """);

        // Simulasyon
        QualifierContainer container = new QualifierContainer();
        container.register(INotificationService.class, "email", new EmailNotification());
        container.register(INotificationService.class, "sms", new SmsNotification());
        container.register(INotificationService.class, "push", new PushNotification());

        // Farkli qualifier'lar ile farkli implementasyonlar
        INotificationService email = container.resolve(INotificationService.class, "email");
        INotificationService sms = container.resolve(INotificationService.class, "sms");
        INotificationService push = container.resolve(INotificationService.class, "push");

        email.send("Email mesaji");
        sms.send("SMS mesaji");
        push.send("Push bildirimi");
    }

    // ==========================================
    // CUSTOM @QUALIFIER ANNOTATION
    // ==========================================

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface Qualifier {
        String value();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface Inject {
    }

    // ==========================================
    // INTERFACE VE IMPLEMENTASYONLAR
    // ==========================================

    interface IPaymentGateway {
        void processPayment(double amount);
    }

    static class StripePayment implements IPaymentGateway {
        @Override
        public void processPayment(double amount) {
            System.out.println("   [Stripe] Odeme: $" + amount);
        }
    }

    static class PayPalPayment implements IPaymentGateway {
        @Override
        public void processPayment(double amount) {
            System.out.println("   [PayPal] Odeme: $" + amount);
        }
    }

    static class IyzicoPayment implements IPaymentGateway {
        @Override
        public void processPayment(double amount) {
            System.out.println("   [Iyzico] Odeme: " + amount + " TL");
        }
    }

    interface INotificationService {
        void send(String message);
    }

    static class EmailNotification implements INotificationService {
        @Override
        public void send(String message) {
            System.out.println("   [Email] " + message);
        }
    }

    static class SmsNotification implements INotificationService {
        @Override
        public void send(String message) {
            System.out.println("   [SMS] " + message);
        }
    }

    static class PushNotification implements INotificationService {
        @Override
        public void send(String message) {
            System.out.println("   [Push] " + message);
        }
    }

    // ==========================================
    // SERVISLER
    // ==========================================

    static class PaymentService {
        private final IPaymentGateway gateway;

        public PaymentService(IPaymentGateway gateway) {
            this.gateway = gateway;
        }

        public void pay(double amount) {
            gateway.processPayment(amount);
        }
    }

    // @Qualifier kullanilan servis
    static class CheckoutService {
        @Inject
        @Qualifier("stripe")
        private IPaymentGateway paymentGateway;

        public void checkout(double amount) {
            if (paymentGateway != null) {
                System.out.println("   Checkout baslatildi...");
                paymentGateway.processPayment(amount);
            }
        }
    }

    // ==========================================
    // QUALIFIER DESTEKLI CONTAINER
    // ==========================================

    static class QualifierContainer {
        private final Map<String, Object> registry = new HashMap<>();

        public <T> void register(Class<T> type, String qualifier, T instance) {
            String key = type.getName() + "#" + qualifier;
            registry.put(key, instance);
        }

        @SuppressWarnings("unchecked")
        public <T> T resolve(Class<T> type, String qualifier) {
            String key = type.getName() + "#" + qualifier;
            return (T) registry.get(key);
        }

        @SuppressWarnings("unchecked")
        public <T> T createWithQualifiers(Class<T> clazz) {
            try {
                T instance = clazz.getDeclaredConstructor().newInstance();

                for (Field field : clazz.getDeclaredFields()) {
                    if (field.isAnnotationPresent(Inject.class) &&
                            field.isAnnotationPresent(Qualifier.class)) {

                        Qualifier q = field.getAnnotation(Qualifier.class);
                        Object dependency = resolve(field.getType(), q.value());

                        if (dependency != null) {
                            field.setAccessible(true);
                            field.set(instance, dependency);
                        }
                    }
                }
                return instance;
            } catch (Exception e) {
                throw new RuntimeException("Injection hatasi", e);
            }
        }
    }
}
