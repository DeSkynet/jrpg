package dao;

import java.sql.SQLException;

public abstract class DAOPERSONAJE<T> {
	public abstract void insertar(String user, String raza, String casta) throws SQLException;
	public abstract void actualizarCordenadasXY(String usuario, int cordX, int cordY) throws SQLException;
	public abstract void actualizarNivel(String usuario, int nivel) throws SQLException;
	public abstract void actualizarNivel(String usuario, String raza,String casta) throws SQLException ;
	public abstract boolean buscar(String user) throws SQLException;
	public abstract void borrar(String user) throws SQLException;
	public abstract String seleccionarUsuario(String usuario) throws SQLException;
	public abstract void listarDatos() throws SQLException;
	public abstract void cerrar();
}