package com.example.neo.ruedelaforetcountdown;

import android.content.Context;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Chronometer;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView mTextField = (TextView) findViewById(R.id.a_timer);
        final TextView mDays = (TextView) findViewById(R.id.days);
        final TextView mWeeks = (TextView) findViewById(R.id.weeks);

        long sellDate = ZonedDateTime.parse(
                "Sep 01 2019 00:00:00.000 CET" ,
                DateTimeFormatter.ofPattern ( "MMM d uuuu HH:mm:ss.SSS z" )
        )
                .toInstant()
                .toEpochMilli();

        long now = System.currentTimeMillis();
        long diff = sellDate - now;

        new CountDownTimer(diff, 1000) {
            private TextView mTextField = (TextView) findViewById(R.id.a_timer);


            public void onTick(long millisUntilFinished) {
                mTextField.setText("seconds remaining: " + (millisUntilFinished/1000));
                mDays.setText("days remaining: " + (millisUntilFinished / (60*60*24*1000) ));
                long days = millisUntilFinished / (60*60*24*1000);
                long dl = days % 7;
                double weeks = Math.floor(days / 7);

                mWeeks.setText("weeks remaining: " + weeks);
            }

            public void onFinish() {
                mTextField.setText("You can sell the flat now!");
            }
        }.start();

        Log.v("main", "I am here now");
    }
}
