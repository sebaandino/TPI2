package org.example;

import java.util.ArrayList;

public class Ronda {
    private String nro;
    private ArrayList partidos;

    public Ronda() {
    }
    public Ronda(String nro, ArrayList partidos) {
        this.nro = nro;
        this.partidos = partidos;
    }

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public ArrayList getPartidos() {
        return partidos;
    }

    public void setPartidos(ArrayList partidos) {
        this.partidos = partidos;
    }
}