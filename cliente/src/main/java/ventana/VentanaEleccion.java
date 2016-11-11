package ventana;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaEleccion extends JFrame {

	private JPanel contentPane;
	private String eleccionRaza;
	private String eleccionCasta;
	private JButton btnHumano;
	private JButton btnRobot;
	private JButton btnSuperheroe;
	private JButton btnAlien;
	private JButton btnLuchador;
	private JButton btnLadron;
	private JButton btnHechicero;
	private JButton btnJugar;
	/**
	 * Create the frame.
	 */
	public VentanaEleccion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setTitle("The Alliance - Selecciona Personaje");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRaza = new JLabel("RAZA");
		lblRaza.setHorizontalAlignment(SwingConstants.CENTER);
		lblRaza.setFont(new Font("Arial", Font.PLAIN, 19));
		lblRaza.setBounds(37, 37, 70, 30);
		contentPane.add(lblRaza);
		
		btnHumano = new JButton("Humano");
		btnHumano.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eleccionRaza = btnHumano.getText();
			}
		});
		btnHumano.setFont(new Font("Arial", Font.PLAIN, 16));
		btnHumano.setBounds(100, 78, 120, 23);
		contentPane.add(btnHumano);
		
		btnRobot = new JButton("Robot");
		btnRobot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eleccionRaza = btnRobot.getText();
			}
		});
		btnRobot.setFont(new Font("Arial", Font.PLAIN, 16));
		btnRobot.setBounds(250, 78, 120, 23);
		contentPane.add(btnRobot);
		
		btnSuperheroe = new JButton("Superheroe");
		btnSuperheroe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eleccionRaza = btnSuperheroe.getText();
			}
		});
		btnSuperheroe.setFont(new Font("Arial", Font.PLAIN, 16));
		btnSuperheroe.setBounds(400, 78, 120, 23);
		contentPane.add(btnSuperheroe);
		
		btnAlien = new JButton("Alien");
		btnAlien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eleccionRaza = btnAlien.getText();
			}
		});
		btnAlien.setFont(new Font("Arial", Font.PLAIN, 16));
		btnAlien.setBounds(550, 78, 120, 23);
		contentPane.add(btnAlien);
		
		JLabel lblCasta = new JLabel("CASTA");
		lblCasta.setHorizontalAlignment(SwingConstants.CENTER);
		lblCasta.setFont(new Font("Arial", Font.PLAIN, 19));
		lblCasta.setBounds(37, 170, 70, 30);
		contentPane.add(lblCasta);
		
		btnLuchador = new JButton("Luchador");
		btnLuchador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eleccionCasta = btnLuchador.getText();
			}
		});
		btnLuchador.setFont(new Font("Arial", Font.PLAIN, 16));
		btnLuchador.setBounds(100, 211, 120, 23);
		contentPane.add(btnLuchador);
		
		btnLadron = new JButton("Ladron");
		btnLadron.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eleccionCasta = btnLadron.getText();
			}
		});
		btnLadron.setFont(new Font("Arial", Font.PLAIN, 16));
		btnLadron.setBounds(250, 211, 120, 23);
		contentPane.add(btnLadron);
		
		btnHechicero = new JButton("Hechicero");
		btnHechicero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eleccionCasta = btnHechicero.getText();
			}
		});
		btnHechicero.setFont(new Font("Arial", Font.PLAIN, 16));
		btnHechicero.setBounds(400, 211, 120, 23);
		contentPane.add(btnHechicero);
		
		btnJugar = new JButton("Jugar");
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "AREA A CONECTAR CON EL JUEGO...");
			}
		});
		btnJugar.setFont(new Font("Arial", Font.PLAIN, 16));
		btnJugar.setBounds(340, 500, 120, 30);
		contentPane.add(btnJugar);
		
	
	}
}
