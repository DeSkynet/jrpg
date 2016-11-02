package test;

import org.junit.*;
import personaje.*;
import personaje.castas.Ladron;


public class CastaTest {
	
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
		Assert.assertEquals(12, bender.obtenerPuntosDeDefensa());
	}
	
	@Test
	public void creoUnaCasta() {
		Personaje aliBaba = new Humano(new Ladron(),"ladro1");
		Assert.assertEquals("Ladron", aliBaba.casta.getCasta());
	}
	
	@Test
	public void creoUnaCastaYVeoSuComportamiento() {
		Personaje aliBaba = new Humano(new Ladron(),"ladro1");
		
		///OBTENGO EL NOMBRE DE LA CASTA
		Assert.assertEquals("Ladron", aliBaba.casta.getCasta());
		///OBTENGO LOS PUNTOS DE ATAQUE
		Assert.assertEquals(0 + 7, aliBaba.obtenerPuntosDeDefensa());
		///OBTENGO LOS PUNTOS DE DEFENSA
		Assert.assertEquals(10 + 2, aliBaba.obtenerPuntosDeAtaque());
	}
	

	
	@Test
	public void defensaDeLaCasta() {
		Personaje bender = new Robot(new Ladron(),"ladro1");
		Assert.assertEquals(5 + 7, bender.obtenerPuntosDeDefensa());
	}
	
}
