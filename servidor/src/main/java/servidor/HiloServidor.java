package servidor;


import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Iterator;
import com.google.gson.Gson;
import dao.DAOJUGADOR;
import dao.DAOPERSONAJE;
import log.Log;
import mensajes.*;

public class HiloServidor extends Thread {
	
    private Socket socket;
	private String nombreMapa;
    private boolean isLogIn;
	private DAOJUGADOR jugador;
    private DAOPERSONAJE personaje;
    private String usuario=null;
    
    public HiloServidor(Socket socket, String nombreMapa, boolean isLogIn, DAOJUGADOR jugador, DAOPERSONAJE personaje) {
        super("ThreadServer");
        this.socket = socket;
        this.nombreMapa=nombreMapa;
        this.isLogIn=isLogIn;
        this.jugador = jugador;
        this.personaje=personaje;
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
									jugador.insertar(reg.getUsuario(), reg.getContraseña(), null);
									envioConfirmacion(socket, true);
                    			}
                    			else envioConfirmacion(socket, false);
							} catch (SQLException e1) {
								Log.crearLog("Error: No se pudo realizar operacion en la BBDD." + e1.getMessage());
								envioConfirmacion(socket, false);
							}
                    		break;
	                    case "Movimiento":
		                    MensajePosicion movi=gson.fromJson(mensajeResivido.getObjeto().toString(), MensajePosicion.class);
		                    try {
								personaje.actualizarCordenadasXY(movi.getUsuario(),movi.getCordenadaX(),movi.getCordenadaY());	//Actualizo la Bd
							} catch (Exception e) {
								Log.crearLog("Error: No se pudo actualizar posición." + e.getMessage());
							}
		                    
	//	                    this.distribuirMovimiento(movi.getUsuario(),movi.getCordenadaX(),movi.getCordenadaY());
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
											this.usuario=nuevo.getUsuario();
											jugador.actualizar(usuario, true);
											if(!personaje.buscar(nuevo.getUsuario())){
												envioMensaje(socket,"EleccionPersonaje");
											}else{
												envioConfirmacion(socket, true);
												envioPersonaje(new Mensaje("EnvioPersonaje", reciboPersonajeDeBD(usuario)));
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
							personaje.insertar(persona.getUsuario(), persona.getRaza(), persona.getCasta());
//                    		envioElPersonaje(this.socket);
						} catch (SQLException e) {
							Log.crearLog("Error: No se pudo realizar operacion en la BBDD." + e.getMessage());
						}
	                    	break;
	                    	                   
	                    case "MensajeEleccionTerreno":
	                    	MensajeEleccionTerreno terreno=gson.fromJson(mensajeResivido.getObjeto().toString(), MensajeEleccionTerreno.class);
	                    	if(terreno.getMapa().equals("Campo") || terreno.getMapa().equals("Playa") || terreno.getMapa().equals("Desierto")){
	                    		try {
	                    			personaje.actualizarMapa(terreno.getUsuario(), terreno.getMapa());
								} catch (Exception e) {
									Log.crearLog("Error: Seleccion de mapa erronea." + e.getMessage());
								}
	                    	}
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
	                    default: System.out.println ("El tipo especificado no es un mensaje.."); break;

                    }
                   
                }
                // indico que el flujo de informacion provenga del usuario de
                // este hilo.
                data = new DataInputStream(socket.getInputStream());

            } while ((mensaje = data.readLine()) != null);

            Servidor.cantActualClientes--;
//            map.get(nombreMapa).remove(socket);	//No Existe mas el map..
        } catch (IOException e) {
            try {
                Servidor.cantActualClientes--;
//                map.get(nombreMapa).remove(socket);	//Soluciona problema de parar servidor.
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
           			 System.out.println("La conexion ha finalizado.");
            		 jugador.actualizar(this.usuario, false);
            		 } catch (SQLException | IOException e2) {
							e2.printStackTrace();
						}
            	 }
			}
           
        }
    }
    
    
    private Object reciboPersonajeDeBD(String usuario2) throws IOException {
    	try {
			String[] perso=personaje.seleccionarUsuario(usuario2).split(" ");
			MensajePersonaje per=new MensajePersonaje(perso[0], Integer.parseInt(perso[1]), Integer.parseInt(perso[2]), Integer.parseInt(perso[3]), Integer.parseInt(perso[4]), Integer.parseInt(perso[5]), Integer.parseInt(perso[6]), Integer.parseInt(perso[7]), Integer.parseInt(perso[8]), Integer.parseInt(perso[9]), perso[10], perso[11], perso[12]);
			return per;
			
		} catch (SQLException e) {
			Log.crearLog("Error: No se pudo separar correctamente el Personaje." + e.getMessage());
		} catch (IOException e) {
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
