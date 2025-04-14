package com.example.karty;

import static com.example.karty.MainActivity.courses;
import static com.example.karty.MainActivity.todayCourses;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.TreeMap;

public class fragment_formularz_kurs extends Fragment {
    private EditText courseNameET,placenameET, numberOfClassesET, classDurationET;
    private DatePicker  startDateET;
    private  TimePicker courseStarts;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_make_kurs,container,false);
        //zapisujemy elemnty formularzu
        courseNameET= view.findViewById(R.id.courseNameET);
        placenameET= view.findViewById(R.id.nazwaplacowki);
        numberOfClassesET = view.findViewById(R.id.lessonCountEditText);
        classDurationET = view.findViewById(R.id.lessonDurationEditText);
        courseStarts = view.findViewById(R.id.timepicker);
        startDateET = view.findViewById(R.id.startDate);
        Button saveBTN = view.findViewById(R.id.saveButton);


        //ustawienie timepickera by czas był podzielon na 24
        TimePicker timePicker = view.findViewById(R.id.timepicker);
        timePicker.setIs24HourView(true);
        timePicker.setCurrentHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));

        //obsługa przycisku
        saveBTN.setOnClickListener(v -> onFormSubmit());





        return view;
    }
    private void onFormSubmit(){
        //walidacja czy pola nie sa puste
        if(String.valueOf(courseNameET.getText()).equals("")||String.valueOf(placenameET.getText()).equals("")
                ||String.valueOf(numberOfClassesET.getText()).equals("")||
                String.valueOf(classDurationET.getText()).equals("")||String.valueOf(courseStarts.getCurrentHour()).equals("")
                ||String.valueOf(startDateET.getYear()).equals("")){
            Toast.makeText(getContext(), "uzupełnij wszystkie pola", Toast.LENGTH_SHORT).show();
            return;
        }

        //pobiera czas i zamienia na stringa
        //czas początku
       String timeOfBegining =(courseStarts.getCurrentHour())+":"+(courseStarts.getCurrentMinute());

       Integer timeOfBeginingHours= Integer.valueOf(courseStarts.getCurrentHour());
       Integer timeOfBeginingMinutes= Integer.valueOf(courseStarts.getCurrentMinute());
       //czas trfania zajęć
       String lessonDuration=String.valueOf(classDurationET.getText());
       float timeAdded=(Float.valueOf(lessonDuration));
       int hours= (int)timeAdded/60;
       float minutes= (float) (timeAdded-(hours*60));

        String timeofEnd;
       //czas końcowy dodaje czas początku z czasem ile kurs ma trwać
        if(minutes+timeOfBeginingMinutes<60&&hours + timeOfBeginingHours<24) {
            timeofEnd = String.valueOf(hours + timeOfBeginingHours) + ":" + String.valueOf(Integer.valueOf((int)
                    (minutes + timeOfBeginingMinutes)));
        }else{
            if(minutes+timeOfBeginingMinutes>60){
                hours= (int) (hours+(minutes+timeOfBeginingMinutes)/60);
            }
            if(hours + timeOfBeginingHours>24){
                //jesli kurs przekracza czasowo dzień w którym został stworzony, to odrzuca dodanie.
                Toast.makeText(getContext(), "kurs może trwać tylko w czasie jednego dnia", Toast.LENGTH_SHORT).show();
                return;

            }
             timeofEnd = String.valueOf((hours + timeOfBeginingHours)%24) + ":" + String.valueOf(Integer.valueOf((int)
                     ((minutes+timeOfBeginingMinutes)%60)));


        }
        //ilosć zajęć
        String numberOfClasses=String.valueOf(numberOfClassesET.getText());


        //tworzenie nazwy grupy
        String groupName=placenameET.getText()+"/"+startDateET.getDayOfMonth()+"/"+
                String.valueOf((hours + timeOfBeginingHours)%24);

        //nazwa kursu , placówka, ilość zajęć
        String startDate;
        if(startDateET.getDayOfMonth()>=10){
            startDate = String.valueOf(startDateET.getYear()+"/"+(startDateET.getMonth()+1)+"/"+startDateET.getDayOfMonth());
        }else {
            startDate = String.valueOf(startDateET.getYear()+"/"+(startDateET.getMonth()+1)+"/"+"0"+startDateET.getDayOfMonth());
        }
        //koniec czasu kursu wyliczany z ilosci zajęć
        String endDate;
        LocalDate startDateParse;
        if(startDateET.getDayOfMonth()>=10) {
            startDateParse = LocalDate.parse(startDateET.getYear() + "-" + (startDateET.getMonth()+1)
                    + "-" + startDateET.getDayOfMonth());
        }else {
            startDateParse = LocalDate.parse(startDateET.getYear() + "-" + (startDateET.getMonth()+1 )
                    + "-0" + startDateET.getDayOfMonth());
        }

        endDate= String.valueOf(startDateParse.plusWeeks( Integer.valueOf(numberOfClasses) ));


        //wartośći przrobionę by dodać do tworzenia obiektu
        //nazwa kursu
        String courseName=String.valueOf(courseNameET.getText());

        //placówka
        String placeName=String.valueOf(placenameET.getText());

        //długość zajęć
        String courseDuration=String.valueOf(classDurationET.getText());


        //formatowanie daty
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();

        //walidacja czy już kurs istnieje w tym dniu
        for( Courses schoolClass : courses) { //dla wszystkich obiektów w array list
            if (Objects.equals(schoolClass.getStartDate(),String.valueOf(startDate))) { //jeśli daty się zgadzają
                if (Objects.equals(schoolClass.getHoursofStart(),timeOfBegining)){
                    // i czasy to wysyła wiadomość użytkownikowi, że termin jest zajęty
                    Toast.makeText(getContext(), "Istnieje już zajęcie w tym terminie",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
            }}

        //jeśli kurs dzisiejszy dodaje do array list courseToday

        if(Objects.equals(formatter.format(date),startDate))
        {

                todayCourses.add(new Courses(courseName,groupName,numberOfClasses,courseDuration,startDate,endDate
                        ,placeName,timeOfBegining,timeofEnd));
        }
        //toast kiedy wszystko pójdzie poprawnie
        Toast.makeText(getContext(), "Kurs został dodany!", Toast.LENGTH_SHORT).show();

        //tworzy kony kurs
        courses.add(new Courses(courseName,groupName,numberOfClasses,courseDuration,startDate,endDate
                ,placeName,timeOfBegining,timeofEnd));



    }


}
