package dao;

import java.io.IOException;
import java.sql.SQLException;

public abstract class DAOPERSONAJE<T> {
	public abstract void insertar(String user, T obj) throws SQLException,IOException;
	
	public abstract void actualizarCordenadasXY(String usuario, int cordX, int cordY) throws SQLException, IOException;
	public abstract void actualizarExperiencia(String usuario, int experiencia) throws SQLException, IOException;
	public abstract void actualizarDestreza(String usuario, int destreza) throws SQLException, IOException;
	public abstract void actualizarNivel(String usuario, int nivel) throws SQLException, IOException;
	public abstract void actualizarRazaCasta(String usuario, String raza,String casta) throws SQLException , IOException;
	public abstract void actualizarFuerza(String usuario, int fuerza) throws SQLException , IOException;
	public abstract void actualizarInteligencia(String usuario, int inteligencia) throws SQLException, IOException;
	public abstract void actualizarEnergia(String usuario, int energia) throws SQLException, IOException;
	public abstract void actualizarSalud(String usuario, int salud) throws SQLException, IOException;
	public abstract void actualizarMapa(String usuario, String mapa) throws SQLException, IOException;
	
	public abstract boolean buscar(String user) throws SQLException;
	public abstract void borrar(String user) throws SQLException, IOException;
	public abstract String seleccionarUsuario(String usuario) throws SQLException, IOException;
	public abstract void listarDatos() throws SQLException, IOException;
	public abstract void cerrar()throws IOException;





	
}