package cliente;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

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
		
//		try {
//			this.cliente = new Socket(host, puerto);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
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
    	
    	try {
			ps = new PrintStream(this.cliente.getOutputStream());
			ps.println(obj);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
    ///PROVISORIO
    public void registrarCliente(String usuario, String pass) {
    	JsonObject nuevoCliente = new JsonObject();
    	
    	nuevoCliente.addProperty("Usuario", usuario);
    	nuevoCliente.addProperty("Pass", pass);
    	
    	System.out.println(nuevoCliente);
    	
    	//enviarObjeto(nuevoCliente);
    }
    
    public void elegirPersonaje(String raza, String casta) {
    	JsonObject eleccionPersonaje = new JsonObject();
    	
    	eleccionPersonaje.addProperty("Usuario", this.nombre);
    	eleccionPersonaje.addProperty("Raza", raza);
    	eleccionPersonaje.addProperty("Casta", casta);
    	
    	System.out.println(eleccionPersonaje);
    	//enviarObjeto(eleccionPersonaje);
    }
    
    public void posicionDelPersonaje(int coordX, int coordY) {
    	JsonObject posicionPersonaje = new JsonObject();
    	
    	posicionPersonaje.addProperty("Usuario", this.nombre);
    	posicionPersonaje.addProperty("CoordenadaX", coordX);
    	posicionPersonaje.addProperty("CoordenadaY", coordY);
    	
    	//enviarObjeto(posicionPersoanje);
    }
    
    public void Ataque(String ataque, String atacado) {
		JsonObject ataquePersonaje = new JsonObject();
		
		ataquePersonaje.addProperty("Usuario", this.nombre);
		ataquePersonaje.addProperty("Ataque", ataque);
		ataquePersonaje.addProperty("Atacado", atacado);
		
//		enviarObjeto(ataquePersonaje);
	}
    
    
    
    public void cerrarCliente() {
        try {
            cliente.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
		Cliente c = new Cliente("usuario");
		c.registrarCliente("ssss", "ffff");
	}
}
