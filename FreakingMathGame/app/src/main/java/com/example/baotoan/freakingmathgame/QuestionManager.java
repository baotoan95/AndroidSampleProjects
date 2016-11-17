package com.example.baotoan.freakingmathgame;

import java.util.Random;

/**
 * Created by BaoToan on 10/28/2016.
 */

public class QuestionManager {
    // levels
    private final int EASY_LEVEL = 0;
    private final int MEDIUM_LEVEL = 1;
    private final int HARD_LEVEL = 2;
    private final int SO_HARD_LEVEL = 3;

    // max score depend level
    private final int MAX_SCORE_EASY_LEVEL = 10;
    private final int MAX_SCORE_MEDIUM_LEVEL = 20;
    private final int MAX_SCORE_HARD_LEVEL = 30;
    private final int MAX_SCORE_SO_HARD_LEVEL = 40;

    // max value of operator for each level: easy, medium, hard
    private final int MAX_VALUE_LEVEL_EASY = 5;
    private final int MAX_VALUE_LEVEL_MEDIUM = 10;
    private final int MAX_VALUE_LEVEL_HARD = 20;
    private final int MAX_VALUE_LEVEL_SO_HARD = 30;
    private final int[] MAX_VALUES = {MAX_VALUE_LEVEL_EASY, MAX_VALUE_LEVEL_MEDIUM, MAX_VALUE_LEVEL_HARD, MAX_VALUE_LEVEL_SO_HARD};

    // operators text
    public static final char ADD_TEXT = '+';
    public static final char SUB_TEXT = '-';
    public static final char MUL_TEXT = '*';
    public static final char DIV_TEXT = '/';
    public static final char[] OPERATORS_TEXT = {ADD_TEXT, SUB_TEXT, MUL_TEXT, DIV_TEXT};

    private Random rand;

    public QuestionManager() {
        this.rand = new Random();
    }

    public Question makeQuestion(int score) {
        int currentLevel = getCurrentLevel(score);
        int x = rand.nextInt(MAX_VALUES[currentLevel]) + 1;
        int y = rand.nextInt(MAX_VALUES[currentLevel]) + 1;
        char operator = OPERATORS_TEXT[rand.nextInt(currentLevel + 1)];

        return new Question(x, y, operator);
    }

    private int getCurrentLevel(int score) {
        if (score <= MAX_SCORE_EASY_LEVEL) {
            return EASY_LEVEL;
        } else if (score <= MAX_SCORE_MEDIUM_LEVEL) {
            return MEDIUM_LEVEL;
        } else if (score <= MAX_SCORE_HARD_LEVEL) {
            return HARD_LEVEL;
        } else {
            return SO_HARD_LEVEL;
        }
    }

}
