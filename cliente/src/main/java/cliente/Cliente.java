package cliente;


import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import com.google.gson.Gson;
//import com.google.gson.JsonObject;

import mensajes.*;

public class Cliente {
	private static final String PATH_ARCHIVO_CONFIG = "config/conexion.config";
	
	private Socket cliente;
	private String nombre = null;
    private String host;
    private int puerto;
    private String mapaActual;
    
    //CONSTRUCTOR DE CLIENTE
	public Cliente(String nombre) {
		this.nombre = nombre;
		leerArchivoConfig();
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
			//DEBERIA abrir una ventana, mostrando que hay un error en el archivo de configuracion.
				System.err.println(e.getLocalizedMessage());
		} finally {
			entrada.close();
		}
	}
		
	///ENVIAR OBJETO para logueo por ejemplo
    public void enviarMensaje(String tipoMensaje,Object obj) {
    	
    	Mensaje mensaje=new Mensaje(tipoMensaje,obj);
    	Gson gson = new Gson();
		String mensajeParaEnviar = gson.toJson(mensaje);

    	PrintStream ps;
    	
    	try {
			ps = new PrintStream(this.cliente.getOutputStream());
			ps.println(mensajeParaEnviar);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
  ///RECIVE Mensaje
    public Mensaje ReciveMensaje() {

            DataInputStream datos;
            String mensaje = null;
            Gson gson;
    		Mensaje mensajeRecuperado;
    		
            try {
            	datos = new DataInputStream(cliente.getInputStream()); //le digo que tiene que leer del Socket //Se queda escuchando al socket..
    	    			
        		mensaje = datos.readLine();
            	//DESEREALIZO EL MENSAJE DE JSON
        		gson = new Gson();
        		mensajeRecuperado = gson.fromJson(mensaje, Mensaje.class);
        		return mensajeRecuperado;
            } catch (IOException e) {
            	e.printStackTrace();
            }
            return null;
    }
    
    
    public boolean registrarCliente(String usuario, String pass) {
    	MensajeLogIn logIn=new MensajeLogIn(usuario,pass);
    	enviarMensaje("MensajeLogInNuevo", logIn);
    	return esperaConfirmacion();
    }
    

    public boolean iniciarSesionCliente(String usuario, String pass) {
    	MensajeLogIn logIn=new MensajeLogIn(usuario,pass);
    	enviarMensaje("MensajeLogIn", logIn);
    	return esperaConfirmacion();			
    }
    
   //Espera confirmacion del servidor 
   private boolean esperaConfirmacion() {
	   Mensaje mensaje=ReciveMensaje();
	   if(mensaje!=null && mensaje.getTipoMensaje().equals("MensajeConfirmacion")){
		   Gson gson = new Gson();
		   boolean confirmacion=(boolean) mensaje.getObjeto();
		   if(confirmacion==true)
			   return true;
	   }   
		return false;
	}


 public void elegirPersonaje(String raza, String casta) {
    	MensajeEleccionPersonaje eleccionPersonaje = new MensajeEleccionPersonaje(this.nombre,raza,casta);
    	enviarMensaje("MensajeEleccionPersonaje", eleccionPersonaje);
    }
    
    public void posicionDelPersonaje(int coordX, int coordY) {
    	MensajePosicion posicionPersonaje = new MensajePosicion(this.nombre,coordX,coordY);
    	enviarMensaje("MensajePosicion", posicionPersonaje);
    }
    
    public void elegirMapa(String mapa) {
    	this.mapaActual=mapa;
		MensajeEleccionTerreno terreno = new MensajeEleccionTerreno(this.nombre,this.mapaActual);
		enviarMensaje("MensajeEleccionTerreno", terreno);
	}
    
    
    public void Ataque(String ataque, String atacado) {
		MensajeAtaque ataquePersonaje = new MensajeAtaque(this.nombre,ataque,atacado);
		enviarMensaje("MensajeAtaque", ataquePersonaje);
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
    
    public void eligeMapa() {
		 try {
			PrintStream psSalida = new PrintStream(cliente.getOutputStream()); //abro el canal
				
			//Abro una ventana y Asigno el mapa a this.mapa.
		
		
	       	//SEREALIZO EL MENSAJE CON GSON.
	           MensajeEleccionTerreno mensajeJson=new MensajeEleccionTerreno(nombre,this.mapaActual);
	           Gson gson = new Gson();
	           String mensajeParaEnviar = gson.toJson(mensajeJson);
	       	
	           psSalida.println(mensajeParaEnviar); //MANDO el mensaje JSON por el socket.
	
			} catch (IOException e) {
	
				e.printStackTrace();
			}
		
	}
    
    public Socket getSocket() {
		return cliente;
	}
}
