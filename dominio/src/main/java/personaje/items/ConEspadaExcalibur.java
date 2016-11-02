package personaje.items;

import personaje.Equipamiento;
public class ConEspadaExcalibur extends Equipamiento{
	public ConEspadaExcalibur() {
		super(20); //20 es la eficiencia de la espada Excalibur
	}
	public int calcularPuntosDeAtaque() {
			return super.calcularPuntosDeAtaque() + getEficiencia();
	}

}
