package servidor;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import com.google.gson.Gson;

public class MainServer {
	public static void main(String[] args) throws IOException {
		Socket socket = null;
		boolean bandera = true;

		Server servidor = null;
		int puerto = 4509;
		int maximoConexiones = 15;

		maximoConexiones = 4;
		servidor = new Server(puerto, maximoConexiones);

//		System.out.println("\nServidor en escucha...");

		while (bandera) {
			socket = servidor.aceptarConexion();
			if (socket != null) {
				DataInputStream mensaje = new DataInputStream(socket.getInputStream());
				Gson gson = new Gson();
				String leido = mensaje.readUTF();

				new ThreadServer(socket, servidor.getLista(), gson.toJson(leido)).start();
			}
		}
		servidor.pararServidor();
	}
}
