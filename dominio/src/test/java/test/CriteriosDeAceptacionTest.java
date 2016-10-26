package test;

import org.junit.Assert;
import org.junit.Test;

import personaje.*;
import personaje.castas.*;
import personaje.items.ConEspadaExcalibur;


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
		Assert.assertEquals(5 + 2, bender.obtenerPuntosDeDefensa());
	}
	
	/// FALTAN HU 3 Y 4, SE NECESITA BATALLA PARA PODER VER LA SUBA DE NIVEL
	
	//Historia de Usuario 05-Obtener Items
	@Test
	public void testConUnaEspadaExcalibur() {
		Personaje arturo = new Humano();
		
		arturo = new ConEspadaExcalibur(arturo);
		Assert.assertEquals(10 + 20, arturo.obtenerPuntosDeAtaque());
	}
	
	/// faltan hu 6 y 7 POR FALTA DE CLASE BATALLA

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

	/// FALTA HU 9 (HABILIDAD ROBOT) POR FALTA DE CLASE BATALLA
	
	//Historia de usuario 10 - Habilidad Alien
	@Test
	public void habilidadAlien() {
		Personaje kodos = new Alien();
		Personaje kang = new Alien();

		kodos.atacar(kang);
		
		//kodos ataca a kang, kang queda con 90 de salud y kodos se cura un 10% de lo que ataco, pero como su salud esta al maximo queda de esa manera
		Assert.assertEquals(90, kang.getSalud());
		Assert.assertEquals(true, kodos.esSaludMaxima());
		
		//ahora kang ataca a kodos y recupera 10% de energia
		kang.atacar(kodos);
		Assert.assertEquals(90 + 1, kang.getSalud());
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
	
	///FALTAN TEST DE HU 12, 13 Y 14
	
}
