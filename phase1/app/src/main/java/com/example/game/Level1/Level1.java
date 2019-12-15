package com.example.game.Level1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Level1 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        String color, time, point;
        color = getIntent().getStringExtra("COLOR");
        time = getIntent().getStringExtra("TIME");
        point = getIntent().getStringExtra("POINTS");


        setContentView(new Level1view(this,time, color, point));
    }

}
