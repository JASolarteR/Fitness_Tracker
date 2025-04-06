package com.globant;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Workout {
    private final String id;
    private final String title;
    private final String description;
    private List<Exercise> exercises;
    private final String notes;
    private int timeTaken;
    private LocalDate workoutDate;

    public Workout(String title, String description) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.exercises = new ArrayList<>();
        notes = "Ensure proper form and take 1 minute rest between sets";
    }

    public String getId() {
        return id;
    }

    public void addExercise(Exercise exercise){
        exercises.add(exercise);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public int getTimeTaken(){
        for (Exercise e : exercises){
            timeTaken += e.getTimeTaken();
        }
        return timeTaken;
    }

    public LocalDate getWorkoutDate() {
        return workoutDate;
    }

    public void setWorkoutDate(LocalDate workoutDate) {
        this.workoutDate = workoutDate;
    }

    public void viewWorkout(){
        System.out.printf("Workout Structure: %s%n", title);
        System.out.printf("Description: %s%n%n", description);
        System.out.println("Exercises: ");
        for (Exercise e : exercises){
            System.out.printf("- %s: %d sets of %d reps%n", e.getTitle(), e.getSets(), e.getReps());
        }
        System.out.printf("%nNotes: %s%n", notes);
    }
}
