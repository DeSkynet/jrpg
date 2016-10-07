package personaje;

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
	
	@Override
	protected int calcularPuntosDeDefensa() {
		return 5;
	}

	@Override
	protected boolean puedeAtacar() {
		return this.energia>=20;
	}

	@Override
	protected int calcularPuntosDeAtaque() {
		return 20;
	}
	

}
