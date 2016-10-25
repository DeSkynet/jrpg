package personaje;

import interfaces.Atacable;
import personaje.Alianza;

public abstract class Personaje implements Atacable {
	protected int experiencia;
	protected int nivel;
	protected int destreza;	///INDICA BENEFICIOS AL PERSONAJE EN LA BATALLA, COMO ESQUIVAR...
	protected int fuerza;
	protected int inteligencia;
	protected int energia = 100;
	protected int salud = 100;
	protected int altura;
	protected String nombre;
	public Casta casta = null;
	public Alianza alianza = null;
	
	public final void atacar(Atacable atacado) {
		if (puedeAtacar()) {
			atacado.serAtacado(calcularPuntosDeAtaque());
			energia -= calcularPuntosDeAtaque();
			despuesDeAtacar();
		}
	}
	
	@Override
	public void serAtacado(int daño) {
		this.salud -= daño;
		this.despuesDeSerAtacado();
	}
	
	//DEFENSA
	protected abstract int calcularPuntosDeDefensa();
	public int obtenerPuntosDeDefensa() {
		return calcularPuntosDeDefensa();
	}
	
	protected void despuesDeSerAtacado() { }
	
	//ATAQUE
	protected abstract boolean puedeAtacar();
	protected abstract int calcularPuntosDeAtaque();
	
	///LO PUEDE USAR EL ALIEN COMO UN MODO DE REGENERARSE
	///POR EJ, DESPUES DE ATACAR RECUPERA UN PORCENTAJE DE ENERGIA
	///EQUIVALENTE AL DAÑO REALIZADO.
	protected void despuesDeAtacar() { }
	public int obtenerPuntosDeAtaque() {
		return calcularPuntosDeAtaque();
	}
	
	public boolean esSaludMaxima() {
		return this.salud==100;
	}
	
	public boolean esEnergiaMaxima() {
		return this.energia==100;
	}
	
	public int getAltura() {
		return this.altura;
	}

	public Casta getCasta() {
		return casta;
	}

	public int getSalud() {
		return salud;
	}
	
	///METODOS ALIANZA
	public void crearAlianza(String nombreAlianza) {
		this.alianza = new Alianza(nombreAlianza);
		this.alianza.agregarAliado(this);	//Se agrega en el momento que se crea la alianza entre dos personajes. 
	}
	
	public boolean aliar(Personaje per) {
		if(per.alianza == null && this.alianza.cantidadDeAliados() < CANTIDAD_MAXIMA_DE_ALIADOS) {
			per.alianza = this.alianza;
			this.alianza.agregarAliado(per);
		} else{
			if(per.alianza != null && this.alianza.cantidadDeAliados()+per.alianza.cantidadDeAliados() <= CANTIDAD_MAXIMA_DE_ALIADOS){
				this.alianza.agregarAlianza(per.alianza);
			}
			else return false;
		}
		return true;
		
	}
	
	public void desaliar(Personaje per) {
		if(this.alianza != null) {
			this.alianza.eliminarAliado(per);
			per.alianza = null;
		}
	}
	
	static final int CANTIDAD_MAXIMA_DE_ALIADOS = 5;

	
}
