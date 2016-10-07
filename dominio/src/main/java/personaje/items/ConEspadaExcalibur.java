package personaje.items;

import personaje.Personaje;
import personaje.PersonajeEquipado;

public class ConEspadaExcalibur extends PersonajeEquipado{

	public ConEspadaExcalibur(Personaje personajeEquipado) {
		super(personajeEquipado);
	}
	
	public int obtenerPuntosDeAtaque() {
		return super.obtenerPuntosDeAtaque() + 20;
	}

}
