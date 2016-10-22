package hechizosYHabPaladin;

import personaje.HechizoOHab;
import personaje.Personaje;

public class Sanar extends HechizoOHab{

	@Override
	public void afectar(Personaje personaje) {
		personaje.setVida(personaje.getSalud()+15);
	}

}
