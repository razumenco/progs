package com.example.samsungapp1.ui.dashboard;

import android.content.Context;
import android.database.Cursor;
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

import org.sqlite.database.sqlite.*;

import com.example.samsungapp1.R;
import com.example.samsungapp1.ui.notifications.User;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView forCountry = root.findViewById(R.id.country);
        final EditText answer = root.findViewById(R.id.countryAnswer);
        final TextView goodOrBad = root.findViewById(R.id.forGoodRes);
        final Button submit = root.findViewById(R.id.button);
        final TextView correct = root.findViewById(R.id.forRes);
        final MyDataBase helper = new MyDataBase(this.getContext(), MyDataBase.TABLE_NAME, null, 1);
        final Button next = root.findViewById(R.id.next);
        final GetRandomFromCountries getRandomFromCountries = new GetRandomFromCountries(helper);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 1; i < 100; i++) {
                        String[] data = getRandomFromCountries.getCountryAndCapital();
                        String country = data[0], capital = data[1];
                        char[] temp1 = country.toCharArray(), temp2 = capital.toCharArray();
                        final String copyCountry = new String(temp1), copyCapital = new String(temp2);
                        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
                            @Override
                            public void onChanged(@Nullable String s) {
                                forCountry.setText(copyCountry);
                                correct.setText(copyCapital);
                            }
                        });
                        try {
                            Thread.sleep(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IllegalStateException e) {
                    Log.d("MyTag", "Thread is stopped");
                }
            }
        }).start();
        String[] data = getRandomFromCountries.getCountryAndCapital();
        String country = data[0], capital = data[1];
        char[] temp1 = country.toCharArray(), temp2 = capital.toCharArray();
        final String copyCountry = new String(temp1), copyCapital = new String(temp2);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                forCountry.setText(copyCountry);
                correct.setText(copyCapital);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String country = forCountry.getText().toString(), capital = correct.getText().toString();
                String ans = answer.getText().toString();
                if (ans.equals(capital)) {
                    goodOrBad.setText(R.string.correct_answer);
                    GetRandomFromCountries getRandomFromCountries = new GetRandomFromCountries(helper);
                    String[] data = getRandomFromCountries.getCountryAndCapital();
                    forCountry.setText(data[0]);
                    correct.setText(data[1]);
                    answer.setText(R.string.empty);
                    User.setScore(User.getScore() + 3);
                    if (User.getScore() >= 100 && User.created())
                        Toast.makeText(getContext(), R.string.congrats, Toast.LENGTH_LONG).show();
                } else {
                    goodOrBad.setText(R.string.wrong_answer);
                    answer.setText(R.string.empty);
                }

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetRandomFromCountries getRandomFromCountries = new GetRandomFromCountries(helper);
                String[] data = getRandomFromCountries.getCountryAndCapital();
                forCountry.setText(data[0]);
                correct.setText(data[1]);
                answer.setText(R.string.empty);
            }
        });
        return root;
    }
}