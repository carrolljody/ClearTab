package com.example.cleartab.ui.journal;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cleartab.model.Journal;
import com.example.cleartab.model.JournalRepository;
import com.example.cleartab.model.PostRepository;

public class JournalViewModel extends ViewModel {

    private final JournalRepository journalRepository;

    public JournalViewModel() {
        this.journalRepository = JournalRepository.getInstance();
    }

    public void init()
    {
        journalRepository.init();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void saveJournal(String body)
    {
        journalRepository.saveJournal(body);
    }

}