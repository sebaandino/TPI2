package org.example;

public class Equipo {
    private String nombre;
    private String descripcion;

    public Equipo(String nombre) {
        super();
        this.nombre = nombre;
    }
    public Equipo() {
        super();
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}