package test;

import java.io.IOException;
import org.junit.Test;
import cliente.Cliente;


public class ClienteTest {
	
	@Test (expected = IOException.class)
	public void nuevoCliente() {
		Cliente cliente = new Cliente("Hola");
	}
}
