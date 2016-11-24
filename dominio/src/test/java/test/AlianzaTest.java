package test;

import org.junit.Assert;

import org.junit.Test;

import personaje.Alien;
import personaje.Humano;
import personaje.Personaje;
import personaje.SuperHeroe;
import personaje.castas.Hechicero;
import personaje.castas.Ladron;
import personaje.castas.Luchador;

import personaje.Batalla;

public class AlianzaTest {
	@Test
	public void agregarAlianza() {
		Personaje p1 = new Humano(new Ladron(), "p1");
		Personaje p2 = new Humano(new Ladron(), "p2");
		
		p1.crearAlianza("Los Indestructibles");
		
		p1.aliar(p2);
		
		Assert.assertEquals(2, p1.getAlianza().cantidadDeAliados());
		Assert.assertEquals(2, p2.getAlianza().cantidadDeAliados());
	}
	
	@Test
	public void eliminarseAUnoMismo() {
		Personaje p1 = new Humano(new Ladron(), "p1");
		Personaje p2 = new Humano(new Ladron(), "p2");
		
		p1.crearAlianza("Los Indestructibles");
		
		p1.aliar(p2);
		p1.desaliar();
		Assert.assertEquals(null, p1.getAlianza());
	}
	
	@Test
	public void sonAliados() {
		Personaje julio = new Humano(new Ladron(), "julio");
		Personaje  lazaro = new Humano(new Ladron(), "lazaro");
		
		julio.crearAlianza("Comodoro");
		
		julio.aliar(lazaro);
		
		Assert.assertTrue(julio.getAlianza().estaEnAlianza(julio));
		Assert.assertTrue(julio.getAlianza().estaEnAlianza(lazaro));
		
		Assert.assertTrue(lazaro.getAlianza().estaEnAlianza(julio));
		Assert.assertTrue(lazaro.getAlianza().estaEnAlianza(lazaro));
	}
	
	@Test
	public void aliadosIndirectos() {
		Personaje gordoValor = new Humano(new Ladron(), "Gordo Valor");
		Personaje vitete = new Humano(new Ladron(), "Vitete");
		Personaje laGarzaSosa = new Humano(new Ladron(), "Garza Sosa");
		
		gordoValor.crearAlianza("Los Piratas");
		
		gordoValor.aliar(vitete);
		vitete.aliar(laGarzaSosa);
		
		Assert.assertTrue(gordoValor.getAlianza().estaEnAlianza(vitete));
		Assert.assertTrue(vitete.getAlianza().estaEnAlianza(laGarzaSosa));
		Assert.assertTrue(laGarzaSosa.getAlianza().estaEnAlianza(gordoValor));
	}
	
	@Test
	public void eliminarDeAlianza() {
		Personaje gordoValor = new Humano(new Ladron(), "Gordo Valor");
		Personaje vitete = new Humano(new Ladron(), "Vitete");
		Personaje laGarzaSosa = new Humano(new Ladron(), "Garza Sosa");
		
		gordoValor.crearAlianza("Los Piratas");
		
		gordoValor.aliar(vitete);
		vitete.aliar(laGarzaSosa);
		
		Assert.assertTrue(gordoValor.getAlianza().estaEnAlianza(vitete));
		Assert.assertTrue(vitete.getAlianza().estaEnAlianza(laGarzaSosa));
		Assert.assertTrue(laGarzaSosa.getAlianza().estaEnAlianza(gordoValor));
		vitete.desaliar();
		Assert.assertFalse(laGarzaSosa.getAlianza().estaEnAlianza(vitete));
		Assert.assertTrue(laGarzaSosa.getAlianza().estaEnAlianza(gordoValor));
		Assert.assertFalse(vitete.getAlianza() != null);
	}
	
	@Test
	public void eliminarDeAlianzaDos() {
		Personaje gordoValor = new Humano(new Ladron(), "Gordo Valor");
		Personaje vitete = new Humano(new Ladron(), "Vitete");
		Personaje laGarzaSosa = new Humano(new Ladron(), "Garza Sosa");
		
		gordoValor.crearAlianza("Los Piratas");
		
		gordoValor.aliar(vitete);
		vitete.aliar(laGarzaSosa);
		
		Assert.assertTrue(gordoValor.getAlianza().estaEnAlianza(vitete));
		Assert.assertTrue(vitete.getAlianza().estaEnAlianza(laGarzaSosa));
		Assert.assertTrue(laGarzaSosa.getAlianza().estaEnAlianza(gordoValor));
		
		gordoValor.desaliar(); // se desalia 
		Assert.assertEquals(null, gordoValor.getAlianza()); 	//me fijo que sea null.
		Assert.assertTrue(vitete.getAlianza().estaEnAlianza(laGarzaSosa));
		Assert.assertFalse(vitete.getAlianza().estaEnAlianza(gordoValor));	//se veridica si
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
		
		Assert.assertTrue(gordoValor.getAlianza().estaEnAlianza(vitete));
		Assert.assertTrue(vitete.getAlianza().estaEnAlianza(laGarzaSosa));
		Assert.assertTrue(laGarzaSosa.getAlianza().estaEnAlianza(gordoValor));
		Assert.assertTrue(vitete.getAlianza().estaEnAlianza(gordoValor));
		
		gordoValor.desaliar();
		Assert.assertTrue(vitete.getAlianza().estaEnAlianza(laGarzaSosa));
		
		laGarzaSosa.desaliar();
		vitete.aliar(aliBaba);
		
//		aliBaba.alianza.mostrarAlianza();
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
		
		Assert.assertTrue(gordoValor9.getAlianza().estaEnAlianza(vitete9));
		Assert.assertTrue(vitete9.getAlianza().estaEnAlianza(laGarzaSosa9));
		Assert.assertTrue(laGarzaSosa9.getAlianza().estaEnAlianza(gordoValor9));
		
		Assert.assertTrue(aliBaba9.getAlianza().estaEnAlianza(willy9));
		Assert.assertTrue(willy9.getAlianza().estaEnAlianza(aliBaba9));
		gordoValor9.aliar(aliBaba9);
//		willy9.alianza.mostrarAlianza();
//		System.out.println("_______");
//		gordoValor9.alianza.mostrarAlianza();
		Assert.assertTrue(gordoValor9.getAlianza().estaEnAlianza(willy9));
		Assert.assertTrue(vitete9.getAlianza().estaEnAlianza(aliBaba9));
		
		Assert.assertTrue(aliBaba9.getAlianza().estaEnAlianza(gordoValor9));
		Assert.assertTrue(willy9.getAlianza().estaEnAlianza(laGarzaSosa9));
		
		Assert.assertEquals(5, laGarzaSosa9.getAlianza().cantidadDeAliados());
		Assert.assertEquals(5, willy9.getAlianza().cantidadDeAliados());
		Assert.assertEquals(5, gordoValor9.getAlianza().cantidadDeAliados());
	}
	
	@Test
	public void pruebaColaBatalla(){
		Personaje gordoValor9 = new SuperHeroe(new Ladron(), "Gordo Valor"); //11 destreza
//		Personaje vitete9 = new Humano(new Hechicero(), "Vitete"); //2/7
		Personaje willy9 = new Humano(new Luchador(), "willy");//5
//		Personaje laGarzaSosa9 = new Humano(new Ladron(), "Garza Sosa");//7
//		Personaje aliBaba9 = new Alien(new Ladron(), "Alí Babá");//10
		
		gordoValor9.crearAlianza("Los Piratas");
		
//		gordoValor9.aliar(vitete9);
//		vitete9.aliar(laGarzaSosa9);
		
		willy9.crearAlianza("Los Indestructibles");
//		willy9.aliar(aliBaba9);
		
		Batalla uno = new Batalla(gordoValor9, willy9);
		uno.batallaAutomatica();
	
	}
}
