package com.example.baotoan.eightpuzzle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Game game;
    private GridLayout girdLayout;
    private Button btnReset;
    private TextView txtCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new Game(this);
        game.newGame();

        txtCounter = (TextView) findViewById(R.id.txt_counter);

        btnReset = (Button) findViewById(R.id.btn_reset);
        btnReset.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btnReset) {
            game.newGame();
        }
    }

    public void setCount(int count) {
        txtCounter.setText("Counter: " + count);
    }
}
