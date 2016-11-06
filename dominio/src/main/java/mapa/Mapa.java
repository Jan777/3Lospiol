package mapa;

import java.util.ArrayList;

import personaje.Jugador;

public class Mapa {

	private static final int LARGO = 1000;
	private static final int ALTO = 1000;
	private ArrayList<Jugador> jugadores;
	private ArrayList<Obstaculo> obstaculos;

	// Constructores
	public Mapa() {
		jugadores = new ArrayList<Jugador>();
		obstaculos = new ArrayList<Obstaculo>();
	}

	// Getters and Setters
	public static int getLargo() {
		return LARGO;
	}

	public static int getAlto() {
		return ALTO;
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

	// Metodos adicionales
	public boolean dentroDelMapa(Punto punto) {
		return punto.getX() >= 0 && punto.getY() >= 0 && punto.getX() < LARGO && punto.getY() < ALTO;
	}

	public void agregarObstaculo(Obstaculo obstaculo) {
		obstaculos.add(obstaculo);
	}

	public void agregarJugador(Jugador jugador) {
		jugadores.add(jugador);
	}
}
