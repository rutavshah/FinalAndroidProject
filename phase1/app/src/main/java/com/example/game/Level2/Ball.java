package com.example.game.Level2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball {
    private float x, y;
    private int color;
    private boolean red;
    private boolean touched;


    void setTouched() {
        this.touched = true;
    }

    float getX() {
        return x;
    }

    float getY() {
        return y;
    }

    boolean isTouched() {
        return !touched;
    }

    void setPair(Ball pair) {
        this.pair = pair;
    }

    private Ball pair;

    Ball getPair() {
        return this.pair;
    }

    Ball(float x, float y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.red = true;
        this.touched = false;
    }

    boolean getRed() {
        return red;
    }


    boolean contains(float l, float m) {
        if (x - 100 <= l && l <= x + 100) {
            return y - 100 <= m && m <= y + 100;
        }
        return false;
    }

    void setRed() {
        this.color = Color.RED;
    }

    public void draw(Canvas c) {
        Paint p = new Paint();
        p.setColor(color);
        c.drawCircle(this.x, this.y, 100, p);
    }

    void setColor() {
        if (this.pair.isRed()) {
            this.color = Color.GREEN;
            this.pair.color = Color.GREEN;
        } else {
            this.color = Color.DKGRAY;
            this.pair.color = Color.DKGRAY;
        }
    }

    private boolean isRed() {
        return this.color == Color.RED;
    }
}
