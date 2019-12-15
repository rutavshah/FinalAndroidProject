package com.example.game.Level2;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;

public class MainThread extends Thread implements View.OnTouchListener {
    private com.example.game.Level2.Level2view gameview;
    private SurfaceHolder surfaceholder;
    public Canvas canvas;
    private int time;
    private boolean running;

    MainThread(SurfaceHolder s, com.example.game.Level2.Level2view g) {
        super();
        this.gameview = g;
        this.surfaceholder = s;
    }

    @Override
    public void run() {
        while (this.running) {
            try {
                time++;
                canvas = this.surfaceholder.lockCanvas();
                this.gameview.update();
                this.gameview.draw(canvas);
                this.surfaceholder.unlockCanvasAndPost(canvas);
            } catch (Exception e) {
            }
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent e) {
        float x1 = e.getX();
        float y1 = e.getY();
        boolean ct = false;
        Ball b1 = gameview.left.get(0);
        Ball b2 = gameview.left.get(1);
        Ball b3 = gameview.left.get(2);
        Ball b4 = gameview.left.get(3);
        if (b1.contains(x1, y1)) {
            if (b1.isTouched())
                b1.setColor();
            b1.setTouched();
            ct = !(b1.getRed());
        } else if (b2.contains(x1, y1)) {
            if (b2.isTouched())
                b2.setColor();
            b2.setTouched();
            ct = !(b2.getRed());
        } else if (b3.contains(x1, y1)) {
            if (b3.isTouched())
                b3.setColor();
            b3.setTouched();
            ct = !(b3.getRed());
        } else if (b4.contains(x1, y1)) {
            if (b4.isTouched())
                b4.setColor();
            b4.setTouched();
            ct = !(b4.getRed());
        }

        if (ct) {
            setrun(false);
        }
        return true;
    }

    void setrun(boolean b) {
        this.running = b;
    }
}
