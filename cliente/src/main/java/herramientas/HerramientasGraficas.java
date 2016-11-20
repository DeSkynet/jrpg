package herramientas;

import java.awt.Point;

public class HerramientasGraficas {
	
	
	// LA IDEA AQUI ES QUE PUEDA PASAR DE COORDENADAS 2D A ISOMETRICO ... SI NO SIRVE SE BORRA.
	public static Point DosDAIsometrico(Point punto) {
		Point temp = new Point(0,0);
		
		temp.x = punto.x - punto.y;
		temp.y = (punto.x + punto.y)/2;
		
		return temp;
	}
	
	public static Point IsometricoADosD(Point punto){
		  Point temp = new Point(0, 0);
		  
		  temp.x = (2 * punto.y + punto.x) / 2;
		  temp.y = (2 * punto.y - punto.x) / 2;
		  
		  return temp;
	}
	
	public static Point clickACuadro(Point punto) {
		Point cuadro = new Point();

		cuadro.x = (int) Math.floor((punto.y / Constantes.ALTO_CUADRO) + (punto.x / Constantes.ANCHO_CUADRO)) + 1;
		cuadro.y = (int) Math.floor((-(punto.x) / Constantes.ANCHO_CUADRO) + (punto.y / Constantes.ALTO_CUADRO)) + 1;

		return cuadro;
	}
}
