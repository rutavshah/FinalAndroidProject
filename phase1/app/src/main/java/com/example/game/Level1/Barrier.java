package com.example.game.Level1;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;


public class Barrier{
    private int l;
    Color colour;
    public int height;
    int start;
    int score;

    Paint paintText = new Paint();

    public Barrier(int height) {
        paintText.setTextSize(36);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
        this.height = height;
        paintText.setColor(-16041008);
        start = 5 + (int)(Math.random() * 15);
        this.score = 0;
    }


    public void draw(Canvas canvas) {


        canvas.drawRoundRect(0, height*Level1view.charHeight, start*Level1view.charWidth, (height+1)*Level1view.charHeight, 100, 100, paintText);
        int end = start + 10;
        canvas.drawRoundRect(end*Level1view.charWidth, height*Level1view.charHeight, Level1view.screenWidth, (height+1)*Level1view.charHeight, 100, 100, paintText);
//        for (int i = 0; i < 34; i++) {
//            if(!(i >= start && i<= end)) {
//                drawString(canvas, "=", height, i);
//            }
//
//        }
        try
        {
            Thread.sleep(50);
        }
        catch(InterruptedException e)
        {
            System.out.println(e);
        }
    }

    private void drawString(Canvas canvas, String s, int x, int y) {
        canvas.drawText(s, y * Level1view.charWidth, x * Level1view.charHeight, paintText);
    }

    protected void move()
    {
        this.height += 1;
    }

    protected boolean contains(int x) {
//        return ((this.start*Level1view.charWidth) <= pressedX && pressedX <= ((this.start+10)*Level1view.charWidth));
        if ((this.start) <= x && x <= (this.start + 10)) {
            this.score += 1;
            return true;
        }
        else
        {
            return false;
        }
    }

}