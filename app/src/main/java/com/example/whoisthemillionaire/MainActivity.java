package com.example.whoisthemillionaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnStartGame, btnTutorial, btnHighScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GamePlayActivity2.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        btnStartGame = findViewById(R.id.btn_start_game);
        btnHighScore = findViewById(R.id.btn_high_score);
        btnTutorial = findViewById(R.id.btn_tutorial);
    }
}