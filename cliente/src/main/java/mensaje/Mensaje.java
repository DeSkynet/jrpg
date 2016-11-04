package mensaje;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Mensaje {
	protected String emisor;
	protected String mensaje;
	protected Calendar hora;
	
	public Mensaje(String emisor,String mensaje){
		this.emisor=emisor;
		this.mensaje=mensaje;
		this.hora=new GregorianCalendar();
		
	}
	
	public String getEmisor() {
		return emisor;
	}
	public String getMensaje() {
		return mensaje;
	}
	public Calendar getHora() {
		return hora;
	}
}
