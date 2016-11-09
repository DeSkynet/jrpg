package mensajes;

public class Mensaje {
	protected String tipoMensaje;
	protected Object objeto;
	
	public Mensaje(String tipoMensaje,Object obj){
		this.tipoMensaje=tipoMensaje;
		this.objeto=obj;
		
	}

	public String getTipoMensaje() {
		return tipoMensaje;
	}

	public void setTipoMensaje(String tipoMensaje) {
		this.tipoMensaje = tipoMensaje;
	}

	public Object getObjeto() {
		return objeto;
	}

	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}
	
	
}
