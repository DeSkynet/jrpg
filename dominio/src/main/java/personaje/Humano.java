package personaje;

import personaje.Casta;

/**
 *  -------- A DESARROLLAR ---------
 * LA IDEA DEL HUMANO ES QUE CUANDO SE FORME PARTE
 * DE UNA ALIANZA SE VUELVA MAS FUERTE (TENGA MAS PUNTOS DE ATAQUE).
 */

public class Humano extends Personaje{
	
	public Humano() {
		this.altura = 180;
	}
	
	public Humano(Casta casta) {
		this.altura = 180;
		this.casta = casta;
	}

	@Override
	protected int calcularPuntosDeDefensa() {
		return 0 + this.casta.bonusDeDefensa();
	}

	@Override
	protected boolean puedeAtacar() {
		return this.energia>=10;
	}

	@Override
	protected int calcularPuntosDeAtaque() {
		return 10 + this.casta.bonusDeAtaque();
	}

}
