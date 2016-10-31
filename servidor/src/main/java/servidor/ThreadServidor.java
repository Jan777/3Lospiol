package servidor;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedList;

public class ThreadServidor extends Thread {

	private Socket socket;
	private LinkedList<Socket> listaSockets;

	public ThreadServidor(Socket socket, LinkedList<Socket> listaSocket) {
		this.socket = socket;
		this.listaSockets = listaSocket;
	}

	public void run() {

		Iterator<Socket> iterator;
		try {
			iterator = this.listaSockets.iterator();
			String mensaje = ""; // Obtener el mensaje del cliente
			//Utilizar mensaje...
			while (iterator.hasNext()) {
				Socket cliente = iterator.next(); // Obtengo el cliente de la
													// lista de clientes
				if (cliente.equals(this.socket)) { // Si es el mismo cliente...
					PrintStream ps = new PrintStream(socket.getOutputStream());
					ps.println(mensaje);// Envia el mensaje al correspondiente
										// socket.
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {

		}

	}

}
