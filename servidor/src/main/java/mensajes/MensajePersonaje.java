package mensajes;

public class MensajePersonaje {
	String usuario;
	int coordX;
	int coordY;
	int experiencia;
	int nivel;
	int destreza;
	int fuerza;
	int inteligencia;
	int energia;
	int salud;
	String casta;
	String raza;
	String mapa;
	
	public MensajePersonaje(String usuario, int coordX, int coordY, int experiencia, int nivel, int destreza,
			int fuerza, int inteligencia, int energia, int salud, String casta, String raza, String mapa) {
		this.usuario = usuario;
		this.coordX = coordX;
		this.coordY = coordY;
		this.experiencia = experiencia;
		this.nivel = nivel;
		this.destreza = destreza;
		this.fuerza = fuerza;
		this.inteligencia = inteligencia;
		this.energia = energia;
		this.salud = salud;
		this.casta = casta;
		this.raza = raza;
		this.mapa = mapa;
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
	public int getExperiencia() {
		return experiencia;
	}
	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public int getDestreza() {
		return destreza;
	}
	public void setDestreza(int destreza) {
		this.destreza = destreza;
	}
	public int getFuerza() {
		return fuerza;
	}
	public void setFuerza(int fuerza) {
		this.fuerza = fuerza;
	}
	public int getInteligencia() {
		return inteligencia;
	}
	public void setInteligencia(int inteligencia) {
		this.inteligencia = inteligencia;
	}
	public int getEnergia() {
		return energia;
	}
	public void setEnergia(int energia) {
		this.energia = energia;
	}
	public int getSalud() {
		return salud;
	}
	public void setSalud(int salud) {
		this.salud = salud;
	}
	public String getCasta() {
		return casta;
	}
	public void setCasta(String casta) {
		this.casta = casta;
	}
	public String getRaza() {
		return raza;
	}
	public void setRaza(String raza) {
		this.raza = raza;
	}
	public String getMapa() {
		return mapa;
	}
	public void setMapa(String mapa) {
		this.mapa = mapa;
	}
	
	
}
