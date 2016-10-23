package personaje.items;

import personaje.Personaje;
import personaje.PersonajeEquipado;

public class ConCampoMagnetico extends PersonajeEquipado{
	
	public ConCampoMagnetico(Personaje personajeEquipado) {
		super(personajeEquipado);
	}
	
	public int obtenerPuntosDeDefensa() {
		return super.obtenerPuntosDeDefensa() + 10;
	}
}
