package com.example.karty;

public class Courses {
    // Course name (e.g., "Java Programming")
    private String courseName;
    // nazwa grupy
    private String groupName;
    // ile będzie zajęc w tym kursie
    private String numberOfClasses;
    // jak dłygo czasu trwa w minutach
    private String classDuration;
    // kiedy kurs się zaczyna
    private String startDate;
    // kiedy kurs się kończy
    private String endDate;
    // gdzie sie kurs odbywa
    private String placeName;
    // kiedy kurs się zaczyna godzinowo
    private String hoursofStart;
    // kiedy kurs siękończy godzinowo
    private String hoursofEnd;
    // konskruktor do inicjaji klasy
    public Courses(String courseName, String groupName,String numberOfClasses,
                   String classDuration,String startDate, String endDate,
                   String placeName,String hoursofStart,String hoursofEnd) {
        this.courseName = courseName;
        this.groupName = groupName;
        this.numberOfClasses = numberOfClasses;
        this.classDuration = classDuration;
        this.startDate = startDate;
        this.endDate = endDate;
        this.placeName=placeName;
        this.hoursofStart=hoursofStart;
        this.hoursofEnd=hoursofEnd;
    }

    // get osobne zmienne
    public String getCourseName() {
        return courseName;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getNumberOfClasses() {
        return numberOfClasses;
    }

    public String getClassDuration() {
        return classDuration;
    }

    public String getStartDate() {
        return startDate;
    }
    public String getCourseDate(){

        return startDate+" - "+endDate;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getHoursofEnd() {
        return hoursofEnd;
    }

    public String getHoursofStart() {
        return hoursofStart;
    }
    // godziny kursy przeformatowane(e.g., "10:00 - 12:00")
    public String getHours(){
        return getHoursofStart()+"- "+getHoursofEnd();

    }

    public String getEndDate() {
        return endDate;
    }
}
