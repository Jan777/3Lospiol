package cliente;

import java.io.IOException;

public class MainCliente {

	public static void main(String args[]) throws IOException {

		Cliente cliente = new Cliente("Cliente");

		new ThreadCliente(cliente.getSocket()).start();
		String mensaje = "Hola";
		cliente.enviarMensaje(mensaje);
	//	cliente.cerrarCliente();
	}
}
