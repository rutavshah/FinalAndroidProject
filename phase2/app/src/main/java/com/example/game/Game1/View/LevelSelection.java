package com.example.game.Game1.View;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.game.R;

public class LevelSelection extends Activity implements View.OnClickListener {
    String username;
    String background, ballColor, difficulty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_selection);
        Button b1 = findViewById(R.id.button7);
        Button b2 = findViewById(R.id.button8);
        Button b3 = findViewById(R.id.button9);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        username = getIntent().getStringExtra("name");


    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button7:
                Intent intent = new Intent(this, Level1.class);
                intent.putExtra("name", username);
                background = getIntent().getStringExtra("COLOR");

                ballColor = getIntent().getStringExtra("TIME");
                difficulty = getIntent().getStringExtra("POINTS");
                intent.putExtra("COLOR", background);
                intent.putExtra("TIME", ballColor);
                intent.putExtra("POINTS", difficulty);

                intent.putExtra("gameMode", 1);
                startActivity(intent);
                break;
            case R.id.button8:
                Intent intent2 = new Intent(this, Level1.class);
                intent2.putExtra("name", username);
                background = getIntent().getStringExtra("COLOR");

                ballColor = getIntent().getStringExtra("TIME");
                difficulty = getIntent().getStringExtra("POINTS");
                intent2.putExtra("COLOR", background);
                intent2.putExtra("TIME", ballColor);
                intent2.putExtra("POINTS", difficulty);
                intent2.putExtra( "gameMode", 2);
                startActivity(intent2);
                break;

            case R.id.button9:
                Intent intent3 = new Intent(this, Level1.class);
                intent3.putExtra("name", username);
                background = getIntent().getStringExtra("COLOR");

                ballColor = getIntent().getStringExtra("TIME");
                difficulty = getIntent().getStringExtra("POINTS");
                intent3.putExtra("COLOR", background);
                intent3.putExtra("TIME", ballColor);
                intent3.putExtra("POINTS", difficulty);
                intent3.putExtra("gameMode", 3);
                startActivity(intent3);
                break;
        }
    }}
