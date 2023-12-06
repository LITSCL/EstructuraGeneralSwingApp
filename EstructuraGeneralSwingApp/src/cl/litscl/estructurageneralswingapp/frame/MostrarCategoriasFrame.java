package cl.litscl.estructurageneralswingapp.frame;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import cl.litscl.estructurageneralswingappmodel.dao.CategoriaDAO;
import cl.litscl.estructurageneralswingappmodel.dto.Categoria;

public class MostrarCategoriasFrame extends JInternalFrame {
	private JTable tableCategorias;
	private CategoriaDAO daoCategoria = new CategoriaDAO();
	private DefaultTableModel dtm = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MostrarCategoriasFrame frame = new MostrarCategoriasFrame();
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
	public MostrarCategoriasFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				List<Categoria> categorias = daoCategoria.getAll();
				
				dtm.addColumn("ID");
				dtm.addColumn("Nombre");
				
				for (Categoria c : categorias) {
					Object []fila = new Object[3];
					fila[0] = c.getId();
					fila[1] = c.getNombre();
					dtm.addRow(fila);
				}	
				tableCategorias.setModel(dtm);
			}
		});
		setTitle("Mostrar Categorías");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPaneCategorias = new JScrollPane();
		scrollPaneCategorias.setBounds(10, 11, 664, 393);
		getContentPane().add(scrollPaneCategorias);
		
		tableCategorias = new JTable();
		tableCategorias.setEnabled(false);
		scrollPaneCategorias.setViewportView(tableCategorias);

	}
}
