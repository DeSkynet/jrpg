package habilidades;

import personaje.Personaje;

public class Alentizar extends Habilidad {		//Debe modificar solo en batalla, no en el juego
	protected final int QUITA_DESTREZA_HABILIDAD = 5;
	@Override						
	public void afectar(Personaje personaje) {	
		personaje.setDestreza(personaje.getDestreza()- QUITA_DESTREZA_HABILIDAD);
	}
}
