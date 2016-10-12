package personaje.castas;

import personaje.Casta;

public class Luchador extends Casta{

	@Override
	public String getCasta() {
		return "Luchador";
	}

	@Override
	public int bonusDeDefensa() {
		return 1;
	}

	@Override
	public int bonusDeAtaque() {
		return 10;
	}

}
