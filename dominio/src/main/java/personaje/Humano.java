package personaje;

/**
 *  -------- A DESARROLLAR ---------
 * LA IDEA DEL HUMANO ES QUE CUANDO SE FORME PARTE
 * DE UNA ALIANZA SE VUELVA MAS FUERTE (TENGA MAS PUNTOS DE ATAQUE).
 */

public class Humano extends Personaje{
	
	public Humano() {
		this.altura = 180;
	}

	@Override
	protected int calcularPuntosDeDefensa() {
		return 0;
	}

	@Override
	protected boolean puedeAtacar() {
		return this.energia>=10;
	}

	@Override
	protected int calcularPuntosDeAtaque() {
		return 10;
	}

}
