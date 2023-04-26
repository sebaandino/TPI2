package org.example;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;
import java.util.List;

public class inicio {

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

        try {

            Properties properties = new Properties();
            FileInputStream input = new FileInputStream("C:\\Users\\Seba\\Desktop\\TPI2\\config.ini");
            properties.load(input);

            //Obtener variables
            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");

            //Conexion a la BD
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM pronosticos");

        List<String> archivoresultados = Files.readAllLines(Paths.get("C:\\Users\\Seba\\Desktop\\TPI2\\resultados.csv")); //lee archivo de resultados
        archivoresultados.remove(0);  //remueve la linea 0 del archivo
        ArrayList<Partido> partidosRonda = new ArrayList<Partido>(); //array para guardar los partidos

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
            partidosRonda.add(partido);  //agrega el partido al array
        }

        Ronda ronda = new Ronda();
        ronda.setNro(archivoresultados.get(1).split(";")[0]);
        ronda.setPartidos(partidosRonda);

            ArrayList<Pronostico> totalresultados = new ArrayList<>();
            ArrayList<String> totalparticipantes = new ArrayList<String>();

            int k = 0;
            while (rs.next()) {

                String participante = rs.getString("participante");
                if (!totalparticipantes.contains(participante)) {
                    totalparticipantes.add(participante);}


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
                    Pronostico pronostico = new Pronostico();
                    pronostico.setResultado(resultadoPronostico);
                    totalresultados.add(pronostico);
                }


            System.out.println("ronda Nª : " + ronda.getNro());
            System.out.println("************");

            Pronostico pronostico = new Pronostico();
            for (int i = 0; i <partidosRonda.size() ; i++) {
            pronostico.setPartido(partidosRonda.get(i));
            pronostico.setEquipo(partidosRonda.get(i).getEquipo1());
            pronostico.setResultado(totalresultados.get(i).getResultado());
            }

            int puntaje = 0 ;
            for(Pronostico total:totalresultados){
            puntaje += pronostico.puntos();}

            for (Partido partido : partidosRonda) {
                System.out.println(partido.getEquipo1().getNombre() + " vs " + partido.getEquipo2().getNombre());
                System.out.println(partido.getResultadoequipo1() + " - " + partido.getResultadoequipo2());
            }
            System.out.println("**********");
            System.out.println("participante: " + totalparticipantes.get(0));
            System.out.println("puntaje total: " + puntaje);

            conn.close();
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
    }
}