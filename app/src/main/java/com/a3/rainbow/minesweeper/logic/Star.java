package com.a3.rainbow.minesweeper.logic;

public class Star {
    private int numStars;

    private static Star instance;

    private Star() {
    }

    public static Star getInstance() {
        if (instance == null) {
            instance = new Star();
        }
        return new Star();
    }

    public void setNumStars(int numStars) {
        this.numStars = numStars;
    }

    public int getNumStars() {
        return this.numStars;
    }
}
