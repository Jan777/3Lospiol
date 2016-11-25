package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;

import com.google.gson.Gson;

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
	private OperacionesBD operaciones = new OperacionesBD();

	private LinkedList<Socket> usuarios = new LinkedList<Socket>();

	public HiloServidor(Socket soc, LinkedList<Socket> users, Mapa mapa) throws IOException {
		socket = soc;
		usuarios = users;
		map = mapa;
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
	}

	public void interpretarMensaje() throws IOException {
		operaciones.conectar();
		if (mensaje.getNombreMensaje().equals("Cargar")) {
			PersonajeDibujable pers = gson.fromJson(mensaje.getJson(), PersonajeDibujable.class);
			idCliente = pers.getID();
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

		if (mensaje.getNombreMensaje().equals("GuardarPersonajeDibujable")) {
			PersonajeDibujable pers = gson.fromJson(mensaje.getJson(), PersonajeDibujable.class);

			// REGISTRAR PERSONAJEDIBUJABLE EN BD
			String respuesta = "true";
			mensaje = new Mensaje("PersonajeDibujableGuardado", respuesta);
			responder();
		}
		
		if (mensaje.getNombreMensaje().equals("GuardarPersonaje")) {
			// ACA ME DA UN ERROR. PARA MI ES POR LAS REFERENCIAS DE PERSONAJE, LA CASTA Y LA RAZA.
			
			Atacable personaje = gson.fromJson(mensaje.getJson(), Atacable.class);
			
			// REGISTRAR PERSONAJE EN BD
			boolean respuestaGuardar = operaciones.insertarPersonaje((Personaje)personaje, null);
			String respuesta = gson.toJson(respuestaGuardar);
			mensaje = new Mensaje("GuardarPersonaje", respuesta);
			responder();
		}
		if(mensaje.getNombreMensaje().equals("consultarPersonaje")){
			String usuario = gson.fromJson(mensaje.getJson(), String.class);
			boolean respuestaConsulta = operaciones.consultarPersonaje(usuario);
			mensaje = new Mensaje("consultarPersonaje",gson.toJson(respuestaConsulta));
			responder();
		}
		if(mensaje.getNombreMensaje().equals("obtenerPersonaje")){
			String usuario = gson.fromJson(mensaje.getJson(), String.class);
			Personaje personaje = operaciones.obtenerPesonaje((usuario));
			mensaje = new Mensaje("consultarPersonaje",gson.toJson(personaje));
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
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
