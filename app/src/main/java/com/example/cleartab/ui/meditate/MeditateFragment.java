package com.example.cleartab.ui.meditate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cleartab.databinding.FragmentMeditateBinding;

public class MeditateFragment extends Fragment {

    private FragmentMeditateBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MeditateViewModel meditateViewModel =
                new ViewModelProvider(this).get(MeditateViewModel.class);

        binding = FragmentMeditateBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textMeditate;
        meditateViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}