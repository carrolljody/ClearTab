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

    public void init() {
        myRef = FirebaseDatabase.getInstance("https://cleartab-default-rtdb.europe-west1.firebasedatabase.app/").getReference().child("posts");
        postLiveData = new PostLiveData(myRef);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void savePost(String body, int rating) {
        myRef.push().setValue(new Post(body, rating));
    }

    public PostLiveData getPost() {
        return postLiveData;
    }
}
