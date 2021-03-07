package com.example.testproju2.ui.setting;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
    String femaleKerroin = "0.66"; //naisen keksimääräinen vesipitoisuus
    String maleKerroin = "0.75";  //miehen keksimääräinen vesipitoisuus
    public double sukupuoliKerroin = 0;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_setting, container, false);
        BottomNavigationView navView = ((MainActivity) getActivity()).findViewById(R.id.nav_view);
        SharedPreferences prefGet = getActivity().getSharedPreferences("Date_value", Activity.MODE_PRIVATE);
        String name = prefGet.getString("Name", "");
        if ((name.equals(""))) {
            navView.setVisibility(View.GONE);
            Log.d("Note", "Gone");
        } else {
            navView.setVisibility(View.VISIBLE);
            Log.d("Note", "Visible");
        }

        btn = (Button) root.findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() {
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

                    TextView textView = (TextView) getActivity().findViewById(R.id.textView11);//hakee textviewin nimelle
                    textView.setText(inputName);//Asettaa syötetyn nimen äskeiseen texviewiin
                    int weightInt = Integer.parseInt(inputWeight);//muuttaa painon string -> int
                    SharedPreferences prefPut = getActivity().getSharedPreferences("Date_value", Activity.MODE_PRIVATE);//hakee sharedpreference(SP) kansion: data_value
                    SharedPreferences.Editor prefEditor = prefPut.edit(); //asettaa SP editointi valmiuteen

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

                    navView.setVisibility(View.VISIBLE);
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


