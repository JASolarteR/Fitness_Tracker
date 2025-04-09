package com.globant.workouts;

import java.time.LocalDate;
import java.util.*;

public class WorkoutRepository {
    private static Map<String, Workout> workouts;

    public WorkoutRepository() {
        workouts = new HashMap<>();
        loadDefaultWorkouts();
    }

    private void loadDefaultWorkouts() {
        createWorkout("Full Body Blast",
                "A comprehensive full-body workout",
                Arrays.asList(
                        new Exercise("Push Ups", 10, 3),
                        new Exercise("Squats", 15, 3),
                        new Exercise("Deadlifts", 10, 3)
                ));

        createWorkout("Cardio Circuit",
                "A high-intensity cardio workout",
                Arrays.asList(
                        new Exercise("Running", 10, 3),
                        new Exercise("Jump Rope", 100, 3),
                        new Exercise("Burpees", 15, 3)
                ));

        createWorkout("Strength Training",
                "A focused strength training workout",
                Arrays.asList(
                        new Exercise("Bench Press", 8, 4),
                        new Exercise("Deadlifts", 10, 4),
                        new Exercise("Bicep Curls", 12, 3)
                ));

    }

    public void createWorkout(String name, String description, List<Exercise> exercises) {
        Workout workout = new Workout(name, description);
        exercises.forEach(workout::addExercise);
        workouts.put(workout.getId(), workout);
    }

    public void showWorkouts() {
        int index = 1;
        for (Workout w : workouts.values()) {
            System.out.printf("%d. %s - %s \n", index++, w.getTitle(), w.getDescription());
        }
    }

    public Workout handleWorkoutSelection(Scanner scanner) {
        do {
            showWorkouts();
            System.out.println("Enter the number of the workout to see details, or type 'back' to return:");

            String workoutChoice = scanner.nextLine();
            if (workoutChoice.equalsIgnoreCase("back")) {
                return null;
            }

            try {
                int workoutIndex = Integer.parseInt(workoutChoice);
                Workout selectedWorkout = getWorkoutByIndex(workoutIndex);

                if (selectedWorkout != null) {
                    return selectedWorkout;
                } else {
                    System.out.println("Invalid selection. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number or 'back' to return.");
            }
        } while (true);
    }

    private Workout logWorkoutDetails(Scanner scanner, Workout selectedWorkout) {
        LocalDate workoutDate = LocalDate.now();
        System.out.printf("Logging '%s':%n", selectedWorkout.getTitle());
        for (Exercise e : selectedWorkout.getExercises()) {
            System.out.printf("Enter time taken for '%s' (in minutes): %n", e.getTitle());
            int timeTaken = scanner.nextInt();
            e.setTimeTaken(timeTaken);
        }
        selectedWorkout.setWorkoutDate(workoutDate);
        System.out.printf("%nWorkout '%s' logged successfully!%n", selectedWorkout.getTitle());
        System.out.printf("Total Time: %d minutes%n%n", selectedWorkout.getTimeTaken());
        return selectedWorkout;
    }

    public void selectWorkout(Scanner scanner) {
        var selectedWorkout = handleWorkoutSelection(scanner);
        if(selectedWorkout == null) return;
        selectedWorkout.viewWorkout();
        System.out.printf("%nPress 'Enter' to go back to menu");
        scanner.nextLine();
    }

    public Workout logWorkout(Scanner scanner) {
        var selectedWorkout = handleWorkoutSelection(scanner);
        if(selectedWorkout == null) return null;
        return logWorkoutDetails(scanner, selectedWorkout);
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
