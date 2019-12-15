package com.example.game.Game1.View;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.game.Game1.Entities.GameManager;
import com.example.game.Game1.Logic.Game1Presenter;
import com.example.game.R;

/**
 * Class for the view of the game
 */
@SuppressLint("ViewConstructor")
public class Level1view extends SurfaceView implements SurfaceHolder.Callback{


    private GameManager gameManager;
    public static Bitmap heart;
    public static boolean gameRunning ;
    private MainThread thread;
    private String background,ballColor, difficulty;
    public static Canvas can;
    private Game1Presenter presenter;
    private String username;
    int gameMode;



    public Level1view(Context context, String background, String ballColor, String difficulty, String username, int gameMode)
    {
        super(context);
        this.username = username;
        this.gameMode = gameMode;
        presenter = new Game1Presenter();
        this.background = background;
        this.ballColor = ballColor;
        this.difficulty = difficulty;
        getHolder().addCallback(this);
        this.thread = new MainThread(getHolder(), this);
        setFocusable(true);
        gameRunning = true;
        heart = BitmapFactory.decodeResource(getResources(), R.drawable.heart);
        heart = Bitmap.createScaledBitmap(heart, 60, 60, true);

    }
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { }

    public void surfaceCreated(SurfaceHolder holder)
    {


        Paint paintText = new Paint();
        paintText.setTextSize(36);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
        gameManager = new GameManager(difficulty,ballColor ,username, gameMode);
        gameManager.createItems();
        thread.setRunning(true);
        thread.start();
    }

    /**
     *  to destroy the surface.
     */
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        boolean retry = true;
        while(retry)
        {
            try {
                thread.setRunning(false);
                thread.join();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    /**
     * draws the bitmap and objects on the canvas.
     *
     * @param canvas    where the objects are drawn.
     */
    public void draw(Canvas canvas) {
        super.draw(canvas);
        can = canvas;
        if (background.equals("forest")){
            Bitmap background = BitmapFactory.decodeResource(getResources(), R.drawable.forest);
            background = Bitmap.createScaledBitmap(background, 1500, 2000, true);
            canvas.drawBitmap(background, 0, 0, null);
        }
        if (canvas != null) {
            presenter.draw(canvas, gameManager);}
    }

    /**
     * updates the running thread
     */
    public void update()
    {
        if(gameRunning) {
            presenter.update(gameManager);
        }
    }

    /**
     * Records the touch on the screen.
     *
     * @return      returns either touched or not.
     */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction() == MotionEvent.ACTION_MOVE) {
            int touchX = (int) event.getX();

            presenter.ballMove(touchX, gameManager);
        }
        return true;
    }

}