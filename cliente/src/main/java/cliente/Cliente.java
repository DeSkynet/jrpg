package cliente;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	private static final String PATH_ARCHIVO_CONFIG = "archivos/conexion.config";
	
	private Socket cliente;
    private String nombre = null;
    private String host;
    private int puerto;
    
    //CONSTRUCTOR DE CLIENTE
	public Cliente(String nombre) {
		this.nombre = nombre;
		
		leerArchivoConfig();
		
		try {
			this.cliente = new Socket(host, puerto);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//LEE DEL ARCHIVO DE CONFIGURACION 
	private void leerArchivoConfig() {
		Scanner entrada = null;
    	try {
			entrada = new Scanner(new File(PATH_ARCHIVO_CONFIG));
			
			if(entrada.hasNextLine()) {
				this.host = entrada.nextLine().substring(3);
				this.puerto = Integer.parseInt(entrada.nextLine().substring(7));
			}
			
		} catch (FileNotFoundException e) {
				System.err.println(e.getLocalizedMessage());
		} finally {
			entrada.close();
		}
    	entrada.close();
	}

    
    
}
