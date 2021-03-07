package com.example.testproju2.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

public class DataProccessor {
    private static Context context;
    private static String FILE_NAME;


    public DataProccessor(Context contexta, String FILE_NAMEA) {
        context = contexta;
        FILE_NAME = FILE_NAMEA;
    }


    public boolean sharedPreferenceExist(String key) {
        SharedPreferences prefs = context.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
        if (!prefs.contains(key)) {
            return true;
        } else {
            return false;
        }
    }

    public static void setInt(String key, int value) {
        SharedPreferences prefs = context.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getInt(String key) {
        SharedPreferences prefs = context.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
        return prefs.getInt(key, 0);
    }

    public static void setStr(String key, String value) {
        SharedPreferences prefs = context.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getStr(String key) {
        SharedPreferences prefs = context.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
        return prefs.getString(key, "0");
    }

    public static void setBool(String key, boolean value) {
        SharedPreferences prefs = context.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBool(String key) {
        SharedPreferences prefs = context.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
        return prefs.getBoolean(key, false);
    }


}

