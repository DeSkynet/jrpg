package personaje;

public class NPC extends Personaje {
	
	static final int[][] NIVELES = {{5,5},{7,7},{9,9},{11,11},{13,13},{15,15},{17,17}}; /// ESTA MATRIZ SE PUEDE SEGUIR Y CAMBIAR VALORES, DEFINE LA DEFENZA Y EL ATAQUE DEL NPC SEGUN SU NIVEL PONER EN CLASE CONSTANTES CUAND ESTE ECHA
	static final int NIVEL_MAX = 7;
	
	private int id;
	
	public NPC(int id){
		this.id = id;
	}
	
	//DEFENSA
	public int calcularPuntosDeDefensa(){
		return NIVELES[this.nivel-1][1];
	}
	public int obtenerPuntosDeDefensa() {
		return calcularPuntosDeDefensa();
	}
	
	//ATAQUE
	public int calcularPuntosDeAtaque(){
		return NIVELES[this.nivel-1][0];
	}
	
	public int obtenerPuntosDeAtaque() {
		return calcularPuntosDeAtaque();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getSalud() {
		return salud;
	}
	
	@Override
	public String getPersonaje() {
		// TODO Auto-generated method stub
		return null;
	}

}
