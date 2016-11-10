package cliente;


import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import com.google.gson.Gson;

import mensajes.Mensaje;

public class HiloCliente extends Thread {
    private Socket socket;
    private Cliente cliente;


    public HiloCliente(Cliente cliente, Socket socket) {
        super("ThreadCliente");
        this.cliente=cliente;
        this.socket = socket;
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
        		System.out.println(mensajeRecuperado.getTipoMensaje() + " " + mensajeRecuperado.getObjeto());
        		cliente.setMensaje(mensajeRecuperado);
        		cliente.setNuevoMensaje(true);
        		
        		datos = new DataInputStream(socket.getInputStream()); //Se queda escuchando al socket..
        	}
        } catch (IOException e) {
           cerrarSocket();  //si no puede o deja de comunicarse, se cierra el socket.
        }
    }
	
	public void cerrarSocket(){
    	try {
            socket.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
