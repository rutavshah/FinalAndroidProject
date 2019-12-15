package com.example.game.Level3.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * The type Ball.
 */
public class Ball {

    private Bitmap currentImage;
    private Bitmap image;
    /**
     * The constant hiddenImage.
     */
    public static Bitmap hiddenImage;
    private int x, y;
    private boolean touched;

    /**
     * Instantiates a new Ball.
     *
     * @param bmp the bmp
     * @param x   the x
     * @param y   the y
     */
    public Ball(Bitmap bmp, int x, int y) {
        currentImage = bmp;
        image = bmp;
        this.x = x;
        this.y = y;
        touched = false;
    }

    /**
     * Draw.
     *
     * @param canvas the canvas
     */
    public void draw(Canvas canvas) {
        canvas.drawBitmap(currentImage, x, y, null);
    }

    /**
     * Hide.
     */
    public void hide() {
        this.currentImage = hiddenImage;
    }

    /**
     * Show.
     */
    public void show() {
        this.currentImage = image;
    }

    /**
     * Is touched boolean.
     *
     * @return the boolean
     */
    public boolean isTouched() {
        return touched;
    }

    /**
     * Contains boolean.
     *
     * @param touchX the touch x
     * @param touchY the touch y
     * @return the boolean
     */
    public boolean contains(float touchX, float touchY) {
        return x <= touchX && touchX <= x + image.getWidth()
                && y <= touchY && touchY <= y + image.getHeight();
    }


    /**
     * Change colour.
     *
     * @param newBmp the new bmp
     */
    public void changeColour(Bitmap newBmp) {
        this.image = newBmp;
    }

    /**
     * Set touched.
     *
     * @param b the b
     */
    public void setTouched(boolean b){
        touched = b;
    }

    /**
     * Equals boolean.
     *
     * @param that the that
     * @return the boolean
     */
    public boolean equals(Ball that) {
        return this.currentImage.sameAs(that.currentImage);
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return this.x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return this.y;
    }
}
