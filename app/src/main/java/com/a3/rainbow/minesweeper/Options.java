package com.a3.rainbow.minesweeper;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRadioButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Options extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        createBoardBtns();
        createSizeBtns();
    }

    //TODO: add erase button with functionality

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
                String sizeNum = numSize[i];

                RadioButton button = new RadioButton(this);
                button.setText(sizeNum + "");
                button.setTextColor(Color.WHITE);
                CompoundButtonCompat.setButtonTintList(button, colorsl);

                sizeGroup.addView(button);
            }
    }

    private void createSizeBtns() {
        RadioGroup starGroup = findViewById(R.id.star_group);
        int[] numStars = getResources().getIntArray(R.array.num_stars);



        for (int i = 0; i < numStars.length; i++) {
            int starNum = numStars[i];

            AppCompatRadioButton button = new AppCompatRadioButton(this);
            button.setText(starNum + "");
            button.setTextColor(Color.WHITE);
            CompoundButtonCompat.setButtonTintList(button, colorsl);

            starGroup.addView(button);
        }
    }
}
