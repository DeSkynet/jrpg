package cliente;

import java.io.IOException;
import ventana.VentanaPrincipal;

public class MainCliente {

    public static void main(String args[]) throws IOException {
        Cliente cliente;
        String usuario;
        String contraseña;
        
        cliente = new Cliente("nombre");
        VentanaPrincipal principal = new VentanaPrincipal(cliente);
        principal.setVisible(true);

    }
}
