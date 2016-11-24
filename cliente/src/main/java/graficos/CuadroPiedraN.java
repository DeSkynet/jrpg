package graficos;


public class CuadroPiedraN extends Cuadro {
	public CuadroPiedraN(int id) {
		super(Sprite.cuadroPiedraNo, id);
	}
	@Override
	public boolean noEsAtravesable() {
		return true;
	}
}
