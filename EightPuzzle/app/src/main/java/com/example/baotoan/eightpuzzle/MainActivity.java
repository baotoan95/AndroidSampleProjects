package com.example.baotoan.eightpuzzle;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Game game;
    private Button btnReset;
    private TextView txtCounter, txtTimer;
    private CountDownTimer countDownTimer;
    private int timeCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new Game(this);
        game.newGame();

        // Mapping view
        txtCounter = (TextView) findViewById(R.id.txt_counter);
        txtTimer = (TextView) findViewById(R.id.txt_timer);
        btnReset = (Button) findViewById(R.id.btn_reset);

        // Add listener
        btnReset.setOnClickListener(this);

        // Create count down
        this.countDownTimer = new CountDownTimer(999999999, 1000) {
            @Override
            public void onTick(long l) {
                txtTimer.setText("Timer: " + timeCount++);
            }

            @Override
            public void onFinish() {
                // Nothing
            }
        };
        this.countDownTimer.start();
    }

    @Override
    public void onClick(View view) {
        if(view == btnReset) {
            // Reset time counter
            this.timeCount = 0;
            txtTimer.setText("Timer: " + timeCount);
            // Reset counter
            this.txtCounter.setText("Counter: 0");
            // Reset game
            game.newGame();
            // Start timer
            stopCountDown();
            this.countDownTimer.start();
        }
    }

    public void setCount(int count) {
        txtCounter.setText("Counter: " + count);
    }

    public void stopCountDown() {
        this.countDownTimer.cancel();
    }
}
