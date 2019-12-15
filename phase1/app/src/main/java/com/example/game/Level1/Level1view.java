package com.example.game.Level1;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.game.R;

/**
 * Class for the view of the game
 */
public class Level1view extends SurfaceView implements SurfaceHolder.Callback{

    public static float charHeight;
    public static float charWidth;
    public static int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    public static int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    protected GameManager gameManager;
    protected static Bitmap leftButtonImage;
    protected static Bitmap rightButtonImage;
    protected static boolean gameRunning ;
    private MainThread thread;
    private String ballColor, color, point;
    // this canvas is specially to show the "YOU LOSE" message
    public static Canvas can;


    public Level1view(Context context,String ballColor, String color, String point)
    {
        super(context);
        this.point = point;
        this.ballColor = ballColor;
        this.color = color;
        getHolder().addCallback(this);
        this.thread = new MainThread(getHolder(), this);
        setFocusable(true);
        gameRunning = true;

    }

    /**
     * This is for the thread created
     * @param holder
     * @param format
     * @param width
     * @param height
     */
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {

    }

    /**
     * This is to destroy the thread
     * @param holder
     */
    public void surfaceCreated(SurfaceHolder holder)
    {
//        thread.setRunning(true);
//        thread.start();

        leftButtonImage = BitmapFactory.decodeResource(getResources(), R.drawable.leftarrow);
        rightButtonImage = BitmapFactory.decodeResource(getResources(), R.drawable.rightarrow);

        Paint paintText = new Paint();
        paintText.setTextSize(36);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
        charWidth = paintText.measureText("W");
        charHeight = (-paintText.ascent() + paintText.descent());

        // Use the letter size and screen height to determine the size of the fish tank.
        gameManager = new GameManager(
                (int) (screenHeight / charHeight), (int) (screenWidth / charWidth), point, ballColor);
        gameManager.createItems();

        thread.setRunning(true);
        thread.start();
    }

    public void surfaceDestroyed(SurfaceHolder holder)
    {
        // it might take multiple events to make the thread to stop so we have a while loop
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


    public void draw(Canvas canvas) {
        super.draw(canvas);
        can = canvas;
        if (color.equals("grass")){
            Bitmap background = BitmapFactory.decodeResource(getResources(), R.drawable.grass);
            background = Bitmap.createScaledBitmap(background, 1500, 2000, true);
            canvas.drawBitmap(background, 0, 0, null);
        }
        if (canvas != null) {
            gameManager.draw(canvas);
        }

    }

    public void update()
    {
        if(gameRunning) {
            gameManager.update();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction() == MotionEvent.ACTION_DOWN);
        {
            int touchX = (int)event.getX();
            int touchY = (int)event.getY();
            gameManager.buttonPressed(touchX, touchY);
        }
        return true;
    }
}