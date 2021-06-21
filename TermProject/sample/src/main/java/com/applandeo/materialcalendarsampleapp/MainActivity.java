package com.applandeo.materialcalendarsampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button openTimerButton = (Button) findViewById(R.id.timerbutton);
        Button openCalenderButton = (Button) findViewById(R.id.calenderbutton);
        Button openloginButton = (Button) findViewById(R.id.loginbutton);



       openTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent TimerIntent = new Intent(MainActivity.this, TimerActivity.class);
                startActivity(TimerIntent);
            }
        });

        openCalenderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent CalenderIntent = new Intent(MainActivity.this, CalenderActivity.class);
                startActivity(CalenderIntent);
            }
        });

        openloginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });


    }
}