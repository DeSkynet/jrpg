package servidor;


import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import dao.DAO;
import mensajes.*;
import personaje.Humano;
import personaje.castas.Hechicero;

public class HiloServidor extends Thread {
	
    private Socket socket;
    private String nombreMapa;
    private boolean isLogIn;
    private DAO jugador;
    
    public HiloServidor(Socket socket, String nombreMapa, boolean isLogIn, DAO jugador) {
        super("ThreadServer");
        this.socket = socket;
        this.nombreMapa=nombreMapa;
        this.isLogIn=isLogIn;
        this.jugador = jugador;
    }


    @SuppressWarnings({"deprecation", "unchecked"})
	public void run() {

        DataInputStream data;
        Iterator<Socket> iterador;
        String mensaje = null;
        
        if(isLogIn){
        	//ENVIO EL PERSONAJE DE LA BD AL CLIENTE.
        }       
        

        try {
            do {
                if (mensaje != null) {
              
                	final Gson gson = new Gson();
                    final Mensaje mensajeResivido = gson.fromJson(mensaje, Mensaje.class);
                	//ESTE ES EL MENSAJE QUE RECIBE EL SERVIDOR, ACA PRIMERO HAY QUE PREGUNTAR QUE TIPO DE MENSAJE ES Y LUEGO CASTIAR EL OBJECT.
                    String tipo=mensajeResivido.getTipoMensaje();
                    
                    switch (tipo) {
                    	case "Registro":
                    		MensajeLogIn reg = gson.fromJson(mensajeResivido.getObjeto().toString(), MensajeLogIn.class);
                    		
                    		try {
                    			if(!jugador.buscar(reg.getUsuario())) {
									jugador.insertar(reg.getUsuario(), reg.getContraseña(), new Humano(new Hechicero(), " "));
									envioConfirmacion(socket, true);
                    			}
                    			else envioConfirmacion(socket, false);
							} catch (SQLException e1) {
								e1.printStackTrace();
								envioConfirmacion(socket, false);
							}
                    		break;
	                    case "Movimiento":
		                    MensajePosicion movi=gson.fromJson(mensajeResivido.getObjeto().toString(), MensajePosicion.class);
	//	                    this.leerMovimiento(movi.getUsuario(),movi.getCordenadaX(),movi.getCordenadaY());	//Actualizo la Bd
	//	                    this.distribuirMovimiento(movi.getUsuario(),movi.getCordenadaX(),movi.getCordenadaY());
		                    break;
	
	                    case "MensajeLogIn":
	                    	
	                    	MensajeLogIn nuevo=gson.fromJson(mensajeResivido.getObjeto().toString(), MensajeLogIn.class);

								try {
									if(!jugador.buscar(nuevo.getUsuario())) {
										///EJEMPLO HARDCORE ----> SUJETA A CAMBIOS OBLIGADOS
										
										envioConfirmacion(socket, false);
									}
									else{
										String registro=jugador.seleccionarUsuario(nuevo.getUsuario());
										String []datos=registro.split(" ");
										if(datos[1].equals(nuevo.getContraseña()))
											envioConfirmacion(socket, true);
										else envioConfirmacion(socket, false);
										
									}

								} catch (SQLException e) {
									e.printStackTrace();
								}
	                    	break;
	                    	
	                    case "MensajeEleccionPersonaje":
	                    	
	                    	MensajeEleccionPersonaje persona=gson.fromJson(mensajeResivido.getObjeto().toString(), MensajeEleccionPersonaje.class);
	//                    	cargarNuevoJugadorALaBD(nuevo.getUsuario(),nuevo.getContraseña());
	//                    	envioElPersonaje(this.socket);
	                    	
	                    	break;
	                    	                   
	                    case "MensajeEleccionTerreno":
	                    	MensajeEleccionTerreno terreno=gson.fromJson(mensajeResivido.getObjeto().toString(), MensajeEleccionTerreno.class);
	//                    	cambioEnBD(terreno.getUsuario(),terreno.getMapa());
	                    	
	                    	break;
	                    	
	                    case "MensajeAtaque":
	                    	MensajeAtaque ataque=gson.fromJson(mensajeResivido.getObjeto().toString(), MensajeAtaque.class);
	                    	//FALTA
	                    	break;
	                    default: System.out.println ("El tipo especificado no es un mensaje.."); break;

                }
                   
                }
                // indico que el flujo de informacion provenga del usuario de
                // este hilo.
                data = new DataInputStream(socket.getInputStream());

            } while ((mensaje = data.readLine()) != null);

            Servidor.cantActualClientes--;
//            map.get(nombreMapa).remove(socket);	//No Existe mas el map..
            System.out.println("Un cliente se ha desconectado.");
        } catch (IOException e) {
            try {
                Servidor.cantActualClientes--;
//                map.get(nombreMapa).remove(socket);	//Soluciona problema de parar servidor.
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.out.println("La conexion ha finalizado.");
        }
    }
    
    
    private void envioConfirmacion(Socket socket, boolean confirmacion) {
    	Mensaje mensaje=new Mensaje("MensajeConfirmacion",confirmacion);
    	Gson gson = new Gson();
		String mensajeParaEnviar = gson.toJson(mensaje);

    	PrintStream ps;
    	
    	try {
			ps = new PrintStream(socket.getOutputStream());
			ps.println(mensajeParaEnviar);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

////DISTRIBUYE A TODOS LOS JUGADORES DE UN PLANO ACTIVOS, LA NUEVA COORDENADA DE X e Y.
//	private void distribuirMovimiento(String usuario, int cordX,int cordY) {
//       //Pido a la BD todos los que esten EN this.mapaActual Y Esten ACTIVOS.
//    	//CREO UN INTERADOR Y DE A UNO VOY HACIENDO EL WHILE.
//        while (iterador.hasNext()) {
//            Socket cliente = iterador.next(); //le pido un cliente de la coleccion.
//            try {
//
//                // si el socket extraido es distinto al socket del
//                // hilo
//                // se enviara el msg a todos los usuarios de la
//                // coleccion menos el que envio dicho msg.
//                if (!cliente.equals(socket)) {
//                    PrintStream ps = new PrintStream(
//                            cliente.getOutputStream());                              
//                    
//                    ps.println(mensaje);// envia el mensaje al
//                                    // correspondiente socket.
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//	}
}
