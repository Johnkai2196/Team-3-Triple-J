package com.example.testproju2.ui.setting;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.testproju2.MainActivity;
import com.example.testproju2.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingFragment extends Fragment {


    Button btn;
    //String muoto genderille kun menee sharepreference läpi
    String femaleKerroin = "0.66"; //naisen keksimääräinen vesipitoisuus
    String maleKerroin = "0.75";  //miehen keksimääräinen vesipitoisuus


    /**
     * Luo tämän fragmentin näkymä sille annettujen argumenttien avulla.
     * Tössä hoidan mitä tapahtuu kun käyttäjä pistää nimen, painon ja sukupuolen
     *
     * @param inflater           LayoutInflater-objekti, jota voidaan käyttää kaikkien fragmenttien paisuttamiseen
     * @param container          Jos se ei ole non-null, tämä on päänäkymä, johon fragmentin käyttöliittymä tulisi liittää. Katkelman ei pitäisi lisätä itse näkymää, mutta sitä voidaan käyttää näkymän LayoutParam-tiedostojen luomiseen. Tämä arvo voi olla tyhjä
     * @param savedInstanceState Jos se ei ole non-null, tätä fragmenttia rakennetaan uudelleen edellisestä tallennetusta tilasta, kuten tässä on annettu.
     * @return palautta minun luotu xml
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //luo View minun xml:sstä
        View root = inflater.inflate(R.layout.fragment_setting, container, false);
        //bottom nav hakkki
        BottomNavigationView navView = ((MainActivity) getActivity()).findViewById(R.id.nav_view);
        //hakee sharedpreference(SP) kansion: data_value
        SharedPreferences prefGet = getActivity().getSharedPreferences("Date_value", Activity.MODE_PRIVATE);
        //Hankki tiedoston nimeä name
        String name = prefGet.getString("Name", "");
        //jos ei ole nimeä bottom nav ei näy että voidaan välttä henkilön menevän toiseen fragmenttin
        if ((name.equals(""))) {
            navView.setVisibility(View.GONE);

        } else {
            //pistää näkyvin nav jos on nimi
            navView.setVisibility(View.VISIBLE);

        }

        btn = (Button) root.findViewById(R.id.button2);
        // kuuntelee kun painaa button
        btn.setOnClickListener(new View.OnClickListener() {
            /**
             *Kun save nappi on painettu
             * @param v kun näkymää on napsautettu.
             */
            @Override
            public void onClick(View v) {

                //Hakee editTextit ja radiogroupin
                EditText nimi = getView().findViewById(R.id.editTextTextPersonName);
                EditText weight = getView().findViewById(R.id.henkilopaino);
                RadioGroup group = getView().findViewById(R.id.sukupuoliGroup);

                //Hankkii käyttäjän syötetyt tiedot ja muokkaa ne toString
                String inputName = nimi.getText().toString();
                String inputWeight = weight.getText().toString();

                int id = group.getCheckedRadioButtonId();

                // jos käyttäjän syöttää nimen ja painon ja valitsee radiobuttonin
                if (!inputName.matches("") && !inputWeight.matches("") && !(id == -1)) {
                    //hakee textviewin nimelle
                    TextView textView = (TextView) getActivity().findViewById(R.id.textView11);
                    //Asettaa syötetyn nimen äskeiseen texviewiin
                    textView.setText(inputName);
                    //muuttaa painon string -> int
                    int weightInt = Integer.parseInt(inputWeight);
                    //hakee sharedpreference(SP) kansion: data_value
                    SharedPreferences prefPut = getActivity().getSharedPreferences("Date_value", Activity.MODE_PRIVATE);
                    //asettaa SP editointi valmiuteen
                    SharedPreferences.Editor prefEditor = prefPut.edit();

                    //jos valittu radiobutton on nainen, SP tallentuu gender ja 0,66
                    if (group.getCheckedRadioButtonId() == R.id.radioFemale) {
                        prefEditor.putString("gender", femaleKerroin);

                        //muutoin SP tallentuu gender ja 0,75
                    } else {
                        prefEditor.putString("gender", maleKerroin);
                    }

                    //tallentaa SP syötetyn nimen ja painon
                    prefEditor.putString("Name", inputName);
                    prefEditor.putInt("Weight", weightInt);


                    //asetta SP tiedot
                    prefEditor.apply();

                    //poistaa textit button painelun jälkeen
                    nimi.setText("");
                    weight.setText("");
                    //asetta uudet hinttit
                    nimi.setHint("Name");
                    weight.setHint("Weight");

                    //pistä näkyville
                    navView.setVisibility(View.VISIBLE);
                    //sirtä suut talennuksen jälkeen alkoholilaskurin
                    navView.setSelectedItemId(R.id.navigation_home);
                }
                // jos käyttäjä ei syötä tietoja
                else {
                    nimi.setHint("Please insert name");
                    weight.setHint("Please insert weight");
                }

            }
        });

        return root;

    }


}


