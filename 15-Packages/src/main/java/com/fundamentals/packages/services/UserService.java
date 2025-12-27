package com.fundamentals.packages.services;

/**
 * UserService - Kullanıcı işlemleri için servis sınıfı
 * 
 * Bu sınıf 'services' package'inde bulunur.
 * İş mantığını içerir.
 */
public class UserService {

    public void createUser(String email) {
        System.out.println("UserService: Kullanıcı oluşturuldu - " + email);
    }

    public void deleteUser(String email) {
        System.out.println("UserService: Kullanıcı silindi - " + email);
    }

    public void updateUser(String email, String newEmail) {
        System.out.println("UserService: Kullanıcı güncellendi - " + email + " -> " + newEmail);
    }

    public boolean validateEmail(String email) {
        return email != null && email.contains("@");
    }
}
