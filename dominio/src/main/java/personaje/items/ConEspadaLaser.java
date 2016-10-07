package personaje.items;

import personaje.Personaje;
import personaje.PersonajeEquipado;

/**
 *  LA ESPADA LASER AUMENTA EL PODER DE ATAQUE +10.
 */

public class ConEspadaLaser extends PersonajeEquipado{

	public ConEspadaLaser(Personaje personajeEquipado) {
		super(personajeEquipado);
	}
	
	public int obtenerPuntosDeAtaque() {
		return super.obtenerPuntosDeAtaque() + 10;
	}
}


