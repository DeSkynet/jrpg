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
		Assert.assertEquals(7, bender.obtenerPuntosDeDefensa());
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
		Assert.assertEquals(0+2, aliBaba.obtenerPuntosDeDefensa());
		///OBTENGO LOS PUNTOS DE DEFENSA
		Assert.assertEquals(10+5, aliBaba.obtenerPuntosDeAtaque());
	}
	
	@Test
	public void veoAltura() {
		Personaje aliBaba = new Humano(new Ladron(),"ladro1");
		Assert.assertEquals(180, aliBaba.getAltura());
	}
	
	@Test
	public void defensaDeLaCasta() {
		Personaje bender = new Robot(new Ladron(),"ladro1");
		Assert.assertEquals(5+2, bender.obtenerPuntosDeDefensa());
	}
	
}
