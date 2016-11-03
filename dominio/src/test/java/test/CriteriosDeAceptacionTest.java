package test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import personaje.*;
import personaje.castas.*;
import personaje.items.ConCascoDeMithril;
import personaje.items.ConEspadaExcalibur;

/*
 * 	PARA ALGUNOS TEST TODAVIA SON NECESARIO PLANTEAR/REFORMAR CLASES 
 *  BATALLA/ALIANZA/PERSONAJE
 */

public class CriteriosDeAceptacionTest {
	
	//Historia de Usuario 01-Elegir Raza
	@Test
	public void creoUnaRaza() {
		Personaje aragorn = new Humano();
		Assert.assertEquals(10, aragorn.obtenerPuntosDeAtaque());
	}

	//Historia de Usuario 02-Elegir Casta
	@Test
	public void creoUnaCastaYVeoElCambioEnSusAtributos() {
		Personaje bender = new Robot(new Ladron(), "ladro1");
		Assert.assertEquals(5 + 7, bender.obtenerPuntosDeDefensa());
	}
	
	//Historia de usuario 03-Experiencia
	@Test
	public void peleoParaGanarExperiencia() {
		int turno = 0;

		Personaje aliBaba = new Humano(new Ladron(), "Alí Baba");
		Personaje robocop = new Robot(new Luchador(), "Robocop");
		
		aliBaba.crearAlianza("Los Ladris");
		
		Assert.assertEquals(0, aliBaba.getExperiencia());
		Assert.assertEquals(0, robocop.getExperiencia());
		
		//INICIA COMBATE
		while(robocop.estaVivo() && aliBaba.estaVivo()) {
			if(turno==0) {
				robocop.atacar(aliBaba);
				turno = 1;
			} else {
				aliBaba.atacar(robocop);
				turno = 0;
			}	
		}
		
		Assert.assertFalse(aliBaba.estaVivo());
		Assert.assertTrue(robocop.estaVivo());
		
		//AL GANAR GANA EXPERIENCIA
		robocop.aumentarExperiencia(aliBaba.getAlianza());
		Assert.assertEquals(100, robocop.getExperiencia());
	}
	
	//Historia de Usuario 04-Subir de Nivel
	@Test
	public void ganoExperienciaParaSubirDeNivel() {
		int turno = 0;

		Personaje aliBaba = new Humano(new Ladron(), "Alí Baba");
		Personaje robocop = new Robot(new Luchador(), "Robocop");
		
		aliBaba.crearAlianza("Los Ladris");
		
		Assert.assertEquals(1, aliBaba.getNivel());
		Assert.assertEquals(1, robocop.getNivel());
		
		//INICIA COMBATE
		while(robocop.estaVivo() && aliBaba.estaVivo()) {
			if(turno==0) {
				robocop.atacar(aliBaba);
				turno = 1;
			} else {
				aliBaba.atacar(robocop);
				turno = 0;
			}	
		}
		
		Assert.assertFalse(aliBaba.estaVivo());
		Assert.assertTrue(robocop.estaVivo());
		
		robocop.aumentarExperiencia(aliBaba.getAlianza());
		Assert.assertEquals(2, robocop.getNivel());
	}
	
	//Historia de Usuario 05-Obtener Items
	@Test
	public void testConUnaEspadaExcalibur() {
		Personaje luke = new Humano();
		Assert.assertEquals(10, luke.obtenerPuntosDeAtaque());
		Assert.assertEquals(0, luke.obtenerPuntosDeDefensa());
		Assert.assertFalse(luke.tieneEquipamiento());
		//Se agrega item
		luke.equiparItem(new ConEspadaExcalibur());
		Assert.assertEquals(10+20, luke.obtenerPuntosDeAtaque());
		Assert.assertEquals(0, luke.obtenerPuntosDeDefensa());
		Assert.assertTrue(luke.tieneEquipamiento());	//Tiene item
	
	}
	
	//Historia de Usuario 06-Alianzas
	@Test
	public void testDeAlianzas() {
		Personaje p1 = new Humano(new Ladron(), "p1");
		Personaje p2 = new Humano(new Ladron(), "p2");
		NPC npc = new NPC(1);
		
		p1.crearAlianza("Los Indestructibles");
		
		p1.aliar(p2);
		p2.aliar(npc);
		
		Assert.assertEquals(3, p1.getAlianza().cantidadDeAliados());
		Assert.assertEquals(3, p2.getAlianza().cantidadDeAliados());
		Assert.assertEquals(3, npc.getAlianza().cantidadDeAliados());
		
	}
	
		//Historia de usuario 07-Quitar Item de un Decorator.
		@Test
		public void testQuitarItem() {
			Personaje luke = new Humano();
			Personaje darthVader = new Humano();
			Assert.assertEquals(10, luke.obtenerPuntosDeAtaque());
			Assert.assertEquals(0, luke.obtenerPuntosDeDefensa());
			
			//Se agrega item
			luke.equiparItem(new ConEspadaExcalibur());
			Assert.assertEquals(10+20, luke.obtenerPuntosDeAtaque());
			Assert.assertEquals(0, luke.obtenerPuntosDeDefensa());

			//Se agrega 2 item
			luke.equiparItem(new ConCascoDeMithril());
			Assert.assertEquals(5, luke.obtenerPuntosDeDefensa());
			Assert.assertEquals(10+20, luke.obtenerPuntosDeAtaque());
			
			luke.equiparItem(new ConCascoDeMithril());
			Assert.assertEquals(5+5, luke.obtenerPuntosDeDefensa());
			Assert.assertEquals(10+20, luke.obtenerPuntosDeAtaque());
			
			//Asigno Item que le saque a luke
			Equipamiento ItemMayor=luke.quitarItemMayor();
			
			darthVader.equiparItem(ItemMayor);
			Assert.assertEquals(10+20, darthVader.obtenerPuntosDeAtaque());
			Assert.assertEquals(0, darthVader.obtenerPuntosDeDefensa());
			Assert.assertTrue(darthVader.tieneEquipamiento());
			
			
			Assert.assertEquals(10, luke.obtenerPuntosDeAtaque());
			Assert.assertEquals(5+5, luke.obtenerPuntosDeDefensa());
			Assert.assertTrue(luke.tieneEquipamiento());
			
			luke.quitarItemMayor();
			Assert.assertEquals(10, luke.obtenerPuntosDeAtaque());
			Assert.assertEquals(5, luke.obtenerPuntosDeDefensa());
			Assert.assertTrue(luke.tieneEquipamiento());
			
			luke.quitarItemMayor();
			Assert.assertEquals(10, luke.obtenerPuntosDeAtaque());
			Assert.assertEquals(0, luke.obtenerPuntosDeDefensa());
			Assert.assertFalse(luke.tieneEquipamiento());
		
			ItemMayor= luke.quitarItemMayor();	//YA NO TIENE ITEM, NO DEBERIA PASAR NADA.
			Assert.assertEquals(10, luke.obtenerPuntosDeAtaque());
			Assert.assertEquals(0, luke.obtenerPuntosDeDefensa());
			Assert.assertFalse(luke.tieneEquipamiento());
			
			darthVader.equiparItem(ItemMayor);	//Pruebo que no se le asigne nada a darth.
			Assert.assertEquals(10+20, darthVader.obtenerPuntosDeAtaque());
			Assert.assertEquals(0, darthVader.obtenerPuntosDeDefensa());
	}

	//Historia de usuario 08-Habilidad SuperHeroe
	@Test
	public void pruebaHabilidadHumano(){
		Personaje smith = new Humano();
		Personaje miller = new Humano();
		
		miller.crearAlianza("Los Yankees");
		miller.aliar(smith);
		
		Assert.assertEquals(10 + 1, miller.obtenerPuntosDeAtaque());
		Assert.assertEquals(10 + 1, smith.obtenerPuntosDeAtaque());
		
		Personaje duncan = new Humano(); 
		
		smith.aliar(duncan);
		
		Assert.assertEquals(10 + 2, miller.obtenerPuntosDeAtaque());
		Assert.assertEquals(10 + 2, smith.obtenerPuntosDeAtaque());
		Assert.assertEquals(10 + 2, duncan.obtenerPuntosDeAtaque());
	}

	@Ignore
	public void robotQueRevive() {
		
		int turno = 0;

		Personaje aliBaba = new Humano(new Ladron(), "Alí Baba");
		Personaje juliVelazquez = new Humano(new Ladron(), "JuliV");
		Personaje macri = new Humano(new Ladron(), "macri");
		
		aliBaba.crearAlianza("Los Ladris");
		
		Personaje robocop = new Robot(new Luchador(), "Robocop");
		Personaje ironMan = new SuperHeroe(new Luchador(), "ironMan");
		Personaje superMan = new SuperHeroe(new Luchador(), "superMan");
		
		robocop.crearAlianza("Los Jodidos");
		
		//INICIA COMBATE
		while(robocop.getAlianza().estanVivos() && aliBaba.getAlianza().estanVivos()) {
			///// QUEDA EN TERMINAR....
		}
	}
	
	//Historia de usuario 10 - Habilidad Alien
	@Test
	public void habilidadAlien() {
		Personaje kodos = new Alien();
		Personaje kang = new Alien();

		kodos.atacar(kang);
		
		//kodos ataca a kang, kang queda con 93 de salud y kodos se cura un 10% de lo que ataco, pero como su salud esta al maximo queda de esa manera
		Assert.assertEquals(93, kang.getSalud());
		Assert.assertEquals(true, kodos.esSaludMaxima());
		
		//ahora kang ataca a kodos y recupera 10% de energia
		kang.atacar(kodos);
		Assert.assertEquals(93 + 1, kang.getSalud());
	}
	
	//Historia de usuario 11-Habilidad SuperHeroe
	@Test
	public void habilidadSuperHeroe() {
		Personaje ironMan = new SuperHeroe();
		Personaje thor = new SuperHeroe();
	
		Assert.assertEquals(20, thor.obtenerPuntosDeAtaque());
	
		//ironMan ataca a thor, entonces su ataque aumenta en 1
		ironMan.atacar(thor);
		Assert.assertEquals(20 + 1, thor.obtenerPuntosDeAtaque());
	}
	
	//Historia de usuario 12-Bonificacion Ladron
	@Test
	public void bonificacionLadron() {
		Personaje politico = new Humano(new Ladron(), "politico");
		
		Assert.assertEquals(0 + 7, politico.getDestreza());
	}
	
	//Historia de usuario 13-Bonificacion Luchador
	@Test
	public void bonificacionLuchador() {
		Personaje estIngenieria = new Humano(new Luchador(), "estIngenieria");
		
		Assert.assertEquals(10 + 7, estIngenieria.getFuerza());
	}
		
	//Historia de usuario 14-Bonificacion Hechicero
	@Test
	public void bonificacionechicero() {
		Personaje gandalf = new Humano(new Hechicero(), "gandalf");
		
		Assert.assertEquals(5 + 7, gandalf.getInteligencia());
	}
}
