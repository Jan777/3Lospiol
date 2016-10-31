package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Servidor {

	private ServerSocket serverSocket;
	private Socket cliente;
	private int cantidadDeClientes;
	private LinkedList<Socket> listaSockets;
	private static final int CANTIDAD_MAXIMA_DE_CLIENTES = 20;
	private static final int PUERTO = 4059;

	public Servidor() {
		this.cantidadDeClientes = 0;
		this.listaSockets = new LinkedList<>();

		try {
			this.serverSocket = new ServerSocket(Servidor.PUERTO);
		} catch (IOException e) {

		}
	}

	public Socket aceptarConexiones() {
		this.cantidadDeClientes++;

		try {
			this.cliente = this.serverSocket.accept();
			if (this.cantidadDeClientes > Servidor.CANTIDAD_MAXIMA_DE_CLIENTES) { // Si supera la cantida de clientes...
				cliente.close();
				return null;
			}
		}

		catch (Exception e) {
			// TODO: handle exception
		}
		this.listaSockets.add(this.cliente);
		return this.cliente;
	}

	public void pararServidor() {
		try {
			this.serverSocket.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	

}
