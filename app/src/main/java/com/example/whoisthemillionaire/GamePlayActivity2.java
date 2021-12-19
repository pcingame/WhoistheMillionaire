package com.example.whoisthemillionaire;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whoisthemillionaire.adapter.PrizeMoneyAdapter;
import com.example.whoisthemillionaire.object.Question;

import java.util.ArrayList;
import java.util.Random;

public class GamePlayActivity2 extends AppCompatActivity {

    ListView lvPrizeMoney;
    PrizeMoneyAdapter prizeMoneyAdapter;
    ArrayList<String> arrPrizeMoney;
    Question question;
    TextView tvQuestion, tvAnswer1, tvAnswer2, tvAnswer3, tvAnswer4, tvLoseGame;
    int posOfQuestion = 1;
    ArrayList<TextView> arrTvAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play2);
        initView();
        setAdapter();
        setAnswer();
        setQuestion();
        showQuestion();
        setClick();


    }

    private void setQuestion() {
        question = new Question();
        question.setContent("Gần mực thì đen gần đèn thì... ");
        question.setCorrectAns("rạng");
        ArrayList<String> arrIncorrectAns = new ArrayList<>();
        arrIncorrectAns.add("rõ");
        arrIncorrectAns.add("mờ");
        arrIncorrectAns.add("trắng");
        question.setArrIncorrectAns(arrIncorrectAns);
    }

    private void setAdapter() {
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

    }

    private void initView() {
        lvPrizeMoney = findViewById(R.id.lv_prize_money);
        tvQuestion = findViewById(R.id.tv_question);
        tvAnswer1 = findViewById(R.id.tv_answer_1);
        tvAnswer2 = findViewById(R.id.tv_answer_2);
        tvAnswer3 = findViewById(R.id.tv_answer_3);
        tvAnswer4 = findViewById(R.id.tv_answer_4);
        tvLoseGame = findViewById(R.id.tv_lose_game);
        tvLoseGame.setVisibility(View.GONE);

    }

    private void setAnswer(){
        arrTvAnswer = new ArrayList<>();
        arrTvAnswer.add(tvAnswer1);
        arrTvAnswer.add(tvAnswer2);
        arrTvAnswer.add(tvAnswer3);
        arrTvAnswer.add(tvAnswer4);
    }

    public void showQuestion() {
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

        for (int i = 0; i <arrTvAnswer.size(); i++){
            arrTvAnswer.get(i).setBackgroundResource(R.drawable.bg_btn);
            arrTvAnswer.get(i).setText(arrAnswer.get(i));
        }

        prizeMoneyAdapter.setPosOfQuestion(posOfQuestion);
    }

    private void setClick() {
        View.OnClickListener listener = new View.OnClickListener() {
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
        String answer = tvAnswer.getText().toString();
        tvAnswer.setBackgroundResource(R.drawable.bg_choose_ans);

        new CountDownTimer(2000, 100){

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
                            posOfQuestion++;
                            if (posOfQuestion >= 15) {
                                posOfQuestion = 15;
                            }
                            showQuestion();
                        }else {
                            tvLoseGame.setVisibility(View.VISIBLE);
                            int posOfPrizeMoney = (posOfQuestion/5) * 5;
                            tvLoseGame.setText("Bạn sẽ ra về với tiền thưởng là \n" + arrPrizeMoney.get(14 - posOfPrizeMoney)
                            + " VNĐ");
                        }
                    }
                }.start();
            }
        }.start();


    }
}