package mensajes;

public class MensajePosicion {
	private String usuario;
	private int cordenadaX;
	private int cordenadaY;
	
	public MensajePosicion(String usuario,int x,int y){
		this.cordenadaX=x;
		this.cordenadaY=y;
	}
	
	public int getCordenadaX() {
		return cordenadaX;
	}

	public int getCordenadaY() {
		return cordenadaY;
	}

	public String getUsuario() {
		return usuario;
	}
	
}
