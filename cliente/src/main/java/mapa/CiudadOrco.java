package mapa;

import gui.Mapa2;
import jugador.Jugador;

public class CiudadOrco extends Mapa2 { // esquina abajo izq

	private static final int LARGO = 100;
	private static final int ALTO = 100;
	private Punto puntoInicial = new Punto(0, 0);

	// Metodos adicionales
	public void aparecerEnCiudadOrco(Jugador jugador) {
		jugador.getPosicion().setX(50);
		jugador.getPosicion().setY(50);
	}

}
