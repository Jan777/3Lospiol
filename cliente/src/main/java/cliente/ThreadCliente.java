package cliente;

import java.io.DataInputStream;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.Socket;

public class ThreadCliente extends Thread {
	private Socket socket;
	private Gson gson;
	private DataInputStream dataInputStream;

	public ThreadCliente(Socket socket) throws IOException {

		this.socket = socket;
		this.dataInputStream = new DataInputStream(socket.getInputStream());
		gson = new Gson();
	}

	@Override
	public void run() {
		// DataInputStream datos;
		// String temp = null;
		boolean conectado = true;
		while (conectado) {
			try {
				// do {
				// if (temp != null)
				// System.out.println(temp);
				// datos = new DataInputStream(socket.getInputStream());
				// } while ((temp = datos.readLine()) != null);
				// String mensaje = "Hola";
				/*String mensajeJSON =*/ gson.fromJson(dataInputStream.readUTF(), String.class);
				// String mensajeJSON = gson.fromJson(mensaje, String.class);
			//	System.out.println(mensajeJSON);
			} catch (Exception e) {
				try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
					conectado = false;
				}
			}
		}
	}
}
