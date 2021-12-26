package com.example.whoisthemillionaire.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.whoisthemillionaire.R;
import com.skydoves.progressview.ProgressView;

public class MainActivity extends AppCompatActivity {

    private Button btnStartGame, btnTutorial, btnHighScore, btnThank;
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setSound();
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

    private void setSound() {
        mediaPlayer = MediaPlayer.create(this, R.raw.soundtrack);
        mediaPlayer.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.release();
        finish();
    }
}