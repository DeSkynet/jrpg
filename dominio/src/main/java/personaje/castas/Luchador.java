package personaje.castas;

import personaje.Casta;

public class Luchador extends Casta{

	@Override
	public String getCasta() {
		return "Luchador";
	}

	@Override
	public int bonusDeDestreza() {
		return 1;
	}

	@Override
	public int bonusDeFuerza() {
		return 10;
	}
	
	@Override
	public int bonusDeInteligencia(){
		return 5;
	}

}
