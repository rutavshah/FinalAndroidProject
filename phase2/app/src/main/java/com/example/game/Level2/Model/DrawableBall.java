package com.example.game.Level2.Model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * The type DrawableBall that includes left balls that can be click and right ones which are the
 * pairs
 */
public class DrawableBall implements Drawable {
    private float x, y;
    /**
     * The Pair to the ball.
     */
    DrawableBall pair;
    /**
     * The Color of the ball.
     */
    int color;
    /**
     * Boolean variable that represents if the ball is a target.
     */
    boolean isTarget;

    /**
     * Instantiates a new DrawableBall.
     *
     * @param x     the x coordinate
     * @param y     the y coordinate
     * @param color the color
     */
    DrawableBall(float x, float y, int color){
        this.x = x;
        this.y = y;
        this.color = color;
        this.isTarget = false;
    }

    /**
     * Gets to see if the ball is a target
     *
     * @return the is target
     */
    boolean getIsTarget() {
        return this.isTarget;
    }

    /**
     * Sets target.
     */
    public void setTarget() {
        this.isTarget = true;
    }

    /**
     * Gets x.
     *
     * @return the x coordinate
     */
    public float getX() {
        return x;
    }

    /**
     * Gets y.
     *
     * @return the y coordinate
     */
    public float getY() {
        return y;
    }

    /**
     * Get pair paired ball.
     *
     * @return pair ball
     */
    DrawableBall getPair(){
        return this.pair;
    }

    /**
     * Contains boolean.
     *
     * @param l the l the x coordinate
     * @param m the m the y coordinate
     * @return the boolean
     */
    public boolean contains(float l, float m) {
        if (x - 100 <= l && l <= x + 100) {
            return y - 100 <= m && m <= y + 100;
        }
        return false;
    }

    /**
     * Sets pair.
     *
     * @param pair the pair
     */
    void setPair(DrawableBall pair) {
        this.pair = pair;
    }

    /**
     * Draws the ball and its pair
     * @param c the canvas that is to be drawn on
     */
    @Override
    public void draw(Canvas c){
        Paint p = new Paint();
        p.setColor(Color.WHITE);
        c.drawCircle(this.x, this.y, 110, p);
        p.setColor(color);
        c.drawCircle(this.x, this.y, 100, p);
    }
}
