package com.fundamentals.packages.services;

/**
 * EmailService - Email gönderme işlemleri için servis sınıfı
 * 
 * Bu sınıf 'services' package'inde bulunur.
 * İş mantığını içerir.
 */
public class EmailService {

    public void sendEmail(String to, String message) {
        System.out.println("EmailService: Email gönderildi");
        System.out.println("  Alıcı: " + to);
        System.out.println("  Mesaj: " + message);
    }

    public void sendBulkEmail(String[] recipients, String message) {
        System.out.println("EmailService: Toplu email gönderiliyor...");
        for (String recipient : recipients) {
            sendEmail(recipient, message);
        }
    }

    public boolean validateEmailAddress(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}
