package com.example.covidtracker;


import static java.util.concurrent.TimeUnit.SECONDS;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import com.example.covidtracker.databinding.ActivityMainBinding;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationBuilderWithBuilderAccessor;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


import android.view.View;

import android.content.Intent;
import android.widget.TextView;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;
    public TextView tv;
    public String watcher;
    public int tims;
    boolean playnotif = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent tologin = new Intent(this, Second.class);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        tv = findViewById(R.id.stopwatch);
        Bundle gettimer = getIntent().getExtras();

        if (gettimer != null) { //if there is something in bundy, extract it and put into actual1 and actual2
            watcher = gettimer.getString("time");
            tims = Integer.parseInt(watcher);
            final ScheduledExecutorService ses =  Executors.newScheduledThreadPool(1);
            final Runnable runitdown = new Runnable() {
                public void run() {
                    System.out.println(tims);
                    if (watcher != null) {
                        watcher = String.valueOf(tims);
                        tv.setText(watcher);
                    }
                    tims--;
                    if (tims < 0) {
                        System.out.println("Time's up!!");
                        tv.setText("Stop!");
                        playnotif=true;
                        ses.shutdown();
                        if (playnotif == true) {
                            System.out.println("hello");
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                NotificationChannel nc =  new NotificationChannel("My backup notif", "Alert!", NotificationManager.IMPORTANCE_DEFAULT);
                                NotificationManager manager =  getSystemService(NotificationManager.class);
                                manager.createNotificationChannel(nc);
                            }
                            NotificationCompat.Builder ncb = new NotificationCompat.Builder(MainActivity.this, "My Notif");
                            ncb.setContentTitle("Time is Up!");
                            ncb.setContentText("You have exceeded your time limit!");
                            ncb.setSmallIcon(R.drawable.ic_launcher_background);
                            ncb.setAutoCancel(true);

                            NotificationManagerCompat nmc = NotificationManagerCompat.from(MainActivity.this);
                            nmc.notify(1, ncb.build());
                        }
                    }
                }

            };

            ses.scheduleAtFixedRate(runitdown, 0, 1, SECONDS);
        }



        binding.adminaccess.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), log.class));
                finish();
            }
        });

        binding.goalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(tologin);
            }
        });



    }



}
