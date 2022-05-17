package com.example.cleartab.model;


import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;

public class Journal {
    private String id;
    private String body;
    private LocalDateTime timestamp;

    public Journal() {
        //
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Journal(String body) {
        this.body = body;
        this.timestamp = LocalDateTime.now();
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
