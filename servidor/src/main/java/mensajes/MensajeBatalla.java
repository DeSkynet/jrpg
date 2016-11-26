package mensajes;

public class MensajeBatalla {
	 private String usuario;
	 private boolean mensaje;
	 
	 public MensajeBatalla(String usuario, boolean mensaje){
		 this.usuario = usuario;
		 this.mensaje = mensaje;
	 }
	 
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public boolean getMensaje() {
		return mensaje;
	}
	public void setMensaje(boolean mensaje) {
		this.mensaje = mensaje;
	}
	 
	 
}
