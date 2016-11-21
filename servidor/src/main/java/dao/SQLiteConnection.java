package dao;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnection {

	private static Connection conn;

	public static Connection getConnection() {

		try {
			if (conn == null) {
				Class.forName("org.sqlite.JDBC");
				conn = DriverManager.getConnection("jdbc:sqlite:src/main/resources/bd/Jugadores.bd");
				System.out.println("** La conexión se ha realizado con éxito.**\nInformación de la Metadata");
				DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
				System.out.println("\tDriver name: " + dm.getDriverName());
				System.out.println("\tDriver version: " + dm.getDriverVersion());
				System.out.println("\tProduct name: "+ dm.getDatabaseProductName());
				System.out.println("\tProduct version: "+ dm.getDatabaseProductVersion());
				System.out.println();
			} else
				System.out.println("La conexión ya se encuentra realizada.");
		} catch (ClassNotFoundException cnfe) {
			System.err.println("No se encuentra el Driver.");
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return conn;
	}

	public static void close() {
		try {
			if (conn != null) {
				conn.close();
				System.out.println("** La Desconexión de la BD exitosa. **");
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public static void main(String args[]) {
		SQLiteConnection.getConnection();
		SQLiteConnection.close();
	}

}