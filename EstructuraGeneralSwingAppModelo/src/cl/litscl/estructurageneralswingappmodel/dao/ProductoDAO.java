package cl.litscl.estructurageneralswingappmodel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cl.litscl.estructurageneralswingappmodel.dto.Producto;
import cl.litscl.estructurageneralswingappmodel.util.BDUtil;

public class ProductoDAO {
	private BDUtil bdUtil = new BDUtil();
	
	public boolean save(Producto p) {
		boolean resultado;
		try {
			//1. Conectarse a la base de datos.
			bdUtil.conectar();
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			//2. Definir la sentencia SQL (INSERT).
			String sql = "INSERT INTO producto(codigo, nombre, precio, categoria_id) VALUES(?, ?, ?, ?)"; //Los ID Autoincrementales no van aca, ya que el dbms asigna su valor.
			//3. Preparar la sentencia SQL.
			Connection conexion = bdUtil.getConexion();
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(1, p.getCodigo());
			ps.setString(2, p.getNombre());
			ps.setDouble(3, p.getPrecio());
			ps.setInt(4, p.getCategoriaFK());
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
	
	public List<Producto> getAll() {
		boolean resultado;
		List<Producto> productos = new ArrayList<Producto>(); //La lista ya no debe estar arriba estatica ya que el contenido va a variar.
		try {
			//1. Conectarse a la base de datos.
			bdUtil.conectar();
			System.out.println("Conexión a la DB: " + bdUtil.conectar());
			//2. Definir la sentencia SQL (SELECT).
			String sql = "SELECT * FROM producto";
			//3. Preparar la sentencia SQL.
			Connection conexion = bdUtil.getConexion();
			PreparedStatement ps = conexion.prepareStatement(sql);
			//4. Ejecutar la sentencia SQL.
			ResultSet rs = ps.executeQuery();
			resultado = true;
			System.out.println("Ejecución del SQL: " + resultado);
			while (rs.next()) { //Se repite mientras avance el puntero.
				Producto p = new Producto();
				p.setCodigo(rs.getString(1));
				p.setNombre(rs.getString(2));
				p.setPrecio(rs.getDouble(3));
				p.setCategoriaFK(rs.getInt(4));
		
				productos.add(p); //Añade el producto a la lista.
			}
			rs.close(); //Se cierra el puntero.
		} catch (Exception ex) {
			productos = null;
			resultado = false;
			System.out.println("Ejecución del SQL: " + resultado);
			//5. Desconectarse.
		} finally { 
			bdUtil.desconectar(); //Envía la petición de desconexión al dbms.
		}
		return productos;
	}
}
