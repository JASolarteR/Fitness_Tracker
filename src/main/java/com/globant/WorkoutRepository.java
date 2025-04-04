package com.globant;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WorkoutRepository {
    private Map<String, Workout> workouts;

    public WorkoutRepository() {
        workouts = new HashMap<>();
        loadDefaultWorkouts();
    }

    private void loadDefaultWorkouts() {
        createWorkout("Full Body Blast",
                "A comprehensive full-body workout",
                "Strength",
                new Exercise[]{
                        new Exercise("Push Ups", 10, 3),
                        new Exercise("Squats", 15, 3),
                        new Exercise("Deadlifts", 10, 3)
                });

        createWorkout("Cardio Circuit",
                "A high-intensity cardio workout",
                "Cardio",
                new Exercise[]{
                        new Exercise("Running", 10, 3),
                        new Exercise("Jump Rope", 100, 3),
                        new Exercise("Burpees", 15, 3)
                });

        createWorkout("Strength Training",
                "A focused strength training workout",
                "Strength",
                new Exercise[]{
                        new Exercise("Bench Press", 8, 4),
                        new Exercise("Deadlifts", 10, 4),
                        new Exercise("Bicep Curls", 12, 3)
                });

    }

    private void createWorkout(String name, String description, String type, Exercise[] exercises) {
        Workout workout = new Workout(name, description, type);
        for (Exercise exercise : exercises) {
            workout.addExercise(exercise);
        }
        addWorkout(workout);
    }

    public boolean addWorkout(Workout workout) {
        if (workouts.containsKey(workout.getId())) {
            System.out.println("Workout already exists!");
            return false;
        }
        workouts.put(workout.getId(), workout);
        return true;
    }

    public void showWorkouts() {
        int index = 1;
        for (Workout w : workouts.values()) {
            System.out.printf("%d. %s - %s \n", index++, w.getTitle(), w.getDescription());
        }
    }

    public void selectWorkout(Scanner scanner) {
        do {
            showWorkouts();
            System.out.println("Enter the number of the workout to see details, or type 'back' to return:");

            String workoutChoice = scanner.nextLine();
            if (workoutChoice.equalsIgnoreCase("back")) {
                return;
            }
            try {
                int workoutIndex = Integer.parseInt(workoutChoice);
                Workout selectedWorkout = getWorkoutByIndex(workoutIndex);

                if (selectedWorkout != null) {
                    selectedWorkout.viewWorkout();
                    System.out.printf("%nPress 'Enter' to go back to menu");
                    scanner.nextLine();
                } else {
                    System.out.println("Invalid selection. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number or 'back' to return.");
            }
        } while (true);

    }

    private Workout getWorkoutByIndex(int index) {
        if (index < 1 || index > workouts.size()) {
            return null;
        }

        int currentIndex = 1;
        for (Workout w : workouts.values()) {
            if (currentIndex == index) {
                return w;
            }
            currentIndex++;
        }
        return null;
    }

}
