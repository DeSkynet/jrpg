package personaje;

import interfaces.Atacable;

public class NPC implements Atacable {
	
	int salud = 100;
	
	@Override
	public void serAtacado(int da�o) {
		this.salud-= da�o;
	}

}
