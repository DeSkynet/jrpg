package dao;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import javax.swing.JOptionPane;

import log.Log;

public class SQLiteConnection {

	private static Connection conexion;

	public static Connection getConnection() throws IOException {
		try {
			if (conexion == null) {
				Class.forName("org.sqlite.JDBC");
				conexion = DriverManager.getConnection("jdbc:sqlite:src/main/resources/bd/Jugadores.bd");
				Log.crearLog("Conexion Exitosa" );
			} 
		} catch (ClassNotFoundException cnfe) {
			JOptionPane.showMessageDialog(null, "No se encuentra el Driver. Asegúrese de tener el archivo Jugadores.bd");
		} catch (SQLException sqle) {
			Log.crearLog("Error de conexion a la Base de Datos " + sqle.getSQLState() );
		}
		return conexion;
	}

	public static void close() throws IOException {
		try {
			if (conexion != null) {
				conexion.close();
				Log.crearLog("Cierre de conexion exitosa");
			}
		} catch (SQLException sqle) {
			Log.crearLog("Error al cierre de la Base de Datos:" + sqle.getSQLState());
		}
	}

}