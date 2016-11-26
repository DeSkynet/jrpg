package pantalla;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import cliente.Cliente;
import cliente.HiloCliente;
import estado.Estado;
import estado.EstadoJuego;
import graficos.Mapa;
import graficos.Sprite;
import herramientas.Constantes;
import herramientas.MousePoint;
import ventana.Ventana;

public class Juego implements Runnable{
	Cliente cliente;
	private Ventana ventana;				//Es la ventana principal del juego
	private MousePoint mouse;				//tengo 2 variables.. POSICIONACTUAL,PUNTO y click.. si click es true, tengo que mirar a donde se hizo click(en punto=	
	private Thread hiloEjecucion;			//--> hilo de ejecucion para el juego
	private volatile boolean estaEjecutando = false;	 //--> me indica si el juego esta detenido para detener el hilo ejecutante.
	// --> volatile evita que se corrompa la variable, si un metodo esta R/W otro metodo no lo podra hacer
	private int aps;
	private long fps;
	private Graphics graficos;
	private EstadoJuego estado;
//	private EstadoJuego estadoJuego;
	private Camara camaraPersonaje;
	private BufferStrategy estrategia; 
	HiloCliente hiloCliente;
	
	public Juego(Cliente cliente) {
		this.cliente=cliente;
		//RECIBIR PERSONAJE
		cliente.RecibePersonaje();
		hiloCliente = new HiloCliente(cliente,cliente.getSocket(),this);
	    hiloCliente.start();
	    cliente.setHiloCliente(hiloCliente);
		cliente.setMapaActual(cliente.getPersona().getMapa());
		cliente.setUsuario(cliente.getPersona().getUsuario());
		this.mouse = new MousePoint();
		
		//this.ventana.addMouseListener(mouse);	//--> le indico que lo que pase en la pantalla lo  va a escuchar el mouse
	}
	
	public synchronized void iniciar() {
		
		this.ventana = new Ventana();
		
		ventana.getCanvas().addMouseListener(mouse);
//		System.out.println(this.cliente.getPersona().getRaza());
		Sprite.cargar(this.cliente.getPersona().getRaza());	//carga el sprite que quiero jugar..
		try{
			switch (cliente.getMapaActual()) {
			case "Campo":
				this.estado = new EstadoJuego(this, new Mapa(this,Constantes.PATH_MAPA_CAMPO));
				break;
			case "Playa":
				this.estado = new EstadoJuego(this, new Mapa(this,Constantes.PATH_MAPA_PLAYA));
				break;
			case "Desierto":
				this.estado = new EstadoJuego(this, new Mapa(this,Constantes.PATH_MAPA_DESIERTO));
			default:
				break;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		Estado.setEstado(estado);
		
		this.estaEjecutando = true;
		this.hiloEjecucion = new Thread(this, "Graficos");  //--> creo thread con un nombrepor si lo quiero monitorear
		this.hiloEjecucion.start();
		
		camaraPersonaje = new Camara(0, 0);
		
	}
	
	
	private synchronized void detener() {
		this.estaEjecutando = false;
		
		try {
			this.hiloEjecucion.join();// --> podria parar con stop pero lo que hace join es preparar el terreno para q el cierre
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// --> actualiza todo, graficos, jugadores, vida, items, etc.
	private void actualizar() {
		mouse.actualizar();
		if (Estado.getEstado() != null) {
			Estado.getEstado().actualizar();
		}
		aps++;
	}
	
	// --> muestra en pantalla todo lo de actualizar.
	private void mostrar() {
		 estrategia = ventana.getCanvas().getBufferStrategy();
		
		if(estrategia==null) {
			ventana.getCanvas().createBufferStrategy(3);
			return;
		}
		
		graficos = estrategia.getDrawGraphics();
		
		///limpio la pantalla para q el juego no deje estela
		graficos.clearRect(0, 0, Constantes.ANCHO_PANTALLA,  Constantes.ALTO_PANTALLA);
		if(Estado.getEstado()!=null){
			estado.graficar(graficos);	//llama al metodo de graficar de EstadoJuego.java
	
		}
		
		estrategia.show();
		graficos.dispose();
		fps++;
	}
	
	
	//EJECUTA EL HILO Y ADEMAS MUESTRA COMO INFORMACION LAS ACTUALIZACIONES POR SEGUNDO Y LOS FRAMES POR SEGUNDO
	@Override
	public void run() {
		long referencia = System.nanoTime();
		long referenciaContador = System.nanoTime();
		double tiempoFinal;
		double delta = 0;
		
		while(estaEjecutando) {
			long inicioBucle = System.nanoTime();
			tiempoFinal = inicioBucle - referencia;
			referencia = inicioBucle;
			delta+= tiempoFinal / Constantes.NANOSEGUNDOS_POR_ACTUALIZACION;
			
			if(delta >= 1) {
				actualizar();//--> aqui actuliza el juego
				mostrar();	//-->aqui lo muestra en pantalla
				delta--;
			}
					
			if((System.nanoTime() - referenciaContador) > Constantes.NANOSEGUNDOS_POR_SEGUNDO) {
				ventana.obtenerVentana().setTitle(Constantes.NOMBRE_JUEGO + " - " + "APS:" + this.aps + " - " + "FPS:" + this.fps);
				this.aps = 0;
				this.fps = 0;
				referenciaContador = System.nanoTime();
			}
		}	
		detener();
	
	}

	public Camara getCamaraPersonaje() {
		return camaraPersonaje;
	}

	public void setCamaraPersonaje(Camara camaraPersonaje) {
		this.camaraPersonaje = camaraPersonaje;
	}

	public MousePoint getMouse() {
		return mouse;
	}
	
	public EstadoJuego getEstadoJuego() {
		return estado;
	}
	
	public Cliente getCliente(){
		return this.cliente;
	}
	
}
