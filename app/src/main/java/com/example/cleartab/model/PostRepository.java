package com.example.cleartab.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostRepository {
    private static PostRepository instance;
    private DatabaseReference myRef;
    private PostLiveData postLiveData;

    private PostRepository(){}

    public static synchronized PostRepository getInstance() {
        if(instance == null)
            instance = new PostRepository();
        return instance;
    }

    public void init(String userId) {
        myRef = FirebaseDatabase.getInstance().getReference().child("users").child(userId);
        postLiveData = new PostLiveData(myRef);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void savePost(String body, int rating) {
        myRef.setValue(new Post(body, rating));
    }

    public PostLiveData getPost() {
        return postLiveData;
    }
}
