package com.globant;

import com.globant.helpers.ValidationHelper;
import com.globant.users.AdminUser;
import com.globant.users.RegularUser;
import com.globant.users.User;
import com.globant.users.UserRepository;
import com.globant.users.exceptions.AuthFailedException;
import com.globant.users.exceptions.UserAlreadyExistsException;
import com.globant.users.exceptions.UserNotFoundException;
import com.globant.workouts.Exercise;
import com.globant.workouts.WorkoutRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class UserInterface {
    private static final UserRepository userRepo = new UserRepository();
    private static final WorkoutRepository workoutRepo = new WorkoutRepository();
    private static final Scanner scanner = new Scanner(System.in);

    public UserInterface() {
    }

    public void start(){
        int optionSelected;
        do {
            displayWelcome();
            optionSelected = ValidationHelper.validateInt("Please select an option: ");
            mainMenuHandler(optionSelected);
        } while (optionSelected != 3);
        scanner.close();
    }

    private void displayWelcome(){
        titleGenerator("Welcome to the Personal Fitness Tracker!");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
    }

    private void mainMenuHandler(int optionSelected) {
        switch (optionSelected) {
            case 1 -> userRegister();
            case 2 -> userLogin();
            case 3 -> System.out.println("See you later!");
            default -> System.out.println("Invalid option, please try again.");
        }
    }

    private void userRegister(){
        titleGenerator("Register");

        System.out.println("Enter your first name: ");
        String firstName = scanner.nextLine();
        System.out.println("Enter your last name: ");
        String lastName = scanner.nextLine();
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        try {
            userRepo.registerUser(firstName, lastName, email, password);
        } catch (UserAlreadyExistsException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private void userLogin(){
        titleGenerator("Login");
        System.out.println("Enter your email: ");
        String loginEmail = scanner.nextLine();
        System.out.println("Enter your password: ");
        String loginPassword = scanner.nextLine();
        try{
            userRepo.loginUser(loginEmail, loginPassword);
            User activeUser = userRepo.getActiveUser();
            if (activeUser != null) {
                if (activeUser.isAdmin()) {
                    adminUserMenu((AdminUser) activeUser);
                } else {
                    regularUserMenu((RegularUser) activeUser);
                }
            }
        } catch (AuthFailedException | UserNotFoundException e){
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }

    }

    private void regularUserMenu(RegularUser activeUser) {
        int command;
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
            command = ValidationHelper.validateInt("Please select an option: ");
            switch (command) {
                case 1:
                    workoutRepo.selectWorkout(scanner);
                    break;
                case 2:
                    var loggedWorkout = workoutRepo.logWorkout(scanner);
                    if (loggedWorkout != null) {
                        activeUser.logWorkout(loggedWorkout);
                    }
                    break;
                case 3:
                    scanner.nextLine();
                    activeUser.handleWorkoutDetailSelection(scanner);
                    break;

                case 4:
                    userRepo.logout();
                    System.out.println("Logout successfully!");
                    return;

                default:
                    System.out.println("Select a valid option");
            }
        } while (true);

    }

    private void adminUserMenu(AdminUser activeUser){
        int command;
        do {
            titleGenerator("Hello", activeUser.getFirstName() + " " + activeUser.getLastName());
            System.out.println("""
                    1. Show Workouts\s
                       - View the list of available workouts and see detailed structures
                    """);
            System.out.println("""
                    2. Add Workout\s
                       - Add a workout to the database
                    """);
            System.out.println("""
                    3. Exit application\s
                    """);
            command = ValidationHelper.validateInt("Please select an option: ");
            switch (command) {
                case 1:
                    workoutRepo.selectWorkout(scanner);
                    break;
                case 2:
                    titleGenerator("Add new workout");
                    System.out.println("Enter Workout Name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter Workout Description:");
                    String description = scanner.nextLine();
                    List<Exercise> exercises = new ArrayList<>();
                    int exercisesNum = ValidationHelper.validateInt("Number of exercises:");
                    for (int i = 0; i < exercisesNum; i++) {
                        titleGenerator("Exercise #" + (i+1));
                        System.out.println("Enter Exercise Name:");
                        String exerciseName = scanner.nextLine();
                        int reps = ValidationHelper.validateInt("Enter Exercise Reps:");
                        int sets = ValidationHelper.validateInt("Enter Exercise Sets:");
                        exercises.add(new Exercise(exerciseName, reps, sets));
                    }
                    workoutRepo.createWorkout(name, description, exercises);
                    System.out.println("Workout added successfully!");
                    break;
                case 3:
                    userRepo.logout();
                    System.out.println("Logout successfully!");
                    return;

                default:
                    System.out.println("Select a valid option");
                    return;
            }
        } while (true);
    }

    private void titleGenerator(String title) {
        System.out.println("-----------------------");
        System.out.println(title);
        System.out.println("-----------------------");
    }

    private void titleGenerator(String title, String userName) {
        System.out.println("-----------------------");
        System.out.printf("%s, %s %n", title, userName);
        System.out.println("-----------------------");
    }

}
