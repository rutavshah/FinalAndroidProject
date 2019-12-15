package com.example.game.Game1.Entities;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * The ball displayed on the screen.
 */
public class Ball {

    private float x;
    private float y;
    private Paint paintText = new Paint();

    Ball(int x, int y, String ballColor) {
        this.x = x;
        this.y = y;
        if (ballColor.equals("red"))
            paintText.setColor(Color.RED);
        else
            paintText.setColor(Color.YELLOW);
    }

    /**
     *
     * draws the ball on the canvas.
     * @param canvas    where the ball is drawn.
     */
    public void draw(Canvas canvas, float x) {


        canvas.drawCircle(x, this.y,
                50, paintText);

    }

    /**
     * returns the x coordinate of the ball.
     * @return      returns the x coordinate of the ball.
     */
    float getX()
    {
        return this.x;
    }
}