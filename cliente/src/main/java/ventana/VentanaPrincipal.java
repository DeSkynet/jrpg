package ventana;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import cliente.HiloCliente;
import herramientas.Constantes;
import mensajes.Mensaje;
import mensajes.MensajeLogIn;
import pantalla.Juego;


@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {
	private Cliente cliente;
	private JPanel contentPane;
	private JTextField usuario;
	private JTextField pass;
	private JButton botonIniciar;
	private JButton registrarUsuario;
	private VentanaRegistro ventanaRegistro;
	private VentanaEleccion ventanaEleccion;

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal(Cliente cliente) {
		
		this.cliente = cliente;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon(Constantes.PATH_ICONO).getImage());
		setSize(600, 600);
		contentPane = new JPanel();
		setTitle("The Alliance");
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
		
		JLabel lblMensajeria = new JLabel("INICIO");
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
		botonIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				usuario.setText(usuario.getText().trim());
				pass.setText(pass.getText().trim());
				if(!usuario.getText().isEmpty() && !pass.getText().isEmpty()) {
					envioSession(usuario.getText(),pass.getText());
					Mensaje mensaje=recibirConfirmacionDeAcceso();
					if(mensaje.getTipoMensaje().equals("MensajeConfirmacion"))
						if(mensaje.getObjeto().equals(true)){
							JOptionPane.showMessageDialog(null, "Iniciar Sesion.");
							//ABRO LA PROXIMA VENTANA DEL JUEGO.
							VentanaPrincipal.this.cliente.setNombre(usuario.getText());
							Juego juego = new Juego(VentanaPrincipal.this.cliente);
							juego.iniciar();
							crearHiloEscucha();
							dispose();
						}
						else{
							JOptionPane.showMessageDialog(null, "El usuario y/o la contaseña no son validas.");
							usuario.setText("");
							pass.setText("");
						}
					else if (mensaje.getTipoMensaje().equals("EleccionPersonaje")) {
						
						JOptionPane.showMessageDialog(null, "Iniciar Sesion.");
						ventanaEleccion = new VentanaEleccion(usuario.getText(),getCliente());
						ventanaEleccion.setVisible(true);
						crearHiloEscucha();
						dispose();
						
					}
					
				}
				else
					JOptionPane.showMessageDialog(null, "Los campos no pueden quedar vacios.", "Advertencia", JOptionPane.WARNING_MESSAGE);
			}
		});
		botonIniciar.setFont(new Font("Tahoma", Font.BOLD, 16));
		botonIniciar.setBounds(289, 416, 179, 35);
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
		this.cliente.enviarMensaje("Registro", new MensajeLogIn(usuario, pass));
	}
	
	public void envioSession(String usuario, String pass){
		this.cliente.enviarMensaje("MensajeLogIn", new MensajeLogIn(usuario, pass));
	}
	
	public Mensaje recibirConfirmacionDeAcceso(){
		Mensaje men=this.cliente.recibeMensaje();
//		if(men.getTipoMensaje().equals("MensajeConfirmacion"))
//			return (boolean)men.getObjeto();
//		return false;
		return men;
	}
	
	public boolean recibirConfirmacion(){
		Mensaje men=this.cliente.recibeMensaje();
		if(men.getTipoMensaje().equals("MensajeConfirmacion"))
			return (boolean)men.getObjeto();
		return false;
	}
	
	public void crearHiloEscucha(){
		HiloCliente hiloCliente = new HiloCliente(cliente,cliente.getSocket());
	    hiloCliente.start();
	}
	protected Cliente getCliente(){
		return this.cliente;
	}
}
