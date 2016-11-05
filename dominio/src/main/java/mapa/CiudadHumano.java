package mapa;

import personaje.Jugador;

public class CiudadHumano extends Mapa { // esquina arriba der

	private static final int LARGO = 100;
	private static final int ALTO = 100;
	private Punto puntoInicial = new Punto(super.getAlto(), super.getAncho());

	// Metodos adicionales
	public void aparecerEnCiudadOrco(Jugador jugador) {
		jugador.getPosicion().setX(super.getAncho() - 50);
		jugador.getPosicion().setY(super.getAlto() - 50);
	}

}
