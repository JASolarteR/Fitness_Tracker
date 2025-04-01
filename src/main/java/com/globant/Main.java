package com.globant;

import com.globant.users.RegularUser;
import com.globant.users.UserRepository;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        UserRepository userRepo = new UserRepository();
        System.out.println("Enter your first name: ");
        String firstName = scanner.nextLine();
        System.out.println("Enter your last name: ");
        String lastName = scanner.nextLine();
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        boolean userRegisteredSuccessfully = userRepo.registerUser(firstName, lastName, email, password);
        if (userRegisteredSuccessfully) {
            System.out.println(userRepo.getUsers());
        }

    }
}