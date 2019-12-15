package com.example.game.Level1;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Button {

    int x,y;
    Bitmap image;

    public Button(Bitmap bmp, int x, int y)
    {
        this.x = x;
        this.y = y;
        this.image = bmp;
    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(image, x, y, null);
    }

    protected boolean contains(int pressedX, int pressedY)
    {
        return ((this.x <= pressedX && pressedX <= this.x+image.getWidth())&&(this.y <= pressedY && pressedY <= this.y+image.getHeight()));
    }
}