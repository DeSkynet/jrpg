package mensajes;

public class MensajeAtaque {
	private String usuario;
	private String ataque;
	private String usuarioAtacado;
	
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
