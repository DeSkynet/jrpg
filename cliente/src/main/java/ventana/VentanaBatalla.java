package ventana;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import herramientas.Constantes;
import mensajes.MensajeAbandono;
import mensajes.MensajeAtaque;
import mensajes.MensajeBatalla;
import mensajes.MensajeHabilidad;
import mensajes.MensajePersonaje;

public class VentanaBatalla extends JFrame {

	private JPanel contentPane;
	private Cliente cliente;
	private boolean turno;
	private MensajePersonaje personaje;
	JLabel salud = new JLabel("Salud : ");
	JLabel energia = new JLabel("Energia : ");
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaBatalla frame = new VentanaBatalla();
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
	public VentanaBatalla() {
		
		this.cliente = cliente;
		this.personaje = personaje;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setTitle("The Alliance - Batalla");
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(128,128,2));
		setLocationRelativeTo(null);
		setResizable(false);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon("Assets/sprites/skeletorbatIzq.png"));
		label.setBounds(136, 165, 156, 223);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("Assets/sprites/male_lightBatDer.png"));
		label_1.setBounds(508, 176, 176, 222);
		contentPane.add(label_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Assets/img/Arena_Battle_Background.jpeg"));
		lblNewLabel.setBounds(0, 0, 794, 492);
		contentPane.add(lblNewLabel);
		
		//if para ver si puede atacar o no
//		enviarMensajeBatalla();
		
		JButton btnNewButton = new JButton("Promocionar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(turno == true){
					enviarHabilidad();
				}
			}
		});
		btnNewButton.setBackground(SystemColor.inactiveCaption);
		btnNewButton.setBounds(417, 508, 138, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Atacar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				///Acá se deberia mandar la informacion del ataque al servidor
				if(turno == true){
					enviarAtaque();
				}
			}
		});
		btnNewButton_1.setBackground(SystemColor.inactiveCaption);
		btnNewButton_1.setBounds(10, 508, 138, 29);
		contentPane.add(btnNewButton_1);
		
		
	
		JButton btnNewButton_2 = new JButton("Salir de Batalla");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcion =JOptionPane.showConfirmDialog (null, new Object[]{"¿Desea salir de la batalla?. Se desaliara y su salud quedara en 1% "},"Salir", JOptionPane.OK_CANCEL_OPTION);
				if(opcion==JOptionPane.OK_OPTION){
					enviarAbandono();
					dispose();
				}
			}
		});
		btnNewButton_2.setBackground(SystemColor.inactiveCaption);
		btnNewButton_2.setBounds(200, 497, 170, 47);
		contentPane.add(btnNewButton_2);
		
		salud.addContainerListener(new ContainerAdapter() {
			public void actionPerformed(){
				label.setText(Integer.toString(personaje.getSalud()));
			}
			
		});
		salud.setBackground(SystemColor.inactiveCaption);
		salud.setBounds(583, 509, 101, 26);
		contentPane.add(salud);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label, BorderLayout.WEST);
		
	
		energia.addContainerListener(new ContainerAdapter() {
			public void actionPerformed(){
				label.setText(Integer.toString(personaje.getEnergia()));
			}
		});
		energia.setBackground(SystemColor.inactiveCaption);
		energia.setBounds(699, 509, 95, 26);
		contentPane.add(energia);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label, BorderLayout.WEST);
	}
	
	public void enviarAtaque(){
		this.cliente.enviarMensaje("MensajeAtaque", new MensajeAtaque(cliente.getNombre(),"Ataque", "Enemigo"));
	}
	
	public void enviarHabilidad(){
		this.cliente.enviarMensaje("MensajeHabilidad", new MensajeHabilidad(cliente.getNombre(),"Habilidad", "Enemigo"));
	}
	
	public void enviarMensajeBatalla(){
		this.cliente.enviarMensaje("MensajeBatalla", new MensajeBatalla(cliente.getNombre(),true));
	}
	
	protected void enviarAbandono() {
		this.cliente.enviarMensaje("MensajeAbandono", new MensajeAbandono(cliente.getNombre(), true));
		
	}
}

