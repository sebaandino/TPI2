package org.example;

public class Pronostico {
    private Partido partido;
    private Equipo equipo;
    private resultadoEnum resultado;

    public Pronostico() {
        super();
    }

    public Pronostico(Partido partido, Equipo equipo, resultadoEnum resultado) {
        this.partido = partido;
        this.equipo = equipo;
        this.resultado = resultado;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public resultadoEnum getResultado() {
        return resultado;
    }

    public void setResultado(resultadoEnum resultado) {
        this.resultado = resultado;
    }

    public int puntos() {
        if (partido.resultado(equipo) == resultado) {
            return 1;
        } else {
            return 0;
        }
    }
}