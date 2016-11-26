package cliente;


import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import mensajes.Mensaje;
import mensajes.MensajeColision;
import mensajes.MensajePosicion;
import mensajes.MensajePosicionOtroPersonaje;
import pantalla.Juego;
import ventana.VentanaBatalla;

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
					case "MensajeColicion" :
						MensajeColision colision=gson.fromJson(mensajeRecuperado.getObjeto().toString(), MensajeColision.class);

						//						int seleccion = JOptionPane.showOptionDialog(
//								   null,
//								   "Desea unirse a la alianza?", 
//								   "Selector de opciones",
//								   JOptionPane.YES_NO_CANCEL_OPTION,
//								   JOptionPane.QUESTION_MESSAGE,
//								   null,    // null para icono por defecto.
//								   new Object[] { "Aliarme", "Batallar" },   // null para YES, NO y CANCEL
//								   "Uir");
//
//						if (seleccion == -1){
//							int resp=JOptionPane.showConfirmDialog(null, "Esta seguro que desea Uir? Tu salud será 1 y te quedaras sin aliados.");
//							if(resp==0 || resp==-1 || resp==2){
//								//BAJAR SALUD
//							}
//							else{
//								new VentanaBatalla();
//							}
//						}else if (seleccion == 1) {
//							new VentanaBatalla();
//						}else if(seleccion ==0){
//							//SE alia.
//						}
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
