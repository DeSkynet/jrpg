package servidor;

import java.net.Socket;

import dao.DAO;
import dao.JugadorDAO;

public class MainServidor {
    public static void main(String[] args) {
        Socket socket = null;
        Servidor servidor = null;
        int maximoConexiones = 1020;
        DAO daoJugador;

        servidor = new Servidor(maximoConexiones);
        daoJugador = new JugadorDAO();

        while (true) {
            socket = servidor.aceptarConexion();

            if (socket != null) //pregunta esto por si el servidor esta lleno.
                new HiloServidor(socket, servidor.getSala(), servidor.isLogIn(), daoJugador).start(); //Crea el hilo por cada conexion y lo pone a andar
            else{
            	servidor.pararServidor(); //me parece que para el servidor si hay mas personas de las que puede conectadas.
            }
        }
    }
}
