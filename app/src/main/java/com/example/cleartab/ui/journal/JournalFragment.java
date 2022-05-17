package com.example.cleartab.ui.journal;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cleartab.R;
import com.example.cleartab.databinding.FragmentJournalBinding;
import com.example.cleartab.ui.meditate.MeditateViewModel;

public class JournalFragment extends Fragment {

    private MeditateViewModel journalViewModel;
    private FragmentJournalBinding binding;
    private EditText journalEditText;
    private Button saveButton;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        JournalViewModel journalViewModel =
                new ViewModelProvider(this).get(JournalViewModel.class);

        journalViewModel.init();

        binding = FragmentJournalBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textJournal;

        journalEditText = root.findViewById(R.id.text_journal);
        saveButton = root.findViewById(R.id.button_saveJournal);

        //save button
        saveButton.setOnClickListener(v-> {
            journalViewModel.saveJournal(journalEditText.getText().toString());
            clear();
            Toast.makeText(root.getContext(),"Journal entry saved",Toast.LENGTH_SHORT).show();
        });
        return root;
    }

    private void clear() {
        journalEditText.setText("");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}