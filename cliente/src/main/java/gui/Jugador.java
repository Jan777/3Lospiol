package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JPanel;

import com.google.gson.Gson;

import cliente.Mensaje;
import mapa.Mapa;
import personaje.Personaje;
import personaje.PersonajeDibujable;

public class Jugador extends JPanel implements Runnable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private String ID;
	private PersonajeDibujable pers;
	private Personaje persBatalla;
	private boolean hayBatalla = false;
	private Mapa map;
	private Thread hilo;
	private final int DELAY = 10;
	///
	private Socket cliente;
	private DataOutputStream out;
	private DataInputStream in;
	private int puerto = 2028;
	private String host = "localhost";

	private Mensaje mensaje = new Mensaje("", "");
	private Gson gson = new Gson();
	private String entrada;

	public Jugador(int num) throws UnknownHostException, IOException {

		cliente = new Socket(host, puerto);
		in = new DataInputStream(cliente.getInputStream());
		out = new DataOutputStream(cliente.getOutputStream());

		setBackground(Color.WHITE);
		setDoubleBuffered(true);
		pers = new PersonajeDibujable("Jugador"+num,"elfoP"); 
		map = new Mapa();
		enviarMensaje("Cargar");
		leerRespuesta();

		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (arg0.getButton() == MouseEvent.BUTTON3) {
					System.out.println("Detected Mouse Right Click! " + pers.getPosicionYMouse());
				}
				pers.setXY(arg0.getX() + map.xRespectoPersonajeMapa(pers),
						arg0.getY() + map.yRespectoPersonajeMapa(pers));
			}
		});

	}
	
	public Jugador(Socket sock, String id, PersonajeDibujable p, Personaje pb) throws UnknownHostException, IOException {

		cliente = sock;
		ID = id;
		in = new DataInputStream(cliente.getInputStream());
		out = new DataOutputStream(cliente.getOutputStream());

		setBackground(Color.WHITE);
		setDoubleBuffered(true);
		pers = p;
		persBatalla = pb;
		map = new Mapa();
		enviarMensaje("Cargar");
		leerRespuesta();

		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (arg0.getButton() == MouseEvent.BUTTON3) {
					System.out.println("Detected Mouse Right Click! " + pers.getPosicionYMouse());
				}
				pers.setXY(arg0.getX() + map.xRespectoPersonajeMapa(pers),
						arg0.getY() + map.yRespectoPersonajeMapa(pers));
			}
		});

	}
	

	public void setHayBatalla(boolean bool)
	{
		this.hayBatalla = bool;
	}
	
	public void leerRespuesta() throws IOException {
		entrada = in.readUTF();
		mensaje = gson.fromJson(entrada, Mensaje.class);

		if (mensaje.getNombreMensaje().equals("Cargado")) {
			map = gson.fromJson(mensaje.getJson(), Mapa.class);

		}

		if (mensaje.getNombreMensaje().equals("MapaActualizado")) {
			map = gson.fromJson(mensaje.getJson(), Mapa.class);
		}
	}

	public void enviar(Mensaje mensj) throws IOException {
		String msg = gson.toJson(mensj);
		out.writeUTF(msg);
		out.flush();
	}

	public void enviarMensaje(String nombreMensaje) throws IOException {
		if (nombreMensaje.equals("Cargar")) {
			String json = gson.toJson(pers);
			mensaje.cambiarMensaje(nombreMensaje, json);
			enviar(mensaje);
		}

		if (nombreMensaje.equals("ActualizarMapa")) {
			String json = gson.toJson(pers);
			mensaje.cambiarMensaje(nombreMensaje, json);
			enviar(mensaje);
		}
	}

	@Override
	public void addNotify() {
		super.addNotify();
		hilo = new Thread(this);
		hilo.start();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		map.pintarMapa(g, pers, this);
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	public void ciclo() throws IOException {
		if(!hayBatalla)
		{
			this.pers.caminar();
			hayBatalla = map.hayBatalla(pers);
			enviarMensaje("ActualizarMapa");
			leerRespuesta();
		}else{
			System.out.println("batalla");
		}
		

		// map.actualizarMapa(pers);
	}

	@Override
	public void run() {
		while (true) {
			try {
				ciclo();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			repaint();
			try {
				Thread.sleep(DELAY);
			} catch (InterruptedException err) {
				System.out.println(err);
			}
		}
	}

}
