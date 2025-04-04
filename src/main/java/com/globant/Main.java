package com.globant;

import com.globant.users.UserRepository;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static UserRepository userRepo = new UserRepository();
    private static WorkoutRepository workoutRepo = new WorkoutRepository();

    public static void main(String[] args) {
        int optionSelected;
        do {
            titleGenerator("Welcome to the Personal Fitness Tracker!");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.println("Please select an option: ");
            optionSelected = scanner.nextInt();
            scanner.nextLine();
            mainMenuHandler(optionSelected);
        } while (optionSelected != 3);

        scanner.close();
    }

    public static void mainMenuHandler(int optionSelected) {
        switch (optionSelected) {
            case 1:
                titleGenerator("Register");
                System.out.println("Enter your first name: ");
                String firstName = scanner.nextLine();
                System.out.println("Enter your last name: ");
                String lastName = scanner.nextLine();
                System.out.println("Enter your email: ");
                String email = scanner.nextLine();
                System.out.println("Enter your password: ");
                String password = scanner.nextLine();
                userRepo.registerUser(firstName, lastName, email, password);
                break;
            case 2:
                titleGenerator("Login");
                System.out.println("Enter your email: ");
                String loginEmail = scanner.nextLine();
                System.out.println("Enter your password: ");
                String loginPassword = scanner.nextLine();
                boolean isUserLoggedIn = userRepo.loginUser(loginEmail, loginPassword);
                if (isUserLoggedIn) {
                    regularUserMenu();
                }
                break;
            case 3:
                System.out.println("See you later!");
                break;
        }
    }

    public static void regularUserMenu() {
        int command;
        var activeUser = userRepo.getActiveUser();
        do {
            titleGenerator("Hello", activeUser.getFirstName() + " " + activeUser.getLastName());
            System.out.println("""
                    1. Show Workouts\s
                       - View the list of available workouts and see detailed structures
                    """);
            System.out.println("""
                    2. Log Workout\s
                       - Select a workout you have performed and enter the time taken in each exercise
                    """);
            System.out.println("""
                    3. View Logged Workouts\s
                       - Review your workout history, including details and total time
                    """);
            System.out.println("""
                    4. Exit application\s
                    """);
            System.out.println("Please select an option: ");
            command = scanner.nextInt();
            switch (command) {
                case 1:
                    scanner.nextLine();
                    workoutRepo.selectWorkout(scanner);
                    break;
                case 2:
                    scanner.nextLine();
                    workoutRepo.logWorkout(scanner);
                    break;
                case 3:
                    System.out.println("View logged workouts");
                    break;
            }
        } while (command != 4);

    }

    public static void titleGenerator(String title) {
        System.out.println("-----------------------");
        System.out.println(title);
        System.out.println("-----------------------");
    }

    public static void titleGenerator(String title, String userName) {
        System.out.println("-----------------------");
        System.out.printf("%s, %s %n", title, userName);
        System.out.println("-----------------------");
    }
}