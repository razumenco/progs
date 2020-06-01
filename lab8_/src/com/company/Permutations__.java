package com.company;

import java.util.*;
/*
Перестановки генерируются рекурсивно, в текущую перестановку мы рекурсивно каждый раз добавляем символ из еще не рассмотренных
частей строки. Повторения избегаются благодаря тому, что перестановки хравнятся в классе Set(множество),
в который невозможно добавить два одинаковых элемента. Далее я конвертирую множество в массив и сортирую его с
помощью MSD-сортировки.
 */
public class Permutations__ {
    public static void main(String[] args) {
        String s = "collection";
        Stopwatch st = new Stopwatch();
        Set<String> ps = permutation("", s);
        String[] a = ps.toArray(new String[ps.size()]);
        StdOut.println("Перестановки строки "+s+" сгенерированы за "+st.elapsedTime()+" c.");
        Stopwatch st_ = new Stopwatch();
        StdRandom.shuffle(a);
        MSDSort.sort(a);
        for (int i = 0; i < 10 ; i++) {
            StdOut.println(a[i]);
        }
        if(isSorted(a))
            StdOut.println("Перестановки строки "+s+" отсортированы с помощью MSD-сортировки за "+st_.elapsedTime()+" c.");
    }

    private static Set<String> permutation(String tperm, String s) {
        Set<String> permutations = new LinkedHashSet<>();
        int n = s.length();
        if (n == 0) {
            permutations.add(tperm);
        } else {
            for (int i = 0; i < n; i++) {
                permutations.addAll(permutation(tperm + s.charAt(i), s.substring(i + 1, n) + s.substring(0, i)));
            }
        }
        return permutations;
    }

    private static String generateString(int n) {
        char[] alph, alph_;
        alph = "qwertyuiopasdfghjklzxcvbnm".toCharArray();
        alph_ = "QWERTYUIOPASDFGHJKLZXCVBNM".toCharArray();
        int R = alph.length;
        char[] a = new char[n];
        a[0] = alph_[StdRandom.uniform(R)];
        for (int i = 1; i < n; i++) {
            a[i] = alph[StdRandom.uniform(R)];
        }
        return new String(a);
    }
    public static boolean isSorted(String[] s) {
        for (int i = 1; i < s.length; i++) {
            if (s[i - 1].compareTo(s[i]) > 0)
                return false;
        }
        return true;
    }

    private static int fact(int n){
        int result = 1;
        for (int i = 1; i <=n; i ++){
            result = result*i;
        }
        return result;
    }
}
