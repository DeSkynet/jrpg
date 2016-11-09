package mensajes;

public class MensajeEleccionTerreno {
	private String usuario;
	private String mapa;
	
	public MensajeEleccionTerreno(String usuario,String mapa){
		this.usuario=usuario;
		this.mapa=mapa;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getMapa() {
		return mapa;
	}
}
