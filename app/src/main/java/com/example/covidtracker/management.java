package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.covidtracker.databinding.ActivityManagementBinding;

public class management extends AppCompatActivity {

    private ActivityManagementBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);
        bind = ActivityManagementBinding.inflate(getLayoutInflater());
        Switch simplyswitch = findViewById(R.id.toggler);
        Button escapism = findViewById(R.id.escape);
        simplyswitch.setChecked(false);
        simplyswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (simplyswitch.isChecked()) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    simplyswitch.setText("Dark mode is ON");
                } else {
                    simplyswitch.setText("Dark mode is OFF");
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

        escapism.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), log.class));
                finish();
            }
        });

    }
}