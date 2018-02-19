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
import java.util.Random;

public class Game extends AppCompatActivity {
    private Star starfield;
    private int num_rows = 0;
    private int num_cols = 0;
    private int starNum = 0;
    private int scanNum = 0;
    private int found = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        starfield = Star.getInstance();
        num_rows = starfield.getRowNum(getBoardSize(this));
        num_cols = starfield.getColNum(getBoardSize(this));

        Button buttons [][] = newButtons();

        populateButtons(buttons);

        starNum = starfield.getNumStars();

        updateUI();
        setStars(buttons);
    }

    private void setStars(Button[][] buttons) {
        Random random = new Random();
        int rand_col, rand_row;

        for (int count = 0; count < starNum; count++) {
            rand_row = random.nextInt(num_rows);
            rand_col = random.nextInt(num_cols);

            if(buttons[rand_col][rand_row].getText() != " "){
                setStarText(buttons, rand_col, rand_row);
            }
            else {
                count--;
            }
        }
    }

    private void setStarText(Button[][] buttons, int col, int row) {
        buttons[col][row].setText(" ");
    }

    public Button [][] newButtons() {
        return new Button[num_cols][num_rows];
    }

    public void setButton(Button[][] buttons, Button button, int cols, int rows){
        buttons[cols][rows] = button;
    }


    private void updateUI() {
        TextView stars = findViewById(R.id.star_num);
        TextView scans = findViewById(R.id.scan_num);

        String starMsg = String.format(Locale.getDefault(), "%d of %d stars found.", found, starNum);
        stars.setText(starMsg);

        String scanMsg = String.format(Locale.getDefault(), "%d scans used.", scanNum);
        scans.setText(scanMsg);
    }


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
        if (button.getText() == " "){
            button.setBackgroundResource(android.R.drawable.star_off);
            found++;
            button.setText("*");
        }
        else if (button.getText() == "X")
        {
            updateUI();
        }
        else {
            if (button.getText() != "*") {
                button.setBackgroundResource(R.drawable.btnbgclicked);
            }
            button.setText("X");
            scanNum++;
        }

        updateUI();
    }


    private String getBoardSize(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        return prefs.getString("Board size", "");
    }
}
