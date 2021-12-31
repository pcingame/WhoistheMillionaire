package com.example.whoisthemillionaire.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
import com.example.whoisthemillionaire.TutorialActivity;
import com.skydoves.progressview.ProgressView;

public class MainActivity extends AppCompatActivity {

    private Button btnStartGame, btnTutorial, btnOutGame;
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

        btnOutGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialogOutGame();
            }
        });

        btnTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TutorialActivity.class));
            }
        });

    }

    private void initView() {
        btnStartGame = findViewById(R.id.btn_start_game);
        btnOutGame = findViewById(R.id.btn_out_game);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setDialogOutGame();
    }

    private void setDialogOutGame() {
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_stop_game);
        TextView tvAnswer = (TextView) dialog.findViewById(R.id.tv_dialog_stop);
        tvAnswer.setText("Bạn có muốn thoát trò chơi không ?");
        Button btnOKStop = (Button) dialog.findViewById(R.id.btn_ok_stop_game);
        Button btnCancelStop = (Button) dialog.findViewById(R.id.btn_cancel_stop_game);
        btnOKStop.setText("OK");
        btnCancelStop.setText("Chơi tiếp!");

        btnOKStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
                ;
            }
        });

        btnCancelStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}