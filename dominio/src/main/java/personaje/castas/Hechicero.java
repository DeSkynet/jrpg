package personaje.castas;

import personaje.Casta;

public class Hechicero extends Casta{
	
	@Override
	public String getCasta() {
		return "Hechicero";
	}

	@Override
	public int bonusDeDestreza() {
		return 6;
	}

	@Override
	public int bonusDeFuerza() {
		return 8;
	}
	
	@Override
	public int bonusDeInteligencia(){
		return 10;
	}
}
