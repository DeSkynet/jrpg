package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import personaje.Personaje;

public class JugadorDAO extends DAO<Personaje>{
	private Connection conexion = null;
	private PreparedStatement statement;
	private static final String PATH_CONNECTION = "jdbc:sqlite:src/main/resources/bd/PruebaBD.bd";
	private static final String INSERTAR = "Insert into Jugador values(?,?,?);";
	private static final String BUSCAR = "select *from Jugador where Usuario = ?;";
	private static final String ACTUALIZAR = "update Jugador set Activo = ? where Usuario = ?;";
	private static final String ELIMINAR = "delete from Jugador where Usuario = ?;";
	private static final String VER_TODOS = "select *from Jugador;";
	private static final String VER_REGISTRO = "select *from Jugador where Usuario = ?;";
	
	
	public JugadorDAO() {
		if(conexion==null) {
			try {
				conexion = DriverManager.getConnection(PATH_CONNECTION);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void insertar(String user, String pass, Personaje obj) throws SQLException {
		statement = conexion.prepareStatement(INSERTAR);
		
		statement.setString(1, user);
		statement.setString(2, pass);
		statement.setBoolean(3, false);
		
		statement.execute();
		
	}

	@Override
	public void actualizar(String usuario, boolean activo) throws SQLException {
		statement = conexion.prepareStatement(ACTUALIZAR);
		
		statement.setString(1, usuario);
		statement.setBoolean(2, activo);
		
		statement.executeUpdate();
		
	}

	@Override
	public boolean buscar(String user) throws SQLException {
		statement = conexion.prepareStatement(BUSCAR);
		statement.setString(1, user);
		ResultSet res = statement.executeQuery();

		if(!res.next())
			return false;
		
		return true;
	}

	@Override
	public void borrar(String user) throws SQLException {
		statement = conexion.prepareStatement(ELIMINAR);
		
		statement.setString(1, user);
		
		statement.executeUpdate();
		
	}
	
	//podria devolver un ArrayList de Jugadores...
	@Override
	public void listarDatos() throws SQLException {
		ResultSet res;
		statement = conexion.prepareStatement(VER_TODOS);
		
		res = statement.executeQuery();
		
		while(res.next()) {
			System.out.println(res.getString(1) + "|" + res.getString(2) + "|"  + res.getDouble(3));
		}
	}

	@Override
	public void cerrar() {
		try {
			conexion.close();
		} catch (SQLException e) {
			e.getSQLState();
		}
	}

	@Override
	public String seleccionarUsuario(String usuario) throws SQLException {
		ResultSet res;
		statement=conexion.prepareStatement(VER_REGISTRO);
		statement.setString(1, usuario);
		res = statement.executeQuery();
		if( res.next())
			return res.getString(1) + " " + res.getString(2) + " "  + res.getDouble(3);
		return null;
	}

	public Connection getConexion() {
		return conexion;
	}
	
}
