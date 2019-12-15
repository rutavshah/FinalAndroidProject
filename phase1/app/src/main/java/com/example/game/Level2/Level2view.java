package com.example.game.Level2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Level2view extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread t;
    Canvas c;
    public List<Ball> right = new ArrayList<>();
    List<Ball> left = new ArrayList<>();
    List<Line> lines = new ArrayList<>();

    public Level2view(Context c) {
        super(c);
        getHolder().addCallback(this);
        this.t = new MainThread(getHolder(), this);
        setFocusable(true);
        int difficulty = 4;
        makeBalls(difficulty);
        makePairs();
        setTarget();
        makeLines();
    }

    private void makeLines() {
        List<Pair> coords = new ArrayList<>();
        int x1, x2, y1, y2;
        for (Ball b : left) {
            while (true) {
                //Randomize again
                x1 = (int) (Math.random() * (751 - 350)) + 350;
                y1 = (int) (Math.random() * (1751 - 350)) + 350;
                x2 = (int) (Math.random() * (751 - 350)) + 350;
                y2 = (int) (Math.random() * (1751 - 350)) + 350;
                Pair c1 = Pair.create(x1, y1);
                Pair c2 = Pair.create(x2, y2);
                if (x1 < (x2 - 50) && Math.abs(y1 - y2) >= 100 &&
                        !coords.contains(c1) && !coords.contains(c2)) {
                    break;
                }
            }
            coords.add(Pair.create(x1, y1));
            coords.add(Pair.create(x2, y2));
            Line l1 = new Line(b.getX() + 100, b.getY(), x1, y1, Color.BLUE);
            Line l2 = new Line(x1, y1, x2, y2, Color.BLUE);
            Line l3 = new Line(x2, y2, b.getPair().getX() - 100, b.getPair().getY(), Color.BLUE);
            lines.add(l1);
            lines.add(l2);
            lines.add(l3);
        }
    }

    private void makePairs() {

        ArrayList<Integer> ints = new ArrayList<>();
        ints.add(0);
        ints.add(1);
        ints.add(2);
        ints.add(3);
        int r;
        for (Ball b : left) {
            r = (int) (Math.random() * (ints.size()));
            Ball b1 = right.get(ints.get(r));
            ints.remove(r);
            b.setPair(b1);
            b1.setPair(b);
        }
    }

    private void makeBalls(int number) {
        int count = 300;
        for (int i = number; i > 0; i--) {
            Ball b = new Ball(200, count, Color.BLUE);
            Ball pair = new Ball(900, count, Color.BLUE);
            right.add(pair);
            left.add(b);
            count += 400;
        }
    }

    private void setTarget() {
        int[] colors = {Color.BLACK, Color.RED};
        int r;
        r = (int) (Math.random() * right.size());
        Ball b1 = right.get(r);
        b1.setRed();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        t.setrun(true);
        t.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        this.t.onTouch(this, e);
        return true;
    }

    public void update() {
    }

    public void draw(Canvas c) {
        this.c = c;
        super.draw(c);
        c.drawColor(Color.WHITE);
        Paint p = new Paint();
        c.drawPaint(p);
        for (Ball b : right) {
            b.draw(c);
        }
        for (Ball b : left) {
            b.draw(c);
        }
        for (Line l : lines) {
            l.draw(c);
        }

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean r = true;
        while (r) {
            try {
                t.setrun(false);
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r = false;
        }
    }

}
