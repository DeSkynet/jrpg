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
		this.fuerza = 20;
		this.destreza = 4;
		this.inteligencia = 3;
	}
	
	public SuperHeroe(Casta casta) {
		this.altura = 190;
		this.casta = casta;
		this.fuerza = 20 + this.casta.bonusDeFuerza();
		this.destreza = 4 + this.casta.bonusDeDestreza();
		this.inteligencia = 3 + this.casta.bonusDeInteligencia();
	}
	
	@Override
	protected int calcularPuntosDeDefensa() {
		return this.destreza;
	}

	@Override
	protected boolean puedeAtacar() {
		return this.energia >= calcularPuntosDeAtaque();
	}

	@Override
	protected int calcularPuntosDeAtaque() {
		return this.fuerza + cantidadDeAtaquesRecibidos;
	}
	
	protected void despuesDeSerAtacado() {
		cantidadDeAtaquesRecibidos++;
	}

}
