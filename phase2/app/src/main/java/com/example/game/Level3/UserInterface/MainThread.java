package com.example.game.Level3.UserInterface;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * The type Main thread.
 */
public class MainThread extends Thread {
    private GameView gameView;
    private final SurfaceHolder surfaceHolder;
    private boolean running;
    /**
     * The constant canvas.
     */
    public static Canvas canvas;


    /**
     * Instantiates a new Main thread.
     *
     * @param surfaceHolder the surface holder
     * @param gameView      the game view
     */
    MainThread(SurfaceHolder surfaceHolder, GameView gameView) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;

    }

    @Override
    public void run()
    {

        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount =0;
        int targetFPS = 30;
        long targetTime = 1000/ targetFPS;


        while(running) {
            startTime = System.nanoTime();
            canvas = null;
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gameView.update();
                    this.gameView.draw(canvas);
                }
            } catch (Exception ignored) {
            }
            finally{
                if(canvas!=null)
                {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                    catch(Exception e){e.printStackTrace();}
                }
            }

            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime-timeMillis;

            try{
                sleep(waitTime);
            }
            catch(Exception e){
                e.printStackTrace();
            }

            totalTime += System.nanoTime()-startTime;
            frameCount++;
            if(frameCount == targetFPS)
            {
                double averageFPS = 1000 / ((totalTime / frameCount) / 1000000);
                frameCount =0;
                totalTime = 0;
                System.out.println(averageFPS);
            }
        }
    }

    /**
     * Sets running.
     *
     * @param isRunning the is running
     */
    void setRunning(boolean isRunning) {
        running = isRunning;
    }
}