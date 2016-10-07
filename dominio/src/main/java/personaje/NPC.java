package personaje;

import interfaces.Atacable;

public class NPC implements Atacable {
	
	int salud = 100;
	
	@Override
	public void serAtacado(int daño) {
		this.salud-= daño;
	}

}
