package cl.litscl.estructurageneralswingappmodel.fk;

import cl.litscl.estructurageneralswingappmodel.dao.CategoriaDAO;
import cl.litscl.estructurageneralswingappmodel.dto.Categoria;
import cl.litscl.estructurageneralswingappmodel.dto.Producto;

public class ProductoFK {
	private CategoriaDAO daoCategoria = new CategoriaDAO();
	private Categoria c = new Categoria();
	
	public int getCategoriaId(Producto p) {
		c = daoCategoria.find(p.getCategoriaFK());
		return c.getId();
	}
	
	public String getCategoriaNombre(Producto p) {
		c = daoCategoria.find(p.getCategoriaFK());	
		return c.getNombre();
	}
}
