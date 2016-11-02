package personaje.items;

public class ItemMayor {
	int eficienciaMayor;
	String clase;
	
	public ItemMayor(int i, String clase) {
		// TODO Auto-generated constructor stub
		this.eficienciaMayor=i;
		this.clase=clase;
	}
	public int getEficienciaMayor() {
		return eficienciaMayor;
	}
	public void setEficienciaMayor(int eficienciaMayor) {
		this.eficienciaMayor = eficienciaMayor;
	}
	public String getClase() {
		return clase;
	}
	public void setClase(String clase) {
		this.clase = clase;
	}
}
