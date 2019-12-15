package com.example.game.Level3.UserInterface;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.game.R;

/**
 * The type Level 3.
 */
public class Level3 extends Activity {
    private MediaPlayer backgroundMusic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        backgroundMusic = MediaPlayer.create(this, R.raw.backgroundmusic);
        backgroundMusic.setLooping(true);
        backgroundMusic.start();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        String background, difficulty;
        background = getIntent().getStringExtra("BACKGROUND");
        difficulty = getIntent().getStringExtra("DIFFICULTY");
        String username = getIntent().getStringExtra("name");
        if (background != null) {
            setContentView(new GameView(this, background, difficulty, username));
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        backgroundMusic.release();
        backgroundMusic = null;

    }


}