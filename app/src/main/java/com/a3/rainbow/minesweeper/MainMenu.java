package com.a3.rainbow.minesweeper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.a3.rainbow.minesweeper.logic.Star;

public class MainMenu extends AppCompatActivity {
    private Star starfield;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        starfield = Star.getInstance();

        int savedStarNum = getStarNum(this);
        starfield.setNumStars(savedStarNum);

        setupGameBtn();
        setupOptionsBtn();
        setupHelpBtn();
    }

    static public int getStarNum(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        return prefs.getInt("Number of stars", 0);
    }

    private void setupGameBtn() {
        Button play = findViewById(R.id.game_btn);

        play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainMenu.this, Game.class);
                int savedStarNum = getStarNum(getBaseContext());
                starfield.setNumStars(savedStarNum);
                startActivity(intent);
            }
        });
    }

    private void setupOptionsBtn() {
        Button options = findViewById(R.id.options_btn);

        options.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainMenu.this, Options.class);
                startActivity(intent);
            }
        });
    }

    private void setupHelpBtn() {
        Button help = findViewById(R.id.help_btn);

        help.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainMenu.this, Help.class);
                startActivity(intent);
            }
        });
    }
}
