package cl.litscl.estructurageneralswingappmodel.dto;

public class Producto {
	private String codigo;
	private String nombre;
	private double precio;
	private int categoriaFK;
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCategoriaFK() {
		return categoriaFK;
	}
	
	public void setCategoriaFK(int categoriaFK) {
		this.categoriaFK = categoriaFK;
	}
}
