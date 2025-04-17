package com.example.karty;

import static com.example.karty.MainActivity.adapter;
import static com.example.karty.MainActivity.courses;
import static com.example.karty.MainActivity.todayCourses;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;

public class fragment_wszystkie_zajecia extends Fragment {
    LocalDate newDate;
    TextView data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_wszystkie_zajecia,container,false);
        RecyclerView recyclerView= view.findViewById(R.id.recyclerView);//nav_host_fragment recyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        data=view.findViewById(R.id.data);
        newDate=LocalDate.now();
        data.setText(String.valueOf(newDate));
        //jeśli baza danych nie jest pusta to wyczyść tekst
        if(courses.size()!=0){
            TextView textView=view.findViewById(R.id.Zajęcia);
            textView.setText("");
        }

        recyclerView.setAdapter(adapter);

        Button btnForward=view.findViewById(R.id.goForwardBTn);
        btnForward.setOnClickListener(x ->
                changeDate(1));
        Button btnBack=view.findViewById(R.id.goBackBTN);
        btnBack.setOnClickListener(x ->
                changeDate(-1));





        return view;
    }

    public void changeDate(int i){
        Log.d("witam", String.valueOf(i));

        if(i==1){
            newDate=newDate.plusDays(1);

        }
        else {
           newDate= newDate.minusDays(1);
        }
        data.setText(String.valueOf(newDate));

    }
}
