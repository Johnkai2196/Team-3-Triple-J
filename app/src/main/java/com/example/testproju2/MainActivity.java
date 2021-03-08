package com.example.testproju2;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    /**
     * Kutsutaan kun aktiviteetti alkaa
     * @param savedInstanceState kun activity alkaa uudeleen samutuksen jälkeen sitten tämä paketti sisältää tiedot, jotka se on viimeksi toimittanut
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //löytä bottom nav
        BottomNavigationView navView = findViewById(R.id.nav_view);

        //Jokaisen valikkotunnuksen välittäminen tunnisteina, koska kukin -valikkoa tulisi pitää ylätason kohteina.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_setting)
                .build();
        //ohjain naville
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //Asettaa AppCompatActivity.getSupportActionBar (): n palauttaman ActionBarin NavControllerin kanssa käytettäväksi.
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        //Asettaa työkalurivin käytettäväksi NavControllerin kanssa.
        NavigationUI.setupWithNavController(navView, navController);
        //Hankki sharedPreferences kansio:Date_value
        SharedPreferences prefGet = getSharedPreferences("Date_value", Activity.MODE_PRIVATE);
        //Hankki tiedoston nimeä name
        String name = prefGet.getString("Name", "");
        TextView textView = (TextView) findViewById(R.id.textView11);
        //asetta textview
        textView.setText(name);
        // Jos SharedPreferences Date_value kansion sisällä oleva tiedost nimi name ei ole mitään se aloitta setting fragmentissä
        if ((name.equals(""))) {
            navView.setSelectedItemId(R.id.navigation_setting); // change to whichever id should be default
        } else {
            //jos on nimi alotta home fragment
            navView.setSelectedItemId(R.id.navigation_home); // change to whichever id should be default
        }
    }
}


