package com.example.karty;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // zainicjowana zmienne courses,todaycourses to array list przychuwujący konkretne kursy
    public static ArrayList<Courses> courses=new ArrayList<>();;

    public static ArrayList<Courses> todayCourses=new ArrayList<>();;

    //adaptery
    public  static ItemAdapter adapter;

    public static  ItemAdapter adapterToday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //adapter
        adapter = new ItemAdapter(courses, this);
        adapterToday = new ItemAdapter(todayCourses,this);

        //bottom nav
        BottomNavigationView bottomNavigationView =findViewById(R.id.bottom_navigation);
        //załaduj mowy listner
        loadFragment(new fragment_dzisiejsze_zajęcia());


        //zmienia fragment zalęznie od inputu użytkownika w navbar
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            if(item.getItemId()==R.id.navigation_create_group){
                selectedFragment = new fragment_wszystkie_zajecia();
            }
            if(item.getItemId()==R.id.navigation_home){
                selectedFragment=new fragment_dzisiejsze_zajęcia();
            }
            if (item.getItemId()==R.id.navigation_create_classes){
                selectedFragment=new fragment_formularz_kurs();
            }
            loadFragment(selectedFragment);


            return true;
        });
    }

    //ładuje nowy fragment
    public void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,fragment).commit();
    }
}