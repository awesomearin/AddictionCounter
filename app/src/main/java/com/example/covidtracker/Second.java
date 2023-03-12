package com.example.covidtracker;

import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;

import android.view.View;


import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.content.Intent;
import android.widget.Toast;

import com.example.covidtracker.databinding.ActivitySecondBinding;

public class Second extends AppCompatActivity {

    private ActivitySecondBinding binding;
    public String[] times = new String[]{"None", "Short", "Medium", "Long"};
    public String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //new intents in order to navigate with confirm and cancel buttons
        Intent inter = new Intent(this, MainActivity.class);
        Intent intentium = new Intent(this, log.class);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Spinner spinnercountries; //declared spinners and connects them to xml counterparts
        Spinner countselect2 = findViewById(R.id.count_select2);
        spinnercountries = (Spinner) findViewById(R.id.count_select);
        ArrayAdapter<String> countadapt = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, times); //adapts the array (duh) and applies it to the spinners
        spinnercountries.setAdapter(countadapt);
        countselect2.setAdapter(countadapt);

        binding.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //sends you back to the mainactivity
                startActivity(inter);
                finish();
            }
        });

        Spinner finaltime = spinnercountries;
        binding.confirmButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
             if (finaltime.getSelectedItem().toString() != "None") {
                 if (finaltime.getSelectedItem().toString() == "Short") {
                     time = "10";
                     inter.putExtra("time", time);

                 } else if (finaltime.getSelectedItem().toString() == "Medium") {
                     time = "60";
                     inter.putExtra("time", time);

                 } else if (finaltime.getSelectedItem().toString() == "Long") {
                     time = "120";
                     inter.putExtra("time", time);

                 }
                    startActivity(inter);
                    finish();
                } else {
                 Toast.makeText(getApplicationContext(), "Fill in data first!", Toast.LENGTH_LONG).show(); //error handling
             }
            }
        });

    }
}