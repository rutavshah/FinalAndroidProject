package com.example.game.Level2.Presenter;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.game.Level2.View.MainThread;

/**
 * The type Game view.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private GameManager gameManager;
    /**
     * The Game mode.
     */
    public int gameMode;
    /**
     * difficulty == hard true/false
     */
    public boolean hard;
    /**
     * The Username.
     */
    public String username;

    /**
     * Instantiates a new Game view.
     *
     * @param context  the context
     * @param gameMode the game mode
     * @param hard     the hard
     * @param username the username
     */
    public GameView(Context context, int gameMode, boolean hard, String username) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
        this.gameMode = gameMode;
        this.hard = hard;
        this.username = username;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gameManager = new GameManager(gameMode, hard, this.username);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            gameManager.buttonPressed(event.getX(), event.getY());
        }
        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        gameManager.draw(canvas);
    }
}
