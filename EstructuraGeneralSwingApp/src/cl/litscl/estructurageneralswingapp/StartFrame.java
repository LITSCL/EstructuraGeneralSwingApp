package cl.litscl.estructurageneralswingapp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cl.litscl.estructurageneralswingapp.frame.CrearCategoriaFrame;
import cl.litscl.estructurageneralswingapp.frame.CrearProductoFrame;
import cl.litscl.estructurageneralswingapp.frame.CrearUsuarioFrame;
import cl.litscl.estructurageneralswingapp.frame.MostrarCategoriasFrame;
import cl.litscl.estructurageneralswingapp.frame.MostrarProductosFrame;
import cl.litscl.estructurageneralswingapp.frame.MostrarUsuariosFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import java.awt.event.ActionEvent;

public class StartFrame extends JFrame {
	private CrearCategoriaFrame crearCategoriaFrame;
	private CrearProductoFrame crearProductoFrame;
	private CrearUsuarioFrame crearUsuarioFrame;
	private MostrarCategoriasFrame mostrarCategoriasFrame;
	private MostrarProductosFrame mostrarProductosFrame;
	private MostrarUsuariosFrame mostrarUsuariosFrame;
	private JPanel contentPane;
	private JDesktopPane desktopPane;
	private String recursoImagen = "/cl/litscl/estructurageneralswingapp/resource/image/";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartFrame frame = new StartFrame();
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
	public StartFrame() {
		setTitle("EstructuraGeneralSwingApp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 652);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnOpciones = new JMenu("Opciones");
		mnOpciones.setIcon(new ImageIcon(StartFrame.class.getResource(recursoImagen + "set.png")));
		menuBar.add(mnOpciones);
		
		JMenuItem mntmCrearUsuario = new JMenuItem("Crear Usuario");
		mntmCrearUsuario.setIcon(new ImageIcon(StartFrame.class.getResource(recursoImagen + "usuario_add.png")));
		mntmCrearUsuario.addActionListener(e -> mostrarFrameCrearUsuario(e));
		mnOpciones.add(mntmCrearUsuario);
		
		JMenuItem mntmCrearProducto = new JMenuItem("Crear Producto");
		mntmCrearProducto.setIcon(new ImageIcon(StartFrame.class.getResource(recursoImagen + "producto_add.png")));
		mntmCrearProducto.addActionListener(e -> mostrarFrameCrearProducto(e));
		mnOpciones.add(mntmCrearProducto);
		
		JMenuItem mntmCrearCategoria = new JMenuItem("Crear Categoria");
		mntmCrearCategoria.setIcon(new ImageIcon(StartFrame.class.getResource(recursoImagen + "categoria_add.png")));
		mntmCrearCategoria.addActionListener(e -> mostrarFrameCrearCategoria(e));
		mnOpciones.add(mntmCrearCategoria);
		
		JMenuItem mntmMostrarUsuarios = new JMenuItem("Mostrar Usuarios");
		mntmMostrarUsuarios.setIcon(new ImageIcon(StartFrame.class.getResource(recursoImagen + "usuario_list.png")));
		mntmMostrarUsuarios.addActionListener(e -> mostrarFrameMostrarUsuarios(e));
		mnOpciones.add(mntmMostrarUsuarios);
		
		JMenuItem mntmMostrarProductos = new JMenuItem("Mostrar Productos");
		mntmMostrarProductos.setIcon(new ImageIcon(StartFrame.class.getResource(recursoImagen + "producto_list.png")));
		mntmMostrarProductos.addActionListener(e -> mostrarFrameMostrarProductos(e));
		mnOpciones.add(mntmMostrarProductos);
		
		JMenuItem mntmMostrarCategorias = new JMenuItem("Mostrar Categorias");
		mntmMostrarCategorias.setIcon(new ImageIcon(StartFrame.class.getResource(recursoImagen + "categoria_list.png")));
		mntmMostrarCategorias.addActionListener(e -> mostrarFrameMostrarCategorias(e));
		mnOpciones.add(mntmMostrarCategorias);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
	}

	private void mostrarFrameMostrarCategorias(ActionEvent e) {
		if (this.mostrarCategoriasFrame != null) {
			desktopPane.remove(this.mostrarCategoriasFrame);
		}
		
		this.mostrarCategoriasFrame = new MostrarCategoriasFrame();
		desktopPane.add(this.mostrarCategoriasFrame);
		this.mostrarCategoriasFrame.setVisible(true);
	}

	private void mostrarFrameMostrarProductos(ActionEvent e) {
		if (this.mostrarProductosFrame != null) {
			desktopPane.remove(this.mostrarProductosFrame);
		}
		
		this.mostrarProductosFrame = new MostrarProductosFrame();
		desktopPane.add(this.mostrarProductosFrame);
		this.mostrarProductosFrame.setVisible(true);
	}

	private void mostrarFrameMostrarUsuarios(ActionEvent e) {
		if (this.mostrarUsuariosFrame != null) {
			desktopPane.remove(this.mostrarUsuariosFrame);
		}
		
		this.mostrarUsuariosFrame = new MostrarUsuariosFrame();
		desktopPane.add(this.mostrarUsuariosFrame);
		this.mostrarUsuariosFrame.setVisible(true);
	}

	private void mostrarFrameCrearCategoria(ActionEvent e) {
		if (this.crearCategoriaFrame != null) {
			desktopPane.remove(this.crearCategoriaFrame);
		}
		
		this.crearCategoriaFrame = new CrearCategoriaFrame();
		desktopPane.add(this.crearCategoriaFrame);
		this.crearCategoriaFrame.setVisible(true);
	}

	private void mostrarFrameCrearProducto(ActionEvent e) {
		if (this.crearProductoFrame != null) {
			desktopPane.remove(this.crearProductoFrame);
		}
		
		this.crearProductoFrame = new CrearProductoFrame();
		desktopPane.add(this.crearProductoFrame);
		this.crearProductoFrame.setVisible(true);
	}

	private void mostrarFrameCrearUsuario(ActionEvent e) {
		if (this.crearUsuarioFrame != null) {
			desktopPane.remove(this.crearUsuarioFrame);
		}
		
		this.crearUsuarioFrame = new CrearUsuarioFrame();
		desktopPane.add(this.crearUsuarioFrame);
		this.crearUsuarioFrame.setVisible(true);
	}

}
