package test;

import org.junit.Assert;
import org.junit.Test;

import personaje.Humano;
import personaje.Personaje;
import personaje.castas.Ladron;
import personaje.castas.Luchador;

public class AlianzaTest {
	@Test
	public void agregarAlianza() {
		Personaje p1 = new Humano(new Ladron(), "p1");
		Personaje p2 = new Humano(new Ladron(), "p2");
		
		p1.crearAlianza("Los Indestructibles");
		
		p1.aliar(p2);
		
		Assert.assertEquals(2, p1.alianza.cantidadDeAliados());
		Assert.assertEquals(2, p2.alianza.cantidadDeAliados());
	}
	
	@Test
	public void sonAliados() {
		Personaje julio = new Humano(new Ladron(), "julio");
		Personaje  lazaro = new Humano(new Ladron(), "lazaro");
		
		julio.crearAlianza("Comodoro");
		
		julio.aliar(lazaro);
		
		Assert.assertTrue(julio.alianza.estaEnAlianza(julio));
		Assert.assertTrue(julio.alianza.estaEnAlianza(lazaro));
		
		Assert.assertTrue(lazaro.alianza.estaEnAlianza(julio));
		Assert.assertTrue(lazaro.alianza.estaEnAlianza(lazaro));
	}
	
	@Test
	public void aliadosIndirectos() {
		Personaje gordoValor = new Humano(new Ladron(), "Gordo Valor");
		Personaje vitete = new Humano(new Ladron(), "Vitete");
		Personaje laGarzaSosa = new Humano(new Ladron(), "Garza Sosa");
		
		gordoValor.crearAlianza("Los Piratas");
		
		gordoValor.aliar(vitete);
		vitete.aliar(laGarzaSosa);
		
		Assert.assertTrue(gordoValor.alianza.estaEnAlianza(vitete));
		Assert.assertTrue(vitete.alianza.estaEnAlianza(laGarzaSosa));
		Assert.assertTrue(laGarzaSosa.alianza.estaEnAlianza(gordoValor));
	}
	
	@Test
	public void eliminarDeAlianza() {
		Personaje gordoValor = new Humano(new Ladron(), "Gordo Valor");
		Personaje vitete = new Humano(new Ladron(), "Vitete");
		Personaje laGarzaSosa = new Humano(new Ladron(), "Garza Sosa");
		
		gordoValor.crearAlianza("Los Piratas");
		
		gordoValor.aliar(vitete);
		vitete.aliar(laGarzaSosa);
		
		Assert.assertTrue(gordoValor.alianza.estaEnAlianza(vitete));
		Assert.assertTrue(vitete.alianza.estaEnAlianza(laGarzaSosa));
		Assert.assertTrue(laGarzaSosa.alianza.estaEnAlianza(gordoValor));
		
		vitete.desaliar(gordoValor);
		Assert.assertFalse(vitete.alianza.estaEnAlianza(gordoValor));
		Assert.assertFalse(laGarzaSosa.alianza.estaEnAlianza(gordoValor));
		Assert.assertFalse(gordoValor.alianza != null);
	}
	
	@Test
	public void eliminarDeAlianzaDos() {
		Personaje gordoValor = new Humano(new Ladron(), "Gordo Valor");
		Personaje vitete = new Humano(new Ladron(), "Vitete");
		Personaje laGarzaSosa = new Humano(new Ladron(), "Garza Sosa");
		
		gordoValor.crearAlianza("Los Piratas");
		
		gordoValor.aliar(vitete);
		vitete.aliar(laGarzaSosa);
		
		Assert.assertTrue(gordoValor.alianza.estaEnAlianza(vitete));
		Assert.assertTrue(vitete.alianza.estaEnAlianza(laGarzaSosa));
		Assert.assertTrue(laGarzaSosa.alianza.estaEnAlianza(gordoValor));
		
		gordoValor.desaliar(gordoValor);
		Assert.assertTrue(vitete.alianza.estaEnAlianza(laGarzaSosa));
	}
	
	@Test
	public void eliminarDeAlianzaTres() {
		Personaje gordoValor = new Humano(new Ladron(), "Gordo Valor");
		Personaje vitete = new Humano(new Ladron(), "Vitete");
		Personaje laGarzaSosa = new Humano(new Ladron(), "Garza Sosa");
		Personaje aliBaba = new Humano(new Ladron(), "Alí Babá");
		
		gordoValor.crearAlianza("Los Piratas");
		
		gordoValor.aliar(vitete);
		vitete.aliar(laGarzaSosa);
		
		Assert.assertTrue(gordoValor.alianza.estaEnAlianza(vitete));
		Assert.assertTrue(vitete.alianza.estaEnAlianza(laGarzaSosa));
		Assert.assertTrue(laGarzaSosa.alianza.estaEnAlianza(gordoValor));
		Assert.assertTrue(vitete.alianza.estaEnAlianza(gordoValor));
		
		gordoValor.desaliar(gordoValor);
		Assert.assertTrue(vitete.alianza.estaEnAlianza(laGarzaSosa));
		
		laGarzaSosa.desaliar(laGarzaSosa);
		vitete.aliar(aliBaba);
		
		aliBaba.alianza.mostrarAlianza();
	}
	
	@Test
	public void pruebaSumaAlianza() {
		Personaje gordoValor9 = new Humano(new Ladron(), "Gordo Valor");
		Personaje vitete9 = new Humano(new Ladron(), "Vitete");
		Personaje willy9 = new Humano(new Luchador(), "willy");
		Personaje laGarzaSosa9 = new Humano(new Ladron(), "Garza Sosa");
		Personaje aliBaba9 = new Humano(new Ladron(), "Alí Babá");
		
		gordoValor9.crearAlianza("Los Piratas");
		
		gordoValor9.aliar(vitete9);
		vitete9.aliar(laGarzaSosa9);
		
		willy9.crearAlianza("Los Indestructibles");
		willy9.aliar(aliBaba9);
		
		Assert.assertTrue(gordoValor9.alianza.estaEnAlianza(vitete9));
		Assert.assertTrue(vitete9.alianza.estaEnAlianza(laGarzaSosa9));
		Assert.assertTrue(laGarzaSosa9.alianza.estaEnAlianza(gordoValor9));
		
		Assert.assertTrue(aliBaba9.alianza.estaEnAlianza(willy9));
		Assert.assertTrue(willy9.alianza.estaEnAlianza(aliBaba9));
		gordoValor9.aliar(aliBaba9);
		willy9.alianza.mostrarAlianza();
		System.out.println("_______");
		gordoValor9.alianza.mostrarAlianza();
		Assert.assertTrue(gordoValor9.alianza.estaEnAlianza(willy9));
		Assert.assertTrue(vitete9.alianza.estaEnAlianza(aliBaba9));
		
		Assert.assertTrue(aliBaba9.alianza.estaEnAlianza(gordoValor9));
		Assert.assertTrue(willy9.alianza.estaEnAlianza(laGarzaSosa9));
		
		Assert.assertEquals(5, laGarzaSosa9.alianza.cantidadDeAliados());
		Assert.assertEquals(5, willy9.alianza.cantidadDeAliados());
		Assert.assertEquals(5, gordoValor9.alianza.cantidadDeAliados());
	}
}
