package com.example.game.Level2.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import com.example.game.R;

/**
 * The type Game 2 instructions.
 */
public class Game2Instructions extends Activity {
    private boolean hard;
    /**
     * The Username.
     */
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructions);
        Switch simpleSwitch = findViewById(R.id.switch1);
        if (simpleSwitch.isChecked()) {
            this.hard = true;
        }
    }

    /**
     * Mode 1 game.
     *
     * @param view the view
     */
    public void mode1Game(View view) {
        Intent intent = new Intent(this, Level2Activity.class);
        intent.putExtra("gameMode", "1");
        intent.putExtra("difficulty", this.hard);
        username = getIntent().getStringExtra("name");
        intent.putExtra("name", username);
        startActivity(intent);
    }

    /**
     * Mode 2 game.
     *
     * @param view the view
     */
    public void mode2Game(View view) {
        Intent intent = new Intent(this, Level2Activity.class);
        intent.putExtra("gameMode", "2");
        intent.putExtra("difficulty", this.hard);
        username = getIntent().getStringExtra("name");
        intent.putExtra("name", username);
        startActivity(intent);
    }

    /**
     * Mode 3 game.
     *
     * @param view the view
     */
    public void mode3Game(View view) {
        Intent intent = new Intent(this, Level2Activity.class);
        intent.putExtra("gameMode", "3");
        intent.putExtra("difficulty", this.hard);
        username = getIntent().getStringExtra("name");
        intent.putExtra("name", username);
        startActivity(intent);
    }

}
