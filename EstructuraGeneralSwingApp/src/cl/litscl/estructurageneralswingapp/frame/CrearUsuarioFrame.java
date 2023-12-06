package cl.litscl.estructurageneralswingapp.frame;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cl.litscl.estructurageneralswingapp.util.StartAudioUtil;
import cl.litscl.estructurageneralswingapp.util.RutUtil;
import cl.litscl.estructurageneralswingappmodel.dao.UsuarioDAO;
import cl.litscl.estructurageneralswingappmodel.dto.Usuario;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class CrearUsuarioFrame extends JInternalFrame {
	private JTextField textFieldNombre;
	private JTextField textFieldRut;
	private JTextField textFieldApellido;
	private JTextField textFieldEmail;
	private JTextField textFieldTipo;
	private JPasswordField passwordFieldClave;
	private RutUtil rutUtil = new RutUtil();
	private String recursoImagen = "/cl/litscl/estructurageneralswingapp/resource/image/";
	private StartAudioUtil sau = new StartAudioUtil();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearUsuarioFrame frame = new CrearUsuarioFrame();
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
	public CrearUsuarioFrame() {
		setTitle("Crear Usuario");
		setClosable(true);
		setBounds(100, 100, 700, 445);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Rut");
		lblNewLabel.setBounds(10, 33, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(10, 58, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Apellido");
		lblNewLabel_2.setBounds(10, 83, 46, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setBounds(10, 108, 46, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Clave");
		lblNewLabel_4.setBounds(10, 133, 46, 14);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Tipo");
		lblNewLabel_5.setBounds(10, 158, 46, 14);
		getContentPane().add(lblNewLabel_5);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(59, 55, 86, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldRut = new JTextField();
		textFieldRut.setBounds(59, 30, 86, 20);
		getContentPane().add(textFieldRut);
		textFieldRut.setColumns(10);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(59, 80, 86, 20);
		getContentPane().add(textFieldApellido);
		textFieldApellido.setColumns(10);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(59, 105, 86, 20);
		getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		textFieldTipo = new JTextField();
		textFieldTipo.setBounds(59, 155, 86, 20);
		getContentPane().add(textFieldTipo);
		textFieldTipo.setColumns(10);
		
		passwordFieldClave = new JPasswordField();
		passwordFieldClave.setBounds(59, 130, 86, 20);
		getContentPane().add(passwordFieldClave);
		
		JButton btnCrearUsuario = new JButton("Crear");
		btnCrearUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				sau.reproducir("SonidoEntrandoBoton", "wav");
			}
		});
		btnCrearUsuario.setIcon(new ImageIcon(CrearCategoriaFrame.class.getResource(recursoImagen + "add.png")));
		btnCrearUsuario.addActionListener(e -> crearUsuario(e));
		btnCrearUsuario.setBounds(585, 381, 89, 23);
		getContentPane().add(btnCrearUsuario);

	}

	private void crearUsuario(ActionEvent e) {
		sau.reproducir("SonidoBoton", "wav");
		
		List<String> errores = new ArrayList<String>();
		
		String rut = textFieldRut.getText();
		String nombre = textFieldNombre.getText();
		String apellido = textFieldApellido.getText();
		String email = textFieldEmail.getText();
		String clave = passwordFieldClave.getText();
		String tipo = textFieldTipo.getText();
		
		if (rutUtil.validarRutChileno(rut) == false) {
			errores.add("El rut ingresado no posee formato válido");
		}
				
		if (errores.isEmpty()) {
			Usuario u = new Usuario();
			u.setRut(rut);
			u.setNombre(nombre);
			u.setApellido(apellido);
			u.setEmail(email);
			u.setClave(clave);
			u.setTipo(tipo);	
			
			UsuarioDAO daoUsuario = new UsuarioDAO();
			
			if (daoUsuario.save(u)) {
				JOptionPane.showMessageDialog(null, "Usuario creado correctamente");
				this.dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "Error al crear el usuario", "Error DB", JOptionPane.ERROR_MESSAGE);
			}					
		}
		else {
			String mensaje = "";
			for (int i = 0 ; i<errores.size() ; i++) {
				mensaje+= "\n" + "- " + errores.get(i);
			}
			JOptionPane.showMessageDialog(null, mensaje, "Error de validación", JOptionPane.WARNING_MESSAGE);
		}
	}
}
