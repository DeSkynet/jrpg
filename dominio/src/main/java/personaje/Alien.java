package personaje;



import personaje.Casta;

/**
 * EL ALIEN PUEDE REGENERARSE. POR EJ, DESPUES DE ATACAR 
 * RECUPERA UN PORCENTAJE DE ENERGIA
 * EQUIVALENTE AL DAÑO REALIZADO.
 */

public class Alien extends Personaje{
	
	public Alien() {
		this.altura = 120;
	}
	
	public Alien(Casta casta) {
		this.altura = 120;
		this.casta = casta;
	}
	
	@Override
	protected int calcularPuntosDeDefensa() {
		if(this.getCasta()== null)
			return 3;
		else
			return 3 + this.casta.bonusDeDefensa();
	}

	@Override
	protected boolean puedeAtacar() {
		return this.energia>=13;
	}

	@Override
	protected int calcularPuntosDeAtaque() {
		if(this.getCasta() == null)
			return 10;
		else
			return 10 + this.casta.bonusDeAtaque();
	}
	
	protected void despuesDeAtacar() {
		if(!this.esSaludMaxima()) {
			this.salud+=calcularPuntosDeAtaque()/10;
			if(this.salud>100)
				this.salud=100;
		}
	}

}
