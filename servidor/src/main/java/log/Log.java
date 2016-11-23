package log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class Log {
	public static void crearLog(String operacion) throws IOException {
		FileWriter salida;
		Calendar fechaActual = Calendar.getInstance();
		
		if(new File(PATH_ARCHIVO).exists()==false)
				salida = new FileWriter(new File(PATH_ARCHIVO),false);
		
		salida = new FileWriter(new File(PATH_ARCHIVO),true);
		
		
		salida.write(fechaActual.get(Calendar.DAY_OF_MONTH) + "/" + 
				(fechaActual.get(Calendar.MONTH)+1) + "/" + fechaActual.get(Calendar.YEAR)
				+ ";" + fechaActual.get(Calendar.HOUR_OF_DAY) + ":" + fechaActual.get(Calendar.MINUTE)
				+ ":" + fechaActual.get(Calendar.SECOND) + ";");
		salida.write(operacion);
		salida.close();
	}
	
	public static final String PATH_ARCHIVO = "src/main/resources/log.prop";
}
