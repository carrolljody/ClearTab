package com.example.cleartab.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class JournalRepository {
    private static JournalRepository instance;
    private DatabaseReference myRef;

    private JournalRepository(){}

    public static synchronized JournalRepository getInstance() {
        if(instance == null)
            instance = new JournalRepository();
        return instance;
    }

    public void init() {
        myRef = FirebaseDatabase.getInstance("https://cleartab-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("journalPosts");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void saveJournal(String body) {
        myRef.push().setValue(new Journal(body));
    }
}

