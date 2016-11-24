package graficos;


public class CuadroAgua extends Cuadro {
	
	public CuadroAgua(int id) {
		super(Sprite.cuadroAgua, id);
	}
	
	@Override
	public boolean noEsAtravesable() {
		return true;
	}
}
