package com.example.samsungapp1.ui.home;

import android.os.Bundle;
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

import com.example.samsungapp1.ArithmeticExpression;
import com.example.samsungapp1.DoubleStackAlgorithm;
import com.example.samsungapp1.R;
import com.example.samsungapp1.ui.notifications.User;

import java.util.Locale;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.textView);
        final TextView help = root.findViewById(R.id.textView1);
        final EditText answer = root.findViewById(R.id.editText);
        final TextView fgr = root.findViewById(R.id.forGoodRes);
        Button submit = root.findViewById(R.id.button);
        Button next = root.findViewById(R.id.next);
        final String toCalculate = ArithmeticExpression.generate();
        char[] buff = toCalculate.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 2; i < buff.length - 2;  i++) {
            stringBuilder.append(buff[i]);
        }
        final String expr = stringBuilder.toString() + " = ";
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(expr);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int correct = DoubleStackAlgorithm.calculate(String.format(Locale.US,"( %s )", textView.getText().toString()));
                int res = correct - 1;
                try {
                    res = Integer.parseInt(answer.getText().toString());
                } catch (Exception e) {
                    fgr.setText(R.string.wrong_answer);
                }
                if (res != correct)
                    fgr.setText(R.string.wrong_answer);
                else {
                    fgr.setText(R.string.correct_answer);
                    User.setScore(User.getScore() + 1);
                    if (User.getScore() >= 100 && User.created())
                        Toast.makeText(getContext(), R.string.congrats, Toast.LENGTH_LONG).show();
                    String newExpr = ArithmeticExpression.generate();
                    char[] buff = newExpr.toCharArray();
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 2; i < buff.length - 2;  i++) {
                        stringBuilder.append(buff[i]);
                    }
                    textView.setText(stringBuilder.toString());
                    answer.setText("");
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newExpr = ArithmeticExpression.generate();
                char[] buff2 = newExpr.toCharArray();
                StringBuilder stringBuilder1 = new StringBuilder();
                for (int i = 2; i < buff2.length - 2; i++) {
                    stringBuilder1.append(buff2[i]);
                }
                textView.setText(stringBuilder1.toString());
            }
        });
        return root;
    }
}