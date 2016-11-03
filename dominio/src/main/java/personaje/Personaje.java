package personaje;

import java.util.Iterator;

import constante.Constantes;
import interfaces.Atacable;
import personaje.Alianza;
import personaje.items.ItemMayor;

public abstract class Personaje implements Atacable {
	protected int coordenadaX = 0;
	protected int coordenadaY = 0;
	protected int experiencia = 0;
	protected int nivel = 1;
	protected int destreza;	///INDICA BENEFICIOS AL PERSONAJE EN LA BATALLA, COMO ESQUIVAR...
	protected int fuerza;
	protected int inteligencia;
	protected int energia = 100;
	protected int salud = 100;
	protected String nombre;
	public Casta casta = null;
	protected Alianza alianza = null;
	protected Equipamiento equipamiento = null;
	
	

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
			daño = 1;
		else
			daño -= this.calcularPuntosDeDefensa();
		
		this.salud -= daño;
		this.despuesDeSerAtacado();
	}
	
	//DEFENSA
	protected abstract int calcularPuntosDeDefensa();
	public int obtenerPuntosDeDefensa() {
		if(tieneEquipamiento()){	//SI TIENE EQUIPAMIENTO
			return calcularPuntosDeDefensa() + equipamiento.calcularPuntosDeDefensa();
		}
		return calcularPuntosDeDefensa();
	}
	
	protected void despuesDeSerAtacado() { 
		this.energia+= Constantes.SUBA_DE_ENERGIA_DESPUES_DE_ATAQUE;
	}
	
	//ATAQUE
	protected abstract boolean puedeAtacar();
	protected abstract int calcularPuntosDeAtaque();
	
	///LO PUEDE USAR EL ALIEN COMO UN MODO DE REGENERARSE
	///POR EJ, DESPUES DE ATACAR RECUPERA UN PORCENTAJE DE ENERGIA
	///EQUIVALENTE AL DAÑO REALIZADO.
	protected void despuesDeAtacar() { }
	public int obtenerPuntosDeAtaque() {
		if(tieneEquipamiento()){	//SI TIENE EQUIPAMIENTO
			return calcularPuntosDeAtaque() + equipamiento.calcularPuntosDeAtaque();
		}
		return calcularPuntosDeAtaque();
	}
	
	/// GETTERS
	public int getDestreza() {
		return destreza;
	}

	public int getFuerza() {
		return fuerza;
	}

	public int getInteligencia() {
		return inteligencia;
	}
	
	public int getExperiencia() {
		return this.experiencia;
	}
	
	public int getNivel() {
		return this.nivel;
	}

	public Casta getCasta() {
		return casta;
	}

	public int getSalud() {
		return salud;
	}
	
	public Alianza getAlianza() {
		return alianza;
	}
	
	/// SETTERS
	
	public void setDestreza(int destreza) {
		this.destreza = destreza;
	}

	public void setSalud(int salud) {
		this.salud = salud;
	}

	// BOOLEANS
	public boolean esSaludMaxima() {
		return this.salud==100;
	}
	
	public boolean esEnergiaMaxima() {
		return this.energia==100;
	}
	
	public boolean estaVivo() {
		return this.salud > 0;
	}
	
	/// ALIANZA
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
	
	public boolean existeAlianza(){
		return this.alianza != null;
	}
	
	///Aumentar Experiencia
	public void aumentarExperiencia(Alianza alianzaEnemiga) {
		Iterator<Personaje> it = alianzaEnemiga.alianza.iterator();
		while(it.hasNext()){
			this.experiencia+= Constantes.EXPERIENCIA_POR_VICTORIA * it.next().nivel; ///1500 SON 3 ALIADOS
			if(esTopeDeExperiencia())
				subirDeNivel();
		}
	}
	
	public boolean esTopeDeExperiencia() {
		return this.experiencia >= Constantes.EXPERERIENCIA_TOPE_POR_NIVEL[this.nivel];
	}
	
	public void subirDeNivel() {
		if(this.nivel < Constantes.NIVEL_MAXIMO){
		this.nivel++;
		}
	}
	
	//ITEMS
	public boolean equiparItem(Equipamiento item) {
		if(item!=null){
		item.equipamiento=this.equipamiento;
		this.equipamiento=item;
		return true;
		}
		return false;
	}
	
	public boolean tieneEquipamiento(){
		return this.equipamiento != null;
	}
	
	protected ItemMayor calcularItemMayor(){
		return this.equipamiento.calcularItemMayor();
	}
	
	public Equipamiento quitarItemMayor(){
		ItemMayor maxItem;
		String claseMaximaEfi;
		if(this.tieneEquipamiento()==true){
			maxItem=this.equipamiento.calcularItemMayor();  //Devuelve un objeto ItemTest
			claseMaximaEfi=maxItem.getClase();	//Se queda solo con el nombre de la clase.
			Equipamiento equipamientoMax=this.quitarItem(claseMaximaEfi);		
			return equipamientoMax;			//Devuelve el item mas poderoso.
		}
		else return null;	//no tienen item.
	}
	
	protected Equipamiento quitarItem(String maxEfi){

		if( this.equipamiento.getClass().getSimpleName().compareTo(maxEfi) == 0 && this.tieneEquipamiento()==true ){
			Equipamiento aux=this.equipamiento;
			this.equipamiento=aux.equipamiento;
			aux.equipamiento=null;
			return aux;
		}
		else if(this.equipamiento.tieneEquipamiento()==true){
			return this.equipamiento.quitarItem(maxEfi);
		}
		return null;	//devuelve null para el caso donde tieneItem()==false
	}

}
