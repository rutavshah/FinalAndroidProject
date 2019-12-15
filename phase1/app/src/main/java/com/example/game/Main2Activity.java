package com.example.game;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.game.Level1.Customise1;
import com.example.game.Level1.Level1;
import com.example.game.Level2.Level2;
import com.example.game.Level3.Level3;
import com.example.game.Level3.customise;


public class Main2Activity extends Activity implements View.OnClickListener{
    private Button b1, b2, b3, b4;
    private int saveGame1Count = 0;
    private int saveGame2Count = 0;
    private int saveSecondGameCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button1:
                Intent i = new Intent(this, Customise1.class);
                saveGame1Count = 0;
                startActivity(i);
                break;
            case R.id.button2:
                Intent i2 = new Intent(this, Level2.class);
                saveSecondGameCount = 0;
                startActivity(i2);
                break;
            case R.id.button3:
                Intent i3 = new Intent(this, customise.class);
                saveGame2Count = 0;
                startActivity(i3);
                break;
            case R.id.button4:
                if (saveGame1Count == 0 && saveGame2Count == 0){
                    saveData(String.valueOf(Integer.valueOf(loadData("guest", "total"))+ com.example.game.Level1.GameManager.finalScore+com.example.game.Level3.GameManager.score),"guest", "total");
                    saveGame1Count++;
                    saveGame2Count++;
                }else if(saveGame1Count == 0){
                    saveData(String.valueOf(Integer.valueOf(loadData("guest", "total"))+ com.example.game.Level1.GameManager.finalScore),"guest", "total");
                    saveGame1Count++;
                } else if(saveGame2Count == 0){
                    saveData(String.valueOf(Integer.valueOf(loadData("guest", "total"))+ com.example.game.Level3.GameManager.score),"guest", "total");
                    saveGame2Count++;
                }
                Intent j = new Intent(this, GameStats.class);
                startActivity(j);
                break;
        }
    }

    public void saveData(String score, String user, String game) {
        SharedPreferences sharedPreferences = getSharedPreferences(user, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(game, score);

        editor.apply();
    }

    public String loadData(String user, String game) {
        SharedPreferences sharedPreferences = getSharedPreferences(user, MODE_PRIVATE);
        return sharedPreferences.getString(game, "0");
    }
}