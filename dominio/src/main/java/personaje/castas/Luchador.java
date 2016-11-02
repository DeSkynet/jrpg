package personaje.castas;

import personaje.Casta;


public class Luchador extends Casta{
	
	@Override
	public String getCasta() {
		return "Luchador";
	}

	@Override
	public int bonusDeDestreza() {
		return 5;
	}

	@Override
	public int bonusDeFuerza() {
		return 7;
	}
	
	@Override
	public int bonusDeInteligencia(){
		return 2;
	}

}
