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
		movimientoX = ente.getPunto().x - (Constantes.ANCHO_PANTALLA - ente.getAncho())/2;
		movimientoY = ente.getPunto().y - (Constantes.ALTO_PANTALLA - ente.getAlto())/2;
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
