package com.example.game.Level2.View;


import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.example.game.Level2.Presenter.GameView;

/**
 * The type Main thread.
 */
public class MainThread extends Thread {
    private SurfaceHolder surfaceHolder;
    private GameView gameView;
    private boolean running;


    /**
     * Instantiates a new Main thread.
     *
     * @param surfaceHolder the surface holder
     * @param gameView      the game view
     */
    public MainThread(SurfaceHolder surfaceHolder, GameView gameView) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
    }

    /**
     * Sets running.
     *
     * @param running the running
     */
    public void setRunning(boolean running) {
        this.running = running;
    }

    public void run() {
        while (running) {
            Canvas canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gameView.draw(canvas);

                }
            } catch (Exception e) {
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}