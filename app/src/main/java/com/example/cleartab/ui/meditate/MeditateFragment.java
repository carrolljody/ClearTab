package com.example.cleartab.ui.meditate;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
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
    private RatingBar meditateRatingBar;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
      meditateViewModel =
                new ViewModelProvider(this).get(MeditateViewModel.class);

      meditateViewModel.init();

        binding = FragmentMeditateBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        meditateEditText = root.findViewById(R.id.text_exercise);
        meditateTextView = root.findViewById(R.id.text_meditate);
        saveButton = root.findViewById(R.id.button_save);
        meditateRatingBar = root.findViewById(R.id.exercise_ratingBar);

        final TextView textView = binding.textMeditate;
        meditateViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        saveButton.setOnClickListener(v-> {
            meditateViewModel.savePost(meditateEditText.getText().toString(), (int)meditateRatingBar.getRating());
        });

        meditateEditText.setOnClickListener(v->{
            meditateEditText.setText("");
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}