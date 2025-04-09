package com.globant.users;

public class AdminUser extends User{
    public AdminUser(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password, UserRole.ADMIN);
    }
}
