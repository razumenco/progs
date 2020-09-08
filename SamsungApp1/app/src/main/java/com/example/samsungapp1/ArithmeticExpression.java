package com.example.samsungapp1;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class ArithmeticExpression {

    public static String generate() {
        int depth = (int) (Math.random() * 3 + 2);
        return generate(depth);
    }

    private static String generate(int depth) {
        int max = (int) (Math.random() * 30 + 1);
        if (depth <= 1)
            return String.valueOf((int) (Math.random() * max + 1));
        ArrayList<Character> operations = new ArrayList<>(4);
        for (int i = 0; i < 2; i++) {
            operations.add('+');
            operations.add('-');
            operations.add('*');
        }
        operations.add('/');

        Collections.shuffle(operations);
        char o = operations.get(0);
        if (o == '/') {
            int notZero = (int)(Math.random() * 10 + 1);
            return String.format(Locale.US, "( %s %c %d )", generate(depth-1), o, notZero);
        }

        return String.format(Locale.US, "( %s %c %s )", generate(depth-1), o, generate(depth-1));
    }


}
