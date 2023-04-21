package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;

public class inicio {

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

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

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/pronosticos_db", "root", "root");
        Statement st = conn.createStatement();

        ResultSet rs = st.executeQuery("SELECT * FROM pronosticos");

        Pronostico[] totalpronosticos1 = new Pronostico[4];

int k = 0;
            for (int i = 0; i <4; i++) {
                while (rs.next()) {
                Pronostico pronostico = new Pronostico();
                resultadoEnum resultadoPronostico = null;

                if (rs.getString("empate").isEmpty()
                        && rs.getString("gana2").isEmpty()) {
                    resultadoPronostico = resultadoEnum.ganador;
                }
                if (rs.getString("gana1").isEmpty()
                        && rs.getString("empate").isEmpty()) {
                    resultadoPronostico = resultadoEnum.perdedor;
                }
                if (rs.getString("gana1").isEmpty()
                        && rs.getString("gana2").isEmpty()) {
                    resultadoPronostico = resultadoEnum.empate;
                }

                pronostico.setPartido(partidosRonda[k]);
                pronostico.setEquipo(partidosRonda[k].getEquipo1());
                pronostico.setResultado(resultadoPronostico);
                totalpronosticos1[k] = pronostico;
                k++;
            }
        }

            for(int i = 0;i<4;i++){
                System.out.println(totalpronosticos1[i].getEquipo().getNombre());
                System.out.println(totalpronosticos1[i].getResultado());
            }



            Persona persona = new Persona();
            persona.setNombre("elias");

            persona.setPronostico(totalpronosticos1);

           int totalPuntos = 0;
            for(Pronostico resultado : totalpronosticos1){
                totalPuntos +=  resultado.puntos();
            }
            persona.setPuntajeTotal(totalPuntos);

        System.out.println("Ronda nª: " + nr.getNro());
        System.out.println("*************");
        for(Partido partido : partidosRonda){
            System.out.println(partido.getEquipo1().getNombre() + " vs " + partido.getEquipo2().getNombre());
            System.out.println(partido.getResultadoequipo1() + " - " + partido.getResultadoequipo2());}

            System.out.println("***********");

            System.out.println("el total de puntos del participante: " + persona.getNombre() + " es: " + totalPuntos);

conn.close();

    }
}