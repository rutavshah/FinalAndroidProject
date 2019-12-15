package com.example.game.Level3.UserInterface;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import com.example.game.Level3.Entities.Ball;
import com.example.game.Level3.GameLogic.GameManager;
import com.example.game.R;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The type Game view.
 */
@SuppressLint("ViewConstructor")
public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private GameManager level1Manager;
    private String difficulty;
    /**
     * The Username.
     */
    public String username;
    /**
     * The Background.
     */
    Drawable background;
    /**
     * The Heart.
     */
    Drawable heart;
    /**
     * The Colours.
     */
    ArrayList<Integer> colours;
    private MediaPlayer success;
    private MediaPlayer failure;
    private MediaPlayer whooshSound;
    private MediaPlayer boost;

    /**
     * Instantiates a new Game view.
     *
     * @param context    the context
     * @param background the background
     * @param difficulty the difficulty
     * @param name       the name
     */
    public GameView(Context context, String background, String difficulty, String name) {
        super(context);
        this.difficulty = difficulty;
        this.username = name;

        getHolder().addCallback(this);
        this.success = MediaPlayer.create(context, R.raw.success);
        this.failure = MediaPlayer.create(context, R.raw.failure);
        this.whooshSound = MediaPlayer.create(context, R.raw.whoosh);
        this.boost = MediaPlayer.create(context, R.raw.boost);

        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        if(background.equals("Forest"))
            this.background = getResources().getDrawable(R.drawable.forest, null);
        else
            this.background = getResources().getDrawable(R.drawable.city, null);
        this.background.setBounds(0   , 0, screenWidth, screenHeight);

        heart = getResources().getDrawable(R.drawable.heart, null);

        thread = new MainThread(getHolder(), this);

        setFocusable(true);

        colours = new ArrayList<>();
        colours.add(R.drawable.bluecircle);
        colours.add(R.drawable.greencircle);
        colours.add(R.drawable.pinkcircle);
        colours.add(R.drawable.orangecircle);
        colours.add(R.drawable.redcircle);
        colours.add(R.drawable.yellowcircle);
        colours.add(R.drawable.purplecircle);
        colours.add(R.drawable.browncircle);
        colours.add(R.drawable.seagreen);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            level1Manager.select(event.getX(), event.getY());
        }

        return super.onTouchEvent(event);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        Ball.hiddenImage = BitmapFactory.decodeResource(getResources(), R.drawable.greycircle);
        if(difficulty.equals("EASY"))
            level1Manager = new GameManager(bmpColours(), heart,90, success,failure,whooshSound, boost, username);
        else
            level1Manager = new GameManager(bmpColours(), heart,50, success,failure,whooshSound, boost, username);

        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();

            } catch(InterruptedException e){
                e.printStackTrace();
            }
            retry = false;
        }
    }

    /**
     * Update the game states.
     */
    public void update() {
        level1Manager.update();
    }

    private ArrayList<Bitmap> bmpColours() {
        ArrayList<android.graphics.Bitmap> bitmapColours = new ArrayList<>();
        Collections.shuffle(colours);
        for(int i = 0; i < 9; i++){
            bitmapColours.add(BitmapFactory.decodeResource(getResources(), colours.get(i)));
        }
        return bitmapColours;
    }

    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        this.background.draw(canvas);
        level1Manager.draw(canvas);
    }
}
