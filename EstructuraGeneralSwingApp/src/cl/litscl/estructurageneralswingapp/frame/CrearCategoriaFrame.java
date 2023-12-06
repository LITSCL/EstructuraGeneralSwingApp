package cl.litscl.estructurageneralswingapp.frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cl.litscl.estructurageneralswingapp.util.StartAudioUtil;
import cl.litscl.estructurageneralswingappmodel.dao.CategoriaDAO;
import cl.litscl.estructurageneralswingappmodel.dto.Categoria;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CrearCategoriaFrame extends JInternalFrame {
	private JTextField textFieldNombre;
	private String recursoImagen = "/cl/litscl/estructurageneralswingapp/resource/image/";
	private StartAudioUtil sau = new StartAudioUtil();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearCategoriaFrame frame = new CrearCategoriaFrame();
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
	public CrearCategoriaFrame() {
		setTitle("Crear Categoría");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(10, 33, 46, 14);
		getContentPane().add(lblNewLabel);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(57, 30, 86, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JButton btnCrearCategoria = new JButton("Crear");
		btnCrearCategoria.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnCrearCategoria.setIcon(new ImageIcon(CrearCategoriaFrame.class.getResource(recursoImagen + "add.png")));
		btnCrearCategoria.addActionListener(e -> crearCategoria(e));
		btnCrearCategoria.setBounds(585, 381, 89, 23);
		getContentPane().add(btnCrearCategoria);
		
	}

	private void crearCategoria(ActionEvent e) {
		sau.reproducir("SonidoBoton", "wav");
		
		String nombre = textFieldNombre.getText();
		
		Categoria c = new Categoria();
		c.setNombre(nombre);
		
		CategoriaDAO daoCategoria = new CategoriaDAO();
		
		if (daoCategoria.save(c)) {
			JOptionPane.showMessageDialog(null, "Categoria creada correctamente");
			this.dispose();
		}
		else {
			JOptionPane.showMessageDialog(null, "Error al crear la categoria", "Error DB", JOptionPane.ERROR_MESSAGE);
		}
	}
}
