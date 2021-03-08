package com.example.testproju2.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

public class DataProccessor {
    private static Context context;
    private static String FILE_NAME;

    /**
     *  että pystyn pistää getActivity ja tiedoston nimejä
     * @param contexta getActivity fragment varten
     * @param FILE_NAMEA Tiedoston nimi
     */
    public DataProccessor(Context contexta, String FILE_NAMEA) {
        context = contexta;
        FILE_NAME = FILE_NAMEA;
    }


    /**
     * Asetta sharedpreferensiin inttin
     * @param key Avain sharedpreferensiin
     * @param value Value sharedpreferensiin
     */
    public static void setInt(String key, int value) {
        //asetta kansio nimeltään esim: olutEuro2021
        SharedPreferences prefs = context.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
        //että pystyn muokka
        SharedPreferences.Editor editor = prefs.edit();
        //asettas avain ja value
        editor.putInt(key, value);
        //varmistaa sen esim kun painaa enter nappia
        editor.apply();
    }

    /**
     * Hakee sharedpreferensiin inttin
     * @param key Avain sharedpreferensiin
     * @return Palautta saatu oleva tieto
     */
    public static int getInt(String key) {
        //hakee kansio nimeltään esim: olutEuro2021
        SharedPreferences prefs = context.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
        //palautta jos löytää sen jos ei ole mitää palautta 0
        return prefs.getInt(key, 0);
    }

    /**
     * Asetta sharedpreferensiin string
     * @param key Avain sharedpreferensiin
     * @param value Value sharedpreferensiin
     */
    public static void setStr(String key, String value) {
        //asetta kansio nimeltään esim: olutEuro2021
        SharedPreferences prefs = context.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
        //että pystyn muokka
        SharedPreferences.Editor editor = prefs.edit();
        //asettas avain ja value
        editor.putString(key, value);
        //varmistaa sen esim kun painaa enter nappia
        editor.apply();
    }

    /**
     * Hakee sharedpreferensiin string
     * @param key Avain sharedpreferensiin
     * @return Palautta saatu oleva tieto
     */
    public static String getStr(String key) {
        //hakee kansio nimeltään esim: olutEuro2021
        SharedPreferences prefs = context.getSharedPreferences(FILE_NAME, Activity.MODE_PRIVATE);
        //palautta jos löytää sen jos ei ole mitää palautta 0
        return prefs.getString(key, "0");
    }



}

