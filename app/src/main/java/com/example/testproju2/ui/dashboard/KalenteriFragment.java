package com.example.testproju2.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.testproju2.R;
import com.example.testproju2.ui.home.DataProccessor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class KalenteriFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);


        //asettaa päivän määrä formatti
        SimpleDateFormat formatter = new SimpleDateFormat("d.M.yyyy");


        // uusi date
        Date date = new Date();

        TextView textView = root.findViewById(R.id.textView);
        textView.setText(formatter.format(date));
        //hankki paivan tiedot
        SimpleDateFormat formatter2 = new SimpleDateFormat("dMyyyy");
        //Formattoi minun kalenteri sillä malillta mitä haluan
        String stringDate = formatter2.format(date);

        //Hankki textview
        TextView annoksia = root.findViewById(R.id.textView5);
        TextView promille = root.findViewById(R.id.textView2);
        TextView kalorit = root.findViewById(R.id.textView4);

        //hakee sharedpreference(SP) kansion: Canedar_valuer
        DataProccessor dataProccessor = new DataProccessor(getActivity(), "Calendar_valuer");

        //hake sharedpreference(SP) hankki string promille ja paiva kuukausi ja vuosi
        String promin = dataProccessor.getStr("Promille " + stringDate);
        int kalo = dataProccessor.getInt("Kalori " + stringDate);
        String annoksi = dataProccessor.getStr("Annoksia " + stringDate);
        //että pystyn vertaila edellistä päivää
        Date daet = backInTime(date);
        //formatoisen saman mallin kun ylhällä oleva formatti
        String theHAHAHASucktobeyou = formatter2.format(daet);
        //hake edellisen päivä string
        String pastAnnoksi = dataProccessor.getStr("Annoksia " + theHAHAHASucktobeyou);

        // asetta textviewlle
        annoksia.setText("Annoksia " + annoksi);
        promille.setText("Promillet " + promin + "‰");
        kalorit.setText("Kalorit " + Integer.toString(kalo));


        return root;
    }

    /**
     *onCreateView-tiedostoa käytetään fragmenttien avulla asettelun luomiseen ja näkymän suurentamiseen
     * @param view Näkymän palautti onCreateView (android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle).
     * @param savedInstanceState Jos se ei ole nolla, tätä fragmenttia rakennetaan uudelleen edellisestä tallennetusta tilasta, kuten tässä on annettu. Tämä arvo voi olla tyhjä.
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //voin hankia calendar view
        CalendarView calendarView = (CalendarView) getView().findViewById(R.id.calendarView);
        calendarView.setFirstDayOfWeek(Calendar.MONDAY);
        //kun painaa calendar view se tunnistaa
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            /**
             * voin tunnistaa mitkä päivät on painattu ja kertoo minulle
             * @onSelectedDaychange
             */
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {

                //Hankki textview

                TextView textView = getView().findViewById(R.id.textView);
                TextView annoksia = getView().findViewById(R.id.textView5);
                TextView promille = getView().findViewById(R.id.textView2);
                TextView kalorit = getView().findViewById(R.id.textView4);
                TextView happyBonus = getView().findViewById(R.id.textView6);
                //teen painetun tiedoston mukaan kalenterin formatti että pystyn käyttää key word.
                String d = dayOfMonth + "" + (month + 1) + "" + year;


                DataProccessor dataProccessor = new DataProccessor(getActivity(), "Calendar_valuer");


                //hakee sharedpreference(SP) kansion: Canedar_valuer
                String promin = dataProccessor.getStr("Promille " + d);
                int kalo = dataProccessor.getInt("Kalori " + d);
                String annoksi = dataProccessor.getStr("Annoksia " + d);
                ;
                //setta textiin
                annoksia.setText("Annoksia " + annoksi);
                promille.setText("Promillet " + promin + "‰");
                kalorit.setText("Kalorit " + Integer.toString(kalo));
                textView.setText(dayOfMonth + "." + (month + 1) + "." + year);
                happyBonus.setText(percentage(d) + "%");
            }
        });


    }

    private static final long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;

    /**
     * Että saan edellisen päivän
     * @param date tänään oleva päivä
     * @return palautaa eilisen päivän määrä
     */
    private static Date backInTime(Date date) {
        {
            return new Date(date.getTime() - MILLIS_IN_A_DAY);
        }
    }

    /**
     * että pystyn vertaila ja palautta että onko juonut vähemän
     * @param datte Päivän määrä että pystyn saada infot
     * @return palautta prosenttin laskevan tiedot
     */
    private String percentage(String datte) {
        Date date = new Date();
        String ender = "";
        SimpleDateFormat formatter2 = new SimpleDateFormat("dMyyyy");
        DataProccessor dataProccessor = new DataProccessor(getActivity(), "Calendar_valuer");
        String theHAHAHASucktobeyou = formatter2.format(date);
        String vertailu1 = dataProccessor.getStr("Annoksia " + theHAHAHASucktobeyou);
        Double kalo = Double.parseDouble(vertailu1);

        String vertailu2 = dataProccessor.getStr("Annoksia " + datte);
        Double kala = Double.parseDouble(vertailu2);

        if (kala == kalo) {
            ender = "0";
            Log.d("Helpmee", Integer.toString(5));
        } else {

            if (kalo < kala) {
                if (kalo == 0) {
                    ender = Double.toString(kala);
                    Log.d("Helpmege", Integer.toString(1));
                } else if (kala == 0) {
                    ender = Double.toString(kala);
                    Log.d("Helpmree", Integer.toString(2));
                } else {
                    double d = (((double) (kala - kalo)) / kala * 100);

                    ender = "Edelisen päivän vertailtuu olet juonut vähemmän" + Integer.toString((int) d);
                    //Prosentin nousu valitun päivän määrä vertailtuu
                    Log.d("Helpmeee", Double.toString(d));
                }
            } else if (kalo > kala) {
                if (kalo == 0) {
                    ender = Double.toString(kalo);
                    Log.d("Helpmeae", Double.toString(kalo));
                } else if (kala == 0) {
                    ender = Double.toString(kala);
                    Log.d("Helpmeve", Double.toString(kala));
                } else {

                    double d = (((double) (kalo - kala)) / kalo * 100) + 100;

                    ender = "Edelisen päivän vertailtuu olet juonut enemmän" + Integer.toString((int) d);
                    Log.d("Helpmewe", ender);
                    Log.d("Helpmete", Integer.toString(7));
                }
            }
        }

        return ender;
    }

}



