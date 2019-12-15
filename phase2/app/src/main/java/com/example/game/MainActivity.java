package com.example.game;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;


/**
 * The activity for log in page.
 */
public class MainActivity extends Activity implements View.OnClickListener{
    /**
     * The Name.
     */
    EditText name, /**
     * The Password.
     */
    password;
    /**
     * The Login.
     */
    Button login, /**
     * The Register.
     */
    register;
    /**
     * The Reference.
     */
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main2);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        login.setOnClickListener(this);
        register = findViewById(R.id.register);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final String username = name.getText().toString();
        final String temppassword = password.getText().toString();
        if(username.equals("") || temppassword.equals("")){
            Toast.makeText(MainActivity.this, "Incomplete info:))", Toast.LENGTH_SHORT).show();
            return;
        }

        switch (v.getId()) {
            case R.id.login:
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(username)){
                            String passwordmade = Objects.requireNonNull(dataSnapshot.child(username).child("password").getValue()).toString();
                            if(passwordmade.equals(temppassword)){Intent gotogames = new Intent(MainActivity.this, Main2Activity.class);
                                gotogames.putExtra("name", username);
                                startActivity(gotogames);}
                            else{
                                Toast.makeText(MainActivity.this, "Incorrect Password :))", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Register first :))", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                break;
            case R.id.register:
               reference.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       if(dataSnapshot.hasChild(username)){
                           Toast.makeText(MainActivity.this, "Username taken:))", Toast.LENGTH_SHORT).show();
                       }
                       else{
                           Toast.makeText(MainActivity.this, "Successful :))", Toast.LENGTH_SHORT).show();
                           reference.child(username).child("name").setValue(username);
                           reference.child(username).child("password").setValue(temppassword);
                           reference.child(username).child("level1").setValue(0);
                           reference.child(username).child("level2").setValue(0);
                           reference.child(username).child("level3").setValue(0);
                           Intent gotogames = new Intent(MainActivity.this, Main2Activity.class);
                           gotogames.putExtra("name", username);
                           startActivity(gotogames);
                       }
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {

                   }
               });
        }
    }

}