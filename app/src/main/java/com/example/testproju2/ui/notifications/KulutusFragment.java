package com.example.testproju2.ui.notifications;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.testproju2.R;
import com.example.testproju2.ui.home.CalendarFormat;
import com.example.testproju2.ui.home.Saving;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class KulutusFragment extends Fragment implements AdapterView.OnItemSelectedListener {


    Date date = new Date();
    CalendarFormat set = new CalendarFormat(date);
    String stringDate = set.getYear();
    SharedPreferences prefGet;
    String str1;
    String str2;
    double olutar;
    double makeItRain;
    double makeitSnow;
    //kaikki tehty arraylist
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<String> array = new ArrayList<>();
    ArrayList<String> arrayVuosi = new ArrayList<>();
    ArrayList<String> arrayViikko = new ArrayList<>();
    ArrayList<String> arrayToday = new ArrayList<>();
    Map<Integer, String> treemap = new TreeMap<Integer, String>(Collections.reverseOrder());

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
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        return root;
    }

    /**
     * kutsu heti sen jälkeen, kun onCreateView (android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle) on palannut, mutta ennen kuin tallennettu tila on palautettu näkymään.
     * Tämä antaa alaluokille mahdollisuuden alustaa itsensä, kun he tietävät, että näkemyshierarkiansa on luotu kokonaan.
     * Fragmentin näkemyshierarkia ei kuitenkaan ole kiinnitetty sen vanhempaan tässä vaiheessa.
     *
     * @param view               View palautti onCreateView (android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle).
     * @param savedInstanceState Jos se ei ole non-null, tätä fragmenttia rakennetaan uudelleen edellisestä tallennetusta tilasta, kuten tässä on annettu. Tämä arvo voi olla tyhjä.
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        Spinner vuosiSpinner = getView().findViewById(R.id.vuosiSpinner);
        Button viikko = (Button) getView().findViewById(R.id.button4);

        viikko.setOnClickListener(new View.OnClickListener() {
            /**
             *Kun viikko nappi on painattu se hakee kaikki vuodet SharedPreferences ja pistää sen arrayViikko että pystyt nähdä spinneristä
             * @param v kun näkymää on napsautettu.
             */
            @Override
            public void onClick(View v) {
                //Poistaa kaikki arrayList et se on tyhjä spinneriä varten
                array.removeAll(array);
                arrayList.removeAll(arrayList);
                arrayVuosi.removeAll(arrayVuosi);
                arrayViikko.removeAll(arrayViikko);
                //Hakee sharedpreferences viikot
                prefGet = getActivity().getSharedPreferences("Viikko", Activity.MODE_PRIVATE);
                //Hankki kaikki viikot sharedprefencissä ja pistää sen array
                Map<String, ?> keys = prefGet.getAll();

                for (Map.Entry<String, ?> entry : keys.entrySet()) {
                    array.add(entry.getValue().toString());
                }
                //Sen jälkeen jakaa sen kahteen että pysty pistää treemappin järjestämään niitä numero sarjan mukaisesti että korkein ensimmäisenä
                for (int counter = 0; counter < array.size(); counter++) {
                    String s = array.get(counter);
                    String[] str = s.split(" ");
                    String cout = str[0];
                    int d = Integer.parseInt(cout);
                    String vuosi = str[1];
                    String viikko = str[2];
                    String as = viikko + " " + vuosi;
                    treemap.put(d, as);
                }
                Set set = treemap.entrySet();
                Iterator i = set.iterator();
                while (i.hasNext()) {
                    Map.Entry me = (Map.Entry) i.next();
                    //lisää arrayViikko että pystyy nähdä spinnerissä
                    arrayViikko.add("Viikko " + (String) me.getValue());
                }
                //if varten
                arrayList.add("1");
                //pistä sen spinnerin
                ArrayAdapter<String> vuosiAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayViikko);
                //millainen spinnner
                vuosiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //Asettaa tämän ListView-kuvan takana olevat tiedot. Tähän menetelmään välitetyn sovittimen voi kääriä WrapperListAdapter, tällä hetkellä käytössä olevien ListView-ominaisuuksien mukaan
                vuosiSpinner.setAdapter(vuosiAdapter);

            }
        });

        Button kuukausi = (Button) getView().findViewById(R.id.button3);
        kuukausi.setOnClickListener(new View.OnClickListener() {
            /**
             *Kun kuukausi nappi on painattu se hakee kaikki vuodet SharedPreferences ja pistää sen array että pystyt nähdä spinneristä
             * @param v kun näkymää on napsautettu.
             */
            @Override
            public void onClick(View v) {
                //Poistaa kaikki arrayList et se on tyhjä spinneriä varten
                arrayViikko.removeAll(arrayViikko);
                array.removeAll(array);
                arrayList.removeAll(arrayList);
                arrayVuosi.removeAll(arrayVuosi);
                //Hakee sharedpreferences kuukausi
                prefGet = getActivity().getSharedPreferences("Months", Activity.MODE_PRIVATE);
                //Hankki kaikki viikot sharedprefencissä ja pistää sen array
                Map<String, ?> keys = prefGet.getAll();

                for (Map.Entry<String, ?> entry : keys.entrySet()) {
                    arrayVuosi.add(entry.getValue().toString());
                }
                //Sen jälkeen järjestään kaikki numerot mitä olen antanut niikle
                Collections.sort(arrayVuosi, new Comparator<String>() {
                    public int compare(String o1, String o2) {
                        return extractInt(o2) - extractInt(o1);
                    }

                    int extractInt(String s) {
                        String num = s.replaceAll("\\D", "");
                        // return 0 if no digits found
                        return num.isEmpty() ? 0 : Integer.parseInt(num);
                    }
                });
                //Jaa niitä että pystyn otta vain kuukausi ja vuoden tiedot
                for (int counter = 0; counter < arrayVuosi.size(); counter++) {
                    String s = arrayVuosi.get(counter);
                    String[] str = s.split(" ");
                    String vuosi = str[1];
                    String kuukausi = str[2];
                    array.add(kuukausi + " " + vuosi);
                }

                //if varten
                arrayVuosi.add("1");
                //pistä sen spinnerin
                ArrayAdapter<String> vuosiAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, array);
                //millainen spinnner
                vuosiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //Asettaa tämän ListView-kuvan takana olevat tiedot. Tähän menetelmään välitetyn sovittimen voi kääriä WrapperListAdapter, tällä hetkellä käytössä olevien ListView-ominaisuuksien mukaan
                vuosiSpinner.setAdapter(vuosiAdapter);


            }

        });


        Button vuosi = (Button) getView().findViewById(R.id.button5);
        vuosi.setOnClickListener(new View.OnClickListener() {
            /**
             *Kun vuosi nappi on painattu se hakee kaikki vuodet SharedPreferences ja pistää sen arrayVuosi että pystyt nähdä spinneristä
             * @param v kun näkymää on napsautettu.
             */
            @Override
            public void onClick(View v) {
                //Poistaa kaikki arrayList et se on tyhjä spinneriä varten
                arrayList.removeAll(arrayList);
                arrayViikko.removeAll(arrayViikko);
                arrayVuosi.removeAll(arrayVuosi);
                //if varten
                arrayViikko.add("1");
                //Hakee sharedpreferences vuodet
                prefGet = getActivity().getSharedPreferences("Year", Activity.MODE_PRIVATE);
                //Hankki kaikki viikot sharedprefencissä ja pistää sen arrayVuosi
                Map<String, ?> keys = prefGet.getAll();

                for (Map.Entry<String, ?> entry : keys.entrySet()) {

                    arrayVuosi.add("Vuosi " + entry.getValue().toString());

                }
                //pistä sen spinnerin
                ArrayAdapter<String> vuosiAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayVuosi);
                //millainen spinnner
                vuosiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //Asettaa tämän ListView-kuvan takana olevat tiedot. Tähän menetelmään välitetyn sovittimen voi kääriä WrapperListAdapter, tällä hetkellä käytössä olevien ListView-ominaisuuksien mukaan
                vuosiSpinner.setAdapter(vuosiAdapter);

            }

        });
        //kun mene kulutus fragmenttin ja ei ole valinnut mitään
        arrayToday.add("Valitse ajankohta painikkeista");
        //pistä sen spinnerin
        ArrayAdapter<String> vuosiAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayToday);
        //millainen spinnner
        vuosiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Asettaa tämän ListView-kuvan takana olevat tiedot. Tähän menetelmään välitetyn sovittimen voi kääriä WrapperListAdapter, tällä hetkellä käytössä olevien ListView-ominaisuuksien mukaan
        vuosiSpinner.setAdapter(vuosiAdapter);
        //Rekisteröi painausta, kun tätä AdapterView-kohdetta on napsautettu.
        vuosiSpinner.setOnItemSelectedListener(this);
    }

    /**
     * Kun spinneristä on valittu viikko, kuukasi ja vuosi lähettä sen etenpäin toisiin methodin
     *
     * @param parent   AdapterView, jossa valinta tapahtui
     * @param view     Napsautettu näkymä AdapterView-näkymässä
     * @param position Missä valittu on tapahtunut
     * @param id       Valitun kohteen rivitunnus
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // että pysty pistää textiä
        TextView months = (TextView) getActivity().findViewById(R.id.monthsText);
        TextView olutHinta = (TextView) getActivity().findViewById(R.id.olutHinta);
        TextView olutKoko = (TextView) getActivity().findViewById(R.id.olutKoko);
        TextView siideriHinta = (TextView) getActivity().findViewById(R.id.siideriHinta);
        TextView siideriKoko = (TextView) getActivity().findViewById(R.id.siideriKoko);
        TextView viiniHinta = (TextView) getActivity().findViewById(R.id.viiniHinta);
        TextView viiniKoko = (TextView) getActivity().findViewById(R.id.viiniKoko);
        TextView viinaHinta = (TextView) getActivity().findViewById(R.id.viinaHinta);
        TextView viinaKoko = (TextView) getActivity().findViewById(R.id.viinaKoko);
        TextView lonkeroHinta = (TextView) getActivity().findViewById(R.id.lonkeroHinta);
        TextView lonkeroKoko = (TextView) getActivity().findViewById(R.id.lonkeroKoko);
        TextView liikoriHinta = (TextView) getActivity().findViewById(R.id.likooriHinta);
        TextView liikoriKoko = (TextView) getActivity().findViewById(R.id.likooriKoko);
        TextView viskiHinta = (TextView) getActivity().findViewById(R.id.viskiHinta);
        TextView viskiKoko = (TextView) getActivity().findViewById(R.id.viskiKoko);
        TextView iAmPoor = (TextView) getActivity().findViewById(R.id.totalSumma);
        TextView iAmFull = (TextView) getActivity().findViewById(R.id.totalLitrat);

        String selectedSpinner;
        String test;
        String vaihtaja;
        String litra;
        String end;

        if (arrayToday.contains("Valitse ajankohta painikkeista")) {
            //Asetta vuoden
            months.setText("Vuosi on " + stringDate);
            makeItRain = 0;
            makeitSnow = 0;
            //tulostaa kaikki eurot vuodessa
            olutHinta.setText(printerYearEuro("olutEuro", stringDate) + "€");
            viiniHinta.setText(printerYearEuro("viiniEuro", stringDate) + "€");
            siideriHinta.setText(printerYearEuro("siideriEuro", stringDate) + "€");
            lonkeroHinta.setText(printerYearEuro("lonkeroEuro", stringDate) + "€");
            liikoriHinta.setText(printerYearEuro("likooriEuro", stringDate) + "€");
            viinaHinta.setText(printerYearEuro("viinaEuro", stringDate) + "€");
            viskiHinta.setText(printerYearEuro("viskiEuro", stringDate) + "€");

            //tulostaa kaikki litrat vuodessa
            olutKoko.setText(printerYearLitra("olutLitra", stringDate) + "l");
            viiniKoko.setText(printerYearLitra("viiniLitra", stringDate) + "l");
            siideriKoko.setText(printerYearLitra("siideriLitra", stringDate) + "l");
            lonkeroKoko.setText(printerYearLitra("lonkeroLitra", stringDate) + "l");
            liikoriKoko.setText(printerYearLitra("likooriLitra", stringDate) + "l");
            viinaKoko.setText(printerYearLitra("viinaLitra", stringDate) + "l");
            viskiKoko.setText(printerYearLitra("viskiLitra", stringDate) + "l");

            //Muokka seen double formatti 12,12254 -> 12,12
            test = new DecimalFormat("##.##").format(makeItRain);
            litra = new DecimalFormat("##.##").format(makeitSnow);
            // vaihta pilkut pisteeksi
            vaihtaja = test.replace(",", ".");
            end = litra.replace(",", ".");

            //pistää koko rahaan
            iAmPoor.setText(vaihtaja + "€");
            iAmFull.setText(end + "l");
        }
        if (arrayViikko.contains("1")) {
            //valittu spinner
            selectedSpinner = parent.getItemAtPosition(position).toString();
            //kun spinerissä näkyy vuodet muokatti pois mutta vielä tarvitaan myöhemmin
            months.setText(" ");
            makeItRain = 0;
            makeitSnow = 0;
            //Jaka valittu spinneri kahteen osaan että voisin heittä argumentttiin
            String[] str = selectedSpinner.split(" ");
            //vuosi
            str2 = str[1];
            //Heitin sharedpreference nimet ja valittu spinneri printerYearEuro methodiin
            olutHinta.setText(printerYearEuro("olutEuro", str2) + "€");
            viiniHinta.setText(printerYearEuro("viiniEuro", str2) + "€");
            siideriHinta.setText(printerYearEuro("siideriEuro", str2) + "€");
            lonkeroHinta.setText(printerYearEuro("lonkeroEuro", str2) + "€");
            liikoriHinta.setText(printerYearEuro("likooriEuro", str2) + "€");
            viinaHinta.setText(printerYearEuro("viinaEuro", str2) + "€");
            viskiHinta.setText(printerYearEuro("viskiEuro", str2) + "€");

            //Heitin sharedpreference nimet ja valittu spinneri printerYearLitra methodiin
            olutKoko.setText(printerYearLitra("olutLitra", str2) + "l");
            viiniKoko.setText(printerYearLitra("viiniLitra", str2) + "l");
            siideriKoko.setText(printerYearLitra("siideriLitra", str2) + "l");
            lonkeroKoko.setText(printerYearLitra("lonkeroLitra", str2) + "l");
            liikoriKoko.setText(printerYearLitra("likooriLitra", str2) + "l");
            viinaKoko.setText(printerYearLitra("viinaLitra", str2) + "l");
            viskiKoko.setText(printerYearLitra("viskiLitra", str2) + "l");

            //Muokka seen double formatti 12,12254 -> 12,12
            test = new DecimalFormat("##.##").format(makeItRain);
            litra = new DecimalFormat("##.##").format(makeitSnow);
            // vaihta pilkkun pisteksi
            vaihtaja = test.replace(",", ".");
            end = litra.replace(",", ".");

            //pistää koko rahaan
            iAmPoor.setText(vaihtaja + "€");
            iAmFull.setText(end + "l");
        }
        if (arrayVuosi.contains("1")) {
            makeItRain = 0;
            makeitSnow = 0;
            //kun spinerissä näkyy vuodet muokatti pois mutta vielä tarvitaan myöhemmin
            selectedSpinner = parent.getItemAtPosition(position).toString();
            //Jaka valittu spinneri kahteen osaan että voisin heittä argumentttiin
            String[] str = selectedSpinner.split(" ");
            //kuukausi
            str1 = str[0];
            //vuosi
            str2 = str[1];
            int kuukausiTunnistaja = 0;
            //kun spinerissä näkyy vuodet muokatti pois mutta vielä tarvitaan myöhemmin
            months.setText(" ");
            if (str1.equals("Tammikuu")) {
                kuukausiTunnistaja = 1;

            } else if (str1.equals("Helmikuu")) {
                kuukausiTunnistaja = 2;

            } else if (str1.equals("Maaliskuu")) {
                kuukausiTunnistaja = 3;


            } else if (str1.equals("Huhtikuu")) {
                kuukausiTunnistaja = 4;


            } else if (str1.equals("Toukokuu")) {
                kuukausiTunnistaja = 5;

            } else if (str1.equals("Kesäkuu")) {
                kuukausiTunnistaja = 6;


            } else if (str1.equals("Heinäkuu")) {
                kuukausiTunnistaja = 7;

            } else if (str1.equals("Elokuu")) {
                kuukausiTunnistaja = 8;

            } else if (str1.equals("Syyskuu")) {
                kuukausiTunnistaja = 9;

            } else if (str1.equals("Lokakuu")) {
                kuukausiTunnistaja = 10;

            } else if (str1.equals("Marraskuu")) {
                kuukausiTunnistaja = 11;

            } else if (str1.equals("Joulukuu")) {
                kuukausiTunnistaja = 12;

            }
            //Heittä methoddin prefsharee kansion nimen ,avain sana ,kuukausi numerona ja vuoden. Sen jälkeen pistää setText
            olutKoko.setText(printerMonthsLitra("olutLitra", "olutlitra", str2, kuukausiTunnistaja) + "l");
            olutHinta.setText(printerMonthsEuro("olutEuro", "oluteuro", str2, kuukausiTunnistaja) + "€");

            //Heittä methoddin prefsharee kansion nimen ,avain sana ,kuukausi numerona ja vuoden. Sen jälkeen pistää setText
            siideriKoko.setText(printerMonthsLitra("siideriLitra", "siiderilitra", str2, kuukausiTunnistaja) + "l");
            siideriHinta.setText(printerMonthsEuro("siideriEuro", "siiderieuro", str2, kuukausiTunnistaja) + "€");

            //Heittä methoddin prefsharee kansion nimen ,avain sana ,kuukausi numerona ja vuoden. Sen jälkeen pistää setText
            viiniKoko.setText(printerMonthsLitra("viiniLitra", "viinilitra", str2, kuukausiTunnistaja) + "l");
            viiniHinta.setText(printerMonthsEuro("viiniEuro", "viinieuro", str2, kuukausiTunnistaja) + "€");

            //Heittä methoddin prefsharee kansion nimen ,avain sana ,kuukausi numerona ja vuoden. Sen jälkeen pistää setText
            viinaKoko.setText(printerMonthsLitra("viinaLitra", "viinalitra", str2, kuukausiTunnistaja) + "l");
            viinaHinta.setText(printerMonthsEuro("viinaEuro", "viinaeuro", str2, kuukausiTunnistaja) + "€");

            //Heittä methoddin prefsharee kansion nimen ,avain sana ,kuukausi numerona ja vuoden. Sen jälkeen pistää setText
            lonkeroKoko.setText(printerMonthsLitra("lonkeroLitra", "lonkerolitra", str2, kuukausiTunnistaja) + "l");
            lonkeroHinta.setText(printerMonthsEuro("lonkeroEuro", "lonkeroeuro", str2, kuukausiTunnistaja) + "€");

            //Heittä methoddin prefsharee kansion nimen ,avain sana ,kuukausi numerona ja vuoden. Sen jälkeen pistää setText
            liikoriKoko.setText(printerMonthsLitra("likooriLitra", "likoorilitra", str2, kuukausiTunnistaja) + "l");
            liikoriHinta.setText(printerMonthsEuro("likooriEuro", "likoorieuro", str2, kuukausiTunnistaja) + "€");

            //Heittä methoddin prefsharee kansion nimen ,avain sana ,kuukausi numerona ja vuoden. Sen jälkeen pistää setText
            viskiKoko.setText(printerMonthsLitra("viskiLitra", "viskilitra", str2, kuukausiTunnistaja) + "l");
            viskiHinta.setText(printerMonthsEuro("viskiEuro", "viskieuro", str2, kuukausiTunnistaja) + "€");
            //Muokka seen double formatti 12,12254 -> 12,12
            test = new DecimalFormat("##.##").format(makeItRain);
            litra = new DecimalFormat("##.##").format(makeitSnow);
            // vaihta pilkkun pisteksi
            vaihtaja = test.replace(",", ".");
            end = litra.replace(",", ".");

            //pistää koko rahaan
            iAmPoor.setText(vaihtaja + "€");
            iAmFull.setText(end + "l");
        }
        if (arrayList.contains("1")) {

            makeItRain = 0;
            makeitSnow = 0;
            //kun spinerissä näkyy vuodet muokatti pois mutta vielä tarvitaan myöhemmin
            selectedSpinner = parent.getItemAtPosition(position).toString();

            //kun spinerissä näkyy vuodet muokatti pois mutta vielä tarvitaan myöhemmin
            months.setText(" ");

            //Heittä methoddin  avain sana (viikko ja vuosi),  juoman nimi. Sen jälkeen pistää setText
            olutKoko.setText(printerWeekLitra(selectedSpinner, "olut") + "l");
            olutHinta.setText(printerWeekEuro(selectedSpinner, "olut") + "€");

            //Heittä methoddin  avain sana (viikko ja vuosi),  juoman nimi. Sen jälkeen pistää setText
            siideriKoko.setText(printerWeekLitra(selectedSpinner, "siideri") + "l");
            siideriHinta.setText(printerWeekEuro(selectedSpinner, "siideri") + "€");

            //Heittä methoddin  avain sana (viikko ja vuosi),  juoman nimi. Sen jälkeen pistää setText
            viiniKoko.setText(printerWeekLitra(selectedSpinner, "viini") + "l");
            viiniHinta.setText(printerWeekEuro(selectedSpinner, "viini") + "€");

            //Heittä methoddin  avain sana (viikko ja vuosi),  juoman nimi. Sen jälkeen pistää setText
            viinaKoko.setText(printerWeekLitra(selectedSpinner, "viina") + "l");
            viinaHinta.setText(printerWeekEuro(selectedSpinner, "viina") + "€");

            //Heittä methoddin  avain sana (viikko ja vuosi),  juoman nimi. Sen jälkeen pistää setText
            lonkeroKoko.setText(printerWeekLitra(selectedSpinner, "lonkero") + "l");
            lonkeroHinta.setText(printerWeekEuro(selectedSpinner, "lonkero") + "€");

            //Heittä methoddin  avain sana (viikko ja vuosi),  juoman nimi. Sen jälkeen pistää setText
            liikoriKoko.setText(printerWeekLitra(selectedSpinner, "likoori") + "l");
            liikoriHinta.setText(printerWeekEuro(selectedSpinner, "likoori") + "€");

            //Heittä methoddin  avain sana (viikko ja vuosi),  juoman nimi. Sen jälkeen pistää setText
            viskiKoko.setText(printerWeekLitra(selectedSpinner, "viski") + "l");
            viskiHinta.setText(printerWeekEuro(selectedSpinner, "viski") + "€");

            //Muokka seen double formatti 12,12254 -> 12,12
            test = new DecimalFormat("##.##").format(makeItRain);
            litra = new DecimalFormat("##.##").format(makeitSnow);
            // vaihta pilkkun pisteksi
            vaihtaja = test.replace(",", ".");
            end = litra.replace(",", ".");

            //pistää koko rahaan
            iAmPoor.setText(vaihtaja + "€");
            iAmFull.setText(end + "l");
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * hakee viikon sharedpreference(SP) kansiosta tiedot ja tulosta ne esille
     *
     * @param product  viikko ja vuosi
     * @param surprice Tuetten nimi
     * @return Palautta string olevan juoman olevan euro kulutettu
     */
    private String printerWeekEuro(String product, String surprice) {
        //hakee sharedpreference(SP) kansion: viikkoJuomat
        Saving pref = new Saving(getActivity(), "viikkoJuomat");

        //jakaa texti että saan tarvittavan viikon ja vuoden
        String splitMan = product;
        String[] str = splitMan.split(" ");
        String viikoa = str[1];
        String vuosia = str[2];
        //hakee sharedpreference(SP) (tuottennimi)euro, viiko ja vuoden avain sanalla
        String end = pref.getStr(surprice + "euro" + viikoa + vuosia);
        // vaihta pilkkun pisteksi
        String miksiEuro = end.replace(",", ".");
        // muokkasen doubleksi
        double Euro = Double.parseDouble(miksiEuro);

        //laskee kaikki eurot yhtensä
        makeItRain += Euro;
        //muokka sen takaisin string
        String test = Double.toString(Euro);


        //palautta mitä on saanut
        return test;
    }

    /**
     * hakee viikon sharedpreference(SP) kansiosta tiedot ja tulosta ne esille
     *
     * @param sasa     viikko ja vuosi
     * @param surprice Tuetten nimi
     * @return Palautta string olevan juoman olevan litra määrä juotu
     */
    private String printerWeekLitra(String sasa, String surprice) {
        //hakee sharedpreference(SP) kansion: viikkoJuomat
        Saving pref = new Saving(getActivity(), "viikkoJuomat");

        //jakaa texti että saan tarvittavan viikon ja vuoden
        String splitMan = sasa;
        String[] str = splitMan.split(" ");
        String viikoa = str[1];
        String vuosia = str[2];
        //hakee sharedpreference(SP) (tuottennimi)litra, viiko ja vuoden avain sanalla
        String litra = pref.getStr(surprice + "litra" + viikoa + vuosia);
        // vaihta pilkkun pisteksi
        String miksiLitra = litra.replace(",", ".");
        // muokkasen doubleksi
        double endGame = Double.parseDouble(miksiLitra);
        //laskee kaikki litra yhtensä
        makeitSnow += endGame;
        //muokka sen takaisin string
        String wee = Double.toString(endGame);


        //palautta mitä on saanut
        return wee;

    }

    /**
     * hakee kuukausi sharedpreference(SP) kansiosta tiedot ja tulosta ne esille
     *
     * @param product      Tuote nimi
     * @param productSmall tuotte pienellä kirjaimella "Pikku moka"
     * @param str2         vuosi
     * @param kuukausi     kuukausi numero
     * @return Palautta string olevan juoman olevan euro kulutettu
     */
    private String printerMonthsEuro(String product, String productSmall, String str2, int kuukausi) {
        //hakee sharedpreference(SP) kansio: juoma esim:olutEuro ja vuosi
        Saving pref = new Saving(getActivity(), product + str2);

        //Hakee avain sanalla juoma esim:olutEuro, kuukausi ja vuoden avain sanalla
        String olut = pref.getStr(productSmall + kuukausi + str2);

        // vaihta pilkkun pisteksi
        String miksiMonthEuro = olut.replace(",", ".");
        //muokka string to double
        double olutEuro = Double.parseDouble(miksiMonthEuro);
        //laskee kaikki eurot yhtensä
        makeItRain += olutEuro;
        //muokka sen takaisin string
        String test = Double.toString(olutEuro);


        //palautta mitä on saanut
        return test;
    }

    /**
     * hakee kuukausi sharedpreference(SP) kansiosta tiedot ja tulosta ne esille
     *
     * @param product      Juoman nimi
     * @param productSmall Juoma pienellä kirjaimella "Pikku moka"
     * @param str2         Vuosi
     * @param kuukausi     Kuukauden numero
     * @return Palautta string olevan juoman olevan litra juonut
     */
    private String printerMonthsLitra(String product, String productSmall, String str2, int kuukausi) {
        //hakee sharedpreference(SP) kansio: juoma esim:olutLitra ja vuosi
        Saving pref = new Saving(getActivity(), product + str2);

        //Hakee avain sanalla juoma esim:olutlitra, kuukausi ja vuoden avain sanalla
        String olut = pref.getStr(productSmall + kuukausi + str2);
        // vaihta pilkkun pisteksi
        String miksiMonth = olut.replace(",", ".");
        //muokka string to double
        double olutEuro = Double.parseDouble(miksiMonth);
        //laskee kaikki litra yhtensä
        makeitSnow += olutEuro;
        //Piste muuttu pilkuksi
        String test = Double.toString(olutEuro);
        //Piste muuttu pilkuksi

        //palautta mitä on saanut
        return test;
    }

    /**
     * Hakee toistuvasti sharedpreference(SP) kaikki tiedot eurot ja lisää sen double olutar
     *
     * @param product Juoman nimi
     * @param year    Vuosi
     * @return Palautta Vuoden käytetty euro juomat
     */
    private String printerYearEuro(String product, String year) {
        olutar = 0;
        //hakee sharedpreference(SP) kansion: oluEuroVuosi
        SharedPreferences pref = getActivity().getSharedPreferences(product + year, Activity.MODE_PRIVATE);

        //hankkki kaikki sharedpreference(SP) ja pistää sen Map objektiin
        Map<String, ?> keysa = pref.getAll();

        //selaa elementtejä taulukoissa
        for (Map.Entry<String, ?> entry : keysa.entrySet()) {

            //hankki string
            String test = entry.getValue().toString();
            // vaihta pilkkun pisteksi
            String miksi = test.replace(",", ".");
            //muokka string to double
            double olutEuro = Double.parseDouble(miksi);
            //lisää sen double
            olutar += olutEuro;
        }

        //koko euro yhtensä
        makeItRain += olutar;
        //muokka muotoo että se on 00.999->00.99
        String test = new DecimalFormat("##.##").format(olutar);
        //Vaihtaa pillkua pisteksi
        String vaihtaja = test.replace(",", ".");
        //palautta mitä on saanut
        return vaihtaja;
    }

    /**
     * Hakee toistuvasti sharedpreference(SP) kaikki tiedot litrasta ja lisää sen double olutar
     *
     * @param product Juoman nimi
     * @param year    Vuosi
     * @returnta Palauttaa vuoden vedetty juomat
     */
    private String printerYearLitra(String product, String year) {
        olutar = 0;
        //hakee sharedpreference(SP) kansion: olutLitraVuosi
        SharedPreferences pref = getActivity().getSharedPreferences(product + year, Activity.MODE_PRIVATE);//hakee sharedpreference(SP) kansion: oluEuro

        //hankkki kaikki sharedpreference(SP) ja pistää sen Map objektiin
        Map<String, ?> keysa = pref.getAll();
        //selaa elementtejä taulukoissa
        for (Map.Entry<String, ?> entry : keysa.entrySet()) {
            //hankki string
            String test = entry.getValue().toString();
            // vaihta pilkkun pisteksi
            String miksi2 = test.replace(",", ".");
            //muokka string to double
            double olutEuro = Double.parseDouble(miksi2);
            //lisää sen double
            olutar += olutEuro;
        }
        //koko litra yhtensä
        makeitSnow += olutar;
        //muokka muotoo että se on 00.999->00.99. Jos tain systä muokkasi sen , kun oli . emulator
        String test = new DecimalFormat("##.##").format(olutar);
        //Vaihtaa pillkua pisteksi
        String vaihtaja = test.replace(",", ".");
        //palautta mitä on saanut
        return vaihtaja;
    }

}
