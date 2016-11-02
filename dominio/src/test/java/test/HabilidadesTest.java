package test;

import org.junit.Assert;
import org.junit.Test;

import habilidades.Alentizar;
import personaje.*;
import personaje.castas.Hechicero;
import personaje.castas.Luchador;

public class HabilidadesTest {
	
	@Test
	public void LanzoHabilidadAPersonaje() {
		Personaje ironMan = new SuperHeroe(new Luchador(), "IronMan");
		Assert.assertEquals(4 + 5, ironMan.getDestreza());
		new Alentizar().afectar(ironMan);
		Assert.assertEquals( 4 + 5 - 5, ironMan.getDestreza());
	}
	
	@Test
	public void AgregoHabilidad() {
		Personaje vivi = new Humano(new Hechicero(), "Vivi");
		Assert.assertEquals(0, vivi.casta.getCantidadDeHabilidades());
		vivi.casta.agregarHabilidad("Alentizar", new Alentizar());
		Assert.assertEquals(1, vivi.casta.getCantidadDeHabilidades());
	}

	@Test
	public void LanzoHabilidadPorNombre() {
		Personaje radagast = new Humano(new Hechicero(), "Radagast");
		radagast.casta.agregarHabilidad("Alentizar", new Alentizar());
		Personaje ironMan = new SuperHeroe(new Luchador(), "IronMan");
		Assert.assertEquals(4 + 5, ironMan.getDestreza());
		radagast.casta.usarHabilidad("Alentizar", ironMan);
		Assert.assertEquals( 4 + 5 - 5, ironMan.getDestreza());
	}

}
