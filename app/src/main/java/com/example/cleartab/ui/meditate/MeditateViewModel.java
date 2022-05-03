package com.example.cleartab.ui.meditate;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cleartab.model.Post;
import com.example.cleartab.model.PostRepository;

public class MeditateViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final PostRepository postRepository;

    public MeditateViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is meditation fragment");
        this.postRepository = PostRepository.getInstance();
    }

    public LiveData<String> getText() {
        return mText;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void savePost(String body, int rating)
    {
        postRepository.savePost(body,rating);
    }

    public LiveData<Post> getPost()
    {
        return postRepository.getPost();
    }
}