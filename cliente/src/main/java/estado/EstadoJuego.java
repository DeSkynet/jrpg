package estado;

import java.awt.Color;
import java.awt.Graphics;

import graficos.Mapa;
import graficos.Sprite;
import herramientas.Constantes;
import pantalla.Ente;
import pantalla.Juego;

/*
*	ESTE METODO ES EL NEXO DE LOS MAPAS Y PERSONAJES CON EL JUEGO.
*/

 
public class EstadoJuego extends Estado {
	private Mapa mapa;
	private Ente ente;
	
	public EstadoJuego(Juego juego, Mapa mapa) {
		super(juego);
		this.mapa = mapa;
		ente = new Ente(juego, mapa, Constantes.ANCHO_ENTE , Constantes.ALTO_ENTE, 0, 0 , Sprite.sprite, Constantes.VELOCIDAD_ANIMACION);
	}
	
	@Override
	public void actualizar() {
		mapa.actualizar();
		ente.actualizar();
	}

	@Override
	public void graficar(Graphics g) {
		g.setColor(Color.BLACK);
		g.fill3DRect(0, 0, Constantes.ANCHO_PANTALLA, Constantes.ALTO_PANTALLA, false);
		mapa.graficar(g);
		ente.graficar(g);
		
	}
	public Ente getEnte() {
		return ente;
	}
	

}
