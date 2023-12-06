package cl.litscl.estructurageneralswingapp.frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import cl.litscl.estructurageneralswingapp.util.StartAudioUtil;
import cl.litscl.estructurageneralswingappmodel.dao.CategoriaDAO;
import cl.litscl.estructurageneralswingappmodel.dao.ProductoDAO;
import cl.litscl.estructurageneralswingappmodel.dto.Categoria;
import cl.litscl.estructurageneralswingappmodel.dto.Producto;

public class CrearProductoFrame extends JInternalFrame {
	private JTextField textFieldNombre;
	private JTextField textFieldCodigo;
	private JComboBox<Categoria> comboBoxCategoria;
	private JSpinner spinnerPrecio;
	private CategoriaDAO daoCategoria = new CategoriaDAO();
	private ProductoDAO daoProducto = new ProductoDAO();
	private String recursoImagen = "/cl/litscl/estructurageneralswingapp/resource/image/";
	private StartAudioUtil sau = new StartAudioUtil();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearProductoFrame frame = new CrearProductoFrame();
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
	public CrearProductoFrame() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
				List<Categoria> categorias = daoCategoria.getAll();
				
				for (Categoria c : categorias) {
					comboBoxCategoria.addItem(c);
				}
			}
		});
		setTitle("Crear Producto");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Codigo");
		lblNewLabel.setBounds(10, 33, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(10, 58, 58, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Precio");
		lblNewLabel_2.setBounds(10, 83, 46, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Categoria");
		lblNewLabel_3.setBounds(10, 108, 72, 14);
		getContentPane().add(lblNewLabel_3);
		
		comboBoxCategoria = new JComboBox<Categoria>();
		comboBoxCategoria.setBounds(73, 105, 86, 20);
		getContentPane().add(comboBoxCategoria);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(73, 55, 86, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.setBounds(73, 30, 86, 20);
		getContentPane().add(textFieldCodigo);
		textFieldCodigo.setColumns(10);
		
		spinnerPrecio = new JSpinner();
		spinnerPrecio.setBounds(73, 80, 86, 20);
		getContentPane().add(spinnerPrecio);
		
		JButton btnCrearProducto = new JButton("Crear");
		btnCrearProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnCrearProducto.setIcon(new ImageIcon(CrearCategoriaFrame.class.getResource(recursoImagen + "add.png")));
		btnCrearProducto.addActionListener(e -> crearCategoria(e));
		btnCrearProducto.setBounds(585, 381, 89, 23);
		getContentPane().add(btnCrearProducto);

	}

	private void crearCategoria(ActionEvent e) {
		sau.reproducir("SonidoBoton", "wav");
		
		String codigo = textFieldCodigo.getText();
		String nombre = textFieldNombre.getText();
		int precio = (int)spinnerPrecio.getValue();
		int categoriaFK = ((Categoria)comboBoxCategoria.getSelectedItem()).getId();
		
		Producto p = new Producto();
		p.setCodigo(codigo);
		p.setNombre(nombre);
		p.setPrecio(precio);
		p.setCategoriaFK(categoriaFK);
		
		if (daoProducto.save(p)) {
			JOptionPane.showMessageDialog(null, "Producto creado correctamente");
			this.dispose();
		}
		else {
			JOptionPane.showMessageDialog(null, "Error al crear el producto", "Error DB", JOptionPane.ERROR_MESSAGE);
		}
	}
}
