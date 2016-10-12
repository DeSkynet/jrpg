package personaje.castas;

import personaje.Casta;

public class Hechicero extends Casta{

	@Override
	public String getCasta() {
		return "Hechicero";
	}

	@Override
	public int bonusDeDefensa() {
		return 6;
	}

	@Override
	public int bonusDeAtaque() {
		return 8;
	}
	

}
