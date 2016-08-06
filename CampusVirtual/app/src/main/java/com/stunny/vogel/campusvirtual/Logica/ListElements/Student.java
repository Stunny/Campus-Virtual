package com.stunny.vogel.campusvirtual.Logica.ListElements;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by alex on 5/08/16.
 */
public class Student {
    public String name;
    public Date birthDate;
    public String degree;
    public String photoPath;
    public String gender;

    public Student(){}

    public int getAge(){
        Calendar c = Calendar.getInstance();
        int currentYear = c.get(Calendar.YEAR);
        String[] bdate = birthDate.toString().split("\\-");
        int bYear = Integer.parseInt(bdate[0]);

        return currentYear - bYear;
    }
}


