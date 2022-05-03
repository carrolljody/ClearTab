package com.example.cleartab.model;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class PostLiveData extends LiveData<Post> {
    private final ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            Post post = snapshot.getValue(Post.class);
            setValue(post);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    };
    DatabaseReference databaseReference;

    public PostLiveData(DatabaseReference ref) {
        databaseReference = ref;
    }

    @Override
    protected void onActive() {
        super.onActive();
        databaseReference.addValueEventListener(listener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        databaseReference.removeEventListener(listener);
    }
}