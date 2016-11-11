package servidor;

import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.DAO;
import dao.DAOPERSONAJE;
import dao.JugadorDAO;
import dao.PersonajeDAO;

public class MainServidor {
    public static void main(String[] args) {
        Socket socket = null;
        Servidor servidor = null;
        int maximoConexiones = 1020;
        JugadorDAO daoJugador;
        PersonajeDAO daoPersonaje;
//        Connection conexion = null;
//    	PreparedStatement statement=null;
//    	final String PATH_CONNECTION = "jdbc:sqlite:src/main/resources/bd/PruebaBD.bd";
//    	
        servidor = new Servidor(maximoConexiones);
        
        
        daoJugador = new JugadorDAO();
//        conexion=daoJugador.getConexion();
        daoPersonaje = new PersonajeDAO();
        		
        while (true) {
            socket = servidor.aceptarConexion();

            if (socket != null) //pregunta esto por si el servidor esta lleno.
                new HiloServidor(socket, servidor.getSala(), servidor.isLogIn(), daoJugador, daoPersonaje).start(); //Crea el hilo por cada conexion y lo pone a andar
            else{
            	servidor.pararServidor(); //me parece que para el servidor si hay mas personas de las que puede conectadas.
            }
        }
    }
}
