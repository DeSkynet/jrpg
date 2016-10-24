package personaje;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import personaje.Personaje;

public class Alianza {
	private String nombreAlianza; 
	private List<Personaje> alianza = new LinkedList<Personaje>();
	
	public Alianza(String nombreAlianza) {
		this.nombreAlianza = nombreAlianza;
	}
	
	public final void agregarAliado(Personaje personaje) {
		alianza.add(personaje);
	}
	
	public final void eliminarAliado(Personaje personaje) {
		alianza.remove(personaje);
	}
	
	public final int cantidadDeAliados() {
		return alianza.size();
	}
	
	public final boolean estaEnAlianza(Personaje personaje) {
		return alianza.contains(personaje);
	}
	
	public final String nombreAlianza() {
		return nombreAlianza;
	}
	
	public final void mostrarAlianza() {
		Iterator<Personaje> it = alianza.iterator();
		
		while(it.hasNext()) {
			System.out.println(it.next().nombre);
		}
	}
}
