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
		this.fuerza = 10;
		this.destreza = 0;
		this.inteligencia = 5;
		this.nombre = "Default";
	}
	
	public Humano(Casta casta,String nombre) {
		this.altura = 180;
		this.casta = casta;
		this.fuerza = 10 + this.casta.bonusDeFuerza();
		this.destreza = 0 + this.casta.bonusDeDestreza();
		this.inteligencia = 5 + this.casta.bonusDeInteligencia();
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
		if(this.alianza == null)
			return this.fuerza;
		
		return this.fuerza + this.alianza.cantidadDeAliados() - 1;  ///SE LE RESTA 1 PARA NO CONTAR AL PERSONAJE EN CUESTION
	}
}
