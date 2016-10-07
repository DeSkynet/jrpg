package personaje.items;

import personaje.Personaje;
import personaje.PersonajeEquipado;

public class ConCascoDeMithril extends PersonajeEquipado{

	public ConCascoDeMithril(Personaje personajeEquipado) {
		super(personajeEquipado);
	}
	
	public int obtenerPuntosDeDefensa() {
		return super.obtenerPuntosDeDefensa() + 5;
	}
}
