package com.example.game.Level3;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class GameManager {
    private ArrayList<Ball> balls;
    private Ball memoryBall;
    private int showCount;
    private int color;
    //    private int hiddenCount;
    private boolean hiddenState;
    public static int score;
    public int time;
    private ArrayList<Bitmap> bitmapColours;
    private int tries;
    private int point;

    GameManager(ArrayList<Bitmap> bitmapColours, int itme, int color, int p) {
        balls = new ArrayList<>();
        hiddenState = false;
        time = itme;
        this.color = color;
        this.point = p;
        showCount = 0;
        score = 0;
        tries = 0;
        this.bitmapColours = bitmapColours;
        Collections.shuffle(bitmapColours);
        balls.add(new Ball(bitmapColours.get(0), 100,100));
        balls.add(new Ball(bitmapColours.get(1), 450, 100));
        balls.add(new Ball(bitmapColours.get(2),  800, 100));

        balls.add(new Ball(bitmapColours.get(3), 100,550));
        balls.add(new Ball(bitmapColours.get(4), 450, 550));
        balls.add(new Ball(bitmapColours.get(5), 800, 550));

        balls.add(new Ball(bitmapColours.get(6), 100,1000));
        balls.add(new Ball(bitmapColours.get(7), 450, 1000));
        balls.add(new Ball(bitmapColours.get(8), 800, 1000));

        memoryBall = new Ball(bitmapColours.get(ThreadLocalRandom.current().nextInt(0, 9)), 450, 1500);
        memoryBall.hide();
    }

//    boolean isHiddenState() {
//        return hiddenState;
//    }

//    int getScore() {
//        return score;
//    }

    void draw(Canvas canvas) {
        canvas.drawColor(color);
        for (Ball ball: balls) {
            if (ball != null)
                ball.draw(canvas);
        }

        memoryBall.draw(canvas);
        Paint scorePaint = new Paint();
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(150);
        StringBuilder messageString = new StringBuilder();
        Paint messagePaint = new Paint();
        messagePaint.setColor(Color.WHITE);
        if (score == 0) {
            messagePaint.setTextSize(80);
            if(point == 3)
                messageString.replace(0, messageString.length(), "GET 3 To win");
            else
                messageString.replace(0, messageString.length(), "Get 5 to win");
        } else if (score == 1){
            messagePaint.setTextSize(75);
            if(point == 3)
                messageString.replace(0, messageString.length(), "Two more");
            else
                messageString.replace(0, messageString.length(), "Four more!!!");
        } else if (score == 2) {
            messagePaint.setTextSize(120);
            if(point == 3)
                messageString.replace(0, messageString.length(), "One more!");
            else
                messageString.replace(0, messageString.length(), "Three more!!!");
        } else if (score == 3) {
            messagePaint.setTextSize(80);
            if(point == 3)
                messageString.replace(0, messageString.length(), "Congrats, you won!");
            else
                messageString.replace(0, messageString.length(), "Two more!!!");
        } else if (score == 4) {
            messagePaint.setTextSize(80);
            if(point == 3)
                messageString.replace(0, messageString.length(), "Congrats, you won!");
            else
                messageString.replace(0, messageString.length(), "One more!!!");
        } else if (score == 5) {
            messagePaint.setTextSize(80);
            messageString.replace(0, messageString.length(), "Congrats, you won!");
        }


        canvas.drawText(messageString.toString(), 100, 1900, messagePaint);

        canvas.drawText(String.valueOf(score), 800, 1900, scorePaint);

    }

    void update() {
        if (!hiddenState) {
            if (showCount == time) {
                for (Ball b : balls) {
                    b.hide();
                    memoryBall.show();
                }
                hiddenState = true;
            } else if (showCount < time) {
                showCount++;
            }
        }

        if (hiddenState) {
            for (Ball b: balls){
                if (b.isTouched() && b.equals(memoryBall)) {
                    score++;
                    resetGame();
                } else if (tries == 3) {
                    score = 0;
                    resetGame();
                }
            }
        }


    }


    void select(float touchX, float touchY) {
        if (hiddenState) {
            for (Ball b: balls){
                if (b.contains(touchX, touchY) && !b.isTouched()) {
                    b.show();
                    b.setTouched(true);
                    tries++;
                }
            }
        }
    }

    private void resetGame(){
//        randomizeColours();
        showCount = 0;
        hiddenState = false;
        tries = 0;

        Collections.shuffle(bitmapColours);
        for(int i = 0; i < 9; i++) {
            balls.get(i).changeColour(bitmapColours.get(i));
            balls.get(i).show();
            balls.get(i).setTouched(false);
        }
        memoryBall.changeColour(bitmapColours.get(ThreadLocalRandom.current().nextInt(0, 9)));
        memoryBall.hide();
    }


}
