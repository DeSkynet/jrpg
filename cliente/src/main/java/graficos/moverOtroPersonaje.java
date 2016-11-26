package graficos;

import java.awt.image.BufferedImage;
import java.util.LinkedList;

import herramientas.Constantes;
import pantalla.Ente;
import pantalla.Juego;

public class moverOtroPersonaje extends Ente{

	public moverOtroPersonaje(Juego juego, Mapa mapa, int puntoX, int puntoY,
			LinkedList<BufferedImage[]> animaciones, int velAnimacion) {
		super(juego, mapa, Constantes.ANCHO_ENTE, Constantes.ALTO_ENTE, puntoX, puntoY, animaciones, velAnimacion);
		// TODO Auto-generated constructor stub
	}

}
