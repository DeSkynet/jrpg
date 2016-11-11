package dao;

import java.sql.SQLException;


// SUJETO A CAMBIOS
public abstract class DAO<T> {
	public abstract void insertar(String user,String pass,T obj) throws SQLException;
//	public abstract void actualizar(T obj) throws SQLException;
	public abstract void actualizar(String usuario, boolean activo) throws SQLException;
	public abstract boolean buscar(String user) throws SQLException;
	public abstract void borrar(String user) throws SQLException;
	public abstract String seleccionarUsuario(String usuario) throws SQLException;
	public abstract void listarDatos() throws SQLException;
	public abstract void cerrar();
}
