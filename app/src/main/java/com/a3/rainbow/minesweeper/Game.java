package com.a3.rainbow.minesweeper;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.a3.rainbow.minesweeper.logic.Star;

import java.util.Locale;

public class Game extends AppCompatActivity {
    private Star starfield;
    private int num_rows = 0;
    private int num_cols = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        starfield = Star.getInstance();

        int sizeints = Integer.parseInt(getBoardSize(this).replaceAll("[\\D]", ""));
        String intStr = Integer.toString(sizeints);
        String num_rows_str = Character.toString(intStr.charAt(0));
        num_rows = Integer.parseInt(num_rows_str);

        String num_cols_str = Character.toString(intStr.charAt(1));
        if (intStr.length() != 2) {
            num_cols_str += Character.toString(intStr.charAt(2));
        }
        num_cols = Integer.parseInt(num_cols_str);

        Button buttons [][] = newButtons();

        populateButtons(buttons);
      //  updateUI();
    }

    public Button [][] newButtons() {
        return new Button[num_cols][num_rows];
    }

    public void setButton(Button[][] buttons, Button button, int cols, int rows){
        buttons[cols][rows] = button;
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
    */

    private void populateButtons(final Button[][] buttons) {
        TableLayout table  = findViewById(R.id.starfield);

        for (int row = 0; row < num_rows; row++) {
            TableRow trow = new TableRow(this);
            trow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            ));
            table.addView(trow);

            for (int col = 0; col < num_cols; col++) {
                final int row_final = row;
                final int col_final = col;

                final Button button = new Button(this);
                button.setBackgroundResource(R.drawable.btnbg);
                button.setTextColor(R.color.white);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f
                ));

                setButton(buttons, button, col_final, row_final);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gridButtonClicked(col_final, row_final, buttons);
                    }
                });

                trow.addView(button);
            }
        }
    }

    private void gridButtonClicked(int row, int col, Button [][] buttons) {
        Button button = buttons[row][col];
        button.setText("X");
        button.setBackgroundResource(R.drawable.btnbgclicked);
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
