package personaje;

import java.util.*;

public class Alianza {

	private List<Jugador> jugadores;
	private String nombre;

	public Alianza(String nombre,Jugador primerJugadorAliado) {
		this.jugadores =new LinkedList<Jugador>();
		this.agregarJugador(primerJugadorAliado);
		this.nombre=nombre;
	}
	
	public boolean eliminarJugador(Jugador jugadorAEliminar){
		return jugadores.remove(jugadorAEliminar);
	}
	
	public void agregarJugador(Jugador jugador) {
		if(jugadores.size()<50)
			this.jugadores.add(jugador);
	}
}
