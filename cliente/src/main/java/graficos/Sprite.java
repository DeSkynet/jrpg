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

	public static LinkedList<BufferedImage[]> sprite = new LinkedList<>();
	private static BufferedImage[] izq;
	private static BufferedImage[] arribaIzq; 
	private static BufferedImage[] arriba;
	private static BufferedImage[] arribaDer;
	private static BufferedImage[] der;
	private static BufferedImage[] abajoDer;
	private static BufferedImage[] abajo;
	private static BufferedImage[] abajoIzq;
	
	
	public static BufferedImage cuadroCesped;
	public static BufferedImage cuadroAgua;
	public static BufferedImage cuadroRoca;
	public static BufferedImage cuadroPiedraNo;
	public static BufferedImage CuadroTierra;
	
public static void cargar(String tipoAcargar) {
		switch (tipoAcargar) {
		case "Robot" :
			cargarRobot();
			break;
		case "Alien" :
			cargarMago();
			break;
		case "Superheroe" :
			cargarSuper();
			break;	
		case "Humano":
			cargarHumano();
		default:
			break;
		}
	}

	
	
	public static void cargarMago() {
		
		ancho = 256;
		alto = 256;
		
		
		HojaSprite spriteMago = new HojaSprite(cargarImagen(Constantes.SPRITE_ALIEN));
		
		izq = new BufferedImage[4];
		arribaIzq = new BufferedImage[4];
		arriba = new BufferedImage[4];
		arribaDer = new BufferedImage[4];
		der = new BufferedImage[4];
		abajoDer = new BufferedImage[4];
		abajo = new BufferedImage[4];
		abajoIzq = new BufferedImage[4];
		
		for(int i = 0; i < 4; i++) {
			Point punto = new Point(ancho*i, 0);
			izq[i] = spriteMago.obtenerCuadro(punto, ancho, alto);
		}
		
		for(int i = 0; i < 4; i++) {
			Point punto = new Point(ancho*i, alto);
			arribaIzq[i] = spriteMago.obtenerCuadro(punto, ancho, alto);
		}
		
		for(int i = 0; i < 4; i++) {
			Point punto = new Point(ancho*i, alto*2);
			arriba[i] = spriteMago.obtenerCuadro(punto, ancho, alto);
		}
		
		for(int i = 0; i < 4; i++) {
			Point punto = new Point(ancho*i, alto*3);
			arribaDer[i] = spriteMago.obtenerCuadro(punto, ancho, alto);
		}
		
		for(int i = 0; i < 4; i++) {
			Point punto = new Point(ancho*i, alto*4);
			der[i] = spriteMago.obtenerCuadro(punto, ancho, alto);
		}
		
		for(int i = 0; i < 4; i++) {
			Point punto = new Point(ancho*i, alto*5);
			abajoDer[i] = spriteMago.obtenerCuadro(punto, ancho, alto);
		}
		
		for(int i = 0; i < 4; i++) {
			Point punto = new Point(ancho*i, alto*6);
			abajo[i] = spriteMago.obtenerCuadro(punto, ancho, alto);
		}
		
		for(int i = 0; i < 4; i++) {
			Point punto = new Point(ancho*i, alto*7);
			abajoIzq[i] = spriteMago.obtenerCuadro(punto, ancho, alto);
		}
		
		 sprite.add(izq);
		 sprite.add(arribaIzq);
		 sprite.add(arriba);
		 sprite.add(arribaDer);
		 sprite.add(der);
		 sprite.add(abajoDer);
		 sprite.add(abajo);
		 sprite.add(abajoIzq);
		 
		 
		 cuadroCesped = cargarImagen(Constantes.PATH_CESPED);
		 cuadroAgua = cargarImagen(Constantes.PATH_AGUA);
		 cuadroRoca = cargarImagen(Constantes.PATH_ROCA);
		 cuadroPiedraNo = cargarImagen(Constantes.PATH_PIEDRAN);
		 CuadroTierra = cargarImagen(Constantes.PATH_TIERRA);
}

public static void cargarRobot() {
		
		ancho = 256;
		alto = 256;
		
		
		HojaSprite spriteRobot = new HojaSprite(cargarImagen(Constantes.SPRITE_ROBOT));
		
		izq = new BufferedImage[4];
		arribaIzq = new BufferedImage[4];
		arriba = new BufferedImage[4];
		arribaDer = new BufferedImage[4];
		der = new BufferedImage[4];
		abajoDer = new BufferedImage[4];
		abajo = new BufferedImage[4];
		abajoIzq = new BufferedImage[4];
		
		for(int i = 0; i < 4; i++) {
			Point punto = new Point(ancho*i, 0);
			izq[i] = spriteRobot.obtenerCuadro(punto, ancho, alto);
		}
		
		for(int i = 0; i < 4; i++) {
			Point punto = new Point(ancho*i, alto);
			arribaIzq[i] = spriteRobot.obtenerCuadro(punto, ancho, alto);
		}
		
		for(int i = 0; i < 4; i++) {
			Point punto = new Point(ancho*i, alto*2);
			arriba[i] = spriteRobot.obtenerCuadro(punto, ancho, alto);
		}
		
		for(int i = 0; i < 4; i++) {
			Point punto = new Point(ancho*i, alto*3);
			arribaDer[i] = spriteRobot.obtenerCuadro(punto, ancho, alto);
		}
		
		for(int i = 0; i < 4; i++) {
			Point punto = new Point(ancho*i, alto*4);
			der[i] = spriteRobot.obtenerCuadro(punto, ancho, alto);
		}
		
		for(int i = 0; i < 4; i++) {
			Point punto = new Point(ancho*i, alto*5);
			abajoDer[i] = spriteRobot.obtenerCuadro(punto, ancho, alto);
		}
		
		for(int i = 0; i < 4; i++) {
			Point punto = new Point(ancho*i, alto*6);
			abajo[i] = spriteRobot.obtenerCuadro(punto, ancho, alto);
		}
		
		for(int i = 0; i < 4; i++) {
			Point punto = new Point(ancho*i, alto*7);
			abajoIzq[i] = spriteRobot.obtenerCuadro(punto, ancho, alto);
		}
		
		 sprite.add(izq);
		 sprite.add(arribaIzq);
		 sprite.add(arriba);
		 sprite.add(arribaDer);
		 sprite.add(der);
		 sprite.add(abajoDer);
		 sprite.add(abajo);
		 sprite.add(abajoIzq);
		 
		 
		 cuadroCesped = cargarImagen(Constantes.PATH_CESPED);
		 cuadroAgua = cargarImagen(Constantes.PATH_AGUA);
		 cuadroRoca = cargarImagen(Constantes.PATH_ROCA);
		 cuadroPiedraNo = cargarImagen(Constantes.PATH_PIEDRAN);
		 CuadroTierra = cargarImagen(Constantes.PATH_TIERRA);
}
	
public static void cargarHumano() {
	
	ancho = 256;
	alto = 256;
	
	
	HojaSprite spriteHumano = new HojaSprite(cargarImagen(Constantes.SPRITE_HUMANO));
	
	izq = new BufferedImage[4];
	arribaIzq = new BufferedImage[4];
	arriba = new BufferedImage[4];
	arribaDer = new BufferedImage[4];
	der = new BufferedImage[4];
	abajoDer = new BufferedImage[4];
	abajo = new BufferedImage[4];
	abajoIzq = new BufferedImage[4];
	
	for(int i = 0; i < 4; i++) {
		Point punto = new Point(ancho*i, 0);
		izq[i] = spriteHumano.obtenerCuadro(punto, ancho, alto);
	}
	
	for(int i = 0; i < 4; i++) {
		Point punto = new Point(ancho*i, alto);
		arribaIzq[i] = spriteHumano.obtenerCuadro(punto, ancho, alto);
	}
	
	for(int i = 0; i < 4; i++) {
		Point punto = new Point(ancho*i, alto*2);
		arriba[i] = spriteHumano.obtenerCuadro(punto, ancho, alto);
	}
	
	for(int i = 0; i < 4; i++) {
		Point punto = new Point(ancho*i, alto*3);
		arribaDer[i] = spriteHumano.obtenerCuadro(punto, ancho, alto);
	}
	
	for(int i = 0; i < 4; i++) {
		Point punto = new Point(ancho*i, alto*4);
		der[i] = spriteHumano.obtenerCuadro(punto, ancho, alto);
	}
	
	for(int i = 0; i < 4; i++) {
		Point punto = new Point(ancho*i, alto*5);
		abajoDer[i] = spriteHumano.obtenerCuadro(punto, ancho, alto);
	}
	
	for(int i = 0; i < 4; i++) {
		Point punto = new Point(ancho*i, alto*6);
		abajo[i] = spriteHumano.obtenerCuadro(punto, ancho, alto);
	}
	
	for(int i = 0; i < 4; i++) {
		Point punto = new Point(ancho*i, alto*7);
		abajoIzq[i] = spriteHumano.obtenerCuadro(punto, ancho, alto);
	}
	
	 sprite.add(izq);
	 sprite.add(arribaIzq);
	 sprite.add(arriba);
	 sprite.add(arribaDer);
	 sprite.add(der);
	 sprite.add(abajoDer);
	 sprite.add(abajo);
	 sprite.add(abajoIzq);
	 
	 
	 cuadroCesped = cargarImagen(Constantes.PATH_CESPED);
	 cuadroAgua = cargarImagen(Constantes.PATH_AGUA);
	 cuadroRoca = cargarImagen(Constantes.PATH_ROCA);
	 cuadroPiedraNo = cargarImagen(Constantes.PATH_PIEDRAN);
	 CuadroTierra = cargarImagen(Constantes.PATH_TIERRA);
}


public static void cargarSuper() {

	ancho = 256;
	alto = 256;
	
	
	HojaSprite spriteSuper = new HojaSprite(cargarImagen(Constantes.SPRITE_SUPER));
	
	izq = new BufferedImage[4];
	arribaIzq = new BufferedImage[4];
	arriba = new BufferedImage[4];
	arribaDer = new BufferedImage[4];
	der = new BufferedImage[4];
	abajoDer = new BufferedImage[4];
	abajo = new BufferedImage[4];
	abajoIzq = new BufferedImage[4];
	
	for(int i = 0; i < 4; i++) {
		Point punto = new Point(ancho*i, 0);
		izq[i] = spriteSuper.obtenerCuadro(punto, ancho, alto);
	}
	
	for(int i = 0; i < 4; i++) {
		Point punto = new Point(ancho*i, alto);
		arribaIzq[i] = spriteSuper.obtenerCuadro(punto, ancho, alto);
	}
	
	for(int i = 0; i < 4; i++) {
		Point punto = new Point(ancho*i, alto*2);
		arriba[i] = spriteSuper.obtenerCuadro(punto, ancho, alto);
	}
	
	for(int i = 0; i < 4; i++) {
		Point punto = new Point(ancho*i, alto*3);
		arribaDer[i] = spriteSuper.obtenerCuadro(punto, ancho, alto);
	}
	
	for(int i = 0; i < 4; i++) {
		Point punto = new Point(ancho*i, alto*4);
		der[i] = spriteSuper.obtenerCuadro(punto, ancho, alto);
	}
	
	for(int i = 0; i < 4; i++) {
		Point punto = new Point(ancho*i, alto*5);
		abajoDer[i] = spriteSuper.obtenerCuadro(punto, ancho, alto);
	}
	
	for(int i = 0; i < 4; i++) {
		Point punto = new Point(ancho*i, alto*6);
		abajo[i] = spriteSuper.obtenerCuadro(punto, ancho, alto);
	}
	
	for(int i = 0; i < 4; i++) {
		Point punto = new Point(ancho*i, alto*7);
		abajoIzq[i] = spriteSuper.obtenerCuadro(punto, ancho, alto);
	}
	
	 sprite.add(izq);
	 sprite.add(arribaIzq);
	 sprite.add(arriba);
	 sprite.add(arribaDer);
	 sprite.add(der);
	 sprite.add(abajoDer);
	 sprite.add(abajo);
	 sprite.add(abajoIzq);
	 
	 
	 cuadroCesped = cargarImagen(Constantes.PATH_CESPED);
	 cuadroAgua = cargarImagen(Constantes.PATH_AGUA);
	 cuadroRoca = cargarImagen(Constantes.PATH_ROCA);
	 cuadroPiedraNo = cargarImagen(Constantes.PATH_PIEDRAN);
	 CuadroTierra = cargarImagen(Constantes.PATH_TIERRA);
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

	public static LinkedList<BufferedImage[]> getSprite() {
		return sprite;
	}
	
}
