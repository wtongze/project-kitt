package com.project.kitt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //check if dark mode is enabled, if so, then manually change colors in calendar
        // to dark theme, otherwise keep normal calendar colors. Implemented because
        // calendar did not respect the theme, while all other fragments did
        int nightModeFlags =
                this.getBaseContext().getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                setTheme(R.style.DarkTheme);
                break;

            case Configuration.UI_MODE_NIGHT_NO:
                setTheme(R.style.LightTheme);
                break;

            case Configuration.UI_MODE_NIGHT_UNDEFINED:
                break;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //start in the home fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();

        // bottom nav bar that appears for all fragments
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
           Fragment selectedFragment = new HomeFragment();
           // start a new fragment when user selects different tab on bottom nav bar
           switch (menuItem.getItemId()){
                // id from tab selected in res/menu/bottom_nav.xml
               case R.id.nav_home:
                   selectedFragment = new HomeFragment();
                   break;

               case R.id.nav_today:
                   selectedFragment = new CalendarFragment();
                   break;

               case R.id.nav_settings:
                   selectedFragment = new SettingsFragment();
                   break;

           }
           //display the chosen fragment
           getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
        return true;
        }
    };

    public void callAddInfo(View v){
        Intent intent = new Intent(MainActivity.this, AddInfo.class);
        startActivity(intent);
    }

}
