package cliente;

import java.io.IOException;
import ventana.VentanaPrincipal;

public class MainCliente {

    public static void main(String args[]) throws IOException {
        Cliente cliente;
        String usuario;
        String contraseña;
        
        cliente = new Cliente("nombre");
        
       HiloCliente hiloCliente = new HiloCliente(cliente,cliente.getSocket());
       hiloCliente.start();
        VentanaPrincipal principal = new VentanaPrincipal(cliente);
        principal.setVisible(true);

    }
}
