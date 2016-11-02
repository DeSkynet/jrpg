package personaje.items;

import personaje.Equipamiento;
public class ConCascoDeMithril extends Equipamiento{
	public ConCascoDeMithril() {
		super(5);	//5 es la eficiencia del casco de mithril.
	}
	
	public int calcularPuntosDeDefensa() {
			return super.calcularPuntosDeDefensa() + getEficiencia();
	}
}
