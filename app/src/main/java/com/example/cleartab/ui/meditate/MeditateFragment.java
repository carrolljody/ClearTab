package com.example.cleartab.ui.meditate;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cleartab.R;
import com.example.cleartab.databinding.FragmentMeditateBinding;

public class MeditateFragment extends Fragment {

    private FragmentMeditateBinding binding;
    private MeditateViewModel meditateViewModel;
    private EditText meditateEditText;
    private TextView meditateTextView;
    private Button saveButton;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
      meditateViewModel =
                new ViewModelProvider(this).get(MeditateViewModel.class);

        binding = FragmentMeditateBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        meditateEditText = root.findViewById(R.id.text_exercise);
        meditateTextView = root.findViewById(R.id.text_meditate);
        saveButton = root.findViewById(R.id.button_save);

        final TextView textView = binding.textMeditate;
        meditateViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        saveButton.setOnClickListener(v-> {
            meditateViewModel.savePost(meditateEditText.getText().toString(), 5);
        });
        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void saveData() {
        meditateViewModel.savePost(meditateEditText.getText().toString(), 5);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}