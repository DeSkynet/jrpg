package graficos;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import herramientas.Constantes;

public class Cuadro {
	
	public static Cuadro[] Cuadros = new Cuadro[256];
	public static Cuadro cesped = new CuadroCesped(0);
	public static Cuadro Agua = new CuadroAgua(4);
	public static Cuadro roca = new CuadroRoca(2);
	public static Cuadro tierra = new CuadroTierra(3);
	public static Cuadro piedra = new CuadroPiedraN(1);
	
	
	public static final int ANCHO = 64;
	public static final int ALTO = 32;
	
	protected BufferedImage textura;
	protected final int id;
	
	public Cuadro(BufferedImage textura, int id) {
		this.textura = textura;
		this.id = id;
		
		Cuadros[id] = this;
		
	}
	
//	private int anchoCuadro;
//	private int altoCuadro;
//	private BufferedImage img;
//	private boolean atravesable;
//	
//	public Cuadro(int anchoCuadro, int altoCuadro, String pathImg, boolean atravesable) {
//		this.anchoCuadro = anchoCuadro;
//		this.altoCuadro = altoCuadro;
//		this.atravesable = atravesable;
//		File imagen=new File(pathImg);
//		try {
//			this.img = ImageIO.read(imagen);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	
//	}
	
	public void actualizar(){
		
	}
	
	public void graficar(Graphics g, int x, int y) {
		g.drawImage(textura, x, y, Cuadro.ANCHO, Cuadro.ALTO, null);
	}
	
	public boolean noEsAtravesable(){
		
		return false;
	}
	
	public int getId() {
		return id;
	}
	
//	public BufferedImage getCuadro() {
//		return this.img;
//	}
//
//	public int getAnchoCuadro() {
//		return anchoCuadro;
//	}
//
//	public void setAnchoCuadro(int anchoCuadro) {
//		this.anchoCuadro = anchoCuadro;
//	}
//
//	public int getAltoCuadro() {
//		return altoCuadro;
//	}
//
//	public void setAltoCuadro(int altoCuadro) {
//		this.altoCuadro = altoCuadro;
//	}
	
	
}
