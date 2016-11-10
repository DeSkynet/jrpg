package cliente;

import java.awt.EventQueue;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import ventana.VentanaPrincipal;

public class MainCliente {

    public static void main(String args[]) throws IOException {
    	Scanner scCliente;
        Cliente cliente;
        String usuario;
        String contraseña;
        
        cliente = new Cliente("nombre");
        
       HiloCliente hiloCliente = new HiloCliente(cliente.getSocket());
       hiloCliente.start();
        VentanaPrincipal principal = new VentanaPrincipal(cliente);
        principal.setVisible(true);
      
        
        

        
//        ///ESTA MUYYYY PRECARIOO! HAY QUE MODIFICAR
//        EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaPrincipal frame = new VentanaPrincipal();
//					frame.setVisible(true);
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//        
//        cliente = new Cliente("nombre");        
//        
//        HiloCliente threadCliente = new HiloCliente(cliente.getSocket()); //creo el hilo Cliente y le mando el socket. Espera un mensaje.
//        threadCliente.start(); //inicializo el hilo, ejecuta el run.
////        cliente.elegirPersonaje(raza, casta);
////        cliente.eligeMapa();  //le dice al servidor en que sala quiere estar.
//        
////        cliente.enviarMensaje();
//        cliente.cerrarCliente();
    }
}
