package mensajes;

public class MensajeIncrementoExperiencia {
	private String usuario;
	private int incremento;
	
	public MensajeIncrementoExperiencia(String usuario, int incrementoExperiencia){
		this.usuario=usuario;
		this.incremento=incrementoExperiencia;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public int getIncremento() {
		return incremento;
	}

	public void setIncremento(int incremento) {
		this.incremento = incremento;
	}
	
	
}
