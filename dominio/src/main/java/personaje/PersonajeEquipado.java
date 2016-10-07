package personaje;

public class PersonajeEquipado extends Personaje{
	
	Personaje personajeEquipado;
	
	public PersonajeEquipado(Personaje personajeEquipado) {
		this.personajeEquipado = personajeEquipado;
	}

	@Override
	protected int calcularPuntosDeDefensa() {
		return this.personajeEquipado.calcularPuntosDeDefensa();
	}

	@Override
	protected boolean puedeAtacar() {
		return this.personajeEquipado.puedeAtacar();
	}

	@Override
	protected int calcularPuntosDeAtaque() {
		return this.personajeEquipado.calcularPuntosDeAtaque();
	}
	
	@Override
	public int obtenerPuntosDeAtaque() {
		return this.personajeEquipado.obtenerPuntosDeAtaque();
	}

}
