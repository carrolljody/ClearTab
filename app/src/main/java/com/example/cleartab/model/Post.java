package com.example.cleartab.model;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

public class Post {
    private int id;
    private String body;
    private LocalDateTime timestamp;
    private int rating;

    public Post(){
        //
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Post(String body, int rating) {
        this.body = body;
        this.timestamp = LocalDateTime.now();
        this.rating = rating;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
