package com.example.game.Level2.Model;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Game mode 3.
 */
public class GameMode3 implements Algorithms {

    private MakeObjects objects;
    private int tries;
    private int lives;
    private int score;
    private double percent;
    private boolean gameOver;
    private List<LeftBall> pressed;
    private boolean undo;

    /**
     * Instantiates a new Game mode 3.
     *
     * @param objects the objects
     * @param lives   the lives
     */
    public GameMode3(MakeObjects objects, int lives) {
        this.objects = objects;
        this.tries = 0;
        this.score = 0;
        this.percent = 0.0;
        this.gameOver = false;
        this.lives = lives;
        pressed = new ArrayList<>();
        this.undo = false;

    }

    @Override
    public void buttonPressed(float x, float y) {
        if (!this.gameOver && lives > 0) {
            for (LeftBall b : objects.getLeft()) {
                if (b.contains(x, y) && !b.getTouched()) {
                    b.setTouched();
                    tries++;
                    pressed.add(b);
                    if (b.getIsTarget() | b.getPair().getIsTarget()) {
                        score++;
                        b.setColor(Color.GREEN);
                        addDelay();
                        resetGame();
                    } else {
                        b.setColor(Color.RED);
                        addDelay();
                        lives--;
                    }
                    calculatePercent();
                    if (tries > 5 && percent > 60) {
                        this.gameOver = true;
                        addDelay();
                    }
                }
            }
        } else {
            UndoButton undoButton = objects.getUndoObject().get(0);
            if (undoButton.contains(x, y)) {
                undo();
            }else{
                undo = true;
            }
        }
    }

    /**
     * adds 200 ms delay
     */
    private void addDelay() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void undo() {
        LeftBall b = this.pressed.get(pressed.size() - 1);
        b.undo();
        this.gameOver = false;
        this.lives = 1;
        this.tries--;
        calculatePercent();
        this.undo = true;
    }

    /**
     * calculates the percent score for this game
     */
    private void calculatePercent() {
        percent = Math.round(((score * 1.0) / (tries * 1.0)) * 10000.0) / 100.0;
    }


    /**
     *
     * @return string representing a percent
     */
    @Override
    public String getScore() {
        calculatePercent();
        String returnValue = Double.toString(percent);
        returnValue += "%";
        return returnValue;
    }

    @Override
    public int getLives() {
        return this.lives;
    }

    @Override
    public boolean getGameOver() {
        return this.gameOver;
    }

    @Override
    public void resetGame() {
        pressed = new ArrayList<>();
        objects.resetGame();
    }

    @Override
    public int getGameScore(){
        calculatePercent();
        double returnScore = percent / 10;
        return (int) Math.round(returnScore);
    }

    @Override
    public boolean getUndo() {
        return this.undo;
    }
}
