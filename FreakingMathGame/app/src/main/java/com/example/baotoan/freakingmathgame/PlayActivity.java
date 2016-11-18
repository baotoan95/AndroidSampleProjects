package com.example.baotoan.freakingmathgame;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnTrue;
    private Button btnFalse;
    private TextView lblExpression;
    private TextView lblResult;
    private TextView lblScore;
    private RelativeLayout board;
    private ProgressBar progressBar;

    private CountDownTimer countDownTimer;

    private QuestionManager questionManager;
    private Question question;

    private int score;
    private final String[] backgroundColors = {"#00cc00", "#cc3300", "#0066cc", "#9900cc", "#c73605", "#00cc33", "#cc0066", "#99cc00"};
    private Random rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        btnTrue = (Button) findViewById(R.id.btn_true);
        btnFalse = (Button) findViewById(R.id.btn_false);
        board = (RelativeLayout) findViewById(R.id.game_context);
        lblExpression = (TextView) findViewById(R.id.lbl_expression);
        lblResult = (TextView) findViewById(R.id.lbl_result);
        lblScore = (TextView) findViewById(R.id.lbl_score);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(1000);

        btnTrue.setOnClickListener(this);
        btnFalse.setOnClickListener(this);

        // init game
        rand = new Random();
        questionManager = new QuestionManager();
        init();

    }

    private void createTimerTask() {
        countDownTimer = new CountDownTimer(2000, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                progressBar.setProgress((int)millisUntilFinished / 2);
            }

            @Override
            public void onFinish() {
                showGameOver();
            }
        };
    }

    @Override
    public void onClick(View v) {
        if(v == btnTrue) {
            if(question.isCorrect()) {
                score++;
                lblScore.setText(String.valueOf(score));
                nextQuestion();
                countDownTimer.start();
            } else {
                showGameOver();
            }
        } else {
            if(!question.isCorrect()) {
                score++;
                lblScore.setText(String.valueOf(score));
                nextQuestion();
                countDownTimer.start();
            } else {
                showGameOver();
            }
        }
    }

    private void nextQuestion() {
        question = questionManager.makeQuestion(score);
        board.setBackgroundColor(Color.parseColor(backgroundColors[rand.nextInt(backgroundColors.length)]));

        displayQuestion();
    }

    private void displayQuestion() {
        lblExpression.setText(question.getStrExpression());
        lblResult.setText(question.getStrTempResult());
    }

    private void init() {
        this.score = 0;
        lblScore.setText(String.valueOf(score));
        nextQuestion();
        btnTrue.setEnabled(true);
        btnFalse.setEnabled(true);
        progressBar.setProgress(0);
        createTimerTask();
    }

    private void showGameOver() {
        countDownTimer.cancel();
        btnTrue.setEnabled(false);
        btnFalse.setEnabled(false);
        progressBar.setProgress(0);
        new AlertDialog.Builder(this)
                .setTitle("Game Over")
                .setMessage("Your score: " + score)
                .setNegativeButton(R.string.home, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        PlayActivity.this.finish();
                    }
                })
                .setPositiveButton(R.string.again, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        init();
                    }
                }).setIcon(android.R.drawable.ic_input_get).show();

    }
}
