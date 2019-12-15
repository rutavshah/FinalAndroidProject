package com.example.game.Level1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.widget.Button;

import androidx.annotation.Dimension;


public class ChildBall {

    public int x;
    public int y;
    String ballColor;
    Color color;

    Paint paintText = new Paint();

    public ChildBall(int x, int y, String ballColor) {
        this.x = x;
        this.y = y;
        paintText.setTextSize(36);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
//        this.ballColor = ballColor;
        if (ballColor.equals("red"))
            paintText.setColor(Color.RED);
        else
            paintText.setColor(Color.YELLOW);
    }

    public void moveRight() {
        if (this.x*Level1view.charWidth < Level1view.screenWidth) {
            x++;
        }
    }

    public void moveLeft() {
        if(this.y*Level1view.charHeight > 0) {
            x--;
        }
    }

    public void draw(Canvas canvas) {

        canvas.drawCircle(this.x*Level1view.charWidth, this.y*Level1view.charHeight, 50, paintText);

    }

}