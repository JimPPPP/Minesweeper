package com.a3.rainbow.minesweeper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

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
        String[][] values = new String[num_rows][num_cols];

        populateButtons(buttons, values);

        starNum = starfield.getNumStars();

        setStars(buttons);
        updateUI(buttons, values);
    }

    private void setStars(Button[][] buttons) {
        Random random = new Random();
        int rand_col, rand_row;

        for (int count = 0; count < starNum; count++) {
            rand_row = random.nextInt(num_rows);
            rand_col = random.nextInt(num_cols);

            if(buttons[rand_row][rand_col].getText() != " "){
                buttons[rand_row][rand_col].setText(" ");
            }
            else {
                count--;
            }
        }
    }

    public Button [][] newButtons() {
        return new Button[num_rows][num_cols];
    }

    public void setButton(Button[][] buttons, Button button, int cols, int rows){
        buttons[rows][cols] = button;
    }


    private void updateUI(Button[][] buttons, String[][] values) {
        TextView stars = findViewById(R.id.star_num);
        TextView scans = findViewById(R.id.scan_num);

        String starMsg = String.format(Locale.getDefault(), "%d of %d stars found.", found, starNum);
        stars.setText(starMsg);

        String scanMsg = String.format(Locale.getDefault(), "%d scans used.", scanNum);
        scans.setText(scanMsg);

        updateButtonText(buttons, values);
    }

    private void updateButtonText(Button[][] buttons, String[][] values) {
        scanner(buttons, values);

        for (int rows = 0; rows < num_rows; rows++) {
            for (int cols = 0; cols < num_cols; cols++) {
                String text = buttons[rows][cols].getText().toString();
                if (text.matches("[0-9]+")) {
                    buttons[rows][cols].setText(values[rows][cols]);
                }
            }
        }
    }

    private void populateButtons(final Button[][] buttons, final String[][] values) {
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
                button.setTextColor(getResources().getColor(R.color.white));
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f
                ));

                setButton(buttons, button, col_final, row_final);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gridButtonClicked(col_final, row_final, buttons, values);
                    }
                });
                trow.addView(button);
            }
        }
    }

    private void scanner(Button[][] buttons, String[][] values) {
        int curr_row = 0;
        int curr_col = 0;
        int[][] temp = new int[num_rows][num_cols];
        int count = 0;

        while (curr_row < num_rows) {
            while (curr_col < num_cols) {
                for (int col = 0; col < num_cols; col++) {
                    if (buttons[curr_row][col].getText() == " ") {
                        count++;
                    }
                }
                for (int row = 0; row < num_rows; row++) {
                    if (buttons[row][curr_col].getText() == " ") {
                        count++;
                    }
                }
                temp[curr_row][curr_col] = count;
                values[curr_row][curr_col] = Integer.toString(temp[curr_row][curr_col]);
                count = 0;
                curr_col++;
            }
            curr_row++;
            curr_col = 0;
            count = 0;
        }
    }


    private void gridButtonClicked(int row, int col, Button [][] buttons, String[][] values) {
        Button button = buttons[col][row];
        String text = button.getText().toString();

        if (text == " "){
            button.setBackgroundResource(android.R.drawable.star_off);
            button.setText("*");
            found++;
            MediaPlayer player = MediaPlayer.create(this, Settings.System.DEFAULT_NOTIFICATION_URI);
            player.start();

            if (found == starNum) {
                congratulate();
            }
        }
        else if (text.matches("[0-9]+"))
        {
            updateButtonText(buttons, values);
        }
        else {
            if (text != "*") {
                button.setBackgroundResource(R.drawable.btnbgclicked);
            }
            CharSequence cs = values[col][row];
            button.setText(cs);
            scanNum++;
        }

        updateUI(buttons, values);
    }

    private void congratulate() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle(R.string.congratulations);

        alertDialogBuilder.setMessage(R.string.congratulations_text)
                .setCancelable(false)
                .setPositiveButton("Yay!",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        Intent intent = new Intent(Game.this, MainMenu.class);
                        startActivity(intent);
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }

    private String getBoardSize(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        return prefs.getString("Board size", "");
    }
}
