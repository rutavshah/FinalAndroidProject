package com.example.game.Level2.Model;

import android.graphics.Color;

/**
 * The type Right ball.
 */
public class RightBall extends DrawableBall {

    /**
     * Instantiates a new Right ball.
     *
     * @param x     the x
     * @param y     the y
     * @param color the color
     */
    RightBall(float x, float y, int color) {
        super(x, y, color);
    }

    /**
     * sets target and changes color
     */
    @Override
    public void setTarget() {
        this.isTarget = true;
        this.color = Color.YELLOW;
    }
}
