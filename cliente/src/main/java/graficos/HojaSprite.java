package graficos;

import java.awt.Point;
import java.awt.image.BufferedImage;

public class HojaSprite {
	private BufferedImage sprite;

	public HojaSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}
	
	public BufferedImage getSprite() {
		return sprite;
	}

	public BufferedImage obtenerCuadro(Point punto, int ancho, int alto) {
		return sprite.getSubimage(punto.x, punto.y, ancho, alto);
	}
	
	
	

}
