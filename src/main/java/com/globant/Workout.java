package com.globant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Workout {
    private String id;
    private String title;
    private String description;
    private String type;
    private List<Exercise> exercises;
    private String notes;
    private int totalTime;
    private int calories;

    public Workout(String title, String description,
                   String type) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.type = type;
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
}
