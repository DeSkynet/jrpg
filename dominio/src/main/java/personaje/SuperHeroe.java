package personaje;

import personaje.Casta;

/**
 * EL SUPERHEROE ATACA MAS FUERTE CADA VEZ QUE RECIBE UN ATAQUE
 * POR ESO SE CREO UNA VARIABLE CANTIDAD DE ATAQUES RECIBIDOS.
 */

public class SuperHeroe extends Personaje{
	
	int cantidadDeAtaquesRecibidos;
	
	public SuperHeroe() {
		this.fuerza = 20;
		this.destreza = 4;
		this.inteligencia = 3;
		this.nombre = "Default";
	}
	
	public SuperHeroe(Casta casta, String nombre) {
		this.casta = casta;
		this.fuerza = 20 + this.casta.bonusDeFuerza();
		this.destreza = 4 + this.casta.bonusDeDestreza();
		this.inteligencia = 3 + this.casta.bonusDeInteligencia();
		this.nombre=nombre;
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
