package com.example.karty;

import static com.example.karty.MainActivity.courses;
import static com.example.karty.MainActivity.todayCourses;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Button;
import  android.widget.TextView;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Objects;

public class DetailsActivity  extends Activity{
    String hours;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView detailsTextView=findViewById(R.id.detailsTextView);
        Button deleteBTN=findViewById(R.id.DeleteBTN);

        //pobieranie pozycji i ustanienie co się staniu po kliknięciu w przycisk
        Integer position=getIntent().getIntExtra("position",0);

        deleteBTN.setOnClickListener(x->
                usuwanie(position)
        );
        //pobranie danych z intenta
        String groupName=getIntent().getStringExtra("groupName");
        String courseName=getIntent().getStringExtra("courseName");
        String numberOfClasses=getIntent().getStringExtra("numberOfClasses");
        String classDuration=getIntent().getStringExtra("classDuration");
        String startDate=getIntent().getStringExtra("startDate");
        String endDate=getIntent().getStringExtra("endDate");
        String placeName=getIntent().getStringExtra("placeName");
         hours=getIntent().getStringExtra("hours");

        //wyświtlanie danych
        detailsTextView.setText("Nazwa: "+courseName+" \n Nazwa grupy: "+groupName
        +"\n Ilość zajęć"+numberOfClasses+"\n czas trwania zajęć: "+classDuration+"min \n " +
                "Data Startowa:"+startDate+" \n Data Końca:"+endDate+" \n nazwa placówki:"
                +placeName+"\n przedział godzinowy: "+hours);

    }
    //usuwa kurs z baz danych i z bazy danych dzisiejszcych jesli występuje dziś
    public void usuwanie(int position){
        Intent MainActivity= new Intent(this,MainActivity.class);
        Log.d("przed", String.valueOf(courses.size()));
        courses.remove(position);
        courses.size();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        LocalDate date=LocalDate.now();
        for(int i=0;i< todayCourses.size();i++) {
            if (Objects.equals( hours, todayCourses.get(i).getHours()))
            {
                todayCourses.remove(i);
            }
        }
        startActivity(MainActivity);
    }

}
