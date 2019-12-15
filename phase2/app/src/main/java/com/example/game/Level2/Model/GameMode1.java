package com.example.game.Level2.Model;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * The Normal Game mode
 */
public class GameMode1 implements Algorithms {

    private MakeObjects objects;
    private int score;
    private boolean gameOver;
    private int lives;
    private List<LeftBall> pressed;
    private boolean undo;

    /**
     * Instantiates a new Game mode 1.
     *
     * @param objects the objects that interact
     * @param lives   the lives
     */
    public GameMode1(MakeObjects objects, int lives) {
        this.objects = objects;
        this.score = 0;
        this.gameOver = false;
        this.lives = lives;
        this.pressed = new ArrayList<>();
        this.undo = false;

    }

    /**
     * loops over the list of left balls to see if they were clicked, and then performs actions
     * accordingly
     * @param x the x variable of the click
     * @param y the y variable of the click
     */
    @Override
    public void buttonPressed(float x, float y) {
        if (!this.gameOver && lives > 0) {
            for (LeftBall b : objects.getLeft()) {
                if (b.contains(x, y) && !b.getTouched()) {
                    b.setTouched();
                    pressed.add(b);
                    if (b.getIsTarget() | b.getPair().getIsTarget()) {
                        score++;
                        b.setColor(Color.GREEN);
                        addDelay();
                        resetGame();
                    } else {
                        b.setColor(Color.RED);
                        lives--;
                        addDelay();
                    }
                    if (score == 7) {
                        this.gameOver = true;
                    }
                }
            }
        } else if (lives == 0) {
            UndoButton undoButton = objects.getUndoObject().get(0);
            if (undoButton.contains(x, y)) {
                undo();
            } else{
                undo = true;
            }
        }
    }

    /**
     * Adds 200 ms delay
     */
    private void addDelay() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * The game ignores the last click thus an "undo"
     */
    @Override
    public void undo() {
        this.gameOver = false;
        this.lives = 1;
        LeftBall b = this.pressed.get(pressed.size() - 1);
        b.undo();
        this.undo = true;
    }

    @Override
    public String getScore() {
        return (Integer.toString(score));
    }

    @Override
    public int getLives() {
        return this.lives;
    }

    /**
     * resets the round
     */
    @Override
    public void resetGame() {
        objects.resetGame();
    }

    @Override
    public boolean getGameOver() {
        return this.gameOver;
    }

    @Override
    public boolean getUndo() {
        return this.undo;
    }

    @Override
    public int getGameScore() {
        return this.score;
    }
}
