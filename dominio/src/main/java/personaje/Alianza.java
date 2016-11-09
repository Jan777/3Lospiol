package personaje;

import java.util.*;


public class Alianza {

	// Utilizado para la alianza con Personajes
	private LinkedList<Personaje> aliados;
//	private List<Jugador> jugadores;
	private String nombre;

	// Constructores
	public Alianza(String nombre/*, Jugador primerJugadorAliado*/) {
	//	this.jugadores = new LinkedList<Jugador>();
	//	this.agregarJugador(primerJugadorAliado);
		this.nombre = nombre;
	}

	// Metodos adicionales
//	public boolean eliminarJugador(/*Jugador jugadorAEliminar*/) {
//		return jugadores.remove(jugadorAEliminar);
//	}

//	public void agregarJugador(Jugador jugador) {
//		if (jugadores.size() < 50)
//			this.jugadores.add(jugador);
//	}

	// Métodos para una alianza con Personajes

	public Alianza() {
		aliados = new LinkedList<>();
	}

	public void agregarAliado(Personaje p) {
		this.aliados.add(p);
	}

	public LinkedList<Personaje> getAliados() {
		return aliados;
	}

	public void setAliados(LinkedList<Personaje> aliados) {
		this.aliados = aliados;
	}

	public void removerAliado(Personaje p) {
		this.aliados.remove(p);
	}

	public int getCantidadDeAliados() {
		return this.aliados.size();
	}
	//
}
