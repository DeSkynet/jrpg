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
	}
	
	public Robot(Casta casta) {
		this.altura = 200;
		this.casta = casta;
	}
	
	@Override
	protected int calcularPuntosDeDefensa() {
		if(this.getCasta()== null)
			return 5;
		else
			return 5 + this.casta.bonusDeDefensa();
	}

	@Override
	protected boolean puedeAtacar() {
		return this.energia>=20;
	}

	@Override
	protected int calcularPuntosDeAtaque() {
		if(this.getCasta()== null)
			return 20;
		else
			return 20 + this.casta.bonusDeAtaque();
	}
	

}
