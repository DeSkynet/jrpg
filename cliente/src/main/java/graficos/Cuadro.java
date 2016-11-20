package graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cuadro {
	private int anchoTile;
	private int altoTile;
	private BufferedImage img;
	private boolean atravesable;
	
	public Cuadro(int anchoTile, int altoTile, String pathImg, boolean atravesable) {
		this.anchoTile = anchoTile;
		this.altoTile = altoTile;
		this.atravesable = atravesable;
		
		try {
			this.img = ImageIO.read(BufferedImage.class.getResource(pathImg));
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	public boolean esAtravesable(){
		return atravesable;
	}
	
	public BufferedImage getCuadro() {
		return this.img;
	}

	public int getAnchoTile() {
		return anchoTile;
	}

	public void setAnchoTile(int anchoTile) {
		this.anchoTile = anchoTile;
	}

	public int getAltoTile() {
		return altoTile;
	}

	public void setAltoTile(int altoTile) {
		this.altoTile = altoTile;
	}
	
	
}
