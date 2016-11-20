package estado;

import java.awt.Graphics;

import estado.Estado;
import pantalla.Juego;

public abstract class Estado {

	private static Estado estadoActual = null; // Estado para saber que actualizacion realizar
 
	protected Juego juego;
	
	public Estado(Juego juego) {
		this.juego = juego;
	}
	
	public abstract void actualizar();
	
	public abstract void graficar(Graphics g);
	
	public static void setEstado(Estado estado) {
		estadoActual = estado;
	}
	
	public static Estado getEstado() {
		return estadoActual;
	}
}
