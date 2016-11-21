package mapa;

import gui.Mapa2;
import jugador.Jugador;

public class CiudadElfo extends Mapa2 { // esquina abajo der

	private static final int LARGO = 100;
	private static final int ALTO = 100;
	private Punto puntoInicial = new Punto(0, super.getLargo());

	// Metodos adicionales
	public void aparecerEnCiudadOrco(Jugador jugador) {
		jugador.getPosicion().setX(super.getLargo() - 50);
		jugador.getPosicion().setY(50);
	}

}
