package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ActivityManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.covidtracker.databinding.ActivityManagementBinding;

import java.util.List;
import java.util.Map;

public class management extends AppCompatActivity {

    private ActivityManagementBinding bind;
    public TextView television;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);
        bind = ActivityManagementBinding.inflate(getLayoutInflater());
        television=findViewById(R.id.appstats);
        Switch simplyswitch = findViewById(R.id.toggler);
        Button escapism = findViewById(R.id.escape);
        ActivityManager am = (ActivityManager) getSystemService(this.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfo = am.getRunningAppProcesses();
        int finaldawn = 0;
        for (int i = 0; i < runningAppProcessInfo.size(); i++) {
            finaldawn++;
        }
        System.out.println(finaldawn);
        if (finaldawn == 1) {
            television.setText("You only have " + finaldawn + " apps running. Awesome!");
        } else if (finaldawn >1) {
            television.setText("You have " + finaldawn + " apps running. Maybe close some to keep the battery healthy!");
        } else if (finaldawn >3) {
            television.setText("Yikes, that's " + finaldawn + " apps running. Definitely take action...");
        }

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