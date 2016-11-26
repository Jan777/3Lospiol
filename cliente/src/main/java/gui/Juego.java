package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import castas.Paladin;
import personaje.Personaje;
import personaje.PersonajeDibujable;
import razas.Elfo;

public class Juego extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Juego(String nombre) throws UnknownHostException, IOException {

		setTitle("Juego 2D con Java ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);

		add(new Jugador(nombre));

		setVisible(true);
	}

	public Juego(Socket sock, String id, PersonajeDibujable p, Personaje pb) throws UnknownHostException, IOException {

		setTitle("Juego 2D con Java ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		setResizable(false);
		JPanel pane = new JPanel();
		pane.setBounds(0, 600, 800, 100);
		pane.setBackground(Color.GREEN);
		pane.setVisible(true);
		pane.setFocusable(true);
		pane.setSize(800, 200);
		pane.setMinimumSize(new Dimension(800, 200));

		JButton boton = new JButton("boton");
		boton.setBackground(Color.BLUE);
		boton.setBounds(0, 0, 100, 100);
		boton.setVisible(true);
		pane.add(boton);

		Jugador ju = new Jugador(sock, id, p, pb);
		// ju.setSize(800, 600);
		add(ju);
		// this.add(pane, BorderLayout.SOUTH);
		// add(boton);

		setVisible(true);
	}

	public static void main(String args[]) throws UnknownHostException, IOException {
		// new Juego(2);
		// new Juego(3);

		Socket cliente = new Socket("localhost", 2028);
		String id = "jugador1";
		String id2 = "jugador2";
		
		PersonajeDibujable personajeDibujable1 = new PersonajeDibujable(id, "elfoP");
		Personaje personaje1 = new Elfo(new Paladin(), id, "elfoP");
		new Juego(cliente, id, personajeDibujable1, personaje1);
		
		//PersonajeDibujable personajeDibujable2 = new PersonajeDibujable(id2, "elfoP");
		//Personaje personaje2 = new Elfo(new Paladin(), id2, "elfoP");
		//new Juego(cliente, id2, personajeDibujable2, personaje2);
	}
}
