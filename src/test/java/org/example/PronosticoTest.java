package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PronosticoTest {
    Pronostico objPronosticoTest;
    @BeforeEach
    void setUp() throws Exception {
        objPronosticoTest = new Pronostico();
    }
    @Test
    void testCalcularPuntos() {

        Equipo equipo1 = new Equipo("argentina");
        Equipo equipo2 = new Equipo("arabia");
        Partido partido = new Partido();
        partido.setEquipo1(equipo1);
        partido.setEquipo2(equipo2);
        partido.setResultadoequipo1(1);
        partido.setResultadoequipo2(2);
        objPronosticoTest.setEquipo(equipo1);
        objPronosticoTest.setPartido(partido);
        objPronosticoTest.setResultado(resultadoEnum.ganador);

        int puntajeTotal = objPronosticoTest.puntos();
        int puntajeEsperado = 0;

        boolean resultado = (puntajeTotal == puntajeEsperado);

        assertTrue(resultado);
    }

    @Test
    void testFallaCalcularPuntos() {

        Equipo equipo1 = new Equipo("argentina");
        Equipo equipo2 = new Equipo("arabia");
        Partido partido = new Partido();
        partido.setEquipo1(equipo1);
        partido.setEquipo2(equipo2);
        partido.setResultadoequipo1(1);
        partido.setResultadoequipo2(2);
        objPronosticoTest.setEquipo(equipo1);
        objPronosticoTest.setPartido(partido);
        objPronosticoTest.setResultado(resultadoEnum.ganador);

        int puntajeTotal = objPronosticoTest.puntos();
        int puntajeEsperado = 1;

        boolean resultado = (puntajeTotal == puntajeEsperado);

        assertFalse(resultado);
    }
}