package com.stunny.vogel.campusvirtual.Logica.ListElements;

import java.util.Date;

/**
 * Created by alex on 5/08/16.
 */
public class Student {

    public String name;
    public Date birthDate;
    public Degree degree;
    public String photoPah;
    public enum Gender{
        M("Masculino"), F("Femenino");
        private final String value;

        Gender(final String value){
            this.value=value;
        }

        public String getValue(){
            return value;
        }

        @Override
        public String toString(){
            return getValue();
        }
    }

}
