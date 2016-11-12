package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import personaje.Personaje;


public class PersonajeDAO extends DAOPERSONAJE<Personaje>{
	private Connection conexion = null;
	private PreparedStatement statement;
	private static final String PATH_CONNECTION = "jdbc:sqlite:src/main/resources/bd/Personaje.bd";
	private static final String INSERTAR = "Insert into Personaje values(?,?,?,?,?,?,?,?,?,?,?,?,?);";
	private static final String BUSCAR = "select *from Personaje where Usuario = ?;";
	private static final String ACTUALIZARCOORDENADAXY = "update Personaje set CordenadaX = ? set CordenadaY = ? where Usuario = ?;";
	private static final String ACTUALIZAREXPERIENCIA = "update Personaje set Experiencia = ? where Usuario = ?;";
	private static final String ACTUALIZARNIVEL = "update Personaje set Nivel = ? where Usuario = ?;";
	
	private static final String ACTUALIZARDESTREZA = "update Personaje set Nivel = ? where Usuario = ?;";
	private static final String ACTUALIZARFUERZA = "update Personaje set Nivel = ? where Usuario = ?;";
	private static final String ACTUALIZARINTELIGENCIA = "update Personaje set Nivel = ? where Usuario = ?;";
	private static final String ACTUALIZARENERGIA = "update Personaje set Energia = ? where Usuario = ?;";
	private static final String ACTUALIZARSALUD = "update Personaje set Salud = ? where Usuario = ?;";
	private static final String ACTUALIZARRAZACASTA = "update Personaje set Raza = ? set Casta = ? where Usuario = ?;";
	
	
	
	private static final String ELIMINAR = "delete from Personaje where Usuario = ?;";
	private static final String VER_TODOS = "select *from Personaje;";
	private static final String VER_REGISTRO = "select *from Personaje where Usuario = ?;";
	
	public PersonajeDAO() {
		if(conexion==null) {
			try {
				conexion = DriverManager.getConnection(PATH_CONNECTION);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void insertar(String user, String raza, String casta) throws SQLException {
		statement = conexion.prepareStatement(INSERTAR);
		
		statement.setString(1, user);
		statement.setInt(2, 0);	//CordenadaX
		statement.setInt(3, 0);	//CordenadaY
		statement.setInt(4, 0); //Experiencia
		statement.setInt(5, 1); //Nivel
		statement.setInt(6, 0);	//DESTREZA
		statement.setInt(7, 0);	//Fuerza
		statement.setInt(8, 0);	//Inteligencia
		statement.setInt(9, 100);	//Energia
		statement.setInt(10, 100);	//Salud
		statement.setString(11, casta);	//Casta
		statement.setString(12, raza);	//Raza	
		statement.setString(13, "");	//Raza	
		statement.execute();
		
	}

	@Override
	public void actualizarCordenadasXY(String usuario, int cordX, int cordY) throws SQLException {
		statement = conexion.prepareStatement(ACTUALIZARCOORDENADAXY);

		statement.setInt(1, cordX);
		statement.setInt(2, cordY);
		statement.setString(3, usuario);
		statement.executeUpdate();
		
	}
	
	@Override
	public void actualizarNivel(String usuario, int nivel) throws SQLException {
		statement = conexion.prepareStatement(ACTUALIZARNIVEL);

		statement.setInt(1, nivel);
		statement.setString(2, usuario);
		statement.executeUpdate();
	}
	
	@Override
	public void actualizarNivel(String usuario, String raza,String casta) throws SQLException {
		statement = conexion.prepareStatement(ACTUALIZARRAZACASTA);

		statement.setString(1, raza);
		statement.setString(2, casta);
		statement.setString(3, usuario);
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
			System.out.println(res.getString(1) + "|" + res.getString(2) + "|"  + res.getDouble(3) + "|" + res.getString(4) + "|" + res.getString(5));
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
			return res.getString(1) + " " + res.getString(2) + " "  + res.getDouble(3) + " " + res.getString(4) + " " + res.getString(5) + " " + res.getString(6) + " " + res.getString(7) + " " + res.getString(8) + " " + res.getString(9) + " " + res.getString(10) + " " + res.getString(11) + " " + res.getString(12);
		return null;
	}
	
	public Connection getConexion() {
		return conexion;
	}
}

