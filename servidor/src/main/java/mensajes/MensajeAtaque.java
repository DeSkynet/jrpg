package mensajes;

public class MensajeAtaque {
	String usuario;
	String ataque;
	String usuarioAtacado;
	
	public MensajeAtaque(String personaje,String ataque,String enemigo){
		this.usuario=personaje;
		this.ataque=ataque;
		this.usuarioAtacado=enemigo;
	}

	String getUsuario() {
		return usuario;
	}

	String getAtaque() {
		return ataque;
	}

	String getUsuarioAtacado() {
		return usuarioAtacado;
	}
}
