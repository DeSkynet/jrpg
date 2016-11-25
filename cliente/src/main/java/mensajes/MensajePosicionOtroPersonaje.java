package mensajes;

public class MensajePosicionOtroPersonaje {
	private String usuario;
	private String raza;
	int coordX;
	int coordY;
	int nivel;
	
	public MensajePosicionOtroPersonaje(String usuario, String raza, int coordX, int coordY, int nivel) {
		this.usuario = usuario;
		this.raza = raza;
		this.coordX = coordX;
		this.coordY = coordY;
		this.nivel = nivel;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public int getCoordX() {
		return coordX;
	}

	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	public int getCoordY() {
		return coordY;
	}

	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	

}
