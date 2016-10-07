package personaje;

/**
 * EL ALIEN PUEDE REGENERARSE. POR EJ, DESPUES DE ATACAR 
 * RECUPERA UN PORCENTAJE DE ENERGIA
 * EQUIVALENTE AL DAÑO REALIZADO.
 */

public class Alien extends Personaje{
	
	public Alien() {
		this.altura = 120;
	}
	
	@Override
	protected int calcularPuntosDeDefensa() {
		return 3;
	}

	@Override
	protected boolean puedeAtacar() {
		return this.energia>=13;
	}

	@Override
	protected int calcularPuntosDeAtaque() {
		return 7;
	}
	
	protected void despuesDeAtacar() {
		if(!this.esSaludMaxima()) {
			this.salud+=calcularPuntosDeAtaque()/10;
			if(this.salud>100)
				this.salud=100;
		}
	}

}
