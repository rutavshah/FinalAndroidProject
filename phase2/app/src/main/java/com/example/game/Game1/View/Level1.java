
package com.example.game.Game1.View;

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
        String background, ballcolor, difficulty;
        background = getIntent().getStringExtra("COLOR");
        ballcolor = getIntent().getStringExtra("TIME");
        difficulty = getIntent().getStringExtra("POINTS");
        String username = getIntent().getStringExtra("name");
        int gameMode = getIntent().getIntExtra("gameMode",1);
        setContentView(new Level1view(this, background, ballcolor, difficulty, username, gameMode));


    }



}
