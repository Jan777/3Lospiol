package mapa;

import personaje.Jugador;

public class CiudadElfo extends Mapa { // esquina abajo der

	private static final int LARGO = 100;
	private static final int ALTO = 100;
	private Punto puntoInicial = new Punto(0, super.getAncho());

	// Metodos adicionales
	public void aparecerEnCiudadOrco(Jugador jugador) {
		jugador.getPosicion().setX(super.getAncho() - 50);
		jugador.getPosicion().setY(50);
	}

}
