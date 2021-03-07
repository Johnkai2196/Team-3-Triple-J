package com.example.testproju2.ui.home;

public class MonthsCounter {

    private String vuosia;
    private String counter;
    private String stringDat;
    private String end;
    private String enda;

    public MonthsCounter(String vuosia, String counter, String stringDat) {
        this.vuosia = vuosia;
        this.stringDat = stringDat;
        this.counter = counter;
    }

    public String set() {

        if (stringDat.equals("1")) {

            end = counter + " " + vuosia + " Tammikuu";
        } else if (stringDat.equals("2")) {
            end = counter + " " + vuosia + " Helmikuu";

        } else if (stringDat.equals("3")) {
            end = counter + " " + vuosia + " Maaliskuu";


        } else if (stringDat.equals("4")) {
            end = counter + " " + vuosia + " Huhtikuu";


        } else if (stringDat.equals("5")) {
            end = counter + " " + vuosia + " Toukokuu";


        } else if (stringDat.equals("6")) {
            end = counter + " " + vuosia + " Kesäkuu";


        } else if (stringDat.equals("7")) {
            end = counter + " " + vuosia + " Heinäkuu";


        } else if (stringDat.equals("8")) {
            end = counter + " " + vuosia + " Elokuu";


        } else if (stringDat.equals("9")) {
            end = counter + " " + vuosia + " Syyskuu";


        } else if (stringDat.equals("10")) {
            end = counter + " " + vuosia + " Lokakuu";


        } else if (stringDat.equals("11")) {
            end = counter + " " + vuosia + " Marraskuu";

        } else if (stringDat.equals("12")) {
            end = counter + " " + vuosia + " Joulokuu";
        }
        return end;

    }

    public String Another() {
        if (stringDat.equals("1")) {

            enda = vuosia + " Tammikuu";
        } else if (stringDat.equals("2")) {
            enda = vuosia + " Helmikuu";

        } else if (stringDat.equals("3")) {
            enda = vuosia + " Maaliskuu";


        } else if (stringDat.equals("4")) {
            enda = vuosia + " Huhtikuu";


        } else if (stringDat.equals("5")) {
            enda = vuosia + " Toukokuu";


        } else if (stringDat.equals("6")) {
            enda = vuosia + " Kesäkuu";


        } else if (stringDat.equals("7")) {
            enda = vuosia + " Heinäkuu";


        } else if (stringDat.equals("8")) {
            enda = vuosia + " Elokuu";


        } else if (stringDat.equals("9")) {
            enda = vuosia + " Syyskuu";


        } else if (stringDat.equals("10")) {
            enda = vuosia + " Lokakuu";


        } else if (stringDat.equals("11")) {
            enda = vuosia + " Marraskuu";

        } else if (stringDat.equals("12")) {
            enda = vuosia + " Joulokuu";
        }
        return enda;

    }
}
