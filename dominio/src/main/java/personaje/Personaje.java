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
	public void serAtacado(int da�o) {
		this.salud -= da�o;
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
	///EQUIVALENTE AL DA�O REALIZADO.
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
		this.alianza.agregarAliado(this);
	}
	
	public void aliar(Personaje per) {
		if(per.alianza == null || this.alianza.cantidadDeAliados() < cantidadMaximaDeAliados) {
			per.alianza = this.alianza;
			this.alianza.agregarAliado(per);
		} else 
			System.out.println("NO PUEDE AGREGAR...");
	}
	
	public void desaliar(Personaje per) {
		if(this.alianza != null) {
			this.alianza.eliminarAliado(per);
			per.alianza = null;
		}
	}
	
	static final int cantidadMaximaDeAliados = 10;

	
}
