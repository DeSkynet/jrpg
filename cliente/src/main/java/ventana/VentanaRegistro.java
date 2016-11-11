package ventana;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class VentanaRegistro extends JFrame {

	private VentanaPrincipal ventanaPrincipal;
	private JPanel contentPane;
	private JTextField usuarioRegistro;
	private JTextField passRegistro;
	private JButton botonRegistro;
	private JLabel labelUsuario;
	@SuppressWarnings("unused")
	private JLabel labelPassword;

	/**
	 * Create the frame.
	 */
	public VentanaRegistro(VentanaPrincipal padre) {
		this.ventanaPrincipal = padre;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500,500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("Registro");
		setLocationRelativeTo(padre);
		contentPane.setLayout(null);
		setResizable(false);
		
		labelUsuario = new JLabel("Usuario");
		labelUsuario.setBounds(59, 70, 69, 20);
		contentPane.add(labelUsuario);
		
		labelUsuario = new JLabel("Password");
		labelUsuario.setBounds(59, 158, 69, 20);
		contentPane.add(labelUsuario);
		
		botonRegistro = new JButton("REGISTRAR");
		botonRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!usuarioRegistro.getText().isEmpty() && !passRegistro.getText().isEmpty()) {
					enviarRegistroAPrincipal(usuarioRegistro.getText() , passRegistro.getText());
					if(ventanaPrincipal.recibirConfirmacion()){
						JOptionPane.showMessageDialog(null, "El registro ha sido correctamente creado.");
						dispose();
					}
					else{
						JOptionPane.showMessageDialog(null, "El usuario no esta disponible.");
						passRegistro.setText("");
						usuarioRegistro.setText("");
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Los campos no pueden quedar vacios.", "Advertencia", JOptionPane.ERROR_MESSAGE);
			}
		});
		botonRegistro.setBounds(184, 304, 131, 29);
		contentPane.add(botonRegistro);
		
		usuarioRegistro = new JTextField();
		usuarioRegistro.setBounds(204, 67, 192, 26);
		contentPane.add(usuarioRegistro);
		usuarioRegistro.setColumns(10);
		
		passRegistro = new JTextField();
		passRegistro.setBounds(204, 155, 192, 26);
		contentPane.add(passRegistro);
		passRegistro.setColumns(10);
	}
	
	public void enviarRegistroAPrincipal(String usuario, String pass) {
		ventanaPrincipal.recibirDeVentanaRegistro(usuario, pass);
	}
	
	

}
