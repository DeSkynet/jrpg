package servidor;

import java.io.IOException;
import java.net.Socket;
import dao.DAOJUGADOR;
import dao.DAOPERSONAJE;
import dao.JugadorDAO;
import dao.PersonajeDAO;

public class MainServidor {
	
    @SuppressWarnings("rawtypes")
	public static void main(String[] args) throws IOException {
        Socket socket = null;
        Servidor servidor = null;
        int maximoConexiones = 1020;
        DAOJUGADOR daoJugador;
        DAOPERSONAJE daoPersonaje;
        
        servidor = new Servidor(maximoConexiones);
 
        daoJugador = new JugadorDAO();
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
