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
    String enderman = "";
    private static final long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;

    /**
     * Luo tämän fragmentin näkymä sille annettujen argumenttien avulla.
     *
     * @param inflater           LayoutInflater-objekti, jota voidaan käyttää kaikkien fragmenttien paisuttamiseen
     * @param container          Jos se ei ole non-null, tämä on päänäkymä, johon fragmentin käyttöliittymä tulisi liittää.
     *                           Katkelman ei pitäisi lisätä itse näkymää, mutta sitä voidaan käyttää näkymän LayoutParam-tiedostojen luomiseen. Tämä arvo voi olla tyhjä
     * @param savedInstanceState Jos se ei ole non-null, tätä fragmenttia rakennetaan uudelleen edellisestä tallennetusta tilasta, kuten tässä on annettu.
     * @return palautta minun luotu xml
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //luo View minun xml:sstä
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);


        //asettaa päivän määrä formatti
        SimpleDateFormat formatter = new SimpleDateFormat("d.M.yyyy");


        // uusi date
        Date date = new Date();

        TextView textView = root.findViewById(R.id.textView);
        //asettaa set text
        textView.setText(formatter.format(date));
        //Formattoi minun kalenteri sillä malillta mitä haluan
        SimpleDateFormat formatter2 = new SimpleDateFormat("dMyyyy");
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

        //muokka sen pilkusta pisteseen
        String promin = prominense.replace(",", ".");

        //hake sharedpreference(SP) hankki string Kalori ja paiva kuukausi ja vuosi
        int kalo = saving.getInt("Kalori " + stringDate);

        //hake sharedpreference(SP) hankki string Annoksia ja paiva kuukausi ja vuosi
        String annos = saving.getStr("Annoksia " + stringDate);

        //muokka sen pilkusta pisteseen
        String annoksi = annos.replace(",", ".");

        //että pystyn vertaila edellistä päivää
        Date dateYesterday = backInTime(date);

        //formatoisen saman mallin kun ylhällä oleva formatti
        String dateYesterda = formatter2.format(dateYesterday);

        //hake edellisen päivä string
        String pastAnnoksi = saving.getStr("Annoksia " + dateYesterda);

        //muokka sen pilkusta pisteseen
        String passingAnnos = pastAnnoksi.replace(",", ".");

        //muokkasen double
        double edellinenAnnos = Double.parseDouble(passingAnnos);
        double todayAnnos = Double.parseDouble(annoksi);

        //Jos alkuperäinen määrä (todayAnnos) on sama kun valittu määrä (edellinenAnnos)
        if (edellinenAnnos == todayAnnos) {
            enderman = "Olet juonut yhtä paljon kuin valittuna päivänä";
        } else {
            //jos edellinen päivän määrä on pienempi kun tämän päiväsen
            if (todayAnnos < edellinenAnnos) {
                //jos edellinen päivän määrä on nolla se anta tämän
                if (todayAnnos == 0) {
                    enderman = "Aiempaan päivään verrattuna olet juonut 100% vähemmän";
                } else {
                    //Prosentin lasku
                    double tulos = (((double) (edellinenAnnos - todayAnnos)) / edellinenAnnos * 100);
                    // asetta textViewlle myöhemmin
                    enderman = "Aiempaan päivään verrattuna olet juonut " + (int) tulos + "% vähemmän";
                }
                //jos edellinen päivän määrä on isompi kun tämän päiväsen
            } else if (todayAnnos > edellinenAnnos) {
                //jos edellinen päivä määrä on pienempi kun tämän päiväsen
                if (edellinenAnnos == 0) {
                    enderman = "Aiempaan päivään verrattuna olet juonut 100% enemmän";

                } else {
                    //Prosentin lasku
                    double tulos = (((double) (edellinenAnnos - todayAnnos)) / edellinenAnnos * 100);
                    // asetta textViewlle myöhemmin
                    enderman = "Aiempaan päivään verrattuna olet juonut " + Math.abs((int) tulos) + "% enemmän";

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
        //muokka aloitus päivää maanantaihin
        calendarView.setFirstDayOfWeek(Calendar.MONDAY);
        //kun painaa calendar view se tunnistaa
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            /**
             * voin tunnistaa mitkä päivät on painattu ja kertoo minulle
             * @param view kun näkymää on napsautettu.
             * @param year vuosi int
             * @param month kuukausi int
             * @param dayOfMonth päivä int
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

                //hankki sharedpreference tiedot
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
     * että pystyn vertaila ja palautta että onko juonut vähemän tai enemmän
     *
     * @param datte Päivän määrä että pystyn saada infot
     * @return palautta prosenttin laskevan tiedot
     */
    private String percentage(String datte) {
        //päviä tänään olevaan
        Date date = new Date();
        //texti mitä palautetaan
        String ender = "";
        //formatoi miten haluan
        SimpleDateFormat formatter2 = new SimpleDateFormat("dMyyyy");
        //hakee sharedpreference(SP) kansion: Canedar_valuer
        Saving saving = new Saving(getActivity(), "Calendar_valuer");
        //Date to string
        String tanaan = formatter2.format(date);
        //hake tanaan olevat tiedot
        String vertailu1 = saving.getStr("Annoksia " + tanaan);
        //vaihta pilkku pisteksi
        String original = vertailu1.replace(",", ".");
        //muokka doubleksi
        double tanaanAnnos = Double.parseDouble(original);
        //hankki valittus paivan maara tiedot
        String vertailu2 = saving.getStr("Annoksia " + datte);
        //pilkku pisteksi
        String Dublicate = vertailu2.replace(",", ".");
        //muokka doubleksi
        double valittusPaivaAnnos = Double.parseDouble(Dublicate);
        //Jos alkuperäinen määrä (tabaanAnnos) on sama kun valittu määrä (valittusPaivaAnnos)
        if (valittusPaivaAnnos == tanaanAnnos) {
            ender = "Olet juonut yhtä paljon kuin valittuna päivänä";
        } else {
            //jos valittu päivän määrä on pienempi kun tämän päiväsen
            if (tanaanAnnos < valittusPaivaAnnos) {
                //jos valittu päivän määrä on nolla se anta tämän
                if (tanaanAnnos == 0) {
                    ender = "Valittuun päivään verrattuna olet juonut 100% vähemmän";

                } else {
                    //Prosentin lasku
                    double d = (((double) (valittusPaivaAnnos - tanaanAnnos)) / valittusPaivaAnnos * 100);
                    // asetta textViewlle myöhemmin
                    ender = "Valittuun päivään verrattuna olet juonut " + (int) d + "% vähemmän";


                }
                //jos edellinen päivän määrä on isompi kun tämän päiväsen
            } else if (tanaanAnnos > valittusPaivaAnnos) {
                //jos valittu päivän määrä on nolla se anta tämän
                if (valittusPaivaAnnos == 0) {
                    ender = "Valittuun päivään verrattuna olet juonut 100% enemmän";

                } else {
                    //Prosentin lasku
                    double d = (((double) (valittusPaivaAnnos - tanaanAnnos)) / valittusPaivaAnnos * 100);
                    // asetta textViewlle myöhemmin
                    ender = "Valittuun päivään verrattuna olet juonut " + Math.abs((int) d) + "% enemmän";

                }
            }
        }
            //palautta
        return ender;
    }

}



