
package com.example.game.Game1.View;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread
{

    private final SurfaceHolder surfaceHolder;
    private Level1view gameView;
    private boolean isRunning;
    public static Canvas canvas;

    MainThread(SurfaceHolder surfaceHolder, Level1view gameView)
    {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;

    }

    void setRunning(boolean isRunning)
    {
        this.isRunning = isRunning;

    }

    public void run()
    {
        while(isRunning)
        {
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gameView.update();
                    this.gameView.draw(canvas);

                }
            }
            catch(Exception ignored)
            {
            }

            finally
            {
                if(canvas!=null)
                {
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}