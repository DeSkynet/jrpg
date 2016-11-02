package personaje;



import personaje.Casta;

/**
 * EL ALIEN PUEDE REGENERARSE. DESPUES DE ATACAR 
 * RECUPERA UN PORCENTAJE DE ENERGIA
 * EQUIVALENTE AL 10% DE SU ATAQUE.
 */

public class Alien extends Personaje{
	
	public Alien() {
		this.fuerza = 10;
		this.destreza = 3;
		this.inteligencia = 8;
		this.nombre = "Default";
	}
	
	public Alien(Casta casta,String nombre) {
		this.casta = casta;
		this.fuerza = 10 + this.casta.bonusDeFuerza();
		this.destreza = 3 + this.casta.bonusDeDestreza();
		this.inteligencia = 8 + this.casta.bonusDeInteligencia();
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
	
	protected void despuesDeAtacar() {
		if(!this.esSaludMaxima()) {
			this.salud+=calcularPuntosDeAtaque()/10;
			if(this.salud>100)
				this.salud=100;
		}
	}

}
