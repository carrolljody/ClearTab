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
        //TODO: move hardcoded value to firebase so it can be easily edited to different types of exercises
        mText.setValue("Close your eyes.\n\nPay attention to the surrounding noise.\n\n " +
                "Focus 100% on that.\n\n" +
                "Refocus when you get lost.\n\n" +
                "Write down your thoughts after.");
        this.postRepository = PostRepository.getInstance();
    }

    public void init()
    {
        postRepository.init();
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