package cliente;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ThreadCliente extends Thread {

	private Socket socket;

	public ThreadCliente(Socket socket) {
		this.socket = socket;
	}

	public void run() {

		try {
			DataInputStream mensaje = new DataInputStream(this.socket.getInputStream());
			System.out.println(mensaje);

		} catch (IOException e) {
			try {
				this.socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
