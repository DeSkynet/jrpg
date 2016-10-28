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
	
	
	public int getDestreza() {
		return destreza;
	}

	public int getFuerza() {
		return fuerza;
	}

	public int getInteligencia() {
		return inteligencia;
	}

	public Casta casta = null;
	protected Alianza alianza = null;
	
	public Alianza getAlianza() {
		return alianza;
	}

	public final void atacar(Atacable atacado) {
		if (puedeAtacar()) {
			atacado.serAtacado(calcularPuntosDeAtaque());
			energia -= calcularPuntosDeAtaque();
			despuesDeAtacar();
		}
	}
	
	@Override
	public void serAtacado(int daño) {
		if( daño <= this.calcularPuntosDeDefensa())
			daño = 0;
		else
			daño -= this.calcularPuntosDeDefensa();
		
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
	
	public void aliar(Personaje per){
		this.alianza.aliar(per);
	}
	
	public void desaliar(){
		this.alianza.desaliar(this);
	}
	
}
