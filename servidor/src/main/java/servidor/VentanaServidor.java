package servidor;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import log.Log;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VentanaServidor extends JFrame {

	private JPanel contentPane;
	private Servidor servidor;

	public VentanaServidor(Servidor servidor) {
		this.servidor = servidor;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Servidor");
		setIconImage(new ImageIcon("img/servidorIcono.png").getImage());
		setBounds(100, 100, 450, 170);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		contentPane.setBackground(Color.BLACK);
		
		JButton btnPararServidor = new JButton("Parar Servidor");
		btnPararServidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					pararServidor();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnPararServidor.setBackground(SystemColor.activeCaption);
		btnPararServidor.setBounds(150, 50, 150, 30);
		contentPane.add(btnPararServidor);
	
	}

	protected void pararServidor() throws IOException {
		try {
			this.servidor.pararServidor();
			System.exit(1);
		} catch (IOException e) {
			Log.crearLog("Error: En el cierre del Servidor" + e.getMessage());
		}
		
	}
}
