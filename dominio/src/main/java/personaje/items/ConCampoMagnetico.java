package personaje.items;

import personaje.Equipamiento;

public class ConCampoMagnetico extends Equipamiento{
	
	public ConCampoMagnetico() {
		super(10);
	}
	
	public int obtenerPuntosDeDefensa() {
		return super.calcularPuntosDeDefensa() + getEficiencia();
	}
}
