package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import servidor.Servidor;

public class ServidorTest {
	
	@SuppressWarnings("unused")
	@Test
	public void nuevoServidor() {
		Servidor servidor = null;
		
		try {
			servidor = new Servidor(1);
		} catch (IOException e) {
			fail();
		}
	}
	
	@Test
	public void puertoServidor() {
		try {
			Servidor servidor = new Servidor(1);
			
			Assert.assertEquals(3000, servidor.getPuerto());
		} catch (IOException e) {
			fail();
		}
	}
	
	@Test
	public void pararServidor() {
		Servidor servidor;
		try {
			servidor = new Servidor(2);
			
			servidor.pararServidor();
		} catch (IOException e) {
			fail();
		}
	}
	
}
