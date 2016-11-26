package ventana;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import cliente.Persona;
import mensajes.MensajeAtaque;
import mensajes.MensajeHabilidad;

public class VentanaBatalla extends JFrame {

	private JPanel contentPane;
	private Cliente cliente;
	private MensajeAtaque ataque;
	private Persona personaje;
	private boolean turno;

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
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setTitle("The Alliance - Batalla");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(128,128,2));
		setLocationRelativeTo(null);
		setResizable(false);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\JULIAN\\Desktop\\workspace\\jrpg\\cliente\\Assets\\sprites\\skeletorbatIzq.png"));
		label.setBounds(136, 165, 156, 223);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("C:\\Users\\JULIAN\\Desktop\\workspace\\jrpg\\cliente\\Assets\\sprites\\werewolfBatDer.png"));
		label_1.setBounds(508, 176, 176, 222);
		contentPane.add(label_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\JULIAN\\Desktop\\workspace\\jrpg\\cliente\\Assets\\img\\Arena_Battle_Background.jpeg"));
		lblNewLabel.setBounds(0, 0, 794, 492);
		contentPane.add(lblNewLabel);
		
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
				int opcion =JOptionPane.showConfirmDialog (null, new Object[]{"¿Desea salir de la batalla?. Se desaliara y su salud quedara en 1% , sos un boludo y no tenes huevos, tomatela te dije"},"Salir", JOptionPane.OK_CANCEL_OPTION);
				if(opcion==JOptionPane.OK_OPTION){
					dispose();
				}
			}
		});
		btnNewButton_2.setBackground(SystemColor.inactiveCaption);
		btnNewButton_2.setBounds(200, 497, 170, 47);
		contentPane.add(btnNewButton_2);
	}
	
	public void enviarAtaque(){
		this.cliente.enviarMensaje("MensajeAtaque", new MensajeAtaque(cliente.getNombre(),"Ataque", "Enemigo"));
	}
	
	public void enviarHabilidad(){
		this.cliente.enviarMensaje("MensajeHabilidad", new MensajeHabilidad(cliente.getNombre(),"Habilidad", "Enemigo"));
	}
	
}
