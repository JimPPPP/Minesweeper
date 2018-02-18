package com.a3.rainbow.minesweeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.a3.rainbow.minesweeper.logic.Star;

import java.util.Locale;

public class Game extends AppCompatActivity {

    private static final int num_rows = 0;

    private Star starfield;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        starfield = Star.getInstance();



       // populateButtons();
      //  updateUI();
    }

//    private int starNum = starfield.getNumStars();
/*
    private void updateUI() {
        TextView stars = findViewById(R.id.star_num);
        TextView scans = findViewById(R.id.scan_num);

        String starMsg = String.format(Locale.getDefault(), "%d of %d stars found.", found, starNum);
        stars.setText(starMsg);

        String scanMsg = String.format(Locale.getDefault(), "%d scans used.", scanNum);
        scans.setText(scanMsg);
    }

    private void populateButtons() {
        for (int row = 0; row < num_rows; row++) {

        }
    }
    */
}
