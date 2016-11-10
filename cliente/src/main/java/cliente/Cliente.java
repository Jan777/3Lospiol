package cliente;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.google.gson.Gson;

public class Cliente {

	private Socket cliente;
	private String nombre;
	private final int PUERTO = 4509;
	private String usuario;
	private Gson gson;

	public int getPuerto() {
		return PUERTO;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public Cliente(String usuario) throws UnknownHostException, IOException {
		this.usuario = usuario;
		cliente = new Socket("localhost", PUERTO);
	}

	public Socket getSocket() {
		return cliente;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void enviarMensaje(Object mensaje) throws IOException {
		DataOutputStream dataOutputStream = new DataOutputStream(this.cliente.getOutputStream());
		mensaje = "Hola";
		this.gson = new Gson();
		dataOutputStream.writeUTF(gson.toJson(mensaje));
//		System.out.println(gson.toJson(mensaje));
	}

	public void cerrarCliente() {
		try {
			cliente.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
