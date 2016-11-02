package personaje;

import personaje.Casta;

/**
 *  ---------- IDEA A DESARROLLAR (A REVISAR) ---------
 * QUE EN UNA CIERTA BATALLA, EL ROBOT PUEDA REVIVIR SOLO UNA VEZ CADA CIERTOS TURNOS.
 * HABRIA QUE GENERAR UN CONTADOR DE TURNOS Y VER LA BATALLA.
 * PERO CON LA MITAD DE ENERGIA Y/O SALUD.
 */

public class Robot extends Personaje{
	
	private int contadorRevivir = 0;
	
	public Robot(){
		this.fuerza = 20;
		this.destreza = 5;
		this.inteligencia = 10;
		this.nombre = "Default";
	}
	
	public Robot(Casta casta, String nombre) {
		this.casta = casta;
		this.fuerza = 15 + this.casta.bonusDeFuerza();
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

	public int getContadorRevivir() {
		return contadorRevivir;
	}

	public void setContadorRevivir(int contadorRevivir) {
		this.contadorRevivir = contadorRevivir;
	}
	
	
}
