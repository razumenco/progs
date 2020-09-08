package com.example.samsungapp1.ui.notifications;

public class User {
    private static String name = "";
    private static int score = 0;
    private static boolean created = false;

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        User.name = name;
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        User.score = score;
    }
    public static void makeCreated() {
        created = true;
    }

    public static boolean created() {
        return created;
    }

}