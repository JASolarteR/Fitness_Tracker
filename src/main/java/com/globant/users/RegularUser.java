package com.globant.users;

import com.globant.Exercise;
import com.globant.Workout;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegularUser extends User {
    private List<Workout> loggedWorkouts;

    public RegularUser(String firstName, String lastName, String email, String password, boolean isAdmin) {
        super(firstName, lastName, email, password, isAdmin);
        loggedWorkouts = new ArrayList<>();
    }

    public void logWorkout(Workout workout) {
        //TODO: Add error handling
        loggedWorkouts.add(workout);
    }

    public void viewLoggedWorkouts(Scanner scanner) {
        do {
            System.out.println("Workout History (Sorted by Date Descending):");
            //TODO: Sort by date descending
            for (Workout w : loggedWorkouts) {
                System.out.printf("%d. Date: %s%n", loggedWorkouts.indexOf(w) + 1, w.getWorkoutDate());
                System.out.printf("Workout: %s%n", w.getTitle());
            }

            System.out.println("Enter the number of the workout to see details, or type 'back' to return:");

            String workoutChoice = scanner.nextLine();
            if (workoutChoice.equalsIgnoreCase("back")) {
                return;
            }

            try {
                int workoutIndex = Integer.parseInt(workoutChoice) - 1;
                Workout selectedWorkout = loggedWorkouts.get(workoutIndex);

                if (selectedWorkout != null) {
                    showWorkout(selectedWorkout);
                    System.out.println("Press 'ENTER' to go back");
                    scanner.nextLine();
                } else {
                    System.out.println("Invalid selection. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number or 'back' to return.");
            }
        } while (true);

    }

    public void showWorkout(Workout loggedWorkout) {
        System.out.printf("%nWorkout Details for '%s' on %s:%n", loggedWorkout.getTitle(), loggedWorkout.getWorkoutDate());
        var workoutExercises = loggedWorkout.getExercises();
        for (Exercise e : workoutExercises) {
            System.out.printf("- %s: %d minutes%n", e.getTitle(), e.getTimeTaken());
        }
        System.out.printf("Total Time: %d minutes%n%n", loggedWorkout.getTimeTaken());
    }
}
