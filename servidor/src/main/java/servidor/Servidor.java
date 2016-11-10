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
            System.out.println("No se puede conectar desde el puerto elegido, cerrando Servidor...");
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
            System.out.println("Error al aceptar conexiones, Cerrando el Servidor...");
            System.exit(1);
        }
        
//        String usuarioJson;
//       try {
//		DataInputStream dato = new DataInputStream(cliente.getInputStream());
//			if((usuarioJson = dato.readLine()) != null){
//				//DESEREALIZO el Log in
//				Gson gson = new Gson();
//			    Mensaje jugadorRecuperado = gson.fromJson(usuarioJson, Mensaje.class);
//				this.tipoMensaje=jugadorRecuperado.getTipoMensaje();
//				//DIFERENCIO ENTRE PRIMERA VEZ Y LOGUIN, PARA ALMACENAR EN BASE DE DATOS Y/O iniciar seccion.
//				MensajeLogIn jugador=(MensajeLogIn) jugadorRecuperado.getObjeto();
//				if(this.tipoMensaje.equals("MensajeLogIn")){
//					//Iniciar Seccion
//					this.logIn=true;
//					//SE FIJA EN BD LOGIN SI EXISTE, en caso de no existir manda mensaje de error al cliente.. sino manda el personaje desde la tabla BD.
//					
//					
//				}
//				else {
//					this.logIn=false;
//					//Se fija en la base de dato si ya existe. Si no existe lo AGrego a la base de datos.
//					
//				}
//				
////				if(mapSalas.containsKey(tipoMensaje) ==false)
////					mapSalas.put(tipoMensaje, new ArrayList<Socket>() );
////				mapSalas.get(tipoMensaje).add(cliente); 
//
////				System.out.println("El Usuario "+   +", NRO " + cantActualClientes
////		                +" ingreso a la sala "+ tipoMensaje +" y fue aceptado correctamente.");
//			}	
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

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
