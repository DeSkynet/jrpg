package mensajes;

public class MensajeHabilidad {
	private String usuario;
	private String habilidad;
	private String usuarioAtacado;
	
	public MensajeHabilidad(String personaje,String habilidad,String enemigo){
		this.usuario=personaje;
		this.habilidad=habilidad;
		this.usuarioAtacado=enemigo;
	}

	String getUsuario() {
		return usuario;
	}

	String getAtaque() {
		return habilidad;
	}

	String getUsuarioAtacado() {
		return usuarioAtacado;
	}
}
