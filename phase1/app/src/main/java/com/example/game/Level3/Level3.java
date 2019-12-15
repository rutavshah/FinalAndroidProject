package com.example.game.Level3;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

public class Level3 extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        String color, time, point;
        color = getIntent().getStringExtra("COLOR");
        time = getIntent().getStringExtra("TIME");
        point = getIntent().getStringExtra("POINTS");
        setContentView(new GameView(this, time, color, point));
    }
}