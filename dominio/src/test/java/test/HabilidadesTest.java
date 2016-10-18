package test;

import org.junit.Assert;
import org.junit.Test;

import personaje.*;

public class HabilidadesTest {
	
	//Historia de ususario 10-Habilidad Alien
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
	
	//Historia de ususario 11-Habilidad SuperHeroe
		@Test
		public void habilidadSuperHeroe() {
			Personaje ironMan = new SuperHeroe();
			Personaje thor = new SuperHeroe();
			
			Assert.assertEquals(20, thor.obtenerPuntosDeAtaque());
			
			//ironMan ataca a thor, entonces su ataque aumenta en 1
			ironMan.atacar(thor);
			Assert.assertEquals(21, thor.obtenerPuntosDeAtaque());
		}


}
