package com.example.baotoan.eightpuzzle;

import android.content.Context;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by baotoan on 10/12/2016.
 */

public class Game {
    private final int MATRIX_SIZE = 3;
    private Context context;
    private int count;
    private boolean win;
    private Button[] buttons;
    private List<Integer> cells;
    private int[][] mappedView;
    private Controller controller;

    // Space position
    private int posX, posY;

    private int[] goal = new int[] {1, 2, 3, 4, 5, 6, 7, 8};

    public Game(Context context) {
        this.context = context;
        this.controller = new Controller(this);
    }

    public void newGame() {
        this.count = 0;
        this.win = false;
        this.cells = new ArrayList<>();
        this.mappedView = new int[MATRIX_SIZE + 2][MATRIX_SIZE + 2];
        this.posX = posY = MATRIX_SIZE - 1;

        initButtons();

        randomPositions();
        initMappedView();
    }

    private void initButtons() {
        this.buttons = new Button[8];

        buttons[0] = (Button) ((MainActivity)context).findViewById(R.id.btn1);
        buttons[1] = (Button) ((MainActivity)context).findViewById(R.id.btn2);
        buttons[2] = (Button) ((MainActivity)context).findViewById(R.id.btn3);
        buttons[3] = (Button) ((MainActivity)context).findViewById(R.id.btn4);
        buttons[4] = (Button) ((MainActivity)context).findViewById(R.id.btn5);
        buttons[5] = (Button) ((MainActivity)context).findViewById(R.id.btn6);
        buttons[6] = (Button) ((MainActivity)context).findViewById(R.id.btn7);
        buttons[7] = (Button) ((MainActivity)context).findViewById(R.id.btn8);

        // Add listener
        for(int i = 0; i < buttons.length; i++) {
            buttons[i].setOnClickListener(controller);
        }

    }

    private void randomPositions() {
        for(int i = 1; i < 9; i++) {
            this.cells.add(i);
        }
        Collections.shuffle(this.cells);
    }

    private void initMappedView() {
        GridLayout.LayoutParams layoutParams = null;
        int index = 0;
        for(int i = 0; i < MATRIX_SIZE; i++) {
            for(int j = 0; j < MATRIX_SIZE; j++) {
                if(index < buttons.length) {
                    mappedView[i + 1][j + 1] = cells.get(index);
                    layoutParams = (GridLayout.LayoutParams) buttons[cells.get(index) - 1].getLayoutParams();
                    layoutParams.rowSpec = GridLayout.spec(i);
                    layoutParams.columnSpec = GridLayout.spec(j);
                    buttons[cells.get(index) - 1].setLayoutParams(layoutParams);
                    index++;
                } else {
                    mappedView[i + 1][j + 1] = -1;
                }
            }
        }

    }

    public void move(int value) {
        // Find position by value
        for(int i = 1; i < MATRIX_SIZE + 1; i++) {
            for(int j = 1; j < MATRIX_SIZE + 1; j++) {
                if(mappedView[i][j] == value && canMove(i, j)) {
                    // Change position mappedView
                    mappedView[posX + 1][posY + 1] = mappedView[i][j];
                    mappedView[i][j] = -1;

                    // Change position for buttons
                    GridLayout.LayoutParams layoutParams = (GridLayout.LayoutParams) buttons[value - 1].getLayoutParams();
                    layoutParams.rowSpec = GridLayout.spec(posX);
                    layoutParams.columnSpec = GridLayout.spec(posY);
                    buttons[value - 1].setLayoutParams(layoutParams);

                    // Change position of space
                    posX = i - 1;
                    posY = j - 1;

                    // Update counter
                    count++;
                    ((MainActivity)context).setCount(count);

                    // check win
                    if(checkWin()) {
                        // Stop count down
                        ((MainActivity)context).stopCountDown();

                        Toast.makeText(context, "You win!!!", Toast.LENGTH_LONG).show();
                    }
                    return;
                }
            }
        }
    }

    public boolean canMove(int row, int col) {
        // Check if can m
        if(mappedView[row - 1][col] == -1 || mappedView[row][col + 1] == -1
                || mappedView[row + 1][col] == -1 || mappedView[row][col - 1] == -1) {
            return true;
        }
        return false;
    }

    public boolean checkWin() {
        int index = 0;
        for(int i = 1; i <= MATRIX_SIZE; i++) {
            for (int j = 1; j <= MATRIX_SIZE; j++) {
                if(mappedView[i][j] == goal[index]) {
                    index++;
                } else {
                    return false;
                }
                if(index == goal.length) {
                    return true;
                }
            }
        }
        return false;
    }

}
