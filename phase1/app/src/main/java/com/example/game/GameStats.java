package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.game.Level1.GameManager;

public class GameStats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_stats);
        TextView TotalScore = (TextView)findViewById(R.id.TotalScore);
        TextView Level1 = (TextView)findViewById(R.id.Level1);
        TextView Level2 = (TextView)findViewById(R.id.Level2);
        TextView Level3 = (TextView)findViewById(R.id.Level3);

        Level1.setText(String.valueOf(com.example.game.Level1.GameManager.finalScore));
        Level3.setText(String.valueOf(com.example.game.Level3.GameManager.score));
        TotalScore.setText(loadData("guest", "total"));


//        int scoreLevel1 = getIntent().getIntExtra("your output", 0);
//        int scoreLevel2 = getIntent().getIntExtra("your output", 0);
//        int scoreLevel3 = getIntent().getIntExtra("your output", 0);
//        SharedPreferences set = getSharedPreferences("ball", Context.MODE_PRIVATE);
//        int total = getIntent().getIntExtra("total",0);
//        total += scoreLevel1 + scoreLevel2 +scoreLevel3;
//
//
//        Level1.setText(scoreLevel1);
//        Level2.setText(scoreLevel2);
//        Level3.setText(scoreLevel3);
//        TotalScore.setText(total);
//        SharedPreferences.Editor editor = set.edit();
//        editor.putInt("total",total);
//        editor.apply();/*commit?*/

    }

    public String loadData(String user, String game) {
        SharedPreferences sharedPreferences = getSharedPreferences(user, MODE_PRIVATE);
        return sharedPreferences.getString(game, "0");
    }
}
