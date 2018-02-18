package com.a3.rainbow.minesweeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        setupGameBtn();
        setupOptionsBtn();
        setupHelpBtn();
    }

    private void setupGameBtn() {
        Button play = findViewById(R.id.game_btn);

        play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainMenu.this, Game.class);
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
