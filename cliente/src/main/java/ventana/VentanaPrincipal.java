package ventana;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField usuario;
	private JTextField pass;
	private JButton botonIniciar;
	private JButton registrarUsuario;
	private VentanaRegistro ventanaRegistro;


	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JLabel lblUsuario = new JLabel("USUARIO");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsuario.setBounds(56, 136, 108, 33);
		contentPane.add(lblUsuario);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(56, 251, 108, 33);
		contentPane.add(lblPassword);
		
		JLabel lblMensajeria = new JLabel("MENSAJERIA");
		lblMensajeria.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 26));
		lblMensajeria.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensajeria.setBounds(204, 16, 179, 24);
		contentPane.add(lblMensajeria);
		
		usuario = new JTextField();
		usuario.setBounds(258, 137, 210, 35);
		contentPane.add(usuario);
		usuario.setColumns(10);
		
		pass = new JTextField();
		pass.setBounds(258, 252, 210, 35);
		contentPane.add(pass);
		pass.setColumns(10);
		
		botonIniciar = new JButton("INICIAR SESION");
		botonIniciar.setFont(new Font("Tahoma", Font.BOLD, 16));
		botonIniciar.setBounds(301, 416, 167, 35);
		contentPane.add(botonIniciar);
		
		registrarUsuario = new JButton("REGISTRAR");
		registrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirVentanaRegistro();
				 
			}
		});
		registrarUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		registrarUsuario.setBounds(79, 416, 143, 35);
		contentPane.add(registrarUsuario);
	}
	
	private void abrirVentanaRegistro() {
		ventanaRegistro = new VentanaRegistro(this);
		ventanaRegistro.setVisible(true);
	}
	
	
	public void recibirDeVentanaRegistro(String usuario, String pass) {
		System.out.println(usuario + pass);
		//this.cliente = new Cliente(usuario);
	}
}
