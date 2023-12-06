package cl.litscl.estructurageneralswingapp.frame;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import cl.litscl.estructurageneralswingappmodel.dao.ProductoDAO;
import cl.litscl.estructurageneralswingappmodel.dto.Producto;
import cl.litscl.estructurageneralswingappmodel.fk.ProductoFK;

public class MostrarProductosFrame extends JInternalFrame {
	private JTable tableProductos;
	private ProductoDAO daoProducto = new ProductoDAO();
	private ProductoFK fkProducto = new ProductoFK();
	private DefaultTableModel dtm = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MostrarProductosFrame frame = new MostrarProductosFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MostrarProductosFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				List<Producto> productos = daoProducto.getAll();
				
				dtm.addColumn("Codigo");
				dtm.addColumn("Nombre");
				dtm.addColumn("Precio");
				dtm.addColumn("Categoria");
				
				for (Producto p : productos) {
					Object []fila = new Object[4];
					fila[0] = p.getCodigo();
					fila[1] = p.getNombre();
					fila[2] = p.getPrecio();
					fila[3] = fkProducto.getCategoriaNombre(p);
					dtm.addRow(fila);
				}	
				tableProductos.setModel(dtm);
			}
		});
		setTitle("Mostrar Productos");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPaneProductos = new JScrollPane();
		scrollPaneProductos.setBounds(10, 11, 664, 393);
		getContentPane().add(scrollPaneProductos);
		
		tableProductos = new JTable();
		tableProductos.setEnabled(false);
		scrollPaneProductos.setViewportView(tableProductos);

	}
}
