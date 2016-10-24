package personaje;

import personaje.Casta;

/**
 *  ---------- IDEA A DESARROLLAR (A REVISAR) ---------
 * QUE EN UNA CIERTA BATALLA, EL ROBOT PUEDA REVIVIR SOLO UNA VEZ CADA CIERTOS TURNOS.
 * HABRIA QUE GENERAR UN CONTADOR DE TURNOS Y VER LA BATALLA.
 * PERO CON LA MITAD DE ENERGIA Y/O SALUD.
 */

public class Robot extends Personaje{
	
	public Robot(){
		this.altura = 200;
		this.fuerza = 20;
		this.destreza = 5;
		this.inteligencia = 10;
		this.nombre = "Default";
	}
	
	public Robot(Casta casta, String nombre) {
		this.altura = 200;
		this.casta = casta;
		this.fuerza = 20 + this.casta.bonusDeFuerza();
		this.destreza = 5 + this.casta.bonusDeDestreza();
		this.inteligencia = 10 + this.casta.bonusDeInteligencia();
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
		return this.fuerza;
	}
}
