package com.globant.users;

import com.globant.workouts.Exercise;
import com.globant.workouts.Workout;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class RegularUser extends User {
    private List<Workout> loggedWorkouts;

    public RegularUser(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password, UserRole.REGULAR);
        loggedWorkouts = new ArrayList<>();
    }

    public void logWorkout(Workout workout) {
        if (workout == null) {
            System.out.println("Invalid workout!");
            return;
        }
        loggedWorkouts.add(workout);
    }

    private void viewLoggedWorkouts() {
        if (loggedWorkouts.isEmpty()) {
            System.out.println("No logged workouts found.");
            return;
        }

        loggedWorkouts.sort(Comparator.comparing(Workout::getWorkoutDate).reversed());
        System.out.println("Workout History (Sorted by Date Descending):");
        for (int i = 0; i < loggedWorkouts.size(); i++) {
            Workout w = loggedWorkouts.get(i);
            System.out.printf("%d. Date: %s - Workout: %s%n", i + 1, w.getWorkoutDate(), w.getTitle());
        }

    }

    public void handleWorkoutDetailSelection(Scanner scanner) {
        while (true) {
            viewLoggedWorkouts();
            System.out.println("Enter the number of the workout to see details, or type 'back' to return:");
            String workoutChoice = scanner.nextLine();
            if (workoutChoice.equalsIgnoreCase("back")) {
                return;
            }
            try {
                int workoutIndex = Integer.parseInt(workoutChoice) - 1;
                showWorkout(loggedWorkouts.get(workoutIndex));
                System.out.println("Press 'ENTER' to go back");
                scanner.nextLine();
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Invalid selection. Please try again.");
            }
        }
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
