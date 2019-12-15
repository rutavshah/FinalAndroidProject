package com.example.game.Level3.GameElements;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.example.game.Level3.Entities.Ball;

import java.util.ArrayList;

/**
 * The type Game elements.
 */
public class GameElements {
    /**
     * The Balls.
     */
    public ArrayList<Ball> balls;
    /**
     * The Memory ball.
     */
    public Ball memoryBall;
    /**
     * The Show count.
     */
    public int showCount;
    /**
     * The Hidden state.
     */
    public boolean hiddenState;
    /**
     * The Score.
     */
    public int score;
    /**
     * The Time.
     */
    public int time;
    /**
     * The Bitmap colours.
     */
    public ArrayList<Bitmap> bitmapColours;
    /**
     * The Lives.
     */
    public int lives;
    /**
     * The Level.
     */
    public int level;
    /**
     * The Number of refreshes.
     */
    public int numberOfRefreshes;
    /**
     * The Heart.
     */
    public Drawable heart;

    /**
     * Set balls.
     *
     * @param balls the balls
     */
    void setBalls(ArrayList<Ball> balls){
        this.balls = balls;
    }

    /**
     * Sets memory ball.
     *
     * @param memoryBall the memory ball
     */
    void setMemoryBall(Ball memoryBall) {
        this.memoryBall = memoryBall;
    }

    /**
     * Sets bitmap colours.
     *
     * @param bitmapColours the bitmap colours
     */
    void setBitmapColours(ArrayList<Bitmap> bitmapColours) {
        this.bitmapColours = bitmapColours;
    }

    /**
     * Sets show count.
     *
     */
    void setShowCount() {
        this.showCount = 0;
    }

    /**
     * Sets hidden state.
     *
     */
    void setHiddenState() {
        this.hiddenState = false;
    }

    /**
     * Sets score.
     *
     * @param score the score
     */
    void setScore(int score) {
        this.score = score;
    }

    /**
     * Sets time.
     *
     * @param time the time
     */
    void setTime(int time) {
        this.time = time;
    }


    /**
     * Sets lives.
     *
     * @param lives the lives
     */
    void setLives(int lives) {
        this.lives = lives;
    }


    /**
     * Sets level.
     *
     */
    void setLevel() {
        this.level = 1;
    }

    /**
     * Sets number of refreshes.
     *
     */
    void setNumberOfRefreshes() {
        this.numberOfRefreshes = 0;
    }

    /**
     * Sets heart.
     *
     * @param heart the heart
     */
    void setHeart(Drawable heart) {
        this.heart = heart;
    }
}
