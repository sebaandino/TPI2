package org.example;

public class Equipo {
    private String nombre;

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Equipo(String nombre) {
        super();
        this.nombre = nombre;
    }
    public Equipo() {
        super();
    }
}