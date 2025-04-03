package com.globant;

import java.util.HashMap;
import java.util.Map;

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

}
