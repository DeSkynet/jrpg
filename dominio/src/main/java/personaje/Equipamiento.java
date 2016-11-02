package personaje;

import personaje.items.ItemMayor;

public class Equipamiento extends Personaje{
	private int eficiencia;
	
	public Equipamiento(int efi) {
		eficiencia=efi;
	}

	@Override
	protected int calcularPuntosDeDefensa() {
		if(this.tieneEquipamiento())
			return this.equipamiento.calcularPuntosDeDefensa();
		return 0;
	}

	@Override
	protected boolean puedeAtacar() {
		return this.equipamiento.puedeAtacar();
	}

	@Override
	protected int calcularPuntosDeAtaque() {
		if(this.tieneEquipamiento())
			return this.equipamiento.calcularPuntosDeAtaque();
		return 0;
	}
	
	public int getEficiencia(){
		return this.eficiencia;
	}
	
	protected ItemMayor calcularItemMayor() {
		ItemMayor itemMayor;
		if(this.tieneEquipamiento()==true){
		 Equipamiento per=this.equipamiento;
		 itemMayor=per.calcularItemMayor();
		 if((itemMayor.getEficienciaMayor()) > this.getEficiencia())
			return itemMayor;
		}
		return new ItemMayor(this.getEficiencia(),this.getClass().getSimpleName());
	}
	
	public Equipamiento getEquipamiento(){
		return this.equipamiento;
	}
	
}
