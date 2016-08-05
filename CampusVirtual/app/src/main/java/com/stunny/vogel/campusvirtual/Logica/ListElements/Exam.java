package com.stunny.vogel.campusvirtual.Logica.ListElements;

import java.sql.Time;
import java.util.Date;

/**
 * Created by alex on 5/08/16.
 */
public class Exam {
    public Date fecha;
    public Time hora;
    public String degree;
    public Subject subject;
    public String room;

    public Exam(Subject s){
        subject = s;
    }
}
