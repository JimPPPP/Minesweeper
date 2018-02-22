package com.a3.rainbow.minesweeper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.a3.rainbow.minesweeper.logic.Star;

import java.util.Locale;
import java.util.Objects;

public class Options extends AppCompatActivity {
    private Star starfield;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        createBoardBtns();
        createSizeBtns();

        starfield = Star.getInstance();
        int savedStarNum = getStarNum(this);
        starfield.setNumStars(savedStarNum);
    }


    ColorStateList colorsl = new ColorStateList(
            new int[][]{
                    new int[]{android.R.attr.state_enabled}
            },
            new int[]{
                    Color.WHITE
            }
    );

    private void createBoardBtns() {
        RadioGroup sizeGroup = findViewById(R.id.board_group);
        String[] numSize = getResources().getStringArray(R.array.board_size);

        for (int i = 0; i < numSize.length; i++) {
            final String sizeNum = numSize[i];

            RadioButton button = new RadioButton(this);
            button.setText(sizeNum + "");
            button.setTextColor(Color.WHITE);
            CompoundButtonCompat.setButtonTintList(button, colorsl);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveSize(sizeNum);
                }
            });

            sizeGroup.addView(button);

            if (Objects.equals(sizeNum, getBoardSize(this))) {
                button.setChecked(true);
            }
        }
    }

    private void createSizeBtns() {
        RadioGroup starGroup = findViewById(R.id.star_group);
        int[] numStars = getResources().getIntArray(R.array.num_stars);

        for (int i = 0; i < numStars.length; i++) {
            final int starNum = numStars[i];

            AppCompatRadioButton button = new AppCompatRadioButton(this);
            button.setText(starNum + "");
            button.setTextColor(Color.WHITE);
            CompoundButtonCompat.setButtonTintList(button, colorsl);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveStars(starNum);
                }
            });

            starGroup.addView(button);

            if (starNum == getStarNum(this)) {
                button.setChecked(true);
            }
        }
    }

    private void saveSize(String sizeNum) {
        SharedPreferences prefs = this.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("Board size", sizeNum);
        editor.apply();
    }

    private void saveStars(int starNum) {
        SharedPreferences prefs = this.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("Number of stars", starNum);
        editor.apply();
    }

    private String getBoardSize(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        return prefs.getString("Board size", "");
    }

    static public int getStarNum(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        return prefs.getInt("Number of stars", 0);
    }
}
