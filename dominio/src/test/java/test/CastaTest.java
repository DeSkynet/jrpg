package test;

import org.junit.*;
import personaje.*;
import personaje.castas.Ladron;


public class CastaTest {
	
	@Test
	public void creoUnaCasta() {
		Personaje aliBaba = new Humano(new Ladron());
		Assert.assertEquals("Ladron", aliBaba.casta.getCasta());
	}
	
	@Test
	public void creoUnaCastaYVeoSuComportamiento() {
		Personaje aliBaba = new Humano(new Ladron());
		
		///OBTENGO EL NOMBRE DE LA CASTA
		Assert.assertEquals("Ladron", aliBaba.casta.getCasta());
		///OBTENGO LOS PUNTOS DE ATAQUE
		Assert.assertEquals(0+2, aliBaba.obtenerPuntosDeDefensa());
		///OBTENGO LOS PUNTOS DE DEFENSA
		Assert.assertEquals(10+5, aliBaba.obtenerPuntosDeAtaque());
	}
	
	@Test
	public void veoAltura() {
		Personaje aliBaba = new Humano(new Ladron());
		Assert.assertEquals(180, aliBaba.getAltura());
	}
	
	@Test
	public void defensaDeLaCasta() {
		Personaje bender = new Robot(new Ladron());
		Assert.assertEquals(5+2, bender.obtenerPuntosDeDefensa());
	}
}
