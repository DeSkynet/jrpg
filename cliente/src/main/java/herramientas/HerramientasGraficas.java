package herramientas;

import java.awt.Point;

import graficos.Cuadro;

public class HerramientasGraficas {
	
	
	// LA IDEA AQUI ES QUE PUEDA PASAR DE COORDENADAS 2D A ISOMETRICO ... SI NO SIRVE SE BORRA.
		public static Point DosDAIsometrico(Point punto) {
			Point temp = new Point(0,0);
			
			temp.x = (punto.x - punto.y)* Cuadro.ANCHO/2;
			temp.y = (punto.x + punto.y)* Cuadro.ALTO /2;
			
			return temp;
		}
		
		public static Point IsometricoADosD(Point punto){
			  Point temp = new Point(0, 0);
			  temp.x=(punto.x / (Cuadro.ANCHO / 2) + punto.y / (Cuadro.ALTO / 2)) / 2;
			  temp.y=(punto.y / (Cuadro.ALTO / 2) - (punto.x / (Cuadro.ANCHO / 2))) / 2;
			  
//			  temp.x = (2 * punto.y + punto.x) / 2;
//			  temp.y = (2 * punto.y - punto.x) / 2;
			  
			  return temp;
		}
		
		public static Point clickACuadro(Point punto) {
			Point cuadro = new Point();
			
			cuadro.x = (int) Math.floor((punto.y / Cuadro.ALTO) + (punto.x / Cuadro.ANCHO)) + 1;
			cuadro.y = (int) Math.floor((-punto.x / Cuadro.ANCHO) + (punto.y / Cuadro.ALTO)) + 1;

			return cuadro;
		}
}
