package pantalla;

import herramientas.Constantes;

public class Camara {
	
	
	private double movimientoX;
	private double movimientoY;

	
	
	public Camara(double movimientoX, double movimientoY) {
		this.movimientoX = movimientoX;
		this.movimientoY = movimientoY;
	}

	public void mover(double x, double y) {
		movimientoX += x;
		movimientoY += y;
	}
	
	public void Centrar(Ente ente) {
		movimientoX = ente.getPunto().getX() - (Constantes.ANCHO_PANTALLA/2 + ente.getAncho()/33);
		movimientoY = ente.getPunto().getY() - (Constantes.ALTO_PANTALLA/2 + ente.getAlto()/33);
	}

	public double getMovimientoX() {
		return movimientoX;
	}

	public void setMovimientoX(double movimientoX) {
		this.movimientoX = movimientoX;
	}

	public double getMovimientoY() {
		return movimientoY;
	}

	public void setMovimientoY(double movimientoY) {
		this.movimientoY = movimientoY;
	}
	
	
	

}
