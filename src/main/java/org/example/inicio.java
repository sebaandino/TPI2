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

        int m=0;
        for(int i=0 ; i<ArchivoResultados.size() ; i++){

            String[] vector = ArchivoResultados.get(i).split(";");

            Equipo equipo1 = new Equipo();
            equipo1.setNombre(ArchivoResultados.get(i).split(";")[1]);

            Equipo equipo2 = new Equipo();
            equipo2.setNombre(ArchivoResultados.get(i).split(";")[4]);

            /* String equipo1 = ArchivoResultados.get(i).split(";")[4];
            Equipo equipoN1 = new Equipo();
            equipoN2.setNombre(vector[1]); */
            /* String equipo2 = ArchivoResultados.get(i).split(";")[4];
            Equipo equipoN2 = new Equipo();
            equipoN2.setNombre(vector[4]); */

            int resultadoEquipo1 = (Integer.parseInt(vector[2]));
            int resultadoEquipo2 = (Integer.parseInt(vector[3]));

            System.out.println(equipo1);
            System.out.println(equipo2);
            System.out.println(resultadoEquipo1);
            System.out.println(resultadoEquipo2);
            Partido partido = new Partido(equipo1, equipo2, resultadoEquipo1, resultadoEquipo2);
            PartidosRonda[m] = partido;
            m++;
        }

        List<String> ArchivoPronostico = Files.readAllLines(Paths.get("C:\\Users\\Seba\\Desktop\\TPI2\\pronosticos.csv"));
        ArchivoPronostico.remove(0);
        Pronostico[] TotalPronosticos = new Pronostico[ArchivoPronostico.size()];
        int h = 0;
        for (int k = 0; k < ArchivoPronostico.size(); k++) {
            resultadoEnum ResultadoPronostico;
            Pronostico pronostico = new Pronostico();
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
