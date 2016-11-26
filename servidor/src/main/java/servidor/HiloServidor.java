package servidor;


import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import dao.DAOJUGADOR;
import dao.DAOPERSONAJE;
import log.Log;
import mensajes.*;
import personaje.Alien;
import personaje.Humano;
import personaje.Personaje;
import personaje.Robot;
import personaje.SuperHeroe;
import personaje.castas.Hechicero;
import personaje.castas.Ladron;
import personaje.castas.Luchador;

public class HiloServidor extends Thread {
	
    private Socket socket;
	private String nombreMapa=null;
    private boolean isLogIn;
	private DAOJUGADOR jugador;
    private DAOPERSONAJE personaje;
    private String usuario=null;
    HashMap<String, Personaje> jugadores;
    HashMap<String, ArrayList<Socket>> jugadoresEnMapa; 	///jugadores por mapa
    HashMap<Socket, String> jugadoresConectados;			///jugadores online al juego...LO DEJE POR LAS DUDAS, SACAR SI ES UN POTUS...

    
    public HiloServidor(Socket socket,HashMap<String, ArrayList<Socket>> jugadoresEnMapa, HashMap<Socket, String> jugadoresConectados,HashMap<String, Personaje> jugadores, DAOJUGADOR jugador, DAOPERSONAJE personaje) {
        super("ThreadServer");
        this.socket = socket;
        this.jugador = jugador;
        this.personaje=personaje;        
        this.jugadoresEnMapa = jugadoresEnMapa;
        this.jugadoresConectados = jugadoresConectados;
        this.jugadores = jugadores;
    }


    @SuppressWarnings({"deprecation", "unchecked"})
	public void run() {

        DataInputStream data;
        Iterator<Socket> iterador;
        String mensaje = null;
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
									jugador.insertar(reg.getUsuario(), reg.getContraseña(), false);
									envioConfirmacion(socket, true);
                    			}
                    			else envioConfirmacion(socket, false);
							} catch (SQLException e1) {
								Log.crearLog("Error: No se pudo realizar operacion en la BBDD." + e1.getMessage());
								envioConfirmacion(socket, false);
							}
                    		break;
	                    case "MensajePosicion":
		                    MensajePosicion movi=gson.fromJson(mensajeResivido.getObjeto().toString(), MensajePosicion.class);
		                    try {
		                    	personaje.actualizarCordenadasXY(movi.getUsuario(),movi.getCordenadaX(),movi.getCordenadaY());	//Actualizo la Bd
							} catch (Exception e) {
								Log.crearLog("Error: No se pudo actualizar posición." + e.getMessage());
							}
		                    
		                    this.distribuirMovimiento(movi.getUsuario(),movi.getCordenadaX(),movi.getCordenadaY());
		                    break;
	
	                    case "MensajeLogIn":
	                    	
	                    	MensajeLogIn nuevo=gson.fromJson(mensajeResivido.getObjeto().toString(), MensajeLogIn.class);

								try {
									if(!jugador.buscar(nuevo.getUsuario())) {
										envioConfirmacion(socket, false);
									}
									else{
										String registro=jugador.seleccionarUsuario(nuevo.getUsuario());
										String []datos=registro.split(" ");
										if(datos[1].equals(nuevo.getContraseña())){
											//INSERTO LOGICA DE PREGUNTAR SI LA RAZA Y LA CASTA ESTAN INICIALIZADAS,
//											jugadoresEnMapa.put(this.socket, this.usuario);	//agrego al Hash de jugadores Conectado al jugador...no voy a agregar a un perro :).
											this.usuario=nuevo.getUsuario();
											jugador.actualizar(usuario, true);
											if(!personaje.buscar(nuevo.getUsuario())){
												envioMensaje(socket,"EleccionPersonaje");
											}else{
												envioConfirmacion(socket, true);
												envioPersonaje(new Mensaje("EnvioPersonaje", reciboPersonajeDeBD(usuario)));
												agregoPersonajeAHash(this.usuario,socket);
											}
										}
										else envioConfirmacion(socket, false);
									}

								} catch (SQLException e) {
									Log.crearLog("Error: No se pudo realizar operacion en la BBDD." + e.getMessage());
								}
	                    	break;
	                    	
	                    case "MensajeEleccionPersonaje":
	                    	
	                    	MensajeEleccionPersonaje persona=gson.fromJson(mensajeResivido.getObjeto().toString(), MensajeEleccionPersonaje.class);
						try {
							jugadores.put(persona.getUsuario(), crearPersonaje(persona.getRaza(), persona.getCasta()));
							personaje.insertar(persona.getUsuario(), jugadores.get(persona.getUsuario()));
						} catch (SQLException e) {
							Log.crearLog("Error: No se pudo realizar operacion en la BBDD." + e.getMessage());
						}
	                    	break;
	                    	                   
	                    case "MensajeEleccionTerreno":
	                    	MensajeEleccionTerreno terreno=gson.fromJson(mensajeResivido.getObjeto().toString(), MensajeEleccionTerreno.class);
	                    	if(terreno.getMapa().equals("Campo") || terreno.getMapa().equals("Playa") || terreno.getMapa().equals("Desierto")){
	                    		try {
	                    			personaje.actualizarMapa(terreno.getUsuario(), terreno.getMapa());
	                    			agregoPersonajeAHash(this.usuario,socket,terreno.getMapa());
	                    			envioPersonaje(new Mensaje("EnvioPersonaje", reciboPersonajeDeBD(usuario)));
								} catch (Exception e) {
									Log.crearLog("Error: Seleccion de mapa erronea." + e.getMessage());
								}
	                    	}

	                    	break;
	                    case "MensajeColicion":
	                    	MensajeColision colision=gson.fromJson(mensajeResivido.getObjeto().toString(), MensajeColision.class);
	                    	distribuirColicion(new MensajeColision(colision.getUsuarioOrigen(), colision.getUsuarioDestino()));
	                    	
	                    	break;
	                    case "MensajeAtaque":
	                    	MensajeAtaque ataque=gson.fromJson(mensajeResivido.getObjeto().toString(), MensajeAtaque.class);
	                    	//FALTA
	                    	break;
	                    	
	                    case "MensajeIncrementoExperiencia":
	                    	MensajeIncrementoExperiencia incExperiencia=gson.fromJson(mensajeResivido.getObjeto().toString(), MensajeIncrementoExperiencia.class);
	                    	try {
								String dato[]=personaje.seleccionarUsuario(incExperiencia.getUsuario()).split(" ");
								//DEBERIA FIJARSE SI PUEDE SUBIR DE NIVEL.
								personaje.actualizarExperiencia(incExperiencia.getUsuario(), Integer.parseInt(dato[4])+incExperiencia.getIncremento());
							} catch (Exception e) {
								Log.crearLog("Error: Actualización de experiencia." + e.getMessage());
							}
	                    		
	                    	break;
	                    default: Log.crearLog("Error: Llego un mensaje no compatible con los tipos de mensajes."); break;

                    }
                   
                }
                // indico que el flujo de informacion provenga del usuario de
                // este hilo.
                data = new DataInputStream(socket.getInputStream());

            } while ((mensaje = data.readLine()) != null);

            Servidor.cantActualClientes--;
            jugadoresEnMapa.get(nombreMapa).remove(socket);	//No Existe mas EN el map..
            distribuirSalgo(this.usuario);
        } catch (IOException e) {
            try {
                Servidor.cantActualClientes--;
                distribuirSalgo(this.usuario);
                jugadoresEnMapa.get(nombreMapa).remove(socket);	//Soluciona problema de parar servidor.
                socket.close();
            } catch (IOException e1) {
    			try {
					Log.crearLog("Error: Error en operación de Hilo Servidor." + e.getMessage());
				} catch (IOException e2) {
					e2.printStackTrace();
				}
            }
            finally {
            	 if(this.jugador!=null){
            		 try{
            			 Log.crearLog("Un cliente ha finalizado su conexion.");
           			 jugador.actualizar(this.usuario, false);
            		 } catch (SQLException | IOException e2) {
							e2.printStackTrace();
						}
            	 }
			}
        }
    }
    

	private void agregoPersonajeAHash(String usuario2, Socket socket2) throws IOException {
    	String mapa=null;
    	try {
    		String[] perso=personaje.seleccionarUsuario(usuario2).split(" ");
    		mapa=perso[12];

		} catch (Exception e) {
			Log.crearLog("Error: No se pudo agregar correctamente el Personaje al hash.");
		}
    	
    	if(mapa!=null){
    		if(jugadoresEnMapa.containsKey(mapa) ==false)
    			jugadoresEnMapa.put(mapa, new ArrayList<Socket>() );
    		jugadoresEnMapa.get(mapa).add(socket2); 
    	}
	}
    
    private void agregoPersonajeAHash(String usuario2, Socket socket2, String mapa) throws IOException {
    	if(jugadoresEnMapa.containsKey(mapa) ==false)
			jugadoresEnMapa.put(mapa, new ArrayList<Socket>() );
		jugadoresEnMapa.get(mapa).add(socket2); 
	}


	private Object reciboPersonajeDeBD(String usuario2) throws IOException {
    	try {
			String[] perso=personaje.seleccionarUsuario(usuario2).split(" ");
			this.nombreMapa=perso[12];
			MensajePersonaje per=new MensajePersonaje(perso[0], Integer.parseInt(perso[1]), Integer.parseInt(perso[2]), Integer.parseInt(perso[3]), Integer.parseInt(perso[4]), Integer.parseInt(perso[5]), Integer.parseInt(perso[6]), Integer.parseInt(perso[7]), Integer.parseInt(perso[8]), Integer.parseInt(perso[9]), perso[10], perso[11], perso[12]);
			return per;
			
		} catch (Exception e) {
			Log.crearLog("Error: No se pudo separar correctamente el Personaje." + e.getMessage());
		}
		return null;
	}


	private void envioMensaje(Socket socket2, String string) throws IOException {
    	Mensaje mensaje=new Mensaje(string,true);
    	Gson gson = new Gson();
		String mensajeParaEnviar = gson.toJson(mensaje);
		PrintStream ps;
    	
    	try {
			ps = new PrintStream(socket.getOutputStream());
			ps.println(mensajeParaEnviar);
			
		} catch (IOException e) {
			Log.crearLog("Error: No se pudo enviar correctamente el mensaje." + e.getMessage());
		}
		
	}
	
	private void envioMensajee(Socket socket2, Mensaje men) throws IOException {
    	Gson gson = new Gson();
		String mensajeParaEnviar = gson.toJson(men);
		PrintStream ps;
    	
    	try {
			ps = new PrintStream(socket2.getOutputStream());
			ps.println(mensajeParaEnviar);
			
		} catch (IOException e) {
			Log.crearLog("Error: No se pudo enviar correctamente el mensaje." + e.getMessage());
		}
		
	}

    private void envioPersonaje(Mensaje men) throws IOException {
    	Gson gson = new Gson();
		String mensajeParaEnviar = gson.toJson(men);
		PrintStream ps;
    	
    	try {
			ps = new PrintStream(socket.getOutputStream());
			ps.println(mensajeParaEnviar);
			
		} catch (IOException e) {
			Log.crearLog("Error: No se pudo enviar correctamente el Personaje." + e.getMessage());
		}
		
	}

	private void envioConfirmacion(Socket socket, boolean confirmacion) throws IOException {
    	Mensaje mensaje=new Mensaje("MensajeConfirmacion",confirmacion);
    	Gson gson = new Gson();
		String mensajeParaEnviar = gson.toJson(mensaje);

    	PrintStream ps;
    	
    	try {
			ps = new PrintStream(socket.getOutputStream());
			ps.println(mensajeParaEnviar);
			
		} catch (IOException e) {
			Log.crearLog("Error: No se pudo enviar correctamente el mensaje confirmación." + e.getMessage());
		}
		
	}

	
	private void distribuirSalgo(String usuario2) throws IOException {
		ArrayList<Socket> lista = jugadoresEnMapa.get(this.nombreMapa);
		Iterator<Socket> iterador = lista.iterator();
		
		String[] dato;

		Mensaje mensajeAEnviar = new Mensaje("MensajequitarOtroPersonaje", usuario2);
		Gson gson = new Gson();
		final String mensaje = gson.toJson(mensajeAEnviar, Mensaje.class);
		
		//Pido a la BD todos los que esten EN this.mapaActual Y Esten ACTIVOS.
    	//CREO UN INTERADOR Y DE A UNO VOY HACIENDO EL WHILE.
        while (iterador.hasNext()) {
            Socket cliente = iterador.next(); //le pido un cliente de la coleccion.
            try {

                // si el socket extraido es distinto al socket del
                // hilo
                // se enviara el msg a todos los usuarios de la
                // coleccion menos el que envio dicho msg.
                if (!cliente.equals(this.socket)) {
                    PrintStream ps = new PrintStream(
                            cliente.getOutputStream());                              
                    
                    ps.println(mensaje);// envia el mensaje al
                                    // correspondiente socket.
                }
            } catch (IOException e) {
            	Log.crearLog("No se pudo distribuir un movimiento.");
            }
        }
	}

	
	private void distribuirColicion(MensajeColision men) throws IOException {
		ArrayList<Socket> lista = jugadoresEnMapa.get(this.nombreMapa);
		Iterator<Socket> iterador = lista.iterator();
		
		String[] dato;

		Mensaje mensajeAEnviar = new Mensaje("MensajeColicion", men);
		Gson gson = new Gson();
		final String mensaje = gson.toJson(mensajeAEnviar, Mensaje.class);
		
		//Pido a la BD todos los que esten EN this.mapaActual Y Esten ACTIVOS.
    	//CREO UN INTERADOR Y DE A UNO VOY HACIENDO EL WHILE.
        while (iterador.hasNext()) {
            Socket cliente = iterador.next(); //le pido un cliente de la coleccion.
            try {

                // si el socket extraido es distinto al socket del
                // hilo
                // se enviara el msg a todos los usuarios de la
                // coleccion menos el que envio dicho msg.
                if (!cliente.equals(this.socket)) {
                    PrintStream ps = new PrintStream(
                            cliente.getOutputStream());                              
                    
                    ps.println(mensaje);// envia el mensaje al
                                    // correspondiente socket.
                }
            } catch (IOException e) {
            	Log.crearLog("No se pudo distribuir un movimiento.");
            }
        }
	
	}
	
private Personaje crearPersonaje(String raza, String casta) {
		
		if(raza.equals("Humano"))
			return crearHumano(casta);
		
		if(raza.equals("Alien"))
			return crearAlien(casta);
		
		if(raza.equals("Superheroe"))
			return crearSuerheroe(casta);
		
		if(raza.equals("Robot"))
			return crearRobot(casta);
		
		return null;
	}

	private Personaje crearSuerheroe(String casta) {
		if(casta.equals("Hechicero"))
			return new SuperHeroe(new Hechicero(), "");
		
		if(casta.equals("Luchador"))
			return new SuperHeroe(new Luchador(), "");
		
		if(casta.equals("Ladron"))
			return new SuperHeroe(new Ladron(), "");
		return null;
	}


	private Personaje crearAlien(String casta) {
		if(casta.equals("Hechicero"))
			return new Alien(new Hechicero(), "");
		
		if(casta.equals("Luchador"))
			return new Alien(new Luchador(), "");
		
		if(casta.equals("Ladron"))
			return new Alien(new Ladron(), "");
		return null;
	}


	private Personaje crearHumano(String casta) {
		if(casta.equals("Hechicero"))
			return new Humano(new Hechicero(), "");
		
		if(casta.equals("Luchador"))
			return new Humano(new Luchador(), "");
		
		if(casta.equals("Ladron"))
			return new Humano(new Ladron(), "");
		return null;
	}
	
	private Personaje crearRobot(String casta) {
		if(casta.equals("Hechicero"))
			return new Robot(new Hechicero(), "");
		
		if(casta.equals("Luchador"))
			return new Robot(new Luchador(), "");
		
		if(casta.equals("Ladron"))
			return new Robot(new Ladron(), "");
		return null;
	}

	
	
	// DISTRIBUYE A TODOS LOS JUGADORES DE UN PLANO ACTIVOS, LA NUEVA COORDENADA DE X e Y.
	// TRAE EL ARRAYLIST DE JUGADORES CONECTADOS DE UN MAPA Y  LUEGO LO RECORRE PARA ENVIAR A TODOS LOS JUGADORES ACTIVOS...
		private void distribuirMovimiento(String usuario, int cordX,int cordY) throws IOException {
			ArrayList<Socket> lista = jugadoresEnMapa.get(this.nombreMapa);
			Iterator<Socket> iterador = lista.iterator();
			
			String[] dato;
			try {
				dato = personaje.seleccionarUsuario(usuario).split(" "); //PIDO NIVEL Y RAZA.
				Mensaje mensajeAEnviar = new Mensaje("MensajePosicionOtroPersonaje", new MensajePosicionOtroPersonaje(usuario,dato[11],cordX, cordY,Integer.parseInt(dato[4])));
				Gson gson = new Gson();
				final String mensaje = gson.toJson(mensajeAEnviar, Mensaje.class);
				
				//Pido a la BD todos los que esten EN this.mapaActual Y Esten ACTIVOS.
		    	//CREO UN INTERADOR Y DE A UNO VOY HACIENDO EL WHILE.
		        while (iterador.hasNext()) {
		            Socket cliente = iterador.next(); //le pido un cliente de la coleccion.
		            try {

		                // si el socket extraido es distinto al socket del
		                // hilo
		                // se enviara el msg a todos los usuarios de la
		                // coleccion menos el que envio dicho msg.
		                if (!cliente.equals(this.socket)) {
		                    PrintStream ps = new PrintStream(
		                            cliente.getOutputStream());                              
		                    
		                    ps.println(mensaje);// envia el mensaje al
		                                    // correspondiente socket.
		                }
		            } catch (IOException e) {
		            	Log.crearLog("No se pudo distribuir un movimiento.");
		            }
		        }
			} catch (SQLException e1) {
				Log.crearLog("Error: No se pudo enviar correctamente la distribucion del movimiento.");
			}

		}
}
