package mensajes;

public class MensajeNuevoJugador {
	private String usuario;
	private String raza;
	private String Casta;
	
	public MensajeNuevoJugador(String usuario,String raza,String casta){
		this.usuario=usuario;
		this.raza=raza;
		this.Casta=casta;		
	}

	public String getUsuario() {
		return usuario;
	}

	public String getRaza() {
		return raza;
	}

	public String getCasta() {
		return Casta;
	}
	
}
