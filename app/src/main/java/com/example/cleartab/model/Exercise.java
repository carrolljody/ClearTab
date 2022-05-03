package com.example.cleartab.model;

import android.os.Build;
import android.os.CountDownTimer;

import androidx.annotation.RequiresApi;

public class Exercise extends Post{

    private CountDownTimer timer;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Exercise(String body, int rating) {
        super(body, rating);
        this.timer = null;
    }

    public CountDownTimer getTimer() {
        return timer;
    }

    public void setTimer(int duration) {
        long durationMilli = duration* 1000L;
        this.timer = new CountDownTimer(durationMilli, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {

            }
        };;
    }
}
