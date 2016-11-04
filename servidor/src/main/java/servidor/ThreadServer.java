package servidor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;

import com.google.gson.Gson;

public class ThreadServer extends Thread {

	private Socket socket;
	private Object mensaje;

	private LinkedList<Socket> listaDeClientes;

	public ThreadServer(Socket socket, LinkedList<Socket> listaDeClientes, String mensaje) {
		this.socket = socket;
		this.listaDeClientes = listaDeClientes;
		this.mensaje = mensaje;
	}

	public Socket getSocket() {
		return this.socket;
	}

	@Override
	public void run() {
		for (Socket cliente : this.listaDeClientes) {
			try {
				DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());

				final Gson gson = new Gson();
				salida.writeUTF(gson.toJson(this.mensaje));
			} catch (Exception e) {
				e.printStackTrace();
				try {
					cliente.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
}
