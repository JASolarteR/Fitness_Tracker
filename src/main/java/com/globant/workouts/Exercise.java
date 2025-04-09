package com.globant.workouts;

public class Exercise {
    private final String title;
    private final int reps;
    private final int sets;
    private int timeTaken;

    public Exercise(String title, int reps, int sets) {
        this.title = title;
        this.reps = reps;
        this.sets = sets;
        this.timeTaken = 0;
    }

    public String getTitle() {
        return title;
    }

    public int getReps() {
        return reps;
    }

    public int getSets() {
        return sets;
    }

    public int getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(int timeTaken) {
        this.timeTaken = timeTaken;
    }
}
