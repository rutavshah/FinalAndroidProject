package com.example.game;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * The Game stats class for viewing the high score.
 */
public class GameStats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_stats);
        final String username = getIntent().getStringExtra("name");

        final TextView  Level1 = findViewById(R.id.Level1);
        final TextView Level2 = findViewById(R.id.Level2);
        final TextView Level3 = findViewById(R.id.Level3);
        assert username != null;
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(username);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Level1.setText(Long.toString((Long)dataSnapshot.child("level1").getValue()));
                Level2.setText(Long.toString((Long)dataSnapshot.child("level2").getValue()));
                Level3.setText(Long.toString((Long)dataSnapshot.child("level3").getValue()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
