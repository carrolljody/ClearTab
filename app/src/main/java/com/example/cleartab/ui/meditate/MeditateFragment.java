package com.example.cleartab.ui.meditate;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
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

import java.util.Locale;

public class MeditateFragment extends Fragment {

    private static final long START_TIME_IN_MILLIS = 3000;

    private TextView timerTextView;
    private Button startButton;
    private Button resetButton;
    private CountDownTimer timer;
    private boolean isTimerRunning;
    private long timeLeftInMillis = START_TIME_IN_MILLIS;

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

        timerTextView = root.findViewById(R.id.timer_meditate);
        startButton = root.findViewById(R.id.button_start);
        resetButton = root.findViewById(R.id.button_reset);

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

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        updateCountDownText();
        return root;
    }

    private void startTimer() {
        timer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                isTimerRunning = false;
                startButton.setText("Start");
                startButton.setVisibility(View.INVISIBLE);
                resetButton.setVisibility(View.VISIBLE);

                final MediaPlayer mp = MediaPlayer.create(binding.getRoot().getContext(), R.raw.gonganak);
                mp.start();
            }
        }.start();

        isTimerRunning = true;
        startButton.setText("pause");
        resetButton.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer() {
        timer.cancel();
        isTimerRunning = false;
        startButton.setText("Start");
        resetButton.setVisibility(View.VISIBLE);
    }

    private void resetTimer() {
        timeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        resetButton.setVisibility(View.INVISIBLE);
        startButton.setVisibility(View.VISIBLE);
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        timerTextView.setText(timeLeftFormatted);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}