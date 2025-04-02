package com.globant;

import java.util.List;

public class Workout {
    String title;
    String description;
    List<Exercise> exercises;
    String notes;
    int totalTime;
    int calories;

    public Workout(String title, String description, List exercises, String notes, int totalTime, int calories) {
        this.title = title;
        this.description = description;
        this.exercises = exercises;
        this.notes = notes;
        this.totalTime = totalTime;
        this.calories = calories;
    }
}
