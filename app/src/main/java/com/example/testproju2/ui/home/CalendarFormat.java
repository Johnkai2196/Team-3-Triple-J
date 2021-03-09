package com.example.testproju2.ui.home;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarFormat {
    private Date date;

    /**
     * hankin tämän päivän tiedot että pystyn muokkaa
     * @param date hankin tämän päivän tiedot
     */
    public  CalendarFormat(Date date) {
        this.date = date;
    }

    /**
     *että pystyn hake vuosi kalenterin formatti tyyli
     * @return palautta Vuoden
     */
    public String getYear() {
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy");
         return formatter1.format(date);
    }
    /**
     *että pystyn hake kuukausi kalenterin formatti tyyli
     * @return palautta kuukauden
     */
    public String getMonth() {
        SimpleDateFormat formatter2 = new SimpleDateFormat("M");
        return formatter2.format(date);
    }
    /**
     *että pystyn hake kuukausi ja vuosi kalenterin formatti tyyli
     * @return palautta kuukausi ja vuosi
     */
    public String getMonthYear() {
        SimpleDateFormat formatter3 = new SimpleDateFormat("Myyyy");
        return formatter3.format(date);
    }
    /**
     *että pystyn hake päivä kuukausi ja vuosi kalenterin formatti tyyli
     * @return päivä, kuukausi ja vuoden
     */
    public String getDateMonthYear() {
        SimpleDateFormat formatter4 = new SimpleDateFormat("dMyyyy");
        return formatter4.format(date);
    }
    /**
     *että pystyn hake viikko kalenterin formatti tyyli
     * @return Viikko
     */
    public String getWeek(){
        SimpleDateFormat formatter5 = new SimpleDateFormat("w");
       return formatter5.format(date);
    }
}
