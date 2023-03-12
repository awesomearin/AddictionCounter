package com.example.covidtracker;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;





public class log extends AppCompatActivity {

    EditText username, passcode;
    Button login, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        username = findViewById(R.id.userinput); //the following are parameters regarding input (username and passcode) and buttons (login and cancel)
        passcode = findViewById(R.id.passput);
        login = findViewById(R.id.signinbutt);
        cancel = findViewById(R.id.cancelbutt);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //When the login button receives input...
                if (username.getText().toString().equals("awesomearin") && passcode.getText().toString().equals("pchandra77")) { //if correct, take user to the broken panel page
                    Toast.makeText(v.getContext(), "Success!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(v.getContext(), MainActivity.class));
                    finish();
                } else { //If wrong, display error Toast message and leave the user on the page, befuddled by their mistakes
                    Toast.makeText(v.getContext(), "Try again...", Toast.LENGTH_LONG).show();
                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //leave button
                startActivity(new Intent(v.getContext(), MainActivity.class));
                finish();
            }
        });

    }


}