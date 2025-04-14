package com.example.karty;

import static com.example.karty.MainActivity.adapter;
import static com.example.karty.MainActivity.adapterToday;
import static com.example.karty.MainActivity.todayCourses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;

public class fragment_dzisiejsze_zajęcia extends Fragment {
    LocalDate newDate;
    TextView data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View view=inflater.inflate(R.layout.fragment_dzisiejsze_zajecia,container,false);
        RecyclerView recyclerView= view.findViewById(R.id.recyclerViewToday);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //ustawia dzisiejszą date i zapisuje pole tekstowe
        data=view.findViewById(R.id.data);
        newDate= LocalDate.now();
        data.setText(String.valueOf(newDate));

        //jeśli baza danych nie jest pusta to wyczyść tekst
        if(todayCourses.size()!=0){
            TextView textView=view.findViewById(R.id.Zajęcia);
            textView.setText("");
        }

        recyclerView.setAdapter(adapterToday);




        return view;

    }
}
