package habilidades;

import personaje.Personaje;

public class Curar extends Habilidad{
	protected final int SUMA_SALUD_HABILIDAD = 20;
	@Override						
	public void afectar(Personaje personaje) {	
		if(!personaje.esSaludMaxima()) {
			personaje.setSalud(personaje.getDestreza() + SUMA_SALUD_HABILIDAD);
			if(personaje.getSalud() > 100)
				personaje.setSalud(100);
		}
	}

}
