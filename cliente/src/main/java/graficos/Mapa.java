package graficos;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

import herramientas.Constantes;

public class Mapa {
	
	///EL OFFSET SE REFIERE A LA POSICION DE LA CAMARA...
	private static int offsetTileX = 300;
	private static int offsetTileY = 150;
	
	int oldX=0;
	int oldY=0;
	private Cuadro cuadroCesped;
	private Cuadro cuadroAgua;
	private Cuadro cuadroRoca;
	private int anchoMapa;
	private int altoMapa;
	private int[][] cuadros = {
							    {0,0,0,0},
							    {0,0,0,0},
							    {0,0,0,0},
							    {0,0,0,0}
										  };
	
	public Mapa(String pathArchivo) {
		cargarMapa(pathArchivo);
		
//		this.anchoMapa = 4;
//		this.altoMapa = 4;
//		this.cuadroCesped = new Cuadro(Constantes.ANCHO_CESPED, Constantes.ALTO_CESPED, Constantes.PATH_CESPED);
	}
	
	
	//LEO EL MAPA QUE ESTA EN FORMATO .txt
	private void cargarMapa(String pathArchivo) {
		Scanner entrada = null;
		
		try {
			entrada = new Scanner(new File(pathArchivo));
			this.anchoMapa = entrada.nextInt();
			this.altoMapa = entrada.nextInt();
			
			this.cuadros = new int[this.anchoMapa][this.altoMapa];
			
			for(int y=0; y<this.anchoMapa; y++)
				for(int x=0; x<this.altoMapa; x++)
					this.cuadros[y][x] = entrada.nextInt();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			entrada.close();
		}
	}
	
	public void actualizar() {
		
	}
	
	
	
	
	//RECORRE Y DIBUJA LOS TILES
	public void graficar(Graphics grafico) {
		//Point coodXY = new Point(0, 0);
		this.cuadroCesped = new Cuadro(Constantes.ANCHO_CUADRO, Constantes.ALTO_CUADRO, Constantes.PATH_CESPED, true);
		this.cuadroAgua = new Cuadro(Constantes.ANCHO_CUADRO, Constantes.ALTO_CUADRO, Constantes.PATH_AGUA, false);
		this.cuadroRoca = new Cuadro(Constantes.ANCHO_CUADRO, Constantes.ALTO_CUADRO, Constantes.PATH_ROCA, true);
		//		int x, y;
		
		for(int i=0; i < anchoMapa ; i++) {
				oldX=(-cuadroCesped.getAnchoTile()/2)*i;
				oldY=(+cuadroCesped.getAltoTile()/2)*i;
			for(int j=0;j<altoMapa;j++) {
//				coodXY = HerramientasGraficas.DosDAIsometrico(new Point(i,j));
//				x = j * Constantes.ANCHO_CESPED;
//				y = i * Constantes.ALTO_CESPED;
				
				if(this.cuadros[i][j] == 0){
					grafico.drawImage(cuadroCesped.getCuadro(), oldX + offsetTileX, oldY + offsetTileY, null);
					oldX+= cuadroCesped.getAnchoTile()/2;
					oldY+= cuadroCesped.getAltoTile()/2;
				}
				else if(this.cuadros[i][j] == 1){
					grafico.drawImage(cuadroAgua.getCuadro(), oldX + offsetTileX, oldY + offsetTileY, null);
					oldX+= cuadroAgua.getAnchoTile()/2;
					oldY+= cuadroAgua.getAltoTile()/2;
				}
				else if(this.cuadros[i][j] == 2){
					grafico.drawImage(cuadroRoca.getCuadro(), oldX + offsetTileX, oldY + offsetTileY, null);
					oldX+= cuadroRoca.getAnchoTile()/2;
					oldY+= cuadroRoca.getAltoTile()/2;
				}
					//	dibujarTile(grafico, cuadroCesped.getCuadro(), HerramientasGraficas.DosDAIsometrico(new Point(x,y)));

				
				
			}
		}
	}
	
	public void moverXDerecha(){
		offsetTileX++;
	}
	
	public void moverXIzquierda(){
		offsetTileX--;
	}
	
	public void moverYArriba(){
		offsetTileY++;
	}
	
	public void moverYAbajo(){
		offsetTileY--;
	}
	
	public Cuadro getCuadro(Point punto) {
		int t = this.cuadros[punto.x][punto.y];
		if (t == 0)
			return getCuadroCesped();
		else{ 
			if(t == 1){
				return null;
		}
//				return getCuadroAgua();
			return getCuadroRoca();
		}
	}
	
	///METODO A USAR CON EL METODO 2D A ISOMETRICO
	public void dibujarTile(Graphics grafico, BufferedImage imagen, Point punto) {
		grafico.drawImage(imagen, punto.x, punto.y, null);
	}


	public Cuadro getCuadroCesped() {
		return cuadroCesped;
	}


	public Cuadro getCuadroAgua() {
		return cuadroAgua;
	}


	public Cuadro getCuadroRoca() {
		return cuadroRoca;
	}
	
	

}
