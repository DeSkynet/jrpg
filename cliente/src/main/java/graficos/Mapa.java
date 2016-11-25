package graficos;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

import javax.swing.JOptionPane;

import herramientas.Constantes;
import herramientas.HerramientasGraficas;
import herramientas.Utilidades;
import pantalla.Juego;

public class Mapa {
	
	///EL OFFSET SE REFIERE A LA POSICION DE LA CAMARA...
	private int desplazamientoEnX; //POSICION DE LA CAMARA
	private int desplazamientoEnY;
	
	private Juego juego;
	
	private int xMinimo;
	private int xMaximo;
	private int yMinimo;
	private int yMaximo;
	
	private int ancho;
	private int alto;
	
	private Point iso = new Point();
	
	private int[][] cuadro;
	
	public Mapa(Juego juego, String pathArchivo) throws Exception {
		this.juego = juego;
		cargarMapa(pathArchivo);

	}
	
	
	//LEO EL MAPA QUE ESTA EN FORMATO .txt
	private void cargarMapa(String pathArchivo) {
		String entrada;
		try {
			entrada = Utilidades.archivoAString(pathArchivo);
			String[] rotulos = entrada.split("\\s+");
			ancho = Utilidades.parseInt(rotulos[0]);
			alto = Utilidades.parseInt(rotulos[1]);
			
			cuadro = new int[ancho][alto];
			
			for(int y=0; y < alto; y++){
				for(int x=0; x < ancho; x++){
					cuadro[x][y] = Utilidades.parseInt(rotulos[(x + y * ancho +4)]);
				}
			}
		} catch (Exception e) {			/// ponerle exception al catch 

			JOptionPane.showMessageDialog(null, "No se encuantra el mapa en el equipo, debe reinstalar.");
		}
	}
	
	public void actualizar() {
		//SUPONGO QUE SE VA A USAR CUANDO TENGA MAS PERSONAJES.
	}
	
	
	
	
	//RECORRE Y DIBUJA LOS TILES
//	public void graficar(Graphics grafico) {
//		//Point coodXY = new Point(0, 0);
//		this.cuadroCesped = new Tile(Sprite.cuadroCesped, 0);
//		this.cuadroAgua = new Tile( Sprite.cuadroAgua, 1);
//		this.cuadroRoca = new Tile(Sprite.cuadroRoca,2);
//		//		int x, y;
//		
//		for(int i=0; i < anchoMapa ; i++) {
//				oldX=(-Tile.ANCHO/2)*i;
//				oldY=(+Tile.ALTO/2)*i;
//			for(int j=0;j<altoMapa;j++) {
////				coodXY = HerramientasGraficas.DosDAIsometrico(new Point(i,j));
////				x = j * Constantes.ANCHO_CESPED;
////				y = i * Constantes.ALTO_CESPED;
//				
//				if(this.cuadros[i][j] == 0){
//					grafico.drawImage(cuadroCesped.textura, oldX + offsetTileX, oldY + offsetTileY, null);
//					oldX+= Tile.ANCHO/2;
//					oldY+= Tile.ALTO/2;
//				}
//				else if(this.cuadros[i][j] == 1){
//					grafico.drawImage(Sprite.cuadroAgua, oldX + offsetTileX, oldY + offsetTileY, null);
//					oldX+= Tile.ANCHO/2;
//					oldY+= Tile.ALTO/2;
//				}
//				else if(this.cuadros[i][j] == 2){
//					grafico.drawImage(Sprite.cuadroRoca, oldX + offsetTileX, oldY + offsetTileY, null);
//					oldX+= Tile.ANCHO/2;
//					oldY+= Tile.ALTO/2;
//				}
//					//	dibujarTile(grafico, cuadroCesped.getCuadro(), HerramientasGraficas.DosDAIsometrico(new Point(x,y)));
//
//				
//				
//			}
//		}
		
	//RECORRE Y DIBUJA LOS cuadro
		public void graficar(Graphics grafico) {
			
			desplazamientoEnX = juego.getEstadoJuego().getEnte().getDesplzamientoX();
			desplazamientoEnY = juego.getEstadoJuego().getEnte().getDesplazamientoY();
			
			xMinimo = (int) (juego.getCamaraPersonaje().getMovimientoX() - desplazamientoEnX - 30);
			xMaximo = xMinimo + Constantes.ANCHO_PANTALLA + desplazamientoEnX + 30;
			yMinimo = (int) (juego.getCamaraPersonaje().getMovimientoY() - desplazamientoEnY + 5);
			yMaximo = yMinimo + Constantes.ALTO_PANTALLA + desplazamientoEnY - 5;
			
			
			for (int i = 0; i < alto; i++) {
				for (int j = 0; j < ancho; j++) {
					iso = HerramientasGraficas.DosDAIsometrico(new Point(j, i));
					if ((iso.x >= xMinimo && iso.x <= xMaximo) && (iso.y >= yMinimo && iso.y <= yMaximo)) {
						getCuadro(new Point(j, i)).graficar(grafico, (int) (iso.x - juego.getCamaraPersonaje().getMovimientoX()),
								(int) (iso.y - juego.getCamaraPersonaje().getMovimientoY()));
					}
				}
			}
		}
	
	public Cuadro getCuadro(Point punto) {
		
		if(punto.x> cuadro.length || punto.y> cuadro.length){
			return Cuadro.piedra;
		}
		try {
			Cuadro t = Cuadro.Cuadros[cuadro[punto.x][punto.y]];
			if (t==null )
				return Cuadro.Agua;
			return t;
		} catch (Exception e) {

			return Cuadro.piedra;
		}
		
//		System.out.println("id:" + t.id);
		
		
	}
	
	///METODO A USAR CON EL METODO 2D A ISOMETRICO
	public void dibujarTile(Graphics grafico, BufferedImage imagen, Point punto) {
		grafico.drawImage(imagen, punto.x, punto.y, null);
	}


//	public Tile getCuadroCesped() {
//		return cuadroCesped;
//	}
//
//
//	public Tile getCuadroAgua() {
//		return cuadroAgua;
//	}
//
//
//	public Tile getCuadroRoca() {
//		return cuadroRoca;
//	}
	
}
