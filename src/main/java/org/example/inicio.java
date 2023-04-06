package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class inicio {

    public static void main(String[] args) throws IOException {

        List<String> ArchivoResultados = Files.readAllLines(Paths.get("C:\\Users\\Seba\\Desktop\\TPI2\\resultados.csv"));
        Partido [] PartidosRonda = new Partido[ArchivoResultados.size()];
        ArchivoResultados.remove(0);

        int j = 0;
        for (int i = 0; i < ArchivoResultados.size() ; i++) {

            String vector[] = ArchivoResultados.get(i).split(";");

            Equipo equipo1 = new Equipo();
            equipo1.setNombre(vector[1]);

            Equipo equipo2 = new Equipo();
            equipo2.setNombre(vector[4]);

            Partido partido1 = new Partido();

            partido1.setEquipo1(equipo1);
            partido1.setEquipo2(equipo2);
            partido1.setResultadoequipo1(Integer.parseInt(vector[2]));
            partido1.setResultadoequipo2(Integer.parseInt(vector[3]));
            PartidosRonda[j] = partido1;
            j++;
        }

        List<String> ArchivoPronostico = Files.readAllLines(Paths.get("C:\\Users\\Seba\\Desktop\\TPI2\\pronosticos.csv"));
        ArchivoPronostico.remove(0);
        Pronostico[] TotalPronosticos = new Pronostico[ArchivoPronostico.size()];
        int h = 0;
        for (int k = 0; k < ArchivoPronostico.size(); k++) {
            Pronostico pronostico = new Pronostico();
            resultadoEnum ResultadoPronostico;
            if (ArchivoPronostico.get(k).split(";")[2].isEmpty()) {
                if (ArchivoPronostico.get(k).split(";")[4].isEmpty()) {
                    ResultadoPronostico = resultadoEnum.empate;
                } else {
                    ResultadoPronostico = resultadoEnum.perdedor;}
                } else {
                ResultadoPronostico = resultadoEnum.ganador;}
            pronostico.setPartido(PartidosRonda[k]);
            pronostico.setEquipo(PartidosRonda[k].getEquipo1());
            pronostico.setResultado(ResultadoPronostico);

            TotalPronosticos[h] = pronostico;
            h++;
        }
        int totalpuntos = 0;
        for (Pronostico pronostico : TotalPronosticos) {
            totalpuntos += pronostico.puntos();
        }

        System.out.println("el total de puntos es: " + totalpuntos);
    }
}
