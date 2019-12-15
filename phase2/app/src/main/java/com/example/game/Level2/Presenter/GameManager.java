package com.example.game.Level2.Presenter;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.game.Level2.Model.Algorithms;
import com.example.game.Level2.Model.GameMode1;
import com.example.game.Level2.Model.GameMode2;
import com.example.game.Level2.Model.GameMode3;
import com.example.game.Level2.Model.MakeObjects;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;

/**
 * The type Game manager.
 */
public class GameManager implements ValueEventListener {
    private String score;
    private MakeObjects makeObjects;
    private int gameMode;
    private Algorithms algorithm;
    private int tries;
    private DrawObjects drawObjects;
    private DatabaseReference reference;
    private String username;
    private int finalScore;

    /**
     * Instantiates a new Game manager.
     *
     * @param gameMode the game mode
     * @param hard     the hard
     * @param username the username
     */
    GameManager(int gameMode, boolean hard, String username) {
        int difficulty = 4;
        this.makeObjects = new MakeObjects(difficulty);
        this.gameMode = gameMode;
        setTries(hard);
        setGameMode();
        score = algorithm.getScore();
        drawObjects = new DrawObjects();
        this.username = username;
        this.reference = FirebaseDatabase.getInstance().getReference().child(username);
        finalScore = 0;
    }

    /**
     * sets the number of tries
     * @param hard boolean that represents the value of the switch on instructions screen
     */
    private void setTries(boolean hard) {
        if (hard) {
            this.tries = 5;
        } else {
            this.tries = 7;
        }
    }

    /**
     * selects gamemode based on what the user chose
     */
    private void setGameMode() {
        if (gameMode == 1) {
            this.algorithm = new GameMode1(this.makeObjects, tries);
        } else if (gameMode == 2) {
            this.algorithm = new GameMode2(this.makeObjects, tries);
        } else if (gameMode == 3) {
            this.algorithm = new GameMode3(this.makeObjects, tries);
        }
    }

    /**
     * runs the game loop and checks to see if game should be finished
     *
     * @param canvas the canvas
     */
    @SuppressLint("DefaultLocale")
    public void draw(Canvas canvas) {
        int lives = algorithm.getLives();
        boolean gameOver = algorithm.getGameOver();
        if (gameOver) {
            Paint winPaint = new Paint();
            winPaint.setColor(Color.GREEN);
            winPaint.setTextSize(100);
            canvas.drawText("YOU WIN", 300, 1000, winPaint);
            finalScore = algorithm.getGameScore();
            reference.addListenerForSingleValueEvent(this);
        } else if (lives <= 0) {
            Paint losePaint = new Paint();
            losePaint.setColor(Color.RED);
            losePaint.setTextSize(100);
            canvas.drawText("YOU LOSE", 300, 1000, losePaint);
            if (!algorithm.getUndo()) {
                drawObjects.undoButton(canvas, makeObjects);
            } else {
                reference.addListenerForSingleValueEvent(this);
            }

        } else {
            Paint scorePaint = new Paint();
            scorePaint.setColor(Color.WHITE);
            scorePaint.setTextSize(50);
            canvas.drawColor(Color.BLACK);
            drawObjects.draw(canvas, makeObjects);
            score = algorithm.getScore();
            canvas.drawText("Score: " + score, 100, 1950, scorePaint);
        }
    }

    /**
     * Button pressed.
     *
     * @param x the x
     * @param y the y
     */
    void buttonPressed(float x, float y) {
        algorithm.buttonPressed(x, y);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
    }

    /**
     * adds score to firebase
     *
     */
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        Long oldScore = (Long) dataSnapshot.child("level2").getValue();
        try {
            if (oldScore < this.finalScore)
                this.reference.child("level2").setValue(this.finalScore);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}

