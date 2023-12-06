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
import cl.litscl.estructurageneralswingappmodel.dao.UsuarioDAO;
import cl.litscl.estructurageneralswingappmodel.dto.Categoria;
import cl.litscl.estructurageneralswingappmodel.dto.Usuario;

public class MostrarUsuariosFrame extends JInternalFrame {
	private JTable tableUsuarios;
	private UsuarioDAO daoUsuario = new UsuarioDAO();
	private DefaultTableModel dtm = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MostrarUsuariosFrame frame = new MostrarUsuariosFrame();
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
	public MostrarUsuariosFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				List<Usuario> usuarios = daoUsuario.getAll();
				
				dtm.addColumn("ID");
				dtm.addColumn("Nombre");
				dtm.addColumn("Apellido");
				dtm.addColumn("Email");
				dtm.addColumn("Clave");
				dtm.addColumn("Tipo");
				
				for (Usuario u : usuarios) {
					Object []fila = new Object[6];
					fila[0] = u.getRut();
					fila[1] = u.getNombre();
					fila[2] = u.getApellido();
					fila[3] = u.getEmail();
					fila[4] = u.getClave();
					fila[5] = u.getTipo();
					dtm.addRow(fila);
				}	
				tableUsuarios.setModel(dtm);
			}
		});
		setTitle("Mostrar Usuarios");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPaneUsuarios = new JScrollPane();
		scrollPaneUsuarios.setBounds(10, 11, 664, 393);
		getContentPane().add(scrollPaneUsuarios);
		
		tableUsuarios = new JTable();
		tableUsuarios.setEnabled(false);
		scrollPaneUsuarios.setViewportView(tableUsuarios);

	}
}