package mensajes;

public class MensajeEleccionPersonaje {
	private String usuario;
	private String raza;
	private String Casta;
	
	public MensajeEleccionPersonaje(String usuario,String raza,String casta){
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
