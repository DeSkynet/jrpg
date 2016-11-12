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

import mensajes.Mensaje;
import mensajes.MensajeLogIn;
import mensajes.MensajeNuevoJugador;

public class Servidor {

    private ServerSocket servidor;
    private Socket cliente;
    public static int cantActualClientes;
    private Map <String, ArrayList<Socket>> mapSalas; //Map de Salas
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

    public Servidor(int maxConexiones) {

        leerArchivoConfig();
        maxClientes = maxConexiones;

        cantActualClientes = 0;
        mapSalas=new HashMap<String,ArrayList<Socket>>(); //creo el map.

        try {
            servidor = new ServerSocket(puerto);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No se puede conectar desde el puerto elegido, cerrando Servidor...");
            System.exit(1);
        }
    }
    
    //LEE ARCHIVO DE CONFIGURACIÓN QUE INDICA EL IP Y EL PUERTO A CONECTAR.
    private void leerArchivoConfig() {
    	Scanner entrada = null;
    	try {
			entrada = new Scanner(new File(PATH_CONFIGURACION));
			
			if(entrada.hasNextLine()) {
				this.IPServidor = entrada.nextLine().substring(3);
				this.puerto = Integer.parseInt(entrada.nextLine().substring(7));
			}
			
		} catch (FileNotFoundException e) {
				System.err.println(e.getLocalizedMessage());
		} finally {
			entrada.close();
		}
    	entrada.close();
    }

    public Map<String, ArrayList<Socket>> getLista() {
        return mapSalas;  //le devuelve el array segun la sala.
    }

    public Socket aceptarConexion() {

        cantActualClientes++;

        try {
            cliente = servidor.accept();	//Se Queda esperando clientes.
            if (cantActualClientes > maxClientes) {
                PrintStream ps = new PrintStream(cliente.getOutputStream()); //para enviar algo al cliente.
                ps.println("Servidor Lleno");
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

	public void pararServidor() {
        try {
            servidor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	private static final String PATH_CONFIGURACION = "src/main/resources/config/conexion.config";
}
