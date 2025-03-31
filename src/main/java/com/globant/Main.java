package com.globant;

import com.globant.users.RegularUser;
import com.globant.users.UserRepository;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        UserRepository userRepo = new UserRepository();
        userRepo.registerUser(new RegularUser("Juan", "Solarte", "j.solarte@globant.com", "123456789", false));
        System.out.println(userRepo.getUsers());
        userRepo.registerUser(new RegularUser("Juan", "Solarte", "j.solarte@globant.com", "123456789", false));
        userRepo.loginUser("j.solarte@globant.com", "12345678");
    }
}