package personaje;

import personaje.Casta;

/**
 * EL SUPERHEROE ATACA MAS FUERTE CADA VEZ QUE RECIBE UN ATAQUE
 * POR ESO SE CREO UNA VARIABLE CANTIDAD DE ATAQUES RECIBIDOS.
 */

public class SuperHeroe extends Personaje{
	
	int cantidadDeAtaquesRecibidos;
	
	public SuperHeroe() {
		this.altura = 190;
	}
	
	public SuperHeroe(Casta casta) {
		this.altura = 190;
		this.casta = casta;
	}
	
	@Override
	protected int calcularPuntosDeDefensa() {
		return 4 + this.casta.bonusDeDefensa();
	}

	@Override
	protected boolean puedeAtacar() {
		return this.energia >= calcularPuntosDeAtaque();
	}

	@Override
	protected int calcularPuntosDeAtaque() {
		return 20 + cantidadDeAtaquesRecibidos + this.casta.bonusDeAtaque();
	}
	
	protected void despuesDeSerAtacado() {
		cantidadDeAtaquesRecibidos++;
	}

}
