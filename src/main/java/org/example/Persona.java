package org.example;

public class Persona {
    private String nombre;
    private Pronostico[] pronostico;

    public Persona() {
    }

    public Persona(String nombre, Pronostico[] pronostico) {
        this.nombre = nombre;
        this.pronostico = pronostico;
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
}