package com.example.game.Level2.Model;

import android.graphics.Color;

/**
 * The type Left ball which can be touched
 */
public class LeftBall extends DrawableBall {
    private boolean touched;

    /**
     * Instantiates a new Left ball.
     *
     * @param x     the x
     * @param y     the y
     * @param color the color
     */
    LeftBall(float x, float y, int color) {
        super(x, y, color);
        this.touched = false;
    }

    /**
     * Gets touched.
     *
     * @return the touched
     */
    boolean getTouched() {
        return touched;
    }

    /**
     * Sets touched.
     */
    void setTouched() {
        this.touched = true;
    }

    /**
     * Sets color.
     *
     * @param color the color
     */
    public void setColor(int color) {
        this.color = color;
        this.pair.color = color;
    }

    /**
     * Undo's the last touch so resets the colors.
     */
    void undo() {
        this.color = Color.BLUE;
        this.touched = false;
        this.pair.color = Color.BLUE;
    }
}
