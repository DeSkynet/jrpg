package ventana;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import herramientas.Constantes;

public class Ventana{
//	private static final long serialVersionUID = 1L; // ---> GENERADO POR COMPILADOR
//	private JFrame ventana;
//	
//	private ImageIcon icono;
//	
//	public Ventana(){
//		ventana = new JFrame();
//		icono = new ImageIcon(getClass().getResource(Constantes.PATH_ICONO));
//		
//		ventana.setSize(Constantes.ANCHO_PANTALLA, Constantes.ALTO_PANTALLA);
//		ventana.setResizable(false);	//---> no se podra maximizar la ventana
//		ventana.setLayout(new BorderLayout());  //---> orden ventana
//		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		ventana.add(this);
//		ventana.setIconImage(icono.getImage());
//		ventana.setVisible(true);
//		ventana.setLocationRelativeTo(null);
//
//		ventana.pack();
//	}
//	
//	public JFrame obtenerVentana() {
//		return this.ventana;
//	}
	private JFrame pantalla;
	private Canvas canvas; // Objeto donde se grafica el juego
	private ImageIcon icono;
	
	public Ventana() {
		pantalla = new JFrame();
		icono = new ImageIcon(Constantes.PATH_ICONO);
		pantalla.setSize(Constantes.ANCHO_PANTALLA, Constantes.ALTO_PANTALLA);
		pantalla.setResizable(false);
		pantalla.setIconImage(icono.getImage());
		pantalla.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pantalla.setLocationRelativeTo(null);
		pantalla.setVisible(true);

		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(Constantes.ANCHO_PANTALLA, Constantes.ALTO_PANTALLA));
		canvas.setMaximumSize(new Dimension(Constantes.ANCHO_PANTALLA, Constantes.ALTO_PANTALLA));
		canvas.setMinimumSize(new Dimension(Constantes.ANCHO_PANTALLA, Constantes.ALTO_PANTALLA));
		canvas.setFocusable(false);
		
		pantalla.add(canvas);
		pantalla.pack();
	}

	public Canvas getCanvas() {
		return canvas;
	}
	
	public JFrame obtenerVentana() {
		return pantalla;
	}
	
}
