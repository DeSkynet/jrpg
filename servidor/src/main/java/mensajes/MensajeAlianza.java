package mensajes;

public class MensajeAlianza {
	private String cliente;
	private String alianza;
	
	public MensajeAlianza(String cliente, String alianza){
		this.cliente = cliente;
		this.alianza = alianza;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getAlianza() {
		return alianza;
	}

	public void setAlianza(String alianza) {
		this.alianza = alianza;
	}
	
	
}
