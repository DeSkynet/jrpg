package personaje;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

import constante.Constantes;

public class Batalla {

	protected String accion;
	protected String habilidadSelecionada;
	protected Personaje ganador;
	protected Personaje objetivoAtaque;
	protected Personaje atacante;
	protected Personaje enemigo;
	protected Alianza alianzaAtacante;
	protected Alianza alianzaEnemiga;
	protected PriorityQueue<Personaje> colaDeTurnos;

	
//	public Alianza clone(){
//		Alianza copiaAlianzaAtacante = new Alianza (alianzaAtacante);
//		
//		return copiaAlianzaAtacante; 
//	}
//	
//	public Alianza clone2(){
//		Alianza copiaAlianzaEnemiga = alianzaEnemiga;
//		
//		return copiaAlianzaEnemiga;
//	}
	
	public Batalla(Personaje atacante,Personaje enemigo){
		
		this.atacante = atacante;
		this.enemigo = enemigo;
		
//		alianzaAtacante.clone();
		
		///VERIFICO SI LA PELEA ES ENTRE ALIANZAS O PERSONAJES SIN ALIANZAS
		if(atacante.existeAlianza()){
			alianzaAtacante = atacante.alianza;
		}
		else{
			alianzaAtacante = null; 
		}
		
		if(enemigo.existeAlianza()){
			alianzaEnemiga = enemigo.alianza;
		}
		else{
			alianzaEnemiga = null; 
		}
	}
	
	public void batallaAutomatica(){
		
		cargarCola(atacante, enemigo);
		
		Personaje atacante = null;
		Personaje atacado = null;
		
		while(!colaDeTurnos.isEmpty()){
			
				atacante = colaDeTurnos.poll();
				atacado = colaDeTurnos.poll();

			if(atacante != null && atacado != null){
				atacante.atacar(atacado);
				atacado.atacar(atacante);
			}
			if(atacante.getSalud()>0 && atacado.getSalud()>0){
				colaDeTurnos.add(atacante);
				colaDeTurnos.add(atacado);
			}
		}
		ganador = determinarGanador();
		CalcularRecompensas();
		repartirExperiencia();

	}
		
	public void Batallar(){
		
		cargarCola(atacante, enemigo);

		while(!hayGanador()){
			while(!colaDeTurnos.isEmpty()){
				realizarAccion(colaDeTurnos.poll(),getObjetivoAtaque(), getAccion());
			}	
		}
		ganador = determinarGanador();
		CalcularRecompensas();
		repartirExperiencia();
		despuesDeBatalla();
	}
	
	private Personaje determinarGanador() {
		if( (alianzaAtacante != null && alianzaAtacante.estanVivos()) || alianzaAtacante == null && atacante.estaVivo() )
			return atacante;
		else
			return enemigo;	
	}

	private void despuesDeBatalla() {
		//Despues de batalla todos los atributos que se modificaron (por las habilidades) vuelven a la normalidad
		// A excepcion de salud, y la energia
		Iterator<Personaje> it = alianzaAtacante.alianza.iterator();
		Iterator<Personaje> it2 = atacante.alianza.alianza.iterator();
		
		while(it.hasNext() && it2.hasNext()){
			it2.next().fuerza = it.next().fuerza;
			it2.next().destreza = it.next().destreza;
			it2.next().inteligencia = it.next().inteligencia;
		}
		
		Iterator<Personaje> it3 = alianzaEnemiga.alianza.iterator();
		Iterator<Personaje> it4 = enemigo.alianza.alianza.iterator();
		
		while(it3.hasNext() && it4.hasNext()){
			it4.next().fuerza = it3.next().fuerza;
			it4.next().destreza = it3.next().destreza;
			it4.next().inteligencia = it3.next().inteligencia;
		}
	}

	private void repartirExperiencia() {
		if(ganador.existeAlianza()){
			Iterator<Personaje> it = ganador.alianza.alianza.iterator();
		while(it.hasNext()){
			it.next().experiencia+=Constantes.EXPERIENCIA_POR_VICTORIA;
			}
		}
		else
			ganador.experiencia +=Constantes.EXPERIENCIA_POR_VICTORIA;
		
	}

	private void CalcularRecompensas() {
		//ACA HAY QUE CALCULAR QUE ITEMS DESEQUIPAR, PARA TERMINARLO NECESITO ANDANDO EL DESEQUIPAR DECORADOR
		//PROPONGO UN SISTEMA DE VALORACION DE ITEMS Q DETERMINE CUAL TIRAR
		if(ganador.equals(alianzaAtacante)){
			
			Iterator<Personaje> it = enemigo.alianza.alianza.iterator();
			Iterator<Personaje> it2 = atacante.alianza.alianza.iterator();
			
			while(it2.hasNext() && it.hasNext()){
				it2.next().equipamiento = it.next().quitarItemMayor();
			}
		}
		else{
			Iterator<Personaje> it3 = enemigo.alianza.alianza.iterator();
			Iterator<Personaje> it4 = atacante.alianza.alianza.iterator();
			
			while(it3.hasNext() && it4.hasNext()){
				it3.next().equipamiento = it4.next().quitarItemMayor();
			}
		}
	}

	private void realizarAccion(Personaje personajeDeTurno, Personaje victima, String accion) {
		if(accion == "Atacar")
			personajeDeTurno.atacar(victima);
		else{ 
			if(accion == "Habilidad")
				personajeDeTurno.casta.usarHabilidad(getHabilidadSelecionada(), victima);
			
		else{								
		///ACA SALE SI O SI POR ACCION HUIR
			personajeDeTurno.desaliar();
			aplicarPenalidad(personajeDeTurno);
			}
		}	
	}
	
	private void aplicarPenalidad(Personaje personajeDeTurno) {
		///ACA HAY QUE PENSAR LA PENALIDAD
		personajeDeTurno.desaliar();
		personajeDeTurno.setSalud(1);
	}

	///ESTOS GETS LOS OBTENEMOS MEDIANTE INTERFAZ GRAFICA (Click Mouse)
	public String getAccion() {
		return accion;
	}

	public Personaje getObjetivoAtaque() {
		return objetivoAtaque;
	}
	
	public String getHabilidadSelecionada() {
		return habilidadSelecionada;
	}

	@SuppressWarnings("unchecked")
	public void cargarCola(Personaje atacante, Personaje enemigo){
		
		colaDeTurnos = new PriorityQueue<Personaje>(10,new ComparaDestreza());
		
		if(alianzaAtacante == null && atacante.estaVivo()){
			colaDeTurnos.add(atacante);
		}
		else{
			if(alianzaAtacante != null)
			{
			Iterator<Personaje> it = alianzaAtacante.alianza.iterator();
			while(it.hasNext()){
				Personaje perAux = it.next();
				if(perAux.estaVivo())
					colaDeTurnos.add(perAux);
				}
			}
		}
		
		if(alianzaEnemiga == null && enemigo.estaVivo()){
			colaDeTurnos.add(enemigo);
		}
		else{
			if(alianzaEnemiga !=null){
	
			Iterator<Personaje> it = alianzaEnemiga.alianza.iterator();
			
			while(it.hasNext()){
				Personaje perAux = it.next();
				if(perAux.estaVivo())
					colaDeTurnos.add(perAux);
				}
			}
		}
//		while(!colaDeTurnos.isEmpty()){
//			Personaje aux = colaDeTurnos.poll();
//			System.out.println(aux.nombre);
//			System.out.println(aux.getDestreza());
//		}
	}
	public boolean hayGanador(){
		if(alianzaAtacante != null && alianzaEnemiga != null) ///PELEAN DOS ALIANZAS
			return !alianzaAtacante.estanVivos() || !alianzaEnemiga.estanVivos();
		else{ 
			if(alianzaAtacante == null && alianzaEnemiga == null)	///PELEAN DOS PERSONAJES SIN ALIANZAS
				return !atacante.estaVivo() || !enemigo.estaVivo();
			else{
				if(alianzaAtacante != null && alianzaEnemiga == null)	///PELEAN UNA ALIANZA Y UN PJ
						return !alianzaAtacante.estanVivos() || !enemigo.estaVivo();
			}
		}
		return !atacante.estaVivo() || !alianzaEnemiga.estanVivos();
	}
}

	@SuppressWarnings("rawtypes")
	class ComparaDestreza implements Comparator{
	
	public int compare(Object per1, Object per2 ){
		int per1Destreza = ((Personaje)per1).getDestreza();
		int per2Destreza = ((Personaje)per2).getDestreza();
		if(per1Destreza > per2Destreza){
			return -1;
		}
		else if(per1Destreza < per2Destreza){
			return 1;
		}
		else{
			return 0; 
		}
	}
	
	
}