package com.example.baotoan.freakingmathgame;

import java.util.Random;

/**
 * Created by BaoToan on 10/28/2016.
 */

public class Question {
    private int x;
    private int y;
    private int result;
    private int tempResult;
    private char operator;

    private Random rand;

    public Question(int x, int y, char operator) {
        this.x = x;
        this.y = y;
        this.operator = operator;
        this.rand = new Random();

        // compute correct result
        this.result = compute();
    }

    private int compute() {
        switch (operator) {
            case QuestionManager.ADD_TEXT:
                return x + y;
            case QuestionManager.SUB_TEXT:
                return x - y;
            case QuestionManager.MUL_TEXT:
                return x * y;
            case QuestionManager.DIV_TEXT:
                return x / y;
            default:
                return 0;
        }
    }

    public boolean isCorrect() {
        return tempResult == result;
    }

    public String getStrExpression() {
        return x + " " + operator + " " + y;
    }

    public String getStrTempResult() {
        if (rand.nextBoolean()) {
            tempResult = this.result;
        } else {
            do {
                int rs = compute();
                tempResult = rand.nextInt(rs > 0 ? rs : (x + y)) + 1;
            } while (tempResult == result);
        }
        return "= " + tempResult;
    }
}
