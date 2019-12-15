package com.example.game.Level3.UserInterface;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.game.R;

/**
 * The Customise.
 */
public class Customise extends Activity {

    /**
     * The Color.
     */
    RadioGroup color, /**
     * The Difficulty group.
     */
    difficultyGroup;
    /**
     * The Background.
     */
    RadioButton background, /**
     * The Difficulty.
     */
    difficulty;
    /**
     * The Apply.
     */
    Button apply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customise);
        apply = findViewById(R.id.apply);
        color = findViewById(R.id.radioGroup);
        difficultyGroup = findViewById(R.id.radioGroup3);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                background = findViewById(color.getCheckedRadioButtonId());
                difficulty = findViewById(difficultyGroup.getCheckedRadioButtonId());
                Intent i = new Intent(Customise.this, Level3.class);
                i.putExtra("BACKGROUND", background.getText().toString());
                i.putExtra("DIFFICULTY", difficulty.getText().toString());
                String username = getIntent().getStringExtra("name");
                i.putExtra("name", username);
                startActivity(i);
            }
        });
    }
}





