package com.example.testproju2.ui.home;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarFormat {
    private Date date;

    public  CalendarFormat(Date date) {
        this.date = date;
    }

    /**
     *
     * @return
     */
    public String getYear() {
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy");
         return formatter1.format(date);
    }

    public String getMonth() {
        SimpleDateFormat formatter2 = new SimpleDateFormat("M");
        return formatter2.format(date);
    }
    public String getMonthYear() {
        SimpleDateFormat formatter3 = new SimpleDateFormat("Myyyy");
        return formatter3.format(date);
    }

    public String getDateMonthYear() {
        SimpleDateFormat formatter4 = new SimpleDateFormat("dMyyyy");
        return formatter4.format(date);
    }
    public String getWeek(){
        SimpleDateFormat formatter5 = new SimpleDateFormat("w");
       return formatter5.format(date);
    }
}
