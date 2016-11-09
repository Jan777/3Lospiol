package mapa;

import cliente.Jugador;

public class CiudadOrco extends Mapa { // esquina abajo izq

	private static final int LARGO = 100;
	private static final int ALTO = 100;
	private Punto puntoInicial = new Punto(0, 0);

	// Metodos adicionales
	public void aparecerEnCiudadOrco(Jugador jugador) {
		jugador.getPosicion().setX(50);
		jugador.getPosicion().setY(50);
	}

}
