package org.example;

public class Persona {
    private String nombre;
    private Pronostico [] pronostico;
    private int puntajeTotal;

    public Persona() {
    }

    public Persona(String nombre, Pronostico[] pronostico, int puntajeTotal) {
        this.nombre = nombre;
        this.pronostico = pronostico;
        this.puntajeTotal = puntajeTotal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Pronostico[] getPronostico() {
        return pronostico;
    }

    public void setPronostico(Pronostico[] pronostico) {
        this.pronostico = pronostico;
    }

    public int getPuntajeTotal() {
        return puntajeTotal;
    }

    public void setPuntajeTotal(int puntajeTotal) {
        this.puntajeTotal = puntajeTotal;
    }
}