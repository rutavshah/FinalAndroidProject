package com.example.game.Level3;

import android.graphics.Bitmap;
import android.graphics.Canvas;

class Ball {

    private Bitmap currentImage;
    private Bitmap image;
    static Bitmap hiddenImage;
    private int x, y;
    private boolean touched;
    private boolean score;
    private boolean win;
//    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
//    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    Ball (Bitmap bmp, int x, int y) {
        currentImage = bmp;
        image = bmp;
        this.x = x;
        this.y = y;
        touched = false;
    }

    void draw(Canvas canvas) {
        canvas.drawBitmap(currentImage, x, y, null);
    }

    void hide() {
        currentImage = hiddenImage;
    }

    void show() {
        currentImage = image;
    }

    boolean isTouched() {
        return touched;
    }


    boolean contains(float touchX, float touchY) {
        return x <= touchX && touchX <= x + image.getWidth()
                && y <= touchY && touchY <= y + image.getHeight();
    }


    void changeColour(Bitmap newBmp) {
        this.image = newBmp;
    }

    void setTouched(boolean b){
        touched = b;
    }




    boolean equals(Ball that) {
        return this.currentImage.sameAs(that.currentImage);
    }
}
