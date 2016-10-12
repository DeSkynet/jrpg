package personaje.castas;

import personaje.Casta;


public class Ladron extends Casta{

	@Override
	public String getCasta() {
		return "Ladron";
	}

	@Override
	public int bonusDeDefensa() {
		return 2;
	}

	@Override
	public int bonusDeAtaque() {
		return 5;
	}
	
}
