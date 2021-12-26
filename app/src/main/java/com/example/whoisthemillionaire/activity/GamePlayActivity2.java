package com.example.whoisthemillionaire.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.whoisthemillionaire.R;
import com.example.whoisthemillionaire.adapter.PrizeMoneyAdapter;

import com.example.whoisthemillionaire.object.DialoAudienceReplied;
//import com.example.whoisthemillionaire.object.DialogAudienceReplied;
import com.example.whoisthemillionaire.object.DialogPhone;
import com.example.whoisthemillionaire.object.FakeData;
import com.example.whoisthemillionaire.object.Question;

import java.util.ArrayList;
import java.util.Random;

public class GamePlayActivity2 extends AppCompatActivity {

    private ListView lvPrizeMoney;
    private PrizeMoneyAdapter prizeMoneyAdapter;
    ArrayList<String> arrPrizeMoney;
    Question question;
    String[] answerABCD = {"A", "B", "C", "D"};
    MediaPlayer correct, incorrect;

    private TextView tvQuestion, tvAnswer1, tvAnswer2, tvAnswer3, tvAnswer4, tvLoseGame;
    private ImageView imgHepl5050, imgHeplAudience, imgHeplPhone, imgHelpExchange;
    private Button btnBack;
    int posOfQuestion = 1;
    ArrayList<TextView> arrTvAnswer;
    FakeData fakeData;
    View.OnClickListener listener;
    String answer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play2);
        initView();
        setUp();
        setAnswer();
        showQuestion();
        setClick();
        setHepl();
        


    }


    public void setQuestion() {

        question = fakeData.question(posOfQuestion);
    }

    private void setUp() {
        arrPrizeMoney = new ArrayList<>();
        arrPrizeMoney.add("150.000.000");
        arrPrizeMoney.add("85.000.000");
        arrPrizeMoney.add("60.000.000");
        arrPrizeMoney.add("40.000.000");
        arrPrizeMoney.add("30.000.000");
        arrPrizeMoney.add("22.000.000");
        arrPrizeMoney.add("14.000.000");
        arrPrizeMoney.add("10.000.000");
        arrPrizeMoney.add("6.000.000");
        arrPrizeMoney.add("3.000.000");
        arrPrizeMoney.add("2.000.000");
        arrPrizeMoney.add("1.000.000");
        arrPrizeMoney.add("600.000");
        arrPrizeMoney.add("400.000");
        arrPrizeMoney.add("200.000");

        prizeMoneyAdapter = new PrizeMoneyAdapter(this, 0, arrPrizeMoney);
        lvPrizeMoney.setAdapter(prizeMoneyAdapter);
        question = new Question();
        fakeData = new FakeData(this);

    }

    private void initView() {
        lvPrizeMoney = findViewById(R.id.lv_prize_money);
        tvQuestion = findViewById(R.id.tv_question);
        tvAnswer1 = findViewById(R.id.tv_answer_1);
        tvAnswer2 = findViewById(R.id.tv_answer_2);
        tvAnswer3 = findViewById(R.id.tv_answer_3);
        tvAnswer4 = findViewById(R.id.tv_answer_4);
        imgHepl5050 = findViewById(R.id.help_5050);
        imgHelpExchange = findViewById(R.id.help_exchange_question);
        imgHeplAudience = findViewById(R.id.help_audience);
        imgHeplPhone = findViewById(R.id.help_phone);
        tvLoseGame = findViewById(R.id.tv_lose_game);
//        btnBack = findViewById(R.id.btn_back_to_main);
        tvLoseGame.setVisibility(View.GONE);
//        btnBack.setVisibility(View.GONE);

    }

    private void setAnswer(){
        arrTvAnswer = new ArrayList<>();
        arrTvAnswer.add(tvAnswer1);
        arrTvAnswer.add(tvAnswer2);
        arrTvAnswer.add(tvAnswer3);
        arrTvAnswer.add(tvAnswer4);


    }

    public void showQuestion() {
        setQuestion();
        tvQuestion.setText(question.getContent());
        ArrayList<String> arrAnswer = new ArrayList<>(question.getArrIncorrectAns());
        arrAnswer.add(question.getCorrectAns());

        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int pos1 = random.nextInt(arrAnswer.size());
            int pos2 = random.nextInt(arrAnswer.size());
            String a = arrAnswer.get(pos1);
            arrAnswer.set(pos1, arrAnswer.get(pos2));
            arrAnswer.set(pos2, a);
        }


        for (int q = 0; q < arrTvAnswer.size(); q++){
            arrTvAnswer.get(q).setOnClickListener(listener);
            arrTvAnswer.get(q).setVisibility(View.VISIBLE);
            arrTvAnswer.get(q).setBackgroundResource(R.drawable.bg_btn);
            arrTvAnswer.get(q).setText( arrAnswer.get(q));
            //answerABCD[q] + ": " +

        }

        prizeMoneyAdapter.setPosOfQuestion(posOfQuestion);
    }

    private void setClick() {
         listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(((TextView) view));
            }
        };
        for(TextView t:arrTvAnswer){
            t.setOnClickListener(listener);
        }
    }

    private void checkAnswer(TextView tvAnswer) {
        correct = MediaPlayer.create(this ,R.raw.correct_answer);
        incorrect = MediaPlayer.create(this ,R.raw.incorrect_answer);
        answer = tvAnswer.getText().toString();
        tvAnswer.setBackgroundResource(R.drawable.bg_choose_ans);
        new CountDownTimer(1000, 100){

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                for(TextView t:arrTvAnswer){
                    String s = t.getText().toString();
                    if(s.equals(question.getCorrectAns())){
                        t.setBackgroundResource(R.drawable.bg_correct_ans);
                        break;
                    }
                }
                new CountDownTimer(2000, 100){
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        if (answer.equals(question.getCorrectAns())) {
                            correct.start();
                            posOfQuestion++;
                            if (posOfQuestion > 15) {
                                posOfQuestion = 15;
                                tvLoseGame.setVisibility(View.VISIBLE);
                                tvLoseGame.setText("Chúc mừng bạn đã được \n" + arrPrizeMoney.get(0) + " VNĐ");
                                return;
                            }
                            showQuestion();
                        }else {
                            incorrect.start();
                            tvLoseGame.setVisibility(View.VISIBLE);
                         //   btnBack.setVisibility(View.VISIBLE);
                            int posOfPrizeMoney = (posOfQuestion/5) * 5;
                            tvLoseGame.setText("Bạn sẽ ra về với tiền thưởng là \n" + arrPrizeMoney.get(14 - posOfPrizeMoney)
                            + " VNĐ");
                            /*Intent intent = new Intent(GamePlayActivity2.this, MainActivity.class);
                            startActivity(intent);*/
                        }
                    }
                }.start();
            }
        }.start();
    }

    boolean hepl_fifty = true;
    boolean isHeplAudience = true;
    boolean isHeplExchange = true;
    boolean isHeplCall = true;
    public void setHepl() {
        imgHepl5050.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(!hepl_fifty){
                return;
            }
            Random random = new Random();
            int numOfAnsHide = 2;
            do{
                int posOfAns = random.nextInt(4);
                TextView textView = arrTvAnswer.get(posOfAns);

                if(textView.getVisibility() == View.VISIBLE && !textView.getText().toString().equals(question.getCorrectAns())){
                    textView.setVisibility(View.INVISIBLE);
                    textView.setOnClickListener(null);
                    numOfAnsHide --;
                }
            }while (numOfAnsHide>0);
            hepl_fifty = false;}
        });

        imgHeplAudience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(!isHeplAudience){
                return;
            }
            for (int j = 0; j<arrTvAnswer.size(); j++){
                TextView textView = arrTvAnswer.get(j);
                if(textView.getText().toString().equals(question.getCorrectAns())){
                    new DialoAudienceReplied(GamePlayActivity2.this, j + 1).show();
                    break;
                }
            }
            isHeplAudience = false;
            }
        });

        imgHelpExchange.setOnClickListener(view -> {
            if(!isHeplExchange){
                return;
            }
            showQuestion();
            isHeplExchange = false;
        });

        imgHeplPhone.setOnClickListener(v -> {
            if(!isHeplCall){
                return;
            }
            for (int r = 0; r<arrTvAnswer.size(); r++){
                TextView text = arrTvAnswer.get(r);
                if(text.getText().toString().equals(question.getCorrectAns())){
                    new DialogPhone(GamePlayActivity2.this, r + 1).show();
                    break;
                }
            }
            //new DialogPhone(GamePlayActivity2.this).show();
            isHeplCall = false;
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        correct.release();
        incorrect.release();
        finish();
    }


}