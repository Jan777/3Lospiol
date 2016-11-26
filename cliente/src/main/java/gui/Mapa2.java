package gui;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import jugador.Jugador;
import mapa.Obstaculo;
import mapa.Punto;

public class Mapa2 extends JFrame implements ActionListener {

	private static final int ANCHO = 1000;
	private static final int ALTO = 1000;
	private static final int LARGO = 1000;
	private static final String NOMBRE = "Mundo";
	private Image fondo;
	private ArrayList<Obstaculo> obstaculos;
	private ArrayList<Jugador> jugadores;
	private ArrayList<Timer> timer;

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Mapa2() {

		obstaculos = new ArrayList<Obstaculo>();
		jugadores = new ArrayList<Jugador>();
		timer = new ArrayList<Timer>();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(ANCHO, ALTO);
		setTitle(NOMBRE);
		setLocationRelativeTo(null);
		setResizable(false);// user no pueda cambiar el tam ventana

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblFondo = new JLabel("");
		lblFondo.setSize(ANCHO, ALTO);
		contentPane.add(lblFondo);

//		ImageIcon img = new ImageIcon(this.getClass().getResource("/Imagenes/fondoInicio.jpg"));
//		//Cambiar fondo del mapa
//		
//		fondo = img.getImage();
//		ImageIcon iconoEscalonado = new ImageIcon(
//				fondo.getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), Image.SCALE_DEFAULT));
//
//		lblFondo.setIcon(iconoEscalonado);
	}

	// Getters and Setters
	public static int getAncho() {
		return ANCHO;
	}

	public static int getAlto() {
		return ALTO;
	}
	
	public static int getLargo(){
		return LARGO;
	}

	public ArrayList<Obstaculo> getObstaculos() {
		return obstaculos;
	}

	public void setObstaculos(ArrayList<Obstaculo> obstaculos) {
		this.obstaculos = obstaculos;
	}

	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	// Metodos adicionales
	public boolean dentroDelMapa(Punto punto) {
		return punto.getX() >= 0 && punto.getY() >= 0 && punto.getX() < ANCHO && punto.getY() < ALTO;
	}

	public void agregarObstaculo(Obstaculo obstaculo) {
		obstaculos.add(obstaculo);
	}

	public void agregarJugador(Jugador jugador) {

		jugadores.add(jugador);
		timer.add(new Timer(15, this)); // velocidad del jugador
		timer.get(timer.size() - 1).start();
		setFocusable(true);// teclado detecta color
		addKeyListener(new teclado());
	}

	public void paint(Graphics grafica) {
		super.paint(grafica);
		Graphics2D g2 = (Graphics2D) grafica;

		int x = jugadores.get(jugadores.size() - 1).getPosicion().getX();
		int y = jugadores.get(jugadores.size() - 1).getPosicion().getY();

		g2.drawImage(jugadores.get(jugadores.size() - 1).getImagen(), x, y, null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		jugadores.get(jugadores.size() - 1).mover();
		repaint();// para q se vuelva a dibujar
	}

	private class teclado extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			jugadores.get(jugadores.size() - 1).keyPressed(e);
		}

		public void keyReleased(KeyEvent e) {
			jugadores.get(jugadores.size() - 1).keyReleased(e);
		}
	}
}