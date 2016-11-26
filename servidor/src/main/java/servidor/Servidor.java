package servidor;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import log.Log;
import mensajes.Mensaje;
import mensajes.MensajeLogIn;
import mensajes.MensajeNuevoJugador;
import personaje.Personaje;

public class Servidor {

    private ServerSocket servidor;
    private Socket cliente;
    public static int cantActualClientes;
    private HashMap<String, Personaje> jugadores;
    private HashMap <String, ArrayList<Socket>> jugadoresEnMapa; //HashMap de jugadores en un mapa
    private HashMap<Socket, String> jugadoresConectados;	//HashMap de jugadoresConectados
    private int maxClientes;
    private int puerto;
    private String IPServidor;
    private String tipoMensaje;
    private boolean logIn;

    boolean isLogIn() {
		return logIn;
	}
	public String getIPServidor() {
        return IPServidor;
    }
    public int getPuerto() {
        return puerto;
    }
    
    public String getSala(){
    	return tipoMensaje;
    }

    public Servidor(int maxConexiones) throws IOException {

        leerArchivoConfig();
        maxClientes = maxConexiones;

        cantActualClientes = 0;
        jugadoresEnMapa=new HashMap<String,ArrayList<Socket>>(); //creo el map.
        jugadoresConectados = new HashMap<>();
        jugadores = new HashMap<>();

        try {
            servidor = new ServerSocket(puerto);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No se puede conectar desde el puerto elegido, cerrando Servidor...");
            System.exit(1);
        }
    }
    
    //LEE ARCHIVO DE CONFIGURACIÓN QUE INDICA EL IP Y EL PUERTO A CONECTAR.
    private void leerArchivoConfig() throws IOException {
    	Scanner entrada = null;
    	try {
			entrada = new Scanner(new File(PATH_CONFIGURACION));
			
			if(entrada.hasNextLine()) {
				this.IPServidor = entrada.nextLine().substring(3);
				this.puerto = Integer.parseInt(entrada.nextLine().substring(7));
			}
			
		} catch (FileNotFoundException e) {
				Log.crearLog("Error: En lectura del archivo de Configuración." + e.getMessage());
		} finally {
			entrada.close();
		}
    	entrada.close();
    }

    
    
    public Socket aceptarConexion() {

        cantActualClientes++;

        try {
            cliente = servidor.accept();	//Se Queda esperando clientes.
            if (cantActualClientes > maxClientes) {
                PrintStream ps = new PrintStream(cliente.getOutputStream()); //para enviar algo al cliente.
                ps.println("Lo sentimos, el Servidor se encuentra Lleno");
                Log.crearLog("El Servidor rechazo un cliente.");
                
                cliente.close(); //cierra el socket.
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al aceptar conexiones, Cerrando el Servidor...");
        	System.exit(1);
        }
        
        return cliente; //devuelvo el socket del cliente
    }
    
    public Socket getCliente() {
		return cliente;
	}

	public void pararServidor() throws IOException {
        try {
            servidor.close();
        } catch (IOException e) {
           Log.crearLog("Error: En operación cerrar Servidor." + e.getMessage());
        }
    }
	public HashMap<String, ArrayList<Socket>> getJugadoresEnMapa() {
        return this.jugadoresEnMapa;  //le devuelve el array segun la sala.
    }
    
    public HashMap<Socket, String> getJugadoresConectados() {
        return this.jugadoresConectados;  //le devuelve el array segun la sala.
    }
    
    public HashMap<String, Personaje> getJugadores() {
		return jugadores;
	}
    
    public void setJugadores(HashMap<String, Personaje> jugadores) {
		this.jugadores = jugadores;
	}

    
	private static final String PATH_CONFIGURACION = "config/conexion.config";
}
