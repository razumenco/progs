package com.example.samsungapp1.ui.notifications;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.widget.Toast;

import com.example.samsungapp1.MainActivity;
import com.example.samsungapp1.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class NotificationsFragment extends Fragment {
    private NotificationsViewModel notificationsViewModel;
    private EditText forUsername;
    private Button check;
    private String username = "";
    private TextView for_bad_answer;
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root;
        if (User.getName().equals("")) {
            root = inflater.inflate(R.layout.fragment_notifications, container, false);
            final TextView help = root.findViewById(R.id.textView2);
            notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s) {
                    help.setText(R.string.help_profile);
                }
            });
            forUsername = root.findViewById(R.id.editTextTextPersonName);
            check = root.findViewById(R.id.done);
            for_bad_answer = root.findViewById(R.id.textView4);
            check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String username = forUsername.getText().toString();
                    if (username.length() < 5)
                        for_bad_answer.setText(R.string.bad_answer);
                    else {
                        User.setName(username);
                        User.setScore(0);
                        Toast.makeText(getContext(), R.string.new_log, Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else {
            root = inflater.inflate(R.layout.fragment_logged, container, false);
            TextView name = root.findViewById(R.id.textView3), score = root.findViewById(R.id.textView2);
            name.setText(String.format(Locale.US, "Your username: %s", User.getName()));
            score.setText(String.format(Locale.US, "Your score is: %d", User.getScore()));
        }
        return root;
    }
}