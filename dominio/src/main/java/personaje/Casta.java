package personaje;

import java.util.HashMap;
import java.util.Map;

import habilidades.Habilidad;

/**
 * IDEA I ----> CONSTA QUE HAYA SOLO HAYA DOS TIPOS DE CASTA
 * BUENOS  Y MALOS.....
 */


public abstract class Casta {
	
	private Map<String, Habilidad> habilidades = new HashMap<String,Habilidad>();
	
	public void agregarHabilidad(String nombreHabilidad, Habilidad habilidad) {
		this.habilidades.put(nombreHabilidad, habilidad);
	}
	
	public int getCantidadDeHabilidades() {
		return this.habilidades.size();
	}

	public void usarHabilidad(String nombreHabilidad, Personaje personaje) {
		this.habilidades.get(nombreHabilidad).afectar(personaje);
		
	}
	
	public abstract String getCasta();
	public abstract int bonusDeDestreza();
	public abstract int bonusDeFuerza();
	public abstract int bonusDeInteligencia();
}
