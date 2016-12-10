package com.example.baotoan.eightpuzzle;

import android.view.View;
import android.widget.Button;

/**
 * Created by baotoan on 10/12/2016.
 */

public class Controller implements View.OnClickListener {
    private Game game;

    public Controller(Game game) {
        this.game = game;
    }

    @Override
    public void onClick(View view) {
        game.move(Integer.parseInt(((Button)view).getText().toString()));
    }
}
