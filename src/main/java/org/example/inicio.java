package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class inicio {

    public static void main(String[] args) throws IOException {
        List<String> archivoresultados = Files.readAllLines(Paths.get("C:\\Users\\Seba\\Desktop\\TPI2\\resultados.csv")); //lee archivo de resultados
        archivoresultados.remove(0);  //remueve la linea 0 del archivo
        Partido[] partidosRonda = new Partido[archivoresultados.size()]; //array para guardar los partidos

        int j = 0;   // variable para recorrer el array de los partidos
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
            j++; //avanzo un lugar en el array
        }
        Ronda nr = new Ronda();
        nr.setNro(archivoresultados.get(1).split(";")[0]);
        nr.setPartidos(partidosRonda);

        List<String> archivopronostico = Files.readAllLines(Paths.get("C:\\Users\\Seba\\Desktop\\TPI2\\pronosticos.csv"));  //lee archivo de pronosticos
        archivopronostico.remove(0);   //remueve la linea 0 del archivo
        Pronostico[] totalpronosticos1 = new Pronostico[4];   //array para guardar los pronosticos

        Persona persona1 = new Persona();
        persona1.setNombre(archivopronostico.get(0).split(";")[0]);

        int h = 0;   // variable para recorrer el array de los pronosticos

        for (int k = 0; k <4; k++) {
            Pronostico pronostico1 = new Pronostico();
            resultadoEnum resultadoPronostico1 = null;    //variable para guardar el resultado del Enum

            if (archivopronostico.get(k).split(";")[3].isEmpty()
            && archivopronostico.get(k).split(";")[4].isEmpty()) {
                resultadoPronostico1 = resultadoEnum.ganador;}
            if(archivopronostico.get(k).split(";")[2].isEmpty()
            && archivopronostico.get(k).split(";")[3].isEmpty()){
                resultadoPronostico1 = resultadoEnum.perdedor;}
            if(archivopronostico.get(k).split(";")[2].isEmpty()
                    && archivopronostico.get(k).split(";")[4].isEmpty()){
                resultadoPronostico1 = resultadoEnum.empate;}

            pronostico1.setPartido(partidosRonda[k]);    //seteo los parametros de la clase pronostico
            pronostico1.setEquipo(partidosRonda[k].getEquipo1());
            pronostico1.setResultado(resultadoPronostico1);

            totalpronosticos1[h] = pronostico1;  //guardo el nuevo pronosticos en el array de pronosticos
            h++;  // avanza un lugar en ese array
        }
        persona1.setPronostico(totalpronosticos1);

        Pronostico[] totalpronosticos2 = new Pronostico[4];   //array para guardar los pronosticos
        Persona persona2 = new Persona();
        persona2.setNombre(archivopronostico.get(5).split(";")[0]);
        int r = 0;   // variable para recorrer el array de los pronosticos

        for (int y = 4; y < 8; y++) {
            Pronostico pronostico2 = new Pronostico();
            resultadoEnum resultadoPronostico2 = null;    //variable para guardar el resultado del Enum

            if (archivopronostico.get(y).split(";")[3].isEmpty()
                    && archivopronostico.get(y).split(";")[4].isEmpty()) {
                resultadoPronostico2 = resultadoEnum.ganador;}
            if(archivopronostico.get(y).split(";")[2].isEmpty()
                    && archivopronostico.get(y).split(";")[3].isEmpty()){
                resultadoPronostico2 = resultadoEnum.perdedor;}
            if(archivopronostico.get(y).split(";")[2].isEmpty()
                    && archivopronostico.get(y).split(";")[4].isEmpty()){
                resultadoPronostico2 = resultadoEnum.empate;}

            pronostico2.setPartido(partidosRonda[r]);    //seteo los parametros de la clase pronostico
            pronostico2.setEquipo(partidosRonda[r].getEquipo1());
            pronostico2.setResultado(resultadoPronostico2);

            totalpronosticos2[r] = pronostico2;  //guardo el nuevo pronosticos en el array de pronosticos
            r++;  // avanza un lugar en ese array
        }
        persona2.setPronostico(totalpronosticos2);

        int totalpuntos1 = 0;  //variable para ir sumando los puntos
        for (Pronostico resultado1 : totalpronosticos1) {   //recorro el array donde estan los pronosticos
            totalpuntos1 += resultado1.puntos();   //llamo al metodo de la clase pronostico para sumar los puntos
        }
        persona1.setPuntajeTotal(totalpuntos1);

        int totalpuntos2 = 0;  //variable para ir sumando los puntos
        for (Pronostico resultado2 : totalpronosticos2) {   //recorro el array donde estan los pronosticos
            totalpuntos2 += resultado2.puntos();   //llamo al metodo de la clase pronostico para sumar los puntos
        }

       System.out.println("Ronda nª: " + nr.getNro());
        System.out.println("*************");
        for (Partido partido : partidosRonda) {
            System.out.println(partido.getEquipo1().getNombre() + " vs " + partido.getEquipo2().getNombre());
            System.out.println(partido.getResultadoequipo1() + " - " + partido.getResultadoequipo2());
        }
        System.out.println("*****************");

        System.out.println("el total de puntos del participante " + persona1.getNombre() + " es: " + totalpuntos1); //muestro por consola el total de los punto
        System.out.println("el total de puntos del participante " + persona2.getNombre() + " es: " + totalpuntos2);
        System.out.println(persona1.getPuntajeTotal());
    }
}