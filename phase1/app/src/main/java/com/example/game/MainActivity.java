package com.example.game;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.game.Level1.Level1;
import com.example.game.Level3.Level3;


public class MainActivity extends Activity implements View.OnClickListener {
    EditText name, password;
    Button login, register;
    private final String ACCOUNTS = "accounts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main2);
        name = (EditText)findViewById(R.id.name);
        password = (EditText)findViewById(R.id.password);
        login = findViewById(R.id.login);
        login.setOnClickListener(this);
        register = findViewById(R.id.register);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                // String name1 = name.getText().toString();
                // String name2 = name.getText().toString();
                //do check if data aldready stored


                 System.out.println("123");
//                String username1 = name.getText().toString();
//                String pass1 = password.getText().toString();
                Intent i = new Intent(this, Main2Activity.class);
                if (contains(name.getText().toString())){
                    if (password.getText().toString().equals(loadData(name.getText().toString()))){
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(this, "Wrong username/password!", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
            case R.id.register:
////                 String username = name.getText().toString();
//                 String pass =
                System.out.println("1234");
                saveData(name.getText().toString(), password.getText().toString());
                break;
        }
    }

    public void saveData(String username, String password) {
        SharedPreferences sharedPreferences = getSharedPreferences(ACCOUNTS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        System.out.println("12345");

        editor.putString(username, password);

        editor.apply();
    }

    public String loadData(String user) {
        SharedPreferences sharedPreferences = getSharedPreferences(ACCOUNTS, MODE_PRIVATE);
        return sharedPreferences.getString(user, "0");
    }

    public boolean contains(String user){
        SharedPreferences sharedPreferences = getSharedPreferences(ACCOUNTS, MODE_PRIVATE);
        return sharedPreferences.contains(user);
    }
}