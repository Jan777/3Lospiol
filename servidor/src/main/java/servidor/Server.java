package servidor;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;

public class Server {

	private ServerSocket servidor;
	private Socket cliente;
	public static int cantActualClientes;
	private LinkedList<Socket> coleccion;
	private int cantidadDeConexionesMaximas;
	private int puerto;
	private String IPHost;

	public String getIPHost() {
		return IPHost;
	}

	public int getCantidadDeConexionesMaximas() {
		return cantidadDeConexionesMaximas;
	}

	public int getPuerto() {
		return puerto;
	}

	public Server(int puerto, int maximasConexiones) {

		// Nombre e IP del Servidor
		try {
			this.IPHost = InetAddress.getLocalHost().getHostAddress().toString();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}

		this.puerto = puerto;
		this.cantidadDeConexionesMaximas = maximasConexiones;

		cantActualClientes = 0;
		coleccion = new LinkedList<Socket>();

		try {
			servidor = new ServerSocket(puerto);

		} catch (IOException e) {
		//	System.out.println("No se puede escuchar desde el puerto elegido, cerrando Servidor...");
			System.exit(1);
		}
	}

	public LinkedList<Socket> getLista() {
		return coleccion;
	}

	public Socket aceptarConexion() {

		cantActualClientes++;

		try {
			cliente = servidor.accept();
			if (cantActualClientes > this.cantidadDeConexionesMaximas) {
				PrintStream ps = new PrintStream(cliente.getOutputStream());
				ps.println("Servidor Lleno");
				cliente.close();
				return null;
			}
		} catch (Exception e) {
			// System.out.println("Error al aceptar conexiones, Cerrando el
			// Servidor...");
			System.exit(1);
		}
		// System.out.println("La Conexion NRO " + cantActualClientes + " fue
		// aceptada correctamente.");
		this.coleccion.add(cliente);
		return cliente;
	}

	public void pararServidor() {
		try {
			servidor.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
