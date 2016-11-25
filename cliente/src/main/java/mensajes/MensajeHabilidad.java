package mensajes;

public class MensajeHabilidad {
	String usuario;
	String habilidad;
	String usuarioAtacado;
	
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
