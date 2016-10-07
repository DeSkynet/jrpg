package personaje;

/**
 * EL SUPERHEROE ATACA MAS FUERTE CADA VEZ QUE RECIBE UN ATAQUE
 * POR ESO SE CREO UNA VARIABLE CANTIDAD DE ATAQUES RECIBIDOS.
 */

public class SuperHeroe extends Personaje{
	
	int cantidadDeAtaquesRecibidos;
	
	public SuperHeroe() {
		this.altura = 190;
	}
	
	@Override
	protected int calcularPuntosDeDefensa() {
		return 4;
	}

	@Override
	protected boolean puedeAtacar() {
		return this.energia >= calcularPuntosDeAtaque();
	}

	@Override
	protected int calcularPuntosDeAtaque() {
		return 20 + cantidadDeAtaquesRecibidos;
	}
	
	protected void despuesDeSerAtacado() {
		cantidadDeAtaquesRecibidos++;
	}

}
