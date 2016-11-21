package graficos;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import herramientas.Constantes;

public class Sprite {
	private static int ancho;
	private static int alto;


	public static LinkedList<BufferedImage[]> mago = new LinkedList<>();
	private static BufferedImage[] magoIzq;
	private static BufferedImage[] magoArribaIzq; 
	private static BufferedImage[] magoArriba;
	private static BufferedImage[] magoArribaDer;
	private static BufferedImage[] magoDer;
	private static BufferedImage[] magoAbajoDer;
	private static BufferedImage[] magoAbajo;
	private static BufferedImage[] magoAbajoIzq;
	
	
	public static BufferedImage cuadroCesped;
	public static BufferedImage cuadroAgua;
	public static BufferedImage cuadroRoca;
	
public static void cargar() {
		
		cargarMago();
	}

	
	
	public static void cargarMago() {
		
		ancho = 256;
		alto = 256;
		
		
		HojaSprite spriteMago = new HojaSprite(cargarImagen(Constantes.SPRITE_MAGO));
		
		magoIzq = new BufferedImage[4];
		magoArribaIzq = new BufferedImage[4];
		magoArriba = new BufferedImage[4];
		magoArribaDer = new BufferedImage[4];
		magoDer = new BufferedImage[4];
		magoAbajoDer = new BufferedImage[4];
		magoAbajo = new BufferedImage[4];
		magoAbajoIzq = new BufferedImage[4];
		
		for(int i = 0; i < 4; i++) {
			Point punto = new Point(ancho*i, 0);
			magoIzq[i] = spriteMago.obtenerCuadro(punto, ancho, alto);
		}
		
		for(int i = 0; i < 4; i++) {
			Point punto = new Point(ancho*i, alto);
			magoArribaIzq[i] = spriteMago.obtenerCuadro(punto, ancho, alto);
		}
		
		for(int i = 0; i < 4; i++) {
			Point punto = new Point(ancho*i, alto*2);
			magoArriba[i] = spriteMago.obtenerCuadro(punto, ancho, alto);
		}
		
		for(int i = 0; i < 4; i++) {
			Point punto = new Point(ancho*i, alto*3);
			magoArribaDer[i] = spriteMago.obtenerCuadro(punto, ancho, alto);
		}
		
		for(int i = 0; i < 4; i++) {
			Point punto = new Point(ancho*i, alto*4);
			magoDer[i] = spriteMago.obtenerCuadro(punto, ancho, alto);
		}
		
		for(int i = 0; i < 4; i++) {
			Point punto = new Point(ancho*i, alto*5);
			magoAbajoDer[i] = spriteMago.obtenerCuadro(punto, ancho, alto);
		}
		
		for(int i = 0; i < 4; i++) {
			Point punto = new Point(ancho*i, alto*6);
			magoAbajo[i] = spriteMago.obtenerCuadro(punto, ancho, alto);
		}
		
		for(int i = 0; i < 4; i++) {
			Point punto = new Point(ancho*i, alto*7);
			magoAbajoIzq[i] = spriteMago.obtenerCuadro(punto, ancho, alto);
		}
		
		 mago.add(magoIzq);
		 mago.add(magoArribaIzq);
		 mago.add(magoArriba);
		 mago.add(magoArribaDer);
		 mago.add(magoDer);
		 mago.add(magoAbajoDer);
		 mago.add(magoAbajo);
		 mago.add(magoAbajoIzq);
		 
		 
		 cuadroCesped = cargarImagen(Constantes.PATH_CESPED);
		 cuadroAgua = cargarImagen(Constantes.PATH_AGUA);
		 cuadroRoca = cargarImagen(Constantes.PATH_ROCA);
}


	public static BufferedImage cargarImagen(String path) {
		try {
			File imagen=new File(path);
			return ImageIO.read(imagen);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}

	public static LinkedList<BufferedImage[]> getMago() {
		return mago;
	}
	
}
