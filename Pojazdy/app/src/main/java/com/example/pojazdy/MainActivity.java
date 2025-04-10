package com.example.pojazdy;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Domyślnie załaduj pierwszy fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new VehiclesFragment()).commit();

        // Obsługa navbar dolnej za pomocą if
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;


            if (item.getItemId() == R.id.nav_vehicles) {
                selectedFragment = new VehiclesFragment();
            } else if (item.getItemId() == R.id.nav_vin) {
                selectedFragment = new VinFragment();
            } else if (item.getItemId() == R.id.nav_fuel) {
                selectedFragment = new FuelFragment();
            } else if (item.getItemId() == R.id.nav_license) {
                selectedFragment = new LicenseFragment();
            }

            // Zamiana fragmentu
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            }

            return true;
        });
    }
}
