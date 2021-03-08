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

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

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
            public void onClick(View v) {
                // tässä lisää valitut spinnerlaatikot ja tekee promillelaskuri() methodin


                //jos spinnerlaatikoiden valinnat vastaavat seuraavia, lisää oluenGramman määrää 1(13g)
                if ((olutSpinner1.getSelectedItem().toString().equals("4,7%")) &&
                        (olutSpinner2.getSelectedItem().toString().equals("0,33l"))) {
                    oluenGrammat.increaseByOne();
                    kalorit.increaseKaloritOlut1();
                    kplOlut.increaseKpl();
                    annokset.AnnosMietoKolme();
                }
                if ((olutSpinner1.getSelectedItem().toString().equals("4,7%")) &&
                        (olutSpinner2.getSelectedItem().toString().equals("0,5l"))) {
                    oluenGrammat.increaseByOneAndHalf(); // 1.5
                    kalorit.increaseKaloritOlut2();
                    kplOlut.increaseKpl();
                    annokset.AnnosMietoViisi();
                }
                if ((olutSpinner1.getSelectedItem().toString().equals("5,5%")) &&
                        (olutSpinner2.getSelectedItem().toString().equals("0,33l"))) {
                    oluenGrammat.increaseByOneAndHalf(); // 1.5
                    kalorit.increaseKaloritOlut3();
                    kplOlut.increaseKpl();
                    annokset.AnnosMietoNelja();
                }
                if ((olutSpinner1.getSelectedItem().toString().equals("5,5%")) &&
                        (olutSpinner2.getSelectedItem().toString().equals("0,5l"))) {
                    oluenGrammat.increaseByTwo(); // 2
                    kalorit.increaseKaloritOlut4();
                    kplOlut.increaseKpl();
                    annokset.AnnosMietoKuusi();
                }
                //hankki Spinnerissä valittu itemmi
                String eru = olutSpinner3.getSelectedItem().toString();
                //Vaihtaa kirjaimet että pystyn muokkka sen doubleksi
                String vaihtaja = eru.replace(",", ".");
                String loppu = vaihtaja.replace("€", " ");
                //lisää olutEuroLaskuu
                olutEuroLasku += Double.parseDouble(loppu);

                //hankki Litra oleva spinner
                String erua = olutSpinner2.getSelectedItem().toString();
                //Vaihtaa kirjaimet että pystyn muokkka sen doubleksi
                String vaihtajaa = erua.replace(",", ".");
                String loppuTulos = vaihtajaa.replace("l", " ");
                //lisää oluLitraLasku
                olutLitraLasku += Double.parseDouble(loppuTulos);
                //vedää promillelaskuri
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
                if ((siideriSpinner1.getSelectedItem().toString().equals("4,7%")) &&
                        (siideriSpinner2.getSelectedItem().toString().equals("0,33l"))) {
                    siideriGrammat.increaseByOne();
                    kalorit.increaseKaloritSiideri2();
                    kplSiideri.increaseKpl();
                    annokset.AnnosMietoKolme();
                }
                if ((siideriSpinner1.getSelectedItem().toString().equals("4,7%")) &&
                        (siideriSpinner2.getSelectedItem().toString().equals("0,275l"))) {
                    siideriGrammat.increaseByZeroPointSeven();
                    kalorit.increaseKaloritSiideri1();
                    kplSiideri.increaseKpl();
                    annokset.AnnosMietoKaksi();
                }
                if ((siideriSpinner1.getSelectedItem().toString().equals("4,7%")) &&
                        (siideriSpinner2.getSelectedItem().toString().equals("0,5l"))) {
                    siideriGrammat.increaseByOneAndHalf();
                    kalorit.increaseKaloritSiideri3();
                    kplSiideri.increaseKpl();
                    annokset.AnnosMietoViisi();
                }
                if ((siideriSpinner1.getSelectedItem().toString().equals("5,5%")) &&
                        (siideriSpinner2.getSelectedItem().toString().equals("0,33l"))) {
                    siideriGrammat.increaseByOneAndHalf();
                    kalorit.increaseKaloritSiideri2();
                    kplSiideri.increaseKpl();
                    annokset.AnnosMietoNelja();
                }
                if ((siideriSpinner1.getSelectedItem().toString().equals("5,5%")) &&
                        (siideriSpinner2.getSelectedItem().toString().equals("0,5l"))) {
                    siideriGrammat.increaseByTwo();
                    kalorit.increaseKaloritSiideri3();
                    kplSiideri.increaseKpl();
                    annokset.AnnosMietoKuusi();
                }
                if ((siideriSpinner1.getSelectedItem().toString().equals("5,5%")) &&
                        (siideriSpinner2.getSelectedItem().toString().equals("0,275l"))) {
                    siideriGrammat.increaseByOnePointThree();
                    kalorit.increaseKaloritSiideri1();
                    kplSiideri.increaseKpl();
                    annokset.AnnosMietoKolme();
                }
                //hankki Spinnerissä valittu itemmi
                String eru = siideriSpinner3.getSelectedItem().toString();
                //Vaihtaa kirjaimet että pystyn muokkka sen doubleksi
                String vaihtaja = eru.replace(",", ".");
                String loppu = vaihtaja.replace("€", " ");
                //lisää siideriEuroLasku
                siideriEuroLasku += Double.parseDouble(loppu);
                //hankki Litra oleva spinner
                String erua = siideriSpinner2.getSelectedItem().toString();
                //Vaihtaa kirjaimet että pystyn muokkka sen doubleksi
                String vaihtajaa = erua.replace(",", ".");
                String loppuTulos = vaihtajaa.replace("l", " ");
                //lisää oluLitraLasku
                siideriLitraLasku += Double.parseDouble(loppuTulos);
                //vedää promillelaskuri
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
                if ((lonkeroSpinner1.getSelectedItem().toString().equals("4,7%")) &&
                        (lonkeroSpinner2.getSelectedItem().toString().equals("0,33l"))) {
                    lonkeroGrammat.increaseByOne();
                    kalorit.increaseKaloritLonkero1();
                    kplLonkero.increaseKpl();
                    annokset.AnnosMietoKolme();
                }
                if ((lonkeroSpinner1.getSelectedItem().toString().equals("4,7%")) &&
                        (lonkeroSpinner2.getSelectedItem().toString().equals("0,5l"))) {
                    lonkeroGrammat.increaseByOneAndHalf(); // 1.5
                    kalorit.increaseKaloritLonkero2();
                    kplLonkero.increaseKpl();
                    annokset.AnnosMietoViisi();
                }
                if ((lonkeroSpinner1.getSelectedItem().toString().equals("5,5%")) &&
                        (lonkeroSpinner2.getSelectedItem().toString().equals("0,33l"))) {
                    lonkeroGrammat.increaseByOnePointThree(); // 1.3
                    kalorit.increaseKaloritLonkero3();
                    kplLonkero.increaseKpl();
                    annokset.AnnosMietoNelja();
                }
                if ((lonkeroSpinner1.getSelectedItem().toString().equals("5,5%")) &&
                        (lonkeroSpinner2.getSelectedItem().toString().equals("0,5l"))) {
                    lonkeroGrammat.increaseByTwo(); // // 2
                    kalorit.increaseKaloritLonkero4();
                    kplLonkero.increaseKpl();
                    annokset.AnnosMietoKuusi();
                }
                //hankki Spinnerissä valittu itemmi
                String eru = lonkeroSpinner3.getSelectedItem().toString();
                //Vaihtaa kirjaimet että pystyn muokkka sen doubleksi
                String vaihtaja = eru.replace(",", ".");
                String loppu = vaihtaja.replace("€", " ");
                //lisää lonkeroEuroLasku
                lonkeroEuroLasku += Double.parseDouble(loppu);
                //hankki Litra oleva spinner
                String erua = lonkeroSpinner2.getSelectedItem().toString();
                //Vaihtaa kirjaimet että pystyn muokkka sen doubleksi
                String vaihtajaa = erua.replace(",", ".");
                String loppuTulos = vaihtajaa.replace("l", " ");
                //lisää oluLitraLasku
                lonkeroLitraLasku += Double.parseDouble(loppuTulos);
                //vetää promillelaskuri
                promillelaskuri();
                //vetää kalorilaskuri
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
                        (viinaSpinner2.getSelectedItem().toString().equals("0,04l"))) {
                    viinaGrammat.increaseByOne();
                    kalorit.increaseKaloritViina1();
                    kplViina.increaseKpl();
                    annokset.AnnosMietoKolme();
                }
                if ((viinaSpinner1.getSelectedItem().toString().equals("40%")) &&
                        (viinaSpinner2.getSelectedItem().toString().equals("0,35l"))) {
                    viinaGrammat.increaseViinaMelkeinPuoliLitraa();
                    kalorit.increaseKaloritViina2();
                    kplViina.increaseKpl();
                    annokset.AnnosViinaNelja();
                }
                if ((viinaSpinner1.getSelectedItem().toString().equals("40%")) &&
                        (viinaSpinner2.getSelectedItem().toString().equals("0,5l"))) {
                    viinaGrammat.increaseViinaPuoliLitraa();
                    kalorit.increaseKaloritViina3();
                    kplViina.increaseKpl();
                    annokset.AnnosViinaKolme();
                }
                if ((viinaSpinner1.getSelectedItem().toString().equals("40%")) &&
                        (viinaSpinner2.getSelectedItem().toString().equals("0,7l"))) {
                    viinaGrammat.increaseViinaMelkeinLitra();
                    kalorit.increaseKaloritViski2();
                    kplViina.increaseKpl();
                    annokset.AnnosViinaKaksi();
                }
                if ((viinaSpinner1.getSelectedItem().toString().equals("40%")) &&
                        (viinaSpinner2.getSelectedItem().toString().equals("1,0l"))) {
                    viinaGrammat.increaseViinaLitra();
                    kalorit.increaseKaloritViina4();
                    kplViina.increaseKpl();
                    annokset.AnnosViinaYksi();
                }
                //hankki Spinnerissä valittu itemmi
                String eru = viinaSpinner3.getSelectedItem().toString();
                //Vaihtaa kirjaimet että pystyn muokkka sen doubleksi
                String vaihtaja = eru.replace(",", ".");
                String loppu = vaihtaja.replace("€", " ");
                viinaEuroLasku += Double.parseDouble(loppu);
                //hankki Litra oleva spinner
                String erua = viinaSpinner2.getSelectedItem().toString();
                //Vaihtaa kirjaimet että pystyn muokkka sen doubleksi
                String vaihtajaa = erua.replace(",", ".");
                String loppuTulos = vaihtajaa.replace("l", " ");
                //lisää oluLitraLasku
                viinaLitraLasku += Double.parseDouble(loppuTulos);
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
                        (likooriSpinner2.getSelectedItem().toString().equals("0,5l"))) {
                    likooriGrammat.increaseLikoori1PuoliLitraa();//
                    kalorit.increaseKaloritLikoori2();
                    kplLikoori.increaseKpl();
                    annokset.AnnosLikooriYksi();
                }
                if ((likooriSpinner1.getSelectedItem().toString().equals("17%")) &&
                        (likooriSpinner2.getSelectedItem().toString().equals("0,7l"))) {
                    likooriGrammat.increaseLikoori1YliPuoliLitraa();//
                    kalorit.increaseKaloritLikoori3();
                    kplLikoori.increaseKpl();
                    annokset.AnnosLikooriKaksi();
                }
                if ((likooriSpinner1.getSelectedItem().toString().equals("17%")) &&
                        (likooriSpinner2.getSelectedItem().toString().equals("0,04l"))) {
                    likooriGrammat.increaseByZeroPointHalf(); //
                    kalorit.increaseKaloritViina1();
                    kplLikoori.increaseKpl();
                    annokset.AnnosMietoYksi();
                }
                if ((likooriSpinner1.getSelectedItem().toString().equals("21%")) &&
                        (likooriSpinner2.getSelectedItem().toString().equals("0,5l"))) {
                    likooriGrammat.increaseLikoori2PuoliLitraa(); //
                    kalorit.increaseKaloritLikoori2();
                    kplLikoori.increaseKpl();
                    annokset.AnnosLikooriKolme();
                }
                if ((likooriSpinner1.getSelectedItem().toString().equals("21%")) &&
                        (likooriSpinner2.getSelectedItem().toString().equals("0,7l"))) {
                    likooriGrammat.increaseLikoori2YliPuoliLitraa(); //
                    kalorit.increaseKaloritLikoori3();
                    kplLikoori.increaseKpl();
                    annokset.AnnosLikooriNelja();
                }
                if ((likooriSpinner1.getSelectedItem().toString().equals("21%")) &&
                        (likooriSpinner2.getSelectedItem().toString().equals("0,04l"))) {
                    likooriGrammat.increaseByZeroPointSeven(); //
                    kalorit.increaseKaloritViina1();
                    kplLikoori.increaseKpl();
                    annokset.AnnosMietoKaksi();
                }
                if ((likooriSpinner1.getSelectedItem().toString().equals("30%")) &&
                        (likooriSpinner2.getSelectedItem().toString().equals("0,7l"))) {
                    likooriGrammat.increaseLikoori3YliPuoliLitraa();
                    kalorit.increaseKaloritLikoori3();
                    kplLikoori.increaseKpl();
                    annokset.AnnosLikooriKuusi();
                }
                if ((likooriSpinner1.getSelectedItem().toString().equals("30%")) &&
                        (likooriSpinner2.getSelectedItem().toString().equals("0,5l"))) {
                    likooriGrammat.increaseLikoori3PuoliLitraa();
                    kalorit.increaseKaloritLikoori2();
                    kplLikoori.increaseKpl();
                    annokset.AnnosLikooriViisi();
                }
                if ((likooriSpinner1.getSelectedItem().toString().equals("30%")) &&
                        (likooriSpinner2.getSelectedItem().toString().equals("0,04l"))) {
                    likooriGrammat.increaseByOne(); //
                    kalorit.increaseKaloritViina1();
                    kplLikoori.increaseKpl();
                    annokset.AnnosMietoKolme();
                }
                //hankki Spinnerissä valittu itemmi
                String eru = likooriSpinner3.getSelectedItem().toString();
                //Vaihtaa kirjaimet että pystyn muokkka sen doubleksi
                String vaihtaja = eru.replace(",", ".");
                String loppu = vaihtaja.replace("€", " ");
                likooriEuroLasku += Double.parseDouble(loppu);
                //hankki Litra oleva spinner
                String erua = likooriSpinner2.getSelectedItem().toString();
                //Vaihtaa kirjaimet että pystyn muokkka sen doubleksi
                String vaihtajaa = erua.replace(",", ".");
                String loppuTulos = vaihtajaa.replace("l", " ");
                //lisää oluLitraLasku
                likooriLitraLasku += Double.parseDouble(loppuTulos);
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
                        (viiniSpinner2.getSelectedItem().toString().equals("0,12l"))) {
                    viiniGrammat.increaseByOne();
                    kalorit.increaseKaloritViini1();
                    kplViini.increaseKpl();
                    annokset.AnnosMietoKolme();
                }
                if ((viiniSpinner1.getSelectedItem().toString().equals("8-15%")) &&
                        (viiniSpinner2.getSelectedItem().toString().equals("0,75l"))) {
                    viiniGrammat.increaseLikoori2YliPuoliLitraa();
                    kalorit.increaseKaloritViini2();
                    kplViini.increaseKpl();
                    annokset.AnnosLikooriKolme();
                }
                //hankki Spinnerissä valittu itemmi
                String eru = viiniSpinner3.getSelectedItem().toString();
                //Vaihtaa kirjaimet että pystyn muokkka sen doubleksi
                String vaihtaja = eru.replace(",", ".");
                String loppu = vaihtaja.replace("€", " ");
                viiniEuroLasku += Double.parseDouble(loppu);
                //hankki Litra oleva spinner
                String erua = viiniSpinner2.getSelectedItem().toString();
                //Vaihtaa kirjaimet että pystyn muokkka sen doubleksi
                String vaihtajaa = erua.replace(",", ".");
                String loppuTulos = vaihtajaa.replace("l", " ");
                //lisää oluLitraLasku
                viiniLitraLasku += Double.parseDouble(loppuTulos);
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
                        (viskiSpinner2.getSelectedItem().toString().equals("0,04l"))) {
                    viskiGrammat.increaseByOne();
                    kalorit.increaseKaloritViina1();
                    kplViski.increaseKpl();
                    annokset.AnnosMietoKolme();
                }
                if ((viskiSpinner1.getSelectedItem().toString().equals("40%")) &&
                        (viskiSpinner2.getSelectedItem().toString().equals("0,2l"))) {
                    viskiGrammat.increaseViski1();
                    kalorit.increaseKaloritViski1();
                    kplViski.increaseKpl();
                    annokset.AnnosViskiYksi();
                }
                if ((viskiSpinner1.getSelectedItem().toString().equals("40%")) &&
                        (viskiSpinner2.getSelectedItem().toString().equals("0,7l"))) {
                    viskiGrammat.increaseViski2();
                    kalorit.increaseKaloritViski2();
                    kplViski.increaseKpl();
                    annokset.AnnosViskiKaksi();
                }
                //hankki Spinnerissä valittu itemmi
                String eru = viskiSpinner3.getSelectedItem().toString();
                //Vaihtaa kirjaimet että pystyn muokkka sen doubleksi
                String vaihtaja = eru.replace(",", ".");
                String loppu = vaihtaja.replace("€", " ");
                viskiEuroLasku += Double.parseDouble(loppu);
                //hankki Litra oleva spinner
                String erua = viskiSpinner2.getSelectedItem().toString();
                //Vaihtaa kirjaimet että pystyn muokkka sen doubleksi
                String vaihtajaa = erua.replace(",", ".");
                String loppuTulos = vaihtajaa.replace("l", " ");
                //lisää oluLitraLasku
                viskiLitraLasku += Double.parseDouble(loppuTulos);
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
            public void onClick(View v) {
                oluenGrammat.reset();
                siideriGrammat.reset();
                lonkeroGrammat.reset();
                viinaGrammat.reset();
                likooriGrammat.reset();
                viiniGrammat.reset();
                viskiGrammat.reset();
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
                kplOlut.reset();
                kplSiideri.reset();
                kplLonkero.reset();
                kplViina.reset();
                kplLikoori.reset();
                kplViini.reset();
                kplViski.reset();
                kalorit.reset();
                promillelaskuri();
                kalorilaskuri();
                juomienKpl();
                annokset.reset();
                annostenMaara();
                hamppariMuunnos();
                alkoholinHaihtuminen();
            }
        });

        Button saveButton = (Button) getView().findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                saveFuntion();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    public void annostenMaara() {
        TextView tvAnnos = getView().findViewById(R.id.tvAnnosMaara);

        tvAnnos.setText(new DecimalFormat("##.##").format(annokset.getDouble()));
    }

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
        //Double.toString tai new DecimalFormat("##.##").format
    }

    public void kalorilaskuri() {
        TextView tv2 = getView().findViewById(R.id.tvKalorit);
        tv2.setText(Integer.toString(kalorit.getValue())); //Double.toString tai new DecimalFormat("##.##").format
    }

    public void alkoholinHaihtuminen() {
        SharedPreferences prefGet = getActivity().getSharedPreferences("Date_value", Activity.MODE_PRIVATE);

        int paino = prefGet.getInt("Weight", 0);
        int haihtuuTunnissa = paino / 10;
        int tuntiaTulos = alkoholiGrammoina / haihtuuTunnissa;
        TextView tvHaihtuminen = getView().findViewById(R.id.tvTunnit);
        tvHaihtuminen.setText(Integer.toString(tuntiaTulos));
    }

    public void hamppariMuunnos() {
        kaloritMuunnos = kalorit.getValue();
        int juustoMaara = kaloritMuunnos / juusto;
        TextView tvJuusto = getView().findViewById(R.id.tvHampurilaiset);
        tvJuusto.setText(Integer.toString(juustoMaara));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void calendarSaver() {
        DataProccessor dataProccessor = new DataProccessor(getActivity(), "Calendar_valuer");
        //Formattoi minun kalenteri sillä malillta mitä haluan

        //hakee sharedpreference(SP) kansion: data_value
        String promin = dataProccessor.getStr("Promille " + stringDateMonthYear);
        int kalori = dataProccessor.getInt("Kalori " + stringDateMonthYear);
        String anonksi = dataProccessor.getStr("Annoksia " + stringDateMonthYear);
        double annoona = Double.parseDouble(anonksi);
        double endanno = annokset.getDouble();
        endanno += annoona;
        double promina = Double.parseDouble(promin);
        prominMaara += promina;
        int kaloria = kalorit.getValue();
        kaloria += kalori;
        //Asetta sen tolle double muotossa
        String test = new DecimalFormat("##.##").format(prominMaara);
        String tesaa = new DecimalFormat("##.##").format(endanno);

        //tallentaa SP syötetyn nimen ja String
        dataProccessor.setStr("Annoksia " + stringDateMonthYear, tesaa);
        dataProccessor.setStr("Promille " + stringDateMonthYear, test);
        dataProccessor.setInt("Kalori " + stringDateMonthYear, kaloria);

        counter = dataProccessor.getInt("Counter ");
        counter++;
        dataProccessor.setInt("Counter ", counter);


        DataProccessor year = new DataProccessor(getActivity(), "Year");

        year.setStr(vuosia, vuosia);

        //done
    }

    private void weekSaver() {
        DataProccessor dataProccessor = new DataProccessor(getActivity(), "Calendar_valuer");
        int countera = dataProccessor.getInt("Counter ");
        DataProccessor viikkoAsettaja = new DataProccessor(getActivity(), "Viikko");
        viikkoAsettaja.setStr(Week + vuosia, countera + " " + vuosia + " " + Week);
        //done
    }

    private void KuukausiSaver() {


        DataProccessor dataProccessor = new DataProccessor(getActivity(), "Calendar_valuer");
        int countera = dataProccessor.getInt("Counter ");
        DataProccessor months = new DataProccessor(getActivity(), "Months");

        MonthsCounter setter = new MonthsCounter(vuosia, Integer.toString(countera), stringDat);
        months.setStr(setter.Another(), setter.set());
        //done
    }


    private void Olut() {


        //OLUT  Euro
        //hakee sharedpreference(SP) kansion: oluEuro
        DataProccessor prefWeek = new DataProccessor(getActivity(), "viikkoJuomat");
        String olutWeek = prefWeek.getStr("oluteuro" + Week + vuosia);
        olutEuroLaskuWeek = olutEuroLasku;
        olutEuroLaskuWeek += Double.parseDouble(olutWeek);
        String euviikko = new DecimalFormat("##.##").format(olutEuroLaskuWeek);
        prefWeek.setStr("oluteuro" + Week + vuosia, euviikko);


        String ol = prefWeek.getStr("olutlitra" + Week + vuosia);

        olutLitraLaskuWeek = olutLitraLasku;

        olutLitraLaskuWeek += Double.parseDouble(ol);

        euviikko = new DecimalFormat("##.##").format(olutLitraLaskuWeek);

        prefWeek.setStr("olutlitra" + Week + vuosia, euviikko);


        DataProccessor pref = new DataProccessor(getActivity(), "olutEuro" + vuosia);
        String olut = pref.getStr("oluteuro" + euString);
        double olutEuro = Double.parseDouble(olut);
        olutEuroLasku += olutEuro;
        //muokkaa decimaalin formatti
        String eu = new DecimalFormat("##.##").format(olutEuroLasku);
        //talentaa Shredpreference
        pref.setStr("oluteuro" + euString, eu);
        //Olut Litra
        //hakee sharedpreference(SP) kansion: olutLitra

        DataProccessor setLitra = new DataProccessor(getActivity(), "olutLitra" + vuosia);
        //hakee sharedpreference tiedot että pystyn lisää
        String litra = setLitra.getStr("olutlitra" + euString);
        //muokkasen double
        double olutLitra = Double.parseDouble(litra);
        olutLitraLasku += olutLitra;
        //asettaa SP editointi valmiuteen
        //muokkaa decimaalin formatti
        String olutLitrao = new DecimalFormat("##.##").format(olutLitraLasku);
        //talentaa Shredpreference
        setLitra.setStr("olutlitra" + euString, olutLitrao);

//done
    }

    private void megaViini() {
        //VIINI

        //hakee sharedpreference(SP) kansion: oluEuro
        //Viikko
        DataProccessor prefWeek = new DataProccessor(getActivity(), "viikkoJuomat");
        String viiniWeek = prefWeek.getStr("viinieuro" + Week + vuosia);
        viiniEuroLaskuWeek = viiniEuroLasku;
        viiniEuroLaskuWeek += Double.parseDouble(viiniWeek);
        String euviikko = new DecimalFormat("##.##").format(viiniEuroLaskuWeek);
        prefWeek.setStr("viinieuro" + Week + vuosia, euviikko);


        String ol = prefWeek.getStr("viinilitra" + Week + vuosia);
        viiniLitraLaskuWeek = viiniLitraLasku;
        viiniLitraLaskuWeek += Double.parseDouble(ol);
        String testing = new DecimalFormat("##.##").format(viiniLitraLaskuWeek);
        prefWeek.setStr("viinilitra" + Week + vuosia, testing);

        //Kuukausi
        DataProccessor pref = new DataProccessor(getActivity(), "viiniEuro" + vuosia);
        String viini = pref.getStr("viinieuro" + euString);
        double viiniEuro = Double.parseDouble(viini);
        viiniEuroLasku += viiniEuro;
        //muokkaa decimaalin formatti
        String eu = new DecimalFormat("##.##").format(viiniEuroLasku);
        //talentaa Shredpreference
        pref.setStr("viinieuro" + euString, eu);

        //hakee sharedpreference(SP) kansion: olutLitra
        DataProccessor setLitra = new DataProccessor(getActivity(), "viiniLitra" + vuosia);
        //hakee sharedpreference tiedot että pystyn lisää
        String litra = setLitra.getStr("viinilitra" + euString);


        //muokkasen double
        double viiniLitra = Double.parseDouble(litra);

        viiniLitraLasku += viiniLitra;
        //asettaa SP editointi valmiuteen

        //muokkaa decimaalin formatti
        String viiniLitrao = new DecimalFormat("##.##").format(viiniLitraLasku);
        //talentaa Shredpreference
        setLitra.setStr("viinilitra" + euString, viiniLitrao);


    }

    private void megaSiideri() {
        //siideri

        //hakee sharedpreference(SP) kansion: oluEuro
        //Viikko
        DataProccessor prefWeek = new DataProccessor(getActivity(), "viikkoJuomat");
        String siideriWeek = prefWeek.getStr("siiderieuro" + Week + vuosia);
        siideriEuroLaskuWeek = siideriEuroLasku;
        siideriEuroLaskuWeek += Double.parseDouble(siideriWeek);
        String euviikko = new DecimalFormat("##.##").format(siideriEuroLaskuWeek);
        prefWeek.setStr("siiderieuro" + Week + vuosia, euviikko);


        String ol = prefWeek.getStr("siiderilitra" + Week + vuosia);
        siideriLitraLaskuWeek = siideriLitraLasku;
        siideriLitraLaskuWeek += Double.parseDouble(ol);
        String testing = new DecimalFormat("##.##").format(siideriLitraLaskuWeek);
        prefWeek.setStr("siiderilitra" + Week + vuosia, testing);

        //Kuukausi
        DataProccessor pref = new DataProccessor(getActivity(), "siideriEuro" + vuosia);
        String siideri = pref.getStr("siiderieuro" + euString);
        double siideriEuro = Double.parseDouble(siideri);
        siideriEuroLasku += siideriEuro;
        //muokkaa decimaalin formatti
        String eu = new DecimalFormat("##.##").format(siideriEuroLasku);
        //talentaa Shredpreference
        pref.setStr("siiderieuro" + euString, eu);

        //hakee sharedpreference(SP) kansion: olutLitra
        DataProccessor setLitra = new DataProccessor(getActivity(), "siideriLitra" + vuosia);
        //hakee sharedpreference tiedot että pystyn lisää
        String litra = setLitra.getStr("siiderilitra" + euString);


        //muokkasen double
        double siideriLitra = Double.parseDouble(litra);

        siideriLitraLasku += siideriLitra;
        //asettaa SP editointi valmiuteen

        //muokkaa decimaalin formatti
        String siideriLitrao = new DecimalFormat("##.##").format(siideriLitraLasku);
        //talentaa Shredpreference
        setLitra.setStr("siiderilitra" + euString, siideriLitrao);

    }

    private void megaLonkero() {

        //hakee sharedpreference(SP) kansion: oluEuro
        //Viikko
        DataProccessor prefWeek = new DataProccessor(getActivity(), "viikkoJuomat");
        String lonkeroWeek = prefWeek.getStr("lonkeroeuro" + Week + vuosia);
        lonkeroEuroLaskuWeek = lonkeroEuroLasku;
        lonkeroEuroLaskuWeek += Double.parseDouble(lonkeroWeek);
        String euviikko = new DecimalFormat("##.##").format(lonkeroEuroLaskuWeek);
        prefWeek.setStr("lonkeroeuro" + Week + vuosia, euviikko);


        String ol = prefWeek.getStr("lonkerolitra" + Week + vuosia);
        lonkeroLitraLaskuWeek = lonkeroLitraLasku;
        lonkeroLitraLaskuWeek += Double.parseDouble(ol);
        euviikko = new DecimalFormat("##.##").format(lonkeroLitraLaskuWeek);
        prefWeek.setStr("lonkerolitra" + Week + vuosia, euviikko);

        //Kuukausi
        DataProccessor pref = new DataProccessor(getActivity(), "lonkeroEuro" + vuosia);
        String lonkero = pref.getStr("lonkeroeuro" + euString);
        double lonkeroEuro = Double.parseDouble(lonkero);
        lonkeroEuroLasku += lonkeroEuro;
        //muokkaa decimaalin formatti
        String eu = new DecimalFormat("##.##").format(lonkeroEuroLasku);
        //talentaa Shredpreference
        pref.setStr("lonkeroeuro" + euString, eu);

        //hakee sharedpreference(SP) kansion: olutLitra
        DataProccessor setLitra = new DataProccessor(getActivity(), "lonkeroLitra" + vuosia);
        //hakee sharedpreference tiedot että pystyn lisää
        String litra = setLitra.getStr("lonkerolitra" + euString);


        //muokkasen double
        double lonkeroLitra = Double.parseDouble(litra);

        lonkeroLitraLasku += lonkeroLitra;
        //asettaa SP editointi valmiuteen

        //muokkaa decimaalin formatti
        String lonkeroLitrao = new DecimalFormat("##.##").format(lonkeroLitraLasku);
        //talentaa Shredpreference
        setLitra.setStr("lonkerolitra" + euString, lonkeroLitrao);

    }

    private void megaLiikori() {

        //hakee sharedpreference(SP) kansion: oluEuro
        //Viikko
        DataProccessor prefWeek = new DataProccessor(getActivity(), "viikkoJuomat");
        String likooriWeek = prefWeek.getStr("likoorieuro" + Week + vuosia);
        likooriEuroLaskuWeek = likooriEuroLasku;
        likooriEuroLaskuWeek += Double.parseDouble(likooriWeek);
        String euviikko = new DecimalFormat("##.##").format(likooriEuroLaskuWeek);
        prefWeek.setStr("likoorieuro" + Week + vuosia, euviikko);


        String ol = prefWeek.getStr("likoorilitra" + Week + vuosia);
        likooriLitraLaskuWeek = likooriLitraLasku;
        likooriLitraLaskuWeek += Double.parseDouble(ol);
        String testing = new DecimalFormat("##.##").format(likooriLitraLaskuWeek);
        prefWeek.setStr("likoorilitra" + Week + vuosia, testing);

        //Kuukausi
        DataProccessor pref = new DataProccessor(getActivity(), "likooriEuro" + vuosia);
        String likoori = pref.getStr("likoorieuro" + euString);
        double likooriEuro = Double.parseDouble(likoori);
        likooriEuroLasku += likooriEuro;
        //muokkaa decimaalin formatti
        String eu = new DecimalFormat("##.##").format(likooriEuroLasku);
        //talentaa Shredpreference
        pref.setStr("likoorieuro" + euString, eu);

        //hakee sharedpreference(SP) kansion: olutLitra
        DataProccessor setLitra = new DataProccessor(getActivity(), "likooriLitra" + vuosia);
        //hakee sharedpreference tiedot että pystyn lisää
        String litra = setLitra.getStr("likoorilitra" + euString);


        //muokkasen double
        double likooriLitra = Double.parseDouble(litra);

        likooriLitraLasku += likooriLitra;
        //asettaa SP editointi valmiuteen

        //muokkaa decimaalin formatti
        String likooriLitrao = new DecimalFormat("##.##").format(likooriLitraLasku);
        //talentaa Shredpreference
        setLitra.setStr("likoorilitra" + euString, likooriLitrao);
    }


    private void megaViiski() {
        //hakee sharedpreference(SP) kansion: oluEuro
        //Viikko
        DataProccessor prefWeek = new DataProccessor(getActivity(), "viikkoJuomat");
        String viskiWeek = prefWeek.getStr("viskieuro" + Week + vuosia);
        viskiEuroLaskuWeek = viskiEuroLasku;
        viskiEuroLaskuWeek += Double.parseDouble(viskiWeek);
        String euviikko = new DecimalFormat("##.##").format(viskiEuroLaskuWeek);
        prefWeek.setStr("viskieuro" + Week + vuosia, euviikko);


        String ol = prefWeek.getStr("viskilitra" + Week + vuosia);
        viskiLitraLaskuWeek = viskiLitraLasku;
        viskiLitraLaskuWeek += Double.parseDouble(ol);
        String testing = new DecimalFormat("##.##").format(viskiLitraLaskuWeek);
        prefWeek.setStr("viskilitra" + Week + vuosia, testing);

        //Kuukausi
        DataProccessor pref = new DataProccessor(getActivity(), "viskiEuro" + vuosia);
        String viski = pref.getStr("viskieuro" + euString);
        double viskiEuro = Double.parseDouble(viski);
        viskiEuroLasku += viskiEuro;
        //muokkaa decimaalin formatti
        String eu = new DecimalFormat("##.##").format(viskiEuroLasku);
        //talentaa Shredpreference
        pref.setStr("viskieuro" + euString, eu);

        //hakee sharedpreference(SP) kansion: olutLitra
        DataProccessor setLitra = new DataProccessor(getActivity(), "viskiLitra" + vuosia);
        //hakee sharedpreference tiedot että pystyn lisää
        String litra = setLitra.getStr("viskilitra" + euString);


        //muokkasen double
        double viskiLitra = Double.parseDouble(litra);

        viskiLitraLasku += viskiLitra;
        //asettaa SP editointi valmiuteen

        //muokkaa decimaalin formatti
        String viskiLitrao = new DecimalFormat("##.##").format(viskiLitraLasku);
        //talentaa Shredpreference
        setLitra.setStr("viskilitra" + euString, viskiLitrao);
    }

    private void megaViina() {
        //hakee sharedpreference(SP) kansion: oluEuro

        //Viikko
        DataProccessor prefWeek = new DataProccessor(getActivity(), "viikkoJuomat");
        String viinaWeek = prefWeek.getStr("viinaeuro" + Week + vuosia);
        viinaEuroLaskuWeek = viinaEuroLasku;
        viinaEuroLaskuWeek += Double.parseDouble(viinaWeek);
        String euviikko = new DecimalFormat("##.##").format(viinaEuroLaskuWeek);
        prefWeek.setStr("viinaeuro" + Week + vuosia, euviikko);


        String ol = prefWeek.getStr("viinalitra" + Week + vuosia);
        viinaLitraLaskuWeek = viinaLitraLasku;
        viinaLitraLaskuWeek += Double.parseDouble(ol);
        String testing = new DecimalFormat("##.##").format(viinaLitraLaskuWeek);
        prefWeek.setStr("viinalitra" + Week + vuosia, testing);

        //Kuukausi
        DataProccessor pref = new DataProccessor(getActivity(), "viinaEuro" + vuosia);
        String viina = pref.getStr("viinaeuro" + euString);
        double viinaEuro = Double.parseDouble(viina);
        viinaEuroLasku += viinaEuro;
        //muokkaa decimaalin formatti
        String eu = new DecimalFormat("##.##").format(viinaEuroLasku);
        //talentaa Shredpreference
        pref.setStr("viinaeuro" + euString, eu);

        //hakee sharedpreference(SP) kansion: olutLitra
        DataProccessor setLitra = new DataProccessor(getActivity(), "viinaLitra" + vuosia);
        //hakee sharedpreference tiedot että pystyn lisää
        String litra = setLitra.getStr("viinalitra" + euString);


        //muokkasen double
        double viinaLitra = Double.parseDouble(litra);

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
     * @promillelaskuri
     */
    public void promillelaskuri() {
        SharedPreferences prefGet = getActivity().getSharedPreferences("Date_value", Activity.MODE_PRIVATE);

        alkoholiGrammoina = siideriGrammat.getValue() + oluenGrammat.getValue() + lonkeroGrammat.getValue() + viinaGrammat.getValue() + likooriGrammat.getValue() + viiniGrammat.getValue() + viskiGrammat.getValue(); // yhdistää juomien grammalaskurien arvot yhteen
        int paino = prefGet.getInt("Weight", 0); // hakee painon sharedprefensistä
        double gender = Double.parseDouble(prefGet.getString("gender", "0")); // hakee sukupuolikertoimen SPstä
        double tulos = alkoholiGrammoina / (paino * gender); // (alkoholin määrä veressä tällä hetkellä)lasku, josta tehdään uusi muuttuja (tulos)
        prominMaara = tulos;

        TextView tv1 = getView().findViewById(R.id.tvPromille);
        tv1.setText(new DecimalFormat("##.##").format(tulos)); //Double.toString tai new DecimalFormat("##.##").format


    }
}