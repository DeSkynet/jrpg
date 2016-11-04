package cliente;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import com.google.gson.Gson;

import mensaje.Mensaje;

public class Cliente {
	private static final String PATH_ARCHIVO_CONFIG = "config/conexion.config";
	
	private Socket cliente;
    private String nombre = null;
    private String host;
    private int puerto;
    
    //CONSTRUCTOR DE CLIENTE
	public Cliente(String nombre) {
		this.nombre = nombre;
		
		leerArchivoConfig();
		
		try {
			this.cliente = new Socket(host, puerto);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//LEE DEL ARCHIVO DE CONFIGURACION 
	private void leerArchivoConfig() {
		Scanner entrada = null;
    	try {
			entrada = new Scanner(new File(PATH_ARCHIVO_CONFIG));
			
			if(entrada.hasNextLine()) {
				this.host = entrada.nextLine().substring(3);
				this.puerto = Integer.parseInt(entrada.nextLine().substring(7));
			}
			
		} catch (FileNotFoundException e) {
				System.err.println(e.getLocalizedMessage());
		} finally {
			entrada.close();
		}
    	entrada.close();
	}
		
	///enviar mensaje 
    public void enviarMensaje(String mjs) {
    	PrintStream ps;
    	Mensaje mensajeJson;
    	Gson gson;
    	
        try {
            //Se lee desde el host del usuario y dirige el flujo o información al server
            ps = new PrintStream(this.cliente.getOutputStream()); //le digo a donde lo tiene que mandar.. cliente es el socket.
            
        	//SEREALIZO EL MENSAJE CON GSON.
        	mensajeJson=new Mensaje(this.nombre,mjs);
        	gson = new Gson();
        	
            ps.println(gson.toJson(mensajeJson)); //MANDO el mensaje JSON por el socket.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	///ENVIAR OBJETO para logueo por ejemplo
    public void enviarObjeto(Object obj) {
    	PrintStream ps;
    	Gson gson;
    	
    	try {
			ps = new PrintStream(this.cliente.getOutputStream());
			gson = new Gson();
			ps.println(gson.toJson(obj));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
