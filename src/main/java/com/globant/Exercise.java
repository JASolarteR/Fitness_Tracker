package com.globant;

public class Exercise {
    private String title;
    private int reps;
    private int sets;
    private int timeTaken;

    public Exercise(String title, int reps, int sets) {
        this.title = title;
        this.reps = reps;
        this.sets = sets;
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
