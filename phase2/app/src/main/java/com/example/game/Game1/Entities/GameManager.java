package com.example.game.Game1.Entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import androidx.annotation.NonNull;

import com.example.game.Game1.View.Level1view;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Manages how the game is run and displayed on the screen
 * */

public class GameManager implements ValueEventListener {

    private Ball b;
    private ArrayList<Barrier> items = new ArrayList<>();
    private String ballColor;
    private int points;
    private ArrayList<Life> lives = new ArrayList<>();
    private int livesX;
    private static int finalScore=0;
    private float x;
    private DatabaseReference reference;
    private boolean checker;
    private ArrayList <Integer> hidden = new ArrayList<>();
    private ArrayList<Integer> hiddenChecker = new ArrayList<>();
    private Canvas canvas;
    private Algorithms algorithm;
    private String username;
    private boolean flag = false;
    private Paint paintText = new Paint();


    public GameManager(String points, String ballColor, String username, int gameMode){

        this.x = 500;
        checker = true;
        this.username = username;
        setGameMode(gameMode);
        hidden.add(1);
        hidden.add(2);
        hidden.add(4);
        this.reference = FirebaseDatabase.getInstance().getReference().child(username);
        paintText.setTextSize(60);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
        paintText.setColor(Color.WHITE);
        this.ballColor = ballColor;

        if (points.equals("EASY"))
            this.points = 15;
        else
            this.points = 20;

        paintText.setColor(Color.WHITE);
        this.livesX = 940;
        createLives();

    }

    /**
     * Creates the lives for the game 1
     */
    private void createLives()
    {
        for(int i = 0; i < 3; i++)
        {
            lives.add(new Life(Level1view.heart, livesX, 0));
            livesX -= 70;
        }
    }

    /**
     * Choses between the first, second or third game.
     * @param gameMode      gives the int value to signify which game to run.
     */
    private void setGameMode(int gameMode){
        if (gameMode == 1){
            algorithm = new GameMode1();
        } else if (gameMode == 2){
            algorithm = new GameMode2();
        } else if (gameMode == 3){
            algorithm = new GameMode3();
        }
    }
    /**
     * Creates the items displayed on the screen
     */
    public void createItems()
    { algorithm.createBarriers(items);
        b = new Ball(100, 1700, ballColor);
    }

    /**
     * Updates the state of the game objects.
     * */
    public void update()
    {
        for(int i = 0; i<items.size();i++)
        {
            items.get(i).move();
            Barrier temp = items.get(items.size()-1);
            if(temp.getHeight() == 39)
            {
                collides(temp);
            }
        }
        deleteAndAddBarrier();
    }


    private void collides(@org.jetbrains.annotations.NotNull Barrier temp)
    {
        if(!temp.contains((int)this.x)) {
            if(this.lives.size() == 1) {
                this.flag = true;
                int lastScore = hiddenChecker.get(hiddenChecker.size() -1);
                hiddenChecker.add(finalScore - lastScore);
                if (hiddenChecker.equals(hidden)){

                    this.flag = false;
                    finalScore = points;

                }
                this.draw(canvas);
            }
            else{
                this.lives.remove(this.lives.size()-1);
                if (hiddenChecker.size()== 0){
                    hiddenChecker.add(finalScore);
                }
                else{
                    int lastScore = hiddenChecker.get(hiddenChecker.size() -1);
                    hiddenChecker.add(finalScore - lastScore);
                }
                this.items.clear();
                createItems();
            }
        }
    }

    public boolean stopGame(){
        return checker;
    }

    /**
     * Draws out the game objects on the canvas.
     * @param canvas    where to draw the objects
     */
    public boolean draw(Canvas canvas)

    {   this.canvas = canvas;
        boolean outcome = displayFinalMessage();
        if(outcome) {
            return true;
        }
        for(int i = 0; i<items.size(); i++)
        {
            items.get(i).draw(canvas);
        }
        b.draw(canvas, x);
        lives.get(0).draw(canvas);
        for(Life l:lives)
        {
            l.draw(canvas);
        }
        return false;
    }

    private boolean displayFinalMessage()
    {
        if(flag)
        {
            canvas.drawText("YOU LOSE", 400, 800, paintText);
            deleteAllBarriers();
            checker = false;
            this.reference.addValueEventListener(this);
        }
        else if(finalScore < points) {
            canvas.drawText("SCORE:" + finalScore, 60, 55, paintText);
            String tempUsername;
            if(username.length() < 5)
            {
                tempUsername = username;
            }
            else
            {
                tempUsername = username.substring(0, 5);
            }
            canvas.drawText(tempUsername, 500, 55, paintText);
        }
        else if(finalScore == points)
        {
            this.reference.addValueEventListener(this);
            checker = false;
            gameStop();
            return true;
        }
        return false;
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        Long oldScore = (Long) dataSnapshot.child("level1").getValue();
        try {
            if (oldScore < finalScore) {
                this.reference.child("level1").setValue(finalScore);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void gameStop(){
        try
        {
            Thread.sleep(1000);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        finalScore = 0;
        System.exit(0);
    }
    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

    /**
     * Deletes the barrier which has reached below the screen and creates a new one at the top.
     * This prevents any memory leaks of our code.
     */
    private void deleteAndAddBarrier()
    {
        for(int i=0;i<items.size();i++)
        {
            Barrier temp = items.get(i);
            if(temp.getHeight() > 40)
            {
                removeBarrier(temp);
                if (checker){
                finalScore += 1;}
                addBarrierAtTop();

            }
        }
    }

    private void deleteAllBarriers()
    {
        this.items.clear();
    }

    /**
     * Helper method to remove the barrier that have gone from the screen.
     * @param b     barrier which has gone off the screen.
     */
    private void removeBarrier(Barrier b)
    {
        items.remove(b);
    }

    /**
     * Helper to add the barrier to the top of the screen
     */
    private void addBarrierAtTop()
    {   float newBarrierHeight = items.get(0).getHeight();
        newBarrierHeight -= 10;
        Barrier c = algorithm.addBarrierAtTop(newBarrierHeight);
        items.add(0, c);
    }


    /**
     * Moves the ball by setting the x coordinate of the ball as x.
     * @param x     the x coordinate of the ball.
     */
    public void ballMove(float x)
    {
        setX(x);
    }

    private void setX(float x) {
        this.x = x;
    }
}