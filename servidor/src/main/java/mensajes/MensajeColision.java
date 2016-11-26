package mensajes;

public class MensajeColision {
	private	String usuarioDestino;
	private String usuarioOrigen;
	
	public MensajeColision(String usuarioDestino, String usuarioOrigen) {
		this.usuarioDestino = usuarioDestino;
		this.usuarioOrigen = usuarioOrigen;
	}

	public String getUsuarioDestino() {
		return usuarioDestino;
	}

	public void setUsuarioDestino(String usuarioDestino) {
		this.usuarioDestino = usuarioDestino;
	}

	public String getUsuarioOrigen() {
		return usuarioOrigen;
	}

	public void setUsuarioOrigen(String usuarioOrigen) {
		this.usuarioOrigen = usuarioOrigen;
	}
	
	
}
