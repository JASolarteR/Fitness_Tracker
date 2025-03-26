package com.globant.users;

public class RegularUser extends User{
    public RegularUser(String firstName, String lastName, String email, String password, boolean isAdmin) {
        super(firstName, lastName, email, password, isAdmin);
    }

    public void register(){}
    public void logWorkout(){}
    public void viewLoggedWorkouts(){}
}
