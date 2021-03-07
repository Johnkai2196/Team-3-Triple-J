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
import com.example.testproju2.ui.home.DataProccessor;

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
    String s;
    String str1;
    String str2;
    double olutar;
    double makeItRain;
    //kaikki tehty arraylist
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<String> array = new ArrayList<>();
    ArrayList<String> arrayVuosi = new ArrayList<>();
    ArrayList<String> arrayViikko = new ArrayList<>();
    ArrayList<String> arrayToday = new ArrayList<>();
    Map<Integer, String> treemap = new TreeMap<Integer, String>(Collections.reverseOrder());


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        return root;
    }

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
                //Sen jälkeen jakaa sen kahteen että pysty pistää treemappin järjestämään niitä numero sarjan mukaisesti
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

                ArrayAdapter<String> vuosiAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayViikko);
                vuosiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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
                ArrayAdapter<String> vuosiAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, array);
                vuosiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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

                ArrayAdapter<String> vuosiAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayVuosi);
                vuosiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                vuosiSpinner.setAdapter(vuosiAdapter);

            }

        });
        //kun mene kulutus fragmenttin ja ei ole valinnut mitään
        arrayToday.add("Valitse vuodenaika painikkeista");
        ArrayAdapter<String> vuosiAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arrayToday);
        vuosiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vuosiSpinner.setAdapter(vuosiAdapter);
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

        String selectedSpinner;
        String test;
        String vaihtaja;

        if (arrayToday.contains("Valitse vuodenaika painikkeista")) {
            //Asetta vuoden
            months.setText("Vuosi on " + stringDate);
            makeItRain = 0;
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

            // vaihta pistettä pilkkuun
            vaihtaja = test.replace(".", ",");

            //pistää koko rahaan
            iAmPoor.setText(vaihtaja + "€");
        }
        if (arrayViikko.contains("1")) {
            //valittu spinner
            selectedSpinner = parent.getItemAtPosition(position).toString();
            //kun spinerissä näkyy vuodet muokatti pois mutta vielä tarvitaan myöhemmin
            months.setText(" ");
            makeItRain = 0;
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

            // vaihtaa pisteet pilkuksu
            vaihtaja = test.replace(".", ",");

            //asetta koko hintaan
            iAmPoor.setText(vaihtaja + "€");

        }
        if (arrayVuosi.contains("1")) {
            makeItRain = 0;
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

            //Pistestä pilkkuun
            vaihtaja = test.replace(".", ",");
            //asetta setText
            iAmPoor.setText(vaihtaja + "€");
        }
        if (arrayList.contains("1")) {

            makeItRain = 0;
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

            //Pistestä pilkkuun
            vaihtaja = test.replace(".", ",");
            //asetta setText
            iAmPoor.setText(vaihtaja + "€");
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
        DataProccessor pref = new DataProccessor(getActivity(), "viikkoJuomat");

        //jakaa texti että saan tarvittavan viikon ja vuoden
        String splitMan = product;
        String[] str = splitMan.split(" ");
        String viikoa = str[1];
        String vuosia = str[2];
        //hakee sharedpreference(SP) (tuottennimi)euro, viiko ja vuoden avain sanalla
        String end = pref.getStr(surprice + "euro" + viikoa + vuosia);

        // muokkasen doubleksi
        double Euro = Double.parseDouble(end);

        //laskee kaikki eurot yhtensä
        makeItRain += Euro;
        //muokka sen takaisin string
        String test = Double.toString(Euro);

        //Piste muuttu pilkuksi
        String vaihtaja = test.replace(".", ",");

        //palautta mitä on saanut
        return vaihtaja;
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
        DataProccessor pref = new DataProccessor(getActivity(), "viikkoJuomat");

        //jakaa texti että saan tarvittavan viikon ja vuoden
        String splitMan = sasa;
        String[] str = splitMan.split(" ");
        String viikoa = str[1];
        String vuosia = str[2];
        //hakee sharedpreference(SP) (tuottennimi)litra, viiko ja vuoden avain sanalla
        String litra = pref.getStr(surprice + "litra" + viikoa + vuosia);

        // muokkasen doubleksi
        double endGame = Double.parseDouble(litra);

        //muokka sen takaisin string
        String wee = Double.toString(endGame);
        //Piste muuttu pilkuksi
        String enderman = wee.replace(".", ",");
        //palautta mitä on saanut
        return enderman;

    }

    /**
     *hakee kuukausi sharedpreference(SP) kansiosta tiedot ja tulosta ne esille
     * @param product Tuote nimi
     * @param productSmall tuotte pienellä kirjaimella "Pikku moka"
     * @param str2 vuosi
     * @param kuukausi kuukausi numero
     * @return Palautta string olevan juoman olevan euro kulutettu
     */
    private String printerMonthsEuro(String product, String productSmall, String str2, int kuukausi) {
        DataProccessor pref = new DataProccessor(getActivity(), product + str2);

        String olut = pref.getStr(productSmall + kuukausi + str2);
        double olutEuro = Double.parseDouble(olut);
        makeItRain += olutEuro;
        String test = Double.toString(olutEuro);
        String vaihtaja = test.replace(".", ",");
        return vaihtaja;
    }

    /**
     *hakee kuukausi sharedpreference(SP) kansiosta tiedot ja tulosta ne esille
     * @param product Juoman nimi
     * @param productSmall Juoma pienellä kirjaimella "Pikku moka"
     * @param str2 Vuosi
     * @param kuukausi Kuukauden numero
     * @return Palautta string olevan juoman olevan litra juonut
     */
    private String printerMonthsLitra(String product, String productSmall, String str2, int kuukausi) {
        DataProccessor pref = new DataProccessor(getActivity(), product + str2);

        String olut = pref.getStr(productSmall + kuukausi + str2);
        double olutEuro = Double.parseDouble(olut);

        String test = Double.toString(olutEuro);
        String vaihtaja = test.replace(".", ",");
        return vaihtaja;
    }

    /**
     *
     * @param product Juoman nimi
     * @param year Vuosi
     * @return
     */
    private String printerYearEuro(String product, String year) {
        olutar = 0;
        SharedPreferences pref = getActivity().getSharedPreferences(product + year, Activity.MODE_PRIVATE);//hakee sharedpreference(SP) kansion: oluEuro


        Map<String, ?> keysa = pref.getAll();

        for (Map.Entry<String, ?> entry : keysa.entrySet()) {

            String test = entry.getValue().toString();
            double olutEuro = Double.parseDouble(test);
            olutar += olutEuro;
        }

        makeItRain += olutar;
        String test = new DecimalFormat("##.##").format(olutar);

        String vaihtaja = test.replace(".", ",");
        return vaihtaja;
    }


    private String printerYearLitra(String product, String year) {
        olutar = 0;
        SharedPreferences pref = getActivity().getSharedPreferences(product + year, Activity.MODE_PRIVATE);//hakee sharedpreference(SP) kansion: oluEuro

        Map<String, ?> keysa = pref.getAll();

        for (Map.Entry<String, ?> entry : keysa.entrySet()) {

            String test = entry.getValue().toString();
            double olutEuro = Double.parseDouble(test);
            olutar += olutEuro;
        }

        String test = new DecimalFormat("##.##").format(olutar);

        String vaihtaja = test.replace(".", ",");
        return vaihtaja;
    }

}