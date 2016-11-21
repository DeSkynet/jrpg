package dao;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class SQLiteConnection {

	private static Connection conexion;

	public static Connection getConnection() {

		try {
			if (conexion == null) {
				Class.forName("org.sqlite.JDBC");
				conexion = DriverManager.getConnection("jdbc:sqlite:src/main/resources/bd/Jugadores.bd");
				System.out.println("** La conexi�n se ha realizado con �xito.**");
			} 
		} catch (ClassNotFoundException cnfe) {
			JOptionPane.showMessageDialog(null, "No se encuentra el Driver. Aseg�rese de tener el archivo Jugadores.bd");
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return conexion;
	}

	public static void close() {
		try {
			if (conexion != null) {
				conexion.close();
				System.out.println("** La Desconexi�n de la BD fue exitosa. **");
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

}