package personaje.castas;

import personaje.Casta;



public class Ladron extends Casta{
	
	@Override
	public String getCasta() {
		return "Ladron";
	}

	@Override
	public int bonusDeDestreza() {
		return 7;
	}

	@Override
	public int bonusDeFuerza() {
		return 2;
	}
	
	@Override
	public int bonusDeInteligencia(){
		return 4;
	}
	
}
