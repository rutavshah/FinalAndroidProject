package com.example.game.Level2.Model;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.game.Level2.Presenter.DrawObjects;

/**
 * The type Line.
 */
public class Line implements Drawable {

    private float x, y, z, a;
    private int color;

    /**
     * Instantiates a new Line.
     *
     * @param x     the x
     * @param y     the y
     * @param z     the z
     * @param a     the a
     * @param color the color
     */
    Line(float x, float y, float z, float a, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.z = z;
        this.a = a;
    }

    public void draw(Canvas c) {
        Paint p = new Paint();
        p.setStrokeWidth(10);
        p.setColor(color);
        c.drawLine(this.x, this.y, this.z, this.a, p);
    }

}
