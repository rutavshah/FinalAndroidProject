package com.example.game.Level3.GameElements;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.example.game.Level3.Entities.Ball;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The Game builder.
 */
public class GameBuilder {
    private GameElements gameElements;
    private ArrayList<Bitmap> bitmapColours;
    private Drawable heart;
    /**
     * The Time.
     */
    private int time;
    /**
     * The Point.
     */


    /**
     * Instantiates a new Game builder.
     *
     * @param bitmapColours the bitmap colours
     * @param heart         the heart
     * @param time          the time
     */
    public GameBuilder(ArrayList<Bitmap> bitmapColours, Drawable heart, int time){
        this.gameElements = new GameElements();
        this.bitmapColours = bitmapColours;
        this.heart = heart;
        this.time = time;

    }

    /**
     * Build balls.
     */
    void buildBalls(){
        ArrayList<Ball> balls = new ArrayList<>();
        balls.add(new Ball(bitmapColours.get(0), 100,200));
        balls.add(new Ball(bitmapColours.get(1), 450, 200));
        balls.add(new Ball(bitmapColours.get(2),  800, 200));

        balls.add(new Ball(bitmapColours.get(3), 100,650));
        balls.add(new Ball(bitmapColours.get(4), 450, 650));
        balls.add(new Ball(bitmapColours.get(5), 800, 650));

        balls.add(new Ball(bitmapColours.get(6), 100,1100));
        balls.add(new Ball(bitmapColours.get(7), 450, 1100));
        balls.add(new Ball(bitmapColours.get(8), 800, 1100));
        gameElements.setBalls(balls);
    }

    /**
     * Build memory ball.
     */
    void buildMemoryBall() {
        gameElements.setMemoryBall(new Ball(this.bitmapColours.get(ThreadLocalRandom.current().nextInt(0, 9)), 450, 1500));
        this.gameElements.memoryBall.hide();
    }

    /**
     * Build bitmap colours.
     */
    void buildBitmapColours() {
        gameElements.setBitmapColours(this.bitmapColours);
    }

    /**
     * Build game refresh elements.
     */
    void buildGameRefreshElements() {
        gameElements.setShowCount();
        gameElements.setNumberOfRefreshes();
        gameElements.setHiddenState();
    }


    /**
     * Build score.
     */
    void buildScore() {
        gameElements.setScore(0);
    }

    /**
     * Build lives.
     */
    void buildLives() {
        gameElements.setLives(7);
    }

    /**
     * Build heart.
     */
    void buildHeart() {
        gameElements.setHeart(this.heart);
    }

    /**
     * Build level.
     */
    void buildLevel() {
        gameElements.setLevel();
    }

    /**
     * Build time.
     */
    void buildTime() {
        gameElements.setTime(this.time);
    }

    /**
     * Get game elements game elements.
     *
     * @return the game elements
     */
    GameElements getGameElements(){
        return this.gameElements;
    }


}
