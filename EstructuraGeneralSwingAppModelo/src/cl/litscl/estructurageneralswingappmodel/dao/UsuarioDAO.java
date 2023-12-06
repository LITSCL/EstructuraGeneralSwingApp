package cl.litscl.estructurageneralswingappmodel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cl.litscl.estructurageneralswingappmodel.dto.Producto;
import cl.litscl.estructurageneralswingappmodel.dto.Usuario;
import cl.litscl.estructurageneralswingappmodel.util.BDUtil;

public class UsuarioDAO {
	private BDUtil bdUtil = new BDUtil();
	
	public boolean save(Usuario u) {
		boolean resultado;
		try {
			//1. Conectarse a la base de datos.
			bdUtil.conectar();
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			//2. Definir la sentencia SQL (INSERT).
			String sql = "INSERT INTO usuario(rut, nombre, apellido, email, clave, tipo) VALUES(?, ?, ?, ?, ?, ?)";
			//3. Preparar la sentencia SQL.
			Connection conexion = bdUtil.getConexion();
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(1, u.getRut());
			ps.setString(2, u.getNombre());
			ps.setString(3, u.getApellido());
			ps.setString(4, u.getEmail());
			ps.setString(5, u.getClave());
			ps.setString(6, u.getTipo());
			//4. Ejecutar la sentencia SQL.
			ps.executeUpdate();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
			//5. Desconectarse.
		} finally { 
			bdUtil.desconectar(); //Envía la petición de desconexión al dbms.
		}
		return resultado;
	}
	
	public List<Usuario> getAll() {
		boolean resultado;
		List<Usuario> usuarios = new ArrayList<Usuario>(); //La lista ya no debe estar arriba estatica ya que el contenido va a variar.
		try {
			//1. Conectarse a la base de datos.
			bdUtil.conectar();
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			//2. Definir la sentencia SQL (SELECT).
			String sql = "SELECT * FROM usuario";
			//3. Preparar la sentencia SQL.
			Connection conexion = bdUtil.getConexion();
			PreparedStatement ps = conexion.prepareStatement(sql);
			//4. Ejecutar la sentencia SQL.
			ResultSet rs = ps.executeQuery();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			while (rs.next()) { //Se repite mientras avance el puntero.
				Usuario u = new Usuario();
				u.setRut(rs.getString(1));
				u.setNombre(rs.getString(2));
				u.setApellido(rs.getString(3));
				u.setEmail(rs.getString(4));
				u.setClave(rs.getString(5));
				u.setTipo(rs.getString(6));
		
				usuarios.add(u); //Añade el producto a la lista.
			}
			rs.close(); //Se cierra el puntero.
		} catch (Exception ex) {
			usuarios = null;
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
			//5. Desconectarse.
		} finally { 
			bdUtil.desconectar(); //Envía la petición de desconexión al dbms.
		}
		return usuarios;
	}
	
	public Usuario find(String rut) {
		boolean resultado;
		Usuario u = new Usuario();
		try {
			//1. Conectarse a la base de datos.
			bdUtil.conectar();
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			//2. Definir la sentencia SQL (SELECT).
			String sql = "SELECT * FROM usuario WHERE rut = ?";
			//3. Preparar la sentencia SQL.
			Connection conexion = bdUtil.getConexion();
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(1, rut);
			//4. Ejecutar la sentencia SQL.
			ResultSet rs = ps.executeQuery();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			while (rs.next()) { //Se repite mientras avance el puntero.
				u.setRut(rs.getString(1));
				u.setNombre(rs.getString(2));
				u.setApellido(rs.getString(3));
				u.setEmail(rs.getString(4));
				u.setClave(rs.getString(5));
				u.setTipo(rs.getString(6));
			}
			rs.close(); //Se cierra el puntero.
		} catch (Exception ex) {
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
			return null;
			//5. Desconectarse.
		} finally { 
			bdUtil.desconectar(); //Envía la petición de desconexión al dbms.
		}
		return u;
	}
}
