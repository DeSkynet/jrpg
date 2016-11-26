package estado;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JOptionPane;

import graficos.Cuadro;
import graficos.Mapa;
import graficos.Sprite;
import herramientas.Constantes;
import pantalla.Ente;
import pantalla.Juego;
import graficos.OtrosPersonajes;
/*
*	ESTE METODO ES EL NEXO DE LOS MAPAS Y PERSONAJES CON EL JUEGO.
*/

 
public class EstadoJuego extends Estado {
	private Mapa mapa;
	private Ente ente;
	private HashMap<String, OtrosPersonajes> otroPersonajes;
	
	public EstadoJuego(Juego juego, Mapa mapa) {
		super(juego);
		otroPersonajes=new HashMap<String, OtrosPersonajes>();
		this.mapa = mapa;
		ente = new Ente(juego, mapa, Constantes.ANCHO_ENTE , Constantes.ALTO_ENTE, 0, 0 , Sprite.sprite, Constantes.VELOCIDAD_ANIMACION);
	}
	
	@Override
	public void actualizar() {
		mapa.actualizar();
		ente.actualizar();
		if(!otroPersonajes.isEmpty()){
			Iterator<OtrosPersonajes> it =otroPersonajes.values().iterator();
			while(it.hasNext()){
				OtrosPersonajes otro=it.next();
				otro.actualizar();
				
			}
		}
	}

	@Override
	public void graficar(Graphics g) {
		g.setColor(Color.BLACK);
		g.fill3DRect(0, 0, Constantes.ANCHO_PANTALLA, Constantes.ALTO_PANTALLA, false);
		mapa.graficar(g);
		ente.graficar(g);
		//grafico otros personajes
		if(!otroPersonajes.isEmpty()){
			Iterator<OtrosPersonajes> it =otroPersonajes.values().iterator();
			while(it.hasNext()){
				OtrosPersonajes otro=it.next();
				otro.graficar(g);
				
			}
		}

		
	}
	public Ente getEnte() {
		return ente;
	}
	
	
	public void setOtroPersonaje(String usuario, String raza, int coordX, int coordY, int nivel){
		OtrosPersonajes per=new OtrosPersonajes(usuario, raza, coordX, coordY, nivel,super.juego,mapa);
		per.setAliado(false);
		if(otroPersonajes.containsKey(usuario)){
			OtrosPersonajes pers=otroPersonajes.get(per.getUsuario());
			pers.setPunto(new Point(coordX,coordY));
			
		}else{
			otroPersonajes.put(per.getUsuario(), per);
		}

		//DEBERIA REALIZAR LA ACTUALIZACION EN EL MAPA.	
	}
	
	public void quitarOtroPersonaje(String usuario){
		otroPersonajes.remove(usuario);
	}
	
	public HashMap<String, OtrosPersonajes> getOtroPersonajes(){
		return otroPersonajes;
		
	}

}
