package com.globant.users;

public class AdminUser extends User{
    public AdminUser(String firstName, String lastName, String email, String password, boolean isAdmin) {
        super(firstName, lastName, email, password, isAdmin);
    }

    public void createWorkout(){}
}
