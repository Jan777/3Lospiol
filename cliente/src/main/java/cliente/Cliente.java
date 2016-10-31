package cliente;

import java.io.PrintStream;
import java.net.Socket;

public class Cliente {

	private Socket cliente;
	private static final int PUERTO = 4059;

	public Cliente(String direccion) {
		try {
			this.cliente = new Socket(direccion, Cliente.PUERTO);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void enviarMensajeAlServidor() {

		try {
			PrintStream ps = new PrintStream(cliente.getOutputStream());
			String mensaje = ""; // Obtengo el mensaje del cliente
			if (mensaje != "exit") {
				ps.println(mensaje);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
