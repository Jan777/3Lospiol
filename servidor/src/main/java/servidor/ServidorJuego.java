package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import mapa.Mapa;

public class ServidorJuego {

	private final int puerto = 2028;
	private final int noConexiones = 20;
	private ServerSocket servidor;
	private Mapa map;
	private LinkedList<Socket> usuarios = new LinkedList<Socket>();
	private HashMap<String, Socket> mapId = new HashMap<String, Socket>();

	public void escuchar() throws IOException {
		try {

			servidor = new ServerSocket(puerto, noConexiones);
			map = new Mapa();

			while (true) {
				Socket cliente = servidor.accept();
				usuarios.add(cliente);

				Runnable run = new HiloServidor(cliente, usuarios, map, mapId);
				Thread hilo = new Thread(run);
				hilo.start();

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al conectar el cliente", "Error",
					JOptionPane.ERROR_MESSAGE);

		} finally {
			servidor.close();
		}
	}

	public static void main(String[] args) {
		ServidorJuego servidor = new ServidorJuego();
		try {
			servidor.escuchar();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error al levantar el servidor", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}