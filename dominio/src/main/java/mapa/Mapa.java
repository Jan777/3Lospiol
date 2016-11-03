package mapa;

import java.util.ArrayList;

import javax.swing.JFrame;

import personaje.Jugador;

public class Mapa extends JFrame {

	private static final int LARGO = 1000;
	private static final int ALTO = 1000;
	private ArrayList<Jugador> jugadores;
	private ArrayList<Obstaculo> obstaculos;

	public Mapa() {
		jugadores = new ArrayList<Jugador>();
		obstaculos = new ArrayList<Obstaculo>();
	}

	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public ArrayList<Obstaculo> getObstaculos() {
		return obstaculos;
	}

	public void setObstaculos(ArrayList<Obstaculo> obstaculos) {
		this.obstaculos = obstaculos;
	}

	public boolean dentroDelMapa(Punto punto) {
		return punto.getX() >= 0 && punto.getY() >= 0 && punto.getX() < LARGO && punto.getY() < ALTO;
	}

	public void agregarObstaculo(Obstaculo obstaculo) {
		obstaculos.add(obstaculo);
	}
}
