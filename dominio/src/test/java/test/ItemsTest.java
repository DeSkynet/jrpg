package test;

import org.junit.Assert;
import org.junit.Test;

import personaje.Humano;
import personaje.Personaje;
import personaje.items.ConCascoDeMithril;
import personaje.items.ConEspadaExcalibur;
import personaje.items.ConEspadaLaser;

//import personajes.Humano;
//import personajes.Personaje;
//import personajes.items.ConCascoDeMithril;
//import personajes.items.ConEspadaExcalibur;
//import personajes.items.ConEspadaLaser;



public class ItemsTest {
	
	@Test
	public void testConUnaEspadaLaser() {
		Personaje luke = new Humano();
		Assert.assertEquals(10, luke.obtenerPuntosDeAtaque());
		
		//Se agrega item
		luke = new ConEspadaLaser(luke);
		Assert.assertEquals(10+10, luke.obtenerPuntosDeAtaque());
	}
	
	@Test
	public void testConDosEspadasLaser() {
		Personaje luke = new Humano();
		Assert.assertEquals(10, luke.obtenerPuntosDeAtaque());
		
		//Se agrega item
		luke = new ConEspadaLaser(luke);
		Assert.assertEquals(10+10, luke.obtenerPuntosDeAtaque());
		
		//Se agrega item
		luke = new ConEspadaLaser(luke);
		Assert.assertEquals(10+10+10, luke.obtenerPuntosDeAtaque());
	}
	
	@Test
	public void testDosEspadasDistintas() {
		Personaje luke = new Humano();
		Assert.assertEquals(10, luke.obtenerPuntosDeAtaque());
		
		//Se agrega item
		luke = new ConEspadaLaser(luke);
		Assert.assertEquals(10+10, luke.obtenerPuntosDeAtaque());
		
		//Se agrega item
		luke = new ConEspadaExcalibur(luke);
		Assert.assertEquals(10+10+20, luke.obtenerPuntosDeAtaque());
	}
	
	@Test
	public void testUnaEspadaExcaliburYUnCascoMithril() {
		Personaje luke = new Humano();
		Assert.assertEquals(10, luke.obtenerPuntosDeAtaque());
		Assert.assertEquals(0, luke.obtenerPuntosDeDefensa());
		
		//Se agrega item
		luke = new ConEspadaExcalibur(luke);
		Assert.assertEquals(10+20, luke.obtenerPuntosDeAtaque());
		
		//Se agrega 2 item
		luke = new ConCascoDeMithril(luke);
		Assert.assertEquals(5, luke.obtenerPuntosDeDefensa());
	}
}
