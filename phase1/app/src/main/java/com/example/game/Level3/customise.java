package com.example.game.Level3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.game.R;

public class customise extends Activity {

    RadioGroup color, time, points;
    RadioButton col, tim, point;
    Button apply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customise);
        apply = findViewById(R.id.apply);
        color = findViewById(R.id.radioGroup);
        time = findViewById(R.id.radioGroup2);
        points = findViewById(R.id.radioGroup3);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                col = findViewById(color.getCheckedRadioButtonId());
                tim = findViewById(time.getCheckedRadioButtonId());
                point = findViewById(points.getCheckedRadioButtonId());
                Intent i = new Intent(customise.this, Level3.class);
                i.putExtra("COLOR", col.getText().toString());
                i.putExtra("TIME", tim.getText().toString());
                i.putExtra("POINTS", point.getText().toString());
                startActivity(i);
            }
        });
    }
}





