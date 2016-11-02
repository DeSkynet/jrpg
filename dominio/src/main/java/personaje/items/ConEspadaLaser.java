package personaje.items;

import personaje.Equipamiento;
/** LA ESPADA LASER AUMENTA EL PODER DE ATAQUE +10.*/
public class ConEspadaLaser extends Equipamiento{
	public ConEspadaLaser() {
		super(10);
	}
	
	public int calcularPuntosDeAtaque() {
		return super.calcularPuntosDeAtaque() + getEficiencia();
	}
}


