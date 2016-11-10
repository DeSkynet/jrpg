package servidor;

import java.net.Socket;

public class MainServidor {
    public static void main(String[] args) {
        Socket socket = null;
        Servidor servidor = null;
        int maximoConexiones = 1020;

        servidor = new Servidor(maximoConexiones);

        while (true) {
            socket = servidor.aceptarConexion();

            if (socket != null) //pregunta esto por si el servidor esta lleno.
                new HiloServidor(socket, servidor.getSala(), servidor.isLogIn()) .start(); //Crea el hilo por cada conexion y lo pone a andar
            else{
            	servidor.pararServidor(); //me parece que para el servidor si hay mas personas de las que puede conectadas.
            }
        }
    }
}
