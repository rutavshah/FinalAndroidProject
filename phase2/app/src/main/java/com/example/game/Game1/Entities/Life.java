package com.example.game.Game1.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Tracks the lives inside the class.
 */
class Life {

    private int x, y;
    private Bitmap image;

    Life(Bitmap image, int x, int y)
    {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    /**
     * draws the object on the canvas.
     * @param canvas   where the heart is drawn.
     */
    public void draw(Canvas canvas)
    {

        canvas.drawBitmap(image, x, y, null);
    }


}

