package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class inicio {

    public static void main(String[] args) throws IOException {
        List<String> archivoresultados = Files.readAllLines(Paths.get("C:\\Users\\Seba\\Desktop\\TPI2\\resultados.csv")); //lee archivo de resultados
        Partido [] partidosRonda = new Partido[archivoresultados.size()]; //array para guardar los partidos

        archivoresultados.remove(0);  //remueve la linea 0 del archiv

        int j =0;   // variable para recorrer el array de los partidos
        for (String archivoresultado : archivoresultados) {
            String[] vector = archivoresultado.split(";");  //separa la linea por ; y lo va guardando en el vector

            Equipo equipo1 = new Equipo();  //crea objeto equipo y setea el nombre
            equipo1.setNombre(archivoresultado.split(";")[1]);

            Equipo equipo2 = new Equipo();
            equipo2.setNombre(archivoresultado.split(";")[4]);

            Partido partido = new Partido();  //crea objeto partido y setea los atributos
            partido.setEquipo1(equipo1);
            partido.setEquipo2(equipo2);
            partido.setResultadoequipo1(Integer.parseInt(vector[2]));
            partido.setResultadoequipo2(Integer.parseInt(vector[3]));
            partidosRonda[j] = partido;  //agrega el partido al array
            j++;  //avanzo un lugar en el array
        }

        Ronda nr = new Ronda();
        nr.setNro(archivoresultados.get(1).split(";")[0]);
        nr.setPartidos(partidosRonda);
        System.out.println("Ronda nª: " + nr.getNro());
        System.out.println(partidosRonda[0].getEquipo1().getNombre() + " vs " + partidosRonda[0].getEquipo2().getNombre());
        System.out.println(partidosRonda[0].getResultadoequipo1() + " - "+ partidosRonda[0].getResultadoequipo2());
        System.out.println(partidosRonda[1].getEquipo1().getNombre() + " vs " + partidosRonda[1].getEquipo2().getNombre());
        System.out.println(partidosRonda[1].getResultadoequipo1() + " - "+ partidosRonda[1].getResultadoequipo2());
        System.out.println(partidosRonda[2].getEquipo1().getNombre() + " vs " + partidosRonda[2].getEquipo2().getNombre());
        System.out.println(partidosRonda[2].getResultadoequipo1() + " - "+ partidosRonda[2].getResultadoequipo2());
        System.out.println(partidosRonda[3].getEquipo1().getNombre() + " vs " + partidosRonda[3].getEquipo2().getNombre());
        System.out.println(partidosRonda[3].getResultadoequipo1() + " - "+ partidosRonda[3].getResultadoequipo2());


        List<String> archivopronostico = Files.readAllLines(Paths.get("C:\\Users\\Seba\\Desktop\\TPI2\\pronosticos.csv"));  //lee archivo de pronosticos
        archivopronostico.remove(0);   //remueve la linea 0 del archivo
        Pronostico[] totalpronosticos = new Pronostico[archivopronostico.size()];   //array para guardar los pronosticos

        int h=0;   // variable para recorrer el array de los pronosticos
        for(int k=0 ; k < archivopronostico.size(); k++) {
            Pronostico pronostico = new Pronostico();
            resultadoEnum resultadoPronostico;    //variable para guardar el resultado del Enum
            if(archivopronostico.get(k).split(";")[2].isEmpty()) {    // isEmpy = si esta vacio es esa posicion devuelve un true
                if(archivopronostico.get(k).split(";")[4].isEmpty()) {
                    resultadoPronostico = resultadoEnum.empate;
                }else {
                    resultadoPronostico = resultadoEnum.perdedor;}
            }else {
                resultadoPronostico = resultadoEnum.ganador;}
            pronostico.setPartido(partidosRonda[k]);    //seteo los parametros de la clase pronostico
            pronostico.setEquipo(partidosRonda[k].getEquipo1());
            pronostico.setResultado(resultadoPronostico);

            totalpronosticos[h] = pronostico;  //guardo el nuevo pronosticos en el array de pronosticos
            h++;  // avanza un lugar en ese array
        }

        int totalpuntos=0;  //variable para ir sumando los puntos
        for (Pronostico pronostico : totalpronosticos) {   //recorro el array donde estan los pronosticos
            totalpuntos += pronostico.puntos();   //llamo al metodo de la clase pronostico para sumar los puntos
        }
        System.out.println("el total de puntos es: " + totalpuntos);  //muestro por consola el total de los puntos
    }
}