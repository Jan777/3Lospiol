package hechizosYHabPaladin;

import personaje.HechizoOHab;
import personaje.Personaje;

public class TormentaDivina extends HechizoOHab {

	@Override
	public void afectar(Personaje personaje) {
		personaje.setVida(personaje.getSalud()-37);
	}

}
