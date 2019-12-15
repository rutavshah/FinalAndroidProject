package com.example.game.Level2;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Line {

    private float x, y, z, a;
    private int color;

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
