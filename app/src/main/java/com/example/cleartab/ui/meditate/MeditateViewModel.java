package com.example.cleartab.ui.meditate;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MeditateViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MeditateViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is meditation fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}