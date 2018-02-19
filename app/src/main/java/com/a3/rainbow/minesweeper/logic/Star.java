package com.a3.rainbow.minesweeper.logic;

public class Star {
    private int numStars = 0;
    private int rowNum = 0;
    private int colNum = 0;

    private static Star instance;

    private Star() {
    }

    public static Star getInstance() {
        if (instance == null) {
            instance = new Star();
        }
        return instance;
    }

    private void setRowNum(String string) {
        int sizeints = Integer.parseInt(string.replaceAll("[\\D]", ""));
        String intStr = Integer.toString(sizeints);
        String num_rows_str = Character.toString(intStr.charAt(0));
        rowNum = Integer.parseInt(num_rows_str);
    }

    private void setColNum (String string) {
        int sizeints = Integer.parseInt(string.replaceAll("[\\D]", ""));
        String intStr = Integer.toString(sizeints);
        String num_cols_str = Character.toString(intStr.charAt(1));
        if (intStr.length() != 2) {
            num_cols_str += Character.toString(intStr.charAt(2));
        }
        colNum = Integer.parseInt(num_cols_str);
    }

    public int getRowNum(String string) {
        setRowNum(string);
        return rowNum;
    }

    public int getColNum (String string) {
        setColNum(string);
        return colNum;
    }

    public void setNumStars(int numStars) {
        this.numStars = numStars;
    }

    public int getNumStars() {
        return numStars;
    }
}
