package com.example.testproju2.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.testproju2.R;
import com.example.testproju2.ui.home.Saving;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class KalenteriFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);


        //asettaa päivän määrä formatti
        SimpleDateFormat formatter = new SimpleDateFormat("d.M.yyyy");
        String enderman="";

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
        TextView happyBonus = root.findViewById(R.id.textView6);
        //hakee sharedpreference(SP) kansion: Canedar_valuer
        Saving saving = new Saving(getActivity(), "Calendar_valuer");

        //hake sharedpreference(SP) hankki string promille ja paiva kuukausi ja vuosi
        String prominense = saving.getStr("Promille " + stringDate);
        String promin = prominense.replace(",",".");
        int kalo = saving.getInt("Kalori " + stringDate);
        String annos= saving.getStr("Annoksia " + stringDate);
        String annoksi = annos.replace(",",".");
        //että pystyn vertaila edellistä päivää
        Date daet = backInTime(date);
        //formatoisen saman mallin kun ylhällä oleva formatti
        String theHAHAHASucktobeyou = formatter2.format(daet);
        //hake edellisen päivä string
        String pastAnnoksi = saving.getStr("Annoksia " + theHAHAHASucktobeyou);
        String passingAnnos= pastAnnoksi.replace(",",".");
        Double kala = Double.parseDouble(passingAnnos);
        Double kallio = Double.parseDouble(annoksi);

        if (kala.equals(kallio)) {
            enderman = "Olet juonut yhtä paljon kuin valittuna päivänä";
        } else {
//s
            if (kallio < kala) {
                if (kallio == 0) {
                    enderman = "Aiempaan päivään verrattuna olet juonut 100% vähemmän";
                } else if (kala == 0) {
                    enderman = Double.toString(kala);

                } else {
                    double d = (((double) (kala - kallio)) / kala * 100);

                    enderman = "Aiempaan päivään verrattuna olet juonut " + (int) d + "% vähemmän";
                    //Prosentin nousu valitun päivän määrä vertailtuu

                }
            } else if (kallio > kala) {
                if (kallio == 0) {
                    enderman = Double.toString(kallio);

                } else if (kala == 0) {
                    enderman = "Aiempaan päivään verrattuna olet juonut 100% enemmän";

                } else {

                    double d = (((double) (kala - kallio)) / kala * 100);

                    enderman = "Aiempaan päivään verrattuna olet juonut " + Math.abs((int) d) + "% enemmän";

                }
            }
        }

        // asetta textviewlle
        annoksia.setText("Annoksia " + annoksi);
        promille.setText("Promillet " + promin + "‰");
        kalorit.setText("Kalorit " + Integer.toString(kalo));
        happyBonus.setText(enderman);

        return root;
    }

    /**
     * onCreateView-tiedostoa käytetään fragmenttien avulla asettelun luomiseen ja näkymän suurentamiseen
     *
     * @param view               Näkymän palautti onCreateView (android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle).
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


                Saving saving = new Saving(getActivity(), "Calendar_valuer");


                //hakee sharedpreference(SP) kansion: Canedar_valuer
                String promin = saving.getStr("Promille " + d);
                int kalo = saving.getInt("Kalori " + d);
                String annoksi = saving.getStr("Annoksia " + d);

                //setta textiin
                annoksia.setText("Annoksia " + annoksi);
                promille.setText("Promillet " + promin + "‰");
                kalorit.setText("Kalorit " + Integer.toString(kalo));
                textView.setText(dayOfMonth + "." + (month + 1) + "." + year);
                happyBonus.setText(percentage(d));
            }
        });


    }

    private static final long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;

    /**
     * Että saan edellisen päivän
     *
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
     *
     * @param datte Päivän määrä että pystyn saada infot
     * @return palautta prosenttin laskevan tiedot
     */
    private String percentage(String datte) {
        Date date = new Date();
        String ender = "";
        SimpleDateFormat formatter2 = new SimpleDateFormat("dMyyyy");
        Saving saving = new Saving(getActivity(), "Calendar_valuer");
        String theHAHAHASucktobeyou = formatter2.format(date);
        String vertailu1 = saving.getStr("Annoksia " + theHAHAHASucktobeyou);
        String original = vertailu1.replace(",", ".");
        Double kalo = Double.parseDouble(original);
        String vertailu2 = saving.getStr("Annoksia " + datte);
        String Dublicate = vertailu2.replace(",", ".");
        Double kala = Double.parseDouble(Dublicate);

        if (kala.equals(kalo)) {
            ender = "Olet juonut yhtä paljon kuin valittuna päivänä";
        } else {
//s
            if (kalo < kala) {
                if (kalo == 0) {
                    ender = "Valittuun päivään verrattuna olet juonut 100% vähemmän";

                } else if (kala == 0) {
                    ender = Double.toString(kala);

                } else {
                    double d = (((double) (kala - kalo)) / kala * 100);

                    ender = "Valittuun päivään verrattuna olet juonut " + (int) d + "% vähemmän";
                    //Prosentin nousu valitun päivän määrä vertailtuu

                }
            } else if (kalo > kala) {
                if (kalo == 0) {
                    ender = Double.toString(kalo);

                } else if (kala == 0) {
                    ender = "Valittuun päivään verrattuna olet juonut 100% enemmän";

                } else {

                    double d = (((double) (kala - kalo)) / kala * 100);

                    ender = "Valittuun päivään verrattuna olet juonut " + Math.abs((int) d) + "% enemmän";

                }
            }
        }

        return ender;
    }

}



