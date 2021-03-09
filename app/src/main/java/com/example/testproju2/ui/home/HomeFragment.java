package com.example.testproju2.ui.home;

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

import java.text.DecimalFormat;
import java.util.Date;


public class HomeFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    //Counterit
    DoubleCounter annokset;
    Counter kalorit;
    Counter oluenGrammat;
    Counter siideriGrammat;
    Counter lonkeroGrammat;
    Counter viinaGrammat;
    Counter viiniGrammat;
    Counter likooriGrammat;
    Counter viskiGrammat;
    Counter kplOlut;
    Counter kplSiideri;
    Counter kplLonkero;
    Counter kplViina;
    Counter kplLikoori;
    Counter kplViini;
    Counter kplViski;
    //Int
    int juusto = 301;
    int counter;
    int alkoholiGrammoina; // kaikki alkoholien grammat yhteensä
    int kaloritMuunnos;
    int haihtuuTunnissa;
    int tuntiaTulos;
    //Double
    double prominMaara = 0;
    double annosDesimaali;
    //Double Eurolasku
    double olutEuroLasku;
    double siideriEuroLasku;
    double lonkeroEuroLasku;
    double viinaEuroLasku;
    double likooriEuroLasku;
    double viiniEuroLasku;
    double viskiEuroLasku;
    //Double litralasku
    double olutLitraLasku;
    double siideriLitraLasku;
    double lonkeroLitraLasku;
    double viinaLitraLasku;
    double likooriLitraLasku;
    double viiniLitraLasku;
    double viskiLitraLasku;
    //Double eurolaskuWeek
    double olutEuroLaskuWeek;
    double siideriEuroLaskuWeek;
    double lonkeroEuroLaskuWeek;
    double viinaEuroLaskuWeek;
    double likooriEuroLaskuWeek;
    double viiniEuroLaskuWeek;
    double viskiEuroLaskuWeek;
    double olutLitraLaskuWeek;
    double siideriLitraLaskuWeek;
    double lonkeroLitraLaskuWeek;
    double viinaLitraLaskuWeek;
    double likooriLitraLaskuWeek;
    double viiniLitraLaskuWeek;
    double viskiLitraLaskuWeek;

    Date date = new Date();
    CalendarFormat set = new CalendarFormat(date);
    String Week = set.getWeek();
    String vuosia = set.getYear();
    String stringDateMonthYear = set.getDateMonthYear();
    String euString = set.getMonthYear();
    String stringDat = set.getMonth();

    /**
     * Luo tämän fragmentin näkymä sille annettujen argumenttien avulla.
     *
     * @param inflater           LayoutInflater-objekti, jota voidaan käyttää kaikkien fragmenttien paisuttamiseen
     * @param container          Jos se ei ole non-null, tämä on päänäkymä, johon fragmentin käyttöliittymä tulisi liittää.
     *                           Katkelman ei pitäisi lisätä itse näkymää, mutta sitä voidaan käyttää näkymän LayoutParam-tiedostojen luomiseen. Tämä arvo voi olla tyhjä
     * @param savedInstanceState Jos se ei ole non-null, tätä fragmenttia rakennetaan uudelleen edellisestä tallennetusta tilasta, kuten tässä on annettu.
     * @return palautta minun luotu xml
     */
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //luo View minun xml:sstä
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    /**
     * Tässä methodissa rakennetaan Spinnerit
     * Tehdään counterit, joihin lisätään arvoja onClick -methodissa
     * Sisältää myös onClick -methodin buttoneille spinnerien valinnoista
     * Spinnerin lista tehdään strings.xml tiedoista
     *
     * @param view               näkymä
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        kplOlut = new Counter();
        kplSiideri = new Counter();
        kplLonkero = new Counter();
        kplViina = new Counter();
        kplLikoori = new Counter();
        kplViini = new Counter();
        kplViski = new Counter();

        oluenGrammat = new Counter();
        siideriGrammat = new Counter();
        lonkeroGrammat = new Counter();
        viinaGrammat = new Counter();
        likooriGrammat = new Counter();
        viiniGrammat = new Counter();
        viskiGrammat = new Counter();
        kalorit = new Counter();
        annokset = new DoubleCounter();

        //OLUT SPINNER LAATIKOT
        //Oluen promille valikko
        Spinner olutSpinner1 = getView().findViewById(R.id.olutPromilleSpinner); //etsii spinnerin id perusteella
        // luo listan string.xml olutPromillen tiedoista
        ArrayAdapter<CharSequence> olutAdapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.olutPromille, android.R.layout.simple_spinner_item);
        olutAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //asettaa putoavan listan ulkonäön/muodon
        olutSpinner1.setAdapter(olutAdapter1);
        olutSpinner1.setOnItemSelectedListener(this); //spinner kuuntelee klikkausta
        //Oluen määrä valikko
        Spinner olutSpinner2 = getView().findViewById(R.id.olutMaaraSpinner);
        ArrayAdapter<CharSequence> olutAdapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.olutMaara, android.R.layout.simple_spinner_item);
        olutAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        olutSpinner2.setAdapter(olutAdapter2);
        olutSpinner2.setOnItemSelectedListener(this);
        //Oluen hinta valikko
        Spinner olutSpinner3 = getView().findViewById(R.id.olutHintaSpinner);
        ArrayAdapter<CharSequence> olutAdapter3 = ArrayAdapter.createFromResource(getActivity(), R.array.olutHinta, android.R.layout.simple_spinner_item);
        olutAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        olutSpinner3.setAdapter(olutAdapter3);
        olutSpinner3.setOnItemSelectedListener(this);

        //SIIDERI SPINNER LAATIKOT
        //Siiderin promille valikko
        Spinner siideriSpinner1 = getView().findViewById(R.id.siideriPromilleSpinner);
        ArrayAdapter<CharSequence> siideriAdapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.siideriPromille, android.R.layout.simple_spinner_item);
        siideriAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        siideriSpinner1.setAdapter(siideriAdapter1);
        siideriSpinner1.setOnItemSelectedListener(this);
        //Siiderin määrä valikko
        Spinner siideriSpinner2 = getView().findViewById(R.id.siideriMaaraSpinner);
        ArrayAdapter<CharSequence> siideriAdapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.siideriMaara, android.R.layout.simple_spinner_item);
        siideriAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        siideriSpinner2.setAdapter(siideriAdapter2);
        siideriSpinner2.setOnItemSelectedListener(this);
        //Siiderin hinta valikko
        Spinner siideriSpinner3 = getView().findViewById(R.id.siideriHintaSpinner);
        ArrayAdapter<CharSequence> siideriAdapter3 = ArrayAdapter.createFromResource(getActivity(), R.array.siideriHinta, android.R.layout.simple_spinner_item);
        siideriAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        siideriSpinner3.setAdapter(siideriAdapter3);
        siideriSpinner3.setOnItemSelectedListener(this);

        //LONKERO SPINNER LAATIKOT
        //Lonkeron promille valikko
        Spinner lonkeroSpinner1 = getView().findViewById(R.id.lonkeroPromilleSpinner);
        ArrayAdapter<CharSequence> lonkeroAdapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.lonkeroPromille, android.R.layout.simple_spinner_item);
        lonkeroAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lonkeroSpinner1.setAdapter(lonkeroAdapter1);
        lonkeroSpinner1.setOnItemSelectedListener(this);
        //Lonkeron määrä valikko
        Spinner lonkeroSpinner2 = getView().findViewById(R.id.lonkeroMaaraSpinner);
        ArrayAdapter<CharSequence> lonkeroAdapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.lonkeroMaara, android.R.layout.simple_spinner_item);
        lonkeroAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lonkeroSpinner2.setAdapter(lonkeroAdapter2);
        lonkeroSpinner2.setOnItemSelectedListener(this);
        //Lonkeron hinta valikko
        Spinner lonkeroSpinner3 = getView().findViewById(R.id.lonkeroHintaSpinner);
        ArrayAdapter<CharSequence> lonkeroAdapter3 = ArrayAdapter.createFromResource(getActivity(), R.array.lonkeroHinta, android.R.layout.simple_spinner_item);
        lonkeroAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lonkeroSpinner3.setAdapter(lonkeroAdapter3);
        lonkeroSpinner3.setOnItemSelectedListener(this);

        //VIINA SPINNER LAATIKOT
        //Viinan promille valikko
        Spinner viinaSpinner1 = getView().findViewById(R.id.viinaPromilleSpinner);
        ArrayAdapter<CharSequence> viinaAdapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.viinaPromille, android.R.layout.simple_spinner_item);
        viinaAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        viinaSpinner1.setAdapter(viinaAdapter1);
        viinaSpinner1.setOnItemSelectedListener(this);
        //Viinan määrä valikko
        Spinner viinaSpinner2 = getView().findViewById(R.id.viinaMaaraSpinner);
        ArrayAdapter<CharSequence> viinaAdapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.viinaMaara, android.R.layout.simple_spinner_item);
        viinaAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        viinaSpinner2.setAdapter(viinaAdapter2);
        viinaSpinner2.setOnItemSelectedListener(this);
        //Viinan hinta valikko
        Spinner viinaSpinner3 = getView().findViewById(R.id.viinaHintaSpinner);
        ArrayAdapter<CharSequence> viinaAdapter3 = ArrayAdapter.createFromResource(getActivity(), R.array.viinaHinta, android.R.layout.simple_spinner_item);
        viinaAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        viinaSpinner3.setAdapter(viinaAdapter3);
        viinaSpinner3.setOnItemSelectedListener(this);

        //LIKÖÖRI SPINNER LAATIKOT
        //Liköörin promille valikko
        Spinner likooriSpinner1 = getView().findViewById(R.id.likooriPromilleSpinner);
        ArrayAdapter<CharSequence> likooriAdapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.likooriPromille, android.R.layout.simple_spinner_item);
        likooriAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        likooriSpinner1.setAdapter(likooriAdapter1);
        likooriSpinner1.setOnItemSelectedListener(this);
        //Liköörin määrä valikko
        Spinner likooriSpinner2 = getView().findViewById(R.id.likooriMaaraSpinner);
        ArrayAdapter<CharSequence> likooriAdapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.likooriMaara, android.R.layout.simple_spinner_item);
        likooriAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        likooriSpinner2.setAdapter(likooriAdapter2);
        likooriSpinner2.setOnItemSelectedListener(this);
        //Liköörin hinta valikko
        Spinner likooriSpinner3 = getView().findViewById(R.id.likooriHintaSpinner);
        ArrayAdapter<CharSequence> likooriAdapter3 = ArrayAdapter.createFromResource(getActivity(), R.array.likooriHinta, android.R.layout.simple_spinner_item);
        likooriAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        likooriSpinner3.setAdapter(likooriAdapter3);
        likooriSpinner3.setOnItemSelectedListener(this);

        //VIINI SPINNER LAATIKOT
        //Viinin promille valikko
        Spinner viiniSpinner1 = getView().findViewById(R.id.viiniPromilleSpinner);
        ArrayAdapter<CharSequence> viiniAdapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.viiniPromille, android.R.layout.simple_spinner_item);
        viiniAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        viiniSpinner1.setAdapter(viiniAdapter1);
        viiniSpinner1.setOnItemSelectedListener(this);
        //Viinin määrä valikko
        Spinner viiniSpinner2 = getView().findViewById(R.id.viiniMaaraSpinner);
        ArrayAdapter<CharSequence> viiniAdapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.viiniMaara, android.R.layout.simple_spinner_item);
        viiniAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        viiniSpinner2.setAdapter(viiniAdapter2);
        viiniSpinner2.setOnItemSelectedListener(this);
        //Viinin hinta valikko
        Spinner viiniSpinner3 = getView().findViewById(R.id.viiniHintaSpinner);
        ArrayAdapter<CharSequence> viiniAdapter3 = ArrayAdapter.createFromResource(getActivity(), R.array.viiniHinta, android.R.layout.simple_spinner_item);
        viiniAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        viiniSpinner3.setAdapter(viiniAdapter3);
        viiniSpinner3.setOnItemSelectedListener(this);

        //VISKI SPINNER LAATIKOT
        //Viskin promille valikko
        Spinner viskiSpinner1 = getView().findViewById(R.id.viskiPromilleSpinner);
        ArrayAdapter<CharSequence> viskiAdapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.viskiPromille, android.R.layout.simple_spinner_item);
        viskiAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        viskiSpinner1.setAdapter(viskiAdapter1);
        viskiSpinner1.setOnItemSelectedListener(this);
        //Viskin määrä valikko
        Spinner viskiSpinner2 = getView().findViewById(R.id.viskiMaaraSpinner);
        ArrayAdapter<CharSequence> viskiAdapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.viskiMaara, android.R.layout.simple_spinner_item);
        viskiAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        viskiSpinner2.setAdapter(viskiAdapter2);
        viskiSpinner2.setOnItemSelectedListener(this);
        //Viskin hinta valikko
        Spinner viskiSpinner3 = getView().findViewById(R.id.viskiHintaSpinner);
        ArrayAdapter<CharSequence> viskiAdapter3 = ArrayAdapter.createFromResource(getActivity(), R.array.viskiHinta, android.R.layout.simple_spinner_item);
        viskiAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        viskiSpinner3.setAdapter(viskiAdapter3);
        viskiSpinner3.setOnItemSelectedListener(this);


        //JUOMIEN LISÄYS BUTTONIT
        //Olut button
        Button olutButton = (Button) getView().findViewById(R.id.olutListaButton);
        olutButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Seuraavassa 7 onClick methodissa on tehdään if-lauseet spinnerlaatikoiden valinnoista.
             * määriä nostetaan valintojen mukaisesti
             * lopussa poistetaan stringeistä € ja l merkit, jotta saadaan ne double muotoon, sitten päivitetään määrät laskureihin
             * @param v
             */
            public void onClick(View v) {
                // tässä lisää valitut spinnerlaatikot ja tekee promillelaskuri() methodin
                //jos spinnerlaatikoiden valinnat vastaavat seuraavia, lisää oluenGramman määrää 1(13g)
                if ((olutSpinner1.getSelectedItem().toString().equals("4.7%")) &&
                        (olutSpinner2.getSelectedItem().toString().equals("0.33l"))) {
                    oluenGrammat.increaseByOne();
                    kalorit.increaseKaloritOlut1();
                    kplOlut.increaseKpl();
                    annokset.AnnosMietoKolme();
                }
                if ((olutSpinner1.getSelectedItem().toString().equals("4.7%")) &&
                        (olutSpinner2.getSelectedItem().toString().equals("0.5l"))) {
                    oluenGrammat.increaseByOneAndHalf();
                    kalorit.increaseKaloritOlut2();
                    kplOlut.increaseKpl();
                    annokset.AnnosMietoViisi();
                }
                if ((olutSpinner1.getSelectedItem().toString().equals("5.5%")) &&
                        (olutSpinner2.getSelectedItem().toString().equals("0.33l"))) {
                    oluenGrammat.increaseByOneAndHalf();
                    kalorit.increaseKaloritOlut3();
                    kplOlut.increaseKpl();
                    annokset.AnnosMietoNelja();
                }
                if ((olutSpinner1.getSelectedItem().toString().equals("5.5%")) &&
                        (olutSpinner2.getSelectedItem().toString().equals("0.5l"))) {
                    oluenGrammat.increaseByTwo();
                    kalorit.increaseKaloritOlut4();
                    kplOlut.increaseKpl();
                    annokset.AnnosMietoKuusi();
                }
                //hankki Spinnerissä valittu itemmi
                String spinner3 = olutSpinner3.getSelectedItem().toString();
                //Vaihtaa kirjaimet että pystyn muokkka sen doubleksi
                String loppu = spinner3.replace("€", " ");
                //lisää olutEuroLaskuu
                olutEuroLasku += Double.parseDouble(loppu);
                //hankki Litra oleva spinner
                String spinner2 = olutSpinner2.getSelectedItem().toString();
                //Vaihtaa kirjaimet että pystyn muokkka sen doubleksi
                String loppuTulos = spinner2.replace("l", " ");
                //lisää oluLitraLasku
                olutLitraLasku += Double.parseDouble(loppuTulos);
                //vetää nämä methodit
                promillelaskuri();
                kalorilaskuri();
                juomienKpl();
                annostenMaara();
                hamppariMuunnos();
                alkoholinHaihtuminen();
            }
        });

        //Siideri button
        Button siideriButton = (Button) getView().findViewById(R.id.siideriListaButton);
        siideriButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if ((siideriSpinner1.getSelectedItem().toString().equals("4.7%")) &&
                        (siideriSpinner2.getSelectedItem().toString().equals("0.33l"))) {
                    siideriGrammat.increaseByOne();
                    kalorit.increaseKaloritSiideri2();
                    kplSiideri.increaseKpl();
                    annokset.AnnosMietoKolme();
                }
                if ((siideriSpinner1.getSelectedItem().toString().equals("4.7%")) &&
                        (siideriSpinner2.getSelectedItem().toString().equals("0.275l"))) {
                    siideriGrammat.increaseByZeroPointSeven();
                    kalorit.increaseKaloritSiideri1();
                    kplSiideri.increaseKpl();
                    annokset.AnnosMietoKaksi();
                }
                if ((siideriSpinner1.getSelectedItem().toString().equals("4.7%")) &&
                        (siideriSpinner2.getSelectedItem().toString().equals("0.5l"))) {
                    siideriGrammat.increaseByOneAndHalf();
                    kalorit.increaseKaloritSiideri3();
                    kplSiideri.increaseKpl();
                    annokset.AnnosMietoViisi();
                }
                if ((siideriSpinner1.getSelectedItem().toString().equals("5.5%")) &&
                        (siideriSpinner2.getSelectedItem().toString().equals("0.33l"))) {
                    siideriGrammat.increaseByOneAndHalf();
                    kalorit.increaseKaloritSiideri2();
                    kplSiideri.increaseKpl();
                    annokset.AnnosMietoNelja();
                }
                if ((siideriSpinner1.getSelectedItem().toString().equals("5.5%")) &&
                        (siideriSpinner2.getSelectedItem().toString().equals("0.5l"))) {
                    siideriGrammat.increaseByTwo();
                    kalorit.increaseKaloritSiideri3();
                    kplSiideri.increaseKpl();
                    annokset.AnnosMietoKuusi();
                }
                if ((siideriSpinner1.getSelectedItem().toString().equals("5.5%")) &&
                        (siideriSpinner2.getSelectedItem().toString().equals("0.275l"))) {
                    siideriGrammat.increaseByOnePointThree();
                    kalorit.increaseKaloritSiideri1();
                    kplSiideri.increaseKpl();
                    annokset.AnnosMietoKolme();
                }
                //hankki Spinnerissä valittu itemmi
                String spinner3 = siideriSpinner3.getSelectedItem().toString();
                //Vaihtaa kirjaimet että pystyn muokkka sen doubleksi
                String loppu = spinner3.replace("€", " ");
                //lisää siideriEuroLasku
                siideriEuroLasku += Double.parseDouble(loppu);
                //hankki Litra oleva spinner
                String spinner2 = siideriSpinner2.getSelectedItem().toString();
                //Vaihtaa kirjaimet että pystyn muokkka sen doubleksi
                String loppuTulos = spinner2.replace("l", " ");
                //lisää oluLitraLasku
                siideriLitraLasku += Double.parseDouble(loppuTulos);
                //vetää nämä methodit
                promillelaskuri();
                kalorilaskuri();
                juomienKpl();
                annostenMaara();
                hamppariMuunnos();
                alkoholinHaihtuminen();
            }
        });

        //Lonkero button
        Button lonkeroButton = (Button) getView().findViewById(R.id.lonkeroListaButton);
        lonkeroButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if ((lonkeroSpinner1.getSelectedItem().toString().equals("4.7%")) &&
                        (lonkeroSpinner2.getSelectedItem().toString().equals("0.33l"))) {
                    lonkeroGrammat.increaseByOne();
                    kalorit.increaseKaloritLonkero1();
                    kplLonkero.increaseKpl();
                    annokset.AnnosMietoKolme();
                }
                if ((lonkeroSpinner1.getSelectedItem().toString().equals("4.7%")) &&
                        (lonkeroSpinner2.getSelectedItem().toString().equals("0.5l"))) {
                    lonkeroGrammat.increaseByOneAndHalf();
                    kalorit.increaseKaloritLonkero2();
                    kplLonkero.increaseKpl();
                    annokset.AnnosMietoViisi();
                }
                if ((lonkeroSpinner1.getSelectedItem().toString().equals("5.5%")) &&
                        (lonkeroSpinner2.getSelectedItem().toString().equals("0.33l"))) {
                    lonkeroGrammat.increaseByOnePointThree();
                    kalorit.increaseKaloritLonkero3();
                    kplLonkero.increaseKpl();
                    annokset.AnnosMietoNelja();
                }
                if ((lonkeroSpinner1.getSelectedItem().toString().equals("5.5%")) &&
                        (lonkeroSpinner2.getSelectedItem().toString().equals("0.5l"))) {
                    lonkeroGrammat.increaseByTwo();
                    kalorit.increaseKaloritLonkero4();
                    kplLonkero.increaseKpl();
                    annokset.AnnosMietoKuusi();
                }
                //hankki Spinnerissä valittu itemmi
                String spinner3 = lonkeroSpinner3.getSelectedItem().toString();
                //Vaihtaa kirjaimet että pystyn muokkka sen doubleksi
                String loppu = spinner3.replace("€", " ");
                //lisää lonkeroEuroLasku
                lonkeroEuroLasku += Double.parseDouble(loppu);
                //hankki Litra oleva spinner
                String spinner2 = lonkeroSpinner2.getSelectedItem().toString();
                //Vaihtaa kirjaimet että pystyn muokkka sen doubleksi
                String loppuTulos = spinner2.replace("l", " ");
                //lisää oluLitraLasku
                lonkeroLitraLasku += Double.parseDouble(loppuTulos);
                //vetää nämä methodit
                promillelaskuri();
                kalorilaskuri();
                juomienKpl();
                annostenMaara();
                hamppariMuunnos();
                alkoholinHaihtuminen();
            }
        });

        //Viina button
        Button viinaButton = (Button) getView().findViewById(R.id.viinaListaButton);
        viinaButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if ((viinaSpinner1.getSelectedItem().toString().equals("40%")) &&
                        (viinaSpinner2.getSelectedItem().toString().equals("0.04l"))) {
                    viinaGrammat.increaseByOne();
                    kalorit.increaseKaloritViina1();
                    kplViina.increaseKpl();
                    annokset.AnnosMietoKolme();
                }
                if ((viinaSpinner1.getSelectedItem().toString().equals("40%")) &&
                        (viinaSpinner2.getSelectedItem().toString().equals("0.35l"))) {
                    viinaGrammat.increaseViinaMelkeinPuoliLitraa();
                    kalorit.increaseKaloritViina2();
                    kplViina.increaseKpl();
                    annokset.AnnosViinaNelja();
                }
                if ((viinaSpinner1.getSelectedItem().toString().equals("40%")) &&
                        (viinaSpinner2.getSelectedItem().toString().equals("0.5l"))) {
                    viinaGrammat.increaseViinaPuoliLitraa();
                    kalorit.increaseKaloritViina3();
                    kplViina.increaseKpl();
                    annokset.AnnosViinaKolme();
                }
                if ((viinaSpinner1.getSelectedItem().toString().equals("40%")) &&
                        (viinaSpinner2.getSelectedItem().toString().equals("0.7l"))) {
                    viinaGrammat.increaseViinaMelkeinLitra();
                    kalorit.increaseKaloritViski2();
                    kplViina.increaseKpl();
                    annokset.AnnosViinaKaksi();
                }
                if ((viinaSpinner1.getSelectedItem().toString().equals("40%")) &&
                        (viinaSpinner2.getSelectedItem().toString().equals("1.0l"))) {
                    viinaGrammat.increaseViinaLitra();
                    kalorit.increaseKaloritViina4();
                    kplViina.increaseKpl();
                    annokset.AnnosViinaYksi();
                }
                //hankki Spinnerissä valittu itemmi
                String spinner3 = viinaSpinner3.getSelectedItem().toString();
                //Vaihtaa kirjaimet että pystyn muokkka sen doubleksi
                String loppu = spinner3.replace("€", " ");
                //lissä viinaEuroLasku
                viinaEuroLasku += Double.parseDouble(loppu);
                //hankki Litra oleva spinner
                String spinner2 = viinaSpinner2.getSelectedItem().toString();
                //Vaihtaa kirjaimet että pystyn muokkka sen doubleksi
                String loppuTulos = spinner2.replace("l", " ");
                //lisää oluLitraLasku
                viinaLitraLasku += Double.parseDouble(loppuTulos);
                //vetää nämä methodit
                promillelaskuri();
                kalorilaskuri();
                juomienKpl();
                annostenMaara();
                hamppariMuunnos();
                alkoholinHaihtuminen();
            }
        });

        //Likööri button
        Button likooriButton = (Button) getView().findViewById(R.id.likooriListaButton);
        likooriButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if ((likooriSpinner1.getSelectedItem().toString().equals("17%")) &&
                        (likooriSpinner2.getSelectedItem().toString().equals("0.5l"))) {
                    likooriGrammat.increaseLikoori1PuoliLitraa();
                    kalorit.increaseKaloritLikoori2();
                    kplLikoori.increaseKpl();
                    annokset.AnnosLikooriYksi();
                }
                if ((likooriSpinner1.getSelectedItem().toString().equals("17%")) &&
                        (likooriSpinner2.getSelectedItem().toString().equals("0.7l"))) {
                    likooriGrammat.increaseLikoori1YliPuoliLitraa();
                    kalorit.increaseKaloritLikoori3();
                    kplLikoori.increaseKpl();
                    annokset.AnnosLikooriKaksi();
                }
                if ((likooriSpinner1.getSelectedItem().toString().equals("17%")) &&
                        (likooriSpinner2.getSelectedItem().toString().equals("0.04l"))) {
                    likooriGrammat.increaseByZeroPointHalf();
                    kalorit.increaseKaloritViina1();
                    kplLikoori.increaseKpl();
                    annokset.AnnosMietoYksi();
                }
                if ((likooriSpinner1.getSelectedItem().toString().equals("21%")) &&
                        (likooriSpinner2.getSelectedItem().toString().equals("0.5l"))) {
                    likooriGrammat.increaseLikoori2PuoliLitraa();
                    kalorit.increaseKaloritLikoori2();
                    kplLikoori.increaseKpl();
                    annokset.AnnosLikooriKolme();
                }
                if ((likooriSpinner1.getSelectedItem().toString().equals("21%")) &&
                        (likooriSpinner2.getSelectedItem().toString().equals("0.7l"))) {
                    likooriGrammat.increaseLikoori2YliPuoliLitraa();
                    kalorit.increaseKaloritLikoori3();
                    kplLikoori.increaseKpl();
                    annokset.AnnosLikooriNelja();
                }
                if ((likooriSpinner1.getSelectedItem().toString().equals("21%")) &&
                        (likooriSpinner2.getSelectedItem().toString().equals("0.04l"))) {
                    likooriGrammat.increaseByZeroPointSeven();
                    kalorit.increaseKaloritViina1();
                    kplLikoori.increaseKpl();
                    annokset.AnnosMietoKaksi();
                }
                if ((likooriSpinner1.getSelectedItem().toString().equals("30%")) &&
                        (likooriSpinner2.getSelectedItem().toString().equals("0.7l"))) {
                    likooriGrammat.increaseLikoori3YliPuoliLitraa();
                    kalorit.increaseKaloritLikoori3();
                    kplLikoori.increaseKpl();
                    annokset.AnnosLikooriKuusi();
                }
                if ((likooriSpinner1.getSelectedItem().toString().equals("30%")) &&
                        (likooriSpinner2.getSelectedItem().toString().equals("0.5l"))) {
                    likooriGrammat.increaseLikoori3PuoliLitraa();
                    kalorit.increaseKaloritLikoori2();
                    kplLikoori.increaseKpl();
                    annokset.AnnosLikooriViisi();
                }
                if ((likooriSpinner1.getSelectedItem().toString().equals("30%")) &&
                        (likooriSpinner2.getSelectedItem().toString().equals("0.04l"))) {
                    likooriGrammat.increaseByOne();
                    kalorit.increaseKaloritViina1();
                    kplLikoori.increaseKpl();
                    annokset.AnnosMietoKolme();
                }
                //hankki Spinnerissä valittu itemmi
                String spinner3 = likooriSpinner3.getSelectedItem().toString();
                //Vaihtaa kirjaimet että pystyn muokkka sen doubleksi
                String loppu = spinner3.replace("€", " ");
                //lisää likooriEuroLaskuun
                likooriEuroLasku += Double.parseDouble(loppu);
                //hankki Litra oleva spinner
                String spinner2 = likooriSpinner2.getSelectedItem().toString();
                //Vaihtaa kirjaimet että pystyn muokkka sen doubleksi
                String loppuTulos = spinner2.replace("l", " ");
                //lisää oluLitraLasku
                likooriLitraLasku += Double.parseDouble(loppuTulos);
                //vetää nämä methodit
                promillelaskuri();
                kalorilaskuri();
                juomienKpl();
                annostenMaara();
                hamppariMuunnos();
                alkoholinHaihtuminen();
            }
        });

        //Viini button
        Button viiniButton = (Button) getView().findViewById(R.id.viiniListaButton);
        viiniButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if ((viiniSpinner1.getSelectedItem().toString().equals("8-15%")) &&
                        (viiniSpinner2.getSelectedItem().toString().equals("0.12l"))) {
                    viiniGrammat.increaseByOne();
                    kalorit.increaseKaloritViini1();
                    kplViini.increaseKpl();
                    annokset.AnnosMietoKolme();
                }
                if ((viiniSpinner1.getSelectedItem().toString().equals("8-15%")) &&
                        (viiniSpinner2.getSelectedItem().toString().equals("0.75l"))) {
                    viiniGrammat.increaseLikoori2YliPuoliLitraa();
                    kalorit.increaseKaloritViini2();
                    kplViini.increaseKpl();
                    annokset.AnnosLikooriKolme();
                }
                //hankki Spinnerissä valittu itemmi
                String spinner3 = viiniSpinner3.getSelectedItem().toString();
                //Vaihtaa kirjaimet että pystyn muokkka sen doubleksi
                String loppu = spinner3.replace("€", " ");
                //lisää viiniEuroLasku
                viiniEuroLasku += Double.parseDouble(loppu);
                //hankki Litra oleva spinner
                String spinner2 = viiniSpinner2.getSelectedItem().toString();
                //Vaihtaa kirjaimet että pystyn muokkka sen doubleksi
                String loppuTulos = spinner2.replace("l", " ");
                //lisää oluLitraLasku
                viiniLitraLasku += Double.parseDouble(loppuTulos);
                //vetää nämä methodit
                promillelaskuri();
                kalorilaskuri();
                juomienKpl();
                annostenMaara();
                hamppariMuunnos();
                alkoholinHaihtuminen();
            }
        });

        //Viski button
        Button viskiButton = (Button) getView().findViewById(R.id.viskiListaButton);
        viskiButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if ((viskiSpinner1.getSelectedItem().toString().equals("40%")) &&
                        (viskiSpinner2.getSelectedItem().toString().equals("0.04l"))) {
                    viskiGrammat.increaseByOne();
                    kalorit.increaseKaloritViina1();
                    kplViski.increaseKpl();
                    annokset.AnnosMietoKolme();
                }
                if ((viskiSpinner1.getSelectedItem().toString().equals("40%")) &&
                        (viskiSpinner2.getSelectedItem().toString().equals("0.2l"))) {
                    viskiGrammat.increaseViski1();
                    kalorit.increaseKaloritViski1();
                    kplViski.increaseKpl();
                    annokset.AnnosViskiYksi();
                }
                if ((viskiSpinner1.getSelectedItem().toString().equals("40%")) &&
                        (viskiSpinner2.getSelectedItem().toString().equals("0.7l"))) {
                    viskiGrammat.increaseViski2();
                    kalorit.increaseKaloritViski2();
                    kplViski.increaseKpl();
                    annokset.AnnosViskiKaksi();
                }
                //hankki Spinnerissä valittu itemmi
                String spinner3 = viskiSpinner3.getSelectedItem().toString();
                //Vaihtaa kirjaimet että pystyn muokkka sen doubleksi
                String loppu = spinner3.replace("€", " ");
                //lisää viskiEuroLasku
                viskiEuroLasku += Double.parseDouble(loppu);
                //hankki Litra oleva spinner
                String spinner2 = viskiSpinner2.getSelectedItem().toString();
                //Vaihtaa kirjaimet että pystyn muokkka sen doubleksi
                String loppuTulos = spinner2.replace("l", " ");
                //lisää oluLitraLasku
                viskiLitraLasku += Double.parseDouble(loppuTulos);
                //vetää nämä methodit
                promillelaskuri();
                kalorilaskuri();
                juomienKpl();
                annostenMaara();
                hamppariMuunnos();
                alkoholinHaihtuminen();
            }
        });
        //LASKURIEN RESET BUTTON
        Button resetButton = (Button) getView().findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Tässä methodissa nollataan listattujen counterien, muuttujien ja methodien arvot
             * @param v kun näkymää on napsautettu.
             */
            public void onClick(View v) {
                //resetoi
                oluenGrammat.reset();
                siideriGrammat.reset();
                lonkeroGrammat.reset();
                viinaGrammat.reset();
                likooriGrammat.reset();
                viiniGrammat.reset();
                viskiGrammat.reset();
                //pistää nollaksi
                olutEuroLasku = 0;
                lonkeroEuroLasku = 0;
                viinaEuroLasku = 0;
                likooriEuroLasku = 0;
                viiniEuroLasku = 0;
                viskiEuroLasku = 0;
                siideriEuroLasku = 0;
                prominMaara = 0;
                olutLitraLasku = 0;
                siideriLitraLasku = 0;
                lonkeroLitraLasku = 0;
                viinaLitraLasku = 0;
                likooriLitraLasku = 0;
                viiniLitraLasku = 0;
                viskiLitraLasku = 0;
                //resetoi
                kplOlut.reset();
                kplSiideri.reset();
                kplLonkero.reset();
                kplViina.reset();
                kplLikoori.reset();
                kplViini.reset();
                kplViski.reset();
                kalorit.reset();
                //päivittä textview
                promillelaskuri();
                kalorilaskuri();
                juomienKpl();
                //resetoi
                annokset.reset();
                //päivittä  textview
                annostenMaara();
                hamppariMuunnos();
                alkoholinHaihtuminen();
            }
        });

        Button saveButton = (Button) getView().findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Tässä methodissa talentaa kaikki sharedpreferencin listattujen counterien, muuttujien ja methodien arvot
             * @param v kun näkymää on napsautettu.
             */
            public void onClick(View v) {

                saveFuntion();
            }
        });
    }

    /**
     * mitä spinnerissä on valittu
     *
     * @param parent   AdapterView, jossa valinta tapahtui
     * @param view     Napsautettu näkymä AdapterView-näkymässä
     * @param position Missä valittu on tapahtunut
     * @param id       Valitun kohteen rivitunnus
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    /**
     * hankkii textViewin annostenmäärä laskuriin ja muokkaa ulkomuodon halutuksi
     */
    public void annostenMaara() {
        TextView tvAnnos = getView().findViewById(R.id.tvAnnosMaara);
        String popo = new DecimalFormat("##.##").format(annokset.getDouble());
        String run = popo.replace(",", ".");
        tvAnnos.setText(run);
    }

    /**
     * hankkii textViewit ja juomien sen hetkiset arvot juomienmäärä laskureihin
     */
    public void juomienKpl() {
        TextView tv3 = getView().findViewById(R.id.tvAnnosOlut);
        TextView tv4 = getView().findViewById(R.id.tvAnnosSiideri);
        TextView tv5 = getView().findViewById(R.id.tvAnnosLonkero);
        TextView tv6 = getView().findViewById(R.id.tvAnnosViina);
        TextView tv7 = getView().findViewById(R.id.tvAnnosLikoori);
        TextView tv8 = getView().findViewById(R.id.tvAnnosViini);
        TextView tv9 = getView().findViewById(R.id.tvAnnosViski);
        tv3.setText(Integer.toString(kplOlut.getValue()));
        tv4.setText(Integer.toString(kplSiideri.getValue()));
        tv5.setText(Integer.toString(kplLonkero.getValue()));
        tv6.setText(Integer.toString(kplViina.getValue()));
        tv7.setText(Integer.toString(kplLikoori.getValue()));
        tv8.setText(Integer.toString(kplViini.getValue()));
        tv9.setText(Integer.toString(kplViski.getValue()));

    }

    /**
     * hankkii textViewin ja asettaa arvon Kalorilaskuriin
     */
    public void kalorilaskuri() {
        TextView tv2 = getView().findViewById(R.id.tvKalorit);
        tv2.setText(Integer.toString(kalorit.getValue()));
    }

    /**
     * hankkii painon sharedpreferenssistä ja muuntaa sen.
     * tehdään laskutoimitus alkoholin haihtumiseen
     * syötetään tulos textViewiin
     */
    public void alkoholinHaihtuminen() {
        SharedPreferences prefGet = getActivity().getSharedPreferences("Date_value", Activity.MODE_PRIVATE);

        int paino = prefGet.getInt("Weight", 0);
        int haihtuuTunnissa = paino / 10;
        int tuntiaTulos = alkoholiGrammoina / haihtuuTunnissa;
        TextView tvHaihtuminen = getView().findViewById(R.id.tvTunnit);
        tvHaihtuminen.setText(Integer.toString(tuntiaTulos));
    }

    /**
     * saadaan kalorien määrä juustoina jakamalla yhden juustohampurilaisen kalorimäärä kalorilaskurin kokonais määrästä
     * joka kerta kun kalorilaskuri on jaollinen 301 lisätään hampurilais-textViewin arvoon +1.
     */
    public void hamppariMuunnos() {
        kaloritMuunnos = kalorit.getValue();
        int juustoMaara = kaloritMuunnos / juusto;
        TextView tvJuusto = getView().findViewById(R.id.tvHampurilaiset);
        tvJuusto.setText(Integer.toString(juustoMaara));
    }

    /**
     * @param parent AdapterView, jossa valinta tapahtui
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * talentaa kalenteri fragment varten kun hakee annos, kalorit ja promille
     */
    private void calendarSaver() {
        //hakee kansi nimeltään calendar_valuer
        Saving saving = new Saving(getActivity(), "Calendar_valuer");


        //hakee sharedpreference(SP) kansion: calendar_valuer
        String promin = saving.getStr("Promille " + stringDateMonthYear);
        int kalori = saving.getInt("Kalori " + stringDateMonthYear);
        String anonksi = saving.getStr("Annoksia " + stringDateMonthYear);

        //muutta pilkku pisteksi
        String miksi3 = anonksi.replace(",", ".");
        //muutta string doubleksi
        double annoksetSavingista = Double.parseDouble(miksi3);
        //koko annos että pystyn lisää kokonaisen annokseen
        double annoksetInWhole = annokset.getDouble();
        annoksetInWhole += annoksetSavingista;
        //muutta pilkku pisteksi
        String miksi4 = promin.replace(",", ".");
        //koko annos että pystyn lisää kokonaisen annokseen
        double promina = Double.parseDouble(miksi4);
        prominMaara += promina;
        //koko annos että pystyn lisää kokonaisen annokseen
        int kaloria = kalorit.getValue();
        kaloria += kalori;
        //Asetta sen tolle double muotossa
        String test = new DecimalFormat("##.##").format(prominMaara);
        String tesaa = new DecimalFormat("##.##").format(annoksetInWhole);

        //tallentaa SP syötetyn nimen ja String
        saving.setStr("Annoksia " + stringDateMonthYear, tesaa);
        saving.setStr("Promille " + stringDateMonthYear, test);
        saving.setInt("Kalori " + stringDateMonthYear, kaloria);

        //lasku että pystyn järjestää viikko numerot ja tallentaa
        counter = saving.getInt("Counter ");
        counter++;
        saving.setInt("Counter ", counter);

        //talenta vuoden
        Saving year = new Saving(getActivity(), "Year");

        year.setStr(vuosia, vuosia);

        //done
    }

    /**
     * talentta viikot
     */
    private void weekSaver() {
        //hakee kansi nimeltään calendar_valuer
        Saving saving = new Saving(getActivity(), "Calendar_valuer");
        //hakee counter
        int countera = saving.getInt("Counter ");
        //tekee tai hakee viikko kansio sharedpreference
        Saving viikkoAsettaja = new Saving(getActivity(), "Viikko");
        //Asetta avain sana viiko vuosi ja value counter vuosia ja viikko
        viikkoAsettaja.setStr(Week + vuosia, countera + " " + vuosia + " " + Week);

    }

    /**
     *talentaa kuukaudet
     */
    private void KuukausiSaver() {

        //hakee kansi nimeltään calendar_valuer
        Saving saving = new Saving(getActivity(), "Calendar_valuer");
        //hakee counter
        int countera = saving.getInt("Counter ");
        //tekee tai hakee kuukausi kansio sharedpreference
        Saving months = new Saving(getActivity(), "Months");
        //heittä tämän vuoden laskin ja kuukausi numerona että pystyn järjestää
        MonthsCounter setter = new MonthsCounter(vuosia, Integer.toString(countera), stringDat);
        //asetta sen  avain sana vuosia + " kuukausi"  ja value counter + " " + vuosia + " kuukausi"
        months.setStr(setter.Another(), setter.set());

    }

    /**
     * talentaa olut sharedpreference(SP)
     */
    private void Olut() {


        //OLUT  Euro
        //hakee sharedpreference(SP) kansion: viikkoJuomat
        Saving prefWeek = new Saving(getActivity(), "viikkoJuomat");
        //hakee sharedpreference(SP) oluteuro
        String olutWeek = prefWeek.getStr("oluteuro" + Week + vuosia);
        //asetta saman kun olutEurolasku
        olutEuroLaskuWeek = olutEuroLasku;
        // muokka sen pilkku pistestä
        String olutEuroReplace = olutWeek.replace(",", ".");
        // lisää double olutEuroLaskuWeek
        olutEuroLaskuWeek += Double.parseDouble(olutEuroReplace);
        //muokkasen 21.12121->21.21
        String decimalStyle = new DecimalFormat("##.##").format(olutEuroLaskuWeek);
        //asetta sen sharedpreference(SP)
        prefWeek.setStr("oluteuro" + Week + vuosia, decimalStyle);

        //Hakee olutlitra viiko
        String olutL = prefWeek.getStr("olutlitra" + Week + vuosia);
        //asetta saman kun olutLitralasku
        olutLitraLaskuWeek = olutLitraLasku;
        //muokkasen 21.12121->21.21
        String style = olutL.replace(",", ".");
        // lisää double olutLitraLaskuWeek
        olutLitraLaskuWeek += Double.parseDouble(style);
        //muokkasen 21.12121->21.21
        decimalStyle = new DecimalFormat("##.##").format(olutLitraLaskuWeek);
        //asetta sen sharedpreference(SP)
        prefWeek.setStr("olutlitra" + Week + vuosia, decimalStyle);

        //hakee sharedpreference(SP) kansion: oluEuro
        Saving pref = new Saving(getActivity(), "olutEuro" + vuosia);
        //hakee sharedpreference(SP) oluteuro
        String olut = pref.getStr("oluteuro" + euString);
        //muokkasen 21.12121->21.21
        String decimalStyle2 = olut.replace(",", ".");
        //muokkasen string ja lisää olutEurolasku
        double olutEuro = Double.parseDouble(decimalStyle2);
        olutEuroLasku += olutEuro;

        //muokkaa decimaalin formatti
        String eu = new DecimalFormat("##.##").format(olutEuroLasku);

        //talentaa Shredpreference
        pref.setStr("oluteuro" + euString, eu);
        //Olut Litra
        //hakee sharedpreference(SP) kansion: olutLitra
        Saving setLitra = new Saving(getActivity(), "olutLitra" + vuosia);
        //hakee sharedpreference tiedot että pystyn lisää
        String litra = setLitra.getStr("olutlitra" + euString);
        //muokkasen 21.12121->21.21
        String litraFormatti = litra.replace(",", ".");
        //muokkasen string ja lisää olutLitraLasku
        double olutLitra = Double.parseDouble(litraFormatti);
        olutLitraLasku += olutLitra;
        //muokkasen 21.12121->21.21
        String olutLitrao = new DecimalFormat("##.##").format(olutLitraLasku);
        //talentaa Shredpreference
        setLitra.setStr("olutlitra" + euString, olutLitrao);

    }

    /**
     * Tallentta viinit sharedpreference(SP)
     */
    private void megaViini() {
        //VIINI

        //Viikko
        // hakee sharedpreference(SP) kansion: viiko juomat
        Saving prefWeek = new Saving(getActivity(), "viikkoJuomat");
        //hakee sharedpreference(SP) viinieuro
        String viiniWeek = prefWeek.getStr("viinieuro" + Week + vuosia);
        // muokka sen pilkku pistestä
        String miksiViini = viiniWeek.replace(",", ".");
        //asetta saman kun viiniEurolasku
        viiniEuroLaskuWeek = viiniEuroLasku;
        // lisää double viiniEuroLaskuWeek
        viiniEuroLaskuWeek += Double.parseDouble(miksiViini);
        //muokkasen 21.12121->21.21
        String decimalStyle = new DecimalFormat("##.##").format(viiniEuroLaskuWeek);
        //asetta sen
        prefWeek.setStr("viinieuro" + Week + vuosia, decimalStyle);
        //hakee viinilitra
        String viiniLitra = prefWeek.getStr("viinilitra" + Week + vuosia);
        //muokkasen 21.12121->21.21
        String viiniLitaPiste = viiniLitra.replace(",", ".");
        //asetta saman kun viiniLitralasku
        viiniLitraLaskuWeek = viiniLitraLasku;
        //lisää viiniLitralaskuweek
        viiniLitraLaskuWeek += Double.parseDouble(viiniLitaPiste);
        //muokkasen 21.12121->21.21
        String viniDecimal = new DecimalFormat("##.##").format(viiniLitraLaskuWeek);
        //asetta sen sharedpreference(SP)
        prefWeek.setStr("viinilitra" + Week + vuosia, viniDecimal);

        //Kuukausi
        //Hakee viiniLitra viiko
        Saving pref = new Saving(getActivity(), "viiniEuro" + vuosia);
        //hakee viinieuro
        String viini = pref.getStr("viinieuro" + euString);
        //muokka pilkusta pisettesen
        String viiniViini = viini.replace(",", ".");
        //asetta saman kun viiniViini
        double viiniEuro = Double.parseDouble(viiniViini);
        //lisää viiniEuro viiniEurolaskuun
        viiniEuroLasku += viiniEuro;
        //muokkasen 21.12121->21.21
        String eu = new DecimalFormat("##.##").format(viiniEuroLasku);
        //talentaa Shredpreference
        pref.setStr("viinieuro" + euString, eu);

        //hakee sharedpreference(SP) kansion: olutLitra
        Saving settiLitra = new Saving(getActivity(), "viiniLitra" + vuosia);
        //hakee sharedpreference tiedot että pystyn lisää
        String litra = settiLitra.getStr("viinilitra" + euString);
        //muokkasen 21.12121->21.21
        String muokattuLitra = litra.replace(",", ".");

        //muokkasen double
        double viiniLitroi = Double.parseDouble(muokattuLitra);
        //lisää viiniLitraLasku
        viiniLitraLasku += viiniLitroi;
        //asettaa SP editointi valmiuteen

        //muokkaa decimaalin formatti
        String viiniLitrao = new DecimalFormat("##.##").format(viiniLitraLasku);
        //talentaa Shredpreference
        settiLitra.setStr("viinilitra" + euString, viiniLitrao);


    }

    /**
     *Tallentaa siideri sharedpreference(SP)
     */
    private void megaSiideri() {
        //siideri

        //hakee sharedpreference(SP) kansion: oluEuro
        //Viikko

        Saving prefWeek = new Saving(getActivity(), "viikkoJuomat");

        String siideriWeek = prefWeek.getStr("siiderieuro" + Week + vuosia);

        String miksiSiideriWeek = siideriWeek.replace(",", ".");

        siideriEuroLaskuWeek = siideriEuroLasku;

        siideriEuroLaskuWeek += Double.parseDouble(miksiSiideriWeek);

        String decimalStyle = new DecimalFormat("##.##").format(siideriEuroLaskuWeek);

        prefWeek.setStr("siiderieuro" + Week + vuosia, decimalStyle);


        String ol = prefWeek.getStr("siiderilitra" + Week + vuosia);
        String miksiOlOl = ol.replace(",", ".");
        siideriLitraLaskuWeek = siideriLitraLasku;
        siideriLitraLaskuWeek += Double.parseDouble(miksiOlOl);
        String testing = new DecimalFormat("##.##").format(siideriLitraLaskuWeek);
        prefWeek.setStr("siiderilitra" + Week + vuosia, testing);

        //Kuukausi
        Saving pref = new Saving(getActivity(), "siideriEuro" + vuosia);
        String siideri = pref.getStr("siiderieuro" + euString);
        String miksiSiideriSiideri = siideri.replace(",", ".");
        double siideriEuro = Double.parseDouble(miksiSiideriSiideri);
        siideriEuroLasku += siideriEuro;
        //muokkaa decimaalin formatti
        String eu = new DecimalFormat("##.##").format(siideriEuroLasku);
        //talentaa Shredpreference
        pref.setStr("siiderieuro" + euString, eu);

        //hakee sharedpreference(SP) kansion: olutLitra
        Saving setLitra = new Saving(getActivity(), "siideriLitra" + vuosia);
        //hakee sharedpreference tiedot että pystyn lisää
        String litra = setLitra.getStr("siiderilitra" + euString);
        String miksiSiideriLitra = litra.replace(",", ".");

        //muokkasen double
        double siideriLitra = Double.parseDouble(miksiSiideriLitra);

        siideriLitraLasku += siideriLitra;
        //asettaa SP editointi valmiuteen

        //muokkaa decimaalin formatti
        String siideriLitrao = new DecimalFormat("##.##").format(siideriLitraLasku);
        //talentaa Shredpreference
        setLitra.setStr("siiderilitra" + euString, siideriLitrao);

    }

    /**
     * Tallentaa lonkero sharedpreference(SP)
     */
    private void megaLonkero() {


        //Viikko
        //hakee sharedpreference(SP) kansion: viikkoJuomat
        Saving prefWeek = new Saving(getActivity(), "viikkoJuomat");
        //hakee sharedpreference(SP) lonkeroeuro
        String lonkeroWeek = prefWeek.getStr("lonkeroeuro" + Week + vuosia);
        // muokka sen pilkku pistestä
        String lonkeroPiste = lonkeroWeek.replace(",", ".");
        //asetta saman kun lonkeroEurolasku
        lonkeroEuroLaskuWeek = lonkeroEuroLasku;
        // lisää double lonkeroEuroLaskuWeek
        lonkeroEuroLaskuWeek += Double.parseDouble(lonkeroPiste);
        //muokkasen 21.12121->21.21
        String decimalStyle = new DecimalFormat("##.##").format(lonkeroEuroLaskuWeek);
        //asetta sen sharedpreference(SP)
        prefWeek.setStr("lonkeroeuro" + Week + vuosia, decimalStyle);

        //Hakee lonkeroLitra viikko
        String ol = prefWeek.getStr("lonkerolitra" + Week + vuosia);
        // muokka sen pilkku pistestä
        String lonkeroFormant = ol.replace(",", ".");
        //asetta saman kun lonkeroLitralasku
        lonkeroLitraLaskuWeek = lonkeroLitraLasku;
        // lisää double lonkeroLitraLaskuWeek
        lonkeroLitraLaskuWeek += Double.parseDouble(lonkeroFormant);
        //muokkasen 21.12121->21.21
        decimalStyle = new DecimalFormat("##.##").format(lonkeroLitraLaskuWeek);
        //asetta sen sharedpreference(SP)
        prefWeek.setStr("lonkerolitra" + Week + vuosia, decimalStyle);

        //Kuukausi
        Saving pref = new Saving(getActivity(), "lonkeroEuro" + vuosia);
        //Hakee lonkeroEuro
        String lonkero = pref.getStr("lonkeroeuro" + euString);
        // muokka sen pilkku pistestä
        String lonkerino = lonkero.replace(",", ".");

        //muokkasen string ja lisää lonkeroEurolasku
        double lonkeroEuro = Double.parseDouble(lonkerino);
        lonkeroEuroLasku += lonkeroEuro;

        //muokkaa decimaalin formatti
        String eu = new DecimalFormat("##.##").format(lonkeroEuroLasku);
        //talentaa Shredpreference
        pref.setStr("lonkeroeuro" + euString, eu);

        //hakee sharedpreference(SP) kansion: lonkeroLitra
        Saving setLitra = new Saving(getActivity(), "lonkeroLitra" + vuosia);
        //hakee sharedpreference tiedot että pystyn lisää
        String litra = setLitra.getStr("lonkerolitra" + euString);
        // muokka sen pilkku pistestä
        String miksiLitraLitraLitra = litra.replace(",", ".");

        //muokkasen double
        double lonkeroLitra = Double.parseDouble(miksiLitraLitraLitra);
        lonkeroLitraLasku += lonkeroLitra;


        //muokkaa decimaalin formatti
        String lonkeroLitrao = new DecimalFormat("##.##").format(lonkeroLitraLasku);
        //talentaa Shredpreference
        setLitra.setStr("lonkerolitra" + euString, lonkeroLitrao);

    }

    /**
     * Tallentaa liikori sharedpreference(SP)
     */
    private void megaLiikori() {


        //Viikko
        //hakee sharedpreference(SP) kansion: viikkoJuomat
        Saving prefWeek = new Saving(getActivity(), "viikkoJuomat");
        //hakee sharedpreference(SP) likoorieuro
        String likooriWeek = prefWeek.getStr("likoorieuro" + Week + vuosia);
        // muokka sen pilkku pistestä
        String likooriWeekPiste = likooriWeek.replace(",", ".");
        //asetta saman kun liikoriEurolasku
        likooriEuroLaskuWeek = likooriEuroLasku;
        // lisää double likooriEuroLaskuWeek
        likooriEuroLaskuWeek += Double.parseDouble(likooriWeekPiste);
        //muokkasen 21.12121->21.21
        String decimalStyle = new DecimalFormat("##.##").format(likooriEuroLaskuWeek);
        //asetta sen sharedpreference(SP)
        prefWeek.setStr("likoorieuro" + Week + vuosia, decimalStyle);
        //hakee sharedpreference(SP) likoorilitra
        String ol = prefWeek.getStr("likoorilitra" + Week + vuosia);
        // muokka sen pilkku pistestä
        String liikoriPiste = ol.replace(",", ".");
        //asetta saman kun likooriLitraLaskuWeek
        likooriLitraLaskuWeek = likooriLitraLasku;
        //lisää double likootiLitraLaskuWeek
        likooriLitraLaskuWeek += Double.parseDouble(liikoriPiste);
        //muokkasen 21.12121->21.21
        String testing = new DecimalFormat("##.##").format(likooriLitraLaskuWeek);
        //asetta sen sharedpreference(SP)
        prefWeek.setStr("likoorilitra" + Week + vuosia, testing);

        //Kuukausi
        //hakee sharedpreference(SP) kansion: likooriEuro
        Saving pref = new Saving(getActivity(), "likooriEuro" + vuosia);
        //hakee sharedpreference(SP) likoorieuro
        String likoori = pref.getStr("likoorieuro" + euString);
        // muokka sen pilkku pistestä
        String miksiLikooriLikoori = likoori.replace(",", ".");

        //muokkasen string ja lisää likooriEuro
        double likooriEuro = Double.parseDouble(miksiLikooriLikoori);
        likooriEuroLasku += likooriEuro;

        //muokkasen 21.12121->21.21
        String eu = new DecimalFormat("##.##").format(likooriEuroLasku);

        //talentaa Shredpreference
        pref.setStr("likoorieuro" + euString, eu);

        //hakee sharedpreference(SP) kansion: olutLitra
        Saving setLitra = new Saving(getActivity(), "likooriLitra" + vuosia);

        //hakee sharedpreference tiedot että pystyn lisää
        String litra = setLitra.getStr("likoorilitra" + euString);
        // muokka sen pilkku pistestä
        String miksiLikooriLitra = litra.replace(",", ".");
        //muokkasen double ja lisää sen
        double likooriLitra = Double.parseDouble(miksiLikooriLitra);
        likooriLitraLasku += likooriLitra;

        //muokkasen 21.12121->21.21
        String likooriLitrao = new DecimalFormat("##.##").format(likooriLitraLasku);
        //talentaa Shredpreference
        setLitra.setStr("likoorilitra" + euString, likooriLitrao);
    }

    /**
     * Tallentaa viski sharedpreference(SP)
     */
    private void megaViiski() {

        //Viikko
        // hakee sharedpreference(SP) kansion: oluEuro
        Saving prefWeek = new Saving(getActivity(), "viikkoJuomat");
        //hakee sharedpreference(SP) viskieuro
        String viskiWeek = prefWeek.getStr("viskieuro" + Week + vuosia);
        // muokka sen pilkku pistestä
        String miksiViskiWeek = viskiWeek.replace(",", ".");
        //asetta saman kun viskiEuroLasku
        viskiEuroLaskuWeek = viskiEuroLasku;
        // lisää double viskiEuroLaskuWeek
        viskiEuroLaskuWeek += Double.parseDouble(miksiViskiWeek);
        //muokkasen 21.12121->21.21
        String decimalStyle = new DecimalFormat("##.##").format(viskiEuroLaskuWeek);
        //asetta sen sharedpreference(SP)
        prefWeek.setStr("viskieuro" + Week + vuosia, decimalStyle);

        //hakee sharedpreference(SP) viskilitra
        String ol = prefWeek.getStr("viskilitra" + Week + vuosia);
        // muokka sen pilkku pistestä
        String viskiMan = ol.replace(",", ".");
        //asetta saman kun viskiLitraLasku
        viskiLitraLaskuWeek = viskiLitraLasku;
        // lisää double viskiLitraLaskuWeek
        viskiLitraLaskuWeek += Double.parseDouble(viskiMan);
        //muokkasen 21.12121->21.21
        String testing = new DecimalFormat("##.##").format(viskiLitraLaskuWeek);
        //asetta sen sharedpreference(SP)
        prefWeek.setStr("viskilitra" + Week + vuosia, testing);

        //Kuukausi
        //hakee sharedpreference(SP) kansion: viskiEuro
        Saving pref = new Saving(getActivity(), "viskiEuro" + vuosia);
        //hakee sharedpreference(SP) viskieuro
        String viski = pref.getStr("viskieuro" + euString);
        // muokka sen pilkku pistestä
        String miksiViskiViski = viski.replace(",", ".");
        //muokkasen double ja lisää sen
        double viskiEuro = Double.parseDouble(miksiViskiViski);
        viskiEuroLasku += viskiEuro;
        //muokkaa decimaalin formatti
        String eu = new DecimalFormat("##.##").format(viskiEuroLasku);
        //talentaa Shredpreference
        pref.setStr("viskieuro" + euString, eu);

        //hakee sharedpreference(SP) kansion: olutLitra
        Saving setLitra = new Saving(getActivity(), "viskiLitra" + vuosia);
        //hakee sharedpreference tiedot että pystyn lisää
        String litra = setLitra.getStr("viskilitra" + euString);
        // muokka sen pilkku pistestä
        String miksiLitraLitraLitra = litra.replace(",", ".");
        //muokkasen double ja lisää sen
        double viskiLitra = Double.parseDouble(miksiLitraLitraLitra);
        viskiLitraLasku += viskiLitra;


        //muokkaa decimaalin formatti
        String viskiLitrao = new DecimalFormat("##.##").format(viskiLitraLasku);
        //talentaa Shredpreference
        setLitra.setStr("viskilitra" + euString, viskiLitrao);
    }

    /**
     * Tallentaa viini sharedpreference(SP)
     */
    private void megaViina() {
        //hakee sharedpreference(SP) kansion: oluEuro

        //Viikko
        Saving prefWeek = new Saving(getActivity(), "viikkoJuomat");
        String viinaWeek = prefWeek.getStr("viinaeuro" + Week + vuosia);
        String miksiViinaWeek = viinaWeek.replace(",", ".");
        viinaEuroLaskuWeek = viinaEuroLasku;
        viinaEuroLaskuWeek += Double.parseDouble(miksiViinaWeek);
        String decimalStyle = new DecimalFormat("##.##").format(viinaEuroLaskuWeek);
        prefWeek.setStr("viinaeuro" + Week + vuosia, decimalStyle);


        String ol = prefWeek.getStr("viinalitra" + Week + vuosia);
        String miksiViinaOl = ol.replace(",", ".");
        viinaLitraLaskuWeek = viinaLitraLasku;
        viinaLitraLaskuWeek += Double.parseDouble(miksiViinaOl);
        String testing = new DecimalFormat("##.##").format(viinaLitraLaskuWeek);
        prefWeek.setStr("viinalitra" + Week + vuosia, testing);

        //Kuukausi
        Saving pref = new Saving(getActivity(), "viinaEuro" + vuosia);
        String viina = pref.getStr("viinaeuro" + euString);
        String miksiViinaViina = viina.replace(",", ".");
        double viinaEuro = Double.parseDouble(miksiViinaViina);
        viinaEuroLasku += viinaEuro;
        //muokkaa decimaalin formatti
        String eu = new DecimalFormat("##.##").format(viinaEuroLasku);
        //talentaa Shredpreference
        pref.setStr("viinaeuro" + euString, eu);

        //hakee sharedpreference(SP) kansion: olutLitra
        Saving setLitra = new Saving(getActivity(), "viinaLitra" + vuosia);
        //hakee sharedpreference tiedot että pystyn lisää
        String litra = setLitra.getStr("viinalitra" + euString);
        String miksiViinaLitra = litra.replace(",", ".");

        //muokkasen double
        double viinaLitra = Double.parseDouble(miksiViinaLitra);

        viinaLitraLasku += viinaLitra;
        //asettaa SP editointi valmiuteen

        //muokkaa decimaalin formatti
        String viinaLitrao = new DecimalFormat("##.##").format(viinaLitraLasku);
        //talentaa Shredpreference
        setLitra.setStr("viinalitra" + euString, viinaLitrao);
    }

    /**
     * tallentaa kun painaa save
     *
     * @saveFuntion()
     */
    private void saveFuntion() {

        calendarSaver();
        weekSaver();
        KuukausiSaver();
        Olut();
        megaLiikori();
        megaLonkero();
        megaSiideri();
        megaViina();
        megaViini();
        megaViiski();
        oluenGrammat.reset();
        siideriGrammat.reset();
        lonkeroGrammat.reset();
        viinaGrammat.reset();
        likooriGrammat.reset();
        viiniGrammat.reset();
        viskiGrammat.reset();
        kplOlut.reset();
        kplSiideri.reset();
        kplLonkero.reset();
        kplViina.reset();
        kplLikoori.reset();
        kplViini.reset();
        kplViski.reset();
        kalorit.reset();
        olutEuroLasku = 0;
        lonkeroEuroLasku = 0;
        viinaEuroLasku = 0;
        likooriEuroLasku = 0;
        viiniEuroLasku = 0;
        viskiEuroLasku = 0;
        siideriEuroLasku = 0;
        prominMaara = 0;
        olutLitraLasku = 0;
        siideriLitraLasku = 0;
        lonkeroLitraLasku = 0;
        viinaLitraLasku = 0;
        likooriLitraLasku = 0;
        viiniLitraLasku = 0;
        viskiLitraLasku = 0;
        promillelaskuri();
        kalorilaskuri();
        juomienKpl();
        annokset.reset();
        annostenMaara();
        hamppariMuunnos();
        alkoholinHaihtuminen();

    }

    /**
     * promillelaskurissa lasketaan promillien määrä: laskemalla alkoholigramma-counterien arvot yhteen, hakemalla käyttäjän syöttämä paino ja sukupuoli sharedPreferencistä, syöttämällä muuttujat laskuun.
     * Tulos muuttuja sisältää laskusta saadun promillemäärän
     * Tulos päivitetään textViewiin
     */
    public void promillelaskuri() {
        SharedPreferences prefGet = getActivity().getSharedPreferences("Date_value", Activity.MODE_PRIVATE);

        alkoholiGrammoina = siideriGrammat.getValue() + oluenGrammat.getValue() + lonkeroGrammat.getValue() + viinaGrammat.getValue() + likooriGrammat.getValue() + viiniGrammat.getValue() + viskiGrammat.getValue(); // yhdistää juomien grammalaskurien arvot yhteen
        int paino = prefGet.getInt("Weight", 0); // hakee painon sharedprefensistä
        double gender = Double.parseDouble(prefGet.getString("gender", "0")); // hakee sukupuolikertoimen SPstä
        double tulos = alkoholiGrammoina / (paino * gender); // (alkoholin määrä veressä tällä hetkellä)lasku, josta tehdään uusi muuttuja (tulos)
        prominMaara = tulos;

        TextView tv1 = getView().findViewById(R.id.tvPromille);
        String tulosi = new DecimalFormat("##.##").format(tulos);
        String miksiTulosi = tulosi.replace(",", ".");
        tv1.setText(miksiTulosi); //Double.toString tai new DecimalFormat("##.##").format


    }
}