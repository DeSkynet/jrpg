package dao;

import java.sql.SQLException;

public abstract class DAOPERSONAJE<T> {
	public abstract void insertar(String user, String raza, String casta) throws SQLException;
	
	public abstract void actualizarCordenadasXY(String usuario, int cordX, int cordY) throws SQLException;
	public abstract void actualizarExperiencia(String usuario, int experiencia) throws SQLException;
	public abstract void actualizarDestreza(String usuario, int destreza) throws SQLException;
	public abstract void actualizarNivel(String usuario, int nivel) throws SQLException;
	public abstract void actualizarRazaCasta(String usuario, String raza,String casta) throws SQLException ;
	public abstract void actualizarFuerza(String usuario, int fuerza) throws SQLException ;
	public abstract void actualizarInteligencia(String usuario, int inteligencia) throws SQLException;
	public abstract void actualizarEnergia(String usuario, int energia) throws SQLException;
	public abstract void actualizarSalud(String usuario, int salud) throws SQLException;
	public abstract void actualizarMapa(String usuario, String mapa) throws SQLException;
	
	public abstract boolean buscar(String user) throws SQLException;
	public abstract void borrar(String user) throws SQLException;
	public abstract String seleccionarUsuario(String usuario) throws SQLException;
	public abstract void listarDatos() throws SQLException;
	public abstract void cerrar();





	
}