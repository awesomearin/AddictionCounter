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
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;
    public TextView tv;
    public String watcher;
    public int tims;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent tologin = new Intent(this, Second.class);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        tv = findViewById(R.id.stopwatch);
        Bundle gettimer = getIntent().getExtras();
        Button recos = findViewById(R.id.recs);
        if (gettimer != null) { //if there is something in bundy, extract it and put into actual1 and actual2
            watcher = gettimer.getString("time");
            String watching = watcher;
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
                        recos.setVisibility(View.VISIBLE);
                        recos.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addNotification("Time", watching);
                            }
                        });
                        tv.setText("Stop!");
                        ses.shutdown();
                    }
                }

            };
            ses.scheduleAtFixedRate(runitdown, 0, 1, SECONDS);
        } else {
            recos.setVisibility(View.GONE);
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

    private void addNotification(String tile, String mess) {
        String message = "BLANK...";
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("YOUR_CHANNEL_ID",
                    "YOUR_CHANNEL_NAME",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("YOUR_NOTIFICATION_CHANNEL_DESCRIPTION");
            mNotificationManager.createNotificationChannel(channel);
        }
        if (Integer.parseInt(mess) >= 10) {
            message = "In " + Integer.parseInt(mess) + " seconds, you could have walked around a little!";
        } else if (Integer.parseInt(mess) >=60) {
            message = "In " + Integer.parseInt(mess) + " seconds, you could have solved a math problem!";
        } else if (Integer.parseInt(mess) >= 120) {
            message = "In " + Integer.parseInt(mess) + "seconds, you could have listened to some music!";
        }
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "YOUR_CHANNEL_ID")
                .setSmallIcon(R.mipmap.ic_launcher) // notification icon
                .setContentTitle(tile) // title for notification
                .setContentText(message)// message for notification
                .setAutoCancel(true); // clear notification after click
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE);
        mBuilder.setContentIntent(pi);
        mNotificationManager.notify(0, mBuilder.build());
    }

}
