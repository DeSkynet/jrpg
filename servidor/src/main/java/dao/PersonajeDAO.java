package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import log.Log;
import personaje.Personaje;


public class PersonajeDAO extends DAOPERSONAJE<Personaje>{
	private Connection conexion = null;
	private static final String INSERTAR = "Insert into Personaje values(?,?,?,?,?,?,?,?,?,?,?,?,?);";
	private static final String BUSCAR = "select *from Personaje where Usuario = ?;";
	
	private static final String ACTUALIZARCOORDENADAXY = "update Personaje set CoordenadaX = ? , CoordenadaY = ? where Usuario = ?;";
	private static final String ACTUALIZAREXPERIENCIA = "update Personaje set Experiencia = ? where Usuario = ?;";
	private static final String ACTUALIZARNIVEL = "update Personaje set Nivel = ? where Usuario = ?;";
	private static final String ACTUALIZARDESTREZA = "update Personaje set Destreza = ? where Usuario = ?;";
	private static final String ACTUALIZARFUERZA = "update Personaje set Fuerza = ? where Usuario = ?;";
	private static final String ACTUALIZARINTELIGENCIA = "update Personaje set Inteligencia = ? where Usuario = ?;";
	private static final String ACTUALIZARENERGIA = "update Personaje set Energia = ? where Usuario = ?;";
	private static final String ACTUALIZARSALUD = "update Personaje set Salud = ? where Usuario = ?;";
	private static final String ACTUALIZARRAZACASTA = "update Personaje set Raza = ? , Casta = ? where Usuario = ?;";
	private static final String ACTUALIZARMAPA = "update Personaje set Mapa = ? where Usuario = ?;";
	
	
	private static final String ELIMINAR = "delete from Personaje where Usuario = ?;";
	private static final String VER_TODOS = "select *from Personaje;";
	private static final String VER_REGISTRO = "select *from Personaje where Usuario = ?;";
	
	public PersonajeDAO() throws IOException {
		conexion=SQLiteConnection.getConnection();
	}
	
	@Override
	public void insertar(String user, String raza, String casta) throws SQLException, IOException {
		PreparedStatement statement=null;
		try {
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
		} catch (Exception e) {
			Log.crearLog("Error: No se pudo insertar correctamente en la BBDD." + e.getMessage());
		}finally {
			try {
				statement.close();
			} catch (Exception e2) {
				Log.crearLog("Error: No se pudo cerrar correctamente el Statement." + e2.getMessage());
			}
		}
		
		
	}

	@Override
	public void actualizarDestreza(String usuario, int destreza) throws SQLException, IOException {
		PreparedStatement statement=null;
		try {
			statement = conexion.prepareStatement(ACTUALIZARDESTREZA);
			statement.setInt(1, destreza);
			statement.setString(3, usuario);
			statement.executeUpdate();
		} catch (Exception e) {
			Log.crearLog("Error: No se pudo actualizar correctamente en la BBDD." + e.getMessage());
		}finally {
			try {
				statement.close();
			} catch (Exception e2) {
				Log.crearLog("Error: No se pudo cerrar correctamente el Statement." + e2.getMessage());
			}
		}
		
		
	}
	
	@Override
	public void actualizarFuerza(String usuario, int fuerza) throws SQLException, IOException {
		PreparedStatement statement=null;
		try {
			statement = conexion.prepareStatement(ACTUALIZARFUERZA);
			statement.setInt(1, fuerza);
			statement.setString(3, usuario);
			statement.executeUpdate();
		} catch (Exception e) {
			Log.crearLog("Error: No se pudo actualizar correctamente en la BBDD." + e.getMessage());
		}finally {
			try {
				statement.close();
			} catch (Exception e2) {
				Log.crearLog("Error: No se pudo cerrar correctamente el Statement." + e2.getMessage());
			}
		}
		
		
	}
	
	@Override
	public void actualizarExperiencia(String usuario, int experiencia) throws SQLException, IOException {
		PreparedStatement statement=null;
		try {
			statement = conexion.prepareStatement(ACTUALIZAREXPERIENCIA);
			statement.setInt(1, experiencia);
			statement.setString(3, usuario);
			statement.executeUpdate();
		} catch (Exception e) {
			Log.crearLog("Error: No se pudo actualizar correctamente en la BBDD." + e.getMessage());
		}finally {
			try {
				statement.close();
			} catch (Exception e2) {
				Log.crearLog("Error: No se pudo cerrar correctamente el Statement." + e2.getMessage());
			}
		}
		
		
	}
	
	@Override
	public void actualizarSalud(String usuario, int salud) throws SQLException, IOException {
		PreparedStatement statement=null;
		try {
			statement = conexion.prepareStatement(ACTUALIZARSALUD);
			statement.setInt(1, salud);
			statement.setString(3, usuario);
			statement.executeUpdate();
			
		} catch (Exception e) {
			Log.crearLog("Error: No se pudo actualizar correctamente en la BBDD." + e.getMessage());
		}finally {
			try {
				statement.close();
			} catch (Exception e2) {
				Log.crearLog("Error: No se pudo cerrar correctamente el Statement." + e2.getMessage());
			}
		}
		
		
	}
	
	@Override
	public void actualizarInteligencia(String usuario, int inteligencia) throws SQLException, IOException {
		PreparedStatement statement=null;
		try {
			statement = conexion.prepareStatement(ACTUALIZARINTELIGENCIA);

		statement.setInt(1, inteligencia);
		statement.setString(3, usuario);
		statement.executeUpdate();
		} catch (Exception e) {
			Log.crearLog("Error: No se pudo actualizar correctamente en la BBDD." + e.getMessage());
		}finally {
			try {
				statement.close();
			} catch (Exception e2) {
				Log.crearLog("Error: No se pudo cerrar correctamente el Statement." + e2.getMessage());

			}
		}
		
		
	}
	
	@Override
	public void actualizarCordenadasXY(String usuario, int cordX, int cordY) throws SQLException, IOException {
		PreparedStatement statement=null;
		try {
			statement = conexion.prepareStatement(ACTUALIZARCOORDENADAXY);
			statement.setInt(1, cordX);
			statement.setInt(2, cordY);
			statement.setString(3, usuario);
			statement.executeUpdate();
		} catch (Exception e) {
			Log.crearLog("Error: No se pudo actualizar correctamente en la BBDD." + e.getMessage());
		}finally {
			try {
				statement.close();
			} catch (Exception e2) {
				Log.crearLog("Error: No se pudo cerrar correctamente el Statement." + e2.getMessage());
			}
		}
	}	
	
	@Override
	public void actualizarMapa(String usuario, String mapa) throws SQLException, IOException {
		PreparedStatement statement=null;
		try {
			statement = conexion.prepareStatement(ACTUALIZARMAPA);
			statement.setString(1, mapa);
			statement.setString(2, usuario);
			statement.executeUpdate();
		} catch (Exception e) {
			Log.crearLog("Error: No se pudo actualizar correctamente en la BBDD." + e.getMessage());
		}finally {
			try {
				statement.close();
			} catch (Exception e2) {
				Log.crearLog("Error: No se pudo cerrar correctamente el Statement." + e2.getMessage());
			}
		}
	}
	
	@Override
	public void actualizarNivel(String usuario, int nivel) throws SQLException, IOException {
		PreparedStatement statement=null;
		try {
			statement = conexion.prepareStatement(ACTUALIZARNIVEL);
			statement.setInt(1, nivel);
			statement.setString(2, usuario);
			statement.executeUpdate();
		} catch (Exception e) {
			Log.crearLog("Error: No se pudo actualizar correctamente en la BBDD." + e.getMessage());
		}finally {
			try {
				statement.close();
			} catch (Exception e2) {
				Log.crearLog("Error: No se pudo cerrar correctamente el Statement." + e2.getMessage());
			}
			
		}
		
	}
	
	@Override
	public void actualizarRazaCasta(String usuario, String raza,String casta) throws SQLException, IOException {
		PreparedStatement statement=null;
		try {
			statement = conexion.prepareStatement(ACTUALIZARRAZACASTA);
			statement.setString(1, raza);
			statement.setString(2, casta);
			statement.setString(3, usuario);
			statement.executeUpdate();
		} catch (Exception e) {
			Log.crearLog("Error: No se pudo actualizar correctamente en la BBDD." + e.getMessage());
		}finally {
			try {
				statement.close();
			} catch (Exception e2) {
				Log.crearLog("Error: No se pudo cerrar correctamente el Statement." + e2.getMessage());
			}
			
		}
		
	}
	
	@Override
	public void actualizarEnergia(String usuario, int energia) throws SQLException, IOException {
		PreparedStatement statement=null;
		
		try {
			statement = conexion.prepareStatement(ACTUALIZARENERGIA);
			statement.setInt(1, energia);
			statement.setString(3, usuario);
			statement.executeUpdate();
		} catch (Exception e) {
			Log.crearLog("Error: No se pudo actualizar correctamente en la BBDD." + e.getMessage());
		}finally {
			try {
				statement.close();
			} catch (Exception e2) {
				Log.crearLog("Error: No se pudo cerrar correctamente el Statement." + e2.getMessage());
			}
		}
		
	}
	

	@Override
	public boolean buscar(String user) throws SQLException {
		PreparedStatement statement=null;
		try {
			statement = conexion.prepareStatement(BUSCAR);
			statement.setString(1, user);
			ResultSet res = statement.executeQuery();
			if(!res.next()){
				statement.close();
				return false;
			}
			
			try {
				statement.close();	
				return true;
			} catch (Exception e) {
				return false;
			}
			
		} catch (Exception e) {
			statement.close();// TODO: handle exception
			return false;
		}
	}

	@Override
	public void borrar(String user) throws SQLException, IOException {
		PreparedStatement statement=null;
		try {
			statement = conexion.prepareStatement(ELIMINAR);
			statement.setString(1, user);
			statement.executeUpdate();
		} catch (Exception e) {
			Log.crearLog("Error: No se pudo eliminar correctamente en la BBDD." + e.getMessage());
		}finally {
			try {
				statement.close();
			} catch (Exception e2) {
				Log.crearLog("Error: No se pudo cerrar correctamente el Statement." + e2.getMessage());
			}
		}
		
		
	}
	
	//podria devolver un ArrayList de Jugadores...
	@Override
	public void listarDatos() throws SQLException, IOException {
		PreparedStatement statement=null;
		ResultSet res;
		try {	
			statement = conexion.prepareStatement(VER_TODOS);
			res = statement.executeQuery();
			while(res.next()) {
				System.out.println(res.getString(1) + "|" + res.getString(2) + "|"  + res.getString(3) + "|" + res.getString(4) + "|" + res.getString(5));
			}

		} catch (Exception e) {
			Log.crearLog("Error: No se pudo listar correctamente en la BBDD." + e.getMessage());
		}finally {
			statement.close();
		}
		
	}
	
	@Override
	public void cerrar() throws IOException {
		try {
			conexion.close();
		} catch (SQLException e) {
			Log.crearLog("Error: No se pudo cerrar correctamente la BBDD." + e.getMessage());
		}
	}


	@Override
	public String seleccionarUsuario(String usuario) throws SQLException {
		PreparedStatement statement=null;
		ResultSet res;
		try {
			statement=conexion.prepareStatement(VER_REGISTRO);
			statement.setString(1, usuario);
			res = statement.executeQuery();
			if( res.next()){
				String result=res.getString(1) + " " + res.getString(2) + " "  + res.getString(3) + " " + res.getString(4) + " " + res.getString(5) + " " + res.getString(6) + " " + res.getString(7) + " " + res.getString(8) + " " + res.getString(9) + " " + res.getString(10) + " " + res.getString(11) + " " + res.getString(12)+ " " + res.getString(13);
				statement.close();
				return result;
			}
			statement.close();
			return null;
			
		} catch (Exception e) {
			statement.close();
			return null;
		}
	}
	
	public Connection getConexion() {
		return conexion;
	}
}

