package cliente;


import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import com.google.gson.Gson;

import mensajes.Mensaje;
import mensajes.MensajePosicion;
import mensajes.MensajePosicionOtroPersonaje;
import pantalla.Juego;

public class HiloCliente extends Thread {
    private Socket socket;
    private Cliente cliente;
    private Juego juego;

    public HiloCliente(Cliente cliente, Socket socket, Juego juego) {
        super("ThreadCliente");
        this.cliente=cliente;
        this.socket = socket;
        this.juego=juego;
    }


	@SuppressWarnings("deprecation")
	public void run() {
        DataInputStream datos;
        String mensaje = null;
        Gson gson;
		Mensaje mensajeRecuperado;
		
        try {
        	datos = new DataInputStream(socket.getInputStream()); //le digo que tiene que leer del Socket
	    			
        	while ((mensaje = datos.readLine()) != null){
            	//DESEREALIZO EL MENSAJE DE JSON
        		gson = new Gson();
        		mensajeRecuperado = gson.fromJson(mensaje, Mensaje.class);
        		if(mensajeRecuperado.getTipoMensaje().equals("MensajePosicionOtroPersonaje")){
        			
        		}
        		
        		switch (mensajeRecuperado.getTipoMensaje()) {
					case "MensajePosicionOtroPersonaje":
						MensajePosicionOtroPersonaje movi=gson.fromJson(mensajeRecuperado.getObjeto().toString(), MensajePosicionOtroPersonaje.class);
						cargoOActualizoOtroJugadorAMiMapa(movi);
					break;
				
					case "MensajequitarOtroPersonaje":
						//MensajePosicionOtroPersonaje men=gson.fromJson(mensajeRecuperado.getObjeto().toString(), MensajePosicionOtroPersonaje.class);
						 String personajeAQuitar=(String) mensajeRecuperado.getObjeto();
						 juego.getEstadoJuego().quitarOtroPersonaje(personajeAQuitar);
						 
					break;
				default:
					break;
				}
        		cliente.setMensaje(mensajeRecuperado);
        		cliente.setNuevoMensaje(true);
        		
        		datos = new DataInputStream(socket.getInputStream()); //Se queda escuchando al socket..
        	}
        } catch (IOException e) {
           cerrarSocket();  //si no puede o deja de comunicarse, se cierra el socket.
        }
    }
	
	private void cargoOActualizoOtroJugadorAMiMapa(MensajePosicionOtroPersonaje movi) {
		juego.getEstadoJuego().setOtroPersonaje(movi.getUsuario(),movi.getRaza(),movi.getCoordX(),movi.getCoordY(),movi.getNivel());
//		juego.getEstadoJuego().|
		
	}


	public void cerrarSocket(){
    	try {
            socket.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
