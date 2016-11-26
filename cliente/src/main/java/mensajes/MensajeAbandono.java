package mensajes;

public class MensajeAbandono {
	private String usuario;
	private boolean tiraGas;
	
	public MensajeAbandono(String usuario, boolean tiraGas) {
		this.usuario = usuario;
		this.tiraGas = tiraGas;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public boolean isTiraGas() {
		return tiraGas;
	}

	public void setTiraGas(boolean tiraGas) {
		this.tiraGas = tiraGas;
	}
	
	

}
