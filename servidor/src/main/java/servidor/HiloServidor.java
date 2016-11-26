package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import batalla.BatallaDibujable;
import conexionSQL.OperacionesBD;
import interfaces.Atacable;
import mapa.Mapa;
import mensaje.Mensaje;
import personaje.Personaje;
import personaje.PersonajeDibujable;

public class HiloServidor implements Runnable {

	private Socket socket;
	private String idCliente;
	private DataOutputStream out;
	private DataInputStream in;
	private Mapa map;
	private Mensaje mensaje;
	private Gson gson = new Gson();
	private HashMap<String, Socket> mapId;
	private BatallaDibujable batalla;
	private String entrada;
	private String enemigo;

	private OperacionesBD operaciones = new OperacionesBD();

	private LinkedList<Socket> usuarios = new LinkedList<Socket>();

	public HiloServidor(Socket soc, LinkedList<Socket> users, Mapa mapa, HashMap<String, Socket> hm)
			throws IOException {
		socket = soc;
		usuarios = users;
		map = mapa;
		mapId = hm;
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
	}

	public void leerRespuesta() throws IOException {
		entrada = in.readUTF();
		mensaje = gson.fromJson(entrada, Mensaje.class);

		if (mensaje.getNombreMensaje().equals("IDEnemigo")) {
			enemigo = gson.fromJson(mensaje.getJson(), String.class);

		}
	}

	public void interpretarMensaje() throws IOException {
		operaciones.conectar();
		if (mensaje.getNombreMensaje().equals("Cargar")) {
			PersonajeDibujable pers = gson.fromJson(mensaje.getJson(), PersonajeDibujable.class);
			idCliente = pers.getID();
			mapId.put(idCliente, socket);
			map.agregarDibujable(pers);
			String respuesta = gson.toJson(map);
			mensaje = new Mensaje("Cargado", respuesta);
			responder();
		}

		if (mensaje.getNombreMensaje().equals("ActualizarMapa")) {
			PersonajeDibujable pers = gson.fromJson(mensaje.getJson(), PersonajeDibujable.class);
			map.actualizarMapa(pers);
			String respuesta = gson.toJson(map);
			mensaje = new Mensaje("MapaActualizado", respuesta);
			responder();
		}

		if (mensaje.getNombreMensaje().equals("batallaNueva")) {
			String bat = mensaje.getJson();
			mensaje = new Mensaje("pasameIDEnemigo", "nada");
			responder();
			this.leerRespuesta();
			Socket soc = mapId.get(enemigo);
			mensaje = new Mensaje("teAtacan", bat);
			this.mensajeASocket(soc, mensaje);
			entrada = gson.toJson(out);
			mensaje = new Mensaje("outputStream", entrada);

			/*
			 * DataInputStream cliIn = new
			 * DataInputStream(soc.getInputStream()); entrada = cliIn.readUTF();
			 * out.writeUTF(entrada); out.flush();
			 */

		}

		if (mensaje.getNombreMensaje().equals("guardarPersonajeDibujable")) {
			PersonajeDibujable pers = gson.fromJson(mensaje.getJson(), PersonajeDibujable.class);
			boolean respuestaInsertar = operaciones.insertarPersonajeDibujable(pers);
			String respuesta = gson.toJson(respuestaInsertar);
			mensaje = new Mensaje("PersonajeDibujableGuardado", respuesta);
			responder();
		}

		if (mensaje.getNombreMensaje().equals("guardarPersonaje")) {
			ArrayList<String> personaje = gson.fromJson(mensaje.getJson(), ArrayList.class);
			boolean respuestaGuardar = operaciones.insertarPersonaje(personaje);
			String respuesta = gson.toJson(respuestaGuardar);
			mensaje = new Mensaje("GuardarPersonaje", respuesta);
			responder();
		}
		if (mensaje.getNombreMensaje().equals("consultarPersonaje")) {
			String usuario = gson.fromJson(mensaje.getJson(), String.class);
			boolean respuestaConsulta = operaciones.consultarPersonaje(usuario);
			mensaje = new Mensaje("consultarPersonaje", gson.toJson(respuestaConsulta));
			responder();
		}
		if (mensaje.getNombreMensaje().equals("obtenerPersonaje")) {
			String usuario = gson.fromJson(mensaje.getJson(), String.class);
			ArrayList<String> personaje = operaciones.obtenerPersonaje((usuario));
			mensaje = new Mensaje("obtenerPersonaje", gson.toJson(personaje));
			responder();
		}

		if (mensaje.getNombreMensaje().equals("obtenerPersonajeDibujable")) {
			String usuario = gson.fromJson(mensaje.getJson(), String.class);
			String personajeDibujable = operaciones.obtenerPersonajeDibujable((usuario));
			mensaje = new Mensaje("consultarPersonaje", gson.toJson(personajeDibujable));
			responder();
		}
		if (mensaje.getNombreMensaje().equals("validarUsuario")) {
			boolean respuestaValidacion = operaciones.existeUsuario(gson.fromJson(mensaje.getJson(), String.class));
			String respuesta = gson.toJson(respuestaValidacion);
			mensaje = new Mensaje("ValidarUsuario", respuesta);
			responder();
		}
		if (mensaje.getNombreMensaje().equals("registrarUsuario")) {

			String datosUsuario = mensaje.getJson();
			String usuario = datosUsuario.split(":")[0];
			String contraseña = datosUsuario.split(":")[1];
			boolean respuestaValidacion = operaciones.insertarUsuario(usuario, contraseña);
			String respuesta = gson.toJson(respuestaValidacion);
			mensaje = new Mensaje("registrarUsuario", respuesta);
			responder();
		}
		if (mensaje.getNombreMensaje().equals("validarCredenciales")) {

			String datosUsuario = mensaje.getJson();
			String usuario = datosUsuario.split(":")[0];
			String contraseña = datosUsuario.split(":")[1];
			boolean respuestaValidacion = operaciones.validarCredenciales(usuario, contraseña);
			String respuesta = gson.toJson(respuestaValidacion);
			mensaje = new Mensaje("registrarUsuario", respuesta);
			responder();
		}
		operaciones.desconectar();
	}

	public void responder() throws IOException {
		String salida = gson.toJson(mensaje);
		out.writeUTF(salida);
		out.flush();
	}

	public void mensajeASocket(Socket cli, Mensaje mens) throws IOException {

		DataOutputStream cliOut = new DataOutputStream(cli.getOutputStream());

		String salida = gson.toJson(mens);
		cliOut.writeUTF(salida);
		cliOut.flush();
	}

	@Override
	public void run() {
		try {
			String entrada = in.readUTF();
			mensaje = gson.fromJson(entrada, Mensaje.class);
			interpretarMensaje();

			while (true) {
				entrada = in.readUTF();
				mensaje = gson.fromJson(entrada, Mensaje.class);
				interpretarMensaje();

			}
		} catch (Exception e) {
			map.quitar(idCliente);
			usuarios.remove(socket);
		}
		try {
			socket.close();
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Error al eliminar el cliente", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}
}
