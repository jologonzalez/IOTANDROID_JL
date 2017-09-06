package com.example.jorgelopez.iotandroid_jl.entidades;

/**
 * Created by Jorge Lopez on 30/08/2017.
 */

public class Hogar {

    private int Sala;
    private int Habitacion1;
    private int Habitacion2;
    private int Cocina;

    public Hogar(){

    }

    public int getSala() {
        return Sala;
    }

    public void setSala(int sala) {
        Sala = sala;
    }

    public int getHabitacion1() {
        return Habitacion1;
    }

    public void setHabitacion1(int habitacion1) {
        Habitacion1 = habitacion1;
    }

    public int getHabitacion2() {
        return Habitacion2;
    }

    public void setHabitacion2(int habitacion2) {
        Habitacion2 = habitacion2;
    }

    public int getCocina() {
        return Cocina;
    }

    public void setCocina(int cocina) {
        Cocina = cocina;
    }
}
