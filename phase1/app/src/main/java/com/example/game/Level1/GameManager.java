package com.example.game.Level1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import java.util.ArrayList;

import static com.example.game.Level1.MainThread.canvas;

public class GameManager {

    protected static int gridHeight;
    protected static int gridWidth;
    protected ChildBall b;
    protected Button left;
    protected Button right;
    Paint paintText = new Paint();
    int score;
    boolean flag = false;
    String ballColor;
    int points;
    public static int finalScore=0;

    ArrayList<Barrier> items = new ArrayList<Barrier>();

    public GameManager(int height, int width, String ballColor, String points) {
        gridHeight = height;
        gridWidth = width;
        paintText.setTextSize(60);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
        this.ballColor = ballColor;
        if (points.equals("EASY"))
            this.points = 10;
        else
            this.points = 15;
        paintText.setColor(Color.WHITE);

        score = 0;
    }

    public void createItems()
    {
        Barrier b1 = new Barrier(0);
        items.add(b1);
        Barrier b2 = new Barrier(10);
        items.add(b2);
        Barrier b3 = new Barrier(20);
        items.add(b3);
        Barrier b4 = new Barrier(30);
        items.add(b4);

        b = new ChildBall(15, 40, ballColor);

        // button
        left = new Button(Level1view.leftButtonImage, 100, 1800);
        right = new Button(Level1view.rightButtonImage, 800, 1800);

    }

    public void update()
    {
        for(int i = 0; i<items.size();i++)
        {
            items.get(i).move();
            Barrier temp = items.get(items.size()-1);
//            if(temp.height*Level1view.charHeight == 1680)
            if(temp.height == 39)
            {
                if(!temp.contains(b.x)) {
                    this.flag = true;
                    this.draw(Level1view.can);
                    collision();
                }
            }
        }
        delete_and_add_barrier();
    }

    public void draw(Canvas canvas)
    {

        if(flag)
        {
            canvas.drawText("YOU LOSE", 400, 800, paintText);
            Level1view.gameRunning = false;
        }
        else if(score < points) {
            canvas.drawText("SCORE:" + this.score, 60, 55, paintText);
        }
        if(score == points)
        {
            canvas.drawText("YOU WON", 400, 800, paintText);
            Level1view.gameRunning = false;
        }

        for(int i = 0; i<items.size(); i++)
        {
            items.get(i).draw(canvas);
        }
        b.draw(canvas);
        left.draw(canvas);
        right.draw(canvas);
    }

    public void delete_and_add_barrier()
    {
        for(int i=0;i<items.size();i++)
        {
            Barrier temp = items.get(i);
            if(temp.height > 40)
            {
                removeBarrier(temp);
                this.score += 1;
                finalScore += 1;
                add_barrier_at_top();
            }
        }
    }

    private void removeBarrier(Barrier b)
    {
        items.remove(b);
    }

    private void add_barrier_at_top()
    {
        int newBarrierHeight = items.get(0).height;
        newBarrierHeight -= 10;
        // Adds barrier at the top
        Barrier b = new Barrier(newBarrierHeight);
        items.add(0, b);
    }

    public void buttonPressed(int x, int y)
    {
        if(left.contains(x, y)) {
            b.moveLeft();
        }
        else if(right.contains(x, y)) {
            b.moveRight();
        }
    }

    public void collision()
    {
//        Level1view.gameRunning = false;
        finalScore = score;
    }
}