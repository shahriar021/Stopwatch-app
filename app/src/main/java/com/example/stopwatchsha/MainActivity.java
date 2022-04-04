package com.example.stopwatchsha;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private boolean isRunning = false;
    private long pauseOffset = 0;
    Button btnstart,btnpause,btnreset;
    Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer =findViewById(R.id.choronomiter);
        btnstart=findViewById(R.id.btn_start);
        btnpause=findViewById(R.id.btn_pause);
        btnreset=findViewById(R.id.btn_reset);

        chronometer.setFormat("Timer: %s");

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if((SystemClock.elapsedRealtime()-chronometer.getBase())>10_000){
                    //Toast.makeText(MainActivity.this, "10 sec completed!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isRunning){
                    chronometer.setBase(SystemClock.elapsedRealtime()-pauseOffset);
                    chronometer.start();
                    isRunning=true;
                }
            }
        });

        btnpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isRunning){
                    chronometer.stop();
                    pauseOffset=SystemClock.elapsedRealtime() - chronometer.getBase();
                    isRunning=false;
                }
            }
        });

        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.stop();
                pauseOffset =0;
                isRunning=false;

            }
        });
    }
}