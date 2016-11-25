package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.google.gson.Gson;

import batalla.Batalla;
import batalla.BatallaDibujable;
import cliente.Mensaje;
import mapa.Mapa;
import personaje.Personaje;
import personaje.PersonajeDibujable;

public class Jugador extends JPanel implements Runnable {
	/**
	* 
	*/
	private JPanel pane = new JPanel();
	private static final long serialVersionUID = 1L;
	private String ID;
	private PersonajeDibujable pers;
	private Personaje persBatalla;
	private boolean hayBatalla = false;
	private BatallaDibujable batalla;
	private Mapa map;
	private Thread hilo;
	private final int DELAY = 10;
	///
	private Socket cliente;
	private DataOutputStream out;
	private DataInputStream in;
	private int puerto = 2028;
	private String host = "localhost";
	public int xMenu = 0, yMenu = 0;

	private Mensaje mensaje = new Mensaje("", "");
	private Gson gson = new Gson();
	private String entrada;
	private String salida;
	private String enemigo = "ene";
	private String bando;

	public Jugador(String nombreJugador) throws UnknownHostException, IOException {

		cliente = new Socket(host, puerto);
		in = new DataInputStream(cliente.getInputStream());
		out = new DataOutputStream(cliente.getOutputStream());

		setBackground(Color.WHITE);
		setDoubleBuffered(true);
		pers = new PersonajeDibujable(nombreJugador, "elfoP");
		map = new Mapa();
		enviarMensaje("Cargar");
		leerRespuesta();

		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (arg0.getButton() == MouseEvent.BUTTON3) {
					// System.out.println("Detected Mouse Right Click! " +
					// pers.getPosicionYMouse());
				}
				pers.setXY(arg0.getX() + map.xRespectoPersonajeMapa(pers),
						arg0.getY() + map.yRespectoPersonajeMapa(pers));
			}
		});

	}

	public Jugador(Socket sock, String id, PersonajeDibujable p, Personaje pb)
			throws UnknownHostException, IOException {

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
		
		pane.setBounds(400, 400, 200, 200);
		pane.setBackground(Color.GREEN);
		pane.setVisible(true);
		pane.setFocusable(true);
		this.add(pane);

		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (arg0.getButton() == MouseEvent.BUTTON3) {
					System.out.println("Detected Mouse Right Click! " + pers.getPosicionYMouse());
				}
				if(!hayBatalla)
				{
				pers.setXY(arg0.getX() + map.xRespectoPersonajeMapa(pers),
						arg0.getY() + map.yRespectoPersonajeMapa(pers));
				}else{
					xMenu = arg0.getX();
					yMenu = arg0.getY();
					System.out.println(xMenu);
				}
			}
		});

	}

	public void setHayBatalla(boolean bool) {
		this.hayBatalla = bool;
	}

	public synchronized void leerRespuesta() throws IOException {
		try {
			entrada = in.readUTF();
			
			System.out.println(entrada);
			mensaje = gson.fromJson(entrada, Mensaje.class);

			if (mensaje.getNombreMensaje().equals("Cargado")) {
				map = gson.fromJson(mensaje.getJson(), Mapa.class);

			}

			if (mensaje.getNombreMensaje().equals("MapaActualizado")) {
				map = gson.fromJson(mensaje.getJson(), Mapa.class);
			}
			
			if (mensaje.getNombreMensaje().equals("pasameIDEnemigo")) {
				enviarMensaje("IDEnemigo");
			}
			
			if (mensaje.getNombreMensaje().equals("BatallaActualizada")) {
				batalla = gson.fromJson(mensaje.getJson(), BatallaDibujable.class);
				System.out.println(mensaje.getJson());
			}
			
			if (mensaje.getNombreMensaje().equals("teAtacan")) {
				batalla = gson.fromJson(mensaje.getJson(), BatallaDibujable.class);
				System.out.println(mensaje.getJson());
				this.setHayBatalla(true);
				batalla.agregarContrincante(this.persBatalla, bando);
				this.leerRespuesta();
				DataOutputStream cliOut = gson.fromJson(mensaje.getJson(), DataOutputStream.class);
				salida = gson.toJson(batalla);
				System.out.println("Saida "+ salida);
				mensaje.cambiarMensaje("BatallaActualizada", salida);
				String msg = gson.toJson(mensaje);
				cliOut.writeUTF(msg);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void enviar(Mensaje mensj) throws IOException {
		String msg = gson.toJson(mensj);
		System.out.println(msg);
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
		if (nombreMensaje.equals("batallaNueva")) {
			String json = gson.toJson(batalla);
			mensaje.cambiarMensaje(nombreMensaje, json);
			enviar(mensaje);
		}
		if (nombreMensaje.equals("IDEnemigo")) {
			String json = gson.toJson(enemigo);
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
		if(!hayBatalla){
			
			map.pintarMapa(g, pers, this);

		}else{
			//map.pintarMapa(g, pers, this);
			batalla.pintarBatalla(g, this);
			//System.out.println(batalla);
		}
		
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	public void ciclo() throws IOException {
		if (!hayBatalla) {
			this.pers.caminar();
			enemigo = map.hayBatalla(pers);
			enviarMensaje("ActualizarMapa");
			leerRespuesta();
			if(enemigo != null)
			{
				hayBatalla = true;
				batalla = new BatallaDibujable(this.persBatalla,bando);
				enviarMensaje("batallaNueva");
				
				leerRespuesta();
				leerRespuesta();
				
			}
			
		}else{
			
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
			//if(!hayBatalla)
			  repaint();
			try {
				Thread.sleep(DELAY);
			} catch (InterruptedException err) {
				System.out.println(err);
			}
		}
	}
	
	public void elegirOpcionBatalla()
	{
		
		this.persBatalla.atacar(this.persBatalla);
		this.persBatalla.aplicarHechizo(this.persBatalla.casta.habilidadCasta, this.persBatalla);
	}
	public void elegirAtacado()
	{
		
	}
	
	public void dibujarMenu(boolean op)
	{
		
		Graphics2D g2 = (Graphics2D)this.getGraphics(); 
		Image menu = new ImageIcon("src/main/java/img/menu.png").getImage();
		Image boton = new ImageIcon("src/main/java/img/botonMenu.png").getImage();
		g2.drawImage(menu, 10, 500, this);
		String opcion = null;
		g2.drawImage(boton, 15, 490, this);
		g2.drawString("atacar", 18, 495);
		g2.drawImage(boton, 15, 460, this);
		g2.drawString("defender", 18, 465);
		
		System.out.println("menu");
		while(opcion == null)
		{
			if(this.xMenu > 15 && this.xMenu < 200 && this.yMenu > 490 && this.yMenu < 520)
			{
				opcion = "atacar";
				System.out.println("atacar");
			}
			if(this.xMenu > 15 && this.xMenu < 200 && this.yMenu > 460 && this.yMenu < 480)
			{
				opcion = "defender";
				System.out.println("defender");
			}
		}
		
		batalla.pintarBatalla(g2, this);
		g2.drawImage(boton, 15, 490, this);
		g2.drawString("enemigo1", 18, 495);
		g2.drawImage(boton, 15, 460, this);
		g2.drawString("enemigo2", 18, 465);
		while(opcion == "atacar")
		{
			
			System.out.println(xMenu);
			if(this.xMenu > 15 && this.xMenu < 200 && this.yMenu > 490 && this.yMenu < 520)
			{
				opcion = "atacar";
				System.out.println("atacar");
			}
			if(this.xMenu > 15 && this.xMenu < 200 && this.yMenu > 460 && this.yMenu < 480)
			{
				opcion = "defender";
				System.out.println("defender");
			}
		}
		
		g2.dispose();
	
		
	
	}

}
