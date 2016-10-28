package personaje.castas;

import personaje.Casta;

public class Hechicero extends Casta{
	
	@Override
	public String getCasta() {
		return "Hechicero";
	}

	@Override
	public int bonusDeDestreza() {
		return 2;
	}

	@Override
	public int bonusDeFuerza() {
		return 4;
	}
	
	@Override
	public int bonusDeInteligencia(){
		return 7;
	}
}
